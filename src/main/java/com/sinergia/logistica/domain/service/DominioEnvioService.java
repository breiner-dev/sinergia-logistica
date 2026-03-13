package com.sinergia.logistica.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DominioEnvioService {

    public BigDecimal calcularPorcentajeDescuentoTerrestre(Integer cantidad) {
        return cantidad != null && cantidad > 10
                ? new BigDecimal("0.05")
                : BigDecimal.ZERO;
    }

    public BigDecimal calcularPorcentajeDescuentoMaritimo(Integer cantidad) {
        return cantidad != null && cantidad > 10
                ? new BigDecimal("0.03")
                : BigDecimal.ZERO;
    }

    public BigDecimal calcularPrecioConDescuento(BigDecimal precioEnvio, BigDecimal porcentajeDescuento) {
        BigDecimal valorDescuento = precioEnvio.multiply(porcentajeDescuento);
        return precioEnvio.subtract(valorDescuento).setScale(2, RoundingMode.HALF_UP);
    }
}
