package com.sinergia.logistica.domain.port.out;

import com.sinergia.logistica.domain.model.Envio;
import reactor.core.publisher.Mono;

public interface PublicadorEventoEnvioPort {
    Mono<Void> crearPublicacion(Envio shipment);
}
