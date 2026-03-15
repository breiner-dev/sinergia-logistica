package com.sinergia.logistica.application.usecase;

import com.sinergia.logistica.application.dto.ClienteResponse;
import com.sinergia.logistica.domain.model.Cliente;
import com.sinergia.logistica.domain.port.in.ClienteUseCase;
import com.sinergia.logistica.domain.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ClienteUseCaseService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteUseCaseService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Flux<ClienteResponse> listarTodos() {
        return clienteRepositoryPort.findAll()
                .map(this::aResponse);
    }

    private ClienteResponse aResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }
}