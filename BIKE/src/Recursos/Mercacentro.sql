SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mercacentro` DEFAULT CHARACTER SET latin1 ;
USE `mercacentro` ;

-- -----------------------------------------------------
-- Table `mercacentro`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`cliente` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`cliente` (
  `ideCLIENTE` DOUBLE NOT NULL ,
  `nomCLIENTE` VARCHAR(30) NULL DEFAULT NULL ,
  `dirCLIENTE` VARCHAR(30) NULL DEFAULT NULL ,
  `telCLIENTE` VARCHAR(30) NULL DEFAULT NULL ,
  `emaCLIENTE` VARCHAR(30) NULL DEFAULT NULL ,
  PRIMARY KEY (`ideCLIENTE`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`usuario` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`usuario` (
  `idUSUARIO` DOUBLE NOT NULL ,
  `nomUSUARIO` VARCHAR(30) NOT NULL ,
  `dirUSUARIO` VARCHAR(30) NOT NULL ,
  `telUSUARIO` VARCHAR(30) NOT NULL ,
  `emaUSUARIO` VARCHAR(30) NOT NULL ,
  `claUSUARIO` VARCHAR(40) NOT NULL ,
  `contUSUARIO` VARCHAR(40) NOT NULL ,
  `rolUSUARIO` VARCHAR(15) NOT NULL ,
  `depUSUARIO` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`idUSUARIO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`factura` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`factura` (
  `idFACTURA` INT NOT NULL AUTO_INCREMENT ,
  `CLIENTE_ideCLIENTE` DOUBLE NOT NULL ,
  `VENDEDOR_idVENDEDOR` DOUBLE NOT NULL ,
  `totalFACTURA` DOUBLE NULL DEFAULT NULL ,
  `ivaFACTURA` DOUBLE NULL DEFAULT NULL ,
  `netoFACTURA` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`idFACTURA`, `CLIENTE_ideCLIENTE`, `VENDEDOR_idVENDEDOR`) ,
  INDEX `fk_FACTURA_CLIENTE_idx` (`CLIENTE_ideCLIENTE` ASC) ,
  INDEX `fk_FACTURA_VENDEDOR1_idx` (`VENDEDOR_idVENDEDOR` ASC) ,
  CONSTRAINT `fk_FACTURA_CLIENTE`
    FOREIGN KEY (`CLIENTE_ideCLIENTE` )
    REFERENCES `mercacentro`.`cliente` (`ideCLIENTE` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_FACTURA_VENDEDOR1`
    FOREIGN KEY (`VENDEDOR_idVENDEDOR` )
    REFERENCES `mercacentro`.`usuario` (`idUSUARIO` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`producto` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`producto` (
  `idProducto` DOUBLE NOT NULL AUTO_INCREMENT ,
  `desProducto` VARCHAR(45) NULL DEFAULT NULL ,
  `valProducto` DOUBLE NULL DEFAULT NULL ,
  `exsProducto` INT NULL ,
  PRIMARY KEY (`idProducto`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`proveedores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`proveedores` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`proveedores` (
  `id_proveedor` DOUBLE NOT NULL ,
  `nom_proveedor` VARCHAR(45) NULL DEFAULT NULL ,
  `dir_proveedor` VARCHAR(45) NULL DEFAULT NULL ,
  `tel_proveedor` VARCHAR(45) NULL DEFAULT NULL ,
  `ema_proveedor` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_proveedor`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`regingreso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`regingreso` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`regingreso` (
  `idRegIngreso` DOUBLE NOT NULL AUTO_INCREMENT ,
  `Proveedores_id_proveedor` DOUBLE NOT NULL ,
  `Producto_idProducto` DOUBLE NOT NULL ,
  `FecRegIngre` DATE NULL DEFAULT NULL ,
  `HorIngreso` TIME NULL DEFAULT NULL ,
  `CantRegIngreso` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idRegIngreso`, `Proveedores_id_proveedor`, `Producto_idProducto`) ,
  INDEX `fk_RegIngreso_Proveedores1_idx` (`Proveedores_id_proveedor` ASC) ,
  INDEX `fk_RegIngreso_Producto1_idx` (`Producto_idProducto` ASC) ,
  CONSTRAINT `fk_RegIngreso_Producto1`
    FOREIGN KEY (`Producto_idProducto` )
    REFERENCES `mercacentro`.`producto` (`idProducto` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_RegIngreso_Proveedores1`
    FOREIGN KEY (`Proveedores_id_proveedor` )
    REFERENCES `mercacentro`.`proveedores` (`id_proveedor` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`venta` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`venta` (
  `idVENTA` INT NOT NULL ,
  `FACTURA_idFACTURA` INT NOT NULL ,
  `Producto_idProducto` DOUBLE NOT NULL ,
  `canVENTA` DOUBLE NULL DEFAULT NULL ,
  `subVENTA` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`idVENTA`, `FACTURA_idFACTURA`, `Producto_idProducto`) ,
  INDEX `fk_VENTA_FACTURA1_idx` (`FACTURA_idFACTURA` ASC) ,
  INDEX `fk_VENTA_Producto1_idx` (`Producto_idProducto` ASC) ,
  CONSTRAINT `fk_VENTA_FACTURA1`
    FOREIGN KEY (`FACTURA_idFACTURA` )
    REFERENCES `mercacentro`.`factura` (`idFACTURA` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_VENTA_Producto1`
    FOREIGN KEY (`Producto_idProducto` )
    REFERENCES `mercacentro`.`producto` (`idProducto` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mercacentro`.`Accesos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mercacentro`.`Accesos` ;

CREATE  TABLE IF NOT EXISTS `mercacentro`.`Accesos` (
  `idAccesos` DOUBLE NOT NULL AUTO_INCREMENT ,
  `usuario_idUSUARIO` DOUBLE NOT NULL ,
  `feAccessos` VARCHAR(45) NULL ,
  `hoAccesos` VARCHAR(45) NULL ,
  PRIMARY KEY (`idAccesos`, `usuario_idUSUARIO`) ,
  INDEX `fk_Accesos_usuario1_idx` (`usuario_idUSUARIO` ASC) ,
  CONSTRAINT `fk_Accesos_usuario1`
    FOREIGN KEY (`usuario_idUSUARIO` )
    REFERENCES `mercacentro`.`usuario` (`idUSUARIO` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `mercacentro` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
