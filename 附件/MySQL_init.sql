CREATE USER 'prm'@'localhost' IDENTIFIED BY 'prm123456';

GRANT all ON prm.* TO 'prm'@'localhost';

flush privileges;

CREATE DATABASE `prm` DEFAULT CHARACTER SET utf8;

use prm;







-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 121.40.83.113    Database: prm
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

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
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_acc_msg`
--

DROP TABLE IF EXISTS `t_acc_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_msg` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_acc_tip`
--

DROP TABLE IF EXISTS `t_acc_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_tip` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_acc_to_group`
--

DROP TABLE IF EXISTS `t_acc_to_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_to_group` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_acc_to_tip`
--

DROP TABLE IF EXISTS `t_acc_to_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_acc_to_tip` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_account`
--

DROP TABLE IF EXISTS `t_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_account` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `acc` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`guid`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_address`
--

DROP TABLE IF EXISTS `t_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_address` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_contact`
--

DROP TABLE IF EXISTS `t_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_contact` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `weixin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系方式表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_country`
--

DROP TABLE IF EXISTS `t_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_country` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_custom`
--

DROP TABLE IF EXISTS `t_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_custom_to_addr`
--

DROP TABLE IF EXISTS `t_custom_to_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom_to_addr` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_custom_to_contact`
--

DROP TABLE IF EXISTS `t_custom_to_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom_to_contact` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_custom_to_psg`
--

DROP TABLE IF EXISTS `t_custom_to_psg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_custom_to_psg` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_group_to_custom`
--

DROP TABLE IF EXISTS `t_group_to_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group_to_custom` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_msg_type`
--

DROP TABLE IF EXISTS `t_msg_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_msg_type` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `custom_id` varchar(45) DEFAULT NULL,
  `order_type` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `price` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_order_item`
--

DROP TABLE IF EXISTS `t_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_item` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `order_id` varchar(45) DEFAULT NULL,
  `passenger_id` varchar(45) DEFAULT NULL,
  `addr_from` varchar(45) DEFAULT NULL,
  `addr_to` varchar(45) DEFAULT NULL,
  `trip_type` int(11) DEFAULT NULL,
  `go_time` timestamp NULL DEFAULT NULL,
  `return_time` timestamp NULL DEFAULT NULL,
  `price` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_order_type`
--

DROP TABLE IF EXISTS `t_order_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_type` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
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
-- Table structure for table `t_passenger`
--

DROP TABLE IF EXISTS `t_passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_passenger` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `country_id` varchar(45) DEFAULT NULL,
  `id_card` varchar(45) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='乘客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_payment`
--

DROP TABLE IF EXISTS `t_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_payment` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `order_id` varchar(45) DEFAULT NULL,
  `drawee` varchar(45) DEFAULT NULL,
  `payee` varchar(45) DEFAULT NULL,
  `price` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sequence`
--

DROP TABLE IF EXISTS `t_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sequence` (
  `guid` varchar(45) NOT NULL,
  `std_name` varchar(45) DEFAULT NULL,
  `std_code` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_user` varchar(45) DEFAULT NULL,
  `modify_user` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL,
  `seq_key` varchar(45) NOT NULL,
  `seq_value` int(11) DEFAULT '0',
  `min_value` int(11) DEFAULT '0',
  `max_value` int(11) DEFAULT '99999999',
  `increment` int(11) DEFAULT '1',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `seq_key_UNIQUE` (`seq_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列表';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-09 13:35:54
