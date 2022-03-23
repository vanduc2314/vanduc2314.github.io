-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: electronic
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `bproduct`
--

DROP TABLE IF EXISTS `bproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bproduct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `tblBillID` int DEFAULT NULL,
  `tblProductID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tblBillID` (`tblBillID`),
  KEY `tblProductID` (`tblProductID`),
  CONSTRAINT `bproduct_ibfk_1` FOREIGN KEY (`tblBillID`) REFERENCES `tblbill` (`id`),
  CONSTRAINT `bproduct_ibfk_2` FOREIGN KEY (`tblProductID`) REFERENCES `tblproduct` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bproduct`
--

LOCK TABLES `bproduct` WRITE;
/*!40000 ALTER TABLE `bproduct` DISABLE KEYS */;
INSERT INTO `bproduct` VALUES (1,1000.99,2,1,1),(2,900.99,1,2,2),(3,100,1,3,3),(4,655.99,3,4,4),(5,566.99,1,5,5),(6,655.99,1,1,6),(8,1000.99,1,1,5),(9,1000.99,1,3,4),(10,1000.99,1,2,3),(11,1000.99,1,3,1);
/*!40000 ALTER TABLE `bproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sproduct`
--

DROP TABLE IF EXISTS `sproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sproduct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `tblSupplierID` int DEFAULT NULL,
  `tblProductID` int DEFAULT NULL,
  `tblIm_invID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tblSupplierID` (`tblSupplierID`),
  KEY `tblProductID` (`tblProductID`),
  KEY `tblIm_invID` (`tblIm_invID`),
  CONSTRAINT `sproduct_ibfk_1` FOREIGN KEY (`tblSupplierID`) REFERENCES `tblsupplier` (`id`),
  CONSTRAINT `sproduct_ibfk_2` FOREIGN KEY (`tblProductID`) REFERENCES `tblproduct` (`id`),
  CONSTRAINT `sproduct_ibfk_3` FOREIGN KEY (`tblIm_invID`) REFERENCES `tblim_inv` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sproduct`
--

LOCK TABLES `sproduct` WRITE;
/*!40000 ALTER TABLE `sproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `sproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbill`
--

DROP TABLE IF EXISTS `tblbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cdate` datetime DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `discount` double NOT NULL DEFAULT '0',
  `StaffID` int DEFAULT NULL,
  `CustomerID` int DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CustomerID` (`CustomerID`),
  KEY `StaffID` (`StaffID`),
  CONSTRAINT `tblbill_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `tbluser` (`id`),
  CONSTRAINT `tblbill_ibfk_2` FOREIGN KEY (`StaffID`) REFERENCES `tbluser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbill`
--

LOCK TABLES `tblbill` WRITE;
/*!40000 ALTER TABLE `tblbill` DISABLE KEYS */;
INSERT INTO `tblbill` VALUES (1,'2021-11-20 12:10:50','2021-11-20 12:10:50','COD','complete',0,1,2,NULL),(2,'2021-11-22 11:30:24','2021-11-23 11:30:24','COD','order',0,1,1,NULL),(3,'2021-11-24 22:10:12','2021-11-25 22:10:12','Cash','order',0,1,1,NULL),(4,'2021-11-26 13:20:23','2021-11-30 13:20:23','Banking','delivering',0,2,1,NULL),(5,'2021-10-20 09:40:27','2021-10-22 09:40:27','COD','complete',0,2,1,NULL);
/*!40000 ALTER TABLE `tblbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldeli_inv`
--

DROP TABLE IF EXISTS `tbldeli_inv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbldeli_inv` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `address` text NOT NULL,
  `fee` double NOT NULL,
  `ShipperID` int DEFAULT NULL,
  `note` text NOT NULL,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tblbillID` int NOT NULL,
  `status` text NOT NULL,
  `phone` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tblbillID` (`tblbillID`),
  KEY `ShipperID` (`ShipperID`),
  CONSTRAINT `tbldeli_inv_ibfk_2` FOREIGN KEY (`tblbillID`) REFERENCES `tblbill` (`id`),
  CONSTRAINT `tbldeli_inv_ibfk_3` FOREIGN KEY (`ShipperID`) REFERENCES `tbluser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldeli_inv`
--

LOCK TABLES `tbldeli_inv` WRITE;
/*!40000 ALTER TABLE `tbldeli_inv` DISABLE KEYS */;
INSERT INTO `tbldeli_inv` VALUES (2,'Jenny','Ha noi',12,4,'','2021-11-20 10:23:13','2021-11-20 10:23:13',1,'cancel',''),(4,'Pham Van Duc','2972 Westheimer Rd. Santa Ana, Illinois 85486 ',2.99,3,'','2021-11-20 15:31:43','2021-11-20 15:31:43',1,'delivered','(704) 555-0127'),(5,'Viet Tran','So 1 Nguyen Van Loc, Ha Dong, Ha Noi',0,NULL,'','2021-11-20 16:12:25','2021-11-20 16:12:25',2,'','0886460583');
/*!40000 ALTER TABLE `tbldeli_inv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblim_inv`
--

DROP TABLE IF EXISTS `tblim_inv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblim_inv` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cdate` datetime DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `StaffID` int DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `StaffID` (`StaffID`),
  CONSTRAINT `tblim_inv_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `tbluser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblim_inv`
--

LOCK TABLES `tblim_inv` WRITE;
/*!40000 ALTER TABLE `tblim_inv` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblim_inv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproduct`
--

DROP TABLE IF EXISTS `tblproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblproduct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `img` blob,
  `description` text,
  `sku` text NOT NULL,
  `importprice` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproduct`
--

LOCK TABLES `tblproduct` WRITE;
/*!40000 ALTER TABLE `tblproduct` DISABLE KEYS */;
INSERT INTO `tblproduct` VALUES (1,'macbook pro 2021',1023.32,10,'laptop',NULL,NULL,'12312312324',500),(2,'airpods',200.11,100,'headphone',NULL,NULL,'5345345',50),(3,'sony bravia 4k',2343.32,10,'tv',NULL,NULL,'234234',500),(4,'samsung zflip 3',1000.32,10,'smartphone',NULL,NULL,'234234645645',500),(5,'samsung zfold',1200.32,10,'smartphone',NULL,NULL,'76867876',500),(6,'iphone 13 promax',1022.32,10,'smartphone',NULL,NULL,'23232',500);
/*!40000 ALTER TABLE `tblproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsupplier`
--

DROP TABLE IF EXISTS `tblsupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblsupplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsupplier`
--

LOCK TABLES `tblsupplier` WRITE;
/*!40000 ALTER TABLE `tblsupplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblsupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbluser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `point` double NOT NULL DEFAULT '0',
  `position` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser`
--

LOCK TABLES `tbluser` WRITE;
/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` VALUES (1,'Ph&#7841;m V&#259;n &#272;&#7913;c','0123456789','S&#7889; 42, Ngõ 140, Tr&#7847;n Phú','vanduc2314@gmail.com','ducvan','123',0,'inventory staff'),(2,'Eleanor Pena','(505) 222-0125','2972 Westheimer Rd. Santa Ana, Illinois 85486 ','vanduc2314@gmail.com','1223','123',0,'inventory staff'),(3,'Nguyen Que Chi','0322652852','Hà &#272;ông','chichi@gmail.com','chi123','111',0,'shipper'),(4,'Pham Van Anh','0366698522','S&#7889; 42, Ngõ 140, Tr&#7847;n Phú','vanduc2314@gmail.com','duc','123',0,'manager'),(5,'Le Ngoc Minh','0977852365','12 Tran Phu, Ha Dong, Ha Noi','minh123@gmail.com','minh1','123',0,'shipper'),(6,'Nguyen Van Long','0986523147','123 Hoan Kiem, Ha Noi','long111@gmail.com','long1','111',0,'shipper'),(7,'Pham Van Duc','0396995982','42 Tran Phu, Ha Dong, Ha Noi','vanduc2314@gmail.com','ducduc123','123',0,NULL),(8,'D&#432;&#417;ng Hoàng V&#361;','0383251220','69B &#273;&#432;&#7901;ng n&#432;&#7899;c ph&#7847;n lan, T&#7913; Liên, Tây H&#7891;, Hà N&#7897;i','hoangvu.15122000@gmai.com','hv','123',0,NULL);
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'electronic'
--

--
-- Dumping routines for database 'electronic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-19 16:21:00
