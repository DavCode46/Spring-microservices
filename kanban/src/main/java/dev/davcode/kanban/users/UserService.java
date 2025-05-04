package dev.davcode.kanban.users;

import dev.davcode.kanban.dto.TokenResponseDTO;
import dev.davcode.kanban.request.LoginRequest;
import dev.davcode.kanban.request.RegisterRequest;
import dev.davcode.kanban.token.JwtService;
import dev.davcode.kanban.token.Token;
import dev.davcode.kanban.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


}
