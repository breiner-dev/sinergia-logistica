package com.sinergia.logistica.auth.service;

import com.sinergia.logistica.auth.dto.LoginRequest;
import com.sinergia.logistica.auth.dto.LoginResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final JwtService jwtService;

    private static final String DEMO_USERNAME = "admin";
    private static final String DEMO_PASSWORD = "secret"; // secret
    private static final String DEMO_ROLE = "ADMIN";

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public Mono<LoginResponse> login(LoginRequest request) {
        boolean validUser = DEMO_USERNAME.equals(request.username());
        boolean validPassword = DEMO_PASSWORD.equals(request.password());

        if (!validUser || !validPassword) {
            return Mono.error(new IllegalArgumentException("Credenciales inválidas"));
        }

        String token = jwtService.generateToken(DEMO_USERNAME, DEMO_ROLE);

        return Mono.just(new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpiration()
        ));
    }
}
