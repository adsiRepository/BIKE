-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema storebike
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `storebike` ;

-- -----------------------------------------------------
-- Schema storebike
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `storebike` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `storebike` ;

-- -----------------------------------------------------
-- Table `storebike`.`ensambladores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`ensambladores` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`ensambladores` (
  `id_emp` VARCHAR(15) NOT NULL COMMENT '',
  `nom_emp` VARCHAR(25) NOT NULL COMMENT '',
  `ape_emp` VARCHAR(25) NOT NULL COMMENT '',
  `tel_emp` VARCHAR(18) NULL COMMENT '',
  `dir_emp` VARCHAR(30) NULL COMMENT '',
  `fecha_ing` DATE NULL COMMENT '',
  `fecha_salida` DATE NULL COMMENT '',
  PRIMARY KEY (`id_emp`)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`ordenes_produccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`ordenes_produccion` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`ordenes_produccion` (
  `no_ord` INT NOT NULL COMMENT '',
  `ensamblador` VARCHAR(15) NOT NULL COMMENT '',
  `hora_despacho` DATETIME(0) NOT NULL COMMENT '',
  `hora_entrega` DATETIME(0) NULL COMMENT '',
  PRIMARY KEY (`no_ord`)  COMMENT '',
  INDEX `fk_ordenes_produccion_ensambladores1_idx` (`ensamblador` ASC)  COMMENT '',
  CONSTRAINT `fk_ensamblador_produzca`
    FOREIGN KEY (`ensamblador`)
    REFERENCES `storebike`.`ensambladores` (`id_emp`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`familia_componente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`familia_componente` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`familia_componente` (
  `cod_fam` CHAR(3) NOT NULL COMMENT '',
  `familia` VARCHAR(25) NOT NULL COMMENT '',
  `desc` VARCHAR(200) NULL COMMENT '',
  `comp_x_par` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '',
  `imprescindible` TINYINT(1) NOT NULL COMMENT '',
  PRIMARY KEY (`cod_fam`)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`componentes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`componentes` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`componentes` (
  `id_comp` CHAR(3) NOT NULL COMMENT '',
  `componente` VARCHAR(35) NOT NULL COMMENT '',
  `desc_comp` VARCHAR(200) NULL COMMENT '',
  `familia` CHAR(3) NOT NULL COMMENT '',
  UNIQUE INDEX `cod_fam_UNIQUE` (`id_comp` ASC)  COMMENT '',
  PRIMARY KEY (`id_comp`)  COMMENT '',
  INDEX `fk_componentes_familia_componente1_idx` (`familia` ASC)  COMMENT '',
  CONSTRAINT `fk_componentes_familia_componente1`
    FOREIGN KEY (`familia`)
    REFERENCES `storebike`.`familia_componente` (`cod_fam`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`tallas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`tallas` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`tallas` (
  `talla` CHAR(2) NOT NULL COMMENT '',
  PRIMARY KEY (`talla`)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`articulos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`articulos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`articulos` (
  `id_articulo` CHAR(3) NOT NULL COMMENT '',
  `articulo` VARCHAR(25) NOT NULL COMMENT '',
  `descripcion` VARCHAR(200) NULL COMMENT '',
  `imagen` LONGBLOB NULL COMMENT '',
  PRIMARY KEY (`id_articulo`)  COMMENT '',
  UNIQUE INDEX `id_cat_UNIQUE` (`id_articulo` ASC)  COMMENT '',
  UNIQUE INDEX `categoria_UNIQUE` (`articulo` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`repuestos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`repuestos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`repuestos` (
  `cod_rep` VARCHAR(6) NOT NULL COMMENT '',
  `repuesto` VARCHAR(80) NOT NULL COMMENT '',
  `componente` CHAR(3) NOT NULL COMMENT '',
  `articulo` CHAR(3) NULL COMMENT '',
  `talla` CHAR(2) NULL COMMENT '',
  `cant_disp` INT NULL COMMENT '',
  PRIMARY KEY (`cod_rep`)  COMMENT '',
  UNIQUE INDEX `cod_UNIQUE` (`cod_rep` ASC)  COMMENT '',
  INDEX `fk_repuestos_componentes1_idx` (`componente` ASC)  COMMENT '',
  INDEX `fk_repuestos_tallas1_idx` (`talla` ASC)  COMMENT '',
  INDEX `fk_repuestos_articulos1_idx` (`articulo` ASC)  COMMENT '',
  CONSTRAINT `fk_repuestos_componentes1`
    FOREIGN KEY (`componente`)
    REFERENCES `storebike`.`componentes` (`id_comp`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_repuestos_tallas1`
    FOREIGN KEY (`talla`)
    REFERENCES `storebike`.`tallas` (`talla`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_repuestos_articulos1`
    FOREIGN KEY (`articulo`)
    REFERENCES `storebike`.`articulos` (`id_articulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`detalle_despacho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`detalle_despacho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`detalle_despacho` (
  `orden` INT NOT NULL COMMENT '',
  `repuesto` VARCHAR(6) NOT NULL COMMENT '',
  `cant_desp` INT NOT NULL COMMENT '',
  INDEX `fk_ordenes_produccion_has_articulos_ordenes_produccion1_idx` (`orden` ASC)  COMMENT '',
  INDEX `fk_detalle_orden_componentes1_idx` (`repuesto` ASC)  COMMENT '',
  PRIMARY KEY (`orden`, `repuesto`)  COMMENT '',
  CONSTRAINT `fk_orden_prod_en_detalle`
    FOREIGN KEY (`orden`)
    REFERENCES `storebike`.`ordenes_produccion` (`no_ord`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_repuesto_en_orden_prod`
    FOREIGN KEY (`repuesto`)
    REFERENCES `storebike`.`repuestos` (`cod_rep`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`produccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`produccion` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`produccion` (
  `no_ord_prod` INT NOT NULL COMMENT '',
  `articulo` CHAR(3) NOT NULL COMMENT '',
  `talla` CHAR(2) NULL COMMENT '',
  `cantidad` INT NOT NULL COMMENT '',
  INDEX `fk_orden` (`no_ord_prod` ASC)  COMMENT '',
  INDEX `fk_articulo_producido` (`articulo` ASC)  COMMENT '',
  PRIMARY KEY (`no_ord_prod`, `articulo`)  COMMENT '',
  INDEX `fk_produccion_tallas1_idx` (`talla` ASC)  COMMENT '',
  CONSTRAINT `fk_orden_ensamble_articulo`
    FOREIGN KEY (`no_ord_prod`)
    REFERENCES `storebike`.`ordenes_produccion` (`no_ord`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_articulo`
    FOREIGN KEY (`articulo`)
    REFERENCES `storebike`.`articulos` (`id_articulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_produccion_tallas1`
    FOREIGN KEY (`talla`)
    REFERENCES `storebike`.`tallas` (`talla`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`componente_articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`componente_articulo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`componente_articulo` (
  `articulo` CHAR(3) NOT NULL COMMENT '',
  `componente` CHAR(3) NOT NULL COMMENT '',
  PRIMARY KEY (`articulo`, `componente`)  COMMENT '',
  INDEX `fk_articulo_compuesto` (`articulo` ASC)  COMMENT '',
  INDEX `fk_componente` (`componente` ASC)  COMMENT '',
  CONSTRAINT `fk_articulo_compuesto`
    FOREIGN KEY (`articulo`)
    REFERENCES `storebike`.`articulos` (`id_articulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_componente`
    FOREIGN KEY (`componente`)
    REFERENCES `storebike`.`componentes` (`id_comp`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`talla_articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`talla_articulo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`talla_articulo` (
  `articulo` CHAR(3) NOT NULL COMMENT '',
  `talla` CHAR(2) NOT NULL COMMENT '',
  PRIMARY KEY (`articulo`, `talla`)  COMMENT '',
  INDEX `fk_articulos_has_tallas_tallas1_idx` (`talla` ASC)  COMMENT '',
  INDEX `fk_articulos_has_tallas_articulos1_idx` (`articulo` ASC)  COMMENT '',
  CONSTRAINT `fk_articulos_has_tallas_articulos1`
    FOREIGN KEY (`articulo`)
    REFERENCES `storebike`.`articulos` (`id_articulo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_articulos_has_tallas_tallas1`
    FOREIGN KEY (`talla`)
    REFERENCES `storebike`.`tallas` (`talla`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
USE `storebike`;

DELIMITER $$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`detalle_despacho_AFTER_INSERT` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_INSERT` AFTER INSERT ON `detalle_despacho` FOR EACH ROW
BEGIN
	update repuestos set cant_disp = (cant_disp - new.cant_desp) where cod_rep = new.repuesto;
END
$$

SHOW WARNINGS$$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`detalle_despacho_AFTER_UPDATE` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_UPDATE` AFTER UPDATE ON `detalle_despacho` FOR EACH ROW
BEGIN
	declare cant_actual int(11);
    declare acumulado int(11);
	if (old.repuesto <> new.repuesto) then
		update repuestos 
		set cant_disp = (cant_disp + old.cant_desp) 
		where cod_rep = old.repuesto;
        
        if (old.cant_desp <> new.cant_desp)then
			update repuestos 
			set cant_disp = (cant_disp - new.cant_desp) 
			where cod_rep = new.repuesto;
		else
			update repuestos 
			set cant_disp = (cant_disp - old.cant_desp) 
			where cod_rep = new.repuesto;
        end if;
        
	elseif (old.cant_desp <> new.cant_desp) then
		select cant_disp from repuestos where cod_rep = old.repuesto into cant_actual;
        set acumulado = cant_actual + old.cant_desp;
		update repuestos 
		set cant_disp = (acumulado - new.cant_desp) 
		where cod_rep = old.repuesto;
	end if;
END
$$

SHOW WARNINGS$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
