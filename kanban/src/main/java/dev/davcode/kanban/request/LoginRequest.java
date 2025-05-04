package dev.davcode.kanban.request;

public record LoginRequest(
        String email,
        String password
) {
}
