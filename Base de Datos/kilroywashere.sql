-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2022 a las 19:37:19
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dwes_manana_kilroywashere`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descubiertos`
--

CREATE TABLE `descubiertos` (
  `nombre_tesoro` varchar(50) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `mensaje` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `piezas`
--

CREATE TABLE `piezas` (
  `nombre` varchar(50) NOT NULL,
  `piezas` int(5) NOT NULL,
  `nombre_tesoro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `piezas`
--

INSERT INTO `piezas` (`nombre`, `piezas`, `nombre_tesoro`) VALUES
('Seta Alucinógena', 3, 'Cazuela de Setas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE `pistas` (
  `nombre` varchar(50) NOT NULL,
  `pista` text NOT NULL,
  `nombre_tesoro` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pistas`
--

INSERT INTO `pistas` (`nombre`, `pista`, `nombre_tesoro`) VALUES
('Pista Star Wars', 'El tesoro se encuentra en la plaza donde se rodó una escena de Star Wars', 'Espada Laser');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tesoros`
--

CREATE TABLE `tesoros` (
  `nombre` varchar(50) NOT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `descripcion` text NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tesoros`
--

INSERT INTO `tesoros` (`nombre`, `latitud`, `longitud`, `descripcion`, `tipo`) VALUES
('Caliz Catedral', 37.386009216308594, -5.993149757385254, 'Emblema de la ciudad de Sevilla', 'reliquia'),
('Cazuela de Setas', 37.39348727403332, -5.99175545046858, 'Comida preparada con las setas que recogiste (se recomienda no consumir)', 'pieza'),
('Espada Laser', 37.377293725745176, -5.986935917656098, 'Arma para un verdadero caballero jedi', 'pista');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `nombre` varchar(50) NOT NULL,
  `contraseña` varchar(500) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `descubiertos`
--
ALTER TABLE `descubiertos`
  ADD PRIMARY KEY (`nombre_tesoro`,`nombre_usuario`),
  ADD KEY `nombre_usuario` (`nombre_usuario`);

--
-- Indices de la tabla `piezas`
--
ALTER TABLE `piezas`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `foreign_key_pieza` (`nombre_tesoro`);

--
-- Indices de la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `foreign_key` (`nombre_tesoro`);

--
-- Indices de la tabla `tesoros`
--
ALTER TABLE `tesoros`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`nombre`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `descubiertos`
--
ALTER TABLE `descubiertos`
  ADD CONSTRAINT `descubiertos_ibfk_1` FOREIGN KEY (`nombre_usuario`) REFERENCES `usuarios` (`nombre`),
  ADD CONSTRAINT `descubiertos_ibfk_2` FOREIGN KEY (`nombre_tesoro`) REFERENCES `tesoros` (`nombre`);

--
-- Filtros para la tabla `piezas`
--
ALTER TABLE `piezas`
  ADD CONSTRAINT `foreign_key_pieza` FOREIGN KEY (`nombre_tesoro`) REFERENCES `tesoros` (`nombre`);

--
-- Filtros para la tabla `pistas`
--
ALTER TABLE `pistas`
  ADD CONSTRAINT `foreign_key` FOREIGN KEY (`nombre_tesoro`) REFERENCES `tesoros` (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
