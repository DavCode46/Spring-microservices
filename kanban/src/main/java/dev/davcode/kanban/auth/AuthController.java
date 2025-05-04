package dev.davcode.kanban.auth;

import dev.davcode.kanban.dto.TokenResponseDTO;
import dev.davcode.kanban.request.LoginRequest;
import dev.davcode.kanban.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody final RegisterRequest request) {
        final TokenResponseDTO token = service.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> authenticate(@RequestBody final LoginRequest request) {
        final TokenResponseDTO token = service.login(request);
        return ResponseEntity.ok(token);
    }


    @PostMapping("/refresh")
    public TokenResponseDTO refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        return service.refreshToken(authHeader);
    }
}
