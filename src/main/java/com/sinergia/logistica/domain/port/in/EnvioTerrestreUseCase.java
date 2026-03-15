package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.CrearEnvioTerrestreRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import reactor.core.publisher.Mono;

public interface EnvioTerrestreUseCase {
    Mono<RespuestaEnvio> create(CrearEnvioTerrestreRequest request);
}
