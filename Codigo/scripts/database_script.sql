-- Script de inicialización de la base de datos para PostgreSQL
-- Proyecto: Sistema JDBC
-- María Emilia Navarrete Ávila

-- Crear la tabla pacientes
CREATE TABLE pacientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    edad INT,
    diagnostico VARCHAR(50),
    habitacion VARCHAR(10),
    estado VARCHAR(20)
);

-- Inserción de datos iniciales para validación
INSERT INTO pacientes (nombre, edad, diagnostico, habitacion, estado) 
VALUES ('Carlos Molina', 45, 'Gripe', '101', 'Estable'),
       ('Emilia Carrera', 32, 'Fractura', '204', 'En observación'),
       ('Gabriela Loor', 60, 'Hipertensión', '302', 'Estable');

-- Consulta de verificación
SELECT * FROM pacientes;
