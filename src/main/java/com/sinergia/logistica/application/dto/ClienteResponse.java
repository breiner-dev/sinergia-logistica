package com.sinergia.logistica.application.dto;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nombre,
        String correo,
        String telefono
) {
}
