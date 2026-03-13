package com.sinergia.logistica.infrastructure.adapter.out.messaging;

import com.sinergia.logistica.domain.model.Envio;
import com.sinergia.logistica.domain.port.out.PublicadorEventoEnvioPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class EnvioEventPublisherAdapter implements PublicadorEventoEnvioPort {

    private static final Logger log = LoggerFactory.getLogger(EnvioEventPublisherAdapter.class);

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public EnvioEventPublisherAdapter(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbit.exchange}") String exchange,
            @Value("${app.rabbit.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public Mono<Void> crearPublicacion(Envio envio) {
        return Mono.fromRunnable(() -> {
                    EventoEnvioCreado evento = new EventoEnvioCreado(
                            envio.getId(),
                            envio.getNumeroGuia(),
                            envio.getClienteId(),
                            envio.getTipoLogistica(),
                            envio.getCantidad(),
                            envio.getFechaRegistro()
                    );

                    rabbitTemplate.convertAndSend(exchange, routingKey, evento);
                    log.info("Evento enviado a RabbitMQ. guia={}", envio.getNumeroGuia());
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
