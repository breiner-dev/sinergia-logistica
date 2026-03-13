package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.Envio;
import com.sinergia.logistica.domain.port.out.EnvioRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ShipmentPersistenceAdapter implements EnvioRepositoryPort {

    private final EnvioRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ShipmentPersistenceAdapter.class);

    public ShipmentPersistenceAdapter(EnvioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Envio> save(Envio envio) {
        return repository.save(EnvioMapper.toEntity(envio))
                .doOnSubscribe(sub -> System.out.println("Guardando envío..."))
                .doOnNext(entity -> System.out.println("Guardado en BD: " + entity))
                .doOnError(error -> System.out.println("Error al guardar: " + error.getMessage()))
                .map(entity -> {
                    Envio envioGuardado = EnvioMapper.toDomain(entity); // breakpoint aquí
                    return envioGuardado;
                });
    }

    @Override
    public Mono<Boolean> existsByGuideNumber(String numeroGuia) {
        return repository.existsByNumeroGuia(numeroGuia);
    }

    @Override
    public Flux<Envio> findAll() {
        return repository.findAll()
                .map(EnvioMapper::toDomain);
    }
}
