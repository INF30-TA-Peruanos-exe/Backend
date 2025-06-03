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
-- Table `Pucp_qatu_db`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `codigo_PUCP` INT NOT NULL,
  `nombreusuario` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `estado` ENUM('HABILITADO', 'DESHABILITADO') NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 127
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`administrador` (
  `id_administrador` INT NOT NULL AUTO_INCREMENT,
  `clave_Maestra` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_administrador`),
  CONSTRAINT `fk_administrador_usuario1`
    FOREIGN KEY (`id_administrador`)
    REFERENCES `Pucp_qatu_db`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 127
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`publicacion` (
  `idpublicacion` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `estado` ENUM('VISIBLE', 'GUARDADO', 'RESTRINGIDO', 'OCULTO') NOT NULL,
  `fechapublicacion` DATE NOT NULL,
  `url_imagen` VARCHAR(45) NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idpublicacion`, `id_usuario`),
  INDEX `fk_publicacion_usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_publicacion_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Pucp_qatu_db`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `contenido` VARCHAR(45) NOT NULL,
  `valoracion` INT NULL DEFAULT NULL,
  `fecha` DATE NOT NULL,
  `id_publicacion` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `fk_comentario_publicacion1_idx` (`id_publicacion` ASC) VISIBLE,
  INDEX `fk_comentario_usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_comentario_publicacion1`
    FOREIGN KEY (`id_publicacion`)
    REFERENCES `Pucp_qatu_db`.`publicacion` (`idpublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comentario_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Pucp_qatu_db`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_curso`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`denuncia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`denuncia` (
  `id_reporte` INT NOT NULL AUTO_INCREMENT,
  `autor` INT NOT NULL,
  `reportante` INT NOT NULL,
  `motivo` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_reporte` DATE NOT NULL,
  `id_administrador` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_reporte`),
  INDEX `fk_denuncia_publicacion1_idx` (`autor` ASC) VISIBLE,
  INDEX `fk_denuncia_usuario1_idx` (`reportante` ASC) VISIBLE,
  INDEX `fk_denuncia_administrador1_idx` (`id_administrador` ASC) VISIBLE,
  CONSTRAINT `fk_denuncia_administrador1`
    FOREIGN KEY (`id_administrador`)
    REFERENCES `Pucp_qatu_db`.`administrador` (`id_administrador`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_denuncia_publicacion1`
    FOREIGN KEY (`autor`)
    REFERENCES `Pucp_qatu_db`.`publicacion` (`idpublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_denuncia_usuario1`
    FOREIGN KEY (`reportante`)
    REFERENCES `Pucp_qatu_db`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`especialidad` (
  `id_especialidad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_especialidad`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`facultad` (
  `id_facultad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_facultad`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`notificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`notificacion` (
  `id_notificacion` INT NOT NULL AUTO_INCREMENT,
  `mensaje` VARCHAR(45) NULL DEFAULT NULL,
  `tipo_notificacion` VARCHAR(45) NOT NULL,
  `cantidad` INT NULL DEFAULT NULL,
  `fecha` DATE NOT NULL,
  `id_publicacion` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_notificacion`),
  INDEX `fk_notificacion_publicacion1_idx` (`id_publicacion` ASC) VISIBLE,
  INDEX `fk_notificacion_usuario1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_NOTIFICACION_publicacion1`
    FOREIGN KEY (`id_publicacion`)
    REFERENCES `Pucp_qatu_db`.`publicacion` (`idpublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_notificacion_usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Pucp_qatu_db`.`usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`publicacion_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`publicacion_curso` (
  `publicacion_idpublicacion` INT NOT NULL,
  `curso_id_curso` INT NOT NULL,
  PRIMARY KEY (`publicacion_idpublicacion`, `curso_id_curso`),
  INDEX `fk_publicacion_has_curso_curso1_idx` (`curso_id_curso` ASC) VISIBLE,
  INDEX `fk_publicacion_has_curso_publicacion1_idx` (`publicacion_idpublicacion` ASC) VISIBLE,
  CONSTRAINT `fk_publicacion_has_curso_curso1`
    FOREIGN KEY (`curso_id_curso`)
    REFERENCES `Pucp_qatu_db`.`curso` (`id_curso`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_publicacion_has_curso_publicacion1`
    FOREIGN KEY (`publicacion_idpublicacion`)
    REFERENCES `Pucp_qatu_db`.`publicacion` (`idpublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`publicacion_especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`publicacion_especialidad` (
  `publicacion_idpublicacion` INT NOT NULL,
  `especialidad_id_especialidad` INT NOT NULL,
  PRIMARY KEY (`publicacion_idpublicacion`, `especialidad_id_especialidad`),
  INDEX `fk_publicacion_has_especialidad_especialidad1_idx` (`especialidad_id_especialidad` ASC) VISIBLE,
  INDEX `fk_publicacion_has_especialidad_publicacion1_idx` (`publicacion_idpublicacion` ASC) VISIBLE,
  CONSTRAINT `fk_publicacion_has_especialidad_especialidad1`
    FOREIGN KEY (`especialidad_id_especialidad`)
    REFERENCES `Pucp_qatu_db`.`especialidad` (`id_especialidad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_publicacion_has_especialidad_publicacion1`
    FOREIGN KEY (`publicacion_idpublicacion`)
    REFERENCES `Pucp_qatu_db`.`publicacion` (`idpublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `Pucp_qatu_db`.`publicacion_facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pucp_qatu_db`.`publicacion_facultad` (
  `publicacion_idpublicacion` INT NOT NULL,
  `facultad_id_facultad` INT NOT NULL,
  PRIMARY KEY (`publicacion_idpublicacion`, `facultad_id_facultad`),
  INDEX `fk_publicacion_has_facultad_facultad1_idx` (`facultad_id_facultad` ASC) VISIBLE,
  INDEX `fk_publicacion_has_facultad_publicacion1_idx` (`publicacion_idpublicacion` ASC) VISIBLE,
  CONSTRAINT `fk_publicacion_has_facultad_facultad1`
    FOREIGN KEY (`facultad_id_facultad`)
    REFERENCES `Pucp_qatu_db`.`facultad` (`id_facultad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_publicacion_has_facultad_publicacion1`
    FOREIGN KEY (`publicacion_idpublicacion`)
    REFERENCES `Pucp_qatu_db`.`publicacion` (`idpublicacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE `Pucp_qatu_db` ;

-- -----------------------------------------------------
-- procedure ELIMINAR_ADMINISTRADOR
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_ADMINISTRADOR`(
    IN p_id_usuario INT
)
BEGIN
    -- Solo se desactiva el usuario (soft delete)
    UPDATE usuario
    SET activo = FALSE
    WHERE id_usuario = p_id_usuario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_COMENTARIO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_COMENTARIO`(
    IN p_id_comentario INT
)
BEGIN
    UPDATE comentario
    SET activo = 0
    WHERE id_comentario = p_id_comentario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_CURSO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_CURSO`(
    IN p_id_curso INT
)
BEGIN
	UPDATE curso
    SET activo = 0
    WHERE id_curso = p_id_curso;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_DENUNCIA
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_DENUNCIA`(
    IN p_id_reporte INT
)
BEGIN
    UPDATE denuncia
    SET activo = 0
    WHERE id_reporte = p_id_reporte;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_ESPECIALIDAD
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_ESPECIALIDAD`(
    IN p_id_especialidad INT
)
BEGIN
	UPDATE especialidad
    SET activo = 0
    WHERE id_especialidad = p_id_especialidad;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_FACULTAD
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_FACULTAD`(
    IN p_id_facultad INT
)
BEGIN
	UPDATE facultad
    SET activo = 0
    WHERE id_facultad = p_id_facultad;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_NOTIFICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_NOTIFICACION`(
    IN p_id_notificacion INT
)
BEGIN
    UPDATE notificacion
    SET activo = 0    
    WHERE id_notificacion = p_id_notificacion;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_PUBLICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_PUBLICACION`(
    IN p_id INT
)
BEGIN
    UPDATE publicacion
    SET activo = 0
    WHERE idpublicacion = p_id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ELIMINAR_USUARIO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `ELIMINAR_USUARIO`(
    IN p_id_usuario INT
)
BEGIN
    UPDATE usuario
    SET activo = 0
    WHERE id_usuario = p_id_usuario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_ADMINISTRADOR
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_ADMINISTRADOR`(
    IN p_codigo_pucp INT,
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_estado VARCHAR(50),
    IN p_activo BOOLEAN,
    IN p_clave_maestra VARCHAR(255),
    OUT p_id_usuario INT
)
BEGIN
    -- Insertar en la tabla usuario
    INSERT INTO usuario (
        codigo_PUCP, nombreusuario, contrasena, nombre, correo, estado, activo
    ) VALUES (
        p_codigo_pucp, p_nombre_usuario, p_contrasena, p_nombre, p_correo, p_estado, p_activo
    );

    -- Obtener ID generado
    SET p_id_usuario = LAST_INSERT_ID();

    -- Insertar en la tabla administrador
    INSERT INTO administrador (
        id_administrador, clave_maestra
    ) VALUES (
        p_id_usuario, p_clave_maestra
    );
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_COMENTARIO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_COMENTARIO`(
    IN p_contenido TEXT,
    IN p_valoracion INT,
    IN p_fecha DATE,
    IN p_id_publicacion INT,
    IN p_id_usuario INT,
    IN p_activo BOOLEAN,
    OUT p_id_comentario INT
)
BEGIN
    INSERT INTO comentario (
        contenido, valoracion, fecha, id_publicacion, id_usuario, activo
    ) VALUES (
        p_contenido, p_valoracion, p_fecha, p_id_publicacion, p_id_usuario, p_activo
    );

    -- ASIGNAR OUT
    set p_id_comentario = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_CURSO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_CURSO`(
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT,
    OUT p_id_curso INT
)
BEGIN
    INSERT INTO curso (nombre, activo)
    VALUES (p_nombre, p_activo);

    -- asignar out
    SET p_id_curso = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_DENUNCIA
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_DENUNCIA`(
    IN p_autor INT,
    IN p_denunciante INT,
    IN p_motivo VARCHAR(255),
    IN p_fecha_denuncia DATE,
    IN p_admin INT,
    IN p_activo BOOLEAN,
    OUT p_id_denuncia INT
)
BEGIN
    INSERT INTO denuncia (
        autor, reportante, motivo, fecha_reporte, id_administrador, activo
    ) VALUES (
        p_autor, p_denunciante, p_motivo, p_fecha_denuncia, p_admin, p_activo
    );
    
    SET p_id_denuncia = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_ESPECIALIDAD
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_ESPECIALIDAD`(
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT,
    OUT p_id_especialidad INT
)
BEGIN
    INSERT INTO especialidad (nombre, activo)
    VALUES (p_nombre, p_activo);

    -- ASIGNAR OUT
    SET p_id_especialidad = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_FACULTAD
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_FACULTAD`(
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT,
    OUT p_id_facultad INT
)
BEGIN
    INSERT INTO facultad (nombre, activo)
    VALUES (p_nombre, p_activo);

    -- Asignar Out
    SET p_id_facultad = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_NOTIFICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_NOTIFICACION`(
    IN p_mensaje VARCHAR(45),
    IN p_tipo_notificacion VARCHAR(45),
    IN p_cantidad INT,
    IN p_fecha DATE,
    IN p_id_publicacion INT,
    IN p_id_usuario INT,
    IN p_activo TINYINT,
    OUT p_id_facultad INT
)
BEGIN
    INSERT INTO notificacion (
        mensaje, tipo_notificacion, cantidad, fecha,
        id_publicacion, id_usuario, activo
    ) VALUES (
        p_mensaje, p_tipo_notificacion, p_cantidad, p_fecha,
        p_id_publicacion, p_id_usuario, p_activo
    );

    -- ASIGNAR OUT
    SET p_id_publicacion = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_PUBLICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_PUBLICACION`(
    IN p_titulo VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_estado VARCHAR(50),
    IN p_fecha DATE,
    IN p_rutaImagen VARCHAR(255),
    IN p_idusuario INT,
    IN p_activo BOOLEAN,
    OUT p_id_publicacion INT
)
BEGIN
    INSERT INTO publicacion (
        titulo, descripcion, estado, fechapublicacion,
        url_imagen, id_usuario, activo
    )
    VALUES (
        p_titulo, p_descripcion, p_estado, p_fecha,
        p_rutaImagen, p_idusuario, p_activo
    );

    -- ASIGNAR OUT
    SET p_id_publicacion = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure INSERTAR_USUARIO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `INSERTAR_USUARIO`(
    IN p_codigo_pucp INT,
    IN p_nombre_usuario VARCHAR(50),
    IN p_contrasena VARCHAR(255),
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_estado VARCHAR(50),
    IN p_activo BOOLEAN,
    OUT p_id_usuario INT
)
BEGIN
    INSERT INTO usuario (
        codigo_PUCP, nombreusuario, contrasena, nombre, correo, estado, activo
    ) VALUES (
        p_codigo_pucp, p_nombre_usuario, p_contrasena, p_nombre, p_correo, p_estado, p_activo
    );
    
    SET p_id_usuario = LAST_INSERT_ID();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_ADMINISTRADOR_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_ADMINISTRADOR_TODOS`()
BEGIN
    SELECT 
        u.id_usuario, 
        u.codigo_PUCP, 
        u.nombreUsuario, 
        u.contrasena, 
        u.nombre, 
        u.correo, 
        u.estado, 
        u.activo,
        a.clave_maestra
    FROM usuario u
    INNER JOIN administrador a 
        ON u.id_usuario = a.id_administrador;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_COMENTARIO_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_COMENTARIO_TODOS`()
BEGIN
    SELECT * FROM comentario WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_CURSOS_X_PUBLICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_CURSOS_X_PUBLICACION`(
    IN p_id_publicacion INT
)
BEGIN
    SELECT c.id_curso, c.nombre, c.activo
    FROM curso c
    INNER JOIN publicacion_curso pc ON c.id_curso = pc.curso_id_curso
    WHERE pc.publicacion_idpublicacion = p_id_publicacion;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_CURSO_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_CURSO_TODOS`()
BEGIN
    SELECT id_curso, nombre, activo
    FROM curso
	WHERE activo=1
;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_DENUNCIA_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_DENUNCIA_TODOS`()
BEGIN
    SELECT * FROM denuncia WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_ESPECIALIDADES_X_PUBLICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_ESPECIALIDADES_X_PUBLICACION`(
    IN p_id_publicacion INT
)
BEGIN
    SELECT e.id_especialidad, e.nombre, e.activo
    FROM especialidad e
    INNER JOIN publicacion_especialidad pe ON e.id_especialidad = pe.especialidad_id_especialidad
    WHERE pe.publicacion_idpublicacion = p_id_publicacion;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_ESPECIALIDAD_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_ESPECIALIDAD_TODOS`()
BEGIN
    SELECT id_especialidad, nombre, activo
    FROM especialidad
    WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_FACULTADES_X_PUBLICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_FACULTADES_X_PUBLICACION`(
    IN p_id_publicacion INT
)
BEGIN
    SELECT f.id_facultad, f.nombre, f.activo
    FROM facultad f
    INNER JOIN publicacion_facultad pf ON f.id_facultad = pf.facultad_id_facultad
    WHERE pf.publicacion_idpublicacion = p_id_publicacion;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_FACULTAD_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_FACULTAD_TODOS`()
BEGIN
    SELECT id_facultad, nombre, activo
    FROM facultad
    WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_NOTIFICACION_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_NOTIFICACION_TODOS`()
BEGIN
    SELECT id_notificacion, mensaje, tipo_notificacion, cantidad,
           fecha, id_publicacion, id_usuario, activo
    FROM notificacion
    WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_PUBLICACION_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_PUBLICACION_TODOS`()
BEGIN
    SELECT * FROM publicacion WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_PUBLICACION_X_CURSO_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_PUBLICACION_X_CURSO_TODOS`(
    IN p_id_curso INT
)
BEGIN
    SELECT p.*
    FROM publicacion p, publicacion_curso pc
    WHERE p.idpublicacion = pc.publicacion_idpublicacion
    AND pc.curso_id_curso = p_id_curso
    AND p.activo = 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_PUBLICACION_X_ESPECIALIDAD_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_PUBLICACION_X_ESPECIALIDAD_TODOS`(
    IN p_id_especialidad INT
)
BEGIN
    SELECT p.*
    FROM publicacion p, publicacion_especialidad pe
    WHERE p.idpublicacion = pe.publicacion_idpublicacion
    AND pe.especialidad_id_especialidad = p_id_especialidad
    AND p.activo = 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_PUBLICACION_X_FACULTAD_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_PUBLICACION_X_FACULTAD_TODOS`(
    IN p_id_facultad INT
)
BEGIN
    SELECT p.*
    FROM publicacion p, publicacion_facultad pf
    WHERE p.idpublicacion = pf.publicacion_idpublicacion
    AND pf.facultad_id_facultad = p_id_facultad
    AND p.activo = 1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure LISTAR_USUARIO_TODOS
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_USUARIO_TODOS`()
BEGIN
    SELECT * FROM usuario WHERE activo=1;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_ADMINISTRADOR
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_ADMINISTRADOR`(
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
    -- 1. Actualizar la tabla usuario
    UPDATE usuario
    SET 
        codigo_PUCP = p_codigo_pucp,
        nombreusuario = p_nombre_usuario,
        contrasena = p_contrasena,
        nombre = p_nombre,
        correo = p_correo,
        estado = p_estado,
        activo = p_activo
    WHERE id_usuario = p_id_usuario;

    -- 2. Actualizar la tabla administrador
    UPDATE administrador
    SET clave_maestra = p_clave_maestra
    WHERE id_administrador = p_id_usuario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_COMENTARIO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_COMENTARIO`(
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

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_CURSO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_CURSO`(
    IN p_id_curso INT,
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    UPDATE curso
    SET nombre = p_nombre,
        activo = p_activo
    WHERE id_curso = p_id_curso;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_DENUNCIA
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_DENUNCIA`(
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

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_ESPECIALIDAD
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_ESPECIALIDAD`(
    IN p_id_especialidad INT,
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    UPDATE especialidad
    SET nombre = p_nombre,
        activo = p_activo
    WHERE id_especialidad = p_id_especialidad;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_FACULTAD
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_FACULTAD`(
    IN p_id_facultad INT,
    IN p_nombre VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    UPDATE facultad
    SET nombre = p_nombre,
        activo = p_activo
    WHERE id_facultad = p_id_facultad;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_NOTIFICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_NOTIFICACION`(
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
    UPDATE notificacion
    SET mensaje = p_mensaje,
        tipo_notificacion = p_tipo_notificacion,
        cantidad = p_cantidad,
        fecha = p_fecha,
        id_publicacion = p_id_publicacion,
        id_usuario = p_id_usuario,
        activo = p_activo
    WHERE id_notificacion = p_id_notificacion;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_PUBLICACION
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_PUBLICACION`(
    IN p_id INT,
    IN p_titulo VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_estado VARCHAR(50),
    IN p_fecha DATE,
    IN p_rutaImagen VARCHAR(255),
    IN p_idusuario INT,
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE publicacion
    SET
        titulo = p_titulo,
        descripcion = p_descripcion,
        estado = p_estado,
        fechapublicacion = p_fecha,
        url_imagen = p_rutaImagen,
        id_usuario = p_idusuario,
        activo = p_activo
    WHERE idpublicacion = p_id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure MODIFICAR_USUARIO
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_USUARIO`(
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
        nombreusuario = p_nombre_usuario,
        contrasena = p_contrasena,
        nombre = p_nombre,
        correo = p_correo,
        estado = p_estado,
        activo = p_activo
    WHERE id_usuario = p_id_usuario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_ADMINISTRADOR_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_ADMINISTRADOR_X_ID`(
    IN p_id_usuario INT
)
BEGIN
    SELECT 
        u.id_usuario, 
        u.codigo_PUCP, 
        u.nombreUsuario, 
        u.contrasena, 
        u.nombre, 
        u.correo, 
        u.estado, 
        u.activo,
        a.clave_maestra
    FROM usuario u
    INNER JOIN administrador a 
        ON u.id_usuario = a.id_administrador
    WHERE u.id_usuario = p_id_usuario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_COMENTARIO_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_COMENTARIO_X_ID`(
    IN p_id_comentario INT
)
BEGIN
    SELECT * FROM comentario
    WHERE id_comentario = p_id_comentario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_CURSO_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_CURSO_X_ID`(
    IN p_id_curso INT
)
BEGIN
    SELECT id_curso, nombre, activo
    FROM curso
    WHERE id_curso = p_id_curso;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_DENUNCIA_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_DENUNCIA_X_ID`(
    IN p_id_reporte INT
)
BEGIN
    SELECT * FROM denuncia
    WHERE id_reporte = p_id_reporte;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_ESPECIALIDAD_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_ESPECIALIDAD_X_ID`(
    IN p_id_especialidad INT
)
BEGIN
    SELECT id_especialidad, nombre, activo
    FROM especialidad
    WHERE id_especialidad = p_id_especialidad;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_FACULTAD_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_FACULTAD_X_ID`(
    IN p_id_facultad INT
)
BEGIN
    SELECT id_facultad, nombre, activo
    FROM facultad
    WHERE id_facultad = p_id_facultad;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_NOTIFICACION_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_NOTIFICACION_X_ID`(
    IN p_id_notificacion INT
)
BEGIN
    SELECT id_notificacion, mensaje, tipo_notificacion, cantidad,
           fecha, id_publicacion, id_usuario, activo
    FROM notificacion
    WHERE id_notificacion = p_id_notificacion;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_PUBLICACION_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_PUBLICACION_X_ID`(
    IN p_id INT
)
BEGIN
    SELECT * FROM publicacion
    WHERE idpublicacion = p_id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure OBTENER_USUARIO_X_ID
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `OBTENER_USUARIO_X_ID`(
    IN p_id_usuario INT
)
BEGIN
    SELECT * FROM usuario
    WHERE id_usuario = p_id_usuario;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PublicEspIntermedio
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `PublicEspIntermedio`(
    IN p_idpublicacion INT,
    IN p_idespecialidad INT
)
BEGIN
    INSERT INTO publicacion_especialidad (publicacion_idpublicacion, especialidad_id_especialidad)
    VALUES (p_idpublicacion, p_idespecialidad);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PublicFacIntermedio
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `PublicFacIntermedio`(
    IN p_idpublicacion INT,
    IN p_idfacultad INT
)
BEGIN
    INSERT INTO publicacion_facultad (publicacion_idpublicacion, facultad_id_facultad)
    VALUES (p_idpublicacion, p_idfacultad);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure PublicursoIntermedio
-- -----------------------------------------------------

DELIMITER $$
USE `Pucp_qatu_db`$$
CREATE DEFINER=`admin`@`%` PROCEDURE `PublicursoIntermedio`(
    IN p_idpublicacion INT,
    IN p_idcurso INT
)
BEGIN
    INSERT INTO publicacion_curso (publicacion_idpublicacion, curso_id_curso)
    VALUES (p_idpublicacion, p_idcurso);
END$$

-- DATOS
INSERT INTO usuario (codigo_PUCP, nombreusuario, contrasena, nombre, correo, estado, activo)
VALUES
(20230001, 'jlopez', 'pass123', 'Juan López', 'jlopez@pucp.edu.pe', 'HABILITADO', 1),
(20230002, 'mmendez', 'secure456', 'María Méndez', 'mmendez@pucp.edu.pe', 'HABILITADO', 1),
(20230003, 'ccastro', 'qwe789', 'Carlos Castro', 'ccastro@pucp.edu.pe', 'DESHABILITADO', 0);

INSERT INTO administrador (id_administrador, clave_Maestra)
VALUES
(1, 'adminKey123');

INSERT INTO publicacion (titulo, descripcion, estado, fechapublicacion, url_imagen, activo, id_usuario)
VALUES
('Introducción a C++', 'Guía básica para empezar en C++', 'VISIBLE', '2025-05-01', 'img/cpp_intro.png', 1, 1),
('Bases de Datos', 'Explicación sobre normalización', 'VISIBLE', '2025-05-03', 'img/cpp_intro.png', 1, 2);

INSERT INTO comentario (contenido, valoracion, fecha, id_publicacion, id_usuario, activo)
VALUES
('Muy útil, gracias!', 5, '2025-05-02', 1, 2, 1),
('Me gustaría ver más ejemplos', 4, '2025-05-04', 2, 1, 1);

INSERT INTO curso (nombre, activo)
VALUES
('Programación I', 1),
('Estructuras de Datos', 1),
('Bases de Datos', 1);

INSERT INTO denuncia (autor, reportante, motivo, fecha_reporte, id_administrador, activo)
VALUES
(1, 2, 'Contenido ofensivo', '2025-05-05', 1, 1);

INSERT INTO especialidad (nombre, activo)
VALUES
('Ingeniería Informática', 1),
('Ingeniería Electrónica', 1);

INSERT INTO facultad (nombre, activo)
VALUES
('Facultad de Ciencias e Ingeniería', 1),
('Facultad de Arquitectura', 1);

INSERT INTO notificacion (mensaje, tipo_notificacion, cantidad, fecha, id_publicacion, id_usuario, activo)
VALUES
('Nueva publicación en tu curso', 'Publicación', 1, '2025-05-06', 1, 2, 1);

INSERT INTO publicacion_curso (publicacion_idpublicacion, curso_id_curso)
VALUES
(1, 1), -- Publicación 1 -> Programación I
(2, 3); -- Publicación 2 -> Bases de Datos

INSERT INTO publicacion_especialidad (publicacion_idpublicacion, especialidad_id_especialidad)
VALUES
(1, 1),
(2, 1);

INSERT INTO publicacion_facultad (publicacion_idpublicacion, facultad_id_facultad)
VALUES
(1, 1),
(2, 1);
DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
