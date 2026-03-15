package com.sinergia.logistica.domain.model;

import java.util.UUID;

public class Cliente {

    private UUID id;
    private String nombre;
    private String correo;
    private String telefono;

    public Cliente() {
    }

    public Cliente(UUID id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public static Cliente crear(String nombre, String correo, String telefono) {
        return new Cliente(UUID.randomUUID(), nombre, correo, telefono);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
}