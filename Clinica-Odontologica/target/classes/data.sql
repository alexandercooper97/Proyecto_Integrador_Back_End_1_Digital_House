
# Inicialización de datos (data.sql)
# Crear el archivo src/main/resources/data.sql con el siguiente contenido:

-- Insertar odontólogos
INSERT INTO odontologos (apellido, nombre, matricula) VALUES
('García', 'Juan', 'ODO001'),
('Rodríguez', 'María', 'ODO002'),
('López', 'Carlos', 'ODO003');
('Gomez', 'Manuel', 'ODO004');
('Herrera', 'Angel', 'ODO005');
('Cuadros', 'Jaime', 'ODO006');
('Trump', 'Donald', 'ODO007');
('Tapara', 'Carlos', 'ODO008');
('Falcone', 'Daniel', 'ODO009');
('Garcia', 'Alan', 'ODO010');


-- Insertar pacientes
INSERT INTO pacientes (apellido, nombre, domicilio, dni, fecha_alta) VALUES
('Pérez', 'Ana', 'Calle 123', '12345678', '2023-01-01'),
('Gómez', 'Luis', 'Avenida 456', '23456789', '2023-02-15'),
('Martínez', 'Laura', 'Plaza 789', '34567890', '2023-03-30');
('Peralta', 'Daniela', 'Surco 189', '45678901', '2023-04-27');
('Carmona', 'Pilar', 'Basilica 545', '56789012', '2023-05-14');
('Chavez', 'Maria del Pilar', 'Arispe 666', '67890123', '2023-06-12');
('Cooper', 'Hurrem', 'Turquia 639', '78901234', '2023-07-27');
('Slchater', 'Frank', 'Francia 123', '89012345', '2023-08-20');


-- Insertar usuarios
INSERT INTO usuarios (username, password, role) VALUES
('admin', '$2a$10$X7UrEJPQxTBq6s2mGaNlIehMPKHI0B1pkZ7FWU7aEvCY2OL1Zy5YK', 'ADMIN'),
('user', '$2a$10$mpTmhbC4JbN6H.l5mGy8dOZLnpk0/NF9gCXWLbE7YpHhCrPt5QH8O', 'USER');

-- Nota: Las contraseñas están hasheadas.
--'admin' tiene la contraseña 'adminpass' y
--'user' tiene la contraseña 'use