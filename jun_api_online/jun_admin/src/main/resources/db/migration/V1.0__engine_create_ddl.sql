
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `app_action` */


# DROP TABLE IF EXISTS `app_config`;

CREATE TABLE `app_config` (
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目Id',
  `app_path` varchar(255) DEFAULT NULL COMMENT '应用目录地址',
  `certificate_path` varchar(255) DEFAULT NULL COMMENT '应用证书配置',
  `create_date` datetime(6) DEFAULT NULL COMMENT '新建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '新建用户',
  `db_address` varchar(255) DEFAULT NULL COMMENT '数据库地址',
  `db_password` varchar(255) DEFAULT NULL COMMENT '数据库密码',
  `db_user` varchar(255) DEFAULT NULL COMMENT '数据库用户',
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ip_address` varchar(255) DEFAULT NULL COMMENT '创建IP地址',
  `repository_address` varchar(255) DEFAULT NULL COMMENT '代码库',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户code',
  `update_date` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '修改用户',
  `db_type` varchar(255) DEFAULT NULL COMMENT '数据库类型',
  `keyword` varchar(255) DEFAULT NULL COMMENT '应用对应关键字',
  `app_id` bigint(20) DEFAULT NULL COMMENT '应用Id',
  `type` varchar(255) DEFAULT NULL COMMENT '数据库类型',
  `defaultdbconfig` varchar(255) DEFAULT NULL,
  `database_create_flag` int(11) NOT NULL COMMENT '是否创建数据库: 0 代表不初始化, 大于0 代表初始化',
  `database_init_flag` int(11) NOT NULL COMMENT '是否初始化数据库: 0 代表不初始化, 大于0 代表初始化',
  `db_admin_create_flag` int(11) NOT NULL COMMENT '是否创建数据库管理员: 0 代表不初始化, 大于0 代表初始化',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_config` (`project_id`),
  UNIQUE KEY `idx_app_id_config` (`app_id`),
  UNIQUE KEY `idx_project_id_config` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
