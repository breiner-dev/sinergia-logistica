package com.sinergia.logistica.application.dto;

import com.sinergia.logistica.domain.model.TipoLogistica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CrearEnvioRequest(
        UUID clienteId,
        String tipoProducto,
        Integer cantidad,
        LocalDate fechaEntrega,
        BigDecimal precioEnvio,
        String numeroGuia,
        TipoLogistica tipoLogistica,
        String nombreBodega,
        String placaVehiculo,
        String nombrePuerto,
        String numeroFlota
) {
}
