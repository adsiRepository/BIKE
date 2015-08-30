-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2014 a las 17:12:34
-- Versión del servidor: 5.6.20
-- Versión de PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `leboutique`
--
DROP DATABASE IF EXISTS `leboutique`;
CREATE DATABASE IF NOT EXISTS `leboutique` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `leboutique`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `accesos`
--

DROP TABLE IF EXISTS `accesos`;
CREATE TABLE IF NOT EXISTS `accesos` (
  `idaccs` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `iduser` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `hour` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `accesos`
--

INSERT INTO `accesos` (`idaccs`, `iduser`, `hour`, `date`) VALUES
('', '1107057722', '18:31', '28/48/2014'),
('', '1107057722', '18:33', '28/48/2014'),
('', '1107057722', '18:39', '28/48/2014'),
('', '1107057722', '18:43', '28/48/2014'),
('', '1107057722', '18:56', '28/48/2014'),
('', '1107057722', '18:58', '28/48/2014'),
('', '1107057722', '19:3', '28/48/2014'),
('', '1107057722', '19:5', '28/48/2014'),
('', '1107057722', '19:6', '28/48/2014'),
('', '1107057722', '19:9', '28/48/2014'),
('', '1107057722', '19:40', '28/48/2014'),
('', '1107057722', '20:13', '28/48/2014'),
('', '1107057722', '20:17', '28/48/2014'),
('', '1107057722', '20:19', '28/48/2014'),
('', '1107057722', '20:23', '28/48/2014'),
('', '1107057722', '20:24', '28/48/2014'),
('', '1107057722', '20:52', '28/48/2014'),
('', '1107057722', '20:54', '28/48/2014'),
('', '1107057722', '21:26', '28/48/2014'),
('', '1107057722', '21:30', '28/48/2014'),
('', '1107057722', '21:33', '28/48/2014'),
('', '1107057722', '22:8', '28/48/2014'),
('', '1107057722', '23:9', '28/48/2014'),
('', '1107057722', '23:39', '28/48/2014'),
('', '1107057722', '23:42', '28/48/2014'),
('', '000', '1:33', '29/48/2014'),
('', '000', '1:43', '29/48/2014'),
('', '000', '1:44', '29/48/2014'),
('', '1107057722', '3:19', '29/48/2014'),
('', '1107057722', '3:26', '29/48/2014'),
('', '1107057722', '5:7', '29/48/2014'),
('', '1107057722', '10:25', '29/48/2014'),
('', '1107057722', '10:26', '29/48/2014'),
('', '1107057722', '2:32', '30/48/2014'),
('', '1107057722', '3:30', '30/48/2014'),
('', '1107057722', '3:43', '30/48/2014'),
('', '1107057722', '3:54', '30/48/2014'),
('', '1107057722', '3:58', '30/48/2014'),
('', '1107057722', '4:54', '30/48/2014'),
('', '1107057722', '4:58', '30/48/2014');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `idcliente` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `tipdoc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `resid` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idcliente`, `tipdoc`, `nombre`, `resid`, `telefono`, `mail`) VALUES
('3232', 'Pasaporte', 'hugo', 'calle', '77', 'gyuyea'),
('1107057722', 'C.C.', 'miguel', 'sena', '44', '323'),
('fff', 'C.C.', 'fff', 'fff', 'fff', 'fff'),
('333', 'Pasaporte', 'Lucas ', 'Casa', '4444444', 'yy@yy.com'),
('232', 'C.Ext', 'fasf', 'fsadgfas', 'agvsfga', 'fasfasdf'),
('443-5', 'C.Ext', 'Junajo Velasquez', 'Buga', '66678', 'jhun@'),
('4654033', 'C.C.', 'Huracan Valencia', 'Mi Campo Bonito', 'Telégrafo', 'huummmm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `codpro` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `codprov` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descpro` varchar(75) COLLATE utf8_unicode_ci NOT NULL,
  `inv` int(10) NOT NULL,
  `fechasurtido` date DEFAULT NULL,
  `horasurtido` time DEFAULT NULL,
  `costound` double(10,2) NOT NULL,
  `prevenund` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`codpro`, `codprov`, `descpro`, `inv`, `fechasurtido`, `horasurtido`, `costound`, `prevenund`) VALUES
('dads', '889', 'dasd', 132, '2014-11-28', '21:31:33', 232.00, 232.00),
('553', '889', 'Gaseosa pepsi', 87, NULL, NULL, 1300.00, 0.00),
('005', '4563557-1', 'Arroz', 12, '2014-11-28', '20:24:45', 400.00, 0.00),
('443', '002', 'Aceite -- Oliva -- Isabel / 200ml', 300, '2014-11-29', '05:09:31', 3870.00, 5600.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE IF NOT EXISTS `proveedores` (
  `codprov` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idprov` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `idtipo` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(35) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`codprov`, `idprov`, `idtipo`, `nombre`, `direccion`, `telefono`, `mail`) VALUES
('776', '889', 'NIT', 'Leonisa', 'Zona Industrial Yumbo', '7771593', 'leo@nisa.com'),
('002', '4563557-1', 'NIT', 'Unilever', 'Cll 5 56-41', '5574563', 'jun@kako.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `iduser` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `dir` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `telf` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nikuser` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contrasena` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dept` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `rol` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`iduser`, `nombre`, `dir`, `telf`, `mail`, `nikuser`, `contrasena`, `dept`, `rol`) VALUES
('000', 'admain', 'system', '--', 'miguelon_91@misena.edu.co', 'admain', '0946', 'General', 'Soporte Tecnico'),
('1107057722', 'Miguel Eduardo Gonzalez', 'Calle 34 24a02', '3183693535', 'miguelgonz17@hotmail.com', 'miguel', '0000', 'Administracion', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `factura` int(10) DEFAULT NULL,
  `idclient` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `lista` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cant` int(10) DEFAULT NULL,
  `total` double(10,2) NOT NULL,
  `dscto` double(10,2) DEFAULT NULL,
  `user` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`factura`, `idclient`, `fecha`, `hora`, `lista`, `cant`, `total`, `dscto`, `user`) VALUES
(1, 'gur', '2014-11-30', '10:46:56', '[Ljava.lang.Object;@11c9dbf', 7, 2800.00, 0.00, 'miuge');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
