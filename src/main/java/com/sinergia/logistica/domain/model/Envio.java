package com.sinergia.logistica.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Envio {

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

    public Envio() {
    }

    public Envio(
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

    public static Envio crearTerrestre(
            UUID clienteId,
            String tipoProducto,
            Integer cantidad,
            LocalDate fechaEntrega,
            BigDecimal precioEnvio,
            String nombreBodega,
            String placaVehiculo,
            String numeroGuia,
            BigDecimal precioConDescuento,
            BigDecimal porcentajeDescuento
    ) {
        return new Envio(
                UUID.randomUUID(),
                numeroGuia,
                clienteId,
                tipoProducto,
                cantidad,
                LocalDateTime.now(),
                fechaEntrega,
                precioEnvio,
                porcentajeDescuento,
                precioConDescuento,
                TipoLogistica.TERRESTRE,
                EstadoEnvio.CREADO,
                nombreBodega,
                placaVehiculo,
                null,
                null
        );
    }

    public static Envio crearMaritimo(
            UUID clienteId,
            String tipoProducto,
            Integer cantidad,
            LocalDate fechaEntrega,
            BigDecimal precioEnvio,
            String nombrePuerto,
            String numeroFlota,
            String numeroGuia,
            BigDecimal precioConDescuento,
            BigDecimal porcentajeDescuento
    ) {
        return new Envio(
                UUID.randomUUID(),
                numeroGuia,
                clienteId,
                tipoProducto,
                cantidad,
                LocalDateTime.now(),
                fechaEntrega,
                precioEnvio,
                porcentajeDescuento,
                precioConDescuento,
                TipoLogistica.MARITIMA,
                EstadoEnvio.CREADO,
                null,
                null,
                nombrePuerto,
                numeroFlota
        );
    }

    public void actualizarTerrestre(
            String tipoProducto,
            Integer cantidad,
            LocalDate fechaEntrega,
            BigDecimal precioEnvio,
            String nombreBodega,
            String placaVehiculo,
            BigDecimal porcentajeDescuento,
            BigDecimal precioConDescuento
    ) {

        if (this.tipoLogistica != TipoLogistica.TERRESTRE) {
            throw new IllegalStateException("El envío no es de tipo terrestre");
        }

        this.tipoProducto = tipoProducto;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
        this.precioEnvio = precioEnvio;
        this.nombreBodega = nombreBodega;
        this.placaVehiculo = placaVehiculo;

        this.porcentajeDescuento = porcentajeDescuento;
        this.precioConDescuento = precioConDescuento;
    }

    public void actualizarMaritimo(
            String tipoProducto,
            Integer cantidad,
            LocalDate fechaEntrega,
            BigDecimal precioEnvio,
            String nombrePuerto,
            String numeroFlota,
            BigDecimal porcentajeDescuento,
            BigDecimal precioConDescuento
    ) {

        if (this.tipoLogistica != TipoLogistica.MARITIMA) {
            throw new IllegalStateException("El envío no es de tipo marítimo");
        }

        this.tipoProducto = tipoProducto;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
        this.precioEnvio = precioEnvio;
        this.nombrePuerto = nombrePuerto;
        this.numeroFlota = numeroFlota;

        this.porcentajeDescuento = porcentajeDescuento;
        this.precioConDescuento = precioConDescuento;
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

}