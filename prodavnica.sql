-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 28, 2020 at 04:32 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prodavnica`
--

-- --------------------------------------------------------

--
-- Table structure for table `proizvodi`
--

DROP TABLE IF EXISTS `proizvodi`;
CREATE TABLE IF NOT EXISTS `proizvodi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) NOT NULL,
  `Kategorija` varchar(255) NOT NULL,
  `Cena` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `proizvodi`
--

INSERT INTO `proizvodi` (`ID`, `Naziv`, `Kategorija`, `Cena`) VALUES
(1, 'Jack Daniels', 'Pica', 3333),
(2, 'Plazma Keks', 'Hrana', 120);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
