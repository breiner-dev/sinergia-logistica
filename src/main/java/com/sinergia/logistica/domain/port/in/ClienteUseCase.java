package com.sinergia.logistica.domain.port.in;

import com.sinergia.logistica.application.dto.ClienteResponse;
import reactor.core.publisher.Flux;

public interface ClienteUseCase {

    public Flux<ClienteResponse> listarTodos();
}
