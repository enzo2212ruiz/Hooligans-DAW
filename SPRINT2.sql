DROP TABLE IF EXISTS ASISTE;
DROP TABLE IF EXISTS CITA;
DROP TABLE IF EXISTS TRAJE;
DROP TABLE IF EXISTS CLIENTE_COLOR;
DROP TABLE IF EXISTS TALLER;
DROP TABLE IF EXISTS EMPLEADO;
DROP TABLE IF EXISTS CLIENTE;

-- TABLA CLIENTE
CREATE TABLE CLIENTE (
    ID_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    superpoder VARCHAR(100)
);

-- Tabla para los colores
CREATE TABLE CLIENTE_COLOR (
    ID_cliente INT AUTO_INCREMENT PRIMARY KEY,
    color VARCHAR(50),
    FOREIGN KEY (ID_cliente) REFERENCES CLIENTE(ID_cliente)
        ON DELETE CASCADE
);

-- TABLA EMPLEADO
CREATE TABLE EMPLEADO (
    ID_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nom_ape VARCHAR(100),
    apodo VARCHAR(50),
    categoria VARCHAR(50)
);

-- TABLA TALLER
CREATE TABLE TALLER (
    ID_taller INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    tipo VARCHAR(50)
);

-- TABLA TRAJE
CREATE TABLE TRAJE (
    ID_traje INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    estado VARCHAR(50),
    ID_cliente INT,
    FOREIGN KEY (ID_cliente) REFERENCES CLIENTE(ID_cliente)
        ON DELETE CASCADE
);

-- TABLA CITA (corregida)
CREATE TABLE CITA (
    ID_cita INT AUTO_INCREMENT PRIMARY KEY,
    dia DATE,
    hora TIME,
    duracion INT,
    ID_taller INT,
    ID_responsable INT,
    ID_traje INT,
    FOREIGN KEY (ID_taller) REFERENCES TALLER(ID_taller)
        ON DELETE CASCADE,
    FOREIGN KEY (ID_responsable) REFERENCES EMPLEADO(ID_empleado)
        ON DELETE CASCADE,
    FOREIGN KEY (ID_traje) REFERENCES TRAJE(ID_traje)
        ON DELETE CASCADE
);

-- TABLA ASISTE	
CREATE TABLE ASISTE (
    ID_cita INT AUTO_INCREMENT,
    ID_asistente INT,
    PRIMARY KEY (ID_cita, ID_asistente),
    FOREIGN KEY (ID_cita) REFERENCES CITA(ID_cita)
        ON DELETE CASCADE,
    FOREIGN KEY (ID_asistente) REFERENCES EMPLEADO(ID_empleado)
        ON DELETE CASCADE
);

-- INSERTAMOS LOS DATOS --

-- CLIENTE
INSERT INTO CLIENTE (nombre, superpoder) VALUES
('cliente1', 'poder1');
INSERT INTO CLIENTE (nombre, superpoder) VALUES
('cliente2', 'poder2');
INSERT INTO CLIENTE (nombre, superpoder) VALUES
('cliente3', 'poder3');
INSERT INTO CLIENTE (nombre, superpoder) VALUES
('cliente4', 'poder4');
INSERT INTO CLIENTE (nombre, superpoder) VALUES
('cliente5', 'poder5');


-- CLIENTE_COLOR
INSERT INTO CLIENTE_COLOR VALUES (1, 'rojo');
INSERT INTO CLIENTE_COLOR VALUES (2, 'azul');
INSERT INTO CLIENTE_COLOR VALUES (3, 'verde');
INSERT INTO CLIENTE_COLOR VALUES (4, 'negro');
INSERT INTO CLIENTE_COLOR VALUES (5, 'blanco');


-- EMPLEADO
INSERT INTO EMPLEADO (nom_ape, apodo, categoria) VALUES
('Victor Perez', 'victor', 'categoria1');
INSERT INTO EMPLEADO (nom_ape, apodo, categoria) VALUES
('Roberto Lopez', 'rober', 'categoria2');
INSERT INTO EMPLEADO (nom_ape, apodo, categoria) VALUES
('Diego Martin', 'diego', 'categoria3');
INSERT INTO EMPLEADO (nom_ape, apodo, categoria) VALUES
('Guillermo Ruiz', 'guille', 'categoria4');
INSERT INTO EMPLEADO (nom_ape, apodo, categoria) VALUES
('Pablo Gomez', 'pablo', 'categoria5');


-- TALLER
INSERT INTO TALLER (nombre, tipo) VALUES ('taller1', 'tipo1');
INSERT INTO TALLER (nombre, tipo) VALUES ('taller2', 'tipo2');
INSERT INTO TALLER (nombre, tipo) VALUES ('taller3', 'tipo3');
INSERT INTO TALLER (nombre, tipo) VALUES ('taller4', 'tipo4');
INSERT INTO TALLER (nombre, tipo) VALUES ('taller5', 'tipo5');


-- TRAJE
INSERT INTO TRAJE (nombre, estado, ID_cliente) VALUES ('traje1', 'nuevo', 1);
INSERT INTO TRAJE (nombre, estado, ID_cliente) VALUES ('traje2', 'usado', 2);
INSERT INTO TRAJE (nombre, estado, ID_cliente) VALUES ('traje3', 'roto', 3);
INSERT INTO TRAJE (nombre, estado, ID_cliente) VALUES ('traje4', 'nuevo', 4);
INSERT INTO TRAJE (nombre, estado, ID_cliente) VALUES ('traje5', 'usado', 5);


-- CITA
INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
('2026-04-01', '10:00', 60, 1, 1, 1);
INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
('2026-04-02', '11:00', 60, 2, 2, 2);
INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
('2026-04-03', '12:00', 60, 3, 3, 3);
INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
('2026-04-04', '13:00', 60, 4, 4, 4);
INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
('2026-04-05', '14:00', 60, 5, 5, 5);


-- ASISTE
INSERT INTO ASISTE VALUES (1, 2);
INSERT INTO ASISTE VALUES (2, 3);
INSERT INTO ASISTE VALUES (3, 4);
INSERT INTO ASISTE VALUES (4, 5);
INSERT INTO ASISTE VALUES (5, 1);