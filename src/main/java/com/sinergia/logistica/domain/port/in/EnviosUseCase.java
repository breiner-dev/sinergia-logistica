package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.ActualizarEnvioRequest;
import com.sinergia.logistica.application.dto.CrearEnvioRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EnviosUseCase {
    Flux<RespuestaEnvio> findAll();

    Mono<RespuestaEnvio> create(CrearEnvioRequest request);

    Mono<Void> eliminar(UUID id);

    Mono<RespuestaEnvio> buscarPorId(UUID id);

    Mono<RespuestaEnvio> actualizar(UUID id, ActualizarEnvioRequest request);
}
