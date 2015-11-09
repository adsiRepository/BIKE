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
  `id_emp` VARCHAR(15) NOT NULL,
  `tipo_id` CHAR(5) NOT NULL,
  `nom_emp` VARCHAR(25) NOT NULL,
  `ape_emp` VARCHAR(25) NOT NULL,
  `tel_emp` VARCHAR(18) NULL,
  `dir_emp` VARCHAR(30) NULL,
  `fecha_ing` DATE NULL,
  `fecha_salida` DATE NULL,
  PRIMARY KEY (`id_emp`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`ordenes_produccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`ordenes_produccion` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`ordenes_produccion` (
  `no_ord` INT NOT NULL,
  `ensamblador` VARCHAR(15) NOT NULL,
  `hora_despacho` DATETIME(0) NOT NULL,
  `hora_entrega` DATETIME(0) NULL,
  PRIMARY KEY (`no_ord`),
  INDEX `fk_ordenes_produccion_ensambladores1_idx` (`ensamblador` ASC),
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
  `cod_fam` CHAR(3) NOT NULL,
  `familia` VARCHAR(25) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `comp_x_par` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cod_fam`),
  UNIQUE INDEX `familia_UNIQUE` (`familia` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`componentes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`componentes` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`componentes` (
  `id_comp` CHAR(3) NOT NULL,
  `componente` VARCHAR(35) NOT NULL,
  `desc_comp` VARCHAR(200) NULL,
  `familia` CHAR(3) NOT NULL,
  UNIQUE INDEX `cod_fam_UNIQUE` (`id_comp` ASC),
  PRIMARY KEY (`id_comp`),
  INDEX `fk_componentes_familia_componente1_idx` (`familia` ASC),
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
  `talla` CHAR(2) NOT NULL,
  PRIMARY KEY (`talla`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`articulos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`articulos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`articulos` (
  `id_articulo` CHAR(3) NOT NULL,
  `articulo` VARCHAR(25) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `imagen` LONGBLOB NULL,
  PRIMARY KEY (`id_articulo`),
  UNIQUE INDEX `id_cat_UNIQUE` (`id_articulo` ASC),
  UNIQUE INDEX `categoria_UNIQUE` (`articulo` ASC))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `storebike`.`repuestos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `storebike`.`repuestos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `storebike`.`repuestos` (
  `cod_rep` VARCHAR(6) NOT NULL,
  `repuesto` VARCHAR(80) NOT NULL,
  `componente` CHAR(3) NOT NULL,
  `articulo` CHAR(3) NULL,
  `talla` CHAR(2) NULL,
  `cant_disp` INT NULL,
  PRIMARY KEY (`cod_rep`),
  UNIQUE INDEX `cod_UNIQUE` (`cod_rep` ASC),
  INDEX `fk_repuestos_componentes1_idx` (`componente` ASC),
  INDEX `fk_repuestos_tallas1_idx` (`talla` ASC),
  INDEX `fk_repuestos_articulos1_idx` (`articulo` ASC),
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
  `orden` INT NOT NULL,
  `repuesto` VARCHAR(6) NOT NULL,
  `cant_desp` INT NOT NULL,
  INDEX `fk_ordenes_produccion_has_articulos_ordenes_produccion1_idx` (`orden` ASC),
  INDEX `fk_detalle_orden_componentes1_idx` (`repuesto` ASC),
  PRIMARY KEY (`orden`, `repuesto`),
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
  `no_ord_prod` INT NOT NULL,
  `articulo` CHAR(3) NOT NULL,
  `talla` CHAR(2) NULL,
  `cantidad` INT NOT NULL,
  INDEX `fk_orden` (`no_ord_prod` ASC),
  INDEX `fk_articulo_producido` (`articulo` ASC),
  PRIMARY KEY (`no_ord_prod`, `articulo`),
  INDEX `fk_produccion_tallas1_idx` (`talla` ASC),
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
  `articulo` CHAR(3) NOT NULL,
  `componente` CHAR(3) NOT NULL,
  PRIMARY KEY (`articulo`, `componente`),
  INDEX `fk_articulo_compuesto` (`articulo` ASC),
  INDEX `fk_componente` (`componente` ASC),
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
  `articulo` CHAR(3) NOT NULL,
  `talla` CHAR(2) NOT NULL,
  PRIMARY KEY (`articulo`, `talla`),
  INDEX `fk_articulos_has_tallas_tallas1_idx` (`talla` ASC),
  INDEX `fk_articulos_has_tallas_articulos1_idx` (`articulo` ASC),
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

DELIMITER $$

USE `storebike`$$
drop function if exists numeroNuevaOrden$$
SHOW WARNINGS$$
USE `storebike`$$
create function numeroNuevaOrden(ensamblador varchar(15), momento varchar(20)) 
returns int
begin
	declare ultima_orden int(11);
	declare nueva_orden int(11);
    select max(no_ord) from ordenes_produccion into ultima_orden;
    if (ultima_orden is null) then
		set nueva_orden = 1;
    else
		set nueva_orden = ultima_orden + 1;
    end if;
    insert into ordenes_produccion (no_ord, ensamblador, hora_despacho) values (nueva_orden, ensamblador, momento);
    return nueva_orden;
end;
$$

SHOW WARNINGS$$

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`ordenes_produccion_BEFORE_DELETE` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`ordenes_produccion_BEFORE_DELETE` BEFORE DELETE ON `ordenes_produccion` FOR EACH ROW
BEGIN
 declare r_cod_rep varchar(6);
 declare r_cant_desp int(11);
 declare fin boolean;
 
 /* http://www.hermosaprogramacion.com/2014/06/mysql-cursores/ */
 declare reps cursor for 
 select d.repuesto, d.cant_desp from detalle_despacho d where d.orden = old.no_ord;
 declare continue handler for not found set fin = true;
 open reps;
 loop_ : loop
  fetch reps into r_cod_rep, r_cant_desp;
  if fin then
   leave loop_;
  end if;
  update repuestos set cant_disp = cant_disp + r_cant_desp where cod_rep = r_cod_rep;
 end loop loop_;
 close reps;
 
END
$$

SHOW WARNINGS$$

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

USE `storebike`$$
DROP TRIGGER IF EXISTS `storebike`.`detalle_despacho_AFTER_DELETE` $$
SHOW WARNINGS$$
USE `storebike`$$
CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_DELETE` AFTER DELETE ON `detalle_despacho` FOR EACH ROW
BEGIN
 update repuestos 
		set cant_disp = (cant_disp + old.cant_desp) 
		where cod_rep = old.repuesto;
END
$$

SHOW WARNINGS$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
