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
  `std_code` int(11) DEFAULT NULL,
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
  `std_code` int(11) DEFAULT NULL,
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
  `std_code` int(11) DEFAULT NULL,
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
  `std_code` int(11) DEFAULT NULL,
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

-- Dump completed on 2016-07-08 14:57:43



-- 国家
-- "AE,United Arab Emirates,阿联酋","AF,Afghanistan,阿富汗","AL,Albania,阿尔巴尼亚","AO,Angola,安哥拉","AR,Argentina,阿根廷","AT,Austria,奥地利","AU,Australia,澳大利亚","AZ,Azerbaijan,阿塞拜疆","BD,Bangladesh,孟加拉","BE,Belgium,比利时","BG,Bulgaria,保加利亚","BH,Bahrain,巴林","BI,Burundi,布隆迪","BJ,Benin,贝宁","BM,Bermuda,百慕大","BN,Brunei,文莱","BO,Bolivia,玻利维亚","BR,Brazil,巴西","BS,Bahamas,巴哈马","BT,Bhutan,不丹","BW,Botswana,博茨瓦纳","CA,Canada,加拿大","CF,Central Africa,中非共和国","CG,Congo,刚果","CH,Switzerland,瑞士","CK,Cook Is.,库克群岛","CL,Chile,智利","CM,Cameroon,喀麦隆","CN,China,中国","CO,Colombia,哥伦比亚","CR,Costa Rica,哥斯达黎加","CU,Cuba,古巴","CV,Cape Verde Is.,佛得角群岛","CY,Cyprus,塞浦路斯","CZ,Czech,捷克共和国","DE,Germany,德国","DK,Denmark,丹麦","DZ,Algeria,阿尔及利亚","EC,Ecuador,厄瓜多尔","EE,Estonia,爱沙尼亚","EG,Egypt,埃及","ES,Spain,西班牙","ET,Ethiopia,埃塞俄比亚","FI,Finland,芬兰","FJ,Fiji,斐济","FR,France,法国","GA,Gabon,加蓬","GB,Great Britain,英国","GD,Grenada,格林纳达","GH,Ghana,加纳","GM,Gambia,冈比亚","GN,Guinea-Bissau,几内亚","GQ,Equatorial Guinea,赤道几内亚","GR,Greece,希腊","GT,Guatemala,危地马拉","GU,Guam,关岛","GY,Guyana,圭亚那","HK,Hong kong,香港","HN,Honduras,洪都拉斯","HR,Croatia,克罗地亚","HT,Haiti,海地","HU,Hungary,匈牙利","ID,Indonesia,印度尼西亚","IE,Ireland,爱尔兰","IL,Israel,以色列","IN,India,印度","IQ,Iraq,伊拉克","IR,Iran,伊朗","IS,Iceland,冰岛","IT,Italy,意大利","JM,Jamaica,牙买加","JO,Jordan,约旦","JP,Japan,日本","KE,Kenya,肯尼亚","KH,Cambodia,柬埔寨","KP,R.O.Korea,韩国","KR,D.P.R.Korea,北朝鲜","KW,Kuwait,科威特","KZ,Kazakhstan,哈萨克斯坦","LA,Laos,老挝","LB,Lebanon,黎巴嫩","LT,Lithuania,立陶宛","LU,Luxembourg,卢森堡","LV,Latvia,拉托维亚","LY,Libya,利比亚","MA,Morocco,摩洛哥","MC,Monaco,摩纳哥","MD,Moldova,摩尔多瓦","MG,Madagascar,马达加斯加","ML,Mali,马里","MN,Mongolia,蒙古","MO,Macao,澳门","MR,Mauritania,毛里塔尼亚","MT,Malta,马耳他","MU,Mauritius,毛里求斯","MV,Maldives,马尔代夫","MX,Mexico,墨西哥","MY,Malaysia,马来西亚","MZ,Mozambique,莫桑比克","NA,Namibia,纳米比亚","NE,Niger,尼日尔","NG,Nigeria,尼日利亚","NI,Nicaragua,尼加拉瓜","NL,Netherlands,荷兰","NO,Norway,挪威","NP,Nepal,尼泊尔","NZ,New Zealand,新西兰","OM,Oman,阿曼","PA,Panama,巴拿马","PE,Peru,秘鲁","PG,Papua New Guinea,巴布亚新几内亚","PH,Philippines,菲律宾","PK,Pakistan,巴基斯坦","PL,Poland,波兰","PT,Portugal,葡萄牙","PY,Paraguay,巴拉圭","QA,Qatar,卡塔尔","RO,Romania,罗马尼亚","RU,Russia,俄罗斯","RW,Rwanda,卢旺达","SA,Saudi Arabia,沙特阿拉伯","SD,Sudan,苏丹","SE,Sweden,瑞典","SG,Singapore,新加坡","SK,Slovakia,斯洛伐克","SM,San Marino,圣马力诺","SN,Senegal,塞内加尔","SO,Somalia,索马里","SY,Syria,叙利亚","TH,Thailand,泰国","TJ,Tadzhikistan,塔吉克斯坦","TM,Turkmenistan,土库曼斯坦","TN,Tunisia,突尼斯","TO,Tonga,汤加","TW,Taiwan,台湾","TZ,Tanzania,坦桑尼亚","UA,Ukraine,乌克兰","UG,Uganda,乌干达","UK,United Kingdom,英国","US,United States,美国","UY,Uruguay,乌拉圭","UZ,Uzbekistan,乌兹别克斯坦","VA,Vatican City,梵蒂冈","VE,Venezuela,委内瑞拉","VN,Viet Nam,越南","YE,Yemen,也门","YU,Yugoslavia,南斯拉夫","ZA,South Africa,南非","ZM,Zambia,赞比亚","ZR,Zaire,扎伊尔","ZW,Zimbabwe,津巴布"