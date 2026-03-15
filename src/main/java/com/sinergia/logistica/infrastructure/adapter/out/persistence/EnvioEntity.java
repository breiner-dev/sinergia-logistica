package com.sinergia.logistica.infrastructure.adapter.out.persistence;

import com.sinergia.logistica.domain.model.EstadoEnvio;
import com.sinergia.logistica.domain.model.TipoLogistica;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("envios")
public class EnvioEntity implements Persistable<UUID> {

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

    @Transient
    private boolean isNew;

    public EnvioEntity() {
        this.isNew = false;
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
        this.isNew = true;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(BigDecimal precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getPrecioConDescuento() {
        return precioConDescuento;
    }

    public void setPrecioConDescuento(BigDecimal precioConDescuento) {
        this.precioConDescuento = precioConDescuento;
    }

    public TipoLogistica getTipoLogistica() {
        return tipoLogistica;
    }

    public void setTipoLogistica(TipoLogistica tipoLogistica) {
        this.tipoLogistica = tipoLogistica;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public String getNumeroFlota() {
        return numeroFlota;
    }

    public void setNumeroFlota(String numeroFlota) {
        this.numeroFlota = numeroFlota;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
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