package com.sinergia.logistica.application.usecase;

import com.sinergia.logistica.application.dto.CrearEnvioMaritimoRequest;
import com.sinergia.logistica.application.dto.CrearEnvioTerrestreRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import com.sinergia.logistica.domain.model.Envio;
import com.sinergia.logistica.domain.port.in.CrearEnvioMaritimoUseCase;
import com.sinergia.logistica.domain.port.in.CrearEnvioTerrestreUseCase;
import com.sinergia.logistica.domain.port.in.ObtenerEnviosUseCase;
import com.sinergia.logistica.domain.port.out.EnvioRepositoryPort;
import com.sinergia.logistica.domain.port.out.PublicadorEventoEnvioPort;
import com.sinergia.logistica.domain.service.DominioEnvioService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class EnvioUseCaseService implements CrearEnvioTerrestreUseCase, CrearEnvioMaritimoUseCase, ObtenerEnviosUseCase {

    private final EnvioRepositoryPort envioRepositoryPort;
    private final PublicadorEventoEnvioPort publicadorEventoEnvioPort;
    private final DominioEnvioService servicioDominioEnvio = new DominioEnvioService();

    public EnvioUseCaseService(
            EnvioRepositoryPort envioRepositoryPort,
            PublicadorEventoEnvioPort publicadorEventoEnvioPort
    ) {
        this.envioRepositoryPort = envioRepositoryPort;
        this.publicadorEventoEnvioPort = publicadorEventoEnvioPort;
    }

    @Override
    public Mono<RespuestaEnvio> create(CrearEnvioTerrestreRequest request) {
        return envioRepositoryPort.existsByGuideNumber(request.numeroGuia())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new IllegalArgumentException("La guía ya existe"));
                    }

                    BigDecimal descuento = servicioDominioEnvio.calcularPorcentajeDescuentoTerrestre(request.cantidad());
                    BigDecimal precioConDescuento = servicioDominioEnvio.calcularPrecioConDescuento(request.precioEnvio(), descuento);

                    Envio envio = Envio.crearTerrestre(
                            request.clienteId(),
                            request.tipoProducto(),
                            request.cantidad(),
                            request.fechaEntrega(),
                            request.precioEnvio(),
                            request.nombreBodega(),
                            request.placaVehiculo(),
                            request.numeroGuia(),
                            precioConDescuento,
                            descuento
                    );

                    return envioRepositoryPort.save(envio)
                            .flatMap(guardado -> publicadorEventoEnvioPort.crearPublicacion(guardado).thenReturn(guardado))
                            .map(this::aRespuesta);
                });
    }

    @Override
    public Mono<RespuestaEnvio> create(CrearEnvioMaritimoRequest request) {
        return envioRepositoryPort.existsByGuideNumber(request.numeroGuia())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new IllegalArgumentException("La guía ya existe"));
                    }

                    BigDecimal descuento = servicioDominioEnvio.calcularPorcentajeDescuentoMaritimo(request.cantidad());
                    BigDecimal precioConDescuento = servicioDominioEnvio.calcularPrecioConDescuento(request.precioEnvio(), descuento);

                    Envio envio = Envio.crearMaritimo(
                            request.clienteId(),
                            request.tipoProducto(),
                            request.cantidad(),
                            request.fechaEntrega(),
                            request.precioEnvio(),
                            request.nombrePuerto(),
                            request.numeroFlota(),
                            request.numeroGuia(),
                            precioConDescuento,
                            descuento
                    );

                    return envioRepositoryPort.save(envio)
                            .flatMap(guardado -> publicadorEventoEnvioPort.crearPublicacion(guardado).thenReturn(guardado))
                            .map(this::aRespuesta);
                });
    }

    @Override
    public Flux<RespuestaEnvio> findAll() {
        return envioRepositoryPort.findAll()
                .map(this::aRespuesta);
    }

    private RespuestaEnvio aRespuesta(Envio envio) {
        return new RespuestaEnvio(
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

}
