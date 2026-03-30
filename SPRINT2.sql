-- BORRAR TABLAS 
DROP TABLE ASISTE CASCADE CONSTRAINTS;
DROP TABLE CITA CASCADE CONSTRAINTS;
DROP TABLE TRAJE CASCADE CONSTRAINTS;
DROP TABLE CLIENTE_COLOR CASCADE CONSTRAINTS;
DROP TABLE TALLER CASCADE CONSTRAINTS;
DROP TABLE EMPLEADO CASCADE CONSTRAINTS;
DROP TABLE CLIENTE CASCADE CONSTRAINTS;

-- TABLA CLIENTE
CREATE TABLE CLIENTE (
    ID_cliente NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    superpoder VARCHAR2(100)
);

-- TABLA CLIENTE_COLOR
CREATE TABLE CLIENTE_COLOR (
    ID_cliente NUMBER,
    color VARCHAR2(50),
    PRIMARY KEY (ID_cliente, color),
    FOREIGN KEY (ID_cliente) REFERENCES CLIENTE(ID_cliente)
);

-- TABLA EMPLEADO
CREATE TABLE EMPLEADO (
    ID_empleado NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nom_ape VARCHAR2(100),
    apodo VARCHAR2(50),
    categoria VARCHAR2(50)
);

-- TABLA TALLER
CREATE TABLE TALLER (
    ID_taller NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    tipo VARCHAR2(50)
);

-- TABLA TRAJE
CREATE TABLE TRAJE (
    ID_traje NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    estado VARCHAR2(50),
    ID_cliente NUMBER,
    FOREIGN KEY (ID_cliente) REFERENCES CLIENTE(ID_cliente)
);

-- TABLA CITA
CREATE TABLE CITA (
    ID_cita NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    dia DATE,
    hora VARCHAR2(10),
    duracion NUMBER,
    ID_taller NUMBER,
    ID_responsable NUMBER,
    ID_traje NUMBER,
    FOREIGN KEY (ID_taller) REFERENCES TALLER(ID_taller),
    FOREIGN KEY (ID_responsable) REFERENCES EMPLEADO(ID_empleado),
    FOREIGN KEY (ID_traje) REFERENCES TRAJE(ID_traje)
);

-- TABLA ASISTE
CREATE TABLE ASISTE (
    ID_cita NUMBER,
    ID_asistente NUMBER,
    PRIMARY KEY (ID_cita, ID_asistente),
    FOREIGN KEY (ID_cita) REFERENCES CITA(ID_cita),
    FOREIGN KEY (ID_asistente) REFERENCES EMPLEADO(ID_empleado)
);


---INSERTAMOS LOS DATOS---

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
(TO_DATE('2026-04-01','YYYY-MM-DD'), '10:00', 60, 1, 1, 1);

INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
(TO_DATE('2026-04-02','YYYY-MM-DD'), '11:00', 60, 2, 2, 2);

INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
(TO_DATE('2026-04-03','YYYY-MM-DD'), '12:00', 60, 3, 3, 3);

INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
(TO_DATE('2026-04-04','YYYY-MM-DD'), '13:00', 60, 4, 4, 4);

INSERT INTO CITA (dia, hora, duracion, ID_taller, ID_responsable, ID_traje) VALUES
(TO_DATE('2026-04-05','YYYY-MM-DD'), '14:00', 60, 5, 5, 5);


-- ASISTE
INSERT INTO ASISTE VALUES (1, 2);
INSERT INTO ASISTE VALUES (2, 3);
INSERT INTO ASISTE VALUES (3, 4);
INSERT INTO ASISTE VALUES (4, 5);
INSERT INTO ASISTE VALUES (5, 1);


---Hemos tenido un error a la hora de crear la base de datos
---Se trataba de que con el autoincrement al ejecutar nos daba un error y no sabiamos como solucionarlo
---La unica solucion que hemos encontrado es sustituirlo por GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
---Que a corto plazo nos sirve pero lo arreglaremos para el proximo sprint