package com.sinergia.logistica.auth.dto;

import com.sinergia.logistica.domain.model.TipoLogistica;

import java.math.BigDecimal;
import java.util.UUID;

public record EnvioResponse(
        UUID id,
        String numeroGuia,
        UUID clienteId,
        BigDecimal precioFinal,
        TipoLogistica tipoLogistica
) {
}

