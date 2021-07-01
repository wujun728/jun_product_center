/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.13_pearproject
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 192.168.0.13:3306
 Source Schema         : pearproject

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 09/03/2021 20:50:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for team_collection
-- ----------------------------
DROP TABLE IF EXISTS `team_collection`;
CREATE TABLE `team_collection`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `source_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '任务ID',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `create_time` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_department
-- ----------------------------
DROP TABLE IF EXISTS `team_department`;
CREATE TABLE `team_department`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `organization_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `pcode` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '上级编号',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '上级路径',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_department
-- ----------------------------
INSERT INTO `team_department` VALUES (1, 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', '研发部', 0, '', NULL, '2020-03-24 15:41:16', '');
INSERT INTO `team_department` VALUES (2, '74818cf1fd624d84bdd8ecccdfdc4cc6', '6v7be19pwman2fird04gqu53', '技术部', 0, '', NULL, '2020-03-24 15:48:03', NULL);
INSERT INTO `team_department` VALUES (3, 'b8dba889327f466fb623e1a89e9c83d8', '2360f2f0f79447f4a2498ae06a9b132d', '项目管理部', 0, '', NULL, '2021-02-01 16:23:57', NULL);
INSERT INTO `team_department` VALUES (4, '900b6cfe182b41de8377fdb9ae40c7ff', '2360f2f0f79447f4a2498ae06a9b132d', '项目调研部', 0, 'b8dba889327f466fb623e1a89e9c83d8', NULL, '2021-02-01 16:24:55', NULL);
INSERT INTO `team_department` VALUES (5, '585c3823b5dd462cbbc8f2b652878d38', '2360f2f0f79447f4a2498ae06a9b132d', '项目调研前期部门', 0, 'b8dba889327f466fb623e1a89e9c83d8', NULL, '2021-02-01 16:25:57', NULL);

-- ----------------------------
-- Table structure for team_department_member
-- ----------------------------
DROP TABLE IF EXISTS `team_department_member`;
CREATE TABLE `team_department_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'id',
  `department_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门id',
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织id',
  `account_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `join_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加入时间',
  `is_principal` tinyint(1) NULL DEFAULT NULL COMMENT '是否负责人',
  `is_owner` tinyint(1) NULL DEFAULT 0 COMMENT '拥有者',
  `authorize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门-成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_department_member
-- ----------------------------
INSERT INTO `team_department_member` VALUES (1, '0e902748c1094461a28c7542d2e7cd87', 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', 'd89ce978017342fdbc99d7dd591582c0', '2020-08-19 11:17:02', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (2, 'a3513ca38bfb40b49b28bdabcd4cc283', 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', '83f18430f0774e54b47d7e90beb1da53', '2020-08-19 11:17:02', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (3, 'edbeb3d2e7d345308fef32a06130f594', 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', 'fb331a85511844fc87d26bae2ebe5220', '2020-08-19 11:17:03', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (4, 'b13f6bdfde9544e4a240e1c4bc8bc652', '74818cf1fd624d84bdd8ecccdfdc4cc6', '6v7be19pwman2fird04gqu53', 'ed03408e4dde4bc2accd481ca597ee0f', '2020-08-19 11:17:10', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (5, '96da80c6e61a4e58b4acb7fcb660d421', '74818cf1fd624d84bdd8ecccdfdc4cc6', '6v7be19pwman2fird04gqu53', 'c9b15bba0746467e9b5fef30bc3b8497', '2020-08-19 11:17:11', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (6, 'e6e65c21babc45bcbbfbf88d4b30f904', '74818cf1fd624d84bdd8ecccdfdc4cc6', '6v7be19pwman2fird04gqu53', 'b4c8c79707784bc285a51deeb629af41', '2020-08-19 11:17:12', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (7, '808498e737dc493abe8b1a457d3970e0', 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', '3bf59606c05a40c9aa75c60c1fd52ee2', '2021-02-01 17:27:30', 0, 0, '116');
INSERT INTO `team_department_member` VALUES (8, '70ebd5866862473fa3711616260cf25d', 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', 'b4c8c79707784bc285a51deeb629af41', '2021-02-01 17:27:38', 0, 0, '116');

-- ----------------------------
-- Table structure for team_file
-- ----------------------------
DROP TABLE IF EXISTS `team_file`;
CREATE TABLE `team_file`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '编号',
  `path_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相对路径',
  `title` char(90) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `extension` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展名',
  `size` mediumint(0) UNSIGNED NULL DEFAULT 0 COMMENT '文件大小',
  `object_type` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型',
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织编码',
  `task_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编码',
  `project_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编码',
  `create_by` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '上传人',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `downloads` mediumint(0) UNSIGNED NULL DEFAULT 0 COMMENT '下载次数',
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '额外信息',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  `file_url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '完整地址',
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `deleted_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_invite_link
-- ----------------------------
DROP TABLE IF EXISTS `team_invite_link`;
CREATE TABLE `team_invite_link`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邀请人',
  `invite_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接类型',
  `source_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源编码',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `over_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邀请链接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_lock
-- ----------------------------
DROP TABLE IF EXISTS `team_lock`;
CREATE TABLE `team_lock`  (
  `pid` bigint(0) UNSIGNED NOT NULL COMMENT 'IP+TYPE',
  `pvalue` tinyint(0) UNSIGNED NOT NULL DEFAULT 1 COMMENT '次数',
  `expiretime` int(0) NOT NULL DEFAULT 0 COMMENT '锁定截止时间',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '防灌水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_mailqueue
-- ----------------------------
DROP TABLE IF EXISTS `team_mailqueue`;
CREATE TABLE `team_mailqueue`  (
  `id` mediumint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `toList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ccList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `body` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `addedBy` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `addedDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'wait',
  `failReason` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sendTime`(`sendTime`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 31858 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件队列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_member
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '系统前台用户表',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户登陆账号',
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登陆密码，32位加密串',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户昵称',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态',
  `last_login_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录时间',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像',
  `idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `province` int(0) NULL DEFAULT 0 COMMENT '省',
  `city` int(0) NULL DEFAULT 0 COMMENT '市',
  `area` int(0) NULL DEFAULT 0 COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在地址',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `dingtalk_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉openid',
  `dingtalk_unionid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉unionid',
  `dingtalk_userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉用户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `username`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 656 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_member
-- ----------------------------
INSERT INTO `team_member` VALUES (1, 'admin', '14e1b600b1fd579f47433b88e8d85291', 'admin', '18681140825', 'juli', NULL, 1, '2020-06-20 17:24:48', '', 'http://localhost:8888/common/image?filePathName=/member/avatar/6v7be19pwman2fird04gqu53/20210202/0e92e563278f48d9a21fb48521a50dae-tomcatgo.jpg&realFileName=tomcatgo.jpg', '', 0, 0, 0, NULL, '', '123@qq.com', '6v7be19pwman2fird04gqu53', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for team_member_account
-- ----------------------------
DROP TABLE IF EXISTS `team_member_account`;
CREATE TABLE `team_member_account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '所属账号id',
  `organization_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '所属组织',
  `department_code` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门编号',
  `authorize` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  `is_owner` tinyint(1) NULL DEFAULT 0 COMMENT '是否主账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `last_login_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_member_account
-- ----------------------------
INSERT INTO `team_member_account` VALUES (1, '6v7be19pwman2fird04gqu11', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', 'axr73ydzo5gemks0pl9qnt16', '3', 1, 'admin', '18681140825', '545522390@qq.com', '2019-01-05 21:57:01', NULL, 1, '描述12', 'http://localhost:8888/common/image?filePathName=/member/avatar/6v7be19pwman2fird04gqu53/20210202/0e92e563278f48d9a21fb48521a50dae-tomcatgo.jpg&realFileName=tomcatgo.jpg', '资深工程师', '研发部');

-- ----------------------------
-- Table structure for team_notify
-- ----------------------------
DROP TABLE IF EXISTS `team_notify`;
CREATE TABLE `team_notify`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` enum('notice','message','task') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知类型。通知：notice，消息：message，待办：task',
  `from` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '发送人id',
  `to` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '送达用户id',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成时间',
  `is_read` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读',
  `read_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阅读时间',
  `send_data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '关联数据',
  `finally_send_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终发送时间',
  `send_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送时间',
  `action` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'none' COMMENT '场景：task，project',
  `terminal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推送终端。pc端：project，移动端：wap',
  `from_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\'project\',\'system\'',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像/图片',
  `source_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '资源code',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4331 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_organization
-- ----------------------------
DROP TABLE IF EXISTS `team_organization`;
CREATE TABLE `team_organization`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `owner_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拥有者',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `personal` tinyint(1) NULL DEFAULT 0 COMMENT '是否个人项目',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '编号',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `province` int(0) NULL DEFAULT 0 COMMENT '省',
  `city` int(0) NULL DEFAULT 0 COMMENT '市',
  `area` int(0) NULL DEFAULT 0 COMMENT '区',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_organization
-- ----------------------------
INSERT INTO `team_organization` VALUES (1, 'admin个人项目', NULL, NULL, '6v7be19pwman2fird04gqu53', '2018-10-12', 1, '6v7be19pwman2fird04gqu53', '', 0, 0, 0);

-- ----------------------------
-- Table structure for team_project
-- ----------------------------
DROP TABLE IF EXISTS `team_project`;
CREATE TABLE `team_project`  (
  `id` mediumint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `access_control_type` enum('open','private','custom') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'open' COMMENT '访问控制l类型',
  `white_list` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可以访问项目的权限组（白名单）',
  `order` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  `template_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目类型',
  `schedule` double(5, 2) NULL DEFAULT 0.00 COMMENT '进度',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织id',
  `deleted_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除时间',
  `private` tinyint(1) NULL DEFAULT 1 COMMENT '是否私有',
  `prefix` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目前缀',
  `open_prefix` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启项目前缀',
  `archive` tinyint(1) NULL DEFAULT 0 COMMENT '是否归档',
  `archive_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归档时间',
  `open_begin_time` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启任务开始时间',
  `open_task_private` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启新任务默认开启隐私模式',
  `task_board_theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default' COMMENT '看板风格',
  `begin_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目开始日期',
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目截止日期',
  `auto_update_schedule` tinyint(1) NULL DEFAULT 0 COMMENT '自动更新项目进度',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `project`(`order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13093 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project
-- ----------------------------
INSERT INTO `team_project` VALUES (1, 'http://easyproject.net/static/image/default/cover.png', '示例项目', '9d0a98c38af34a8c8963870d89bf759a', '示例项目简介', 'open', NULL, 0, 0, 'd85f1bvwpml2nhxe92zu7tyi', 0.00, '2021-03-09 20:49:21', '6v7be19pwman2fird04gqu53', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);

-- ----------------------------
-- Table structure for team_project_auth
-- ----------------------------
DROP TABLE IF EXISTS `team_project_auth`;
CREATE TABLE `team_project_auth`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `status` tinyint(0) UNSIGNED NULL DEFAULT 1 COMMENT '状态(1:禁用,2:启用)',
  `sort` smallint(0) UNSIGNED NULL DEFAULT 0 COMMENT '排序权重',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注说明',
  `create_by` bigint(0) UNSIGNED NULL DEFAULT 0 COMMENT '创建人',
  `create_at` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '所属组织',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目权限表' ROW_FORMAT = Dynamic;

INSERT INTO `team_project_auth` VALUES (1, '管理员', 1, 0, '管理员', 0, '2018-08-01 14:20:46', '', 0, 'admin');
INSERT INTO `team_project_auth` VALUES (2, '成员', 1, 0, '成员', 0, '2018-12-20 13:39:59', '', 1, 'member');
INSERT INTO `team_project_auth` VALUES (3, '管理员', 1, 0, '管理员', 0, '2018-08-01 14:20:46', '6v7be19pwman2fird04gqu53', 0, 'admin');
INSERT INTO `team_project_auth` VALUES (4, '成员', 1, 0, '成员', 0, '2018-12-20 13:39:59', '6v7be19pwman2fird04gqu53', 0, 'member');
-- ----------------------------
-- Table structure for team_project_auth_node
-- ----------------------------
DROP TABLE IF EXISTS `team_project_auth_node`;
CREATE TABLE `team_project_auth_node`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `auth` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '角色ID',
  `node` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点路径',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_system_auth_auth`(`auth`) USING BTREE,
  INDEX `index_system_auth_node`(`node`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20421 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目角色与节点绑定' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_auth_node
-- ----------------------------
INSERT INTO `team_project_auth_node` VALUES (3097, 1, 'project');
INSERT INTO `team_project_auth_node` VALUES (3098, 1, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (3099, 1, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (3100, 1, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (3101, 1, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (3102, 1, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (3103, 1, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (3104, 1, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (3105, 1, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (3106, 1, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (3107, 1, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (3108, 1, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (3109, 1, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (3110, 1, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (3111, 1, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (3112, 1, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (3113, 1, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (3114, 1, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (3115, 1, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (3116, 1, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (3117, 1, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (3118, 1, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (3119, 1, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (3120, 1, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (3121, 1, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (3122, 1, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (3123, 1, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (3124, 1, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (3125, 1, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (3126, 1, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (3127, 1, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (3128, 1, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (3129, 1, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (3130, 1, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (3131, 1, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (3132, 1, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (3133, 1, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (3134, 1, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (3135, 1, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (3136, 1, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (3137, 1, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (3138, 1, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (3139, 1, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (3140, 1, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (3141, 1, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (3142, 1, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (3143, 1, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (3144, 1, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (3145, 1, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (3146, 1, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (3147, 1, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (3148, 1, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (3149, 1, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (3150, 1, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (3151, 1, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (3152, 1, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (3153, 1, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (3154, 1, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (3155, 1, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (3156, 1, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (3157, 1, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (3158, 1, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (3159, 1, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (3160, 1, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (3161, 1, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (3162, 1, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (3163, 1, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (3164, 1, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (3165, 1, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (3166, 1, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (3167, 1, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (3168, 1, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (3169, 1, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (3170, 1, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (3171, 1, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (3172, 1, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (3173, 1, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (3174, 1, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (3175, 1, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (3176, 1, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (3177, 1, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (3178, 1, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (3179, 1, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (3180, 1, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (3181, 1, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (3182, 1, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (3183, 1, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (3184, 1, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (3185, 1, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (3186, 1, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (3187, 1, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (3188, 1, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (3189, 1, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (3190, 1, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (3191, 1, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (3192, 1, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (3193, 1, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (3194, 1, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (3195, 1, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (3196, 1, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (3197, 1, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (3198, 1, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (3199, 1, 'project/task_log');
INSERT INTO `team_project_auth_node` VALUES (3200, 1, 'project/task_log/index');
INSERT INTO `team_project_auth_node` VALUES (3201, 1, 'project/task_log/getlistbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (3202, 1, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (3203, 1, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (3204, 1, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (3205, 1, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (3206, 1, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (3207, 1, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (3208, 1, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (3209, 1, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (3210, 1, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (3211, 1, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (3212, 1, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (3213, 1, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (3214, 1, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (3215, 1, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (3216, 1, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (3217, 1, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (3218, 1, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (3219, 2, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (3220, 2, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (3221, 2, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (3222, 2, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (3223, 2, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (3224, 2, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (3225, 2, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (3226, 2, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (3227, 2, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (3228, 2, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (3229, 2, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (3230, 2, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (3231, 2, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (3232, 2, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (3233, 2, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (3234, 2, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (3235, 2, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (3236, 2, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (3237, 2, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (3238, 2, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (3239, 2, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (3240, 2, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (3241, 2, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (3242, 2, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (3243, 2, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (3244, 2, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (3245, 2, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (3246, 2, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (3247, 2, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (3248, 2, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (3249, 2, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (3250, 2, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (3251, 2, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (3252, 2, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (3253, 2, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (3254, 2, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (3255, 2, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (3256, 2, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (3257, 2, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (3258, 2, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (3259, 2, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (3260, 2, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (3261, 2, 'project/task_log/index');
INSERT INTO `team_project_auth_node` VALUES (3262, 2, 'project/task_log');
INSERT INTO `team_project_auth_node` VALUES (3263, 2, 'project/task_log/getlistbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (3264, 2, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (3265, 2, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (3266, 2, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (3267, 2, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (3268, 2, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (3269, 2, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (3270, 2, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (3271, 2, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (3272, 2, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (3273, 2, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (3274, 2, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (3275, 2, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (3276, 2, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (13588, 4, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (13589, 4, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (13590, 4, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (13591, 4, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (13592, 4, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (13593, 4, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (13594, 4, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (13595, 4, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (13596, 4, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (13597, 4, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (13598, 4, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (13599, 4, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (13600, 4, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (13601, 4, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (13602, 4, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (13603, 4, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (13604, 4, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (13605, 4, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (13606, 4, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (13607, 4, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (13608, 4, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (13609, 4, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (13610, 4, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (13611, 4, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (13612, 4, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (13613, 4, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (13614, 4, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (13615, 4, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13616, 4, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (13617, 4, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13618, 4, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (13619, 4, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (13620, 4, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (13621, 4, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (13622, 4, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (13623, 4, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (13624, 4, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (13625, 4, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (13626, 4, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (13627, 4, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (13628, 4, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (13629, 4, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (13630, 4, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (13631, 4, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (13632, 4, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (13633, 4, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (13634, 4, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (13635, 4, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (13636, 4, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (13637, 4, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (13638, 4, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (13639, 4, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (13640, 4, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (13641, 4, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (13642, 4, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (13643, 4, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (13644, 4, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (13645, 4, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (13646, 4, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (13647, 4, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (13648, 4, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (13649, 4, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (13650, 4, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (13651, 4, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (13652, 4, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (13653, 4, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (13654, 4, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (13655, 4, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (13656, 4, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (13657, 4, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (13658, 4, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (13659, 4, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (13660, 4, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (13661, 4, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (13662, 4, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (13663, 4, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (13664, 4, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (13665, 4, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (13666, 4, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (13667, 4, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (13668, 4, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (13669, 4, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (13670, 4, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (13671, 4, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (13672, 4, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (13673, 4, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (13674, 4, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (13675, 4, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (13676, 4, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (13677, 4, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (13678, 4, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (13679, 4, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (13680, 4, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (13681, 4, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (13682, 4, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (13683, 4, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (13684, 4, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (13685, 4, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (13686, 4, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (13687, 4, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (13688, 4, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (13689, 4, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13690, 4, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (13691, 4, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13692, 4, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (13693, 4, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (13694, 4, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (13695, 4, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (13696, 4, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (13697, 4, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (13698, 4, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (13699, 4, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (13700, 4, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (13701, 4, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (13702, 4, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (13703, 4, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (13704, 4, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (13705, 4, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (13706, 4, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (13707, 4, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (13708, 4, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (13709, 4, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (13710, 4, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (13711, 4, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (13712, 4, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (13713, 4, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (13714, 4, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (13715, 4, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (13716, 4, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (13717, 4, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (13718, 4, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (13719, 4, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (13720, 4, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (13721, 4, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (13722, 4, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (13723, 4, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (13724, 4, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (13725, 4, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (13726, 4, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (13727, 4, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (13728, 4, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (13729, 4, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (13730, 4, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (13731, 4, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (13732, 4, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (13733, 4, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (13734, 4, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (13735, 4, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (13736, 4, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (13737, 4, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (13738, 4, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (13739, 4, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (13740, 4, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (13741, 4, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13742, 4, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (13743, 4, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13744, 4, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (13745, 4, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (13746, 4, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (13747, 4, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (13748, 4, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (13749, 4, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (13750, 4, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (13751, 4, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (13752, 4, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (13753, 4, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (13754, 4, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (13755, 4, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (13756, 4, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (13757, 4, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (13758, 4, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (13759, 4, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (13760, 4, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (13761, 4, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (13762, 4, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (13763, 4, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (13764, 4, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (13765, 4, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (13766, 4, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (13767, 100, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (13768, 100, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (13769, 100, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (13770, 100, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (13771, 100, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (13772, 100, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (13773, 100, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (13774, 100, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (13775, 100, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (13776, 100, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (13777, 100, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (13778, 100, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (13779, 100, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (13780, 100, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (13781, 100, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (13782, 100, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (13783, 100, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (13784, 100, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (13785, 100, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (13786, 100, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (13787, 100, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (13788, 100, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (13789, 100, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (13790, 100, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (13791, 100, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (13792, 100, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (13793, 100, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (13794, 100, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13795, 100, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13796, 100, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (13797, 100, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (13798, 100, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (13799, 100, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (13800, 100, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (13801, 100, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (13802, 100, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (13803, 100, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (13804, 100, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (13805, 100, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (13806, 100, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (13807, 100, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (13808, 100, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (13809, 100, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (13810, 100, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (13811, 100, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (13812, 100, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (13813, 100, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (13814, 100, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (13815, 100, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (13816, 100, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (13817, 100, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (13818, 100, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (13819, 100, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (13820, 100, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (13821, 100, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (13822, 100, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (13823, 100, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (13824, 100, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (13825, 100, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (13826, 100, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (13827, 100, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (13828, 100, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (13829, 100, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (13830, 100, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (13831, 100, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (13832, 100, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (13833, 100, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (13834, 100, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (13835, 100, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (13836, 100, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (13837, 100, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (13838, 100, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (13839, 100, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (13840, 100, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (13841, 100, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (13842, 100, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (13843, 100, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (13844, 100, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (13845, 100, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (13846, 100, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (13847, 100, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (13848, 100, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (13849, 100, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (13850, 100, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (13851, 100, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (13852, 100, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (13853, 100, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (13854, 100, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (13855, 100, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (13856, 100, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (13857, 100, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (13858, 100, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (13859, 100, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (13860, 100, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (13861, 100, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (13862, 100, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (13863, 100, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (13864, 100, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (13865, 100, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13866, 100, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13867, 100, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (13868, 100, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (13869, 100, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (13870, 100, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (13871, 100, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (13872, 100, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (13873, 100, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (13874, 100, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (13875, 100, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (13876, 100, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (13877, 100, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (13878, 100, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (13879, 100, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (13880, 100, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (13881, 100, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (13882, 100, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (13883, 100, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (13884, 100, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (13885, 100, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (13886, 100, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (13887, 100, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (13888, 100, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (13889, 100, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (13890, 100, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (13891, 100, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (13892, 100, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (13893, 100, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (13894, 100, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (13895, 100, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (13896, 100, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (13897, 100, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (13898, 100, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (13899, 100, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (13900, 100, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (13901, 100, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (13902, 100, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (13903, 100, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (13904, 100, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (13905, 100, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (13906, 100, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (13907, 100, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (13908, 100, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (13909, 100, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (13910, 100, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (13911, 100, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (13912, 100, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (13913, 100, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (13914, 100, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (13915, 100, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13916, 100, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13917, 100, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (13918, 100, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (13919, 100, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (13920, 100, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (13921, 100, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (13922, 100, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (13923, 100, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (13924, 100, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (13925, 100, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (13926, 100, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (13927, 100, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (13928, 100, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (13929, 100, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (13930, 100, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (13931, 100, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (13932, 100, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (13933, 100, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (13934, 100, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (13935, 100, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (13936, 100, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (13937, 100, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (13938, 100, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (13939, 100, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (13940, 100, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (13941, 100, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (13942, 100, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (13943, 100, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (13944, 100, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (13945, 100, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (13946, 101, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (13947, 101, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (13948, 101, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (13949, 101, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (13950, 101, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (13951, 101, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (13952, 101, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (13953, 101, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (13954, 101, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (13955, 101, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (13956, 101, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (13957, 101, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (13958, 101, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (13959, 101, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (13960, 101, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (13961, 101, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (13962, 101, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (13963, 101, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (13964, 101, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (13965, 101, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (13966, 101, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (13967, 101, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (13968, 101, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (13969, 101, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (13970, 101, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (13971, 101, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (13972, 101, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (13973, 101, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (13974, 101, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (13975, 101, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (13976, 101, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (13977, 101, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (13978, 101, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (13979, 101, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (13980, 101, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (13981, 101, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (13982, 101, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (13983, 101, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (13984, 101, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (13985, 101, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (13986, 101, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (13987, 101, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (13988, 101, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (13989, 101, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (13990, 101, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (13991, 101, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (13992, 101, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (13993, 101, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (13994, 101, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (13995, 101, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (13996, 101, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (13997, 101, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (13998, 101, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (13999, 101, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (14000, 101, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (14001, 101, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (14002, 101, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (14003, 101, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (14004, 101, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (14005, 101, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (14006, 101, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (14007, 101, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (14008, 101, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (14009, 101, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (14010, 101, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (14011, 101, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (14012, 101, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (14013, 101, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (14014, 101, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (14015, 101, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (14016, 101, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (14017, 101, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (14018, 101, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (14019, 101, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (14020, 101, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (14021, 101, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (14022, 101, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (14023, 101, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (14024, 101, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (14025, 101, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (14026, 101, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (14027, 101, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (14028, 101, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (14029, 101, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (14030, 101, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (14031, 101, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (14032, 101, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (14033, 101, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (14034, 101, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (14035, 101, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (14036, 101, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (14037, 101, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14038, 101, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (14039, 101, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (14040, 101, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (14041, 101, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (14042, 101, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (14043, 101, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (14044, 101, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (14045, 101, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (14046, 101, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (14047, 101, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14048, 101, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14049, 101, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14050, 101, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (14051, 101, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (14052, 101, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14053, 101, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14054, 101, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (14055, 101, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (14056, 101, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14057, 101, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (14058, 101, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (14059, 101, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (14060, 101, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (14061, 101, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (14062, 101, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (14063, 101, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (14064, 101, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (14065, 101, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (14066, 101, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (14067, 101, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (14068, 101, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (14069, 101, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (14070, 101, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (14071, 101, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (14072, 101, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (14073, 101, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (14074, 101, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14075, 101, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (14076, 101, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14077, 101, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (14078, 101, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (14079, 101, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (14080, 101, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (14081, 101, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (14082, 101, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (14083, 101, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (14084, 101, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (14085, 101, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14086, 101, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (14087, 101, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (14088, 101, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (14089, 101, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (14090, 101, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (14091, 101, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (14092, 101, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (14093, 101, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (14094, 101, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (14095, 101, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14096, 101, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (14097, 101, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (14098, 101, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (14099, 101, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14100, 101, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (14101, 101, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14102, 101, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (14103, 101, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (14104, 101, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (14105, 101, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (14106, 101, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (14107, 101, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (14108, 101, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (14109, 101, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (14110, 101, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (14111, 101, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14112, 101, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14113, 101, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (14114, 101, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (14115, 101, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (14116, 101, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (14117, 101, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (14118, 101, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (14119, 101, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (14120, 101, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (14121, 101, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (14122, 101, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (14123, 101, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (14124, 101, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (14125, 102, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (14126, 102, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (14127, 102, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (14128, 102, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (14129, 102, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (14130, 102, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (14131, 102, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (14132, 102, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (14133, 102, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (14134, 102, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (14135, 102, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (14136, 102, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (14137, 102, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (14138, 102, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (14139, 102, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (14140, 102, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (14141, 102, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (14142, 102, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (14143, 102, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (14144, 102, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (14145, 102, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (14146, 102, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (14147, 102, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (14148, 102, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (14149, 102, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (14150, 102, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (14151, 102, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (14152, 102, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14153, 102, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14154, 102, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14155, 102, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14156, 102, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (14157, 102, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (14158, 102, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (14159, 102, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (14160, 102, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (14161, 102, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (14162, 102, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (14163, 102, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (14164, 102, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (14165, 102, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (14166, 102, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (14167, 102, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (14168, 102, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (14169, 102, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (14170, 102, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (14171, 102, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (14172, 102, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (14173, 102, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (14174, 102, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (14175, 102, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (14176, 102, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (14177, 102, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (14178, 102, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (14179, 102, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (14180, 102, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (14181, 102, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (14182, 102, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (14183, 102, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (14184, 102, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (14185, 102, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (14186, 102, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (14187, 102, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (14188, 102, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (14189, 102, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (14190, 102, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (14191, 102, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (14192, 102, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (14193, 102, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (14194, 102, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (14195, 102, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (14196, 102, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (14197, 102, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (14198, 102, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (14199, 102, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (14200, 102, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (14201, 102, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (14202, 102, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (14203, 102, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (14204, 102, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (14205, 102, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (14206, 102, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (14207, 102, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (14208, 102, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14209, 102, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (14210, 102, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (14211, 102, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (14212, 102, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (14213, 102, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (14214, 102, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (14215, 102, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (14216, 102, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (14217, 102, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (14218, 102, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (14219, 102, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (14220, 102, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (14221, 102, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (14222, 102, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (14223, 102, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14224, 102, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14225, 102, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14226, 102, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (14227, 102, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (14228, 102, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (14229, 102, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14230, 102, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14231, 102, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14232, 102, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (14233, 102, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (14234, 102, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (14235, 102, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (14236, 102, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (14237, 102, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (14238, 102, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (14239, 102, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (14240, 102, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (14241, 102, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (14242, 102, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (14243, 102, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (14244, 102, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (14245, 102, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (14246, 102, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (14247, 102, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (14248, 102, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (14249, 102, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (14250, 102, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (14251, 102, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (14252, 102, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (14253, 102, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (14254, 102, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (14255, 102, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (14256, 102, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (14257, 102, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (14258, 102, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (14259, 102, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (14260, 102, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (14261, 102, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (14262, 102, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (14263, 102, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14264, 102, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14265, 102, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14266, 102, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14267, 102, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (14268, 102, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (14269, 102, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (14270, 102, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (14271, 102, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (14272, 102, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (14273, 102, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14274, 102, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14275, 102, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (14276, 102, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (14277, 102, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (14278, 102, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (14279, 102, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (14280, 102, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (14281, 102, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (14282, 102, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (14283, 102, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (14284, 102, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (14285, 102, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (14286, 102, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14287, 102, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14288, 102, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (14289, 102, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (14290, 102, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (14291, 102, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (14292, 102, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (14293, 102, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (14294, 102, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (14295, 102, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (14296, 102, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (14297, 102, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (14298, 102, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (14299, 102, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (14300, 102, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (14301, 102, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (14302, 102, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (14303, 102, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (14304, 103, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (14305, 103, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (14306, 103, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (14307, 103, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (14308, 103, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (14309, 103, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (14310, 103, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (14311, 103, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (14312, 103, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (14313, 103, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (14314, 103, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (14315, 103, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (14316, 103, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (14317, 103, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (14318, 103, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (14319, 103, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (14320, 103, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (14321, 103, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (14322, 103, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (14323, 103, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (14324, 103, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (14325, 103, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (14326, 103, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (14327, 103, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (14328, 103, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (14329, 103, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (14330, 103, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (14331, 103, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14332, 103, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14333, 103, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14334, 103, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14335, 103, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (14336, 103, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (14337, 103, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (14338, 103, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (14339, 103, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (14340, 103, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (14341, 103, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (14342, 103, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (14343, 103, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (14344, 103, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (14345, 103, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (14346, 103, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (14347, 103, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (14348, 103, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (14349, 103, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (14350, 103, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (14351, 103, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (14352, 103, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (14353, 103, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (14354, 103, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (14355, 103, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (14356, 103, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (14357, 103, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (14358, 103, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (14359, 103, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (14360, 103, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (14361, 103, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (14362, 103, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (14363, 103, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (14364, 103, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (14365, 103, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (14366, 103, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (14367, 103, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (14368, 103, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (14369, 103, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (14370, 103, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (14371, 103, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (14372, 103, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (14373, 103, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (14374, 103, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (14375, 103, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (14376, 103, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (14377, 103, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (14378, 103, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (14379, 103, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (14380, 103, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (14381, 103, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (14382, 103, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (14383, 103, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (14384, 103, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (14385, 103, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (14386, 103, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (14387, 103, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (14388, 103, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (14389, 103, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (14390, 103, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (14391, 103, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (14392, 103, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (14393, 103, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (14394, 103, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (14395, 103, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14396, 103, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (14397, 103, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (14398, 103, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (14399, 103, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (14400, 103, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (14401, 103, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (14402, 103, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (14403, 103, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (14404, 103, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (14405, 103, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14406, 103, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14407, 103, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14408, 103, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (14409, 103, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (14410, 103, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14411, 103, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14412, 103, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (14413, 103, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (14414, 103, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14415, 103, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (14416, 103, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (14417, 103, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (14418, 103, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (14419, 103, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (14420, 103, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (14421, 103, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (14422, 103, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (14423, 103, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (14424, 103, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (14425, 103, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (14426, 103, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (14427, 103, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (14428, 103, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (14429, 103, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (14430, 103, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (14431, 103, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (14432, 103, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14433, 103, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (14434, 103, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14435, 103, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (14436, 103, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (14437, 103, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (14438, 103, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (14439, 103, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (14440, 103, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (14441, 103, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (14442, 103, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (14443, 103, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14444, 103, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (14445, 103, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (14446, 103, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (14447, 103, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (14448, 103, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (14449, 103, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (14450, 103, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (14451, 103, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (14452, 103, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (14453, 103, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14454, 103, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (14455, 103, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (14456, 103, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (14457, 103, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14458, 103, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (14459, 103, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14460, 103, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (14461, 103, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (14462, 103, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (14463, 103, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (14464, 103, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (14465, 103, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (14466, 103, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (14467, 103, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (14468, 103, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (14469, 103, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14470, 103, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14471, 103, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (14472, 103, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (14473, 103, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (14474, 103, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (14475, 103, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (14476, 103, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (14477, 103, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (14478, 103, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (14479, 103, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (14480, 103, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (14481, 103, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (14482, 103, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (14483, 104, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (14484, 104, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (14485, 104, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (14486, 104, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (14487, 104, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (14488, 104, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (14489, 104, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (14490, 104, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (14491, 104, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (14492, 104, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (14493, 104, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (14494, 104, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (14495, 104, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (14496, 104, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (14497, 104, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (14498, 104, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (14499, 104, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (14500, 104, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (14501, 104, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (14502, 104, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (14503, 104, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (14504, 104, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (14505, 104, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (14506, 104, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (14507, 104, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (14508, 104, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (14509, 104, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (14510, 104, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14511, 104, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14512, 104, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14513, 104, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14514, 104, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (14515, 104, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (14516, 104, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (14517, 104, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (14518, 104, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (14519, 104, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (14520, 104, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (14521, 104, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (14522, 104, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (14523, 104, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (14524, 104, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (14525, 104, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (14526, 104, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (14527, 104, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (14528, 104, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (14529, 104, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (14530, 104, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (14531, 104, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (14532, 104, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (14533, 104, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (14534, 104, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (14535, 104, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (14536, 104, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (14537, 104, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (14538, 104, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (14539, 104, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (14540, 104, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (14541, 104, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (14542, 104, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (14543, 104, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (14544, 104, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (14545, 104, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (14546, 104, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (14547, 104, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (14548, 104, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (14549, 104, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (14550, 104, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (14551, 104, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (14552, 104, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (14553, 104, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (14554, 104, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (14555, 104, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (14556, 104, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (14557, 104, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (14558, 104, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (14559, 104, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (14560, 104, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (14561, 104, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (14562, 104, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (14563, 104, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (14564, 104, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (14565, 104, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (14566, 104, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14567, 104, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (14568, 104, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (14569, 104, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (14570, 104, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (14571, 104, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (14572, 104, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (14573, 104, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (14574, 104, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (14575, 104, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (14576, 104, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (14577, 104, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (14578, 104, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (14579, 104, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (14580, 104, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (14581, 104, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14582, 104, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14583, 104, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14584, 104, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (14585, 104, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (14586, 104, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (14587, 104, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14588, 104, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14589, 104, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14590, 104, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (14591, 104, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (14592, 104, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (14593, 104, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (14594, 104, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (14595, 104, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (14596, 104, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (14597, 104, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (14598, 104, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (14599, 104, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (14600, 104, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (14601, 104, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (14602, 104, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (14603, 104, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (14604, 104, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (14605, 104, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (14606, 104, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (14607, 104, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (14608, 104, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (14609, 104, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (14610, 104, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (14611, 104, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (14612, 104, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (14613, 104, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (14614, 104, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (14615, 104, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (14616, 104, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (14617, 104, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (14618, 104, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (14619, 104, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (14620, 104, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (14621, 104, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14622, 104, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14623, 104, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14624, 104, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14625, 104, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (14626, 104, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (14627, 104, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (14628, 104, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (14629, 104, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (14630, 104, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (14631, 104, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14632, 104, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14633, 104, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (14634, 104, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (14635, 104, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (14636, 104, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (14637, 104, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (14638, 104, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (14639, 104, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (14640, 104, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (14641, 104, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (14642, 104, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (14643, 104, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (14644, 104, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14645, 104, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14646, 104, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (14647, 104, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (14648, 104, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (14649, 104, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (14650, 104, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (14651, 104, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (14652, 104, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (14653, 104, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (14654, 104, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (14655, 104, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (14656, 104, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (14657, 104, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (14658, 104, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (14659, 104, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (14660, 104, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (14661, 104, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (14662, 105, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (14663, 105, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (14664, 105, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (14665, 105, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (14666, 105, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (14667, 105, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (14668, 105, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (14669, 105, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (14670, 105, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (14671, 105, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (14672, 105, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (14673, 105, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (14674, 105, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (14675, 105, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (14676, 105, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (14677, 105, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (14678, 105, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (14679, 105, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (14680, 105, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (14681, 105, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (14682, 105, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (14683, 105, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (14684, 105, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (14685, 105, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (14686, 105, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (14687, 105, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (14688, 105, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (14689, 105, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14690, 105, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14691, 105, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14692, 105, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14693, 105, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (14694, 105, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (14695, 105, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (14696, 105, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (14697, 105, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (14698, 105, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (14699, 105, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (14700, 105, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (14701, 105, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (14702, 105, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (14703, 105, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (14704, 105, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (14705, 105, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (14706, 105, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (14707, 105, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (14708, 105, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (14709, 105, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (14710, 105, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (14711, 105, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (14712, 105, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (14713, 105, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (14714, 105, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (14715, 105, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (14716, 105, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (14717, 105, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (14718, 105, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (14719, 105, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (14720, 105, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (14721, 105, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (14722, 105, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (14723, 105, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (14724, 105, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (14725, 105, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (14726, 105, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (14727, 105, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (14728, 105, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (14729, 105, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (14730, 105, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (14731, 105, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (14732, 105, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (14733, 105, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (14734, 105, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (14735, 105, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (14736, 105, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (14737, 105, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (14738, 105, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (14739, 105, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (14740, 105, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (14741, 105, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (14742, 105, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (14743, 105, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (14744, 105, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (14745, 105, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (14746, 105, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (14747, 105, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (14748, 105, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (14749, 105, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (14750, 105, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (14751, 105, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (14752, 105, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (14753, 105, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14754, 105, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (14755, 105, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (14756, 105, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (14757, 105, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (14758, 105, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (14759, 105, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (14760, 105, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (14761, 105, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (14762, 105, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (14763, 105, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14764, 105, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14765, 105, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14766, 105, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (14767, 105, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (14768, 105, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14769, 105, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14770, 105, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (14771, 105, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (14772, 105, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14773, 105, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (14774, 105, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (14775, 105, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (14776, 105, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (14777, 105, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (14778, 105, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (14779, 105, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (14780, 105, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (14781, 105, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (14782, 105, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (14783, 105, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (14784, 105, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (14785, 105, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (14786, 105, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (14787, 105, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (14788, 105, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (14789, 105, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (14790, 105, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14791, 105, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (14792, 105, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14793, 105, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (14794, 105, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (14795, 105, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (14796, 105, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (14797, 105, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (14798, 105, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (14799, 105, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (14800, 105, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (14801, 105, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14802, 105, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (14803, 105, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (14804, 105, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (14805, 105, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (14806, 105, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (14807, 105, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (14808, 105, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (14809, 105, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (14810, 105, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (14811, 105, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14812, 105, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (14813, 105, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (14814, 105, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (14815, 105, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14816, 105, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (14817, 105, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14818, 105, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (14819, 105, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (14820, 105, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (14821, 105, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (14822, 105, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (14823, 105, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (14824, 105, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (14825, 105, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (14826, 105, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (14827, 105, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14828, 105, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14829, 105, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (14830, 105, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (14831, 105, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (14832, 105, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (14833, 105, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (14834, 105, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (14835, 105, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (14836, 105, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (14837, 105, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (14838, 105, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (14839, 105, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (14840, 105, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (14841, 106, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (14842, 106, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (14843, 106, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (14844, 106, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (14845, 106, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (14846, 106, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (14847, 106, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (14848, 106, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (14849, 106, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (14850, 106, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (14851, 106, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (14852, 106, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (14853, 106, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (14854, 106, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (14855, 106, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (14856, 106, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (14857, 106, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (14858, 106, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (14859, 106, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (14860, 106, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (14861, 106, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (14862, 106, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (14863, 106, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (14864, 106, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (14865, 106, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (14866, 106, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (14867, 106, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (14868, 106, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14869, 106, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14870, 106, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14871, 106, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14872, 106, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (14873, 106, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (14874, 106, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (14875, 106, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (14876, 106, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (14877, 106, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (14878, 106, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (14879, 106, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (14880, 106, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (14881, 106, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (14882, 106, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (14883, 106, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (14884, 106, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (14885, 106, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (14886, 106, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (14887, 106, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (14888, 106, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (14889, 106, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (14890, 106, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (14891, 106, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (14892, 106, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (14893, 106, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (14894, 106, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (14895, 106, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (14896, 106, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (14897, 106, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (14898, 106, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (14899, 106, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (14900, 106, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (14901, 106, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (14902, 106, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (14903, 106, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (14904, 106, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (14905, 106, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (14906, 106, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (14907, 106, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (14908, 106, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (14909, 106, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (14910, 106, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (14911, 106, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (14912, 106, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (14913, 106, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (14914, 106, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (14915, 106, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (14916, 106, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (14917, 106, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (14918, 106, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (14919, 106, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (14920, 106, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (14921, 106, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (14922, 106, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (14923, 106, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (14924, 106, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14925, 106, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (14926, 106, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (14927, 106, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (14928, 106, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (14929, 106, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (14930, 106, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (14931, 106, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (14932, 106, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (14933, 106, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (14934, 106, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (14935, 106, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (14936, 106, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (14937, 106, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (14938, 106, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (14939, 106, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14940, 106, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14941, 106, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (14942, 106, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (14943, 106, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (14944, 106, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (14945, 106, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (14946, 106, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (14947, 106, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (14948, 106, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (14949, 106, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (14950, 106, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (14951, 106, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (14952, 106, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (14953, 106, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (14954, 106, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (14955, 106, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (14956, 106, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (14957, 106, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (14958, 106, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (14959, 106, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (14960, 106, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (14961, 106, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (14962, 106, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (14963, 106, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (14964, 106, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (14965, 106, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (14966, 106, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (14967, 106, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (14968, 106, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (14969, 106, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (14970, 106, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (14971, 106, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (14972, 106, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (14973, 106, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (14974, 106, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (14975, 106, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (14976, 106, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (14977, 106, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (14978, 106, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (14979, 106, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14980, 106, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14981, 106, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (14982, 106, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (14983, 106, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (14984, 106, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (14985, 106, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (14986, 106, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (14987, 106, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (14988, 106, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (14989, 106, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (14990, 106, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (14991, 106, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (14992, 106, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (14993, 106, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (14994, 106, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (14995, 106, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (14996, 106, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (14997, 106, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (14998, 106, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (14999, 106, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (15000, 106, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (15001, 106, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (15002, 106, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15003, 106, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15004, 106, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (15005, 106, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (15006, 106, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (15007, 106, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (15008, 106, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (15009, 106, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (15010, 106, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (15011, 106, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (15012, 106, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (15013, 106, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (15014, 106, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (15015, 106, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (15016, 106, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (15017, 106, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (15018, 106, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (15019, 106, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (15020, 107, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (15021, 107, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (15022, 107, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (15023, 107, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (15024, 107, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (15025, 107, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (15026, 107, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (15027, 107, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (15028, 107, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (15029, 107, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (15030, 107, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (15031, 107, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (15032, 107, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (15033, 107, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (15034, 107, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (15035, 107, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (15036, 107, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (15037, 107, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (15038, 107, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (15039, 107, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (15040, 107, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (15041, 107, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (15042, 107, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (15043, 107, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (15044, 107, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (15045, 107, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (15046, 107, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (15047, 107, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15048, 107, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15049, 107, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15050, 107, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15051, 107, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (15052, 107, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (15053, 107, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (15054, 107, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (15055, 107, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (15056, 107, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (15057, 107, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (15058, 107, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (15059, 107, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (15060, 107, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (15061, 107, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (15062, 107, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (15063, 107, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (15064, 107, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (15065, 107, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (15066, 107, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (15067, 107, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (15068, 107, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (15069, 107, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (15070, 107, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (15071, 107, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (15072, 107, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (15073, 107, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (15074, 107, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (15075, 107, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (15076, 107, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (15077, 107, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (15078, 107, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (15079, 107, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (15080, 107, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (15081, 107, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (15082, 107, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (15083, 107, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (15084, 107, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (15085, 107, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (15086, 107, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (15087, 107, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (15088, 107, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (15089, 107, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (15090, 107, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (15091, 107, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (15092, 107, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (15093, 107, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (15094, 107, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (15095, 107, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (15096, 107, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (15097, 107, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (15098, 107, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (15099, 107, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (15100, 107, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (15101, 107, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (15102, 107, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (15103, 107, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (15104, 107, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (15105, 107, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (15106, 107, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (15107, 107, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (15108, 107, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (15109, 107, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (15110, 107, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (15111, 107, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15112, 107, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (15113, 107, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (15114, 107, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (15115, 107, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (15116, 107, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (15117, 107, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (15118, 107, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (15119, 107, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (15120, 107, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (15121, 107, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15122, 107, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15123, 107, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15124, 107, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (15125, 107, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (15126, 107, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15127, 107, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15128, 107, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (15129, 107, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (15130, 107, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15131, 107, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (15132, 107, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (15133, 107, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (15134, 107, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (15135, 107, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (15136, 107, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (15137, 107, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (15138, 107, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (15139, 107, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (15140, 107, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (15141, 107, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (15142, 107, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (15143, 107, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (15144, 107, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (15145, 107, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (15146, 107, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (15147, 107, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (15148, 107, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15149, 107, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (15150, 107, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15151, 107, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (15152, 107, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (15153, 107, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (15154, 107, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (15155, 107, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (15156, 107, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (15157, 107, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (15158, 107, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (15159, 107, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15160, 107, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (15161, 107, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (15162, 107, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (15163, 107, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (15164, 107, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (15165, 107, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (15166, 107, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (15167, 107, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (15168, 107, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (15169, 107, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15170, 107, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (15171, 107, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (15172, 107, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (15173, 107, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15174, 107, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (15175, 107, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15176, 107, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (15177, 107, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (15178, 107, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (15179, 107, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (15180, 107, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (15181, 107, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (15182, 107, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (15183, 107, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (15184, 107, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (15185, 107, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15186, 107, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15187, 107, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (15188, 107, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (15189, 107, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (15190, 107, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (15191, 107, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (15192, 107, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (15193, 107, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (15194, 107, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (15195, 107, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (15196, 107, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (15197, 107, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (15198, 107, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (15199, 108, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (15200, 108, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (15201, 108, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (15202, 108, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (15203, 108, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (15204, 108, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (15205, 108, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (15206, 108, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (15207, 108, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (15208, 108, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (15209, 108, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (15210, 108, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (15211, 108, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (15212, 108, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (15213, 108, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (15214, 108, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (15215, 108, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (15216, 108, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (15217, 108, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (15218, 108, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (15219, 108, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (15220, 108, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (15221, 108, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (15222, 108, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (15223, 108, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (15224, 108, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (15225, 108, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (15226, 108, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15227, 108, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15228, 108, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15229, 108, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15230, 108, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (15231, 108, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (15232, 108, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (15233, 108, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (15234, 108, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (15235, 108, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (15236, 108, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (15237, 108, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (15238, 108, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (15239, 108, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (15240, 108, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (15241, 108, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (15242, 108, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (15243, 108, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (15244, 108, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (15245, 108, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (15246, 108, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (15247, 108, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (15248, 108, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (15249, 108, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (15250, 108, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (15251, 108, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (15252, 108, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (15253, 108, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (15254, 108, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (15255, 108, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (15256, 108, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (15257, 108, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (15258, 108, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (15259, 108, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (15260, 108, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (15261, 108, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (15262, 108, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (15263, 108, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (15264, 108, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (15265, 108, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (15266, 108, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (15267, 108, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (15268, 108, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (15269, 108, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (15270, 108, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (15271, 108, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (15272, 108, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (15273, 108, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (15274, 108, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (15275, 108, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (15276, 108, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (15277, 108, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (15278, 108, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (15279, 108, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (15280, 108, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (15281, 108, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (15282, 108, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15283, 108, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (15284, 108, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (15285, 108, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (15286, 108, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (15287, 108, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (15288, 108, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (15289, 108, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (15290, 108, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (15291, 108, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (15292, 108, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (15293, 108, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (15294, 108, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (15295, 108, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (15296, 108, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (15297, 108, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15298, 108, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15299, 108, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15300, 108, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (15301, 108, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (15302, 108, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (15303, 108, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15304, 108, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15305, 108, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15306, 108, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (15307, 108, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (15308, 108, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (15309, 108, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (15310, 108, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (15311, 108, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (15312, 108, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (15313, 108, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (15314, 108, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (15315, 108, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (15316, 108, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (15317, 108, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (15318, 108, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (15319, 108, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (15320, 108, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (15321, 108, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (15322, 108, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (15323, 108, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (15324, 108, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (15325, 108, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (15326, 108, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (15327, 108, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (15328, 108, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (15329, 108, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (15330, 108, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (15331, 108, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (15332, 108, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (15333, 108, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (15334, 108, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (15335, 108, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (15336, 108, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (15337, 108, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15338, 108, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15339, 108, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15340, 108, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15341, 108, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (15342, 108, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (15343, 108, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (15344, 108, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (15345, 108, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (15346, 108, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (15347, 108, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15348, 108, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15349, 108, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (15350, 108, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (15351, 108, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (15352, 108, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (15353, 108, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (15354, 108, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (15355, 108, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (15356, 108, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (15357, 108, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (15358, 108, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (15359, 108, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (15360, 108, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15361, 108, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15362, 108, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (15363, 108, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (15364, 108, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (15365, 108, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (15366, 108, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (15367, 108, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (15368, 108, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (15369, 108, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (15370, 108, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (15371, 108, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (15372, 108, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (15373, 108, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (15374, 108, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (15375, 108, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (15376, 108, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (15377, 108, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (15378, 109, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (15379, 109, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (15380, 109, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (15381, 109, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (15382, 109, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (15383, 109, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (15384, 109, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (15385, 109, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (15386, 109, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (15387, 109, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (15388, 109, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (15389, 109, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (15390, 109, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (15391, 109, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (15392, 109, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (15393, 109, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (15394, 109, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (15395, 109, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (15396, 109, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (15397, 109, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (15398, 109, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (15399, 109, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (15400, 109, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (15401, 109, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (15402, 109, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (15403, 109, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (15404, 109, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (15405, 109, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15406, 109, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15407, 109, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15408, 109, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15409, 109, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (15410, 109, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (15411, 109, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (15412, 109, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (15413, 109, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (15414, 109, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (15415, 109, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (15416, 109, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (15417, 109, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (15418, 109, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (15419, 109, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (15420, 109, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (15421, 109, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (15422, 109, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (15423, 109, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (15424, 109, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (15425, 109, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (15426, 109, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (15427, 109, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (15428, 109, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (15429, 109, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (15430, 109, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (15431, 109, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (15432, 109, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (15433, 109, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (15434, 109, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (15435, 109, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (15436, 109, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (15437, 109, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (15438, 109, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (15439, 109, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (15440, 109, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (15441, 109, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (15442, 109, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (15443, 109, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (15444, 109, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (15445, 109, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (15446, 109, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (15447, 109, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (15448, 109, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (15449, 109, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (15450, 109, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (15451, 109, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (15452, 109, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (15453, 109, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (15454, 109, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (15455, 109, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (15456, 109, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (15457, 109, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (15458, 109, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (15459, 109, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (15460, 109, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (15461, 109, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (15462, 109, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (15463, 109, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (15464, 109, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (15465, 109, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (15466, 109, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (15467, 109, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (15468, 109, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (15469, 109, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15470, 109, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (15471, 109, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (15472, 109, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (15473, 109, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (15474, 109, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (15475, 109, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (15476, 109, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (15477, 109, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (15478, 109, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (15479, 109, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15480, 109, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15481, 109, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15482, 109, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (15483, 109, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (15484, 109, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15485, 109, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15486, 109, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (15487, 109, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (15488, 109, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15489, 109, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (15490, 109, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (15491, 109, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (15492, 109, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (15493, 109, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (15494, 109, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (15495, 109, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (15496, 109, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (15497, 109, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (15498, 109, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (15499, 109, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (15500, 109, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (15501, 109, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (15502, 109, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (15503, 109, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (15504, 109, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (15505, 109, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (15506, 109, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15507, 109, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (15508, 109, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15509, 109, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (15510, 109, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (15511, 109, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (15512, 109, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (15513, 109, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (15514, 109, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (15515, 109, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (15516, 109, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (15517, 109, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15518, 109, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (15519, 109, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (15520, 109, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (15521, 109, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (15522, 109, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (15523, 109, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (15524, 109, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (15525, 109, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (15526, 109, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (15527, 109, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15528, 109, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (15529, 109, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (15530, 109, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (15531, 109, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15532, 109, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (15533, 109, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15534, 109, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (15535, 109, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (15536, 109, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (15537, 109, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (15538, 109, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (15539, 109, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (15540, 109, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (15541, 109, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (15542, 109, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (15543, 109, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15544, 109, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15545, 109, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (15546, 109, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (15547, 109, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (15548, 109, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (15549, 109, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (15550, 109, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (15551, 109, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (15552, 109, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (15553, 109, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (15554, 109, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (15555, 109, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (15556, 109, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (15557, 110, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (15558, 110, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (15559, 110, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (15560, 110, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (15561, 110, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (15562, 110, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (15563, 110, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (15564, 110, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (15565, 110, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (15566, 110, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (15567, 110, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (15568, 110, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (15569, 110, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (15570, 110, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (15571, 110, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (15572, 110, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (15573, 110, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (15574, 110, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (15575, 110, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (15576, 110, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (15577, 110, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (15578, 110, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (15579, 110, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (15580, 110, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (15581, 110, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (15582, 110, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (15583, 110, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (15584, 110, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15585, 110, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15586, 110, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15587, 110, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15588, 110, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (15589, 110, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (15590, 110, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (15591, 110, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (15592, 110, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (15593, 110, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (15594, 110, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (15595, 110, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (15596, 110, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (15597, 110, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (15598, 110, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (15599, 110, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (15600, 110, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (15601, 110, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (15602, 110, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (15603, 110, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (15604, 110, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (15605, 110, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (15606, 110, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (15607, 110, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (15608, 110, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (15609, 110, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (15610, 110, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (15611, 110, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (15612, 110, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (15613, 110, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (15614, 110, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (15615, 110, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (15616, 110, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (15617, 110, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (15618, 110, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (15619, 110, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (15620, 110, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (15621, 110, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (15622, 110, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (15623, 110, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (15624, 110, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (15625, 110, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (15626, 110, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (15627, 110, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (15628, 110, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (15629, 110, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (15630, 110, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (15631, 110, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (15632, 110, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (15633, 110, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (15634, 110, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (15635, 110, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (15636, 110, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (15637, 110, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (15638, 110, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (15639, 110, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (15640, 110, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15641, 110, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (15642, 110, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (15643, 110, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (15644, 110, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (15645, 110, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (15646, 110, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (15647, 110, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (15648, 110, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (15649, 110, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (15650, 110, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (15651, 110, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (15652, 110, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (15653, 110, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (15654, 110, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (15655, 110, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15656, 110, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15657, 110, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15658, 110, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (15659, 110, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (15660, 110, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (15661, 110, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15662, 110, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15663, 110, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15664, 110, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (15665, 110, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (15666, 110, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (15667, 110, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (15668, 110, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (15669, 110, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (15670, 110, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (15671, 110, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (15672, 110, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (15673, 110, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (15674, 110, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (15675, 110, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (15676, 110, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (15677, 110, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (15678, 110, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (15679, 110, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (15680, 110, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (15681, 110, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (15682, 110, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (15683, 110, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (15684, 110, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (15685, 110, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (15686, 110, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (15687, 110, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (15688, 110, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (15689, 110, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (15690, 110, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (15691, 110, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (15692, 110, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (15693, 110, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (15694, 110, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (15695, 110, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15696, 110, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15697, 110, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15698, 110, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15699, 110, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (15700, 110, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (15701, 110, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (15702, 110, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (15703, 110, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (15704, 110, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (15705, 110, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15706, 110, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15707, 110, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (15708, 110, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (15709, 110, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (15710, 110, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (15711, 110, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (15712, 110, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (15713, 110, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (15714, 110, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (15715, 110, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (15716, 110, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (15717, 110, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (15718, 110, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15719, 110, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15720, 110, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (15721, 110, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (15722, 110, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (15723, 110, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (15724, 110, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (15725, 110, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (15726, 110, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (15727, 110, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (15728, 110, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (15729, 110, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (15730, 110, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (15731, 110, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (15732, 110, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (15733, 110, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (15734, 110, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (15735, 110, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (15736, 111, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (15737, 111, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (15738, 111, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (15739, 111, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (15740, 111, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (15741, 111, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (15742, 111, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (15743, 111, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (15744, 111, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (15745, 111, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (15746, 111, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (15747, 111, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (15748, 111, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (15749, 111, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (15750, 111, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (15751, 111, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (15752, 111, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (15753, 111, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (15754, 111, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (15755, 111, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (15756, 111, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (15757, 111, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (15758, 111, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (15759, 111, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (15760, 111, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (15761, 111, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (15762, 111, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (15763, 111, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15764, 111, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15765, 111, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15766, 111, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15767, 111, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (15768, 111, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (15769, 111, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (15770, 111, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (15771, 111, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (15772, 111, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (15773, 111, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (15774, 111, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (15775, 111, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (15776, 111, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (15777, 111, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (15778, 111, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (15779, 111, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (15780, 111, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (15781, 111, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (15782, 111, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (15783, 111, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (15784, 111, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (15785, 111, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (15786, 111, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (15787, 111, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (15788, 111, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (15789, 111, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (15790, 111, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (15791, 111, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (15792, 111, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (15793, 111, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (15794, 111, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (15795, 111, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (15796, 111, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (15797, 111, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (15798, 111, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (15799, 111, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (15800, 111, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (15801, 111, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (15802, 111, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (15803, 111, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (15804, 111, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (15805, 111, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (15806, 111, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (15807, 111, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (15808, 111, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (15809, 111, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (15810, 111, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (15811, 111, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (15812, 111, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (15813, 111, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (15814, 111, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (15815, 111, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (15816, 111, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (15817, 111, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (15818, 111, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (15819, 111, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (15820, 111, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (15821, 111, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (15822, 111, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (15823, 111, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (15824, 111, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (15825, 111, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (15826, 111, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (15827, 111, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15828, 111, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (15829, 111, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (15830, 111, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (15831, 111, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (15832, 111, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (15833, 111, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (15834, 111, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (15835, 111, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (15836, 111, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (15837, 111, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15838, 111, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15839, 111, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15840, 111, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (15841, 111, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (15842, 111, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15843, 111, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15844, 111, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (15845, 111, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (15846, 111, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15847, 111, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (15848, 111, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (15849, 111, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (15850, 111, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (15851, 111, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (15852, 111, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (15853, 111, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (15854, 111, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (15855, 111, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (15856, 111, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (15857, 111, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (15858, 111, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (15859, 111, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (15860, 111, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (15861, 111, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (15862, 111, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (15863, 111, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (15864, 111, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15865, 111, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (15866, 111, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15867, 111, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (15868, 111, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (15869, 111, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (15870, 111, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (15871, 111, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (15872, 111, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (15873, 111, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (15874, 111, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (15875, 111, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (15876, 111, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (15877, 111, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (15878, 111, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (15879, 111, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (15880, 111, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (15881, 111, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (15882, 111, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (15883, 111, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (15884, 111, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (15885, 111, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15886, 111, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (15887, 111, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (15888, 111, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (15889, 111, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15890, 111, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (15891, 111, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15892, 111, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (15893, 111, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (15894, 111, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (15895, 111, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (15896, 111, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (15897, 111, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (15898, 111, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (15899, 111, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (15900, 111, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (15901, 111, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (15902, 111, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (15903, 111, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (15904, 111, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (15905, 111, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (15906, 111, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (15907, 111, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (15908, 111, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (15909, 111, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (15910, 111, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (15911, 111, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (15912, 111, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (15913, 111, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (15914, 111, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (15915, 112, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (15916, 112, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (15917, 112, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (15918, 112, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (15919, 112, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (15920, 112, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (15921, 112, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (15922, 112, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (15923, 112, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (15924, 112, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (15925, 112, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (15926, 112, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (15927, 112, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (15928, 112, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (15929, 112, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (15930, 112, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (15931, 112, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (15932, 112, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (15933, 112, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (15934, 112, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (15935, 112, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (15936, 112, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (15937, 112, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (15938, 112, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (15939, 112, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (15940, 112, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (15941, 112, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (15942, 112, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (15943, 112, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (15944, 112, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (15945, 112, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (15946, 112, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (15947, 112, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (15948, 112, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (15949, 112, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (15950, 112, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (15951, 112, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (15952, 112, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (15953, 112, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (15954, 112, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (15955, 112, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (15956, 112, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (15957, 112, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (15958, 112, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (15959, 112, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (15960, 112, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (15961, 112, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (15962, 112, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (15963, 112, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (15964, 112, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (15965, 112, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (15966, 112, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (15967, 112, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (15968, 112, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (15969, 112, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (15970, 112, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (15971, 112, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (15972, 112, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (15973, 112, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (15974, 112, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (15975, 112, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (15976, 112, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (15977, 112, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (15978, 112, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (15979, 112, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (15980, 112, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (15981, 112, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (15982, 112, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (15983, 112, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (15984, 112, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (15985, 112, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (15986, 112, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (15987, 112, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (15988, 112, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (15989, 112, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (15990, 112, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (15991, 112, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (15992, 112, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (15993, 112, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (15994, 112, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (15995, 112, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (15996, 112, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (15997, 112, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (15998, 112, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (15999, 112, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (16000, 112, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (16001, 112, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (16002, 112, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (16003, 112, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (16004, 112, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (16005, 112, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (16006, 112, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (16007, 112, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (16008, 112, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (16009, 112, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (16010, 112, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (16011, 112, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (16012, 112, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (16013, 112, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16014, 112, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16015, 112, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16016, 112, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (16017, 112, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (16018, 112, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (16019, 112, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16020, 112, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16021, 112, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16022, 112, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (16023, 112, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (16024, 112, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (16025, 112, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (16026, 112, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (16027, 112, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (16028, 112, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (16029, 112, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (16030, 112, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (16031, 112, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (16032, 112, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (16033, 112, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (16034, 112, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (16035, 112, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (16036, 112, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (16037, 112, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (16038, 112, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (16039, 112, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (16040, 112, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (16041, 112, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (16042, 112, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (16043, 112, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (16044, 112, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (16045, 112, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (16046, 112, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (16047, 112, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (16048, 112, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (16049, 112, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (16050, 112, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (16051, 112, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (16052, 112, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (16053, 112, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16054, 112, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16055, 112, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16056, 112, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16057, 112, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (16058, 112, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (16059, 112, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (16060, 112, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (16061, 112, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (16062, 112, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (16063, 112, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16064, 112, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16065, 112, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (16066, 112, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (16067, 112, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (16068, 112, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (16069, 112, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (16070, 112, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (16071, 112, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (16072, 112, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (16073, 112, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (16074, 112, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (16075, 112, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (16076, 112, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16077, 112, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16078, 112, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (16079, 112, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (16080, 112, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (16081, 112, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (16082, 112, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (16083, 112, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (16084, 112, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (16085, 112, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (16086, 112, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (16087, 112, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (16088, 112, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (16089, 112, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (16090, 112, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (16091, 112, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (16092, 112, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (16093, 112, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (16094, 113, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (16095, 113, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (16096, 113, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (16097, 113, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (16098, 113, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (16099, 113, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (16100, 113, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (16101, 113, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (16102, 113, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (16103, 113, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (16104, 113, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (16105, 113, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (16106, 113, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (16107, 113, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (16108, 113, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (16109, 113, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (16110, 113, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (16111, 113, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (16112, 113, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (16113, 113, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (16114, 113, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (16115, 113, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (16116, 113, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (16117, 113, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (16118, 113, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (16119, 113, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (16120, 113, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (16121, 113, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16122, 113, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16123, 113, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16124, 113, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16125, 113, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (16126, 113, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (16127, 113, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (16128, 113, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (16129, 113, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (16130, 113, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (16131, 113, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (16132, 113, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (16133, 113, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (16134, 113, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (16135, 113, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (16136, 113, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (16137, 113, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (16138, 113, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (16139, 113, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (16140, 113, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (16141, 113, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (16142, 113, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (16143, 113, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (16144, 113, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (16145, 113, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (16146, 113, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (16147, 113, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (16148, 113, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (16149, 113, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (16150, 113, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (16151, 113, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (16152, 113, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (16153, 113, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (16154, 113, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (16155, 113, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (16156, 113, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (16157, 113, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (16158, 113, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (16159, 113, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (16160, 113, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (16161, 113, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (16162, 113, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (16163, 113, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (16164, 113, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (16165, 113, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (16166, 113, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (16167, 113, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (16168, 113, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (16169, 113, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (16170, 113, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (16171, 113, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (16172, 113, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (16173, 113, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (16174, 113, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (16175, 113, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (16176, 113, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (16177, 113, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (16178, 113, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (16179, 113, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (16180, 113, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (16181, 113, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (16182, 113, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (16183, 113, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (16184, 113, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (16185, 113, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16186, 113, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (16187, 113, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (16188, 113, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (16189, 113, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (16190, 113, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (16191, 113, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (16192, 113, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (16193, 113, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (16194, 113, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (16195, 113, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16196, 113, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16197, 113, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16198, 113, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (16199, 113, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (16200, 113, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16201, 113, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16202, 113, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (16203, 113, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (16204, 113, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16205, 113, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (16206, 113, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (16207, 113, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (16208, 113, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (16209, 113, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (16210, 113, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (16211, 113, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (16212, 113, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (16213, 113, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (16214, 113, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (16215, 113, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (16216, 113, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (16217, 113, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (16218, 113, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (16219, 113, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (16220, 113, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (16221, 113, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (16222, 113, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16223, 113, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (16224, 113, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16225, 113, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (16226, 113, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (16227, 113, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (16228, 113, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (16229, 113, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (16230, 113, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (16231, 113, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (16232, 113, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (16233, 113, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16234, 113, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (16235, 113, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (16236, 113, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (16237, 113, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (16238, 113, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (16239, 113, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (16240, 113, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (16241, 113, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (16242, 113, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (16243, 113, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16244, 113, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (16245, 113, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (16246, 113, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (16247, 113, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16248, 113, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (16249, 113, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16250, 113, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (16251, 113, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (16252, 113, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (16253, 113, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (16254, 113, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (16255, 113, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (16256, 113, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (16257, 113, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (16258, 113, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (16259, 113, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16260, 113, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16261, 113, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (16262, 113, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (16263, 113, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (16264, 113, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (16265, 113, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (16266, 113, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (16267, 113, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (16268, 113, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (16269, 113, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (16270, 113, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (16271, 113, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (16272, 113, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (16273, 114, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (16274, 114, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (16275, 114, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (16276, 114, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (16277, 114, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (16278, 114, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (16279, 114, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (16280, 114, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (16281, 114, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (16282, 114, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (16283, 114, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (16284, 114, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (16285, 114, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (16286, 114, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (16287, 114, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (16288, 114, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (16289, 114, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (16290, 114, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (16291, 114, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (16292, 114, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (16293, 114, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (16294, 114, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (16295, 114, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (16296, 114, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (16297, 114, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (16298, 114, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (16299, 114, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (16300, 114, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16301, 114, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16302, 114, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16303, 114, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16304, 114, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (16305, 114, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (16306, 114, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (16307, 114, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (16308, 114, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (16309, 114, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (16310, 114, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (16311, 114, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (16312, 114, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (16313, 114, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (16314, 114, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (16315, 114, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (16316, 114, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (16317, 114, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (16318, 114, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (16319, 114, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (16320, 114, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (16321, 114, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (16322, 114, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (16323, 114, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (16324, 114, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (16325, 114, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (16326, 114, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (16327, 114, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (16328, 114, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (16329, 114, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (16330, 114, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (16331, 114, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (16332, 114, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (16333, 114, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (16334, 114, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (16335, 114, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (16336, 114, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (16337, 114, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (16338, 114, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (16339, 114, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (16340, 114, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (16341, 114, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (16342, 114, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (16343, 114, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (16344, 114, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (16345, 114, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (16346, 114, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (16347, 114, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (16348, 114, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (16349, 114, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (16350, 114, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (16351, 114, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (16352, 114, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (16353, 114, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (16354, 114, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (16355, 114, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (16356, 114, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16357, 114, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (16358, 114, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (16359, 114, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (16360, 114, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (16361, 114, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (16362, 114, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (16363, 114, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (16364, 114, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (16365, 114, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (16366, 114, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (16367, 114, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (16368, 114, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (16369, 114, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (16370, 114, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (16371, 114, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16372, 114, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16373, 114, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16374, 114, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (16375, 114, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (16376, 114, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (16377, 114, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16378, 114, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16379, 114, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16380, 114, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (16381, 114, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (16382, 114, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (16383, 114, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (16384, 114, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (16385, 114, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (16386, 114, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (16387, 114, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (16388, 114, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (16389, 114, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (16390, 114, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (16391, 114, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (16392, 114, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (16393, 114, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (16394, 114, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (16395, 114, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (16396, 114, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (16397, 114, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (16398, 114, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (16399, 114, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (16400, 114, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (16401, 114, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (16402, 114, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (16403, 114, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (16404, 114, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (16405, 114, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (16406, 114, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (16407, 114, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (16408, 114, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (16409, 114, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (16410, 114, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (16411, 114, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16412, 114, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16413, 114, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16414, 114, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16415, 114, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (16416, 114, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (16417, 114, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (16418, 114, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (16419, 114, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (16420, 114, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (16421, 114, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16422, 114, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16423, 114, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (16424, 114, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (16425, 114, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (16426, 114, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (16427, 114, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (16428, 114, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (16429, 114, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (16430, 114, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (16431, 114, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (16432, 114, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (16433, 114, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (16434, 114, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16435, 114, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16436, 114, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (16437, 114, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (16438, 114, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (16439, 114, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (16440, 114, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (16441, 114, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (16442, 114, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (16443, 114, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (16444, 114, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (16445, 114, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (16446, 114, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (16447, 114, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (16448, 114, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (16449, 114, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (16450, 114, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (16451, 114, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (16452, 115, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (16453, 115, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (16454, 115, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (16455, 115, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (16456, 115, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (16457, 115, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (16458, 115, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (16459, 115, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (16460, 115, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (16461, 115, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (16462, 115, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (16463, 115, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (16464, 115, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (16465, 115, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (16466, 115, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (16467, 115, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (16468, 115, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (16469, 115, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (16470, 115, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (16471, 115, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (16472, 115, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (16473, 115, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (16474, 115, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (16475, 115, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (16476, 115, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (16477, 115, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (16478, 115, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (16479, 115, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16480, 115, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16481, 115, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16482, 115, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16483, 115, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (16484, 115, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (16485, 115, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (16486, 115, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (16487, 115, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (16488, 115, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (16489, 115, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (16490, 115, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (16491, 115, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (16492, 115, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (16493, 115, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (16494, 115, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (16495, 115, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (16496, 115, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (16497, 115, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (16498, 115, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (16499, 115, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (16500, 115, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (16501, 115, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (16502, 115, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (16503, 115, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (16504, 115, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (16505, 115, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (16506, 115, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (16507, 115, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (16508, 115, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (16509, 115, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (16510, 115, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (16511, 115, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (16512, 115, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (16513, 115, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (16514, 115, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (16515, 115, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (16516, 115, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (16517, 115, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (16518, 115, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (16519, 115, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (16520, 115, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (16521, 115, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (16522, 115, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (16523, 115, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (16524, 115, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (16525, 115, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (16526, 115, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (16527, 115, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (16528, 115, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (16529, 115, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (16530, 115, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (16531, 115, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (16532, 115, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (16533, 115, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (16534, 115, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (16535, 115, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (16536, 115, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (16537, 115, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (16538, 115, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (16539, 115, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (16540, 115, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (16541, 115, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (16542, 115, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (16543, 115, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16544, 115, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (16545, 115, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (16546, 115, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (16547, 115, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (16548, 115, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (16549, 115, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (16550, 115, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (16551, 115, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (16552, 115, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (16553, 115, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16554, 115, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (16555, 115, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16556, 115, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (16557, 115, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (16558, 115, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16559, 115, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16560, 115, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (16561, 115, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (16562, 115, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (16563, 115, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (16564, 115, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (16565, 115, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (16566, 115, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (16567, 115, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (16568, 115, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (16569, 115, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (16570, 115, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (16571, 115, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (16572, 115, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (16573, 115, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (16574, 115, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (16575, 115, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (16576, 115, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (16577, 115, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (16578, 115, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (16579, 115, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (16580, 115, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16581, 115, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (16582, 115, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16583, 115, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (16584, 115, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (16585, 115, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (16586, 115, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (16587, 115, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (16588, 115, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (16589, 115, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (16590, 115, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (16591, 115, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (16592, 115, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (16593, 115, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (16594, 115, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (16595, 115, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (16596, 115, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (16597, 115, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (16598, 115, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (16599, 115, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (16600, 115, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (16601, 115, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (16602, 115, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (16603, 115, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (16604, 115, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (16605, 115, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (16606, 115, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (16607, 115, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (16608, 115, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (16609, 115, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (16610, 115, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (16611, 115, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (16612, 115, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (16613, 115, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (16614, 115, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (16615, 115, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (16616, 115, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (16617, 115, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (16618, 115, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (16619, 115, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (16620, 115, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (16621, 115, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (16622, 115, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (16623, 115, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (16624, 115, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (16625, 115, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (16626, 115, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (16627, 115, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (16628, 115, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (16629, 115, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (16630, 115, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (16635, 4, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16636, 4, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16637, 4, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16638, 4, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (16639, 100, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16640, 101, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16641, 102, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16642, 103, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16643, 104, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16644, 105, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16645, 106, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16646, 107, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16647, 108, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16648, 109, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16649, 110, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16650, 111, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16651, 112, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16652, 113, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16653, 114, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16654, 115, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (16655, 100, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16656, 101, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16657, 102, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16658, 103, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16659, 104, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16660, 105, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16661, 106, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16662, 107, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16663, 108, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16664, 109, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16665, 110, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16666, 111, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16667, 112, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16668, 113, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16669, 114, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16670, 115, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (16671, 100, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16672, 101, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16673, 102, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16674, 103, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16675, 104, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16676, 105, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16677, 106, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16678, 107, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16679, 108, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16680, 109, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16681, 110, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16682, 111, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16683, 112, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16684, 113, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16685, 114, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16686, 115, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (16687, 1, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16688, 2, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16690, 4, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16691, 100, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16692, 101, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16693, 102, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16694, 103, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16695, 104, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16696, 105, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16697, 106, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16698, 107, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16699, 108, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16700, 109, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16701, 110, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16702, 111, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16703, 112, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16704, 113, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16705, 114, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16706, 115, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (16707, 1, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16708, 2, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16710, 4, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16711, 100, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16712, 101, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16713, 102, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16714, 103, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16715, 104, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16716, 105, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16717, 106, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16718, 107, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16719, 108, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16720, 109, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16721, 110, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16722, 111, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16723, 112, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16724, 113, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16725, 114, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16726, 115, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (16727, NULL, NULL);
INSERT INTO `team_project_auth_node` VALUES (19161, 118, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (19162, 118, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (19163, 118, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (19164, 118, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (19165, 118, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (19166, 118, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (19167, 118, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (19168, 118, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (19169, 118, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (19170, 118, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (19171, 118, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (19172, 118, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (19173, 118, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (19174, 118, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (19175, 118, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (19176, 118, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (19177, 118, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (19178, 118, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (19179, 118, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (19180, 118, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (19181, 118, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (19182, 118, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (19183, 118, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (19184, 118, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (19185, 118, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (19186, 118, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (19187, 118, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19188, 118, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19189, 118, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19190, 118, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (19191, 118, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19192, 118, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (19193, 118, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (19194, 118, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (19195, 118, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (19196, 118, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (19197, 118, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (19198, 118, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (19199, 118, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (19200, 118, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (19201, 118, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (19202, 118, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (19203, 118, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (19204, 118, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (19205, 118, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (19206, 118, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (19207, 118, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (19208, 118, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (19209, 118, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (19210, 118, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (19211, 118, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (19212, 118, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (19213, 118, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (19214, 118, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (19215, 118, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (19216, 118, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (19217, 118, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (19218, 118, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (19219, 118, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (19220, 118, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (19221, 118, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (19222, 118, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (19223, 118, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (19224, 118, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (19225, 118, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (19226, 118, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (19227, 118, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (19228, 118, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (19229, 118, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (19230, 118, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (19231, 118, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (19232, 118, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (19233, 118, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (19234, 118, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (19235, 118, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (19236, 118, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (19237, 118, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (19238, 118, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (19239, 118, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (19240, 118, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (19241, 118, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (19242, 118, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (19243, 118, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19244, 118, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (19245, 118, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (19246, 118, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (19247, 118, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (19248, 118, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (19249, 118, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (19250, 118, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (19251, 118, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (19252, 118, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (19253, 118, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (19254, 118, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (19255, 118, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (19256, 118, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (19257, 118, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (19258, 118, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19259, 118, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19260, 118, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19261, 118, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (19262, 118, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (19263, 118, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (19264, 118, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (19265, 118, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19266, 118, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19267, 118, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19268, 118, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (19269, 118, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (19270, 118, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (19271, 118, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (19272, 118, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (19273, 118, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (19274, 118, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (19275, 118, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (19276, 118, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (19277, 118, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (19278, 118, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (19279, 118, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (19280, 118, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (19281, 118, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (19282, 118, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (19283, 118, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (19284, 118, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (19285, 118, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (19286, 118, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (19287, 118, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (19288, 118, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (19289, 118, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (19290, 118, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (19291, 118, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (19292, 118, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (19293, 118, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (19294, 118, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (19295, 118, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (19296, 118, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (19297, 118, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (19298, 118, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (19299, 118, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (19300, 118, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (19301, 118, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (19302, 118, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (19303, 118, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19304, 118, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19305, 118, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19306, 118, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19307, 118, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (19308, 118, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (19309, 118, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (19310, 118, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19311, 118, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19312, 118, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (19313, 118, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (19314, 118, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (19315, 118, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (19316, 118, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (19317, 118, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (19318, 118, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (19319, 118, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (19320, 118, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (19321, 118, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (19322, 118, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (19323, 118, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (19324, 118, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19325, 118, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19326, 118, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (19327, 118, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (19328, 118, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (19329, 118, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (19330, 118, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (19331, 118, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (19332, 118, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (19333, 118, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (19334, 118, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (19335, 118, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (19336, 118, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (19337, 118, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (19338, 118, 'project/project/taskpriority');
INSERT INTO `team_project_auth_node` VALUES (19339, 118, 'project');
INSERT INTO `team_project_auth_node` VALUES (19340, 118, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (19341, 118, 'project/project/gettoplist');
INSERT INTO `team_project_auth_node` VALUES (19342, 118, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (19343, 118, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (19344, 119, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (19345, 119, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (19346, 119, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (19347, 119, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (19348, 119, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (19349, 119, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (19350, 119, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (19351, 119, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (19352, 119, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (19353, 119, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (19354, 119, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (19355, 119, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (19356, 119, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (19357, 119, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (19358, 119, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (19359, 119, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (19360, 119, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (19361, 119, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (19362, 119, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (19363, 119, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (19364, 119, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (19365, 119, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (19366, 119, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (19367, 119, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (19368, 119, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (19369, 119, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (19370, 119, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (19371, 119, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19372, 119, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19373, 119, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19374, 119, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19375, 119, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (19376, 119, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (19377, 119, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (19378, 119, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (19379, 119, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (19380, 119, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (19381, 119, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (19382, 119, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (19383, 119, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (19384, 119, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (19385, 119, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (19386, 119, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (19387, 119, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (19388, 119, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (19389, 119, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (19390, 119, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (19391, 119, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (19392, 119, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (19393, 119, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (19394, 119, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (19395, 119, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (19396, 119, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (19397, 119, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (19398, 119, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (19399, 119, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (19400, 119, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (19401, 119, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (19402, 119, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (19403, 119, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (19404, 119, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (19405, 119, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (19406, 119, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (19407, 119, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (19408, 119, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (19409, 119, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (19410, 119, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (19411, 119, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (19412, 119, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (19413, 119, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (19414, 119, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (19415, 119, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (19416, 119, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (19417, 119, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (19418, 119, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (19419, 119, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (19420, 119, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (19421, 119, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (19422, 119, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (19423, 119, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (19424, 119, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (19425, 119, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (19426, 119, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (19427, 119, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (19428, 119, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (19429, 119, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (19430, 119, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (19431, 119, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (19432, 119, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (19433, 119, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (19434, 119, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (19435, 119, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19436, 119, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (19437, 119, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (19438, 119, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (19439, 119, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (19440, 119, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (19441, 119, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (19442, 119, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (19443, 119, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (19444, 119, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (19445, 119, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19446, 119, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19447, 119, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19448, 119, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (19449, 119, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (19450, 119, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19451, 119, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19452, 119, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (19453, 119, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (19454, 119, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19455, 119, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (19456, 119, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (19457, 119, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (19458, 119, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (19459, 119, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (19460, 119, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (19461, 119, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (19462, 119, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (19463, 119, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (19464, 119, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (19465, 119, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (19466, 119, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (19467, 119, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (19468, 119, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (19469, 119, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (19470, 119, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (19471, 119, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (19472, 119, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19473, 119, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (19474, 119, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19475, 119, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (19476, 119, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (19477, 119, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (19478, 119, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (19479, 119, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (19480, 119, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (19481, 119, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (19482, 119, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (19483, 119, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19484, 119, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (19485, 119, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (19486, 119, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (19487, 119, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (19488, 119, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (19489, 119, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (19490, 119, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (19491, 119, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (19492, 119, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (19493, 119, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19494, 119, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (19495, 119, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (19496, 119, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (19497, 119, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19498, 119, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (19499, 119, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19500, 119, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (19501, 119, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (19502, 119, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (19503, 119, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (19504, 119, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (19505, 119, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (19506, 119, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (19507, 119, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (19508, 119, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (19509, 119, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19510, 119, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19511, 119, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (19512, 119, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (19513, 119, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (19514, 119, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (19515, 119, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (19516, 119, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (19517, 119, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (19518, 119, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (19519, 119, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (19520, 119, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (19521, 119, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (19522, 119, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (19523, 119, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (19524, 119, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (19525, 119, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (19526, 119, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (19527, 119, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (19528, 119, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (19529, 120, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (19530, 120, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (19531, 120, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (19532, 120, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (19533, 120, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (19534, 120, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (19535, 120, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (19536, 120, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (19537, 120, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (19538, 120, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (19539, 120, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (19540, 120, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (19541, 120, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (19542, 120, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (19543, 120, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (19544, 120, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (19545, 120, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (19546, 120, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (19547, 120, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (19548, 120, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (19549, 120, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (19550, 120, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (19551, 120, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (19552, 120, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (19553, 120, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (19554, 120, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (19555, 120, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19556, 120, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19557, 120, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19558, 120, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (19559, 120, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19560, 120, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (19561, 120, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (19562, 120, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (19563, 120, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (19564, 120, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (19565, 120, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (19566, 120, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (19567, 120, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (19568, 120, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (19569, 120, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (19570, 120, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (19571, 120, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (19572, 120, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (19573, 120, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (19574, 120, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (19575, 120, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (19576, 120, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (19577, 120, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (19578, 120, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (19579, 120, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (19580, 120, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (19581, 120, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (19582, 120, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (19583, 120, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (19584, 120, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (19585, 120, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (19586, 120, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (19587, 120, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (19588, 120, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (19589, 120, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (19590, 120, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (19591, 120, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (19592, 120, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (19593, 120, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (19594, 120, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (19595, 120, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (19596, 120, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (19597, 120, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (19598, 120, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (19599, 120, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (19600, 120, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (19601, 120, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (19602, 120, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (19603, 120, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (19604, 120, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (19605, 120, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (19606, 120, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (19607, 120, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (19608, 120, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (19609, 120, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (19610, 120, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (19611, 120, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19612, 120, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (19613, 120, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (19614, 120, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (19615, 120, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (19616, 120, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (19617, 120, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (19618, 120, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (19619, 120, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (19620, 120, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (19621, 120, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (19622, 120, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (19623, 120, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (19624, 120, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (19625, 120, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (19626, 120, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19627, 120, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19628, 120, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19629, 120, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (19630, 120, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (19631, 120, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (19632, 120, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (19633, 120, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19634, 120, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19635, 120, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19636, 120, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (19637, 120, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (19638, 120, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (19639, 120, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (19640, 120, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (19641, 120, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (19642, 120, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (19643, 120, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (19644, 120, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (19645, 120, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (19646, 120, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (19647, 120, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (19648, 120, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (19649, 120, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (19650, 120, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (19651, 120, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (19652, 120, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (19653, 120, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (19654, 120, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (19655, 120, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (19656, 120, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (19657, 120, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (19658, 120, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (19659, 120, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (19660, 120, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (19661, 120, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (19662, 120, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (19663, 120, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (19664, 120, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (19665, 120, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (19666, 120, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (19667, 120, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (19668, 120, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (19669, 120, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (19670, 120, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (19671, 120, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19672, 120, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19673, 120, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19674, 120, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19675, 120, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (19676, 120, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (19677, 120, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (19678, 120, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19679, 120, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19680, 120, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (19681, 120, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (19682, 120, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (19683, 120, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (19684, 120, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (19685, 120, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (19686, 120, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (19687, 120, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (19688, 120, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (19689, 120, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (19690, 120, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (19691, 120, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (19692, 120, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19693, 120, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19694, 120, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (19695, 120, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (19696, 120, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (19697, 120, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (19698, 120, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (19699, 120, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (19700, 120, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (19701, 120, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (19702, 120, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (19703, 120, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (19704, 120, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (19705, 120, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (19706, 120, 'project/project/taskpriority');
INSERT INTO `team_project_auth_node` VALUES (19707, 120, 'project');
INSERT INTO `team_project_auth_node` VALUES (19708, 120, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (19709, 120, 'project/project/gettoplist');
INSERT INTO `team_project_auth_node` VALUES (19710, 120, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (19711, 120, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (19712, 121, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (19713, 121, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (19714, 121, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (19715, 121, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (19716, 121, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (19717, 121, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (19718, 121, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (19719, 121, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (19720, 121, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (19721, 121, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (19722, 121, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (19723, 121, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (19724, 121, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (19725, 121, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (19726, 121, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (19727, 121, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (19728, 121, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (19729, 121, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (19730, 121, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (19731, 121, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (19732, 121, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (19733, 121, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (19734, 121, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (19735, 121, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (19736, 121, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (19737, 121, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (19738, 121, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (19739, 121, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19740, 121, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19741, 121, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19742, 121, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19743, 121, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (19744, 121, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (19745, 121, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (19746, 121, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (19747, 121, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (19748, 121, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (19749, 121, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (19750, 121, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (19751, 121, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (19752, 121, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (19753, 121, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (19754, 121, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (19755, 121, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (19756, 121, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (19757, 121, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (19758, 121, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (19759, 121, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (19760, 121, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (19761, 121, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (19762, 121, 'project/login/_bindmail');
INSERT INTO `team_project_auth_node` VALUES (19763, 121, 'project/login/_bindmobile');
INSERT INTO `team_project_auth_node` VALUES (19764, 121, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (19765, 121, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (19766, 121, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (19767, 121, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (19768, 121, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (19769, 121, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (19770, 121, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (19771, 121, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (19772, 121, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (19773, 121, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (19774, 121, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (19775, 121, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (19776, 121, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (19777, 121, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (19778, 121, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (19779, 121, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (19780, 121, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (19781, 121, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (19782, 121, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (19783, 121, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (19784, 121, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (19785, 121, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (19786, 121, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (19787, 121, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (19788, 121, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (19789, 121, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (19790, 121, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (19791, 121, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (19792, 121, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (19793, 121, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (19794, 121, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (19795, 121, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (19796, 121, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (19797, 121, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (19798, 121, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (19799, 121, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (19800, 121, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (19801, 121, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (19802, 121, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (19803, 121, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19804, 121, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (19805, 121, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (19806, 121, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (19807, 121, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (19808, 121, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (19809, 121, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (19810, 121, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (19811, 121, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (19812, 121, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (19813, 121, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19814, 121, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19815, 121, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19816, 121, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (19817, 121, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (19818, 121, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19819, 121, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19820, 121, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (19821, 121, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (19822, 121, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19823, 121, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (19824, 121, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (19825, 121, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (19826, 121, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (19827, 121, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (19828, 121, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (19829, 121, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (19830, 121, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (19831, 121, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (19832, 121, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (19833, 121, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (19834, 121, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (19835, 121, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (19836, 121, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (19837, 121, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (19838, 121, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (19839, 121, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (19840, 121, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19841, 121, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (19842, 121, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19843, 121, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (19844, 121, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (19845, 121, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (19846, 121, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (19847, 121, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (19848, 121, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (19849, 121, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (19850, 121, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (19851, 121, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (19852, 121, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (19853, 121, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (19854, 121, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (19855, 121, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (19856, 121, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (19857, 121, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (19858, 121, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (19859, 121, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (19860, 121, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (19861, 121, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19862, 121, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (19863, 121, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (19864, 121, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (19865, 121, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19866, 121, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (19867, 121, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19868, 121, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (19869, 121, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (19870, 121, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (19871, 121, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (19872, 121, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (19873, 121, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (19874, 121, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (19875, 121, 'project/task_stages/_getAll');
INSERT INTO `team_project_auth_node` VALUES (19876, 121, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (19877, 121, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (19878, 121, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (19879, 121, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (19880, 121, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (19881, 121, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (19882, 121, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (19883, 121, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (19884, 121, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (19885, 121, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (19886, 121, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (19887, 121, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (19888, 121, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (19889, 121, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (19890, 121, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (19891, 121, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (19892, 121, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (19893, 121, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (19894, 121, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (19895, 121, 'project/project_version/_getversiontask');
INSERT INTO `team_project_auth_node` VALUES (19896, 121, 'project/project_version/_getversionlog');
INSERT INTO `team_project_auth_node` VALUES (19897, 3, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (19898, 3, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (19899, 3, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (19900, 3, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (19901, 3, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (19902, 3, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (19903, 3, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (19904, 3, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (19905, 3, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (19906, 3, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (19907, 3, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (19908, 3, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (19909, 3, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (19910, 3, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (19911, 3, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (19912, 3, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (19913, 3, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (19914, 3, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (19915, 3, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (19916, 3, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (19917, 3, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (19918, 3, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (19919, 3, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (19920, 3, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (19921, 3, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (19922, 3, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (19923, 3, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19924, 3, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19925, 3, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19926, 3, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (19927, 3, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (19928, 3, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (19929, 3, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (19930, 3, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (19931, 3, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (19932, 3, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (19933, 3, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (19934, 3, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (19935, 3, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (19936, 3, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (19937, 3, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (19938, 3, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (19939, 3, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (19940, 3, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (19941, 3, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (19942, 3, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (19943, 3, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (19944, 3, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (19945, 3, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (19946, 3, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (19947, 3, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (19948, 3, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (19949, 3, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (19950, 3, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (19951, 3, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (19952, 3, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (19953, 3, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (19954, 3, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (19955, 3, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (19956, 3, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (19957, 3, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (19958, 3, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (19959, 3, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (19960, 3, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (19961, 3, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (19962, 3, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (19963, 3, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (19964, 3, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (19965, 3, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (19966, 3, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (19967, 3, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (19968, 3, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (19969, 3, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (19970, 3, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (19971, 3, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (19972, 3, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (19973, 3, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (19974, 3, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (19975, 3, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (19976, 3, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (19977, 3, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (19978, 3, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (19979, 3, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (19980, 3, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (19981, 3, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (19982, 3, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (19983, 3, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (19984, 3, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (19985, 3, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (19986, 3, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (19987, 3, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (19988, 3, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (19989, 3, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (19990, 3, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (19991, 3, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (19992, 3, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (19993, 3, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (19994, 3, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (19995, 3, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (19996, 3, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (19997, 3, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (19998, 3, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (19999, 3, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (20000, 3, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (20001, 3, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (20002, 3, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (20003, 3, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (20004, 3, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (20005, 3, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (20006, 3, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (20007, 3, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (20008, 3, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (20009, 3, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (20010, 3, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (20011, 3, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (20012, 3, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (20013, 3, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (20014, 3, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (20015, 3, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (20016, 3, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (20017, 3, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (20018, 3, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (20019, 3, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (20020, 3, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (20021, 3, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (20022, 3, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (20023, 3, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (20024, 3, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (20025, 3, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (20026, 3, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (20027, 3, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (20028, 3, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (20029, 3, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (20030, 3, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (20031, 3, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (20032, 3, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (20033, 3, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (20034, 3, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (20035, 3, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (20036, 3, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (20037, 3, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (20038, 3, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (20039, 3, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (20040, 3, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20041, 3, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20042, 3, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20043, 3, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (20044, 3, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (20045, 3, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (20046, 3, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (20047, 3, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20048, 3, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20049, 3, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (20050, 3, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (20051, 3, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (20052, 3, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (20053, 3, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (20054, 3, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (20055, 3, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (20056, 3, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (20057, 3, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (20058, 3, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (20059, 3, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (20060, 3, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (20061, 3, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (20062, 3, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (20063, 3, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (20064, 3, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (20065, 3, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (20066, 3, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (20067, 3, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (20068, 3, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (20069, 3, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (20070, 3, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (20071, 3, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (20072, 3, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (20073, 3, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (20074, 3, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (20075, 3, 'project/project/taskpriority');
INSERT INTO `team_project_auth_node` VALUES (20076, 3, 'project/project/gettoplist');
INSERT INTO `team_project_auth_node` VALUES (20077, 3, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (20078, 3, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (20079, 3, 'project/project_info/index');
INSERT INTO `team_project_auth_node` VALUES (20080, 3, 'project');
INSERT INTO `team_project_auth_node` VALUES (20081, 116, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (20082, 116, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (20083, 116, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (20084, 116, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (20085, 116, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (20086, 116, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (20087, 116, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (20088, 116, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (20089, 116, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (20090, 116, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (20091, 116, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (20092, 116, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (20093, 116, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (20094, 116, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (20095, 116, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (20096, 116, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (20097, 116, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (20098, 116, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (20099, 116, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (20100, 116, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (20101, 116, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (20102, 116, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20103, 116, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20104, 116, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (20105, 116, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (20106, 116, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (20107, 116, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (20108, 116, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (20109, 116, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (20110, 116, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (20111, 116, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (20112, 116, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (20113, 116, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (20114, 116, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (20115, 116, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (20116, 116, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (20117, 116, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (20118, 116, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (20119, 116, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (20120, 116, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (20121, 116, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (20122, 116, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (20123, 116, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (20124, 116, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (20125, 116, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20126, 116, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20127, 116, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (20128, 116, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (20129, 116, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (20130, 116, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (20131, 116, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (20132, 116, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (20133, 116, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (20134, 116, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (20135, 116, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (20136, 116, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (20137, 116, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (20138, 116, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (20139, 116, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (20140, 116, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (20141, 116, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (20142, 116, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (20143, 116, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (20144, 116, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (20145, 116, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (20146, 116, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (20147, 116, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (20148, 116, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (20149, 116, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (20150, 116, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (20151, 116, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (20152, 116, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (20153, 116, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (20154, 116, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (20155, 116, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (20156, 116, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (20157, 116, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (20158, 116, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (20159, 116, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20160, 116, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20161, 116, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20162, 116, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (20163, 116, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (20164, 116, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (20165, 116, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (20166, 116, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20167, 116, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20168, 116, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (20169, 116, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (20170, 116, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (20171, 116, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (20172, 116, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (20173, 116, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (20174, 116, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (20175, 116, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (20176, 116, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (20177, 116, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (20178, 116, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (20179, 116, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (20180, 116, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (20181, 116, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (20182, 116, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (20183, 116, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (20184, 116, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (20185, 116, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (20186, 116, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (20187, 116, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (20188, 116, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (20189, 116, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (20190, 116, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (20191, 116, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (20192, 116, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (20193, 116, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (20194, 116, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (20195, 116, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (20196, 116, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (20197, 116, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (20198, 116, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (20199, 116, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (20200, 116, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (20201, 116, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (20202, 116, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (20203, 116, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (20204, 116, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (20205, 116, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (20206, 116, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (20207, 116, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (20208, 116, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (20209, 116, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (20210, 116, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (20211, 116, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (20212, 116, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (20213, 116, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (20214, 116, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (20215, 116, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (20216, 116, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (20217, 116, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (20218, 116, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (20219, 116, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (20220, 116, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (20221, 116, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (20222, 116, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (20223, 116, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (20224, 116, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (20225, 116, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (20226, 116, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (20227, 116, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (20228, 116, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (20229, 116, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (20230, 116, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (20231, 116, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (20232, 116, 'project/project/taskpriority');
INSERT INTO `team_project_auth_node` VALUES (20233, 116, 'project/project/gettoplist');
INSERT INTO `team_project_auth_node` VALUES (20234, 116, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (20235, 116, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (20236, 116, 'project/project_info/index');
INSERT INTO `team_project_auth_node` VALUES (20237, 117, 'project');
INSERT INTO `team_project_auth_node` VALUES (20238, 117, 'project/index');
INSERT INTO `team_project_auth_node` VALUES (20239, 117, 'project/index/info');
INSERT INTO `team_project_auth_node` VALUES (20240, 117, 'project/index/index');
INSERT INTO `team_project_auth_node` VALUES (20241, 117, 'project/index/systemconfig');
INSERT INTO `team_project_auth_node` VALUES (20242, 117, 'project/index/editpersonal');
INSERT INTO `team_project_auth_node` VALUES (20243, 117, 'project/index/uploadavatar');
INSERT INTO `team_project_auth_node` VALUES (20244, 117, 'project/index/changecurrentorganization');
INSERT INTO `team_project_auth_node` VALUES (20245, 117, 'project/index/editpassword');
INSERT INTO `team_project_auth_node` VALUES (20246, 117, 'project/index/uploadimg');
INSERT INTO `team_project_auth_node` VALUES (20247, 117, 'project/account');
INSERT INTO `team_project_auth_node` VALUES (20248, 117, 'project/account/index');
INSERT INTO `team_project_auth_node` VALUES (20249, 117, 'project/account/auth');
INSERT INTO `team_project_auth_node` VALUES (20250, 117, 'project/account/add');
INSERT INTO `team_project_auth_node` VALUES (20251, 117, 'project/account/edit');
INSERT INTO `team_project_auth_node` VALUES (20252, 117, 'project/account/del');
INSERT INTO `team_project_auth_node` VALUES (20253, 117, 'project/account/forbid');
INSERT INTO `team_project_auth_node` VALUES (20254, 117, 'project/account/resume');
INSERT INTO `team_project_auth_node` VALUES (20255, 117, 'project/account/read');
INSERT INTO `team_project_auth_node` VALUES (20256, 117, 'project/organization');
INSERT INTO `team_project_auth_node` VALUES (20257, 117, 'project/organization/index');
INSERT INTO `team_project_auth_node` VALUES (20258, 117, 'project/organization/save');
INSERT INTO `team_project_auth_node` VALUES (20259, 117, 'project/organization/read');
INSERT INTO `team_project_auth_node` VALUES (20260, 117, 'project/organization/edit');
INSERT INTO `team_project_auth_node` VALUES (20261, 117, 'project/organization/delete');
INSERT INTO `team_project_auth_node` VALUES (20262, 117, 'project/organization/_getorglist');
INSERT INTO `team_project_auth_node` VALUES (20263, 117, 'project/auth');
INSERT INTO `team_project_auth_node` VALUES (20264, 117, 'project/auth/index');
INSERT INTO `team_project_auth_node` VALUES (20265, 117, 'project/auth/add');
INSERT INTO `team_project_auth_node` VALUES (20266, 117, 'project/auth/edit');
INSERT INTO `team_project_auth_node` VALUES (20267, 117, 'project/auth/forbid');
INSERT INTO `team_project_auth_node` VALUES (20268, 117, 'project/auth/resume');
INSERT INTO `team_project_auth_node` VALUES (20269, 117, 'project/auth/del');
INSERT INTO `team_project_auth_node` VALUES (20270, 117, 'project/auth/apply');
INSERT INTO `team_project_auth_node` VALUES (20271, 117, 'project/auth/setdefault');
INSERT INTO `team_project_auth_node` VALUES (20272, 117, 'project/notify');
INSERT INTO `team_project_auth_node` VALUES (20273, 117, 'project/notify/index');
INSERT INTO `team_project_auth_node` VALUES (20274, 117, 'project/notify/noreads');
INSERT INTO `team_project_auth_node` VALUES (20275, 117, 'project/notify/read');
INSERT INTO `team_project_auth_node` VALUES (20276, 117, 'project/notify/delete');
INSERT INTO `team_project_auth_node` VALUES (20277, 117, 'project/notify/setreadied');
INSERT INTO `team_project_auth_node` VALUES (20278, 117, 'project/notify/batchdel');
INSERT INTO `team_project_auth_node` VALUES (20279, 117, 'project/department');
INSERT INTO `team_project_auth_node` VALUES (20280, 117, 'project/department/index');
INSERT INTO `team_project_auth_node` VALUES (20281, 117, 'project/department/read');
INSERT INTO `team_project_auth_node` VALUES (20282, 117, 'project/department/save');
INSERT INTO `team_project_auth_node` VALUES (20283, 117, 'project/department/edit');
INSERT INTO `team_project_auth_node` VALUES (20284, 117, 'project/department/delete');
INSERT INTO `team_project_auth_node` VALUES (20285, 117, 'project/department_member');
INSERT INTO `team_project_auth_node` VALUES (20286, 117, 'project/department_member/index');
INSERT INTO `team_project_auth_node` VALUES (20287, 117, 'project/department_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20288, 117, 'project/department_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20289, 117, 'project/department_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (20290, 117, 'project/department_member/detail');
INSERT INTO `team_project_auth_node` VALUES (20291, 117, 'project/department_member/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (20292, 117, 'project/menu');
INSERT INTO `team_project_auth_node` VALUES (20293, 117, 'project/menu/menu');
INSERT INTO `team_project_auth_node` VALUES (20294, 117, 'project/menu/menuadd');
INSERT INTO `team_project_auth_node` VALUES (20295, 117, 'project/menu/menuedit');
INSERT INTO `team_project_auth_node` VALUES (20296, 117, 'project/menu/menuforbid');
INSERT INTO `team_project_auth_node` VALUES (20297, 117, 'project/menu/menuresume');
INSERT INTO `team_project_auth_node` VALUES (20298, 117, 'project/menu/menudel');
INSERT INTO `team_project_auth_node` VALUES (20299, 117, 'project/node');
INSERT INTO `team_project_auth_node` VALUES (20300, 117, 'project/node/index');
INSERT INTO `team_project_auth_node` VALUES (20301, 117, 'project/node/alllist');
INSERT INTO `team_project_auth_node` VALUES (20302, 117, 'project/node/clear');
INSERT INTO `team_project_auth_node` VALUES (20303, 117, 'project/node/save');
INSERT INTO `team_project_auth_node` VALUES (20304, 117, 'project/project');
INSERT INTO `team_project_auth_node` VALUES (20305, 117, 'project/project/index');
INSERT INTO `team_project_auth_node` VALUES (20306, 117, 'project/project/selflist');
INSERT INTO `team_project_auth_node` VALUES (20307, 117, 'project/project/save');
INSERT INTO `team_project_auth_node` VALUES (20308, 117, 'project/project/read');
INSERT INTO `team_project_auth_node` VALUES (20309, 117, 'project/project/edit');
INSERT INTO `team_project_auth_node` VALUES (20310, 117, 'project/project/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (20311, 117, 'project/project/recycle');
INSERT INTO `team_project_auth_node` VALUES (20312, 117, 'project/project/recovery');
INSERT INTO `team_project_auth_node` VALUES (20313, 117, 'project/project/archive');
INSERT INTO `team_project_auth_node` VALUES (20314, 117, 'project/project/recoveryarchive');
INSERT INTO `team_project_auth_node` VALUES (20315, 117, 'project/project/quit');
INSERT INTO `team_project_auth_node` VALUES (20316, 117, 'project/project/getlogbyselfproject');
INSERT INTO `team_project_auth_node` VALUES (20317, 117, 'project/project/_getprojectreport');
INSERT INTO `team_project_auth_node` VALUES (20318, 117, 'project/project/_projectstats');
INSERT INTO `team_project_auth_node` VALUES (20319, 117, 'project/project/gettoplist');
INSERT INTO `team_project_auth_node` VALUES (20320, 117, 'project/project/taskpriority');
INSERT INTO `team_project_auth_node` VALUES (20321, 117, 'project/project/analysis');
INSERT INTO `team_project_auth_node` VALUES (20322, 117, 'project/project_collect');
INSERT INTO `team_project_auth_node` VALUES (20323, 117, 'project/project_collect/collect');
INSERT INTO `team_project_auth_node` VALUES (20324, 117, 'project/project_member');
INSERT INTO `team_project_auth_node` VALUES (20325, 117, 'project/project_member/index');
INSERT INTO `team_project_auth_node` VALUES (20326, 117, 'project/project_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20327, 117, 'project/project_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20328, 117, 'project/project_member/removemember');
INSERT INTO `team_project_auth_node` VALUES (20329, 117, 'project/project_member/_listforinvite');
INSERT INTO `team_project_auth_node` VALUES (20330, 117, 'project/project_template');
INSERT INTO `team_project_auth_node` VALUES (20331, 117, 'project/project_template/index');
INSERT INTO `team_project_auth_node` VALUES (20332, 117, 'project/project_template/save');
INSERT INTO `team_project_auth_node` VALUES (20333, 117, 'project/project_template/uploadcover');
INSERT INTO `team_project_auth_node` VALUES (20334, 117, 'project/project_template/edit');
INSERT INTO `team_project_auth_node` VALUES (20335, 117, 'project/project_template/delete');
INSERT INTO `team_project_auth_node` VALUES (20336, 117, 'project/task');
INSERT INTO `team_project_auth_node` VALUES (20337, 117, 'project/task/index');
INSERT INTO `team_project_auth_node` VALUES (20338, 117, 'project/task/selflist');
INSERT INTO `team_project_auth_node` VALUES (20339, 117, 'project/task/read');
INSERT INTO `team_project_auth_node` VALUES (20340, 117, 'project/task/save');
INSERT INTO `team_project_auth_node` VALUES (20341, 117, 'project/task/taskdone');
INSERT INTO `team_project_auth_node` VALUES (20342, 117, 'project/task/assigntask');
INSERT INTO `team_project_auth_node` VALUES (20343, 117, 'project/task/sort');
INSERT INTO `team_project_auth_node` VALUES (20344, 117, 'project/task/createcomment');
INSERT INTO `team_project_auth_node` VALUES (20345, 117, 'project/task/edit');
INSERT INTO `team_project_auth_node` VALUES (20346, 117, 'project/task/like');
INSERT INTO `team_project_auth_node` VALUES (20347, 117, 'project/task/star');
INSERT INTO `team_project_auth_node` VALUES (20348, 117, 'project/task/recycle');
INSERT INTO `team_project_auth_node` VALUES (20349, 117, 'project/task/recovery');
INSERT INTO `team_project_auth_node` VALUES (20350, 117, 'project/task/delete');
INSERT INTO `team_project_auth_node` VALUES (20351, 117, 'project/task/datetotalforproject');
INSERT INTO `team_project_auth_node` VALUES (20352, 117, 'project/task/tasksources');
INSERT INTO `team_project_auth_node` VALUES (20353, 117, 'project/task/tasklog');
INSERT INTO `team_project_auth_node` VALUES (20354, 117, 'project/task/recyclebatch');
INSERT INTO `team_project_auth_node` VALUES (20355, 117, 'project/task/setprivate');
INSERT INTO `team_project_auth_node` VALUES (20356, 117, 'project/task/batchassigntask');
INSERT INTO `team_project_auth_node` VALUES (20357, 117, 'project/task/tasktotags');
INSERT INTO `team_project_auth_node` VALUES (20358, 117, 'project/task/settag');
INSERT INTO `team_project_auth_node` VALUES (20359, 117, 'project/task/getlistbytasktag');
INSERT INTO `team_project_auth_node` VALUES (20360, 117, 'project/task/savetaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20361, 117, 'project/task/edittaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20362, 117, 'project/task/deltaskworktime');
INSERT INTO `team_project_auth_node` VALUES (20363, 117, 'project/task/uploadfile');
INSERT INTO `team_project_auth_node` VALUES (20364, 117, 'project/task/_taskworktimelist');
INSERT INTO `team_project_auth_node` VALUES (20365, 117, 'project/task_member');
INSERT INTO `team_project_auth_node` VALUES (20366, 117, 'project/task_member/index');
INSERT INTO `team_project_auth_node` VALUES (20367, 117, 'project/task_member/searchinvitemember');
INSERT INTO `team_project_auth_node` VALUES (20368, 117, 'project/task_member/invitemember');
INSERT INTO `team_project_auth_node` VALUES (20369, 117, 'project/task_member/invitememberbatch');
INSERT INTO `team_project_auth_node` VALUES (20370, 117, 'project/task_stages');
INSERT INTO `team_project_auth_node` VALUES (20371, 117, 'project/task_stages/index');
INSERT INTO `team_project_auth_node` VALUES (20372, 117, 'project/task_stages/tasks');
INSERT INTO `team_project_auth_node` VALUES (20373, 117, 'project/task_stages/sort');
INSERT INTO `team_project_auth_node` VALUES (20374, 117, 'project/task_stages/save');
INSERT INTO `team_project_auth_node` VALUES (20375, 117, 'project/task_stages/edit');
INSERT INTO `team_project_auth_node` VALUES (20376, 117, 'project/task_stages/delete');
INSERT INTO `team_project_auth_node` VALUES (20377, 117, 'project/task_stages/_getall');
INSERT INTO `team_project_auth_node` VALUES (20378, 117, 'project/task_stages_template');
INSERT INTO `team_project_auth_node` VALUES (20379, 117, 'project/task_stages_template/index');
INSERT INTO `team_project_auth_node` VALUES (20380, 117, 'project/task_stages_template/save');
INSERT INTO `team_project_auth_node` VALUES (20381, 117, 'project/task_stages_template/edit');
INSERT INTO `team_project_auth_node` VALUES (20382, 117, 'project/task_stages_template/delete');
INSERT INTO `team_project_auth_node` VALUES (20383, 117, 'project/file');
INSERT INTO `team_project_auth_node` VALUES (20384, 117, 'project/file/index');
INSERT INTO `team_project_auth_node` VALUES (20385, 117, 'project/file/read');
INSERT INTO `team_project_auth_node` VALUES (20386, 117, 'project/file/uploadfiles');
INSERT INTO `team_project_auth_node` VALUES (20387, 117, 'project/file/edit');
INSERT INTO `team_project_auth_node` VALUES (20388, 117, 'project/file/recycle');
INSERT INTO `team_project_auth_node` VALUES (20389, 117, 'project/file/recovery');
INSERT INTO `team_project_auth_node` VALUES (20390, 117, 'project/file/delete');
INSERT INTO `team_project_auth_node` VALUES (20391, 117, 'project/source_link');
INSERT INTO `team_project_auth_node` VALUES (20392, 117, 'project/source_link/delete');
INSERT INTO `team_project_auth_node` VALUES (20393, 117, 'project/invite_link');
INSERT INTO `team_project_auth_node` VALUES (20394, 117, 'project/invite_link/save');
INSERT INTO `team_project_auth_node` VALUES (20395, 117, 'project/task_tag');
INSERT INTO `team_project_auth_node` VALUES (20396, 117, 'project/task_tag/index');
INSERT INTO `team_project_auth_node` VALUES (20397, 117, 'project/task_tag/save');
INSERT INTO `team_project_auth_node` VALUES (20398, 117, 'project/task_tag/edit');
INSERT INTO `team_project_auth_node` VALUES (20399, 117, 'project/task_tag/delete');
INSERT INTO `team_project_auth_node` VALUES (20400, 117, 'project/project_features');
INSERT INTO `team_project_auth_node` VALUES (20401, 117, 'project/project_features/index');
INSERT INTO `team_project_auth_node` VALUES (20402, 117, 'project/project_features/save');
INSERT INTO `team_project_auth_node` VALUES (20403, 117, 'project/project_features/edit');
INSERT INTO `team_project_auth_node` VALUES (20404, 117, 'project/project_features/delete');
INSERT INTO `team_project_auth_node` VALUES (20405, 117, 'project/project_version');
INSERT INTO `team_project_auth_node` VALUES (20406, 117, 'project/project_version/index');
INSERT INTO `team_project_auth_node` VALUES (20407, 117, 'project/project_version/save');
INSERT INTO `team_project_auth_node` VALUES (20408, 117, 'project/project_version/edit');
INSERT INTO `team_project_auth_node` VALUES (20409, 117, 'project/project_version/changestatus');
INSERT INTO `team_project_auth_node` VALUES (20410, 117, 'project/project_version/read');
INSERT INTO `team_project_auth_node` VALUES (20411, 117, 'project/project_version/addversiontask');
INSERT INTO `team_project_auth_node` VALUES (20412, 117, 'project/project_version/removeversiontask');
INSERT INTO `team_project_auth_node` VALUES (20413, 117, 'project/project_version/delete');
INSERT INTO `team_project_auth_node` VALUES (20414, 117, 'project/task_workflow');
INSERT INTO `team_project_auth_node` VALUES (20415, 117, 'project/task_workflow/index');
INSERT INTO `team_project_auth_node` VALUES (20416, 117, 'project/task_workflow/save');
INSERT INTO `team_project_auth_node` VALUES (20417, 117, 'project/task_workflow/edit');
INSERT INTO `team_project_auth_node` VALUES (20418, 117, 'project/task_workflow/delete');
INSERT INTO `team_project_auth_node` VALUES (20419, 117, 'project/project_info');
INSERT INTO `team_project_auth_node` VALUES (20420, 117, 'project/project_info/index');

-- ----------------------------
-- Table structure for team_project_collection
-- ----------------------------
DROP TABLE IF EXISTS `team_project_collection`;
CREATE TABLE `team_project_collection`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `project_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目id',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目-收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_project_config
-- ----------------------------
DROP TABLE IF EXISTS `team_project_config`;
CREATE TABLE `team_project_config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `project_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_project_features
-- ----------------------------
DROP TABLE IF EXISTS `team_project_features`;
CREATE TABLE `team_project_features`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本库名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织id',
  `project_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `project_code`(`project_code`) USING BTREE,
  INDEX `organization_code`(`organization_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '版本库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_project_info
-- ----------------------------
DROP TABLE IF EXISTS `team_project_info`;
CREATE TABLE `team_project_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织id',
  `project_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目id',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'code',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `project_code`(`project_code`) USING BTREE,
  INDEX `organization_code`(`organization_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目自定义信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_project_log
-- ----------------------------
DROP TABLE IF EXISTS `team_project_log`;
CREATE TABLE `team_project_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '操作人id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'create' COMMENT '操作类型',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加时间',
  `source_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '任务id',
  `action_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场景类型',
  `to_member_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `is_comment` tinyint(1) NULL DEFAULT 0 COMMENT '是否评论，0：否',
  `project_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_robot` tinyint(1) NULL DEFAULT 0 COMMENT '是否机器人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `member_code`(`member_code`) USING BTREE,
  INDEX `source_code`(`source_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5086 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_log
-- ----------------------------
INSERT INTO `team_project_log` VALUES (1, '054ad8705ce3496f8f7bc0a0881724b3', '6v7be19pwman2fird04gqu53', '示例项目', '创建了项目', 'create', '2021-03-09 20:49:21', '9d0a98c38af34a8c8963870d89bf759a', NULL, '0', 0, '9d0a98c38af34a8c8963870d89bf759a', 'plus', 0);
INSERT INTO `team_project_log` VALUES (2, '2cf08c96ba2f4c5c8980df0b59cd4b68', '6v7be19pwman2fird04gqu53', 'admin', '邀请admin加入项目', 'inviteMember', '2021-03-09 20:49:21', '9d0a98c38af34a8c8963870d89bf759a', NULL, '6v7be19pwman2fird04gqu53', 0, '9d0a98c38af34a8c8963870d89bf759a', 'user-add', 0);

-- ----------------------------
-- Table structure for team_project_member
-- ----------------------------
DROP TABLE IF EXISTS `team_project_member`;
CREATE TABLE `team_project_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `project_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目id',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `join_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加入时间',
  `is_owner` int(0) NULL DEFAULT 0 COMMENT '拥有者',
  `authorize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`project_code`, `member_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目-成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_member
-- ----------------------------
INSERT INTO `team_project_member` VALUES (1, '9d0a98c38af34a8c8963870d89bf759a', '6v7be19pwman2fird04gqu53', '2021-03-09 20:49:21', 1, NULL);

-- ----------------------------
-- Table structure for team_project_menu
-- ----------------------------
DROP TABLE IF EXISTS `team_project_menu`;
CREATE TABLE `team_project_menu`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pid` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
  `url` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '链接',
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `params` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '链接参数',
  `node` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '权限节点',
  `sort` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '菜单排序',
  `status` tinyint(0) UNSIGNED NULL DEFAULT 1 COMMENT '状态(0:禁用,1:启用)',
  `create_by` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `create_at` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `is_inner` tinyint(1) NULL DEFAULT 0 COMMENT '是否内页',
  `values` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数默认值',
  `show_slider` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示侧栏',
  `_values` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 176 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_menu
-- ----------------------------
INSERT INTO `team_project_menu` VALUES (120, 0, '工作台', 'appstore-o', 'home', 'home', ':org', '#', 0, 1, 0, '2018-09-30 16:30:01', 0, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (121, 0, '项目管理', 'project', '#', '#', '', '#', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (122, 121, '项目列表', 'branches', '#', '#', '', '#', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (124, 0, '系统设置', 'setting', '#', '#', '', '#', 100, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (125, 124, '成员管理', 'unlock', '#', '#', '', '#', 10, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (126, 125, '账号列表', '', 'system/account', 'system/account', '', 'project/account/index', 10, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (127, 122, '我的组织', '', 'organization', 'organization', '', 'project/organization/index', 30, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (130, 125, '访问授权', '', 'system/account/auth', 'system/account/auth', '', 'project/auth/index', 20, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (131, 125, '授权页面', '', 'system/account/apply', 'system/account/apply', ':id', 'project/auth/apply', 30, 1, 0, '0000-00-00 00:00:00', 1, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (138, 121, '消息提醒', 'info-circle-o', '#', '#', '', '#', 30, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (139, 138, '站内消息', '', 'notify/notice', 'notify/notice', '', 'project/notify/index', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (140, 138, '系统公告', '', 'notify/system', 'notify/system', '', 'project/notify/index', 10, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (143, 124, '系统管理', 'appstore', '#', '#', '', '#', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (144, 143, '菜单路由', '', 'system/config/menu', 'system/config/menu', '', 'project/menu/menuadd', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (145, 143, '访问节点', '', 'system/config/node', 'system/config/node', '', 'project/node/save', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (148, 124, '个人管理', 'user', '#', '#', '', '#', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (149, 148, '个人设置', '', 'account/setting/base', 'account/setting/base', '', 'project/index/editpersonal', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (150, 148, '安全设置', '', 'account/setting/security', 'account/setting/security', '', 'project/index/editpersonal', 0, 1, 0, '0000-00-00 00:00:00', 1, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (151, 122, '我的项目', '', 'project/list', 'project/list', ':type', 'project/project/index', 0, 1, 0, '0000-00-00 00:00:00', 0, 'my', 1, NULL);
INSERT INTO `team_project_menu` VALUES (152, 122, '回收站', '', 'project/recycle', 'project/recycle', '', 'project/project/index', 20, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (153, 121, '项目空间', 'heat-map', 'project/space/task', 'project/space/task', ':code', '#', 20, 1, 0, '0000-00-00 00:00:00', 1, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (154, 153, '任务详情', '', 'project/space/task/:code/detail', 'project/space/taskdetail', ':code', 'project/task/read', 0, 1, 0, '0000-00-00 00:00:00', 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (155, 122, '我的收藏', '', 'project/list', 'project/list', ':type', 'project/project/index', 10, 1, 0, '0000-00-00 00:00:00', 0, 'collect', 1, NULL);
INSERT INTO `team_project_menu` VALUES (156, 121, '基础设置', 'experiment', '#', '#', '', '#', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (157, 156, '项目模板', '', 'project/template', 'project/template', '', 'project/project_template/index', 0, 1, 0, '0000-00-00 00:00:00', 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (158, 156, '项目列表模板', '', 'project/template/taskStages', 'project/template/taskStages', ':code', 'project/task_stages_template/index', 0, 1, 0, '0000-00-00 00:00:00', 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (159, 122, '已归档项目', '', 'project/archive', 'project/archive', '', 'project/project/index', 10, 1, 0, NULL, 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (160, 0, '团队成员', 'team', '#', '#', '', '#', 0, 1, 0, NULL, 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (161, 153, '项目概况', '', 'project/space/overview', 'project/space/overview', ':code', 'project/index/info', 20, 1, 0, NULL, 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (162, 153, '项目文件', '', 'project/space/files', 'project/space/files', ':code', 'project/index/info', 10, 1, 0, NULL, 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (163, 122, '项目分析', '', 'project/analysis', 'project/analysis', '', 'project/index/info', 5, 1, 0, NULL, 0, '', 1, NULL);
INSERT INTO `team_project_menu` VALUES (164, 160, '团队成员', '', '#', '#', '', '#', 0, 1, 0, NULL, 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (166, 164, '团队成员', '', 'members', 'members', '', 'project/department/index', 0, 1, 0, NULL, 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (167, 164, '成员信息', '', 'members/profile', 'members/profile', ':code', 'project/department/read', 0, 1, 0, NULL, 1, '', 0, NULL);
INSERT INTO `team_project_menu` VALUES (168, 153, '版本管理', '', 'project/space/features', 'project/space/features', ':code', 'project/index/info', 20, 1, 0, NULL, 1, '', 0, NULL);

-- ----------------------------
-- Table structure for team_project_node
-- ----------------------------
DROP TABLE IF EXISTS `team_project_node`;
CREATE TABLE `team_project_node`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `node` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点代码',
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点标题',
  `is_menu` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '是否可设置为菜单',
  `is_auth` tinyint(0) UNSIGNED NULL DEFAULT 1 COMMENT '是否启动RBAC权限控制',
  `is_login` tinyint(0) UNSIGNED NULL DEFAULT 1 COMMENT '是否启动登录控制',
  `create_at` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_system_node_node`(`node`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 654 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目端节点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_node
-- ----------------------------
INSERT INTO `team_project_node` VALUES (360, 'project', '项目管理模块', 0, 1, 1, '2018-09-19 17:48:16');
INSERT INTO `team_project_node` VALUES (361, 'project/index/info', '详情', 0, 1, 1, '2018-09-19 17:48:34');
INSERT INTO `team_project_node` VALUES (362, 'project/index', '基础版块', 0, 1, 1, '2018-09-19 17:48:34');
INSERT INTO `team_project_node` VALUES (363, 'project/index/index', '框架布局', 0, 1, 1, '2018-09-30 16:48:35');
INSERT INTO `team_project_node` VALUES (364, 'project/index/systemconfig', '系统信息', 0, 1, 1, '2018-09-30 16:55:11');
INSERT INTO `team_project_node` VALUES (365, 'project/index/editpersonal', '修改个人资料', 0, 1, 1, '2018-09-30 17:42:42');
INSERT INTO `team_project_node` VALUES (366, 'project/index/uploadavatar', '上传头像', 0, 1, 1, '2018-09-30 17:42:46');
INSERT INTO `team_project_node` VALUES (370, 'project/account', '账号管理', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (371, 'project/account/index', '账号列表', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (372, 'project/organization/index', '组织列表', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (373, 'project/organization/save', '创建组织', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (374, 'project/organization/read', '组织信息', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (375, 'project/organization/edit', '编辑组织', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (376, 'project/organization/delete', '删除组织', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (377, 'project/organization', '组织管理', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (388, 'project/auth/index', '权限列表', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (389, 'project/auth/add', '添加权限角色', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (390, 'project/auth/edit', '编辑权限', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (391, 'project/auth/forbid', '禁用权限', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (392, 'project/auth/resume', '启用权限', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (393, 'project/auth/del', '删除权限', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (394, 'project/auth', '访问授权', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (395, 'project/auth/apply', '应用权限', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (396, 'project/notify/index', '通知列表', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (397, 'project/notify/noreads', '未读通知', 0, 0, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (399, 'project/notify/read', '通知信息', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (401, 'project/notify/delete', '删除通知', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (402, 'project/notify', '通知管理', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (434, 'project/account/auth', '授权管理', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (435, 'project/account/add', '添加账号', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (436, 'project/account/edit', '编辑账号', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (437, 'project/account/del', '删除账号', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (438, 'project/account/forbid', '禁用账号', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (439, 'project/account/resume', '启用账号', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (498, 'project/notify/setreadied', '设置已读', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (499, 'project/notify/batchdel', '批量删除', 0, 1, 1, '0000-00-00 00:00:00');
INSERT INTO `team_project_node` VALUES (500, 'project/auth/setdefault', '设置默认权限', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (501, 'project/department', '部门管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (502, 'project/department/index', '部门列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (503, 'project/department/read', '部门信息', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (504, 'project/department/save', '创建部门', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (505, 'project/department/edit', '编辑部门', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (506, 'project/department/delete', '删除部门', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (507, 'project/department_member', '部门成员管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (508, 'project/department_member/index', '部门成员列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (509, 'project/department_member/searchinvitemember', '搜索部门成员', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (510, 'project/department_member/invitemember', '添加部门成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (511, 'project/department_member/removemember', '移除部门成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (512, 'project/index/changecurrentorganization', '切换当前组织', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (513, 'project/index/editpassword', '修改密码', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (514, 'project/index/uploadimg', '上传图片', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (515, 'project/menu', '菜单管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (516, 'project/menu/menu', '菜单列表', 0, 0, 0, NULL);
INSERT INTO `team_project_node` VALUES (517, 'project/menu/menuadd', '添加菜单', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (518, 'project/menu/menuedit', '编辑菜单', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (519, 'project/menu/menuforbid', '禁用菜单', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (520, 'project/menu/menuresume', '启用菜单', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (521, 'project/menu/menudel', '删除菜单', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (522, 'project/node', '节点管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (523, 'project/node/index', '节点列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (524, 'project/node/alllist', '全部节点列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (525, 'project/node/clear', '清理节点', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (526, 'project/node/save', '编辑节点', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (527, 'project/project', '项目管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (528, 'project/project/index', '项目列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (529, 'project/project/selflist', '个人项目列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (530, 'project/project/save', '创建项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (531, 'project/project/read', '项目信息', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (532, 'project/project/edit', '编辑项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (533, 'project/project/uploadcover', '上传项目封面', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (534, 'project/project/recycle', '项目放入回收站', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (535, 'project/project/recovery', '恢复项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (536, 'project/project/archive', '归档项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (537, 'project/project/recoveryarchive', '取消归档项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (538, 'project/project/quit', '退出项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (539, 'project/project_collect', '项目收藏管理', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (540, 'project/project_collect/collect', '收藏项目', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (541, 'project/project_member', '项目成员管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (542, 'project/project_member/index', '项目成员列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (543, 'project/project_member/searchinvitemember', '搜索项目成员', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (544, 'project/project_member/invitemember', '邀请项目成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (545, 'project/project_template', '项目模板管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (546, 'project/project_template/index', '项目模板列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (547, 'project/project_template/save', '创建项目模板', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (548, 'project/project_template/uploadcover', '上传项目模板封面', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (549, 'project/project_template/edit', '编辑项目模板', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (550, 'project/project_template/delete', '删除项目模板', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (551, 'project/task/index', '任务列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (552, 'project/task/selflist', '个人任务列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (553, 'project/task/read', '任务信息', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (554, 'project/task/save', '创建任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (555, 'project/task/taskdone', '更改任务状态', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (556, 'project/task/assigntask', '指派任务执行者', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (557, 'project/task/sort', '任务排序', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (558, 'project/task/createcomment', '发表任务评论', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (559, 'project/task/edit', '编辑任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (560, 'project/task/like', '点赞任务', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (561, 'project/task/star', '收藏任务', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (562, 'project/task/recycle', '移动任务到回收站', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (563, 'project/task/recovery', '恢复任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (564, 'project/task/delete', '删除任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (565, 'project/task', '任务管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (569, 'project/task_member/index', '任务成员列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (570, 'project/task_member/searchinvitemember', '搜索任务成员', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (571, 'project/task_member/invitemember', '添加任务成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (572, 'project/task_member/invitememberbatch', '批量添加任务成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (573, 'project/task_member', '任务成员管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (574, 'project/task_stages', '任务分组管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (575, 'project/task_stages/index', '任务分组列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (576, 'project/task_stages/tasks', '任务分组任务列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (577, 'project/task_stages/sort', '任务分组排序', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (578, 'project/task_stages/save', '添加任务分组', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (579, 'project/task_stages/edit', '编辑任务分组', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (580, 'project/task_stages/delete', '删除任务分组', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (581, 'project/task_stages_template/index', '任务分组模板列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (582, 'project/task_stages_template/save', '创建任务分组模板', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (583, 'project/task_stages_template/edit', '编辑任务分组模板', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (584, 'project/task_stages_template/delete', '删除任务分组模板', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (585, 'project/task_stages_template', '任务分组模板管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (587, 'project/project_member/removemember', '移除项目成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (588, 'project/task/datetotalforproject', '任务统计', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (589, 'project/task/tasksources', '任务资源列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (590, 'project/file', '文件管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (591, 'project/file/index', '文件列表', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (592, 'project/file/read', '文件详情', 0, 0, 1, NULL);
INSERT INTO `team_project_node` VALUES (593, 'project/file/uploadfiles', '上传文件', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (594, 'project/file/edit', '编辑文件', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (595, 'project/file/recycle', '文件移至回收站', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (596, 'project/file/recovery', '恢复文件', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (597, 'project/file/delete', '删除文件', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (598, 'project/project/getlogbyselfproject', '项目概况', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (599, 'project/source_link', '资源关联管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (600, 'project/source_link/delete', '取消关联', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (601, 'project/task/tasklog', '任务动态', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (602, 'project/task/recyclebatch', '批量移动任务到回收站', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (603, 'project/invite_link', '邀请链接管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (604, 'project/invite_link/save', '创建邀请链接', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (605, 'project/task/setprivate', '设置任务隐私模式', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (606, 'project/account/read', '账号信息', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (607, 'project/task/batchassigntask', '批量指派任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (608, 'project/task/tasktotags', '任务标签', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (609, 'project/task/settag', '设置任务标签', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (610, 'project/task_tag', '任务标签管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (611, 'project/task_tag/index', '任务标签列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (612, 'project/task_tag/save', '创建任务标签', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (613, 'project/task_tag/edit', '编辑任务标签', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (614, 'project/task_tag/delete', '删除任务标签', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (615, 'project/project_features', '项目版本库管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (616, 'project/project_features/index', '版本库列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (617, 'project/project_features/save', '添加版本库', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (618, 'project/project_features/edit', '编辑版本库', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (619, 'project/project_features/delete', '删除版本库', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (620, 'project/project_version', '项目版本管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (621, 'project/project_version/index', '项目版本列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (622, 'project/project_version/save', '添加项目版本', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (623, 'project/project_version/edit', '编辑项目版本', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (624, 'project/project_version/changestatus', '更改项目版本状态', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (625, 'project/project_version/read', '项目版本详情', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (626, 'project/project_version/addversiontask', '关联项目版本任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (627, 'project/project_version/removeversiontask', '移除项目版本任务', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (628, 'project/project_version/delete', '删除项目版本', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (629, 'project/task/getlistbytasktag', '标签任务列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (630, 'project/task_workflow', '任务流转管理', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (631, 'project/task_workflow/index', '任务流转列表', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (632, 'project/task_workflow/save', '添加任务流转', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (633, 'project/task_workflow/edit', '编辑任务流转', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (634, 'project/task_workflow/delete', '删除任务流转', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (635, 'project/department_member/detail', '部门成员详情', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (636, 'project/department_member/uploadfile', '上传头像', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (637, 'project/task/savetaskworktime', '保存任务流转', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (638, 'project/task/edittaskworktime', '编辑任务流转', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (639, 'project/task/deltaskworktime', '删除任务流转', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (640, 'project/task/uploadfile', '上传文件', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (641, 'project/task_stages/_getall', '创建规则', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (642, 'project/login/_bindmobile', '绑定号码', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (643, 'project/login/_bindmail', '绑定邮箱', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (644, 'project/task/_taskworktimelist', '任务工时', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (645, 'project/project_member/_listforinvite', '邀请新成员', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (646, 'project/project_info', '项目信息', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (647, 'project/project/_getprojectreport', '项目报告', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (648, 'project/project/_projectstats', '项目统计资料', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (649, 'project/project/gettoplist', '项目排名', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (650, 'project/project/taskpriority', '项目优先级分布', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (651, 'project/organization/_getorglist', '组织列表（权限）', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (652, 'project/project/analysis', '项目分析', 0, 1, 1, NULL);
INSERT INTO `team_project_node` VALUES (653, 'project/project_info/index', '项目信息总览', 0, 1, 1, NULL);

-- ----------------------------
-- Table structure for team_project_report
-- ----------------------------
DROP TABLE IF EXISTS `team_project_report`;
CREATE TABLE `team_project_report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `project_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `date` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日期',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `union`(`project_code`, `date`) USING BTREE,
  INDEX `code`(`project_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 195 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目报表统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_project_template
-- ----------------------------
DROP TABLE IF EXISTS `team_project_template`;
CREATE TABLE `team_project_template`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `sort` tinyint(0) NULL DEFAULT 0,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `organization_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '组织id',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `member_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建人',
  `is_system` tinyint(1) NULL DEFAULT 0 COMMENT '系统默认',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_template
-- ----------------------------
INSERT INTO `team_project_template` VALUES (11, '产品进展', '适用于互联网产品人员对产品计划、跟进及发布管理', 0, '2018-04-30 22:15:10', 'd85f1bvwpml2nhxe94zu7tyi', '6v7be19pwman2fird04gqu53', 'http://easyproject.net/static/image/default/cover.png', '', 1);
INSERT INTO `team_project_template` VALUES (12, '需求管理', '适用于产品部门对需求的收集、评估及反馈管理', 0, '2018-04-30 22:16:29', 'd85f1bvwpml2nhxe92zu7tyi', '6v7be19pwman2fird04gqu53', 'http://easyproject.net/static/image/default/cover.png', '', 1);

-- ----------------------------
-- Table structure for team_project_version
-- ----------------------------
DROP TABLE IF EXISTS `team_project_version`;
CREATE TABLE `team_project_version`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本库名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织id',
  `publish_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `start_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态。0：未开始，1：进行中，2：延期发布，3：已发布',
  `schedule` int(0) NULL DEFAULT 0 COMMENT '进度百分比',
  `plan_publish_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预计发布时间',
  `features_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本库编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `organization_code`(`organization_code`) USING BTREE,
  INDEX `features_code`(`features_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目版本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_project_version_log
-- ----------------------------
DROP TABLE IF EXISTS `team_project_version_log`;
CREATE TABLE `team_project_version_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日志描述',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'create' COMMENT '操作类型',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加时间',
  `source_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '任务id',
  `project_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `features_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本库编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `project_code`(`project_code`) USING BTREE,
  INDEX `features_code`(`features_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_source_link
-- ----------------------------
DROP TABLE IF EXISTS `team_source_link`;
CREATE TABLE `team_source_link`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `source_type` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源类型',
  `source_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源编号',
  `link_type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联类型',
  `link_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联编号',
  `organization_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织编码',
  `create_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_system_config
-- ----------------------------
DROP TABLE IF EXISTS `team_system_config`;
CREATE TABLE `team_system_config`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置编码',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_system_config_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_system_config
-- ----------------------------
INSERT INTO `team_system_config` VALUES (1, 'app_name', 'Pear Project');
INSERT INTO `team_system_config` VALUES (2, 'site_name', 'Pear Project');
INSERT INTO `team_system_config` VALUES (3, 'app_version', '2.0');
INSERT INTO `team_system_config` VALUES (4, 'site_copy', 'Copyright © 2018 Pear Project出品');
INSERT INTO `team_system_config` VALUES (5, 'browser_icon', '');
INSERT INTO `team_system_config` VALUES (6, 'tongji_baidu_key', '');
INSERT INTO `team_system_config` VALUES (7, 'miitbeian', '粤ICP备16eeeee2号-2');
INSERT INTO `team_system_config` VALUES (8, 'storage_type', 'local');
INSERT INTO `team_system_config` VALUES (9, 'storage_local_exts', 'png,jpg,rar,doc,icon,mp4,zip,gif,jpeg,bmp,webp,mp4,m3u8,rmvb,avi,swf,3gp,mkv,flv,txt,docx,pages,epub,pdf,numbers,csv,xls,xlsx,keynote,ppt,pptx,mp3,wav,wma,ogg,aac,flac;');
INSERT INTO `team_system_config` VALUES (10, 'storage_qiniu_bucket', 'static');
INSERT INTO `team_system_config` VALUES (11, 'storage_qiniu_domain', 'example.xyz');
INSERT INTO `team_system_config` VALUES (12, 'storage_qiniu_access_key', 'example');
INSERT INTO `team_system_config` VALUES (13, 'storage_qiniu_secret_key', 'example');
INSERT INTO `team_system_config` VALUES (14, 'storage_oss_bucket', 'vilson-static');
INSERT INTO `team_system_config` VALUES (15, 'storage_oss_endpoint', 'oss-cn-shenzhen.aliyuncs.com');
INSERT INTO `team_system_config` VALUES (16, 'storage_oss_domain', 'example.xyz');
INSERT INTO `team_system_config` VALUES (17, 'storage_oss_keyid', 'example');
INSERT INTO `team_system_config` VALUES (18, 'storage_oss_secret', 'example');
INSERT INTO `team_system_config` VALUES (34, 'wechat_appid', '');
INSERT INTO `team_system_config` VALUES (35, 'wechat_appkey', '');
INSERT INTO `team_system_config` VALUES (36, 'storage_oss_is_https', 'https');
INSERT INTO `team_system_config` VALUES (37, 'wechat_type', 'thr');
INSERT INTO `team_system_config` VALUES (38, 'wechat_token', 'test');
INSERT INTO `team_system_config` VALUES (39, 'wechat_appsecret', '');
INSERT INTO `team_system_config` VALUES (40, 'wechat_encodingaeskey', '');
INSERT INTO `team_system_config` VALUES (41, 'wechat_thr_appid', '');
INSERT INTO `team_system_config` VALUES (42, 'wechat_thr_appkey', '');
INSERT INTO `team_system_config` VALUES (43, 'storage_qiniu_is_https', 'https');
INSERT INTO `team_system_config` VALUES (44, 'single_mode', '0');
INSERT INTO `team_system_config` VALUES (45, 'single_org_code', '');
INSERT INTO `team_system_config` VALUES (46, 'app_desc', '轻量级任务协作平台');
INSERT INTO `team_system_config` VALUES (47, 'app_title', '任务协同项目管理');

-- ----------------------------
-- Table structure for team_system_log
-- ----------------------------
DROP TABLE IF EXISTS `team_system_log`;
CREATE TABLE `team_system_log`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ip` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作者IP地址',
  `node` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '当前操作节点',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作人用户名',
  `action` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作行为',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作内容描述',
  `create_at` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task
-- ----------------------------
DROP TABLE IF EXISTS `team_task`;
CREATE TABLE `team_task`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `project_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '项目编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pri` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '紧急程度',
  `execute_status` enum('wait','doing','done','pause','cancel','closed') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'wait' COMMENT '执行状态',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
  `create_by` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建日期',
  `assign_to` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '指派给谁',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '回收站',
  `stage_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '任务列表',
  `task_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务标签',
  `done` tinyint(0) NULL DEFAULT 0 COMMENT '是否完成',
  `begin_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '截止时间',
  `remind_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提醒时间',
  `pcode` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父任务id',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `like` int(0) NULL DEFAULT 0 COMMENT '点赞数',
  `star` int(0) NULL DEFAULT 0 COMMENT '收藏数',
  `deleted_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除时间',
  `private` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐私模式',
  `id_num` int(0) NULL DEFAULT 1 COMMENT '任务id编号',
  `path` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '上级任务路径',
  `schedule` int(0) NULL DEFAULT 0 COMMENT '进度百分比',
  `version_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '版本id',
  `features_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '版本库id',
  `work_time` int(0) NULL DEFAULT 0 COMMENT '预估工时',
  `status` int(0) NULL DEFAULT NULL COMMENT '\'执行状态。0：未开始，1：已完成，2：进行中，3：挂起，4：测试中\'',
  `liked` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `project_code`) USING BTREE,
  UNIQUE INDEX `task`(`code`) USING BTREE,
  INDEX `stage_code`(`stage_code`) USING BTREE,
  INDEX `project_code`(`project_code`) USING BTREE,
  INDEX `pcode`(`pcode`) USING BTREE,
  INDEX `sort`(`sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12502 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_like
-- ----------------------------
DROP TABLE IF EXISTS `team_task_like`;
CREATE TABLE `team_task_like`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '任务ID',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_member
-- ----------------------------
DROP TABLE IF EXISTS `team_task_member`;
CREATE TABLE `team_task_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '任务ID',
  `is_executor` tinyint(1) NULL DEFAULT 0 COMMENT '执行者',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `join_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_owner` tinyint(1) NULL DEFAULT 0 COMMENT '是否创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 349 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务-成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_stages
-- ----------------------------
DROP TABLE IF EXISTS `team_task_stages`;
CREATE TABLE `team_task_stages`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `project_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '项目id',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 278 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务列表表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_stages
-- ----------------------------
INSERT INTO `team_task_stages` VALUES (1, '需求收集', '9d0a98c38af34a8c8963870d89bf759a', 0, NULL, '2021-03-09 20:49:21', 'd10135f3e33447ffa7497c73fc3805cd', 0);
INSERT INTO `team_task_stages` VALUES (2, '评估确认', '9d0a98c38af34a8c8963870d89bf759a', 1, NULL, '2021-03-09 20:49:21', 'c9142514ebe149c9a9648cfb03079bf1', 0);
INSERT INTO `team_task_stages` VALUES (3, '需求暂缓', '9d0a98c38af34a8c8963870d89bf759a', 2, NULL, '2021-03-09 20:49:21', '261bcaba626d437dade4b028d853c4c2', 0);
INSERT INTO `team_task_stages` VALUES (4, '研发中', '9d0a98c38af34a8c8963870d89bf759a', 3, NULL, '2021-03-09 20:49:21', '91dd386bf2374ca6beede87379395b28', 0);
INSERT INTO `team_task_stages` VALUES (5, '内测中', '9d0a98c38af34a8c8963870d89bf759a', 4, NULL, '2021-03-09 20:49:21', 'ccd76dbaa3114ac79ac56e29dde33a84', 0);
INSERT INTO `team_task_stages` VALUES (6, '通知用户', '9d0a98c38af34a8c8963870d89bf759a', 5, NULL, '2021-03-09 20:49:21', '2a00acbf5abe4b2db5494cfb0b03fc24', 0);
INSERT INTO `team_task_stages` VALUES (7, '已完成&归档', '9d0a98c38af34a8c8963870d89bf759a', 6, NULL, '2021-03-09 20:49:21', 'f06c0c8bd9584d07baf61c6795634c1a', 0);

-- ----------------------------
-- Table structure for team_task_stages_template
-- ----------------------------
DROP TABLE IF EXISTS `team_task_stages_template`;
CREATE TABLE `team_task_stages_template`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `project_template_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '项目id',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT 0,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务列表模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_stages_template
-- ----------------------------
INSERT INTO `team_task_stages_template` VALUES (1, '内部检验', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:01:04', 0, '4phrcltwygziu2s13jxbaqv8');
INSERT INTO `team_task_stages_template` VALUES (2, '验收', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:01:09', 0, 'qxi9n42p0w57jtrmyhz8gl3c');
INSERT INTO `team_task_stages_template` VALUES (3, '需求收集', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:01:30', 0, '48h13usk7en6ljyxbqgiw02z');
INSERT INTO `team_task_stages_template` VALUES (4, '评估确认', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:17', 0, '70z1fpxytvchbadkgsieowuj');
INSERT INTO `team_task_stages_template` VALUES (5, '需求暂缓', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:22', 0, 'bkyunf9jr2c37m4oi81sxzqp');
INSERT INTO `team_task_stages_template` VALUES (6, '研发中', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:27', 0, 'zu0vrhpoi835klgxqndmf6w9');
INSERT INTO `team_task_stages_template` VALUES (7, '内测中', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:32', 0, 'j4d5l7s6rgvk9o32ayt1uefc');
INSERT INTO `team_task_stages_template` VALUES (8, '通知用户', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:40', 0, 'cjk6al7f2ygp39des148iwzh');
INSERT INTO `team_task_stages_template` VALUES (9, '已完成&归档', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:45', 0, 'vn6dxyzme1g8ucbl3ikq0awt');
INSERT INTO `team_task_stages_template` VALUES (10, '产品计划', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:03', 0, '3atxfsv5rhz64pk8jl0enqd2');
INSERT INTO `team_task_stages_template` VALUES (11, '即将发布', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:09', 0, '1nucptea9b2vl7yfj8xgz4d6');
INSERT INTO `team_task_stages_template` VALUES (12, '测试', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:13', 0, 'pfidejaq2vn653h8zmsytrlb');
INSERT INTO `team_task_stages_template` VALUES (13, '准备发布', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:17', 0, 'uc1etmw4k5gys8jfpdbo7zrh');

-- ----------------------------
-- Table structure for team_task_tag
-- ----------------------------
DROP TABLE IF EXISTS `team_task_tag`;
CREATE TABLE `team_task_tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `project_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `color` enum('blue','red','orange','green','brown','purple') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'blue' COMMENT '颜色',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_to_tag
-- ----------------------------
DROP TABLE IF EXISTS `team_task_to_tag`;
CREATE TABLE `team_task_to_tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `task_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `tag_code` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务标签映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_work_time
-- ----------------------------
DROP TABLE IF EXISTS `team_task_work_time`;
CREATE TABLE `team_task_work_time`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '任务ID',
  `member_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成员id',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `begin_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `num` int(0) NULL DEFAULT 0 COMMENT '工时',
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务工时表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_workflow
-- ----------------------------
DROP TABLE IF EXISTS `team_task_workflow`;
CREATE TABLE `team_task_workflow`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '组织id',
  `project_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '项目id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务工作流表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_task_workflow_rule
-- ----------------------------
DROP TABLE IF EXISTS `team_task_workflow_rule`;
CREATE TABLE `team_task_workflow_rule`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '规则类型，0：任务分组，1：人员，2：条件',
  `object_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象id',
  `action` tinyint(1) NULL DEFAULT 0 COMMENT '场景。0：任何条件，1：被完成，2：被重做，3：设置执行人，4：截止时间，5：优先级',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workflow_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '工作流id',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务工作流规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_user_token
-- ----------------------------
DROP TABLE IF EXISTS `team_user_token`;
CREATE TABLE `team_user_token`  (
  `token_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '令牌编号',
  `user_id` int(0) UNSIGNED NOT NULL COMMENT '用户编号',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录令牌',
  `login_time` int(0) UNSIGNED NOT NULL COMMENT '登录时间',
  `client_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端类型 android wap',
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`token_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'PC端登录令牌表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
