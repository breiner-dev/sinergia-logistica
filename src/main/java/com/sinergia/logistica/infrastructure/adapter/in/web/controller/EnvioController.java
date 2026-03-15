package com.sinergia.logistica.infrastructure.adapter.in.web.controller;

import com.sinergia.logistica.application.dto.ActualizarEnvioRequest;
import com.sinergia.logistica.application.dto.CrearEnvioRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import com.sinergia.logistica.domain.port.in.EnviosUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnviosUseCase enviosUseCase;

    public EnvioController(
            EnviosUseCase enviosUseCase
    ) {
        this.enviosUseCase = enviosUseCase;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RespuestaEnvio> createLand(
            @Valid @RequestBody CrearEnvioRequest request
    ) {
        return enviosUseCase.create(request);
    }

    @GetMapping
    public Flux<RespuestaEnvio> findAll() {
        return enviosUseCase.findAll();
    }

    @GetMapping("/{id}")
    public Mono<RespuestaEnvio> findById(@PathVariable UUID id) {
        return enviosUseCase.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Mono<RespuestaEnvio> updateMaritime(
            @PathVariable UUID id,
            @Valid @RequestBody ActualizarEnvioRequest request
    ) {
        return enviosUseCase.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable UUID id) {
        return enviosUseCase.eliminar(id);
    }

}
