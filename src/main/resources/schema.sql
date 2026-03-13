CREATE TABLE IF NOT EXISTS envios (
    id UUID PRIMARY KEY,
    numero_guia VARCHAR(10) NOT NULL UNIQUE,
    cliente_id UUID NOT NULL,
    tipo_producto VARCHAR(100) NOT NULL,
    cantidad INTEGER NOT NULL,
    fecha_registro TIMESTAMP NOT NULL,
    fecha_entrega DATE NOT NULL,
    precio_envio NUMERIC(12,2) NOT NULL,
    porcentaje_descuento NUMERIC(10,2) NOT NULL,
    precio_con_descuento NUMERIC(12,2) NOT NULL,
    tipo_logistica VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    nombre_bodega VARCHAR(120),
    placa_vehiculo VARCHAR(10),
    nombre_puerto VARCHAR(120),
    numero_flota VARCHAR(10)
);