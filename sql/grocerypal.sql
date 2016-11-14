-- phpMyAdmin SQL Dump
-- version 4.0.10.12
-- http://www.phpmyadmin.net
--
-- Host: 127.5.105.2:3306
-- Generation Time: Nov 14, 2016 at 08:38 AM
-- Server version: 5.5.52
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `grocerypal`
--
CREATE DATABASE IF NOT EXISTS `grocerypal` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `grocerypal`;

-- --------------------------------------------------------

--
-- Table structure for table `deal`
--

DROP TABLE IF EXISTS `deal`;
CREATE TABLE IF NOT EXISTS `deal` (
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
  `api_keyword` text NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`deal_id`),
  UNIQUE KEY `deal_id_UNIQUE` (`deal_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `deal`
--

INSERT INTO `deal` (`deal_id`, `product_name`, `brand_name`, `price`, `shop`, `location`, `time`, `img_dir`, `like_count`, `dislike_count`, `device_id`, `api_keyword`, `description`) VALUES
(1, 'Kisses Cookies ''n'' Creme', 'Hershey''s', 7, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 12:59:35', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016125934365.jpg', 0, 0, '3b2f13e725b8f04f', 'dessert,junk food,flavor,food', 'White chocolate with cookie bits'),
(2, 'Ferrero Rocher', 'Ferrero Rocher', 18.5, 'Sheng Siong', '1.3005677338931318,103.85199808318694', '12-11-2016 01:03:17', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016010316310.jpg', 0, 0, '3b2f13e725b8f04f', 'food,bakery,dessert,chocolate,baking', 'Special offer: 32  pieces!'),
(3, 'Cream Candies', 'Werther''s Original', 3.85, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:06:20', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016010618847.jpg', 0, 0, '3b2f13e725b8f04f', 'food,dessert,sense,snack food,flavor', 'Butterscotch candy, 150G'),
(4, 'Jelly Beans Fruit Bowl', 'Jelly Bean', 3.5, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:08:42', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016010841652.jpg', 0, 0, '3b2f13e725b8f04f', 'food,dessert,sense,sweetness,snack food', 'Jellybeans fruit bowl mix 100g'),
(5, 'Cashews', 'Camel', 5.1, 'Fairprice', '1.3005677338931318,103.85199808318694', '12-11-2016 01:12:37', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016011236058.jpg', 0, 0, '3b2f13e725b8f04f', 'food,plant,produce,land plant,fruit', 'Natural baked cashews. Premium quality.'),
(6, 'Dishwashing Liquid', 'Cold Storage essential', 4.35, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:16:36', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016011635610.jpg', 0, 0, '3b2f13e725b8f04f', 'product,cleaning,produce', 'Bundle Pack.'),
(7, '2 in 1 Sponge', 'Scotch Brite', 4.45, 'Sheng Siong', '1.3005677338931318,103.85199808318694', '12-11-2016 01:19:08', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016011906215.jpg', 0, 0, '3b2f13e725b8f04f', 'produce,product', '3+1 super value. Heavy Duty. Anti-bacterial. Resist Odours'),
(8, 'Glass cleaner', 'Mr Muscle', 5.25, 'Sheng Siong', '1.3005677338931318,103.85199808318694', '12-11-2016 01:21:13', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016012112968.jpg', 0, 0, '3b2f13e725b8f04f', 'product', '1 for 1. Better coverage for better cleaning.'),
(9, 'Toothbrush', 'Lion', 10.2, 'Giant', '1.3005677338931318,103.85199808318694', '12-11-2016 01:24:12', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016012411470.jpg', 0, 0, '3b2f13e725b8f04f', 'product', 'Systema. Buy 2 get 1 free.'),
(10, 'Diapers', 'Huggies', 37.9, 'Sheng Siong', '1.3005677338931318,103.85199808318694', '12-11-2016 01:26:31', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016012630081.jpg', 0, 0, '3b2f13e725b8f04f', 'product', 'Platinum diapers newborn. 66 diapers.'),
(11, 'Body Wash', 'Dove', 9.95, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:30:16', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016013014956.jpg', 0, 0, '3b2f13e725b8f04f', 'product,produce', 'Nutrium mosisture. 1L'),
(12, 'Pro-V Hair Fall Control Shampoo', 'Pantene', 14.35, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:33:10', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016013309493.jpg', 0, 0, '3b2f13e725b8f04f', 'skin,product', 'Buy 1 get 1 free'),
(13, 'Strawberry Wafer', 'OREO', 3.8, 'Fairprice', '1.3005677338931318,103.85199808318694', '12-11-2016 01:37:54', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016013752829.jpg', 0, 0, '3b2f13e725b8f04f', 'food,junk food,dessert', '16 sticks. Stawberry and chocolate cream.'),
(14, 'Granola Bars', 'Nature Valley', 5.3, 'Sheng Siong', '1.3005677338931318,103.85199808318694', '12-11-2016 01:40:46', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016014044755.jpg', 0, 0, '3b2f13e725b8f04f', 'food,meal,breakfast,dish,snack food', 'Apple Crisp'),
(15, 'Aerovera Yogurt', 'Emmi', 2.75, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:46:59', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016014658048.jpg', 0, 0, '3b2f13e725b8f04f', 'food,product,breakfast,produce,meal', 'Any 2 for $2.75'),
(16, 'Honey Lemon Drink', 'Pokka', 1.5, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:50:54', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016015053235.jpg', 0, 0, '3b2f13e725b8f04f', 'food,breakfast,produce,junk food,meal,baverage', '$0.30 off usual price'),
(17, 'Premium Milk Tea', 'Pokka', 1.45, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 01:54:23', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016015422592.jpg', 0, 0, '3b2f13e725b8f04f', 'food,drink,breakfast,flavor,produce', '100% real brewed'),
(18, 'Kisses Cookie & Cream', 'Hersheys', 7, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 02:10:09', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016021008727.jpg', 0, 0, '3b2f13e725b8f04f', 'food,drink,dessert,junk food,flavor', 'Hershey''s cookie and cream chocolate'),
(19, 'Jagabee', 'Calbee', 3.8, 'Cold Storage', '1.3005677338931318,103.85199808318694', '12-11-2016 02:16:22', 'http://grocerypal-shuwei.rhcloud.com:80/image?name=12112016021621208.jpg', 0, 0, '3b2f13e725b8f04f', 'food,junk food,snack food,meal', '85g. Harvest the Power of Nature.');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_id` int(11) NOT NULL,
  `device_id` varchar(16) NOT NULL,
  `review_time` varchar(50) NOT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`),
  KEY `deal_id_idx` (`deal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
CREATE TABLE IF NOT EXISTS `vote` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(16) NOT NULL,
  `is_like` tinyint(1) NOT NULL,
  `deal_id` int(11) NOT NULL,
  PRIMARY KEY (`vote_id`),
  UNIQUE KEY `vote_id_UNIQUE` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
