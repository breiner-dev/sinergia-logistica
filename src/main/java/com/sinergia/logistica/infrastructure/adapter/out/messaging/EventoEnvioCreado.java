package com.sinergia.logistica.infrastructure.adapter.out.messaging;

import com.sinergia.logistica.domain.model.TipoLogistica;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoEnvioCreado(
        UUID id,
        String numeroGuia,
        UUID clienteId,
        TipoLogistica tipoLogistica,
        Integer cantidad,
        LocalDateTime fechaRegistro
) {
}
