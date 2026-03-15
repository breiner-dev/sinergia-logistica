package com.sinergia.logistica.application.usecase;

import com.sinergia.logistica.application.dto.ActualizarEnvioRequest;
import com.sinergia.logistica.application.dto.ClienteResponse;
import com.sinergia.logistica.application.dto.CrearEnvioRequest;
import com.sinergia.logistica.application.dto.RespuestaEnvio;
import com.sinergia.logistica.domain.model.Envio;
import com.sinergia.logistica.domain.model.TipoLogistica;
import com.sinergia.logistica.domain.port.in.EnviosUseCase;
import com.sinergia.logistica.domain.port.out.ClienteRepositoryPort;
import com.sinergia.logistica.domain.port.out.EnvioRepositoryPort;
import com.sinergia.logistica.domain.port.out.PublicadorEventoEnvioPort;
import com.sinergia.logistica.domain.service.DominioEnvioService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class EnvioUseCaseService implements EnviosUseCase {

    private final EnvioRepositoryPort envioRepositoryPort;
    private final PublicadorEventoEnvioPort publicadorEventoEnvioPort;
    private final DominioEnvioService servicioDominioEnvio = new DominioEnvioService();
    private final ClienteRepositoryPort clienteRepositoryPort;

    public EnvioUseCaseService(
            EnvioRepositoryPort envioRepositoryPort,
            PublicadorEventoEnvioPort publicadorEventoEnvioPort, ClienteRepositoryPort clienteRepositoryPort
    ) {
        this.envioRepositoryPort = envioRepositoryPort;
        this.publicadorEventoEnvioPort = publicadorEventoEnvioPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Mono<RespuestaEnvio> create(CrearEnvioRequest request) {
        return envioRepositoryPort.existsByGuideNumber(request.numeroGuia())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new IllegalArgumentException("La guía ya existe"));
                    }

                    Envio envio;
                    BigDecimal descuento;

                    if (request.tipoLogistica() == TipoLogistica.TERRESTRE) {
                        descuento = servicioDominioEnvio
                                .calcularPorcentajeDescuentoTerrestre(request.cantidad());

                        BigDecimal precioConDescuento =
                                servicioDominioEnvio.calcularPrecioConDescuento(
                                        request.precioEnvio(),
                                        descuento
                                );

                        envio = Envio.crearTerrestre(
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
                    } else {
                        descuento = servicioDominioEnvio
                                .calcularPorcentajeDescuentoMaritimo(request.cantidad());

                        BigDecimal precioConDescuento =
                                servicioDominioEnvio.calcularPrecioConDescuento(
                                        request.precioEnvio(),
                                        descuento
                                );

                        envio = Envio.crearMaritimo(
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
                    }

                    return envioRepositoryPort.save(envio)
                            .flatMap(guardado ->
                                    publicadorEventoEnvioPort.crearPublicacion(guardado)
                                            .thenReturn(guardado)
                            )
                            .flatMap(this::aRespuestaConCliente);
                });
    }

    @Override
    public Mono<RespuestaEnvio> buscarPorId(UUID id) {
        return envioRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Envío no encontrado")))
                .flatMap(this::aRespuestaConCliente);
    }

    public Mono<RespuestaEnvio> actualizar(UUID id, ActualizarEnvioRequest request) {
        return envioRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Envío no encontrado")))
                .flatMap(envio -> {
                    if (envio.getTipoLogistica() == TipoLogistica.TERRESTRE) {
                        BigDecimal descuento = servicioDominioEnvio
                                .calcularPorcentajeDescuentoTerrestre(request.cantidad());
                        BigDecimal precioConDescuento = servicioDominioEnvio
                                .calcularPrecioConDescuento(request.precioEnvio(), descuento);

                        envio.actualizarTerrestre(
                                request.tipoProducto(),
                                request.cantidad(),
                                request.fechaEntrega(),
                                request.precioEnvio(),
                                request.nombreBodega(),
                                request.placaVehiculo(),
                                descuento,
                                precioConDescuento
                        );
                    } else {
                        BigDecimal descuento = servicioDominioEnvio
                                .calcularPorcentajeDescuentoMaritimo(request.cantidad());
                        BigDecimal precioConDescuento = servicioDominioEnvio
                                .calcularPrecioConDescuento(request.precioEnvio(), descuento);

                        envio.actualizarMaritimo(
                                request.tipoProducto(),
                                request.cantidad(),
                                request.fechaEntrega(),
                                request.precioEnvio(),
                                request.nombrePuerto(),
                                request.numeroFlota(),
                                descuento,
                                precioConDescuento
                        );
                    }

                    return envioRepositoryPort.update(envio);
                })
                .flatMap(this::aRespuestaConCliente);
    }

    @Override
    public Mono<Void> eliminar(UUID id) {
        return envioRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Envío no encontrado")))
                .flatMap(envio -> envioRepositoryPort.deleteById(id));
    }

    @Override
    public Flux<RespuestaEnvio> findAll() {
        return envioRepositoryPort.findAll()
                .flatMap(this::aRespuestaConCliente);
    }

    /*
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
    }*/

    private Mono<RespuestaEnvio> aRespuestaConCliente(Envio envio) {
        return clienteRepositoryPort.findById(envio.getClienteId())
                .map(cliente -> new RespuestaEnvio(
                        envio.getId(),
                        envio.getNumeroGuia(),
                        new ClienteResponse(
                                cliente.getId(),
                                cliente.getNombre(),
                                cliente.getCorreo(),
                                cliente.getTelefono()
                        ),
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
                ));
    }

}
