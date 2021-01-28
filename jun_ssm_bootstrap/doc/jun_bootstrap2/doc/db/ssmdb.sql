
-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【数据字典】';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【组织机构代码表】';

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '没有上级NULL',
  `is_show` int(11) DEFAULT '0' COMMENT '0 显示\r\n            1 不显示',
  `weight` int(11) DEFAULT NULL COMMENT '1/2/3/4/...数据越小放在最前面',
  `permission` varchar(50) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【系统菜单表】';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('0', '我的主页', 'index', null, '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('1', '系统管理', '', null, '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('10', '用户管理', 'sys/user/list', '1', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('11', '资源管理', 'sys/resource/list', '1', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('12', '数据字典', 'sys/dict/list', '1', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('2', '测试管理', '', null, '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('20', '测试管理', 'test/test/list', '2', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('3', '演示管理', '', null, '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('30', '演示1', 'demo/demo1/list', '3', '0', null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `sys_resource` VALUES ('31', '演示2', 'demo/demo2/list', '3', '0', null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【角色表】';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` varchar(32) DEFAULT NULL COMMENT '外键：SE_ROLE的主键Id',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '外键：SE_RES的主键Id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【角色资源关联表】';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '0 男 1女',
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `is_lock` int(11) DEFAULT '0' COMMENT '是否锁定 0 非锁定 1 锁定',
  `is_admin` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `org_id` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【用户表】';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('750cc66c84c54ccba3190813107713ce', null, 'admin', '2a75cbf2861918a4d800c1361a2d7734202224c2', '1fcd6496522a69e0', null, null, null, null, '0', '1', null, '2016-05-10 17:46:48', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) DEFAULT NULL COMMENT '外键：SE_USER的主键Id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '外键：SE_ROLE的主键Id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【用户角色关联表】';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【角色表】';

-- ----------------------------
-- Records of t_test
-- ----------------------------
