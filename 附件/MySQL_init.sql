CREATE USER 'prm'@'localhost' IDENTIFIED BY 'prm123456';

GRANT all ON prm.* TO 'prm'@'localhost';

flush privileges;

CREATE DATABASE `prm` DEFAULT CHARACTER SET utf8;

use prm;

-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: prm
-- ------------------------------------------------------
-- Server version	5.7.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_acc_group`
--

DROP TABLE IF EXISTS `t_acc_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_group` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `is_private` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_acc_group`
--

LOCK TABLES `t_acc_group` WRITE;
/*!40000 ALTER TABLE `t_acc_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_acc_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_acc_tip`
--

DROP TABLE IF EXISTS `t_acc_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_tip` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_acc_tip`
--

LOCK TABLES `t_acc_tip` WRITE;
/*!40000 ALTER TABLE `t_acc_tip` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_acc_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_acc_to_group`
--

DROP TABLE IF EXISTS `t_acc_to_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_to_group` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `acc_id` varchar(45) DEFAULT NULL,
  `group_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户与账户组关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_acc_to_group`
--

LOCK TABLES `t_acc_to_group` WRITE;
/*!40000 ALTER TABLE `t_acc_to_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_acc_to_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_acc_to_msg`
--

DROP TABLE IF EXISTS `t_acc_to_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_to_msg` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `acc_id` varchar(45) DEFAULT NULL,
  `msg_type` varchar(45) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `is_read` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_acc_to_msg`
--

LOCK TABLES `t_acc_to_msg` WRITE;
/*!40000 ALTER TABLE `t_acc_to_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_acc_to_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_acc_to_tip`
--

DROP TABLE IF EXISTS `t_acc_to_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_to_tip` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `acc_id` varchar(45) DEFAULT NULL,
  `tip_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户与标签关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_acc_to_tip`
--

LOCK TABLES `t_acc_to_tip` WRITE;
/*!40000 ALTER TABLE `t_acc_to_tip` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_acc_to_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_account`
--

DROP TABLE IF EXISTS `t_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_account` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `acc` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_account`
--

LOCK TABLES `t_account` WRITE;
/*!40000 ALTER TABLE `t_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_address`
--

DROP TABLE IF EXISTS `t_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_address` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `last_use` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_address`
--

LOCK TABLES `t_address` WRITE;
/*!40000 ALTER TABLE `t_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_contact`
--

DROP TABLE IF EXISTS `t_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contact` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系方式表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_contact`
--

LOCK TABLES `t_contact` WRITE;
/*!40000 ALTER TABLE `t_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_country`
--

DROP TABLE IF EXISTS `t_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_country` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `en_name` varchar(45) DEFAULT NULL,
  `cn_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国家表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_country`
--

LOCK TABLES `t_country` WRITE;
/*!40000 ALTER TABLE `t_country` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_custom`
--

DROP TABLE IF EXISTS `t_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_custom`
--

LOCK TABLES `t_custom` WRITE;
/*!40000 ALTER TABLE `t_custom` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_custom_to_addr`
--

DROP TABLE IF EXISTS `t_custom_to_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom_to_addr` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  `addr_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户与地址关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_custom_to_addr`
--

LOCK TABLES `t_custom_to_addr` WRITE;
/*!40000 ALTER TABLE `t_custom_to_addr` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_custom_to_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_custom_to_contact`
--

DROP TABLE IF EXISTS `t_custom_to_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom_to_contact` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  `contact_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户与联系方式关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_custom_to_contact`
--

LOCK TABLES `t_custom_to_contact` WRITE;
/*!40000 ALTER TABLE `t_custom_to_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_custom_to_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_custom_to_psg`
--

DROP TABLE IF EXISTS `t_custom_to_psg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom_to_psg` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  `passenger_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户与乘客关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_custom_to_psg`
--

LOCK TABLES `t_custom_to_psg` WRITE;
/*!40000 ALTER TABLE `t_custom_to_psg` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_custom_to_psg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_group_to_custom`
--

DROP TABLE IF EXISTS `t_group_to_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group_to_custom` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `group_id` varchar(45) DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户组与客户关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group_to_custom`
--

LOCK TABLES `t_group_to_custom` WRITE;
/*!40000 ALTER TABLE `t_group_to_custom` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_group_to_custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_msg_type`
--

DROP TABLE IF EXISTS `t_msg_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_msg_type` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `is_private` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_msg_type`
--

LOCK TABLES `t_msg_type` WRITE;
/*!40000 ALTER TABLE `t_msg_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_msg_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_item`
--

DROP TABLE IF EXISTS `t_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_item` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `order_id` varchar(45) DEFAULT NULL,
  `passenger_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_item`
--

LOCK TABLES `t_order_item` WRITE;
/*!40000 ALTER TABLE `t_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_type`
--

DROP TABLE IF EXISTS `t_order_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_type` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_type`
--

LOCK TABLES `t_order_type` WRITE;
/*!40000 ALTER TABLE `t_order_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_passenger`
--

DROP TABLE IF EXISTS `t_passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_passenger` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `stdcode` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  `country_id` varchar(45) DEFAULT NULL,
  `id_card` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='乘客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_passenger`
--

LOCK TABLES `t_passenger` WRITE;
/*!40000 ALTER TABLE `t_passenger` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_passenger` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-04 17:44:04
