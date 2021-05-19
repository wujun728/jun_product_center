/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : platform

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-25 19:24:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `conf_key` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '键',
  `conf_value` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '值',
  `status` int(11) DEFAULT NULL COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `conf_key` (`conf_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('2', 'SMS_CONFIG_KEY', '{\"domain\":\"http://web.cr6868.com/asmx/smsservice.aspx?\",\"name\":\"\",\"pwd\":\"\",\"sign\":\"\",\"type\":1}', '0', '短信配置');
INSERT INTO `sys_config` VALUES ('3', 'CLOUD_STORAGE_CONFIG_KEY', '{\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `dept_level` int(11) DEFAULT NULL COMMENT '级别',
  `status` int(11) DEFAULT '0' COMMENT '状态  0：无效   1：有效',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('01', null, '大平台', '1', '1', '0', null, '2017-11-21 10:00:19', '1', '2017-12-25 17:26:18');
INSERT INTO `sys_dept` VALUES ('0101', '01', '北京分公司', '1', '2', '0', '1', '2017-11-21 11:05:54', '1', '2017-11-21 15:34:31');
INSERT INTO `sys_dept` VALUES ('0102', '01', '上海分公司', '2', '2', '0', '1', '2017-11-21 11:09:28', '1', '2017-11-21 15:34:38');
INSERT INTO `sys_dept` VALUES ('010201', '0102', 'test', '1', '3', '-1', '1', '2017-12-04 19:38:38', null, null);
INSERT INTO `sys_dept` VALUES ('0103', '01', '广州分公司', '3', '2', '-1', '1', '2017-11-21 11:10:19', '1', '2017-11-21 15:34:46');
INSERT INTO `sys_dept` VALUES ('0104', '01', '333', '2', '2', '-1', '1', '2017-11-28 12:57:08', '1', '2017-11-28 12:57:16');
INSERT INTO `sys_dept` VALUES ('0105', '01', '12', '0', '2', '-1', '1', '2017-12-04 19:42:59', '1', '2017-12-04 19:43:05');
INSERT INTO `sys_dept` VALUES ('0106', '01', '合肥分公司', '3', '2', '0', '1', '2017-12-25 17:26:43', '1', '2017-12-25 17:26:52');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `group_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '码值唯一标识, 例如，SEX、SEX_1、SEX_2',
  `dict_key` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '码值的中文表示， 例如：是、否      、性别',
  `dict_value` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '码值 的数字表示，例如：1，2，3。。。。。、sex',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '备注，备用字段',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_domain`
-- ----------------------------
DROP TABLE IF EXISTS `sys_domain`;
CREATE TABLE `sys_domain` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `domain_code` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '域编码',
  `domain_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '域名称',
  `domain_url` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '域地址',
  `domain_status` int(11) DEFAULT NULL COMMENT '状态  0：无效   1：有效',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `icon` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='域对象';

-- ----------------------------
-- Records of sys_domain
-- ----------------------------
INSERT INTO `sys_domain` VALUES ('1r4egj4584gf4a911a66761d6g73918d', 'business', '业务系统', '', '1', '', '2017-11-20 11:34:54', '1', '2017-12-25 19:24:03', '业务系统', 'ivu-icon ivu-icon-android-cart');
INSERT INTO `sys_domain` VALUES ('735e3dd6bb4f4a9191a66761d6g73918', 'platform', '综合管理平台', '', '1', '', '2017-11-20 11:34:54', '1', '2017-11-28 15:41:30', '综合管理平台', 'ivu-icon ivu-icon-android-contacts');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `title` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `operation` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户操作',
  `params` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `domain_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属域',
  `status` int(11) DEFAULT NULL COMMENT '状态   0：无效   1：有效',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('01', '0', '系统管理', null, null, 0, 'fa fa-cog', 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:16:22', null, '2017-11-20 12:16:25');
INSERT INTO `sys_menu` VALUES ('0101', '01', '管理员列表', 'sys/user.html', null, 1, 'fa fa-user', 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:27:16', null, '2017-11-20 12:27:20');
INSERT INTO `sys_menu` VALUES ('010101', '0101', '查看', null, 'sys:user:list,sys:user:info', 2, null, 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:00', null, '2017-11-20 12:38:01');
INSERT INTO `sys_menu` VALUES ('010102', '0101', '新增', null, 'sys:user:save,sys:role:select', 2, null, 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:39', null, '2017-11-20 12:38:41');
INSERT INTO `sys_menu` VALUES ('010103', '0101', '修改', null, 'sys:user:update,sys:role:select', 2, null, 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:26', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('010104', '0101', '刪除', null, 'sys:user:delete', 2, null, 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:27', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('0102', '01', '角色管理', 'sys/role.html', null, 1, 'fa fa-trophy', 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:21:14', '1', '2017-11-21 15:16:03');
INSERT INTO `sys_menu` VALUES ('010201', '0102', '查看', null, 'sys:role:list,sys:role:info', 2, null, 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:00', null, '2017-11-20 12:38:01');
INSERT INTO `sys_menu` VALUES ('010202', '0102', '新增', null, 'sys:role:save,sys:menu:perms', 2, null, 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:39', null, '2017-11-20 12:38:41');
INSERT INTO `sys_menu` VALUES ('010203', '0102', '修改', null, 'sys:role:update,sys:menu:perms', 2, null, 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:26', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('010204', '0102', '刪除', null, 'sys:role:delete', 2, null, 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:27', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('0103', '01', '部门管理', 'sys/dept.html', null, 1, 'fa fa-sitemap', 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:35:13', null, '2017-11-20 12:35:14');
INSERT INTO `sys_menu` VALUES ('010301', '0103', '查看', null, 'sys:dept:list,sys:dept:info', 2, null, 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:00', null, '2017-11-20 12:38:01');
INSERT INTO `sys_menu` VALUES ('010302', '0103', '新增', null, 'sys:dept:save,sys:dept:select', 2, null, 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:39', null, '2017-11-20 12:38:41');
INSERT INTO `sys_menu` VALUES ('010303', '0103', '修改', null, 'sys:dept:update', 2, null, 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:26', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('010304', '0103', '刪除', null, 'sys:dept:delete', 2, null, 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:27', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('0104', '01', '菜单管理', 'sys/menu.html', null, 1, 'fa fa-folder-open', 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:22:19', null, '2017-11-20 12:22:20');
INSERT INTO `sys_menu` VALUES ('010401', '0104', '查看', null, 'sys:menu:list,sys:menu:info', 2, null, 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:00', null, '2017-11-20 12:38:01');
INSERT INTO `sys_menu` VALUES ('010402', '0104', '新增', null, 'sys:menu:save,sys:menu:select', 2, null, 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:39', null, '2017-11-20 12:38:41');
INSERT INTO `sys_menu` VALUES ('010403', '0104', '修改', null, 'sys:menu:update,sys:menu:select', 2, null, 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:26', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('010404', '0104', '刪除', null, 'sys:menu:delete', 2, null, 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:27', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('0105', '01', '应用域管理', 'sys/domain.html', null, 1, 'fa fa-th-large', 5, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 19:10:31', '1', '2017-11-21 15:18:13');
INSERT INTO `sys_menu` VALUES ('010501', '0105', '查看', null, 'sys:domain:list,sys:domain:info', 2, null, 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:00', null, '2017-11-20 12:38:01');
INSERT INTO `sys_menu` VALUES ('010502', '0105', '新增', null, 'sys:domain:save', 2, null, 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:38:39', null, '2017-11-20 12:38:41');
INSERT INTO `sys_menu` VALUES ('010503', '0105', '修改', null, 'sys:domain:update,sys:domain:select', 2, null, 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:26', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('010504', '0105', '刪除', null, 'sys:domain:delete', 2, null, 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:39:27', null, '2017-11-20 12:39:31');
INSERT INTO `sys_menu` VALUES ('0106', '01', '文件上传', 'sys/oss.html', 'sys:oss:all', 1, 'fa fa-cloud-upload', 6, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:33:29', '1', '2017-11-21 15:19:00');
INSERT INTO `sys_menu` VALUES ('0107', '01', '参数管理', 'sys/config.html', null, 1, 'fa fa-cogs', 7, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:03:03', '1', '2017-11-21 15:22:00');
INSERT INTO `sys_menu` VALUES ('010701', '0107', '新增', null, 'sys:config:save', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:04:43', null, null);
INSERT INTO `sys_menu` VALUES ('010702', '0107', '修改', null, 'sys:config:update', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:05:11', null, null);
INSERT INTO `sys_menu` VALUES ('010703', '0107', '删除', null, 'sys:config:delete', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:05:32', null, null);
INSERT INTO `sys_menu` VALUES ('010704', '0107', '查看', null, 'sys:config:list,sys:config:info', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:06:00', null, null);
INSERT INTO `sys_menu` VALUES ('0108', '01', '数据字典', 'sys/dict.html', null, 1, 'fa fa-group', 8, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:17:19', '1', '2017-11-21 15:21:49');
INSERT INTO `sys_menu` VALUES ('010801', '0108', '查看', null, 'sys:dict:list,sys:dict:info', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-25 17:17:33', null, null);
INSERT INTO `sys_menu` VALUES ('010802', '0108', '新增', null, 'sys:dict:save', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-25 17:17:54', null, null);
INSERT INTO `sys_menu` VALUES ('010803', '0108', '修改', null, 'sys:dict:update', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-25 17:18:08', null, null);
INSERT INTO `sys_menu` VALUES ('010804', '0108', '删除', null, 'sys:dict:delete', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-25 17:18:21', null, null);
INSERT INTO `sys_menu` VALUES ('0109', '01', '系统日志', 'sys/log.html', null, 1, 'fa fa-code', 9, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:17:19', '1', '2017-11-21 15:21:49');
INSERT INTO `sys_menu` VALUES ('010901', '0109', '查看', null, 'sys:log:list', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-21 14:18:18', null, null);
INSERT INTO `sys_menu` VALUES ('02', '0', '代码工具', null, null, 0, 'fa fa-coffee', 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:18:36', '1', '2017-11-21 15:15:05');
INSERT INTO `sys_menu` VALUES ('0201', '02', 'SQL监控', 'druid/sql.html', null, 1, 'fa fa-linux', 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:23:55', null, '2017-11-20 12:23:58');
INSERT INTO `sys_menu` VALUES ('0202', '02', '代码生成器', 'sys/generator.html', 'sys:generator:list,sys:generator:code', 1, 'fa fa-rocket', 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:25:30', null, '2017-11-20 12:25:32');
INSERT INTO `sys_menu` VALUES ('0203', '02', 'swagger接口列表', 'swagger-ui.html', null, 1, 'fa fa-code', 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2018-06-18 13:18:51', '1', '2018-06-18 13:19:16');
INSERT INTO `sys_menu` VALUES ('03', '0', '任务管理', null, null, 0, 'fa fa-fire', 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:27:15', null, '2017-11-20 12:27:19');
INSERT INTO `sys_menu` VALUES ('0301', '03', '定时任务', 'sys/schedule.html', null, 1, 'fa fa-tasks', 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:28:45', null, '2017-11-20 12:28:47');
INSERT INTO `sys_menu` VALUES ('030101', '0301', '查看', null, 'sys:schedule:list,sys:schedule:info', 2, null, 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('030102', '0301', '新增', null, 'sys:schedule:save', 2, null, 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('030103', '0301', '修改', null, 'sys:schedule:update', 2, null, 3, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('030104', '0301', '删除', null, 'sys:schedule:delete', 2, null, 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('030105', '0301', '暂停', null, 'sys:schedule:pause', 2, null, 5, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('030106', '0301', '恢复', null, 'sys:schedule:resume', 2, null, 6, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('030107', '0301', '立即执行', null, 'sys:schedule:run', 2, null, 7, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:47:45', null, '2017-11-20 12:47:48');
INSERT INTO `sys_menu` VALUES ('0302', '03', '定时任务日志', 'sys/schedule_log.html', 'sys:schedule:log', 1, 'fa fa-code', 2, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-11-20 12:31:16', '1', '2017-11-21 15:35:45');
INSERT INTO `sys_menu` VALUES ('04', '0', '短信平台', null, null, 0, 'fa fa-television', 4, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-16 23:42:22', '1', '2017-12-17 11:55:56');
INSERT INTO `sys_menu` VALUES ('0401', '04', '短信配置', 'sys/smslog.html', null, 1, 'fa fa-envelope-open', 1, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-16 23:45:35', '1', '2017-12-25 19:13:37');
INSERT INTO `sys_menu` VALUES ('040101', '0401', '查看', null, 'sys:smslog:list,sys:smslog:info', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-16 23:46:10', null, null);
INSERT INTO `sys_menu` VALUES ('040102', '0401', '短信配置', null, 'sys:smslog:saveConfig', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-25 19:17:39', null, null);
INSERT INTO `sys_menu` VALUES ('040103', '0401', '发送短信', null, 'sys:smslog:sendSms', 2, null, 0, '735e3dd6bb4f4a9191a66761d6g73918', 1, '1', '2017-12-25 19:17:59', null, null);
-- ----------------------------
-- Table structure for `sys_oss`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'URL地址',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `dept_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属部门ID',
  `remark` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1acae81674cf4525a2b2eec2e065965c', 'superadmin', '01', 'superadmin', '1', '2017-11-21 13:19:35', '1', '2017-12-25 17:27:20');
INSERT INTO `sys_role` VALUES ('6a9f76de20a944059dca5e2ae90d780d', '测试用户', '0106', '菜单权限测试', '1', '2017-12-25 17:28:44', null, null);

-- ----------------------------
-- Table structure for `sys_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色ID',
  `dept_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('29562912e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '01');
INSERT INTO `sys_role_dept` VALUES ('29562b52e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0101');
INSERT INTO `sys_role_dept` VALUES ('29562bdce95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0102');
INSERT INTO `sys_role_dept` VALUES ('29562c37e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0106');
INSERT INTO `sys_role_dept` VALUES ('5b8ee930e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0106');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2953cba1e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '01');
INSERT INTO `sys_role_menu` VALUES ('2955458ce95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0101');
INSERT INTO `sys_role_menu` VALUES ('295546e3e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010101');
INSERT INTO `sys_role_menu` VALUES ('29554765e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010102');
INSERT INTO `sys_role_menu` VALUES ('295547cce95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010103');
INSERT INTO `sys_role_menu` VALUES ('2955485ee95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010104');
INSERT INTO `sys_role_menu` VALUES ('295548c1e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0102');
INSERT INTO `sys_role_menu` VALUES ('2955492be95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010201');
INSERT INTO `sys_role_menu` VALUES ('29554992e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010202');
INSERT INTO `sys_role_menu` VALUES ('29554a28e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010203');
INSERT INTO `sys_role_menu` VALUES ('29554a73e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010204');
INSERT INTO `sys_role_menu` VALUES ('29554b30e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0103');
INSERT INTO `sys_role_menu` VALUES ('29554b8be95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010301');
INSERT INTO `sys_role_menu` VALUES ('29554beae95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010302');
INSERT INTO `sys_role_menu` VALUES ('29554c49e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010303');
INSERT INTO `sys_role_menu` VALUES ('29554ca3e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010304');
INSERT INTO `sys_role_menu` VALUES ('29554d02e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0104');
INSERT INTO `sys_role_menu` VALUES ('29554d61e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010401');
INSERT INTO `sys_role_menu` VALUES ('29554da4e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010402');
INSERT INTO `sys_role_menu` VALUES ('29554ddbe95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010403');
INSERT INTO `sys_role_menu` VALUES ('29554e13e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010404');
INSERT INTO `sys_role_menu` VALUES ('29554e4ae95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0105');
INSERT INTO `sys_role_menu` VALUES ('29554e7de95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010501');
INSERT INTO `sys_role_menu` VALUES ('29554eb4e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010502');
INSERT INTO `sys_role_menu` VALUES ('29554ee8e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010503');
INSERT INTO `sys_role_menu` VALUES ('29554f1fe95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010504');
INSERT INTO `sys_role_menu` VALUES ('29554f56e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0106');
INSERT INTO `sys_role_menu` VALUES ('29554f92e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0107');
INSERT INTO `sys_role_menu` VALUES ('29554fc5e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010701');
INSERT INTO `sys_role_menu` VALUES ('29554ffce95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010702');
INSERT INTO `sys_role_menu` VALUES ('2955502fe95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010703');
INSERT INTO `sys_role_menu` VALUES ('29555067e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010704');
INSERT INTO `sys_role_menu` VALUES ('29555096e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0108');
INSERT INTO `sys_role_menu` VALUES ('295550c9e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010801');
INSERT INTO `sys_role_menu` VALUES ('29555105e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010802');
INSERT INTO `sys_role_menu` VALUES ('29555138e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010803');
INSERT INTO `sys_role_menu` VALUES ('2955516be95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010804');
INSERT INTO `sys_role_menu` VALUES ('295551a6e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0109');
INSERT INTO `sys_role_menu` VALUES ('295551d6e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '010901');
INSERT INTO `sys_role_menu` VALUES ('29555209e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '02');
INSERT INTO `sys_role_menu` VALUES ('29555244e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0201');
INSERT INTO `sys_role_menu` VALUES ('29555274e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0202');
INSERT INTO `sys_role_menu` VALUES ('295552abe95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '03');
INSERT INTO `sys_role_menu` VALUES ('295552dee95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0301');
INSERT INTO `sys_role_menu` VALUES ('2955530ee95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030101');
INSERT INTO `sys_role_menu` VALUES ('29555341e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030102');
INSERT INTO `sys_role_menu` VALUES ('29555374e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030103');
INSERT INTO `sys_role_menu` VALUES ('295553ace95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030104');
INSERT INTO `sys_role_menu` VALUES ('295553dfe95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030105');
INSERT INTO `sys_role_menu` VALUES ('29555412e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030106');
INSERT INTO `sys_role_menu` VALUES ('29555449e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '030107');
INSERT INTO `sys_role_menu` VALUES ('2955547de95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0302');
INSERT INTO `sys_role_menu` VALUES ('295554b0e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '04');
INSERT INTO `sys_role_menu` VALUES ('295554e3e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '0401');
INSERT INTO `sys_role_menu` VALUES ('29555517e95611e7bdd354ee75866478', '1acae81674cf4525a2b2eec2e065965c', '040101');
INSERT INTO `sys_role_menu` VALUES ('5b8c6004e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '01');
INSERT INTO `sys_role_menu` VALUES ('5b8c6398e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0109');
INSERT INTO `sys_role_menu` VALUES ('5b8c6445e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '010901');
INSERT INTO `sys_role_menu` VALUES ('5b8c64b8e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '02');
INSERT INTO `sys_role_menu` VALUES ('5b8c6522e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0201');
INSERT INTO `sys_role_menu` VALUES ('5b8c6589e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0202');
INSERT INTO `sys_role_menu` VALUES ('5b8c65dce95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '03');
INSERT INTO `sys_role_menu` VALUES ('5b8c6627e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0301');
INSERT INTO `sys_role_menu` VALUES ('5b8c6676e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030101');
INSERT INTO `sys_role_menu` VALUES ('5b8c66bde95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030102');
INSERT INTO `sys_role_menu` VALUES ('5b8c66fce95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030103');
INSERT INTO `sys_role_menu` VALUES ('5b8c673fe95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030104');
INSERT INTO `sys_role_menu` VALUES ('5b8c6786e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030105');
INSERT INTO `sys_role_menu` VALUES ('5b8c67cde95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030106');
INSERT INTO `sys_role_menu` VALUES ('5b8c6810e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '030107');
INSERT INTO `sys_role_menu` VALUES ('5b8c685be95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0302');
INSERT INTO `sys_role_menu` VALUES ('5b8c689fe95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '04');
INSERT INTO `sys_role_menu` VALUES ('5b8c68eae95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '0401');
INSERT INTO `sys_role_menu` VALUES ('5b8c6931e95611e7bdd354ee75866478', '6a9f76de20a944059dca5e2ae90d780d', '040101');

-- ----------------------------
-- Table structure for `sys_sms_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_log`;
CREATE TABLE `sys_sms_log` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人',
  `content` text COLLATE utf8_unicode_ci COMMENT '必填参数。发送内容（1-500 个汉字）UTF-8编码',
  `mobile` text COLLATE utf8_unicode_ci COMMENT '必填参数。手机号码。多个以英文逗号隔开',
  `stime` datetime DEFAULT NULL COMMENT '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送',
  `sign` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '必填参数。用户签名',
  `type` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '必填参数。固定值 pt',
  `extno` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '可选参数。扩展码，用户定义扩展码，只能为数字',
  `send_status` int(11) DEFAULT NULL COMMENT '1成功 0失败',
  `send_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发送编号',
  `invalid_num` int(11) DEFAULT NULL COMMENT '无效号码数',
  `success_num` int(11) DEFAULT NULL COMMENT '成功提交数',
  `black_num` int(11) DEFAULT NULL COMMENT '黑名单数',
  `return_msg` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '返回消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_sms_log
-- ----------------------------
INSERT INTO `sys_sms_log` VALUES ('3a526e31529e4f9683f2377ba4361124', '1', '尊敬的用户，您的验证码是：462457。', '15209831990', '2017-12-18 09:19:49', '【鲜风生活】', 'pt', null, '0', '2017121809180767189495840', '0', '1', '0', '提交成功');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `pass_word` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `mobile` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `dept_id` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属部门ID',
  `qq` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'QQ',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `status` int(11) DEFAULT NULL COMMENT '状态  0：无效   1：有效',
  `create_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '15209831990', '01', '939961241', '939961241@qq.com', '1', '', '2017-11-21 09:56:58', '1', '2017-11-21 13:44:26');
INSERT INTO `sys_user` VALUES ('bafb9ce52a67491e8cdcd801bcfe142c', 'test', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', '15209831990', '0106', null, '939961241@qq.com', '1', '1', '2017-12-25 17:32:58', null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0b886bf7ce7f11e78f3b54ee75866478', '1', '1acae81674cf4525a2b2eec2e065965c');
INSERT INTO `sys_user_role` VALUES ('f2ed0b35e95611e7bdd354ee75866478', 'bafb9ce52a67491e8cdcd801bcfe142c', '6a9f76de20a944059dca5e2ae90d780d');

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('PlatformScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('PlatformScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('PlatformScheduler', 'TASK_1', 'DEFAULT', null, 'com.platform.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720025636F6D2E706C6174666F726D2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000474657374740008706C6174666F726D74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);
INSERT INTO `qrtz_job_details` VALUES ('PlatformScheduler', 'TASK_2', 'DEFAULT', null, 'com.platform.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720025636F6D2E706C6174666F726D2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('PlatformScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('PlatformScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('PlatformScheduler', 'pc1514200713331', '1514201090612', '15000');

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('PlatformScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1507390200000', '-1', '5', 'PAUSED', 'CRON', '1507388787000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720025636F6D2E706C6174666F726D2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000474657374740008706C6174666F726D74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);
INSERT INTO `qrtz_triggers` VALUES ('PlatformScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1507390200000', '-1', '5', 'PAUSED', 'CRON', '1507388787000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720025636F6D2E706C6174666F726D2E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'platform', '0 0/30 * * * ?', '1', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for `schedule_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('3', '3', 'testTask', 'test2', null, '0', null, '2', '2017-11-21 14:01:17');
INSERT INTO `schedule_job_log` VALUES ('4', '1', 'testTask', 'test', 'platform', '0', null, '1036', '2017-11-21 15:12:37');

-- ----------------------------
-- Table structure for `rest_user`
-- ----------------------------
DROP TABLE IF EXISTS `rest_user`;
CREATE TABLE `rest_user` (
  `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `pass_word` varchar(32) DEFAULT NULL COMMENT '密码',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `mobile` varchar(32) NOT NULL COMMENT '手机号',
  `weixin_openid` varchar(200) DEFAULT NULL COMMENT 'weixin_openid',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='会员用户';

DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token` (
  `user_id` bigint(32) NOT NULL COMMENT 'id',
	`token` varchar(100) not null comment 'token',
	`expire_time` datetime null comment '过期时间',
	`update_time` datetime null comment '更新时间',
  constraint token unique (token)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户Token';
