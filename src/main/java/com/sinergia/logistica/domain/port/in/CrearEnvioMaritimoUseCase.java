package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.CrearEnvioMaritimoRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Mono;

public interface CrearEnvioMaritimoUseCase {
    Mono<RespuestaEnvio> create(CrearEnvioMaritimoRequest request);
}
