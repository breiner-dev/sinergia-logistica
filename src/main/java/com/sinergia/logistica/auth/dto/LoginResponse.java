package com.sinergia.logistica.auth.dto;

public record LoginResponse(
        String token,
        String type,
        Long expiresIn
) {
}