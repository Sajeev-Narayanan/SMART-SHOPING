/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.6.12-log : Database - shopmee_app
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shopmee_app` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `shopmee_app`;

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `bank_id` int(100) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(100) DEFAULT NULL,
  `account_number` varchar(100) DEFAULT NULL,
  `balance` varchar(100) DEFAULT NULL,
  `pin_number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

insert  into `bank`(`bank_id`,`bank_name`,`account_number`,`balance`,`pin_number`) values 
(1,'SBI','1234','1000000','2233');

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_l_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `sellerlid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `cart` */

insert  into `cart`(`cart_id`,`customer_l_id`,`product_id`,`quantity`,`sellerlid`) values 
(1,39,9,'5',26),
(2,39,9,'10',26);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`) values 
(16,'laptops'),
(18,'Dress'),
(19,'Books'),
(20,'Mask'),
(21,'Bottle Arts'),
(22,'Sanitizer');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_l_id` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `complaint` varchar(200) DEFAULT NULL,
  `reply` varchar(200) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`customer_l_id`,`date`,`complaint`,`reply`,`status`) values 
(1,27,'2020-12-12','abcd','hgj','replied'),
(2,NULL,NULL,NULL,NULL,NULL),
(3,NULL,NULL,NULL,NULL,NULL),
(4,27,'2021-05-13','hello','hi','replied'),
(5,39,'2021-05-18','httf','ok','replied'),
(6,39,'2021-05-18','bad app,system hang','pending','pending'),
(7,39,'2021-05-27','poor quality',NULL,'pending'),
(8,39,'2021-05-29','ha ha ha ',NULL,'pending'),
(9,39,'2021-06-01','gshsjsjksksss',NULL,'pending'),
(10,53,'2021-06-01','hdjjdkdkdd','ghghjhghg','replied');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `customer_name` varchar(15) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `email_id` varchar(20) DEFAULT NULL,
  `date_of_birth` varchar(55) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `ph_no` varchar(20) DEFAULT NULL,
  `house_name` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

insert  into `customer`(`customer_name`,`login_id`,`email_id`,`date_of_birth`,`gender`,`ph_no`,`house_name`,`city`,`district`,`state`,`latitude`,`longitude`,`post`,`pincode`,`image`) values 
('robert',27,'rob@gmail.com','1994-01-01','male','8978674534','bloom','trissur','trissur','kerala',27.2046,77.4977,'kolazhi',680001,'/static/customer/1618572668534.png'),
(NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('sandul mk',36,'san@gmail.com','0000-00-00','female','+918301867981','sandul mk','kozhikode','kozhikode','kerala',77,77,'post',754688,'/static/customer/1618572668534.png'),
('sandul mk',37,'san@gmail.com','0000-00-00','female','+916238776224','sandul mk','kozhikode','kozhikode','kerala',66,77,'post',865443,'/static/customer/1618573983702.png'),
('sandul mk',38,'sree@gmail.com','11-12-2000','female','+918301867981','sandul mk','kannur','kannur','kerala',88,77,'post',76544,'/static/customer/1618574093072.png'),
('ashwini',6,'ashwini@gmail.com','12-4-2000','male','+916238776224','ashwini','kozhikode','kozhikode','kerala',77,66,'palazhi',673011,'/static/customer/1618574905122.png'),
('Likhil Riss',53,'likhil@gmail.com','2000-12-12','male','9747360170','Thekkedath','kozhikode','kozhikode','kerala',23.11,11.11,'kk',673611,'/static/customer/1619879019635.png');

/*Table structure for table `damageditems` */

DROP TABLE IF EXISTS `damageditems`;

CREATE TABLE `damageditems` (
  `damagedid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`damagedid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `damageditems` */

insert  into `damageditems`(`damagedid`,`pid`,`qty`,`store_id`) values 
(1,10,45,52),
(2,9,2,52),
(3,9,4,52),
(4,9,48,52);

/*Table structure for table `delivery_boy` */

DROP TABLE IF EXISTS `delivery_boy`;

CREATE TABLE `delivery_boy` (
  `deliveryboy_id` int(11) NOT NULL AUTO_INCREMENT,
  `deliveryboy_name` varchar(50) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `ph_no` varchar(20) DEFAULT NULL,
  `house_name` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `seller_l_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`deliveryboy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `delivery_boy` */

insert  into `delivery_boy`(`deliveryboy_id`,`deliveryboy_name`,`email_id`,`ph_no`,`house_name`,`city`,`district`,`state`,`post`,`pincode`,`image`,`login_id`,`seller_l_id`) values 
(1,'abc','email@123','1234','rty','clt','clt','kl','hyyy',5552,'null',4,7),
(2,'sam','sam@gmai.com','323434','gtt','clt','clt','kl','dee',34335,NULL,3,7),
(3,'ram','ram','ram','ram','ram','ram','ram','ram',0,'/static/deliveryboy/fast-cart.png',20,0),
(4,'ram','ram','ram','ram','ram','ram','ram','ram',0,'/static/deliveryboy/fast-cart.png',21,0),
(5,'Ram','Ram','Ram','Ram','Ram','Ram','Ram','Ram',0,'/static/deliveryboy/fast-cart.png',22,0),
(6,'Ram','Ram','Ram','Ram','Ram','Ram','Ram','Ram',0,'/static/deliveryboy/fast-cart.png',23,0),
(7,'Suresh','sura@gmail.com','9898765342','dreams','calicut','calicut','kerala','nellikode',673012,'/static/deliveryboy/fast-cart.png',24,7),
(8,'Adarsh','adarsh20mk@gmail.com','846732366','Akshaya','Pavangad','kozhikode','Kerala','Puthiyangadi',673021,'/static/deliveryboy/fast-cart.png',25,7),
(9,'Adarsh','adarsh20mk@gmail.com','9846732366','Akshaya','Pavangad','kasargod','Kerala','Puthiyangadi',673021,'/static/deliveryboy/fast-cart.png',28,26),
(13,'rahul','rah@gmail.com','1232434','rahulhouse','calicut','kasargod','kerala','Puthiyangadi',67301,'/static/deliveryboy/fast-cart.png',32,26);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `usertype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin@gmail.com','admin','admin'),
(2,'ashwin@gmail.com','ash','rejected'),
(3,'vivek@gmail.com','1234','rejected'),
(4,'amal@gmail.com','456','rejected'),
(5,'midhun@gmail.com','midhun','seller'),
(6,'som@gmail.com','som','pending'),
(7,'seller@gmail.com','12345','rejected'),
(8,'sandul@gmail.com','7264','seller'),
(9,'sandul@gmail.com','2683','seller'),
(10,'sandul@gmail.com','700','seller'),
(11,'sandul@gmail.com','6846','seller'),
(12,'sandul@gmail.com','9620','seller'),
(13,'sandul@gmail.com','422','seller'),
(14,'sandul@gmail.com','2391','seller'),
(15,'ash@gmail.com','7545','rejected'),
(16,'ash@gmail.com','4890','rejected'),
(17,'sandul@gmail.com','8067','rejected'),
(24,'suresh','suresh','delivery_boy'),
(26,'seller@gmail.com','seller','seller'),
(34,'sandul@gmail.com','397','delivery_boy'),
(39,'user','user','user'),
(40,'mk@gmail.com','16405239','store'),
(41,'mk@gmail.com','44142836','store'),
(42,'mk@gmail.com','44142836','store'),
(43,'mk@gmail.com','73195481','store'),
(44,'mk@gmail.com','12172085','store'),
(45,'mk@gmail.com','18098852','store'),
(46,'mk@gmail.com','95829690','store'),
(47,'mk@gmail.com','55528609','store'),
(48,'mk@gmail.com','83200779','store'),
(49,'mk@gmail.com','65888832','store'),
(50,'mk@gmail.com','26294009','store'),
(51,'mk@gmail.com','78358467','store'),
(52,'ek@gmail.com','123','store'),
(53,'likhil@gmail.com','aa','user');

/*Table structure for table `order_assign` */

DROP TABLE IF EXISTS `order_assign`;

CREATE TABLE `order_assign` (
  `assign_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `deliveryboy_l_id` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `delivery_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`assign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `order_assign` */

insert  into `order_assign`(`assign_id`,`order_id`,`deliveryboy_l_id`,`status`,`delivery_date`) values 
(1,1,1,'pending',NULL),
(2,20,32,'pending','2021-06-01');

/*Table structure for table `order_sub` */

DROP TABLE IF EXISTS `order_sub`;

CREATE TABLE `order_sub` (
  `order_sub_id` int(50) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) DEFAULT NULL,
  `product_id` varchar(50) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

/*Data for the table `order_sub` */

insert  into `order_sub`(`order_sub_id`,`order_id`,`product_id`,`quantity`,`seller_id`) values 
(1,'1','1','4',NULL),
(2,'1','2','5',NULL),
(3,'2','9','1',NULL),
(4,'2','9','6',NULL),
(5,'2','9','1',NULL),
(6,'2','9','6',NULL),
(7,'3','9','6',NULL),
(8,'3','9','1',NULL),
(9,'4','9','4',NULL),
(10,'5','9','2',NULL),
(11,'5','9','5',NULL),
(12,'6','9','5',NULL),
(13,'6','9','5',NULL),
(14,'6','9','5',NULL),
(15,'6','9','5',NULL),
(16,'6','9','5',NULL),
(17,'6','9','5',NULL),
(18,'6','9','5',NULL),
(19,'6','9','5',NULL),
(20,'7','9','1',NULL),
(21,'8','9','1',NULL),
(22,'10','9','1',NULL),
(23,'10','9','1',NULL),
(24,'10','9','1',NULL),
(25,'11','9','3',NULL),
(26,'12','10','4',NULL),
(27,'12','13','5',NULL),
(28,'14','11','10',NULL),
(29,'14','13','5',NULL),
(30,'14','10','3',NULL),
(31,'15','9','5',NULL),
(32,'15','10','1',NULL),
(33,'15','10','1',NULL),
(34,'15','9','5',NULL),
(35,'15','9','3',NULL),
(36,'15','9','3',NULL),
(37,'15','9','3',NULL),
(38,'15','9','3',NULL),
(39,'17','10','3',26),
(40,'17','13','10',26),
(41,'18','10','1',26),
(42,'18','13','3',26),
(43,'19','9','12',26),
(44,'20','13','3',26),
(45,'20','13','3',26),
(46,'24','11','10',26),
(47,'25','13','34',26),
(48,'26','13','34',26);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_l_id` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `total` varchar(70) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`order_id`,`customer_l_id`,`status`,`date`,`total`,`seller_id`) values 
(1,27,'pending','2/22/2021',NULL,26),
(2,27,'pending','2021-05-14','330000',NULL),
(3,27,'pending','2021-05-14','55000',NULL),
(4,27,'pending','2021-05-14','220000',NULL),
(5,27,'pending','2021-05-14','275000',NULL),
(6,27,'pending','2021-05-14','275000',NULL),
(7,27,'pending','2021-05-16','55000',NULL),
(8,39,'pending','2021-05-18','55000',NULL),
(9,39,'pending','2021-05-18','0',NULL),
(10,39,'pending','2021-05-18','55000',NULL),
(11,39,'pending','2021-05-18','165000',NULL),
(12,39,'pending','2021-05-20','12995',NULL),
(13,39,'pending','2021-05-20','0',NULL),
(14,39,'pending','2021-05-28','13155.0',NULL),
(15,39,'pending','2021-05-28','102.0',NULL),
(16,39,'pending','2021-05-28','120.0',NULL),
(17,39,'pending','2021-05-28','26110.0',NULL),
(18,39,'pending','2021-05-28','7837.0',26),
(19,53,'pending','2021-06-01','12.0',26),
(20,53,'pending','2021-06-01','15594.0',26),
(21,53,'pending','2021-06-01','0',26),
(22,53,'pending','2021-06-01','0',26),
(23,53,'pending','2021-06-01','0',26),
(24,53,'pending','2021-06-01','40.0',26),
(25,53,'pending','2021-06-02','88366.0',26),
(26,53,'pending','2021-06-02','88366.0',26);

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`order_id`,`date`,`amount`) values 
(1,1,'2/22/2021','344'),
(2,20,'2021-06-01','15594.0'),
(3,21,'2021-06-01','0'),
(4,22,'2021-06-01','0'),
(5,23,'2021-06-01','0'),
(6,24,'2021-06-01','40.0'),
(7,25,'2021-06-02','88366.0'),
(8,26,'2021-06-02','88366.0');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `pos` int(11) DEFAULT '0',
  `neg` int(11) DEFAULT '0',
  `neu` int(11) DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`category_id`,`seller_id`,`product_name`,`price`,`quantity`,`image`,`description`,`pos`,`neg`,`neu`) values 
(1,2,7,'books','344','1','static/product/fast-cart.png','jejeheh',0,0,0),
(2,6,7,'soap','455','1','static/product/fast-cart.png','jjshsh',0,0,0),
(3,NULL,NULL,NULL,'55','1',NULL,NULL,0,0,0),
(4,26,17,'lenovo','16000','100','static/product/fast-cart.png','RAM - 8\r\nCAMERA - 48MP\r\nSTORAGE - 128GB ',0,0,0),
(5,26,17,'lenovo','16000','100','static/product/fast-cart.png','RAM - 8\r\nCAMERA - 48MP\r\nSTORAGE - 128GB ',0,0,0),
(6,26,16,'lenovo','55000','40','static/product/fast-cart.png','ram',0,0,0),
(7,26,16,'lenovo','55000','40','static/product/fast-cart.png','hdsd',0,0,0),
(8,26,19,'jnjj','8','78','static/product/fast-cart.png','mmmmmmmmmmm',0,0,0),
(9,16,26,'lenovo','1','40','static/product/fast-cart.png','good quality',0,1,0),
(10,16,26,'Medicre Sanitizer','40','50','static/product/WhatsApp Image 2021-04-17 at 3.40.35 PM.jpeg','Kill viuses in 1 second..',0,0,0),
(11,16,26,'Medicre Sanitizer','4','50','static/product/WhatsApp Image 2021-04-17 at 3.40.35 PM.jpeg','Kill viruses in 1 second..',0,0,0),
(12,16,26,'Medicre Sanitizer','4','500','static/product/WhatsApp Image 2021-04-17 at 3.40.35 PM.jpeg','Kill viuses in 1 second..',0,0,0),
(13,20,26,'cotton mask','2599','500','/static/product/n95.jpeg','safe,comfort to wear,adjustable type',3,0,0),
(14,16,5,'s','1000','12','/static/product/Capture 2020-09-03 11.32.25.jpg','sd',0,0,0);

/*Table structure for table `productratings` */

DROP TABLE IF EXISTS `productratings`;

CREATE TABLE `productratings` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `review` varchar(11) DEFAULT NULL,
  `uid` int(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `productratings` */

insert  into `productratings`(`rid`,`pid`,`review`,`uid`,`date`) values 
(1,10,'0',39,'2021-05-28 00:00:00'),
(2,10,'Good Qualit',39,'2021-05-28 00:00:00'),
(3,13,'very good p',39,'2021-05-29 00:00:00'),
(4,9,'gdhjdkdkdkd',53,'2021-06-01 00:00:00'),
(5,13,'xufuvighjjj',53,'2021-06-01 00:00:00'),
(6,13,'very good p',53,'2021-06-01 00:00:00'),
(7,13,'very good p',53,'2021-06-01 00:00:00'),
(8,13,'very good p',53,'2021-06-01 00:00:00'),
(9,13,'very good p',53,'2021-06-01 00:00:00'),
(10,9,'very bad pr',53,'2021-06-01 00:00:00');

/*Table structure for table `productreq` */

DROP TABLE IF EXISTS `productreq`;

CREATE TABLE `productreq` (
  `reqid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`reqid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `productreq` */

insert  into `productreq`(`reqid`,`pid`,`qty`,`lid`,`date`) values 
(1,9,5,52,NULL),
(2,9,2,52,NULL),
(3,10,7,52,'0000-00-00 00:00:00'),
(4,9,4,52,'2021-05-30 00:00:00');

/*Table structure for table `raiting` */

DROP TABLE IF EXISTS `raiting`;

CREATE TABLE `raiting` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_l_id` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `rating` varchar(50) DEFAULT NULL,
  `review` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `raiting` */

insert  into `raiting`(`rating_id`,`customer_l_id`,`date`,`rating`,`review`) values 
(1,6,'11-02-21','good','poli'),
(2,53,'2021-06-01','2.5','uchougvhh');

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `seller_id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_name` varchar(50) DEFAULT NULL,
  `dob` varchar(50) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `ph_no` varchar(20) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `house_name` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `seller` */

insert  into `seller`(`seller_id`,`seller_name`,`dob`,`email_id`,`ph_no`,`login_id`,`house_name`,`city`,`district`,`state`,`longitude`,`latitude`,`image`) values 
(1,'ashwin',NULL,'ash@gmai.com','7790346763',2,'ashwin','calicut','calicut','kerala',NULL,NULL,'static/seller/ss.png'),
(2,'amal',NULL,'amal@gmail.com','985674321',4,'amal','kochi','ernakulam','kerala',NULL,NULL,'static/seller/pic.png'),
(3,'vivek',NULL,'vivek@gmail.com','780876780',3,'viv','calicut','calicut','kerala',NULL,NULL,'static/seller/pic.png'),
(4,'midhun',NULL,'midhun@gmail.com','807876468',5,'midhun','pkd','palakkad','kerala',NULL,NULL,'static/seller/pic.png'),
(5,'som',NULL,'som@gmail.com','978654300',6,'som','kollam','kollam','kerala',NULL,NULL,'static/seller/pic.png'),
(6,'Anuraj',NULL,'sandul@gmail.com','67756436',7,'dreams','kozhikode','kozhikode','kerala',NULL,NULL,'static/seller/fast-cart.png'),
(7,'sandul','2021-04-27','sandul@gmail.com','67756436',14,'s1','kozhikode','kozhikode','kerala','11.2584','75.7853','static/seller/fast-cart.png'),
(8,'Rahul','2021-04-21','ash@gmail.com','987654332',15,'s3','palakkad','palakkad','kerala','11.2485','75.8336','static/seller/fast-cart.png'),
(9,'ash','2021-04-26','ash@gmail.com','987654332',16,'s3','palakkad','palakkad','kerala','11.2595','75.7882','static/seller/fast-cart.png'),
(10,'Akshay','2021-04-30','sandul@gmail.com','987654332',17,'s1','kozhikode','kozhikode','kerala','11.2607','75.7685','static/seller/fast-cart.png'),
(11,'seller','2021-05-13','seller@gmail.com','8467366546',26,'Seller Store','Calicut','kozhikode','kerala','11.2596','75.7883','static/seller/fast-cart.png');

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `store_id` int(100) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(50) DEFAULT NULL,
  `email_id` varchar(25) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `place` varchar(25) DEFAULT NULL,
  `latitude` varchar(25) DEFAULT NULL,
  `longitude` varchar(25) DEFAULT NULL,
  `manager_name` varchar(25) DEFAULT NULL,
  `manager_phone` varchar(25) DEFAULT NULL,
  `manager_image` varchar(200) DEFAULT NULL,
  `login_id` varchar(25) DEFAULT NULL,
  `seller_lid` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `store` */

insert  into `store`(`store_id`,`store_name`,`email_id`,`phone`,`place`,`latitude`,`longitude`,`manager_name`,`manager_phone`,`manager_image`,`login_id`,`seller_lid`) values 
(1,'Mk Stores','mk@gmail.com','9846732366','Mavoor Road','87','77','Vaishnav','9846732282','/static/store/WhatsApp Image 2021-04-01 at 3.17.02 PM (1).jpeg','50','26'),
(2,'Mk Stores','mk@gmail.com','9846732366','Mavoor Road','87','77','Vaishnav','984632282','/static/store/WhatsApp Image 2021-04-01 at 3.17.02 PM (1).jpeg','51','26'),
(3,'Ek store','ek@gmail.com','9846732366','Westhill','87','74','Sudev','845254255','/static/store/WhatsApp Image 2021-04-17 at 3.40.35 PM.jpeg','52','26');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
