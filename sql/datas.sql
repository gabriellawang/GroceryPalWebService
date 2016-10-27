CREATE DATABASE  IF NOT EXISTS `grocerypal` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `grocerypal`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: grocerypal
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deal` (
  `deal_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `brand_name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `shop` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `time` varchar(50) NOT NULL,
  `img_dir` varchar(500) NOT NULL,
  `like_count` int(11) NOT NULL,
  `dislike_count` int(11) NOT NULL,
  `device_id` varchar(16) NOT NULL,
  `api_keyword` varchar(60) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`deal_id`),
  UNIQUE KEY `deal_id_UNIQUE` (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deal`
--

LOCK TABLES `deal` WRITE;
/*!40000 ALTER TABLE `deal` DISABLE KEYS */;
INSERT INTO `deal` VALUES (1,'12','12',12,'Sheng Siong','location data','15-10-2016 06:43:52','/Library/apache-tomcat-8.0.26/work/Catalina/localhost/GroceryPalWebService/IMG_20161015_184340.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(2,'aq2fwe','aefbesdfb',22,'Sheng Siong','location data','17-10-2016 06:06:58','/Library/apache-tomcat-8.0.26/work/Catalina/localhost/GroceryPalWebService/IMG_20161017_180639.jpg',0,1,'654a6c3887b402a5','Google Vision API',NULL),(3,'c','v',11,'Sheng Siong','location data','17-10-2016 07:16:40','/Library/apache-tomcat-8.0.26/work/Catalina/localhost/GroceryPalWebService/IMG_20161017_190708.jpg',1,0,'654a6c3887b402a5','Google Vision API',NULL),(4,'12334','wefwase',3123,'Sheng Siong','location data','17-10-2016 07:31:02','/Library/apache-tomcat-8.0.26/work/Catalina/localhost/GroceryPalWebService/IMG_20161017_192127.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(5,'111','zzz',1,'Sheng Siong','location data','17-10-2016 07:40:54','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161017_194039.jpg',1,0,'654a6c3887b402a5','Google Vision API',NULL),(6,'adafg','asd',12,'dfafb','adfg','17-10-2016 07:40:59','asda',0,0,'654a6c3887b402a5','afvadf',NULL),(7,'adafg','asd',12,'dfafb','adfg','17-10-2016 07:40:59','asda',0,0,'654a6c3887b402a5','afvadf',NULL),(8,'llllllll','ppppp',9,'Sheng Siong','location data','18-10-2016 10:06:06','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100550.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(9,'yrctvkbhjkn','hgfdsxdfghj',5678,'Giant','location data','18-10-2016 10:06:59','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100639.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(10,'8765rdfcvbnm','jhgfdxcvbnm',8765,'Fairprice','location data','18-10-2016 10:07:26','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100704.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(11,'nxcvbnm','iuytrdfxcv',98,'Cold Storage','location data','18-10-2016 10:07:51','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100732.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(12,'jhvc','ytfc',23,'Sheng Siong','location data','18-10-2016 10:08:22','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100808.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(13,'uydxcvbn','lkn',87654,'Sheng Siong','location data','18-10-2016 10:08:44','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100827.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(14,'kjhgfdsdfxcbvn','kjhgcv',87,'Sheng Siong','location data','18-10-2016 10:09:19','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100857.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL),(15,'hgfxcvbn','ghvbn',3,'Cold Storage','location data','18-10-2016 10:09:41','http://10.124.4.127:8080//GroceryPalWebService/image?name=IMG_20161018_100924.jpg',0,0,'654a6c3887b402a5','Google Vision API',NULL);
/*!40000 ALTER TABLE `deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_id` int(11) NOT NULL,
  `device_id` varchar(16) NOT NULL,
  `review_time` varchar(50) NOT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`),
  KEY `deal_id_idx` (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'1234567890123456','2016-10-14 19:37:00','good!');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(16) NOT NULL,
  `is_like` tinyint(1) NOT NULL,
  `deal_id` int(11) NOT NULL,
  PRIMARY KEY (`vote_id`),
  UNIQUE KEY `vote_id_UNIQUE` (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (1,'1234567890123456',1,1),(2,'654a6c3887b402a5',1,3),(3,'654a6c3887b402a5',0,2),(4,'654a6c3887b402a5',0,8),(5,'654a6c3887b402a5',1,5);
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-18 14:58:15
