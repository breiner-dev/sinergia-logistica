package com.sinergia.logistica.application.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ActualizarEnvioTerrestreRequest(

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
        String nombreBodega,

        @NotBlank
        @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "La placa debe tener formato AAA111")
        String placaVehiculo
) {
}
