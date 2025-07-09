INSERT INTO Marca_vehiculo (nombre_marca) VALUES
('Renault'),
('Chevrolet'),
('Mazda'),
('Hyundai'),
('Kia'),
('Toyota'),
('Nissan'),
('Volkswagen'),
('Ford'),
('Suzuki'),
('Mitsubishi'),
('Peugeot');

INSERT INTO Color_vehiculo (nombre_color) VALUES
('Blanco'),
('Negro'),
('Rojo'),
('Gris'),
('Plateado'),
('Azul'),
('Verde'),
('Beige'),
('Amarillo'),
('Café'),
('Vino Tinto'),
('Naranja');


INSERT INTO Tipo_vehiculo (descripcion, capacidad, combustible, tarifa_dia) VALUES
('Sedán', 5, 'Gasolina', 120000),
('SUV', 7, 'Diesel', 180000),
('Pickup', 5, 'Diesel', 200000),
('Hatchback', 5, 'Gasolina', 100000),
('Convertible', 4, 'Gasolina', 220000),
('Camioneta', 7, 'Gasolina', 190000),
('Coupé', 4, 'Gasolina', 160000),
('Minivan', 8, 'Gasolina', 210000),
('4x4', 5, 'Diesel', 230000),
('Eléctrico', 5, 'Eléctrico', 150000),
('Híbrido', 5, 'Híbrido', 170000);

INSERT INTO Blindaje_vehiculo (descripcion) VALUES
('Ninguno'),
('Nivel 1'),
('Nivel 2'),
('Nivel 3'),
('Nivel 4'),
('Nivel 5'),
('Nivel 6'),
('Nivel 7'),
('Nivel 8'),
('Nivel 9'),
('Nivel 10');

INSERT INTO Sucursal (nombre, direccion, telefono, gerente, id_codigo_postal)
VALUES
    ('Sucursal Norte', 'Av 68 #100-20, Bogotá', '3209876543', 'Ana Gerente', '110111'),
    ('Sucursal Sur', 'Cra 15 #50-40, Cali', '3211234567', 'Juan Gerente', '760001');

INSERT INTO Transmision_vehiculo (descripcion) VALUES
('Manual'),
('Automática'),
('Semi-Automática'),
('Doble embrague'),
('Secuencial');

INSERT INTO Cilindraje_vehiculo (descripcion) VALUES
('1000cc'),
('1200cc'),
('1400cc'),
('1600cc'),
('1800cc'),
('2000cc'),
('2200cc'),
('2400cc'),
('3000cc'),
('3500cc'),
('4000cc');

INSERT INTO Estado_vehiculo (descripcion) VALUES ('Disponible'), ('En mantenimiento');

INSERT INTO Vehiculo (placa, n_chasis, modelo, kilometraje, id_marca, id_color, id_tipo_vehiculo, id_blindaje, id_transmision, id_cilindraje, id_seguro_vehiculo, id_estado_vehiculo, id_proveedor, id_sucursal)
VALUES
('ABC123', 'CHS123456', 'Logan 2020', 25000, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1),
('XYZ789', 'CHS654321', 'Captiva 2022', 12000, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1);