CREATE DATABASE empresa;
USE empresa;

 CREATE TABLE `administrador` (
  `id` int(10) primary key NOT NULL,
  `senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `administrador` (`id`, `senha`) VALUES
(1, 'teste123');

CREATE TABLE `clientes` (
  `id` int(10) primary key NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `endereco` varchar(100) NOT NULL,
    `tel` varchar(100) NOT NULL,
      `divida` varchar(100) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `clientes` (`id`, `nome`, `endereco`, `tel`, `divida`) VALUES
(14, 'Ali', 'Rua tijuca', '123456345', '', '123,00');