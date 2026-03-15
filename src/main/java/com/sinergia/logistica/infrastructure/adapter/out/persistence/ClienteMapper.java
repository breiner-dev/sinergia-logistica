package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.Cliente;

public final class ClienteMapper {

    private ClienteMapper() {
    }

    public static ClienteEntity toEntity(Cliente cliente) {
        return new ClienteEntity(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono()
        );
    }

    public static Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNombre(),
                entity.getCorreo(),
                entity.getTelefono()
        );
    }
}
