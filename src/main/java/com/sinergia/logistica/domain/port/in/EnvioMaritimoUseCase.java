package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.ActualizarEnvioMaritimoRequest;
import com.sinergia.logistica.application.dto.CrearEnvioMaritimoRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EnvioMaritimoUseCase {
    Mono<RespuestaEnvio> create(CrearEnvioMaritimoRequest request);

    Mono<RespuestaEnvio> actualizar(UUID id, ActualizarEnvioMaritimoRequest request);
}
