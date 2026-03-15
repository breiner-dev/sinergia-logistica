package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.Cliente;
import com.sinergia.logistica.domain.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ClientePersistenceAdapter implements ClienteRepositoryPort {

    private final ClienteRepository repository;

    public ClientePersistenceAdapter(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Cliente> findById(UUID id) {
        return repository.findById(id)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public Flux<Cliente> findAll() {
        return repository.findAll()
                .map(ClienteMapper::toDomain);
    }
}
