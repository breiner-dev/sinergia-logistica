package com.sinergia.logistica.infrastructure.adapter.in.web.controller;

import com.sinergia.logistica.application.dto.*;
import com.sinergia.logistica.domain.port.in.EnvioMaritimoUseCase;
import com.sinergia.logistica.domain.port.in.EnvioTerrestreUseCase;
import com.sinergia.logistica.domain.port.in.ObtenerEnviosUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioTerrestreUseCase envioTerrestreUseCase;
    private final EnvioMaritimoUseCase envioMaritimoUseCase;
    private final ObtenerEnviosUseCase obtenerEnviosUseCase;

    public EnvioController(
            EnvioTerrestreUseCase envioTerrestreUseCase,
            EnvioMaritimoUseCase envioMaritimoUseCase,
            ObtenerEnviosUseCase obtenerEnviosUseCase
    ) {
        this.envioTerrestreUseCase = envioTerrestreUseCase;
        this.envioMaritimoUseCase = envioMaritimoUseCase;
        this.obtenerEnviosUseCase = obtenerEnviosUseCase;
    }

    @PostMapping("/terrestre")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RespuestaEnvio> createLand(
            @Valid @RequestBody CrearEnvioTerrestreRequest request
    ) {
        return envioTerrestreUseCase.create(request);
    }

    @PostMapping("/maritimo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RespuestaEnvio> createMaritime(
            @Valid @RequestBody CrearEnvioMaritimoRequest request
    ) {
        return envioMaritimoUseCase.create(request);
    }

    @GetMapping
    public Flux<RespuestaEnvio> findAll() {
        return obtenerEnviosUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<RespuestaEnvio> findById(@PathVariable UUID id) {
        return obtenerEnviosUseCase.buscarPorId(id);
    }

    @PutMapping("/terrestre/{id}")
    public Mono<RespuestaEnvio> updateLand(
            @PathVariable UUID id,
            @Valid @RequestBody ActualizarEnvioTerrestreRequest request
    ) {
        return envioTerrestreUseCase.actualizar(id, request);
    }

    @PutMapping("/maritimo/{id}")
    public Mono<RespuestaEnvio> updateMaritime(
            @PathVariable UUID id,
            @Valid @RequestBody ActualizarEnvioMaritimoRequest request
    ) {
        return envioMaritimoUseCase.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable UUID id) {
        return obtenerEnviosUseCase.eliminar(id);
    }

}
