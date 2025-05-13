-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema Pucp_qatu_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Pucp_qatu_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Pucp_qatu_db` DEFAULT CHARACTER SET utf8mb3 ;
USE `Pucp_qatu_db` ;

-- -----------------------------------------------------
-- Table `pruebas_ta`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `codigo_PUCP` INT NOT NULL,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `estado` ENUM('HABILITADO', 'DESHABILITADO') NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Administrador` (
  `id_Administrador` INT NOT NULL AUTO_INCREMENT,
  `clave_Maestra` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Administrador`),
  CONSTRAINT `fk_Administrador_Usuario1`
    FOREIGN KEY (`id_Administrador`)
    REFERENCES `pruebas_ta`.`Usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Publicacion` (
  `idPublicacion` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `estado` ENUM('VISIBLE', 'GUARDADO', 'RESTRINGIDO', 'OCULTO') NOT NULL,
  `fechaPublicacion` DATE NOT NULL,
  `url_imagen` VARCHAR(45) NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idPublicacion`, `id_usuario`),
  INDEX `fk_Publicacion_Usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Publicacion_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `pruebas_ta`.`Usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `contenido` VARCHAR(45) NOT NULL,
  `valoracion` INT NULL DEFAULT NULL,
  `fecha` DATE NOT NULL,
  `id_publicacion` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `fk_Comentario_Publicacion1_idx` (`id_publicacion` ASC) VISIBLE,
  INDEX `fk_Comentario_Usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Comentario_Publicacion1`
    FOREIGN KEY (`id_publicacion`)
    REFERENCES `pruebas_ta`.`Publicacion` (`idPublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comentario_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `pruebas_ta`.`Usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_curso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Denuncia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Denuncia` (
  `id_reporte` INT NOT NULL AUTO_INCREMENT,
  `autor` INT NOT NULL,
  `reportante` INT NOT NULL,
  `motivo` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_reporte` DATE NOT NULL,
  `id_administrador` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_reporte`),
  INDEX `fk_Denuncia_Publicacion1_idx` (`autor` ASC) VISIBLE,
  INDEX `fk_Denuncia_Usuario1_idx` (`reportante` ASC) VISIBLE,
  INDEX `fk_Denuncia_Administrador1_idx` (`id_administrador` ASC) VISIBLE,
  CONSTRAINT `fk_Denuncia_Administrador1`
    FOREIGN KEY (`id_administrador`)
    REFERENCES `pruebas_ta`.`Administrador` (`id_Administrador`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Denuncia_Publicacion1`
    FOREIGN KEY (`autor`)
    REFERENCES `pruebas_ta`.`Publicacion` (`idPublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Denuncia_Usuario1`
    FOREIGN KEY (`reportante`)
    REFERENCES `pruebas_ta`.`Usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Especialidad` (
  `id_especialidad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_especialidad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Facultad` (
  `id_facultad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_facultad`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Notificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Notificacion` (
  `id_notificacion` INT NOT NULL AUTO_INCREMENT,
  `mensaje` VARCHAR(45) NULL DEFAULT NULL,
  `tipo_notificacion` VARCHAR(45) NOT NULL,
  `cantidad` INT NULL DEFAULT NULL,
  `fecha` DATE NOT NULL,
  `id_publicacion` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_notificacion`),
  INDEX `fk_Notificacion_Publicacion1_idx` (`id_publicacion` ASC) VISIBLE,
  INDEX `fk_Notificacion_Usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Notificacion_Publicacion1`
    FOREIGN KEY (`id_publicacion`)
    REFERENCES `pruebas_ta`.`Publicacion` (`idPublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Notificacion_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `pruebas_ta`.`Usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Publicacion_Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Publicacion_Curso` (
  `Publicacion_idPublicacion` INT NOT NULL,
  `Curso_id_curso` INT NOT NULL,
  PRIMARY KEY (`Publicacion_idPublicacion`, `Curso_id_curso`),
  INDEX `fk_Publicacion_has_Curso_Curso1_idx` (`Curso_id_curso` ASC) VISIBLE,
  INDEX `fk_Publicacion_has_Curso_Publicacion1_idx` (`Publicacion_idPublicacion` ASC) VISIBLE,
  CONSTRAINT `fk_Publicacion_has_Curso_Curso1`
    FOREIGN KEY (`Curso_id_curso`)
    REFERENCES `pruebas_ta`.`Curso` (`id_curso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Publicacion_has_Curso_Publicacion1`
    FOREIGN KEY (`Publicacion_idPublicacion`)
    REFERENCES `pruebas_ta`.`Publicacion` (`idPublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Publicacion_Especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Publicacion_Especialidad` (
  `Publicacion_idPublicacion` INT NOT NULL,
  `Especialidad_id_especialidad` INT NOT NULL,
  PRIMARY KEY (`Publicacion_idPublicacion`, `Especialidad_id_especialidad`),
  INDEX `fk_Publicacion_has_Especialidad_Especialidad1_idx` (`Especialidad_id_especialidad` ASC) VISIBLE,
  INDEX `fk_Publicacion_has_Especialidad_Publicacion1_idx` (`Publicacion_idPublicacion` ASC) VISIBLE,
  CONSTRAINT `fk_Publicacion_has_Especialidad_Especialidad1`
    FOREIGN KEY (`Especialidad_id_especialidad`)
    REFERENCES `pruebas_ta`.`Especialidad` (`id_especialidad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Publicacion_has_Especialidad_Publicacion1`
    FOREIGN KEY (`Publicacion_idPublicacion`)
    REFERENCES `pruebas_ta`.`Publicacion` (`idPublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pruebas_ta`.`Publicacion_Facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pruebas_ta`.`Publicacion_Facultad` (
  `Publicacion_idPublicacion` INT NOT NULL,
  `Facultad_id_facultad` INT NOT NULL,
  PRIMARY KEY (`Publicacion_idPublicacion`, `Facultad_id_facultad`),
  INDEX `fk_Publicacion_has_Facultad_Facultad1_idx` (`Facultad_id_facultad` ASC) VISIBLE,
  INDEX `fk_Publicacion_has_Facultad_Publicacion1_idx` (`Publicacion_idPublicacion` ASC) VISIBLE,
  CONSTRAINT `fk_Publicacion_has_Facultad_Facultad1`
    FOREIGN KEY (`Facultad_id_facultad`)
    REFERENCES `pruebas_ta`.`Facultad` (`id_facultad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Publicacion_has_Facultad_Publicacion1`
    FOREIGN KEY (`Publicacion_idPublicacion`)
    REFERENCES `pruebas_ta`.`Publicacion` (`idPublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- PROCEDIMIENTOS CURSO: ---------------

-- INSERTAR
DELIMITER $$

CREATE PROCEDURE INSERTAR_CURSO(
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    INSERT INTO Curso (nombre, activo)
    VALUES (p_nombre, p_activo);
END $$

DELIMITER ;

-- MODIFICAR

DELIMITER $$

CREATE PROCEDURE MODIFICAR_CURSO(
    IN p_id_curso INT,
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    UPDATE Curso
    SET nombre = p_nombre,
        activo = p_activo
    WHERE id_curso = p_id_curso;
END $$

DELIMITER ;

-- ELIMINAR
DELIMITER $$

CREATE PROCEDURE ELIMINAR_CURSO(
    IN p_id_curso INT
)
BEGIN
	UPDATE curso
    SET activo = 0
    WHERE id_curso = p_id_curso;
END $$

DELIMITER ;

-- OBTENER POR ID

DELIMITER $$

CREATE PROCEDURE OBTENER_CURSO_X_ID(
    IN p_id_curso INT
)
BEGIN
    SELECT id_curso, nombre, activo
    FROM Curso
    WHERE id_curso = p_id_curso;
END $$

DELIMITER ;

-- OBTENER TODOS

DELIMITER $$

CREATE PROCEDURE LISTAR_CURSO_TODOS()
BEGIN
    SELECT id_curso, nombre, activo
    FROM Curso;
END $$

DELIMITER ;

-- PROCEDIMIENTOS ESPECIALIDAD: ----------------

-- INSERTAR

DELIMITER $$

CREATE PROCEDURE INSERTAR_ESPECIALIDAD(
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    INSERT INTO especialidad (nombre, activo)
    VALUES (p_nombre, p_activo);
END $$

DELIMITER ;

-- MODIFICAR

DELIMITER $$

CREATE PROCEDURE MODIFICAR_ESPECIALIDAD(
    IN p_id_especialidad INT,
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    UPDATE especialidad
    SET nombre = p_nombre,
        activo = p_activo
    WHERE id_especialidad = p_id_especialidad;
END $$

DELIMITER ;

-- ELIMINAR

DELIMITER $$

CREATE PROCEDURE ELIMINAR_ESPECIALIDAD(
    IN p_id_especialidad INT
)
BEGIN
	UPDATE especialidad
    SET activo = 0
    WHERE id_especialidad = p_id_especialidad;
END $$

DELIMITER ;

-- OBTENER POR ID

DELIMITER $$

CREATE PROCEDURE OBTENER_ESPECIALIDAD_X_ID(
    IN p_id_especialidad INT
)
BEGIN
    SELECT id_especialidad, nombre, activo
    FROM especialidad
    WHERE id_especialidad = p_id_especialidad;
END $$

DELIMITER ;

-- OBTENER TODOS

DELIMITER $$

CREATE PROCEDURE LISTAR_ESPECIALIDAD_TODOS()
BEGIN
    SELECT id_especialidad, nombre, activo
    FROM especialidad;
END $$

DELIMITER ;

-- PROCEDIMIENTOS FACULTAD: ------------------------------

-- INSERTAR 

DELIMITER $$

CREATE PROCEDURE INSERTAR_FACULTAD(
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    INSERT INTO facultad (nombre, activo)
    VALUES (p_nombre, p_activo);
END $$

DELIMITER ;

-- MODIFICAR

DELIMITER $$

CREATE PROCEDURE MODIFICAR_FACULTAD(
    IN p_id_facultad INT,
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    UPDATE facultad
    SET nombre = p_nombre,
        activo = p_activo
    WHERE id_facultad = p_id_facultad;
END $$

DELIMITER ;

-- ELIMINAR

DELIMITER $$

CREATE PROCEDURE ELIMINAR_FACULTAD(
    IN p_id_facultad INT
)
BEGIN
	UPDATE facultad
    SET activo = 0
    WHERE id_facultad = p_id_facultad;
END $$

DELIMITER ;

-- OBTENER POR ID

DELIMITER $$

CREATE PROCEDURE OBTENER_FACULTAD_X_ID(
    IN p_id_facultad INT
)
BEGIN
    SELECT id_facultad, nombre, activo
    FROM facultad
    WHERE id_facultad = p_id_facultad;
END $$

DELIMITER ;

-- OBTENER TODOS


DELIMITER $$

CREATE PROCEDURE LISTAR_FACULTAD_TODOS()
BEGIN
    SELECT id_facultad, nombre, activo
    FROM facultad;
END $$

DELIMITER ;

-- PROCEDIMIENTOS NOTIFICACION: --------------------

-- INSERTAR

DELIMITER $$

CREATE PROCEDURE INSERTAR_NOTIFICACION(
    IN p_mensaje VARCHAR(45),
    IN p_tipo_notificacion VARCHAR(45),
    IN p_cantidad INT,
    IN p_fecha DATE,
    IN p_id_publicacion INT,
    IN p_id_usuario INT,
    IN p_activo TINYINT
)
BEGIN
    INSERT INTO Notificacion (
        mensaje, tipo_notificacion, cantidad, fecha,
        id_publicacion, id_usuario, activo
    ) VALUES (
        p_mensaje, p_tipo_notificacion, p_cantidad, p_fecha,
        p_id_publicacion, p_id_usuario, p_activo
    );
END $$

DELIMITER ;

-- MODIFICAR

DELIMITER $$

CREATE PROCEDURE MODIFICAR_NOTIFICACION(
    IN p_id_notificacion INT,
    IN p_mensaje VARCHAR(45),
    IN p_tipo_notificacion VARCHAR(45),
    IN p_cantidad INT,
    IN p_fecha DATE,
    IN p_id_publicacion INT,
    IN p_id_usuario INT,
    IN p_activo TINYINT
)
BEGIN
    UPDATE Notificacion
    SET mensaje = p_mensaje,
        tipo_notificacion = p_tipo_notificacion,
        cantidad = p_cantidad,
        fecha = p_fecha,
        id_publicacion = p_id_publicacion,
        id_usuario = p_id_usuario,
        activo = p_activo
    WHERE id_notificacion = p_id_notificacion;
END $$

DELIMITER ;

-- ELIMINAR
DELIMITER $$

CREATE PROCEDURE ELIMINAR_NOTIFICACION(
    IN p_id_notificacion INT
)
BEGIN
    UPDATE notificacion
    SET activo = 0    
    WHERE id_notificacion = p_id_notificacion;
END $$

DELIMITER ;

-- OBTENER POR ID
DELIMITER $$

CREATE PROCEDURE OBTENER_NOTIFICACION_X_ID(
    IN p_id_notificacion INT
)
BEGIN
    SELECT id_notificacion, mensaje, tipo_notificacion, cantidad,
           fecha, id_publicacion, id_usuario, activo
    FROM Notificacion
    WHERE id_notificacion = p_id_notificacion;
END $$

DELIMITER ;

-- OBTENER TODOS

DELIMITER $$

CREATE PROCEDURE LISTAR_NOTIFICACION_TODOS()
BEGIN
    SELECT id_notificacion, mensaje, tipo_notificacion, cantidad,
           fecha, id_publicacion, id_usuario, activo
    FROM Notificacion;
END $$

DELIMITER ;


-- PROCEDIMIENTOS PUBLICACION: -----------------

-- INSERTAR
DELIMITER $$

CREATE PROCEDURE INSERTAR_PUBLICACION (
    IN p_titulo VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_estado VARCHAR(50),
    IN p_fecha DATE,
    IN p_rutaImagen VARCHAR(255),
    IN p_idUsuario INT,
    IN p_activo BOOLEAN
)
BEGIN
    INSERT INTO publicacion (
        titulo, descripcion, estado, fechaPublicacion,
        url_imagen, id_usuario, activo
    )
    VALUES (
        p_titulo, p_descripcion, p_estado, p_fecha,
        p_rutaImagen, p_idUsuario, p_activo
    );
END$$

DELIMITER ;

-- ACTUALIZAR
	DELIMITER $$

CREATE PROCEDURE MODIFICAR_PUBLICACION (
    IN p_id INT,
    IN p_titulo VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_estado VARCHAR(50),
    IN p_fecha DATE,
    IN p_rutaImagen VARCHAR(255),
    IN p_idUsuario INT,
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE publicacion
    SET
        titulo = p_titulo,
        descripcion = p_descripcion,
        estado = p_estado,
        fechaPublicacion = p_fecha,
        url_imagen = p_rutaImagen,
        id_usuario = p_idUsuario,
        activo = p_activo
    WHERE idPublicacion = p_id;
END$$

DELIMITER ;

-- ELIMINAR
DELIMITER $$

CREATE PROCEDURE ELIMINAR_PUBLICACION (
    IN p_id INT
)
BEGIN
    UPDATE publicacion
    SET activo = 0
    WHERE idPublicacion = p_id;
END$$

DELIMITER ;

-- SELECCIONAR POR ID
	DELIMITER $$

CREATE PROCEDURE OBTENER_PUBLICACION_X_ID (
    IN p_id INT
)
BEGIN
    SELECT * FROM publicacion
    WHERE idPublicacion = p_id;
END$$

DELIMITER ;

-- SELECCIONAR TODOS
DELIMITER $$

CREATE PROCEDURE LISTAR_PUBLICACION_TODOS()
BEGIN
    SELECT * FROM publicacion;
END$$

DELIMITER ;

-- SELECCIONAR POR FACULTAD TODOS
DELIMITER $$

CREATE PROCEDURE LISTAR_PUBLICACION_X_FACULTAD_TODOS (
    IN p_id_facultad INT
)
BEGIN
    SELECT p.*
    FROM publicacion p
    JOIN usuario u ON p.id_usuario = u.idUsuario
    WHERE u.id_facultad = p_id_facultad;
END$$

DELIMITER ;


-- SELECCIONAR POR ESPECIALIDAD TODOS
DELIMITER $$

CREATE PROCEDURE LISTAR_PUBLICACION_X_ESPECIALIDAD_TODOS (
    IN p_id_especialidad INT
)
BEGIN
    SELECT p.*
    FROM publicacion p
    JOIN usuario u ON p.id_usuario = u.idUsuario
    WHERE u.id_especialidad = p_id_especialidad;
END$$

DELIMITER ;


-- SELECCIONAR POR CURSO TODOS
DELIMITER $$

CREATE PROCEDURE LISTAR_PUBLICACION_X_CURSO_TODOS (
    IN p_id_curso INT
)
BEGIN
    SELECT p.*
    FROM publicacion p
    JOIN usuario u ON p.id_usuario = u.idUsuario
    WHERE u.id_curso = p_id_curso;
END$$

DELIMITER ;

-- PROCEDIMIENTOS COMENTARIOS ------------------
-- insertar
DELIMITER $$

CREATE PROCEDURE INSERTAR_COMENTARIO (
    IN p_contenido TEXT,
    IN p_valoracion INT,
    IN p_fecha DATE,
    IN p_id_publicacion INT,
    IN p_id_usuario INT,
    IN p_activo BOOLEAN
)
BEGIN
    INSERT INTO comentario (
        contenido, valoracion, fecha, id_publicacion, id_usuario, activo
    ) VALUES (
        p_contenido, p_valoracion, p_fecha, p_id_publicacion, p_id_usuario, p_activo
    );
END$$

-- modificar
CREATE PROCEDURE MODIFICAR_COMENTARIO (
    IN p_id_comentario INT,
    IN p_contenido TEXT,
    IN p_valoracion INT,
    IN p_fecha DATE,
    IN p_id_publicacion INT,
    IN p_id_usuario INT,
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE comentario
    SET contenido = p_contenido,
        valoracion = p_valoracion,
        fecha = p_fecha,
        id_publicacion = p_id_publicacion,
        id_usuario = p_id_usuario,
        activo = p_activo
    WHERE id_comentario = p_id_comentario;
END$$
-- eliminar
CREATE PROCEDURE ELIMINAR_COMENTARIO (
    IN p_id_comentario INT
)
BEGIN
    UPDATE comentario
    SET activo = 0
    WHERE id_comentario = p_id_comentario;
END$$
-- obtener por id
CREATE PROCEDURE OBTENER_COMENTARIO_X_ID (
    IN p_id_comentario INT
)
BEGIN
    SELECT * FROM comentario
    WHERE id_comentario = p_id_comentario;
END$$
-- listar todos
CREATE PROCEDURE LISTAR_COMENTARIO_TODOS()
BEGIN
    SELECT * FROM comentario;
END$$

DELIMITER ;

-- Procedimientos Denuncias -------------------------
DELIMITER $$

-- Procedimiento para insertar una denuncia
CREATE PROCEDURE INSERTAR_DENUNCIA (
    IN p_autor INT,
    IN p_denunciante INT,
    IN p_motivo VARCHAR(255),
    IN p_fecha_denuncia DATE,
    IN p_admin INT,
    IN p_activo BOOLEAN
)
BEGIN
    INSERT INTO denuncia (
        autor, reportante, motivo, fecha_reporte, id_administrador, activo
    ) VALUES (
        p_autor, p_denunciante, p_motivo, p_fecha_denuncia, p_admin, p_activo
    );
END$$

-- Procedimiento para modificar una denuncia
CREATE PROCEDURE MODIFICAR_DENUNCIA (
    IN p_id_reporte INT,
    IN p_autor INT,
    IN p_denunciante INT,
    IN p_motivo VARCHAR(255),
    IN p_fecha_denuncia DATE,
    IN p_admin INT,
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE denuncia
    SET autor = p_autor,
        reportante = p_denunciante,
        motivo = p_motivo,
        fecha_reporte = p_fecha_denuncia,
        id_administrador = p_admin,
        activo = p_activo
    WHERE id_reporte = p_id_reporte;
END$$

-- Procedimiento para eliminar una denuncia (borrado lógico, estableciendo 'activo' a false)
CREATE PROCEDURE ELIMINAR_DENUNCIA (
    IN p_id_reporte INT
)
BEGIN
    UPDATE denuncia
    SET activo = 0
    WHERE id_reporte = p_id_reporte;
END$$

-- Procedimiento para obtener una denuncia por ID
CREATE PROCEDURE OBTENER_DENUNCIA_X_ID (
    IN p_id_reporte INT
)
BEGIN
    SELECT * FROM denuncia
    WHERE id_reporte = p_id_reporte;
END$$

-- Procedimiento para listar todas las denuncias
CREATE PROCEDURE LISTAR_DENUNCIA_TODOS()
BEGIN
    SELECT * FROM denuncia;
END$$

DELIMITER ;

-- Procedimientos USUARIOS
DELIMITER $$

-- Procedimiento para insertar un usuario
CREATE PROCEDURE INSERTAR_USUARIO (
    IN p_codigo_pucp INT,
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_estado VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    INSERT INTO usuario (
        codigo_PUCP, nombreUsuario, contrasena, nombre, correo, estado, activo
    ) VALUES (
        p_codigo_pucp, p_nombre_usuario, p_contrasena, p_nombre, p_correo, p_estado, p_activo
    );
END$$

-- Procedimiento para modificar un usuario
CREATE PROCEDURE MODIFICAR_USUARIO (
    IN p_id_usuario INT,
    IN p_codigo_pucp INT,
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_estado VARCHAR(50),
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE usuario
    SET codigo_PUCP = p_codigo_pucp,
        nombreUsuario = p_nombre_usuario,
        contrasena = p_contrasena,
        nombre = p_nombre,
        correo = p_correo,
        estado = p_estado,
        activo = p_activo
    WHERE id_usuario = p_id_usuario;
END$$

-- Procedimiento para eliminar un usuario (borrado lógico, estableciendo 'activo' a false)
CREATE PROCEDURE ELIMINAR_USUARIO (
    IN p_id_usuario INT
)
BEGIN
    UPDATE usuario
    SET activo = 0
    WHERE id_usuario = p_id_usuario;
END$$

-- Procedimiento para obtener un usuario por ID
CREATE PROCEDURE OBTENER_USUARIO_X_ID (
    IN p_id_usuario INT
)
BEGIN
    SELECT * FROM usuario
    WHERE id_usuario = p_id_usuario;
END$$

-- Procedimiento para listar todos los usuarios
CREATE PROCEDURE LISTAR_USUARIO_TODOS()
BEGIN
    SELECT * FROM usuario;
END$$

DELIMITER ;

-- Procedimientos Administrador

DELIMITER $$

-- Procedimiento para insertar un administrador
CREATE PROCEDURE INSERTAR_ADMINISTRADOR (
    IN p_codigo_pucp INT,
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_estado VARCHAR(50),
    IN p_activo BOOLEAN,
    IN p_clave_maestra VARCHAR(255)
)
BEGIN
    INSERT INTO administrador (
        codigo_PUCP, nombreUsuario, contrasena, nombre, correo, estado, activo, clave_Maestra
    ) VALUES (
        p_codigo_pucp, p_nombre_usuario, p_contrasena, p_nombre, p_correo, p_estado, p_activo, p_clave_maestra
    );
END$$

-- Procedimiento para modificar un administrador
CREATE PROCEDURE MODIFICAR_ADMINISTRADOR (
    IN p_id_usuario INT,
    IN p_codigo_pucp INT,
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_estado VARCHAR(50),
    IN p_activo BOOLEAN,
    IN p_clave_maestra VARCHAR(255)
)
BEGIN
    UPDATE administrador
    SET codigo_PUCP = p_codigo_pucp,
        nombreUsuario = p_nombre_usuario,
        contrasena = p_contrasena,
        nombre = p_nombre,
        correo = p_correo,
        estado = p_estado,
        activo = p_activo,
        clave_Maestra = p_clave_maestra
    WHERE id_usuario = p_id_usuario;
END$$

-- Procedimiento para eliminar un administrador (borrado lógico, estableciendo 'activo' a false)
CREATE PROCEDURE ELIMINAR_ADMINISTRADOR (
    IN p_id_usuario INT
)
BEGIN
    UPDATE administrador
    SET activo = 0
    WHERE id_usuario = p_id_usuario;
END$$

-- Procedimiento para obtener un administrador por ID
CREATE PROCEDURE OBTENER_ADMINISTRADOR_X_ID (
    IN p_id_usuario INT
)
BEGIN
    SELECT * FROM administrador
    WHERE id_usuario = p_id_usuario;
END$$

-- Procedimiento para listar todos los administradores
CREATE PROCEDURE LISTAR_ADMINISTRADOR_TODOS()
BEGIN
    SELECT * FROM administrador;
END$$

DELIMITER ;
