package com.sinergia.logistica.application.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CrearEnvioMaritimoRequest(

        @NotNull
        UUID clienteId,

        @NotBlank
        String tipoProducto,

        @NotNull
        @Min(1)
        Integer cantidad,

        @NotNull
        LocalDate fechaEntrega,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal precioEnvio,

        @NotBlank
        String nombrePuerto,

        @NotBlank
        @Pattern(regexp = "^[A-Z]{3}[0-9]{4}[A-Z]$", message = "La flota debe tener formato AAA1111A")
        String numeroFlota,

        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9]{10}$", message = "La guía debe ser alfanumérica de 10 caracteres")
        String numeroGuia
) {
}