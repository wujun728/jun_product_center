/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : febs_cloud_base

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 15/04/2020 16:04:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255) NOT NULL,
    `resource_ids`            varchar(255)  DEFAULT NULL,
    `client_secret`           varchar(255) NOT NULL,
    `scope`                   varchar(255) NOT NULL,
    `authorized_grant_types`  varchar(255) NOT NULL,
    `web_server_redirect_uri` varchar(255)  DEFAULT NULL,
    `authorities`             varchar(255)  DEFAULT NULL,
    `access_token_validity`   int(11)      NOT NULL,
    `refresh_token_validity`  int(11)       DEFAULT NULL,
    `additional_information`  varchar(4096) DEFAULT NULL,
    `autoapprove`             tinyint(4)    DEFAULT NULL,
    `origin_secret`           varchar(255)  DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='客户端配置表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details`
VALUES ('app', '', '$2a$10$8Qk/efslEpO1Af1kyw/rp.DdJGsdnET8UCp1vGDzpQEa.1qBklvua', 'all', 'refresh_token,password', '',
        NULL, 86400, 864000, NULL, NULL, '123456');
INSERT INTO `oauth_client_details`
VALUES ('febs', ' ', '$2a$10$aSZTvMOtUAYUQ.75z2n3ceJd6dCIk9Vy3J/SKZUE4hBLd6sz7.6ge', 'all', 'password,refresh_token',
        NULL, NULL, 86400, 8640000, NULL, 0, '123456');
COMMIT;

-- ----------------------------
-- Table structure for t_data_permission_test
-- ----------------------------
DROP TABLE IF EXISTS `t_data_permission_test`;
CREATE TABLE `t_data_permission_test`
(
    `FIELD1`      varchar(20) NOT NULL,
    `FIELD2`      varchar(20) NOT NULL,
    `FIELD3`      varchar(20) NOT NULL,
    `FIELD4`      varchar(20) NOT NULL,
    `DEPT_ID`     int(11)     NOT NULL,
    `CREATE_TIME` datetime    NOT NULL,
    `ID`          int(11)     NOT NULL,
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户权限测试';

-- ----------------------------
-- Records of t_data_permission_test
-- ----------------------------
BEGIN;
INSERT INTO `t_data_permission_test`
VALUES ('小米', '小米10Pro', '4999', '珍珠白', 1, '2020-04-14 15:00:38', 1);
INSERT INTO `t_data_permission_test`
VALUES ('腾讯', '黑鲨游戏手机3', '3799', '铠甲灰', 2, '2020-04-14 15:01:36', 2);
INSERT INTO `t_data_permission_test`
VALUES ('华为', '华为P30', '3299', '天空之境', 1, '2020-04-14 15:03:11', 3);
INSERT INTO `t_data_permission_test`
VALUES ('华为', '华为P40Pro', '6488', '亮黑色', 3, '2020-04-14 15:04:31', 4);
INSERT INTO `t_data_permission_test`
VALUES ('vivo', 'Vivo iQOO 3', '3998', '拉力橙', 4, '2020-04-14 15:05:55', 5);
INSERT INTO `t_data_permission_test`
VALUES ('一加', '一加7T', '3199', '冰际蓝', 5, '2020-04-14 15:06:53', 6);
INSERT INTO `t_data_permission_test`
VALUES ('三星', '三星Galaxy S10', '4098', '浩玉白', 6, '2020-04-14 15:08:25', 7);
INSERT INTO `t_data_permission_test`
VALUES ('苹果', 'iPhone 11 pro max', '9198', '暗夜绿', 4, '2020-04-14 15:09:20', 8);
COMMIT;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`
(
    `DEPT_ID`     bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `PARENT_ID`   bigint(20)   NOT NULL COMMENT '上级部门ID',
    `DEPT_NAME`   varchar(100) NOT NULL COMMENT '部门名称',
    `ORDER_NUM`   double(20, 0) DEFAULT NULL COMMENT '排序',
    `CREATE_TIME` datetime      DEFAULT NULL COMMENT '创建时间',
    `MODIFY_TIME` datetime      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`DEPT_ID`) USING BTREE,
    KEY `t_dept_parent_id` (`PARENT_ID`),
    KEY `t_dept_dept_name` (`DEPT_NAME`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
BEGIN;
INSERT INTO `t_dept`
VALUES (1, 0, '开发部', 1, '2018-01-04 15:42:26', '2019-01-05 21:08:27');
INSERT INTO `t_dept`
VALUES (2, 1, '开发一部', 1, '2018-01-04 15:42:34', '2019-01-18 00:59:37');
INSERT INTO `t_dept`
VALUES (3, 1, '开发二部', 2, '2018-01-04 15:42:29', '2019-01-05 14:09:39');
INSERT INTO `t_dept`
VALUES (4, 0, '市场部', 2, '2018-01-04 15:42:36', '2019-01-23 06:27:56');
INSERT INTO `t_dept`
VALUES (5, 0, '人事部', 3, '2018-01-04 15:42:32', '2019-01-23 06:27:59');
INSERT INTO `t_dept`
VALUES (6, 0, '测试部', 4, '2018-01-04 15:42:38', '2019-01-17 08:15:47');
COMMIT;

-- ----------------------------
-- Table structure for t_eximport
-- ----------------------------
DROP TABLE IF EXISTS `t_eximport`;
CREATE TABLE `t_eximport`
(
    `FIELD1`      varchar(20)  NOT NULL,
    `FIELD2`      int(11)      NOT NULL,
    `FIELD3`      varchar(100) NOT NULL,
    `CREATE_TIME` datetime     NOT NULL
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='Excel导入导出测试';

-- ----------------------------
-- Records of t_eximport
-- ----------------------------
BEGIN;
INSERT INTO `t_eximport`
VALUES ('字段1', 1, 'mrbird0@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 2, 'mrbird1@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 3, 'mrbird2@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 4, 'mrbird3@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 5, 'mrbird4@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 6, 'mrbird5@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 7, 'mrbird6@gmail.com', '2019-07-25 19:08:01');
INSERT INTO `t_eximport`
VALUES ('字段1', 8, 'mrbird7@gmail.com', '2019-07-25 19:08:01');
COMMIT;

-- ----------------------------
-- Table structure for t_generator_config
-- ----------------------------
DROP TABLE IF EXISTS `t_generator_config`;
CREATE TABLE `t_generator_config`
(
    `id`                   int(11)     NOT NULL COMMENT '主键',
    `author`               varchar(20) NOT NULL COMMENT '作者',
    `base_package`         varchar(50) NOT NULL COMMENT '基础包名',
    `entity_package`       varchar(20) NOT NULL COMMENT 'entity文件存放路径',
    `mapper_package`       varchar(20) NOT NULL COMMENT 'mapper文件存放路径',
    `mapper_xml_package`   varchar(20) NOT NULL COMMENT 'mapper xml文件存放路径',
    `service_package`      varchar(20) NOT NULL COMMENT 'servcie文件存放路径',
    `service_impl_package` varchar(20) NOT NULL COMMENT 'serviceImpl文件存放路径',
    `controller_package`   varchar(20) NOT NULL COMMENT 'controller文件存放路径',
    `is_trim`              char(1)     NOT NULL COMMENT '是否去除前缀 1是 0否',
    `trim_value`           varchar(10) DEFAULT NULL COMMENT '前缀内容',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='代码生成配置表';

-- ----------------------------
-- Records of t_generator_config
-- ----------------------------
BEGIN;
INSERT INTO `t_generator_config`
VALUES (1, 'MrBird', 'cc.mrbird.febs.server.generator.gen', 'entity', 'mapper', 'mapper', 'service', 'service.impl',
        'controller', '1', 't_');
COMMIT;

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`
(
    `JOB_ID`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '任务id',
    `BEAN_NAME`       varchar(50) NOT NULL COMMENT 'spring bean名称',
    `METHOD_NAME`     varchar(50) NOT NULL COMMENT '方法名',
    `PARAMS`          varchar(50) DEFAULT NULL COMMENT '参数',
    `CRON_EXPRESSION` varchar(20) NOT NULL COMMENT 'cron表达式',
    `STATUS`          char(2)     NOT NULL COMMENT '任务状态  0：正常  1：暂停',
    `REMARK`          varchar(50) DEFAULT NULL COMMENT '备注',
    `CREATE_TIME`     datetime    DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`JOB_ID`) USING BTREE,
    KEY `t_job_create_time` (`CREATE_TIME`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='定时任务表';

-- ----------------------------
-- Records of t_job
-- ----------------------------
BEGIN;
INSERT INTO `t_job`
VALUES (1, 'taskList', 'test', 'hello', '0/1 * * * * ?', '1', '有参任务调度测试', '2018-02-24 16:26:14');
INSERT INTO `t_job`
VALUES (2, 'taskList', 'test1', NULL, '0/10 * * * * ?', '1', '无参任务调度测试', '2018-02-24 17:06:23');
INSERT INTO `t_job`
VALUES (3, 'taskList', 'test2', '{\"name\":\"mrbird\",\"age\":18}', '0/1 * * * * ?', '1', 'JSON类型参数任务测试',
        '2018-02-26 09:28:26');
INSERT INTO `t_job`
VALUES (4, 'taskList', 'test3', '', '0/5 * * * * ?', '1', '测试异常，没有编写test3任务', '2018-02-26 11:15:30');
COMMIT;

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log`
(
    `LOG_ID`      bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
    `JOB_ID`      bigint(20)   NOT NULL COMMENT '任务id',
    `BEAN_NAME`   varchar(100) NOT NULL COMMENT 'spring bean名称',
    `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
    `PARAMS`      varchar(200)   DEFAULT NULL COMMENT '参数',
    `STATUS`      char(2)      NOT NULL COMMENT '任务状态    0：成功    1：失败',
    `ERROR`       text COMMENT '失败信息',
    `TIMES`       decimal(11, 0) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
    `CREATE_TIME` datetime       DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`LOG_ID`) USING BTREE,
    KEY `t_job_log_create_time` (`CREATE_TIME`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='调度日志表';

-- ----------------------------
-- Records of t_job_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`
(
    `ID`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `USERNAME`    varchar(50)    DEFAULT NULL COMMENT '操作用户',
    `OPERATION`   text COMMENT '操作内容',
    `TIME`        decimal(11, 0) DEFAULT NULL COMMENT '耗时',
    `METHOD`      text COMMENT '操作方法',
    `PARAMS`      text COMMENT '方法参数',
    `IP`          varchar(64)    DEFAULT NULL COMMENT '操作者IP',
    `CREATE_TIME` datetime       DEFAULT NULL COMMENT '创建时间',
    `location`    varchar(50)    DEFAULT NULL COMMENT '操作地点',
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `t_log_create_time` (`CREATE_TIME`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户操作日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger`
(
    `id`          bigint(20)    NOT NULL AUTO_INCREMENT,
    `group_id`    varchar(64)   NOT NULL,
    `unit_id`     varchar(32)   NOT NULL,
    `tag`         varchar(50)   NOT NULL,
    `content`     varchar(1024) NOT NULL,
    `create_time` varchar(30)   NOT NULL,
    `app_name`    varchar(128)  NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='分布式事务日志';

-- ----------------------------
-- Records of t_logger
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log`
(
    `ID`         bigint(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `USERNAME`   varchar(50) NOT NULL COMMENT '用户名',
    `LOGIN_TIME` datetime    NOT NULL COMMENT '登录时间',
    `LOCATION`   varchar(50) DEFAULT NULL COMMENT '登录地点',
    `IP`         varchar(50) DEFAULT NULL COMMENT 'IP地址',
    `SYSTEM`     varchar(50) DEFAULT NULL COMMENT '操作系统',
    `BROWSER`    varchar(50) DEFAULT NULL COMMENT '浏览器',
    PRIMARY KEY (`ID`) USING BTREE,
    KEY `t_login_log_login_time` (`LOGIN_TIME`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='登录日志表';

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`
(
    `MENU_ID`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
    `PARENT_ID`   bigint(20)  NOT NULL COMMENT '上级菜单ID',
    `MENU_NAME`   varchar(50) NOT NULL COMMENT '菜单/按钮名称',
    `PATH`        varchar(255)  DEFAULT NULL COMMENT '对应路由path',
    `COMPONENT`   varchar(255)  DEFAULT NULL COMMENT '对应路由组件component',
    `PERMS`       varchar(50)   DEFAULT NULL COMMENT '权限标识',
    `ICON`        varchar(50)   DEFAULT NULL COMMENT '图标',
    `TYPE`        char(2)     NOT NULL COMMENT '类型 0菜单 1按钮',
    `ORDER_NUM`   double(20, 0) DEFAULT NULL COMMENT '排序',
    `CREATE_TIME` datetime    NOT NULL COMMENT '创建时间',
    `MODIFY_TIME` datetime      DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`MENU_ID`) USING BTREE,
    KEY `t_menu_parent_id` (`PARENT_ID`),
    KEY `t_menu_menu_id` (`MENU_ID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 195
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_menu`
VALUES (1, 0, '系统管理', '/system', 'Layout', NULL, 'el-icon-set-up', '0', 1, '2017-12-27 16:39:07',
        '2019-07-20 16:19:04');
INSERT INTO `t_menu`
VALUES (2, 0, '系统监控', '/monitor', 'Layout', NULL, 'el-icon-data-line', '0', 2, '2017-12-27 16:45:51',
        '2019-01-23 06:27:12');
INSERT INTO `t_menu`
VALUES (3, 1, '用户管理', '/system/user', 'febs/system/user/Index', 'user:view', '', '0', 1, '2017-12-27 16:47:13',
        '2019-01-22 06:45:55');
INSERT INTO `t_menu`
VALUES (4, 1, '角色管理', '/system/role', 'febs/system/role/Index', 'role:view', '', '0', 2, '2017-12-27 16:48:09',
        '2018-04-25 09:01:12');
INSERT INTO `t_menu`
VALUES (5, 1, '菜单管理', '/system/menu', 'febs/system/menu/Index', 'menu:view', '', '0', 3, '2017-12-27 16:48:57',
        '2018-04-25 09:01:30');
INSERT INTO `t_menu`
VALUES (6, 1, '部门管理', '/system/dept', 'febs/system/dept/Index', 'dept:view', '', '0', 4, '2017-12-27 16:57:33',
        '2018-04-25 09:01:40');
INSERT INTO `t_menu`
VALUES (10, 2, '系统日志', '/monitor/systemlog', 'febs/monitor/systemlog/Index', 'log:view', '', '0', 2,
        '2017-12-27 17:00:50', '2020-04-13 11:38:04');
INSERT INTO `t_menu`
VALUES (11, 3, '新增用户', '', '', 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `t_menu`
VALUES (12, 3, '修改用户', '', '', 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `t_menu`
VALUES (13, 3, '删除用户', '', '', 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `t_menu`
VALUES (14, 4, '新增角色', '', '', 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu`
VALUES (15, 4, '修改角色', '', '', 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu`
VALUES (16, 4, '删除角色', '', '', 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu`
VALUES (17, 5, '新增菜单', '', '', 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu`
VALUES (18, 5, '修改菜单', '', '', 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu`
VALUES (19, 5, '删除菜单', '', '', 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu`
VALUES (20, 6, '新增部门', '', '', 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu`
VALUES (21, 6, '修改部门', '', '', 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu`
VALUES (22, 6, '删除部门', '', '', 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu`
VALUES (24, 10, '删除日志', '', '', 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', NULL);
INSERT INTO `t_menu`
VALUES (130, 3, '导出Excel', NULL, NULL, 'user:export', NULL, '1', NULL, '2019-01-23 06:35:16', NULL);
INSERT INTO `t_menu`
VALUES (131, 4, '导出Excel', NULL, NULL, 'role:export', NULL, '1', NULL, '2019-01-23 06:35:36', NULL);
INSERT INTO `t_menu`
VALUES (132, 5, '导出Excel', NULL, NULL, 'menu:export', NULL, '1', NULL, '2019-01-23 06:36:05', NULL);
INSERT INTO `t_menu`
VALUES (133, 6, '导出Excel', NULL, NULL, 'dept:export', NULL, '1', NULL, '2019-01-23 06:36:25', NULL);
INSERT INTO `t_menu`
VALUES (135, 3, '密码重置', NULL, NULL, 'user:reset', NULL, '1', NULL, '2019-01-23 06:37:00', NULL);
INSERT INTO `t_menu`
VALUES (136, 10, '导出Excel', NULL, NULL, 'log:export', NULL, '1', NULL, '2019-01-23 06:37:27', NULL);
INSERT INTO `t_menu`
VALUES (150, 2, '登录日志', '/monitor/loginlog', 'febs/monitor/loginlog/Index', 'monitor:loginlog', '', '0', 3,
        '2019-07-22 13:41:17', '2020-04-13 11:38:08');
INSERT INTO `t_menu`
VALUES (151, 150, '删除日志', NULL, NULL, 'loginlog:delete', NULL, '1', NULL, '2019-07-22 13:43:04', NULL);
INSERT INTO `t_menu`
VALUES (152, 150, '导出Excel', NULL, NULL, 'loginlog:export', NULL, '1', NULL, '2019-07-22 13:43:30', NULL);
INSERT INTO `t_menu`
VALUES (154, 0, '其他模块', '/others', 'Layout', '', 'el-icon-shopping-bag-1', '0', 6, '2019-07-25 10:16:16',
        '2020-04-14 18:38:20');
INSERT INTO `t_menu`
VALUES (155, 154, '导入导出', '/others/eximport', 'febs/others/eximport/Index', 'others:eximport', '', '0', 1,
        '2019-07-25 10:19:31', NULL);
INSERT INTO `t_menu`
VALUES (156, 0, '代码生成', '/gen', 'Layout', '', 'el-icon-printer', '0', 4, '2019-07-25 10:24:03', '2020-01-16 13:59:49');
INSERT INTO `t_menu`
VALUES (157, 156, '基础配置', '/gen/config', 'febs/gen/config/Index', 'gen:config', '', '0', 1, '2019-07-25 10:24:55',
        '2020-04-09 14:21:54');
INSERT INTO `t_menu`
VALUES (158, 156, '生成代码', '/gen/generate', 'febs/gen/generate/Index', 'gen:generate', '', '0', 2, '2019-07-25 10:25:26',
        '2019-07-25 11:13:20');
INSERT INTO `t_menu`
VALUES (159, 157, '修改配置', NULL, NULL, 'gen:config:update', NULL, '1', NULL, '2019-07-26 16:22:56', NULL);
INSERT INTO `t_menu`
VALUES (160, 158, '打包生成', NULL, NULL, 'gen:generate:gen', NULL, '1', NULL, '2019-07-26 16:23:38',
        '2019-07-26 16:23:53');
INSERT INTO `t_menu`
VALUES (163, 1, '客户端管理', '/client', 'febs/system/client/Index', 'client:view', '', '0', 5, '2019-09-26 22:58:09', NULL);
INSERT INTO `t_menu`
VALUES (164, 163, '新增', NULL, NULL, 'client:add', NULL, '1', NULL, '2019-09-26 22:58:21', NULL);
INSERT INTO `t_menu`
VALUES (165, 163, '修改', NULL, NULL, 'client:update', NULL, '1', NULL, '2019-09-26 22:58:43', NULL);
INSERT INTO `t_menu`
VALUES (166, 163, '删除', NULL, NULL, 'client:delete', NULL, '1', NULL, '2019-09-26 22:58:55', NULL);
INSERT INTO `t_menu`
VALUES (167, 163, '解密', NULL, NULL, 'client:decrypt', NULL, '1', NULL, '2019-09-26 22:59:08', NULL);
INSERT INTO `t_menu`
VALUES (168, 0, '静态组件', '/components', 'Layout', '', 'el-icon-present', '0', 7, '2019-12-02 16:41:28',
        '2020-04-14 18:38:23');
INSERT INTO `t_menu`
VALUES (169, 168, '二级菜单', '/two', 'demos/two/Index', '', '', '0', 1, '2019-12-02 16:41:51', NULL);
INSERT INTO `t_menu`
VALUES (170, 169, '三级菜单', '/three', 'demos/two/three/Index', '', '', '0', 1, '2019-12-02 16:42:09', NULL);
INSERT INTO `t_menu`
VALUES (171, 168, 'MarkDown', '/components/markdown', 'demos/markdown', '', '', '0', 2, '2019-12-02 16:42:34', NULL);
INSERT INTO `t_menu`
VALUES (172, 168, '富文本编辑器', '/components/tinymce', 'demos/tinymce', '', '', '0', 3, '2019-12-02 16:42:50', NULL);
INSERT INTO `t_menu`
VALUES (173, 0, '网关管理', '/route', 'Layout', '', 'el-icon-odometer', '0', 3, '2020-01-16 14:00:15', NULL);
INSERT INTO `t_menu`
VALUES (174, 173, '网关用户', '/route/user', 'febs/route/routeuser/Index', '', '', '0', 1, '2020-01-16 14:00:32', NULL);
INSERT INTO `t_menu`
VALUES (175, 173, '网关日志', '/route/log', 'febs/route/routelog/Index', '', '', '0', 2, '2020-01-16 14:00:47', NULL);
INSERT INTO `t_menu`
VALUES (176, 173, '限流规则', '/route/ratelimitrule', 'febs/route/ratelimitrule/Index', '', '', '0', 3,
        '2020-01-16 14:01:01', NULL);
INSERT INTO `t_menu`
VALUES (177, 173, '限流日志', '/route/ratelimitlog', 'febs/route/ratelimitlog/Index', '', '', '0', 4, '2020-01-16 14:01:17',
        NULL);
INSERT INTO `t_menu`
VALUES (178, 173, '黑名单管理', '/route/blacklist', 'febs/route/blacklist/Index', '', '', '0', 5, '2020-01-16 14:01:32',
        NULL);
INSERT INTO `t_menu`
VALUES (179, 173, '黑名单日志', '/route/blocklog', 'febs/route/blocklog/Index', '', '', '0', 6, '2020-01-16 14:01:49', NULL);
INSERT INTO `t_menu`
VALUES (180, 2, '监控面板', '/monitor/dashboard', 'febs/monitor/dashboard/Index', 'monitor:dashboard', '', '0', 1,
        '2020-04-13 09:44:09', '2020-04-13 11:38:00');
INSERT INTO `t_menu`
VALUES (181, 154, '个人博客', '/others/blog', 'febs/others/blog/Index', '', '', '0', 2, '2020-04-13 16:11:48',
        '2020-04-13 16:12:26');
INSERT INTO `t_menu`
VALUES (182, 154, '数据权限', '/others/datapermission', 'febs/others/datapermission/Index', 'others:datapermission', '',
        '0', 3, '2020-04-14 14:51:35', '2020-04-14 15:37:19');
INSERT INTO `t_menu`
VALUES (183, 0, '任务调度', '/job', 'Layout', '', 'el-icon-alarm-clock', '0', 5, '2020-04-14 18:39:35',
        '2020-04-14 18:39:53');
INSERT INTO `t_menu`
VALUES (184, 183, '任务列表', '/job/list', 'febs/job/job/Index', 'job:view', '', '0', 1, '2020-04-14 18:40:37',
        '2020-04-14 18:41:36');
INSERT INTO `t_menu`
VALUES (185, 183, '调度日志', '/job/log', 'febs/job/log/Index', 'job:log:view', '', '0', 2, '2020-04-14 18:42:25', NULL);
INSERT INTO `t_menu`
VALUES (186, 184, '新增任务', NULL, NULL, 'job:add', NULL, '1', NULL, '2020-04-14 18:59:55', '2020-04-15 08:56:03');
INSERT INTO `t_menu`
VALUES (187, 184, '修改任务', NULL, NULL, 'job:update', NULL, '1', NULL, '2020-04-14 19:00:13', NULL);
INSERT INTO `t_menu`
VALUES (188, 184, '删除任务', NULL, NULL, 'job:delete', NULL, '1', NULL, '2020-04-14 19:00:26', NULL);
INSERT INTO `t_menu`
VALUES (189, 184, '暂停任务', NULL, NULL, 'job:pause', NULL, '1', NULL, '2020-04-14 19:00:42', NULL);
INSERT INTO `t_menu`
VALUES (190, 184, '恢复任务', NULL, NULL, 'job:resume', NULL, '1', NULL, '2020-04-14 19:00:56', NULL);
INSERT INTO `t_menu`
VALUES (191, 184, '立即执行一次', NULL, NULL, 'job:run', NULL, '1', NULL, '2020-04-14 19:01:42', NULL);
INSERT INTO `t_menu`
VALUES (192, 184, '导出Excel', NULL, NULL, 'job:export', NULL, '1', NULL, '2020-04-14 19:01:59', NULL);
INSERT INTO `t_menu`
VALUES (193, 185, '删除', NULL, NULL, 'job:log:delete', NULL, '1', NULL, '2020-04-15 14:01:33', NULL);
INSERT INTO `t_menu`
VALUES (194, 185, '导出', NULL, NULL, 'job:log:export', NULL, '1', NULL, '2020-04-15 14:01:45', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `ROLE_ID`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `ROLE_NAME`   varchar(10) NOT NULL COMMENT '角色名称',
    `REMARK`      varchar(100) DEFAULT NULL COMMENT '角色描述',
    `CREATE_TIME` datetime    NOT NULL COMMENT '创建时间',
    `MODIFY_TIME` datetime     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role`
VALUES (1, '管理员', '管理员', '2017-12-27 16:23:11', '2020-04-15 14:02:27');
INSERT INTO `t_role`
VALUES (2, '注册用户', '可查看，新增，导出', '2019-01-04 14:11:28', '2020-04-15 16:00:16');
INSERT INTO `t_role`
VALUES (3, '系统监控员', '负责系统监控模块', '2019-09-01 10:30:25', '2019-09-01 10:30:37');
INSERT INTO `t_role`
VALUES (4, '测试角色', '测试角色', '2020-03-08 19:16:01', '2020-04-13 11:26:13');
COMMIT;

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`
(
    `ROLE_ID` bigint(20) NOT NULL,
    `MENU_ID` bigint(20) NOT NULL,
    PRIMARY KEY (`ROLE_ID`, `MENU_ID`),
    KEY `t_role_menu_menu_id` (`MENU_ID`),
    KEY `t_role_menu_role_id` (`ROLE_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='角色菜单关联表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_role_menu`
VALUES (1, 1);
INSERT INTO `t_role_menu`
VALUES (1, 2);
INSERT INTO `t_role_menu`
VALUES (1, 3);
INSERT INTO `t_role_menu`
VALUES (1, 4);
INSERT INTO `t_role_menu`
VALUES (1, 5);
INSERT INTO `t_role_menu`
VALUES (1, 6);
INSERT INTO `t_role_menu`
VALUES (1, 10);
INSERT INTO `t_role_menu`
VALUES (1, 11);
INSERT INTO `t_role_menu`
VALUES (1, 12);
INSERT INTO `t_role_menu`
VALUES (1, 13);
INSERT INTO `t_role_menu`
VALUES (1, 14);
INSERT INTO `t_role_menu`
VALUES (1, 15);
INSERT INTO `t_role_menu`
VALUES (1, 16);
INSERT INTO `t_role_menu`
VALUES (1, 17);
INSERT INTO `t_role_menu`
VALUES (1, 18);
INSERT INTO `t_role_menu`
VALUES (1, 19);
INSERT INTO `t_role_menu`
VALUES (1, 20);
INSERT INTO `t_role_menu`
VALUES (1, 21);
INSERT INTO `t_role_menu`
VALUES (1, 22);
INSERT INTO `t_role_menu`
VALUES (1, 24);
INSERT INTO `t_role_menu`
VALUES (1, 130);
INSERT INTO `t_role_menu`
VALUES (1, 131);
INSERT INTO `t_role_menu`
VALUES (1, 132);
INSERT INTO `t_role_menu`
VALUES (1, 133);
INSERT INTO `t_role_menu`
VALUES (1, 135);
INSERT INTO `t_role_menu`
VALUES (1, 136);
INSERT INTO `t_role_menu`
VALUES (1, 150);
INSERT INTO `t_role_menu`
VALUES (1, 151);
INSERT INTO `t_role_menu`
VALUES (1, 152);
INSERT INTO `t_role_menu`
VALUES (1, 154);
INSERT INTO `t_role_menu`
VALUES (1, 155);
INSERT INTO `t_role_menu`
VALUES (1, 156);
INSERT INTO `t_role_menu`
VALUES (1, 157);
INSERT INTO `t_role_menu`
VALUES (1, 158);
INSERT INTO `t_role_menu`
VALUES (1, 159);
INSERT INTO `t_role_menu`
VALUES (1, 160);
INSERT INTO `t_role_menu`
VALUES (1, 163);
INSERT INTO `t_role_menu`
VALUES (1, 164);
INSERT INTO `t_role_menu`
VALUES (1, 165);
INSERT INTO `t_role_menu`
VALUES (1, 166);
INSERT INTO `t_role_menu`
VALUES (1, 167);
INSERT INTO `t_role_menu`
VALUES (1, 168);
INSERT INTO `t_role_menu`
VALUES (1, 169);
INSERT INTO `t_role_menu`
VALUES (1, 170);
INSERT INTO `t_role_menu`
VALUES (1, 171);
INSERT INTO `t_role_menu`
VALUES (1, 172);
INSERT INTO `t_role_menu`
VALUES (1, 173);
INSERT INTO `t_role_menu`
VALUES (1, 174);
INSERT INTO `t_role_menu`
VALUES (1, 175);
INSERT INTO `t_role_menu`
VALUES (1, 176);
INSERT INTO `t_role_menu`
VALUES (1, 177);
INSERT INTO `t_role_menu`
VALUES (1, 178);
INSERT INTO `t_role_menu`
VALUES (1, 179);
INSERT INTO `t_role_menu`
VALUES (1, 180);
INSERT INTO `t_role_menu`
VALUES (1, 181);
INSERT INTO `t_role_menu`
VALUES (1, 182);
INSERT INTO `t_role_menu`
VALUES (1, 183);
INSERT INTO `t_role_menu`
VALUES (1, 184);
INSERT INTO `t_role_menu`
VALUES (1, 185);
INSERT INTO `t_role_menu`
VALUES (1, 186);
INSERT INTO `t_role_menu`
VALUES (1, 187);
INSERT INTO `t_role_menu`
VALUES (1, 188);
INSERT INTO `t_role_menu`
VALUES (1, 189);
INSERT INTO `t_role_menu`
VALUES (1, 190);
INSERT INTO `t_role_menu`
VALUES (1, 191);
INSERT INTO `t_role_menu`
VALUES (1, 192);
INSERT INTO `t_role_menu`
VALUES (1, 193);
INSERT INTO `t_role_menu`
VALUES (1, 194);
INSERT INTO `t_role_menu`
VALUES (2, 1);
INSERT INTO `t_role_menu`
VALUES (2, 2);
INSERT INTO `t_role_menu`
VALUES (2, 3);
INSERT INTO `t_role_menu`
VALUES (2, 4);
INSERT INTO `t_role_menu`
VALUES (2, 5);
INSERT INTO `t_role_menu`
VALUES (2, 6);
INSERT INTO `t_role_menu`
VALUES (2, 10);
INSERT INTO `t_role_menu`
VALUES (2, 14);
INSERT INTO `t_role_menu`
VALUES (2, 17);
INSERT INTO `t_role_menu`
VALUES (2, 20);
INSERT INTO `t_role_menu`
VALUES (2, 130);
INSERT INTO `t_role_menu`
VALUES (2, 131);
INSERT INTO `t_role_menu`
VALUES (2, 132);
INSERT INTO `t_role_menu`
VALUES (2, 133);
INSERT INTO `t_role_menu`
VALUES (2, 136);
INSERT INTO `t_role_menu`
VALUES (2, 150);
INSERT INTO `t_role_menu`
VALUES (2, 152);
INSERT INTO `t_role_menu`
VALUES (2, 154);
INSERT INTO `t_role_menu`
VALUES (2, 155);
INSERT INTO `t_role_menu`
VALUES (2, 156);
INSERT INTO `t_role_menu`
VALUES (2, 157);
INSERT INTO `t_role_menu`
VALUES (2, 158);
INSERT INTO `t_role_menu`
VALUES (2, 160);
INSERT INTO `t_role_menu`
VALUES (2, 163);
INSERT INTO `t_role_menu`
VALUES (2, 164);
INSERT INTO `t_role_menu`
VALUES (2, 167);
INSERT INTO `t_role_menu`
VALUES (2, 168);
INSERT INTO `t_role_menu`
VALUES (2, 169);
INSERT INTO `t_role_menu`
VALUES (2, 170);
INSERT INTO `t_role_menu`
VALUES (2, 171);
INSERT INTO `t_role_menu`
VALUES (2, 172);
INSERT INTO `t_role_menu`
VALUES (2, 173);
INSERT INTO `t_role_menu`
VALUES (2, 174);
INSERT INTO `t_role_menu`
VALUES (2, 175);
INSERT INTO `t_role_menu`
VALUES (2, 176);
INSERT INTO `t_role_menu`
VALUES (2, 177);
INSERT INTO `t_role_menu`
VALUES (2, 178);
INSERT INTO `t_role_menu`
VALUES (2, 179);
INSERT INTO `t_role_menu`
VALUES (2, 180);
INSERT INTO `t_role_menu`
VALUES (2, 181);
INSERT INTO `t_role_menu`
VALUES (2, 182);
INSERT INTO `t_role_menu`
VALUES (2, 183);
INSERT INTO `t_role_menu`
VALUES (2, 184);
INSERT INTO `t_role_menu`
VALUES (2, 185);
INSERT INTO `t_role_menu`
VALUES (2, 192);
INSERT INTO `t_role_menu`
VALUES (2, 194);
INSERT INTO `t_role_menu`
VALUES (3, 2);
INSERT INTO `t_role_menu`
VALUES (3, 10);
INSERT INTO `t_role_menu`
VALUES (3, 24);
INSERT INTO `t_role_menu`
VALUES (3, 136);
INSERT INTO `t_role_menu`
VALUES (3, 148);
INSERT INTO `t_role_menu`
VALUES (3, 149);
INSERT INTO `t_role_menu`
VALUES (3, 150);
INSERT INTO `t_role_menu`
VALUES (3, 151);
INSERT INTO `t_role_menu`
VALUES (3, 152);
INSERT INTO `t_role_menu`
VALUES (3, 153);
INSERT INTO `t_role_menu`
VALUES (4, 1);
INSERT INTO `t_role_menu`
VALUES (4, 3);
INSERT INTO `t_role_menu`
VALUES (4, 11);
INSERT INTO `t_role_menu`
VALUES (4, 12);
INSERT INTO `t_role_menu`
VALUES (4, 13);
INSERT INTO `t_role_menu`
VALUES (4, 130);
INSERT INTO `t_role_menu`
VALUES (4, 135);
COMMIT;

-- ----------------------------
-- Table structure for t_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_log`;
CREATE TABLE `t_trade_log`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `goods_id`    int(11)     NOT NULL COMMENT '商品ID',
    `goods_name`  varchar(50) NOT NULL COMMENT '商品名称',
    `status`      varchar(50) NOT NULL COMMENT '状态',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='分布式事务测试';

-- ----------------------------
-- Records of t_trade_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `USER_ID`         bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `USERNAME`        varchar(50)  NOT NULL COMMENT '用户名',
    `PASSWORD`        varchar(128) NOT NULL COMMENT '密码',
    `DEPT_ID`         bigint(20)   DEFAULT NULL COMMENT '部门ID',
    `EMAIL`           varchar(128) DEFAULT NULL COMMENT '邮箱',
    `MOBILE`          varchar(20)  DEFAULT NULL COMMENT '联系电话',
    `STATUS`          char(1)      NOT NULL COMMENT '状态 0锁定 1有效',
    `CREATE_TIME`     datetime     NOT NULL COMMENT '创建时间',
    `MODIFY_TIME`     datetime     DEFAULT NULL COMMENT '修改时间',
    `LAST_LOGIN_TIME` datetime     DEFAULT NULL COMMENT '最近访问时间',
    `SSEX`            char(1)      DEFAULT NULL COMMENT '性别 0男 1女 2保密',
    `IS_TAB`          char(1)      DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
    `THEME`           varchar(10)  DEFAULT NULL COMMENT '主题',
    `AVATAR`          varchar(100) DEFAULT NULL COMMENT '头像',
    `DESCRIPTION`     varchar(100) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`USER_ID`) USING BTREE,
    KEY `t_user_username` (`USERNAME`),
    KEY `t_user_mobile` (`MOBILE`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user`
VALUES (1, 'MrBird', '$2a$10$gzhiUb1ldc1Rf3lka4k/WOoFKKGPepHSzJxzcPSN5/65SzkMdc.SK', 2, 'mrbird@qq.com', '17788888888',
        '1', '2019-06-14 20:39:22', '2020-04-15 16:00:32', '2020-04-15 16:03:13', '0', '1', 'white',
        'gaOngJwsRYRaVAuXXcmB.png', '我是帅比作者。');
INSERT INTO `t_user`
VALUES (15, 'scott', '$2a$10$7tATi2STciLHnEgO/RfIxOYf2MQBu/SDVMRDs54rlSYVj2VmwwCHC', 5, 'scott@hotmail.com',
        '17720888888', '1', '2019-07-20 19:00:32', '2020-04-15 16:00:42', '2020-04-14 16:49:27', '2', NULL, NULL,
        'BiazfanxmamNRoxxVxka.png', NULL);
INSERT INTO `t_user`
VALUES (16, 'Jane', '$2a$10$ECkfipOPY7hORVdlSzIOX.8hnig0shAZQPG8pQ7D5iVP.uVogmmHy', 4, 'Jane@hotmail.com',
        '13489898989', '1', '2019-09-01 10:31:21', '2020-04-15 16:00:48', '2019-09-01 10:32:27', '1', NULL, NULL,
        '2dd7a2d09fa94bf8b5c52e5318868b4d9.jpg', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user_connection
-- ----------------------------
DROP TABLE IF EXISTS `t_user_connection`;
CREATE TABLE `t_user_connection`
(
    `USER_NAME`          varchar(50) NOT NULL COMMENT 'FEBS系统用户名',
    `PROVIDER_NAME`      varchar(20) NOT NULL COMMENT '第三方平台名称',
    `PROVIDER_USER_ID`   varchar(50) NOT NULL COMMENT '第三方平台账户ID',
    `PROVIDER_USER_NAME` varchar(50)  DEFAULT NULL COMMENT '第三方平台用户名',
    `NICK_NAME`          varchar(50)  DEFAULT NULL COMMENT '第三方平台昵称',
    `IMAGE_URL`          varchar(512) DEFAULT NULL COMMENT '第三方平台头像',
    `LOCATION`           varchar(255) DEFAULT NULL COMMENT '地址',
    `REMARK`             varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`USER_NAME`, `PROVIDER_NAME`, `PROVIDER_USER_ID`) USING BTREE,
    UNIQUE KEY `UserConnectionRank` (`USER_NAME`, `PROVIDER_NAME`, `PROVIDER_USER_ID`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='系统用户社交账户关联表';

-- ----------------------------
-- Records of t_user_connection
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user_data_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_user_data_permission`;
CREATE TABLE `t_user_data_permission`
(
    `USER_ID` bigint(20) NOT NULL,
    `DEPT_ID` bigint(20) NOT NULL,
    PRIMARY KEY (`USER_ID`, `DEPT_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户数据权限关联表';

-- ----------------------------
-- Records of t_user_data_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_user_data_permission`
VALUES (1, 1);
INSERT INTO `t_user_data_permission`
VALUES (1, 2);
INSERT INTO `t_user_data_permission`
VALUES (1, 3);
INSERT INTO `t_user_data_permission`
VALUES (1, 4);
INSERT INTO `t_user_data_permission`
VALUES (1, 5);
INSERT INTO `t_user_data_permission`
VALUES (1, 6);
INSERT INTO `t_user_data_permission`
VALUES (15, 1);
INSERT INTO `t_user_data_permission`
VALUES (15, 2);
INSERT INTO `t_user_data_permission`
VALUES (16, 4);
INSERT INTO `t_user_data_permission`
VALUES (16, 5);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`
(
    `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
    `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`USER_ID`, `ROLE_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role`
VALUES (1, 1);
INSERT INTO `t_user_role`
VALUES (15, 2);
INSERT INTO `t_user_role`
VALUES (16, 3);
COMMIT;

CREATE TABLE `t_tx_exception`
(
    `id`                bigint(20)                                                    NOT NULL AUTO_INCREMENT,
    `group_id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `unit_id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL,
    `mod_id`            varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `transaction_state` tinyint(4)                                                    NULL DEFAULT NULL,
    `registrar`         tinyint(4)                                                    NULL DEFAULT NULL,
    `ex_state`          tinyint(4)                                                    NULL DEFAULT NULL COMMENT '0 待处理 1已处理',
    `remark`            varchar(10240)                                                NULL DEFAULT NULL COMMENT '备注',
    `create_time`       datetime(0)                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
