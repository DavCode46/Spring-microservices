package dev.davcode.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> finAllValidIsFalseOrRevokedIsFalseByUserId(Long id);

    Optional<Token> findByToken(String jwtToken);
}
