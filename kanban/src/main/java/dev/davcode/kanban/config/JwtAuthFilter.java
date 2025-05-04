package dev.davcode.kanban.config;

import dev.davcode.kanban.token.JwtService;
import dev.davcode.kanban.token.Token;
import dev.davcode.kanban.token.TokenRepository;
import dev.davcode.kanban.users.User;
import dev.davcode.kanban.users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();
        String info =  request.getPathInfo();

        String fullPath = (path == null ? "" : path) + (info == null ? "" : info);
        return fullPath.startsWith("/auth/");
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authHeader.substring(7);
        String email = jwtService.extractEmail(jwtToken);

        if(email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        Token token = tokenRepository.findByToken(jwtToken).orElse(null);

        if(token == null || token.isExpired() || token.isRevoked()){
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Optional<User> user = userRepository.findByEmail(userDetails.getUsername());

        if(user.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isTokenValid = jwtService.isTokenValid(jwtToken, user.get());
        if(!isTokenValid){
            return;
        }

        var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
