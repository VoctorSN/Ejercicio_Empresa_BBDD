CREATE SCHEMA IF NOT EXISTS EMPRESA;
USE EMPRESA;

DROP TABLE IF EXISTS DEPARTAMENTOS;
CREATE TABLE IF NOT EXISTS DEPARTAMENTOS(
    NUMERO INT,
    NOMBRE VARCHAR(25) NOT NULL,
    NSS VARCHAR(15) NULL,
    FECHA DATE NULL,
    PRIMARY KEY(NUMERO)
    );
    
DROP TABLE IF EXISTS PROYECTOS;
CREATE TABLE IF NOT EXISTS PROYECTOS(
    NUMERO INT,
    NOMBRE VARCHAR(25) NOT NULL,
    LUGAR VARCHAR(25) NOT NULL,
    DEPARTAMENTO INT NOT NULL,
    PRIMARY KEY(NUMERO),
    FOREIGN KEY (DEPARTAMENTO) REFERENCES DEPARTAMENTOS(NUMERO)
    );
    
DROP TABLE IF EXISTS EMPLEADOS;
CREATE TABLE IF NOT EXISTS EMPLEADOS(
    NOMBRE VARCHAR(25) NOT NULL,
    APELLIDO1 VARCHAR(25) NOT NULL,
    APELLIDO2 VARCHAR(25) NOT NULL,
    NSS VARCHAR(15) NOT NULL,
    CALLE VARCHAR(30) NULL,
    NUMERO_CALLE INT NULL,
    PISO VARCHAR(4) NULL,
    CP VARCHAR(5) NULL,
    LOCALIDAD VARCHAR(25) NULL,
    FECHA_NACIMIENTO DATE NULL,
    SALARIO FLOAT NULL,
    SEXO CHAR(1) NULL,
    SUPERVISOR VARCHAR(15) NULL,
    DEPARTAMENTO INT NULL,
    PRIMARY KEY(NSS),
    FOREIGN KEY (SUPERVISOR) REFERENCES EMPLEADOS (NSS),
    FOREIGN KEY (DEPARTAMENTO) REFERENCES DEPARTAMENTOS (NUMERO)
    );

DROP TABLE IF EXISTS EMPLEADOS_PROYECTOS;
CREATE TABLE IF NOT EXISTS EMPLEADOS_PROYECTOS(
    EMPLEADO VARCHAR(15),
    PROYECTO INT NOT NULL,
    HORAS INT NULL,
    PRIMARY KEY(EMPLEADO,PROYECTO),
    FOREIGN KEY (EMPLEADO) REFERENCES EMPLEADOS(NSS),
    FOREIGN KEY (PROYECTO) REFERENCES PROYECTOS(NUMERO)
    );

ALTER TABLE DEPARTAMENTOS
	ADD FOREIGN KEY (NSS) REFERENCES EMPLEADOS (NSS);
    