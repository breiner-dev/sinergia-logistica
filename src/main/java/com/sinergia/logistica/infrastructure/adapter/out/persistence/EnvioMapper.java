package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.Envio;

public final class EnvioMapper {

    private EnvioMapper() {
    }

    public static EnvioEntity toEntity(Envio envio) {
        return new EnvioEntity(
                envio.getId(),
                envio.getNumeroGuia(),
                envio.getClienteId(),
                envio.getTipoProducto(),
                envio.getCantidad(),
                envio.getFechaRegistro(),
                envio.getFechaEntrega(),
                envio.getPrecioEnvio(),
                envio.getPorcentajeDescuento(),
                envio.getPrecioConDescuento(),
                envio.getTipoLogistica(),
                envio.getEstado(),
                envio.getNombreBodega(),
                envio.getPlacaVehiculo(),
                envio.getNombrePuerto(),
                envio.getNumeroFlota()
        );
    }

    public static Envio toDomain(EnvioEntity entidad) {
        return new Envio(
                entidad.getId(),
                entidad.getNumeroGuia(),
                entidad.getClienteId(),
                entidad.getTipoProducto(),
                entidad.getCantidad(),
                entidad.getFechaRegistro(),
                entidad.getFechaEntrega(),
                entidad.getPrecioEnvio(),
                entidad.getPorcentajeDescuento(),
                entidad.getPrecioConDescuento(),
                entidad.getTipoLogistica(),
                entidad.getEstado(),
                entidad.getNombreBodega(),
                entidad.getPlacaVehiculo(),
                entidad.getNombrePuerto(),
                entidad.getNumeroFlota()
        );
    }
}