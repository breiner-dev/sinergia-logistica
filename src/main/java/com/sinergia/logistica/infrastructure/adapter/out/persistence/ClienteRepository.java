package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ClienteRepository extends ReactiveCrudRepository<ClienteEntity, UUID> {
}