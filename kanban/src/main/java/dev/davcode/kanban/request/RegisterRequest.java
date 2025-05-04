package dev.davcode.kanban.request;

public record RegisterRequest(
        String email,
        String password
) {
}
