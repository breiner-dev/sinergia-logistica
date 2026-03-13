package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EnvioRepository extends ReactiveCrudRepository<EnvioEntity, UUID> {

    Mono<Boolean> existsByNumeroGuia(String numeroGuia);

}
