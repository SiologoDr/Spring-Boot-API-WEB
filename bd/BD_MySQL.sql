CREATE DATABASE digital_buho_api;

USE digital_buho_api;

-- Rol
CREATE TABLE rol (
	id_rol INT AUTO_INCREMENT PRIMARY KEY,
    rol VARCHAR(100) NOT NULL UNIQUE,
    CHECK (rol IN ('CLIENTE', 'DESARROLLADOR', 'TECNICO')),
    descripcion VARCHAR(100)
);

-- Usuario 
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol) ON DELETE CASCADE
);

-- Persona
CREATE TABLE persona (
	id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL UNIQUE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Cliente
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    ruc VARCHAR(20) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_persona INT NOT NULL UNIQUE,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona) ON DELETE CASCADE
);

-- Técnico
CREATE TABLE tecnico (
    id_tecnico INT AUTO_INCREMENT PRIMARY KEY,
    especialidad VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_persona INT NOT NULL UNIQUE,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona) ON DELETE CASCADE
);

-- Desarrollador
CREATE TABLE desarrollador (
    id_desarrollador INT AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_persona INT NOT NULL UNIQUE,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona) ON DELETE CASCADE
);

-- Estado
CREATE TABLE estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50) NOT NULL UNIQUE,
    CHECK (estado IN ('EN_ESPERA', 'EN_DESARROLLO', 'FINALIZADO')),
    descripcion VARCHAR(100)
);

-- Revisión
CREATE TABLE revision (
    id_revision INT AUTO_INCREMENT PRIMARY KEY,
    revision VARCHAR(50) NOT NULL UNIQUE,
    CHECK (revision IN ('EN_ESPERA', 'APROBADO', 'RECHAZADO')),
    descripcion VARCHAR(100)
);

-- Solicitud
CREATE TABLE solicitud (
    id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL,
    observacion VARCHAR(50),
    prioridad VARCHAR(20),
    CHECK (prioridad IN ('ALTA', 'MEDIA', 'BAJA')),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_cliente INT NOT NULL,
    id_tecnico INT,
    id_estado INT NOT NULL DEFAULT 1,
    id_revision INT NOT NULL DEFAULT 1,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE,
	FOREIGN KEY (id_tecnico) REFERENCES tecnico(id_tecnico) ON DELETE CASCADE,
    FOREIGN KEY (id_estado) REFERENCES estado(id_estado) ON DELETE CASCADE,
    FOREIGN KEY (id_revision) REFERENCES revision(id_revision) ON DELETE CASCADE
);

-- Notificación Cliente
CREATE TABLE notificacion_cliente (
    id_notificacion_cliente INT AUTO_INCREMENT PRIMARY KEY,
    observacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_solicitud INT NOT NULL,
	FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud) ON DELETE CASCADE
);

-- Asignacion
CREATE TABLE asignacion (
	id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_solicitud INT NOT NULL,
    id_desarrollador INT NOT NULL,
    FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud) ON DELETE CASCADE,
    FOREIGN KEY (id_desarrollador) REFERENCES desarrollador(id_desarrollador) ON DELETE CASCADE
);

-- Notificación Desarrollador
CREATE TABLE notificacion_desarrollador (
    id_notificacion_desarrollador INT AUTO_INCREMENT PRIMARY KEY,
    observacion VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_asignacion INT NOT NULL,
	FOREIGN KEY (id_asignacion) REFERENCES asignacion(id_asignacion) ON DELETE CASCADE
);

