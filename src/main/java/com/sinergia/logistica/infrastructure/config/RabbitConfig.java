package com.sinergia.logistica.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.rabbit.exchange}")
    private String exchangeName;

    @Value("${app.rabbit.queue}")
    private String queueName;

    @Value("${app.rabbit.routing-key}")
    private String routingKey;

    @Bean
    public DirectExchange envioExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue envioQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding envioBinding(Queue envioQueue, DirectExchange envioExchange) {
        return BindingBuilder
                .bind(envioQueue)
                .to(envioExchange)
                .with(routingKey);
    }

}
