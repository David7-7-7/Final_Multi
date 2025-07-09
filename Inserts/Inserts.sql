
-- ============================================
-- INSERCIÓN DE DATOS
-- ============================================

-- Marca
INSERT INTO Marca_vehiculo (nombre_marca) VALUES
('Renault'), ('Chevrolet'), ('Mazda'), ('Hyundai'), ('Kia'),
('Toyota'), ('Nissan'), ('Volkswagen'), ('Ford'), ('Suzuki'),
('Mitsubishi'), ('Peugeot');

-- Color
INSERT INTO Color_vehiculo (nombre_color) VALUES
('Blanco'), ('Negro'), ('Rojo'), ('Gris'), ('Plateado'),
('Azul'), ('Verde'), ('Beige'), ('Amarillo'), ('Café'),
('Vino Tinto'), ('Naranja');

-- Tipo de vehículo
INSERT INTO Tipo_vehiculo (descripcion, capacidad, combustible, tarifa_dia) VALUES
('Sedán', 5, 'Gasolina', 120000), ('SUV', 7, 'Diesel', 180000),
('Pickup', 5, 'Diesel', 200000), ('Hatchback', 5, 'Gasolina', 100000),
('Convertible', 4, 'Gasolina', 220000), ('Camioneta', 7, 'Gasolina', 190000),
('Coupé', 4, 'Gasolina', 160000), ('Minivan', 8, 'Gasolina', 210000),
('4x4', 5, 'Diesel', 230000), ('Eléctrico', 5, 'Eléctrico', 150000),
('Híbrido', 5, 'Híbrido', 170000);

-- Blindaje
INSERT INTO Blindaje_vehiculo (descripcion) VALUES
('Ninguno'), ('Nivel 1'), ('Nivel 2'), ('Nivel 3'), ('Nivel 4'),
('Nivel 5'), ('Nivel 6'), ('Nivel 7'), ('Nivel 8'), ('Nivel 9'), ('Nivel 10');

-- Transmisión
INSERT INTO Transmision_vehiculo (descripcion) VALUES
('Manual'), ('Automática'), ('Semi-Automática'), ('Doble embrague'), ('Secuencial');

-- Cilindraje
INSERT INTO Cilindraje_vehiculo (descripcion) VALUES
('1000cc'), ('1200cc'), ('1400cc'), ('1600cc'), ('1800cc'),
('2000cc'), ('2200cc'), ('2400cc'), ('3000cc'), ('3500cc'), ('4000cc');

-- Estado
INSERT INTO Estado_vehiculo (descripcion) VALUES
('Disponible'), ('En mantenimiento');

-- Código postal
INSERT INTO Codigo_postal (id_codigo_postal, pais, departamento, ciudad) VALUES
('110111', 'Colombia', 'Cundinamarca', 'Bogotá'),
('760001', 'Colombia', 'Valle del Cauca', 'Cali');

-- Sucursal
INSERT INTO Sucursal (nombre, direccion, telefono, gerente, id_codigo_postal) VALUES
('Sucursal Norte', 'Av 68 #100-20, Bogotá', '3209876543', 'Ana Gerente', '110111'),
('Sucursal Sur', 'Cra 15 #50-40, Cali', '3211234567', 'Juan Gerente', '760001');

-- Seguro
INSERT INTO Seguro_vehiculo (estado, descripcion, vencimiento, costo) VALUES
('Vigente', 'SOAT', '2025-12-31', 150000),
('Vigente', 'Todo riesgo', '2025-12-31', 300000);

-- Proveedor
CREATE TABLE IF NOT EXISTS proveedor_vehiculo (
    id_proveedor INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    telefono VARCHAR(20),
    correo VARCHAR(50),
    direccion VARCHAR(100)
);
INSERT INTO proveedor_vehiculo (nombre, telefono, correo, direccion) VALUES
('Distribuidora Nacional', '3001234567', 'contacto@distribuidor.com', 'Calle 100 #10-20'),
('AutoPartes Express', '3107654321', 'ventas@autopartes.com', 'Av. 30 #45-50');

-- Vehículo
INSERT INTO Vehiculo (placa, n_chasis, modelo, kilometraje, id_marca, id_color, id_tipo_vehiculo,
    id_blindaje, id_transmision, id_cilindraje, id_seguro_vehiculo, id_estado_vehiculo, id_proveedor, id_sucursal)
VALUES
('ABC123', 'CHS123456', 'Logan 2020', 25000, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1);

select * from vehiculo;
select * from estado_vehiculo;

SELECT id_estado_vehiculo FROM Vehiculo WHERE id_marca = 1 AND id_tipo_vehiculo = 1 AND id_transmision = 1 AND id_sucursal = 1 AND id_blindaje = 1 AND id_color = 1 AND id_cilindraje = 1 ;

INSERT INTO Empleado (
  documento, nombre, salario, cargo, telefono, direccion, correo, tipo_documento, contrasena
) VALUES
('123456789', 'Carlos Pérez', 2500000.00, 'Gerente', '3101234567', 'Cra 10 #45-23', 'carlos.perez@email.com', 'Cédula de Ciudadanía', 'clave123'),
('987654321', 'Laura Gómez', 1800000.00, 'Asistente', '3127654321', 'Calle 55 #23-10', 'laura.gomez@email.com', 'Cédula de Ciudadanía', 'laura2025');
INSERT INTO Empleado (
  documento, nombre, salario, cargo, telefono, direccion, correo, tipo_documento, contrasena
) VALUES('123456789', 'Carlos Pérez', 2500000.00, 'Gerente', '3101234567', 'Cra 10 #45-23', 'b@', 'Cédula de Ciudadanía', '1');
