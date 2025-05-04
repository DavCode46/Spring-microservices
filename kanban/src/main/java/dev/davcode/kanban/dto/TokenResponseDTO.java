package dev.davcode.kanban.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponseDTO(
        @JsonProperty("access_token")
        String accesToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {
}
