-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 11, 2015 at 01:07 AM
-- Server version: 5.1.37
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `erm`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
  `IDCATEGORIA` bigint(20) NOT NULL,
  `CATEGORIA` varchar(255) DEFAULT NULL,
  `TECNOLOGIA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDCATEGORIA`),
  KEY `FK_CATEGORIAS_TECNOLOGIA` (`TECNOLOGIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`IDCATEGORIA`, `CATEGORIA`, `TECNOLOGIA`) VALUES
(552, 'FASTENER', 551),
(553, 'FITTINGS', 551),
(659, 'MACHINING', 652),
(661, 'COATING', 660),
(662, 'SEALANT', 660),
(663, 'METALLIC - BAR', 655),
(664, 'METALLIC - PLATE', 655),
(665, 'METALLIC - SHEET', 655),
(666, 'METALLIC - TUBE', 655),
(704, 'COMPOSITE - PREPREG', 652),
(705, 'BONDING ASSEMBLE', 658),
(707, 'FINAL ASSEMBLE', 706),
(712, 'MECHANICAL', 658),
(713, 'STRUCTURAL', 658);

-- --------------------------------------------------------

--
-- Table structure for table `cidades`
--

CREATE TABLE IF NOT EXISTS `cidades` (
  `IDCIDADE` bigint(20) NOT NULL,
  `CIDADE` varchar(255) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDCIDADE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cidades`
--


-- --------------------------------------------------------

--
-- Table structure for table `fornecedores`
--

CREATE TABLE IF NOT EXISTS `fornecedores` (
  `IDFORNECEDOR` bigint(20) NOT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `CONTATO` varchar(255) DEFAULT NULL,
  `DATA_CADASTRO` date DEFAULT NULL,
  `DATA_REAVALIACAO` date DEFAULT NULL,
  `DATA_STATUS` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ENDERECO` varchar(255) DEFAULT NULL,
  `FORNECEDOR` varchar(255) DEFAULT NULL,
  `IDE` double DEFAULT NULL,
  `IQF` double DEFAULT NULL,
  `IQPF` double DEFAULT NULL,
  `PRODUTO` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `TELEFONE` varchar(255) DEFAULT NULL,
  `CRIADOR` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDFORNECEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fornecedores`
--

INSERT INTO `fornecedores` (`IDFORNECEDOR`, `CNPJ`, `CONTATO`, `DATA_CADASTRO`, `DATA_REAVALIACAO`, `DATA_STATUS`, `EMAIL`, `ENDERECO`, `FORNECEDOR`, `IDE`, `IQF`, `IQPF`, `PRODUTO`, `STATUS`, `TELEFONE`, `CRIADOR`) VALUES
(668, '12.345.678/9152-31', 'TESTE', '2015-10-21', '2015-10-21', '2015-10-21', 'ASDAA@CCC.CIM', 'TESTE', 'PPG AEROSPACE', 0, 0, 0, 'CONSUMABLES', 'Pendente', '12 555555555', 651),
(669, '', 'Alex Siqueira', '2015-10-21', '2016-10-21', '2015-10-21', 'siqueira.alex@utec.net.br', 'Av. Jos� de Campos, 96, Jardim Morumbi, S�o Jos� dos Campos, S�o Paulo, Brasil', 'UTEC Ind. Com. Serv. E Usinagem de pe�as Aeroespacial Ltda Me', 0, 0, 0, 'Usinagem, medi��o tridimencional CNC. Executa pequenas montagens.\r\n', 'Aprovado', '12 3931-3825', 651),
(671, '11.111.111/1111-11', 'JENNIFER WATSON', '2015-10-21', '2015-10-21', '2015-10-21', 'jwatson@bralco.com', '15090 NORTHAM STREET - LA MIRADA - CALIFORNIA 90638 - USA', 'BRALCO METALS', 0, 0, 0, 'Armazenagem, processamento e distribui��o de mat�rias primas met�lica( ligas de aluminio, ligas de a�o, ligas de a�o inox e, bobinas, chapas, placas barras e tubos.\r\n', 'Pendente', '714 736-4800', 651),
(708, '02.447.516/0002-95', 'NOVAER', '2015-10-27', '2015-10-27', '2015-10-27', 'amanda@novaer.ind.br', 'ROD. PRESIDENTE DUTRA, 154, PREDIO 4, ALA B', 'NOVAER CRAFT EMPREENDIMENTOS AERONAUTICOS S.A.', 0, 0, 0, 'MONTAGENS INTERNAS', 'Aprovado', '1239492000', 999999999);

-- --------------------------------------------------------

--
-- Table structure for table `fornecedores_aprovadores`
--

CREATE TABLE IF NOT EXISTS `fornecedores_aprovadores` (
  `FORNECEDOR` bigint(20) NOT NULL,
  `USUARIO` bigint(20) NOT NULL,
  PRIMARY KEY (`FORNECEDOR`,`USUARIO`),
  KEY `FK_FORNECEDORES_APROVADORES_USUARIO` (`USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fornecedores_aprovadores`
--

INSERT INTO `fornecedores_aprovadores` (`FORNECEDOR`, `USUARIO`) VALUES
(669, 651),
(708, 999999999);

-- --------------------------------------------------------

--
-- Table structure for table `materiais`
--

CREATE TABLE IF NOT EXISTS `materiais` (
  `IDMATERIAL` bigint(20) NOT NULL,
  `AMBIENTE` tinyint(1) DEFAULT '0',
  `CALIBRACAO` bigint(20) DEFAULT NULL,
  `CICLOS` bigint(20) DEFAULT NULL,
  `CLASSE_COMPRA` varchar(255) DEFAULT NULL,
  `DATA_STATUS` date DEFAULT NULL,
  `ESPECIFICACAO` varchar(255) DEFAULT NULL,
  `INDICACAO_A` bigint(20) DEFAULT NULL,
  `INDICACAO_DE` bigint(20) DEFAULT NULL,
  `LEAD` double DEFAULT NULL,
  `LOTE` bigint(20) DEFAULT NULL,
  `LOTE_M` bigint(20) DEFAULT NULL,
  `MATERIAL` varchar(255) DEFAULT NULL,
  `MONTAGEM` double DEFAULT NULL,
  `NCODE` bigint(20) DEFAULT NULL,
  `PESO` double DEFAULT NULL,
  `PN` varchar(255) DEFAULT NULL,
  `PN_FORNECEDOR` varchar(255) DEFAULT NULL,
  `PRATELEIRA` tinyint(1) DEFAULT '0',
  `STATUS` varchar(255) DEFAULT NULL,
  `TIPO` varchar(255) DEFAULT NULL,
  `VIDA` bigint(20) DEFAULT NULL,
  `CATEGORIA` bigint(20) DEFAULT NULL,
  `CRIADOR` bigint(20) DEFAULT NULL,
  `FORNECEDOR` bigint(20) DEFAULT NULL,
  `UN_FORNECEDOR` bigint(20) DEFAULT NULL,
  `UN_L` bigint(20) DEFAULT NULL,
  `UN_L_FORNECEDOR` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDMATERIAL`),
  KEY `FK_MATERIAIS_UN_FORNECEDOR` (`UN_FORNECEDOR`),
  KEY `FK_MATERIAIS_CATEGORIA` (`CATEGORIA`),
  KEY `FK_MATERIAIS_UN_L_FORNECEDOR` (`UN_L_FORNECEDOR`),
  KEY `FK_MATERIAIS_UN_L` (`UN_L`),
  KEY `FK_MATERIAIS_FORNECEDOR` (`FORNECEDOR`),
  KEY `FK_MATERIAIS_CRIADOR` (`CRIADOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `materiais`
--

INSERT INTO `materiais` (`IDMATERIAL`, `AMBIENTE`, `CALIBRACAO`, `CICLOS`, `CLASSE_COMPRA`, `DATA_STATUS`, `ESPECIFICACAO`, `INDICACAO_A`, `INDICACAO_DE`, `LEAD`, `LOTE`, `LOTE_M`, `MATERIAL`, `MONTAGEM`, `NCODE`, `PESO`, `PN`, `PN_FORNECEDOR`, `PRATELEIRA`, `STATUS`, `TIPO`, `VIDA`, `CATEGORIA`, `CRIADOR`, `FORNECEDOR`, `UN_FORNECEDOR`, `UN_L`, `UN_L_FORNECEDOR`) VALUES
(709, NULL, NULL, NULL, NULL, '2015-10-27', NULL, 0, 0, NULL, NULL, NULL, 'WING, INSTALLATION', NULL, 1, NULL, '310-57-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(710, NULL, NULL, NULL, NULL, '2015-10-27', NULL, 0, 0, NULL, NULL, NULL, 'WING, EQUIPPED', NULL, 2, NULL, '310-57-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(711, NULL, NULL, NULL, NULL, '2015-10-27', NULL, 0, 0, NULL, NULL, NULL, 'WING TIP, FAIRING INSTALLATION', NULL, 3, NULL, '310-57-00-0030-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(714, NULL, NULL, NULL, NULL, '2015-10-27', NULL, 0, 0, NULL, NULL, NULL, 'LEFT WING TIP, FAIRING STRUCTURAL', NULL, 4, NULL, '310-57-30-0031-501', NULL, NULL, 'Pendente', 'MAKE', NULL, 713, 999999999, 708, NULL, NULL, NULL),
(715, NULL, NULL, NULL, NULL, '2015-10-27', NULL, 0, 0, NULL, NULL, NULL, 'LEFT WING TIP, FAIRING', NULL, 5, NULL, '310-57-30-0037-301', NULL, NULL, 'Pendente', 'MAKE', NULL, 704, 999999999, 708, NULL, NULL, NULL),
(716, NULL, NULL, NULL, NULL, '2015-10-27', NULL, 0, 0, NULL, NULL, NULL, 'WING, STRUCTURAL BONDING', NULL, 6, NULL, '310-57-10-0003-501', NULL, NULL, 'Pendente', 'MAKE', NULL, 705, 999999999, 708, NULL, NULL, NULL),
(751, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'PAINT, SCHEME', NULL, 7, NULL, '310-11-10-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(752, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'MARKS AND PLACARDS', NULL, 8, NULL, '310-11-20-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(753, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'AIR CONDITIONING, INSTALLATION', NULL, 9, NULL, '310-21-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(754, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'PRESSURE SYSTEM, INSTALLATION', NULL, 10, NULL, '310-21-30-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(755, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ELECTRICAL SYSTEM, INSTALLATION', NULL, 11, NULL, '310-24-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(756, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'GENERATION ELECTRICAL SYSTEM, INSTALLATION', NULL, 12, NULL, '310-24-10-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(757, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'DISTRIBUTION ELECTRICAL SYSTEM, INSTALLATION', NULL, 13, NULL, '310-24-30-0003-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(758, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'INDICATION ELECTRICAL SYSTEM, INSTALLATION', NULL, 14, NULL, '310-24-20-0004-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(759, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ELECTRICAL SYSTEM BRACKED , INSTALLATION', NULL, 15, NULL, '310-24-10-0005-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 705, 999999999, 708, NULL, NULL, NULL),
(760, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'EMERGENCE BRAKE COMMAND, INSTALLATION', NULL, 16, NULL, '310-25-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(761, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'INTERIOR, INSTALLATION', NULL, 17, NULL, '310-25-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 713, 999999999, 708, NULL, NULL, NULL),
(762, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'SEAT BELT, INSTALLATION', NULL, 18, NULL, '310-25-00-0003-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 713, 999999999, 708, NULL, NULL, NULL),
(763, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'EJECTION SEAT, INSTALLATION', NULL, 19, NULL, '310-25-00-0004-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 713, 999999999, 708, NULL, NULL, NULL),
(764, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'PANEL INSTRUMENTS, INSTALLATION', NULL, 20, NULL, '310-25-00-0005-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(765, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'FLOOR PANEL, INSTALLATION', NULL, 21, NULL, '310-25-00-0006-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 705, 999999999, 708, NULL, NULL, NULL),
(766, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'FIRE PROTECTION, INSTALLATION', NULL, 22, NULL, '310-26-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(767, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'WING FUEL SYSTEM, INSTALLATION', NULL, 23, NULL, '310-28-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(768, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'SUB-ALAR TANK, INSTALLATION', NULL, 24, NULL, '310-28-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(769, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'HIDRAULIC SYSTEM, INSTALLATION', NULL, 25, NULL, '310-29-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(770, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'LANDING GEAR, INSTALLATION', NULL, 26, NULL, '310-32-10-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(771, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'MAIN LANDING GEAR, INSTALLATION', NULL, 27, NULL, '310-32-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(772, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'NOSE LANDING GEAR EQUIPPED, INSTALLATION', NULL, 28, NULL, '310-32-00-0003-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(773, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'RIGHT MAIN LANDING GEAR EQUIPPED, INSTALLATION', NULL, 29, NULL, '310-32-00-0004-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 713, 999999999, 708, NULL, NULL, NULL),
(774, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'LEFT MAIN LANDING GEAR EQUIPPED, INSTALLATION', NULL, 30, NULL, '310-32-00-0004-802', NULL, NULL, 'Pendente', 'MAKE', NULL, 713, 999999999, 708, NULL, NULL, NULL),
(775, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'FREE FALL COMMAND, INSTALLATION', NULL, 31, NULL, '310-32-00-0005-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(776, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'STEERING COMMAND, INSTALLATION', NULL, 32, NULL, '310-32-00-0006-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(777, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'AVIONIC SYSTEM, INSTALLATION', NULL, 33, NULL, '310-34-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(778, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'OXIGEN SYSTEM, INSTALLATION', NULL, 34, NULL, '310-35-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(779, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ANEMOMETRIC SYSTEM, INSTALLATION', NULL, 35, NULL, '310-36-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(780, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'BLEED SYSTEM, INSTALLATION', NULL, 36, NULL, '310-36-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(781, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'DOOR, INSTALLATION', NULL, 37, NULL, '310-52-40-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(782, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'BAGGAGE DOOR EQUIPPED, INSTALLATION', NULL, 38, NULL, '310-52-40-0002-501', NULL, NULL, 'Pendente', 'MAKE', NULL, 705, 999999999, 708, NULL, NULL, NULL),
(783, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'PROPELLER, INSTALLATION', NULL, 39, NULL, '310-61-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(784, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'POWER PLAINT, INSTALLATION', NULL, 40, NULL, '310-71-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(785, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'LUBRIFICATION SYSTEM, INSTALLATION', NULL, 41, NULL, '310-71-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(786, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ENGINE SENSOR, INSTALLATION', NULL, 42, NULL, '310-71-00-0003-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(787, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ENGINE, INSTALLATION', NULL, 43, NULL, '310-72-20-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(788, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'IGNITION SYSTEM, INSTALLATION', NULL, 44, NULL, '310-74-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(789, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'BATTERY, INSTALLATION', NULL, 45, NULL, '310-74-00-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(790, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ENGINE COMMAND, INSTALLATION', NULL, 46, NULL, '310-76-10-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(791, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'EXHAUST, INSTALLATION', NULL, 47, NULL, '310-78-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(792, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'ELEVATOR, INSTALLATION', NULL, 48, NULL, '310-55-00-0001-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(793, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'TRIM TAB ELEVATOR, INSTALLATION', NULL, 49, NULL, '310-55-20-0002-801', NULL, NULL, 'Pendente', 'MAKE', NULL, 707, 999999999, 708, NULL, NULL, NULL),
(794, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'LEFT TRIM TAB ELEVATOR, STRUCTURAL', NULL, 50, NULL, '310-55-20-0003-501', NULL, NULL, 'Pendente', 'MAKE', NULL, 705, 999999999, 708, NULL, NULL, NULL),
(795, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'LEFT TRIM TAB RING, ELEVATOR', NULL, 51, NULL, '310-55-20-0034-001', NULL, NULL, 'Pendente', 'MAKE', NULL, 659, 999999999, 708, NULL, NULL, NULL),
(796, NULL, NULL, NULL, NULL, '2015-10-28', NULL, 0, 0, NULL, NULL, NULL, 'LEFT TRIM TAB RING ROOT, ELEVATOR', NULL, 52, NULL, '310-55-20-0035-001', NULL, NULL, 'Pendente', 'MAKE', NULL, 659, 999999999, 708, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `materiais_aprovadores`
--

CREATE TABLE IF NOT EXISTS `materiais_aprovadores` (
  `MATERIAL` bigint(20) NOT NULL,
  `USUARIO` bigint(20) NOT NULL,
  PRIMARY KEY (`MATERIAL`,`USUARIO`),
  KEY `FK_MATERIAIS_APROVADORES_USUARIO` (`USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `materiais_aprovadores`
--


-- --------------------------------------------------------

--
-- Table structure for table `materiais_processos`
--

CREATE TABLE IF NOT EXISTS `materiais_processos` (
  `MATERIAL` bigint(20) NOT NULL,
  `PROCESSO` bigint(20) NOT NULL,
  PRIMARY KEY (`MATERIAL`,`PROCESSO`),
  KEY `FK_MATERIAIS_PROCESSOS_PROCESSO` (`PROCESSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `materiais_processos`
--

INSERT INTO `materiais_processos` (`MATERIAL`, `PROCESSO`) VALUES
(709, 701),
(710, 701),
(711, 701),
(714, 701),
(715, 701),
(716, 701),
(751, 701),
(752, 701),
(753, 701),
(755, 701),
(758, 701),
(759, 701),
(760, 701),
(761, 701),
(709, 702),
(710, 702),
(711, 702),
(714, 702),
(715, 702),
(716, 702),
(751, 702),
(752, 702),
(753, 702),
(755, 702),
(758, 702),
(759, 702),
(760, 702),
(761, 702),
(709, 703),
(710, 703),
(711, 703),
(714, 703),
(715, 703),
(716, 703),
(751, 703),
(752, 703),
(753, 703),
(755, 703),
(758, 703),
(759, 703),
(760, 703),
(761, 703);

-- --------------------------------------------------------

--
-- Table structure for table `processo_especial`
--

CREATE TABLE IF NOT EXISTS `processo_especial` (
  `IDPROCESSO` bigint(20) NOT NULL,
  `ESPECIFICACAO` varchar(255) DEFAULT NULL,
  `PROCESSO` varchar(255) DEFAULT NULL,
  `TEXTO` varchar(255) DEFAULT NULL,
  `CRIADOR` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDPROCESSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `processo_especial`
--

INSERT INTO `processo_especial` (`IDPROCESSO`, `ESPECIFICACAO`, `PROCESSO`, `TEXTO`, `CRIADOR`) VALUES
(701, 'RESERVA DE ESPA�O', 'PRELIMINAR 1', 'RESERVA DE ESPA�O', 999999999),
(702, 'RESERVA DE ESPA�O', 'PRELIMINAR 2', 'RESERVA DE ESPA�O', 999999999),
(703, 'RESERVA DE ESPA�O', 'PRELIMINAR 3', 'RESERVA DE ESPA�O', 999999999);

-- --------------------------------------------------------

--
-- Table structure for table `regras`
--

CREATE TABLE IF NOT EXISTS `regras` (
  `IDREGRA` bigint(20) NOT NULL,
  `REGRA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDREGRA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `regras`
--

INSERT INTO `regras` (`IDREGRA`, `REGRA`) VALUES
(1, 'Administrador'),
(2, 'Projetos'),
(3, 'Compras'),
(4, 'Qualidade'),
(5, 'Manufatura'),
(6, 'Engenharia Geral'),
(7, 'Engenharia Material'),
(8, 'Gerente Projeto'),
(9, 'Gerente Manufatura');

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '800');

-- --------------------------------------------------------

--
-- Table structure for table `tecnologias`
--

CREATE TABLE IF NOT EXISTS `tecnologias` (
  `IDTECNOLOGIA` bigint(20) NOT NULL,
  `TECNOLOGIA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDTECNOLOGIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tecnologias`
--

INSERT INTO `tecnologias` (`IDTECNOLOGIA`, `TECNOLOGIA`) VALUES
(551, 'HARDWARE'),
(652, 'PRIMARY PART'),
(653, 'ASSEMBLY'),
(654, 'LRU'),
(655, 'RAW MATERIAL'),
(656, 'TOOLING'),
(657, 'INSTRUMENTS'),
(658, 'SUB ASSEMBLY'),
(660, 'CONSUMABLES'),
(706, 'INSTALLATION');

-- --------------------------------------------------------

--
-- Table structure for table `unidades_fornecedor`
--

CREATE TABLE IF NOT EXISTS `unidades_fornecedor` (
  `IDUNIDADEF` bigint(20) NOT NULL,
  `UNIDADE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDUNIDADEF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unidades_fornecedor`
--


-- --------------------------------------------------------

--
-- Table structure for table `unidades_lote`
--

CREATE TABLE IF NOT EXISTS `unidades_lote` (
  `IDUNIDADEL` bigint(20) NOT NULL,
  `UNIDADE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDUNIDADEL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unidades_lote`
--


-- --------------------------------------------------------

--
-- Table structure for table `unidades_lote_fornecedor`
--

CREATE TABLE IF NOT EXISTS `unidades_lote_fornecedor` (
  `IDUNIDADELF` bigint(20) NOT NULL,
  `UNIDADE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDUNIDADELF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unidades_lote_fornecedor`
--


-- --------------------------------------------------------

--
-- Table structure for table `unidades_projeto`
--

CREATE TABLE IF NOT EXISTS `unidades_projeto` (
  `IDUNIDADEP` bigint(20) NOT NULL,
  `UNIDADE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDUNIDADEP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unidades_projeto`
--


-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `IDUSUARIO` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOME` varchar(255) DEFAULT NULL,
  `SENHA` varchar(255) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT '0',
  `USUARIO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDUSUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`IDUSUARIO`, `EMAIL`, `NOME`, `SENHA`, `STATUS`, `USUARIO`) VALUES
(601, 'ranieri.marcos@novaer.ind.br', 'Ranieri Ferreira', '087541', 1, 'ranieri.marcos'),
(651, 'amanda@novaer.ind.br', 'Amanda Almeida', 'vida1987', 1, 'amanda.almeida'),
(797, 'nelson.gavioli@novaer.ind.br', 'Nelson Gavioli', 'eunicerafael1', 1, 'ngavioli'),
(999999999, 'sistema@novaer.com.br', 'Administrador', '1', 1, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios_regras`
--

CREATE TABLE IF NOT EXISTS `usuarios_regras` (
  `USUARIO` bigint(20) NOT NULL,
  `REGRA` bigint(20) NOT NULL,
  PRIMARY KEY (`USUARIO`,`REGRA`),
  KEY `FK_USUARIOS_REGRAS_REGRA` (`REGRA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuarios_regras`
--

INSERT INTO `usuarios_regras` (`USUARIO`, `REGRA`) VALUES
(651, 1),
(999999999, 1),
(601, 2),
(797, 5),
(651, 8),
(651, 9);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `categorias`
--
ALTER TABLE `categorias`
  ADD CONSTRAINT `FK_CATEGORIAS_TECNOLOGIA` FOREIGN KEY (`TECNOLOGIA`) REFERENCES `tecnologias` (`IDTECNOLOGIA`);

--
-- Constraints for table `fornecedores_aprovadores`
--
ALTER TABLE `fornecedores_aprovadores`
  ADD CONSTRAINT `FK_FORNECEDORES_APROVADORES_FORNECEDOR` FOREIGN KEY (`FORNECEDOR`) REFERENCES `fornecedores` (`IDFORNECEDOR`),
  ADD CONSTRAINT `FK_FORNECEDORES_APROVADORES_USUARIO` FOREIGN KEY (`USUARIO`) REFERENCES `usuarios` (`IDUSUARIO`);

--
-- Constraints for table `materiais`
--
ALTER TABLE `materiais`
  ADD CONSTRAINT `FK_MATERIAIS_CATEGORIA` FOREIGN KEY (`CATEGORIA`) REFERENCES `categorias` (`IDCATEGORIA`),
  ADD CONSTRAINT `FK_MATERIAIS_CRIADOR` FOREIGN KEY (`CRIADOR`) REFERENCES `usuarios` (`IDUSUARIO`),
  ADD CONSTRAINT `FK_MATERIAIS_FORNECEDOR` FOREIGN KEY (`FORNECEDOR`) REFERENCES `fornecedores` (`IDFORNECEDOR`),
  ADD CONSTRAINT `FK_MATERIAIS_UN_FORNECEDOR` FOREIGN KEY (`UN_FORNECEDOR`) REFERENCES `unidades_fornecedor` (`IDUNIDADEF`),
  ADD CONSTRAINT `FK_MATERIAIS_UN_L` FOREIGN KEY (`UN_L`) REFERENCES `unidades_lote` (`IDUNIDADEL`),
  ADD CONSTRAINT `FK_MATERIAIS_UN_L_FORNECEDOR` FOREIGN KEY (`UN_L_FORNECEDOR`) REFERENCES `unidades_lote_fornecedor` (`IDUNIDADELF`);

--
-- Constraints for table `materiais_aprovadores`
--
ALTER TABLE `materiais_aprovadores`
  ADD CONSTRAINT `FK_MATERIAIS_APROVADORES_MATERIAL` FOREIGN KEY (`MATERIAL`) REFERENCES `materiais` (`IDMATERIAL`),
  ADD CONSTRAINT `FK_MATERIAIS_APROVADORES_USUARIO` FOREIGN KEY (`USUARIO`) REFERENCES `usuarios` (`IDUSUARIO`);

--
-- Constraints for table `materiais_processos`
--
ALTER TABLE `materiais_processos`
  ADD CONSTRAINT `FK_MATERIAIS_PROCESSOS_MATERIAL` FOREIGN KEY (`MATERIAL`) REFERENCES `materiais` (`IDMATERIAL`),
  ADD CONSTRAINT `FK_MATERIAIS_PROCESSOS_PROCESSO` FOREIGN KEY (`PROCESSO`) REFERENCES `processo_especial` (`IDPROCESSO`);

--
-- Constraints for table `usuarios_regras`
--
ALTER TABLE `usuarios_regras`
  ADD CONSTRAINT `FK_USUARIOS_REGRAS_REGRA` FOREIGN KEY (`REGRA`) REFERENCES `regras` (`IDREGRA`),
  ADD CONSTRAINT `FK_USUARIOS_REGRAS_USUARIO` FOREIGN KEY (`USUARIO`) REFERENCES `usuarios` (`IDUSUARIO`);
