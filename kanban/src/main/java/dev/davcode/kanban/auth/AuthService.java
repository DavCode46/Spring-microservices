package dev.davcode.kanban.auth;

import dev.davcode.kanban.dto.TokenResponseDTO;
import dev.davcode.kanban.request.LoginRequest;
import dev.davcode.kanban.request.RegisterRequest;
import dev.davcode.kanban.token.JwtService;
import dev.davcode.kanban.token.Token;
import dev.davcode.kanban.token.TokenRepository;
import dev.davcode.kanban.users.User;
import dev.davcode.kanban.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public TokenResponseDTO register(RegisterRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        User savedUser = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);

        return new TokenResponseDTO(jwtToken, refreshToken);
    }

    public TokenResponseDTO login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new TokenResponseDTO(jwtToken, refreshToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());

        if(!validUserTokens.isEmpty()){
            for(Token token : validUserTokens) {
                token.setExpired(false);
                token.setRevoked(false);
            }

            tokenRepository.saveAll(validUserTokens);
        }
    }


    public TokenResponseDTO refreshToken(String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid token");
        }

        String refreshToken = authHeader.substring(7);
        String userEmail = jwtService.extractEmail(refreshToken);

        if(userEmail == null) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        final User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail));

        if(!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        final String accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        return new TokenResponseDTO(accessToken, refreshToken);
    }
}
