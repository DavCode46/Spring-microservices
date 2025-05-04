package dev.davcode.jwt.controllers;

public record LoginRequest(
        String email,
        String password
) {
}
