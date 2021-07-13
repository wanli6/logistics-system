-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: logistics
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consignor`
--

DROP TABLE IF EXISTS `consignor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consignor` (
  `id` int NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL,
  `address` varchar(90) DEFAULT NULL,
  `tel` char(11) NOT NULL,
  `password` varchar(20) NOT NULL DEFAULT '000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consignor`
--

LOCK TABLES `consignor` WRITE;
/*!40000 ALTER TABLE `consignor` DISABLE KEYS */;
INSERT INTO `consignor` VALUES (2401,'中元制造','青海省白银市新青区白玉路','13459874903','000000'),(2402,'泰镇电子','山东省安庆市带岭区宝昌路','15845685201','000000'),(2403,'永和建设','台湾省保定市乌伊岭区安远路','14596325842','000000'),(2404,'正耀化工','青海省蚌埠市五营区白兰路','19912587410','000000'),(2405,'广港建材','河南省安康市伊春区白水路','15639401567','000000'),(2406,'昭和建设','浙江省蚌埠市五营区宝源路','15012894562','000000'),(2407,'同州纺织厂','甘肃省安顺市南岔区安顺路','15478930215','000000'),(2408,'正初工业','湖北省蚌埠市五营区宝联路','18896570648','000000'),(2409,'元和服饰','河南省安阳市金山屯区鞍山支路','15275201486','000000'),(2410,'正耀电信','福建省蚌埠市五营区宝林路','15639401568','000000');
/*!40000 ALTER TABLE `consignor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-13 12:45:36
