package dev.davcode.jwt.controllers;

public record RegisterRequest(
        String email,
        String password,
        String name
) {
}
