package com.sinergia.logistica.infrastructure.adapter.in.web;

import com.sinergia.logistica.application.dto.CrearEnvioMaritimoRequest;
import com.sinergia.logistica.application.dto.CrearEnvioTerrestreRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import com.sinergia.logistica.domain.port.in.CrearEnvioMaritimoUseCase;
import com.sinergia.logistica.domain.port.in.CrearEnvioTerrestreUseCase;
import com.sinergia.logistica.domain.port.in.ObtenerEnviosUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final CrearEnvioTerrestreUseCase crearEnvioTerrestreUseCase;
    private final CrearEnvioMaritimoUseCase crearEnvioMaritimoUseCase;
    private final ObtenerEnviosUseCase obtenerEnviosUseCase;

    public EnvioController(
            CrearEnvioTerrestreUseCase crearEnvioTerrestreUseCase,
            CrearEnvioMaritimoUseCase crearEnvioMaritimoUseCase,
            ObtenerEnviosUseCase obtenerEnviosUseCase
    ) {
        this.crearEnvioTerrestreUseCase = crearEnvioTerrestreUseCase;
        this.crearEnvioMaritimoUseCase = crearEnvioMaritimoUseCase;
        this.obtenerEnviosUseCase = obtenerEnviosUseCase;
    }

    @PostMapping("/terrestre")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RespuestaEnvio> createLand(@Valid @RequestBody CrearEnvioTerrestreRequest request) {
        return crearEnvioTerrestreUseCase.create(request);
    }

    @PostMapping("/maritimo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RespuestaEnvio> createMaritime(@Valid @RequestBody CrearEnvioMaritimoRequest request) {
        return crearEnvioMaritimoUseCase.create(request);
    }

    @GetMapping
    public Flux<RespuestaEnvio> findAll() {
        return obtenerEnviosUseCase.findAll();
    }
}