package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Flux;

public interface ObtenerEnviosUseCase {
    Flux<RespuestaEnvio> findAll();
}
