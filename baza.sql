/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - prodajafotografija
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prodajafotografija` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `prodajafotografija`;

/*Table structure for table `digitalnafotografija` */

DROP TABLE IF EXISTS `digitalnafotografija`;

CREATE TABLE `digitalnafotografija` (
  `idDigitalnaFotografija` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivFotografije` varchar(50) NOT NULL,
  `cena` double NOT NULL,
  PRIMARY KEY (`idDigitalnaFotografija`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `digitalnafotografija` */

insert  into `digitalnafotografija`(`idDigitalnaFotografija`,`nazivFotografije`,`cena`) values 
(1,'Osmeh',1000),
(2,'Tuga',2000),
(3,'Sloboda',1500),
(5,'Maska',5500),
(7,'Problem',3500),
(8,'Domovina',500),
(10,'Ostrvo',2000);

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `idKupac` bigint(20) NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `mesto` bigint(20) NOT NULL,
  PRIMARY KEY (`idKupac`),
  KEY `mesto` (`mesto`),
  CONSTRAINT `kupac_ibfk_1` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kupac` */

insert  into `kupac`(`idKupac`,`imePrezime`,`email`,`datum`,`mesto`) values 
(7,'Marko Petrović','marko.petrovic@example.com','2004-02-24',2),
(13,'Jelena Ilić','jelena.ilic@example.com','2003-10-27',2),
(21,'Nikola Stanković','nikola.stankovic@example.com','2015-07-22',2),
(22,'Ana Popović','ana.popovic@example.com','2003-05-04',8),
(26,'Stefan Marković','stefan.markovic@example.com','2005-08-23',1);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `idMesto` bigint(20) NOT NULL AUTO_INCREMENT,
  `država` varchar(50) NOT NULL,
  `grad` varchar(50) NOT NULL,
  `poštanskiBroj` varchar(50) NOT NULL,
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`idMesto`,`država`,`grad`,`poštanskiBroj`) values 
(1,'Srbija','Pirot','18300'),
(2,'Srbija','Beograd','11000'),
(4,'Srbija','Požarevac','12000'),
(5,'Srbija','Smederevo','15000'),
(8,'Srbija','Trstenik','37240');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `idRačun` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  `ukupanIznos` double NOT NULL,
  `umetnik` bigint(20) NOT NULL,
  `kupac` bigint(20) NOT NULL,
  PRIMARY KEY (`idRačun`),
  KEY `umetnik` (`umetnik`),
  KEY `kupac` (`kupac`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`umetnik`) REFERENCES `umetnik` (`idUmetnik`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`kupac`) REFERENCES `kupac` (`idKupac`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racun` */

insert  into `racun`(`idRačun`,`datum`,`ukupanIznos`,`umetnik`,`kupac`) values 
(66,'2005-05-05',9000,9,7),
(67,'2023-03-12',26000,27,22),
(68,'2025-05-04',20000,38,13),
(69,'2025-05-04',4000,27,26),
(70,'2021-05-04',6500,27,22),
(71,'2024-04-04',3500,28,21),
(72,'2024-04-04',3500,28,21),
(75,'2007-09-30',9000,39,26);

/*Table structure for table `stavkaračuna` */

DROP TABLE IF EXISTS `stavkaračuna`;

CREATE TABLE `stavkaračuna` (
  `rb` bigint(20) NOT NULL,
  `račun` bigint(20) NOT NULL,
  `iznos` double NOT NULL,
  `količina` bigint(20) NOT NULL,
  `cenaFotografije` double NOT NULL,
  `digitalnaFotografija` bigint(20) NOT NULL,
  PRIMARY KEY (`rb`,`račun`),
  KEY `račun` (`račun`),
  KEY `digitalnaFotografija` (`digitalnaFotografija`),
  CONSTRAINT `stavkaračuna_ibfk_1` FOREIGN KEY (`račun`) REFERENCES `racun` (`idRačun`),
  CONSTRAINT `stavkaračuna_ibfk_2` FOREIGN KEY (`digitalnaFotografija`) REFERENCES `digitalnafotografija` (`idDigitalnaFotografija`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaračuna` */

insert  into `stavkaračuna`(`rb`,`račun`,`iznos`,`količina`,`cenaFotografije`,`digitalnaFotografija`) values 
(1,66,1500,1,1500,3),
(1,67,21000,6,3500,7),
(1,68,1500,1,1500,3),
(1,69,3500,1,3500,7),
(1,70,5500,1,5500,5),
(1,71,2000,1,2000,2),
(1,72,2000,1,2000,2),
(1,75,1000,2,500,8),
(2,66,3500,1,3500,7),
(2,67,4500,3,1500,3),
(2,68,16500,3,5500,5),
(2,69,500,1,500,8),
(2,70,1000,2,500,8),
(2,71,1500,3,500,8),
(2,72,1500,3,500,8),
(2,75,8000,4,2000,10),
(3,66,4000,2,2000,2),
(3,67,500,1,500,8),
(3,68,2000,2,1000,1);

/*Table structure for table `strucnasprema` */

DROP TABLE IF EXISTS `strucnasprema`;

CREATE TABLE `strucnasprema` (
  `idStrucnaSprema` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  PRIMARY KEY (`idStrucnaSprema`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `strucnasprema` */

insert  into `strucnasprema`(`idStrucnaSprema`,`naziv`) values 
(3,'Kurs digitalne fotografije'),
(4,'Srednja umetnička škola'),
(5,'Fakultet dramskih umetnosti'),
(8,'Fakultet savremenih umetnosti');

/*Table structure for table `umetnik` */

DROP TABLE IF EXISTS `umetnik`;

CREATE TABLE `umetnik` (
  `idUmetnik` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `korisničkoIme` varchar(50) NOT NULL,
  `šifra` varchar(50) NOT NULL,
  PRIMARY KEY (`idUmetnik`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `umetnik` */

insert  into `umetnik`(`idUmetnik`,`ime`,`prezime`,`korisničkoIme`,`šifra`) values 
(9,'Petar','Lazić','petar','petar123'),
(27,'Milena','Đorđević','milena','milena123'),
(28,'Luka','Pavlović','luka','luka123'),
(34,'Demo','Korisnik','demo','demo'),
(38,'Ivana','Kostić','ivana','ivana123'),
(39,'Teodora','Simić','teodora','teodora123'),
(41,'Vuk','Ristić','vuk','vuk123'),
(50,'Mina','Todorović','mina','mina123');

/*Table structure for table `umss` */

DROP TABLE IF EXISTS `umss`;

CREATE TABLE `umss` (
  `umetnik` bigint(20) NOT NULL,
  `stručnaSprema` bigint(20) NOT NULL,
  `datumZavršetka` date NOT NULL,
  `ustanova` varchar(50) NOT NULL,
  PRIMARY KEY (`umetnik`,`stručnaSprema`),
  KEY `stručnaSprema` (`stručnaSprema`),
  CONSTRAINT `umss_ibfk_1` FOREIGN KEY (`umetnik`) REFERENCES `umetnik` (`idUmetnik`),
  CONSTRAINT `umss_ibfk_2` FOREIGN KEY (`stručnaSprema`) REFERENCES `strucnasprema` (`idStrucnaSprema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `umss` */

insert  into `umss`(`umetnik`,`stručnaSprema`,`datumZavršetka`,`ustanova`) values 
(9,5,'2004-05-04','Beogradski univerzitet'),
(27,4,'2019-05-05','Srednja umetnička Beograd'),
(28,4,'2011-05-05','Srednja umetnička'),
(28,5,'2015-05-05','Beogradski univerzitet'),
(34,3,'2018-09-09','Muzej'),
(38,5,'2025-05-26','Beogradski univerzitet'),
(39,3,'2013-04-16','Foto duga Pirot'),
(41,3,'2025-04-04','Muzej'),
(50,3,'2024-06-03','Foto Duga');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
