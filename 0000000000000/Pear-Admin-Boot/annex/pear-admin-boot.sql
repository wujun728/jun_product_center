/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : pear-admin

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 25/07/2021 02:04:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `parent_menu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单',
  `parent_menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `tree_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树表主键',
  `tree_parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树表父级字段',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES ('1418977532593569792', 'sys_power', '权限列表', NULL, NULL, 'SysPower', 'tree', 'com.pearadmin.system', 'system', 'power', '权限列表', '就眠仪式', '0', '/', 'null', '', '2021-07-25 00:53:01', '', '2021-07-25 01:31:08', '权限列表', '1', '系统管理', 'id', 'parent_id');
INSERT INTO `gen_table` VALUES ('1418978010781974528', 'sys_user', '用户列表', NULL, NULL, 'SysUser', 'crud', 'com.pearadmin.system', 'system', 'user', '用户列表', '就眠仪式', '0', '/', 'null', '', '2021-07-25 00:54:55', '', '2021-07-25 01:04:45', '用户列表', '0', '顶级权限', '3', '3');

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES ('1418977532627124224', '1418977532593569792', 'power_id', '权限编号', 'char(19)', 'String', 'powerId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', 'input', 1, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532648095744', '1418977532593569792', 'power_name', '权限名称', 'varchar(255)', 'String', 'powerName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', 'input', 2, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532660678656', '1418977532593569792', 'power_type', '权限类型', 'char(1)', 'String', 'powerType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'input', 3, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532673261568', '1418977532593569792', 'power_code', '权限标识', 'char(30)', 'String', 'powerCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 4, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532681650176', '1418977532593569792', 'power_url', '权限路径', 'varchar(255)', 'String', 'powerUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 5, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532690038784', '1418977532593569792', 'open_type', '打开方式', 'char(10)', 'String', 'openType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'input', 6, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532698427392', '1418977532593569792', 'parent_id', '父类编号', 'char(19)', 'String', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 7, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532702621696', '1418977532593569792', 'icon', '图标', 'varchar(128)', 'String', 'icon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 8, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532711010304', '1418977532593569792', 'sort', '排序', 'int', 'Long', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 9, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532715204608', '1418977532593569792', 'create_by', '创建人', 'char(19)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', 'input', 10, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532723593216', '1418977532593569792', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', 'input', 11, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532727787520', '1418977532593569792', 'update_by', '修改人', 'char(19)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', 'input', 12, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532740370432', '1418977532593569792', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', 'input', 13, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532748759040', '1418977532593569792', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', 'input', 14, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418977532757147648', '1418977532593569792', 'enable', '是否开启', 'char(1)', 'String', 'enable', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 15, '', '2021-07-25 00:53:01', NULL, '2021-07-25 01:31:08');
INSERT INTO `gen_table_column` VALUES ('1418978010802946048', '1418978010781974528', 'user_id', '编号', 'char(19)', 'String', 'userId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', 'input', 1, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010811334656', '1418978010781974528', 'username', '账户', 'char(20)', 'String', 'username', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', 'input', 2, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010815528960', '1418978010781974528', 'password', '密码', 'char(60)', 'String', 'password', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 3, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010819723264', '1418978010781974528', 'salt', '姓名', 'char(10)', 'String', 'salt', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 4, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010828111872', '1418978010781974528', 'status', '状态', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', 'input', 5, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010832306176', '1418978010781974528', 'real_name', '姓名', 'char(8)', 'String', 'realName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', 'input', 6, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010836500480', '1418978010781974528', 'email', '邮箱', 'char(20)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 7, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010840694784', '1418978010781974528', 'avatar', '头像', 'varchar(30)', 'String', 'avatar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 8, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010849083392', '1418978010781974528', 'sex', '性别', 'char(1)', 'String', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', 'input', 9, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010853277696', '1418978010781974528', 'phone', '电话', 'char(11)', 'String', 'phone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 10, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010857472000', '1418978010781974528', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', 'input', 11, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010861666304', '1418978010781974528', 'create_by', '创建人', 'char(1)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', 'input', 12, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010874249216', '1418978010781974528', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', 'input', 13, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010882637824', '1418978010781974528', 'update_by', '修改人', 'char(1)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', 'input', 14, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010886832128', '1418978010781974528', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', 'input', 15, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010895220736', '1418978010781974528', 'enable', '是否启用', 'char(1)', 'String', 'enable', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 16, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010903609344', '1418978010781974528', 'login', '是否登录', 'char(1)', 'String', 'login', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 17, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010907803648', '1418978010781974528', 'dept_id', '部门编号', 'char(19)', 'String', 'deptId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'input', 18, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');
INSERT INTO `gen_table_column` VALUES ('1418978010916192256', '1418978010781974528', 'last_time', '最后一次登录时间', 'datetime', 'Date', 'lastTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', 'input', 19, '', '2021-07-25 00:54:55', NULL, '2021-07-25 01:04:45');

-- ----------------------------
-- Table structure for schedule_calendars
-- ----------------------------
DROP TABLE IF EXISTS `schedule_calendars`;
CREATE TABLE `schedule_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `schedule_cron_triggers`;
CREATE TABLE `schedule_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `schedule_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `schedule_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `schedule_fired_triggers`;
CREATE TABLE `schedule_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(0) NOT NULL,
  `SCHED_TIME` bigint(0) NOT NULL,
  `PRIORITY` int(0) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_group
-- ----------------------------
DROP TABLE IF EXISTS `schedule_group`;
CREATE TABLE `schedule_group`  (
  `group_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '注释',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_group
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job_details
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_details`;
CREATE TABLE `schedule_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_locks
-- ----------------------------
DROP TABLE IF EXISTS `schedule_locks`;
CREATE TABLE `schedule_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_locks
-- ----------------------------
INSERT INTO `schedule_locks` VALUES ('PearScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for schedule_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_log`;
CREATE TABLE `schedule_log`  (
  `log_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务日志id',
  `job_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(0) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(0) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_log
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `schedule_paused_trigger_grps`;
CREATE TABLE `schedule_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `schedule_scheduler_state`;
CREATE TABLE `schedule_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(0) NOT NULL,
  `CHECKIN_INTERVAL` bigint(0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `schedule_simple_triggers`;
CREATE TABLE `schedule_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(0) NOT NULL,
  `REPEAT_INTERVAL` bigint(0) NOT NULL,
  `TIMES_TRIGGERED` bigint(0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `schedule_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `schedule_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `schedule_simprop_triggers`;
CREATE TABLE `schedule_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(0) NULL DEFAULT NULL,
  `INT_PROP_2` int(0) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(0) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(0) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `schedule_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `schedule_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_triggers
-- ----------------------------
DROP TABLE IF EXISTS `schedule_triggers`;
CREATE TABLE `schedule_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(0) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(0) NULL DEFAULT NULL,
  `PRIORITY` int(0) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(0) NOT NULL,
  `END_TIME` bigint(0) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(0) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `schedule_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `schedule_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置标识',
  `config_name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置名称',
  `config_code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置标识',
  `config_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置值',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `config_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置类型',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '1', 'oss_point', 'oss-cn-beijing.aliyuncs.com', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_config` VALUES ('1307313917164257280', '网站描述', 'system_desc', '网站描述', '2020-11-08 19:19:32', NULL, NULL, NULL, '网站描述', 'custom');
INSERT INTO `sys_config` VALUES ('1309118169381601280', '网站数据', 'system_meta', '网站数据', '2020-11-03 19:20:48', NULL, NULL, NULL, '网站数据', 'custom');
INSERT INTO `sys_config` VALUES ('1356140265433202688', '系统配置', 'main_from', '854085467@qq.com', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1356140265865216000', '系统配置', 'main_user', '854085467', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1356140266297229312', '系统配置', 'main_pass', '123456', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1356140266754408448', '系统配置', 'main_port', '456', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1356140267211587584', '系统配置', 'main_host', 'smtp.qq.com', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1356178612746715136', '系统配置', 'oss_path', 'D://upload', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1356178613115813888', '系统配置', 'oss_type', 'local', NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1370975131278508032', '上传方式', 'upload_kind', NULL, NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('1370975131630829568', '上传路径', 'upload_path', NULL, NULL, NULL, NULL, NULL, NULL, 'system');
INSERT INTO `sys_config` VALUES ('2', '2', 'oss_key', 'LTAI4G8ZDXDU6DiibSVd8G2b', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_config` VALUES ('3', '3', 'oss_secret', '9apyAWE7Xfu7NP5jgFHFdXeyPa28jL', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_config` VALUES ('4', '4', 'oss_bucket', 'pearadmin-bbs', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级编号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `leader` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门状态',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '济南总公司', 1, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('10', '8', '设计部', 3, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('1316361008259792896', '1316360459930042368', '软件部', 1, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('1316361192645591040', '1316360459930042368', '市场部', 1, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('1377824449830584320', '3', '财务部', 1, '就眠仪式', '15553726531', '854085467@qq.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('1377825171905183744', '8', '财务部', 1, '就眠仪式', '15553726531', '854085467@qq.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('3', '1', '杭州分公司', 1, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '浙江杭州');
INSERT INTO `sys_dept` VALUES ('4', '2', '软件部', 2, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('5', '2', '市场部', 2, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('6', '3', '软件部', 3, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '浙江杭州');
INSERT INTO `sys_dept` VALUES ('7', '3', '设计部', 4, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('8', '1', '深圳分公司', 3, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');
INSERT INTO `sys_dept` VALUES ('9', '8', '软件部', 3, '就眠仪式', '15553726531', 'pearadmin@gmail.com', '0', NULL, NULL, NULL, NULL, NULL, '山东济南');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `data_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标识',
  `data_label` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标签',
  `data_value` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `type_code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属类型',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否默认',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '男', '0', 'system_user_sex', '0', NULL, NULL, NULL, NULL, '描述', '1');
INSERT INTO `sys_dict_data` VALUES ('1302833449496739840', '字典名称', '字典值', 'dict_code', '1', NULL, NULL, NULL, NULL, 'aw', '0');
INSERT INTO `sys_dict_data` VALUES ('1317401149287956480', '男', 'boy', 'user_sex', NULL, NULL, NULL, NULL, NULL, '男 : body', '0');
INSERT INTO `sys_dict_data` VALUES ('1317402976670711808', '女', 'girl', 'user_sex', NULL, NULL, NULL, NULL, NULL, '女 : girl', '0');
INSERT INTO `sys_dict_data` VALUES ('1370411072367886336', '公告', 'public', 'sys_notice_type', NULL, NULL, NULL, NULL, NULL, '公告', '0');
INSERT INTO `sys_dict_data` VALUES ('1370411179544936448', '私信', 'private', 'sys_notice_type', NULL, NULL, NULL, NULL, NULL, '私信', '0');
INSERT INTO `sys_dict_data` VALUES ('1387076909438861312', '短信', 'smmm', 'sys_notice_type', NULL, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_dict_data` VALUES ('2', '女', '1', 'system_user_sex', '1', NULL, NULL, NULL, NULL, '描述', '0');
INSERT INTO `sys_dict_data` VALUES ('447572898392182784', 'awd', 'awd', 'dict_code', '1', NULL, NULL, NULL, NULL, 'awd', '0');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标识',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型名称',
  `type_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型标识',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型描述',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1317360314219495424', '登录类型', 'sys_notice_type', '登录类型', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('1317400519127334912', '用户类型', 'user_status', '用户类型', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('1317400823096934400', '配置类型', 'config_type', '配置类型', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('1370410853110644736', '消息类型', 'sys_notice_type', '消息类型', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('455184568505470976', '用户性别', 'user_sex', '用户性别', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES ('455184935989415936', '全局状态', 'sys_status', '状态描述\n', '0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标识',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件描述',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `target_date` datetime(0) NULL DEFAULT NULL COMMENT '所属时间',
  `file_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logging
-- ----------------------------
DROP TABLE IF EXISTS `sys_logging`;
CREATE TABLE `sys_logging`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '相应消息体',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `request_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `operate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `operate_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作路径',
  `operate_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作地址',
  `request_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `response_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相应消息体',
  `success` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否成功',
  `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `request_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求消息体',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '使用浏览器',
  `system_os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `logging_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型，登录日志，操作日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logging
-- ----------------------------
INSERT INTO `sys_logging` VALUES ('1418995375045345280', '登录', '/login', 'OTHER', 'POST', 'admin', '/login', '127.0.0.1', NULL, NULL, '1', NULL, '2021-07-25 02:03:56', '登录成功', NULL, '谷歌浏览器', 'Windows', 'LOGIN');
INSERT INTO `sys_logging` VALUES ('1418995380669906944', '主页', '/index', 'ADD', 'GET', 'admin', '/index', '127.0.0.1', NULL, NULL, '1', NULL, '2021-07-25 02:03:57', '返回 Index 主页视图', NULL, '谷歌浏览器', 'Windows', 'OPERATE');
INSERT INTO `sys_logging` VALUES ('1418995385006817280', '查询用户', '/system/user/data', 'QUERY', 'GET', 'admin', '/system/user/data', '127.0.0.1', NULL, NULL, '1', NULL, '2021-07-25 02:03:58', '查询用户', 'page=1&limit=10', '谷歌浏览器', 'Windows', 'OPERATE');

-- ----------------------------
-- Table structure for sys_mail
-- ----------------------------
DROP TABLE IF EXISTS `sys_mail`;
CREATE TABLE `sys_mail`  (
  `mail_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮件id(主键)',
  `receiver` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '收件人邮箱',
  `subject` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件主体',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮件正文',
  `create_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`mail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_mail
-- ----------------------------
INSERT INTO `sys_mail` VALUES ('1349598576807772160', '1218600762@qq.com', 'macbook pro', '13.3寸\nm1处理器\n16G内存', 'admin', '2021-01-14 06:06:23');
INSERT INTO `sys_mail` VALUES ('1357215518368464896', 'BoscoKuo@aliyun.com', '湖人总冠军', '湖人总冠军', 'admin', '2021-02-04 06:33:36');
INSERT INTO `sys_mail` VALUES ('1357219037586653184', 'BoscoKuo@aliyun.com', 'LebronJames', 'Lakers', 'admin', '2021-02-04 06:47:35');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `sender` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送人',
  `accept` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收者',
  `type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1370769290961092608', '公告测试', '公告测试', '1309861917694623744', NULL, 'public', NULL, '2021-03-14 00:10:41', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370769348204953600', '私信测试', '私信测试', '1309861917694623744', '1310409555649232897', 'private', NULL, '2021-03-14 00:10:55', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370771980034244608', '公告测试', '公告测试', '1309861917694623744', NULL, 'public', NULL, '2021-03-14 00:21:22', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370772014771470336', '公告测试', '公告测试', '1309861917694623744', NULL, 'public', NULL, '2021-03-14 00:21:31', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370772050439831552', '公告测试', '公告测试', '1309861917694623744', NULL, 'public', NULL, '2021-03-14 00:21:39', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370772089446858752', '私信测试', '私信测试', '1309861917694623744', '1310409555649232897', 'private', NULL, '2021-03-14 00:21:48', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370772143918284800', '私信测试', '私信测试', '1309861917694623744', '1310409555649232897', 'private', NULL, '2021-03-14 00:22:01', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370772363838226432', '私信测试', '私信测试', '1309861917694623744', '1349021166525743105', 'private', NULL, '2021-03-14 00:22:54', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370772466212798464', '私信测试', '私信测试', '1309861917694623744', '1349021166525743105', 'private', NULL, '2021-03-14 00:23:18', NULL, NULL, NULL);
INSERT INTO `sys_notice` VALUES ('1370971086266564608', '私信测试', '私信测试', '1309861917694623744', '1309861917694623744', 'private', NULL, '2021-03-14 13:32:33', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `power_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编号',
  `power_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `power_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型',
  `power_code` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `power_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限路径',
  `open_type` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打开方式',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父类编号',
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否开启',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES ('1', '系统管理', '0', '', '', NULL, '0', 'layui-icon layui-icon-set-fill', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1284020948269268992', '用户列表', '2', 'sys:user:data', '', '', '2', 'layui-icon-username', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1284022967767924736', '用户保存', '2', 'sys:user:add', '', '', '2', 'layui-icon-username', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1302180351979814912', '布局构建', '1', 'generator:from:main', 'component/code/index.html', '_iframe', '442417411065516032', 'layui-icon-senior', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1304387665067507712', '数据字典', '1', 'sys:dictType:main', '/system/dictType/main', '_iframe', '1', 'layui-icon layui-icon layui-icon layui-icon layui-icon-flag', 4, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1304793451996381184', '文件管理', '1', 'sys:file:main', '/system/file/main', '_iframe', '1', 'layui-icon layui-icon layui-icon-read', 5, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1305870685385523200', '百度一下', '1', '', 'http://www.baidu.com', '0', '474356044148117504', 'layui-icon-search', 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('1305875436139446272', '百度一下', '1', 'http://www.baidu.com', 'http://www.baidu.com', '0', '451002662209589248', 'layui-icon-search', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1307299332784914432', '系统配置', '1', 'sys:config:main', '/system/config/main', '0', '1', 'layui-icon layui-icon layui-icon-note', 6, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1307562196556840960', '工作流程', '0', '', '', '0', '0', 'layui-icon layui-icon-chat', 5, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1307562519451140096', '模型管理', '1', '/process/model/main', '/process/model/main', '_iframe', '1307562196556840960', 'layui-icon layui-icon-rate', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1308571483794046976', '流程定义', '1', 'process:defined:main', '/process/defined/main', '_iframe', '1307562196556840960', 'layui-icon layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310206853057085440', '用户修改', '2', 'sys:user:edit', '', '', '2', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310208636370288640', '用户删除', '2', 'sys:user:remove', '', '', '2', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310209696916832256', '角色新增', '2', 'sys:role:add', '', '', '3', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310209900478988288', '角色删除', '2', 'sys:role:remove', '', '', '3', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310210054728712192', '角色修改', '2', 'sys:role:edit', '', '', '3', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310211965188046848', '角色授权', '2', 'sys:role:power', '', '', '3', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310226416867999744', '权限列表', '2', 'sys:power:data', '', '', '4', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310226976593674240', '权限新增', '2', 'sys:power:add', '', '', '4', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310227130998587392', '权限修改', '2', 'sys:power:edit', '', '', '4', 'layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310227300935008256', '权限删除', '2', 'sys:power:remove', '', '', '4', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310232350285627392', '操作日志', '2', 'sys:log:operateLog', '', '', '450300705362808832', 'layui-icon layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310232462562951168', '登录日志', '2', 'sys:log:loginLog', '', '', '450300705362808832', 'layui-icon layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310238229588344832', '配置列表', '2', 'sys:config:data', '', '', '1307299332784914432', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310238417082122240', '配置新增', '2', 'sys:config:add', '', '', '1307299332784914432', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310238574355939328', '配置修改', '2', 'sys:config:edit', '', '', '1307299332784914432', 'layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310238700705153024', '配置删除', '2', 'sys:config:remove', '', '', '1307299332784914432', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310243862937075712', '文件列表', '2', 'sys:file:data', '', '', '1304793451996381184', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310244103824343040', '文件新增', '2', 'sys:file:add', '', '', '1304793451996381184', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310244248884346880', '文件删除', '2', 'sys:file:remove', '', '', '1304793451996381184', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310390699333517312', '任务列表', '2', 'sch:job:data', '', '', '442650770626711552', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310390994826428416', '任务新增', '2', 'sch:job:add', '', '', '442650770626711552', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310391095670079488', '任务修改', '2', 'sch:job:edit', '', '', '442650770626711552', 'layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310391707069579264', '任务删除', '2', 'sch:job:remove', '', '', '442650770626711552', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310395250908332032', '日志列表', '2', 'sch:log:data', '', '', '442651158935375872', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310397832091402240', '任务恢复', '2', 'sch:job:resume', '', '', '442650770626711552', 'layui-icon-vercode', NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310398020692475904', '任务停止', '2', 'sch:job:pause', '', '', '442650770626711552', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310398158974484480', '任务运行', '2', 'sch:job:run', '', '', '442650770626711552', 'layui-icon-vercode', 4, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310402491631796224', '数据类型列表', '2', 'sys:dictType:data', '', '', '1304387665067507712', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310402688881524736', '数据类型新增', '2', 'sys:dictType:add', '', '', '1304387665067507712', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310402817776680960', '数据类型修改', '2', 'sys:dictType:edit', '', '', '1304387665067507712', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310403004406431744', '数据类型删除', '2', 'sys:dictType:remove', '', '', '1304387665067507712', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310404584291696640', '数据字典视图', '2', 'sys:dictData:main', '', '', '1304387665067507712', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310404705934901248', '数据字典列表', '2', 'sys:dictData:data', '', '', '1304387665067507712', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310404831407505408', '数据字典新增', '2', 'sys:dictData:add', '', '', '1304387665067507712', 'layui-icon-vercode', 5, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310404999599095808', '数据字典删除', '2', 'sys:dictData:remove', '', '', '1304387665067507712', 'layui-icon-vercode', 6, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1310405161587310592', '数据字典修改', '2', 'sys:dictData:edit', '', '', '1304387665067507712', 'layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1313142510486290432', '公告列表', '1', 'sys:notice:data', '/system/notice/data', '0', '1313142171393589248', 'layui-icon-notice', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1313482983558086656', '公告新增', '2', 'sys:notice:add', '', '', '1313142171393589248', 'layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1313483090852577280', '公告修改', '2', 'sys:notice:edit', '', '', '1313142171393589248', 'layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1313483189850734592', '公告删除', '2', 'sys:notice:remove', '', '', '1313142171393589248', 'layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1315584471046553600', '部门管理', '1', 'sys:dept:main', '/system/dept/main', '_iframe', '1', 'layui-icon layui-icon layui-icon layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1316558444790022144', '部门新增', '2', 'sys:dept:add', '', '', '1315584471046553600', 'layui-icon layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1316558556102656000', '部门修改', '2', 'sys:dept:edit', '', '', '1315584471046553600', 'layui-icon layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1316558685442408448', '部门删除', '2', 'sys:dept:remove', '', '', '1315584471046553600', 'layui-icon layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1317555660455411712', '部门列表', '2', 'sys:dept:data', '', '', '1315584471046553600', 'layui-icon layui-icon layui-icon layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1318229908526202880', '模型新增', '2', 'pro:model:add', '', '', '1307562519451140096', 'layui-icon layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1318230013262168064', '模型修改', '2', 'pro:model:edit', '', '', '1307562519451140096', 'layui-icon layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1318230265385975808', '模型删除', '2', 'pro:model:remove', '', '', '1307562519451140096', 'layui-icon layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1320969572051845120', '111111', '2', '', '', '', '1284020948269268992', 'layui-icon-login-qq', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('1322085079861690368', '用户管理', '1', 'sys:user:main', '/system/user/main', '_iframe', '1', 'layui-icon layui-icon layui-icon layui-icon layui-icon-rate', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1322085270392143872', '用户列表', '2', 'sys:user:data', '', '', '1322085079861690368', 'layui-icon layui-icon layui-icon layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1322085393021009920', '用户新增', '2', 'sys:user:add', '', '', '1322085079861690368', 'layui-icon layui-icon-vercode', NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1322085497798918144', '用户修改', '2', 'sys:user:edit', '', '', '1322085079861690368', 'layui-icon layui-icon layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1322085659766161408', '用户删除', '2', 'sys:user:remove', '', '', '1322085079861690368', 'layui-icon layui-icon-rate', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1329349076189184000', '', '1', '', '', '', '451002662209589248', 'layui-icon', NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1330865171429588992', '在线用户', '1', 'sys:online:main', '/system/online/main', '_iframe', '694203021537574912', 'layui-icon layui-icon layui-icon-username', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1348562759603716096', '在线列表', '1', 'sys:online:data', '/system/online/data', '_iframe', '1330865171429588992', 'layui-icon layui-icon-username', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1349016358033031168', '环境监控', '1', 'sys:monitor:main', '/system/monitor/main', '_iframe', '694203021537574912', 'layui-icon layui-icon-vercode', 9, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1349279791521464320', '电子邮件', '1', 'sys:mail:main', '/system/mail/main', '_iframe', '1', 'layui-icon layui-icon layui-icon layui-icon-list', 7, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1349636574442160128', '邮件发送', '2', 'sys:mail:save', '', '', '1349279791521464320', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon-vercode', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1349636919478190080', '邮件删除', '2', 'sys:mail:remove', '', '', '1349279791521464320', 'layui-icon layui-icon layui-icon layui-icon layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1349637786285637632', '邮件列表', '2', 'sys:mail:data', '', '', '1349279791521464320', 'layui-icon layui-icon layui-icon-vercode', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1349638479767666688', '邮件新增', '2', 'sys:mail:add', '', '', '1349279791521464320', 'layui-icon layui-icon layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1355962888132493312', '系统设置', '1', 'sys:setup:main', '/system/setup/main', '_iframe', '1', 'layui-icon layui-icon layui-icon-set', 11, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1370412169610395648', '站内消息', '1', 'system:notice:main', '/system/notice/main', '_iframe', '1', 'layui-icon layui-icon layui-icon layui-icon-set-fill', 8, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1370412169610395649', '消息列表', '2', 'system:notice:data', '', NULL, '1370412169610395648', 'layui-icon layui-icon-set-fill', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1370412169610395650', '消息新增', '2', 'system:notice:add', '', NULL, '1370412169610395648', 'layui-icon layui-icon-set-fill', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1370412169610395651', '消息修改', '2', 'system:notice:edit', '', NULL, '1370412169610395648', 'layui-icon layui-icon-set-fill', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1370412169610395652', '消息删除', '2', 'system:notice:remove', '', NULL, '1370412169610395648', 'layui-icon layui-icon-set-fill', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('1370974716822552576', '修改设置', '2', 'sys:setup:add', '', '', '1355962888132493312', 'layui-icon layui-icon-vercode', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('2', '用户管理', '2', '', '', '_iframe', '1320969572051845120', 'layui-icon layui-icon layui-icon-username', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('3', '角色管理', '1', 'sys:role:main', '/system/role/main', '_iframe', '1', 'layui-icon layui-icon-user', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('4', '权限管理', '1', 'sys:power:main', '/system/power/main', '_iframe', '1', 'layui-icon layui-icon-vercode', 2, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('442359447487123456', '角色列表', '2', 'sys:role:data', '', '', '3', 'layui-icon layui-icon-rate', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('442417411065516032', '开发工具', '0', '', '', '', '0', 'layui-icon layui-icon layui-icon-senior', 4, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('442418188639145984', '代码生成', '1', 'exp:template:main', '/generate/main', '_iframe', '442417411065516032', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon-template-1', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('442650770626711552', '定时任务', '1', 'sch:job:main', '/schedule/job/main', '_iframe', '694203021537574912', 'layui-icon layui-icon layui-icon layui-icon  layui-icon-chat', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('442651158935375872', '任务日志', '1', 'sch:log:main', '/schedule/log/main', '_iframe', '694203021537574912', 'layui-icon layui-icon layui-icon  layui-icon-file', 1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('450300705362808832', '行为日志', '1', 'sys:log:main', '/system/log/main', '_iframe', '694203021537574912', 'layui-icon layui-icon layui-icon layui-icon  layui-icon-chart', 7, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('451002662209589248', '工作空间', '1', '', '', '', '451002662209589248', 'layui-icon layui-icon layui-icon-home', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('451003242072117248', '项目总览', '1', 'process:model:main', '/console', '_iframe', '451002662209589248', 'layui-icon  layui-icon-component', 0, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('474356363552755712', '项目介绍', '1', 'home', '/console', '_iframe', '474356044148117504', 'layui-icon layui-icon-home', 1, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_power` VALUES ('694203021537574912', '系统监控', '0', '', '', '', '0', 'layui-icon  layui-icon-console', 3, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_power` VALUES ('694203311615639552', '接口文档', '1', 'sys:doc:main', '/system/doc/main', '_iframe', '442417411065516032', 'layui-icon layui-icon layui-icon layui-icon  layui-icon-chart', 9, NULL, NULL, NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编号',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1309851245195821056', '超级管理员', 'admin', '0', NULL, NULL, NULL, NULL, NULL, '超级管理员', 1);
INSERT INTO `sys_role` VALUES ('1313761100243664896', '普通管理员', 'manager', '0', NULL, NULL, NULL, NULL, NULL, '普通管理员', 2);
INSERT INTO `sys_role` VALUES ('1356112133691015168', '应急管理员', 'users', '0', NULL, NULL, NULL, NULL, NULL, '应急管理员', 2);

-- ----------------------------
-- Table structure for sys_role_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_power`;
CREATE TABLE `sys_role_power`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `power_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_power
-- ----------------------------
INSERT INTO `sys_role_power` VALUES ('1284022485632679936', '3', '474356044148117504', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679937', '3', '474356363552755712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679938', '3', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679939', '3', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679940', '3', '442359447487123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679941', '3', '4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679942', '3', '442722702474743808', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679943', '3', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679944', '3', '1284020948269268992', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679945', '3', '442417411065516032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679946', '3', '442418188639145984', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679947', '3', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679948', '3', '450300705362808832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679949', '3', '442520236248403968', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679950', '3', '694203311615639552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679951', '3', '442650387514789888', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679952', '3', '442650770626711552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679953', '3', '442651158935375872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679954', '3', '451002662209589248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1284022485632679955', '3', '451003242072117248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650364506112', '2', '474356044148117504', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700416', '2', '474356363552755712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700417', '2', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700418', '2', '1284020948269268992', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700419', '2', '450300705362808832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700420', '2', '442417411065516032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700421', '2', '442418188639145984', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700422', '2', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700423', '2', '442520236248403968', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700424', '2', '694203311615639552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700425', '2', '442650387514789888', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700426', '2', '442650770626711552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700427', '2', '442651158935375872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700428', '2', '451002662209589248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1305379650368700429', '2', '451003242072117248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380352', '1', '451002662209589248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380353', '1', '451003242072117248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380354', '1', '1305875436139446272', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380355', '1', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380356', '1', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380357', '1', '1284020948269268992', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380358', '1', '1284022967767924736', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380359', '1', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380360', '1', '442359447487123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380361', '1', '4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380362', '1', '1304387665067507712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380363', '1', '450300705362808832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380364', '1', '1304793451996381184', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380365', '1', '1307299332784914432', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380366', '1', '442650387514789888', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380367', '1', '442650770626711552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380368', '1', '442651158935375872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380369', '1', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380370', '1', '442520236248403968', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380371', '1', '694203311615639552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380372', '1', '442417411065516032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380373', '1', '442418188639145984', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380374', '1', '1302180351979814912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380375', '1', '1307562196556840960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380376', '1', '1307562519451140096', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1308571532737380377', '1', '1308571483794046976', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897792', '1310215420371795968', '451002662209589248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897793', '1310215420371795968', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897794', '1310215420371795968', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897795', '1310215420371795968', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897796', '1310215420371795968', '4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897797', '1310215420371795968', '1304387665067507712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897798', '1310215420371795968', '450300705362808832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897799', '1310215420371795968', '1304793451996381184', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897800', '1310215420371795968', '1307299332784914432', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897801', '1310215420371795968', '1313142171393589248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897802', '1310215420371795968', '1313142510486290432', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897803', '1310215420371795968', '442650387514789888', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897804', '1310215420371795968', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897805', '1310215420371795968', '442417411065516032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1313147486356897806', '1310215420371795968', '1307562196556840960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1320969221462556672', '1320969145759563776', '451002662209589248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1320969221462556673', '1320969145759563776', '451003242072117248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1320969221462556674', '1320969145759563776', '1305875436139446272', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778112', '1309851245195821056', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778113', '1309851245195821056', '1322085079861690368', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778114', '1309851245195821056', '1322085393021009920', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778115', '1309851245195821056', '1322085270392143872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778116', '1309851245195821056', '1322085497798918144', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778117', '1309851245195821056', '1322085659766161408', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778118', '1309851245195821056', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778119', '1309851245195821056', '1310209696916832256', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778120', '1309851245195821056', '1310209900478988288', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778121', '1309851245195821056', '1310210054728712192', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778122', '1309851245195821056', '442359447487123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778123', '1309851245195821056', '1310211965188046848', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778124', '1309851245195821056', '4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778125', '1309851245195821056', '1310226416867999744', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778126', '1309851245195821056', '1310226976593674240', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778127', '1309851245195821056', '1310227130998587392', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778128', '1309851245195821056', '1310227300935008256', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778129', '1309851245195821056', '1315584471046553600', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778130', '1309851245195821056', '1316558444790022144', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778131', '1309851245195821056', '1316558556102656000', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778132', '1309851245195821056', '1317555660455411712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778133', '1309851245195821056', '1316558685442408448', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778134', '1309851245195821056', '1304387665067507712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778135', '1309851245195821056', '1310405161587310592', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778136', '1309851245195821056', '1310402491631796224', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778137', '1309851245195821056', '1310404584291696640', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778138', '1309851245195821056', '1310404705934901248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778139', '1309851245195821056', '1310402688881524736', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778140', '1309851245195821056', '1310402817776680960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778141', '1309851245195821056', '1310403004406431744', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778142', '1309851245195821056', '1310404831407505408', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778143', '1309851245195821056', '1310404999599095808', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778144', '1309851245195821056', '1304793451996381184', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778145', '1309851245195821056', '1310243862937075712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778146', '1309851245195821056', '1310244103824343040', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778147', '1309851245195821056', '1310244248884346880', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778148', '1309851245195821056', '1307299332784914432', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778149', '1309851245195821056', '1310238229588344832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778150', '1309851245195821056', '1310238417082122240', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778151', '1309851245195821056', '1310238574355939328', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778152', '1309851245195821056', '1310238700705153024', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778153', '1309851245195821056', '1349279791521464320', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778154', '1309851245195821056', '1349637786285637632', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778155', '1309851245195821056', '1349638479767666688', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778156', '1309851245195821056', '1349636919478190080', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778157', '1309851245195821056', '1349636574442160128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778158', '1309851245195821056', '1355962888132493312', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778159', '1309851245195821056', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778160', '1309851245195821056', '1330865171429588992', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778161', '1309851245195821056', '1348562759603716096', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778162', '1309851245195821056', '442650770626711552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778163', '1309851245195821056', '1310397832091402240', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778164', '1309851245195821056', '1310390699333517312', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778165', '1309851245195821056', '1310390994826428416', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778166', '1309851245195821056', '1310391095670079488', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778167', '1309851245195821056', '1310391707069579264', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778168', '1309851245195821056', '1310398020692475904', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778169', '1309851245195821056', '1310398158974484480', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778170', '1309851245195821056', '442651158935375872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778171', '1309851245195821056', '1310395250908332032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778172', '1309851245195821056', '450300705362808832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778173', '1309851245195821056', '1310232350285627392', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778174', '1309851245195821056', '1310232462562951168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778175', '1309851245195821056', '1349016358033031168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778176', '1309851245195821056', '442417411065516032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778177', '1309851245195821056', '442418188639145984', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778178', '1309851245195821056', '1302180351979814912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778179', '1309851245195821056', '694203311615639552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778180', '1309851245195821056', '1307562196556840960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778181', '1309851245195821056', '1307562519451140096', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778182', '1309851245195821056', '1318229908526202880', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778183', '1309851245195821056', '1318230013262168064', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778184', '1309851245195821056', '1318230265385975808', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1355962953458778185', '1309851245195821056', '1308571483794046976', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712128', '1313761100243664896', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712129', '1313761100243664896', '1322085079861690368', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712130', '1313761100243664896', '1322085393021009920', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712131', '1313761100243664896', '1322085270392143872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712132', '1313761100243664896', '1322085497798918144', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712133', '1313761100243664896', '1322085659766161408', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712134', '1313761100243664896', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712135', '1313761100243664896', '1310209696916832256', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712136', '1313761100243664896', '1310209900478988288', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712137', '1313761100243664896', '1310210054728712192', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712138', '1313761100243664896', '442359447487123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712139', '1313761100243664896', '1310211965188046848', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712140', '1313761100243664896', '4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712141', '1313761100243664896', '1310226416867999744', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712142', '1313761100243664896', '1310226976593674240', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712143', '1313761100243664896', '1310227130998587392', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712144', '1313761100243664896', '1310227300935008256', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712145', '1313761100243664896', '1315584471046553600', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712146', '1313761100243664896', '1316558444790022144', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712147', '1313761100243664896', '1316558556102656000', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712148', '1313761100243664896', '1317555660455411712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712149', '1313761100243664896', '1316558685442408448', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712150', '1313761100243664896', '1304387665067507712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712151', '1313761100243664896', '1310402491631796224', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712152', '1313761100243664896', '1310404584291696640', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712153', '1313761100243664896', '1310405161587310592', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712154', '1313761100243664896', '1310402688881524736', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712155', '1313761100243664896', '1310404705934901248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712156', '1313761100243664896', '1310402817776680960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712157', '1313761100243664896', '1310403004406431744', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712158', '1313761100243664896', '1310404831407505408', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712159', '1313761100243664896', '1310404999599095808', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712160', '1313761100243664896', '1304793451996381184', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712161', '1313761100243664896', '1310243862937075712', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712162', '1313761100243664896', '1310244103824343040', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712163', '1313761100243664896', '1310244248884346880', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712164', '1313761100243664896', '1307299332784914432', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712165', '1313761100243664896', '1310238229588344832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712166', '1313761100243664896', '1310238417082122240', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712167', '1313761100243664896', '1310238574355939328', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712168', '1313761100243664896', '1310238700705153024', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712169', '1313761100243664896', '1349279791521464320', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712170', '1313761100243664896', '1349637786285637632', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712171', '1313761100243664896', '1349638479767666688', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712172', '1313761100243664896', '1349636919478190080', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712173', '1313761100243664896', '1349636574442160128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712174', '1313761100243664896', '1355962888132493312', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712175', '1313761100243664896', '1370974716822552576', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712176', '1313761100243664896', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712177', '1313761100243664896', '1330865171429588992', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712178', '1313761100243664896', '442650770626711552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712179', '1313761100243664896', '442651158935375872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712180', '1313761100243664896', '450300705362808832', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712181', '1313761100243664896', '1349016358033031168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712182', '1313761100243664896', '442417411065516032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712183', '1313761100243664896', '442418188639145984', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712184', '1313761100243664896', '1302180351979814912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712185', '1313761100243664896', '694203311615639552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712186', '1313761100243664896', '1307562196556840960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712187', '1313761100243664896', '1307562519451140096', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712188', '1313761100243664896', '1318229908526202880', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712189', '1313761100243664896', '1318230013262168064', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712190', '1313761100243664896', '1318230265385975808', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1370974927745712191', '1313761100243664896', '1308571483794046976', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959552', '1356112133691015168', '694203021537574912', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959553', '1356112133691015168', '442650770626711552', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959554', '1356112133691015168', '1310397832091402240', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959555', '1356112133691015168', '1310390699333517312', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959556', '1356112133691015168', '1310390994826428416', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959557', '1356112133691015168', '1310391095670079488', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959558', '1356112133691015168', '1310391707069579264', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959559', '1356112133691015168', '1310398020692475904', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959560', '1356112133691015168', '1310398158974484480', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959561', '1356112133691015168', '442651158935375872', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959562', '1356112133691015168', '1310395250908332032', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959563', '1356112133691015168', '1307562196556840960', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959564', '1356112133691015168', '1307562519451140096', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('1417395897527959565', '1356112133691015168', '1308571483794046976', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('442062615250866176', '693913251020275712', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('442062615250866177', '693913251020275712', '2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('442062615250866178', '693913251020275712', '3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_power` VALUES ('442062615250866179', '693913251020275712', '4', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `username` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `real_name` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `login` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否登录',
  `dept_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1306230031168569344', 'feng', '$2a$10$jjf2h8Cx2lkFMKy3NY9pguADYAMewyPr2IJw8YAI5zSH2/0R/9Kra', NULL, '1', '风筝', 'feng@gmail.com', NULL, '0', '15553726531', '2000-02-09 00:00:00', NULL, NULL, NULL, '被岁月镂空，亦受其雕琢', '1', NULL, '1', NULL);
INSERT INTO `sys_user` VALUES ('1309861917694623744', 'admin', '$2a$10$6T.NGloFO.mD/QOAUelMTOcjAH8N49h34TsXduDVlnNMrASIGBNz6', NULL, '1', '管理', 'Jmys1992@qq.com', '1417168309870133248', '0', '15553726531', '2020-09-26 22:26:32', NULL, NULL, NULL, '被岁月镂空，亦受其雕琢', '1', NULL, '1', '2021-07-25 02:03:56');
INSERT INTO `sys_user` VALUES ('1310409555649232897', 'ruhua', '$2a$10$pkvLdCLdFp2sXZpmK34wveekbWvHinW2ldBnic4SqjiKO8jK4Etka', NULL, '1', '如花', 'ruhua@gmail.com', NULL, '0', '15553726531', '2020-09-28 10:42:39', NULL, NULL, NULL, NULL, '1', NULL, '1', NULL);
INSERT INTO `sys_user` VALUES ('1349016976730619905', 'mwj', '$2a$10$mD0pnwOGjmOKihboidaTveUdrqcDYoluzfCOA0Ho87iwr9PKrDA6i', NULL, '1', '风筝', '', NULL, '1', '666666666', '2021-01-12 23:34:45', NULL, NULL, NULL, NULL, '1', NULL, '6', '2021-01-12 23:35:12');
INSERT INTO `sys_user` VALUES ('1349021166525743105', 'xiana', '$2a$10$6VuyGmiEbIix/gPDU8oe3O7DZSxGVByjXCHQGtyEMoRAt74M/daee', NULL, '1', '夏娜', 'xiana@gmail.com', NULL, '0', '15553726531', '2021-01-12 23:51:24', NULL, NULL, NULL, NULL, '1', NULL, '1', NULL);
INSERT INTO `sys_user` VALUES ('1355966975355912193', 'sanman', '$2a$10$AD3QnQMRhYY7RUDHd1EEL.KHaDW8/S66SsESwh.9ta8bLiUXrZcJe', NULL, '1', '散漫', 'sanman@gmail.com', NULL, '0', '15553726531', '2021-02-01 03:51:34', NULL, NULL, NULL, NULL, '1', NULL, '1', NULL);
INSERT INTO `sys_user` VALUES ('1355967204012589057', 'langhua', '$2a$10$MNbf6dSvvncpoPsNFyMW6ObPwfj3jCKsZa7LvVAiXco1DWtgA46he', NULL, '1', '浪花', 'langhua@gmail.com', NULL, '0', '15553726531', '2021-02-01 03:52:29', NULL, NULL, NULL, NULL, '1', NULL, '1', NULL);
INSERT INTO `sys_user` VALUES ('1355967579994193921', 'zidian', '$2a$10$c9OatFOMGnj37A6UJTwfGOKqCwCx50K8eZsjV5YoBRlpYHcz8WfyW', NULL, '1', '字典', 'zidian', NULL, '0', '15553726531', '2021-02-01 03:53:58', NULL, NULL, NULL, NULL, '1', NULL, '1', NULL);
INSERT INTO `sys_user` VALUES ('1370973608502886401', 'duanlang', '$2a$10$XNcKlX3AnXR/Gh2g8aLX5OFtLD69Yjl1O8PDLmITH4WCQT.shsrWe', NULL, '1', '断浪', 'duanlang@gmail.com', NULL, '0', '15553726531', '2021-03-14 13:42:34', NULL, NULL, NULL, NULL, '1', NULL, '1', '2021-03-14 13:47:28');
INSERT INTO `sys_user` VALUES ('1417395546586349569', 'jmys', '$2a$10$0HzindahrO8KyTSOrDt7A.0FkDN.6A4JkLf4FdYvhaArFHBsOh4uO', NULL, '1', '柏良', '854085467@qq.com', NULL, '0', '15553726531', '2021-07-20 16:06:47', NULL, NULL, NULL, NULL, '1', NULL, '1', '2021-07-20 16:08:48');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标识',
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1302196622322565120', '1302196622007992320', '1');
INSERT INTO `sys_user_role` VALUES ('1304443027040763904', '1304443026482921472', '1');
INSERT INTO `sys_user_role` VALUES ('1304443027044958208', '1304443026482921472', '2');
INSERT INTO `sys_user_role` VALUES ('1304443027044958209', '1304443026482921472', '3');
INSERT INTO `sys_user_role` VALUES ('1304443307404820480', '1304443306888921088', '1');
INSERT INTO `sys_user_role` VALUES ('1304443307404820481', '1304443306888921088', '2');
INSERT INTO `sys_user_role` VALUES ('1305359805342285824', '1305359804906078208', '');
INSERT INTO `sys_user_role` VALUES ('1305359807724650496', '1305359807296831488', '');
INSERT INTO `sys_user_role` VALUES ('1305390235135246336', '1305390234694844416', '');
INSERT INTO `sys_user_role` VALUES ('1306229860422647808', '1306229859755753472', '1');
INSERT INTO `sys_user_role` VALUES ('1306229892144168960', '1306229891624075264', '1');
INSERT INTO `sys_user_role` VALUES ('1306243520893288448', '1306243520482246656', '');
INSERT INTO `sys_user_role` VALUES ('1308074663896678400', '1308074663313670144', '1');
INSERT INTO `sys_user_role` VALUES ('1308074663896678401', '1308074663313670144', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1308074663896678402', '1308074663313670144', '2');
INSERT INTO `sys_user_role` VALUES ('1308075167091523584', '1308075166433017856', '1');
INSERT INTO `sys_user_role` VALUES ('1308075167091523585', '1308075166433017856', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1308075241188098048', '1308074939114323968', '1');
INSERT INTO `sys_user_role` VALUES ('1308075241188098049', '1308074939114323968', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1308075407685189632', '1308075407114764288', '1');
INSERT INTO `sys_user_role` VALUES ('1308075407685189633', '1308075407114764288', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1308075638158000128', '1308075637621129216', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1308328954523811840', '1308328954045661184', '1');
INSERT INTO `sys_user_role` VALUES ('1308328954523811841', '1308328954045661184', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1308328954523811842', '1308328954045661184', '2');
INSERT INTO `sys_user_role` VALUES ('1308571264494862336', '1308076162903179264', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1309445423668133888', '1309444883659882496', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1309445423668133889', '1309444883659882496', '1309121036125470720');
INSERT INTO `sys_user_role` VALUES ('1309445423668133890', '1309444883659882496', '2');
INSERT INTO `sys_user_role` VALUES ('1309752526945386496', '1', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1309752526945386497', '1', '1309121036125470720');
INSERT INTO `sys_user_role` VALUES ('1309752526945386498', '1', '2');
INSERT INTO `sys_user_role` VALUES ('1309860016655695872', '1309860016043327488', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1309860554432577536', '1309860553891512320', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1309861324494209024', '1309861323898617856', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1309861325593116672', '1309861324909445120', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1310080380040118272', '1310080379331280896', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310080380589572096', '1310080379935260672', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310080718918909952', '1310080718256209920', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310080719917154304', '1310080719208316928', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310082314557980672', '1310082313954000896', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310082315195514880', '1310082314545397760', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310083089153654784', '1310083088511926272', '1309121036125470720');
INSERT INTO `sys_user_role` VALUES ('1310083089828937728', '1310083089216569344', '1309121036125470720');
INSERT INTO `sys_user_role` VALUES ('1310083324709961728', '1310083324110176256', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310208453033066496', '1310208452424892416', '1309121036125470720');
INSERT INTO `sys_user_role` VALUES ('1310209026096627712', '1310209025576534016', '1306230258952830976');
INSERT INTO `sys_user_role` VALUES ('1310209026096627713', '1310209025576534016', '1309121036125470720');
INSERT INTO `sys_user_role` VALUES ('1310381721815875584', '1306229381332467712', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1310424875067768832', '1310421836906889217', '1310421428759166976');
INSERT INTO `sys_user_role` VALUES ('1314015448013996032', '1304491590080790528', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1314410103465574400', '1314410059245027329', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1314416691479838720', '1314416690875858945', '');
INSERT INTO `sys_user_role` VALUES ('1316251185065230336', '1306230031168569344', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1316275764227735552', '1316275763711836161', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1316275764227735553', '1316275763711836161', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1316275899439513600', '1315827004456566785', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1316275899439513601', '1315827004456566785', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1316275930657718272', '1315829324519047169', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1316276059032780800', '1310409555649232897', '');
INSERT INTO `sys_user_role` VALUES ('1316410619078901760', '1306229606205882368', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1316410619078901761', '1306229606205882368', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1316410619078901762', '1306229606205882368', '1316407534105395200');
INSERT INTO `sys_user_role` VALUES ('1316410619078901763', '1306229606205882368', '1316408008376320000');
INSERT INTO `sys_user_role` VALUES ('1318205966671413248', '1318205965996130305', '');
INSERT INTO `sys_user_role` VALUES ('1320899195875360768', '1320899195225243649', '');
INSERT INTO `sys_user_role` VALUES ('1329795580615983104', '1329795579919728641', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1329795580615983105', '1329795579919728641', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1329795614484987904', '1329795613730013185', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1329795688124383232', '1329795687465877505', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1329795704863850496', '1329795703882383361', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1329795716930863104', '1329795716255580161', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1329795741211688960', '1329795740536406017', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1349021014649995264', '1349016976730619905', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1349021167326855168', '1349021166525743105', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1349021167326855169', '1349021166525743105', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1355967256000987136', '1355966975355912193', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1355967256000987137', '1355966975355912193', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1355967330718318592', '1355967204012589057', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1355967330718318593', '1355967204012589057', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1355967580686254080', '1355967579994193921', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1355967580686254081', '1355967579994193921', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1360858458609418240', '1309861917694623744', '1309851245195821056');
INSERT INTO `sys_user_role` VALUES ('1360858458609418241', '1309861917694623744', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1370973609278832640', '1370973608502886401', '1313761100243664896');
INSERT INTO `sys_user_role` VALUES ('1417395547056111616', '1417395546586349569', '1356112133691015168');
INSERT INTO `sys_user_role` VALUES ('442110794142978048', NULL, '1');
INSERT INTO `sys_user_role` VALUES ('442110794142978049', NULL, '2');
INSERT INTO `sys_user_role` VALUES ('442110794142978050', NULL, '3');
INSERT INTO `sys_user_role` VALUES ('442114944884936704', '442114944884936704', '1');
INSERT INTO `sys_user_role` VALUES ('442114944884936705', '442114944884936704', '2');
INSERT INTO `sys_user_role` VALUES ('442114944884936706', '442114944884936704', '3');
INSERT INTO `sys_user_role` VALUES ('442114944884936707', '442114944884936704', '693913251020275712');
INSERT INTO `sys_user_role` VALUES ('442114944884936708', '442114944884936704', '693949793801601024');
INSERT INTO `sys_user_role` VALUES ('442114944884936709', '442114944884936704', '694106517393113088');
INSERT INTO `sys_user_role` VALUES ('442127724396548096', '3', '1');
INSERT INTO `sys_user_role` VALUES ('442127724396548097', '3', '2');
INSERT INTO `sys_user_role` VALUES ('442127724396548098', '3', '3');
INSERT INTO `sys_user_role` VALUES ('445004989551742976', '442492965651353600', '1');
INSERT INTO `sys_user_role` VALUES ('445004989551742977', '442492965651353600', '2');
INSERT INTO `sys_user_role` VALUES ('445005010271604736', '444226209941950464', '1');
INSERT INTO `sys_user_role` VALUES ('445005010271604737', '444226209941950464', '2');
INSERT INTO `sys_user_role` VALUES ('445005010271604738', '444226209941950464', '3');
INSERT INTO `sys_user_role` VALUES ('447196043407396864', '447196042723725312', '1');
INSERT INTO `sys_user_role` VALUES ('447196043407396865', '447196042723725312', '2');
INSERT INTO `sys_user_role` VALUES ('447197132043194368', '447197131518906368', '1');
INSERT INTO `sys_user_role` VALUES ('447197773046091776', '447197772274339840', '1');
INSERT INTO `sys_user_role` VALUES ('447200144400715776', '447199996320813056', '1');
INSERT INTO `sys_user_role` VALUES ('447200144400715777', '447199996320813056', '2');
INSERT INTO `sys_user_role` VALUES ('449248198469488640', '449248198058446848', '3');
INSERT INTO `sys_user_role` VALUES ('463926002653990912', '463926002318446592', '3');
INSERT INTO `sys_user_role` VALUES ('463926371165540352', '442488661347536896', '3');

SET FOREIGN_KEY_CHECKS = 1;
