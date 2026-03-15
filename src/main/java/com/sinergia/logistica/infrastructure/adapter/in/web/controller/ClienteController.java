package com.sinergia.logistica.infrastructure.adapter.in.web.controller;

import com.sinergia.logistica.application.dto.ClienteResponse;
import com.sinergia.logistica.domain.port.in.ClienteUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/auth")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @GetMapping
    public Flux<ClienteResponse> findAll() {
        return clienteUseCase.listarTodos();
    }
}
