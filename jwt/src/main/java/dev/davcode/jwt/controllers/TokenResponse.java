package dev.davcode.jwt.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        @JsonProperty("access_token")
        String accesToken,
        @JsonProperty("refreshToken")
        String refreshToken
) {
}
