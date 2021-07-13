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
-- Temporary view structure for view `arrive_msg`
--

DROP TABLE IF EXISTS `arrive_msg`;
/*!50001 DROP VIEW IF EXISTS `arrive_msg`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `arrive_msg` AS SELECT 
 1 AS `tid`,
 1 AS `num`,
 1 AS `startTime`,
 1 AS `data`,
 1 AS `id`,
 1 AS `name`,
 1 AS `address`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `logistics_view`
--

DROP TABLE IF EXISTS `logistics_view`;
/*!50001 DROP VIEW IF EXISTS `logistics_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `logistics_view` AS SELECT 
 1 AS `lid`,
 1 AS `lname`,
 1 AS `ladd`,
 1 AS `ltel`,
 1 AS `cid`,
 1 AS `cname`,
 1 AS `gid`,
 1 AS `name`,
 1 AS `num`,
 1 AS `driver1`,
 1 AS `driver2`,
 1 AS `weight`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `goodsforlog`
--

DROP TABLE IF EXISTS `goodsforlog`;
/*!50001 DROP VIEW IF EXISTS `goodsforlog`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `goodsforlog` AS SELECT 
 1 AS `lid`,
 1 AS `lname`,
 1 AS `cid`,
 1 AS `cname`,
 1 AS `gid`,
 1 AS `gname`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `free_truck`
--

DROP TABLE IF EXISTS `free_truck`;
/*!50001 DROP VIEW IF EXISTS `free_truck`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `free_truck` AS SELECT 
 1 AS `id`,
 1 AS `num`,
 1 AS `driver1`,
 1 AS `driver2`,
 1 AS `isFree`,
 1 AS `lid`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `arrive_msg`
--

/*!50001 DROP VIEW IF EXISTS `arrive_msg`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `arrive_msg` AS select `trucks`.`id` AS `tid`,`trucks`.`num` AS `num`,`trucks`.`startTime` AS `startTime`,`arrive`.`data` AS `data`,`receive_site`.`id` AS `id`,`receive_site`.`name` AS `name`,`receive_site`.`address` AS `address` from ((`trucks` join `arrive`) join `receive_site`) where ((`trucks`.`id` = `arrive`.`t_id`) and (`receive_site`.`id` = `arrive`.`r_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `logistics_view`
--

/*!50001 DROP VIEW IF EXISTS `logistics_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `logistics_view` AS select `logistics`.`id` AS `lid`,`logistics`.`name` AS `lname`,`logistics`.`address` AS `ladd`,`logistics`.`tel` AS `ltel`,`consignor`.`id` AS `cid`,`consignor`.`name` AS `cname`,`goods`.`id` AS `gid`,`goods`.`name` AS `name`,`trucks`.`num` AS `num`,`trucks`.`driver1` AS `driver1`,`trucks`.`driver2` AS `driver2`,`trucks`.`weight` AS `weight` from (((((`logistics` join `consignor`) join `trucks`) join `contract`) join `goods`) join `cooperate`) where ((`logistics`.`id` = `contract`.`l_id`) and (`consignor`.`id` = `contract`.`c_id`) and (`logistics`.`id` = `cooperate`.`l_id`) and (`cooperate`.`t_id` = `trucks`.`id`) and (`goods`.`c_id` = `consignor`.`id`) and (`goods`.`t_id` = `trucks`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `goodsforlog`
--

/*!50001 DROP VIEW IF EXISTS `goodsforlog`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `goodsforlog` AS select `logistics`.`id` AS `lid`,`logistics`.`name` AS `lname`,`consignor`.`id` AS `cid`,`consignor`.`name` AS `cname`,`goods`.`id` AS `gid`,`goods`.`name` AS `gname` from (((`logistics` join `contract`) join `goods`) join `consignor`) where ((`logistics`.`id` = `contract`.`l_id`) and (`contract`.`c_id` = `consignor`.`id`) and (`goods`.`c_id` = `consignor`.`id`) and (`goods`.`t_id` = 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `free_truck`
--

/*!50001 DROP VIEW IF EXISTS `free_truck`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `free_truck` AS select `trucks`.`id` AS `id`,`trucks`.`num` AS `num`,`trucks`.`driver1` AS `driver1`,`trucks`.`driver2` AS `driver2`,`trucks`.`isFree` AS `isFree`,`logistics`.`id` AS `lid` from ((`trucks` join `cooperate`) join `logistics`) where ((`logistics`.`id` = `cooperate`.`l_id`) and (`cooperate`.`t_id` = `trucks`.`id`) and (`trucks`.`isFree` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping events for database 'logistics'
--

--
-- Dumping routines for database 'logistics'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-13 12:45:37
