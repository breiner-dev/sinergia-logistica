package com.sinergia.logistica.application.dto;

import com.sinergia.logistica.domain.model.EstadoEnvio;
import com.sinergia.logistica.domain.model.TipoLogistica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record RespuestaEnvio(
        UUID id,
        String numeroGuia,
        UUID clienteId,
        String tipoProducto,
        Integer cantidad,
        LocalDateTime fechaRegistro,
        LocalDate fechaEntrega,
        BigDecimal precioEnvio,
        BigDecimal porcentajeDescuento,
        BigDecimal precioConDescuento,
        TipoLogistica tipoLogistica,
        EstadoEnvio estado,
        String nombreBodega,
        String placaVehiculo,
        String nombrePuerto,
        String numeroFlota
) {
}