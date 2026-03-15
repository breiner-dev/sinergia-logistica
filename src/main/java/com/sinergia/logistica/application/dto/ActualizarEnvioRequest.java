package com.sinergia.logistica.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ActualizarEnvioRequest(
        String tipoProducto,
        Integer cantidad,
        LocalDate fechaEntrega,
        BigDecimal precioEnvio,
        String nombreBodega,
        String placaVehiculo,
        String nombrePuerto,
        String numeroFlota
) {
}
