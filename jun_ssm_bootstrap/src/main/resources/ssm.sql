/*
SQLyog Ultimate v9.60 
MySQL - 5.5.21 : Database - ssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm`;

/*Table structure for table `sys_buttom` */

DROP TABLE IF EXISTS `sys_buttom`;

CREATE TABLE `sys_buttom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `buttom` varchar(200) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `sys_buttom` */

insert  into `sys_buttom`(`id`,`name`,`buttom`,`description`) values (1,'新增','<button type=\"button\" id=\"addFun\" class=\"btn btn-primary marR10\">新增</button>',''),(2,'编辑','<button type=\"button\" id=\"editFun\" class=\"btn btn-info marR10\">编辑</button>',NULL),(3,'删除','<button type=\"button\" id=\"delFun\" class=\"btn btn-danger marR10\">删除</button>',NULL),(4,'上传','<button type=\"button\" id=\"upLoad\" class=\"btn btn-primary marR10\">上传</button>',NULL),(5,'下载','<button type=\"button\" id=\"downLoad\" class=\"btn btn-primary marR10\">下载</button>',NULL),(6,'上移','<button type=\"button\" id=\"lyGridUp\" class=\"btn btn-success marR10\">上移</button>',NULL),(7,'下移','<button type=\"button\" id=\"lyGridDown\" class=\"btn btn btn-grey marR10\">下移</button>',NULL),(8,'分配权限','<button type=\"button\" id=\"permissions\" class=\"btn btn btn-grey marR10\">分配权限</button>',NULL);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(30) DEFAULT NULL,
  `module` varchar(30) DEFAULT NULL,
  `methods` varchar(30) DEFAULT NULL,
  `actionTime` varchar(30) DEFAULT NULL,
  `userIP` varchar(30) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`accountName`,`module`,`methods`,`actionTime`,`userIP`,`operTime`,`description`) values (135,'admin','系统管理','资源管理-删除资源','22','0:0:0:0:0:0:0:1','2016-02-23 16:53:23','执行成功!'),(136,'admin','系统管理','资源管理-删除资源','25','0:0:0:0:0:0:0:1','2016-02-23 16:53:27','执行成功!'),(137,'admin','系统管理','资源管理-删除资源','17','0:0:0:0:0:0:0:1','2016-02-23 16:53:31','执行成功!'),(138,'admin','系统管理','用户管理-修改用户','12','0:0:0:0:0:0:0:1','2016-02-23 16:53:53','执行成功!'),(139,'admin','系统管理','用户管理/组管理-修改权限','34','0:0:0:0:0:0:0:1','2016-02-26 08:50:05','执行成功!'),(140,'admin','系统管理','用户管理-新增用户','16','0:0:0:0:0:0:0:1','2016-02-26 15:37:23','执行成功!'),(141,'admin','系统管理','用户管理/组管理-修改权限','0','0:0:0:0:0:0:0:1','2016-02-26 15:38:17','执行成功!'),(142,'admin','系统管理','用户管理/组管理-修改权限','18','0:0:0:0:0:0:0:1','2016-02-26 15:42:01','执行成功!');

/*Table structure for table `sys_res_user` */

DROP TABLE IF EXISTS `sys_res_user`;

CREATE TABLE `sys_res_user` (
  `resId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`resId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_res_user` */

insert  into `sys_res_user`(`resId`,`userId`) values (1,1),(2,1),(5,1),(6,1),(7,1),(34,1),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(25,3),(26,3),(27,3),(28,3),(29,3),(30,3),(31,3),(32,3),(33,3),(34,3),(35,3),(1,4),(2,4),(3,4),(4,4),(8,4),(9,4),(10,4),(11,4),(25,4),(26,4),(27,4),(28,4);

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `resKey` varchar(50) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `resUrl` varchar(200) DEFAULT NULL,
  `level` int(4) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `ishide` int(3) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resources` */

insert  into `sys_resources`(`id`,`name`,`parentId`,`resKey`,`type`,`resUrl`,`level`,`icon`,`ishide`,`description`) values (1,'系统基础管理',0,'system','0','system',1,'fa-desktop',0,'系统基础管理'),(2,'用户管理',1,'account','1','/user/list',2,NULL,0,NULL),(3,'角色管理',1,'role','1','/role/list',7,'fa-list',0,'组管理'),(4,'菜单管理',1,'sys_resources','1','/resources/list',12,'fa-list-alt',0,'菜单管理'),(5,'新增用户',2,'account_add','2','/user/addUI',3,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;'),(6,'修改用户',2,'account_edit','2','/user/editUI',4,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;'),(7,'删除用户',2,'account_delete','2','/user/deleteById',5,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delAccount&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;'),(8,'新增角色',3,'role_add','2','/role/addUI',8,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addRole&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;'),(9,'修改角色',3,'role_edit','2','/role/editUI',9,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editRole&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;'),(10,'删除角色',3,'role_delete','2','/role/delete',10,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delRole&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;'),(11,'分配权限',3,'role_perss','2','/resources/permissions',11,'无',0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-grey&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;'),(25,'登陆信息管理',0,'sys_login','0','sys_login',18,'fa-calendar',0,'登陆信息管理'),(26,'用户登录记录',25,'sys_log_list','1','/userlogin/listUI',19,NULL,0,'用户登录记录'),(27,'操作日志管理',0,'sys_log','0','sys_log',20,'fa-picture-o',1,'操作日志管理'),(28,'日志查询',27,'sys_log','1','/log/list',21,NULL,0,NULL),(29,'新增菜单资源',4,'sys_resources_add','2','/resources/addUI',13,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;addFun&quot;&nbsp;class=&quot;btn&nbsp;btn-primary&nbsp;marR10&quot;&gt;新增&lt;/button&gt;'),(30,'修改菜单资源',4,'sys_resources_edit','2','/resources/editUI',14,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;editFun&quot;&nbsp;class=&quot;btn&nbsp;btn-info&nbsp;marR10&quot;&gt;编辑&lt;/button&gt;'),(31,'删除菜单资源',4,'sys_resources_delete','2','/resources/delete',15,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;delFun&quot;&nbsp;class=&quot;btn&nbsp;btn-danger&nbsp;marR10&quot;&gt;删除&lt;/button&gt;'),(34,'分配权限',2,'permissions','2','/resources/permissions',6,NULL,0,'&lt;button&nbsp;type=&quot;button&quot;&nbsp;id=&quot;permissions&quot;&nbsp;class=&quot;btn&nbsp;btn&nbsp;btn-grey&nbsp;marR10&quot;&gt;分配权限&lt;/button&gt;');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `state` varchar(3) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roleKey` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`state`,`name`,`roleKey`,`description`) values (1,'0','管理员','admin','管理员'),(2,'0','普通角色','simple','普通角色'),(3,'0','超级管理员','SUPER','超级管理员');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `credentialsSalt` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `locked` varchar(3) DEFAULT '0',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deletestatus` int(1) DEFAULT '0' COMMENT '逻辑删除状态0:存在1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`userName`,`accountName`,`password`,`credentialsSalt`,`description`,`locked`,`createTime`,`deletestatus`) values (1,'简单','simple','78e21a6eb88529eab722793a448ed394','4157c3feef4a6ed91b2c28cf4392f2d1','0','0','2016-02-23 16:53:53',0),(2,'超级管理员','ROOT','78e21a6eb88529eab722793a448ed394','4157c3feef4a6ed91b2c28cf4392f2d1','0000','1','2015-05-23 17:39:37',0),(3,'管理员','admin','78e21a6eb88529eab722793a448ed394','4157c3feef4a6ed91b2c28cf4392f2d1','3434','1','2015-05-23 17:39:39',0),(4,'熊阅文','xiongyw','900e9dda2dd127c3c2ff2b54ad8abee8','c0be89f436e682a95298380cde273886','普普通通','1','2016-02-26 15:37:23',0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`userId`,`roleId`) values (1,2),(2,3),(3,1),(4,2);

/*Table structure for table `sys_userlogin` */

DROP TABLE IF EXISTS `sys_userlogin`;

CREATE TABLE `sys_userlogin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginIP` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_user_loginlist` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8;

/*Data for the table `sys_userlogin` */

insert  into `sys_userlogin`(`id`,`userId`,`accountName`,`loginTime`,`loginIP`) values (143,3,'admin','2016-02-23 16:45:59','0:0:0:0:0:0:0:1'),(144,3,'admin','2016-02-23 16:52:23','0:0:0:0:0:0:0:1'),(145,3,'admin','2016-02-24 16:27:17','0:0:0:0:0:0:0:1'),(146,3,'admin','2016-02-24 16:31:02','0:0:0:0:0:0:0:1'),(147,3,'admin','2016-02-24 16:36:33','0:0:0:0:0:0:0:1'),(148,3,'admin','2016-02-24 16:42:17','0:0:0:0:0:0:0:1'),(149,3,'admin','2016-02-24 16:42:58','0:0:0:0:0:0:0:1'),(150,3,'admin','2016-02-24 16:47:43','0:0:0:0:0:0:0:1'),(151,3,'admin','2016-02-24 16:49:54','0:0:0:0:0:0:0:1'),(152,3,'admin','2016-02-24 17:03:21','0:0:0:0:0:0:0:1'),(153,3,'admin','2016-02-24 18:49:03','0:0:0:0:0:0:0:1'),(154,3,'admin','2016-02-24 19:10:40','0:0:0:0:0:0:0:1'),(155,3,'admin','2016-02-24 19:13:04','0:0:0:0:0:0:0:1'),(156,3,'admin','2016-02-24 19:55:23','0:0:0:0:0:0:0:1'),(157,3,'admin','2016-02-24 19:57:53','0:0:0:0:0:0:0:1'),(158,3,'admin','2016-02-24 19:59:35','0:0:0:0:0:0:0:1'),(159,3,'admin','2016-02-24 20:04:42','0:0:0:0:0:0:0:1'),(160,3,'admin','2016-02-24 20:15:53','0:0:0:0:0:0:0:1'),(161,3,'admin','2016-02-26 08:29:35','0:0:0:0:0:0:0:1'),(162,3,'admin','2016-02-26 08:48:21','0:0:0:0:0:0:0:1'),(163,3,'admin','2016-02-26 08:49:41','0:0:0:0:0:0:0:1'),(164,3,'admin','2016-02-26 08:52:19','0:0:0:0:0:0:0:1'),(165,3,'admin','2016-02-26 08:52:23','0:0:0:0:0:0:0:1'),(166,3,'admin','2016-02-26 08:52:26','0:0:0:0:0:0:0:1'),(167,3,'admin','2016-02-26 09:03:27','0:0:0:0:0:0:0:1'),(168,3,'admin','2016-02-26 11:34:54','0:0:0:0:0:0:0:1'),(169,3,'admin','2016-02-26 11:39:28','0:0:0:0:0:0:0:1'),(170,3,'admin','2016-02-26 13:31:08','0:0:0:0:0:0:0:1'),(171,3,'admin','2016-02-26 13:35:52','127.0.0.1'),(172,3,'admin','2016-02-26 14:03:37','0:0:0:0:0:0:0:1'),(173,3,'admin','2016-02-26 14:03:50','0:0:0:0:0:0:0:1'),(174,3,'admin','2016-02-26 14:03:58','0:0:0:0:0:0:0:1'),(175,3,'admin','2016-02-26 14:04:10','0:0:0:0:0:0:0:1'),(176,3,'admin','2016-02-26 14:05:23','0:0:0:0:0:0:0:1'),(177,3,'admin','2016-02-26 14:33:45','0:0:0:0:0:0:0:1'),(178,3,'admin','2016-02-26 14:48:11','0:0:0:0:0:0:0:1'),(179,3,'admin','2016-02-26 15:34:29','0:0:0:0:0:0:0:1'),(180,4,'xiongyw','2016-02-26 15:38:41','0:0:0:0:0:0:0:1'),(181,3,'admin','2016-02-26 15:41:02','0:0:0:0:0:0:0:1'),(182,4,'xiongyw','2016-02-26 15:42:25','0:0:0:0:0:0:0:1'),(183,3,'admin','2016-02-26 15:45:48','0:0:0:0:0:0:0:1'),(184,3,'admin','2016-02-26 15:50:27','0:0:0:0:0:0:0:1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
