package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ObtenerEnviosUseCase {
    Flux<RespuestaEnvio> findAll();

    Mono<Void> eliminar(UUID id);

    Mono<RespuestaEnvio> buscarPorId(UUID id);
}
