package com.sinergia.logistica.domain.port.out;

import com.sinergia.logistica.domain.model.Cliente;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClienteRepositoryPort {
    Mono<Cliente> findById(UUID id);
}