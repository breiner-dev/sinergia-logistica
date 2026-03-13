package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.EstadoEnvio;
import com.sinergia.logistica.domain.model.TipoLogistica;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("envios")
public class EnvioEntity {

    @Id
    private UUID id;
    private String numeroGuia;
    private UUID clienteId;
    private String tipoProducto;
    private Integer cantidad;
    private LocalDateTime fechaRegistro;
    private LocalDate fechaEntrega;
    private BigDecimal precioEnvio;
    private BigDecimal porcentajeDescuento;
    private BigDecimal precioConDescuento;
    private TipoLogistica tipoLogistica;
    private EstadoEnvio estado;
    private String nombreBodega;
    private String placaVehiculo;
    private String nombrePuerto;
    private String numeroFlota;

    public EnvioEntity() {
    }

    public EnvioEntity(
            UUID id,
            String numeroGuia,
            UUID clienteId,
            String tipoProducto,
            Integer cantidad,
            LocalDateTime fechaRegistro,
            LocalDate fechaEntrega,
            BigDecimal precioEnvio,
            BigDecimal porcentajeDescuento,
            BigDecimal precioConDescuento,
            TipoLogistica tipoLogistica,
            EstadoEnvio estado,
            String nombreBodega,
            String placaVehiculo,
            String nombrePuerto,
            String numeroFlota
    ) {
        this.id = id;
        this.numeroGuia = numeroGuia;
        this.clienteId = clienteId;
        this.tipoProducto = tipoProducto;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
        this.fechaEntrega = fechaEntrega;
        this.precioEnvio = precioEnvio;
        this.porcentajeDescuento = porcentajeDescuento;
        this.precioConDescuento = precioConDescuento;
        this.tipoLogistica = tipoLogistica;
        this.estado = estado;
        this.nombreBodega = nombreBodega;
        this.placaVehiculo = placaVehiculo;
        this.nombrePuerto = nombrePuerto;
        this.numeroFlota = numeroFlota;
    }

    public UUID getId() {
        return id;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public BigDecimal getPrecioEnvio() {
        return precioEnvio;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public BigDecimal getPrecioConDescuento() {
        return precioConDescuento;
    }

    public TipoLogistica getTipoLogistica() {
        return tipoLogistica;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public String getNumeroFlota() {
        return numeroFlota;
    }

    @Override
    public String toString() {
        return "EnvioEntity{" +
                "id=" + id +
                ", numeroGuia='" + numeroGuia + '\'' +
                ", clienteId=" + clienteId +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", cantidad=" + cantidad +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaEntrega=" + fechaEntrega +
                ", precioEnvio=" + precioEnvio +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", precioConDescuento=" + precioConDescuento +
                ", tipoLogistica=" + tipoLogistica +
                ", estado=" + estado +
                ", nombreBodega='" + nombreBodega + '\'' +
                ", placaVehiculo='" + placaVehiculo + '\'' +
                ", nombrePuerto='" + nombrePuerto + '\'' +
                ", numeroFlota='" + numeroFlota + '\'' +
                '}';
    }
}