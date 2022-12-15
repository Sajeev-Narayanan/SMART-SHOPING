/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.7.31 : Database - smart_shopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smart_shopping` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `smart_shopping`;

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `pin` int(11) DEFAULT NULL,
  `acc_no` bigint(20) DEFAULT NULL,
  `balance` bigint(20) DEFAULT NULL,
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bank_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

insert  into `bank`(`pin`,`acc_no`,`balance`,`bank_id`) values 
(123,854702204,9999999400,1);

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `customer_l_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Data for the table `cart` */

/*Table structure for table `catagory` */

DROP TABLE IF EXISTS `catagory`;

CREATE TABLE `catagory` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `catagory` */

insert  into `catagory`(`category_id`,`category_name`) values 
(15,'KITCHEN'),
(14,'ELECTRICAL'),
(13,'KIDS'),
(12,'UTENSILS'),
(11,'CHOCOLATE'),
(16,'vegetables'),
(17,'stationary ');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `complaint` varchar(200) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`complaint`,`user_id`,`date`,`reply`,`status`) values 
(4,'improve interface',30,'2022-04-11','ok','replayed'),
(3,'bad ',30,'2022-03-22','sorry','replayed');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`type`) values 
(1,'admin','admin','admin'),
(29,'lulu','lulu','shop'),
(28,'kenza','kenza','REJECTED'),
(27,'MEGA HYPERMARKET','happy','shop'),
(30,'sajeevnarayanan817@gmail.com','123','user'),
(31,'shiyas store','she@123','shop');

/*Table structure for table `parchase_main` */

DROP TABLE IF EXISTS `parchase_main`;

CREATE TABLE `parchase_main` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

/*Data for the table `parchase_main` */

insert  into `parchase_main`(`purchase_id`,`date`,`user_id`,`total`,`status`) values 
(1,'2022-04-11',30,240,'pending'),
(2,'2022-04-11',30,15,'pending'),
(3,'2022-04-11',30,220,'pending'),
(4,'2022-04-11',30,220,'pending'),
(5,'2022-04-11',30,15,'pending'),
(6,'2022-04-11',30,220,'pending'),
(7,'2022-04-11',30,80,'pending'),
(8,'2022-04-11',30,80,'pending'),
(9,'2022-04-11',30,80,'pending'),
(10,'2022-04-11',30,80,'pending'),
(11,'2022-04-11',30,80,'pending'),
(12,'2022-04-11',30,25,'pending'),
(13,'2022-04-11',30,25,'pending'),
(14,'2022-04-11',30,25,'pending'),
(15,'2022-04-11',30,25,'pending'),
(16,'2022-04-11',30,120,'pending'),
(17,'2022-04-11',30,120,'pending'),
(18,'2022-04-11',30,120,'pending'),
(19,'2022-04-11',30,15,'pending'),
(20,'2022-04-11',30,15,'pending'),
(21,'2022-04-11',30,120,'pending'),
(22,'2022-04-11',30,25,'pending'),
(23,'2022-04-11',30,120,'pending'),
(24,'2022-04-11',30,120,'pending'),
(25,'2022-04-11',30,120,'pending'),
(26,'2022-04-11',30,120,'pending'),
(27,'2022-04-11',30,120,'pending'),
(28,'2022-04-11',30,120,'pending'),
(29,'2022-04-11',30,120,'pending'),
(30,'2022-04-11',30,360,'pending'),
(31,'2022-04-11',30,120,'pending'),
(32,'2022-04-11',30,120,'pending'),
(33,'2022-04-11',30,240,'pending'),
(34,'2022-04-11',30,120,'pending'),
(35,'2022-04-11',30,600,'pending'),
(36,'2022-04-11',30,120,'pending'),
(37,'2022-04-11',30,120,'pending'),
(38,'2022-04-11',30,600,'pending'),
(39,'2022-04-11',30,600,'pending');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_id` int(11) DEFAULT NULL,
  `payment_status` varchar(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `payment_type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`purchase_id`,`payment_status`,`date`,`payment_type`) values 
(3,15,'completed','2022-04-11','online'),
(4,20,'completed','2022-04-11','online'),
(5,21,'completed','2022-04-11','online'),
(6,30,'completed','2022-04-11','online'),
(7,32,'completed','2022-04-11','online'),
(8,33,'completed','2022-04-11','online'),
(9,34,'completed','2022-04-11','online'),
(10,35,'completed','2022-04-11','online'),
(11,36,'completed','2022-04-11','online'),
(12,39,'completed','2022-04-11','online');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `category_id` int(50) DEFAULT NULL,
  `shoplid` int(11) DEFAULT NULL,
  `price` int(20) DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`product_name`,`brand`,`quantity`,`category_id`,`shoplid`,`price`,`image`) values 
(16,'DIARY MILK SILK','CADBURRY','1',11,27,80,'/static/product/Diary Milk.jpg'),
(15,'SPOON','TMT','1',12,27,25,'/static/product/spoon.jpg'),
(13,'SAMBAR POWDER','EASTERN','100',15,27,15,'/static/product/Eastern_Sambar_Powder_.jpg'),
(14,'TOY CAR','HOTWHEELS','100',13,27,110,'/static/product/hotwheel.jpg'),
(12,'bulb','EVERYDAY','1',14,27,130,'/static/product/everyday-led-bulb.jpg'),
(21,'salt','surya','1',15,27,10,'/static/product/salt.jpg'),
(19,'bulb','everyday','1',14,29,120,'/static/product/everyday-led-bulb.jpg'),
(20,'Diary Milk','CADBURRY','1',11,29,120,'/static/product/Diary Milk.jpg');

/*Table structure for table `purchase_sub` */

DROP TABLE IF EXISTS `purchase_sub`;

CREATE TABLE `purchase_sub` (
  `purchasesub_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`purchasesub_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `purchase_sub` */

insert  into `purchase_sub`(`purchasesub_id`,`purchase_id`,`product_id`,`quantity`) values 
(1,1,19,'2'),
(2,2,13,'1'),
(3,3,14,'2'),
(4,4,14,'2'),
(5,5,13,'1'),
(6,6,14,'2'),
(7,11,16,'1'),
(8,12,15,'1'),
(9,13,15,'1'),
(10,14,15,'1'),
(11,15,15,'1'),
(12,20,13,'1'),
(13,21,19,'1'),
(14,26,20,'1'),
(15,27,19,'3'),
(16,28,20,'4'),
(17,29,20,'2'),
(18,30,19,'3'),
(19,31,19,'2'),
(20,32,20,'2'),
(21,33,19,'2'),
(22,34,19,'1'),
(23,35,19,'5'),
(24,36,19,'1'),
(25,39,20,'1'),
(26,39,19,'1');

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `review` varchar(200) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `review` */

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `pin` varchar(50) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `shop` */

insert  into `shop`(`shop_id`,`shop_name`,`email`,`phone`,`place`,`post`,`district`,`pin`,`login_id`) values 
(26,'shiyas store','shiyasstore123@gmail.com','0876544566','malapuram','palakkd','india','87654',31),
(25,'lulu','asdfghjk','2345678','asdfghj','asdfghj','fghj','23456',29),
(24,'kenza','kenza@gmail.com','4321567883','kozhikkode','balusery','kozhikkode','123456',28),
(23,'MEGA HYPERMARKET','MHYPER@gmail.com','23456788','SREEKRISHNAPURAM','THOTTARA','PALAKAD','67514',27),
(22,'kjk','khdiashdo','34567','Ernakulam','guruvayoor po','palakkad','678765',26);

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_quantity` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`stock_id`,`stock_quantity`,`product_id`) values 
(8,10,15),
(7,50,17),
(6,77,16),
(9,20,21),
(10,20,13),
(11,10,14);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `pin` int(11) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`email`,`phone`,`photo`,`place`,`post`,`district`,`pin`,`login_id`) values 
(4,'Sajeev Narayanan','sajeevnarayanan817@gmail.com','+918547022049','/static/customer/1647935072649.png','pkd','pkd','pkd',678583,30);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
