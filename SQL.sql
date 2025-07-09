-- ------------------------------------------------------------------
-- 1) TABLAS AUXILIARES / CATÁLOGOS HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
-- ------------------------------------------------------------------
drop database alquiler_vehiculo;

CREATE DATABASE IF NOT EXISTS Alquiler_vehiculo;
USE Alquiler_vehiculo;
CREATE TABLE Tipo_entidad (
  id_tipo_entidad   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) 
) ENGINE=InnoDB;

CREATE TABLE Medio_pago (
  id_medio_pago     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) 
) ENGINE=InnoDB;



CREATE TABLE Codigo_postal (
  id_codigo_postal  VARCHAR(50)  PRIMARY KEY,
  pais              VARCHAR(50) ,
  departamento      VARCHAR(50) ,
  ciudad            VARCHAR(50) 
) ENGINE=InnoDB;



CREATE TABLE Tipo_mantenimiento (
  id_tipo           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) 
) ENGINE=InnoDB;

CREATE TABLE Taller_mantenimiento (
  id_taller         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre            VARCHAR(100) ,
  direccion         VARCHAR(150),
  telefono          VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE Estado_vehiculo (
  id_estado         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) 
) ENGINE=InnoDB;

CREATE TABLE Marca_vehiculo (
  id_marca          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre_marca      VARCHAR(100) 
) ENGINE=InnoDB;

CREATE TABLE Color_vehiculo (
  id_color          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre_color      VARCHAR(50) 
) ENGINE=InnoDB;

CREATE TABLE Tipo_vehiculo (
  id_tipo           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) ,
  capacidad         INT,
  combustible       VARCHAR(50),
  tarifa_dia        DECIMAL(10,2)
) ENGINE=InnoDB;

CREATE TABLE Blindaje_vehiculo (
  id_blindaje       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) 
) ENGINE=InnoDB;

CREATE TABLE Transmision_vehiculo (
  id_transmision    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(50) 
) ENGINE=InnoDB;

CREATE TABLE Cilindraje_vehiculo (
  id_cilindraje     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(50) 
) ENGINE=InnoDB;

CREATE TABLE Estado_alquiler (
  id_estado         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100) 
) ENGINE=InnoDB;


-- ------------------------------------------------------------------
-- 2) TABLAS PRINCIPALES / TRANSACCIONALES
-- ------------------------------------------------------------------

CREATE TABLE Sucursal (
  id_sucursal       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre            VARCHAR(100) ,
  direccion         VARCHAR(150),
  telefono          VARCHAR(20),
  gerente           VARCHAR(100),
  id_codigo_postal  VARCHAR(50),
  FOREIGN KEY (id_codigo_postal)
    REFERENCES Codigo_postal(id_codigo_postal)
) ENGINE=InnoDB; 

CREATE TABLE Empleado (
  id_empleado       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  documento         VARCHAR(20) ,
  nombre            VARCHAR(100) ,
  salario           DECIMAL(10,2),
  cargo             VARCHAR(100),
  telefono          VARCHAR(20),
  direccion         VARCHAR(150),
  correo            VARCHAR(100),
  tipo_documento VARCHAR(100)
) ENGINE=InnoDB;
ALTER TABLE Empleado
ADD COLUMN contrasena VARCHAR(100);

CREATE TABLE Licencia_conduccion (
 id_licencia INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    fecha_expedicion DATE,
    fecha_vencimiento DATE,
    estado VARCHAR(50)
) ENGINE=InnoDB;

CREATE TABLE Cliente (
  id_cliente        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  documento         VARCHAR(20) NOT NULL,
  nombre            VARCHAR(100) NOT NULL,
  telefono          VARCHAR(20),
  direccion         VARCHAR(150),
  correo            VARCHAR(100),
  infracciones      VARCHAR(150),
  id_licencia       INT UNSIGNED,
  tipo_documento    VARCHAR(100),
  tipo_cliente   VARCHAR(100),
  id_codigo_postal  VARCHAR(50),
  FOREIGN KEY (id_licencia)
    REFERENCES Licencia_conduccion(id_licencia),
  FOREIGN KEY (id_codigo_postal)
    REFERENCES Codigo_postal(id_codigo_postal)
  -- id_cuenta se añade tras definir la tabla Cuenta
) ENGINE=InnoDB;

CREATE TABLE Seguro_vehiculo (
  id_seguro         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  estado            VARCHAR(50),
  descripcion       VARCHAR(255),
  vencimiento       DATE,
  costo             DECIMAL(10,2)
) ENGINE=InnoDB;

CREATE TABLE Proveedor_vehiculo (
  id_proveedor      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre            VARCHAR(100),
  direccion         VARCHAR(150),
  telefono          VARCHAR(20),
  correo            VARCHAR(100),
  id_cuenta         INT UNSIGNED
  -- FK a Cuenta si quieres trackear facturación
) ENGINE=InnoDB;

CREATE TABLE Mantenimiento_vehiculo (
  id_mantenimiento  INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(255),
  fecha_hora        DATETIME,
  valor             DECIMAL(10,2),
  id_tipo           INT UNSIGNED,
  id_taller         INT UNSIGNED,
  FOREIGN KEY (id_tipo)
    REFERENCES Tipo_mantenimiento(id_tipo),
  FOREIGN KEY (id_taller)
    REFERENCES Taller_mantenimiento(id_taller)
) ENGINE=InnoDB;

-- Crear tabla Vehiculo
CREATE TABLE Vehiculo (
  placa              VARCHAR(20) PRIMARY KEY,
  n_chasis           VARCHAR(50),
  modelo             VARCHAR(50),
  kilometraje        INT,
  id_marca           INT UNSIGNED,
  id_color           INT UNSIGNED,
  id_tipo_vehiculo   INT UNSIGNED,
  id_blindaje        INT UNSIGNED,
  id_transmision     INT UNSIGNED,
  id_cilindraje      INT UNSIGNED,
  id_seguro_vehiculo INT UNSIGNED,
  id_estado_vehiculo INT UNSIGNED,
  id_proveedor       INT UNSIGNED,
  id_sucursal        INT UNSIGNED,
  FOREIGN KEY (id_marca)           REFERENCES Marca_vehiculo(id_marca),
  FOREIGN KEY (id_color)           REFERENCES Color_vehiculo(id_color),
  FOREIGN KEY (id_tipo_vehiculo)   REFERENCES Tipo_vehiculo(id_tipo),
  FOREIGN KEY (id_blindaje)        REFERENCES Blindaje_vehiculo(id_blindaje),
  FOREIGN KEY (id_transmision)     REFERENCES Transmision_vehiculo(id_transmision),
  FOREIGN KEY (id_cilindraje)      REFERENCES Cilindraje_vehiculo(id_cilindraje),
  FOREIGN KEY (id_seguro_vehiculo) REFERENCES Seguro_vehiculo(id_seguro),
  FOREIGN KEY (id_estado_vehiculo) REFERENCES Estado_vehiculo(id_estado),
  FOREIGN KEY (id_proveedor)       REFERENCES Proveedor_vehiculo(id_proveedor),
  FOREIGN KEY (id_sucursal)        REFERENCES Sucursal(id_sucursal)
) ENGINE=InnoDB;

-- Agregar columna id_vehiculo y su clave foránea a Mantenimiento_vehiculo
ALTER TABLE Mantenimiento_vehiculo
ADD COLUMN id_vehiculo VARCHAR(20),
ADD FOREIGN KEY (id_vehiculo) REFERENCES Vehiculo(placa);

CREATE TABLE Descuento_alquiler (
  id_descuento      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(255),
  valor             DECIMAL(10,2)
) ENGINE=InnoDB;

CREATE TABLE Estado_reserva (
  id_estado         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion       VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE Seguro_alquiler (
  id_seguro         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  estado            VARCHAR(50),
  descripcion       VARCHAR(255),
  vencimiento       DATE,
  costo             DECIMAL(10,2)
) ENGINE=InnoDB;

-- Crear tabla Reserva_alquiler sin la columna id_alquiler
CREATE TABLE Reserva_alquiler (
  id_reserva         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  fecha_hora         DATETIME,
  abono              DECIMAL(10,2),
  saldo_pendiente    DECIMAL(10,2),
  id_estado_reserva  INT UNSIGNED,
  FOREIGN KEY (id_estado_reserva)
    REFERENCES Estado_reserva(id_estado)
) ENGINE=InnoDB;

-- Crear tabla Alquiler
CREATE TABLE Alquiler (
  id_alquiler        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  fecha_hora_salida  DATETIME,
  valor              DECIMAL(10,2),
  fecha_hora_entrada DATETIME,
  id_vehiculo        VARCHAR(20),
  id_cliente         INT UNSIGNED,
  id_sucursal        INT UNSIGNED,
  id_medio_pago      INT UNSIGNED,
  id_estado          INT UNSIGNED,
  id_seguro          INT UNSIGNED,
  id_descuento       INT UNSIGNED,
  FOREIGN KEY (id_vehiculo)    REFERENCES Vehiculo(placa),
  FOREIGN KEY (id_cliente)     REFERENCES Cliente(id_cliente),
  FOREIGN KEY (id_sucursal)    REFERENCES Sucursal(id_sucursal),
  FOREIGN KEY (id_medio_pago)  REFERENCES Medio_pago(id_medio_pago),
  FOREIGN KEY (id_estado)      REFERENCES Estado_alquiler(id_estado),
  FOREIGN KEY (id_seguro)      REFERENCES Seguro_alquiler(id_seguro),
  FOREIGN KEY (id_descuento)   REFERENCES Descuento_alquiler(id_descuento)
) ENGINE=InnoDB;

-- Alterar tabla Reserva_alquiler para agregar columna id_alquiler y su clave foránea
ALTER TABLE Reserva_alquiler
ADD COLUMN id_alquiler INT UNSIGNED,
ADD FOREIGN KEY (id_alquiler) REFERENCES Alquiler(id_alquiler);


CREATE TABLE Det_factura (
  id_det_factura    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  id_servicio       INT UNSIGNED,
  valor             DECIMAL(10,2),
  impuestos         DECIMAL(10,2)
) ENGINE=InnoDB;

CREATE TABLE Factura (
  id_factura        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  valor             DECIMAL(10,2),
  id_alquiler       INT UNSIGNED,
  id_cliente        INT UNSIGNED,
  id_vehiculo       VARCHAR(20),
  id_det_factura    INT UNSIGNED,
  FOREIGN KEY (id_alquiler)
    REFERENCES Alquiler(id_alquiler),
  FOREIGN KEY (id_cliente)
    REFERENCES Cliente(id_cliente),
  FOREIGN KEY (id_vehiculo)
    REFERENCES Vehiculo(placa),
  FOREIGN KEY (id_det_factura)
    REFERENCES Det_factura(id_det_factura)
) ENGINE=InnoDB;

CREATE TABLE Cuenta_pagar (
  id_cuenta_pagar   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  fecha_hora        DATETIME,
  valor             DECIMAL(10,2),
  descripcion       VARCHAR(255),
  id_medio_pago     INT UNSIGNED,
  id_tipo_entidad   INT UNSIGNED,
  id_entidad        INT UNSIGNED,
  FOREIGN KEY (id_medio_pago)
    REFERENCES Medio_pago(id_medio_pago),
  FOREIGN KEY (id_tipo_entidad)
    REFERENCES Tipo_entidad(id_tipo_entidad)
) ENGINE=InnoDB;

CREATE TABLE Cuenta_cobrar (
  id_cuenta_cobrar  INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  fecha_hora        DATETIME,
  valor             DECIMAL(10,2),
  descripcion       VARCHAR(255),
  id_medio_pago     INT UNSIGNED,
  id_tipo_entidad   INT UNSIGNED,
  id_entidad        INT UNSIGNED,
  FOREIGN KEY (id_medio_pago)
    REFERENCES Medio_pago(id_medio_pago),
  FOREIGN KEY (id_tipo_entidad)
    REFERENCES Tipo_entidad(id_tipo_entidad)
) ENGINE=InnoDB;

CREATE TABLE Cuenta (
  id_cuenta         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  id_cuenta_pagar   INT UNSIGNED,
  id_cuenta_cobrar  INT UNSIGNED,
  FOREIGN KEY (id_cuenta_pagar)
    REFERENCES Cuenta_pagar(id_cuenta_pagar),
  FOREIGN KEY (id_cuenta_cobrar)
    REFERENCES Cuenta_cobrar(id_cuenta_cobrar)
) ENGINE=InnoDB;

CREATE TABLE Abono_reserva (
  id_abono          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  valor             DECIMAL(10,2),
  fecha_hora        DATETIME,
  id_reserva        INT UNSIGNED,
  id_medio_pago     INT UNSIGNED,
  FOREIGN KEY (id_reserva)
    REFERENCES Reserva_alquiler(id_reserva),
  FOREIGN KEY (id_medio_pago)
    REFERENCES Medio_pago(id_medio_pago)
) ENGINE=InnoDB;


-- Finalmente actualizamos la FK pendiente en Cliente:


select * from licencia_conduccion;
ALTER TABLE Cliente ADD COLUMN contrasena VARCHAR(100);
DROP TABLE Empleado;

CREATE TABLE Usuario (
  id_usuario        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  correo            VARCHAR(100) NOT NULL UNIQUE,
  contrasena        VARCHAR(100) NOT NULL,
  rol               ENUM('Cliente', 'Empleado', 'Administrador'),
  id_referencia     INT UNSIGNED, -- Cliente o Empleado
  FOREIGN KEY (id_referencia) REFERENCES Cliente(id_cliente) -- o Empleado según el rol
);
	
    
SELECT * FROM Empleado WHERE correo = 'carlos.perez@email.com' AND contrasena = 'clave123';