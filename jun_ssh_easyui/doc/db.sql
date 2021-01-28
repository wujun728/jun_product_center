DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字段编号',
  `a_userNumber` varchar(11) NOT NULL COMMENT '用户名',
  `a_name` varchar(12) NOT NULL COMMENT '真实姓名',
  `a_password` varchar(12) NOT NULL COMMENT '密码',
  PRIMARY KEY (`a_id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='绠＄悊鍛樿处鍙';
 
LOCK TABLES `admin` WRITE;
INSERT INTO `admin` VALUES (1,'admin','石超','admin'),(11,'admin','admin','admin'),(12,'admin','admin','admin'),(13,'admin','admin','admin'),(14,'admin','admin','admin'),(15,'admin','admin','admin'),(16,'admin','admin','admin'),(17,'sad','admin','admin'),(18,'admin','admin','admin'),(19,'','',''),(20,'','',''),(21,'','','admin'),(22,'','','admin'),(23,'','','admin'),(24,'','','admin'),(25,'','','admin'),(26,'admin','admin','admin'),(27,'admin','admin','admin'),(28,'admin','admin','admin'),(29,'admin','admin','admin'),(30,'admin','admin','admin'),(31,'admin','admin','admin'),(32,'joekr','admin','admin');
UNLOCK TABLES;
  
