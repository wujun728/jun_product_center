/*
 Navicat Premium Data Transfer

 Source Server         : pianowan-test
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 122.112.164.217:3306
 Source Schema         : teamwork

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 03/02/2021 10:19:11
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
-- Records of team_collection
-- ----------------------------
INSERT INTO `team_collection` VALUES (113, 'k15gd32z0npileh96qxmcauw', 'task', '28qet31u7kym6gi54pa9jldr', '6v7be19pwman2fird04gqu53', '2020-03-22 11:22:24');
INSERT INTO `team_collection` VALUES (115, '4cf53494a1cb48718ade6d0af0b317de', 'task', '0c0e2970ce4c4dd0be94eb492aa76651', '6v7be19pwman2fird04gqu53', '2020-06-29 11:22:29');
INSERT INTO `team_collection` VALUES (116, '17b0602327f240b7babe00207f0e3414', 'task', 'ccd020c2f02949a3b2a7544fce2fbff5', '6v7be19pwman2fird04gqu53', '2020-07-01 19:04:19');

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
INSERT INTO `team_department` VALUES (5, 'axr73ydzo5gemks0pl9qnt16', '6v7be19pwman2fird04gqu53', '研发部', 0, '', NULL, '2020-03-24 15:41:16', '');
INSERT INTO `team_department` VALUES (6, '74818cf1fd624d84bdd8ecccdfdc4cc6', '6v7be19pwman2fird04gqu53', '技术部', 0, '', NULL, '2020-03-24 15:48:03', NULL);
INSERT INTO `team_department` VALUES (20, 'b8dba889327f466fb623e1a89e9c83d8', '2360f2f0f79447f4a2498ae06a9b132d', '项目管理部', 0, '', NULL, '2021-02-01 16:23:57', NULL);
INSERT INTO `team_department` VALUES (21, '900b6cfe182b41de8377fdb9ae40c7ff', '2360f2f0f79447f4a2498ae06a9b132d', '项目调研部', 0, 'b8dba889327f466fb623e1a89e9c83d8', NULL, '2021-02-01 16:24:55', NULL);
INSERT INTO `team_department` VALUES (22, '585c3823b5dd462cbbc8f2b652878d38', '2360f2f0f79447f4a2498ae06a9b132d', '项目调研前期部门', 0, 'b8dba889327f466fb623e1a89e9c83d8', NULL, '2021-02-01 16:25:57', NULL);
INSERT INTO `team_department` VALUES (23, '17d96c3386c74316961aa15a8de3940e', '2360f2f0f79447f4a2498ae06a9b132d', 'test', 0, '', NULL, '2021-02-01 17:11:56', NULL);
INSERT INTO `team_department` VALUES (27, '09b271e39fba4094bcda0e73b99b504b', '2360f2f0f79447f4a2498ae06a9b132d', '1215', 0, 'f1f7040b0e8848e7ba53b9366eb38090', NULL, '2021-02-01 17:37:11', NULL);
INSERT INTO `team_department` VALUES (28, '2bac7059c5614176a6772cd4ad0b0e1b', '2360f2f0f79447f4a2498ae06a9b132d', '852', 0, '09b271e39fba4094bcda0e73b99b504b', NULL, '2021-02-01 17:37:22', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_file
-- ----------------------------
INSERT INTO `team_file` VALUES (44, 'wtp4v8ukqoe3g5bichj07rn1', 'static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315132728-mysqlLog记录.txt', 'mysqlLog记录', 'txt', 69, '', '6v7be19pwman2fird04gqu53', NULL, 'oebw2ycf016j7pnxum5zikg4', '6v7be19pwman2fird04gqu53', '2020-03-15 13:27:28', 0, '', 0, 'http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315132728-mysqlLog记录.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (45, 'ojc1vktab49fx6z2iru03me7', 'static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315141207-login.txt', '123456', 'txt', 642, '', '6v7be19pwman2fird04gqu53', NULL, 'oebw2ycf016j7pnxum5zikg4', '6v7be19pwman2fird04gqu53', '2020-03-15 14:12:07', 0, '', 1, 'http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315141207-login.txt', 'text/plain', '2020-04-07 21:51:58');
INSERT INTO `team_file` VALUES (46, 'uawbpjz0kl6oc7htfng415is', 'static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200605/20200605233849-0423.jpg', '0423111', 'jpg', 50112, '', '6v7be19pwman2fird04gqu53', NULL, 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '2020-06-05 23:38:49', 0, '', 1, 'http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200605/20200605233849-0423.jpg', 'image/jpeg', '2020-06-06 10:18:22');
INSERT INTO `team_file` VALUES (47, '9d457e348c1a403cac7092e9ff068f7f', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/2020060717362672a23f0921b1441b92b1212ddfc3090c.txt', '72a23f0921b1441b92b1212ddfc3090c.txt', NULL, 1366, NULL, '6v7be19pwman2fird04gqu53', 'f806fa9f3f8048dba65e70b1e556e7ec', 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '20200607173626', 0, NULL, 0, 'http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/2020060717362672a23f0921b1441b92b1212ddfc3090c.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (48, 'bwft6ymczkd5n9goqvl0h81e', 'static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200607/20200607173956-I6000请求报文-可用.txt', 'I6000请求报文-可用', 'txt', 1366, '', '6v7be19pwman2fird04gqu53', NULL, 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '2020-06-07 17:39:56', 0, '', 0, 'http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200607/20200607173956-I6000请求报文-可用.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (49, '23f3610d33f94e49925d8b92883b7c1c', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607174546f60e8a1d6eba4efba9932c95093f2ca8.txt', 'f60e8a1d6eba4efba9932c95093f2ca8.txt', NULL, 1366, NULL, '6v7be19pwman2fird04gqu53', 'f806fa9f3f8048dba65e70b1e556e7ec', 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '20200607174546', 0, NULL, 0, 'http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607174546f60e8a1d6eba4efba9932c95093f2ca8.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (50, '0c4afd2433ce4d2ab7cd084973cc238c', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607175226c13515b5b23a46b2b49a916fea2a2e14.txt', 'c13515b5b23a46b2b49a916fea2a2e14.txt', NULL, 1366, NULL, '6v7be19pwman2fird04gqu53', 'f806fa9f3f8048dba65e70b1e556e7ec', 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '20200607175226', 0, NULL, 0, 'http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607175226c13515b5b23a46b2b49a916fea2a2e14.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (51, 'ebba1d8d754647ae8aa9761579837d90', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/202006071757473dff86112f964a73b1bffe34ac735d65.txt', 'I6000请求报文-可用.txt', NULL, 1366, NULL, '6v7be19pwman2fird04gqu53', 'f806fa9f3f8048dba65e70b1e556e7ec', 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '20200607175747', 0, NULL, 0, 'http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/202006071757473dff86112f964a73b1bffe34ac735d65.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (52, 'b9005e3c02de423a90f16cb7639db22c', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607180433dcbe22f1804841edb5325b3f07a043bc.txt', 'I6000请求报文-可用.txt', NULL, 1366, NULL, '6v7be19pwman2fird04gqu53', 'f806fa9f3f8048dba65e70b1e556e7ec', 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '20200607180433', 0, NULL, 0, 'http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607180433dcbe22f1804841edb5325b3f07a043bc.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (53, '4d13154924644452a967ca0cd4d03078', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/202006071805284f086148cdbb4b2d9fcd242a225b3503.txt', 'I6000请求报文-可用.txt', NULL, 1366, NULL, '6v7be19pwman2fird04gqu53', 'f806fa9f3f8048dba65e70b1e556e7ec', 'borhsewfgqxy38k1jmtznuv5', '6v7be19pwman2fird04gqu53', '20200607180528', 0, NULL, 0, 'http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/202006071805284f086148cdbb4b2d9fcd242a225b3503.txt', 'text/plain', '');
INSERT INTO `team_file` VALUES (54, '73b95f12f78542f681f391eef04df0d7', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-29/20200629112345659e8701898a4c509d193edb9bea6d95.sql', 'pearproject', 'sql', 316197, NULL, '6v7be19pwman2fird04gqu53', NULL, '4c2d9d0c0a3542798259a3cbe24a13ff', '6v7be19pwman2fird04gqu53', '20200629112345', 0, NULL, 0, 'http://127.0.0.1:8888D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-29/20200629112345659e8701898a4c509d193edb9bea6d95.sql', 'text/plain', '2020-06-29 11:28:59');
INSERT INTO `team_file` VALUES (55, 'bdc0470980844626ac98712a91a8c42c', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-29/20200629232142ff0ee856cc514fc7a17dd0aab2bd9c00.sql', 'fwxq.sql', NULL, 16389, NULL, '6v7be19pwman2fird04gqu53', 'l9n0imsewcbg5okzqp8fjd7x', 'nd5gkr9qc7wumei8zo630y41', '6v7be19pwman2fird04gqu53', '20200629232142', 0, NULL, 0, 'http://120.27.62.173:80D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-29/20200629232142ff0ee856cc514fc7a17dd0aab2bd9c00.sql', 'text/plain', '');
INSERT INTO `team_file` VALUES (56, '48b2c5a2e94a488d87f410c5b1e2d9cd', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-30/2020063001412991de88d9cc3d4103b145acb01bf0f3a8.png', '中奖名单-最终版_01.png', NULL, 448970, NULL, '6v7be19pwman2fird04gqu53', 'cf9360a4b38d43e9b51da10252b816ab', '11c80c7fabd146ca95d24621124f61b4', '6v7be19pwman2fird04gqu53', '20200630014129', 0, NULL, 1, 'http://120.27.62.173:80D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-30/2020063001412991de88d9cc3d4103b145acb01bf0f3a8.png', 'text/plain', '2020-06-30 01:44:59');
INSERT INTO `team_file` VALUES (57, '4b2e345e91244a73892a291d987b2c03', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/2020070415542615bfcf5583dc4ef891a00774bba0523f.xls', '15bfcf5583dc4ef891a00774bba0523f', 'xls', 33280, NULL, '6v7be19pwman2fird04gqu53', '7d92754ae4244c0e8eb7b4f93da42f69', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 15:54:26', 0, NULL, 1, 'http://120.27.62.173:80D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/2020070415542615bfcf5583dc4ef891a00774bba0523f.xls', 'text/plain', '2020-07-05 21:42:24');
INSERT INTO `team_file` VALUES (58, '4bed1c4bad6d4fc9b3d6e2ea9c9ed092', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/20200704155535211682cf55114e5eb896a72eb57fd61b.docx', '211682cf55114e5eb896a72eb57fd61b', 'docx', 12431, NULL, '6v7be19pwman2fird04gqu53', 'b7cb4cb5d61542148cf02c4c9e5639f5', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 15:55:35', 0, NULL, 1, 'http://120.27.62.173:80D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/20200704155535211682cf55114e5eb896a72eb57fd61b.docx', 'text/plain', '2020-07-05 21:42:27');
INSERT INTO `team_file` VALUES (59, '948beeea21da4ba58cd1ef170c7c24c1', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/2020070409082401dcaeac03854019adc95ff14ee3e021.sql', '01dcaeac03854019adc95ff14ee3e021', 'sql', 16389, NULL, '6v7be19pwman2fird04gqu53', '38144bfd4f6c4a3f8163bba9e3cca9b9', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 09:08:24', 0, NULL, 1, 'http://127.0.0.1:8888D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/2020070409082401dcaeac03854019adc95ff14ee3e021.sql', 'text/plain', '2020-07-05 21:42:31');
INSERT INTO `team_file` VALUES (60, '1445ab1ef6f547b4993cd27d96b1f98a', 'D:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/202007040925561445ab1ef6f547b4993cd27d96b1f98afwxq.sql', 'fwxq', 'sql', 16389, NULL, '6v7be19pwman2fird04gqu53', '38144bfd4f6c4a3f8163bba9e3cca9b9', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 09:25:56', 0, NULL, 1, 'http://127.0.0.1:8888/static/upload/6v7be19pwman2fird04gqu53/2020-07-04/202007040925561445ab1ef6f547b4993cd27d96b1f98afwxq.sql', 'text/plain', '2020-07-05 21:42:35');
INSERT INTO `team_file` VALUES (61, 'd1090ac9747643cdb85966698fb9e7e0', 'D:/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200704/d1090ac9747643cdb85966698fb9e7e0-物资发放签收单.xls', '物资发放签收单', 'xls', 20992, NULL, '6v7be19pwman2fird04gqu53', '38144bfd4f6c4a3f8163bba9e3cca9b9', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 11:48:36', 0, NULL, 0, 'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/d1090ac9747643cdb85966698fb9e7e0-物资发放签收单.xls&realFileName=物资发放签收单.xls', 'text/plain', '');
INSERT INTO `team_file` VALUES (62, '0c332bb955c749ab9dce4cace84a9262', 'D:/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200704/0c332bb955c749ab9dce4cace84a9262-批量导入成员模板(2).xlsx', '批量导入成员模板(2)', 'xlsx', 9936, NULL, '6v7be19pwman2fird04gqu53', '38144bfd4f6c4a3f8163bba9e3cca9b9', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 11:54:16', 0, NULL, 0, 'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/0c332bb955c749ab9dce4cace84a9262-批量导入成员模板(2).xlsx&realFileName=批量导入成员模板(2).xlsx', 'text/plain', '');
INSERT INTO `team_file` VALUES (63, '3772abe44c434ee1935d8b95fffb1f74', 'D:/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200704/3772abe44c434ee1935d8b95fffb1f74-批量导入成员模板(2).xlsx', '批量导入成员模板(2)', 'xlsx', 9936, NULL, '551f201bf8614a1c81308f64d326213f', '38144bfd4f6c4a3f8163bba9e3cca9b9', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-04 12:47:44', 0, NULL, 0, 'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/3772abe44c434ee1935d8b95fffb1f74-批量导入成员模板(2).xlsx&realFileName=批量导入成员模板(2).xlsx', 'text/plain', '');
INSERT INTO `team_file` VALUES (64, '61f06d868866418fb62bec95fbdf5fc5', 'D:/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200704/61f06d868866418fb62bec95fbdf5fc5-报价1.xlsx', '报价1', 'xlsx', 10800, NULL, '551f201bf8614a1c81308f64d326213f', '0261ceed8197454a9cdcd66a8f9257da', 'eb8bfddb03ec42d2af05e5f418e46259', '6v7be19pwman2fird04gqu53', '2020-07-04 12:50:52', 0, NULL, 0, 'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/61f06d868866418fb62bec95fbdf5fc5-报价1.xlsx&realFileName=报价1.xlsx', 'text/plain', '');
INSERT INTO `team_file` VALUES (67, '178bf57fbd8844b387b46f9af5686f42', 'D:/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200704/178bf57fbd8844b387b46f9af5686f42-物资发放签收单.xls', '物资发放签收单', 'xls', 20992, NULL, '551f201bf8614a1c81308f64d326213f', '0261ceed8197454a9cdcd66a8f9257da', 'eb8bfddb03ec42d2af05e5f418e46259', '6v7be19pwman2fird04gqu53', '2020-07-04 13:08:37', 0, NULL, 0, 'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/178bf57fbd8844b387b46f9af5686f42-物资发放签收单.xls&realFileName=物资发放签收单.xls', 'text/plain', '');
INSERT INTO `team_file` VALUES (68, '0ad5b9fdeefa4ba1af1fc07d87e70056', 'D:/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200704/0ad5b9fdeefa4ba1af1fc07d87e70056-万科社区CIEMS开发计划.xlsx', '万科社区CIEMS开发计划', 'xlsx', 21849, NULL, '551f201bf8614a1c81308f64d326213f', '0261ceed8197454a9cdcd66a8f9257da', 'eb8bfddb03ec42d2af05e5f418e46259', '6v7be19pwman2fird04gqu53', '2020-07-04 13:13:30', 0, NULL, 0, 'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/0ad5b9fdeefa4ba1af1fc07d87e70056-万科社区CIEMS开发计划.xlsx&realFileName=万科社区CIEMS开发计划.xlsx', 'text/plain', '');
INSERT INTO `team_file` VALUES (69, 'fcdbc6cfb7e64759ba3e691990110e08', '/home/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200705/fcdbc6cfb7e64759ba3e691990110e08-测试文件-副本.docx', '测试文件-副本', 'docx', 12431, NULL, '6v7be19pwman2fird04gqu53', 'abb6a40af8c248d9be0bf80ab8e0b4db', '6d54506b13a947f58895eec8db465e7e', '6v7be19pwman2fird04gqu53', '2020-07-05 21:40:26', 0, NULL, 0, 'http://120.27.62.173:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200705/fcdbc6cfb7e64759ba3e691990110e08-测试文件-副本.docx&realFileName=测试文件-副本.docx', 'text/plain', '');
INSERT INTO `team_file` VALUES (70, 'c0d0757f7c0946919dab7b34bf330c1a', '/home/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200706/c0d0757f7c0946919dab7b34bf330c1a-测试文件-副本.docx', '测试文件-副本', 'docx', 12431, NULL, '6v7be19pwman2fird04gqu53', '129cdebcf8e84a8b88e5c8920f566d20', '7109c15a1dcc4292b2514d91842d9463', '6v7be19pwman2fird04gqu53', '2020-07-06 00:48:26', 0, NULL, 0, 'http://120.27.62.173:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/c0d0757f7c0946919dab7b34bf330c1a-测试文件-副本.docx&realFileName=测试文件-副本.docx', 'text/plain', '');
INSERT INTO `team_file` VALUES (71, 'de2106f61100473d934aa6d98dacffc3', '/home/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200706/de2106f61100473d934aa6d98dacffc3-测试文件-副本.docx', '测试文件111', 'docx', 12431, NULL, '6v7be19pwman2fird04gqu53', '8d4c1bde367f43ed9db2b7da13a059ae', '8c4f887129e54068996e2d10a1c3bac9', '6v7be19pwman2fird04gqu53', '2020-07-06 16:48:21', 0, NULL, 1, 'http://120.27.62.173:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/de2106f61100473d934aa6d98dacffc3-测试文件-副本.docx&realFileName=测试文件-副本.docx', 'text/plain', '2021-02-02 17:09:25');
INSERT INTO `team_file` VALUES (72, 'f323dc2d06244163a1f6151af06f30a5', '/home/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200706/f323dc2d06244163a1f6151af06f30a5-测试文件-副本.docx', '测试文件-副本', 'docx', 12431, NULL, '6v7be19pwman2fird04gqu53', '211affd25de14f988c92d5c7b84bdcb4', '3488bba47b8e48fc9cc75f5e5580cfb4', '6v7be19pwman2fird04gqu53', '2020-07-06 17:24:48', 0, NULL, 0, 'http://120.27.62.173:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/f323dc2d06244163a1f6151af06f30a5-测试文件-副本.docx&realFileName=测试文件-副本.docx', 'text/plain', '');
INSERT INTO `team_file` VALUES (73, 'b7eb94cfd1344bf09bee348a9a000b0c', '/home/teamwork/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20200706/b7eb94cfd1344bf09bee348a9a000b0c-测试文件-副本 (2).docx', '测试文件-副本 (2)', 'docx', 12431, NULL, '6v7be19pwman2fird04gqu53', 'c3f30c32f31b46a8b2b413ffb7f742d9', '3488bba47b8e48fc9cc75f5e5580cfb4', '6v7be19pwman2fird04gqu53', '2020-07-06 17:25:13', 0, NULL, 0, 'http://120.27.62.173:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/b7eb94cfd1344bf09bee348a9a000b0c-测试文件-副本 (2).docx&realFileName=测试文件-副本 (2).docx', 'text/plain', '');
INSERT INTO `team_file` VALUES (74, '406abf3a73bd4baf8ca3d0fdb74126a5', '/home/mproject/uploadPath/projectfile/bddc79203627409e9928b290b952ee88/20210201/406abf3a73bd4baf8ca3d0fdb74126a5-kibana-7.10.2-x86_64.rpm.sha512', 'kibana-7.10.2-x86_64.rpm', 'sha512', 154, NULL, '2360f2f0f79447f4a2498ae06a9b132d', NULL, '813a9c8b199e4dc59486cf6468ab91af', 'bddc79203627409e9928b290b952ee88', '2021-02-01 14:20:01', 0, NULL, 0, 'http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210201/406abf3a73bd4baf8ca3d0fdb74126a5-kibana-7.10.2-x86_64.rpm.sha512&realFileName=kibana-7.10.2-x86_64.rpm.sha512', 'text/plain', '');
INSERT INTO `team_file` VALUES (75, 'd91430238ef74c12ac650e568a9d7f17', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/d91430238ef74c12ac650e568a9d7f17-tomcatgo.jpg', 'tomcatgo', 'jpg', 34912, NULL, '6v7be19pwman2fird04gqu53', NULL, '3488bba47b8e48fc9cc75f5e5580cfb4', '6v7be19pwman2fird04gqu53', '2021-02-02 13:35:12', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/d91430238ef74c12ac650e568a9d7f17-tomcatgo.jpg&realFileName=tomcatgo.jpg', 'text/plain', '');
INSERT INTO `team_file` VALUES (76, '072539ed82074083b4ef1f0824b7b7a9', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/072539ed82074083b4ef1f0824b7b7a9-tomcatgo.jpg', 'tomcatgo', 'jpg', 34912, NULL, '6v7be19pwman2fird04gqu53', NULL, '8c4f887129e54068996e2d10a1c3bac9', '6v7be19pwman2fird04gqu53', '2021-02-02 17:09:31', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/072539ed82074083b4ef1f0824b7b7a9-tomcatgo.jpg&realFileName=tomcatgo.jpg', 'text/plain', '');
INSERT INTO `team_file` VALUES (77, '7f3fde339f284565b65932c2058a0ff3', '/home/mproject/uploadPath/projectfile/bddc79203627409e9928b290b952ee88/20210202/7f3fde339f284565b65932c2058a0ff3-u=1689053532,4230915864&fm=26&gp=0.jpg', 'u=1689053532,4230915864&fm=26&gp=0', 'jpg', 21970, NULL, '2360f2f0f79447f4a2498ae06a9b132d', '62086de33087479885e9a54f8a22808e', '88435aab6cad403788275282852d61a6', 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:10:16', 0, NULL, 0, 'http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/7f3fde339f284565b65932c2058a0ff3-u=1689053532,4230915864&fm=26&gp=0.jpg&realFileName=u=1689053532,4230915864&fm=26&gp=0.jpg', 'text/plain', '');
INSERT INTO `team_file` VALUES (78, '31b336c69b8349248843adfe92f00a2f', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/31b336c69b8349248843adfe92f00a2f-屏幕截图(1).png', '屏幕截图(1)', 'png', 372870, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:14:48', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/31b336c69b8349248843adfe92f00a2f-屏幕截图(1).png&realFileName=屏幕截图(1).png', 'text/plain', '');
INSERT INTO `team_file` VALUES (79, '3c85fb68230a4cf68cfb4f6f95ff0908', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/3c85fb68230a4cf68cfb4f6f95ff0908-智慧餐厅完整解决方案.pdf', '智慧餐厅完整解决方案', 'pdf', 1048576, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:16:02', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/3c85fb68230a4cf68cfb4f6f95ff0908-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf', 'text/plain', '');
INSERT INTO `team_file` VALUES (80, 'adf1f8a2612f463b985216b518578a20', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/adf1f8a2612f463b985216b518578a20-智慧餐厅完整解决方案.pdf', '智慧餐厅完整解决方案', 'pdf', 1048576, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:16:02', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/adf1f8a2612f463b985216b518578a20-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf', 'text/plain', '');
INSERT INTO `team_file` VALUES (81, '4c980c0219ef419bbd68004ee164ea94', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/4c980c0219ef419bbd68004ee164ea94-智慧餐厅完整解决方案.pdf', '智慧餐厅完整解决方案', 'pdf', 1048576, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:16:02', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/4c980c0219ef419bbd68004ee164ea94-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf', 'text/plain', '');
INSERT INTO `team_file` VALUES (82, '4cb37d06008e43fd9e9421a70f190768', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/4cb37d06008e43fd9e9421a70f190768-智慧餐厅完整解决方案.pdf', '智慧餐厅完整解决方案', 'pdf', 1048576, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:16:03', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/4cb37d06008e43fd9e9421a70f190768-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf', 'text/plain', '');
INSERT INTO `team_file` VALUES (83, 'cc7d1897a98b4b6ca00c67c625b21bd7', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/cc7d1897a98b4b6ca00c67c625b21bd7-智慧餐厅完整解决方案.pdf', '智慧餐厅完整解决方案', 'pdf', 1557586, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:16:03', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/cc7d1897a98b4b6ca00c67c625b21bd7-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf', 'text/plain', '');
INSERT INTO `team_file` VALUES (84, 'dcad47f8444f4b04bcef716be94a7655', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/dcad47f8444f4b04bcef716be94a7655-解烟茶【左右烟茶】的由来.pdf', '解烟茶【左右烟茶】的由来', 'pdf', 759288, NULL, '6v7be19pwman2fird04gqu53', '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:17:18', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/dcad47f8444f4b04bcef716be94a7655-解烟茶【左右烟茶】的由来.pdf&realFileName=解烟茶【左右烟茶】的由来.pdf', 'text/plain', '');
INSERT INTO `team_file` VALUES (85, 'd3f3635d44264b4d9c2fa1e9db3bb87c', 'D:/mproject/uploadPath/projectfile/6v7be19pwman2fird04gqu53/20210202/d3f3635d44264b4d9c2fa1e9db3bb87c-22.png', '22', 'png', 56454, NULL, '6v7be19pwman2fird04gqu53', 'e9591ace4fc1451f9268283cc6021fbf', 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:18:34', 0, NULL, 0, 'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/d3f3635d44264b4d9c2fa1e9db3bb87c-22.png&realFileName=22.png', 'text/plain', '');
INSERT INTO `team_file` VALUES (86, '1b948d7436474e44adb7892d05e86072', '/home/mproject/uploadPath/projectfile/bddc79203627409e9928b290b952ee88/20210202/1b948d7436474e44adb7892d05e86072-ECS共享型 s6.xlsx', 'ECS共享型 s6', 'xlsx', 16022, NULL, '2360f2f0f79447f4a2498ae06a9b132d', NULL, '88435aab6cad403788275282852d61a6', 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:20:42', 0, NULL, 0, 'http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/1b948d7436474e44adb7892d05e86072-ECS共享型 s6.xlsx&realFileName=ECS共享型 s6.xlsx', 'text/plain', '');
INSERT INTO `team_file` VALUES (87, '6a7590ec28ff44399d2ed9848775e4df', '/home/mproject/uploadPath/projectfile/bddc79203627409e9928b290b952ee88/20210202/6a7590ec28ff44399d2ed9848775e4df-server.cfg', 'server', 'cfg', 12193, NULL, '2360f2f0f79447f4a2498ae06a9b132d', NULL, '88435aab6cad403788275282852d61a6', 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:21:30', 0, NULL, 0, 'http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/6a7590ec28ff44399d2ed9848775e4df-server.cfg&realFileName=server.cfg', 'text/plain', '');
INSERT INTO `team_file` VALUES (88, '44a9169a75254578b203972861821804', '/home/mproject/uploadPath/projectfile/bddc79203627409e9928b290b952ee88/20210202/44a9169a75254578b203972861821804-测试扩展名.jpg', '测试扩展名', 'jpg', 153416, NULL, '2360f2f0f79447f4a2498ae06a9b132d', NULL, 'a3c56e1116ff42b780fa8c16afc76311', 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:26:32', 0, NULL, 0, 'http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/44a9169a75254578b203972861821804-测试扩展名.jpg&realFileName=测试扩展名.jpg', 'text/plain', '');
INSERT INTO `team_file` VALUES (89, '308492a541944d49947147054c1dd2de', '/home/mproject/uploadPath/projectfile/bddc79203627409e9928b290b952ee88/20210202/308492a541944d49947147054c1dd2de-新建文本文档.txt', '新建文本文档', 'txt', 8685, NULL, '2360f2f0f79447f4a2498ae06a9b132d', NULL, 'a3c56e1116ff42b780fa8c16afc76311', 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:27:13', 0, NULL, 0, 'http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/308492a541944d49947147054c1dd2de-新建文本文档.txt&realFileName=新建文本文档.txt', 'text/plain', '');

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
-- Records of team_invite_link
-- ----------------------------
INSERT INTO `team_invite_link` VALUES (4, '084305ad51b246eea1364974d0cec5f2', '6v7be19pwman2fird04gqu53', 'project', 'nd5gkr9qc7wumei8zo630y41', '2020-03-13 21:53:43', '2020-03-14 21:53:43');
INSERT INTO `team_invite_link` VALUES (5, 'af844560d81c4c098f906d2701211a9a', '6v7be19pwman2fird04gqu53', 'project', '11c80c7fabd146ca95d24621124f61b4', '2020-07-01 16:57:53', '2020-07-02 16:57:53');
INSERT INTO `team_invite_link` VALUES (6, 'a8aad93c54784100b443409e89f0246d', '6v7be19pwman2fird04gqu53', 'project', '468e736e7dbd4693a40e89fb8fc9dd5e', '2020-07-01 17:31:22', '2020-07-02 17:31:22');
INSERT INTO `team_invite_link` VALUES (7, '95363ad1d04e4a778f749d2560f77c6b', '6v7be19pwman2fird04gqu53', 'project', '6v7be19pwman2fird04gqu53', '2020-07-02 10:19:29', '2020-07-03 10:19:29');
INSERT INTO `team_invite_link` VALUES (8, 'd9f3db856e0d4b71aaa1bef10227ff3d', '6v7be19pwman2fird04gqu53', 'project', '6v7be19pwman2fird04gqu53', '2020-07-02 22:19:26', '2020-07-03 22:19:26');
INSERT INTO `team_invite_link` VALUES (9, 'a730cacbedf340209f2491bdf0950f79', '6v7be19pwman2fird04gqu53', 'project', '468e736e7dbd4693a40e89fb8fc9dd5e', '2020-07-02 15:39:25', '2020-07-03 15:39:25');

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
-- Records of team_lock
-- ----------------------------
INSERT INTO `team_lock` VALUES (21307064333, 2, 1475226020);

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
INSERT INTO `team_member` VALUES (582, 'admin', 'cae6137fc5283746a058c679767d18a2', 'admin', '18681140825', 'juli', NULL, 1, '2020-06-20 17:24:48', '', 'http://localhost:8888/common/image?filePathName=/member/avatar/6v7be19pwman2fird04gqu53/20210202/0e92e563278f48d9a21fb48521a50dae-tomcatgo.jpg&realFileName=tomcatgo.jpg', '', 0, 0, 0, NULL, '', '123@qq.com', '6v7be19pwman2fird04gqu53', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (646, 'demo', '14e1b600b1fd579f47433b88e8d85291', 'demo', '13399944031', NULL, '2020-07-06 10:50:59', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/member/avatar/bddc79203627409e9928b290b952ee88/20210201/7b07d4a68d2e499e93ea74702ba03ec8-demo.png&realFileName=demo.png', NULL, 0, 0, 0, NULL, '', '322323@qq.com', 'bddc79203627409e9928b290b952ee88', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (647, 'wangwei', '14e1b600b1fd579f47433b88e8d85291', '王伟', '13399944032', NULL, '2020-07-06 10:51:00', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '3332324@qq.com', '6d44c444965349ae926cd5be98912292', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (648, 'lichao', '14e1b600b1fd579f47433b88e8d85291', '李超', '13399944033', NULL, '2020-07-06 10:51:01', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '4422325@qq.com', 'e9b4bbe96f04474599c9f014ea1f47e7', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (649, 'lijie', '14e1b600b1fd579f47433b88e8d85291', '李杰', '13399944034', NULL, '2020-07-06 10:51:02', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '522326@qq.com', '8e0310d06f904f0bbaf3b1ba1fc06620', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (650, 'songfei', '14e1b600b1fd579f47433b88e8d85291', '宋飞', '13399944035', NULL, '2020-07-06 10:51:02', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '622327@qq.com', '5cddde5b22ed421fabfe32adb6b7ca07', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (651, 'hanzhongwei', '14e1b600b1fd579f47433b88e8d85291', '韩钟伟', '13399944036', NULL, '2020-07-06 10:51:03', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '722328@qq.com', '7dac6850d3224e12a5040150ecb09122', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (652, 'yangkai', '14e1b600b1fd579f47433b88e8d85291', '杨凯', '13399944037', NULL, '2020-07-06 10:51:04', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '888888888@qq.com', '386cd8152dab48ada28bea04058abc96', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (653, 'wuzhong', '14e1b600b1fd579f47433b88e8d85291', '吴忠', '13399944038', NULL, '2020-07-06 10:51:05', 1, NULL, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, 0, 0, 0, NULL, '', '000000@qq.com', '1355fa40b3714a5ea5ed127953f79a9f', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (654, 'wulon', '14e1b600b1fd579f47433b88e8d85291', 'wulon', '13811111111', NULL, '2021-02-02 10:46:38', 1, NULL, '', '/common/download?filePathName=D:/mproject/uploadPath/user.jpg&realFileName=user.jpg/common/download?filePathName=D:/mproject/uploadPath/user.jpg&realFileName=user.jpg', NULL, 0, 0, 0, NULL, '', '1@1.com', '204e782da95946389bbf495d61e50c1c', NULL, NULL, NULL);
INSERT INTO `team_member` VALUES (655, 'wulon1', '14e1b600b1fd579f47433b88e8d85291', 'wulon1', '13811111111', NULL, '2021-02-02 10:47:07', 1, NULL, '', '/common/download?filePathName=D:/mproject/uploadPath/user.jpg&realFileName=user.jpg/common/download?filePathName=D:/mproject/uploadPath/user.jpg&realFileName=user.jpg', NULL, 0, 0, 0, NULL, '', '2@1.com', 'a4be5bfcecba479a93b3cefdbfbc21a7', NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 156 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_member_account
-- ----------------------------
INSERT INTO `team_member_account` VALUES (21, '6v7be19pwman2fird04gqu11', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', 'axr73ydzo5gemks0pl9qnt16', '3', 1, 'admin', '18681140825', '545522390@qq.com', '2019-01-05 21:57:01', NULL, 1, '描述12', 'http://localhost:8888/common/image?filePathName=/member/avatar/6v7be19pwman2fird04gqu53/20210202/0e92e563278f48d9a21fb48521a50dae-tomcatgo.jpg&realFileName=tomcatgo.jpg', '资深工程师', '研发部');
INSERT INTO `team_member_account` VALUES (135, 'd89ce978017342fdbc99d7dd591582c0', 'bddc79203627409e9928b290b952ee88', '6v7be19pwman2fird04gqu53', 'axr73ydzo5gemks0pl9qnt16', '116', 0, 'demo', '13399944031', '322323@qq.com', '2020-07-06 10:51:00', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/member/avatar/bddc79203627409e9928b290b952ee88/20210201/7b07d4a68d2e499e93ea74702ba03ec8-demo.png&realFileName=demo.png', '主任', '研发部');
INSERT INTO `team_member_account` VALUES (136, '092cca04b25242df87f4fbbf25325be0', 'bddc79203627409e9928b290b952ee88', '2360f2f0f79447f4a2498ae06a9b132d', '', '100', 1, 'demo', '13399944031', '322323@qq.com', '2020-07-06 10:51:00', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/member/avatar/bddc79203627409e9928b290b952ee88/20210201/7b07d4a68d2e499e93ea74702ba03ec8-demo.png&realFileName=demo.png', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (138, 'f320c5aeca234b04a50db371ba571db5', '6d44c444965349ae926cd5be98912292', 'f7041ee86dbc4b4494fbf17607ac94ce', '', '102', 1, '王伟', '13399944032', '3332324@qq.com', '2020-07-06 10:51:01', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (139, '83f18430f0774e54b47d7e90beb1da53', 'e9b4bbe96f04474599c9f014ea1f47e7', '6v7be19pwman2fird04gqu53', 'axr73ydzo5gemks0pl9qnt16', '116', 0, '李超', '13399944033', '4422325@qq.com', '2020-07-06 10:51:02', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '科员', '研发部');
INSERT INTO `team_member_account` VALUES (140, 'debcf5c0a5d841cc838d18ae2ff118bd', 'e9b4bbe96f04474599c9f014ea1f47e7', '47d5d113b44f44128cb79b1d4965c531', '', '104', 1, '李超', '13399944033', '4422325@qq.com', '2020-07-06 10:51:02', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (141, 'fb331a85511844fc87d26bae2ebe5220', '8e0310d06f904f0bbaf3b1ba1fc06620', '6v7be19pwman2fird04gqu53', 'axr73ydzo5gemks0pl9qnt16', '116', 0, '李杰', '13399944034', '522326@qq.com', '2020-07-06 10:51:02', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '科员', '研发部');
INSERT INTO `team_member_account` VALUES (142, 'd8d234d9349e4c1ab93607dfabc146b8', '8e0310d06f904f0bbaf3b1ba1fc06620', '678e42846beb4b0493f0174ea95f7292', '', '106', 1, '李杰', '13399944034', '522326@qq.com', '2020-07-06 10:51:02', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (143, '3bf59606c05a40c9aa75c60c1fd52ee2', '5cddde5b22ed421fabfe32adb6b7ca07', '6v7be19pwman2fird04gqu53', 'axr73ydzo5gemks0pl9qnt16', '116', 0, '宋飞', '13399944035', '622327@qq.com', '2020-07-06 10:51:03', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '科员', '研发部');
INSERT INTO `team_member_account` VALUES (144, '332a35b9310242a28091e00055a3e412', '5cddde5b22ed421fabfe32adb6b7ca07', 'df15fae11f7746238a879e2e67c1a62a', '', '108', 1, '宋飞', '13399944035', '622327@qq.com', '2020-07-06 10:51:03', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (145, 'b4c8c79707784bc285a51deeb629af41', '7dac6850d3224e12a5040150ecb09122', '6v7be19pwman2fird04gqu53', '74818cf1fd624d84bdd8ecccdfdc4cc6,axr73ydzo5gemks0pl9qnt16', '116', 0, '韩钟伟', '13399944036', '722328@qq.com', '2020-07-06 10:51:04', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '科员', '技术部-研发部');
INSERT INTO `team_member_account` VALUES (146, '8d0ac93c502a4e8bb19e5acf5f9fff0c', '7dac6850d3224e12a5040150ecb09122', '15750248caeb4f3cbb0d951f645f922d', '', '110', 1, '韩钟伟', '13399944036', '722328@qq.com', '2020-07-06 10:51:04', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (147, 'ed03408e4dde4bc2accd481ca597ee0f', '386cd8152dab48ada28bea04058abc96', '6v7be19pwman2fird04gqu53', '74818cf1fd624d84bdd8ecccdfdc4cc6', '116', 0, '杨凯', '13399944037', '888888888@qq.com', '2020-07-06 10:51:05', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '科员', '技术部');
INSERT INTO `team_member_account` VALUES (148, '0f71086830cd4013bf3c2100e0de6b73', '386cd8152dab48ada28bea04058abc96', 'e715d61816674c378589e34b062dedf2', '', '112', 1, '杨凯', '13399944037', '888888888@qq.com', '2020-07-06 10:51:05', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (149, 'c9b15bba0746467e9b5fef30bc3b8497', '1355fa40b3714a5ea5ed127953f79a9f', '6v7be19pwman2fird04gqu53', '74818cf1fd624d84bdd8ecccdfdc4cc6', '116', 0, '吴忠', '13399944038', '000000@qq.com', '2020-07-06 10:51:05', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '科员', '技术部');
INSERT INTO `team_member_account` VALUES (150, 'c5b58773911c4575b0821b92c34db3ce', '1355fa40b3714a5ea5ed127953f79a9f', '0c880c2966644465ba45cfd3a87701df', '', '114', 1, '吴忠', '13399944038', '000000@qq.com', '2020-07-06 10:51:05', NULL, 1, '', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (152, 'c35cc7be1a2a4dc8a6b3aef555634763', '204e782da95946389bbf495d61e50c1c', '6v7be19pwman2fird04gqu53', '', '116', 0, 'wulon', '13811111111', '1@1.com', '2021-02-02 10:47:07', NULL, 1, '', NULL, NULL, '');
INSERT INTO `team_member_account` VALUES (153, '4371667fe8af43e8a5f03bc570b187c0', '204e782da95946389bbf495d61e50c1c', '81afb4d2d4f644e792d94d2cc11fddb2', '', '118', 1, 'wulon', '13811111111', '1@1.com', '2021-02-02 10:47:07', NULL, 1, '', NULL, '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (154, 'cdec485817ef4f41a2ad74127287fd5f', 'a4be5bfcecba479a93b3cefdbfbc21a7', '6v7be19pwman2fird04gqu53', '', '121', 0, 'wulon1', '13811111111', '2@1.com', '2021-02-02 10:47:36', NULL, 1, '', NULL, NULL, '');
INSERT INTO `team_member_account` VALUES (155, 'd4a7f77d54e0408a8aebec692a798ec4', 'a4be5bfcecba479a93b3cefdbfbc21a7', '4b85f864069f4774a9b87941823bc947', '', '120', 1, 'wulon1', '13811111111', '2@1.com', '2021-02-02 10:47:36', NULL, 1, '', NULL, '资深工程师', '某某公司－某某某事业群－某某平台部－某某技术部－BM');
INSERT INTO `team_member_account` VALUES (156, '7f10f62647934c24bcb094b97ee272fa', '6d44c444965349ae926cd5be98912292', '6v7be19pwman2fird04gqu53', '', '116', 0, '王伟', '13399944032', '3332324@qq.com', '2021-02-02 15:35:42', NULL, 1, NULL, 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', NULL, NULL);

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
-- Records of team_notify
-- ----------------------------
INSERT INTO `team_notify` VALUES (4325, 'admin', '指给王伟的任务', 'notice', '6v7be19pwman2fird04gqu53', '6d44c444965349ae926cd5be98912292', NULL, 0, NULL, 'project', NULL, NULL, 'task', 'project', 'system', 'http://127.0.0.1:8888/common/image?filePathName=/member/avatar/6v7be19pwman2fird04gqu53/20200709/17c8573b917c4b45a1cb61588522780d-project.png&realFileName=project.png', '0');
INSERT INTO `team_notify` VALUES (4326, 'demo: 完成了任务 ', '指给王伟的任务', 'message', 'bddc79203627409e9928b290b952ee88', '6d44c444965349ae926cd5be98912292', '2021-02-01 11:19:28', 0, NULL, NULL, NULL, NULL, 'task', 'project', 'system', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '0');
INSERT INTO `team_notify` VALUES (4327, 'demo: 重做了任务 ', '测试任务', 'message', 'bddc79203627409e9928b290b952ee88', '6v7be19pwman2fird04gqu53', '2021-02-01 11:19:30', 0, NULL, NULL, NULL, NULL, 'task', 'project', 'system', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '0');
INSERT INTO `team_notify` VALUES (4328, 'demo: 重做了任务 ', '测试任务', 'message', 'bddc79203627409e9928b290b952ee88', '6d44c444965349ae926cd5be98912292', '2021-02-01 11:19:30', 0, NULL, NULL, NULL, NULL, 'task', 'project', 'system', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '0');
INSERT INTO `team_notify` VALUES (4329, 'demo: 完成了任务 ', '测试任务', 'message', 'bddc79203627409e9928b290b952ee88', '6v7be19pwman2fird04gqu53', '2021-02-01 11:19:31', 0, NULL, NULL, NULL, NULL, 'task', 'project', 'system', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '0');
INSERT INTO `team_notify` VALUES (4330, 'demo: 完成了任务 ', '测试任务', 'message', 'bddc79203627409e9928b290b952ee88', '6d44c444965349ae926cd5be98912292', '2021-02-01 11:19:31', 0, NULL, NULL, NULL, NULL, 'task', 'project', 'system', 'http://122.112.164.217:8888/common/image?filePathName=/defaultimg/user.jpg', '0');

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
INSERT INTO `team_organization` VALUES (38, '张一的个人项目', NULL, NULL, 'bddc79203627409e9928b290b952ee88', '2020-07-06 10:50:59', 1, '2360f2f0f79447f4a2498ae06a9b132d', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (39, '王伟的个人项目', NULL, NULL, '6d44c444965349ae926cd5be98912292', '2020-07-06 10:51:00', 1, 'f7041ee86dbc4b4494fbf17607ac94ce', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (40, '李超的个人项目', NULL, NULL, 'e9b4bbe96f04474599c9f014ea1f47e7', '2020-07-06 10:51:01', 1, '47d5d113b44f44128cb79b1d4965c531', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (41, '李杰的个人项目', NULL, NULL, '8e0310d06f904f0bbaf3b1ba1fc06620', '2020-07-06 10:51:02', 1, '678e42846beb4b0493f0174ea95f7292', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (42, '宋飞的个人项目', NULL, NULL, '5cddde5b22ed421fabfe32adb6b7ca07', '2020-07-06 10:51:02', 1, 'df15fae11f7746238a879e2e67c1a62a', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (43, '韩钟伟的个人项目', NULL, NULL, '7dac6850d3224e12a5040150ecb09122', '2020-07-06 10:51:03', 1, '15750248caeb4f3cbb0d951f645f922d', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (44, '杨凯的个人项目', NULL, NULL, '386cd8152dab48ada28bea04058abc96', '2020-07-06 10:51:04', 1, 'e715d61816674c378589e34b062dedf2', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (45, '吴忠的个人项目', NULL, NULL, '1355fa40b3714a5ea5ed127953f79a9f', '2020-07-06 10:51:05', 1, '0c880c2966644465ba45cfd3a87701df', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (46, 'wulon的个人项目', NULL, NULL, '204e782da95946389bbf495d61e50c1c', '2021-02-02 10:46:38', 1, '81afb4d2d4f644e792d94d2cc11fddb2', NULL, 0, 0, 0);
INSERT INTO `team_organization` VALUES (47, 'wulon1的个人项目', NULL, NULL, 'a4be5bfcecba479a93b3cefdbfbc21a7', '2021-02-02 10:47:07', 1, '4b85f864069f4774a9b87941823bc947', NULL, 0, 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 13089 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project
-- ----------------------------
INSERT INTO `team_project` VALUES (13073, NULL, '新项目0706', 'edba7252bddd447ba0a44485d886dd0a', '新项目0706简介', 'open', NULL, 0, 1, '0', 0.00, '2020-07-06 10:20:45', '6v7be19pwman2fird04gqu53', '2020-07-06 10:50:46', 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13074, 'http://122.112.164.217:8888/common/image?filePathName=/projectfile/project/cover/20200819/d0befdd0a131418d901c0e42d2ac9cfb-U字浅口V4.png&realFileName=U字浅口V4.png', '水利部水资源调研项目', '8c4f887129e54068996e2d10a1c3bac9', '', 'open', NULL, 0, 0, '', 33.33, '2020-07-06 10:22:43', '6v7be19pwman2fird04gqu53', NULL, 1, '123', 1, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13075, NULL, '测试项目', '78bcc133d0cc4885975ebabeabb97ffa', '', 'open', NULL, 0, 1, '', 0.00, '2020-07-06 10:51:24', '6v7be19pwman2fird04gqu53', '2020-07-06 15:08:04', 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13076, NULL, '张一的项目', 'c70dbd8001e04e96b838309915f790ea', '', 'open', NULL, 0, 1, '', 0.00, '2020-07-06 11:08:29', '2360f2f0f79447f4a2498ae06a9b132d', '2020-07-06 17:00:25', 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13077, 'http://122.112.164.217:8888/common/image?filePathName=/projectfile/project/cover/20200819/4d2aca9fb9fa4553a01ded90bd804778-22.png&realFileName=22.png', '水利部1号项目', '3488bba47b8e48fc9cc75f5e5580cfb4', '', 'open', NULL, 0, 0, '5b72d66e70b6404f93a74bbf5d4de2a4', 80.00, '2020-07-06 17:07:26', '6v7be19pwman2fird04gqu53', NULL, 1, 'test', 1, 0, '', 1, 1, 'simple', '2020-07-07', '2020-08-31', 1);
INSERT INTO `team_project` VALUES (13078, '', '烧烤乐园', '813a9c8b199e4dc59486cf6468ab91af', 'yyyyyyyy', 'open', NULL, 0, 0, 'd85f1bvwpml2nhxe94zu7tyi', 0.00, '2021-02-01 11:54:03', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, '', 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13079, NULL, '11232', '4d619572638b41b29e4e9d9b93c0fc6e', '123', 'open', NULL, 0, 0, 'd85f1bvwpml2nhxe94zu7tyi', 0.00, '2021-02-01 15:44:04', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13080, NULL, '六盘水水城项目', '4f2619f1d1c64a389a2ba671e7fca2ed', '十四五项目', 'open', NULL, 0, 0, '0', 0.00, '2021-02-01 16:07:02', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13081, NULL, 'SSM', 'd0e82a94783a4ee7881f6c342f990bc5', '123', 'open', NULL, 0, 0, 'd85f1bvwpml2nhxe94zu7tyi', 0.00, '2021-02-01 16:42:39', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13082, '', 'test11', '81d43786f82c4c84baf34c6eb9ecac78', '111', 'open', NULL, 0, 0, 'd85f1bvwpml2nhxe94zu7tyi', 0.00, '2021-02-01 18:25:43', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, '', 1, 0, NULL, 1, 1, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13083, '', '校车安全管理平台项目文档进度', 'a3c56e1116ff42b780fa8c16afc76311', '', 'open', NULL, 0, 0, 'd85f1bvwpml2nhxe94zu7tyi', 70.00, '2021-02-01 21:33:17', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, '', 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13084, NULL, '统一招生平台', 'd99cbb2012ce413bab0cde5866fa5041', '', 'open', NULL, 0, 1, '', 0.00, '2021-02-01 21:55:31', '2360f2f0f79447f4a2498ae06a9b132d', '2021-02-01 21:56:22', 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13085, '', '东莞市义务教育阶段学校统一招生平台文档进度', '9749247d6ae44e8abeadac35d9f46ba0', '', 'open', NULL, 0, 0, '', 80.00, '2021-02-01 21:56:16', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, '', 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13086, '', '电子健康码管理平台项目文档进度', 'd2076c5f961b4bfe915906cbd4c773d3', '', 'open', NULL, 0, 0, '', 30.00, '2021-02-01 21:56:41', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, '', 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13087, NULL, '1231', '88435aab6cad403788275282852d61a6', '1231', 'open', NULL, 0, 0, '0', 0.00, '2021-02-02 08:41:06', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13088, NULL, 'test', 'f35e6d3ef3e04b5bb532039947b30eae', 'rwar', 'open', NULL, 0, 1, '5b72d66e70b6404f93a74bbf5d4de2a4', 0.00, '2021-02-02 10:52:50', '6v7be19pwman2fird04gqu53', '2021-02-02 10:58:41', 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13089, 'http://localhost:8888/common/image?filePathName=/projectfile/project/cover/20210202/7137ab1ea1354122bd70c361a7247a46-tomcatgo.jpg&realFileName=tomcatgo.jpg', 'wuliang', '0ecde82e3bed4789a8e201621ba79d9f', 'test', 'open', NULL, 0, 0, '5b72d66e70b6404f93a74bbf5d4de2a4', 0.00, '2021-02-02 16:29:51', '6v7be19pwman2fird04gqu53', NULL, 1, '', 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13090, NULL, '1111', '74228c6bfd6040d3b00f934cd0b0014f', '1111', 'open', NULL, 0, 1, '5b72d66e70b6404f93a74bbf5d4de2a4', 0.00, '2021-02-02 17:03:03', '6v7be19pwman2fird04gqu53', '2021-02-02 17:03:45', 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13091, 'http://localhost:8888/common/image?filePathName=/projectfile/project/cover/20210202/361aa715ecd14a38830e612dc40db567-22.png&realFileName=22.png', '1111', 'cf50726e99d746fa88107ed355d4a8cc', '111', 'open', NULL, 0, 0, '5b72d66e70b6404f93a74bbf5d4de2a4', 0.00, '2021-02-02 17:06:18', '6v7be19pwman2fird04gqu53', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);
INSERT INTO `team_project` VALUES (13092, NULL, 'test112131', 'd17947344cc1474499b1b43537e61c8c', '111', 'open', NULL, 0, 0, '0', 0.00, '2021-02-02 18:10:45', '2360f2f0f79447f4a2498ae06a9b132d', NULL, 1, NULL, 0, 0, NULL, 0, 0, 'simple', NULL, NULL, 0);

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

-- ----------------------------
-- Records of team_project_auth
-- ----------------------------
INSERT INTO `team_project_auth` VALUES (1, '管理员', 1, 0, '管理员', 0, '2018-08-01 14:20:46', '', 0, 'admin');
INSERT INTO `team_project_auth` VALUES (2, '成员', 1, 0, '成员', 0, '2018-12-20 13:39:59', '', 1, 'member');
INSERT INTO `team_project_auth` VALUES (3, '管理员', 1, 0, '管理员', 0, '2018-08-01 14:20:46', '6v7be19pwman2fird04gqu53', 0, 'admin');
INSERT INTO `team_project_auth` VALUES (4, '成员', 1, 0, '成员', 0, '2018-12-20 13:39:59', '6v7be19pwman2fird04gqu53', 0, 'member');
INSERT INTO `team_project_auth` VALUES (88, '管理员', 1, 0, '管理员', 0, '2020-07-05 19:59:40', '063392bbed8346c4b468871f116c296e', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (89, '成员', 1, 0, '成员', 0, '2020-07-05 19:59:40', '063392bbed8346c4b468871f116c296e', 0, 'member');
INSERT INTO `team_project_auth` VALUES (90, '管理员', 1, 0, '管理员', 0, '2020-07-05 21:14:08', '05870cecf92f47db978ae547ace853ec', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (91, '成员', 1, 0, '成员', 0, '2020-07-05 21:14:08', '05870cecf92f47db978ae547ace853ec', 1, 'member');
INSERT INTO `team_project_auth` VALUES (92, '管理员', 1, 0, '管理员', 0, '2020-07-05 22:16:58', '721bc2b1b8b04a3fb0be66f27ffc5e04', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (93, '成员', 1, 0, '成员', 0, '2020-07-05 22:16:58', '721bc2b1b8b04a3fb0be66f27ffc5e04', 0, 'member');
INSERT INTO `team_project_auth` VALUES (94, '管理员', 1, 0, '管理员', 0, '2020-07-05 22:20:39', '5b58014e1a014244bc1abf3636e6f836', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (95, '成员', 1, 0, '成员', 0, '2020-07-05 22:20:39', '5b58014e1a014244bc1abf3636e6f836', 0, 'member');
INSERT INTO `team_project_auth` VALUES (96, '管理员', 1, 0, '管理员', 0, '2020-07-05 22:47:50', '38f726a316c441fe914cdf8ad1de2745', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (97, '成员', 1, 0, '成员', 0, '2020-07-05 22:47:50', '38f726a316c441fe914cdf8ad1de2745', 0, 'member');
INSERT INTO `team_project_auth` VALUES (98, '管理员', 1, 0, '管理员', 0, '2020-07-05 23:36:15', 'fca95aa407a44e32931110c713d0bcb3', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (99, '成员', 1, 0, '成员', 0, '2020-07-05 23:36:15', 'fca95aa407a44e32931110c713d0bcb3', 0, 'member');
INSERT INTO `team_project_auth` VALUES (100, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:50:59', '2360f2f0f79447f4a2498ae06a9b132d', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (101, '成员', 1, 0, '成员', 0, '2020-07-06 10:50:59', '2360f2f0f79447f4a2498ae06a9b132d', 0, 'member');
INSERT INTO `team_project_auth` VALUES (102, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:00', 'f7041ee86dbc4b4494fbf17607ac94ce', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (103, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:00', 'f7041ee86dbc4b4494fbf17607ac94ce', 0, 'member');
INSERT INTO `team_project_auth` VALUES (104, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:01', '47d5d113b44f44128cb79b1d4965c531', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (105, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:01', '47d5d113b44f44128cb79b1d4965c531', 0, 'member');
INSERT INTO `team_project_auth` VALUES (106, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:02', '678e42846beb4b0493f0174ea95f7292', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (107, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:02', '678e42846beb4b0493f0174ea95f7292', 0, 'member');
INSERT INTO `team_project_auth` VALUES (108, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:02', 'df15fae11f7746238a879e2e67c1a62a', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (109, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:02', 'df15fae11f7746238a879e2e67c1a62a', 0, 'member');
INSERT INTO `team_project_auth` VALUES (110, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:03', '15750248caeb4f3cbb0d951f645f922d', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (111, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:03', '15750248caeb4f3cbb0d951f645f922d', 0, 'member');
INSERT INTO `team_project_auth` VALUES (112, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:04', 'e715d61816674c378589e34b062dedf2', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (113, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:04', 'e715d61816674c378589e34b062dedf2', 0, 'member');
INSERT INTO `team_project_auth` VALUES (114, '管理员', 1, 0, '管理员', 0, '2020-07-06 10:51:05', '0c880c2966644465ba45cfd3a87701df', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (115, '成员', 1, 0, '成员', 0, '2020-07-06 10:51:05', '0c880c2966644465ba45cfd3a87701df', 0, 'member');
INSERT INTO `team_project_auth` VALUES (116, 'DEMO', 1, 0, '演示', 0, '2020-08-19 11:00:20', '6v7be19pwman2fird04gqu53', 1, NULL);
INSERT INTO `team_project_auth` VALUES (117, '下游项目负责人', 1, 0, '填报项目进度情况', 0, '2021-02-01 16:30:26', '2360f2f0f79447f4a2498ae06a9b132d', 0, NULL);
INSERT INTO `team_project_auth` VALUES (118, '管理员', 1, 0, '管理员', 0, '2021-02-02 10:46:38', '81afb4d2d4f644e792d94d2cc11fddb2', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (119, '成员', 1, 0, '成员', 0, '2021-02-02 10:46:38', '81afb4d2d4f644e792d94d2cc11fddb2', 0, 'member');
INSERT INTO `team_project_auth` VALUES (120, '管理员', 1, 0, '管理员', 0, '2021-02-02 10:47:08', '4b85f864069f4774a9b87941823bc947', 1, 'admin');
INSERT INTO `team_project_auth` VALUES (121, '成员', 1, 0, '成员', 0, '2021-02-02 10:47:08', '4b85f864069f4774a9b87941823bc947', 0, 'member');

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
) ENGINE = InnoDB AUTO_INCREMENT = 19897 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目角色与节点绑定' ROW_FORMAT = Dynamic;

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
-- Records of team_project_collection
-- ----------------------------
INSERT INTO `team_project_collection` VALUES (68, 'edba7252bddd447ba0a44485d886dd0a', '6v7be19pwman2fird04gqu53', '2020-07-06 10:50:43');
INSERT INTO `team_project_collection` VALUES (69, '8c4f887129e54068996e2d10a1c3bac9', 'bddc79203627409e9928b290b952ee88', '2020-07-06 15:08:12');

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
-- Records of team_project_features
-- ----------------------------
INSERT INTO `team_project_features` VALUES (1, 'qtsxlwob1m0uja37y2g4pefc', '3.x', '3.x', '2019-06-23 11:17:48', NULL, '6v7be19pwman2fird04gqu53', 'mo4uqwfb06dxv8ez2spkl3rg');
INSERT INTO `team_project_features` VALUES (2, 'qtsxlwob1m0uja37y2g4pefc1', '3.x', '3.x', '2019-06-23 11:17:48', '', '6v7be19pwman2fird04gqu53', 'nd5gkr9qc7wumei8zo630y41');
INSERT INTO `team_project_features` VALUES (3, '0jn2pulbgyav581fzsich4or', 'V1.0', '第一版本', '2020-04-08 15:49:04', NULL, '6v7be19pwman2fird04gqu53', 'nd5gkr9qc7wumei8zo630y41');
INSERT INTO `team_project_features` VALUES (4, 'rbqihzcnte5yw2vxd8uslg6k', 'aaa', 'aaaaaa', '2020-04-08 22:55:14', NULL, '6v7be19pwman2fird04gqu53', 'nd5gkr9qc7wumei8zo630y41');
INSERT INTO `team_project_features` VALUES (7, 'ca9d9600ebaa4313af7f23abbbd8170e', '新版本库', '新版本库', '2020-06-06 15:42:15', NULL, '6v7be19pwman2fird04gqu53', 'borhsewfgqxy38k1jmtznuv5');
INSERT INTO `team_project_features` VALUES (8, 'a6de1a6d3e174caa96d944276a3ebb85', 'Default', '888', '2021-02-01 14:20:26', NULL, '2360f2f0f79447f4a2498ae06a9b132d', '813a9c8b199e4dc59486cf6468ab91af');

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
-- Records of team_project_info
-- ----------------------------
INSERT INTO `team_project_info` VALUES (1, '1', '1', '1', '1', '1', '1', 'oebw2ycf016j7pnxum5zikg4', 0, '1');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5044 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_log
-- ----------------------------
INSERT INTO `team_project_log` VALUES (4468, 'bpw0ezos5y8mudjrv2naitg9', '6v7be19pwman2fird04gqu53', 'vilson', '邀请 vilson 加入项目', 'inviteMember', '2020-03-10 18:17:32', 'oebw2ycf016j7pnxum5zikg4', 'project', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4469, 'xr73h61kgtfvz0n4poqj9msa', '6v7be19pwman2fird04gqu53', 'test', '创建了项目 ', 'create', '2020-03-10 18:17:32', 'oebw2ycf016j7pnxum5zikg4', 'project', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4470, 'nzaw3rsbvf0t8596pju7lk24', '6v7be19pwman2fird04gqu53', 'vilson', '邀请 vilson 加入项目', 'inviteMember', '2020-03-10 22:36:48', 'nd5gkr9qc7wumei8zo630y41', 'project', '6v7be19pwman2fird04gqu53', 0, 'nd5gkr9qc7wumei8zo630y41', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4471, 't96u5pbcrsv3i0dznyq1mgao', '6v7be19pwman2fird04gqu53', 'aaaa', '创建了项目 ', 'create', '2020-03-10 22:36:48', 'nd5gkr9qc7wumei8zo630y41', 'project', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4472, '57vs0f9lmiup24na6ytbr3hw', '6v7be19pwman2fird04gqu53', 'vilson', '邀请 vilson 加入项目', 'inviteMember', '2020-03-11 10:30:57', 'jiflz2v1oxswtdqma468bk97', 'project', '6v7be19pwman2fird04gqu53', 0, 'jiflz2v1oxswtdqma468bk97', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4473, 'z6o2a8579mudk03sjvhy1xrf', '6v7be19pwman2fird04gqu53', 'bbbb', '创建了项目 ', 'create', '2020-03-11 10:30:58', 'jiflz2v1oxswtdqma468bk97', 'project', '', 0, 'jiflz2v1oxswtdqma468bk97', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4474, '0c5bemzgusv79thd6nx3kail', '6v7be19pwman2fird04gqu53', 'vilson', '邀请 vilson 加入项目', 'inviteMember', '2020-03-11 10:31:45', '38xs2fyvbgdc61hqiplazr7k', 'project', '6v7be19pwman2fird04gqu53', 0, '38xs2fyvbgdc61hqiplazr7k', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4475, '8a3976bieoy1pmwfvcg0hzkr', '6v7be19pwman2fird04gqu53', 'cccc', '创建了项目 ', 'create', '2020-03-11 10:31:45', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4476, 'wtj3ar0kdqgyox16984pl5su', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 12:06:41', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4477, 'yqmzd5760rpfkvt2cnebu1l8', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 12:07:24', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4478, '9xc4gneq5wl06h73zvftdj1b', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 12:08:22', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4479, 'rzdb8sk9fhtlym6v172njpaw', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 12:22:27', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4480, '38rbxci1ypnzskhvoqdtawle', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 14:04:05', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4481, 'vb9lz1yn34txmcfegp56d8ah', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 14:05:07', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4482, 'f08hxpr5bzq4de29c7tuagiv', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 14:05:40', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4483, 'v5psd09cx1wo2ufz3kmla74j', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 14:06:37', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4484, 'n4zmwfvilhe7s5cugx36oy9b', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 14:06:59', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4485, '03kt9j4zpyradbiucv76q1ex', '6v7be19pwman2fird04gqu53', 'cccc', '编辑了项目 ', 'edit', '2020-03-12 14:07:11', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4486, 'jzfwc5nxt0vku6iasqeo3g74', '6v7be19pwman2fird04gqu53', '', '归档了项目 ', 'archive', '2020-03-12 19:18:05', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4487, 'diy3e1safguqjncob5twx8v2', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recoveryArchive', '2020-03-12 19:18:27', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4488, 'g4v8fladkhqpix57uyenco29', '6v7be19pwman2fird04gqu53', '', '归档了项目 ', 'archive', '2020-03-12 19:18:52', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4489, '078kyrx5fwh2oaszjqbpvlgi', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recoveryArchive', '2020-03-12 19:30:35', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4490, 'o6h2myezsb04wjaucrl7vqnp', '6v7be19pwman2fird04gqu53', '', '归档了项目 ', 'archive', '2020-03-12 19:45:38', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4491, 'pzi5gmh1v7bq90t3ounwe86d', '6v7be19pwman2fird04gqu53', '', '把项目移到了回收站 ', 'recycle', '2020-03-12 20:21:50', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4492, 'z8krqu70eo9216nvgjcalhby', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recovery', '2020-03-12 20:24:21', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4493, 'gimbvadq5hxcw4pyukforjt3', '6v7be19pwman2fird04gqu53', '', '把项目移到了回收站 ', 'recycle', '2020-03-12 20:24:38', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4494, 'xc98vathlz6j7o0iy3p4m5w2', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recovery', '2020-03-12 20:30:39', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4495, 'ho2iv17zmrb3p0ujysl8anqc', '6v7be19pwman2fird04gqu53', '', '把项目移到了回收站 ', 'recycle', '2020-03-12 20:30:54', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4496, 'ypq471rbh5zdv9aetxo3um0n', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recovery', '2020-03-12 20:34:11', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4497, '76elktyua1fcxq2vg40bdjrh', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recovery', '2020-03-12 20:44:18', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4498, 'wjk8visqbecl57g3h2r046n1', '6v7be19pwman2fird04gqu53', '', '恢复了项目 ', 'recovery', '2020-03-12 20:45:21', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4499, 'ugy2i6j35od9c1qtnekpw8af', '6v7be19pwman2fird04gqu53', '', '把项目移到了回收站 ', 'recycle', '2020-03-12 20:55:31', '38xs2fyvbgdc61hqiplazr7k', 'project', '', 0, '38xs2fyvbgdc61hqiplazr7k', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4500, 'mxl40whs19v285tgeudcjop6', '6v7be19pwman2fird04gqu53', '', '把项目移到了回收站 ', 'recycle', '2020-03-12 22:14:05', 'jiflz2v1oxswtdqma468bk97', 'project', '', 0, 'jiflz2v1oxswtdqma468bk97', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4501, '4x19k8fruswiy5mgjvb7qzca', '6v7be19pwman2fird04gqu53', 'laoxuan', '邀请 laoxuan 加入项目', 'inviteMember', '2020-03-13 13:02:06', 'nd5gkr9qc7wumei8zo630y41', 'project', 'hscn4j2d0o1vl8a7tkwy6z5r', 0, 'nd5gkr9qc7wumei8zo630y41', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4502, 'rwb6s4xzpjych8mdu3i0an5g', '6v7be19pwman2fird04gqu53', 'aaaa', '编辑了项目 ', 'edit', '2020-03-14 13:17:50', 'nd5gkr9qc7wumei8zo630y41', 'project', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4503, 'm87fo2gz301jhlvdprcseqt4', '6v7be19pwman2fird04gqu53', 'aaaa', '编辑了项目 ', 'edit', '2020-03-14 13:17:53', 'nd5gkr9qc7wumei8zo630y41', 'project', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4504, 'gbr3tuhqxa12o8n5zm4eilsw', '6v7be19pwman2fird04gqu53', 'aaaa', '编辑了项目 ', 'edit', '2020-03-14 13:18:45', 'nd5gkr9qc7wumei8zo630y41', 'project', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4505, '532ypwzl1iecr8hg0sjmoxvb', '6v7be19pwman2fird04gqu53', '1233', '创建了任务 ', 'create', '2020-03-14 21:47:05', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4506, '5e7grln4fu18dyjowvb36tak', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-14 21:47:06', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4507, 's2y1rz59lwf346q0v8gxpkdo', '6v7be19pwman2fird04gqu53', '子任务', '创建了任务 ', 'create', '2020-03-14 22:15:20', 'o2tek3mp0ihfrsy8l7c6wqu4', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4508, 'gp54hk8ql1iv7scmzuoxfweb', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"子任务\"', 'createChild', '2020-03-14 22:15:20', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4509, '9qwzn53fxrmocaesg64lup8b', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-14 22:15:20', 'o2tek3mp0ihfrsy8l7c6wqu4', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4510, 'z847amew2jfcivdul16r0qyx', '6v7be19pwman2fird04gqu53', '<p>备注<br></p>', '更新了备注 ', 'content', '2020-03-14 23:01:27', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4511, 'nrtmoz8jhc0xikaw4ve1qfgp', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315132728-mysqlLog记录.txt \">mysqlLog记录.txt</a>', '关联了文件 ', 'linkFile', '2020-03-15 13:27:28', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4512, 'olydwtbxnvr2m745s90pgczj', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315132728-mysqlLog记录.txt \">mysqlLog记录.txt</a>', '上传了文件文件 ', 'uploadFile', '2020-03-15 13:27:29', 'oebw2ycf016j7pnxum5zikg4', 'project', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4513, 'whyzrnu7jfdbatpsx09lieov', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315141207-login.txt \">login.txt</a>', '关联了文件 ', 'linkFile', '2020-03-15 14:12:07', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4514, 'wdi894h17ojk5g6nxcum0aye', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200315/20200315141207-login.txt \">login.txt</a>', '上传了文件文件 ', 'uploadFile', '2020-03-15 14:12:07', 'oebw2ycf016j7pnxum5zikg4', 'project', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4515, '8qvdrem1w4u2azkipj90x653', '6v7be19pwman2fird04gqu53', '子任务二', '创建了任务 ', 'create', '2020-03-17 20:02:43', 'ysfgnztjdi0e2lm53u1p6ohk', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4516, 'hq7s4d35lvyt2ouwg96zprfi', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"子任务二\"', 'createChild', '2020-03-17 20:02:43', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4517, 'uotilq5f82mv7wrz3gdc0nx1', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-17 20:02:43', 'ysfgnztjdi0e2lm53u1p6ohk', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4518, 'wf1a8hqm47ldnctgbezk3ri0', '6v7be19pwman2fird04gqu53', '子任务二', '创建了任务 ', 'create', '2020-03-17 20:03:24', 'wr02dy68o3xtl149iqvbshkj', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4519, 'rtl017ewy5u9znj4q2apdh6m', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"子任务二\"', 'createChild', '2020-03-17 20:03:24', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4520, 'nz4tvgbr59f03ixeldhy2qok', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-17 20:03:24', 'wr02dy68o3xtl149iqvbshkj', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4521, 'oiu4gkr1cs0ythfbjx35lzwq', '6v7be19pwman2fird04gqu53', 'asd', 'asd', 'comment', '2020-03-18 21:38:41', '28qet31u7kym6gi54pa9jldr', 'task', '', 1, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4522, '103ujerakc2zf7si8m4odp5t', '6v7be19pwman2fird04gqu53', 'aaa@vilson ', 'aaa@vilson ', 'comment', '2020-03-19 14:39:13', '28qet31u7kym6gi54pa9jldr', 'task', '', 1, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4523, 'tfnhoy7kewr6udxsqj0cb3i4', '6v7be19pwman2fird04gqu53', 'ccc@vilson    vvv@vilson ', 'ccc@vilson    vvv@vilson ', 'comment', '2020-03-19 14:40:04', '28qet31u7kym6gi54pa9jldr', 'task', '', 1, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4525, 'xkuda7hoibwg35cysetj6nzm', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 03月26日 18:00', 'setEndTime', '2020-03-20 10:01:42', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4526, '2a102852b3e14667b5a5c4b471809db4', '6v7be19pwman2fird04gqu53', '111111111111111111111@vilson    22222222222222222@vilson ', '111111111111111111111@vilson    22222222222222222@vilson ', 'comment', '2020-03-20 20:52:33', '28qet31u7kym6gi54pa9jldr', 'task', '', 1, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4527, '37f02f4af69847a5b1641d5bfecc63b0', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-03-26 20:30', 'setEndTime', '2020-03-20 21:17:35', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4528, 'lu6rgh4a82nzxkivjedwmop3', '6v7be19pwman2fird04gqu53', '<p>备注aaa<br></p>', '更新了备注 ', 'content', '2020-03-20 21:21:57', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4529, '31b8ea7154da42fe99330355c114e883', '6v7be19pwman2fird04gqu53', '2020-03-26 20:30', '更新了备注 ', 'setEndTime', '2020-03-20 21:28:58', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4530, 'ia89clb3jsgh21nvkwx5p70z', '6v7be19pwman2fird04gqu53', '', '更新任务优先级为 紧急', 'pri', '2020-03-20 21:30:12', '28qet31u7kym6gi54pa9jldr', 'task', '', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4531, 'z1ju0ihq9rkpsl7v4dewbt63', '6v7be19pwman2fird04gqu53', '', '移除了执行者 ', 'removeExecutor', '2020-03-20 22:28:12', '28qet31u7kym6gi54pa9jldr', 'task', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4532, 'x8ycf6w29g4pqn0lsrthome3', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-20 22:30:40', '28qet31u7kym6gi54pa9jldr', 'task', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4533, 'khes3o9vdin6w01r7l2p5ucb', '6v7be19pwman2fird04gqu53', '', '移除了执行者 ', 'removeExecutor', '2020-03-20 22:31:11', '28qet31u7kym6gi54pa9jldr', 'task', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4534, 'up4e3l60toczfvrdm7wj9sib', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-20 22:31:20', '28qet31u7kym6gi54pa9jldr', 'task', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4535, 'x2pzuyeoksh7qivdla064bf3', '6v7be19pwman2fird04gqu53', '', '移除了执行者 ', 'removeExecutor', '2020-03-20 22:34:05', '28qet31u7kym6gi54pa9jldr', 'task', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4536, 'se9jdy60x1taprimqgw4f5vu', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-03-21 10:42:22', '28qet31u7kym6gi54pa9jldr', 'task', '6v7be19pwman2fird04gqu53', 0, 'oebw2ycf016j7pnxum5zikg4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4537, 'lb1oxvit8d07mw2z45ehf93u', '6v7be19pwman2fird04gqu53', 'laoxuan', '邀请 laoxuan 加入项目', 'inviteMember', '2020-03-27 22:51:11', 'nd5gkr9qc7wumei8zo630y41', 'project', 't2ci0sfhw1nlmzb8v7p5e3d6', 0, 'nd5gkr9qc7wumei8zo630y41', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4538, '61rqab89dnljitwm0z53ueox', '6v7be19pwman2fird04gqu53', 'laoxuan', '邀请 laoxuan 加入项目', 'inviteMember', '2020-03-27 22:53:04', 'nd5gkr9qc7wumei8zo630y41', 'project', 'kxl5vu9d63sfnwmterhpc21z', 0, 'nd5gkr9qc7wumei8zo630y41', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4539, 'b0or4t6np8hs2wm1c5vuxqk3', '6v7be19pwman2fird04gqu53', '啊啊啊', '创建了任务 ', 'create', '2020-04-05 13:40:34', 'l9n0imsewcbg5okzqp8fjd7x', 'task', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4540, 'as9fq14wmj2olkbg38yedrh7', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-04-05 13:40:35', 'l9n0imsewcbg5okzqp8fjd7x', 'task', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'user', 0);
INSERT INTO `team_project_log` VALUES (4541, 'es3m9vjqy6nr5hlb8407tgdu', '6v7be19pwman2fird04gqu53', 'bbbb', '创建了任务 ', 'create', '2020-04-09 16:23:21', 'f01gmwibphn4357zol2tqs9j', 'task', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4542, 'egf26jkinu3r4wz1xlmos9vh', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-04-09 16:23:21', 'f01gmwibphn4357zol2tqs9j', 'task', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'user', 0);
INSERT INTO `team_project_log` VALUES (4543, 'vfsh5tnl49ojkczy08bi3qx1', '6v7be19pwman2fird04gqu53', 'laoxuan', '邀请 laoxuan 加入项目', 'inviteMember', '2020-05-27 14:59:34', 'oebw2ycf016j7pnxum5zikg4', 'project', 'n4yht7i1a23wpvl6gf85e9dr', 0, 'oebw2ycf016j7pnxum5zikg4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4544, 'rt73zxdg8ka0cy6e5fpbwm29', '6v7be19pwman2fird04gqu53', 'laoxuan', '邀请 laoxuan 加入项目', 'inviteMember', '2020-05-27 15:02:01', 'nd5gkr9qc7wumei8zo630y41', 'project', 'n4yht7i1a23wpvl6gf85e9dr', 0, 'nd5gkr9qc7wumei8zo630y41', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4545, '9pnc314bhs2vj6mz7go0rki8', '6v7be19pwman2fird04gqu53', 'vilson', '邀请 vilson 加入项目', 'inviteMember', '2020-05-27 22:30:36', 'ol5bxfcrwz8p0jn12ihs3uaq', 'project', '6v7be19pwman2fird04gqu53', 0, 'ol5bxfcrwz8p0jn12ihs3uaq', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4546, 'p7jof0e5w2qvmgxkb6c4h91l', '6v7be19pwman2fird04gqu53', '22', '创建了项目 ', 'create', '2020-05-27 22:30:36', 'ol5bxfcrwz8p0jn12ihs3uaq', 'project', '', 0, 'ol5bxfcrwz8p0jn12ihs3uaq', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4547, 'xwps8b2z4uemc5d7h0rti1aq', '6v7be19pwman2fird04gqu53', 'vilson', '邀请 vilson 加入项目', 'inviteMember', '2020-05-28 00:48:00', 'borhsewfgqxy38k1jmtznuv5', 'project', '6v7be19pwman2fird04gqu53', 0, 'borhsewfgqxy38k1jmtznuv5', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4548, '16zyh08mswtno4u2kvfqgpai', '6v7be19pwman2fird04gqu53', '777', '创建了项目 ', 'create', '2020-05-28 00:48:00', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4549, 'xla4onsk81zghvy302dtfue9', '6v7be19pwman2fird04gqu53', '123', '创建了任务 ', 'create', '2020-06-02 21:55:03', 'upjzfcg3iqd05a7hxk1ys8to', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4550, 'an8s7vork6dwi1xyfqcjb2mt', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 21:55:03', 'upjzfcg3iqd05a7hxk1ys8to', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4551, 'qsuipzhb7rne2d4vx3mwg6al', '6v7be19pwman2fird04gqu53', '456', '创建了任务 ', 'create', '2020-06-02 21:55:37', 'vxwgqtb37m0ahfj5sd64y98i', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4552, '3hyaolcexbkdg2w5860tj17i', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"456\"', 'createChild', '2020-06-02 21:55:37', 'upjzfcg3iqd05a7hxk1ys8to', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4553, 'hgnwduytaf5mo7cb6kz4pjq3', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 21:55:37', 'vxwgqtb37m0ahfj5sd64y98i', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4554, 'pebs2nuc9r5ol4iwx7hjy8fd', '6v7be19pwman2fird04gqu53', '678', '创建了任务 ', 'create', '2020-06-02 21:56:21', 'h9eybcdg0opn52q4rfstvkum', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4555, '13zkuyefhsg0tnbxrila6pm2', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"678\"', 'createChild', '2020-06-02 21:56:21', 'vxwgqtb37m0ahfj5sd64y98i', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4556, 'paxsjumdbngk504yvwclt629', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 21:56:21', 'h9eybcdg0opn52q4rfstvkum', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4557, 'ejvboxd3gz6pnw74ra8fhcml', '6v7be19pwman2fird04gqu53', '890', '创建了任务 ', 'create', '2020-06-02 21:58:48', '9n1a0h3d5owyjteglfzic4bk', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4558, 'k9e3itabj6vxwpg4u5lr21f7', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"890\"', 'createChild', '2020-06-02 21:58:48', 'h9eybcdg0opn52q4rfstvkum', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4559, 'xms82iun40go79jcpve6r3th', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 21:58:48', '9n1a0h3d5owyjteglfzic4bk', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4560, 'ukcyx8pwa19dhz3r6ob427nm', '6v7be19pwman2fird04gqu53', 'aaa', '创建了任务 ', 'create', '2020-06-02 22:16:57', 't08omeap3fnlkqc5gyj2vh1z', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4561, 'u5jcs2o4pxkr0y67mn39fdwa', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 22:16:57', 't08omeap3fnlkqc5gyj2vh1z', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4562, '5siup9vymhaf2c71n3q0blet', '6v7be19pwman2fird04gqu53', 'aaa', '创建了任务 ', 'create', '2020-06-02 22:17:16', 'ma7b0tgwhx86sncfr5jl4z1o', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4563, 'ixd6fwzveu4ar8j20b7tc1q5', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 22:17:16', 'ma7b0tgwhx86sncfr5jl4z1o', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4564, '68yrpcl4d5i39befkxaq70su', '6v7be19pwman2fird04gqu53', 'bbb', '创建了任务 ', 'create', '2020-06-02 22:17:16', 'qgj7i4pzw8hovtcnkx653bae', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4565, '2ipzk3mc0oljn8hqxfutgera', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 22:17:16', 'qgj7i4pzw8hovtcnkx653bae', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4566, 'caqygv2rdkm30z8ebtw1jhfs', '6v7be19pwman2fird04gqu53', 'ccc', '创建了任务 ', 'create', '2020-06-02 22:17:16', 'sh2lqzgo6a10tmi7drfxevuj', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4567, 'fbx5dlp1qk9i6msyj2a8zo3n', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-02 22:17:16', 'sh2lqzgo6a10tmi7drfxevuj', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4568, '8rysweu3p965xqt0z1b47aln', '6v7be19pwman2fird04gqu53', '11111', '创建了任务 ', 'create', '2020-06-03 16:55:07', 'nsfjet4hxur6q5bl2wi970zc', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4569, '9yxav8miqzu07kne15fjt64s', '6v7be19pwman2fird04gqu53', '', '添加了子任务 \"11111\"', 'createChild', '2020-06-03 16:55:08', 'vxwgqtb37m0ahfj5sd64y98i', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4570, 'w20mgtz4scjkp6vhq91y853a', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-03 16:55:08', 'nsfjet4hxur6q5bl2wi970zc', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4571, '80aacfa5da994eefac67420f656fc56a', '6v7be19pwman2fird04gqu53', '<p>11111<br></p>', '更新了备注 ', 'content', '2020-06-03 18:10:16', 'upjzfcg3iqd05a7hxk1ys8to', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4572, 'b2a9debe4bc54ce48c55870baca156d3', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-06-24 18:00', 'setEndTime', '2020-06-03 18:12:56', 'upjzfcg3iqd05a7hxk1ys8to', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4573, '9a38aa513104449ebe45b8ac34aeb302', '6v7be19pwman2fird04gqu53', NULL, '更新任务优先级为 紧急', 'pri', '2020-06-03 18:13:02', 'upjzfcg3iqd05a7hxk1ys8to', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4574, 't74k6ofqpy39nha2mg1uxsw5', '6v7be19pwman2fird04gqu53', '555', '创建了任务 ', 'create', '2020-06-03 19:58:55', 'xl2e68b1g4vdjc5fhatruwo9', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4575, 'czn872edg6w3rmyufh4ob01x', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-06-03 19:58:55', 'xl2e68b1g4vdjc5fhatruwo9', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'user', 0);
INSERT INTO `team_project_log` VALUES (4576, '69a36797650e4fe9a6eb5af8622564ff', '6v7be19pwman2fird04gqu53', '111', '111', 'comment', '2020-06-03 20:51:39', '433cdd9e1bdf4ad58bf7a3edad05ce25', 'task', '', 1, 'borhsewfgqxy38k1jmtznuv5', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4577, 'ab7c92d0e7fd42b5b3f16b73457b5a91', '6v7be19pwman2fird04gqu53', '222', '222', 'comment', '2020-06-03 20:51:44', '433cdd9e1bdf4ad58bf7a3edad05ce25', 'task', '', 1, 'borhsewfgqxy38k1jmtznuv5', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4578, '762dwienrfklhupza3gm094v', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200605/20200605233849-0423.jpg \">0423.jpg</a>', '上传了文件文件 ', 'uploadFile', '2020-06-05 23:38:50', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4579, '0dd6e3a154c04e88a526668fa67ff014', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '20200607173626', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4580, '613vxaw7jk8mr50dqcoenhgu', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200607/20200607173956-I6000请求报文-可用.txt \">I6000请求报文-可用.txt</a>', '关联了文件 ', 'linkFile', '2020-06-07 17:39:56', 'f806fa9f3f8048dba65e70b1e556e7ec', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4581, 'mq8oc0zaky9bnj7e46hwgpxt', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200607/20200607173956-I6000请求报文-可用.txt \">I6000请求报文-可用.txt</a>', '上传了文件文件 ', 'uploadFile', '2020-06-07 17:39:57', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4582, 'bb448e2bd4f442f09d71d7d5cf3b59b8', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '20200607174546', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4583, 'wfsntzpo9mah835jv1l4gkei', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/2020060717362672a23f0921b1441b92b1212ddfc3090c.txt \">72a23f0921b1441b92b1212ddfc3090c.txt</a>', '取消关联文件', 'unlinkFile', '2020-06-07 17:47:34', 'f806fa9f3f8048dba65e70b1e556e7ec', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (4584, 'dcmzouqh1slb4rgj3apxk0t7', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:6565/pearProjectApi/static/upload/file/default/6v7be19pwman2fird04gqu53/6v7be19pwman2fird04gqu53/20200607/20200607173956-I6000请求报文-可用.txt \">I6000请求报文-可用</a>', '取消关联文件', 'unlinkFile', '2020-06-07 17:50:55', 'f806fa9f3f8048dba65e70b1e556e7ec', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (4585, 'ivb5t1u347ozwkanfjc6re28', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:8888/apiD:/idea-workspace/mproject/static/upload/6v7be19pwman2fird04gqu53/2020-06-07/20200607174546f60e8a1d6eba4efba9932c95093f2ca8.txt \">f60e8a1d6eba4efba9932c95093f2ca8.txt</a>', '取消关联文件', 'unlinkFile', '2020-06-07 17:51:00', 'f806fa9f3f8048dba65e70b1e556e7ec', 'task', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (4586, '167ea865865e45258d3867f274739cc7', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '20200607175226', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4587, 'e68bb3e9f27443f6b75c19c58fc41c27', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '20200607175747', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4588, 'a407763d19154782b69eefbfb434b026', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '20200607180433', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4589, '499ce7297d234aa9854758d50b41f45e', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '20200607180528', 'borhsewfgqxy38k1jmtznuv5', 'project', '', 0, 'borhsewfgqxy38k1jmtznuv5', 'link', 0);
INSERT INTO `team_project_log` VALUES (4590, '99f340ea9adb45d09e6d168bb70bc8b8', '6v7be19pwman2fird04gqu53', '00', '00', 'comment', '2020-06-22 20:30:01', 'f01gmwibphn4357zol2tqs9j', 'task', '', 1, 'nd5gkr9qc7wumei8zo630y41', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4591, 'c08d3f83f3ee4947a6d779d9648b8de5', 'bf5cf3f94e314f169f75ff2771b2f6f3', '111', '邀请111加入项目', 'inviteMember', '20200626094509', '4c2d9d0c0a3542798259a3cbe24a13ff', 'project', 'bf5cf3f94e314f169f75ff2771b2f6f3', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4592, '14fb323296fb4b4ab02da0ed023fbbb9', 'bf5cf3f94e314f169f75ff2771b2f6f3', '111', '移除了成员111', 'removeMember', '20200626102647', '4c2d9d0c0a3542798259a3cbe24a13ff', 'project', 'bf5cf3f94e314f169f75ff2771b2f6f3', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4596, '812bb940bfcc48b78749bfee13d571f5', '6v7be19pwman2fird04gqu53', '', '', 'recycle', '2020-06-29 10:37:08', '0c0e2970ce4c4dd0be94eb492aa76651', 'task', '', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4597, '2a5eee7046fc43e9a51bacb72abfa409', '6v7be19pwman2fird04gqu53', '', '', 'recovery', '2020-06-29 11:20:01', '0c0e2970ce4c4dd0be94eb492aa76651', 'task', '', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4598, 'a584206e109a40bf8f7b4d1e2a772946', '6v7be19pwman2fird04gqu53', NULL, '更新任务优先级为 非常紧急', 'pri', '2020-06-29 11:21:09', '0c0e2970ce4c4dd0be94eb492aa76651', 'task', '', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'user', 0);
INSERT INTO `team_project_log` VALUES (4599, '466bc598a9634fffb117c9562117f3de', '6v7be19pwman2fird04gqu53', '1111', '1111', 'comment', '2020-06-29 11:21:13', '0c0e2970ce4c4dd0be94eb492aa76651', 'task', '', 1, '4c2d9d0c0a3542798259a3cbe24a13ff', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4600, 'e9a31121361c4e71a14808fa61a22eb3', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-06-29 11:23:45', '4c2d9d0c0a3542798259a3cbe24a13ff', 'project', '', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'link', 0);
INSERT INTO `team_project_log` VALUES (4601, '9f8230157bdc4acdb09c3cdbffcbff5d', 'bf5cf3f94e314f169f75ff2771b2f6f3', '111', '邀请111加入项目', 'inviteMember', '2020-06-29 23:17:48', '11c80c7fabd146ca95d24621124f61b4', 'project', 'bf5cf3f94e314f169f75ff2771b2f6f3', 0, '11c80c7fabd146ca95d24621124f61b4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4602, 'ad32d9c6d7464f9f86b5c2b01796645d', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-06-29 23:21:42', 'nd5gkr9qc7wumei8zo630y41', 'project', '', 0, 'nd5gkr9qc7wumei8zo630y41', 'link', 0);
INSERT INTO `team_project_log` VALUES (4603, '1451aeb58dd3446d8863f6b48359d66d', '6v7be19pwman2fird04gqu53', '<p>犯得上反对犯得上房贷首付</p>', '更新了备注 ', 'content', '2020-06-30 01:40:36', 'cf9360a4b38d43e9b51da10252b816ab', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4604, '82fe45a66735456ab9f6de8a8e5f44d7', '6v7be19pwman2fird04gqu53', NULL, '更新任务优先级为 紧急', 'pri', '2020-06-30 01:40:39', 'cf9360a4b38d43e9b51da10252b816ab', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'user', 0);
INSERT INTO `team_project_log` VALUES (4605, 'de7832aba9f94e0f9d9de40f82099030', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-06-30 01:41:29', '11c80c7fabd146ca95d24621124f61b4', 'project', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4606, '968f2377189f4c5c95e35a3f8d69d21b', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-06-29 18:00', 'setEndTime', '2020-06-30 01:43:13', 'cf9360a4b38d43e9b51da10252b816ab', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4607, 'db0a6eebb3a244e4a9785184a68e6062', '6v7be19pwman2fird04gqu53', '', '', 'recycle', '2020-06-30 01:47:19', 'cf9360a4b38d43e9b51da10252b816ab', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4608, '9657c62f174f4567b890a3c9cd8fc4a2', '6v7be19pwman2fird04gqu53', '', '', 'recycle', '2020-06-30 01:47:43', '32a9e9d22c45449a8125d561d246ff7f', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4609, '318e1fa5b8134968b349136109eba4b5', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-06-29 18:00', 'setEndTime', '2020-06-30 01:48:18', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4610, '728a69e5dd6f46b9b383e39021328ef3', '6v7be19pwman2fird04gqu53', '', '', 'done', '2020-06-30 22:58:51', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4611, '333fc9f5c2e843488a28e36f90f009e0', '6v7be19pwman2fird04gqu53', '', '', 'done', '2020-06-30 22:59:20', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4612, 'b2cbd35cf9eb4745ab994e54189d7752', '6v7be19pwman2fird04gqu53', '', '', 'done', '2020-06-30 22:59:45', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4613, '82f15c4d85fd47ec893cc44f55bc2ee2', '6v7be19pwman2fird04gqu53', '', '', 'done', '2020-06-30 23:00:04', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4614, '0e2495531b3241c8be877cd9407dfc0c', '6v7be19pwman2fird04gqu53', '', '', 'done', '2020-06-30 23:09:10', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4615, 'db0f48fac8674ec7bd12a61aeea641ed', '6v7be19pwman2fird04gqu53', '', '', 'done', '2020-06-30 23:09:44', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4616, 'b91699b7568f462b87f0db63dbcaccb9', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-06-30 23:18:13', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4617, '9ae7dfff9c234b7badc614eaf3286f38', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-06-30 23:18:25', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4618, 'b71a72c390bd4148afe0abfc0bf5e1fc', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-06-30 23:18:29', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4622, 'a934fd9f4c8047b1a34e8a8cadf964cb', '48fd70b525c64e1698defd25129e3403', '222', '邀请222加入项目', 'inviteMember', '2020-07-01 16:57:32', '11c80c7fabd146ca95d24621124f61b4', 'project', '48fd70b525c64e1698defd25129e3403', 0, '11c80c7fabd146ca95d24621124f61b4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4623, '3e64cb6fb75645edb8bdec36d971be9f', 'a97fd0f77d69481c8f3af344dfd2ae40', '333', '邀请333加入项目', 'inviteMember', '2020-07-01 16:57:33', '11c80c7fabd146ca95d24621124f61b4', 'project', 'a97fd0f77d69481c8f3af344dfd2ae40', 0, '11c80c7fabd146ca95d24621124f61b4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4624, 'bc6acbe9ac594ef79ede16529cc0d91f', 'bf5cf3f94e314f169f75ff2771b2f6f3', '111', '邀请111加入项目', 'inviteMember', '2020-07-01 17:30:51', '468e736e7dbd4693a40e89fb8fc9dd5e', 'project', 'bf5cf3f94e314f169f75ff2771b2f6f3', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4625, '1489b9588c5e45158bb5a89e15e237df', '48fd70b525c64e1698defd25129e3403', '222', '邀请222加入项目', 'inviteMember', '2020-07-01 17:30:52', '468e736e7dbd4693a40e89fb8fc9dd5e', 'project', '48fd70b525c64e1698defd25129e3403', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4626, '29aedd2d1ce54e999dd50d20f3fb9fd8', 'a97fd0f77d69481c8f3af344dfd2ae40', '333', '邀请333加入项目', 'inviteMember', '2020-07-01 17:30:53', '468e736e7dbd4693a40e89fb8fc9dd5e', 'project', 'a97fd0f77d69481c8f3af344dfd2ae40', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4627, 'c81b07ec27ea4ea9a93c4bb26bbbf933', 'bf5cf3f94e314f169f75ff2771b2f6f3', '111', '邀请111加入项目', 'inviteMember', '2020-07-01 17:31:59', '38xs2fyvbgdc61hqiplazr7k', 'project', 'bf5cf3f94e314f169f75ff2771b2f6f3', 0, '38xs2fyvbgdc61hqiplazr7k', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4628, 'dad67a21dfd84404b8041d93b2681477', 'bf5cf3f94e314f169f75ff2771b2f6f3', '111', '移除了成员111', 'removeMember', '2020-07-01 17:32:19', '11c80c7fabd146ca95d24621124f61b4', 'project', 'bf5cf3f94e314f169f75ff2771b2f6f3', 0, '11c80c7fabd146ca95d24621124f61b4', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4630, '13b2358b5f9849b08b3e105aaffbb0da', '6v7be19pwman2fird04gqu53', '<p>撒范德萨范德萨</p>', '更新了备注 ', 'content', '2020-07-01 19:03:22', 'ccd020c2f02949a3b2a7544fce2fbff5', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4631, 'b1e0c70cda7f41539bbcaa376b2c6476', '6v7be19pwman2fird04gqu53', NULL, '更新任务优先级为 紧急', 'pri', '2020-07-01 19:03:26', 'ccd020c2f02949a3b2a7544fce2fbff5', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'user', 0);
INSERT INTO `team_project_log` VALUES (4632, '2cd9ff82543e4379924122dbb1b39b9d', '6v7be19pwman2fird04gqu53', '实时', '实时', 'comment', '2020-07-01 19:03:55', 'ccd020c2f02949a3b2a7544fce2fbff5', 'task', '', 1, '468e736e7dbd4693a40e89fb8fc9dd5e', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4633, '3937ada18a4d479d9a01e7dcee6d50d2', '6v7be19pwman2fird04gqu53', '', '', 'recycle', '2020-07-01 19:06:28', '10921f84fe254dde9598f6a4d60da9a2', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4634, 'ba49476244174c1f8fe1c29167691f07', '6v7be19pwman2fird04gqu53', '', '', 'recycle', '2020-07-02 06:21:21', '0c0e2970ce4c4dd0be94eb492aa76651', 'task', '', 0, '4c2d9d0c0a3542798259a3cbe24a13ff', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4636, 'f3d6f79770b74bf08322018d57b5e2f2', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-02 15:19:11', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4637, 'b333f1b54a944371b3377e3aaeb0de2c', '6v7be19pwman2fird04gqu53', '任务001\n', '将任务移动到 进行中', 'move', '2020-07-02 15:19:39', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4638, 'cdc3d11f8ab04ed4b254c2d7c219328a', '6v7be19pwman2fird04gqu53', '任务001\n', '将任务移动到 已完成', 'move', '2020-07-02 15:20:03', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4639, '02f5973fe722478ba54594402c6e01d8', '6v7be19pwman2fird04gqu53', '', '把任务移到了回收站 ', 'recycle', '2020-07-02 15:23:42', '3c3bd8d56a294978a8e55cd1a899bcc7', 'task', '', 0, 'jiflz2v1oxswtdqma468bk97', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4640, '9cbae92fcd9344f1ac3ffda4a3760438', '6v7be19pwman2fird04gqu53', '', '恢复了任务 ', 'recovery', '2020-07-02 15:24:06', '3c3bd8d56a294978a8e55cd1a899bcc7', 'task', '', 0, 'jiflz2v1oxswtdqma468bk97', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4641, '45b20e368cd44a229035c3489505aeb6', '6v7be19pwman2fird04gqu53', '', '把任务移到了回收站 ', 'recycle', '2020-07-02 15:24:28', '3c3bd8d56a294978a8e55cd1a899bcc7', 'task', '', 0, 'jiflz2v1oxswtdqma468bk97', 'delete', 0);
INSERT INTO `team_project_log` VALUES (4642, '8f81a6b7204f403893ec75b6e475d947', '6v7be19pwman2fird04gqu53', '', '恢复了任务 ', 'recovery', '2020-07-02 15:24:48', '3c3bd8d56a294978a8e55cd1a899bcc7', 'task', '', 0, 'jiflz2v1oxswtdqma468bk97', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4643, '2d3bcddc45ca4ab4a7ee20a09b37ff02', '6v7be19pwman2fird04gqu53', '1111', '将任务移动到 进行中', 'move', '2020-07-02 20:56:55', 'fa5dfdf4515a462f87b200cb18d46590', 'task', '', 0, 'jiflz2v1oxswtdqma468bk97', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4644, '9757352c4fc64a29bdc4f8578dd15f28', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-02 22:27:38', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4645, '8bd850c54f3c4232b7ef5a4e997157be', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-02 23:04:18', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4646, 'bc11ec74aa6344bf8a3ad7abc500761c', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-06-30 18:00', 'setEndTime', '2020-07-02 23:04:53', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4647, '6e4ff55b71014d73957763fd388db0be', '6v7be19pwman2fird04gqu53', '<p>fggh<br></p>', '更新了备注 ', 'content', '2020-07-02 23:05:03', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4648, 'ac6628aa275b42dabe2c12707722de4c', '6v7be19pwman2fird04gqu53', '<p>fgghaaaa<br></p>', '更新了备注 ', 'content', '2020-07-02 23:06:00', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4649, 'bdd058b8e06b4673b9be7710e780ca4d', '6v7be19pwman2fird04gqu53', '11111222222', '11111222222', 'comment', '2020-07-02 23:32:28', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 1, '11c80c7fabd146ca95d24621124f61b4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4650, 'b31ccd9671394f36a4a9be86a6beea59', '6v7be19pwman2fird04gqu53', '@\nss', '@\nss', 'comment', '2020-07-02 23:36:19', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 1, '11c80c7fabd146ca95d24621124f61b4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4651, '27058c0ef02d48c8bb6fed46e99fee3d', '6v7be19pwman2fird04gqu53', '', '更新预估工时为 10', 'setWorkTime', '2020-07-02 23:36:45', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'clock-circle', 0);
INSERT INTO `team_project_log` VALUES (4652, 'dc7341e173bd4881be51b02a79057a74', '6v7be19pwman2fird04gqu53', '任务001\n', '将任务移动到 进行中', 'move', '2020-07-02 23:38:02', '868df2ac982940c498ed5a5872e58ec5', 'task', '', 0, '11c80c7fabd146ca95d24621124f61b4', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4655, '6e05d87a26ea43a1b5701f183b32d2c5', '6v7be19pwman2fird04gqu53', '任务001\n', '将任务移动到 进行中', 'move', '2020-07-03 18:39:03', 'ccd020c2f02949a3b2a7544fce2fbff5', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4656, 'e4486599faad4f9a9baa5d93b6b008c2', '6v7be19pwman2fird04gqu53', '任务003\n', '将任务移动到 已完成', 'move', '2020-07-03 18:39:06', 'e90ce57142a14d15a2242265b1ed74cd', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4657, '8246eaa43cb74f3aa3b3410e6836e62b', '6v7be19pwman2fird04gqu53', '任务001\n', '将任务移动到 已完成', 'move', '2020-07-03 18:39:09', '54660fb7f9e540dd94385aac096bd589', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4658, 'dd139d66fc1c4c93b80cf23c9faa08e2', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 18:42:30', '54660fb7f9e540dd94385aac096bd589', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4659, '81fa550fab9648efb027e5c8ff3acf3c', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-03 18:43:46', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4660, '7b7b6eff37ba4e40a5c871b36b3a391d', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 挂起', 'status', '2020-07-03 18:44:18', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4661, '9611ec8247514f43b49adc6cb4f5c3c1', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 18:44:20', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4662, '72a7a339c72444e39f9474758228a4f3', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 未开始', 'status', '2020-07-03 18:44:38', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4663, 'ec184f02dc8a464c8c4fc7308556b9ca', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-03 18:44:41', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4664, '37bf50ce34044282aea99ef3a19def34', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 进行中', 'status', '2020-07-03 18:44:44', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4665, '21130c4d5c934d4494ef444f4c06c2e8', '6v7be19pwman2fird04gqu53', '<p>随时</p>', '更新了备注 ', 'content', '2020-07-03 18:44:53', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4666, '59f39b55c04d4d8d8eb62d6384a93277', '6v7be19pwman2fird04gqu53', '', '更新预估工时为 21', 'setWorkTime', '2020-07-03 18:46:08', 'fe545301d489440a9db961af6e0dafdb', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'clock-circle', 0);
INSERT INTO `team_project_log` VALUES (4667, '58db82e712de434a8b05f3e97d2d5031', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 18:53:44', 'e90ce57142a14d15a2242265b1ed74cd', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4668, 'f2d22be2b616414bb902ba844057bc43', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-03 18:53:45', 'e90ce57142a14d15a2242265b1ed74cd', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4669, '3731b5c72e654939875174242fd56019', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-03 18:53:46', '54660fb7f9e540dd94385aac096bd589', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4670, 'ecd1e8978b8e4b33830c3858b1d7012f', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 18:53:48', '54660fb7f9e540dd94385aac096bd589', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4671, '6d60da2c31474a1397cdab353e4204e7', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 18:53:55', 'e90ce57142a14d15a2242265b1ed74cd', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4672, '231bd3500366453e97779d0738c05b4e', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-03 18:54:02', 'e90ce57142a14d15a2242265b1ed74cd', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4673, '12841fcb22ea4db48b7ccb795b22cd83', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 18:54:04', 'e90ce57142a14d15a2242265b1ed74cd', 'task', '', 0, '468e736e7dbd4693a40e89fb8fc9dd5e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4674, 'fa896ecd6fe94b3287f94540be97f970', 'f3a06949f46049a986591de45e1cf9ec', 'tom', '邀请tom加入项目', 'inviteMember', '2020-07-03 11:43:46', 'jiflz2v1oxswtdqma468bk97', 'project', 'f3a06949f46049a986591de45e1cf9ec', 0, 'jiflz2v1oxswtdqma468bk97', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4675, '68a2579bf5b04de38d518f3471b103cb', '6v7be19pwman2fird04gqu53', '123456', '邀请123456加入项目', 'inviteMember', '2020-07-03 12:12:16', 'eb8bfddb03ec42d2af05e5f418e46259', 'project', '6v7be19pwman2fird04gqu53', 0, 'eb8bfddb03ec42d2af05e5f418e46259', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4676, '7b88db2f533f4dee8d9d4f19ca6b4d98', '6v7be19pwman2fird04gqu53', '2020-7-20汇报水文调查情况范德萨范德萨范德萨范德萨范德萨范德萨发范德萨范德萨范德萨范德萨', '更新了内容 ', 'name', '2020-07-04 01:32:42', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4677, '100094779db641629defe38788917c89', '6v7be19pwman2fird04gqu53', '2020-7-20汇报水文调查情况范德萨范德萨范德萨范德萨范德萨范德萨发范德萨范德萨范德萨范德萨但是犯得上房贷首付范德萨范德萨范德萨范德萨纷纷我热我热我热温热', '更新了内容 ', 'name', '2020-07-04 01:32:54', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4678, '82b78f7fc0ea488cbbc67bbf33af0c38', '6v7be19pwman2fird04gqu53', '2020-7-20汇报水文调查情况范德萨范德萨范德萨范德萨范德萨范德萨发范德萨范德萨范德萨范德萨但是犯得上房贷首付范德萨范德萨范德萨范德萨纷纷我热我热我热温热', '将任务移动到 进行中', 'move', '2020-07-04 01:36:30', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4679, '653a4cef71314768ada822cd9ba25156', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-04 01:37:02', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4680, 'c4803e63d3df4628b2a9ab570beaee4e', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-04 01:37:05', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4681, '293358e6ea96414ca268665ca11f8710', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 未开始', 'status', '2020-07-04 01:37:15', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4682, '3817d63b42e845f186432c52fc48bef7', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-04 01:37:17', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4683, 'e15d9dcda3e24d2994b5a994f8b9f4ba', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-04 01:37:19', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4684, 'a4d12320883741f4bac30430fffb8952', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-04 01:37:20', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4685, '8243564cdd25475da9cfc6b3605b56c6', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-07-03 18:00', 'setEndTime', '2020-07-04 01:37:23', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4686, '74c00a72870e421fa711f0ac02a44e27', '6v7be19pwman2fird04gqu53', '<p>JFK拉德斯基分厘卡的德萨范德萨范德萨范德萨纷</p>', '更新了备注 ', 'content', '2020-07-04 01:37:44', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4687, '397f1235909944a190366b1d7419c32d', 'f3a06949f46049a986591de45e1cf9ec', 'tom', '邀请tom加入项目', 'inviteMember', '2020-07-04 01:40:25', '6d54506b13a947f58895eec8db465e7e', 'project', 'f3a06949f46049a986591de45e1cf9ec', 0, '6d54506b13a947f58895eec8db465e7e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4688, 'c8a66409765743db929ba88e19f2f982', '6v7be19pwman2fird04gqu53', '2020-7-20汇报水文调查情况范德萨范德萨范德萨范德萨范德萨范德萨发范德萨范德萨范德萨范德萨但是犯得上房贷首付范德萨范德萨范德萨范德萨纷纷我热我热我热温热', '将任务移动到 已完成', 'move', '2020-07-04 03:08:05', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4689, '84ab6a31b0324542ad51f64ec81f2556', '6v7be19pwman2fird04gqu53', '2020-7-20汇报水文调查情况范德萨范德萨范德萨范德萨范德萨范德萨发范德萨范德萨范德萨范德萨但是犯得上房贷首付范德萨范德萨范德萨范德萨纷纷我热我热我热温热', '将任务移动到 进行中', 'move', '2020-07-04 03:08:10', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4690, 'd15bfae4fdc745bfa145a4d677f27069', '6v7be19pwman2fird04gqu53', '任务0003\n', '将任务移动到 已完成', 'move', '2020-07-04 03:08:13', '6d04956d1be4437183cf5a477b9575fa', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4691, '731aa05e52ae4217930f36da9de6060e', '6v7be19pwman2fird04gqu53', 'ss', 'ss', 'comment', '2020-07-04 03:08:36', '6d04956d1be4437183cf5a477b9575fa', 'task', '', 1, '6d54506b13a947f58895eec8db465e7e', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4692, 'fdfcba5fb95942fca60b1d178a5e3a06', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-04 03:13:31', 'b7cb4cb5d61542148cf02c4c9e5639f5', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4693, 'bdd69cf6c3294832943b375bc29b8fae', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-04 03:13:35', 'b7cb4cb5d61542148cf02c4c9e5639f5', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4694, 'bcd3b9c155e94a61aa68ae19d5aecd2b', '6v7be19pwman2fird04gqu53', '2020-7-20汇报水文调查情况', '更新了内容 ', 'name', '2020-07-04 03:16:38', '836a97066da741869ed87c1b8c456018', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4695, 'f2a96466a35b4539b30541e2034d8410', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 22:20:29', '536fb69f9c794a6a8a4009ac3e4e336c', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4696, '48e941d299464c2899e190d0ad336511', '6v7be19pwman2fird04gqu53', '', '完成了子任务 任务004\n', 'doneChild', '2020-07-03 22:20:29', '38144bfd4f6c4a3f8163bba9e3cca9b9', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4697, '66f2c6ac468b484d81394f3e3e993e45', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-03 23:47:51', '536fb69f9c794a6a8a4009ac3e4e336c', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4698, '6966d003ee8b4f858c049a078df2635a', '6v7be19pwman2fird04gqu53', '', '重做了子任务 任务004\n', 'redoChild', '2020-07-03 23:47:51', '38144bfd4f6c4a3f8163bba9e3cca9b9', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4699, 'd23f55cee84245e18c9243cbb691f0e5', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 23:47:55', '536fb69f9c794a6a8a4009ac3e4e336c', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4700, 'a5518b6bc1d14ec0bc68b279994faa89', '6v7be19pwman2fird04gqu53', '', '完成了子任务 任务004\n', 'doneChild', '2020-07-03 23:47:55', '38144bfd4f6c4a3f8163bba9e3cca9b9', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4701, 'f5e024e0ea1b4227ba94d586f8670801', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-03 23:47:58', '536fb69f9c794a6a8a4009ac3e4e336c', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4702, 'bab23dc20fd34e07ad5c83612f1b0f61', '6v7be19pwman2fird04gqu53', '', '重做了子任务 任务004\n', 'redoChild', '2020-07-03 23:47:58', '38144bfd4f6c4a3f8163bba9e3cca9b9', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4703, '2a52d54c0c3144808ae684410774654d', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-03 23:48:02', '536fb69f9c794a6a8a4009ac3e4e336c', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4704, '856940b9f5074b719ea31fa91fca584f', '6v7be19pwman2fird04gqu53', '', '完成了子任务 任务004\n', 'doneChild', '2020-07-03 23:48:02', '38144bfd4f6c4a3f8163bba9e3cca9b9', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4705, '4bb628ff9baf4c1da182f08e886ed3db', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-03 23:48:19', '536fb69f9c794a6a8a4009ac3e4e336c', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4706, '484d620cedec4f7b81b67a958fb16f5f', '6v7be19pwman2fird04gqu53', '', '重做了子任务 任务004\n', 'redoChild', '2020-07-03 23:48:19', '38144bfd4f6c4a3f8163bba9e3cca9b9', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4707, '8e47a3114e1f4f15958859da2fddeaa7', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 15:54:26', '6d54506b13a947f58895eec8db465e7e', 'project', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4708, '200484acdb6746648c2684ac9e81edf1', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 15:55:35', '6d54506b13a947f58895eec8db465e7e', 'project', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4709, '9bd13954cd6347449232c0c960c4adaa', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 09:08:24', '6d54506b13a947f58895eec8db465e7e', 'project', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4710, '998e628c4fe941188e83aa6860ba853a', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 09:25:56', '6d54506b13a947f58895eec8db465e7e', 'project', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4711, '9101ef812b004f719186c65ce3b35c0e', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 11:48:36', '6d54506b13a947f58895eec8db465e7e', 'project', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4712, '4f0c80a93abb40f9a2825e0018c26e27', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 11:54:16', '6d54506b13a947f58895eec8db465e7e', 'project', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4713, '3e1f8ba20d33459480f70cb7c2be0e93', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 12:47:45', '6d54506b13a947f58895eec8db465e7e', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4714, '941284a107d34a8680c692052c76d82f', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"null \">\"null</a>', '上传了文件文件', 'uploadFile', '2020-07-04 12:50:52', '0261ceed8197454a9cdcd66a8f9257da', 'task', '', 0, 'eb8bfddb03ec42d2af05e5f418e46259', 'link', 0);
INSERT INTO `team_project_log` VALUES (4715, '508c0d70ce584526bf318c432926f7b8', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/178bf57fbd8844b387b46f9af5686f42-物资发放签收单.xls&realFileName=物资发放签收单.xls \">\"物资发放签收单</a>', '上传了文件文件', 'uploadFile', '2020-07-04 13:08:37', '0261ceed8197454a9cdcd66a8f9257da', 'task', '', 0, 'eb8bfddb03ec42d2af05e5f418e46259', 'link', 0);
INSERT INTO `team_project_log` VALUES (4716, '7ed548a2d7bd4828a52cfcaf04f68a19', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/0ad5b9fdeefa4ba1af1fc07d87e70056-万科社区CIEMS开发计划.xlsx&realFileName=万科社区CIEMS开发计划.xlsx \">\"万科社区CIEMS开发计划</a>', '上传了文件文件', 'uploadFile', '2020-07-04 13:13:30', '0261ceed8197454a9cdcd66a8f9257da', 'task', '', 0, 'eb8bfddb03ec42d2af05e5f418e46259', 'link', 0);
INSERT INTO `team_project_log` VALUES (4717, '720df02c3b3b44878c74566a80dd9131', '6v7be19pwman2fird04gqu53', '<a target=\'_blank\' class=\'muted\' href=\'http://127.0.0.1:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200704/0ad5b9fdeefa4ba1af1fc07d87e70056-万科社区CIEMS开发计划.xlsx&realFileName=万科社区CIEMS开发计划.xlsx\'>万科社区CIEMS开发计划</a>', '取消关联文件', 'unlinkFile', '2020-07-04 13:17:37', '0261ceed8197454a9cdcd66a8f9257da', 'task', '', 0, 'eb8bfddb03ec42d2af05e5f418e46259', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (4718, 'e6d06ce97adc49bca59f59a1a40c3ff7', '6v7be19pwman2fird04gqu53', 'aaaa1111', '更新了内容 ', 'name', '2020-07-04 17:47:40', '3f49fe66c0b840f0845397c2848914a3', 'task', '', 0, 'jiflz2v1oxswtdqma468bk97', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4719, '27ba920cdb5b46df9d01e2e3cba2a09e', 'c91005101ca347568133686374d8c6e5', '张三', '邀请张三加入项目', 'inviteMember', '2020-07-04 19:26:57', 'jiflz2v1oxswtdqma468bk97', 'project', 'c91005101ca347568133686374d8c6e5', 0, 'jiflz2v1oxswtdqma468bk97', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4720, 'ac847ad5fedb498092e38e5557a15c8d', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-05 21:39:36', 'abb6a40af8c248d9be0bf80ab8e0b4db', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4721, '074d60c954cb459babbfb1dbf04be849', '6v7be19pwman2fird04gqu53', '', '完成了子任务 父任务001\n', 'doneChild', '2020-07-05 21:39:36', '7d92754ae4244c0e8eb7b4f93da42f69', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4722, 'f805cb5874fe457186f9f35079f19f12', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-05 21:40:08', 'abb6a40af8c248d9be0bf80ab8e0b4db', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'border', 0);
INSERT INTO `team_project_log` VALUES (4723, '6abcee9f9c314fdfb3f677493c29c354', '6v7be19pwman2fird04gqu53', '', '重做了子任务 父任务001\n', 'redoChild', '2020-07-05 21:40:08', '7d92754ae4244c0e8eb7b4f93da42f69', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'undo', 0);
INSERT INTO `team_project_log` VALUES (4724, '469bf45cefe7498d9ed554fd00a149a0', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-05 21:40:17', 'abb6a40af8c248d9be0bf80ab8e0b4db', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4725, 'b25607094a574525b4a07eb99be85ec1', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-05 21:40:19', 'abb6a40af8c248d9be0bf80ab8e0b4db', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4726, 'e5d05e6ae7944d45a3c59c71a5e04632', '6v7be19pwman2fird04gqu53', '', '完成了子任务 父任务001\n', 'doneChild', '2020-07-05 21:40:19', '7d92754ae4244c0e8eb7b4f93da42f69', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4727, 'ac5f75a0faeb44d5aa5a7ee11564e153', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://120.27.62.173:80/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200705/fcdbc6cfb7e64759ba3e691990110e08-测试文件-副本.docx&realFileName=测试文件-副本.docx \">\"测试文件-副本</a>', '上传了文件文件', 'uploadFile', '2020-07-05 21:40:26', 'abb6a40af8c248d9be0bf80ab8e0b4db', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'link', 0);
INSERT INTO `team_project_log` VALUES (4728, '0d7ee02a45104bc4b937e26974639cad', '6v7be19pwman2fird04gqu53', '工作总结编写', '将任务移动到 进行中', 'move', '2020-07-05 21:43:12', 'b7cb4cb5d61542148cf02c4c9e5639f5', 'task', '', 0, '6d54506b13a947f58895eec8db465e7e', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4729, 'b2aa5ceb34ec449e871ac9ac77a0bdbc', '9531193bca0e4049a2d763398e7e6ee7', '', '更新任务优先级为 紧急', 'pri', '2020-07-05 22:00:18', 'f4b6595b4a304bdab34ab3ce3afe01a8', 'task', '', 0, '12b5fb56e28749eabe46c276308f6d15', 'user', 0);
INSERT INTO `team_project_log` VALUES (4730, '4d748fd82d334f62a024237a94f594df', '9531193bca0e4049a2d763398e7e6ee7', '水利调研报告编写\n', '将任务移动到 进行中', 'move', '2020-07-05 22:22:39', 'f4b6595b4a304bdab34ab3ce3afe01a8', 'task', '', 0, '12b5fb56e28749eabe46c276308f6d15', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4731, '9b0b510fd3aa41d2babbfc79a0c21eff', '9531193bca0e4049a2d763398e7e6ee7', '水利调研报告编写\n', '将任务移动到 已完成', 'move', '2020-07-05 22:22:41', 'f4b6595b4a304bdab34ab3ce3afe01a8', 'task', '', 0, '12b5fb56e28749eabe46c276308f6d15', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4732, 'fa7a52114fd2474abd62332332649cd9', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-07-05 18:00', 'setEndTime', '2020-07-05 23:26:14', '92ed22cebd29473782914b603dd2f624', 'task', '', 0, 'd4f542577ac4406eaaac4ff4d1bc195e', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4734, '4443f93237124a5bb398be1bc5ee71ec', 'bbee05e00037470786f299cc730f21a2', '羊多多', '邀请羊多多加入项目', 'inviteMember', '2020-07-05 16:05:58', '6d54506b13a947f58895eec8db465e7e', NULL, 'bbee05e00037470786f299cc730f21a2', 0, '6d54506b13a947f58895eec8db465e7e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4735, '167fab506d694791bd21a0d62a82d05c', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-07-10 18:00', 'setEndTime', '2020-07-06 00:47:26', '129cdebcf8e84a8b88e5c8920f566d20', 'task', '', 0, '7109c15a1dcc4292b2514d91842d9463', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4736, 'fd6227b432f44687a12bd4c78feaa913', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 进行中', 'status', '2020-07-06 00:47:36', '129cdebcf8e84a8b88e5c8920f566d20', 'task', '', 0, '7109c15a1dcc4292b2514d91842d9463', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4737, 'dca639c589e24e0f945a37db59bd11aa', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://120.27.62.173:80/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/c0d0757f7c0946919dab7b34bf330c1a-测试文件-副本.docx&realFileName=测试文件-副本.docx \">\"测试文件-副本</a>', '上传了文件文件', 'uploadFile', '2020-07-06 00:48:26', '129cdebcf8e84a8b88e5c8920f566d20', 'task', '', 0, '7109c15a1dcc4292b2514d91842d9463', 'link', 0);
INSERT INTO `team_project_log` VALUES (4738, '02aca68000d94ef3bcb953d756053863', '6v7be19pwman2fird04gqu53', '项目经理分配项目1', '创建了项目', 'create', '2020-07-05 19:53:38', '002a636f06e5488b80fe266762acb4d6', NULL, '0', 0, '002a636f06e5488b80fe266762acb4d6', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4739, 'f488e4f3082c4a54babb9f0392acace7', '6v7be19pwman2fird04gqu53', '项目经理', '邀请项目经理加入项目', 'inviteMember', '2020-07-05 19:53:38', '002a636f06e5488b80fe266762acb4d6', NULL, '6v7be19pwman2fird04gqu53', 0, '002a636f06e5488b80fe266762acb4d6', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4740, 'ccef176800ad49fd90911c5a0632fab7', '11f38e99e8104a73a05133ca67155fa7', '羊多多', '邀请羊多多加入项目', 'inviteMember', '2020-07-05 20:25:36', '002a636f06e5488b80fe266762acb4d6', NULL, '11f38e99e8104a73a05133ca67155fa7', 0, '002a636f06e5488b80fe266762acb4d6', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4741, 'f5b45ca0e69040fd81453e49492a87d8', '11f38e99e8104a73a05133ca67155fa7', '羊多多的个人项目1', '创建了项目', 'create', '2020-07-05 20:57:40', '7fc135d9be2f4076b7286e497399014f', NULL, '0', 0, '7fc135d9be2f4076b7286e497399014f', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4742, 'e85741581ab14434ada217a69b0ac073', '11f38e99e8104a73a05133ca67155fa7', '羊多多', '邀请羊多多加入项目', 'inviteMember', '2020-07-05 20:57:40', '7fc135d9be2f4076b7286e497399014f', NULL, '11f38e99e8104a73a05133ca67155fa7', 0, '7fc135d9be2f4076b7286e497399014f', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4743, 'cdb9f6b3ae8e41eba38a4e20bee339d7', '6v7be19pwman2fird04gqu53', '项目经理的项目', '创建了项目', 'create', '2020-07-05 22:22:51', 'd1b35fdb678242efbad4e700e712afb1', NULL, '0', 0, 'd1b35fdb678242efbad4e700e712afb1', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4744, '0f289ad5d5bd474d82699b51d24a3d46', '6v7be19pwman2fird04gqu53', '项目经理', '邀请项目经理加入项目', 'inviteMember', '2020-07-05 22:22:51', 'd1b35fdb678242efbad4e700e712afb1', NULL, '6v7be19pwman2fird04gqu53', 0, 'd1b35fdb678242efbad4e700e712afb1', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4745, '9857701a23c34bc6bed636bdb5538071', '914c0e87aa11436fae649dc69c134b39', '牛多多', '邀请牛多多加入项目', 'inviteMember', '2020-07-05 22:23:00', 'd1b35fdb678242efbad4e700e712afb1', NULL, '914c0e87aa11436fae649dc69c134b39', 0, 'd1b35fdb678242efbad4e700e712afb1', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4746, 'e6282b57d01a4d9bbe476b65599d8044', 'd4692e58e8fc45b1b6a6423dc4d06b14', '项目1', '创建了项目', 'create', '2020-07-05 22:49:07', '50c9cebd310a4461a8355a8e2ebd74cf', NULL, '0', 0, '50c9cebd310a4461a8355a8e2ebd74cf', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4747, 'd2acaa18fa6648c79dad167681a63fcb', 'd4692e58e8fc45b1b6a6423dc4d06b14', '牛多多', '邀请牛多多加入项目', 'inviteMember', '2020-07-05 22:49:07', '50c9cebd310a4461a8355a8e2ebd74cf', NULL, 'd4692e58e8fc45b1b6a6423dc4d06b14', 0, '50c9cebd310a4461a8355a8e2ebd74cf', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4748, 'b7274e6d0fc045cd94586d12d49080b1', 'd4692e58e8fc45b1b6a6423dc4d06b14', '牛多多创建', '创建了项目', 'create', '2020-07-05 23:05:28', 'e5b3217981104973a3b14d37f3c9133e', NULL, '0', 0, 'e5b3217981104973a3b14d37f3c9133e', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4749, '8a6826efff094085a909438d8ec08cae', 'd4692e58e8fc45b1b6a6423dc4d06b14', '牛多多', '邀请牛多多加入项目', 'inviteMember', '2020-07-05 23:05:28', 'e5b3217981104973a3b14d37f3c9133e', NULL, 'd4692e58e8fc45b1b6a6423dc4d06b14', 0, 'e5b3217981104973a3b14d37f3c9133e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4751, '42e0dd29596f4c2ba6a29b68a7929ed1', 'd4692e58e8fc45b1b6a6423dc4d06b14', '111', '创建了任务 ', 'create', '2020-07-05 23:34:51', '91bdbf48cf8748629f61ad583087e8b2', 'task', '', 0, 'e5b3217981104973a3b14d37f3c9133e', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4752, 'c37ee63be091452695c6238f1987a3b4', 'd4692e58e8fc45b1b6a6423dc4d06b14', '', '认领了任务 ', 'claim', '2020-07-05 23:34:51', '91bdbf48cf8748629f61ad583087e8b2', 'task', '', 0, 'e5b3217981104973a3b14d37f3c9133e', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4753, '2ac64a7d07e24ec18e5bcb39fd878f79', '365ff3d5ffee4b5a9ad7b75c589d1086', '史多多', '邀请史多多加入项目', 'inviteMember', '2020-07-05 23:59:45', 'e5b3217981104973a3b14d37f3c9133e', NULL, '365ff3d5ffee4b5a9ad7b75c589d1086', 0, 'e5b3217981104973a3b14d37f3c9133e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4754, '5284c25ecaca4649b370ed0fb10e00d3', 'd4692e58e8fc45b1b6a6423dc4d06b14', '', '添加了参与者 史多多', 'inviteMember', '2020-07-06 00:27:21', '91bdbf48cf8748629f61ad583087e8b2', 'task', '365ff3d5ffee4b5a9ad7b75c589d1086', 0, 'e5b3217981104973a3b14d37f3c9133e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4755, '699960be74a24974b192df86aec4a668', 'd4692e58e8fc45b1b6a6423dc4d06b14', '', '完成了任务 ', 'done', '2020-07-06 00:27:37', '91bdbf48cf8748629f61ad583087e8b2', 'task', '', 0, 'e5b3217981104973a3b14d37f3c9133e', 'check', 0);
INSERT INTO `team_project_log` VALUES (4756, '6f6f6dcdc76e4f32854b40bad871c2c2', '6v7be19pwman2fird04gqu53', '新项目0706', '创建了项目', 'create', '2020-07-06 10:20:45', 'edba7252bddd447ba0a44485d886dd0a', NULL, '0', 0, 'edba7252bddd447ba0a44485d886dd0a', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4757, '8cd02d48167f420d95b303b3102fc113', '6v7be19pwman2fird04gqu53', '项目经理', '邀请项目经理加入项目', 'inviteMember', '2020-07-06 10:20:45', 'edba7252bddd447ba0a44485d886dd0a', NULL, '6v7be19pwman2fird04gqu53', 0, 'edba7252bddd447ba0a44485d886dd0a', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4758, 'b1f3022625894600a22f9bd276663a28', '6v7be19pwman2fird04gqu53', '水利部水资源调研项目', '创建了项目', 'create', '2020-07-06 10:22:43', '8c4f887129e54068996e2d10a1c3bac9', NULL, '0', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4759, '75df4f539ae343a79e79e72b0ba30e0b', '6v7be19pwman2fird04gqu53', '项目经理', '邀请项目经理加入项目', 'inviteMember', '2020-07-06 10:22:43', '8c4f887129e54068996e2d10a1c3bac9', NULL, '6v7be19pwman2fird04gqu53', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4760, '01948a43807143c5a3136a4f159bfb71', 'bddc79203627409e9928b290b952ee88', '张一', '邀请张一加入项目', 'inviteMember', '2020-07-06 10:51:15', '8c4f887129e54068996e2d10a1c3bac9', NULL, 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4761, '5cecaa6009724f7aba743e2e103a25bb', '6d44c444965349ae926cd5be98912292', '王伟', '邀请王伟加入项目', 'inviteMember', '2020-07-06 10:51:17', '8c4f887129e54068996e2d10a1c3bac9', NULL, '6d44c444965349ae926cd5be98912292', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4762, '32a54f8554324171be3d029d3382b8f6', 'e9b4bbe96f04474599c9f014ea1f47e7', '李超', '邀请李超加入项目', 'inviteMember', '2020-07-06 10:51:18', '8c4f887129e54068996e2d10a1c3bac9', NULL, 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4763, '69eef98b569546cba7467c49600d95ff', '6v7be19pwman2fird04gqu53', '测试项目', '创建了项目', 'create', '2020-07-06 10:51:24', '78bcc133d0cc4885975ebabeabb97ffa', NULL, '0', 0, '78bcc133d0cc4885975ebabeabb97ffa', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4764, '9f2301011ee241b4bc8b2be2fdcdb941', '6v7be19pwman2fird04gqu53', '项目经理', '邀请项目经理加入项目', 'inviteMember', '2020-07-06 10:51:24', '78bcc133d0cc4885975ebabeabb97ffa', NULL, '6v7be19pwman2fird04gqu53', 0, '78bcc133d0cc4885975ebabeabb97ffa', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4765, 'a80296daea6b4070a58c2c3fe2c03913', 'bddc79203627409e9928b290b952ee88', '张一', '邀请张一加入项目', 'inviteMember', '2020-07-06 10:51:37', '78bcc133d0cc4885975ebabeabb97ffa', NULL, 'bddc79203627409e9928b290b952ee88', 0, '78bcc133d0cc4885975ebabeabb97ffa', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4766, '626dc155c72b47708716de8e4d4c8771', '6d44c444965349ae926cd5be98912292', '王伟', '邀请王伟加入项目', 'inviteMember', '2020-07-06 10:51:38', '78bcc133d0cc4885975ebabeabb97ffa', NULL, '6d44c444965349ae926cd5be98912292', 0, '78bcc133d0cc4885975ebabeabb97ffa', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4767, '7903edc0bf5f41428b87455dfe373ff2', 'bddc79203627409e9928b290b952ee88', '张一的项目', '创建了项目', 'create', '2020-07-06 11:08:29', 'c70dbd8001e04e96b838309915f790ea', NULL, '0', 0, 'c70dbd8001e04e96b838309915f790ea', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4768, '8eefe351998744a4944040d270627c8c', 'bddc79203627409e9928b290b952ee88', '张一', '邀请张一加入项目', 'inviteMember', '2020-07-06 11:08:29', 'c70dbd8001e04e96b838309915f790ea', NULL, 'bddc79203627409e9928b290b952ee88', 0, 'c70dbd8001e04e96b838309915f790ea', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4769, '1698d072d4544d4991cb835e5a022c15', '6v7be19pwman2fird04gqu53', '水资源调研报告编写\n', '创建了任务 ', 'create', '2020-07-06 15:11:58', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4770, '5a2dcd9fe2e8478d82aafc4e58d93914', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 15:11:58', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4771, '5ac5c0ec0aef43bca30833b7fda097f5', '6v7be19pwman2fird04gqu53', '', '添加了参与者 王伟', 'inviteMember', '2020-07-06 15:12:40', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '6d44c444965349ae926cd5be98912292', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4772, 'f3828aad1e7544e8ad2585eb15cb37aa', '6v7be19pwman2fird04gqu53', '', '添加了参与者 张一', 'inviteMember', '2020-07-06 15:12:40', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4773, 'b075c37a9b474b958affc6eb32db8345', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 已完成', 'status', '2020-07-06 15:28:23', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4774, '35396a55b64a4d5bbb4ab227ce3e79c6', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-06 15:28:39', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4775, '066c68c9b98e43618eaff172547ebb4a', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-06 15:28:47', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4776, '3b5a9a3165264fc7bfea9acb29e1e159', '6v7be19pwman2fird04gqu53', '调研报告会议讨论', '创建了任务 ', 'create', '2020-07-06 15:42:14', '1b87f1d5642b4a549eeeffd4306d1b81', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4777, 'c242b8cad4924068b8e9b4e389e46ef8', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 15:42:14', '1b87f1d5642b4a549eeeffd4306d1b81', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4778, 'a7ced0e1ee0640aea4f3d83836bd25f0', '6v7be19pwman2fird04gqu53', '', '移除了参与者 张一', 'removeMember', '2020-07-06 15:43:52', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4779, '90973178f4a249dda174afe198cd7aac', '6v7be19pwman2fird04gqu53', '@王伟 ss', '@王伟 ss', 'comment', '2020-07-06 15:44:46', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 1, '8c4f887129e54068996e2d10a1c3bac9', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4780, '38df6829770647bc9257d143fa9bbe24', '6v7be19pwman2fird04gqu53', '资源报告审核\n', '创建了任务 ', 'create', '2020-07-06 15:54:17', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4781, 'f548ed5dca104d9d8d30c0aad6d89344', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 15:54:17', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4782, '759d76e5f706467fa29d8d54b375f75a', '6v7be19pwman2fird04gqu53', '资源报告实施', '创建了任务 ', 'create', '2020-07-06 15:55:01', '0375e80f2edf48e3835a8cd73a1532e1', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4783, '77b79b765a7c4174a132208d7a4fd414', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 15:55:01', '0375e80f2edf48e3835a8cd73a1532e1', 'task', '6d44c444965349ae926cd5be98912292', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4784, 'ee97587f9ced4e779f361f1ccc395367', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://120.27.62.173:80/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/de2106f61100473d934aa6d98dacffc3-测试文件-副本.docx&realFileName=测试文件-副本.docx \">\"测试文件-副本</a>', '上传了文件文件', 'uploadFile', '2020-07-06 16:48:21', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'link', 0);
INSERT INTO `team_project_log` VALUES (4785, '7c90adef28d1400689ce53a43a08cc66', '6v7be19pwman2fird04gqu53', '临时小组会议', '创建了任务 ', 'create', '2020-07-06 16:49:51', '59461dba170540c59679bff152de19a4', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4786, '801923fd208e47ff838e6ff4f1cac317', '6v7be19pwman2fird04gqu53', '', '添加了子任务 临时小组会议', 'createChild', '2020-07-06 16:49:51', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4787, 'e82e0130454042c088d85a3019f6af3c', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 16:49:51', '59461dba170540c59679bff152de19a4', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4788, '103132f90e094bc6abd09aa4b1b135d3', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-06 16:49:55', '59461dba170540c59679bff152de19a4', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4789, '2d26df95168449148d61311062399974', '6v7be19pwman2fird04gqu53', '', '完成了子任务 水资源调研报告编写\n', 'doneChild', '2020-07-06 16:49:55', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4790, 'c46d23f5bafd4e50a294afed4ba53527', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-06 16:49:59', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4791, '354035376b334adb9c1a8d0b28e044e3', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-06 16:50:05', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4792, '63d25609285640b4a1f5001de1530180', '6v7be19pwman2fird04gqu53', '张一的任务', '创建了任务 ', 'create', '2020-07-06 16:57:18', '46077d78315d443382eb86e44225e29a', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4793, '15aced1fd3ac411ab57b9ac353990b5e', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 16:57:18', '46077d78315d443382eb86e44225e29a', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4794, '35c42296faca427589e05c2f807af9d0', '6v7be19pwman2fird04gqu53', '水利部1号项目', '创建了项目', 'create', '2020-07-06 17:07:26', '3488bba47b8e48fc9cc75f5e5580cfb4', NULL, '0', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4795, 'a3960650ea264084b8a09112911b78e1', '6v7be19pwman2fird04gqu53', 'admin', '邀请admin加入项目', 'inviteMember', '2020-07-06 17:07:26', '3488bba47b8e48fc9cc75f5e5580cfb4', NULL, '6v7be19pwman2fird04gqu53', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4796, 'c47c0fcda62040169839c4affbcd898c', 'bddc79203627409e9928b290b952ee88', '张一', '邀请张一加入项目', 'inviteMember', '2020-07-06 17:07:48', '3488bba47b8e48fc9cc75f5e5580cfb4', NULL, 'bddc79203627409e9928b290b952ee88', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4797, '127edebb10ef477eb73e080f9e41ec00', '6d44c444965349ae926cd5be98912292', '王伟', '邀请王伟加入项目', 'inviteMember', '2020-07-06 17:07:49', '3488bba47b8e48fc9cc75f5e5580cfb4', NULL, '6d44c444965349ae926cd5be98912292', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4798, 'fd0394857e6c4154813bef8a77736012', '6v7be19pwman2fird04gqu53', '张一负责的任务', '创建了任务 ', 'create', '2020-07-06 17:08:04', '23e47dd7bcc54acebe0122bb2c939b76', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4799, '934eeda3aa0d4fdeaa0da12aeae01e96', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 17:08:04', '23e47dd7bcc54acebe0122bb2c939b76', 'task', 'bddc79203627409e9928b290b952ee88', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4800, '645afa2bcf01480abf28137312d8106e', 'bddc79203627409e9928b290b952ee88', '指给潘工的任务', '创建了任务 ', 'create', '2020-07-06 17:09:10', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4801, '2891f805bb22402ca78fbae6f58c8d16', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2020-07-06 17:09:10', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '6v7be19pwman2fird04gqu53', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4802, 'b4026ffc76974df09a5db407881b5040', 'bddc79203627409e9928b290b952ee88', '指给王伟的任务', '创建了任务 ', 'create', '2020-07-06 17:09:51', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4803, '2583e685115b453b980719cbc6e3bce7', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2020-07-06 17:09:51', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '6d44c444965349ae926cd5be98912292', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4804, 'f85dc248926343119d0bf2195eb5d345', 'bddc79203627409e9928b290b952ee88', '<h1>随风倒十分</h1>', '更新了备注 ', 'content', '2020-07-06 17:11:18', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4805, '39777f65f4b845dcae135a11155fa308', '6v7be19pwman2fird04gqu53', '测试任务', '创建了任务 ', 'create', '2020-07-06 17:20:30', '025fb21bea124f958b6dbe9f157ece00', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4806, '805537bdecd3411ab62ce25772016d6a', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 17:20:30', '025fb21bea124f958b6dbe9f157ece00', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4807, 'c6d2d3ddce2e43b09fca44a4fe5291b9', '6v7be19pwman2fird04gqu53', '', '添加了参与者 王伟', 'inviteMember', '2020-07-06 17:20:37', '025fb21bea124f958b6dbe9f157ece00', 'task', '6d44c444965349ae926cd5be98912292', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4808, 'b1a9234e000345a88a5517f0733e73cd', '6v7be19pwman2fird04gqu53', '', '添加了参与者 张一', 'inviteMember', '2020-07-06 17:20:49', '025fb21bea124f958b6dbe9f157ece00', 'task', 'bddc79203627409e9928b290b952ee88', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4809, 'b49fccb029584422961d722c08a491fc', 'bddc79203627409e9928b290b952ee88', '', '移除了参与者 王伟', 'removeMember', '2020-07-06 17:22:15', '025fb21bea124f958b6dbe9f157ece00', 'task', '6d44c444965349ae926cd5be98912292', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-delete', 0);
INSERT INTO `team_project_log` VALUES (4810, '3e24a73fe2a1403aadcf5b02df8323c5', 'bddc79203627409e9928b290b952ee88', '', '添加了参与者 王伟', 'inviteMember', '2020-07-06 17:22:21', '025fb21bea124f958b6dbe9f157ece00', 'task', '6d44c444965349ae926cd5be98912292', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4811, 'a52b87bfed3d430fad3a9251baefccc5', '6v7be19pwman2fird04gqu53', '测试附件', '创建了任务 ', 'create', '2020-07-06 17:24:08', '211affd25de14f988c92d5c7b84bdcb4', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4812, '8c10d8fa6d5443e288c88d8616c86844', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-06 17:24:08', '211affd25de14f988c92d5c7b84bdcb4', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4813, '27bd7bb6af234d52baf7ef5b15fcf6d7', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://120.27.62.173:80/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/f323dc2d06244163a1f6151af06f30a5-测试文件-副本.docx&realFileName=测试文件-副本.docx \">\"测试文件-副本</a>', '上传了文件文件', 'uploadFile', '2020-07-06 17:24:48', '211affd25de14f988c92d5c7b84bdcb4', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4814, '872e97e5923045c9a17cd32312f93232', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://120.27.62.173:80/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20200706/b7eb94cfd1344bf09bee348a9a000b0c-测试文件-副本 (2).docx&realFileName=测试文件-副本 (2).docx \">\"测试文件-副本 (2)</a>', '上传了文件文件', 'uploadFile', '2020-07-06 17:25:13', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'link', 0);
INSERT INTO `team_project_log` VALUES (4815, 'd5dae7ef37d04bbb983e9f74580caf6b', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 10:09:25', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4816, '549c439c7ba249e895c790c587e43a22', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 10:09:27', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4817, '420714d115e14d629b67994f5c3b3638', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 10:09:27', '025fb21bea124f958b6dbe9f157ece00', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4818, '0c938c6dce5244639b44412dfe2d0159', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 10:09:29', '211affd25de14f988c92d5c7b84bdcb4', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4819, '01c42984fb374c42aee5a1eb8330d05a', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 10:09:52', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4820, '72e43132935541c58bc63206bcb8ec69', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 10:09:53', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4821, '5f4c9894d1524ba285e68a548c52a8ba', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 测试中', 'status', '2020-07-07 21:39:58', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4822, '46d53c7e062d42f69cf8c7231a7976f4', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2020-07-10 18:00', 'setEndTime', '2020-07-07 21:40:08', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (4823, '9ab0940fb4eb48eb9da8a7e453d3df0d', '6v7be19pwman2fird04gqu53', '<p>test<br></p>', '更新了备注 ', 'content', '2020-07-07 21:40:19', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4824, '4bcff38a211d47c3844ca79146e9687e', '6v7be19pwman2fird04gqu53', '', '更新预估工时为 10', 'setWorkTime', '2020-07-07 21:41:20', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'clock-circle', 0);
INSERT INTO `team_project_log` VALUES (4825, 'e366798cd282453ab3ed365f8579db71', '6v7be19pwman2fird04gqu53', '', '清空了备注 ', 'clearContent', '2020-07-07 21:48:29', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4826, '462c62516a17421cb5643305c2946d53', '6v7be19pwman2fird04gqu53', '', '修改执行状态为 未开始', 'status', '2020-07-07 21:48:39', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'deployment-unit', 0);
INSERT INTO `team_project_log` VALUES (4827, '21a27a04410e4cc9a41fdcc17b47c45e', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 22:35:19', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4828, 'd836f47d57d94b19bf6ee4159de324aa', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 22:35:30', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4829, 'a9bfe8cc31d142609073c5bfb0273b77', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 22:35:43', '0375e80f2edf48e3835a8cd73a1532e1', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4830, '99b0fe847ed24d54949f646898492f8f', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 22:35:58', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4831, '15a00cf15bac46faa69a24c907025921', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 22:49:06', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4832, 'f73832c0d7d34a888710f9dde708af69', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 22:50:03', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4833, 'cf50abc0b2f14de59653ea3a58a63c04', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 22:50:39', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4834, '8dde514fd7084e94a43a0006023338dd', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 22:51:45', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4835, '52e34f280b32499cbaeabc99292ffbb8', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 22:55:05', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4836, 'a0fc2e5fa46d4d758175c55852a45e47', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 22:55:57', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4837, 'aee0a7281a19439aab9cf14f165cdf62', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 23:01:12', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4838, '5c39fb780f84400a9b8234e907484d55', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 23:10:59', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4839, '350a604f0c6f4a4e92e88418e5f99e2e', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 23:11:06', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4840, '23ae9253231d4134a7bc3a2a307e17e4', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 23:11:33', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4841, '74ce8eb77e2c4653a9e7d218642766b1', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 23:12:32', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4842, '925ff6651ee34e6e9343b06ce37bb781', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 23:13:42', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4843, 'fd8f45de398040bda7204ba55fbc7e92', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 23:14:55', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4844, 'afa2bce58ce7476db89d6cfd969499b3', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 23:28:17', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4845, 'e99a9239a8a84337b9327e42353e3890', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 23:30:18', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4846, '66b0fe06221648b6a0fe4494cb599b6e', '6v7be19pwman2fird04gqu53', '', '完成了任务 ', 'done', '2020-07-07 23:31:59', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'check', 0);
INSERT INTO `team_project_log` VALUES (4847, '71ce63c8161640cda0faff74c6ccd802', '6v7be19pwman2fird04gqu53', '', '重做了任务 ', 'redo', '2020-07-07 23:32:13', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'border', 0);
INSERT INTO `team_project_log` VALUES (4848, 'f349925c817e4b248e974a1d0e93bfb3', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:36:50', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4849, 'cc0d02aa902444dbb8711f2a6df750a0', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:36:50', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4850, '9644348a2b594d958c4cc336a7e3c495', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:36:50', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4851, 'b78cbcad34da47e4a4db75d2ebadbe0c', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:39:59', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4852, '2a4f053a9278479fa1ffada3f323c3ec', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:39:59', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4853, '048caf9994d94cc78f6362c255c176bd', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:39:59', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4854, '27dcc97127b14c2c9f320eb4de0aa771', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:47:32', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4855, 'd4a3b734c4ef44629c458370f208b17e', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:47:59', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4856, '839ed9c56a284575bbb60286e5f786d8', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:48:01', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4857, '2005cacb7d24425984fbb7876b01ebf0', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:48:01', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4858, '8f00b45c55a440fc834d75c238c30c0d', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:48:01', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4859, '49ce72e8f64b4c959f6ce99ee06e442c', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:48:02', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4860, '5e0bcf00257e48bd9629cf961704213c', '6v7be19pwman2fird04gqu53', '', '指派给了 王伟', 'assign', '2020-07-09 10:53:02', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', '6d44c444965349ae926cd5be98912292', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4861, '0b6a80edad994b2ebc2f3ba3f79f8358', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2020-07-09 10:53:06', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', '6d44c444965349ae926cd5be98912292', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4862, '3d49142f1dfd41d6a1a44a54876ca1cc', '6v7be19pwman2fird04gqu53', '', '指派给了 王伟', 'assign', '2020-07-09 10:53:09', '0375e80f2edf48e3835a8cd73a1532e1', 'task', '6d44c444965349ae926cd5be98912292', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4863, 'f92b944d3d4f4fa6a988eeb967d3ed14', '6v7be19pwman2fird04gqu53', '', '指派给了 李超', 'assign', '2020-07-09 10:55:53', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4864, 'd86505c93cd14a2186e3d99eec671960', '6v7be19pwman2fird04gqu53', '', '指派给了 李超', 'assign', '2020-07-09 10:55:53', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4865, '97e0c8268301448f9c8bbf342e6d8b83', '6v7be19pwman2fird04gqu53', '', '指派给了 李超', 'assign', '2020-07-09 10:55:54', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4866, 'f2ac6f7e66f4488188cbb07ab54ed223', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 10:59:20', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4867, 'e2e06dd761b84b81b0279d497543ef17', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 11:00:04', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4868, 'f3139ff35c314e8090d34b6e46a8fbaa', '6v7be19pwman2fird04gqu53', '', '指派给了 张一', 'assign', '2020-07-09 11:00:24', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'bddc79203627409e9928b290b952ee88', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4869, '6bb57430b44b41f0838fcc07fc659fc1', '6v7be19pwman2fird04gqu53', '', '指派给了 李超', 'assign', '2020-07-09 11:03:03', '8d4c1bde367f43ed9db2b7da13a059ae', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4870, '3fd80d9c7ded4322a4448dbf7009d4e7', '6v7be19pwman2fird04gqu53', '', '指派给了 李超', 'assign', '2020-07-09 11:03:47', 'da7a9009fae44bb6a701ffbc5bab7f6b', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4871, '1993c4a6aaa848a592eb63f5a6351bac', '6v7be19pwman2fird04gqu53', '', '指派给了 李超', 'assign', '2020-07-09 11:03:59', '0375e80f2edf48e3835a8cd73a1532e1', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4872, '88b95141e23545b7a84f5644b0ac7ed9', '6v7be19pwman2fird04gqu53', '@admin  赶紧 干', '@admin  赶紧 干', 'comment', '2020-07-11 17:52:31', 'dccf7dbf08fc4323bc124a948f6c6c74', 'task', '', 1, '3488bba47b8e48fc9cc75f5e5580cfb4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4873, '47c9c5fd63cb43aba9c69b0ac7c9e0b4', '6v7be19pwman2fird04gqu53', '@王伟  干啊', '@王伟  干啊', 'comment', '2020-07-11 17:53:47', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 1, '3488bba47b8e48fc9cc75f5e5580cfb4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4874, 'dc582274205e4afa9cd0d53b11d07206', '6v7be19pwman2fird04gqu53', '@王伟  111', '@王伟  111', 'comment', '2020-07-11 17:55:51', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 1, '3488bba47b8e48fc9cc75f5e5580cfb4', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (4875, 'a58e79c5e9c341cb8792f23d728fc337', 'bddc79203627409e9928b290b952ee88', '指给王伟的任务', '将任务移动到 任务分组1', 'move', '2021-02-01 11:17:01', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4876, '98a4ec8331ec4fa184314c66b327b436', 'bddc79203627409e9928b290b952ee88', '指给王伟的任务', '将任务移动到 任务分组3', 'move', '2021-02-01 11:17:13', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'drag', 0);
INSERT INTO `team_project_log` VALUES (4877, 'fc2bb3f97afa4640a66409261eaaea4b', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 11:19:22', '23e47dd7bcc54acebe0122bb2c939b76', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4878, 'cc37d21603fc4ea8baf22b0272b29530', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 11:19:24', '23e47dd7bcc54acebe0122bb2c939b76', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4879, '3a94adaed1ec45629c6c3e2c13381c28', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 11:19:25', '23e47dd7bcc54acebe0122bb2c939b76', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4880, 'a9934722eaea4101a16728dfdb13a31f', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 11:19:28', 'c3f30c32f31b46a8b2b413ffb7f742d9', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4881, 'f9f42cc838314490b65e0bd92db8fc92', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 11:19:30', '025fb21bea124f958b6dbe9f157ece00', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'border', 0);
INSERT INTO `team_project_log` VALUES (4882, '6efa9dc0325f4e649b861db98a6e1af0', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 11:19:31', '025fb21bea124f958b6dbe9f157ece00', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'check', 0);
INSERT INTO `team_project_log` VALUES (4883, 'f4e9dd0e06654a66b081d137f1fb5c86', 'bddc79203627409e9928b290b952ee88', '烧烤乐园', '创建了项目', 'create', '2021-02-01 11:54:03', '813a9c8b199e4dc59486cf6468ab91af', NULL, '0', 0, '813a9c8b199e4dc59486cf6468ab91af', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4884, '5b788c95d1364085880737a9b6290f3b', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 11:54:03', '813a9c8b199e4dc59486cf6468ab91af', NULL, 'bddc79203627409e9928b290b952ee88', 0, '813a9c8b199e4dc59486cf6468ab91af', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4885, '9cb28a21ac7e44f0af1d50a1b933a34e', 'bddc79203627409e9928b290b952ee88', '<a target=\"_blank\" class=\"muted\" href=\"http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210201/406abf3a73bd4baf8ca3d0fdb74126a5-kibana-7.10.2-x86_64.rpm.sha512&realFileName=kibana-7.10.2-x86_64.rpm.sha512 \">\"kibana-7.10.2-x86_64.rpm</a>', '上传了文件文件', 'uploadFile', '2021-02-01 14:20:01', '0', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'link', 0);
INSERT INTO `team_project_log` VALUES (4886, '86e3fb4a4fbe438f9177afc3d5f4b47d', 'bddc79203627409e9928b290b952ee88', '测试计划\n', '创建了任务 ', 'create', '2021-02-01 14:30:03', '828f9ad33dc84aa4b878f74fa74317dd', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4887, '97483e84f4694115ae2cd55f0cc77aed', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 14:30:04', '828f9ad33dc84aa4b878f74fa74317dd', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4888, 'fd824d1d1f4942aea458684449e22362', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 15:09:21', '828f9ad33dc84aa4b878f74fa74317dd', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'check', 0);
INSERT INTO `team_project_log` VALUES (4889, 'da15c52df81440e2bfade6bd53215f6a', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 15:09:30', '828f9ad33dc84aa4b878f74fa74317dd', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'border', 0);
INSERT INTO `team_project_log` VALUES (4890, '503d2ed5ecc1469d874356d11dd638e3', 'bddc79203627409e9928b290b952ee88', '测试任务', '创建了任务 ', 'create', '2021-02-01 15:09:39', 'be6698b6bdcc4cf7a87e2a8338eccabb', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4891, 'd46a7514b2a2459a9ae8d09bfcd200ac', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 15:09:39', 'be6698b6bdcc4cf7a87e2a8338eccabb', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4892, '587a77e0975b467e9ad0cb89931be23a', 'bddc79203627409e9928b290b952ee88', '11232', '创建了项目', 'create', '2021-02-01 15:44:04', '4d619572638b41b29e4e9d9b93c0fc6e', NULL, '0', 0, '4d619572638b41b29e4e9d9b93c0fc6e', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4893, '4b451a23420e45dbab1900c11780f426', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 15:44:04', '4d619572638b41b29e4e9d9b93c0fc6e', NULL, 'bddc79203627409e9928b290b952ee88', 0, '4d619572638b41b29e4e9d9b93c0fc6e', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4894, '00ed637722e84ab38031225336730fc0', 'bddc79203627409e9928b290b952ee88', '1111', '创建了任务 ', 'create', '2021-02-01 15:45:50', '37c256be26a24d86b4b4f3c9894b8eda', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4895, 'b8af7f4904554d9eb66cb84d0ee52a91', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 15:45:50', '37c256be26a24d86b4b4f3c9894b8eda', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4896, 'a3d43d1ee3eb4dfca35866aa8f3df302', 'bddc79203627409e9928b290b952ee88', '六盘水水城项目', '创建了项目', 'create', '2021-02-01 16:07:02', '4f2619f1d1c64a389a2ba671e7fca2ed', NULL, '0', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4897, 'd795a2dc6cac44699107b155c295e77d', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 16:07:02', '4f2619f1d1c64a389a2ba671e7fca2ed', NULL, 'bddc79203627409e9928b290b952ee88', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4898, '6f998a50c9494a4b83c6f71b45065624', 'bddc79203627409e9928b290b952ee88', 'SSM', '创建了项目', 'create', '2021-02-01 16:42:39', 'd0e82a94783a4ee7881f6c342f990bc5', NULL, '0', 0, 'd0e82a94783a4ee7881f6c342f990bc5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4899, '9eeec8785fe04495bf1ddcec451787a5', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 16:42:39', 'd0e82a94783a4ee7881f6c342f990bc5', NULL, 'bddc79203627409e9928b290b952ee88', 0, 'd0e82a94783a4ee7881f6c342f990bc5', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4900, 'c11a5c38d40644af8379608b82cf0362', 'bddc79203627409e9928b290b952ee88', 'aaaa', '创建了任务 ', 'create', '2021-02-01 16:42:47', '648b0d92913b4238b532a1ccfc46c534', 'task', '', 0, 'd0e82a94783a4ee7881f6c342f990bc5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4901, '810d8584823a4017acf21128b6744ff3', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 16:42:47', '648b0d92913b4238b532a1ccfc46c534', 'task', '', 0, 'd0e82a94783a4ee7881f6c342f990bc5', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4902, '9216ec9f0dea4c98b897f36b0031612b', 'bddc79203627409e9928b290b952ee88', 'dff', '创建了任务 ', 'create', '2021-02-01 16:42:51', '3b65628ca15c41669c2b6bfb885d953d', 'task', '', 0, 'd0e82a94783a4ee7881f6c342f990bc5', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4903, 'dcab00318cfb457482310871ec1cf569', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 16:42:51', '3b65628ca15c41669c2b6bfb885d953d', 'task', '', 0, 'd0e82a94783a4ee7881f6c342f990bc5', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4904, '6d27f7f6ca1941e48cfe7fced2571ffb', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 17:37:48', '37c256be26a24d86b4b4f3c9894b8eda', 'task', '', 0, '813a9c8b199e4dc59486cf6468ab91af', 'check', 0);
INSERT INTO `team_project_log` VALUES (4909, '4b337404194d419b8a8136029fe0ca70', 'bddc79203627409e9928b290b952ee88', 'test11', '创建了项目', 'create', '2021-02-01 18:25:44', '81d43786f82c4c84baf34c6eb9ecac78', NULL, '0', 0, '81d43786f82c4c84baf34c6eb9ecac78', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4910, '374441b4c4a5466d81c35fd20a77a0ff', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 18:25:44', '81d43786f82c4c84baf34c6eb9ecac78', NULL, 'bddc79203627409e9928b290b952ee88', 0, '81d43786f82c4c84baf34c6eb9ecac78', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4911, '52e70b3bdb2b4021b4cffb0d51e2f435', 'bddc79203627409e9928b290b952ee88', '校车安全管理平台项目', '创建了项目', 'create', '2021-02-01 21:33:17', 'a3c56e1116ff42b780fa8c16afc76311', NULL, '0', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4912, '41742b088a094786b45a7be40f2a45ae', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 21:33:17', 'a3c56e1116ff42b780fa8c16afc76311', NULL, 'bddc79203627409e9928b290b952ee88', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4913, '4dd18edf919c4eb48530273d004ffb9a', 'bddc79203627409e9928b290b952ee88', '需求规格说明书', '创建了任务 ', 'create', '2021-02-01 21:35:35', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4914, 'f1e8c872a13d45d5a46c8f6e38e487a3', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:35:35', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4915, 'fa137bd36611462c8d42601d91f72c39', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 21:39:57', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4916, 'af52d2044e1049ae9c96270d574f64dd', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 21:39:59', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'border', 0);
INSERT INTO `team_project_log` VALUES (4917, '79e6647df88f4be0bca9ace7ce9242e3', 'bddc79203627409e9928b290b952ee88', '概要设计说明书', '创建了任务 ', 'create', '2021-02-01 21:42:06', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4918, '3c89bf0eba8143c8b7f6b8854e60d6c9', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:42:06', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4919, '4f8ca016cba9487896b96490e12e19b0', 'bddc79203627409e9928b290b952ee88', '接口设计说明书', '创建了任务 ', 'create', '2021-02-01 21:42:19', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4920, 'c4270f16537b47cba8f72d9f2a646c85', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:42:19', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4921, '8f0dd7af0b7a4413b070ef9da6671682', 'bddc79203627409e9928b290b952ee88', '数据库设计说明书', '创建了任务 ', 'create', '2021-02-01 21:43:02', 'ec92afe944414c20a3ce30f5ed74cc7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4922, '23fddcc0458f45638929c6ed6833e663', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:43:02', 'ec92afe944414c20a3ce30f5ed74cc7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4923, '56970cfbb434484ab2daf7fc20936f4a', 'bddc79203627409e9928b290b952ee88', '详细设计说明书', '创建了任务 ', 'create', '2021-02-01 21:43:16', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4924, 'baaacdcd34964d979c3b271a824ac84a', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:43:16', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4925, '10393e28a5be41ffa2908ece442f4697', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-完成', '更新了内容 ', 'name', '2021-02-01 21:44:27', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4926, '05c4f00feec94fc4b1b9f1906325eb1f', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-内部审核完毕', '更新了内容 ', 'name', '2021-02-01 21:45:16', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4927, '0ca77b074dfa4b00a22b90cec6a8146b', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-监理审核完毕', '更新了内容 ', 'name', '2021-02-01 21:45:43', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4928, '285effc2f7af430ba0027f8c496b0c27', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-定稿', '更新了内容 ', 'name', '2021-02-01 21:47:23', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4929, '75a0f7928e1148239c2b165c42bb6119', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-内部审核完毕-2020-12-29', '更新了内容 ', 'name', '2021-02-01 21:48:16', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4930, '266ef9cdbf7849a5a8dfd586904835ed', 'bddc79203627409e9928b290b952ee88', '内审', '创建了任务 ', 'create', '2021-02-01 21:48:59', 'a1fcd15b001a42f9bbf2d33b75cd28a5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4931, '8a6e873a6eba40fcb30a5cb14339a6bb', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 内审', 'createChild', '2021-02-01 21:48:59', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4932, '164910911c8642159c8ddfe4b80ebe81', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:48:59', 'a1fcd15b001a42f9bbf2d33b75cd28a5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4933, '097bae2d4b2c4fdfbe7736f75b552b15', 'bddc79203627409e9928b290b952ee88', '老万审', '创建了任务 ', 'create', '2021-02-01 21:49:11', '20ca7ed884d54d2291f172fd6ff1c0c1', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4934, '38a134d93b0048b2a96fd89bf801930a', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 老万审', 'createChild', '2021-02-01 21:49:11', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4935, '103b28732dee4152a4ce1032fc6a9d67', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:49:11', '20ca7ed884d54d2291f172fd6ff1c0c1', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4936, 'ccb183534421459497fc45a4beea3b56', 'bddc79203627409e9928b290b952ee88', '1', '创建了任务 ', 'create', '2021-02-01 21:49:32', 'b98123b2f95741b9a595882050c75a3c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4937, 'c7f22c97a2f84da8ac1d768cbf52ba9f', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 1', 'createChild', '2021-02-01 21:49:32', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4938, '172bb080f993427b80c50007a9b1754b', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:49:32', 'b98123b2f95741b9a595882050c75a3c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4939, 'edc2ba45ea8749dcb4ea4a353a811d49', 'bddc79203627409e9928b290b952ee88', '2', '创建了任务 ', 'create', '2021-02-01 21:49:35', 'cfba366ab35b43e794862690faa5c8df', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4940, '090fcfcb8b30466a992d748dde022f44', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 2', 'createChild', '2021-02-01 21:49:35', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4941, '02e90e30e78e4893a63c48710aa67e68', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:49:35', 'cfba366ab35b43e794862690faa5c8df', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4942, 'cd3a8d24396f4311bdfff97bd9dfc10b', 'bddc79203627409e9928b290b952ee88', '1', '创建了任务 ', 'create', '2021-02-01 21:49:44', 'f5b8d37bbf2a4be091f8a4899ddc051a', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4943, 'b37104ebc96e4d80b4115c42d7a47991', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 1', 'createChild', '2021-02-01 21:49:44', 'a1fcd15b001a42f9bbf2d33b75cd28a5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4944, 'aa6b524219c84affbc35432d7d5cbda9', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 21:49:44', 'f5b8d37bbf2a4be091f8a4899ddc051a', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4945, '7f4a8b8cb3c648e7b3894bc24fb7f426', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-已定稿', '更新了内容 ', 'name', '2021-02-01 21:51:09', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4946, '8eba2f2069c84e5b809434d8102438d4', 'bddc79203627409e9928b290b952ee88', '概要设计-已定稿', '更新了内容 ', 'name', '2021-02-01 21:51:35', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4947, '130a216addaa453c999eeae22c4905a5', 'bddc79203627409e9928b290b952ee88', '需求规格说明书-未定稿', '更新了内容 ', 'name', '2021-02-01 21:51:50', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4948, 'f3bc28b8298947f89b55f831e92809eb', 'bddc79203627409e9928b290b952ee88', '详细设计-未定稿', '更新了内容 ', 'name', '2021-02-01 21:52:41', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4949, '14cac9b8d3944080ab1d2758629288a1', 'bddc79203627409e9928b290b952ee88', '数据库设计说明书-未定稿', '更新了内容 ', 'name', '2021-02-01 21:52:51', 'ec92afe944414c20a3ce30f5ed74cc7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4950, '1bea150a00254cafaea963ddfbb7063f', 'bddc79203627409e9928b290b952ee88', '统一招生平台', '创建了项目', 'create', '2021-02-01 21:55:31', 'd99cbb2012ce413bab0cde5866fa5041', NULL, '0', 0, 'd99cbb2012ce413bab0cde5866fa5041', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4951, 'd5a748b3ed974872ac0b674ddcf6cc8a', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 21:55:31', 'd99cbb2012ce413bab0cde5866fa5041', NULL, 'bddc79203627409e9928b290b952ee88', 0, 'd99cbb2012ce413bab0cde5866fa5041', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4952, '68d23d91bd7244d0ab0bcf736a8308ec', 'bddc79203627409e9928b290b952ee88', '东莞市义务教育阶段学校统一招生平台', '创建了项目', 'create', '2021-02-01 21:56:16', '9749247d6ae44e8abeadac35d9f46ba0', NULL, '0', 0, '9749247d6ae44e8abeadac35d9f46ba0', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4953, '98a54e5cdb084dc8932df7b90570aa2a', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 21:56:16', '9749247d6ae44e8abeadac35d9f46ba0', NULL, 'bddc79203627409e9928b290b952ee88', 0, '9749247d6ae44e8abeadac35d9f46ba0', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4954, 'f5e101f77b234672933ed21cdac216ab', 'bddc79203627409e9928b290b952ee88', '电子健康码管理平台项目', '创建了项目', 'create', '2021-02-01 21:56:41', 'd2076c5f961b4bfe915906cbd4c773d3', NULL, '0', 0, 'd2076c5f961b4bfe915906cbd4c773d3', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4955, 'dc30c581d9594a20a9cd0637b0a5a1de', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-01 21:56:41', 'd2076c5f961b4bfe915906cbd4c773d3', NULL, 'bddc79203627409e9928b290b952ee88', 0, 'd2076c5f961b4bfe915906cbd4c773d3', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (4956, '5e1277cea16b416997751d4c80e609fe', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 21:57:11', 'cfba366ab35b43e794862690faa5c8df', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4957, 'b21398c1094b4901808638d168bed33b', 'bddc79203627409e9928b290b952ee88', '', '完成了子任务 需求规格说明书-已定稿', 'doneChild', '2021-02-01 21:57:11', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4958, 'fd41c1e9292e4ad3ad6f8be98df63799', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 21:57:11', 'b98123b2f95741b9a595882050c75a3c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4959, '67f245323aa048af8b37eafeed32a1c9', 'bddc79203627409e9928b290b952ee88', '', '完成了子任务 需求规格说明书-已定稿', 'doneChild', '2021-02-01 21:57:11', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4960, '03721dc1108d4611944eb663f9b9d335', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 21:57:12', '20ca7ed884d54d2291f172fd6ff1c0c1', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4961, '1a61c7e3eb4240e682a644d2f8689adc', 'bddc79203627409e9928b290b952ee88', '', '完成了子任务 需求规格说明书-已定稿', 'doneChild', '2021-02-01 21:57:12', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4962, '94c68c3ddaf7437e96687c5fd95a3a90', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 22:02:00', 'f5b8d37bbf2a4be091f8a4899ddc051a', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4963, '6d355ee7a3c44f4c936629ec9e6cbbe2', 'bddc79203627409e9928b290b952ee88', '', '完成了子任务 内审', 'doneChild', '2021-02-01 22:02:00', 'a1fcd15b001a42f9bbf2d33b75cd28a5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4964, 'c4ca5b6ebbbd4c90b14086ad23e8315a', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 22:02:07', 'a1fcd15b001a42f9bbf2d33b75cd28a5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4965, '0f5693fd319241d7b9b1845825b5e3fa', 'bddc79203627409e9928b290b952ee88', '', '完成了子任务 需求规格说明书-已定稿', 'doneChild', '2021-02-01 22:02:07', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'bars', 0);
INSERT INTO `team_project_log` VALUES (4966, '7db6451b777140c78aba41955db66383', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 22:02:12', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4967, '9576ca4917a846b981fadc3366c0c92d', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 22:02:14', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4968, '29f1ca70c5584406be194078fc8a10fd', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 22:02:44', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'border', 0);
INSERT INTO `team_project_log` VALUES (4969, 'd30508a5ce2643f7acb17ad0d8097b5f', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 22:02:45', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'border', 0);
INSERT INTO `team_project_log` VALUES (4970, '0811c736b4094890b6edf498ed5fbbd1', 'bddc79203627409e9928b290b952ee88', '说明书', '更新了内容 ', 'name', '2021-02-01 22:03:14', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4971, 'f863bf0d217b4f5ca0690d0dc85d91cc', 'bddc79203627409e9928b290b952ee88', '接口设计说明书', '更新了内容 ', 'name', '2021-02-01 22:03:24', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4972, '5a5444c10e914353b50944d88564328f', 'bddc79203627409e9928b290b952ee88', '接口设计说明书-未定稿', '更新了内容 ', 'name', '2021-02-01 22:03:34', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4973, '18449b66ea0e424ab3a0f385d2cbe84d', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 22:03:41', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4974, '560712731ae340e7ac708955dd02a833', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-01 22:03:43', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'check', 0);
INSERT INTO `team_project_log` VALUES (4975, '0411c846f0d84a33b2a95fa4e433c5bc', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 22:04:01', '6d8862e580b44bec82bdb373b66172ce', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'border', 0);
INSERT INTO `team_project_log` VALUES (4976, 'c87d42ef048f49b69ce98f531e773650', 'bddc79203627409e9928b290b952ee88', '', '重做了任务 ', 'redo', '2021-02-01 22:04:03', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'border', 0);
INSERT INTO `team_project_log` VALUES (4977, 'bbf306aa155e4eaea66750acbcac595e', 'bddc79203627409e9928b290b952ee88', '详细设计-已定稿', '更新了内容 ', 'name', '2021-02-01 22:06:47', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4978, 'db254d58945043929a53bb9addc9606a', 'bddc79203627409e9928b290b952ee88', '数据库设计说明书-定稿', '更新了内容 ', 'name', '2021-02-01 22:06:53', 'ec92afe944414c20a3ce30f5ed74cc7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4979, '9133002f415148e692ebd62af67c4085', 'bddc79203627409e9928b290b952ee88', '接口设计说明书-已定稿', '更新了内容 ', 'name', '2021-02-01 22:07:04', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4980, 'd6db781c0727402993a887d804be9933', 'bddc79203627409e9928b290b952ee88', '数据库设计说明书-已定稿', '更新了内容 ', 'name', '2021-02-01 22:07:15', 'ec92afe944414c20a3ce30f5ed74cc7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4981, 'd5a87b367f5e44ca8f74994401795bab', 'bddc79203627409e9928b290b952ee88', '概要设计说明书-已定稿', '更新了内容 ', 'name', '2021-02-01 22:09:30', '730bbbcb16d048faaed58417537e9c2c', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4982, '99d5b955c1874ec8b094cb77cb295ec6', 'bddc79203627409e9928b290b952ee88', '详细设计说明书-已定稿', '更新了内容 ', 'name', '2021-02-01 22:09:42', '69523f0f56d14b58a657d15f3e8e8e6d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4983, '81b58a56714f4959b8de1d264432961d', 'bddc79203627409e9928b290b952ee88', '软件测试报告-未定稿', '创建了任务 ', 'create', '2021-02-01 22:10:02', '32632e1ca4c2461dac80d6a32d1a4c44', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4984, 'b8ca9d8f17264c86a6d61a39e02cb236', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:10:02', '32632e1ca4c2461dac80d6a32d1a4c44', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4985, '3f5aa913db2641f2b4fa4d8b826ed0e5', 'bddc79203627409e9928b290b952ee88', '集成部署方案-已定稿', '创建了任务 ', 'create', '2021-02-01 22:14:51', '1a4770cb15434abd8506a3a476ba0584', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4986, 'b615f9b793114ba485fe6535186b09e9', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:14:51', '1a4770cb15434abd8506a3a476ba0584', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4987, '573e04e4bde44df0bae45751955b2dbb', 'bddc79203627409e9928b290b952ee88', '软件测试方案-未定稿', '更新了内容 ', 'name', '2021-02-01 22:15:04', '32632e1ca4c2461dac80d6a32d1a4c44', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4988, '6e684da45fb944689e41c36367e68758', 'bddc79203627409e9928b290b952ee88', '项目计划', '创建了任务 ', 'create', '2021-02-01 22:19:02', '7b3e52cd6d1e4ef3a056a6775ed6cea8', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4989, 'a6fd9187c2754fc082cc03a1c95374cc', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:19:02', '7b3e52cd6d1e4ef3a056a6775ed6cea8', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4990, '994934d3c2b846b29c624d3686686b61', 'bddc79203627409e9928b290b952ee88', '项目计划-已定稿', '更新了内容 ', 'name', '2021-02-01 22:19:42', '7b3e52cd6d1e4ef3a056a6775ed6cea8', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (4991, 'a0dc6df4ecbd4a718e28458ba46dbedf', 'bddc79203627409e9928b290b952ee88', '培训方案-未定稿', '创建了任务 ', 'create', '2021-02-01 22:20:24', '9aa4adad0ff34ed793bf83d57e898060', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4992, '658ef22964784e7cb17a512e2755c083', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:20:24', '9aa4adad0ff34ed793bf83d57e898060', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4993, '1ca168cbf87541ce98bcd597f6288e6b', 'bddc79203627409e9928b290b952ee88', '试运行方案-未定稿', '创建了任务 ', 'create', '2021-02-01 22:20:46', '122409550ae4486c9935d93e8b923e7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4994, '6e9b9979003643beaf8da4dd641d8dea', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:20:46', '122409550ae4486c9935d93e8b923e7d', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4995, '6b04eab4538741faa23e9ad90abb1f48', 'bddc79203627409e9928b290b952ee88', '测试记录', '创建了任务 ', 'create', '2021-02-01 22:22:01', 'ed51394ab89642e6b7367b8beb21ae28', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4996, '716a10b510824bac8fb4015b775ca00a', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:22:01', 'ed51394ab89642e6b7367b8beb21ae28', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4997, '8d171b4e75f549f5962de95e5f3b9be3', 'bddc79203627409e9928b290b952ee88', '测试记录', '创建了任务 ', 'create', '2021-02-01 22:22:03', 'b124f39bc9f14d3bb3c186a9a63d3991', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (4998, 'a30d894f8db34ccebbe1e2a65f78fd36', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:22:03', 'b124f39bc9f14d3bb3c186a9a63d3991', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (4999, '25e0a2635fbb482481fc8a33b5ff2c98', 'bddc79203627409e9928b290b952ee88', '测试记录', '创建了任务 ', 'create', '2021-02-01 22:22:05', 'dcbaadbebcfe46fd9f77cb6ad0656cb5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5000, 'f71735cb2b044ad784246616a8f4a812', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-01 22:22:05', 'dcbaadbebcfe46fd9f77cb6ad0656cb5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5001, '4facc04dd40448428eab54ef37461807', 'bddc79203627409e9928b290b952ee88', '测试报告', '更新了内容 ', 'name', '2021-02-01 22:22:20', 'b124f39bc9f14d3bb3c186a9a63d3991', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (5002, '7477675fba544561aebd6d85be6b5aa7', 'bddc79203627409e9928b290b952ee88', '', '把任务移到了回收站 ', 'recycle', '2021-02-01 22:23:30', 'dcbaadbebcfe46fd9f77cb6ad0656cb5', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'delete', 0);
INSERT INTO `team_project_log` VALUES (5003, '1ffb74bb8ab047e184deaeb93f28e0eb', 'bddc79203627409e9928b290b952ee88', '测试记录-已定稿', '更新了内容 ', 'name', '2021-02-01 22:23:52', 'ed51394ab89642e6b7367b8beb21ae28', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (5004, '9ef47224f6e34999951990f51f392bdc', 'bddc79203627409e9928b290b952ee88', '测试报告-已定稿', '更新了内容 ', 'name', '2021-02-01 22:24:12', 'b124f39bc9f14d3bb3c186a9a63d3991', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'edit', 0);
INSERT INTO `team_project_log` VALUES (5013, '236175fcfd46427d85b3a84fa02e4fa3', 'bddc79203627409e9928b290b952ee88', '添加', '创建了任务 ', 'create', '2021-02-02 00:10:00', 'cc168a8cb4424f16983cbff00d464806', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5014, '07d08c8f1f4d4b40b351d8e547a5ee9b', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 00:10:00', 'cc168a8cb4424f16983cbff00d464806', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5015, 'cba4decae31a499f97c0773371bc199f', 'bddc79203627409e9928b290b952ee88', '1', '创建了任务 ', 'create', '2021-02-02 00:10:06', '056556ee2e35482688a9c19ba88420f5', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5016, 'e7f921ab8ce44e72bf02f3e3759fa377', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 1', 'createChild', '2021-02-02 00:10:06', 'cc168a8cb4424f16983cbff00d464806', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'bars', 0);
INSERT INTO `team_project_log` VALUES (5017, '71b3605b0a714b21844eb44bd89e71f7', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 00:10:06', '056556ee2e35482688a9c19ba88420f5', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5018, '4aaf70995b184fe58b1cb0ff86b65ecd', 'bddc79203627409e9928b290b952ee88', '2', '创建了任务 ', 'create', '2021-02-02 00:10:08', '36f5bebf257240e5848190fa213f99cf', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5019, '3887d52ad1ef45a1ac530c952faa2314', 'bddc79203627409e9928b290b952ee88', '', '添加了子任务 2', 'createChild', '2021-02-02 00:10:08', 'cc168a8cb4424f16983cbff00d464806', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'bars', 0);
INSERT INTO `team_project_log` VALUES (5020, '551392c2320644039bb32e185fd091dd', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 00:10:08', '36f5bebf257240e5848190fa213f99cf', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5021, '27ac940fe22d4ccea04ad057562e9de1', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 00:10:14', '056556ee2e35482688a9c19ba88420f5', 'task', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5022, 'c85637b547424a869ece5aae1a3b7a95', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 00:10:26', '36f5bebf257240e5848190fa213f99cf', 'task', '6v7be19pwman2fird04gqu53', 0, '8c4f887129e54068996e2d10a1c3bac9', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5030, 'f7e754c52a234a189545675a00f30d32', 'bddc79203627409e9928b290b952ee88', '1231', '创建了项目', 'create', '2021-02-02 08:41:06', '88435aab6cad403788275282852d61a6', NULL, '0', 0, '88435aab6cad403788275282852d61a6', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5031, 'd6310c9a5b51449e9fcec972c57a9aca', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-02 08:41:06', '88435aab6cad403788275282852d61a6', NULL, 'bddc79203627409e9928b290b952ee88', 0, '88435aab6cad403788275282852d61a6', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (5040, '986f3bc84c404ba49eb3560545f149e1', '6v7be19pwman2fird04gqu53', 'test', '创建了项目', 'create', '2021-02-02 10:52:50', 'f35e6d3ef3e04b5bb532039947b30eae', NULL, '0', 0, 'f35e6d3ef3e04b5bb532039947b30eae', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5041, 'ed5d308c81f0423fb499776eb25af5f1', '6v7be19pwman2fird04gqu53', 'admin', '邀请admin加入项目', 'inviteMember', '2021-02-02 10:52:50', 'f35e6d3ef3e04b5bb532039947b30eae', NULL, '6v7be19pwman2fird04gqu53', 0, 'f35e6d3ef3e04b5bb532039947b30eae', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (5042, 'fcb3cb052ccf4b8091f136320a915311', 'bddc79203627409e9928b290b952ee88', '', '更新任务优先级为 非常紧急', 'pri', '2021-02-02 11:26:39', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'user', 0);
INSERT INTO `team_project_log` VALUES (5043, '1665ddbf83c84329958d6d8802680a86', 'bddc79203627409e9928b290b952ee88', '', '更新截止时间为 2021-02-19 18:00', 'setEndTime', '2021-02-02 11:29:11', '7dc3192213db45dbb49bb62e8d92aacd', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (5044, '950b54171f5542edbf9ea0ae33ecf913', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/d91430238ef74c12ac650e568a9d7f17-tomcatgo.jpg&realFileName=tomcatgo.jpg \">\"tomcatgo</a>', '上传了文件文件', 'uploadFile', '2021-02-02 13:35:12', '0', 'task', '', 0, '3488bba47b8e48fc9cc75f5e5580cfb4', 'link', 0);
INSERT INTO `team_project_log` VALUES (5045, '1dff44b2a07e4a549eb950d6c02815fe', 'bddc79203627409e9928b290b952ee88', '111', '创建了任务 ', 'create', '2021-02-02 15:00:36', '7155d6a6b6954cfda9a9175385346d4f', 'task', '', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5046, 'b3af4e7970b54160ae7d69bb2a8aaa86', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 15:00:36', '7155d6a6b6954cfda9a9175385346d4f', 'task', '', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5047, 'c9ee10d7245844f0a077fdadb3e8266f', 'bddc79203627409e9928b290b952ee88', '', '完成了任务 ', 'done', '2021-02-02 15:00:58', '7155d6a6b6954cfda9a9175385346d4f', 'task', '', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'check', 0);
INSERT INTO `team_project_log` VALUES (5048, '257945152aa14116bdf3e49e622ed944', 'bddc79203627409e9928b290b952ee88', '111', '将任务移动到 进行中', 'move', '2021-02-02 15:01:00', '7155d6a6b6954cfda9a9175385346d4f', 'task', '', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'drag', 0);
INSERT INTO `team_project_log` VALUES (5049, '75bc17f3e11149b389f2e1537464f121', 'bddc79203627409e9928b290b952ee88', '', '更新截止时间为 2021-02-19 18:00', 'setEndTime', '2021-02-02 15:02:20', '7155d6a6b6954cfda9a9175385346d4f', 'task', '', 0, '4f2619f1d1c64a389a2ba671e7fca2ed', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (5050, '377c1cd5b19549d2b4b2eceb963ed195', '6v7be19pwman2fird04gqu53', 'wuliang', '创建了项目', 'create', '2021-02-02 16:29:52', '0ecde82e3bed4789a8e201621ba79d9f', NULL, '0', 0, '0ecde82e3bed4789a8e201621ba79d9f', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5051, '6ec3e1b0762e433cbed90bc13c6e88d1', '6v7be19pwman2fird04gqu53', 'admin', '邀请admin加入项目', 'inviteMember', '2021-02-02 16:29:52', '0ecde82e3bed4789a8e201621ba79d9f', NULL, '6v7be19pwman2fird04gqu53', 0, '0ecde82e3bed4789a8e201621ba79d9f', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (5052, '1aee109c05b04fda99c1e87f297707aa', '6v7be19pwman2fird04gqu53', '1111', '创建了项目', 'create', '2021-02-02 17:03:04', '74228c6bfd6040d3b00f934cd0b0014f', NULL, '0', 0, '74228c6bfd6040d3b00f934cd0b0014f', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5053, '0c97e974335848b1a28a67ecb39d1c33', '6v7be19pwman2fird04gqu53', 'admin', '邀请admin加入项目', 'inviteMember', '2021-02-02 17:03:04', '74228c6bfd6040d3b00f934cd0b0014f', NULL, '6v7be19pwman2fird04gqu53', 0, '74228c6bfd6040d3b00f934cd0b0014f', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (5054, '5c93adc333ab47b1bc73cb5be5af1212', '6v7be19pwman2fird04gqu53', '1111', '创建了项目', 'create', '2021-02-02 17:06:18', 'cf50726e99d746fa88107ed355d4a8cc', NULL, '0', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5055, '397ea4880c64497fa452fce245a6bc44', '6v7be19pwman2fird04gqu53', 'admin', '邀请admin加入项目', 'inviteMember', '2021-02-02 17:06:18', 'cf50726e99d746fa88107ed355d4a8cc', NULL, '6v7be19pwman2fird04gqu53', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'user-add', 0);
INSERT INTO `team_project_log` VALUES (5056, '4e2ad24cf4664537a6ab848bab245eba', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/072539ed82074083b4ef1f0824b7b7a9-tomcatgo.jpg&realFileName=tomcatgo.jpg \">\"tomcatgo</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:09:31', '0', 'task', '', 0, '8c4f887129e54068996e2d10a1c3bac9', 'link', 0);
INSERT INTO `team_project_log` VALUES (5057, '188967ef358a45dfa9e8f9aa738e1e0a', 'bddc79203627409e9928b290b952ee88', '11', '创建了任务 ', 'create', '2021-02-02 17:09:57', '62086de33087479885e9a54f8a22808e', 'task', '', 0, '88435aab6cad403788275282852d61a6', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5058, 'a0e4ac36822442c4876325f849f4fe89', 'bddc79203627409e9928b290b952ee88', '', '认领了任务 ', 'claim', '2021-02-02 17:09:57', '62086de33087479885e9a54f8a22808e', 'task', '', 0, '88435aab6cad403788275282852d61a6', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5059, '166461ed8f554316a4216e4b808bad49', 'bddc79203627409e9928b290b952ee88', '<a target=\"_blank\" class=\"muted\" href=\"http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/7f3fde339f284565b65932c2058a0ff3-u=1689053532,4230915864&fm=26&gp=0.jpg&realFileName=u=1689053532,4230915864&fm=26&gp=0.jpg \">\"u=1689053532,4230915864&fm=26&gp=0</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:10:16', '62086de33087479885e9a54f8a22808e', 'task', '', 0, '88435aab6cad403788275282852d61a6', 'link', 0);
INSERT INTO `team_project_log` VALUES (5060, '76e350f35ec94f01b7b9279b542ef889', '6v7be19pwman2fird04gqu53', '秀梅', '创建了任务 ', 'create', '2021-02-02 17:12:47', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5061, 'fa5051728c674613ab69236be75a357f', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2021-02-02 17:12:48', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5062, '5160602a6b3548babf289a3e255227fb', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/31b336c69b8349248843adfe92f00a2f-屏幕截图(1).png&realFileName=屏幕截图(1).png \">\"屏幕截图(1)</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:14:48', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5063, '24b90daca81e49f393c2eb3135e85690', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/3c85fb68230a4cf68cfb4f6f95ff0908-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf \">\"智慧餐厅完整解决方案</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:16:02', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5064, '9315a36a3a6c47abbb18e958edfc1013', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/adf1f8a2612f463b985216b518578a20-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf \">\"智慧餐厅完整解决方案</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:16:02', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5065, '3ea71448f3a84914b2149ca941492791', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/4c980c0219ef419bbd68004ee164ea94-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf \">\"智慧餐厅完整解决方案</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:16:03', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5066, '9de69bdddaef4c7998d48e916d28e17e', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/4cb37d06008e43fd9e9421a70f190768-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf \">\"智慧餐厅完整解决方案</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:16:03', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5067, '8c35b1ea9f1842a99bb43a60c0298d96', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/cc7d1897a98b4b6ca00c67c625b21bd7-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf \">\"智慧餐厅完整解决方案</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:16:03', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5068, '7992c9b41aec42d196c8f52a5fa1935d', '6v7be19pwman2fird04gqu53', '<a target=\'_blank\' class=\'muted\' href=\'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/cc7d1897a98b4b6ca00c67c625b21bd7-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf\'>智慧餐厅完整解决方案</a>', '取消关联文件', 'unlinkFile', '2021-02-02 17:16:54', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (5069, '89513df6bbb34cf8ad211cdb1f8f8466', '6v7be19pwman2fird04gqu53', '<a target=\'_blank\' class=\'muted\' href=\'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/4cb37d06008e43fd9e9421a70f190768-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf\'>智慧餐厅完整解决方案</a>', '取消关联文件', 'unlinkFile', '2021-02-02 17:16:58', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (5070, 'a26cbee8a7a74a80806975c945ceba81', '6v7be19pwman2fird04gqu53', '<a target=\'_blank\' class=\'muted\' href=\'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/4c980c0219ef419bbd68004ee164ea94-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf\'>智慧餐厅完整解决方案</a>', '取消关联文件', 'unlinkFile', '2021-02-02 17:17:01', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (5071, '313a9cd6427349f19a29d12239f090ec', '6v7be19pwman2fird04gqu53', '<a target=\'_blank\' class=\'muted\' href=\'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/adf1f8a2612f463b985216b518578a20-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf\'>智慧餐厅完整解决方案</a>', '取消关联文件', 'unlinkFile', '2021-02-02 17:17:05', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (5072, '0db3fc2f492e4e2c9487235fda12e84e', '6v7be19pwman2fird04gqu53', '<a target=\'_blank\' class=\'muted\' href=\'http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/3c85fb68230a4cf68cfb4f6f95ff0908-智慧餐厅完整解决方案.pdf&realFileName=智慧餐厅完整解决方案.pdf\'>智慧餐厅完整解决方案</a>', '取消关联文件', 'unlinkFile', '2021-02-02 17:17:09', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'disconnect', 0);
INSERT INTO `team_project_log` VALUES (5073, '22b2a455740c4f35a37fcdb56ced6d3d', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/dcad47f8444f4b04bcef716be94a7655-解烟茶【左右烟茶】的由来.pdf&realFileName=解烟茶【左右烟茶】的由来.pdf \">\"解烟茶【左右烟茶】的由来</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:17:18', '96360501c4f9466db1194415772f4750', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5074, '382bfe93325548ef8bac2e9ca1133ff0', '6v7be19pwman2fird04gqu53', 'test', '创建了任务 ', 'create', '2021-02-02 17:17:58', 'e9591ace4fc1451f9268283cc6021fbf', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5075, 'b7c40fd63ecc4792a0bacbeb6215445d', '6v7be19pwman2fird04gqu53', '', '认领了任务 ', 'claim', '2021-02-02 17:17:59', 'e9591ace4fc1451f9268283cc6021fbf', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'alert', 1);
INSERT INTO `team_project_log` VALUES (5076, 'b73f96ad0dfd432c893169d0e9d5f5ec', '6v7be19pwman2fird04gqu53', '', '更新截止时间为 2021-02-05 18:00', 'setEndTime', '2021-02-02 17:18:10', 'e9591ace4fc1451f9268283cc6021fbf', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'calendar', 0);
INSERT INTO `team_project_log` VALUES (5077, '634a8313738e4a738c4416b5028f387f', '6v7be19pwman2fird04gqu53', '<p>test</p>', '更新了备注 ', 'content', '2021-02-02 17:18:14', 'e9591ace4fc1451f9268283cc6021fbf', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'file-text', 0);
INSERT INTO `team_project_log` VALUES (5078, 'ba9b38622a2f49aa9319a3ac7ddf4835', '6v7be19pwman2fird04gqu53', '', '更新任务优先级为 紧急', 'pri', '2021-02-02 17:18:17', 'e9591ace4fc1451f9268283cc6021fbf', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'user', 0);
INSERT INTO `team_project_log` VALUES (5079, '593c076bbed944fd8befcdc8ef6848b1', '6v7be19pwman2fird04gqu53', '<a target=\"_blank\" class=\"muted\" href=\"http://localhost:8888/common/download?filePathName=/projectfile/6v7be19pwman2fird04gqu53/20210202/d3f3635d44264b4d9c2fa1e9db3bb87c-22.png&realFileName=22.png \">\"22</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:18:35', 'e9591ace4fc1451f9268283cc6021fbf', 'task', '', 0, 'cf50726e99d746fa88107ed355d4a8cc', 'link', 0);
INSERT INTO `team_project_log` VALUES (5080, '157e736540c84f7c87ec93feae311a1e', 'bddc79203627409e9928b290b952ee88', '<a target=\"_blank\" class=\"muted\" href=\"http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/1b948d7436474e44adb7892d05e86072-ECS共享型 s6.xlsx&realFileName=ECS共享型 s6.xlsx \">\"ECS共享型 s6</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:20:42', '0', 'task', '', 0, '88435aab6cad403788275282852d61a6', 'link', 0);
INSERT INTO `team_project_log` VALUES (5081, 'bf505a4c9e9940c0a78116012bca17f2', 'bddc79203627409e9928b290b952ee88', '<a target=\"_blank\" class=\"muted\" href=\"http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/6a7590ec28ff44399d2ed9848775e4df-server.cfg&realFileName=server.cfg \">\"server</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:21:30', '0', 'task', '', 0, '88435aab6cad403788275282852d61a6', 'link', 0);
INSERT INTO `team_project_log` VALUES (5082, '8257e8eaaf4b4b02b8cba4e6a0eec4bf', 'bddc79203627409e9928b290b952ee88', '<a target=\"_blank\" class=\"muted\" href=\"http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/44a9169a75254578b203972861821804-测试扩展名.jpg&realFileName=测试扩展名.jpg \">\"测试扩展名</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:26:32', '0', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'link', 0);
INSERT INTO `team_project_log` VALUES (5083, 'fb12ef96ad9a45f19ad74f15c49c8e91', 'bddc79203627409e9928b290b952ee88', '<a target=\"_blank\" class=\"muted\" href=\"http://122.112.164.217:8888/common/download?filePathName=/projectfile/bddc79203627409e9928b290b952ee88/20210202/308492a541944d49947147054c1dd2de-新建文本文档.txt&realFileName=新建文本文档.txt \">\"新建文本文档</a>', '上传了文件文件', 'uploadFile', '2021-02-02 17:27:13', '0', 'task', '', 0, 'a3c56e1116ff42b780fa8c16afc76311', 'link', 0);
INSERT INTO `team_project_log` VALUES (5084, '78b03bfe71de46bf8672f80538358dff', 'bddc79203627409e9928b290b952ee88', 'test112131', '创建了项目', 'create', '2021-02-02 18:10:45', 'd17947344cc1474499b1b43537e61c8c', NULL, '0', 0, 'd17947344cc1474499b1b43537e61c8c', 'plus', 0);
INSERT INTO `team_project_log` VALUES (5085, '172f7e28e5b24525b1e871f9b38f6ff3', 'bddc79203627409e9928b290b952ee88', 'demo', '邀请demo加入项目', 'inviteMember', '2021-02-02 18:10:45', 'd17947344cc1474499b1b43537e61c8c', NULL, 'bddc79203627409e9928b290b952ee88', 0, 'd17947344cc1474499b1b43537e61c8c', 'user-add', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目-成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_member
-- ----------------------------
INSERT INTO `team_project_member` VALUES (94, 'edba7252bddd447ba0a44485d886dd0a', '6v7be19pwman2fird04gqu53', '2020-07-06 10:20:45', 1, NULL);
INSERT INTO `team_project_member` VALUES (95, '8c4f887129e54068996e2d10a1c3bac9', '6v7be19pwman2fird04gqu53', '2020-07-06 10:22:43', 1, NULL);
INSERT INTO `team_project_member` VALUES (96, '8c4f887129e54068996e2d10a1c3bac9', 'bddc79203627409e9928b290b952ee88', '2020-07-06 10:51:15', 0, NULL);
INSERT INTO `team_project_member` VALUES (98, '8c4f887129e54068996e2d10a1c3bac9', 'e9b4bbe96f04474599c9f014ea1f47e7', '2020-07-06 10:51:18', 0, NULL);
INSERT INTO `team_project_member` VALUES (99, '78bcc133d0cc4885975ebabeabb97ffa', '6v7be19pwman2fird04gqu53', '2020-07-06 10:51:24', 1, NULL);
INSERT INTO `team_project_member` VALUES (100, '78bcc133d0cc4885975ebabeabb97ffa', 'bddc79203627409e9928b290b952ee88', '2020-07-06 10:51:37', 0, NULL);
INSERT INTO `team_project_member` VALUES (102, 'c70dbd8001e04e96b838309915f790ea', 'bddc79203627409e9928b290b952ee88', '2020-07-06 11:08:29', 1, NULL);
INSERT INTO `team_project_member` VALUES (103, '3488bba47b8e48fc9cc75f5e5580cfb4', '6v7be19pwman2fird04gqu53', '2020-07-06 17:07:26', 1, NULL);
INSERT INTO `team_project_member` VALUES (104, '3488bba47b8e48fc9cc75f5e5580cfb4', 'bddc79203627409e9928b290b952ee88', '2020-07-06 17:07:48', 0, NULL);
INSERT INTO `team_project_member` VALUES (106, '813a9c8b199e4dc59486cf6468ab91af', 'bddc79203627409e9928b290b952ee88', '2021-02-01 11:54:03', 1, NULL);
INSERT INTO `team_project_member` VALUES (107, '4d619572638b41b29e4e9d9b93c0fc6e', 'bddc79203627409e9928b290b952ee88', '2021-02-01 15:44:04', 1, NULL);
INSERT INTO `team_project_member` VALUES (108, '4f2619f1d1c64a389a2ba671e7fca2ed', 'bddc79203627409e9928b290b952ee88', '2021-02-01 16:07:02', 1, NULL);
INSERT INTO `team_project_member` VALUES (109, 'd0e82a94783a4ee7881f6c342f990bc5', 'bddc79203627409e9928b290b952ee88', '2021-02-01 16:42:39', 1, NULL);
INSERT INTO `team_project_member` VALUES (110, '81d43786f82c4c84baf34c6eb9ecac78', 'bddc79203627409e9928b290b952ee88', '2021-02-01 18:25:44', 1, NULL);
INSERT INTO `team_project_member` VALUES (111, 'a3c56e1116ff42b780fa8c16afc76311', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:33:17', 1, NULL);
INSERT INTO `team_project_member` VALUES (112, 'd99cbb2012ce413bab0cde5866fa5041', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:55:31', 1, NULL);
INSERT INTO `team_project_member` VALUES (113, '9749247d6ae44e8abeadac35d9f46ba0', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:56:16', 1, NULL);
INSERT INTO `team_project_member` VALUES (114, 'd2076c5f961b4bfe915906cbd4c773d3', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:56:41', 1, NULL);
INSERT INTO `team_project_member` VALUES (115, '88435aab6cad403788275282852d61a6', 'bddc79203627409e9928b290b952ee88', '2021-02-02 08:41:06', 1, NULL);
INSERT INTO `team_project_member` VALUES (116, 'f35e6d3ef3e04b5bb532039947b30eae', '6v7be19pwman2fird04gqu53', '2021-02-02 10:52:50', 1, NULL);
INSERT INTO `team_project_member` VALUES (117, '0ecde82e3bed4789a8e201621ba79d9f', '6v7be19pwman2fird04gqu53', '2021-02-02 16:29:52', 1, NULL);
INSERT INTO `team_project_member` VALUES (118, '74228c6bfd6040d3b00f934cd0b0014f', '6v7be19pwman2fird04gqu53', '2021-02-02 17:03:03', 1, NULL);
INSERT INTO `team_project_member` VALUES (119, 'cf50726e99d746fa88107ed355d4a8cc', '6v7be19pwman2fird04gqu53', '2021-02-02 17:06:18', 1, NULL);
INSERT INTO `team_project_member` VALUES (120, 'd17947344cc1474499b1b43537e61c8c', 'bddc79203627409e9928b290b952ee88', '2021-02-02 18:10:45', 1, NULL);

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
INSERT INTO `team_project_menu` VALUES (175, 0, 'aa', '', '#', '', '', 'project', 0, 1, 0, '2021-02-02 18:07:17', 0, NULL, 1, '');

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
) ENGINE = InnoDB AUTO_INCREMENT = 127 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目报表统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_project_report
-- ----------------------------
INSERT INTO `team_project_report` VALUES (1, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-23', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-02 00:01:00');
INSERT INTO `team_project_report` VALUES (2, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-24', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (3, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-25', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (4, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-26', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (5, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-27', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (6, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-28', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (7, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-29', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (8, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-30', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (9, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-01-31', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-02 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (10, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-02 00:01:02');
INSERT INTO `team_project_report` VALUES (11, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (12, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (13, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:05');
INSERT INTO `team_project_report` VALUES (14, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:05');
INSERT INTO `team_project_report` VALUES (15, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:05');
INSERT INTO `team_project_report` VALUES (16, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:05');
INSERT INTO `team_project_report` VALUES (17, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:05');
INSERT INTO `team_project_report` VALUES (18, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:05');
INSERT INTO `team_project_report` VALUES (19, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-02 00:01:03');
INSERT INTO `team_project_report` VALUES (20, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (21, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (22, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (23, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (24, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (25, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (26, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (27, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (28, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-02 00:01:05');
INSERT INTO `team_project_report` VALUES (29, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:08');
INSERT INTO `team_project_report` VALUES (30, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (31, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (32, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (33, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (34, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (35, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (36, '78bcc133d0cc4885975ebabeabb97ffa', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:09');
INSERT INTO `team_project_report` VALUES (37, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-02 00:01:06');
INSERT INTO `team_project_report` VALUES (38, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (39, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (40, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (41, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (42, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (43, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (44, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:11');
INSERT INTO `team_project_report` VALUES (45, '813a9c8b199e4dc59486cf6468ab91af', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:11');
INSERT INTO `team_project_report` VALUES (46, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-02 00:01:07');
INSERT INTO `team_project_report` VALUES (47, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:11');
INSERT INTO `team_project_report` VALUES (48, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:11');
INSERT INTO `team_project_report` VALUES (49, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:11');
INSERT INTO `team_project_report` VALUES (50, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (51, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (52, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:00', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (53, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (54, '81d43786f82c4c84baf34c6eb9ecac78', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (55, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-23', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-02 00:01:09');
INSERT INTO `team_project_report` VALUES (56, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-24', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (57, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-25', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (58, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-26', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (59, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-27', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (60, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-28', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (61, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-29', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (62, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-30', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (63, '8c4f887129e54068996e2d10a1c3bac9', '2021-01-31', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (64, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-02 00:01:10');
INSERT INTO `team_project_report` VALUES (65, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (66, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (67, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (68, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:16');
INSERT INTO `team_project_report` VALUES (69, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:16');
INSERT INTO `team_project_report` VALUES (70, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:16');
INSERT INTO `team_project_report` VALUES (71, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:16');
INSERT INTO `team_project_report` VALUES (72, '9749247d6ae44e8abeadac35d9f46ba0', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:16');
INSERT INTO `team_project_report` VALUES (73, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-02 00:01:11');
INSERT INTO `team_project_report` VALUES (74, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (75, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (76, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (77, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (78, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (79, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (80, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:17');
INSERT INTO `team_project_report` VALUES (81, 'a3c56e1116ff42b780fa8c16afc76311', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (82, 'c70dbd8001e04e96b838309915f790ea', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-02 00:01:13');
INSERT INTO `team_project_report` VALUES (83, 'c70dbd8001e04e96b838309915f790ea', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (84, 'c70dbd8001e04e96b838309915f790ea', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (85, 'c70dbd8001e04e96b838309915f790ea', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (86, 'c70dbd8001e04e96b838309915f790ea', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (87, 'c70dbd8001e04e96b838309915f790ea', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (88, 'c70dbd8001e04e96b838309915f790ea', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:19');
INSERT INTO `team_project_report` VALUES (89, 'c70dbd8001e04e96b838309915f790ea', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:19');
INSERT INTO `team_project_report` VALUES (90, 'c70dbd8001e04e96b838309915f790ea', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:19');
INSERT INTO `team_project_report` VALUES (91, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-02 00:01:14');
INSERT INTO `team_project_report` VALUES (92, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (93, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (94, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:01', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (95, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (96, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (97, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (98, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:21');
INSERT INTO `team_project_report` VALUES (99, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (100, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-02 00:01:15');
INSERT INTO `team_project_report` VALUES (101, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:23');
INSERT INTO `team_project_report` VALUES (102, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:23');
INSERT INTO `team_project_report` VALUES (103, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (104, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (105, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (106, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (107, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (108, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (109, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-02 00:01:17');
INSERT INTO `team_project_report` VALUES (110, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (111, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (112, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (113, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (114, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (115, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (116, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:25');
INSERT INTO `team_project_report` VALUES (117, 'd99cbb2012ce413bab0cde5866fa5041', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:26');
INSERT INTO `team_project_report` VALUES (118, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-23', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-02 00:01:18');
INSERT INTO `team_project_report` VALUES (119, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:26');
INSERT INTO `team_project_report` VALUES (120, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:26');
INSERT INTO `team_project_report` VALUES (121, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:26');
INSERT INTO `team_project_report` VALUES (122, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:26');
INSERT INTO `team_project_report` VALUES (123, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (124, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (125, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (126, 'edba7252bddd447ba0a44485d886dd0a', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-02 00:01:02', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (127, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:01');
INSERT INTO `team_project_report` VALUES (128, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:02');
INSERT INTO `team_project_report` VALUES (129, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:02');
INSERT INTO `team_project_report` VALUES (130, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:02');
INSERT INTO `team_project_report` VALUES (131, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:02');
INSERT INTO `team_project_report` VALUES (132, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:02');
INSERT INTO `team_project_report` VALUES (133, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (134, '0ecde82e3bed4789a8e201621ba79d9f', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (135, '0ecde82e3bed4789a8e201621ba79d9f', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:03');
INSERT INTO `team_project_report` VALUES (136, '3488bba47b8e48fc9cc75f5e5580cfb4', '2021-02-01', '{\"task\":5,\"undoneTask\":1,\"baseLineList\":1}', '2021-02-03 00:01:00', '2021-02-03 00:01:04');
INSERT INTO `team_project_report` VALUES (137, '4d619572638b41b29e4e9d9b93c0fc6e', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:06');
INSERT INTO `team_project_report` VALUES (138, '4f2619f1d1c64a389a2ba671e7fca2ed', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (139, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (140, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (141, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (142, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:07');
INSERT INTO `team_project_report` VALUES (143, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:08');
INSERT INTO `team_project_report` VALUES (144, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:08');
INSERT INTO `team_project_report` VALUES (145, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:08');
INSERT INTO `team_project_report` VALUES (146, '74228c6bfd6040d3b00f934cd0b0014f', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:08');
INSERT INTO `team_project_report` VALUES (147, '74228c6bfd6040d3b00f934cd0b0014f', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:00', '2021-02-03 00:01:08');
INSERT INTO `team_project_report` VALUES (148, '78bcc133d0cc4885975ebabeabb97ffa', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:10');
INSERT INTO `team_project_report` VALUES (149, '813a9c8b199e4dc59486cf6468ab91af', '2021-02-01', '{\"task\":3,\"undoneTask\":2,\"baseLineList\":2}', '2021-02-03 00:01:01', '2021-02-03 00:01:11');
INSERT INTO `team_project_report` VALUES (150, '81d43786f82c4c84baf34c6eb9ecac78', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (151, '88435aab6cad403788275282852d61a6', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:12');
INSERT INTO `team_project_report` VALUES (152, '88435aab6cad403788275282852d61a6', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:13');
INSERT INTO `team_project_report` VALUES (153, '88435aab6cad403788275282852d61a6', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:13');
INSERT INTO `team_project_report` VALUES (154, '88435aab6cad403788275282852d61a6', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:13');
INSERT INTO `team_project_report` VALUES (155, '88435aab6cad403788275282852d61a6', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:13');
INSERT INTO `team_project_report` VALUES (156, '88435aab6cad403788275282852d61a6', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:13');
INSERT INTO `team_project_report` VALUES (157, '88435aab6cad403788275282852d61a6', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:13');
INSERT INTO `team_project_report` VALUES (158, '88435aab6cad403788275282852d61a6', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (159, '88435aab6cad403788275282852d61a6', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:14');
INSERT INTO `team_project_report` VALUES (160, '8c4f887129e54068996e2d10a1c3bac9', '2021-02-01', '{\"task\":6,\"undoneTask\":4,\"baseLineList\":0}', '2021-02-03 00:01:01', '2021-02-03 00:01:15');
INSERT INTO `team_project_report` VALUES (161, '9749247d6ae44e8abeadac35d9f46ba0', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:16');
INSERT INTO `team_project_report` VALUES (162, 'a3c56e1116ff42b780fa8c16afc76311', '2021-02-01', '{\"task\":17,\"undoneTask\":12,\"baseLineList\":11}', '2021-02-03 00:01:02', '2021-02-03 00:01:18');
INSERT INTO `team_project_report` VALUES (163, 'c70dbd8001e04e96b838309915f790ea', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:19');
INSERT INTO `team_project_report` VALUES (164, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:19');
INSERT INTO `team_project_report` VALUES (165, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:19');
INSERT INTO `team_project_report` VALUES (166, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (167, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (168, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (169, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (170, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (171, 'cf50726e99d746fa88107ed355d4a8cc', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (172, 'cf50726e99d746fa88107ed355d4a8cc', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:20');
INSERT INTO `team_project_report` VALUES (173, 'd0e82a94783a4ee7881f6c342f990bc5', '2021-02-01', '{\"task\":2,\"undoneTask\":2,\"baseLineList\":2}', '2021-02-03 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (174, 'd17947344cc1474499b1b43537e61c8c', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (175, 'd17947344cc1474499b1b43537e61c8c', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (176, 'd17947344cc1474499b1b43537e61c8c', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (177, 'd17947344cc1474499b1b43537e61c8c', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (178, 'd17947344cc1474499b1b43537e61c8c', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:02', '2021-02-03 00:01:22');
INSERT INTO `team_project_report` VALUES (179, 'd17947344cc1474499b1b43537e61c8c', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:23');
INSERT INTO `team_project_report` VALUES (180, 'd17947344cc1474499b1b43537e61c8c', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:23');
INSERT INTO `team_project_report` VALUES (181, 'd17947344cc1474499b1b43537e61c8c', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:23');
INSERT INTO `team_project_report` VALUES (182, 'd17947344cc1474499b1b43537e61c8c', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:23');
INSERT INTO `team_project_report` VALUES (183, 'd2076c5f961b4bfe915906cbd4c773d3', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:24');
INSERT INTO `team_project_report` VALUES (184, 'd99cbb2012ce413bab0cde5866fa5041', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:26');
INSERT INTO `team_project_report` VALUES (185, 'edba7252bddd447ba0a44485d886dd0a', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (186, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-24', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (187, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-25', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:27');
INSERT INTO `team_project_report` VALUES (188, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-26', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');
INSERT INTO `team_project_report` VALUES (189, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-27', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');
INSERT INTO `team_project_report` VALUES (190, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-28', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');
INSERT INTO `team_project_report` VALUES (191, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-29', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');
INSERT INTO `team_project_report` VALUES (192, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-30', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');
INSERT INTO `team_project_report` VALUES (193, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-01-31', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');
INSERT INTO `team_project_report` VALUES (194, 'f35e6d3ef3e04b5bb532039947b30eae', '2021-02-01', '{\"task\":0,\"undoneTask\":0,\"baseLineList\":0}', '2021-02-03 00:01:03', '2021-02-03 00:01:28');

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
INSERT INTO `team_project_template` VALUES (33, '水利部工作模板1', '水利部工作模板1', 0, '2020-07-05 22:27:04', '5b72d66e70b6404f93a74bbf5d4de2a4', '6v7be19pwman2fird04gqu53', 'http://localhost:8888/common/image?filePathName=/projectfile/project/cover/20210202/361aa715ecd14a38830e612dc40db567-22.png&realFileName=22.png', '6v7be19pwman2fird04gqu53', 0);

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
-- Records of team_project_version
-- ----------------------------
INSERT INTO `team_project_version` VALUES (1, 'y0spow4feaxgulzc7m65ht91', '3.1', '3.1', '2019-06-23 11:18:24', NULL, '6v7be19pwman2fird04gqu53', '2019-06-24 11:22', '2019-06-01 11:18', 3, 100, '2019-06-30 11:18', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version` VALUES (2, 'xldvs0g1yefa6h4t87w5oqiz', '3.2', '3.2', '2019-06-23 11:23:43', NULL, '6v7be19pwman2fird04gqu53', NULL, '2019-06-25 11:23', 1, 33, '2019-06-30 11:23', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version` VALUES (3, 'zbktj7myco83wsxvde1qlfha', '3.3', '3.3', '2019-06-23 11:24:00', NULL, '6v7be19pwman2fird04gqu53', NULL, '2019-07-10 11:23', 2, 20, '2019-07-19 11:23', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version` VALUES (4, 'txnl37gzy58pkfwajm2qo4b1', 'bbmc', 'bbbz', '2020-04-08 21:43:51', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-04-10 21:43', 1, 0, '', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version` VALUES (6, 'v0fptg76bc2khi95wrue43jd', 'a123456', 'a', '2020-04-08 22:38:25', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-04-09 22:37', 0, 0, '2020-04-30 22:37', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version` VALUES (7, 'gfmn8jdc3yew5uosp690l71v', '1111', '1', '2020-06-06 16:03:52', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-06-06 16:03', 0, 0, '2020-06-13 16:03', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version` VALUES (8, 'ufrgqbzi13s64k82o0jex5t7', '2', '2', '2020-06-06 16:05:17', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-06-05 16:05', 0, 0, '2020-06-07 16:05', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version` VALUES (10, 'c23e43bc1a294b268235188e23dec3ba', 'rrr', 'rrr1111', '2020-06-06 16:28:31', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-06-05 16:28', 1, 0, '2020-06-30 16:28', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version` VALUES (11, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `team_project_version` VALUES (12, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `team_project_version` VALUES (13, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `team_project_version` VALUES (14, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `team_project_version` VALUES (15, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 0, 0, NULL, NULL);
INSERT INTO `team_project_version` VALUES (16, '5848a5bc379147e88655d23f0439027c', 'test', 'test', '2020-07-07 22:12:12', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-07-26 22:12', 0, 0, '2020-07-22 22:12', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version` VALUES (17, '760d73674c1c4db6afcb2e423cc4dbfc', '新版本', '新版本', '2020-07-07 22:34:41', NULL, '6v7be19pwman2fird04gqu53', NULL, '2020-07-08 22:34', 2, 50, '2020-07-08 22:34', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version` VALUES (18, 'd1570d81b5cc405790c47b7005463e14', '111', '111', '2021-02-01 14:20:40', NULL, '2360f2f0f79447f4a2498ae06a9b132d', NULL, '2021-02-01 14:20', 0, 0, '2021-02-28 14:20', 'a6de1a6d3e174caa96d944276a3ebb85');

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
-- Records of team_project_version_log
-- ----------------------------
INSERT INTO `team_project_version_log` VALUES (8, '75uegyrpwbo39c6t8lnh1f40', '6v7be19pwman2fird04gqu53', '修复了 Badge 代码错误引起的 TypeScript 类型报错，重构了 DatePicker 相关 type 定义', '添加了 2 项发布内容 ', 'addVersionTask', '2019-06-23 11:21:26', 'y0spow4feaxgulzc7m65ht91', NULL, 'link', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (9, 'bk138zouw5ca9yq4fis7tvh0', '6v7be19pwman2fird04gqu53', '优化 Spin 样式并略微提升了切换状态的性能', '添加了 1 项发布内容 ', 'addVersionTask', '2019-06-23 11:21:38', 'y0spow4feaxgulzc7m65ht91', NULL, 'link', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (10, 'lhw0s3xa1qn25z8rcgm9oe47', '6v7be19pwman2fird04gqu53', '', '完成版本时间为 06月24日 11:21', 'publish', '2019-06-23 11:21:51', 'y0spow4feaxgulzc7m65ht91', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (11, 'u7x5in3ecawf2qvols4gmhjd', '6v7be19pwman2fird04gqu53', '', '更新了状态为  未开始', 'status', '2019-06-23 11:21:56', 'y0spow4feaxgulzc7m65ht91', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (12, 'l7d6jaxoyb2w4pgvzsq5km98', '6v7be19pwman2fird04gqu53', '', '完成版本时间为 06月24日 11:22', 'publish', '2019-06-23 11:22:04', 'y0spow4feaxgulzc7m65ht91', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (13, 'lu6wg5sohb3q08tpxk4r1jya', '6v7be19pwman2fird04gqu53', '3.2', '创建了版本 ', 'create', '2019-06-23 11:23:43', 'xldvs0g1yefa6h4t87w5oqiz', NULL, 'plus', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (14, 'oqy8w0rmcagdh94blksxif2z', '6v7be19pwman2fird04gqu53', '3.3', '创建了版本 ', 'create', '2019-06-23 11:24:00', 'zbktj7myco83wsxvde1qlfha', NULL, 'plus', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (15, 'bzcqhgnds5r08mu9v1o6ywj7', '6v7be19pwman2fird04gqu53', '', '更新了状态为  延期发布', 'status', '2019-06-23 11:24:04', 'xldvs0g1yefa6h4t87w5oqiz', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (16, 'fqh97xkza2u1bs5wg68i4yj3', '6v7be19pwman2fird04gqu53', '', '更新了状态为  进行中', 'status', '2019-06-23 11:24:07', 'zbktj7myco83wsxvde1qlfha', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (17, 'q75n0hljzitmgey4k1xcurfp', '6v7be19pwman2fird04gqu53', '', '更新了状态为  进行中', 'status', '2019-06-23 11:24:09', 'xldvs0g1yefa6h4t87w5oqiz', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (18, '6kl17x9ceziq3yh8swd5pjnv', '6v7be19pwman2fird04gqu53', '', '更新了状态为  延期发布', 'status', '2019-06-23 11:24:11', 'zbktj7myco83wsxvde1qlfha', NULL, 'check-square', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (19, '1yjkv2ofn9743zm8l6qisxbp', '6v7be19pwman2fird04gqu53', '修复了 Upload 对无扩展名图片地址的预览展示问题，修复一处 less 语法错误', '添加了 2 项发布内容 ', 'addVersionTask', '2019-06-23 11:24:25', 'xldvs0g1yefa6h4t87w5oqiz', NULL, 'link', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (20, 'qwzxk5vdcerh6pt4j8iylm0g', '6v7be19pwman2fird04gqu53', 'Steps 进行了重构，首次渲染的时候不会再闪烁', '添加了 1 项发布内容 ', 'addVersionTask', '2019-06-23 11:24:39', 'xldvs0g1yefa6h4t87w5oqiz', NULL, 'link', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (21, '6axlg951b2uzdfv0otyknsmp', '6v7be19pwman2fird04gqu53', 'Avatar 组件增加 srcSet 属性，用于设置图片类头像响应式资源地址，增加 less 变量 @font-variant-base 定制 font-variant 样式，微调 Card 头部和加载中的样式细节', '添加了 3 项发布内容 ', 'addVersionTask', '2019-06-23 11:24:51', 'zbktj7myco83wsxvde1qlfha', NULL, 'link', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (22, '7obdgm56hqwtriezsjckyp9u', '6v7be19pwman2fird04gqu53', 'Carousel: 升级 react-slick 版本以修复宽度计算错误，修复 enterButton 的值为 button 元素时显示错误的问题', '添加了 2 项发布内容 ', 'addVersionTask', '2019-06-23 11:24:59', 'zbktj7myco83wsxvde1qlfha', NULL, 'link', 'qtsxlwob1m0uja37y2g4pefc');
INSERT INTO `team_project_version_log` VALUES (23, 'iq5cf61yex7gsmu8n9bj4vlw', '6v7be19pwman2fird04gqu53', 'bbmc', '创建了版本 ', 'create', '2020-04-08 21:43:51', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'plus', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (24, 'hsfpig3u45o276c1kanxt9zl', '6v7be19pwman2fird04gqu53', 'a123456', '创建了版本 ', 'create', '2020-04-08 22:37:48', 'omunx3v8zh2dlt5yag7r4fwq', NULL, 'plus', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (25, 'pozas3bdemql7w2yx9hfj1in', '6v7be19pwman2fird04gqu53', '', '删除了版本 ', 'delete', '2020-04-08 22:38:10', 'omunx3v8zh2dlt5yag7r4fwq', NULL, 'delete', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (26, 'za9ucsij2fpdrvyq85nhol7t', '6v7be19pwman2fird04gqu53', 'a123456', '创建了版本 ', 'create', '2020-04-08 22:38:25', 'v0fptg76bc2khi95wrue43jd', NULL, 'plus', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (27, 'bdafxis8p9lw6o4tyc0ujmvz', '6v7be19pwman2fird04gqu53', '啊啊啊', '添加了 1 项发布内容 ', 'addVersionTask', '2020-04-09 15:30:31', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'link', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (28, '82qymd3tnrfxb1z5vu09ajhw', '6v7be19pwman2fird04gqu53', '啊啊啊', '移除了发布内容', 'removeVersionTask', '2020-04-09 15:35:53', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'disconnect', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (29, 'vfuylo27biq18h40jp3txmen', '6v7be19pwman2fird04gqu53', '啊啊啊', '添加了 1 项发布内容 ', 'addVersionTask', '2020-04-09 15:36:11', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'link', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (30, 'bkrzy58jsfo24vndglmq9wp0', '6v7be19pwman2fird04gqu53', 'bbbb', '添加了 1 项发布内容 ', 'addVersionTask', '2020-04-09 16:23:54', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'link', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (31, '5olnbzhdwix9g08qa34v71mc', '6v7be19pwman2fird04gqu53', 'bbbb', '移除了发布内容', 'removeVersionTask', '2020-04-09 16:24:15', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'disconnect', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (32, 'avnj2em3zrgflwkcb17xo0t4', '6v7be19pwman2fird04gqu53', '啊啊啊', '移除了发布内容', 'removeVersionTask', '2020-04-09 16:24:18', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'disconnect', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (33, 'bpcjlmyzv1iwu0r8tka4d952', '6v7be19pwman2fird04gqu53', '啊啊啊，bbbb', '添加了 2 项发布内容 ', 'addVersionTask', '2020-04-09 16:24:37', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'link', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (34, 'r5ntfkmgd61x0hyizwoe42lp', '6v7be19pwman2fird04gqu53', '', '更新了状态为  进行中', 'status', '2020-04-09 16:57:40', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'check-square', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (35, 'zvsm4iqyt9x7lhceo6fu0a2d', '6v7be19pwman2fird04gqu53', '', '更新了状态为  进行中', 'status', '2020-04-09 18:03:14', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'check-square', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (36, '5u1cwmfeph2bzodgk468nrtv', '6v7be19pwman2fird04gqu53', '', '更新开始时间为 04月10日 21:43', 'setStartTime', '2020-04-09 18:15:40', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'calendar', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (37, 'qxj8osa59ten4dz3gfm0blrv', '6v7be19pwman2fird04gqu53', '', '更新开始时间为 04月10日 21:43', 'setStartTime', '2020-04-09 18:15:53', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'calendar', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (38, '54v9czexmdn1fplkw3qgoj6y', '6v7be19pwman2fird04gqu53', '', '清除了计划发布时间 ', 'clearPlanPublishTime', '2020-04-09 22:46:50', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'calendar', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (39, '4btfokyhd02xm9vglw3eiu87', '6v7be19pwman2fird04gqu53', '啊啊啊', '移除了发布内容', 'removeVersionTask', '2020-04-09 22:47:16', 'txnl37gzy58pkfwajm2qo4b1', NULL, 'disconnect', '0jn2pulbgyav581fzsich4or');
INSERT INTO `team_project_version_log` VALUES (40, 'f8slr216izqw3kdphmexga7n', '6v7be19pwman2fird04gqu53', '1111', '创建了版本 ', 'create', '2020-06-06 16:03:52', 'gfmn8jdc3yew5uosp690l71v', NULL, 'plus', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (41, 'iyehkc4m9fsb3r5gqtol2jnd', '6v7be19pwman2fird04gqu53', '2', '创建了版本 ', 'create', '2020-06-06 16:05:18', 'ufrgqbzi13s64k82o0jex5t7', NULL, 'plus', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (42, '6b06e6aa99e14040a8416695924fc054', NULL, 'rrr', '创建了新版本', 'create', '2020-06-06 16:28:31', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'plus', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (43, '52a052ebe4b644ab9bb2a1262c562491', '6v7be19pwman2fird04gqu53', '', '更新计划发布时间为 2020-06-30 16:28', 'status', '2020-06-06 16:28:50', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'check-square', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (44, '1360d3ecb29c473bbb84955cd8d0985d', '6v7be19pwman2fird04gqu53', '', '更新了状态为进行中', 'status', '2020-06-06 16:28:58', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'check-square', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (45, '97c817aabca14b19a50526d8219ace3f', '6v7be19pwman2fird04gqu53', '', '更新描述为 rrr1111 ', 'status', '2020-06-06 16:29:07', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'check-square', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (46, 'fwidsuq1j9gn52xzl4vy7hpc', '6v7be19pwman2fird04gqu53', '123', '添加了 1 项发布内容 ', 'addVersionTask', '2020-06-06 16:34:33', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (47, '606555b5ad104df286b0609361c962eb', '6v7be19pwman2fird04gqu53', '[ccc]', '添加了 1 项目发布内容', 'addVersionTask', '2020-06-06 16:42:16', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (48, 'bf2026a06b804499857464b34adfa3e8', '6v7be19pwman2fird04gqu53', '[1qaz, wsx]', '添加了 2 项目发布内容', 'addVersionTask', '2020-06-06 16:43:36', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (49, 'avh568npuf0cxgtbi2syewrk', '6v7be19pwman2fird04gqu53', '123', '移除了发布内容', 'removeVersionTask', '2020-06-06 16:44:34', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'disconnect', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (50, 'rd4ocn7wqxet5zk8h1p620s3', '6v7be19pwman2fird04gqu53', '2wsx，555', '添加了 2 项发布内容 ', 'addVersionTask', '2020-06-06 16:44:50', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (51, 'c795af4c495d4e31a1d8f0d7021d6d56', '6v7be19pwman2fird04gqu53', 'aaa,aaa', '添加了 2 项目发布内容', 'addVersionTask', '2020-06-06 16:53:06', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (52, '8aa07dc266dc421484b8c183b6a9e3e5', '6v7be19pwman2fird04gqu53', NULL, NULL, 'removeVersionTask', '2020-06-07 15:20:07', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'disconnect', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (53, '2b4caa58ff1e42ef8febbd22e7c7904f', '6v7be19pwman2fird04gqu53', NULL, NULL, 'removeVersionTask', '2020-06-07 15:23:55', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'disconnect', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (54, 'oh9jufqyne703184rplgzvbk', '6v7be19pwman2fird04gqu53', '2wsx', '添加了 1 项发布内容 ', 'addVersionTask', '2020-06-07 16:27:47', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (55, '4800fce51b5646eb980522bafc60d934', '6v7be19pwman2fird04gqu53', '2wsx', '移除了发布内容', 'removeVersionTask', '2020-06-07 16:28:15', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'disconnect', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (56, '2y9jzshwto38ix5auqpmgd6v', '6v7be19pwman2fird04gqu53', '2wsx', '添加了 1 项发布内容 ', 'addVersionTask', '2020-06-07 16:28:35', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (57, 'rexpqgfhzs83ik1a6vw4bn05', '6v7be19pwman2fird04gqu53', '2wsx', '移除了发布内容', 'removeVersionTask', '2020-06-07 16:28:38', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'disconnect', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (58, '5e8cef601abc460791ece9684b593fea', '6v7be19pwman2fird04gqu53', '2wsx', '添加了1项发布内容', 'addVersionTask', '2020-06-07 17:15:14', 'c23e43bc1a294b268235188e23dec3ba', NULL, 'link', 'ca9d9600ebaa4313af7f23abbbd8170e');
INSERT INTO `team_project_version_log` VALUES (59, 'f457ae2a307f409fa6aeb3a4b14bdd8f', NULL, 'test', '创建了新版本', 'create', '2020-07-07 22:12:12', '5848a5bc379147e88655d23f0439027c', NULL, 'plus', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (60, 'c8a61954f3b143b6a1f214d918c50643', NULL, '新版本', '创建了新版本', 'create', '2020-07-07 22:34:41', '760d73674c1c4db6afcb2e423cc4dbfc', NULL, 'plus', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (61, '93bfa90a4f5946609a71953d02e7a09d', '6v7be19pwman2fird04gqu53', '水资源调研报告编写\n,资源报告审核\n,资源报告实施', '添加了3项发布内容', 'addVersionTask', '2020-07-07 22:34:59', '760d73674c1c4db6afcb2e423cc4dbfc', NULL, 'link', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (62, '3fca0edf71674091bd6f8ca438864d38', '6v7be19pwman2fird04gqu53', '', '更新开始时间为 2020-07-08 22:34 ', 'status', '2020-07-07 23:32:46', '760d73674c1c4db6afcb2e423cc4dbfc', NULL, 'check-square', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (63, '2633cd99f0784f99afbb5438397d3794', '6v7be19pwman2fird04gqu53', '水资源调研报告编写\n', '移除了发布内容', 'removeVersionTask', '2020-07-07 23:32:55', '760d73674c1c4db6afcb2e423cc4dbfc', NULL, 'disconnect', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (64, '74b1d7b1b61c496196b4d0a66d680f15', '6v7be19pwman2fird04gqu53', '', '更新了状态为进行中', 'status', '2020-07-07 23:33:14', '760d73674c1c4db6afcb2e423cc4dbfc', NULL, 'check-square', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (65, '91c702331154410f96db11bec758392a', '6v7be19pwman2fird04gqu53', '', '更新了状态为延期发布', 'status', '2020-07-07 23:33:23', '760d73674c1c4db6afcb2e423cc4dbfc', NULL, 'check-square', '2297b00222804dad83d5cf99277a8f81');
INSERT INTO `team_project_version_log` VALUES (66, '024e697f79c84b1086e3b44f855dc09b', NULL, '111', '创建了新版本', 'create', '2021-02-01 14:20:40', 'd1570d81b5cc405790c47b7005463e14', NULL, 'plus', 'a6de1a6d3e174caa96d944276a3ebb85');
INSERT INTO `team_project_version_log` VALUES (67, '1d33f5791fe048f9ae2001a018e0829c', 'bddc79203627409e9928b290b952ee88', '', '更新了状态为进行中', 'status', '2021-02-01 15:07:58', 'd1570d81b5cc405790c47b7005463e14', NULL, 'check-square', 'a6de1a6d3e174caa96d944276a3ebb85');
INSERT INTO `team_project_version_log` VALUES (68, '24effd8c5b8f4d3eb0c8bcecf58f0767', 'bddc79203627409e9928b290b952ee88', '', '更新了状态为未开始', 'status', '2021-02-01 15:07:59', 'd1570d81b5cc405790c47b7005463e14', NULL, 'check-square', 'a6de1a6d3e174caa96d944276a3ebb85');

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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_source_link
-- ----------------------------
INSERT INTO `team_source_link` VALUES (6, 'yglxnidcs8jkpa130oe2frz9', 'file', 'wtp4v8ukqoe3g5bichj07rn1', 'task', '28qet31u7kym6gi54pa9jldr', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', '2020-03-15 13:27:28', 0);
INSERT INTO `team_source_link` VALUES (7, 'pbrfak7q1xyc2j6d0nh39vzl', 'file', 'ojc1vktab49fx6z2iru03me7', 'task', '28qet31u7kym6gi54pa9jldr', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', '2020-03-15 14:12:07', 0);
INSERT INTO `team_source_link` VALUES (12, 'b59c1afce207489585fd2f0dccad4973', 'file', '0c4afd2433ce4d2ab7cd084973cc238c', 'task', 'f806fa9f3f8048dba65e70b1e556e7ec', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (13, '4735ec505b6f40579ab7f5276c77099a', 'file', 'ebba1d8d754647ae8aa9761579837d90', 'task', 'f806fa9f3f8048dba65e70b1e556e7ec', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (14, '4b0b8eda1dd5423abaa43e9ab476d509', 'file', 'b9005e3c02de423a90f16cb7639db22c', 'task', 'f806fa9f3f8048dba65e70b1e556e7ec', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (15, '5ede222476004de6a71a90c9f8300d10', 'file', '4d13154924644452a967ca0cd4d03078', 'task', 'f806fa9f3f8048dba65e70b1e556e7ec', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (16, 'b1015b151bbc4393a65616990c528407', 'file', 'bdc0470980844626ac98712a91a8c42c', 'task', 'l9n0imsewcbg5okzqp8fjd7x', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (18, '31970781e60545ff8bafa5b9aa67f209', 'file', '4b2e345e91244a73892a291d987b2c03', 'task', '7d92754ae4244c0e8eb7b4f93da42f69', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (19, 'ce0c79621b524dcbb8fdf5fb3fb636a2', 'file', '4bed1c4bad6d4fc9b3d6e2ea9c9ed092', 'task', 'b7cb4cb5d61542148cf02c4c9e5639f5', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (30, '980d53cb9c2f4109b47f30f10e49d258', 'file', 'fcdbc6cfb7e64759ba3e691990110e08', 'task', 'abb6a40af8c248d9be0bf80ab8e0b4db', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (31, 'cd9a84f41b6a4a5c9bd1ae0c93e94eb1', 'file', 'c0d0757f7c0946919dab7b34bf330c1a', 'task', '129cdebcf8e84a8b88e5c8920f566d20', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (32, '2e445f9199d5456db78c5aa22adad281', 'file', 'de2106f61100473d934aa6d98dacffc3', 'task', '8d4c1bde367f43ed9db2b7da13a059ae', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (33, 'ab37fa24c1db4336bf0cd164d8633097', 'file', 'f323dc2d06244163a1f6151af06f30a5', 'task', '211affd25de14f988c92d5c7b84bdcb4', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (34, 'ecb1d64b6bb247e3a515d71c1cd19a84', 'file', 'b7eb94cfd1344bf09bee348a9a000b0c', 'task', 'c3f30c32f31b46a8b2b413ffb7f742d9', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (35, '8002ea3de0544c549b8c3d4878c557d5', 'file', '7f3fde339f284565b65932c2058a0ff3', 'task', '62086de33087479885e9a54f8a22808e', '2360f2f0f79447f4a2498ae06a9b132d', 'bddc79203627409e9928b290b952ee88', NULL, 0);
INSERT INTO `team_source_link` VALUES (36, 'f310c86fdcb94bf59dc57533fd16ffc8', 'file', '31b336c69b8349248843adfe92f00a2f', 'task', '96360501c4f9466db1194415772f4750', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (42, 'e76926cfb10e4d1fbd3dab68dbf041d7', 'file', 'dcad47f8444f4b04bcef716be94a7655', 'task', '96360501c4f9466db1194415772f4750', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);
INSERT INTO `team_source_link` VALUES (43, '1c89818f64df415e827b5ec099b44ea5', 'file', 'd3f3635d44264b4d9c2fa1e9db3bb87c', 'task', 'e9591ace4fc1451f9268283cc6021fbf', '6v7be19pwman2fird04gqu53', '6v7be19pwman2fird04gqu53', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 12498 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task
-- ----------------------------
INSERT INTO `team_task` VALUES (12456, '8d4c1bde367f43ed9db2b7da13a059ae', '8c4f887129e54068996e2d10a1c3bac9', '水资源调研报告编写\n', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 15:11:58', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, 'e558aeb440494ed2828368a1634f2c09', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 1, '', 0, '', '', 0, 1, NULL);
INSERT INTO `team_task` VALUES (12457, '1b87f1d5642b4a549eeeffd4306d1b81', '8c4f887129e54068996e2d10a1c3bac9', '调研报告会议讨论', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 15:42:14', 'bddc79203627409e9928b290b952ee88', 0, '0669fbf3380c486390f4edf8962cb7cf', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 1, 2, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12458, 'da7a9009fae44bb6a701ffbc5bab7f6b', '8c4f887129e54068996e2d10a1c3bac9', '资源报告审核\n', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 15:54:17', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, 'e558aeb440494ed2828368a1634f2c09', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 3, '', 0, '', '', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12459, '0375e80f2edf48e3835a8cd73a1532e1', '8c4f887129e54068996e2d10a1c3bac9', '资源报告实施', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 15:55:01', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, 'e558aeb440494ed2828368a1634f2c09', NULL, 1, '', '', NULL, '', 0, 0, 0, NULL, 0, 4, '', 0, '', '', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12460, '59461dba170540c59679bff152de19a4', '8c4f887129e54068996e2d10a1c3bac9', '临时小组会议', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 16:49:51', '6v7be19pwman2fird04gqu53', 0, 'e558aeb440494ed2828368a1634f2c09', NULL, 1, '', '', NULL, '8d4c1bde367f43ed9db2b7da13a059ae', 0, 0, 0, NULL, 0, 5, '8d4c1bde367f43ed9db2b7da13a059ae', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12461, '46077d78315d443382eb86e44225e29a', '8c4f887129e54068996e2d10a1c3bac9', '张一的任务', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 16:57:18', 'bddc79203627409e9928b290b952ee88', 0, '3d2b44fbf63f49498746469438f3971a', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 1, 6, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12462, '23e47dd7bcc54acebe0122bb2c939b76', '3488bba47b8e48fc9cc75f5e5580cfb4', '张一负责的任务', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 17:08:04', 'bddc79203627409e9928b290b952ee88', 0, '67553773f3c245c0abbd3a58ddbbe374', NULL, 1, '', '', NULL, '', 0, 0, 0, NULL, 1, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12463, 'dccf7dbf08fc4323bc124a948f6c6c74', '3488bba47b8e48fc9cc75f5e5580cfb4', '指给潘工的任务', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2020-07-06 17:09:10', '6v7be19pwman2fird04gqu53', 0, '9efeaa61ef274457922a8a10b77e13f6', NULL, 0, '', '2020-07-10 18:00', NULL, '', 0, 0, 0, NULL, 1, 2, '', 0, '0', '0', 10, 0, NULL);
INSERT INTO `team_task` VALUES (12464, 'c3f30c32f31b46a8b2b413ffb7f742d9', '3488bba47b8e48fc9cc75f5e5580cfb4', '指给王伟的任务', 0, 'wait', '<h1>随风倒十分</h1>', 'bddc79203627409e9928b290b952ee88', '2020-07-06 17:09:51', '6d44c444965349ae926cd5be98912292', 0, 'e9c7e444dac549419f701aacc1947008', NULL, 1, '', '', NULL, '', 0, 0, 0, NULL, 0, 3, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12465, '025fb21bea124f958b6dbe9f157ece00', '3488bba47b8e48fc9cc75f5e5580cfb4', '测试任务', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 17:20:30', '6v7be19pwman2fird04gqu53', 0, 'c1eaf52db37c4ff0820e9128afc2a418', NULL, 1, '', '', NULL, '', 0, 0, 0, NULL, 0, 4, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12466, '211affd25de14f988c92d5c7b84bdcb4', '3488bba47b8e48fc9cc75f5e5580cfb4', '测试附件', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2020-07-06 17:24:08', '6v7be19pwman2fird04gqu53', 0, 'c1eaf52db37c4ff0820e9128afc2a418', NULL, 1, '', '', NULL, '', 0, 0, 0, NULL, 0, 5, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12467, '828f9ad33dc84aa4b878f74fa74317dd', '813a9c8b199e4dc59486cf6468ab91af', '测试计划\n', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 14:30:03', 'bddc79203627409e9928b290b952ee88', 0, '60115e7c99bf43568e0b71bf35a9c760', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12468, 'be6698b6bdcc4cf7a87e2a8338eccabb', '813a9c8b199e4dc59486cf6468ab91af', '测试任务', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 15:09:39', 'bddc79203627409e9928b290b952ee88', 0, '1b6c700558fd4eca9966392912a60c08', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 2, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12469, '37c256be26a24d86b4b4f3c9894b8eda', '813a9c8b199e4dc59486cf6468ab91af', '1111', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 15:45:50', 'bddc79203627409e9928b290b952ee88', 0, '60115e7c99bf43568e0b71bf35a9c760', NULL, 1, '', '', NULL, '', 0, 0, 0, NULL, 0, 3, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12470, '648b0d92913b4238b532a1ccfc46c534', 'd0e82a94783a4ee7881f6c342f990bc5', 'aaaa', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 16:42:47', 'bddc79203627409e9928b290b952ee88', 0, '79faa5c153db4ff19c9502badfc46c47', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12471, '3b65628ca15c41669c2b6bfb885d953d', 'd0e82a94783a4ee7881f6c342f990bc5', 'dff', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 16:42:51', 'bddc79203627409e9928b290b952ee88', 0, 'de81fcdf4b0042e2b710a4164e0ba68a', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 2, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12473, '6d8862e580b44bec82bdb373b66172ce', 'a3c56e1116ff42b780fa8c16afc76311', '需求规格说明书-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:35:35', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 1, 0, 0, NULL, 0, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12474, '730bbbcb16d048faaed58417537e9c2c', 'a3c56e1116ff42b780fa8c16afc76311', '概要设计说明书-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:42:06', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 2, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12475, '69523f0f56d14b58a657d15f3e8e8e6d', 'a3c56e1116ff42b780fa8c16afc76311', '详细设计说明书-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:42:19', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 2, 0, 0, NULL, 0, 3, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12476, 'ec92afe944414c20a3ce30f5ed74cc7d', 'a3c56e1116ff42b780fa8c16afc76311', '数据库设计说明书-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:43:02', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 3, 0, 0, NULL, 0, 4, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12477, '7dc3192213db45dbb49bb62e8d92aacd', 'a3c56e1116ff42b780fa8c16afc76311', '接口设计说明书-已定稿', 2, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:43:16', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '2021-02-19 18:00', NULL, '', 4, 0, 0, NULL, 0, 5, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12478, 'a1fcd15b001a42f9bbf2d33b75cd28a5', 'a3c56e1116ff42b780fa8c16afc76311', '内审', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:48:59', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 1, '', '', NULL, '6d8862e580b44bec82bdb373b66172ce', 0, 0, 0, NULL, 0, 6, '6d8862e580b44bec82bdb373b66172ce', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12479, '20ca7ed884d54d2291f172fd6ff1c0c1', 'a3c56e1116ff42b780fa8c16afc76311', '老万审', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:11', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 1, '', '', NULL, '6d8862e580b44bec82bdb373b66172ce', 0, 0, 0, NULL, 0, 7, '6d8862e580b44bec82bdb373b66172ce', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12480, 'b98123b2f95741b9a595882050c75a3c', 'a3c56e1116ff42b780fa8c16afc76311', '1', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:32', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 1, '', '', NULL, '6d8862e580b44bec82bdb373b66172ce', 0, 0, 0, NULL, 0, 8, '6d8862e580b44bec82bdb373b66172ce', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12481, 'cfba366ab35b43e794862690faa5c8df', 'a3c56e1116ff42b780fa8c16afc76311', '2', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:35', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 1, '', '', NULL, '6d8862e580b44bec82bdb373b66172ce', 0, 0, 0, NULL, 0, 9, '6d8862e580b44bec82bdb373b66172ce', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12482, 'f5b8d37bbf2a4be091f8a4899ddc051a', 'a3c56e1116ff42b780fa8c16afc76311', '1', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:44', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 1, '', '', NULL, 'a1fcd15b001a42f9bbf2d33b75cd28a5', 0, 0, 0, NULL, 0, 10, 'a1fcd15b001a42f9bbf2d33b75cd28a5,6d8862e580b44bec82bdb373b66172ce', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12483, '32632e1ca4c2461dac80d6a32d1a4c44', 'a3c56e1116ff42b780fa8c16afc76311', '软件测试方案-未定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:10:02', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 5, 0, 0, NULL, 0, 11, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12484, '1a4770cb15434abd8506a3a476ba0584', 'a3c56e1116ff42b780fa8c16afc76311', '集成部署方案-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:14:51', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 6, 0, 0, NULL, 0, 12, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12485, '7b3e52cd6d1e4ef3a056a6775ed6cea8', 'a3c56e1116ff42b780fa8c16afc76311', '项目计划-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:19:02', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 7, 0, 0, NULL, 0, 13, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12486, '9aa4adad0ff34ed793bf83d57e898060', 'a3c56e1116ff42b780fa8c16afc76311', '培训方案-未定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:20:24', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 14, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12487, '122409550ae4486c9935d93e8b923e7d', 'a3c56e1116ff42b780fa8c16afc76311', '试运行方案-未定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:20:46', 'bddc79203627409e9928b290b952ee88', 0, 'd7780f1f0c6f4ae091b0ad1c5ce678f1', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 15, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12488, 'ed51394ab89642e6b7367b8beb21ae28', 'a3c56e1116ff42b780fa8c16afc76311', '测试记录-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:22:01', 'bddc79203627409e9928b290b952ee88', 0, 'd40dff2311894e098e7c92ae9aa85008', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 16, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12489, 'b124f39bc9f14d3bb3c186a9a63d3991', 'a3c56e1116ff42b780fa8c16afc76311', '测试报告-已定稿', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:22:03', 'bddc79203627409e9928b290b952ee88', 0, 'd40dff2311894e098e7c92ae9aa85008', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 17, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12490, 'dcbaadbebcfe46fd9f77cb6ad0656cb5', 'a3c56e1116ff42b780fa8c16afc76311', '测试记录', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:22:05', 'bddc79203627409e9928b290b952ee88', 1, 'd40dff2311894e098e7c92ae9aa85008', NULL, 0, '', '', NULL, '', 0, 0, 0, '2021-02-01 22:23:30', 0, 18, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12495, 'cc168a8cb4424f16983cbff00d464806', '8c4f887129e54068996e2d10a1c3bac9', '添加', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-02 00:10:00', 'bddc79203627409e9928b290b952ee88', 0, '0669fbf3380c486390f4edf8962cb7cf', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 7, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12496, '056556ee2e35482688a9c19ba88420f5', '8c4f887129e54068996e2d10a1c3bac9', '1', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-02 00:10:06', 'e9b4bbe96f04474599c9f014ea1f47e7', 0, '0669fbf3380c486390f4edf8962cb7cf', NULL, 0, '', '', NULL, 'cc168a8cb4424f16983cbff00d464806', 0, 0, 0, NULL, 0, 8, 'cc168a8cb4424f16983cbff00d464806', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12497, '36f5bebf257240e5848190fa213f99cf', '8c4f887129e54068996e2d10a1c3bac9', '2', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-02 00:10:08', '6v7be19pwman2fird04gqu53', 0, '0669fbf3380c486390f4edf8962cb7cf', NULL, 0, '', '', NULL, 'cc168a8cb4424f16983cbff00d464806', 0, 0, 0, NULL, 0, 9, 'cc168a8cb4424f16983cbff00d464806', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12498, '7155d6a6b6954cfda9a9175385346d4f', '4f2619f1d1c64a389a2ba671e7fca2ed', '111', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-02 15:00:36', 'bddc79203627409e9928b290b952ee88', 0, '1528fcb63b1741578da5a08cf057eb5b', NULL, 1, '', '2021-02-19 18:00', NULL, '', 0, 0, 0, NULL, 0, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12499, '62086de33087479885e9a54f8a22808e', '88435aab6cad403788275282852d61a6', '11', 0, 'wait', '', 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:09:57', 'bddc79203627409e9928b290b952ee88', 0, '5cb7f3a8a6a34b9da9da10b7d5977495', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12500, '96360501c4f9466db1194415772f4750', 'cf50726e99d746fa88107ed355d4a8cc', '秀梅', 0, 'wait', '', '6v7be19pwman2fird04gqu53', '2021-02-02 17:12:47', '6v7be19pwman2fird04gqu53', 0, '97af4b39f6fc472d97165308b1cd55b5', NULL, 0, '', '', NULL, '', 0, 0, 0, NULL, 0, 1, '', 0, '0', '0', 0, NULL, NULL);
INSERT INTO `team_task` VALUES (12501, 'e9591ace4fc1451f9268283cc6021fbf', 'cf50726e99d746fa88107ed355d4a8cc', 'test', 1, 'wait', '<p>test</p>', '6v7be19pwman2fird04gqu53', '2021-02-02 17:17:58', '6v7be19pwman2fird04gqu53', 0, '56392cfefd3a4b8baf1059031b9e899b', NULL, 0, '', '2021-02-05 18:00', NULL, '', 0, 0, 0, NULL, 0, 2, '', 0, '0', '0', 0, NULL, NULL);

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
-- Records of team_task_like
-- ----------------------------
INSERT INTO `team_task_like` VALUES (130, '243f62d8ea5d4c2abbebb5951869baa7', 'd4692e58e8fc45b1b6a6423dc4d06b14', '2020-07-05 23:07:05');
INSERT INTO `team_task_like` VALUES (131, '91bdbf48cf8748629f61ad583087e8b2', 'd4692e58e8fc45b1b6a6423dc4d06b14', '2020-07-06 00:27:42');

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
) ENGINE = InnoDB AUTO_INCREMENT = 345 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务-成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_member
-- ----------------------------
INSERT INTO `team_task_member` VALUES (289, '8d4c1bde367f43ed9db2b7da13a059ae', 0, '6v7be19pwman2fird04gqu53', '2020-07-06 15:11:58', 1);
INSERT INTO `team_task_member` VALUES (290, '8d4c1bde367f43ed9db2b7da13a059ae', 0, '6d44c444965349ae926cd5be98912292', '2020-07-06 15:12:40', 0);
INSERT INTO `team_task_member` VALUES (292, '1b87f1d5642b4a549eeeffd4306d1b81', 1, 'bddc79203627409e9928b290b952ee88', '2020-07-06 15:42:14', 0);
INSERT INTO `team_task_member` VALUES (293, 'da7a9009fae44bb6a701ffbc5bab7f6b', 0, '6v7be19pwman2fird04gqu53', '2020-07-06 15:54:17', 1);
INSERT INTO `team_task_member` VALUES (294, '0375e80f2edf48e3835a8cd73a1532e1', 0, '6d44c444965349ae926cd5be98912292', '2020-07-06 15:55:01', 0);
INSERT INTO `team_task_member` VALUES (295, '59461dba170540c59679bff152de19a4', 1, '6v7be19pwman2fird04gqu53', '2020-07-06 16:49:51', 1);
INSERT INTO `team_task_member` VALUES (296, '46077d78315d443382eb86e44225e29a', 1, 'bddc79203627409e9928b290b952ee88', '2020-07-06 16:57:18', 0);
INSERT INTO `team_task_member` VALUES (297, '23e47dd7bcc54acebe0122bb2c939b76', 1, 'bddc79203627409e9928b290b952ee88', '2020-07-06 17:08:04', 0);
INSERT INTO `team_task_member` VALUES (298, 'dccf7dbf08fc4323bc124a948f6c6c74', 1, '6v7be19pwman2fird04gqu53', '2020-07-06 17:09:10', 0);
INSERT INTO `team_task_member` VALUES (299, 'c3f30c32f31b46a8b2b413ffb7f742d9', 1, '6d44c444965349ae926cd5be98912292', '2020-07-06 17:09:51', 0);
INSERT INTO `team_task_member` VALUES (300, '025fb21bea124f958b6dbe9f157ece00', 1, '6v7be19pwman2fird04gqu53', '2020-07-06 17:20:30', 1);
INSERT INTO `team_task_member` VALUES (302, '025fb21bea124f958b6dbe9f157ece00', 0, 'bddc79203627409e9928b290b952ee88', '2020-07-06 17:20:49', 0);
INSERT INTO `team_task_member` VALUES (303, '025fb21bea124f958b6dbe9f157ece00', 0, '6d44c444965349ae926cd5be98912292', '2020-07-06 17:22:21', 0);
INSERT INTO `team_task_member` VALUES (304, '211affd25de14f988c92d5c7b84bdcb4', 1, '6v7be19pwman2fird04gqu53', '2020-07-06 17:24:08', 1);
INSERT INTO `team_task_member` VALUES (305, '8d4c1bde367f43ed9db2b7da13a059ae', 0, 'bddc79203627409e9928b290b952ee88', '2020-07-09 10:36:50', 0);
INSERT INTO `team_task_member` VALUES (306, 'da7a9009fae44bb6a701ffbc5bab7f6b', 0, 'bddc79203627409e9928b290b952ee88', '2020-07-09 10:36:50', 0);
INSERT INTO `team_task_member` VALUES (307, '0375e80f2edf48e3835a8cd73a1532e1', 0, 'bddc79203627409e9928b290b952ee88', '2020-07-09 10:36:50', 0);
INSERT INTO `team_task_member` VALUES (308, '8d4c1bde367f43ed9db2b7da13a059ae', 1, 'e9b4bbe96f04474599c9f014ea1f47e7', '2020-07-09 10:39:59', 0);
INSERT INTO `team_task_member` VALUES (309, 'da7a9009fae44bb6a701ffbc5bab7f6b', 1, 'e9b4bbe96f04474599c9f014ea1f47e7', '2020-07-09 10:39:59', 0);
INSERT INTO `team_task_member` VALUES (310, '0375e80f2edf48e3835a8cd73a1532e1', 1, 'e9b4bbe96f04474599c9f014ea1f47e7', '2020-07-09 10:39:59', 0);
INSERT INTO `team_task_member` VALUES (311, 'da7a9009fae44bb6a701ffbc5bab7f6b', 0, '6d44c444965349ae926cd5be98912292', '2020-07-09 10:53:06', 0);
INSERT INTO `team_task_member` VALUES (312, '828f9ad33dc84aa4b878f74fa74317dd', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 14:30:04', 1);
INSERT INTO `team_task_member` VALUES (313, 'be6698b6bdcc4cf7a87e2a8338eccabb', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 15:09:39', 1);
INSERT INTO `team_task_member` VALUES (314, '37c256be26a24d86b4b4f3c9894b8eda', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 15:45:50', 1);
INSERT INTO `team_task_member` VALUES (315, '648b0d92913b4238b532a1ccfc46c534', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 16:42:47', 1);
INSERT INTO `team_task_member` VALUES (316, '3b65628ca15c41669c2b6bfb885d953d', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 16:42:51', 1);
INSERT INTO `team_task_member` VALUES (318, '6d8862e580b44bec82bdb373b66172ce', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:35:35', 1);
INSERT INTO `team_task_member` VALUES (319, '730bbbcb16d048faaed58417537e9c2c', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:42:06', 1);
INSERT INTO `team_task_member` VALUES (320, '69523f0f56d14b58a657d15f3e8e8e6d', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:42:19', 1);
INSERT INTO `team_task_member` VALUES (321, 'ec92afe944414c20a3ce30f5ed74cc7d', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:43:02', 1);
INSERT INTO `team_task_member` VALUES (322, '7dc3192213db45dbb49bb62e8d92aacd', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:43:16', 1);
INSERT INTO `team_task_member` VALUES (323, 'a1fcd15b001a42f9bbf2d33b75cd28a5', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:48:59', 1);
INSERT INTO `team_task_member` VALUES (324, '20ca7ed884d54d2291f172fd6ff1c0c1', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:11', 1);
INSERT INTO `team_task_member` VALUES (325, 'b98123b2f95741b9a595882050c75a3c', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:32', 1);
INSERT INTO `team_task_member` VALUES (326, 'cfba366ab35b43e794862690faa5c8df', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:35', 1);
INSERT INTO `team_task_member` VALUES (327, 'f5b8d37bbf2a4be091f8a4899ddc051a', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 21:49:44', 1);
INSERT INTO `team_task_member` VALUES (328, '32632e1ca4c2461dac80d6a32d1a4c44', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:10:02', 1);
INSERT INTO `team_task_member` VALUES (329, '1a4770cb15434abd8506a3a476ba0584', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:14:51', 1);
INSERT INTO `team_task_member` VALUES (330, '7b3e52cd6d1e4ef3a056a6775ed6cea8', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:19:02', 1);
INSERT INTO `team_task_member` VALUES (331, '9aa4adad0ff34ed793bf83d57e898060', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:20:24', 1);
INSERT INTO `team_task_member` VALUES (332, '122409550ae4486c9935d93e8b923e7d', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:20:46', 1);
INSERT INTO `team_task_member` VALUES (333, 'ed51394ab89642e6b7367b8beb21ae28', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:22:01', 1);
INSERT INTO `team_task_member` VALUES (334, 'b124f39bc9f14d3bb3c186a9a63d3991', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:22:03', 1);
INSERT INTO `team_task_member` VALUES (335, 'dcbaadbebcfe46fd9f77cb6ad0656cb5', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-01 22:22:05', 1);
INSERT INTO `team_task_member` VALUES (340, 'cc168a8cb4424f16983cbff00d464806', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-02 00:10:00', 1);
INSERT INTO `team_task_member` VALUES (341, '056556ee2e35482688a9c19ba88420f5', 0, 'bddc79203627409e9928b290b952ee88', '2021-02-02 00:10:06', 1);
INSERT INTO `team_task_member` VALUES (342, '36f5bebf257240e5848190fa213f99cf', 0, 'bddc79203627409e9928b290b952ee88', '2021-02-02 00:10:08', 1);
INSERT INTO `team_task_member` VALUES (343, '056556ee2e35482688a9c19ba88420f5', 1, 'e9b4bbe96f04474599c9f014ea1f47e7', '2021-02-02 00:10:14', 0);
INSERT INTO `team_task_member` VALUES (344, '36f5bebf257240e5848190fa213f99cf', 1, '6v7be19pwman2fird04gqu53', '2021-02-02 00:10:26', 0);
INSERT INTO `team_task_member` VALUES (345, '7155d6a6b6954cfda9a9175385346d4f', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-02 15:00:36', 1);
INSERT INTO `team_task_member` VALUES (346, '62086de33087479885e9a54f8a22808e', 1, 'bddc79203627409e9928b290b952ee88', '2021-02-02 17:09:57', 1);
INSERT INTO `team_task_member` VALUES (347, '96360501c4f9466db1194415772f4750', 1, '6v7be19pwman2fird04gqu53', '2021-02-02 17:12:47', 1);
INSERT INTO `team_task_member` VALUES (348, 'e9591ace4fc1451f9268283cc6021fbf', 1, '6v7be19pwman2fird04gqu53', '2021-02-02 17:17:59', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 263 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务列表表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_stages
-- ----------------------------
INSERT INTO `team_task_stages` VALUES (77, '待处理', 'oebw2ycf016j7pnxum5zikg4', 0, NULL, '2020-03-10 18:17:32', '7qs8hzgjrbuxcyn5vkltdow3', 0);
INSERT INTO `team_task_stages` VALUES (78, '进行中', 'oebw2ycf016j7pnxum5zikg4', 1, NULL, '2020-03-10 18:17:32', 'u4iy9s8p5lre0zcxngbqvfjd', 0);
INSERT INTO `team_task_stages` VALUES (79, '已完成', 'oebw2ycf016j7pnxum5zikg4', 2, NULL, '2020-03-10 18:17:32', 'fe0doi2vy5j839lwb6ashkcx', 0);
INSERT INTO `team_task_stages` VALUES (80, '待处理', 'nd5gkr9qc7wumei8zo630y41', 0, NULL, '2020-03-10 22:36:48', 'wyvtemnskxgadhz92pufcr5q', 0);
INSERT INTO `team_task_stages` VALUES (81, '进行中', 'nd5gkr9qc7wumei8zo630y41', 1, NULL, '2020-03-10 22:36:48', 'q3589jvendrzbla1mtf2coh6', 0);
INSERT INTO `team_task_stages` VALUES (82, '已完成', 'nd5gkr9qc7wumei8zo630y41', 2, NULL, '2020-03-10 22:36:48', 'e5ogyc7ml3kj6hwun2r1v8bt', 0);
INSERT INTO `team_task_stages` VALUES (83, '待处理', 'jiflz2v1oxswtdqma468bk97', 0, NULL, '2020-03-11 10:30:57', 'mv46stpqaho3r05bciljnwgy', 0);
INSERT INTO `team_task_stages` VALUES (84, '进行中', 'jiflz2v1oxswtdqma468bk97', 1, NULL, '2020-03-11 10:30:58', 'gtnv21y3m0iokpexslhfz5bu', 0);
INSERT INTO `team_task_stages` VALUES (85, '已完成', 'jiflz2v1oxswtdqma468bk97', 2, NULL, '2020-03-11 10:30:58', 'j3kvh5fqancer9tlgsxy80m6', 0);
INSERT INTO `team_task_stages` VALUES (86, '协议签订', '38xs2fyvbgdc61hqiplazr7k', 0, NULL, '2020-03-11 10:31:45', '2e3wr109fsljnugm8iyqxcha', 0);
INSERT INTO `team_task_stages` VALUES (87, '图纸设计', '38xs2fyvbgdc61hqiplazr7k', 1, NULL, '2020-03-11 10:31:45', 'pnoqew4kdvz2xb90lay5r8ut', 0);
INSERT INTO `team_task_stages` VALUES (88, '评审及打样', '38xs2fyvbgdc61hqiplazr7k', 2, NULL, '2020-03-11 10:31:45', 'i1mphqvf4dx637ot0y9kwzcu', 0);
INSERT INTO `team_task_stages` VALUES (89, '构件采购', '38xs2fyvbgdc61hqiplazr7k', 3, NULL, '2020-03-11 10:31:45', 'zr54tbuw218mpfegj6vlas0y', 0);
INSERT INTO `team_task_stages` VALUES (90, '制造安装', '38xs2fyvbgdc61hqiplazr7k', 4, NULL, '2020-03-11 10:31:45', '27ap0zklsut8nrvc1m5jbhqo', 0);
INSERT INTO `team_task_stages` VALUES (91, '内部检验', '38xs2fyvbgdc61hqiplazr7k', 5, NULL, '2020-03-11 10:31:45', '9oxj3u0t82zsrnqpa7ydcg14', 0);
INSERT INTO `team_task_stages` VALUES (92, '验收', '38xs2fyvbgdc61hqiplazr7k', 6, NULL, '2020-03-11 10:31:45', 'x9v8g0n1rf436eotiduqha5w', 0);
INSERT INTO `team_task_stages` VALUES (93, '协议签订', 'ol5bxfcrwz8p0jn12ihs3uaq', 0, NULL, '2020-05-27 22:30:36', 'mloz7kf42r5hgawqsby8ue1c', 0);
INSERT INTO `team_task_stages` VALUES (94, '图纸设计', 'ol5bxfcrwz8p0jn12ihs3uaq', 1, NULL, '2020-05-27 22:30:36', 'pz34i1o9fdtgnesv5k2ycxmw', 0);
INSERT INTO `team_task_stages` VALUES (95, '评审及打样', 'ol5bxfcrwz8p0jn12ihs3uaq', 2, NULL, '2020-05-27 22:30:36', 'mt4e7s6pr1u9bfa32n5jzg0h', 0);
INSERT INTO `team_task_stages` VALUES (96, '构件采购', 'ol5bxfcrwz8p0jn12ihs3uaq', 3, NULL, '2020-05-27 22:30:36', 'nc1vo8h4war3y06zxbutkdfl', 0);
INSERT INTO `team_task_stages` VALUES (97, '制造安装', 'ol5bxfcrwz8p0jn12ihs3uaq', 4, NULL, '2020-05-27 22:30:36', '6cy13tn8e0sjpglfbv4qd759', 0);
INSERT INTO `team_task_stages` VALUES (98, '内部检验', 'ol5bxfcrwz8p0jn12ihs3uaq', 5, NULL, '2020-05-27 22:30:36', 'bqcu9aom8k1xj50lzpfeidy4', 0);
INSERT INTO `team_task_stages` VALUES (99, '验收', 'ol5bxfcrwz8p0jn12ihs3uaq', 6, NULL, '2020-05-27 22:30:36', '72hy5608pzfqk13bovnidcja', 0);
INSERT INTO `team_task_stages` VALUES (100, '协议签订', '65a462912f9f473d9acfa99d98bcdef0', 0, NULL, '2020-05-27 23:08:01', '4ac7038e439744528e10f9c8e48c5bb1', 0);
INSERT INTO `team_task_stages` VALUES (101, '图纸设计', '65a462912f9f473d9acfa99d98bcdef0', 1, NULL, '2020-05-27 23:08:01', '6f34d90e1c054b3899955e4d6d6b6b3f', 0);
INSERT INTO `team_task_stages` VALUES (102, '评审及打样', '65a462912f9f473d9acfa99d98bcdef0', 2, NULL, '2020-05-27 23:08:01', 'c53d770bba214520ba615463b8035405', 0);
INSERT INTO `team_task_stages` VALUES (103, '构件采购', '65a462912f9f473d9acfa99d98bcdef0', 3, NULL, '2020-05-27 23:08:01', '20142b92fa4a4d439133b15a2f7978c7', 0);
INSERT INTO `team_task_stages` VALUES (104, '制造安装', '65a462912f9f473d9acfa99d98bcdef0', 4, NULL, '2020-05-27 23:08:01', '56884e825cbb41ad9aec5b7cd50370ed', 0);
INSERT INTO `team_task_stages` VALUES (105, '内部检验', '65a462912f9f473d9acfa99d98bcdef0', 5, NULL, '2020-05-27 23:08:01', 'aa9e93fe81454972896d6d7f84ef5fc9', 0);
INSERT INTO `team_task_stages` VALUES (106, '验收', '65a462912f9f473d9acfa99d98bcdef0', 6, NULL, '2020-05-27 23:08:01', '6c7027c3f5904c2585b6df5c207b3720', 0);
INSERT INTO `team_task_stages` VALUES (107, '协议签订', '9d88e6d9fef34999ab2218428f59062b', 0, NULL, '2020-05-28 00:30:45', '5f4f508387aa4e2da16431068c4a6386', 0);
INSERT INTO `team_task_stages` VALUES (108, '图纸设计', '9d88e6d9fef34999ab2218428f59062b', 1, NULL, '2020-05-28 00:30:46', '1f0ebdb59dc34aa48d43f68b52bec534', 0);
INSERT INTO `team_task_stages` VALUES (109, '评审及打样', '9d88e6d9fef34999ab2218428f59062b', 2, NULL, '2020-05-28 00:30:46', 'e6665d38256b4745aa615c6c614d4b1e', 0);
INSERT INTO `team_task_stages` VALUES (110, '构件采购', '9d88e6d9fef34999ab2218428f59062b', 3, NULL, '2020-05-28 00:30:46', '4bacb407ff194682b57b6ce0407c9d0f', 0);
INSERT INTO `team_task_stages` VALUES (111, '制造安装', '9d88e6d9fef34999ab2218428f59062b', 4, NULL, '2020-05-28 00:30:46', 'ba4fef493db7474386b0374a716857f2', 0);
INSERT INTO `team_task_stages` VALUES (112, '内部检验', '9d88e6d9fef34999ab2218428f59062b', 5, NULL, '2020-05-28 00:30:46', '2ac899d4b1b743c8b290e75f07a7f3aa', 0);
INSERT INTO `team_task_stages` VALUES (113, '验收', '9d88e6d9fef34999ab2218428f59062b', 6, NULL, '2020-05-28 00:30:46', '131dca6a33d5416eafe595c795f6fc3e', 0);
INSERT INTO `team_task_stages` VALUES (121, '待处理', '41c0e3a71c7048989152bf56f278895e', 0, NULL, '2020-05-28 00:41:46', '630d42bb48cd447e913992fb15bf5aae', 0);
INSERT INTO `team_task_stages` VALUES (122, '进行中', '41c0e3a71c7048989152bf56f278895e', 1, NULL, '2020-05-28 00:41:46', '78f0b42066d64ce580288bcee6d1560d', 0);
INSERT INTO `team_task_stages` VALUES (123, '已完成', '41c0e3a71c7048989152bf56f278895e', 2, NULL, '2020-05-28 00:41:46', '5bd397830dc44c269895c297b42f5644', 0);
INSERT INTO `team_task_stages` VALUES (124, '待处理', '4c2d9d0c0a3542798259a3cbe24a13ff', 0, NULL, '2020-05-28 00:45:43', '80c80de18cda476a8261c3a1e4ed0f26', 0);
INSERT INTO `team_task_stages` VALUES (125, '进行中', '4c2d9d0c0a3542798259a3cbe24a13ff', 1, NULL, '2020-05-28 00:45:43', 'cc66c9c3e14040778f64d375dadf2679', 0);
INSERT INTO `team_task_stages` VALUES (126, '已完成', '4c2d9d0c0a3542798259a3cbe24a13ff', 2, NULL, '2020-05-28 00:45:43', 'e37f422f6ba84ee2bfcc744466da8ac4', 0);
INSERT INTO `team_task_stages` VALUES (127, '协议签订', 'borhsewfgqxy38k1jmtznuv5', 0, NULL, '2020-05-28 00:48:00', 'btkpgas2d1on6qrhim9y540l', 0);
INSERT INTO `team_task_stages` VALUES (128, '图纸设计', 'borhsewfgqxy38k1jmtznuv5', 1, NULL, '2020-05-28 00:48:00', 'o32v9ypmzucs7ehbq5fdnxgk', 0);
INSERT INTO `team_task_stages` VALUES (129, '评审及打样', 'borhsewfgqxy38k1jmtznuv5', 2, NULL, '2020-05-28 00:48:00', 'utswi4omgnh07cqbpr28l6xa', 0);
INSERT INTO `team_task_stages` VALUES (130, '构件采购', 'borhsewfgqxy38k1jmtznuv5', 3, NULL, '2020-05-28 00:48:00', 'oanqyxsd3k46mc8guv09irew', 0);
INSERT INTO `team_task_stages` VALUES (131, '制造安装', 'borhsewfgqxy38k1jmtznuv5', 4, NULL, '2020-05-28 00:48:00', 'kzrdnqjg3f576lpvhyxs8w1o', 0);
INSERT INTO `team_task_stages` VALUES (132, '内部检验', 'borhsewfgqxy38k1jmtznuv5', 5, NULL, '2020-05-28 00:48:00', '73uivpeh9fxy5w2n4j1qkszb', 0);
INSERT INTO `team_task_stages` VALUES (133, '验收', 'borhsewfgqxy38k1jmtznuv5', 6, NULL, '2020-05-28 00:48:00', 'me2y4nbv36g1d8ur70xosc9l', 0);
INSERT INTO `team_task_stages` VALUES (134, '待处理', '11c80c7fabd146ca95d24621124f61b4', 0, NULL, '2020-06-29 23:17:32', '6043a6338e824c9ba91d3690a833e5e5', 0);
INSERT INTO `team_task_stages` VALUES (135, '进行中', '11c80c7fabd146ca95d24621124f61b4', 1, NULL, '2020-06-29 23:17:32', '149992994065493d94427ce2a7ce358f', 0);
INSERT INTO `team_task_stages` VALUES (136, '已完成', '11c80c7fabd146ca95d24621124f61b4', 2, NULL, '2020-06-29 23:17:32', '75cf84aca8d848dbb79d5e982d3bcdec', 0);
INSERT INTO `team_task_stages` VALUES (137, '待处理', '468e736e7dbd4693a40e89fb8fc9dd5e', 0, NULL, '2020-07-01 17:26:33', '39c23a57be754cb3800dfb188c21bde5', 0);
INSERT INTO `team_task_stages` VALUES (138, '进行中', '468e736e7dbd4693a40e89fb8fc9dd5e', 1, NULL, '2020-07-01 17:26:33', '7572ddd66cd646c9b2280e5bc468a182', 0);
INSERT INTO `team_task_stages` VALUES (139, '已完成', '468e736e7dbd4693a40e89fb8fc9dd5e', 2, NULL, '2020-07-01 17:26:33', 'e7aac9b7183f4f47843a8fbb22c198b1', 0);
INSERT INTO `team_task_stages` VALUES (140, '待处理', 'e4183b8c7d1441ae86592b1b9af72f05', 0, NULL, '2020-07-01 17:26:52', '309e69dc6469404c9bfade60f0fad121', 0);
INSERT INTO `team_task_stages` VALUES (141, '进行中', 'e4183b8c7d1441ae86592b1b9af72f05', 1, NULL, '2020-07-01 17:26:52', '31f5824c20a9481894b41af04916e275', 0);
INSERT INTO `team_task_stages` VALUES (142, '已完成', 'e4183b8c7d1441ae86592b1b9af72f05', 2, NULL, '2020-07-01 17:26:52', 'add3f2712f6646c79fd75b51711cbbfd', 0);
INSERT INTO `team_task_stages` VALUES (145, 'test', 'jiflz2v1oxswtdqma468bk97', 145, NULL, '2020-07-02 15:28:00', 'c764f1eaf7474d9784a3a3a160da7b0b', 1);
INSERT INTO `team_task_stages` VALUES (146, '新建列表', '468e736e7dbd4693a40e89fb8fc9dd5e', 146, NULL, '2020-07-03 18:39:17', '3cfbdac34518451abe63c37e14d53cf4', 0);
INSERT INTO `team_task_stages` VALUES (150, '待处理', 'eb8bfddb03ec42d2af05e5f418e46259', 0, NULL, '2020-07-03 12:03:35', 'cce4840f66ed4637a2380263016329b7', 0);
INSERT INTO `team_task_stages` VALUES (151, '进行中', 'eb8bfddb03ec42d2af05e5f418e46259', 1, NULL, '2020-07-03 12:03:35', '8a7e0817c26140a48bac1cf6a0fb92c4', 0);
INSERT INTO `team_task_stages` VALUES (152, '已完成', 'eb8bfddb03ec42d2af05e5f418e46259', 2, NULL, '2020-07-03 12:03:35', '0060a91072b84a4fa3ca0cd9fdd2308b', 0);
INSERT INTO `team_task_stages` VALUES (153, '待处理', '6d54506b13a947f58895eec8db465e7e', 0, NULL, '2020-07-03 22:35:16', '53a8bfa213b348108271e5a2be0ea36d', 0);
INSERT INTO `team_task_stages` VALUES (154, '进行中', '6d54506b13a947f58895eec8db465e7e', 1, NULL, '2020-07-03 22:35:16', '8ff36d3491ed41e6b7b50e0eea3e8131', 0);
INSERT INTO `team_task_stages` VALUES (155, '已完成', '6d54506b13a947f58895eec8db465e7e', 2, NULL, '2020-07-03 22:35:16', '1315ee7c5fe144e795479ff38778fc90', 0);
INSERT INTO `team_task_stages` VALUES (156, '测试列表', '6d54506b13a947f58895eec8db465e7e', 156, NULL, '2020-07-04 01:25:31', '054255d177044663b88eccf2ce0fd0b0', 0);
INSERT INTO `team_task_stages` VALUES (159, '待处理', '6db53e1cfe4c4a4d82b95914b98b1c64', 0, NULL, '2020-07-04 17:18:52', 'ba8e4de8400740d1b479f080a78ab058', 0);
INSERT INTO `team_task_stages` VALUES (160, '进行中', '6db53e1cfe4c4a4d82b95914b98b1c64', 1, NULL, '2020-07-04 17:18:52', 'a77c1057dab7468790a2b37f88487a0f', 0);
INSERT INTO `team_task_stages` VALUES (161, '已完成', '6db53e1cfe4c4a4d82b95914b98b1c64', 2, NULL, '2020-07-04 17:18:52', '385a834fda7f45df877d016af7fa6252', 0);
INSERT INTO `team_task_stages` VALUES (162, '产品计划', '7e356db428c74e3caba617991e4bbcef', 0, NULL, '2020-07-04 17:35:56', '4c4e5292756e4bb0a9aecbbaf5ccc1f6', 0);
INSERT INTO `team_task_stages` VALUES (163, '即将发布', '7e356db428c74e3caba617991e4bbcef', 1, NULL, '2020-07-04 17:35:56', 'e7092266e82f4497b132dbdc673bb07c', 0);
INSERT INTO `team_task_stages` VALUES (164, '测试', '7e356db428c74e3caba617991e4bbcef', 2, NULL, '2020-07-04 17:35:56', '13b78f29821e4e1d906de0d031be40f8', 0);
INSERT INTO `team_task_stages` VALUES (165, '准备发布', '7e356db428c74e3caba617991e4bbcef', 3, NULL, '2020-07-04 17:35:56', 'f55f90b15fe1487e89f804956e2dd618', 0);
INSERT INTO `team_task_stages` VALUES (166, '发布成功', '7e356db428c74e3caba617991e4bbcef', 4, NULL, '2020-07-04 17:35:56', '05bdbe4ed16544d0905d5cc5c40f5b14', 0);
INSERT INTO `team_task_stages` VALUES (167, '一组任务', '12b5fb56e28749eabe46c276308f6d15', 0, NULL, '2020-07-05 21:58:05', '2a73722111404ed79ac4ac459f0f2afb', 0);
INSERT INTO `team_task_stages` VALUES (168, '二组任务', '12b5fb56e28749eabe46c276308f6d15', 1, NULL, '2020-07-05 21:58:05', '178d29fede204125a6aca8377af5fdd8', 0);
INSERT INTO `team_task_stages` VALUES (169, '三组任务', '12b5fb56e28749eabe46c276308f6d15', 2, NULL, '2020-07-05 21:58:05', '8de0bfb68733409a9000586349be448e', 0);
INSERT INTO `team_task_stages` VALUES (170, '暂缓执行', '12b5fb56e28749eabe46c276308f6d15', 170, NULL, '2020-07-05 22:25:07', 'd51881224e814cf2b572b8375169d22c', 0);
INSERT INTO `team_task_stages` VALUES (171, '一组处理', 'fe94d5823cf74eccbd4492aa71e2755a', 0, NULL, '2020-07-05 22:28:06', '383bb4696abf49199e794302df6aa0f1', 0);
INSERT INTO `team_task_stages` VALUES (172, '二组处理', 'fe94d5823cf74eccbd4492aa71e2755a', 1, NULL, '2020-07-05 22:28:06', 'e0c8a3a93ebb49b0903132b80f72d75a', 0);
INSERT INTO `team_task_stages` VALUES (173, '三组处理', 'fe94d5823cf74eccbd4492aa71e2755a', 2, NULL, '2020-07-05 22:28:06', '0f02da63e3cf45068a797a65d87cb19e', 0);
INSERT INTO `team_task_stages` VALUES (174, '暂缓任务', 'fe94d5823cf74eccbd4492aa71e2755a', 3, NULL, '2020-07-05 22:28:06', 'f2a6fb252b7f487cb277cd16c6c1642d', 0);
INSERT INTO `team_task_stages` VALUES (175, '需求收集', 'd4f542577ac4406eaaac4ff4d1bc195e', 0, NULL, '2020-07-05 22:30:11', '4661ac87a18c4875b7d07788a9c44337', 0);
INSERT INTO `team_task_stages` VALUES (176, '评估确认', 'd4f542577ac4406eaaac4ff4d1bc195e', 1, NULL, '2020-07-05 22:30:11', 'eb1f8c45a7ac43bda96aa1c22728b42c', 0);
INSERT INTO `team_task_stages` VALUES (177, '需求暂缓', 'd4f542577ac4406eaaac4ff4d1bc195e', 2, NULL, '2020-07-05 22:30:11', 'f0c5b741ebff427d97ce2c22d8a77da3', 0);
INSERT INTO `team_task_stages` VALUES (178, '研发中', 'd4f542577ac4406eaaac4ff4d1bc195e', 3, NULL, '2020-07-05 22:30:11', '09aa507b96ca4880ba92e3946dc7767c', 0);
INSERT INTO `team_task_stages` VALUES (179, '内测中', 'd4f542577ac4406eaaac4ff4d1bc195e', 4, NULL, '2020-07-05 22:30:11', 'd317234027b24c94b244b755b7ace724', 0);
INSERT INTO `team_task_stages` VALUES (180, '通知用户', 'd4f542577ac4406eaaac4ff4d1bc195e', 5, NULL, '2020-07-05 22:30:11', 'a8f57803cf434d05afc3e4ef3fc54595', 0);
INSERT INTO `team_task_stages` VALUES (181, '已完成&归档', 'd4f542577ac4406eaaac4ff4d1bc195e', 6, NULL, '2020-07-05 22:30:11', '4fe2bade32474557b510e9b0143e1d25', 0);
INSERT INTO `team_task_stages` VALUES (182, '一组处理', '7109c15a1dcc4292b2514d91842d9463', 0, NULL, '2020-07-06 00:43:54', '44c44a2e73114a7f83cb227dd12030cc', 0);
INSERT INTO `team_task_stages` VALUES (183, '二组处理', '7109c15a1dcc4292b2514d91842d9463', 1, NULL, '2020-07-06 00:43:54', '493aed6812b74e75985ca01720a10e8e', 0);
INSERT INTO `team_task_stages` VALUES (184, '三组处理', '7109c15a1dcc4292b2514d91842d9463', 2, NULL, '2020-07-06 00:43:54', '0d2f5e38290c488999eccb54c26a6f04', 0);
INSERT INTO `team_task_stages` VALUES (185, '暂缓任务', '7109c15a1dcc4292b2514d91842d9463', 3, NULL, '2020-07-06 00:43:54', '7da6cfebf86843768a2e8428326dfc76', 0);
INSERT INTO `team_task_stages` VALUES (186, '待处理', '002a636f06e5488b80fe266762acb4d6', 0, NULL, '2020-07-05 19:53:38', '80bd774041194555b792c719e1aba950', 0);
INSERT INTO `team_task_stages` VALUES (187, '进行中', '002a636f06e5488b80fe266762acb4d6', 1, NULL, '2020-07-05 19:53:38', 'ae0c203a7790477fba48041d819a3e49', 0);
INSERT INTO `team_task_stages` VALUES (188, '已完成', '002a636f06e5488b80fe266762acb4d6', 2, NULL, '2020-07-05 19:53:38', '4129166c5574429ca8e94781586babe6', 0);
INSERT INTO `team_task_stages` VALUES (189, '待处理', '7fc135d9be2f4076b7286e497399014f', 0, NULL, '2020-07-05 20:57:39', 'b9a02d64d8d94762ba58e29b93aff0e6', 0);
INSERT INTO `team_task_stages` VALUES (190, '进行中', '7fc135d9be2f4076b7286e497399014f', 1, NULL, '2020-07-05 20:57:40', '9650b14c205240d6890872190ed8d777', 0);
INSERT INTO `team_task_stages` VALUES (191, '已完成', '7fc135d9be2f4076b7286e497399014f', 2, NULL, '2020-07-05 20:57:40', 'ef0bd17bf68746769c6a27415d327725', 0);
INSERT INTO `team_task_stages` VALUES (192, '待处理', 'd1b35fdb678242efbad4e700e712afb1', 0, NULL, '2020-07-05 22:22:51', '33fc93321a2942528d0bd467e1c1a651', 0);
INSERT INTO `team_task_stages` VALUES (193, '进行中', 'd1b35fdb678242efbad4e700e712afb1', 1, NULL, '2020-07-05 22:22:51', '44becc8f1ce248ad99f9f7f0a28283d4', 0);
INSERT INTO `team_task_stages` VALUES (194, '已完成', 'd1b35fdb678242efbad4e700e712afb1', 2, NULL, '2020-07-05 22:22:51', 'cf428b2e83a24fa497e92bba6bd4bcce', 0);
INSERT INTO `team_task_stages` VALUES (195, '待处理', '50c9cebd310a4461a8355a8e2ebd74cf', 0, NULL, '2020-07-05 22:49:07', '3f881348e453460aa1b15d8413034f53', 0);
INSERT INTO `team_task_stages` VALUES (196, '进行中', '50c9cebd310a4461a8355a8e2ebd74cf', 1, NULL, '2020-07-05 22:49:07', '61fbe07b667f41809d26cedfb03e0531', 0);
INSERT INTO `team_task_stages` VALUES (197, '已完成', '50c9cebd310a4461a8355a8e2ebd74cf', 2, NULL, '2020-07-05 22:49:07', '9e2b2dd68cfb49cc839972921778b313', 0);
INSERT INTO `team_task_stages` VALUES (198, '待处理', 'e5b3217981104973a3b14d37f3c9133e', 0, NULL, '2020-07-05 23:05:28', '7332fb079ddc4452b2bc3f851e2d5d46', 0);
INSERT INTO `team_task_stages` VALUES (199, '进行中', 'e5b3217981104973a3b14d37f3c9133e', 1, NULL, '2020-07-05 23:05:28', '0977636446fa486c84d2002ae911f963', 0);
INSERT INTO `team_task_stages` VALUES (200, '已完成', 'e5b3217981104973a3b14d37f3c9133e', 2, NULL, '2020-07-05 23:05:28', '87acb5086a914c1cb2564d2203e511b6', 0);
INSERT INTO `team_task_stages` VALUES (201, '待处理', 'edba7252bddd447ba0a44485d886dd0a', 0, NULL, '2020-07-06 10:20:45', '5c20dfe1872d47b3be3b6a93225f14a7', 0);
INSERT INTO `team_task_stages` VALUES (202, '进行中', 'edba7252bddd447ba0a44485d886dd0a', 1, NULL, '2020-07-06 10:20:45', '68043b9d3b624c49bd63aa96b56eef22', 0);
INSERT INTO `team_task_stages` VALUES (203, '已完成', 'edba7252bddd447ba0a44485d886dd0a', 2, NULL, '2020-07-06 10:20:45', 'f906f3343d6f4823805a34e43d6e85e9', 0);
INSERT INTO `team_task_stages` VALUES (204, '任务分组1', '8c4f887129e54068996e2d10a1c3bac9', 0, NULL, '2020-07-06 10:22:43', 'e558aeb440494ed2828368a1634f2c09', 0);
INSERT INTO `team_task_stages` VALUES (205, '任务分组2', '8c4f887129e54068996e2d10a1c3bac9', 1, NULL, '2020-07-06 10:22:43', '0669fbf3380c486390f4edf8962cb7cf', 0);
INSERT INTO `team_task_stages` VALUES (206, '任务分组3', '8c4f887129e54068996e2d10a1c3bac9', 2, NULL, '2020-07-06 10:22:43', '3d2b44fbf63f49498746469438f3971a', 0);
INSERT INTO `team_task_stages` VALUES (207, '待处理', '78bcc133d0cc4885975ebabeabb97ffa', 0, NULL, '2020-07-06 10:51:24', '213eaf9064bb45309b0e1d9f6aca14fc', 0);
INSERT INTO `team_task_stages` VALUES (208, '进行中', '78bcc133d0cc4885975ebabeabb97ffa', 1, NULL, '2020-07-06 10:51:24', 'eb942b1fd5b84c0db4c331b7ed88b5c7', 0);
INSERT INTO `team_task_stages` VALUES (209, '已完成', '78bcc133d0cc4885975ebabeabb97ffa', 2, NULL, '2020-07-06 10:51:24', '31745ede896f4816916125a23f230520', 0);
INSERT INTO `team_task_stages` VALUES (210, '待处理', 'c70dbd8001e04e96b838309915f790ea', 0, NULL, '2020-07-06 11:08:29', 'a213c4bfeb8947359b4a429e0ebb6c81', 0);
INSERT INTO `team_task_stages` VALUES (211, '进行中', 'c70dbd8001e04e96b838309915f790ea', 1, NULL, '2020-07-06 11:08:29', 'ba0fd090de0a41328d3c9875263b1ec6', 0);
INSERT INTO `team_task_stages` VALUES (212, '已完成', 'c70dbd8001e04e96b838309915f790ea', 2, NULL, '2020-07-06 11:08:29', 'cd7fe24e1e8944059259b7691a923435', 0);
INSERT INTO `team_task_stages` VALUES (213, '任务分组1', '3488bba47b8e48fc9cc75f5e5580cfb4', 0, NULL, '2020-07-06 17:07:26', '67553773f3c245c0abbd3a58ddbbe374', 0);
INSERT INTO `team_task_stages` VALUES (214, '任务分组2', '3488bba47b8e48fc9cc75f5e5580cfb4', 1, NULL, '2020-07-06 17:07:26', '9efeaa61ef274457922a8a10b77e13f6', 0);
INSERT INTO `team_task_stages` VALUES (215, '任务分组3', '3488bba47b8e48fc9cc75f5e5580cfb4', 2, NULL, '2020-07-06 17:07:26', 'e9c7e444dac549419f701aacc1947008', 0);
INSERT INTO `team_task_stages` VALUES (216, '任务分组4', '3488bba47b8e48fc9cc75f5e5580cfb4', 3, NULL, '2020-07-06 17:07:26', 'c1eaf52db37c4ff0820e9128afc2a418', 0);
INSERT INTO `team_task_stages` VALUES (217, '产品计划', '813a9c8b199e4dc59486cf6468ab91af', 0, NULL, '2021-02-01 11:54:03', '60115e7c99bf43568e0b71bf35a9c760', 0);
INSERT INTO `team_task_stages` VALUES (218, '即将发布', '813a9c8b199e4dc59486cf6468ab91af', 1, NULL, '2021-02-01 11:54:03', '60b2527cbe1d4616aeae1c314e83ca23', 0);
INSERT INTO `team_task_stages` VALUES (219, '测试', '813a9c8b199e4dc59486cf6468ab91af', 2, NULL, '2021-02-01 11:54:03', '1b6c700558fd4eca9966392912a60c08', 0);
INSERT INTO `team_task_stages` VALUES (220, '准备发布', '813a9c8b199e4dc59486cf6468ab91af', 3, NULL, '2021-02-01 11:54:03', '1485bcdeb87148a6be7633c638a43746', 0);
INSERT INTO `team_task_stages` VALUES (221, '发布成功', '813a9c8b199e4dc59486cf6468ab91af', 4, NULL, '2021-02-01 11:54:03', '5b185d6e575b4af49f14379bb51666bb', 0);
INSERT INTO `team_task_stages` VALUES (222, '产品计划', '4d619572638b41b29e4e9d9b93c0fc6e', 0, NULL, '2021-02-01 15:44:04', '2ae3ca02aef842f38166e29a16d86542', 0);
INSERT INTO `team_task_stages` VALUES (223, '即将发布', '4d619572638b41b29e4e9d9b93c0fc6e', 1, NULL, '2021-02-01 15:44:04', 'c13f42ed77634b318d6c369292610f9b', 0);
INSERT INTO `team_task_stages` VALUES (224, '测试', '4d619572638b41b29e4e9d9b93c0fc6e', 2, NULL, '2021-02-01 15:44:04', '38111a960ac646599f53b081c0b85eba', 0);
INSERT INTO `team_task_stages` VALUES (225, '准备发布', '4d619572638b41b29e4e9d9b93c0fc6e', 3, NULL, '2021-02-01 15:44:04', 'a2d70683072f4fb1a8cd5f1ab762c57f', 0);
INSERT INTO `team_task_stages` VALUES (226, '发布成功', '4d619572638b41b29e4e9d9b93c0fc6e', 4, NULL, '2021-02-01 15:44:04', '7b25931a3f33417fb2644038ca1cf5bf', 0);
INSERT INTO `team_task_stages` VALUES (227, '待处理', '4f2619f1d1c64a389a2ba671e7fca2ed', 0, NULL, '2021-02-01 16:07:02', '05abd94593464ca1bea7e1f7b814ba1e', 0);
INSERT INTO `team_task_stages` VALUES (228, '进行中', '4f2619f1d1c64a389a2ba671e7fca2ed', 1, NULL, '2021-02-01 16:07:02', '1528fcb63b1741578da5a08cf057eb5b', 0);
INSERT INTO `team_task_stages` VALUES (229, '已完成', '4f2619f1d1c64a389a2ba671e7fca2ed', 2, NULL, '2021-02-01 16:07:02', '47b1b6e30e624b08b6402951a39bcee4', 0);
INSERT INTO `team_task_stages` VALUES (230, '产品计划', 'd0e82a94783a4ee7881f6c342f990bc5', 0, NULL, '2021-02-01 16:42:39', '79faa5c153db4ff19c9502badfc46c47', 0);
INSERT INTO `team_task_stages` VALUES (231, '即将发布', 'd0e82a94783a4ee7881f6c342f990bc5', 1, NULL, '2021-02-01 16:42:39', 'de81fcdf4b0042e2b710a4164e0ba68a', 0);
INSERT INTO `team_task_stages` VALUES (232, '测试', 'd0e82a94783a4ee7881f6c342f990bc5', 2, NULL, '2021-02-01 16:42:39', '448a99e0ceda46a38e94a546cd781b1b', 0);
INSERT INTO `team_task_stages` VALUES (233, '准备发布', 'd0e82a94783a4ee7881f6c342f990bc5', 3, NULL, '2021-02-01 16:42:39', '97158cc977274a9992fe96b3017feae8', 0);
INSERT INTO `team_task_stages` VALUES (234, '发布成功', 'd0e82a94783a4ee7881f6c342f990bc5', 4, NULL, '2021-02-01 16:42:39', '0ad2a58f73104bb3ba910dc913d4aa71', 0);
INSERT INTO `team_task_stages` VALUES (235, '产品计划', '81d43786f82c4c84baf34c6eb9ecac78', 0, NULL, '2021-02-01 18:25:43', 'ef88b9d41aa84371989f00efcde1992f', 0);
INSERT INTO `team_task_stages` VALUES (236, '即将发布', '81d43786f82c4c84baf34c6eb9ecac78', 1, NULL, '2021-02-01 18:25:44', '1fbfa8057d3e4e8f964abf3725afd6db', 0);
INSERT INTO `team_task_stages` VALUES (237, '测试', '81d43786f82c4c84baf34c6eb9ecac78', 2, NULL, '2021-02-01 18:25:44', 'a6e17552fd784b88b29e854ad172a921', 0);
INSERT INTO `team_task_stages` VALUES (238, '准备发布', '81d43786f82c4c84baf34c6eb9ecac78', 3, NULL, '2021-02-01 18:25:44', '116af7628d594110980dd90a57e833dc', 0);
INSERT INTO `team_task_stages` VALUES (239, '发布成功', '81d43786f82c4c84baf34c6eb9ecac78', 4, NULL, '2021-02-01 18:25:44', '41c8e69bdccf403ea8016d8ead4651f1', 0);
INSERT INTO `team_task_stages` VALUES (240, '交付文档进度-报审', 'a3c56e1116ff42b780fa8c16afc76311', 0, NULL, '2021-02-01 21:33:17', 'd7780f1f0c6f4ae091b0ad1c5ce678f1', 0);
INSERT INTO `team_task_stages` VALUES (245, '待处理', 'd99cbb2012ce413bab0cde5866fa5041', 0, NULL, '2021-02-01 21:55:31', '57562ae4eddb441ebc5db0fa9d76e79b', 0);
INSERT INTO `team_task_stages` VALUES (246, '进行中', 'd99cbb2012ce413bab0cde5866fa5041', 1, NULL, '2021-02-01 21:55:31', 'f7f7be9fd6cd4acaba3bf770383f54b4', 0);
INSERT INTO `team_task_stages` VALUES (247, '已完成', 'd99cbb2012ce413bab0cde5866fa5041', 2, NULL, '2021-02-01 21:55:31', 'e7e6d6b3ad544a978a8f2593293754c4', 0);
INSERT INTO `team_task_stages` VALUES (248, '待处理', '9749247d6ae44e8abeadac35d9f46ba0', 0, NULL, '2021-02-01 21:56:16', '0ceb3dffc1384497a99fa2af5703e29a', 0);
INSERT INTO `team_task_stages` VALUES (249, '进行中', '9749247d6ae44e8abeadac35d9f46ba0', 1, NULL, '2021-02-01 21:56:16', '21b8c09e9cd941e29cf908a331590147', 0);
INSERT INTO `team_task_stages` VALUES (250, '已完成', '9749247d6ae44e8abeadac35d9f46ba0', 2, NULL, '2021-02-01 21:56:16', '6050b0dcb2a44c709e79d8bd664bb031', 0);
INSERT INTO `team_task_stages` VALUES (251, '待处理', 'd2076c5f961b4bfe915906cbd4c773d3', 0, NULL, '2021-02-01 21:56:41', 'b6ad76fc673848369dff1bf26be0f06f', 0);
INSERT INTO `team_task_stages` VALUES (252, '进行中', 'd2076c5f961b4bfe915906cbd4c773d3', 1, NULL, '2021-02-01 21:56:41', '06e506af08a34755b3bdb257c7352e91', 0);
INSERT INTO `team_task_stages` VALUES (253, '已完成', 'd2076c5f961b4bfe915906cbd4c773d3', 2, NULL, '2021-02-01 21:56:41', 'd468ac19366e42d6ba356724bdcd3c69', 0);
INSERT INTO `team_task_stages` VALUES (254, '交付文档进度-不报审', 'a3c56e1116ff42b780fa8c16afc76311', 254, NULL, '2021-02-01 22:21:31', 'd40dff2311894e098e7c92ae9aa85008', 0);
INSERT INTO `team_task_stages` VALUES (255, '审核', 'a3c56e1116ff42b780fa8c16afc76311', 255, NULL, '2021-02-02 00:07:22', '03a28e00d0c34a7c9d3be446db0e5c02', 0);
INSERT INTO `team_task_stages` VALUES (256, '待处理', '88435aab6cad403788275282852d61a6', 0, NULL, '2021-02-02 08:41:06', '075eb7a13ef54fb088b7e18862132fc4', 0);
INSERT INTO `team_task_stages` VALUES (257, '进行中', '88435aab6cad403788275282852d61a6', 1, NULL, '2021-02-02 08:41:06', '38df05694b644570b8dcc4fd72d05813', 0);
INSERT INTO `team_task_stages` VALUES (258, '已完成', '88435aab6cad403788275282852d61a6', 2, NULL, '2021-02-02 08:41:06', '5cb7f3a8a6a34b9da9da10b7d5977495', 0);
INSERT INTO `team_task_stages` VALUES (259, '任务分组1', 'f35e6d3ef3e04b5bb532039947b30eae', 0, NULL, '2021-02-02 10:52:50', '5164a2cec00644b490513ca349a17563', 0);
INSERT INTO `team_task_stages` VALUES (260, '任务分组2', 'f35e6d3ef3e04b5bb532039947b30eae', 1, NULL, '2021-02-02 10:52:50', '2deede67bdba4a0c86f858377729ffd4', 0);
INSERT INTO `team_task_stages` VALUES (261, '任务分组3', 'f35e6d3ef3e04b5bb532039947b30eae', 2, NULL, '2021-02-02 10:52:50', '4d3a9f02facc4ea09c89a0559983b770', 0);
INSERT INTO `team_task_stages` VALUES (262, '任务分组4', 'f35e6d3ef3e04b5bb532039947b30eae', 3, NULL, '2021-02-02 10:52:50', 'b2003ce6c5304e8490e3755fc4437e3b', 0);
INSERT INTO `team_task_stages` VALUES (263, '任务分组1', '0ecde82e3bed4789a8e201621ba79d9f', 0, NULL, '2021-02-02 16:29:51', '241eeb9ddd264e038aaa1dde813721df', 0);
INSERT INTO `team_task_stages` VALUES (264, '任务分组2', '0ecde82e3bed4789a8e201621ba79d9f', 1, NULL, '2021-02-02 16:29:51', 'bba60811db994418af921048439cf873', 0);
INSERT INTO `team_task_stages` VALUES (265, '任务分组3', '0ecde82e3bed4789a8e201621ba79d9f', 2, NULL, '2021-02-02 16:29:51', '954a7b5d9e5e4fa39049bf26db836764', 0);
INSERT INTO `team_task_stages` VALUES (266, '任务分组4', '0ecde82e3bed4789a8e201621ba79d9f', 3, NULL, '2021-02-02 16:29:52', '754428c9f89a42c793114d8c528611ed', 0);
INSERT INTO `team_task_stages` VALUES (267, '任务分组1', '74228c6bfd6040d3b00f934cd0b0014f', 0, NULL, '2021-02-02 17:03:03', '2ee08f8684604439808ebbf77f1df151', 0);
INSERT INTO `team_task_stages` VALUES (268, '任务分组2', '74228c6bfd6040d3b00f934cd0b0014f', 1, NULL, '2021-02-02 17:03:03', '776142f8b79d4ce08a63ba822caddd2d', 0);
INSERT INTO `team_task_stages` VALUES (269, '任务分组3', '74228c6bfd6040d3b00f934cd0b0014f', 2, NULL, '2021-02-02 17:03:03', '5f5f5185f9f8406183f7c00cef8be617', 0);
INSERT INTO `team_task_stages` VALUES (270, '任务分组4', '74228c6bfd6040d3b00f934cd0b0014f', 3, NULL, '2021-02-02 17:03:03', '9ac875b7703244c981fea1f414563811', 0);
INSERT INTO `team_task_stages` VALUES (271, '任务分组1', 'cf50726e99d746fa88107ed355d4a8cc', 0, NULL, '2021-02-02 17:06:18', '97af4b39f6fc472d97165308b1cd55b5', 0);
INSERT INTO `team_task_stages` VALUES (272, '任务分组2', 'cf50726e99d746fa88107ed355d4a8cc', 1, NULL, '2021-02-02 17:06:18', '56392cfefd3a4b8baf1059031b9e899b', 0);
INSERT INTO `team_task_stages` VALUES (273, '任务分组3', 'cf50726e99d746fa88107ed355d4a8cc', 2, NULL, '2021-02-02 17:06:18', '5e5af9ee093e4e3883e1a3cac8457e39', 0);
INSERT INTO `team_task_stages` VALUES (274, '任务分组4', 'cf50726e99d746fa88107ed355d4a8cc', 3, NULL, '2021-02-02 17:06:18', '849f7077889b47a7ad802b6327810f92', 0);
INSERT INTO `team_task_stages` VALUES (275, '待处理', 'd17947344cc1474499b1b43537e61c8c', 0, NULL, '2021-02-02 18:10:45', '6490bc9f5292467aaadd18fb41d26b22', 0);
INSERT INTO `team_task_stages` VALUES (276, '进行中', 'd17947344cc1474499b1b43537e61c8c', 1, NULL, '2021-02-02 18:10:45', '1b4e254c461c421cb1da670a290b52bd', 0);
INSERT INTO `team_task_stages` VALUES (277, '已完成', 'd17947344cc1474499b1b43537e61c8c', 2, NULL, '2021-02-02 18:10:45', '38393097c0774956825e705e1aa6f8bf', 0);

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
INSERT INTO `team_task_stages_template` VALUES (65, '协议签订', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:00:33', 0, '4510enfyvjtzho3cw28xsagi');
INSERT INTO `team_task_stages_template` VALUES (66, '图纸设计', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:00:38', 0, '3esu4p172alok89wjmrvqihz');
INSERT INTO `team_task_stages_template` VALUES (67, '评审及打样', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:00:43', 0, 'e6jp81o7drfkzluxbhmiaqtv');
INSERT INTO `team_task_stages_template` VALUES (68, '构件采购', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:00:52', 0, 'tpy76njoair0clhz9xmeg482');
INSERT INTO `team_task_stages_template` VALUES (69, '制造安装', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:00:58', 0, 've97pldtbnjrqco1hyfx82sa');
INSERT INTO `team_task_stages_template` VALUES (70, '内部检验', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:01:04', 0, '4phrcltwygziu2s13jxbaqv8');
INSERT INTO `team_task_stages_template` VALUES (71, '验收', 'd85f1bvwpml2nhxe91zu7tyi', '2018-12-24 22:01:09', 0, 'qxi9n42p0w57jtrmyhz8gl3c');
INSERT INTO `team_task_stages_template` VALUES (72, '需求收集', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:01:30', 0, '48h13usk7en6ljyxbqgiw02z');
INSERT INTO `team_task_stages_template` VALUES (73, '评估确认', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:17', 0, '70z1fpxytvchbadkgsieowuj');
INSERT INTO `team_task_stages_template` VALUES (74, '需求暂缓', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:22', 0, 'bkyunf9jr2c37m4oi81sxzqp');
INSERT INTO `team_task_stages_template` VALUES (75, '研发中', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:27', 0, 'zu0vrhpoi835klgxqndmf6w9');
INSERT INTO `team_task_stages_template` VALUES (76, '内测中', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:32', 0, 'j4d5l7s6rgvk9o32ayt1uefc');
INSERT INTO `team_task_stages_template` VALUES (77, '通知用户', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:40', 0, 'cjk6al7f2ygp39des148iwzh');
INSERT INTO `team_task_stages_template` VALUES (78, '已完成&归档', 'd85f1bvwpml2nhxe92zu7tyi', '2018-12-24 22:02:45', 0, 'vn6dxyzme1g8ucbl3ikq0awt');
INSERT INTO `team_task_stages_template` VALUES (79, '产品计划', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:03', 0, '3atxfsv5rhz64pk8jl0enqd2');
INSERT INTO `team_task_stages_template` VALUES (80, '即将发布', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:09', 0, '1nucptea9b2vl7yfj8xgz4d6');
INSERT INTO `team_task_stages_template` VALUES (81, '测试', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:13', 0, 'pfidejaq2vn653h8zmsytrlb');
INSERT INTO `team_task_stages_template` VALUES (82, '准备发布', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:17', 0, 'uc1etmw4k5gys8jfpdbo7zrh');
INSERT INTO `team_task_stages_template` VALUES (83, '发布成功', 'd85f1bvwpml2nhxe94zu7tyi', '2018-12-24 22:06:23', 0, 'rmutqozd51shfp4w70n96iel');
INSERT INTO `team_task_stages_template` VALUES (110, '任务分组1', '5b72d66e70b6404f93a74bbf5d4de2a4', '2020-07-05 22:27:04', 0, 'b05e96e8f69e44ba8bf11f05850930a4');
INSERT INTO `team_task_stages_template` VALUES (111, '任务分组2', '5b72d66e70b6404f93a74bbf5d4de2a4', '2020-07-05 22:27:04', 0, 'da9f71a345f9475487ec3451ce60166e');
INSERT INTO `team_task_stages_template` VALUES (112, '任务分组3', '5b72d66e70b6404f93a74bbf5d4de2a4', '2020-07-05 22:27:04', 0, '4e8be3d274b043fb9fea9f3181915408');
INSERT INTO `team_task_stages_template` VALUES (113, '任务分组4', '5b72d66e70b6404f93a74bbf5d4de2a4', '2020-07-05 22:27:55', 0, 'ea6e2e86944f4e5ca8342c60ddeeb7f3');

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_tag
-- ----------------------------
INSERT INTO `team_task_tag` VALUES (15, 'd5eabcb11c414124a9888e6fe698556e', 'oebw2ycf016j7pnxum5zikg4', '1111工asdfsdfsd', 'blue', '2020-03-15 23:17:34');
INSERT INTO `team_task_tag` VALUES (16, 'd10t7rvky52usan9i8w3epog', 'oebw2ycf016j7pnxum5zikg4', 'aaaa', 'red', '2020-03-15 23:31:34');
INSERT INTO `team_task_tag` VALUES (17, 'tw2i7pvs1kalh8dfr4qx936b', 'oebw2ycf016j7pnxum5zikg4', 'aas', 'blue', '2020-03-15 23:34:19');
INSERT INTO `team_task_tag` VALUES (18, 'c5ba0a25ee914e269b5cb0d569c7573a', 'borhsewfgqxy38k1jmtznuv5', 'fuck', 'red', '2020-06-03 18:13:14');
INSERT INTO `team_task_tag` VALUES (19, 'cfbcd63272fd4067afc7dabc58532e6b', 'borhsewfgqxy38k1jmtznuv5', 'wocao', 'orange', '2020-06-03 18:13:20');
INSERT INTO `team_task_tag` VALUES (20, '04f79019ec7f4d1e8764b2def7f54570', 'borhsewfgqxy38k1jmtznuv5', 'qu', 'orange', '2020-06-03 18:13:24');
INSERT INTO `team_task_tag` VALUES (21, '5a63ad587a6a49a38488b3edf71366a9', '4c2d9d0c0a3542798259a3cbe24a13ff', 'aaa', 'blue', '2020-06-29 11:21:59');
INSERT INTO `team_task_tag` VALUES (22, '782d205606dc432780b029197b438b78', '4c2d9d0c0a3542798259a3cbe24a13ff', 'bbb', 'red', '2020-06-29 11:22:03');
INSERT INTO `team_task_tag` VALUES (23, 'b22e11ed230e4b8294262a3a0d0c2a24', '11c80c7fabd146ca95d24621124f61b4', '标签1', 'red', '2020-06-30 01:40:51');
INSERT INTO `team_task_tag` VALUES (24, '1773c16d99cb4cdc989cdb3920b9ddea', 'oebw2ycf016j7pnxum5zikg4', 'IOS', 'blue', '2020-06-29 20:54:13');
INSERT INTO `team_task_tag` VALUES (25, '17b76d080349434081c38fc51913e5c5', 'oebw2ycf016j7pnxum5zikg4', 'Android', 'orange', '2020-06-29 20:54:13');
INSERT INTO `team_task_tag` VALUES (26, '5c9e56c4a9ab4074929866ad91c81c46', 'oebw2ycf016j7pnxum5zikg4', 'HHHH', 'green', '2020-06-29 20:56:03');
INSERT INTO `team_task_tag` VALUES (27, '59b39b7b25e349a3ba9a4327e56ff53c', 'oebw2ycf016j7pnxum5zikg4', 'KKK', 'blue', '2020-06-29 20:56:03');
INSERT INTO `team_task_tag` VALUES (28, '3d08cf398b2e4204965b845ee013ad0f', 'oebw2ycf016j7pnxum5zikg4', 'CCC', 'brown', '2020-06-29 20:56:03');
INSERT INTO `team_task_tag` VALUES (29, 'b8154e57ff1a4a2a8e55011774ba8e7e', '468e736e7dbd4693a40e89fb8fc9dd5e', 'd是', 'blue', '2020-07-01 19:03:36');
INSERT INTO `team_task_tag` VALUES (30, '8e86d0e45b9a44f0a1d2735108aae53e', '6d54506b13a947f58895eec8db465e7e', '特色', 'blue', '2020-07-04 01:37:52');
INSERT INTO `team_task_tag` VALUES (31, 'dc2ac14023d74b739b8e2057dbcc8651', '8c4f887129e54068996e2d10a1c3bac9', '测试标签', 'blue', '2020-07-06 15:29:00');
INSERT INTO `team_task_tag` VALUES (32, 'c65b63b323fb4d1394aafb588f23d11f', '3488bba47b8e48fc9cc75f5e5580cfb4', 'test1', 'blue', '2020-07-07 21:40:34');
INSERT INTO `team_task_tag` VALUES (33, '106fd61426e54ce49e80768b0c9f9061', 'cf50726e99d746fa88107ed355d4a8cc', 'a', 'red', '2021-02-02 17:18:22');

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
) ENGINE = InnoDB AUTO_INCREMENT = 211 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务标签映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_to_tag
-- ----------------------------
INSERT INTO `team_task_to_tag` VALUES (153, 'bfc05585ff464c9eb0dce8c5f2d6de63', '28qet31u7kym6gi54pa9jldr', 'tw2i7pvs1kalh8dfr4qx936b', '2020-03-15 23:43:33');
INSERT INTO `team_task_to_tag` VALUES (154, '6984ba8c964e47688c6bd064e340d85e', 'upjzfcg3iqd05a7hxk1ys8to', 'c5ba0a25ee914e269b5cb0d569c7573a', '2020-06-03 18:13:14');
INSERT INTO `team_task_to_tag` VALUES (155, 'b93ca74bbafa4afdbd65f2e88cd62b3c', 'upjzfcg3iqd05a7hxk1ys8to', 'cfbcd63272fd4067afc7dabc58532e6b', '2020-06-03 18:13:20');
INSERT INTO `team_task_to_tag` VALUES (156, '3042da8a413249d587c03d73a3a62f79', 'upjzfcg3iqd05a7hxk1ys8to', '04f79019ec7f4d1e8764b2def7f54570', '2020-06-03 18:13:24');
INSERT INTO `team_task_to_tag` VALUES (157, '4d97d0e445ee4fc4937b7f1924ae5b33', 'xl2e68b1g4vdjc5fhatruwo9', 'cfbcd63272fd4067afc7dabc58532e6b', '2020-06-03 20:05:15');
INSERT INTO `team_task_to_tag` VALUES (158, '6749ff241aa0401f9cb88e5d12fe495b', '0c0e2970ce4c4dd0be94eb492aa76651', '5a63ad587a6a49a38488b3edf71366a9', '2020-06-29 11:21:59');
INSERT INTO `team_task_to_tag` VALUES (159, '64b459b1d49148479a0eab4f3639788b', '0c0e2970ce4c4dd0be94eb492aa76651', '782d205606dc432780b029197b438b78', '2020-06-29 11:22:03');
INSERT INTO `team_task_to_tag` VALUES (160, '0ef90302345a45989dfc9039802e9526', 'cf9360a4b38d43e9b51da10252b816ab', 'b22e11ed230e4b8294262a3a0d0c2a24', '2020-06-30 01:40:51');
INSERT INTO `team_task_to_tag` VALUES (161, 'b0a5f8a4430347a9bc16f7663244e11b', '7da222e4527d414d8256283b2700208f', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (162, 'cf24905d1e904000aabf2660feec984e', '7da222e4527d414d8256283b2700208f', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (163, 'b7943545748843ca8e8fdb60f8b042aa', 'c42398c6d4ff496fb232fa872ed38d11', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (164, '06d8ca12e5cc40fb99b7b221f45ba35c', 'c42398c6d4ff496fb232fa872ed38d11', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (165, 'a8951cf8c8df41e2bf8388d3b72be5d9', '1914ebd4fe3648fc8069b1d48f5965df', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (166, 'c2815f591e99481da5781a9e9f07dd73', '1914ebd4fe3648fc8069b1d48f5965df', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (167, '901cf2dd83f846eeba64eb527f6ab8bb', '6ebf5e2663c444ec877d0cbc79a8daa7', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (168, '7163c13e7575455ab26d6691da5a4fa2', '6ebf5e2663c444ec877d0cbc79a8daa7', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (169, 'be963d5c62674075817037e139856cc8', '77c7105b4d8546aa9b1d9e1ae43b5b51', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (170, '46950d9cc82640f6b16a722885192a34', '77c7105b4d8546aa9b1d9e1ae43b5b51', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (171, '45b3c4f7adbe4f08b433a200bf81a0e4', 'af7ac683f57143c8a3f22b1ff7c15a43', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (172, '5106fd5259284e5fa372dacacba03a27', 'af7ac683f57143c8a3f22b1ff7c15a43', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (173, '043a97925ce14d42b22451740c930c84', 'c72a51f0205841b486523889eb2717f8', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (174, 'acbaf9fa0fc9499d88ee3136ca931ece', 'c72a51f0205841b486523889eb2717f8', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (175, '19a6c206be5c48738b8545c518e9bdca', '18a3950f76a144e59e72cb7313f44b47', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (176, '3e841fb503554736a8ab4e0d4d697463', '18a3950f76a144e59e72cb7313f44b47', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (177, 'f398ee9af88d44c882ef803cbed274cb', '2c74c57cdde2422b9579315e58e61b37', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (178, 'e1f0ec1d1ee244a884776470cb835103', '2c74c57cdde2422b9579315e58e61b37', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (179, 'f583d038fe8c4b36a88a233ca4746ac0', '38152b8876934402b83584055bacfb58', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (180, '91b1a4f5f0e843c5b2d223c682d489b3', '38152b8876934402b83584055bacfb58', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (181, 'df4b2f13ba3542a3804cd43c1803023a', '247eb465050b4cedbfda2c32b1581682', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (182, '0c43d11732584209a783793fb2b93394', '247eb465050b4cedbfda2c32b1581682', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (183, '6229c0b725684bdb9f7fb2a434e1f813', '91cb80b254cc4f31beeb387088e3f80e', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (184, 'ef0b6b534f8b499a9935a61395aa33ae', '91cb80b254cc4f31beeb387088e3f80e', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (185, '44b219c749f44051b463f4cccdd4844f', '07be6fd5bec24c4c895ad265dd2a8807', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (186, '40722c984bf5436dacf405dcb5c8d208', '07be6fd5bec24c4c895ad265dd2a8807', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (187, 'ade93d25e44f47539a73a32eeac8c928', '18774ce429d64ef7bf5ab6800380f4b3', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (188, 'bfbea75df1f24345a7a5b289cb2953ff', '18774ce429d64ef7bf5ab6800380f4b3', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (189, '5c7948e021414a24bbee307c40e82faa', 'fa76b0cfb0794eb49f357012107e72af', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (190, 'dd909c50c3c344e1ade337f421e620ee', 'fa76b0cfb0794eb49f357012107e72af', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (191, '44935e4ce6964820979dd2b50f842da4', '91670637e1d74506b5493928f7c6c1be', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (192, '6e9f6b965f454872a6df54bb05af6478', '91670637e1d74506b5493928f7c6c1be', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (193, 'dc1e6d17607d4497be3ca1c7405d7f5c', 'f3bc5f347de143bfb8d417c598e3d49b', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (194, '3357d562766341ff9d3278bef67b53be', 'f3bc5f347de143bfb8d417c598e3d49b', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:54:13');
INSERT INTO `team_task_to_tag` VALUES (195, 'df0a49c3221342e49801c10c77302a9e', '5e6e982786fa44cfad7069b9251a1252', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (196, '7e2d7a7200514c96b2ad13bb7012ddfe', '5e6e982786fa44cfad7069b9251a1252', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (197, 'd004f5c18f9d493aa289998b998a96f1', '840af875e85747d58911149176e0c1b5', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (198, 'dc27d7b39f9f44a9bd0415c6def79173', '840af875e85747d58911149176e0c1b5', '5c9e56c4a9ab4074929866ad91c81c46', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (199, 'c5419034f04843a7b47fae4781912826', 'afec015592dc4998804cc6fdec614ac2', '1773c16d99cb4cdc989cdb3920b9ddea', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (200, 'b657f55670844883a2ef230490b48f83', 'afec015592dc4998804cc6fdec614ac2', '59b39b7b25e349a3ba9a4327e56ff53c', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (201, '4b2e16b52f154e278c931e7c49e874e7', 'bc11b4def2de469bb1bc9e7386a946bc', '3d08cf398b2e4204965b845ee013ad0f', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (202, '423dc04b04014c9baf099a148ac9a8cc', 'bc11b4def2de469bb1bc9e7386a946bc', '17b76d080349434081c38fc51913e5c5', '2020-06-29 20:56:03');
INSERT INTO `team_task_to_tag` VALUES (203, 'f5d2aae0c06a4f77a19ce76e6fa78d3e', '868df2ac982940c498ed5a5872e58ec5', 'b22e11ed230e4b8294262a3a0d0c2a24', '2020-06-30 23:11:56');
INSERT INTO `team_task_to_tag` VALUES (204, '0699b0987f394c568d081dc8bc299be5', 'ccd020c2f02949a3b2a7544fce2fbff5', 'b8154e57ff1a4a2a8e55011774ba8e7e', '2020-07-01 19:03:36');
INSERT INTO `team_task_to_tag` VALUES (205, 'e849477a80a542439432cb757c21a4f5', 'fe545301d489440a9db961af6e0dafdb', 'b8154e57ff1a4a2a8e55011774ba8e7e', '2020-07-03 18:44:57');
INSERT INTO `team_task_to_tag` VALUES (206, 'e9fda1e96ec448ef9c58fefe1b197f9e', '836a97066da741869ed87c1b8c456018', '8e86d0e45b9a44f0a1d2735108aae53e', '2020-07-04 01:37:52');
INSERT INTO `team_task_to_tag` VALUES (210, '6ad7e7cdc48f4b649fd8545c3e4eb5d3', '8d4c1bde367f43ed9db2b7da13a059ae', 'dc2ac14023d74b739b8e2057dbcc8651', '2020-07-06 15:36:26');
INSERT INTO `team_task_to_tag` VALUES (211, '53577b646256458fb0e5ac5af695b287', 'e9591ace4fc1451f9268283cc6021fbf', '106fd61426e54ce49e80768b0c9f9061', '2021-02-02 17:18:22');

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务工时表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_task_work_time
-- ----------------------------
INSERT INTO `team_task_work_time` VALUES (1, '28qet31u7kym6gi54pa9jldr', '6v7be19pwman2fird04gqu53', '2020-03-14 22:15:34', '工时工作内容', '2020-03-14 22:15', 5, 'w6lp2i9n3ej8bt10yqhkdvo4');
INSERT INTO `team_task_work_time` VALUES (3, '28qet31u7kym6gi54pa9jldr', '6v7be19pwman2fird04gqu53', '2020-03-18 18:03:38', '作内容', '2020-03-18 18:03', 12, 'ne10pkm7o3wltcx6u2gs5fj4');
INSERT INTO `team_task_work_time` VALUES (6, '433cdd9e1bdf4ad58bf7a3edad05ce25', '6v7be19pwman2fird04gqu53', '2020-06-03 20:50:24', '10aawt', '2020-06-03 20:50', 10, '05b644f86d184b99bd6d8627bbdd01d0');
INSERT INTO `team_task_work_time` VALUES (9, 'fe545301d489440a9db961af6e0dafdb', '6v7be19pwman2fird04gqu53', '2020-07-03 18:45:10', '2', '2020-07-03 11:03', 2, '8b79068f0cf64a28bf0fb79e8b9046ef');
INSERT INTO `team_task_work_time` VALUES (10, 'fe545301d489440a9db961af6e0dafdb', '6v7be19pwman2fird04gqu53', '2020-07-03 18:45:56', '三十分', '2020-07-04 11:03', 24, '645de4c747504b509c06a417eb7d7110');
INSERT INTO `team_task_work_time` VALUES (11, '836a97066da741869ed87c1b8c456018', '6v7be19pwman2fird04gqu53', '2020-07-04 01:38:09', '胜多负少', '2020-07-03 17:56', 24, '77ba065eb5cf487ab0d8508154811586');
INSERT INTO `team_task_work_time` VALUES (12, '836a97066da741869ed87c1b8c456018', '6v7be19pwman2fird04gqu53', '2020-07-04 01:38:18', '法大师傅大师傅', '2020-07-03 17:56', 2, '7ac13b455b9243d18ea20dddd951f507');
INSERT INTO `team_task_work_time` VALUES (13, '96360501c4f9466db1194415772f4750', '6v7be19pwman2fird04gqu53', '2021-02-02 17:13:16', 'test', '2021-02-02 17:13', 2, 'ea94ddccdceb4457a5f1d961aa1f5ad8');

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
-- Records of team_task_workflow
-- ----------------------------
INSERT INTO `team_task_workflow` VALUES (3, 'sbklfvyouc0qpmwhitn47j5z', '面试邀约', NULL, NULL, '6v7be19pwman2fird04gqu53', 'sbklfvyouc0qpmwhitn47j5z');
INSERT INTO `team_task_workflow` VALUES (4, 'sbklfvyouc0qpmwhitn47j51', '面试确认', NULL, NULL, '6v7be19pwman2fird04gqu53', 'sbklfvyouc0qpmwhitn47j5z');
INSERT INTO `team_task_workflow` VALUES (5, 'sbklfvyouc0qpmwhitn47j52', '确认入职', NULL, NULL, '6v7be19pwman2fird04gqu53', 'sbklfvyouc0qpmwhitn47j5z');
INSERT INTO `team_task_workflow` VALUES (6, 'sbklfvyouc0qpmwhitn47j53', 'Offer发放', NULL, NULL, '6v7be19pwman2fird04gqu53', 'sbklfvyouc0qpmwhitn47j5z');

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
-- Records of team_task_workflow_rule
-- ----------------------------
INSERT INTO `team_task_workflow_rule` VALUES (3, 'sbklfvyouc0qpmwhitn47j5z', 0, '7fea2dnwqvhxpt3mcrl4j1uz', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j5z', 1);
INSERT INTO `team_task_workflow_rule` VALUES (4, '3', 1, 'kqdcn2w40p58r31zyo6efjib', 3, NULL, NULL, 'sbklfvyouc0qpmwhitn47j5z', 2);
INSERT INTO `team_task_workflow_rule` VALUES (5, '4', 0, 'xqo8u02h315ytjmlegnsbvip', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j5z', 3);
INSERT INTO `team_task_workflow_rule` VALUES (6, '5', 0, 'xqo8u02h315ytjmlegnsbvip', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j51', 1);
INSERT INTO `team_task_workflow_rule` VALUES (7, '6', 0, 'y680trgedcavbhnz24u7i5m3', 3, NULL, NULL, 'sbklfvyouc0qpmwhitn47j51', 2);
INSERT INTO `team_task_workflow_rule` VALUES (8, '7', 0, 'csb8djyktl0f9nop71h64m5r', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j51', 3);
INSERT INTO `team_task_workflow_rule` VALUES (9, '8', 0, '9mo2nrbqygfv6j3tlzd1csex', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j52', 1);
INSERT INTO `team_task_workflow_rule` VALUES (10, '9', 0, NULL, 1, NULL, NULL, 'sbklfvyouc0qpmwhitn47j52', 2);
INSERT INTO `team_task_workflow_rule` VALUES (11, '10', 0, '5dinzhvtl29rxqey8puoa1b6', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j52', 3);
INSERT INTO `team_task_workflow_rule` VALUES (12, '11', 0, '6v7be19pwman2fird04gqu53', 1, NULL, NULL, 'sbklfvyouc0qpmwhitn47j52', 4);
INSERT INTO `team_task_workflow_rule` VALUES (13, '12', 0, 'csb8djyktl0f9nop71h64m5r', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j53', 1);
INSERT INTO `team_task_workflow_rule` VALUES (14, '13', 0, 'kqdcn2w40p58r31zyo6efjib', 3, NULL, NULL, 'sbklfvyouc0qpmwhitn47j53', 2);
INSERT INTO `team_task_workflow_rule` VALUES (15, '14', 0, '9mo2nrbqygfv6j3tlzd1csex', 0, NULL, NULL, 'sbklfvyouc0qpmwhitn47j53', 3);

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
