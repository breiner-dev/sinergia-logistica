package com.sinergia.logistica.infrastructure.adapter.in.messaging;

import com.sinergia.logistica.infrastructure.adapter.out.messaging.EventoEnvioCreado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EnvioRabbitConsumer {

    private static final Logger log = LoggerFactory.getLogger(EnvioRabbitConsumer.class);

    //@RabbitListener(queues = "${app.rabbit.queue}")
    public void consumirEnvioCreado(EventoEnvioCreado evento) {
        log.info("Evento consumido desde RabbitMQ. guia={}, tipo={}",
                evento.numeroGuia(),
                evento.tipoLogistica());
    }
}
