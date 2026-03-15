package com.sinergia.logistica.infrastructure.adapter.in.web.controller;

import com.sinergia.logistica.auth.dto.LoginRequest;
import com.sinergia.logistica.auth.dto.LoginResponse;
import com.sinergia.logistica.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class ClienteController {

    private final ClienteService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Mono<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
