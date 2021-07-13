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
-- Table structure for table `receive_site`
--

DROP TABLE IF EXISTS `receive_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receive_site` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `person` char(12) DEFAULT NULL,
  `address` varchar(90) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT '000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receive_site`
--

LOCK TABLES `receive_site` WRITE;
/*!40000 ALTER TABLE `receive_site` DISABLE KEYS */;
INSERT INTO `receive_site` VALUES (101,'南阳站点','江叙','河南省南阳市独山大道','15196385274','000000'),(102,'郑州站点','王富贵','郑州市中原区中原西路','13974185296','000000'),(103,'北京站点','许瞒','北京市大兴区','15325874123','000000'),(104,'武汉站点','王大龙','武汉市华容区葛洪大道','18896385241','000000'),(105,'上海站点','郑远东','上海市嘉定区胜辛北路','18632145698','000000'),(106,'曹县站点','王蕴','山东省菏泽市曹县','13485274196','000000'),(107,'开封站点','唐周','河南省开封市鼓楼区','15485296371','000000'),(108,'洛阳站点','王丰源','河南省洛阳市洛龙区','18296374185','000000'),(109,'商丘站点','张士济','河南省商丘市梁园区','13374185296','000000'),(110,'驻马店站点','张小满','河南省驻马店市驿城区','15696385274','000000');
/*!40000 ALTER TABLE `receive_site` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-13 12:45:37
