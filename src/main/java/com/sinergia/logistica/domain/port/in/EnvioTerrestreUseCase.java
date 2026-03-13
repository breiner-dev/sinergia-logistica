package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.ActualizarEnvioTerrestreRequest;
import com.sinergia.logistica.application.dto.CrearEnvioTerrestreRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EnvioTerrestreUseCase {
    Mono<RespuestaEnvio> create(CrearEnvioTerrestreRequest request);

    Mono<RespuestaEnvio> actualizar(UUID id, ActualizarEnvioTerrestreRequest request);
}
