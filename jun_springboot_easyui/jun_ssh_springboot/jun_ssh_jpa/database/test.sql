/*
 Navicat Premium Data Transfer

 Source Server         : dev-mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 47.98.129.217:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 14/10/2018 22:02:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aentity
-- ----------------------------
DROP TABLE IF EXISTS `aentity`;
CREATE TABLE `aentity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `remark` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  `version` bigint(20) NOT NULL,
  `AentityName` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aentity
-- ----------------------------
INSERT INTO `aentity` VALUES (1, '2018-09-20 14:31:44', '2018-09-20 14:31:44', NULL, 0, 'haha', 'hello-aentity');

-- ----------------------------
-- Table structure for logrole
-- ----------------------------
DROP TABLE IF EXISTS `logrole`;
CREATE TABLE `logrole`  (
  `logRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `logRoleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logRoleRight` decimal(20, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`logRoleId`) USING BTREE,
  UNIQUE INDEX `logRoleName`(`logRoleName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logrole
-- ----------------------------
INSERT INTO `logrole` VALUES (1, 'admin', 3);
INSERT INTO `logrole` VALUES (2, 'admin1', 1);
INSERT INTO `logrole` VALUES (3, '学生', 0);
INSERT INTO `logrole` VALUES (4, 'aaa', NULL);

-- ----------------------------
-- Table structure for menu_tree
-- ----------------------------
DROP TABLE IF EXISTS `menu_tree`;
CREATE TABLE `menu_tree`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `parent_node_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `node_level` int(11) NULL DEFAULT NULL,
  `node_degree` int(11) NULL DEFAULT NULL,
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `order_num` double NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version` bigint(20) NULL DEFAULT NULL,
  `iconCls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `perm_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_tree
-- ----------------------------
INSERT INTO `menu_tree` VALUES (1, NULL, ',', 1, 2, '14:41:42', '2018-06-09 14:41:43', '2018-06-09 14:42:36', NULL, 100, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `menu_tree` VALUES (2, 3, ',1,3,', 3, 1, '14:41:43', '2018-06-09 14:41:44', '2018-06-09 15:22:41', NULL, 100, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `menu_tree` VALUES (3, 1, ',1,', 2, 3, '14:42:33', '2018-06-09 14:42:35', '2018-06-09 15:22:41', NULL, 100, NULL, 5, NULL, NULL, NULL, NULL);
INSERT INTO `menu_tree` VALUES (4, 1, ',1,', 2, 0, '14:42:36', '2018-06-09 14:42:36', '2018-06-09 14:42:36', NULL, 200, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `menu_tree` VALUES (5, 2, ',2,', 2, 0, '14:43:12', '2018-06-09 14:43:13', '2018-06-09 15:15:44', NULL, 100, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `menu_tree` VALUES (6, 3, ',1,3,', 3, 0, '14:43:13', '2018-06-09 14:43:14', '2018-06-11 11:26:53', NULL, 200, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `menu_tree` VALUES (7, 3, ',1,3,', 3, 0, '14:43:14', '2018-06-09 14:43:15', '2018-06-09 14:43:15', NULL, 300, NULL, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_sample
-- ----------------------------
DROP TABLE IF EXISTS `t_sample`;
CREATE TABLE `t_sample`  (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sample
-- ----------------------------
INSERT INTO `t_sample` VALUES (352, 'hello world0');
INSERT INTO `t_sample` VALUES (353, 'hello world1');
INSERT INTO `t_sample` VALUES (354, 'hello world2');
INSERT INTO `t_sample` VALUES (355, 'hello world3');
INSERT INTO `t_sample` VALUES (356, 'hello world4');
INSERT INTO `t_sample` VALUES (357, 'hello world5');
INSERT INTO `t_sample` VALUES (358, 'hello world6');
INSERT INTO `t_sample` VALUES (359, 'hello world7');
INSERT INTO `t_sample` VALUES (360, 'hello world8');
INSERT INTO `t_sample` VALUES (361, 'hello world9');
INSERT INTO `t_sample` VALUES (362, 'hello world1');

-- ----------------------------
-- Table structure for tree_entity
-- ----------------------------
DROP TABLE IF EXISTS `tree_entity`;
CREATE TABLE `tree_entity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_jsl4rm9b7825i95m03d4rhdje`(`parent_id`) USING BTREE,
  CONSTRAINT `tree_entity_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `tree_entity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree_entity
-- ----------------------------
INSERT INTO `tree_entity` VALUES (1, NULL, 'rootnode');
INSERT INTO `tree_entity` VALUES (2, 1, 'children_1_1');
INSERT INTO `tree_entity` VALUES (3, 1, 'children_1_2');
INSERT INTO `tree_entity` VALUES (4, 1, 'children_1_3');
INSERT INTO `tree_entity` VALUES (5, 2, 'children_2_1');
INSERT INTO `tree_entity` VALUES (6, 2, 'children_2_2');
INSERT INTO `tree_entity` VALUES (7, 5, 'children_5_1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logRoleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `userName`(`userName`) USING BTREE,
  INDEX `FK36EBCB9BF1C03B`(`logRoleId`) USING BTREE,
  CONSTRAINT `FK5r34egmp5914vtonecn26n7rh` FOREIGN KEY (`logRoleId`) REFERENCES `logrole` (`logRoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`logRoleId`) REFERENCES `logrole` (`logRoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'admin', 1);
INSERT INTO `user` VALUES (7, NULL, NULL, '君君', 4);
INSERT INTO `user` VALUES (8, NULL, NULL, '你好', 3);
INSERT INTO `user` VALUES (9, NULL, NULL, '阿三地方', 4);
INSERT INTO `user` VALUES (10, NULL, NULL, '111', 4);
INSERT INTO `user` VALUES (12, NULL, NULL, 'asdfsd', 4);
INSERT INTO `user` VALUES (13, NULL, NULL, 'sadf', 3);
INSERT INTO `user` VALUES (14, NULL, NULL, 'sdf', 3);
INSERT INTO `user` VALUES (15, NULL, NULL, 'asd', 3);
INSERT INTO `user` VALUES (16, NULL, NULL, 'df', 3);
INSERT INTO `user` VALUES (17, NULL, NULL, 'asdfasdfasd', 4);
INSERT INTO `user` VALUES (18, NULL, NULL, 'sdfgasdfg', 3);
INSERT INTO `user` VALUES (19, NULL, NULL, 'asdfasdfasdf', 3);
INSERT INTO `user` VALUES (20, NULL, NULL, 'dsafg', 3);
INSERT INTO `user` VALUES (21, NULL, NULL, 'asdfasdf', 3);
INSERT INTO `user` VALUES (25, NULL, NULL, 'asfd', 3);
INSERT INTO `user` VALUES (26, NULL, NULL, 'safd', 3);
INSERT INTO `user` VALUES (27, NULL, NULL, '你好asdf', 3);
INSERT INTO `user` VALUES (28, NULL, NULL, '你好sdfg asf', 3);
INSERT INTO `user` VALUES (30, NULL, NULL, 'aaaaa', 3);
INSERT INTO `user` VALUES (31, NULL, NULL, 'BBBBB', 3);
INSERT INTO `user` VALUES (32, NULL, NULL, '阿三', 3);
INSERT INTO `user` VALUES (33, NULL, NULL, '啊2222', 3);
INSERT INTO `user` VALUES (34, NULL, NULL, '啊速度', 3);
INSERT INTO `user` VALUES (45, 'transaction', NULL, NULL, 3);
INSERT INTO `user` VALUES (51, 'kkkkklll', NULL, 'kkkkkll', 3);
INSERT INTO `user` VALUES (56, 'GG-A-1', NULL, 'gg-n-1', NULL);
INSERT INTO `user` VALUES (57, 'GG-A-2', NULL, 'gg-n-2', NULL);
INSERT INTO `user` VALUES (58, NULL, NULL, NULL, 4);
INSERT INTO `user` VALUES (60, 'GGGG', NULL, NULL, NULL);
INSERT INTO `user` VALUES (61, NULL, NULL, '2018-5-7 0:56:06', NULL);
INSERT INTO `user` VALUES (62, NULL, NULL, '2018-9-10 17:50:50', NULL);

-- ----------------------------
-- View structure for test
-- ----------------------------
DROP VIEW IF EXISTS `test`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `test` AS select `user`.`userId` AS `userId`,`user`.`account` AS `account`,`user`.`password` AS `password`,`user`.`userName` AS `userName`,`user`.`nickName` AS `nickName`,`user`.`isInOffice` AS `isInOffice`,`user`.`allowedIP` AS `allowedIP`,`user`.`logRoleId` AS `logRoleId`,`user`.`r1` AS `r1`,`user`.`r2` AS `r2`,`user`.`r3` AS `r3`,`user`.`r4` AS `r4`,`user`.`r5` AS `r5` from `user`;

SET FOREIGN_KEY_CHECKS = 1;
