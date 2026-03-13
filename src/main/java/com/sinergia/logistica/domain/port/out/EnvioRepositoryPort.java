package com.sinergia.logistica.domain.port.out;

import com.sinergia.logistica.domain.model.Envio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnvioRepositoryPort {
    Mono<Envio> save(Envio envio);

    Mono<Boolean> existsByGuideNumber(String guideNumber);

    Flux<Envio> findAll();
}
