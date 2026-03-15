package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.Envio;
import com.sinergia.logistica.domain.port.out.EnvioRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
                .map(EnvioMapper::toDomain);
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

    @Override
    public Mono<Envio> findById(UUID id) {
        return repository.findById(id)
                .map(EnvioMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Envio> update(Envio envio) {
        return repository.findById(envio.getId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Envío no encontrado")))
                .flatMap(entity -> {
                    entity.setNumeroGuia(envio.getNumeroGuia());
                    entity.setClienteId(envio.getClienteId());
                    entity.setTipoProducto(envio.getTipoProducto());
                    entity.setCantidad(envio.getCantidad());
                    entity.setFechaRegistro(envio.getFechaRegistro());
                    entity.setFechaEntrega(envio.getFechaEntrega());
                    entity.setPrecioEnvio(envio.getPrecioEnvio());
                    entity.setPorcentajeDescuento(envio.getPorcentajeDescuento());
                    entity.setPrecioConDescuento(envio.getPrecioConDescuento());
                    entity.setTipoLogistica(envio.getTipoLogistica());
                    entity.setEstado(envio.getEstado());
                    entity.setNombreBodega(envio.getNombreBodega());
                    entity.setPlacaVehiculo(envio.getPlacaVehiculo());
                    entity.setNombrePuerto(envio.getNombrePuerto());
                    entity.setNumeroFlota(envio.getNumeroFlota());

                    return repository.save(entity);
                })
                .map(EnvioMapper::toDomain);
    }
}
