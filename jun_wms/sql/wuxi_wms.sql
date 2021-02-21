/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50628
Source Host           : sh-cdb-60jldfhk.sql.tencentcdb.com:62802
Source Database       : wuxi_wms

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2020-05-20 13:57:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_alias`
-- ----------------------------
DROP TABLE IF EXISTS `account_alias`;
CREATE TABLE `account_alias` (
  `alias_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户别名id',
  `account_alias` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '账户别名',
  `description` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '描述',
  `type` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类型',
  `disposition_id` int(11) DEFAULT NULL COMMENT '账户别名Id',
  `effective_date` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '有效时间',
  `disable_date` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '失效时间',
  `enabled_flag` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '失效标识',
  `organization_id` int(11) DEFAULT NULL COMMENT '库存组织组织Id',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`alias_id`) USING BTREE,
  KEY `alias_id_PK` (`alias_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of account_alias
-- ----------------------------
INSERT INTO `account_alias` VALUES ('1', '成品-期初库存周期调整', '期初库存周期调整', null, '11436', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('2', '库存初始化', '用于期初库存初始化', null, '11438', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('3', '物料编码转换', '物料编码转换', null, '11440', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('4', '物料-自动杂入杂出', '生产拉式物料杂入杂出#', null, '11442', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('5', '原材料-物料批次调整', '物料批次调整(仅仅用于批次调整)', null, '11444', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('6', '原材料-无订单退货-采购仓库', '采购仓库无订单退货', null, '11446', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('7', '原材料-盘点', 'SUB一厂原材料盘盈/盘亏', null, '11448', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('8', 'WIP-盘点', 'SUB一厂WIP盘盈/盘亏', null, '11450', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('9', '成品-盘点', 'SUB一厂成品盘盈/盘亏', null, '11452', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('10', '原材料-报废', 'SUB一厂原材料报废', null, '11454', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('11', '备件-报废', 'SUB一厂备件报废', null, '11456', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('12', '成品-报废-包装', 'SUB一厂包装成品报废/接收', null, '11458', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('13', '成品-报废-备货预多库存', 'SUB一厂备货预多库存报废', null, '11460', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('14', '成品-报废-SUB一厂预多库存', 'SUB一厂成品预多库存报废', null, '11462', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('15', '成品-报废-市场预多库存', 'SUB一厂成品预多库存报废', null, '11464', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('16', '成品-报废-市场部', 'SUB一厂客户资料更改、取消SO', null, '11466', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('17', '原材料-领用-机加-机械钻孔', 'SUB一厂机加-机械钻孔发放/退回', null, '11468', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('18', '原材料-领用-机加-激光钻孔', 'SUB一厂机加-激光钻孔发放/退回', null, '11470', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('19', '原材料-领用-机加-棕化', 'SUB一厂机加-棕化发放/退回', null, '11472', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('20', '原材料-领用-机加-层压', 'SUB一厂机加-层压发放/退回', null, '11474', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('21', '原材料-领用-机加-外形', 'SUB一厂机加-外形发放/退回', null, '11476', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('22', '原材料-领用-机加-酸洗', 'SUB一厂机加-酸洗发放/退回', null, '11478', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('23', '原材料-领用-机加-OSP(T)', 'SUB一厂机加-OSP(T)发放/退回', null, '11480', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('24', '原材料-领用-机加-OSP(S)', 'SUB一厂机加-OSP(S)发放/退回', null, '11482', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('25', '原材料-领用-电镀-去钻污沉铜', 'SUB一厂电镀-水平去钻污沉铜发放/退回', null, '11484', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('26', '原材料-领用-电镀-填孔电镀', 'SUB一厂电镀-填孔电镀发放/退回', null, '11486', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('27', '原材料-领用-电镀-非填孔电镀', 'SUB一厂电镀-非填孔电镀发放/退回', null, '11488', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('28', '原材料-领用-电镀-图形褪膜', 'SUB一厂电镀-图形褪膜发放/退回', null, '11490', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('29', '原材料-领用-电镀-快速蚀刻', 'SUB一厂电镀-快速蚀刻发放/退回', null, '11492', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('30', '原材料-领用-电镀-软金', 'SUB一厂电镀-软金发放/退回', null, '11494', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('31', '原材料-领用-电镀-有机褪膜', 'SUB一厂电镀-有机褪膜发放/退回', null, '11496', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('32', '原材料-领用-阻焊-超粗化', 'SUB一厂阻焊-超粗化发放/退回', null, '11498', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('33', '原材料-领用-阻焊-滚涂', 'SUB一厂阻焊-滚涂发放/退回', null, '11500', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('34', '原材料-领用-阻焊-阻焊压平', 'SUB一厂阻焊-阻焊压平发放/退回', null, '11502', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('35', '原材料-领用-阻焊-阻焊DI曝光', 'SUB一厂阻焊-阻焊DI曝光发放/退回', null, '11504', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('36', '原材料-领用-阻焊-显影后固化', 'SUB一厂阻焊-显影后固化发放/退回', null, '11506', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('37', '原材料-领用-阻焊-陶瓷刷喷砂', 'SUB一厂阻焊-陶瓷刷喷砂发放/退回', null, '11508', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('38', '原材料-领用-图形-前后处理', 'SUB一厂图形-图形前后处理发放/退回', null, '11510', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('39', '原材料-领用-图形-图形LDI曝光', 'SUB一厂图形-图形LDI曝光发放/退回', null, '11512', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('40', '原材料-领用-图形-酸性蚀刻', 'SUB一厂图形-酸性蚀刻发放/退回', null, '11514', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('41', '原材料-领用-图形-抗镀金前后', 'SUB一厂图形-抗镀金前后处理发放/退回', null, '11516', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('42', '原材料-领用-图形-抗镀金贴膜', 'SUB一厂图形-抗镀金图形贴膜发放/退回', null, '11518', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('43', '原材料-领用-生产部专项管理', 'SUB一厂生产部专项管理发放/退回', null, '11520', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('44', '原材料-领用-质量部-MQA-物理室', 'SUB一厂质量部MQA-物理室发放/退回', null, '11522', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('45', '原材料-领用-质量部-MQA-化验室', 'SUB一厂质量部MQA-化验室发放/退回', null, '11524', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('46', '原材料-领用-质量部-MQA-IQC', 'SUB一厂质量部MQA-IQC发放/退回', null, '11526', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('47', '原材料-领用-质量部-MQA-PQC', 'SUB一厂质量部MQA-PQC发放/退回', null, '11528', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('48', '原材料-领用-质量部过检-AOI', 'SUB一厂质量部过程检验-AOI检验', null, '11530', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('49', '原材料-领用-质量部过检-打标', 'SUB一厂质量部过程检验-成品激光打标', null, '11532', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('50', '原材料-领用-质量部过检-包装', 'SUB一厂质量部过程检验-包装', null, '11534', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('51', '原材料-领用-质量部终检-成品', 'SUB一厂质量部终检-成品检验发放/退回', null, '11536', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('52', '原材料-领用-质量部终检-测试', 'SUB一厂质量部终检-测试发放/退回', null, '11538', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('53', '原材料-领用-质量部DQE', 'SUB一厂质量部DQE发放/退回', null, '11540', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('54', '原材料-领用-质量部整合', 'SUB一厂质量部整合发放/退回', null, '11542', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('55', '原材料-领用-成品库', 'SUB一厂供应链部-成品库发放/退回', null, '11544', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('56', '备件-领用-EMCS', 'SUB一厂EMCS发放/退回', null, '11546', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('57', '备件-领用-大修改造', 'SUB一厂大修改造发放/退回', null, '11548', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('58', '备件-领用-机加-机械钻孔', 'SUB一厂机加-机械钻孔发放/退回', null, '11550', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('59', '备件-领用-机加-激光钻孔', 'SUB一厂机加-激光钻孔发放/退回', null, '11552', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('60', '备件-领用-机加-棕化', 'SUB一厂机加-棕化发放/退回', null, '11554', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('61', '备件-领用-机加-层压', 'SUB一厂机加-层压发放/退回', null, '11556', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('62', '备件-领用-机加-外形', 'SUB一厂机加-外形发放/退回', null, '11558', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('63', '备件-领用-机加-酸洗', 'SUB一厂机加-酸洗发放/退回', null, '11560', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('64', '备件-领用-机加-OSP(T)', 'SUB一厂机加-OSP(T)发放/退回', null, '11562', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('65', '备件-领用-机加-OSP(S)', 'SUB一厂机加-OSP(S)发放/退回', null, '11564', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('66', '备件-领用-电镀-备件-领用-电', 'SUB一厂电镀-水平去钻污沉铜发放/退回', null, '11566', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('67', '备件-领用-电镀-填孔电镀', 'SUB一厂电镀-填孔电镀发放/退回', null, '11568', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('68', '备件-领用-电镀-非填孔电镀', 'SUB一厂电镀-非填孔电镀发放/退回', null, '11570', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('69', '备件-领用-电镀-图形褪膜', 'SUB一厂电镀-图形褪膜发放/退回', null, '11572', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('70', '备件-领用-电镀-快速蚀刻', 'SUB一厂电镀-快速蚀刻发放/退回', null, '11574', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('71', '备件-领用-电镀-软金', 'SUB一厂电镀-软金发放/退回', null, '11576', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('72', '备件-领用-电镀-有机褪膜', 'SUB一厂电镀-有机褪膜发放/退回', null, '11578', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('73', '备件-领用-阻焊-超粗化', 'SUB一厂阻焊-超粗化发放/退回', null, '11580', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('74', '备件-领用-阻焊-滚涂', 'SUB一厂阻焊-滚涂发放/退回', null, '11582', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('75', '备件-领用-阻焊-阻焊压平', 'SUB一厂阻焊-阻焊压平发放/退回', null, '11584', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('76', '备件-领用-阻焊-阻焊DI曝光', 'SUB一厂阻焊-阻焊DI曝光发放/退回', null, '11586', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('77', '备件-领用-阻焊-显影后固化', 'SUB一厂阻焊-显影后固化发放/退回', null, '11588', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('78', '备件-领用-阻焊-陶瓷刷喷砂', 'SUB一厂阻焊-陶瓷刷喷砂发放/退回', null, '11590', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('79', '备件-领用-图形-图形前后处理', 'SUB一厂图形-图形前后处理发放/退回', null, '11592', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('80', '备件-领用-图形-图形LDI曝光', 'SUB一厂图形-图形LDI曝光发放/退回', null, '11594', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('81', '备件-领用-图形-酸性蚀刻', 'SUB一厂图形-酸性蚀刻发放/退回', null, '11596', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('82', '备件-领用-图形-抗镀金前后', 'SUB一厂图形-抗镀金前后处理发放/退回', null, '11598', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('83', '备件-领用-图形-抗镀金贴膜', 'SUB一厂图形-抗镀金图形贴膜发放/退回', null, '11600', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('84', '备件-领用-图形-抗镀金曝光', 'SUB一厂图形-抗镀金图形曝光发放/退回', null, '11602', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('85', '备件-领用-生产部专项管理', 'SUB一厂生产部专项管理发放/退回', null, '11604', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('86', '原材料-领用-图形-抗镀金曝光', 'SUB一厂图形-抗镀金图形曝光发放/退回', null, '11606', '2019-01-22 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('87', '工装-领用-层压', 'SUB一厂机加-层压工装发放/退回', null, '11608', '2019-01-23 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('88', '工装-领用-测试', 'SUB一厂测试工装发放/退回', null, '11610', '2019-01-23 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('89', '工装-领用-软金', 'SUB一厂软金工装发放/退回', null, '11612', '2019-01-23 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('90', '工装-领用-生产部', 'SUB一厂生产部工装发放/退回', null, '11614', '2019-01-23 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('91', '原材料-领用-仓库', '无锡封装基板-仓库发放/退回', null, '11078', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('92', '原材料-领用-采购部钻头刃磨', '无锡封装基板-钻头刃磨发放/退回', null, '11080', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('93', '原材料-领用-中央供药', '无锡封装基板-中央供药发放/退回', null, '11082', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('94', '原材料-领用-技术部', 'SUB一厂技术部发放/退回', null, '11084', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('95', '原材料-领用-设备部', 'SUB一厂设备部发放/退回', null, '11086', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('96', '原材料-领用-供应链部', 'SUB一厂供应链部发放/退回', null, '11088', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('97', '原材料-领用-制作工程部', '无锡封装基板-制作工程部发放/退回', null, '11090', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('98', '备件-领用-质量部', 'SUB一厂质量部发放/退回', null, '11092', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('99', '备件-领用-技术部', 'SUB一厂技术部发放/退回', null, '11094', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('100', '备件-领用-设备部', 'SUB一厂设备部发放/退回', null, '11096', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('101', '备件-领用-供应链部', 'SUB一厂供应链部发放/退回', null, '11098', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('102', '备件-领用-制作工程部', '无锡封装基板-制作工程部发放/退回', null, '11100', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');
INSERT INTO `account_alias` VALUES ('103', '备件-领用-TPM项目', 'SUB一厂TPM项目发放/退回', null, '11102', '2018-12-27 00:00:00', null, 'Y', '327', '2020-01-20');

-- ----------------------------
-- Table structure for `area_info`
-- ----------------------------
DROP TABLE IF EXISTS `area_info`;
CREATE TABLE `area_info` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货区ID',
  `area_code` varchar(30) DEFAULT NULL COMMENT '货区编码',
  `area_name` varchar(20) DEFAULT NULL COMMENT '货区名',
  `ware_id` int(11) DEFAULT NULL COMMENT '添加时间',
  `create_time` datetime DEFAULT NULL COMMENT '备注',
  `create_user_id` int(11) DEFAULT NULL COMMENT '仓库Id',
  `memo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`area_id`) USING BTREE,
  KEY `ware_id` (`ware_id`) USING BTREE,
  KEY `area_info_area_id` (`area_id`) USING BTREE,
  CONSTRAINT `area_info_ibfk_1` FOREIGN KEY (`ware_id`) REFERENCES `ware_info` (`ware_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='货区设置';

-- ----------------------------
-- Records of area_info
-- ----------------------------
INSERT INTO `area_info` VALUES ('248', '11', '货区1', '212', null, null, '11');

-- ----------------------------
-- Table structure for `bill_in_check_record`
-- ----------------------------
DROP TABLE IF EXISTS `bill_in_check_record`;
CREATE TABLE `bill_in_check_record` (
  `check_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检验记录',
  `organization_id` int(11) DEFAULT NULL COMMENT '组织Id',
  `receipt_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '接收号',
  `transaction_id` int(20) DEFAULT NULL COMMENT '检验ID(为交货接口的源标志号)',
  `po_header_id` int(20) DEFAULT NULL COMMENT '采购单头Id',
  `po_line_id` int(20) DEFAULT NULL COMMENT '采购单行ID',
  `po_line_location_id` int(20) DEFAULT NULL COMMENT '采购单发运行ID',
  `po_distribution_id` int(20) DEFAULT NULL COMMENT '采购单分配行ID',
  `item_id` int(20) DEFAULT NULL COMMENT '物料编码',
  `quantity` int(11) DEFAULT NULL COMMENT '接收数量',
  `transaction` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '检验结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `batch` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '批次',
  `state` int(2) DEFAULT NULL COMMENT '状态 1-未交货  2-交货',
  PRIMARY KEY (`check_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bill_in_check_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_in_detail`
-- ----------------------------
DROP TABLE IF EXISTS `bill_in_detail`;
CREATE TABLE `bill_in_detail` (
  `bill_in_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bill_id` int(11) DEFAULT NULL COMMENT '入库单ID',
  `item_id` int(20) DEFAULT NULL COMMENT '物料Id',
  `po_distribution_id` int(20) DEFAULT NULL COMMENT '分配行ID',
  `distribution_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分配行号',
  `segment` varchar(255) DEFAULT NULL COMMENT '采购单号',
  `po_header_id` int(20) DEFAULT NULL COMMENT '采购订单头ID',
  `line_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '采购订单行号',
  `po_line_id` int(20) DEFAULT NULL COMMENT '采购订单行ID',
  `shipment_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '发运行号',
  `line_location_id` int(20) DEFAULT NULL COMMENT '发运行ID',
  `org_id` int(20) DEFAULT NULL COMMENT 'OU组织ID',
  `ship_to_organization_id` int(20) DEFAULT NULL COMMENT '接收库存组织ID',
  `item_description` varchar(900) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '物料描述',
  `unit_meas_lookup_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '采购单位',
  `unit_price` decimal(16,4) DEFAULT NULL COMMENT '采购单价',
  `quantity` int(11) DEFAULT NULL COMMENT '发运行数量',
  `quantity_received` int(11) DEFAULT NULL COMMENT '已接收数量',
  `closed_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '发运行状态',
  `supply_type_code` varchar(255) DEFAULT NULL COMMENT '接收类型',
  `surplus_received_quantity` int(11) DEFAULT NULL COMMENT '可接收数量',
  `vendor_id` int(20) DEFAULT NULL COMMENT '供应商ID',
  `vendor_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '供应商编码',
  `vendor_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '供应商名称',
  `bill_in_state` int(11) DEFAULT NULL COMMENT '状态',
  `expected_arrival_date` datetime DEFAULT NULL COMMENT '预计交货日期',
  PRIMARY KEY (`bill_in_detail_id`) USING BTREE,
  KEY `bill_in_detail_bill_id` (`bill_id`) USING BTREE,
  KEY `bill_in_detail_item_code` (`item_id`) USING BTREE,
  KEY `bill_in_detail_id_PK` (`bill_in_detail_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='入库单详情';

-- ----------------------------
-- Records of bill_in_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_in_master`
-- ----------------------------
DROP TABLE IF EXISTS `bill_in_master`;
CREATE TABLE `bill_in_master` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bill_no` varchar(255) DEFAULT NULL COMMENT '单据号',
  `contract_no` varchar(20) DEFAULT NULL COMMENT '合同号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(20) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人员',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `memo` varchar(20) DEFAULT NULL COMMENT '备注',
  `ware_id` int(11) DEFAULT NULL COMMENT '仓库ID',
  `supplier_code` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '入库类型1-订单入库，2-退料入库',
  PRIMARY KEY (`bill_id`) USING BTREE,
  KEY `bill_in_master_bill_id_PK` (`bill_id`) USING BTREE,
  KEY `bill_in_master_bill_no` (`bill_no`) USING BTREE,
  KEY `bill_in_master_ware_id` (`ware_id`) USING BTREE,
  KEY `bill_in_master_supplier_code` (`supplier_code`) USING BTREE,
  KEY `bill_in_master_state` (`state`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='入库单';

-- ----------------------------
-- Records of bill_in_master
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_in_record`
-- ----------------------------
DROP TABLE IF EXISTS `bill_in_record`;
CREATE TABLE `bill_in_record` (
  `bill_in_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库记录表',
  `bill_in_detail_id` int(11) DEFAULT NULL COMMENT '出库单id',
  `accept_quantity` int(11) DEFAULT NULL COMMENT '入库数量',
  `accept_time` datetime DEFAULT NULL,
  `box_code` varchar(255) DEFAULT NULL,
  `bar_code` varchar(300) DEFAULT NULL COMMENT '扫描得到的二维码',
  `pd` date DEFAULT NULL COMMENT '生产日期',
  `exp` date DEFAULT NULL COMMENT '失效日期',
  `batch` varchar(255) DEFAULT NULL COMMENT '批次',
  `state` int(2) DEFAULT NULL COMMENT '状态  1-未回传EBS  2-已回传EBS',
  `receipt_num` varchar(20) DEFAULT NULL COMMENT '接收单号',
  PRIMARY KEY (`bill_in_record_id`) USING BTREE,
  KEY `box_code` (`box_code`) USING BTREE,
  KEY `detail_id` (`bill_in_detail_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bill_in_record
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_out_detail`
-- ----------------------------
DROP TABLE IF EXISTS `bill_out_detail`;
CREATE TABLE `bill_out_detail` (
  `bill_out_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bill_id` int(20) DEFAULT NULL COMMENT '入库单ID',
  `item_code` varchar(20) DEFAULT NULL COMMENT '物料编码',
  `quantity` int(20) DEFAULT NULL COMMENT '订单数量',
  `task_id` varchar(255) DEFAULT NULL,
  `finished_code` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bill_out_detail_id`) USING BTREE,
  KEY `bill_out_detail_bill_id` (`bill_id`) USING BTREE,
  KEY `bill_out_detail_id_PK` (`bill_out_detail_id`) USING BTREE,
  KEY `bill_out_detail_item_code` (`item_code`) USING BTREE,
  KEY `bill_out_detail_priority` (`priority`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='入库单详情';

-- ----------------------------
-- Records of bill_out_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `bill_out_master`
-- ----------------------------
DROP TABLE IF EXISTS `bill_out_master`;
CREATE TABLE `bill_out_master` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bill_no` varchar(20) DEFAULT NULL COMMENT '单据号',
  `contract_no` varchar(20) DEFAULT NULL COMMENT '合同号',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(20) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人员',
  `account_alias_id` int(11) DEFAULT NULL COMMENT '账户别名Id',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `memo` varchar(20) DEFAULT NULL COMMENT '备注',
  `ware_id` int(11) DEFAULT NULL COMMENT '仓库ID',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`bill_id`) USING BTREE,
  KEY `bill_out_master_bill_id_PK` (`bill_id`) USING BTREE,
  KEY `bill_out_master_state` (`state`) USING BTREE,
  KEY `bill_out_master_ware_id` (`ware_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='入库单';

-- ----------------------------
-- Records of bill_out_master
-- ----------------------------

-- ----------------------------
-- Table structure for `box_info`
-- ----------------------------
DROP TABLE IF EXISTS `box_info`;
CREATE TABLE `box_info` (
  `box_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `box_code` varchar(20) DEFAULT NULL COMMENT '编码',
  `box_type` int(2) DEFAULT NULL COMMENT '类型：1-料箱  2-托盘',
  `box_memo` varchar(30) DEFAULT NULL COMMENT '备注',
  `box_unit` varchar(25) DEFAULT NULL COMMENT '所属单位',
  `box_cell_id` int(11) DEFAULT NULL,
  `box_state` int(2) DEFAULT '0' COMMENT '0-不在货位 1-货位上  2-任务中',
  `has_goods` int(1) DEFAULT '0' COMMENT '0-无货 1-有货',
  PRIMARY KEY (`box_id`) USING BTREE,
  KEY `box_info_box_cell_id` (`box_cell_id`) USING BTREE,
  KEY `box_info_box_code` (`box_code`) USING BTREE,
  KEY `box_info_box_id_PK` (`box_id`) USING BTREE,
  KEY `box_info_box_state` (`box_state`) USING BTREE,
  KEY `box_info_has_goods` (`has_goods`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='容器';

-- ----------------------------
-- Records of box_info
-- ----------------------------
INSERT INTO `box_info` VALUES ('1', 'FTB0001', '2', null, null, '2175', '1', '0');
INSERT INTO `box_info` VALUES ('2', 'FTB0002', '2', null, null, '2176', '1', '0');
INSERT INTO `box_info` VALUES ('3', 'FTB0003', '2', null, null, '2177', '1', '0');
INSERT INTO `box_info` VALUES ('4', 'FTB0004', '2', null, null, '2178', '1', '0');
INSERT INTO `box_info` VALUES ('5', 'FTB0005', '2', null, null, '2180', '1', '0');
INSERT INTO `box_info` VALUES ('6', 'FTB0006', '2', null, null, '2181', '1', '0');
INSERT INTO `box_info` VALUES ('7', 'FTB0007', '2', null, null, '2182', '1', '0');
INSERT INTO `box_info` VALUES ('8', 'FTB0008', '2', null, null, '2184', '1', '0');
INSERT INTO `box_info` VALUES ('9', 'FTB0009', '2', null, null, '2186', '1', '0');
INSERT INTO `box_info` VALUES ('10', 'FTB0010', '2', null, null, '2188', '1', '0');
INSERT INTO `box_info` VALUES ('11', 'FTB0011', '2', null, null, '2189', '1', '0');
INSERT INTO `box_info` VALUES ('12', 'FTB0012', '2', null, null, '2190', '1', '0');
INSERT INTO `box_info` VALUES ('13', 'FTB0013', '2', null, null, '2191', '1', '0');
INSERT INTO `box_info` VALUES ('14', 'FTB0014', '2', null, null, '2192', '1', '0');
INSERT INTO `box_info` VALUES ('15', 'FTB0015', '2', null, null, '2193', '1', '0');
INSERT INTO `box_info` VALUES ('16', 'FTB0016', '2', null, null, '2194', '1', '0');
INSERT INTO `box_info` VALUES ('17', 'FTB0017', '2', null, null, '2196', '1', '0');
INSERT INTO `box_info` VALUES ('18', 'FTB0018', '2', null, null, '2198', '1', '0');
INSERT INTO `box_info` VALUES ('19', 'FTB0019', '2', null, null, '2199', '1', '0');
INSERT INTO `box_info` VALUES ('20', 'FTB0020', '2', null, null, '2200', '1', '0');
INSERT INTO `box_info` VALUES ('21', 'FTB0021', '2', null, null, '2201', '1', '0');
INSERT INTO `box_info` VALUES ('22', 'FTB0022', '2', null, null, '2202', '1', '0');
INSERT INTO `box_info` VALUES ('23', 'FTB0023', '2', null, null, '2203', '1', '0');
INSERT INTO `box_info` VALUES ('24', 'FTB0024', '2', null, null, '2204', '1', '0');
INSERT INTO `box_info` VALUES ('25', 'FTB0025', '2', null, null, '2205', '1', '0');
INSERT INTO `box_info` VALUES ('26', 'FTB0026', '2', null, null, '2733', '1', '0');
INSERT INTO `box_info` VALUES ('27', 'FTB0027', '2', null, null, '2734', '1', '0');
INSERT INTO `box_info` VALUES ('28', 'FTB0028', '2', null, null, '2735', '1', '0');
INSERT INTO `box_info` VALUES ('29', 'FTB0029', '2', null, null, '2736', '1', '0');
INSERT INTO `box_info` VALUES ('30', 'FTB0030', '2', null, null, '2737', '1', '0');
INSERT INTO `box_info` VALUES ('31', 'FTB0031', '2', null, null, '2738', '1', '0');
INSERT INTO `box_info` VALUES ('32', 'FTB0032', '2', null, null, '2739', '1', '0');
INSERT INTO `box_info` VALUES ('33', 'FTB0033', '2', null, null, '2740', '1', '0');
INSERT INTO `box_info` VALUES ('34', 'FTB0034', '2', null, null, '2741', '1', '0');
INSERT INTO `box_info` VALUES ('35', 'FTB0035', '2', null, null, '2742', '1', '0');
INSERT INTO `box_info` VALUES ('36', 'FTB0036', '2', null, null, '2743', '1', '0');
INSERT INTO `box_info` VALUES ('37', 'FTB0037', '2', null, null, '2744', '1', '0');
INSERT INTO `box_info` VALUES ('38', 'FTB0038', '2', null, null, '2745', '1', '0');
INSERT INTO `box_info` VALUES ('39', 'FTB0039', '2', null, null, '2746', '1', '0');
INSERT INTO `box_info` VALUES ('40', 'FTB0040', '2', null, null, '2747', '1', '0');
INSERT INTO `box_info` VALUES ('41', 'FTB0041', '2', null, null, '2748', '1', '0');
INSERT INTO `box_info` VALUES ('42', 'FTB0042', '2', null, null, '2749', '1', '0');
INSERT INTO `box_info` VALUES ('43', 'FTB0043', '2', null, null, '2750', '1', '0');
INSERT INTO `box_info` VALUES ('44', 'FTB0044', '2', null, null, '2751', '1', '0');
INSERT INTO `box_info` VALUES ('45', 'FTB0045', '2', null, null, '2752', '1', '0');
INSERT INTO `box_info` VALUES ('46', 'FTB0046', '2', null, null, '2753', '1', '0');
INSERT INTO `box_info` VALUES ('47', 'FTB0047', '2', null, null, '2754', '1', '0');
INSERT INTO `box_info` VALUES ('48', 'FTB0048', '2', null, null, '2755', '1', '0');
INSERT INTO `box_info` VALUES ('49', 'FTB0049', '2', null, null, '2756', '1', '0');
INSERT INTO `box_info` VALUES ('50', 'FTB0050', '2', null, null, '2757', '1', '0');
INSERT INTO `box_info` VALUES ('51', 'FTB0051', '2', null, null, '2758', '1', '0');
INSERT INTO `box_info` VALUES ('52', 'FTB0052', '2', null, null, '2759', '1', '0');
INSERT INTO `box_info` VALUES ('53', 'FTB0053', '2', null, null, '2760', '1', '0');
INSERT INTO `box_info` VALUES ('54', 'FTB0054', '2', null, null, '2761', '1', '0');
INSERT INTO `box_info` VALUES ('55', 'FTB0055', '2', null, null, '2762', '1', '0');
INSERT INTO `box_info` VALUES ('56', 'FTB0056', '2', null, null, '2763', '1', '0');
INSERT INTO `box_info` VALUES ('57', 'FTB0057', '2', null, null, '2206', '1', '0');
INSERT INTO `box_info` VALUES ('58', 'FTB0058', '2', null, null, '2207', '1', '0');
INSERT INTO `box_info` VALUES ('59', 'FTB0059', '2', null, null, '2208', '1', '0');
INSERT INTO `box_info` VALUES ('60', 'FTB0060', '2', null, null, '2209', '1', '0');
INSERT INTO `box_info` VALUES ('61', 'FTB0061', '2', null, null, '2210', '1', '0');
INSERT INTO `box_info` VALUES ('62', 'FTB0062', '2', null, null, '2211', '1', '0');
INSERT INTO `box_info` VALUES ('63', 'FTB0063', '2', null, null, '2212', '1', '0');
INSERT INTO `box_info` VALUES ('64', 'FTB0064', '2', null, null, '2213', '1', '0');
INSERT INTO `box_info` VALUES ('65', 'FTB0065', '2', null, null, '2214', '1', '0');
INSERT INTO `box_info` VALUES ('66', 'FTB0066', '2', null, null, '2215', '1', '0');
INSERT INTO `box_info` VALUES ('67', 'FTB0067', '2', null, null, '2216', '1', '0');
INSERT INTO `box_info` VALUES ('68', 'FTB0068', '2', null, null, '2217', '1', '0');
INSERT INTO `box_info` VALUES ('69', 'FTB0069', '2', null, null, '2218', '1', '0');
INSERT INTO `box_info` VALUES ('70', 'FTB0070', '2', null, null, '2219', '1', '0');
INSERT INTO `box_info` VALUES ('71', 'FTB0071', '2', null, null, '2220', '1', '0');
INSERT INTO `box_info` VALUES ('72', 'FTB0072', '2', null, null, '2221', '1', '0');
INSERT INTO `box_info` VALUES ('73', 'FTB0073', '2', null, null, '2222', '1', '0');
INSERT INTO `box_info` VALUES ('74', 'FTB0074', '2', null, null, '2223', '1', '0');
INSERT INTO `box_info` VALUES ('75', 'FTB0075', '2', null, null, '2224', '1', '0');
INSERT INTO `box_info` VALUES ('76', 'FTB0076', '2', null, null, '2225', '1', '0');
INSERT INTO `box_info` VALUES ('77', 'FTB0077', '2', null, null, '2226', '1', '0');
INSERT INTO `box_info` VALUES ('78', 'FTB0078', '2', null, null, '2227', '1', '0');
INSERT INTO `box_info` VALUES ('79', 'FTB0079', '2', null, null, '2228', '1', '0');
INSERT INTO `box_info` VALUES ('80', 'FTB0080', '2', null, null, '2229', '1', '0');
INSERT INTO `box_info` VALUES ('81', 'FTB0081', '2', null, null, '2230', '1', '0');
INSERT INTO `box_info` VALUES ('82', 'FTB0082', '2', null, null, '2231', '1', '0');
INSERT INTO `box_info` VALUES ('83', 'FTB0083', '2', null, null, '2232', '1', '0');
INSERT INTO `box_info` VALUES ('84', 'FTB0084', '2', null, null, '2233', '1', '0');
INSERT INTO `box_info` VALUES ('85', 'FTB0085', '2', null, null, '2234', '1', '0');
INSERT INTO `box_info` VALUES ('86', 'FTB0086', '2', null, null, '2235', '1', '0');
INSERT INTO `box_info` VALUES ('87', 'FTB0087', '2', null, null, '2236', '1', '0');
INSERT INTO `box_info` VALUES ('88', 'FTB0088', '2', null, null, '2764', '1', '0');
INSERT INTO `box_info` VALUES ('89', 'FTB0089', '2', null, null, '2765', '1', '0');
INSERT INTO `box_info` VALUES ('90', 'FTB0090', '2', null, null, '2766', '1', '0');
INSERT INTO `box_info` VALUES ('91', 'FTB0091', '2', null, null, '2767', '1', '0');
INSERT INTO `box_info` VALUES ('92', 'FTB0092', '2', null, null, '2768', '1', '0');
INSERT INTO `box_info` VALUES ('93', 'FTB0093', '2', null, null, '2769', '1', '0');
INSERT INTO `box_info` VALUES ('94', 'FTB0094', '2', null, null, '2770', '1', '0');
INSERT INTO `box_info` VALUES ('95', 'FTB0095', '2', null, null, '2771', '1', '0');
INSERT INTO `box_info` VALUES ('96', 'FTB0096', '2', null, null, '2772', '1', '0');
INSERT INTO `box_info` VALUES ('97', 'FTB0097', '2', null, null, '2773', '1', '0');
INSERT INTO `box_info` VALUES ('98', 'FTB0098', '2', null, null, '2774', '1', '0');
INSERT INTO `box_info` VALUES ('99', 'FTB0099', '2', null, null, '2775', '1', '0');
INSERT INTO `box_info` VALUES ('100', 'FTB0100', '2', null, null, '2776', '1', '0');
INSERT INTO `box_info` VALUES ('101', 'FTB0101', '2', null, null, '2777', '1', '0');
INSERT INTO `box_info` VALUES ('102', 'FTB0102', '2', null, null, '2778', '1', '0');
INSERT INTO `box_info` VALUES ('103', 'FTB0103', '2', null, null, '2779', '1', '0');
INSERT INTO `box_info` VALUES ('104', 'FTB0104', '2', null, null, '2780', '1', '0');
INSERT INTO `box_info` VALUES ('105', 'FTB0105', '2', null, null, '2781', '1', '0');
INSERT INTO `box_info` VALUES ('106', 'FTB0106', '2', null, null, '2782', '1', '0');
INSERT INTO `box_info` VALUES ('107', 'FTB0107', '2', null, null, '2783', '1', '0');
INSERT INTO `box_info` VALUES ('108', 'FTB0108', '2', null, null, '2784', '1', '0');
INSERT INTO `box_info` VALUES ('109', 'FTB0109', '2', null, null, '2785', '1', '0');
INSERT INTO `box_info` VALUES ('110', 'FTB0110', '2', null, null, '2786', '1', '0');
INSERT INTO `box_info` VALUES ('111', 'FTB0111', '2', null, null, '2787', '1', '0');
INSERT INTO `box_info` VALUES ('112', 'FTB0112', '2', null, null, '2788', '1', '0');
INSERT INTO `box_info` VALUES ('113', 'FTB0113', '2', null, null, '2789', '1', '0');
INSERT INTO `box_info` VALUES ('114', 'FTB0114', '2', null, null, '2790', '1', '0');
INSERT INTO `box_info` VALUES ('115', 'FTB0115', '2', null, null, '2791', '1', '0');
INSERT INTO `box_info` VALUES ('116', 'FTB0116', '2', null, null, '2792', '1', '0');
INSERT INTO `box_info` VALUES ('117', 'FTB0117', '2', null, null, '2793', '1', '0');
INSERT INTO `box_info` VALUES ('118', 'FTB0118', '2', null, null, '2794', '1', '0');
INSERT INTO `box_info` VALUES ('119', 'FTB0119', '2', null, null, '2237', '1', '0');
INSERT INTO `box_info` VALUES ('120', 'FTB0120', '2', null, null, '2238', '1', '0');
INSERT INTO `box_info` VALUES ('121', 'FTB0121', '2', null, null, '2239', '1', '0');
INSERT INTO `box_info` VALUES ('122', 'FTB0122', '2', null, null, '2240', '1', '0');
INSERT INTO `box_info` VALUES ('123', 'FTB0123', '2', null, null, '2241', '1', '0');
INSERT INTO `box_info` VALUES ('124', 'FTB0124', '2', null, null, '2242', '1', '0');
INSERT INTO `box_info` VALUES ('125', 'FTB0125', '2', null, null, '2243', '1', '0');
INSERT INTO `box_info` VALUES ('126', 'FTB0126', '2', null, null, '2244', '1', '0');
INSERT INTO `box_info` VALUES ('127', 'FTB0127', '2', null, null, '2245', '1', '0');
INSERT INTO `box_info` VALUES ('128', 'FTB0128', '2', null, null, '2246', '1', '0');
INSERT INTO `box_info` VALUES ('129', 'FTB0129', '2', null, null, '2247', '1', '0');
INSERT INTO `box_info` VALUES ('130', 'FTB0130', '2', null, null, '2248', '1', '0');
INSERT INTO `box_info` VALUES ('131', 'FTB0131', '2', null, null, '2249', '1', '0');
INSERT INTO `box_info` VALUES ('132', 'FTB0132', '2', null, null, '2250', '1', '0');
INSERT INTO `box_info` VALUES ('133', 'FTB0133', '2', null, null, '2251', '1', '0');
INSERT INTO `box_info` VALUES ('134', 'FTB0134', '2', null, null, '2252', '1', '0');
INSERT INTO `box_info` VALUES ('135', 'FTB0135', '2', null, null, '2253', '1', '0');
INSERT INTO `box_info` VALUES ('136', 'FTB0136', '2', null, null, '2254', '1', '0');
INSERT INTO `box_info` VALUES ('137', 'FTB0137', '2', null, null, '2255', '1', '0');
INSERT INTO `box_info` VALUES ('138', 'FTB0138', '2', null, null, '2256', '1', '0');
INSERT INTO `box_info` VALUES ('139', 'FTB0139', '2', null, null, '2257', '1', '0');
INSERT INTO `box_info` VALUES ('140', 'FTB0140', '2', null, null, '2258', '1', '0');
INSERT INTO `box_info` VALUES ('141', 'FTB0141', '2', null, null, '2259', '1', '0');
INSERT INTO `box_info` VALUES ('142', 'FTB0142', '2', null, null, '2260', '1', '0');
INSERT INTO `box_info` VALUES ('143', 'FTB0143', '2', null, null, '2261', '1', '0');
INSERT INTO `box_info` VALUES ('144', 'FTB0144', '2', null, null, '2262', '1', '0');
INSERT INTO `box_info` VALUES ('145', 'FTB0145', '2', null, null, '2263', '1', '0');
INSERT INTO `box_info` VALUES ('146', 'FTB0146', '2', null, null, '2264', '1', '0');
INSERT INTO `box_info` VALUES ('147', 'FTB0147', '2', null, null, '2265', '1', '0');
INSERT INTO `box_info` VALUES ('148', 'FTB0148', '2', null, null, '2266', '1', '0');
INSERT INTO `box_info` VALUES ('149', 'FTB0149', '2', null, null, '2267', '1', '0');
INSERT INTO `box_info` VALUES ('150', 'FTB0150', '2', null, null, '2795', '1', '0');
INSERT INTO `box_info` VALUES ('151', 'FTB0151', '2', null, null, '2796', '1', '0');
INSERT INTO `box_info` VALUES ('152', 'FTB0152', '2', null, null, '2797', '1', '0');
INSERT INTO `box_info` VALUES ('153', 'FTB0153', '2', null, null, '2798', '1', '0');
INSERT INTO `box_info` VALUES ('154', 'FTB0154', '2', null, null, '2799', '1', '0');
INSERT INTO `box_info` VALUES ('155', 'FTB0155', '2', null, null, '2800', '1', '0');
INSERT INTO `box_info` VALUES ('156', 'FTB0156', '2', null, null, '2801', '1', '0');
INSERT INTO `box_info` VALUES ('157', 'FTB0157', '2', null, null, '2802', '1', '0');
INSERT INTO `box_info` VALUES ('158', 'FTB0158', '2', null, null, '2803', '1', '0');
INSERT INTO `box_info` VALUES ('159', 'FTB0159', '2', null, null, '2804', '1', '0');
INSERT INTO `box_info` VALUES ('160', 'FTB0160', '2', null, null, '2805', '1', '0');
INSERT INTO `box_info` VALUES ('161', 'FTB0161', '2', null, null, '2806', '1', '0');
INSERT INTO `box_info` VALUES ('162', 'FTB0162', '2', null, null, '2807', '1', '0');
INSERT INTO `box_info` VALUES ('163', 'FTB0163', '2', null, null, '2808', '1', '0');
INSERT INTO `box_info` VALUES ('164', 'FTB0164', '2', null, null, '2809', '1', '0');
INSERT INTO `box_info` VALUES ('165', 'FTB0165', '2', null, null, '2810', '1', '0');
INSERT INTO `box_info` VALUES ('166', 'FTB0166', '2', null, null, '2811', '1', '0');
INSERT INTO `box_info` VALUES ('167', 'FTB0167', '2', null, null, '2812', '1', '0');
INSERT INTO `box_info` VALUES ('168', 'FTB0168', '2', null, null, '2813', '1', '0');
INSERT INTO `box_info` VALUES ('169', 'FTB0169', '2', null, null, '2814', '1', '0');
INSERT INTO `box_info` VALUES ('170', 'FTB0170', '2', null, null, '2815', '1', '0');
INSERT INTO `box_info` VALUES ('171', 'FTB0171', '2', null, null, '2816', '1', '0');
INSERT INTO `box_info` VALUES ('172', 'FTB0172', '2', null, null, '2817', '1', '0');
INSERT INTO `box_info` VALUES ('173', 'FTB0173', '2', null, null, '2818', '1', '0');
INSERT INTO `box_info` VALUES ('174', 'FTB0174', '2', null, null, '2819', '1', '0');
INSERT INTO `box_info` VALUES ('175', 'FTB0175', '2', null, null, '2820', '1', '0');
INSERT INTO `box_info` VALUES ('176', 'FTB0176', '2', null, null, '2821', '1', '0');
INSERT INTO `box_info` VALUES ('177', 'FTB0177', '2', null, null, '2822', '1', '0');
INSERT INTO `box_info` VALUES ('178', 'FTB0178', '2', null, null, '2823', '1', '0');
INSERT INTO `box_info` VALUES ('179', 'FTB0179', '2', null, null, '2824', '1', '0');
INSERT INTO `box_info` VALUES ('180', 'FTB0180', '2', null, null, '2825', '1', '0');
INSERT INTO `box_info` VALUES ('181', 'FTB0181', '2', null, null, '2268', '1', '0');
INSERT INTO `box_info` VALUES ('182', 'FTB0182', '2', null, null, '2269', '1', '0');
INSERT INTO `box_info` VALUES ('183', 'FTB0183', '2', null, null, '2270', '1', '0');
INSERT INTO `box_info` VALUES ('184', 'FTB0184', '2', null, null, '2271', '1', '0');
INSERT INTO `box_info` VALUES ('185', 'FTB0185', '2', null, null, '2272', '1', '0');
INSERT INTO `box_info` VALUES ('186', 'FTB0186', '2', null, null, '2273', '1', '0');
INSERT INTO `box_info` VALUES ('187', 'FTB0187', '2', null, null, '2274', '1', '0');
INSERT INTO `box_info` VALUES ('188', 'FTB0188', '2', null, null, '2275', '1', '0');
INSERT INTO `box_info` VALUES ('189', 'FTB0189', '2', null, null, '2276', '1', '0');
INSERT INTO `box_info` VALUES ('190', 'FTB0190', '2', null, null, '2277', '1', '0');
INSERT INTO `box_info` VALUES ('191', 'FTB0191', '2', null, null, '2278', '1', '0');
INSERT INTO `box_info` VALUES ('192', 'FTB0192', '2', null, null, '2279', '1', '0');
INSERT INTO `box_info` VALUES ('193', 'FTB0193', '2', null, null, '2280', '1', '0');
INSERT INTO `box_info` VALUES ('194', 'FTB0194', '2', null, null, '2281', '1', '0');
INSERT INTO `box_info` VALUES ('195', 'FTB0195', '2', null, null, '2282', '1', '0');
INSERT INTO `box_info` VALUES ('196', 'FTB0196', '2', null, null, '2283', '1', '0');
INSERT INTO `box_info` VALUES ('197', 'FTB0197', '2', null, null, '2284', '1', '0');
INSERT INTO `box_info` VALUES ('198', 'FTB0198', '2', null, null, '2285', '1', '0');
INSERT INTO `box_info` VALUES ('199', 'FTB0199', '2', null, null, '2286', '1', '0');
INSERT INTO `box_info` VALUES ('200', 'FTB0200', '2', null, null, '2287', '1', '0');
INSERT INTO `box_info` VALUES ('201', 'FTB0201', '2', null, null, '2288', '1', '0');
INSERT INTO `box_info` VALUES ('202', 'FTB0202', '2', null, null, '2289', '1', '0');
INSERT INTO `box_info` VALUES ('203', 'FTB0203', '2', null, null, '2290', '1', '0');
INSERT INTO `box_info` VALUES ('204', 'FTB0204', '2', null, null, '2291', '1', '0');
INSERT INTO `box_info` VALUES ('205', 'FTB0205', '2', null, null, '2292', '1', '0');
INSERT INTO `box_info` VALUES ('206', 'FTB0206', '2', null, null, '2293', '1', '0');
INSERT INTO `box_info` VALUES ('207', 'FTB0207', '2', null, null, '2294', '1', '0');
INSERT INTO `box_info` VALUES ('208', 'FTB0208', '2', null, null, '2295', '1', '0');
INSERT INTO `box_info` VALUES ('209', 'FTB0209', '2', null, null, '2296', '1', '0');
INSERT INTO `box_info` VALUES ('210', 'FTB0210', '2', null, null, '2297', '1', '0');
INSERT INTO `box_info` VALUES ('211', 'FTB0211', '2', null, null, '2298', '1', '0');
INSERT INTO `box_info` VALUES ('212', 'FTB0212', '2', null, null, '2826', '1', '0');
INSERT INTO `box_info` VALUES ('213', 'FTB0213', '2', null, null, '2827', '1', '0');
INSERT INTO `box_info` VALUES ('214', 'FTB0214', '2', null, null, '2828', '1', '0');
INSERT INTO `box_info` VALUES ('215', 'FTB0215', '2', null, null, '2829', '1', '0');
INSERT INTO `box_info` VALUES ('216', 'FTB0216', '2', null, null, '2830', '1', '0');
INSERT INTO `box_info` VALUES ('217', 'FTB0217', '2', null, null, '2831', '1', '0');
INSERT INTO `box_info` VALUES ('218', 'FTB0218', '2', null, null, '2832', '1', '0');
INSERT INTO `box_info` VALUES ('219', 'FTB0219', '2', null, null, '2833', '1', '0');
INSERT INTO `box_info` VALUES ('220', 'FTB0220', '2', null, null, '2834', '1', '0');
INSERT INTO `box_info` VALUES ('221', 'FTB0221', '2', null, null, '2835', '1', '0');
INSERT INTO `box_info` VALUES ('222', 'FTB0222', '2', null, null, '2836', '1', '0');
INSERT INTO `box_info` VALUES ('223', 'FTB0223', '2', null, null, '2837', '1', '0');
INSERT INTO `box_info` VALUES ('224', 'FTB0224', '2', null, null, '2838', '1', '0');
INSERT INTO `box_info` VALUES ('225', 'FTB0225', '2', null, null, '2839', '1', '0');
INSERT INTO `box_info` VALUES ('226', 'FTB0226', '2', null, null, '2840', '1', '0');
INSERT INTO `box_info` VALUES ('227', 'FTB0227', '2', null, null, '2841', '1', '0');
INSERT INTO `box_info` VALUES ('228', 'FTB0228', '2', null, null, '2842', '1', '0');
INSERT INTO `box_info` VALUES ('229', 'FTB0229', '2', null, null, '2843', '1', '0');
INSERT INTO `box_info` VALUES ('230', 'FTB0230', '2', null, null, '2844', '1', '0');
INSERT INTO `box_info` VALUES ('231', 'FTB0231', '2', null, null, '2845', '1', '0');
INSERT INTO `box_info` VALUES ('232', 'FTB0232', '2', null, null, '2846', '1', '0');
INSERT INTO `box_info` VALUES ('233', 'FTB0233', '2', null, null, '2847', '1', '0');
INSERT INTO `box_info` VALUES ('234', 'FTB0234', '2', null, null, '2848', '1', '0');
INSERT INTO `box_info` VALUES ('235', 'FTB0235', '2', null, null, '2849', '1', '0');
INSERT INTO `box_info` VALUES ('236', 'FTB0236', '2', null, null, '2850', '1', '0');
INSERT INTO `box_info` VALUES ('237', 'FTB0237', '2', null, null, '2851', '1', '0');
INSERT INTO `box_info` VALUES ('238', 'FTB0238', '2', null, null, '2852', '1', '0');
INSERT INTO `box_info` VALUES ('239', 'FTB0239', '2', null, null, '2853', '1', '0');
INSERT INTO `box_info` VALUES ('240', 'FTB0240', '2', null, null, '2854', '1', '0');
INSERT INTO `box_info` VALUES ('241', 'FTB0241', '2', null, null, '2855', '1', '0');
INSERT INTO `box_info` VALUES ('242', 'FTB0242', '2', null, null, '2856', '1', '0');
INSERT INTO `box_info` VALUES ('243', 'FTB0243', '2', null, null, '2299', '1', '0');
INSERT INTO `box_info` VALUES ('244', 'FTB0244', '2', null, null, '2300', '1', '0');
INSERT INTO `box_info` VALUES ('245', 'FTB0245', '2', null, null, '2301', '1', '0');
INSERT INTO `box_info` VALUES ('246', 'FTB0246', '2', null, null, '2302', '1', '0');
INSERT INTO `box_info` VALUES ('247', 'FTB0247', '2', null, null, '2303', '1', '0');
INSERT INTO `box_info` VALUES ('248', 'FTB0248', '2', null, null, '2304', '1', '0');
INSERT INTO `box_info` VALUES ('249', 'FTB0249', '2', null, null, '2305', '1', '0');
INSERT INTO `box_info` VALUES ('250', 'FTB0250', '2', null, null, '2306', '1', '0');
INSERT INTO `box_info` VALUES ('251', 'FTB0251', '2', null, null, '2307', '1', '0');
INSERT INTO `box_info` VALUES ('252', 'FTB0252', '2', null, null, '2308', '1', '0');
INSERT INTO `box_info` VALUES ('253', 'FTB0253', '2', null, null, '2309', '1', '0');
INSERT INTO `box_info` VALUES ('254', 'FTB0254', '2', null, null, '2310', '1', '0');
INSERT INTO `box_info` VALUES ('255', 'FTB0255', '2', null, null, '2311', '1', '0');
INSERT INTO `box_info` VALUES ('256', 'FTB0256', '2', null, null, '2312', '1', '0');
INSERT INTO `box_info` VALUES ('257', 'FTB0257', '2', null, null, '2313', '1', '0');
INSERT INTO `box_info` VALUES ('258', 'FTB0258', '2', null, null, '2314', '1', '0');
INSERT INTO `box_info` VALUES ('259', 'FTB0259', '2', null, null, '2315', '1', '0');
INSERT INTO `box_info` VALUES ('260', 'FTB0260', '2', null, null, '2316', '1', '0');
INSERT INTO `box_info` VALUES ('261', 'FTB0261', '2', null, null, '2317', '1', '0');
INSERT INTO `box_info` VALUES ('262', 'FTB0262', '2', null, null, '2318', '1', '0');
INSERT INTO `box_info` VALUES ('263', 'FTB0263', '2', null, null, '2319', '1', '0');
INSERT INTO `box_info` VALUES ('264', 'FTB0264', '2', null, null, '2320', '1', '0');
INSERT INTO `box_info` VALUES ('265', 'FTB0265', '2', null, null, '2321', '1', '0');
INSERT INTO `box_info` VALUES ('266', 'FTB0266', '2', null, null, '2322', '1', '0');
INSERT INTO `box_info` VALUES ('267', 'FTB0267', '2', null, null, '2323', '1', '0');
INSERT INTO `box_info` VALUES ('268', 'FTB0268', '2', null, null, '2324', '1', '0');
INSERT INTO `box_info` VALUES ('269', 'FTB0269', '2', null, null, '2325', '1', '0');
INSERT INTO `box_info` VALUES ('270', 'FTB0270', '2', null, null, '2326', '1', '0');
INSERT INTO `box_info` VALUES ('271', 'FTB0271', '2', null, null, '2327', '1', '0');
INSERT INTO `box_info` VALUES ('272', 'FTB0272', '2', null, null, '2328', '1', '0');
INSERT INTO `box_info` VALUES ('273', 'FTB0273', '2', null, null, '2329', '1', '0');
INSERT INTO `box_info` VALUES ('274', 'FTB0274', '2', null, null, '2857', '1', '0');
INSERT INTO `box_info` VALUES ('275', 'FTB0275', '2', null, null, '2858', '1', '0');
INSERT INTO `box_info` VALUES ('276', 'FTB0276', '2', null, null, '2859', '1', '0');
INSERT INTO `box_info` VALUES ('277', 'FTB0277', '2', null, null, '2860', '1', '0');
INSERT INTO `box_info` VALUES ('278', 'FTB0278', '2', null, null, '2861', '1', '0');
INSERT INTO `box_info` VALUES ('279', 'FTB0279', '2', null, null, '2862', '1', '0');
INSERT INTO `box_info` VALUES ('280', 'FTB0280', '2', null, null, '2863', '1', '0');
INSERT INTO `box_info` VALUES ('281', 'FTB0281', '2', null, null, '2864', '1', '0');
INSERT INTO `box_info` VALUES ('282', 'FTB0282', '2', null, null, '2865', '1', '0');
INSERT INTO `box_info` VALUES ('283', 'FTB0283', '2', null, null, '2866', '1', '0');
INSERT INTO `box_info` VALUES ('284', 'FTB0284', '2', null, null, '2867', '1', '0');
INSERT INTO `box_info` VALUES ('285', 'FTB0285', '2', null, null, '2868', '1', '0');
INSERT INTO `box_info` VALUES ('286', 'FTB0286', '2', null, null, '2869', '1', '0');
INSERT INTO `box_info` VALUES ('287', 'FTB0287', '2', null, null, '2870', '1', '0');
INSERT INTO `box_info` VALUES ('288', 'FTB0288', '2', null, null, '2871', '1', '0');
INSERT INTO `box_info` VALUES ('289', 'FTB0289', '2', null, null, '2872', '1', '0');
INSERT INTO `box_info` VALUES ('290', 'FTB0290', '2', null, null, '2873', '1', '0');
INSERT INTO `box_info` VALUES ('291', 'FTB0291', '2', null, null, '2874', '1', '0');
INSERT INTO `box_info` VALUES ('292', 'FTB0292', '2', null, null, '2875', '1', '0');
INSERT INTO `box_info` VALUES ('293', 'FTB0293', '2', null, null, '2876', '1', '0');
INSERT INTO `box_info` VALUES ('294', 'FTB0294', '2', null, null, '2877', '1', '0');
INSERT INTO `box_info` VALUES ('295', 'FTB0295', '2', null, null, '2878', '1', '0');
INSERT INTO `box_info` VALUES ('296', 'FTB0296', '2', null, null, '2879', '1', '0');

-- ----------------------------
-- Table structure for `box_item`
-- ----------------------------
DROP TABLE IF EXISTS `box_item`;
CREATE TABLE `box_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `box_code` varchar(50) DEFAULT NULL COMMENT '托盘/料箱编码',
  `item_code` varchar(50) DEFAULT NULL COMMENT '物料编码',
  `batch` varchar(255) DEFAULT NULL COMMENT '批次',
  `quantity` int(16) DEFAULT NULL,
  `forecast_stock_quantity` int(16) DEFAULT '0' COMMENT '锁定库存数量',
  `work_order_stock_state` int(11) DEFAULT '0' COMMENT '是否是工单备料 0-否，1-是',
  `bill_in_detail_id` int(11) DEFAULT NULL COMMENT '入库单行ID',
  `sub_inventory_id` int(11) DEFAULT NULL COMMENT '用来存放当前批次料号的状态',
  `pd` date DEFAULT NULL,
  `exp` date DEFAULT NULL,
  `in_time` datetime DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `box_item_bill_no` (`bill_in_detail_id`) USING BTREE,
  KEY `box_item_box_code` (`box_code`) USING BTREE,
  KEY `box_item_id_PK` (`id`) USING BTREE,
  KEY `box_item_item_code` (`item_code`) USING BTREE,
  KEY `box_item_batch` (`batch`) USING BTREE,
  KEY `box_item_item_state` (`sub_inventory_id`) USING BTREE,
  KEY `box_item_exp` (`exp`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组盘信息';

-- ----------------------------
-- Records of box_item
-- ----------------------------
INSERT INTO `box_item` VALUES ('1', 'FTB0001', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('2', 'FTB0002', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('3', 'FTB0003', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('4', 'FTB0004', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('5', 'FTB0005', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('6', 'FTB0006', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('7', 'FTB0007', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('8', 'FTB0008', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('9', 'FTB0009', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('10', 'FTB0010', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('11', 'FTB0011', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('12', 'FTB0012', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('13', 'FTB0013', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('14', 'FTB0014', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('15', 'FTB0015', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('16', 'FTB0016', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('17', 'FTB0017', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('18', 'FTB0018', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('19', 'FTB0019', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('20', 'FTB0020', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('21', 'FTB0021', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('22', 'FTB0022', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('23', 'FTB0023', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('24', 'FTB0024', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('25', 'FTB0025', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('26', 'FTB0026', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('27', 'FTB0027', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('28', 'FTB0028', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('29', 'FTB0029', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('30', 'FTB0030', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('31', 'FTB0031', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('32', 'FTB0032', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('33', 'FTB0033', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('34', 'FTB0034', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('35', 'FTB0035', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('36', 'FTB0036', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('37', 'FTB0037', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('38', 'FTB0038', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('39', 'FTB0039', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('40', 'FTB0040', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('41', 'FTB0041', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('42', 'FTB0042', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('43', 'FTB0043', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('44', 'FTB0044', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('45', 'FTB0045', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('46', 'FTB0046', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('47', 'FTB0047', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('48', 'FTB0048', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('49', 'FTB0049', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('50', 'FTB0050', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('51', 'FTB0051', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('52', 'FTB0052', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('53', 'FTB0053', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('54', 'FTB0054', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('55', 'FTB0055', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('56', 'FTB0056', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('57', 'FTB0057', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('58', 'FTB0058', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('59', 'FTB0059', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('60', 'FTB0060', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('61', 'FTB0061', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('62', 'FTB0062', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('63', 'FTB0063', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('64', 'FTB0064', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('65', 'FTB0065', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('66', 'FTB0066', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('67', 'FTB0067', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('68', 'FTB0068', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('69', 'FTB0069', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('70', 'FTB0070', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('71', 'FTB0071', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('72', 'FTB0072', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('73', 'FTB0073', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('74', 'FTB0074', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('75', 'FTB0075', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('76', 'FTB0076', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('77', 'FTB0077', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('78', 'FTB0078', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('79', 'FTB0079', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('80', 'FTB0080', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('81', 'FTB0081', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('82', 'FTB0082', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('83', 'FTB0083', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('84', 'FTB0084', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('85', 'FTB0085', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('86', 'FTB0086', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('87', 'FTB0087', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('88', 'FTB0088', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('89', 'FTB0089', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('90', 'FTB0090', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('91', 'FTB0091', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('92', 'FTB0092', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('93', 'FTB0093', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('94', 'FTB0094', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('95', 'FTB0095', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('96', 'FTB0096', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('97', 'FTB0097', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('98', 'FTB0098', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('99', 'FTB0099', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('100', 'FTB0100', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('101', 'FTB0101', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('102', 'FTB0102', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('103', 'FTB0103', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('104', 'FTB0104', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('105', 'FTB0105', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('106', 'FTB0106', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('107', 'FTB0107', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('108', 'FTB0108', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('109', 'FTB0109', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('110', 'FTB0110', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('111', 'FTB0111', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('112', 'FTB0112', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('113', 'FTB0113', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('114', 'FTB0114', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('115', 'FTB0115', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('116', 'FTB0116', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('117', 'FTB0117', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('118', 'FTB0118', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('119', 'FTB0119', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('120', 'FTB0120', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('121', 'FTB0121', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('122', 'FTB0122', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('123', 'FTB0123', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('124', 'FTB0124', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('125', 'FTB0125', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('126', 'FTB0126', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('127', 'FTB0127', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('128', 'FTB0128', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('129', 'FTB0129', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('130', 'FTB0130', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('131', 'FTB0131', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('132', 'FTB0132', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('133', 'FTB0133', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('134', 'FTB0134', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('135', 'FTB0135', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('136', 'FTB0136', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('137', 'FTB0137', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('138', 'FTB0138', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('139', 'FTB0139', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('140', 'FTB0140', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('141', 'FTB0141', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('142', 'FTB0142', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('143', 'FTB0143', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('144', 'FTB0144', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('145', 'FTB0145', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('146', 'FTB0146', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('147', 'FTB0147', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('148', 'FTB0148', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('149', 'FTB0149', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('150', 'FTB0150', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('151', 'FTB0151', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('152', 'FTB0152', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('153', 'FTB0153', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('154', 'FTB0154', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('155', 'FTB0155', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('156', 'FTB0156', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('157', 'FTB0157', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('158', 'FTB0158', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('159', 'FTB0159', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('160', 'FTB0160', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('161', 'FTB0161', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('162', 'FTB0162', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('163', 'FTB0163', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('164', 'FTB0164', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('165', 'FTB0165', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('166', 'FTB0166', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('167', 'FTB0167', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('168', 'FTB0168', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('169', 'FTB0169', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('170', 'FTB0170', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('171', 'FTB0171', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('172', 'FTB0172', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('173', 'FTB0173', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('174', 'FTB0174', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('175', 'FTB0175', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('176', 'FTB0176', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('177', 'FTB0177', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('178', 'FTB0178', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('179', 'FTB0179', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('180', 'FTB0180', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('181', 'FTB0181', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('182', 'FTB0182', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('183', 'FTB0183', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('184', 'FTB0184', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('185', 'FTB0185', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('186', 'FTB0186', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('187', 'FTB0187', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('188', 'FTB0188', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('189', 'FTB0189', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('190', 'FTB0190', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('191', 'FTB0191', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('192', 'FTB0192', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('193', 'FTB0193', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('194', 'FTB0194', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('195', 'FTB0195', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('196', 'FTB0196', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('197', 'FTB0197', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('198', 'FTB0198', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('199', 'FTB0199', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('200', 'FTB0200', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('201', 'FTB0201', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('202', 'FTB0202', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('203', 'FTB0203', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('204', 'FTB0204', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('205', 'FTB0205', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('206', 'FTB0206', null, null, '0', null, null, null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('207', 'FTB0207', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('208', 'FTB0208', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('209', 'FTB0209', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('210', 'FTB0210', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('211', 'FTB0211', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('212', 'FTB0212', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('213', 'FTB0213', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('214', 'FTB0214', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('215', 'FTB0215', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('216', 'FTB0216', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('217', 'FTB0217', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('218', 'FTB0218', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('219', 'FTB0219', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('220', 'FTB0220', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('221', 'FTB0221', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('222', 'FTB0222', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('223', 'FTB0223', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('224', 'FTB0224', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('225', 'FTB0225', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('226', 'FTB0226', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('227', 'FTB0227', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('228', 'FTB0228', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('229', 'FTB0229', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('230', 'FTB0230', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('231', 'FTB0231', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('232', 'FTB0232', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('233', 'FTB0233', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('234', 'FTB0234', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('235', 'FTB0235', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('236', 'FTB0236', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('237', 'FTB0237', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('238', 'FTB0238', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('239', 'FTB0239', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('240', 'FTB0240', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('241', 'FTB0241', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('242', 'FTB0242', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('243', 'FTB0243', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('244', 'FTB0244', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('245', 'FTB0245', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('246', 'FTB0246', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('247', 'FTB0247', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('248', 'FTB0248', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('249', 'FTB0249', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('250', 'FTB0250', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('251', 'FTB0251', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('252', 'FTB0252', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('253', 'FTB0253', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('254', 'FTB0254', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('255', 'FTB0255', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('256', 'FTB0256', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('257', 'FTB0257', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('258', 'FTB0258', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('259', 'FTB0259', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('260', 'FTB0260', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('261', 'FTB0261', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('262', 'FTB0262', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('263', 'FTB0263', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('264', 'FTB0264', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('265', 'FTB0265', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('266', 'FTB0266', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('267', 'FTB0267', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('268', 'FTB0268', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('269', 'FTB0269', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('270', 'FTB0270', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('271', 'FTB0271', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('272', 'FTB0272', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('273', 'FTB0273', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('274', 'FTB0274', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('275', 'FTB0275', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('276', 'FTB0276', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('277', 'FTB0277', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('278', 'FTB0278', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('279', 'FTB0279', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('280', 'FTB0280', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('281', 'FTB0281', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('282', 'FTB0282', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('283', 'FTB0283', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('284', 'FTB0284', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('285', 'FTB0285', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('286', 'FTB0286', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('287', 'FTB0287', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('288', 'FTB0288', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('289', 'FTB0289', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('290', 'FTB0290', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('291', 'FTB0291', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('292', 'FTB0292', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('293', 'FTB0293', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('294', 'FTB0294', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('295', 'FTB0295', null, null, '0', '0', '0', null, '0', null, null, null);
INSERT INTO `box_item` VALUES ('296', 'FTB0296', null, null, '0', '0', '0', null, '0', null, null, null);

-- ----------------------------
-- Table structure for `call_agv`
-- ----------------------------
DROP TABLE IF EXISTS `call_agv`;
CREATE TABLE `call_agv` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '呼叫空载具任务',
  `code` varchar(3000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '编码',
  `state` int(11) DEFAULT NULL COMMENT '状态 1-已呼叫  2-已完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of call_agv
-- ----------------------------

-- ----------------------------
-- Table structure for `carrier`
-- ----------------------------
DROP TABLE IF EXISTS `carrier`;
CREATE TABLE `carrier` (
  `carrier_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'AGV载具id',
  `carrier_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '载具编码',
  `carrier_state` int(2) DEFAULT NULL COMMENT '载具状态',
  `carrier_task_id` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '运送载具的任务号',
  `create_time` datetime DEFAULT NULL COMMENT 'mes空载具到达立体库入料口',
  `code` varchar(3000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`carrier_id`) USING BTREE,
  KEY `carrier_id_PK` (`carrier_id`) USING BTREE,
  KEY `carrier_state` (`carrier_state`) USING BTREE,
  KEY `carrier_code` (`carrier_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of carrier
-- ----------------------------

-- ----------------------------
-- Table structure for `cell_info`
-- ----------------------------
DROP TABLE IF EXISTS `cell_info`;
CREATE TABLE `cell_info` (
  `cell_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货位ID',
  `shelf_id` int(11) NOT NULL COMMENT '货架编码',
  `cell_code` varchar(30) DEFAULT NULL COMMENT '货物编码',
  `memo` varchar(30) DEFAULT NULL COMMENT '备注',
  `s_row` int(11) DEFAULT NULL COMMENT '层',
  `s_column` int(11) DEFAULT NULL COMMENT '列',
  `state` int(11) DEFAULT '0' COMMENT '状态  ：0-无托盘；1-有货；2-出库中；3-入库中；4故障',
  PRIMARY KEY (`cell_id`) USING BTREE,
  KEY `cell_info_shelf_id` (`shelf_id`) USING BTREE,
  KEY `cell_info_state` (`state`) USING BTREE,
  KEY `cell_info_cell_id_PK` (`cell_id`) USING BTREE,
  KEY `cell_info_s_row` (`s_row`) USING BTREE,
  KEY `cell_info_s_column` (`s_column`) USING BTREE,
  CONSTRAINT `cell_info_ibfk_1` FOREIGN KEY (`shelf_id`) REFERENCES `shelf_info` (`shelf_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3291 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='货位设置';

-- ----------------------------
-- Records of cell_info
-- ----------------------------
INSERT INTO `cell_info` VALUES ('2175', '39', null, null, '1', '1', '2');
INSERT INTO `cell_info` VALUES ('2176', '39', null, null, '1', '2', '1');
INSERT INTO `cell_info` VALUES ('2177', '39', null, null, '1', '3', '1');
INSERT INTO `cell_info` VALUES ('2178', '39', null, null, '1', '4', '1');
INSERT INTO `cell_info` VALUES ('2179', '39', null, null, '1', '5', '2');
INSERT INTO `cell_info` VALUES ('2180', '39', null, null, '1', '6', '1');
INSERT INTO `cell_info` VALUES ('2181', '39', null, null, '1', '7', '1');
INSERT INTO `cell_info` VALUES ('2182', '39', null, null, '1', '8', '1');
INSERT INTO `cell_info` VALUES ('2183', '39', null, null, '1', '9', '2');
INSERT INTO `cell_info` VALUES ('2184', '39', null, null, '1', '10', '1');
INSERT INTO `cell_info` VALUES ('2185', '39', null, null, '1', '11', '2');
INSERT INTO `cell_info` VALUES ('2186', '39', null, null, '1', '12', '1');
INSERT INTO `cell_info` VALUES ('2187', '39', null, null, '1', '13', '2');
INSERT INTO `cell_info` VALUES ('2188', '39', null, null, '1', '14', '1');
INSERT INTO `cell_info` VALUES ('2189', '39', null, null, '1', '15', '1');
INSERT INTO `cell_info` VALUES ('2190', '39', null, null, '1', '16', '1');
INSERT INTO `cell_info` VALUES ('2191', '39', null, null, '1', '17', '1');
INSERT INTO `cell_info` VALUES ('2192', '39', null, null, '1', '18', '1');
INSERT INTO `cell_info` VALUES ('2193', '39', null, null, '1', '19', '1');
INSERT INTO `cell_info` VALUES ('2194', '39', null, null, '1', '20', '1');
INSERT INTO `cell_info` VALUES ('2195', '39', null, null, '1', '21', '2');
INSERT INTO `cell_info` VALUES ('2196', '39', null, null, '1', '22', '1');
INSERT INTO `cell_info` VALUES ('2197', '39', null, null, '1', '23', '2');
INSERT INTO `cell_info` VALUES ('2198', '39', null, null, '1', '24', '1');
INSERT INTO `cell_info` VALUES ('2199', '39', null, null, '1', '25', '1');
INSERT INTO `cell_info` VALUES ('2200', '39', null, null, '1', '26', '1');
INSERT INTO `cell_info` VALUES ('2201', '39', null, null, '1', '27', '1');
INSERT INTO `cell_info` VALUES ('2202', '39', null, null, '1', '28', '1');
INSERT INTO `cell_info` VALUES ('2203', '39', null, null, '1', '29', '1');
INSERT INTO `cell_info` VALUES ('2204', '39', null, null, '1', '30', '1');
INSERT INTO `cell_info` VALUES ('2205', '39', null, null, '1', '31', '1');
INSERT INTO `cell_info` VALUES ('2206', '39', null, null, '2', '1', '1');
INSERT INTO `cell_info` VALUES ('2207', '39', null, null, '2', '2', '1');
INSERT INTO `cell_info` VALUES ('2208', '39', null, null, '2', '3', '1');
INSERT INTO `cell_info` VALUES ('2209', '39', null, null, '2', '4', '1');
INSERT INTO `cell_info` VALUES ('2210', '39', null, null, '2', '5', '1');
INSERT INTO `cell_info` VALUES ('2211', '39', null, null, '2', '6', '1');
INSERT INTO `cell_info` VALUES ('2212', '39', null, null, '2', '7', '1');
INSERT INTO `cell_info` VALUES ('2213', '39', null, null, '2', '8', '1');
INSERT INTO `cell_info` VALUES ('2214', '39', null, null, '2', '9', '1');
INSERT INTO `cell_info` VALUES ('2215', '39', null, null, '2', '10', '1');
INSERT INTO `cell_info` VALUES ('2216', '39', null, null, '2', '11', '1');
INSERT INTO `cell_info` VALUES ('2217', '39', null, null, '2', '12', '1');
INSERT INTO `cell_info` VALUES ('2218', '39', null, null, '2', '13', '1');
INSERT INTO `cell_info` VALUES ('2219', '39', null, null, '2', '14', '1');
INSERT INTO `cell_info` VALUES ('2220', '39', null, null, '2', '15', '1');
INSERT INTO `cell_info` VALUES ('2221', '39', null, null, '2', '16', '1');
INSERT INTO `cell_info` VALUES ('2222', '39', null, null, '2', '17', '1');
INSERT INTO `cell_info` VALUES ('2223', '39', null, null, '2', '18', '1');
INSERT INTO `cell_info` VALUES ('2224', '39', null, null, '2', '19', '1');
INSERT INTO `cell_info` VALUES ('2225', '39', null, null, '2', '20', '1');
INSERT INTO `cell_info` VALUES ('2226', '39', null, null, '2', '21', '1');
INSERT INTO `cell_info` VALUES ('2227', '39', null, null, '2', '22', '1');
INSERT INTO `cell_info` VALUES ('2228', '39', null, null, '2', '23', '1');
INSERT INTO `cell_info` VALUES ('2229', '39', null, null, '2', '24', '1');
INSERT INTO `cell_info` VALUES ('2230', '39', null, null, '2', '25', '1');
INSERT INTO `cell_info` VALUES ('2231', '39', null, null, '2', '26', '1');
INSERT INTO `cell_info` VALUES ('2232', '39', null, null, '2', '27', '1');
INSERT INTO `cell_info` VALUES ('2233', '39', null, null, '2', '28', '1');
INSERT INTO `cell_info` VALUES ('2234', '39', null, null, '2', '29', '1');
INSERT INTO `cell_info` VALUES ('2235', '39', null, null, '2', '30', '1');
INSERT INTO `cell_info` VALUES ('2236', '39', null, null, '2', '31', '1');
INSERT INTO `cell_info` VALUES ('2237', '39', null, null, '3', '1', '1');
INSERT INTO `cell_info` VALUES ('2238', '39', null, null, '3', '2', '1');
INSERT INTO `cell_info` VALUES ('2239', '39', null, null, '3', '3', '1');
INSERT INTO `cell_info` VALUES ('2240', '39', null, null, '3', '4', '1');
INSERT INTO `cell_info` VALUES ('2241', '39', null, null, '3', '5', '1');
INSERT INTO `cell_info` VALUES ('2242', '39', null, null, '3', '6', '1');
INSERT INTO `cell_info` VALUES ('2243', '39', null, null, '3', '7', '1');
INSERT INTO `cell_info` VALUES ('2244', '39', null, null, '3', '8', '1');
INSERT INTO `cell_info` VALUES ('2245', '39', null, null, '3', '9', '1');
INSERT INTO `cell_info` VALUES ('2246', '39', null, null, '3', '10', '1');
INSERT INTO `cell_info` VALUES ('2247', '39', null, null, '3', '11', '1');
INSERT INTO `cell_info` VALUES ('2248', '39', null, null, '3', '12', '1');
INSERT INTO `cell_info` VALUES ('2249', '39', null, null, '3', '13', '1');
INSERT INTO `cell_info` VALUES ('2250', '39', null, null, '3', '14', '1');
INSERT INTO `cell_info` VALUES ('2251', '39', null, null, '3', '15', '1');
INSERT INTO `cell_info` VALUES ('2252', '39', null, null, '3', '16', '1');
INSERT INTO `cell_info` VALUES ('2253', '39', null, null, '3', '17', '1');
INSERT INTO `cell_info` VALUES ('2254', '39', null, null, '3', '18', '1');
INSERT INTO `cell_info` VALUES ('2255', '39', null, null, '3', '19', '1');
INSERT INTO `cell_info` VALUES ('2256', '39', null, null, '3', '20', '1');
INSERT INTO `cell_info` VALUES ('2257', '39', null, null, '3', '21', '1');
INSERT INTO `cell_info` VALUES ('2258', '39', null, null, '3', '22', '1');
INSERT INTO `cell_info` VALUES ('2259', '39', null, null, '3', '23', '1');
INSERT INTO `cell_info` VALUES ('2260', '39', null, null, '3', '24', '1');
INSERT INTO `cell_info` VALUES ('2261', '39', null, null, '3', '25', '1');
INSERT INTO `cell_info` VALUES ('2262', '39', null, null, '3', '26', '1');
INSERT INTO `cell_info` VALUES ('2263', '39', null, null, '3', '27', '1');
INSERT INTO `cell_info` VALUES ('2264', '39', null, null, '3', '28', '1');
INSERT INTO `cell_info` VALUES ('2265', '39', null, null, '3', '29', '1');
INSERT INTO `cell_info` VALUES ('2266', '39', null, null, '3', '30', '1');
INSERT INTO `cell_info` VALUES ('2267', '39', null, null, '3', '31', '1');
INSERT INTO `cell_info` VALUES ('2268', '39', null, null, '4', '1', '1');
INSERT INTO `cell_info` VALUES ('2269', '39', null, null, '4', '2', '1');
INSERT INTO `cell_info` VALUES ('2270', '39', null, null, '4', '3', '1');
INSERT INTO `cell_info` VALUES ('2271', '39', null, null, '4', '4', '1');
INSERT INTO `cell_info` VALUES ('2272', '39', null, null, '4', '5', '1');
INSERT INTO `cell_info` VALUES ('2273', '39', null, null, '4', '6', '1');
INSERT INTO `cell_info` VALUES ('2274', '39', null, null, '4', '7', '1');
INSERT INTO `cell_info` VALUES ('2275', '39', null, null, '4', '8', '1');
INSERT INTO `cell_info` VALUES ('2276', '39', null, null, '4', '9', '1');
INSERT INTO `cell_info` VALUES ('2277', '39', null, null, '4', '10', '1');
INSERT INTO `cell_info` VALUES ('2278', '39', null, null, '4', '11', '1');
INSERT INTO `cell_info` VALUES ('2279', '39', null, null, '4', '12', '1');
INSERT INTO `cell_info` VALUES ('2280', '39', null, null, '4', '13', '1');
INSERT INTO `cell_info` VALUES ('2281', '39', null, null, '4', '14', '1');
INSERT INTO `cell_info` VALUES ('2282', '39', null, null, '4', '15', '1');
INSERT INTO `cell_info` VALUES ('2283', '39', null, null, '4', '16', '1');
INSERT INTO `cell_info` VALUES ('2284', '39', null, null, '4', '17', '1');
INSERT INTO `cell_info` VALUES ('2285', '39', null, null, '4', '18', '1');
INSERT INTO `cell_info` VALUES ('2286', '39', null, null, '4', '19', '1');
INSERT INTO `cell_info` VALUES ('2287', '39', null, null, '4', '20', '1');
INSERT INTO `cell_info` VALUES ('2288', '39', null, null, '4', '21', '1');
INSERT INTO `cell_info` VALUES ('2289', '39', null, null, '4', '22', '1');
INSERT INTO `cell_info` VALUES ('2290', '39', null, null, '4', '23', '1');
INSERT INTO `cell_info` VALUES ('2291', '39', null, null, '4', '24', '1');
INSERT INTO `cell_info` VALUES ('2292', '39', null, null, '4', '25', '1');
INSERT INTO `cell_info` VALUES ('2293', '39', null, null, '4', '26', '1');
INSERT INTO `cell_info` VALUES ('2294', '39', null, null, '4', '27', '1');
INSERT INTO `cell_info` VALUES ('2295', '39', null, null, '4', '28', '1');
INSERT INTO `cell_info` VALUES ('2296', '39', null, null, '4', '29', '1');
INSERT INTO `cell_info` VALUES ('2297', '39', null, null, '4', '30', '1');
INSERT INTO `cell_info` VALUES ('2298', '39', null, null, '4', '31', '1');
INSERT INTO `cell_info` VALUES ('2299', '39', null, null, '5', '1', '1');
INSERT INTO `cell_info` VALUES ('2300', '39', null, null, '5', '2', '1');
INSERT INTO `cell_info` VALUES ('2301', '39', null, null, '5', '3', '1');
INSERT INTO `cell_info` VALUES ('2302', '39', null, null, '5', '4', '1');
INSERT INTO `cell_info` VALUES ('2303', '39', null, null, '5', '5', '1');
INSERT INTO `cell_info` VALUES ('2304', '39', null, null, '5', '6', '1');
INSERT INTO `cell_info` VALUES ('2305', '39', null, null, '5', '7', '1');
INSERT INTO `cell_info` VALUES ('2306', '39', null, null, '5', '8', '1');
INSERT INTO `cell_info` VALUES ('2307', '39', null, null, '5', '9', '1');
INSERT INTO `cell_info` VALUES ('2308', '39', null, null, '5', '10', '1');
INSERT INTO `cell_info` VALUES ('2309', '39', null, null, '5', '11', '1');
INSERT INTO `cell_info` VALUES ('2310', '39', null, null, '5', '12', '1');
INSERT INTO `cell_info` VALUES ('2311', '39', null, null, '5', '13', '1');
INSERT INTO `cell_info` VALUES ('2312', '39', null, null, '5', '14', '1');
INSERT INTO `cell_info` VALUES ('2313', '39', null, null, '5', '15', '1');
INSERT INTO `cell_info` VALUES ('2314', '39', null, null, '5', '16', '1');
INSERT INTO `cell_info` VALUES ('2315', '39', null, null, '5', '17', '1');
INSERT INTO `cell_info` VALUES ('2316', '39', null, null, '5', '18', '1');
INSERT INTO `cell_info` VALUES ('2317', '39', null, null, '5', '19', '1');
INSERT INTO `cell_info` VALUES ('2318', '39', null, null, '5', '20', '1');
INSERT INTO `cell_info` VALUES ('2319', '39', null, null, '5', '21', '1');
INSERT INTO `cell_info` VALUES ('2320', '39', null, null, '5', '22', '1');
INSERT INTO `cell_info` VALUES ('2321', '39', null, null, '5', '23', '1');
INSERT INTO `cell_info` VALUES ('2322', '39', null, null, '5', '24', '1');
INSERT INTO `cell_info` VALUES ('2323', '39', null, null, '5', '25', '1');
INSERT INTO `cell_info` VALUES ('2324', '39', null, null, '5', '26', '1');
INSERT INTO `cell_info` VALUES ('2325', '39', null, null, '5', '27', '1');
INSERT INTO `cell_info` VALUES ('2326', '39', null, null, '5', '28', '1');
INSERT INTO `cell_info` VALUES ('2327', '39', null, null, '5', '29', '1');
INSERT INTO `cell_info` VALUES ('2328', '39', null, null, '5', '30', '1');
INSERT INTO `cell_info` VALUES ('2329', '39', null, null, '5', '31', '1');
INSERT INTO `cell_info` VALUES ('2330', '39', null, null, '6', '1', '0');
INSERT INTO `cell_info` VALUES ('2331', '39', null, null, '6', '2', '0');
INSERT INTO `cell_info` VALUES ('2332', '39', null, null, '6', '3', '0');
INSERT INTO `cell_info` VALUES ('2333', '39', null, null, '6', '4', '0');
INSERT INTO `cell_info` VALUES ('2334', '39', null, null, '6', '5', '0');
INSERT INTO `cell_info` VALUES ('2335', '39', null, null, '6', '6', '0');
INSERT INTO `cell_info` VALUES ('2336', '39', null, null, '6', '7', '0');
INSERT INTO `cell_info` VALUES ('2337', '39', null, null, '6', '8', '0');
INSERT INTO `cell_info` VALUES ('2338', '39', null, null, '6', '9', '0');
INSERT INTO `cell_info` VALUES ('2339', '39', null, null, '6', '10', '0');
INSERT INTO `cell_info` VALUES ('2340', '39', null, null, '6', '11', '0');
INSERT INTO `cell_info` VALUES ('2341', '39', null, null, '6', '12', '0');
INSERT INTO `cell_info` VALUES ('2342', '39', null, null, '6', '13', '0');
INSERT INTO `cell_info` VALUES ('2343', '39', null, null, '6', '14', '0');
INSERT INTO `cell_info` VALUES ('2344', '39', null, null, '6', '15', '0');
INSERT INTO `cell_info` VALUES ('2345', '39', null, null, '6', '16', '0');
INSERT INTO `cell_info` VALUES ('2346', '39', null, null, '6', '17', '0');
INSERT INTO `cell_info` VALUES ('2347', '39', null, null, '6', '18', '0');
INSERT INTO `cell_info` VALUES ('2348', '39', null, null, '6', '19', '0');
INSERT INTO `cell_info` VALUES ('2349', '39', null, null, '6', '20', '0');
INSERT INTO `cell_info` VALUES ('2350', '39', null, null, '6', '21', '0');
INSERT INTO `cell_info` VALUES ('2351', '39', null, null, '6', '22', '0');
INSERT INTO `cell_info` VALUES ('2352', '39', null, null, '6', '23', '0');
INSERT INTO `cell_info` VALUES ('2353', '39', null, null, '6', '24', '0');
INSERT INTO `cell_info` VALUES ('2354', '39', null, null, '6', '25', '0');
INSERT INTO `cell_info` VALUES ('2355', '39', null, null, '6', '26', '0');
INSERT INTO `cell_info` VALUES ('2356', '39', null, null, '6', '27', '0');
INSERT INTO `cell_info` VALUES ('2357', '39', null, null, '6', '28', '0');
INSERT INTO `cell_info` VALUES ('2358', '39', null, null, '6', '29', '0');
INSERT INTO `cell_info` VALUES ('2359', '39', null, null, '6', '30', '0');
INSERT INTO `cell_info` VALUES ('2360', '39', null, null, '6', '31', '0');
INSERT INTO `cell_info` VALUES ('2361', '39', null, null, '7', '1', '0');
INSERT INTO `cell_info` VALUES ('2362', '39', null, null, '7', '2', '0');
INSERT INTO `cell_info` VALUES ('2363', '39', null, null, '7', '3', '0');
INSERT INTO `cell_info` VALUES ('2364', '39', null, null, '7', '4', '0');
INSERT INTO `cell_info` VALUES ('2365', '39', null, null, '7', '5', '0');
INSERT INTO `cell_info` VALUES ('2366', '39', null, null, '7', '6', '0');
INSERT INTO `cell_info` VALUES ('2367', '39', null, null, '7', '7', '0');
INSERT INTO `cell_info` VALUES ('2368', '39', null, null, '7', '8', '0');
INSERT INTO `cell_info` VALUES ('2369', '39', null, null, '7', '9', '0');
INSERT INTO `cell_info` VALUES ('2370', '39', null, null, '7', '10', '0');
INSERT INTO `cell_info` VALUES ('2371', '39', null, null, '7', '11', '0');
INSERT INTO `cell_info` VALUES ('2372', '39', null, null, '7', '12', '0');
INSERT INTO `cell_info` VALUES ('2373', '39', null, null, '7', '13', '0');
INSERT INTO `cell_info` VALUES ('2374', '39', null, null, '7', '14', '0');
INSERT INTO `cell_info` VALUES ('2375', '39', null, null, '7', '15', '0');
INSERT INTO `cell_info` VALUES ('2376', '39', null, null, '7', '16', '0');
INSERT INTO `cell_info` VALUES ('2377', '39', null, null, '7', '17', '0');
INSERT INTO `cell_info` VALUES ('2378', '39', null, null, '7', '18', '0');
INSERT INTO `cell_info` VALUES ('2379', '39', null, null, '7', '19', '0');
INSERT INTO `cell_info` VALUES ('2380', '39', null, null, '7', '20', '0');
INSERT INTO `cell_info` VALUES ('2381', '39', null, null, '7', '21', '0');
INSERT INTO `cell_info` VALUES ('2382', '39', null, null, '7', '22', '0');
INSERT INTO `cell_info` VALUES ('2383', '39', null, null, '7', '23', '0');
INSERT INTO `cell_info` VALUES ('2384', '39', null, null, '7', '24', '0');
INSERT INTO `cell_info` VALUES ('2385', '39', null, null, '7', '25', '0');
INSERT INTO `cell_info` VALUES ('2386', '39', null, null, '7', '26', '0');
INSERT INTO `cell_info` VALUES ('2387', '39', null, null, '7', '27', '0');
INSERT INTO `cell_info` VALUES ('2388', '39', null, null, '7', '28', '0');
INSERT INTO `cell_info` VALUES ('2389', '39', null, null, '7', '29', '0');
INSERT INTO `cell_info` VALUES ('2390', '39', null, null, '7', '30', '0');
INSERT INTO `cell_info` VALUES ('2391', '39', null, null, '7', '31', '0');
INSERT INTO `cell_info` VALUES ('2392', '39', null, null, '8', '1', '0');
INSERT INTO `cell_info` VALUES ('2393', '39', null, null, '8', '2', '0');
INSERT INTO `cell_info` VALUES ('2394', '39', null, null, '8', '3', '0');
INSERT INTO `cell_info` VALUES ('2395', '39', null, null, '8', '4', '0');
INSERT INTO `cell_info` VALUES ('2396', '39', null, null, '8', '5', '0');
INSERT INTO `cell_info` VALUES ('2397', '39', null, null, '8', '6', '0');
INSERT INTO `cell_info` VALUES ('2398', '39', null, null, '8', '7', '0');
INSERT INTO `cell_info` VALUES ('2399', '39', null, null, '8', '8', '0');
INSERT INTO `cell_info` VALUES ('2400', '39', null, null, '8', '9', '0');
INSERT INTO `cell_info` VALUES ('2401', '39', null, null, '8', '10', '0');
INSERT INTO `cell_info` VALUES ('2402', '39', null, null, '8', '11', '0');
INSERT INTO `cell_info` VALUES ('2403', '39', null, null, '8', '12', '0');
INSERT INTO `cell_info` VALUES ('2404', '39', null, null, '8', '13', '0');
INSERT INTO `cell_info` VALUES ('2405', '39', null, null, '8', '14', '0');
INSERT INTO `cell_info` VALUES ('2406', '39', null, null, '8', '15', '0');
INSERT INTO `cell_info` VALUES ('2407', '39', null, null, '8', '16', '0');
INSERT INTO `cell_info` VALUES ('2408', '39', null, null, '8', '17', '0');
INSERT INTO `cell_info` VALUES ('2409', '39', null, null, '8', '18', '0');
INSERT INTO `cell_info` VALUES ('2410', '39', null, null, '8', '19', '0');
INSERT INTO `cell_info` VALUES ('2411', '39', null, null, '8', '20', '0');
INSERT INTO `cell_info` VALUES ('2412', '39', null, null, '8', '21', '0');
INSERT INTO `cell_info` VALUES ('2413', '39', null, null, '8', '22', '0');
INSERT INTO `cell_info` VALUES ('2414', '39', null, null, '8', '23', '0');
INSERT INTO `cell_info` VALUES ('2415', '39', null, null, '8', '24', '0');
INSERT INTO `cell_info` VALUES ('2416', '39', null, null, '8', '25', '0');
INSERT INTO `cell_info` VALUES ('2417', '39', null, null, '8', '26', '0');
INSERT INTO `cell_info` VALUES ('2418', '39', null, null, '8', '27', '0');
INSERT INTO `cell_info` VALUES ('2419', '39', null, null, '8', '28', '0');
INSERT INTO `cell_info` VALUES ('2420', '39', null, null, '8', '29', '0');
INSERT INTO `cell_info` VALUES ('2421', '39', null, null, '8', '30', '0');
INSERT INTO `cell_info` VALUES ('2422', '39', null, null, '8', '31', '0');
INSERT INTO `cell_info` VALUES ('2423', '39', null, null, '9', '1', '0');
INSERT INTO `cell_info` VALUES ('2424', '39', null, null, '9', '2', '0');
INSERT INTO `cell_info` VALUES ('2425', '39', null, null, '9', '3', '0');
INSERT INTO `cell_info` VALUES ('2426', '39', null, null, '9', '4', '0');
INSERT INTO `cell_info` VALUES ('2427', '39', null, null, '9', '5', '0');
INSERT INTO `cell_info` VALUES ('2428', '39', null, null, '9', '6', '0');
INSERT INTO `cell_info` VALUES ('2429', '39', null, null, '9', '7', '0');
INSERT INTO `cell_info` VALUES ('2430', '39', null, null, '9', '8', '0');
INSERT INTO `cell_info` VALUES ('2431', '39', null, null, '9', '9', '0');
INSERT INTO `cell_info` VALUES ('2432', '39', null, null, '9', '10', '0');
INSERT INTO `cell_info` VALUES ('2433', '39', null, null, '9', '11', '0');
INSERT INTO `cell_info` VALUES ('2434', '39', null, null, '9', '12', '0');
INSERT INTO `cell_info` VALUES ('2435', '39', null, null, '9', '13', '0');
INSERT INTO `cell_info` VALUES ('2436', '39', null, null, '9', '14', '0');
INSERT INTO `cell_info` VALUES ('2437', '39', null, null, '9', '15', '0');
INSERT INTO `cell_info` VALUES ('2438', '39', null, null, '9', '16', '0');
INSERT INTO `cell_info` VALUES ('2439', '39', null, null, '9', '17', '0');
INSERT INTO `cell_info` VALUES ('2440', '39', null, null, '9', '18', '0');
INSERT INTO `cell_info` VALUES ('2441', '39', null, null, '9', '19', '0');
INSERT INTO `cell_info` VALUES ('2442', '39', null, null, '9', '20', '0');
INSERT INTO `cell_info` VALUES ('2443', '39', null, null, '9', '21', '0');
INSERT INTO `cell_info` VALUES ('2444', '39', null, null, '9', '22', '0');
INSERT INTO `cell_info` VALUES ('2445', '39', null, null, '9', '23', '0');
INSERT INTO `cell_info` VALUES ('2446', '39', null, null, '9', '24', '0');
INSERT INTO `cell_info` VALUES ('2447', '39', null, null, '9', '25', '0');
INSERT INTO `cell_info` VALUES ('2448', '39', null, null, '9', '26', '0');
INSERT INTO `cell_info` VALUES ('2449', '39', null, null, '9', '27', '0');
INSERT INTO `cell_info` VALUES ('2450', '39', null, null, '9', '28', '0');
INSERT INTO `cell_info` VALUES ('2451', '39', null, null, '9', '29', '0');
INSERT INTO `cell_info` VALUES ('2452', '39', null, null, '9', '30', '0');
INSERT INTO `cell_info` VALUES ('2453', '39', null, null, '9', '31', '0');
INSERT INTO `cell_info` VALUES ('2454', '39', null, null, '10', '1', '0');
INSERT INTO `cell_info` VALUES ('2455', '39', null, null, '10', '2', '0');
INSERT INTO `cell_info` VALUES ('2456', '39', null, null, '10', '3', '0');
INSERT INTO `cell_info` VALUES ('2457', '39', null, null, '10', '4', '0');
INSERT INTO `cell_info` VALUES ('2458', '39', null, null, '10', '5', '0');
INSERT INTO `cell_info` VALUES ('2459', '39', null, null, '10', '6', '0');
INSERT INTO `cell_info` VALUES ('2460', '39', null, null, '10', '7', '0');
INSERT INTO `cell_info` VALUES ('2461', '39', null, null, '10', '8', '0');
INSERT INTO `cell_info` VALUES ('2462', '39', null, null, '10', '9', '0');
INSERT INTO `cell_info` VALUES ('2463', '39', null, null, '10', '10', '0');
INSERT INTO `cell_info` VALUES ('2464', '39', null, null, '10', '11', '0');
INSERT INTO `cell_info` VALUES ('2465', '39', null, null, '10', '12', '0');
INSERT INTO `cell_info` VALUES ('2466', '39', null, null, '10', '13', '0');
INSERT INTO `cell_info` VALUES ('2467', '39', null, null, '10', '14', '0');
INSERT INTO `cell_info` VALUES ('2468', '39', null, null, '10', '15', '0');
INSERT INTO `cell_info` VALUES ('2469', '39', null, null, '10', '16', '0');
INSERT INTO `cell_info` VALUES ('2470', '39', null, null, '10', '17', '0');
INSERT INTO `cell_info` VALUES ('2471', '39', null, null, '10', '18', '0');
INSERT INTO `cell_info` VALUES ('2472', '39', null, null, '10', '19', '0');
INSERT INTO `cell_info` VALUES ('2473', '39', null, null, '10', '20', '0');
INSERT INTO `cell_info` VALUES ('2474', '39', null, null, '10', '21', '0');
INSERT INTO `cell_info` VALUES ('2475', '39', null, null, '10', '22', '0');
INSERT INTO `cell_info` VALUES ('2476', '39', null, null, '10', '23', '0');
INSERT INTO `cell_info` VALUES ('2477', '39', null, null, '10', '24', '0');
INSERT INTO `cell_info` VALUES ('2478', '39', null, null, '10', '25', '0');
INSERT INTO `cell_info` VALUES ('2479', '39', null, null, '10', '26', '0');
INSERT INTO `cell_info` VALUES ('2480', '39', null, null, '10', '27', '0');
INSERT INTO `cell_info` VALUES ('2481', '39', null, null, '10', '28', '0');
INSERT INTO `cell_info` VALUES ('2482', '39', null, null, '10', '29', '0');
INSERT INTO `cell_info` VALUES ('2483', '39', null, null, '10', '30', '0');
INSERT INTO `cell_info` VALUES ('2484', '39', null, null, '10', '31', '0');
INSERT INTO `cell_info` VALUES ('2485', '39', null, null, '11', '1', '0');
INSERT INTO `cell_info` VALUES ('2486', '39', null, null, '11', '2', '0');
INSERT INTO `cell_info` VALUES ('2487', '39', null, null, '11', '3', '0');
INSERT INTO `cell_info` VALUES ('2488', '39', null, null, '11', '4', '0');
INSERT INTO `cell_info` VALUES ('2489', '39', null, null, '11', '5', '0');
INSERT INTO `cell_info` VALUES ('2490', '39', null, null, '11', '6', '0');
INSERT INTO `cell_info` VALUES ('2491', '39', null, null, '11', '7', '0');
INSERT INTO `cell_info` VALUES ('2492', '39', null, null, '11', '8', '0');
INSERT INTO `cell_info` VALUES ('2493', '39', null, null, '11', '9', '0');
INSERT INTO `cell_info` VALUES ('2494', '39', null, null, '11', '10', '0');
INSERT INTO `cell_info` VALUES ('2495', '39', null, null, '11', '11', '0');
INSERT INTO `cell_info` VALUES ('2496', '39', null, null, '11', '12', '0');
INSERT INTO `cell_info` VALUES ('2497', '39', null, null, '11', '13', '0');
INSERT INTO `cell_info` VALUES ('2498', '39', null, null, '11', '14', '0');
INSERT INTO `cell_info` VALUES ('2499', '39', null, null, '11', '15', '0');
INSERT INTO `cell_info` VALUES ('2500', '39', null, null, '11', '16', '0');
INSERT INTO `cell_info` VALUES ('2501', '39', null, null, '11', '17', '0');
INSERT INTO `cell_info` VALUES ('2502', '39', null, null, '11', '18', '0');
INSERT INTO `cell_info` VALUES ('2503', '39', null, null, '11', '19', '0');
INSERT INTO `cell_info` VALUES ('2504', '39', null, null, '11', '20', '0');
INSERT INTO `cell_info` VALUES ('2505', '39', null, null, '11', '21', '0');
INSERT INTO `cell_info` VALUES ('2506', '39', null, null, '11', '22', '0');
INSERT INTO `cell_info` VALUES ('2507', '39', null, null, '11', '23', '0');
INSERT INTO `cell_info` VALUES ('2508', '39', null, null, '11', '24', '0');
INSERT INTO `cell_info` VALUES ('2509', '39', null, null, '11', '25', '0');
INSERT INTO `cell_info` VALUES ('2510', '39', null, null, '11', '26', '0');
INSERT INTO `cell_info` VALUES ('2511', '39', null, null, '11', '27', '0');
INSERT INTO `cell_info` VALUES ('2512', '39', null, null, '11', '28', '0');
INSERT INTO `cell_info` VALUES ('2513', '39', null, null, '11', '29', '0');
INSERT INTO `cell_info` VALUES ('2514', '39', null, null, '11', '30', '0');
INSERT INTO `cell_info` VALUES ('2515', '39', null, null, '11', '31', '0');
INSERT INTO `cell_info` VALUES ('2516', '39', null, null, '12', '1', '0');
INSERT INTO `cell_info` VALUES ('2517', '39', null, null, '12', '2', '0');
INSERT INTO `cell_info` VALUES ('2518', '39', null, null, '12', '3', '0');
INSERT INTO `cell_info` VALUES ('2519', '39', null, null, '12', '4', '0');
INSERT INTO `cell_info` VALUES ('2520', '39', null, null, '12', '5', '0');
INSERT INTO `cell_info` VALUES ('2521', '39', null, null, '12', '6', '0');
INSERT INTO `cell_info` VALUES ('2522', '39', null, null, '12', '7', '0');
INSERT INTO `cell_info` VALUES ('2523', '39', null, null, '12', '8', '0');
INSERT INTO `cell_info` VALUES ('2524', '39', null, null, '12', '9', '0');
INSERT INTO `cell_info` VALUES ('2525', '39', null, null, '12', '10', '0');
INSERT INTO `cell_info` VALUES ('2526', '39', null, null, '12', '11', '0');
INSERT INTO `cell_info` VALUES ('2527', '39', null, null, '12', '12', '0');
INSERT INTO `cell_info` VALUES ('2528', '39', null, null, '12', '13', '0');
INSERT INTO `cell_info` VALUES ('2529', '39', null, null, '12', '14', '0');
INSERT INTO `cell_info` VALUES ('2530', '39', null, null, '12', '15', '0');
INSERT INTO `cell_info` VALUES ('2531', '39', null, null, '12', '16', '0');
INSERT INTO `cell_info` VALUES ('2532', '39', null, null, '12', '17', '0');
INSERT INTO `cell_info` VALUES ('2533', '39', null, null, '12', '18', '0');
INSERT INTO `cell_info` VALUES ('2534', '39', null, null, '12', '19', '0');
INSERT INTO `cell_info` VALUES ('2535', '39', null, null, '12', '20', '0');
INSERT INTO `cell_info` VALUES ('2536', '39', null, null, '12', '21', '0');
INSERT INTO `cell_info` VALUES ('2537', '39', null, null, '12', '22', '0');
INSERT INTO `cell_info` VALUES ('2538', '39', null, null, '12', '23', '0');
INSERT INTO `cell_info` VALUES ('2539', '39', null, null, '12', '24', '0');
INSERT INTO `cell_info` VALUES ('2540', '39', null, null, '12', '25', '0');
INSERT INTO `cell_info` VALUES ('2541', '39', null, null, '12', '26', '0');
INSERT INTO `cell_info` VALUES ('2542', '39', null, null, '12', '27', '0');
INSERT INTO `cell_info` VALUES ('2543', '39', null, null, '12', '28', '0');
INSERT INTO `cell_info` VALUES ('2544', '39', null, null, '12', '29', '0');
INSERT INTO `cell_info` VALUES ('2545', '39', null, null, '12', '30', '0');
INSERT INTO `cell_info` VALUES ('2546', '39', null, null, '12', '31', '0');
INSERT INTO `cell_info` VALUES ('2547', '39', null, null, '13', '1', '0');
INSERT INTO `cell_info` VALUES ('2548', '39', null, null, '13', '2', '0');
INSERT INTO `cell_info` VALUES ('2549', '39', null, null, '13', '3', '0');
INSERT INTO `cell_info` VALUES ('2550', '39', null, null, '13', '4', '0');
INSERT INTO `cell_info` VALUES ('2551', '39', null, null, '13', '5', '0');
INSERT INTO `cell_info` VALUES ('2552', '39', null, null, '13', '6', '0');
INSERT INTO `cell_info` VALUES ('2553', '39', null, null, '13', '7', '0');
INSERT INTO `cell_info` VALUES ('2554', '39', null, null, '13', '8', '0');
INSERT INTO `cell_info` VALUES ('2555', '39', null, null, '13', '9', '0');
INSERT INTO `cell_info` VALUES ('2556', '39', null, null, '13', '10', '0');
INSERT INTO `cell_info` VALUES ('2557', '39', null, null, '13', '11', '0');
INSERT INTO `cell_info` VALUES ('2558', '39', null, null, '13', '12', '0');
INSERT INTO `cell_info` VALUES ('2559', '39', null, null, '13', '13', '0');
INSERT INTO `cell_info` VALUES ('2560', '39', null, null, '13', '14', '0');
INSERT INTO `cell_info` VALUES ('2561', '39', null, null, '13', '15', '0');
INSERT INTO `cell_info` VALUES ('2562', '39', null, null, '13', '16', '0');
INSERT INTO `cell_info` VALUES ('2563', '39', null, null, '13', '17', '0');
INSERT INTO `cell_info` VALUES ('2564', '39', null, null, '13', '18', '0');
INSERT INTO `cell_info` VALUES ('2565', '39', null, null, '13', '19', '0');
INSERT INTO `cell_info` VALUES ('2566', '39', null, null, '13', '20', '0');
INSERT INTO `cell_info` VALUES ('2567', '39', null, null, '13', '21', '0');
INSERT INTO `cell_info` VALUES ('2568', '39', null, null, '13', '22', '0');
INSERT INTO `cell_info` VALUES ('2569', '39', null, null, '13', '23', '0');
INSERT INTO `cell_info` VALUES ('2570', '39', null, null, '13', '24', '0');
INSERT INTO `cell_info` VALUES ('2571', '39', null, null, '13', '25', '0');
INSERT INTO `cell_info` VALUES ('2572', '39', null, null, '13', '26', '0');
INSERT INTO `cell_info` VALUES ('2573', '39', null, null, '13', '27', '0');
INSERT INTO `cell_info` VALUES ('2574', '39', null, null, '13', '28', '0');
INSERT INTO `cell_info` VALUES ('2575', '39', null, null, '13', '29', '0');
INSERT INTO `cell_info` VALUES ('2576', '39', null, null, '13', '30', '0');
INSERT INTO `cell_info` VALUES ('2577', '39', null, null, '13', '31', '0');
INSERT INTO `cell_info` VALUES ('2578', '39', null, null, '14', '1', '0');
INSERT INTO `cell_info` VALUES ('2579', '39', null, null, '14', '2', '0');
INSERT INTO `cell_info` VALUES ('2580', '39', null, null, '14', '3', '0');
INSERT INTO `cell_info` VALUES ('2581', '39', null, null, '14', '4', '0');
INSERT INTO `cell_info` VALUES ('2582', '39', null, null, '14', '5', '0');
INSERT INTO `cell_info` VALUES ('2583', '39', null, null, '14', '6', '0');
INSERT INTO `cell_info` VALUES ('2584', '39', null, null, '14', '7', '0');
INSERT INTO `cell_info` VALUES ('2585', '39', null, null, '14', '8', '0');
INSERT INTO `cell_info` VALUES ('2586', '39', null, null, '14', '9', '0');
INSERT INTO `cell_info` VALUES ('2587', '39', null, null, '14', '10', '0');
INSERT INTO `cell_info` VALUES ('2588', '39', null, null, '14', '11', '0');
INSERT INTO `cell_info` VALUES ('2589', '39', null, null, '14', '12', '0');
INSERT INTO `cell_info` VALUES ('2590', '39', null, null, '14', '13', '0');
INSERT INTO `cell_info` VALUES ('2591', '39', null, null, '14', '14', '0');
INSERT INTO `cell_info` VALUES ('2592', '39', null, null, '14', '15', '0');
INSERT INTO `cell_info` VALUES ('2593', '39', null, null, '14', '16', '0');
INSERT INTO `cell_info` VALUES ('2594', '39', null, null, '14', '17', '0');
INSERT INTO `cell_info` VALUES ('2595', '39', null, null, '14', '18', '0');
INSERT INTO `cell_info` VALUES ('2596', '39', null, null, '14', '19', '0');
INSERT INTO `cell_info` VALUES ('2597', '39', null, null, '14', '20', '0');
INSERT INTO `cell_info` VALUES ('2598', '39', null, null, '14', '21', '0');
INSERT INTO `cell_info` VALUES ('2599', '39', null, null, '14', '22', '0');
INSERT INTO `cell_info` VALUES ('2600', '39', null, null, '14', '23', '0');
INSERT INTO `cell_info` VALUES ('2601', '39', null, null, '14', '24', '0');
INSERT INTO `cell_info` VALUES ('2602', '39', null, null, '14', '25', '0');
INSERT INTO `cell_info` VALUES ('2603', '39', null, null, '14', '26', '0');
INSERT INTO `cell_info` VALUES ('2604', '39', null, null, '14', '27', '0');
INSERT INTO `cell_info` VALUES ('2605', '39', null, null, '14', '28', '0');
INSERT INTO `cell_info` VALUES ('2606', '39', null, null, '14', '29', '0');
INSERT INTO `cell_info` VALUES ('2607', '39', null, null, '14', '30', '0');
INSERT INTO `cell_info` VALUES ('2608', '39', null, null, '14', '31', '0');
INSERT INTO `cell_info` VALUES ('2609', '39', null, null, '15', '1', '0');
INSERT INTO `cell_info` VALUES ('2610', '39', null, null, '15', '2', '0');
INSERT INTO `cell_info` VALUES ('2611', '39', null, null, '15', '3', '0');
INSERT INTO `cell_info` VALUES ('2612', '39', null, null, '15', '4', '0');
INSERT INTO `cell_info` VALUES ('2613', '39', null, null, '15', '5', '0');
INSERT INTO `cell_info` VALUES ('2614', '39', null, null, '15', '6', '0');
INSERT INTO `cell_info` VALUES ('2615', '39', null, null, '15', '7', '0');
INSERT INTO `cell_info` VALUES ('2616', '39', null, null, '15', '8', '0');
INSERT INTO `cell_info` VALUES ('2617', '39', null, null, '15', '9', '0');
INSERT INTO `cell_info` VALUES ('2618', '39', null, null, '15', '10', '0');
INSERT INTO `cell_info` VALUES ('2619', '39', null, null, '15', '11', '0');
INSERT INTO `cell_info` VALUES ('2620', '39', null, null, '15', '12', '0');
INSERT INTO `cell_info` VALUES ('2621', '39', null, null, '15', '13', '0');
INSERT INTO `cell_info` VALUES ('2622', '39', null, null, '15', '14', '0');
INSERT INTO `cell_info` VALUES ('2623', '39', null, null, '15', '15', '0');
INSERT INTO `cell_info` VALUES ('2624', '39', null, null, '15', '16', '0');
INSERT INTO `cell_info` VALUES ('2625', '39', null, null, '15', '17', '0');
INSERT INTO `cell_info` VALUES ('2626', '39', null, null, '15', '18', '0');
INSERT INTO `cell_info` VALUES ('2627', '39', null, null, '15', '19', '0');
INSERT INTO `cell_info` VALUES ('2628', '39', null, null, '15', '20', '0');
INSERT INTO `cell_info` VALUES ('2629', '39', null, null, '15', '21', '0');
INSERT INTO `cell_info` VALUES ('2630', '39', null, null, '15', '22', '0');
INSERT INTO `cell_info` VALUES ('2631', '39', null, null, '15', '23', '0');
INSERT INTO `cell_info` VALUES ('2632', '39', null, null, '15', '24', '0');
INSERT INTO `cell_info` VALUES ('2633', '39', null, null, '15', '25', '0');
INSERT INTO `cell_info` VALUES ('2634', '39', null, null, '15', '26', '0');
INSERT INTO `cell_info` VALUES ('2635', '39', null, null, '15', '27', '0');
INSERT INTO `cell_info` VALUES ('2636', '39', null, null, '15', '28', '0');
INSERT INTO `cell_info` VALUES ('2637', '39', null, null, '15', '29', '0');
INSERT INTO `cell_info` VALUES ('2638', '39', null, null, '15', '30', '0');
INSERT INTO `cell_info` VALUES ('2639', '39', null, null, '15', '31', '0');
INSERT INTO `cell_info` VALUES ('2640', '39', null, null, '16', '1', '0');
INSERT INTO `cell_info` VALUES ('2641', '39', null, null, '16', '2', '0');
INSERT INTO `cell_info` VALUES ('2642', '39', null, null, '16', '3', '0');
INSERT INTO `cell_info` VALUES ('2643', '39', null, null, '16', '4', '0');
INSERT INTO `cell_info` VALUES ('2644', '39', null, null, '16', '5', '0');
INSERT INTO `cell_info` VALUES ('2645', '39', null, null, '16', '6', '0');
INSERT INTO `cell_info` VALUES ('2646', '39', null, null, '16', '7', '0');
INSERT INTO `cell_info` VALUES ('2647', '39', null, null, '16', '8', '0');
INSERT INTO `cell_info` VALUES ('2648', '39', null, null, '16', '9', '0');
INSERT INTO `cell_info` VALUES ('2649', '39', null, null, '16', '10', '0');
INSERT INTO `cell_info` VALUES ('2650', '39', null, null, '16', '11', '0');
INSERT INTO `cell_info` VALUES ('2651', '39', null, null, '16', '12', '0');
INSERT INTO `cell_info` VALUES ('2652', '39', null, null, '16', '13', '0');
INSERT INTO `cell_info` VALUES ('2653', '39', null, null, '16', '14', '0');
INSERT INTO `cell_info` VALUES ('2654', '39', null, null, '16', '15', '0');
INSERT INTO `cell_info` VALUES ('2655', '39', null, null, '16', '16', '0');
INSERT INTO `cell_info` VALUES ('2656', '39', null, null, '16', '17', '0');
INSERT INTO `cell_info` VALUES ('2657', '39', null, null, '16', '18', '0');
INSERT INTO `cell_info` VALUES ('2658', '39', null, null, '16', '19', '0');
INSERT INTO `cell_info` VALUES ('2659', '39', null, null, '16', '20', '0');
INSERT INTO `cell_info` VALUES ('2660', '39', null, null, '16', '21', '0');
INSERT INTO `cell_info` VALUES ('2661', '39', null, null, '16', '22', '0');
INSERT INTO `cell_info` VALUES ('2662', '39', null, null, '16', '23', '0');
INSERT INTO `cell_info` VALUES ('2663', '39', null, null, '16', '24', '0');
INSERT INTO `cell_info` VALUES ('2664', '39', null, null, '16', '25', '0');
INSERT INTO `cell_info` VALUES ('2665', '39', null, null, '16', '26', '0');
INSERT INTO `cell_info` VALUES ('2666', '39', null, null, '16', '27', '0');
INSERT INTO `cell_info` VALUES ('2667', '39', null, null, '16', '28', '0');
INSERT INTO `cell_info` VALUES ('2668', '39', null, null, '16', '29', '0');
INSERT INTO `cell_info` VALUES ('2669', '39', null, null, '16', '30', '0');
INSERT INTO `cell_info` VALUES ('2670', '39', null, null, '16', '31', '0');
INSERT INTO `cell_info` VALUES ('2671', '39', null, null, '17', '1', '0');
INSERT INTO `cell_info` VALUES ('2672', '39', null, null, '17', '2', '0');
INSERT INTO `cell_info` VALUES ('2673', '39', null, null, '17', '3', '0');
INSERT INTO `cell_info` VALUES ('2674', '39', null, null, '17', '4', '0');
INSERT INTO `cell_info` VALUES ('2675', '39', null, null, '17', '5', '0');
INSERT INTO `cell_info` VALUES ('2676', '39', null, null, '17', '6', '0');
INSERT INTO `cell_info` VALUES ('2677', '39', null, null, '17', '7', '0');
INSERT INTO `cell_info` VALUES ('2678', '39', null, null, '17', '8', '0');
INSERT INTO `cell_info` VALUES ('2679', '39', null, null, '17', '9', '0');
INSERT INTO `cell_info` VALUES ('2680', '39', null, null, '17', '10', '0');
INSERT INTO `cell_info` VALUES ('2681', '39', null, null, '17', '11', '0');
INSERT INTO `cell_info` VALUES ('2682', '39', null, null, '17', '12', '0');
INSERT INTO `cell_info` VALUES ('2683', '39', null, null, '17', '13', '0');
INSERT INTO `cell_info` VALUES ('2684', '39', null, null, '17', '14', '0');
INSERT INTO `cell_info` VALUES ('2685', '39', null, null, '17', '15', '0');
INSERT INTO `cell_info` VALUES ('2686', '39', null, null, '17', '16', '0');
INSERT INTO `cell_info` VALUES ('2687', '39', null, null, '17', '17', '0');
INSERT INTO `cell_info` VALUES ('2688', '39', null, null, '17', '18', '0');
INSERT INTO `cell_info` VALUES ('2689', '39', null, null, '17', '19', '0');
INSERT INTO `cell_info` VALUES ('2690', '39', null, null, '17', '20', '0');
INSERT INTO `cell_info` VALUES ('2691', '39', null, null, '17', '21', '0');
INSERT INTO `cell_info` VALUES ('2692', '39', null, null, '17', '22', '0');
INSERT INTO `cell_info` VALUES ('2693', '39', null, null, '17', '23', '0');
INSERT INTO `cell_info` VALUES ('2694', '39', null, null, '17', '24', '0');
INSERT INTO `cell_info` VALUES ('2695', '39', null, null, '17', '25', '0');
INSERT INTO `cell_info` VALUES ('2696', '39', null, null, '17', '26', '0');
INSERT INTO `cell_info` VALUES ('2697', '39', null, null, '17', '27', '0');
INSERT INTO `cell_info` VALUES ('2698', '39', null, null, '17', '28', '0');
INSERT INTO `cell_info` VALUES ('2699', '39', null, null, '17', '29', '0');
INSERT INTO `cell_info` VALUES ('2700', '39', null, null, '17', '30', '0');
INSERT INTO `cell_info` VALUES ('2701', '39', null, null, '17', '31', '0');
INSERT INTO `cell_info` VALUES ('2702', '39', null, null, '18', '1', '0');
INSERT INTO `cell_info` VALUES ('2703', '39', null, null, '18', '2', '0');
INSERT INTO `cell_info` VALUES ('2704', '39', null, null, '18', '3', '0');
INSERT INTO `cell_info` VALUES ('2705', '39', null, null, '18', '4', '0');
INSERT INTO `cell_info` VALUES ('2706', '39', null, null, '18', '5', '0');
INSERT INTO `cell_info` VALUES ('2707', '39', null, null, '18', '6', '0');
INSERT INTO `cell_info` VALUES ('2708', '39', null, null, '18', '7', '0');
INSERT INTO `cell_info` VALUES ('2709', '39', null, null, '18', '8', '0');
INSERT INTO `cell_info` VALUES ('2710', '39', null, null, '18', '9', '0');
INSERT INTO `cell_info` VALUES ('2711', '39', null, null, '18', '10', '0');
INSERT INTO `cell_info` VALUES ('2712', '39', null, null, '18', '11', '0');
INSERT INTO `cell_info` VALUES ('2713', '39', null, null, '18', '12', '0');
INSERT INTO `cell_info` VALUES ('2714', '39', null, null, '18', '13', '0');
INSERT INTO `cell_info` VALUES ('2715', '39', null, null, '18', '14', '0');
INSERT INTO `cell_info` VALUES ('2716', '39', null, null, '18', '15', '0');
INSERT INTO `cell_info` VALUES ('2717', '39', null, null, '18', '16', '0');
INSERT INTO `cell_info` VALUES ('2718', '39', null, null, '18', '17', '0');
INSERT INTO `cell_info` VALUES ('2719', '39', null, null, '18', '18', '0');
INSERT INTO `cell_info` VALUES ('2720', '39', null, null, '18', '19', '0');
INSERT INTO `cell_info` VALUES ('2721', '39', null, null, '18', '20', '0');
INSERT INTO `cell_info` VALUES ('2722', '39', null, null, '18', '21', '0');
INSERT INTO `cell_info` VALUES ('2723', '39', null, null, '18', '22', '0');
INSERT INTO `cell_info` VALUES ('2724', '39', null, null, '18', '23', '0');
INSERT INTO `cell_info` VALUES ('2725', '39', null, null, '18', '24', '0');
INSERT INTO `cell_info` VALUES ('2726', '39', null, null, '18', '25', '0');
INSERT INTO `cell_info` VALUES ('2727', '39', null, null, '18', '26', '0');
INSERT INTO `cell_info` VALUES ('2728', '39', null, null, '18', '27', '0');
INSERT INTO `cell_info` VALUES ('2729', '39', null, null, '18', '28', '0');
INSERT INTO `cell_info` VALUES ('2730', '39', null, null, '18', '29', '0');
INSERT INTO `cell_info` VALUES ('2731', '39', null, null, '18', '30', '0');
INSERT INTO `cell_info` VALUES ('2732', '39', null, null, '18', '31', '0');
INSERT INTO `cell_info` VALUES ('2733', '40', null, null, '1', '1', '1');
INSERT INTO `cell_info` VALUES ('2734', '40', null, null, '1', '2', '1');
INSERT INTO `cell_info` VALUES ('2735', '40', null, null, '1', '3', '1');
INSERT INTO `cell_info` VALUES ('2736', '40', null, null, '1', '4', '1');
INSERT INTO `cell_info` VALUES ('2737', '40', null, null, '1', '5', '1');
INSERT INTO `cell_info` VALUES ('2738', '40', null, null, '1', '6', '1');
INSERT INTO `cell_info` VALUES ('2739', '40', null, null, '1', '7', '1');
INSERT INTO `cell_info` VALUES ('2740', '40', null, null, '1', '8', '1');
INSERT INTO `cell_info` VALUES ('2741', '40', null, null, '1', '9', '1');
INSERT INTO `cell_info` VALUES ('2742', '40', null, null, '1', '10', '1');
INSERT INTO `cell_info` VALUES ('2743', '40', null, null, '1', '11', '1');
INSERT INTO `cell_info` VALUES ('2744', '40', null, null, '1', '12', '1');
INSERT INTO `cell_info` VALUES ('2745', '40', null, null, '1', '13', '1');
INSERT INTO `cell_info` VALUES ('2746', '40', null, null, '1', '14', '1');
INSERT INTO `cell_info` VALUES ('2747', '40', null, null, '1', '15', '1');
INSERT INTO `cell_info` VALUES ('2748', '40', null, null, '1', '16', '1');
INSERT INTO `cell_info` VALUES ('2749', '40', null, null, '1', '17', '1');
INSERT INTO `cell_info` VALUES ('2750', '40', null, null, '1', '18', '1');
INSERT INTO `cell_info` VALUES ('2751', '40', null, null, '1', '19', '1');
INSERT INTO `cell_info` VALUES ('2752', '40', null, null, '1', '20', '1');
INSERT INTO `cell_info` VALUES ('2753', '40', null, null, '1', '21', '1');
INSERT INTO `cell_info` VALUES ('2754', '40', null, null, '1', '22', '1');
INSERT INTO `cell_info` VALUES ('2755', '40', null, null, '1', '23', '1');
INSERT INTO `cell_info` VALUES ('2756', '40', null, null, '1', '24', '1');
INSERT INTO `cell_info` VALUES ('2757', '40', null, null, '1', '25', '1');
INSERT INTO `cell_info` VALUES ('2758', '40', null, null, '1', '26', '1');
INSERT INTO `cell_info` VALUES ('2759', '40', null, null, '1', '27', '1');
INSERT INTO `cell_info` VALUES ('2760', '40', null, null, '1', '28', '1');
INSERT INTO `cell_info` VALUES ('2761', '40', null, null, '1', '29', '1');
INSERT INTO `cell_info` VALUES ('2762', '40', null, null, '1', '30', '1');
INSERT INTO `cell_info` VALUES ('2763', '40', null, null, '1', '31', '1');
INSERT INTO `cell_info` VALUES ('2764', '40', null, null, '2', '1', '1');
INSERT INTO `cell_info` VALUES ('2765', '40', null, null, '2', '2', '1');
INSERT INTO `cell_info` VALUES ('2766', '40', null, null, '2', '3', '1');
INSERT INTO `cell_info` VALUES ('2767', '40', null, null, '2', '4', '1');
INSERT INTO `cell_info` VALUES ('2768', '40', null, null, '2', '5', '1');
INSERT INTO `cell_info` VALUES ('2769', '40', null, null, '2', '6', '1');
INSERT INTO `cell_info` VALUES ('2770', '40', null, null, '2', '7', '1');
INSERT INTO `cell_info` VALUES ('2771', '40', null, null, '2', '8', '1');
INSERT INTO `cell_info` VALUES ('2772', '40', null, null, '2', '9', '1');
INSERT INTO `cell_info` VALUES ('2773', '40', null, null, '2', '10', '1');
INSERT INTO `cell_info` VALUES ('2774', '40', null, null, '2', '11', '1');
INSERT INTO `cell_info` VALUES ('2775', '40', null, null, '2', '12', '1');
INSERT INTO `cell_info` VALUES ('2776', '40', null, null, '2', '13', '1');
INSERT INTO `cell_info` VALUES ('2777', '40', null, null, '2', '14', '1');
INSERT INTO `cell_info` VALUES ('2778', '40', null, null, '2', '15', '1');
INSERT INTO `cell_info` VALUES ('2779', '40', null, null, '2', '16', '1');
INSERT INTO `cell_info` VALUES ('2780', '40', null, null, '2', '17', '1');
INSERT INTO `cell_info` VALUES ('2781', '40', null, null, '2', '18', '1');
INSERT INTO `cell_info` VALUES ('2782', '40', null, null, '2', '19', '1');
INSERT INTO `cell_info` VALUES ('2783', '40', null, null, '2', '20', '1');
INSERT INTO `cell_info` VALUES ('2784', '40', null, null, '2', '21', '1');
INSERT INTO `cell_info` VALUES ('2785', '40', null, null, '2', '22', '1');
INSERT INTO `cell_info` VALUES ('2786', '40', null, null, '2', '23', '1');
INSERT INTO `cell_info` VALUES ('2787', '40', null, null, '2', '24', '1');
INSERT INTO `cell_info` VALUES ('2788', '40', null, null, '2', '25', '1');
INSERT INTO `cell_info` VALUES ('2789', '40', null, null, '2', '26', '1');
INSERT INTO `cell_info` VALUES ('2790', '40', null, null, '2', '27', '1');
INSERT INTO `cell_info` VALUES ('2791', '40', null, null, '2', '28', '1');
INSERT INTO `cell_info` VALUES ('2792', '40', null, null, '2', '29', '1');
INSERT INTO `cell_info` VALUES ('2793', '40', null, null, '2', '30', '1');
INSERT INTO `cell_info` VALUES ('2794', '40', null, null, '2', '31', '1');
INSERT INTO `cell_info` VALUES ('2795', '40', null, null, '3', '1', '1');
INSERT INTO `cell_info` VALUES ('2796', '40', null, null, '3', '2', '1');
INSERT INTO `cell_info` VALUES ('2797', '40', null, null, '3', '3', '1');
INSERT INTO `cell_info` VALUES ('2798', '40', null, null, '3', '4', '1');
INSERT INTO `cell_info` VALUES ('2799', '40', null, null, '3', '5', '1');
INSERT INTO `cell_info` VALUES ('2800', '40', null, null, '3', '6', '1');
INSERT INTO `cell_info` VALUES ('2801', '40', null, null, '3', '7', '1');
INSERT INTO `cell_info` VALUES ('2802', '40', null, null, '3', '8', '1');
INSERT INTO `cell_info` VALUES ('2803', '40', null, null, '3', '9', '1');
INSERT INTO `cell_info` VALUES ('2804', '40', null, null, '3', '10', '1');
INSERT INTO `cell_info` VALUES ('2805', '40', null, null, '3', '11', '1');
INSERT INTO `cell_info` VALUES ('2806', '40', null, null, '3', '12', '1');
INSERT INTO `cell_info` VALUES ('2807', '40', null, null, '3', '13', '1');
INSERT INTO `cell_info` VALUES ('2808', '40', null, null, '3', '14', '1');
INSERT INTO `cell_info` VALUES ('2809', '40', null, null, '3', '15', '1');
INSERT INTO `cell_info` VALUES ('2810', '40', null, null, '3', '16', '1');
INSERT INTO `cell_info` VALUES ('2811', '40', null, null, '3', '17', '1');
INSERT INTO `cell_info` VALUES ('2812', '40', null, null, '3', '18', '1');
INSERT INTO `cell_info` VALUES ('2813', '40', null, null, '3', '19', '1');
INSERT INTO `cell_info` VALUES ('2814', '40', null, null, '3', '20', '1');
INSERT INTO `cell_info` VALUES ('2815', '40', null, null, '3', '21', '1');
INSERT INTO `cell_info` VALUES ('2816', '40', null, null, '3', '22', '1');
INSERT INTO `cell_info` VALUES ('2817', '40', null, null, '3', '23', '1');
INSERT INTO `cell_info` VALUES ('2818', '40', null, null, '3', '24', '1');
INSERT INTO `cell_info` VALUES ('2819', '40', null, null, '3', '25', '1');
INSERT INTO `cell_info` VALUES ('2820', '40', null, null, '3', '26', '1');
INSERT INTO `cell_info` VALUES ('2821', '40', null, null, '3', '27', '1');
INSERT INTO `cell_info` VALUES ('2822', '40', null, null, '3', '28', '1');
INSERT INTO `cell_info` VALUES ('2823', '40', null, null, '3', '29', '1');
INSERT INTO `cell_info` VALUES ('2824', '40', null, null, '3', '30', '1');
INSERT INTO `cell_info` VALUES ('2825', '40', null, null, '3', '31', '1');
INSERT INTO `cell_info` VALUES ('2826', '40', null, null, '4', '1', '1');
INSERT INTO `cell_info` VALUES ('2827', '40', null, null, '4', '2', '1');
INSERT INTO `cell_info` VALUES ('2828', '40', null, null, '4', '3', '1');
INSERT INTO `cell_info` VALUES ('2829', '40', null, null, '4', '4', '1');
INSERT INTO `cell_info` VALUES ('2830', '40', null, null, '4', '5', '1');
INSERT INTO `cell_info` VALUES ('2831', '40', null, null, '4', '6', '1');
INSERT INTO `cell_info` VALUES ('2832', '40', null, null, '4', '7', '1');
INSERT INTO `cell_info` VALUES ('2833', '40', null, null, '4', '8', '1');
INSERT INTO `cell_info` VALUES ('2834', '40', null, null, '4', '9', '1');
INSERT INTO `cell_info` VALUES ('2835', '40', null, null, '4', '10', '1');
INSERT INTO `cell_info` VALUES ('2836', '40', null, null, '4', '11', '1');
INSERT INTO `cell_info` VALUES ('2837', '40', null, null, '4', '12', '1');
INSERT INTO `cell_info` VALUES ('2838', '40', null, null, '4', '13', '1');
INSERT INTO `cell_info` VALUES ('2839', '40', null, null, '4', '14', '1');
INSERT INTO `cell_info` VALUES ('2840', '40', null, null, '4', '15', '1');
INSERT INTO `cell_info` VALUES ('2841', '40', null, null, '4', '16', '1');
INSERT INTO `cell_info` VALUES ('2842', '40', null, null, '4', '17', '1');
INSERT INTO `cell_info` VALUES ('2843', '40', null, null, '4', '18', '1');
INSERT INTO `cell_info` VALUES ('2844', '40', null, null, '4', '19', '1');
INSERT INTO `cell_info` VALUES ('2845', '40', null, null, '4', '20', '1');
INSERT INTO `cell_info` VALUES ('2846', '40', null, null, '4', '21', '1');
INSERT INTO `cell_info` VALUES ('2847', '40', null, null, '4', '22', '1');
INSERT INTO `cell_info` VALUES ('2848', '40', null, null, '4', '23', '1');
INSERT INTO `cell_info` VALUES ('2849', '40', null, null, '4', '24', '1');
INSERT INTO `cell_info` VALUES ('2850', '40', null, null, '4', '25', '1');
INSERT INTO `cell_info` VALUES ('2851', '40', null, null, '4', '26', '1');
INSERT INTO `cell_info` VALUES ('2852', '40', null, null, '4', '27', '1');
INSERT INTO `cell_info` VALUES ('2853', '40', null, null, '4', '28', '1');
INSERT INTO `cell_info` VALUES ('2854', '40', null, null, '4', '29', '1');
INSERT INTO `cell_info` VALUES ('2855', '40', null, null, '4', '30', '1');
INSERT INTO `cell_info` VALUES ('2856', '40', null, null, '4', '31', '1');
INSERT INTO `cell_info` VALUES ('2857', '40', null, null, '5', '1', '1');
INSERT INTO `cell_info` VALUES ('2858', '40', null, null, '5', '2', '1');
INSERT INTO `cell_info` VALUES ('2859', '40', null, null, '5', '3', '1');
INSERT INTO `cell_info` VALUES ('2860', '40', null, null, '5', '4', '1');
INSERT INTO `cell_info` VALUES ('2861', '40', null, null, '5', '5', '1');
INSERT INTO `cell_info` VALUES ('2862', '40', null, null, '5', '6', '1');
INSERT INTO `cell_info` VALUES ('2863', '40', null, null, '5', '7', '1');
INSERT INTO `cell_info` VALUES ('2864', '40', null, null, '5', '8', '1');
INSERT INTO `cell_info` VALUES ('2865', '40', null, null, '5', '9', '1');
INSERT INTO `cell_info` VALUES ('2866', '40', null, null, '5', '10', '1');
INSERT INTO `cell_info` VALUES ('2867', '40', null, null, '5', '11', '1');
INSERT INTO `cell_info` VALUES ('2868', '40', null, null, '5', '12', '1');
INSERT INTO `cell_info` VALUES ('2869', '40', null, null, '5', '13', '1');
INSERT INTO `cell_info` VALUES ('2870', '40', null, null, '5', '14', '1');
INSERT INTO `cell_info` VALUES ('2871', '40', null, null, '5', '15', '1');
INSERT INTO `cell_info` VALUES ('2872', '40', null, null, '5', '16', '1');
INSERT INTO `cell_info` VALUES ('2873', '40', null, null, '5', '17', '1');
INSERT INTO `cell_info` VALUES ('2874', '40', null, null, '5', '18', '1');
INSERT INTO `cell_info` VALUES ('2875', '40', null, null, '5', '19', '1');
INSERT INTO `cell_info` VALUES ('2876', '40', null, null, '5', '20', '1');
INSERT INTO `cell_info` VALUES ('2877', '40', null, null, '5', '21', '1');
INSERT INTO `cell_info` VALUES ('2878', '40', null, null, '5', '22', '1');
INSERT INTO `cell_info` VALUES ('2879', '40', null, null, '5', '23', '1');
INSERT INTO `cell_info` VALUES ('2880', '40', null, null, '5', '24', '0');
INSERT INTO `cell_info` VALUES ('2881', '40', null, null, '5', '25', '0');
INSERT INTO `cell_info` VALUES ('2882', '40', null, null, '5', '26', '0');
INSERT INTO `cell_info` VALUES ('2883', '40', null, null, '5', '27', '0');
INSERT INTO `cell_info` VALUES ('2884', '40', null, null, '5', '28', '0');
INSERT INTO `cell_info` VALUES ('2885', '40', null, null, '5', '29', '0');
INSERT INTO `cell_info` VALUES ('2886', '40', null, null, '5', '30', '0');
INSERT INTO `cell_info` VALUES ('2887', '40', null, null, '5', '31', '0');
INSERT INTO `cell_info` VALUES ('2888', '40', null, null, '6', '1', '0');
INSERT INTO `cell_info` VALUES ('2889', '40', null, null, '6', '2', '0');
INSERT INTO `cell_info` VALUES ('2890', '40', null, null, '6', '3', '0');
INSERT INTO `cell_info` VALUES ('2891', '40', null, null, '6', '4', '0');
INSERT INTO `cell_info` VALUES ('2892', '40', null, null, '6', '5', '0');
INSERT INTO `cell_info` VALUES ('2893', '40', null, null, '6', '6', '0');
INSERT INTO `cell_info` VALUES ('2894', '40', null, null, '6', '7', '0');
INSERT INTO `cell_info` VALUES ('2895', '40', null, null, '6', '8', '0');
INSERT INTO `cell_info` VALUES ('2896', '40', null, null, '6', '9', '0');
INSERT INTO `cell_info` VALUES ('2897', '40', null, null, '6', '10', '0');
INSERT INTO `cell_info` VALUES ('2898', '40', null, null, '6', '11', '0');
INSERT INTO `cell_info` VALUES ('2899', '40', null, null, '6', '12', '0');
INSERT INTO `cell_info` VALUES ('2900', '40', null, null, '6', '13', '0');
INSERT INTO `cell_info` VALUES ('2901', '40', null, null, '6', '14', '0');
INSERT INTO `cell_info` VALUES ('2902', '40', null, null, '6', '15', '0');
INSERT INTO `cell_info` VALUES ('2903', '40', null, null, '6', '16', '0');
INSERT INTO `cell_info` VALUES ('2904', '40', null, null, '6', '17', '0');
INSERT INTO `cell_info` VALUES ('2905', '40', null, null, '6', '18', '0');
INSERT INTO `cell_info` VALUES ('2906', '40', null, null, '6', '19', '0');
INSERT INTO `cell_info` VALUES ('2907', '40', null, null, '6', '20', '0');
INSERT INTO `cell_info` VALUES ('2908', '40', null, null, '6', '21', '0');
INSERT INTO `cell_info` VALUES ('2909', '40', null, null, '6', '22', '0');
INSERT INTO `cell_info` VALUES ('2910', '40', null, null, '6', '23', '0');
INSERT INTO `cell_info` VALUES ('2911', '40', null, null, '6', '24', '0');
INSERT INTO `cell_info` VALUES ('2912', '40', null, null, '6', '25', '0');
INSERT INTO `cell_info` VALUES ('2913', '40', null, null, '6', '26', '0');
INSERT INTO `cell_info` VALUES ('2914', '40', null, null, '6', '27', '0');
INSERT INTO `cell_info` VALUES ('2915', '40', null, null, '6', '28', '0');
INSERT INTO `cell_info` VALUES ('2916', '40', null, null, '6', '29', '0');
INSERT INTO `cell_info` VALUES ('2917', '40', null, null, '6', '30', '0');
INSERT INTO `cell_info` VALUES ('2918', '40', null, null, '6', '31', '0');
INSERT INTO `cell_info` VALUES ('2919', '40', null, null, '7', '1', '0');
INSERT INTO `cell_info` VALUES ('2920', '40', null, null, '7', '2', '0');
INSERT INTO `cell_info` VALUES ('2921', '40', null, null, '7', '3', '0');
INSERT INTO `cell_info` VALUES ('2922', '40', null, null, '7', '4', '0');
INSERT INTO `cell_info` VALUES ('2923', '40', null, null, '7', '5', '0');
INSERT INTO `cell_info` VALUES ('2924', '40', null, null, '7', '6', '0');
INSERT INTO `cell_info` VALUES ('2925', '40', null, null, '7', '7', '0');
INSERT INTO `cell_info` VALUES ('2926', '40', null, null, '7', '8', '0');
INSERT INTO `cell_info` VALUES ('2927', '40', null, null, '7', '9', '0');
INSERT INTO `cell_info` VALUES ('2928', '40', null, null, '7', '10', '0');
INSERT INTO `cell_info` VALUES ('2929', '40', null, null, '7', '11', '0');
INSERT INTO `cell_info` VALUES ('2930', '40', null, null, '7', '12', '0');
INSERT INTO `cell_info` VALUES ('2931', '40', null, null, '7', '13', '0');
INSERT INTO `cell_info` VALUES ('2932', '40', null, null, '7', '14', '0');
INSERT INTO `cell_info` VALUES ('2933', '40', null, null, '7', '15', '0');
INSERT INTO `cell_info` VALUES ('2934', '40', null, null, '7', '16', '0');
INSERT INTO `cell_info` VALUES ('2935', '40', null, null, '7', '17', '0');
INSERT INTO `cell_info` VALUES ('2936', '40', null, null, '7', '18', '0');
INSERT INTO `cell_info` VALUES ('2937', '40', null, null, '7', '19', '0');
INSERT INTO `cell_info` VALUES ('2938', '40', null, null, '7', '20', '0');
INSERT INTO `cell_info` VALUES ('2939', '40', null, null, '7', '21', '0');
INSERT INTO `cell_info` VALUES ('2940', '40', null, null, '7', '22', '0');
INSERT INTO `cell_info` VALUES ('2941', '40', null, null, '7', '23', '0');
INSERT INTO `cell_info` VALUES ('2942', '40', null, null, '7', '24', '0');
INSERT INTO `cell_info` VALUES ('2943', '40', null, null, '7', '25', '0');
INSERT INTO `cell_info` VALUES ('2944', '40', null, null, '7', '26', '0');
INSERT INTO `cell_info` VALUES ('2945', '40', null, null, '7', '27', '0');
INSERT INTO `cell_info` VALUES ('2946', '40', null, null, '7', '28', '0');
INSERT INTO `cell_info` VALUES ('2947', '40', null, null, '7', '29', '0');
INSERT INTO `cell_info` VALUES ('2948', '40', null, null, '7', '30', '0');
INSERT INTO `cell_info` VALUES ('2949', '40', null, null, '7', '31', '0');
INSERT INTO `cell_info` VALUES ('2950', '40', null, null, '8', '1', '0');
INSERT INTO `cell_info` VALUES ('2951', '40', null, null, '8', '2', '0');
INSERT INTO `cell_info` VALUES ('2952', '40', null, null, '8', '3', '0');
INSERT INTO `cell_info` VALUES ('2953', '40', null, null, '8', '4', '0');
INSERT INTO `cell_info` VALUES ('2954', '40', null, null, '8', '5', '0');
INSERT INTO `cell_info` VALUES ('2955', '40', null, null, '8', '6', '0');
INSERT INTO `cell_info` VALUES ('2956', '40', null, null, '8', '7', '0');
INSERT INTO `cell_info` VALUES ('2957', '40', null, null, '8', '8', '0');
INSERT INTO `cell_info` VALUES ('2958', '40', null, null, '8', '9', '0');
INSERT INTO `cell_info` VALUES ('2959', '40', null, null, '8', '10', '0');
INSERT INTO `cell_info` VALUES ('2960', '40', null, null, '8', '11', '0');
INSERT INTO `cell_info` VALUES ('2961', '40', null, null, '8', '12', '0');
INSERT INTO `cell_info` VALUES ('2962', '40', null, null, '8', '13', '0');
INSERT INTO `cell_info` VALUES ('2963', '40', null, null, '8', '14', '0');
INSERT INTO `cell_info` VALUES ('2964', '40', null, null, '8', '15', '0');
INSERT INTO `cell_info` VALUES ('2965', '40', null, null, '8', '16', '0');
INSERT INTO `cell_info` VALUES ('2966', '40', null, null, '8', '17', '0');
INSERT INTO `cell_info` VALUES ('2967', '40', null, null, '8', '18', '0');
INSERT INTO `cell_info` VALUES ('2968', '40', null, null, '8', '19', '0');
INSERT INTO `cell_info` VALUES ('2969', '40', null, null, '8', '20', '0');
INSERT INTO `cell_info` VALUES ('2970', '40', null, null, '8', '21', '0');
INSERT INTO `cell_info` VALUES ('2971', '40', null, null, '8', '22', '0');
INSERT INTO `cell_info` VALUES ('2972', '40', null, null, '8', '23', '0');
INSERT INTO `cell_info` VALUES ('2973', '40', null, null, '8', '24', '0');
INSERT INTO `cell_info` VALUES ('2974', '40', null, null, '8', '25', '0');
INSERT INTO `cell_info` VALUES ('2975', '40', null, null, '8', '26', '0');
INSERT INTO `cell_info` VALUES ('2976', '40', null, null, '8', '27', '0');
INSERT INTO `cell_info` VALUES ('2977', '40', null, null, '8', '28', '0');
INSERT INTO `cell_info` VALUES ('2978', '40', null, null, '8', '29', '0');
INSERT INTO `cell_info` VALUES ('2979', '40', null, null, '8', '30', '0');
INSERT INTO `cell_info` VALUES ('2980', '40', null, null, '8', '31', '0');
INSERT INTO `cell_info` VALUES ('2981', '40', null, null, '9', '1', '0');
INSERT INTO `cell_info` VALUES ('2982', '40', null, null, '9', '2', '0');
INSERT INTO `cell_info` VALUES ('2983', '40', null, null, '9', '3', '0');
INSERT INTO `cell_info` VALUES ('2984', '40', null, null, '9', '4', '0');
INSERT INTO `cell_info` VALUES ('2985', '40', null, null, '9', '5', '0');
INSERT INTO `cell_info` VALUES ('2986', '40', null, null, '9', '6', '0');
INSERT INTO `cell_info` VALUES ('2987', '40', null, null, '9', '7', '0');
INSERT INTO `cell_info` VALUES ('2988', '40', null, null, '9', '8', '0');
INSERT INTO `cell_info` VALUES ('2989', '40', null, null, '9', '9', '0');
INSERT INTO `cell_info` VALUES ('2990', '40', null, null, '9', '10', '0');
INSERT INTO `cell_info` VALUES ('2991', '40', null, null, '9', '11', '0');
INSERT INTO `cell_info` VALUES ('2992', '40', null, null, '9', '12', '0');
INSERT INTO `cell_info` VALUES ('2993', '40', null, null, '9', '13', '0');
INSERT INTO `cell_info` VALUES ('2994', '40', null, null, '9', '14', '0');
INSERT INTO `cell_info` VALUES ('2995', '40', null, null, '9', '15', '0');
INSERT INTO `cell_info` VALUES ('2996', '40', null, null, '9', '16', '0');
INSERT INTO `cell_info` VALUES ('2997', '40', null, null, '9', '17', '0');
INSERT INTO `cell_info` VALUES ('2998', '40', null, null, '9', '18', '0');
INSERT INTO `cell_info` VALUES ('2999', '40', null, null, '9', '19', '0');
INSERT INTO `cell_info` VALUES ('3000', '40', null, null, '9', '20', '0');
INSERT INTO `cell_info` VALUES ('3001', '40', null, null, '9', '21', '0');
INSERT INTO `cell_info` VALUES ('3002', '40', null, null, '9', '22', '0');
INSERT INTO `cell_info` VALUES ('3003', '40', null, null, '9', '23', '0');
INSERT INTO `cell_info` VALUES ('3004', '40', null, null, '9', '24', '0');
INSERT INTO `cell_info` VALUES ('3005', '40', null, null, '9', '25', '0');
INSERT INTO `cell_info` VALUES ('3006', '40', null, null, '9', '26', '0');
INSERT INTO `cell_info` VALUES ('3007', '40', null, null, '9', '27', '0');
INSERT INTO `cell_info` VALUES ('3008', '40', null, null, '9', '28', '0');
INSERT INTO `cell_info` VALUES ('3009', '40', null, null, '9', '29', '0');
INSERT INTO `cell_info` VALUES ('3010', '40', null, null, '9', '30', '0');
INSERT INTO `cell_info` VALUES ('3011', '40', null, null, '9', '31', '0');
INSERT INTO `cell_info` VALUES ('3012', '40', null, null, '10', '1', '0');
INSERT INTO `cell_info` VALUES ('3013', '40', null, null, '10', '2', '0');
INSERT INTO `cell_info` VALUES ('3014', '40', null, null, '10', '3', '0');
INSERT INTO `cell_info` VALUES ('3015', '40', null, null, '10', '4', '0');
INSERT INTO `cell_info` VALUES ('3016', '40', null, null, '10', '5', '0');
INSERT INTO `cell_info` VALUES ('3017', '40', null, null, '10', '6', '0');
INSERT INTO `cell_info` VALUES ('3018', '40', null, null, '10', '7', '0');
INSERT INTO `cell_info` VALUES ('3019', '40', null, null, '10', '8', '0');
INSERT INTO `cell_info` VALUES ('3020', '40', null, null, '10', '9', '0');
INSERT INTO `cell_info` VALUES ('3021', '40', null, null, '10', '10', '0');
INSERT INTO `cell_info` VALUES ('3022', '40', null, null, '10', '11', '0');
INSERT INTO `cell_info` VALUES ('3023', '40', null, null, '10', '12', '0');
INSERT INTO `cell_info` VALUES ('3024', '40', null, null, '10', '13', '0');
INSERT INTO `cell_info` VALUES ('3025', '40', null, null, '10', '14', '0');
INSERT INTO `cell_info` VALUES ('3026', '40', null, null, '10', '15', '0');
INSERT INTO `cell_info` VALUES ('3027', '40', null, null, '10', '16', '0');
INSERT INTO `cell_info` VALUES ('3028', '40', null, null, '10', '17', '0');
INSERT INTO `cell_info` VALUES ('3029', '40', null, null, '10', '18', '0');
INSERT INTO `cell_info` VALUES ('3030', '40', null, null, '10', '19', '0');
INSERT INTO `cell_info` VALUES ('3031', '40', null, null, '10', '20', '0');
INSERT INTO `cell_info` VALUES ('3032', '40', null, null, '10', '21', '0');
INSERT INTO `cell_info` VALUES ('3033', '40', null, null, '10', '22', '0');
INSERT INTO `cell_info` VALUES ('3034', '40', null, null, '10', '23', '0');
INSERT INTO `cell_info` VALUES ('3035', '40', null, null, '10', '24', '0');
INSERT INTO `cell_info` VALUES ('3036', '40', null, null, '10', '25', '0');
INSERT INTO `cell_info` VALUES ('3037', '40', null, null, '10', '26', '0');
INSERT INTO `cell_info` VALUES ('3038', '40', null, null, '10', '27', '0');
INSERT INTO `cell_info` VALUES ('3039', '40', null, null, '10', '28', '0');
INSERT INTO `cell_info` VALUES ('3040', '40', null, null, '10', '29', '0');
INSERT INTO `cell_info` VALUES ('3041', '40', null, null, '10', '30', '0');
INSERT INTO `cell_info` VALUES ('3042', '40', null, null, '10', '31', '0');
INSERT INTO `cell_info` VALUES ('3043', '40', null, null, '11', '1', '0');
INSERT INTO `cell_info` VALUES ('3044', '40', null, null, '11', '2', '0');
INSERT INTO `cell_info` VALUES ('3045', '40', null, null, '11', '3', '0');
INSERT INTO `cell_info` VALUES ('3046', '40', null, null, '11', '4', '0');
INSERT INTO `cell_info` VALUES ('3047', '40', null, null, '11', '5', '0');
INSERT INTO `cell_info` VALUES ('3048', '40', null, null, '11', '6', '0');
INSERT INTO `cell_info` VALUES ('3049', '40', null, null, '11', '7', '0');
INSERT INTO `cell_info` VALUES ('3050', '40', null, null, '11', '8', '0');
INSERT INTO `cell_info` VALUES ('3051', '40', null, null, '11', '9', '0');
INSERT INTO `cell_info` VALUES ('3052', '40', null, null, '11', '10', '0');
INSERT INTO `cell_info` VALUES ('3053', '40', null, null, '11', '11', '0');
INSERT INTO `cell_info` VALUES ('3054', '40', null, null, '11', '12', '0');
INSERT INTO `cell_info` VALUES ('3055', '40', null, null, '11', '13', '0');
INSERT INTO `cell_info` VALUES ('3056', '40', null, null, '11', '14', '0');
INSERT INTO `cell_info` VALUES ('3057', '40', null, null, '11', '15', '0');
INSERT INTO `cell_info` VALUES ('3058', '40', null, null, '11', '16', '0');
INSERT INTO `cell_info` VALUES ('3059', '40', null, null, '11', '17', '0');
INSERT INTO `cell_info` VALUES ('3060', '40', null, null, '11', '18', '0');
INSERT INTO `cell_info` VALUES ('3061', '40', null, null, '11', '19', '0');
INSERT INTO `cell_info` VALUES ('3062', '40', null, null, '11', '20', '0');
INSERT INTO `cell_info` VALUES ('3063', '40', null, null, '11', '21', '0');
INSERT INTO `cell_info` VALUES ('3064', '40', null, null, '11', '22', '0');
INSERT INTO `cell_info` VALUES ('3065', '40', null, null, '11', '23', '0');
INSERT INTO `cell_info` VALUES ('3066', '40', null, null, '11', '24', '0');
INSERT INTO `cell_info` VALUES ('3067', '40', null, null, '11', '25', '0');
INSERT INTO `cell_info` VALUES ('3068', '40', null, null, '11', '26', '0');
INSERT INTO `cell_info` VALUES ('3069', '40', null, null, '11', '27', '0');
INSERT INTO `cell_info` VALUES ('3070', '40', null, null, '11', '28', '0');
INSERT INTO `cell_info` VALUES ('3071', '40', null, null, '11', '29', '0');
INSERT INTO `cell_info` VALUES ('3072', '40', null, null, '11', '30', '0');
INSERT INTO `cell_info` VALUES ('3073', '40', null, null, '11', '31', '0');
INSERT INTO `cell_info` VALUES ('3074', '40', null, null, '12', '1', '0');
INSERT INTO `cell_info` VALUES ('3075', '40', null, null, '12', '2', '0');
INSERT INTO `cell_info` VALUES ('3076', '40', null, null, '12', '3', '0');
INSERT INTO `cell_info` VALUES ('3077', '40', null, null, '12', '4', '0');
INSERT INTO `cell_info` VALUES ('3078', '40', null, null, '12', '5', '0');
INSERT INTO `cell_info` VALUES ('3079', '40', null, null, '12', '6', '0');
INSERT INTO `cell_info` VALUES ('3080', '40', null, null, '12', '7', '0');
INSERT INTO `cell_info` VALUES ('3081', '40', null, null, '12', '8', '0');
INSERT INTO `cell_info` VALUES ('3082', '40', null, null, '12', '9', '0');
INSERT INTO `cell_info` VALUES ('3083', '40', null, null, '12', '10', '0');
INSERT INTO `cell_info` VALUES ('3084', '40', null, null, '12', '11', '0');
INSERT INTO `cell_info` VALUES ('3085', '40', null, null, '12', '12', '0');
INSERT INTO `cell_info` VALUES ('3086', '40', null, null, '12', '13', '0');
INSERT INTO `cell_info` VALUES ('3087', '40', null, null, '12', '14', '0');
INSERT INTO `cell_info` VALUES ('3088', '40', null, null, '12', '15', '0');
INSERT INTO `cell_info` VALUES ('3089', '40', null, null, '12', '16', '0');
INSERT INTO `cell_info` VALUES ('3090', '40', null, null, '12', '17', '0');
INSERT INTO `cell_info` VALUES ('3091', '40', null, null, '12', '18', '0');
INSERT INTO `cell_info` VALUES ('3092', '40', null, null, '12', '19', '0');
INSERT INTO `cell_info` VALUES ('3093', '40', null, null, '12', '20', '0');
INSERT INTO `cell_info` VALUES ('3094', '40', null, null, '12', '21', '0');
INSERT INTO `cell_info` VALUES ('3095', '40', null, null, '12', '22', '0');
INSERT INTO `cell_info` VALUES ('3096', '40', null, null, '12', '23', '0');
INSERT INTO `cell_info` VALUES ('3097', '40', null, null, '12', '24', '0');
INSERT INTO `cell_info` VALUES ('3098', '40', null, null, '12', '25', '0');
INSERT INTO `cell_info` VALUES ('3099', '40', null, null, '12', '26', '0');
INSERT INTO `cell_info` VALUES ('3100', '40', null, null, '12', '27', '0');
INSERT INTO `cell_info` VALUES ('3101', '40', null, null, '12', '28', '0');
INSERT INTO `cell_info` VALUES ('3102', '40', null, null, '12', '29', '0');
INSERT INTO `cell_info` VALUES ('3103', '40', null, null, '12', '30', '0');
INSERT INTO `cell_info` VALUES ('3104', '40', null, null, '12', '31', '0');
INSERT INTO `cell_info` VALUES ('3105', '40', null, null, '13', '1', '0');
INSERT INTO `cell_info` VALUES ('3106', '40', null, null, '13', '2', '0');
INSERT INTO `cell_info` VALUES ('3107', '40', null, null, '13', '3', '0');
INSERT INTO `cell_info` VALUES ('3108', '40', null, null, '13', '4', '0');
INSERT INTO `cell_info` VALUES ('3109', '40', null, null, '13', '5', '0');
INSERT INTO `cell_info` VALUES ('3110', '40', null, null, '13', '6', '0');
INSERT INTO `cell_info` VALUES ('3111', '40', null, null, '13', '7', '0');
INSERT INTO `cell_info` VALUES ('3112', '40', null, null, '13', '8', '0');
INSERT INTO `cell_info` VALUES ('3113', '40', null, null, '13', '9', '0');
INSERT INTO `cell_info` VALUES ('3114', '40', null, null, '13', '10', '0');
INSERT INTO `cell_info` VALUES ('3115', '40', null, null, '13', '11', '0');
INSERT INTO `cell_info` VALUES ('3116', '40', null, null, '13', '12', '0');
INSERT INTO `cell_info` VALUES ('3117', '40', null, null, '13', '13', '0');
INSERT INTO `cell_info` VALUES ('3118', '40', null, null, '13', '14', '0');
INSERT INTO `cell_info` VALUES ('3119', '40', null, null, '13', '15', '0');
INSERT INTO `cell_info` VALUES ('3120', '40', null, null, '13', '16', '0');
INSERT INTO `cell_info` VALUES ('3121', '40', null, null, '13', '17', '0');
INSERT INTO `cell_info` VALUES ('3122', '40', null, null, '13', '18', '0');
INSERT INTO `cell_info` VALUES ('3123', '40', null, null, '13', '19', '0');
INSERT INTO `cell_info` VALUES ('3124', '40', null, null, '13', '20', '0');
INSERT INTO `cell_info` VALUES ('3125', '40', null, null, '13', '21', '0');
INSERT INTO `cell_info` VALUES ('3126', '40', null, null, '13', '22', '0');
INSERT INTO `cell_info` VALUES ('3127', '40', null, null, '13', '23', '0');
INSERT INTO `cell_info` VALUES ('3128', '40', null, null, '13', '24', '0');
INSERT INTO `cell_info` VALUES ('3129', '40', null, null, '13', '25', '0');
INSERT INTO `cell_info` VALUES ('3130', '40', null, null, '13', '26', '0');
INSERT INTO `cell_info` VALUES ('3131', '40', null, null, '13', '27', '0');
INSERT INTO `cell_info` VALUES ('3132', '40', null, null, '13', '28', '0');
INSERT INTO `cell_info` VALUES ('3133', '40', null, null, '13', '29', '0');
INSERT INTO `cell_info` VALUES ('3134', '40', null, null, '13', '30', '0');
INSERT INTO `cell_info` VALUES ('3135', '40', null, null, '13', '31', '0');
INSERT INTO `cell_info` VALUES ('3136', '40', null, null, '14', '1', '0');
INSERT INTO `cell_info` VALUES ('3137', '40', null, null, '14', '2', '0');
INSERT INTO `cell_info` VALUES ('3138', '40', null, null, '14', '3', '0');
INSERT INTO `cell_info` VALUES ('3139', '40', null, null, '14', '4', '0');
INSERT INTO `cell_info` VALUES ('3140', '40', null, null, '14', '5', '0');
INSERT INTO `cell_info` VALUES ('3141', '40', null, null, '14', '6', '0');
INSERT INTO `cell_info` VALUES ('3142', '40', null, null, '14', '7', '0');
INSERT INTO `cell_info` VALUES ('3143', '40', null, null, '14', '8', '0');
INSERT INTO `cell_info` VALUES ('3144', '40', null, null, '14', '9', '0');
INSERT INTO `cell_info` VALUES ('3145', '40', null, null, '14', '10', '0');
INSERT INTO `cell_info` VALUES ('3146', '40', null, null, '14', '11', '0');
INSERT INTO `cell_info` VALUES ('3147', '40', null, null, '14', '12', '0');
INSERT INTO `cell_info` VALUES ('3148', '40', null, null, '14', '13', '0');
INSERT INTO `cell_info` VALUES ('3149', '40', null, null, '14', '14', '0');
INSERT INTO `cell_info` VALUES ('3150', '40', null, null, '14', '15', '0');
INSERT INTO `cell_info` VALUES ('3151', '40', null, null, '14', '16', '0');
INSERT INTO `cell_info` VALUES ('3152', '40', null, null, '14', '17', '0');
INSERT INTO `cell_info` VALUES ('3153', '40', null, null, '14', '18', '0');
INSERT INTO `cell_info` VALUES ('3154', '40', null, null, '14', '19', '0');
INSERT INTO `cell_info` VALUES ('3155', '40', null, null, '14', '20', '0');
INSERT INTO `cell_info` VALUES ('3156', '40', null, null, '14', '21', '0');
INSERT INTO `cell_info` VALUES ('3157', '40', null, null, '14', '22', '0');
INSERT INTO `cell_info` VALUES ('3158', '40', null, null, '14', '23', '0');
INSERT INTO `cell_info` VALUES ('3159', '40', null, null, '14', '24', '0');
INSERT INTO `cell_info` VALUES ('3160', '40', null, null, '14', '25', '0');
INSERT INTO `cell_info` VALUES ('3161', '40', null, null, '14', '26', '0');
INSERT INTO `cell_info` VALUES ('3162', '40', null, null, '14', '27', '0');
INSERT INTO `cell_info` VALUES ('3163', '40', null, null, '14', '28', '0');
INSERT INTO `cell_info` VALUES ('3164', '40', null, null, '14', '29', '0');
INSERT INTO `cell_info` VALUES ('3165', '40', null, null, '14', '30', '0');
INSERT INTO `cell_info` VALUES ('3166', '40', null, null, '14', '31', '0');
INSERT INTO `cell_info` VALUES ('3167', '40', null, null, '15', '1', '0');
INSERT INTO `cell_info` VALUES ('3168', '40', null, null, '15', '2', '0');
INSERT INTO `cell_info` VALUES ('3169', '40', null, null, '15', '3', '0');
INSERT INTO `cell_info` VALUES ('3170', '40', null, null, '15', '4', '0');
INSERT INTO `cell_info` VALUES ('3171', '40', null, null, '15', '5', '0');
INSERT INTO `cell_info` VALUES ('3172', '40', null, null, '15', '6', '0');
INSERT INTO `cell_info` VALUES ('3173', '40', null, null, '15', '7', '0');
INSERT INTO `cell_info` VALUES ('3174', '40', null, null, '15', '8', '0');
INSERT INTO `cell_info` VALUES ('3175', '40', null, null, '15', '9', '0');
INSERT INTO `cell_info` VALUES ('3176', '40', null, null, '15', '10', '0');
INSERT INTO `cell_info` VALUES ('3177', '40', null, null, '15', '11', '0');
INSERT INTO `cell_info` VALUES ('3178', '40', null, null, '15', '12', '0');
INSERT INTO `cell_info` VALUES ('3179', '40', null, null, '15', '13', '0');
INSERT INTO `cell_info` VALUES ('3180', '40', null, null, '15', '14', '0');
INSERT INTO `cell_info` VALUES ('3181', '40', null, null, '15', '15', '0');
INSERT INTO `cell_info` VALUES ('3182', '40', null, null, '15', '16', '0');
INSERT INTO `cell_info` VALUES ('3183', '40', null, null, '15', '17', '0');
INSERT INTO `cell_info` VALUES ('3184', '40', null, null, '15', '18', '0');
INSERT INTO `cell_info` VALUES ('3185', '40', null, null, '15', '19', '0');
INSERT INTO `cell_info` VALUES ('3186', '40', null, null, '15', '20', '0');
INSERT INTO `cell_info` VALUES ('3187', '40', null, null, '15', '21', '0');
INSERT INTO `cell_info` VALUES ('3188', '40', null, null, '15', '22', '0');
INSERT INTO `cell_info` VALUES ('3189', '40', null, null, '15', '23', '0');
INSERT INTO `cell_info` VALUES ('3190', '40', null, null, '15', '24', '0');
INSERT INTO `cell_info` VALUES ('3191', '40', null, null, '15', '25', '0');
INSERT INTO `cell_info` VALUES ('3192', '40', null, null, '15', '26', '0');
INSERT INTO `cell_info` VALUES ('3193', '40', null, null, '15', '27', '0');
INSERT INTO `cell_info` VALUES ('3194', '40', null, null, '15', '28', '0');
INSERT INTO `cell_info` VALUES ('3195', '40', null, null, '15', '29', '0');
INSERT INTO `cell_info` VALUES ('3196', '40', null, null, '15', '30', '0');
INSERT INTO `cell_info` VALUES ('3197', '40', null, null, '15', '31', '0');
INSERT INTO `cell_info` VALUES ('3198', '40', null, null, '16', '1', '0');
INSERT INTO `cell_info` VALUES ('3199', '40', null, null, '16', '2', '0');
INSERT INTO `cell_info` VALUES ('3200', '40', null, null, '16', '3', '0');
INSERT INTO `cell_info` VALUES ('3201', '40', null, null, '16', '4', '0');
INSERT INTO `cell_info` VALUES ('3202', '40', null, null, '16', '5', '0');
INSERT INTO `cell_info` VALUES ('3203', '40', null, null, '16', '6', '0');
INSERT INTO `cell_info` VALUES ('3204', '40', null, null, '16', '7', '0');
INSERT INTO `cell_info` VALUES ('3205', '40', null, null, '16', '8', '0');
INSERT INTO `cell_info` VALUES ('3206', '40', null, null, '16', '9', '0');
INSERT INTO `cell_info` VALUES ('3207', '40', null, null, '16', '10', '0');
INSERT INTO `cell_info` VALUES ('3208', '40', null, null, '16', '11', '0');
INSERT INTO `cell_info` VALUES ('3209', '40', null, null, '16', '12', '0');
INSERT INTO `cell_info` VALUES ('3210', '40', null, null, '16', '13', '0');
INSERT INTO `cell_info` VALUES ('3211', '40', null, null, '16', '14', '0');
INSERT INTO `cell_info` VALUES ('3212', '40', null, null, '16', '15', '0');
INSERT INTO `cell_info` VALUES ('3213', '40', null, null, '16', '16', '0');
INSERT INTO `cell_info` VALUES ('3214', '40', null, null, '16', '17', '0');
INSERT INTO `cell_info` VALUES ('3215', '40', null, null, '16', '18', '0');
INSERT INTO `cell_info` VALUES ('3216', '40', null, null, '16', '19', '0');
INSERT INTO `cell_info` VALUES ('3217', '40', null, null, '16', '20', '0');
INSERT INTO `cell_info` VALUES ('3218', '40', null, null, '16', '21', '0');
INSERT INTO `cell_info` VALUES ('3219', '40', null, null, '16', '22', '0');
INSERT INTO `cell_info` VALUES ('3220', '40', null, null, '16', '23', '0');
INSERT INTO `cell_info` VALUES ('3221', '40', null, null, '16', '24', '0');
INSERT INTO `cell_info` VALUES ('3222', '40', null, null, '16', '25', '0');
INSERT INTO `cell_info` VALUES ('3223', '40', null, null, '16', '26', '0');
INSERT INTO `cell_info` VALUES ('3224', '40', null, null, '16', '27', '0');
INSERT INTO `cell_info` VALUES ('3225', '40', null, null, '16', '28', '0');
INSERT INTO `cell_info` VALUES ('3226', '40', null, null, '16', '29', '0');
INSERT INTO `cell_info` VALUES ('3227', '40', null, null, '16', '30', '0');
INSERT INTO `cell_info` VALUES ('3228', '40', null, null, '16', '31', '0');
INSERT INTO `cell_info` VALUES ('3229', '40', null, null, '17', '1', '0');
INSERT INTO `cell_info` VALUES ('3230', '40', null, null, '17', '2', '0');
INSERT INTO `cell_info` VALUES ('3231', '40', null, null, '17', '3', '0');
INSERT INTO `cell_info` VALUES ('3232', '40', null, null, '17', '4', '0');
INSERT INTO `cell_info` VALUES ('3233', '40', null, null, '17', '5', '0');
INSERT INTO `cell_info` VALUES ('3234', '40', null, null, '17', '6', '0');
INSERT INTO `cell_info` VALUES ('3235', '40', null, null, '17', '7', '0');
INSERT INTO `cell_info` VALUES ('3236', '40', null, null, '17', '8', '0');
INSERT INTO `cell_info` VALUES ('3237', '40', null, null, '17', '9', '0');
INSERT INTO `cell_info` VALUES ('3238', '40', null, null, '17', '10', '0');
INSERT INTO `cell_info` VALUES ('3239', '40', null, null, '17', '11', '0');
INSERT INTO `cell_info` VALUES ('3240', '40', null, null, '17', '12', '0');
INSERT INTO `cell_info` VALUES ('3241', '40', null, null, '17', '13', '0');
INSERT INTO `cell_info` VALUES ('3242', '40', null, null, '17', '14', '0');
INSERT INTO `cell_info` VALUES ('3243', '40', null, null, '17', '15', '0');
INSERT INTO `cell_info` VALUES ('3244', '40', null, null, '17', '16', '0');
INSERT INTO `cell_info` VALUES ('3245', '40', null, null, '17', '17', '0');
INSERT INTO `cell_info` VALUES ('3246', '40', null, null, '17', '18', '0');
INSERT INTO `cell_info` VALUES ('3247', '40', null, null, '17', '19', '0');
INSERT INTO `cell_info` VALUES ('3248', '40', null, null, '17', '20', '0');
INSERT INTO `cell_info` VALUES ('3249', '40', null, null, '17', '21', '0');
INSERT INTO `cell_info` VALUES ('3250', '40', null, null, '17', '22', '0');
INSERT INTO `cell_info` VALUES ('3251', '40', null, null, '17', '23', '0');
INSERT INTO `cell_info` VALUES ('3252', '40', null, null, '17', '24', '0');
INSERT INTO `cell_info` VALUES ('3253', '40', null, null, '17', '25', '0');
INSERT INTO `cell_info` VALUES ('3254', '40', null, null, '17', '26', '0');
INSERT INTO `cell_info` VALUES ('3255', '40', null, null, '17', '27', '0');
INSERT INTO `cell_info` VALUES ('3256', '40', null, null, '17', '28', '0');
INSERT INTO `cell_info` VALUES ('3257', '40', null, null, '17', '29', '0');
INSERT INTO `cell_info` VALUES ('3258', '40', null, null, '17', '30', '0');
INSERT INTO `cell_info` VALUES ('3259', '40', null, null, '17', '31', '0');
INSERT INTO `cell_info` VALUES ('3260', '40', null, null, '18', '1', '0');
INSERT INTO `cell_info` VALUES ('3261', '40', null, null, '18', '2', '0');
INSERT INTO `cell_info` VALUES ('3262', '40', null, null, '18', '3', '0');
INSERT INTO `cell_info` VALUES ('3263', '40', null, null, '18', '4', '0');
INSERT INTO `cell_info` VALUES ('3264', '40', null, null, '18', '5', '0');
INSERT INTO `cell_info` VALUES ('3265', '40', null, null, '18', '6', '0');
INSERT INTO `cell_info` VALUES ('3266', '40', null, null, '18', '7', '0');
INSERT INTO `cell_info` VALUES ('3267', '40', null, null, '18', '8', '0');
INSERT INTO `cell_info` VALUES ('3268', '40', null, null, '18', '9', '0');
INSERT INTO `cell_info` VALUES ('3269', '40', null, null, '18', '10', '0');
INSERT INTO `cell_info` VALUES ('3270', '40', null, null, '18', '11', '0');
INSERT INTO `cell_info` VALUES ('3271', '40', null, null, '18', '12', '0');
INSERT INTO `cell_info` VALUES ('3272', '40', null, null, '18', '13', '0');
INSERT INTO `cell_info` VALUES ('3273', '40', null, null, '18', '14', '0');
INSERT INTO `cell_info` VALUES ('3274', '40', null, null, '18', '15', '0');
INSERT INTO `cell_info` VALUES ('3275', '40', null, null, '18', '16', '0');
INSERT INTO `cell_info` VALUES ('3276', '40', null, null, '18', '17', '0');
INSERT INTO `cell_info` VALUES ('3277', '40', null, null, '18', '18', '0');
INSERT INTO `cell_info` VALUES ('3278', '40', null, null, '18', '19', '0');
INSERT INTO `cell_info` VALUES ('3279', '40', null, null, '18', '20', '0');
INSERT INTO `cell_info` VALUES ('3280', '40', null, null, '18', '21', '0');
INSERT INTO `cell_info` VALUES ('3281', '40', null, null, '18', '22', '0');
INSERT INTO `cell_info` VALUES ('3282', '40', null, null, '18', '23', '0');
INSERT INTO `cell_info` VALUES ('3283', '40', null, null, '18', '24', '0');
INSERT INTO `cell_info` VALUES ('3284', '40', null, null, '18', '25', '0');
INSERT INTO `cell_info` VALUES ('3285', '40', null, null, '18', '26', '0');
INSERT INTO `cell_info` VALUES ('3286', '40', null, null, '18', '27', '0');
INSERT INTO `cell_info` VALUES ('3287', '40', null, null, '18', '28', '0');
INSERT INTO `cell_info` VALUES ('3288', '40', null, null, '18', '29', '0');
INSERT INTO `cell_info` VALUES ('3289', '40', null, null, '18', '30', '0');
INSERT INTO `cell_info` VALUES ('3290', '40', null, null, '18', '31', '0');

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_name` varchar(11) DEFAULT NULL COMMENT '班级名称',
  `class_type` varchar(11) DEFAULT NULL COMMENT '班级类型',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='班级表';

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '高一', '普通班');
INSERT INTO `class` VALUES ('2', '高二', '实验班');
INSERT INTO `class` VALUES ('3', '高三', '火箭班');

-- ----------------------------
-- Table structure for `combine_box_record`
-- ----------------------------
DROP TABLE IF EXISTS `combine_box_record`;
CREATE TABLE `combine_box_record` (
  `combine_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合框记录id',
  `from_box_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '从箱',
  `from_quantity` int(11) DEFAULT NULL COMMENT '从本箱数量',
  `to_box_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '到箱',
  `to_quantity` int(11) DEFAULT NULL COMMENT '到箱数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `card_no` varchar(2550) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作人工号',
  PRIMARY KEY (`combine_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of combine_box_record
-- ----------------------------

-- ----------------------------
-- Table structure for `conveyor`
-- ----------------------------
DROP TABLE IF EXISTS `conveyor`;
CREATE TABLE `conveyor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `point1` varchar(600) DEFAULT NULL,
  `point2` varchar(600) DEFAULT NULL,
  `point3` varchar(600) DEFAULT NULL,
  `point4` varchar(600) DEFAULT NULL,
  `point5` varchar(600) DEFAULT NULL,
  `point6` varchar(600) DEFAULT NULL,
  `point7` varchar(600) DEFAULT NULL,
  `point8` varchar(600) DEFAULT NULL,
  `point9` varchar(600) DEFAULT NULL,
  `point10` varchar(600) DEFAULT NULL,
  `point11` varchar(600) DEFAULT NULL,
  `point12` varchar(600) DEFAULT NULL,
  `point13` varchar(600) DEFAULT NULL,
  `point14` varchar(600) DEFAULT NULL,
  `point15` varchar(600) DEFAULT NULL,
  `point16` varchar(600) DEFAULT NULL,
  `point17` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of conveyor
-- ----------------------------

-- ----------------------------
-- Table structure for `door`
-- ----------------------------
DROP TABLE IF EXISTS `door`;
CREATE TABLE `door` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `address_code` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` int(2) DEFAULT NULL COMMENT '类型：1-入库口 2-出库口',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `ware_id` int(11) DEFAULT NULL COMMENT '仓库ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `door_id_PK` (`id`) USING BTREE,
  KEY `door_ware_id` (`ware_id`) USING BTREE,
  KEY `door_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='出入库口';

-- ----------------------------
-- Records of door
-- ----------------------------
INSERT INTO `door` VALUES ('5', 'AC1908252', 'D10012', 'AGV出库口', '2', '1', '212');
INSERT INTO `door` VALUES ('7', 'AC1908252', 'D10011', 'AGV入库口', '1', '0', '212');

-- ----------------------------
-- Table structure for `duiduoji`
-- ----------------------------
DROP TABLE IF EXISTS `duiduoji`;
CREATE TABLE `duiduoji` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `columns` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '堆垛机高',
  `rows` varchar(10) CHARACTER SET latin1 NOT NULL COMMENT '堆垛机层',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of duiduoji
-- ----------------------------

-- ----------------------------
-- Table structure for `inventory_check`
-- ----------------------------
DROP TABLE IF EXISTS `inventory_check`;
CREATE TABLE `inventory_check` (
  `inventory_check_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '盘点',
  `box_code` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '从箱号',
  `to_box_code` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '到箱号',
  `quantity` int(11) DEFAULT NULL COMMENT '当前箱数量',
  `after_check_quantity` int(11) DEFAULT NULL COMMENT '盘点后数量',
  `check_quantity` int(11) DEFAULT NULL COMMENT '盘盈/盘亏',
  `type` int(11) DEFAULT NULL COMMENT '类型 1-工单出库盘点  2-非工单出库盘点 3-盘点任务',
  `state` int(11) DEFAULT NULL COMMENT '1-待下发 2-已下发 3- 已完成',
  `item_code` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '物料编码',
  `batch` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '批次',
  `bill_out_detail_id` int(11) DEFAULT NULL COMMENT '入库单Id',
  PRIMARY KEY (`inventory_check_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of inventory_check
-- ----------------------------

-- ----------------------------
-- Table structure for `item_info`
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `item_code` varchar(20) DEFAULT NULL COMMENT '编码',
  `item_name` varchar(1000) DEFAULT NULL COMMENT '名称',
  `unit` varchar(11) DEFAULT NULL,
  `spec` varchar(20) DEFAULT NULL COMMENT '规格',
  `model` varchar(20) DEFAULT NULL COMMENT '型号',
  `thickness` decimal(11,2) DEFAULT '0.00' COMMENT '厚度（毫米）',
  `item_type_id` int(11) DEFAULT NULL,
  `floor_limit` int(11) DEFAULT '0' COMMENT '库存下限',
  `upper_limit` int(11) DEFAULT '1000' COMMENT '库存上线',
  `date_warning` int(11) DEFAULT NULL COMMENT '保质期（天）',
  `max_pack_qty` int(11) DEFAULT NULL COMMENT '最大装箱量',
  `inventory_item_id` int(11) DEFAULT NULL COMMENT 'EBS物料Id',
  `card_no_one` varchar(11) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人卡号',
  `card_no_two` varchar(11) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人卡号二',
  PRIMARY KEY (`item_id`) USING BTREE,
  KEY `item_type_id` (`item_type_id`) USING BTREE,
  KEY `item_info_item_code` (`item_code`) USING BTREE,
  KEY `item_info_item_id_PK` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='物料';

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES ('1', '104125659', '覆铜板 BT 0.15 B/B 0.154 20.20X24.53 HL832NX A-HS-E (5R) (HF)', '张', null, null, '0.10', null, '0', '1000', null, '1300', '1341901', '0000000000', '0865257155');
INSERT INTO `item_info` VALUES ('2', '104125661', '覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)', '张', null, null, '0.20', null, '0', '1000', null, '650', '1341903', '0000000000', '1996092737');
INSERT INTO `item_info` VALUES ('3', '104116173', '覆铜板 BT 0.20 T/T 0.224 20X24 HL832NS (5R) (HF)', '张', null, null, '0.10', null, '0', '1000', null, '1300', '695325', '0000000000', '1111111111');

-- ----------------------------
-- Table structure for `item_type`
-- ----------------------------
DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type` (
  `item_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `item_type_code` varchar(20) DEFAULT NULL COMMENT '编码',
  `item_type_name` varchar(20) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `order_num` int(4) DEFAULT NULL,
  PRIMARY KEY (`item_type_id`) USING BTREE,
  KEY `item_type_id` (`item_type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='物料分类';

-- ----------------------------
-- Records of item_type
-- ----------------------------

-- ----------------------------
-- Table structure for `operator`
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `operator_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库操作员id ',
  `operator_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `operator_card` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `emp_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '工号',
  `autoverify_permission` int(11) DEFAULT NULL COMMENT '初始化不合格权限',
  `check_permission` int(11) DEFAULT NULL COMMENT 'IQC检验权限',
  `combine_permission` int(11) DEFAULT NULL COMMENT '合框权限',
  `return_item_permission` int(11) DEFAULT NULL COMMENT '退货权限',
  `quality_check_permission` int(11) DEFAULT NULL COMMENT '品质异常检验权限',
  `scrap_permission` int(11) DEFAULT NULL COMMENT '报废权限',
  `manual_out_permission` int(11) DEFAULT NULL COMMENT '非工单出库权限',
  `transfer_warehouse_permission` int(11) DEFAULT NULL COMMENT '转库权限',
  `logout_flag` int(255) DEFAULT NULL COMMENT '注销标志',
  PRIMARY KEY (`operator_id`) USING BTREE,
  KEY `operator_card` (`operator_card`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('1', 'AA', '3240216411', '', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `operator` VALUES ('2', 'bb', '4057586412', '', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `operator` VALUES ('3', '李红保', '2699333981', 'SN15079', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `operator` VALUES ('4', '吴', '0000000000', 'Hony', '2', '1', '1', '1', '1', '1', '1', '2', '1');

-- ----------------------------
-- Table structure for `order_information`
-- ----------------------------
DROP TABLE IF EXISTS `order_information`;
CREATE TABLE `order_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'EBS抓取订单信息表Id',
  `item_id` int(20) DEFAULT NULL COMMENT '物料Id',
  `po_distribution_id` int(20) DEFAULT NULL COMMENT '分配行ID',
  `distribution_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '分配行号',
  `segment` varchar(255) DEFAULT NULL COMMENT '采购单号',
  `po_header_id` int(20) DEFAULT NULL COMMENT '采购订单头ID',
  `line_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '采购订单行号',
  `po_line_id` int(20) DEFAULT NULL COMMENT '采购订单行ID',
  `shipment_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '发运行号',
  `line_location_id` int(20) DEFAULT NULL COMMENT '发运行ID',
  `org_id` int(20) DEFAULT NULL COMMENT 'OU组织ID',
  `ship_to_organization_id` int(20) DEFAULT NULL COMMENT '接收库存组织ID',
  `item_description` varchar(900) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '物料描述',
  `unit_meas_lookup_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '采购单位',
  `unit_price` decimal(16,4) DEFAULT NULL COMMENT '采购单价',
  `quantity` int(11) DEFAULT NULL COMMENT '发运行数量',
  `quantity_received` int(11) DEFAULT NULL COMMENT '已接收数量',
  `closed_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '发运行状态',
  `supply_type_code` varchar(255) DEFAULT NULL COMMENT '接收类型',
  `surplus_received_quantity` int(11) DEFAULT NULL COMMENT '可接收数量',
  `vendor_id` int(20) DEFAULT NULL COMMENT '供应商ID',
  `vendor_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '供应商编码',
  `vendor_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '供应商名称',
  `due_date` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '预计到货日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_information
-- ----------------------------

-- ----------------------------
-- Table structure for `pick_task`
-- ----------------------------
DROP TABLE IF EXISTS `pick_task`;
CREATE TABLE `pick_task` (
  `pick_task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `box_code` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '箱号',
  `pick_quantity` int(20) NOT NULL COMMENT '数量',
  `bill_out_detail_id` int(11) NOT NULL COMMENT 'ID',
  `pick_state` int(11) DEFAULT NULL COMMENT '1-待领，2-已领',
  `batch` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '批次',
  `sub_inventory_id` int(11) DEFAULT NULL COMMENT '子库',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `out_time` datetime DEFAULT NULL COMMENT '出库时间',
  PRIMARY KEY (`pick_task_id`) USING BTREE,
  KEY `bill_out_detail_id` (`bill_out_detail_id`) USING BTREE,
  KEY `pick_task_box_code` (`box_code`) USING BTREE,
  KEY `pick_task_pick_state` (`pick_state`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pick_task
-- ----------------------------

-- ----------------------------
-- Table structure for `process_record`
-- ----------------------------
DROP TABLE IF EXISTS `process_record`;
CREATE TABLE `process_record` (
  `process_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '处理记录',
  `item_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '物料编码',
  `batch` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '批次',
  `exp` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '失效日期',
  `sub_inventory_id` int(11) DEFAULT NULL COMMENT '子库',
  `flow_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '流程编号',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`process_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of process_record
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目序号',
  `project_code` varchar(50) DEFAULT NULL COMMENT '工程编码',
  `project_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0-默认 1-开工 2-下水  3-试航 4-完工',
  `memo` varchar(50) DEFAULT NULL,
  `item_master_id` int(11) DEFAULT NULL COMMENT '财务组织ID 货主ID',
  `project_type_id` int(11) DEFAULT NULL COMMENT '工程系列编号',
  `create_user_id` int(11) DEFAULT NULL,
  `expected_complete_time` varchar(25) DEFAULT NULL COMMENT '预计完成时间',
  `project_attribute_id` int(11) DEFAULT NULL COMMENT '工程属性',
  `address` int(11) DEFAULT NULL COMMENT '地址',
  `date_start` datetime DEFAULT NULL COMMENT '开工时间',
  `date_end` datetime DEFAULT NULL COMMENT '完工时间',
  `near_end_date` int(11) DEFAULT NULL COMMENT '临近完工提醒',
  `schedule` int(11) DEFAULT NULL COMMENT '进度百分比',
  `contact_user_id` int(11) DEFAULT NULL,
  `contact` varchar(25) DEFAULT NULL,
  `contact_info` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `limited` int(11) DEFAULT '0' COMMENT '0是否扣限额  1是  2否',
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `project_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `project_attribute`;
CREATE TABLE `project_attribute` (
  `project_attribute_id` int(11) NOT NULL,
  `project_attribute_name` varchar(255) DEFAULT NULL COMMENT '工程属性名称',
  `create_time` datetime DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `type` int(11) DEFAULT NULL COMMENT '1-通用工程，2-非通用工程 ,3-研发项目',
  PRIMARY KEY (`project_attribute_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of project_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `project_type`
-- ----------------------------
DROP TABLE IF EXISTS `project_type`;
CREATE TABLE `project_type` (
  `project_type_id` int(11) NOT NULL,
  `project_type_name` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`project_type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of project_type
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0 30 0/1 * * ? ', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME4', 'DEFAULT', '0 0 11/4 * * ? ', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME5', 'DEFAULT', '0 10 1 1/1 * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', null, 'com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720021636F6D2E646565722E776D732E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A636F6D2E646565722E776D732E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672795461736B74000A72794E6F506172616D7374000074000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', null, 'com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720021636F6D2E646565722E776D732E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A636F6D2E646565722E776D732E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000672795461736B7400087279506172616D73740002727974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', null, 'com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720021636F6D2E646565722E776D732E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A636F6D2E646565722E776D732E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016F0D6A7FE878707400007070707400013174000F3020333020302F31202A202A203F2074002AE88EB7E58F96454253E6A380E9AA8CE695B0E68DAEE5B9B6E59B9EE4BCA0E4BAA4E8B4A7E695B0E9878F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000374000672795461736B740012676574436865636B4F757446726F6D4542537074000131740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME4', 'DEFAULT', null, 'com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720021636F6D2E646565722E776D732E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A636F6D2E646565722E776D732E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016F9C9AF69878707400007070707400013174000F3020302031312F34202A202A203F2074001934E5B08FE697B6E58F91E4B880E6ACA14D4553E5B7A5E58D957372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000474000672795461736B74000D74696D6543616C63756C61746574000074000131740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME5', 'DEFAULT', null, 'com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720021636F6D2E646565722E776D732E71756172747A2E646F6D61696E2E5379734A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A636F6D2E646565722E776D732E636F6D6D6F6E2E636F72652E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001713A00ACD87870740043E69FA5E8AFA2E4B88DE59088E6A0BCE78AB6E68081E789A9E69699E698AFE590A6E8B685E697B62CE8B685E8BF87E8AEBEE5AE9AE697B6E997B4E58899E68AA5E8ADA67070707400013174000E30203130203120312F31202A203F740033E6A380E69FA5E4B88DE59088E6A0BCE789A9E69699E5AD98E582A8E697B6E997B4E698AFE590A6E8B685E697B6E68EA5E58FA37372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000574000672795461736B740020756E7175616C696669656453746F72616765537567676973684F76657264756574000074000133740001317800);

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'SD-20200130DTSQ1587279075547', '1587282503229', '15000');

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`) USING BTREE,
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', null, '1587279080000', '-1', '5', 'PAUSED', 'CRON', '1587279077000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', null, '1587279080000', '-1', '5', 'PAUSED', 'CRON', '1587279078000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', null, '1587281400000', '-1', '5', 'PAUSED', 'CRON', '1587279078000', '0', null, '-1', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME4', 'DEFAULT', 'TASK_CLASS_NAME4', 'DEFAULT', null, '1587279600000', '-1', '5', 'PAUSED', 'CRON', '1587279079000', '0', null, '-1', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME5', 'DEFAULT', 'TASK_CLASS_NAME5', 'DEFAULT', null, '1587316200000', '-1', '5', 'PAUSED', 'CRON', '1587279080000', '0', null, '2', '');

-- ----------------------------
-- Table structure for `request_id`
-- ----------------------------
DROP TABLE IF EXISTS `request_id`;
CREATE TABLE `request_id` (
  `auto_growing_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '请求Id辅助表',
  `request_id` int(11) DEFAULT NULL COMMENT 'request_id',
  `process_status` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '处理状态',
  `last_update_date` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新时间',
  `last_updated_by` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后更新人',
  `creation_date` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `item_id` int(11) DEFAULT NULL COMMENT '物料Id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `lot_number` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '批次',
  `po_header_id` int(11) DEFAULT NULL COMMENT '订单头Id',
  `po_line_id` int(11) DEFAULT NULL COMMENT '订单行Id',
  `po_line_location_id` int(11) DEFAULT NULL COMMENT '发运行Id',
  `po_distribution_id` int(11) DEFAULT NULL COMMENT '分配行Id',
  `receipt_date` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '接收日期',
  `id` int(11) DEFAULT NULL,
  `transaction_date` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '交货处理日期',
  `shipment_num` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '接收单号',
  `error_msg` varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '错误信息',
  `sub_inventory` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '子库',
  `locator_id` int(11) DEFAULT NULL COMMENT '子库Id',
  `organization_id` int(11) DEFAULT NULL COMMENT '库存组织',
  `transaction_id` int(11) DEFAULT NULL COMMENT '事务处理Id',
  `type` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '1-成功 2-失败需处理 3-失败无需处理 4-已处理',
  `transaction_type_id` varchar(11) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类型Id',
  `wip_entity_id` varchar(11) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '工单Id',
  `operation_seqnum` int(11) DEFAULT NULL COMMENT '工序号',
  `transaction_uom` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '单位',
  `trans_sub_inventory` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '目标子库',
  `trans_locator_id` int(11) DEFAULT NULL COMMENT '目标子库货位Id',
  `source_header_id` int(11) DEFAULT NULL COMMENT 'billInMasterId',
  `source_line_id` int(11) DEFAULT NULL COMMENT 'billInDetaillId',
  `trans_source_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '账户别名',
  `trans_source_id` int(11) DEFAULT NULL COMMENT '账户别名Id',
  `due_date` datetime DEFAULT NULL COMMENT '到货日期',
  PRIMARY KEY (`auto_growing_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of request_id
-- ----------------------------

-- ----------------------------
-- Table structure for `request_id_auto`
-- ----------------------------
DROP TABLE IF EXISTS `request_id_auto`;
CREATE TABLE `request_id_auto` (
  `auto_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自动递增id',
  `memo` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`auto_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of request_id_auto
-- ----------------------------

-- ----------------------------
-- Table structure for `server_visit_address`
-- ----------------------------
DROP TABLE IF EXISTS `server_visit_address`;
CREATE TABLE `server_visit_address` (
  `visit_address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '请求接口Id',
  `visit_address` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '服务地址',
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '服务地址描述',
  `account` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`visit_address_id`) USING BTREE,
  KEY `visit_address_id_PK` (`visit_address_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of server_visit_address
-- ----------------------------
INSERT INTO `server_visit_address` VALUES ('1', 'http://10.20.12.177:9091/wsservice/macWS?wsdl', 'WMS访问MES的地址(不可删除，只可修改ip地址与端口号)', null, null);
INSERT INTO `server_visit_address` VALUES ('2', 'http://localhost:8099/WMSMesWebService?wsdl', 'MES访问WMS的地址(不可删除，只可修改ip地址与端口号)', null, null);
INSERT INTO `server_visit_address` VALUES ('3', 'http://10.10.183.212:9007/esb/intergrateService', '访问EBS的地址(不可删除，只可修改ip地址与端口号)', null, null);
INSERT INTO `server_visit_address` VALUES ('4', 'http://10.10.90.193:8899/oauth/token?grant_type=client_credentials', '获取token的地址(不可删除，只可修改ip地址与端口号)', 'aps_client', 'Aps112233');
INSERT INTO `server_visit_address` VALUES ('5', 'http://10.10.90.205:9081/esb/intergrateService', '获取物料信息地址(不可删除，只可修改ip地址与端口号)', null, null);

-- ----------------------------
-- Table structure for `shelf_info`
-- ----------------------------
DROP TABLE IF EXISTS `shelf_info`;
CREATE TABLE `shelf_info` (
  `shelf_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货架ID',
  `area_id` int(11) DEFAULT NULL COMMENT '货区编码',
  `shelf_code` varchar(35) DEFAULT NULL COMMENT '货架编码',
  `shelf_name` varchar(20) DEFAULT NULL COMMENT '货架名',
  `shelf_row` int(11) DEFAULT NULL,
  `shelf_column` int(11) DEFAULT NULL,
  `memo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`shelf_id`) USING BTREE,
  KEY `shelf_info_area_id` (`area_id`) USING BTREE,
  KEY `shelf_info_shelf_id_PK` (`shelf_id`) USING BTREE,
  CONSTRAINT `shelf_info_ibfk_1` FOREIGN KEY (`area_id`) REFERENCES `area_info` (`area_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='货架设置';

-- ----------------------------
-- Records of shelf_info
-- ----------------------------
INSERT INTO `shelf_info` VALUES ('39', '248', '01', '1', '18', '31', '');
INSERT INTO `shelf_info` VALUES ('40', '248', '02', '2', '18', '31', '');

-- ----------------------------
-- Table structure for `sluggish_overdue`
-- ----------------------------
DROP TABLE IF EXISTS `sluggish_overdue`;
CREATE TABLE `sluggish_overdue` (
  `sluggish_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '呆滞过期报表',
  `item_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '物料编码',
  `batch` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '批次',
  `last_quantity` int(11) DEFAULT NULL COMMENT '上次申报数量',
  `quantity` int(11) DEFAULT NULL COMMENT '当前呆滞数量',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `exp` date DEFAULT NULL COMMENT '失效日期',
  `last_time` date DEFAULT NULL COMMENT '最后入库日期日期',
  `create_time` datetime DEFAULT NULL COMMENT '当前时间',
  `last_out_time` date DEFAULT NULL COMMENT '最后出库日期',
  `sluggish_type` int(2) DEFAULT NULL COMMENT '呆滞类型',
  `first_declare_time` date DEFAULT NULL COMMENT '第一次申报日期',
  `first_declare_quantity` int(11) DEFAULT NULL COMMENT '第一次申报数量',
  PRIMARY KEY (`sluggish_id`) USING BTREE,
  KEY `sluggish_item_code` (`item_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sluggish_overdue
-- ----------------------------
INSERT INTO `sluggish_overdue` VALUES ('2', '104125659', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `subinventory_transfer_record`
-- ----------------------------
DROP TABLE IF EXISTS `subinventory_transfer_record`;
CREATE TABLE `subinventory_transfer_record` (
  `transfer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '子库存转移记录',
  `box_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '箱号',
  `item_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '物料编码',
  `batch` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '批次',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '转移时间',
  `transfer_card_no` varchar(11) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '转移人卡号',
  `for_subinventory` int(11) DEFAULT NULL COMMENT '从子库存id',
  `to_subinventory` int(11) DEFAULT NULL COMMENT '转移到子库存id',
  `transfer_memo` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '转移原因',
  PRIMARY KEY (`transfer_id`) USING BTREE,
  KEY `transfer_id_PK` (`transfer_id`) USING BTREE,
  KEY `box_code` (`box_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of subinventory_transfer_record
-- ----------------------------

-- ----------------------------
-- Table structure for `sub_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `sub_inventory`;
CREATE TABLE `sub_inventory` (
  `sub_inventory_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '子库存表Id',
  `sub_inventory_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '子库名称',
  `sub_inventory_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '子库编码',
  `slotting` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '货位Id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `organization_id` int(11) DEFAULT NULL COMMENT '库存组织Id',
  PRIMARY KEY (`sub_inventory_id`) USING BTREE,
  KEY `sub_inventory_code` (`sub_inventory_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sub_inventory
-- ----------------------------
INSERT INTO `sub_inventory` VALUES ('1', '堆垛机库', '311', null, '2020-03-30 16:33:27', '3', '327');
INSERT INTO `sub_inventory` VALUES ('2', '基板一厂板材库', '311-104', null, '2020-01-17 14:51:44', '1', '308');
INSERT INTO `sub_inventory` VALUES ('3', '基板一厂板材过期库', '311-119', null, null, null, '308');
INSERT INTO `sub_inventory` VALUES ('4', '基板一厂板材延期库', '311-123', null, null, null, '308');
INSERT INTO `sub_inventory` VALUES ('5', '基板一厂不合格库', '311-124', null, null, null, '308');
INSERT INTO `sub_inventory` VALUES ('6', '基板一厂责任待确认材料库', '311-127', null, null, null, '308');
INSERT INTO `sub_inventory` VALUES ('7', '基板一厂预测备料库', '311-128', null, null, null, '308');

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `supplier_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `supplier_name` varchar(20) NOT NULL COMMENT '供应商名称',
  `supplier_code` varchar(255) DEFAULT NULL COMMENT '供应商编码',
  `vendor_id` int(20) DEFAULT NULL COMMENT 'EBS供应商ID',
  `link_man` varchar(11) DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`supplier_id`) USING BTREE,
  KEY `supplier_supplier_code` (`supplier_code`) USING BTREE,
  KEY `supplier_id_PK` (`supplier_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='供应商';

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '深圳市宝安区沙井伟林电子辅料经营部', '100356', '356', null, null, null, null);

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-12 21:32:11', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '南京大鹿智造科技有限公司', '0', '', '15888888888', '', '0', '0', 'admin', '2018-03-16 11:33:00', 'wxy', '2020-04-18 23:11:06');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '6号楼', '1', '', '', '', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-11-15 09:28:20');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-27 09:25:41');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-27 09:25:31');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('110', '101', '0,100,101', '仓管部门', '6', null, null, null, '0', '0', 'admin', '2019-11-15 09:29:43', '', null);
INSERT INTO `sys_dept` VALUES ('111', '101', '0,100,101', 'IQC', '7', null, null, null, '0', '0', 'admin', '2019-11-15 09:30:10', '', null);

-- ----------------------------
-- Table structure for `sys_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('18', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('19', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('20', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('21', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('22', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('23', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('24', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('25', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('26', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');

-- ----------------------------
-- Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('6', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('8', '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('9', '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');

-- ----------------------------
-- Table structure for `sys_job`
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(50) DEFAULT NULL COMMENT '方法参数',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', 'ryTask', '系统默认（无参）', 'ryNoParams', '', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('2', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES ('3', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, '0 30 0/1 * * ? ', '1', '1', '1', '', '2019-12-16 14:34:41', '', '2019-12-16 14:35:06', '');
INSERT INTO `sys_job` VALUES ('4', 'ryTask', '4小时发一次MES工单', 'timeCalculate', '', '0 0 11/4 * * ? ', '1', '1', '1', '', '2020-01-13 09:53:19', '', '2020-01-13 09:53:46', '');
INSERT INTO `sys_job` VALUES ('5', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', '', '0 10 1 1/1 * ?', '3', '1', '1', '', '2020-04-02 16:27:35', '', '2020-04-03 10:11:53', '查询不合格状态物料是否超时,超过设定时间则报警');

-- ----------------------------
-- Table structure for `sys_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(50) DEFAULT NULL COMMENT '方法参数',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES ('1', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：9毫秒', '1', 'java.lang.IllegalAccessException: Class com.deer.wms.quartz.util.JobInvokeUtil can not access a member of class com.deer.wms.quartz.task.RyTask with modifiers \"private\"\r\n	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)\r\n	at java.lang.reflect.AccessibleObject.slowCheckMemberAccess(AccessibleObject.java:296)\r\n	at java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:288)\r\n	at java.lang.reflect.Method.invoke(Method.java:491)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2019-12-16 15:08:18');
INSERT INTO `sys_job_log` VALUES ('2', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：3547毫秒', '0', '', '2019-12-16 15:11:14');
INSERT INTO `sys_job_log` VALUES ('3', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：5422毫秒', '0', '', '2019-12-16 15:20:39');
INSERT INTO `sys_job_log` VALUES ('4', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：36682毫秒', '0', '', '2019-12-26 17:30:40');
INSERT INTO `sys_job_log` VALUES ('5', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：114133毫秒', '0', '', '2019-12-26 17:39:33');
INSERT INTO `sys_job_log` VALUES ('6', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：597毫秒', '0', '', '2019-12-26 17:40:23');
INSERT INTO `sys_job_log` VALUES ('7', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：40350毫秒', '0', '', '2019-12-26 17:41:31');
INSERT INTO `sys_job_log` VALUES ('8', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：135462毫秒', '0', '', '2019-12-26 17:46:46');
INSERT INTO `sys_job_log` VALUES ('9', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：105748毫秒', '0', '', '2019-12-26 17:50:17');
INSERT INTO `sys_job_log` VALUES ('10', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：19768毫秒', '0', '', '2019-12-26 17:51:16');
INSERT INTO `sys_job_log` VALUES ('11', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：359165毫秒', '0', '', '2019-12-26 17:57:20');
INSERT INTO `sys_job_log` VALUES ('12', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：1789毫秒', '0', '', '2019-12-26 17:58:45');
INSERT INTO `sys_job_log` VALUES ('13', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：449196毫秒', '0', '', '2019-12-26 18:06:28');
INSERT INTO `sys_job_log` VALUES ('14', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：2770毫秒', '0', '', '2019-12-26 18:07:30');
INSERT INTO `sys_job_log` VALUES ('15', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：76222毫秒', '0', '', '2019-12-26 18:08:57');
INSERT INTO `sys_job_log` VALUES ('16', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：97633毫秒', '0', '', '2019-12-26 18:11:57');
INSERT INTO `sys_job_log` VALUES ('17', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：591毫秒', '0', '', '2019-12-26 18:12:54');
INSERT INTO `sys_job_log` VALUES ('18', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：144015毫秒', '0', '', '2019-12-26 18:15:51');
INSERT INTO `sys_job_log` VALUES ('19', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：11665毫秒', '0', '', '2019-12-28 14:38:08');
INSERT INTO `sys_job_log` VALUES ('20', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：69241毫秒', '0', '', '2019-12-28 14:40:37');
INSERT INTO `sys_job_log` VALUES ('21', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：94808毫秒', '0', '', '2019-12-28 14:43:49');
INSERT INTO `sys_job_log` VALUES ('22', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：154337毫秒', '0', '', '2019-12-28 14:47:15');
INSERT INTO `sys_job_log` VALUES ('23', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：21218毫秒', '0', '', '2019-12-28 14:54:40');
INSERT INTO `sys_job_log` VALUES ('24', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：90608毫秒', '0', '', '2019-12-28 14:56:35');
INSERT INTO `sys_job_log` VALUES ('25', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：413337毫秒', '0', '', '2019-12-28 15:06:05');
INSERT INTO `sys_job_log` VALUES ('26', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：61978毫秒', '0', '', '2019-12-28 15:24:56');
INSERT INTO `sys_job_log` VALUES ('27', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：268906毫秒', '0', '', '2019-12-28 15:29:29');
INSERT INTO `sys_job_log` VALUES ('28', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：14012毫秒', '0', '', '2019-12-28 15:37:22');
INSERT INTO `sys_job_log` VALUES ('29', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：35954毫秒', '0', '', '2019-12-28 15:38:21');
INSERT INTO `sys_job_log` VALUES ('30', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：52035毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 通用错误码\r\n	at com.deer.wms.quartz.task.RyTask.getCheckOutFromEBS(RyTask.java:208)\r\n	... 10 more\r\n', '2019-12-28 15:43:40');
INSERT INTO `sys_job_log` VALUES ('31', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：49660毫秒', '0', '', '2020-01-13 17:00:04');
INSERT INTO `sys_job_log` VALUES ('32', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：93365毫秒', '0', '', '2020-01-13 17:01:55');
INSERT INTO `sys_job_log` VALUES ('33', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：144243毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 通用错误码\r\n	at com.deer.wms.quartz.task.RyTask.getCheckOutFromEBS(RyTask.java:242)\r\n	... 10 more\r\n', '2020-01-13 17:04:36');
INSERT INTO `sys_job_log` VALUES ('34', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：129017毫秒', '0', '', '2020-01-13 17:08:41');
INSERT INTO `sys_job_log` VALUES ('35', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：480毫秒', '0', '', '2020-01-13 17:08:57');
INSERT INTO `sys_job_log` VALUES ('36', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：376毫秒', '0', '', '2020-01-13 17:09:29');
INSERT INTO `sys_job_log` VALUES ('37', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：22950毫秒', '0', '', '2020-01-13 17:10:08');
INSERT INTO `sys_job_log` VALUES ('38', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：27023毫秒', '0', '', '2020-01-13 17:11:04');
INSERT INTO `sys_job_log` VALUES ('39', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：150977毫秒', '0', '', '2020-01-13 17:13:38');
INSERT INTO `sys_job_log` VALUES ('40', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：144890毫秒', '0', '', '2020-01-13 17:17:17');
INSERT INTO `sys_job_log` VALUES ('41', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：413毫秒', '0', '', '2020-01-13 17:31:15');
INSERT INTO `sys_job_log` VALUES ('42', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：6861毫秒', '0', '', '2020-01-13 17:31:58');
INSERT INTO `sys_job_log` VALUES ('43', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：241612毫秒', '0', '', '2020-01-13 17:42:21');
INSERT INTO `sys_job_log` VALUES ('44', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：63795毫秒', '0', '', '2020-01-13 17:46:39');
INSERT INTO `sys_job_log` VALUES ('45', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：728332毫秒', '0', '', '2020-01-13 17:59:00');
INSERT INTO `sys_job_log` VALUES ('46', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：4555毫秒', '0', '', '2020-01-13 18:08:53');
INSERT INTO `sys_job_log` VALUES ('47', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：399毫秒', '0', '', '2020-01-13 18:10:04');
INSERT INTO `sys_job_log` VALUES ('48', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：29081毫秒', '0', '', '2020-01-13 18:10:48');
INSERT INTO `sys_job_log` VALUES ('49', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：26236毫秒', '0', '', '2020-01-13 18:11:37');
INSERT INTO `sys_job_log` VALUES ('50', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：80727毫秒', '0', '', '2020-01-13 18:40:16');
INSERT INTO `sys_job_log` VALUES ('51', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：9880毫秒', '0', '', '2020-01-13 19:01:17');
INSERT INTO `sys_job_log` VALUES ('52', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：42178毫秒', '0', '', '2020-01-13 19:02:09');
INSERT INTO `sys_job_log` VALUES ('53', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：93毫秒', '0', '', '2020-01-14 11:39:01');
INSERT INTO `sys_job_log` VALUES ('54', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：269毫秒', '0', '', '2020-01-14 13:14:20');
INSERT INTO `sys_job_log` VALUES ('55', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：61毫秒', '0', '', '2020-01-14 14:07:54');
INSERT INTO `sys_job_log` VALUES ('56', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：217毫秒', '0', '', '2020-01-14 14:21:43');
INSERT INTO `sys_job_log` VALUES ('57', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：72毫秒', '0', '', '2020-01-14 14:37:58');
INSERT INTO `sys_job_log` VALUES ('58', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：56毫秒', '0', '', '2020-01-14 14:45:20');
INSERT INTO `sys_job_log` VALUES ('59', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：218毫秒', '0', '', '2020-01-14 14:59:11');
INSERT INTO `sys_job_log` VALUES ('60', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：169毫秒', '0', '', '2020-01-14 16:12:11');
INSERT INTO `sys_job_log` VALUES ('61', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：192毫秒', '0', '', '2020-01-14 16:31:33');
INSERT INTO `sys_job_log` VALUES ('62', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：8538毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 通用错误码\r\n	at com.deer.wms.quartz.task.RyTask.getCheckOutFromEBS(RyTask.java:257)\r\n	... 10 more\r\n', '2020-01-17 18:04:20');
INSERT INTO `sys_job_log` VALUES ('63', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：50123毫秒', '0', '', '2020-01-17 18:10:26');
INSERT INTO `sys_job_log` VALUES ('64', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：60753毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 通用错误码\r\n	at com.deer.wms.quartz.task.RyTask.getCheckOutFromEBS(RyTask.java:257)\r\n	... 10 more\r\n', '2020-01-17 18:11:47');
INSERT INTO `sys_job_log` VALUES ('65', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：68812毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 通用错误码\r\n	at com.deer.wms.quartz.task.RyTask.getCheckOutFromEBS(RyTask.java:257)\r\n	... 10 more\r\n', '2020-01-17 18:13:32');
INSERT INTO `sys_job_log` VALUES ('66', 'ryTask', '获取EBS检验数据并回传交货数量', 'getCheckOutFromEBS', null, 'ryTask 总共耗时：326741毫秒', '0', '', '2020-01-17 18:21:58');
INSERT INTO `sys_job_log` VALUES ('67', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：6303毫秒', '0', '', '2020-01-19 14:53:49');
INSERT INTO `sys_job_log` VALUES ('68', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：18681毫秒', '0', '', '2020-01-19 15:03:54');
INSERT INTO `sys_job_log` VALUES ('69', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：4078毫秒', '0', '', '2020-01-19 15:05:58');
INSERT INTO `sys_job_log` VALUES ('70', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：2730毫秒', '0', '', '2020-01-19 15:14:49');
INSERT INTO `sys_job_log` VALUES ('71', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：4667毫秒', '0', '', '2020-01-19 16:15:40');
INSERT INTO `sys_job_log` VALUES ('72', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：2579毫秒', '0', '', '2020-01-19 16:24:25');
INSERT INTO `sys_job_log` VALUES ('73', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：3199毫秒', '0', '', '2020-01-19 16:36:39');
INSERT INTO `sys_job_log` VALUES ('74', 'ryTask', '4小时发一次MES工单', 'timeCalculate', null, 'ryTask 总共耗时：3013毫秒', '0', '', '2020-01-19 17:31:06');
INSERT INTO `sys_job_log` VALUES ('75', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：27931毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 服务器内部错误，请联系管理员\r\n	at com.deer.wms.quartz.task.RyTask.unqualifiedStorageSuggishOverdue(RyTask.java:337)\r\n	... 10 more\r\n', '2020-04-03 10:12:26');
INSERT INTO `sys_job_log` VALUES ('76', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：28521毫秒', '0', '', '2020-04-03 10:14:27');
INSERT INTO `sys_job_log` VALUES ('77', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：34841毫秒', '0', '', '2020-04-03 10:15:38');
INSERT INTO `sys_job_log` VALUES ('78', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：10997毫秒', '0', '', '2020-04-03 10:16:22');
INSERT INTO `sys_job_log` VALUES ('79', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：113毫秒', '0', '', '2020-04-03 10:54:53');
INSERT INTO `sys_job_log` VALUES ('80', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：90毫秒', '0', '', '2020-04-03 11:00:37');
INSERT INTO `sys_job_log` VALUES ('81', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：40毫秒', '0', '', '2020-04-03 11:01:34');
INSERT INTO `sys_job_log` VALUES ('82', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：196毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeSpringBean(JobInvokeUtil.java:49)\r\n	at com.deer.wms.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:27)\r\n	at com.deer.wms.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.deer.wms.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:44)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: com.deer.wms.common.exception.ServiceException: 服务器内部错误，请联系管理员\r\n	at com.deer.wms.quartz.task.RyTask.unqualifiedStorageSuggishOverdue(RyTask.java:344)\r\n	... 10 more\r\n', '2020-04-03 11:15:59');
INSERT INTO `sys_job_log` VALUES ('83', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：16307毫秒', '0', '', '2020-04-03 11:20:46');
INSERT INTO `sys_job_log` VALUES ('84', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：106毫秒', '0', '', '2020-04-03 11:40:13');
INSERT INTO `sys_job_log` VALUES ('85', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：63毫秒', '0', '', '2020-04-03 11:40:31');
INSERT INTO `sys_job_log` VALUES ('86', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：84毫秒', '0', '', '2020-04-03 15:18:19');
INSERT INTO `sys_job_log` VALUES ('87', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：98毫秒', '0', '', '2020-04-03 16:51:34');
INSERT INTO `sys_job_log` VALUES ('88', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：39毫秒', '0', '', '2020-04-03 16:53:51');
INSERT INTO `sys_job_log` VALUES ('89', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：43毫秒', '0', '', '2020-04-03 16:53:57');
INSERT INTO `sys_job_log` VALUES ('90', 'ryTask', '检查不合格物料存储时间是否超时接口', 'unqualifiedStorageSuggishOverdue', null, 'ryTask 总共耗时：85毫秒', '0', '', '2020-04-03 16:58:49');

-- ----------------------------
-- Table structure for `sys_logininfor`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4289 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES ('4260', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '1', '密码输入错误5次，帐户锁定10分钟', '2020-04-18 23:03:18');
INSERT INTO `sys_logininfor` VALUES ('4261', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '1', '密码输入错误5次，帐户锁定10分钟', '2020-04-18 23:03:29');
INSERT INTO `sys_logininfor` VALUES ('4262', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '1', '密码输入错误5次，帐户锁定10分钟', '2020-04-18 23:04:20');
INSERT INTO `sys_logininfor` VALUES ('4263', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-18 23:06:39');
INSERT INTO `sys_logininfor` VALUES ('4264', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-18 23:06:46');
INSERT INTO `sys_logininfor` VALUES ('4265', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-18 23:07:20');
INSERT INTO `sys_logininfor` VALUES ('4266', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-18 23:10:11');
INSERT INTO `sys_logininfor` VALUES ('4267', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-18 23:12:17');
INSERT INTO `sys_logininfor` VALUES ('4268', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '1', '密码输入错误1次', '2020-04-19 01:19:31');
INSERT INTO `sys_logininfor` VALUES ('4269', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-19 01:19:39');
INSERT INTO `sys_logininfor` VALUES ('4270', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-19 13:34:11');
INSERT INTO `sys_logininfor` VALUES ('4271', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-19 13:34:15');
INSERT INTO `sys_logininfor` VALUES ('4272', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 13:38:47');
INSERT INTO `sys_logininfor` VALUES ('4273', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 13:38:53');
INSERT INTO `sys_logininfor` VALUES ('4274', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-19 13:39:00');
INSERT INTO `sys_logininfor` VALUES ('4275', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 13:39:12');
INSERT INTO `sys_logininfor` VALUES ('4276', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 13:39:17');
INSERT INTO `sys_logininfor` VALUES ('4277', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '退出成功', '2020-04-19 13:39:31');
INSERT INTO `sys_logininfor` VALUES ('4278', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:04:44');
INSERT INTO `sys_logininfor` VALUES ('4279', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '退出成功', '2020-04-19 14:04:46');
INSERT INTO `sys_logininfor` VALUES ('4280', 'wxy', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', '0', '登录成功', '2020-04-19 14:17:56');
INSERT INTO `sys_logininfor` VALUES ('4281', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:29:29');
INSERT INTO `sys_logininfor` VALUES ('4282', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:29:35');
INSERT INTO `sys_logininfor` VALUES ('4283', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:44:47');
INSERT INTO `sys_logininfor` VALUES ('4284', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:44:53');
INSERT INTO `sys_logininfor` VALUES ('4285', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:47:01');
INSERT INTO `sys_logininfor` VALUES ('4286', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:47:10');
INSERT INTO `sys_logininfor` VALUES ('4287', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:52:01');
INSERT INTO `sys_logininfor` VALUES ('4288', 'wxy', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', '0', '登录成功', '2020-04-19 14:52:07');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2093 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '4', '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-09 14:02:42', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '6', '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-09 14:02:54', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '5', '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'admin', '2019-07-09 14:02:48', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '3', '/monitor/server', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', '/tool/swagger', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '#', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '#', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '#', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '#', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '#', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '#', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '详细信息', '500', '3', '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '任务详细', '110', '6', '#', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '任务导出', '110', '7', '#', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1056', '生成查询', '114', '1', '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1057', '生成代码', '114', '2', '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('2001', '仓库信息', '1', '1', '#', 'M', '0', null, 'fa fa-archive', 'admin', '2019-05-08 11:11:28', '', null, '');
INSERT INTO `sys_menu` VALUES ('2002', '仓库设置', '2001', '1', '/system/wareInfo', 'C', '0', 'system:wareInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-05-08 11:21:56', '仓库设置菜单');
INSERT INTO `sys_menu` VALUES ('2003', '仓库设置查询', '2002', '1', '#', 'F', '0', 'system:wareInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2004', '仓库设置新增', '2002', '2', '#', 'F', '0', 'system:wareInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2005', '仓库设置修改', '2002', '3', '#', 'F', '0', 'system:wareInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2006', '仓库设置删除', '2002', '4', '#', 'F', '0', 'system:wareInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2007', '货区设置', '2001', '1', '/system/areaInfo', 'C', '0', 'system:areaInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-05-08 14:44:23', '货区设置菜单');
INSERT INTO `sys_menu` VALUES ('2008', '货区设置查询', '2007', '1', '#', 'F', '0', 'system:areaInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2009', '货区设置新增', '2007', '2', '#', 'F', '0', 'system:areaInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2010', '货区设置修改', '2007', '3', '#', 'F', '0', 'system:areaInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2011', '货区设置删除', '2007', '4', '#', 'F', '0', 'system:areaInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2012', '货位设置', '2001', '4', '/system/cellInfo', 'C', '0', 'system:cellInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-05-08 14:44:43', '货位设置菜单');
INSERT INTO `sys_menu` VALUES ('2013', '货位设置查询', '2012', '1', '#', 'F', '0', 'system:cellInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2014', '货位设置新增', '2012', '2', '#', 'F', '0', 'system:cellInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2015', '货位设置修改', '2012', '3', '#', 'F', '0', 'system:cellInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2016', '货位设置删除', '2012', '4', '#', 'F', '0', 'system:cellInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2017', '货架设置', '2001', '3', '/system/shelfInfo', 'C', '0', 'system:shelfInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-05-08 14:44:58', '货架设置菜单');
INSERT INTO `sys_menu` VALUES ('2018', '货架设置查询', '2017', '1', '#', 'F', '0', 'system:shelfInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2019', '货架设置新增', '2017', '2', '#', 'F', '0', 'system:shelfInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2020', '货架设置修改', '2017', '3', '#', 'F', '0', 'system:shelfInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2021', '货架设置删除', '2017', '4', '#', 'F', '0', 'system:shelfInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2022', '物料', '2032', '2', '/system/itemInfo', 'C', '0', 'system:itemInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-06-26 09:27:34', '物料菜单');
INSERT INTO `sys_menu` VALUES ('2023', '物料查询', '2022', '1', '#', 'F', '0', 'system:itemInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2024', '物料新增', '2022', '2', '#', 'F', '0', 'system:itemInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2025', '物料修改', '2022', '3', '#', 'F', '0', 'system:itemInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2026', '物料删除', '2022', '4', '#', 'F', '0', 'system:itemInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2027', '物料分类', '2032', '1', '/system/itemType', 'C', '1', 'system:itemType:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-12-02 17:03:50', '物料分类菜单');
INSERT INTO `sys_menu` VALUES ('2028', '物料分类查询', '2027', '1', '#', 'F', '0', 'system:itemType:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2029', '物料分类新增', '2027', '2', '#', 'F', '0', 'system:itemType:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2030', '物料分类修改', '2027', '3', '#', 'F', '0', 'system:itemType:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2031', '物料分类删除', '2027', '4', '#', 'F', '0', 'system:itemType:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2032', '物料信息', '1', '1', '#', 'M', '0', '', 'fa fa-cubes', 'admin', '2019-05-08 15:13:21', 'admin', '2019-05-08 15:14:04', '');
INSERT INTO `sys_menu` VALUES ('2033', '出入库口', '2001', '5', '/system/door', 'C', '0', 'system:door:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-05-12 17:12:26', '出入库口菜单');
INSERT INTO `sys_menu` VALUES ('2034', '出入库口查询', '2033', '1', '#', 'F', '0', 'system:door:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2035', '出入库口新增', '2033', '2', '#', 'F', '0', 'system:door:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2036', '出入库口修改', '2033', '3', '#', 'F', '0', 'system:door:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2037', '出入库口删除', '2033', '4', '#', 'F', '0', 'system:door:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2038', '入库管理', '0', '1', '#', 'M', '0', '', 'fa fa-calculator', 'admin', '2019-05-13 11:05:19', 'admin', '2019-07-09 14:04:18', '');
INSERT INTO `sys_menu` VALUES ('2039', '入库单', '2038', '1', '/in/billInMaster/page', 'C', '0', 'in:billInMaster:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-07-12 12:09:30', '入库单菜单');
INSERT INTO `sys_menu` VALUES ('2040', '入库单查询', '2039', '1', '#', 'F', '0', 'in:billInMaster:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2041', '入库单新增', '2039', '2', '#', 'F', '0', 'in:billInMaster:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2042', '入库单修改', '2039', '3', '#', 'F', '0', 'in:billInMaster:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2043', '入库单删除', '2039', '4', '#', 'F', '0', 'in:billInMaster:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2044', '容器', '2001', '6', '/boxInfo', 'C', '0', 'system:boxInfo:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-06-24 18:41:55', '容器菜单');
INSERT INTO `sys_menu` VALUES ('2045', '容器查询', '2044', '1', '#', 'F', '0', 'system:boxInfo:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2046', '容器新增', '2044', '2', '#', 'F', '0', 'system:boxInfo:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2047', '容器修改', '2044', '3', '#', 'F', '0', 'system:boxInfo:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2048', '容器删除', '2044', '4', '#', 'F', '0', 'system:boxInfo:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2049', '任务查询', '0', '3', '#', 'M', '0', '', 'fa fa-plane', 'admin', '2019-06-02 20:19:39', 'admin', '2019-11-13 11:09:52', '');
INSERT INTO `sys_menu` VALUES ('2050', '供应商', '2001', '6', '/system/supplier', 'C', '0', 'system:supplier:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-06-25 09:24:54', '供应商菜单');
INSERT INTO `sys_menu` VALUES ('2051', '供应商查询', '2050', '1', '#', 'F', '0', 'system:supplier:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2052', '供应商新增', '2050', '2', '#', 'F', '0', 'system:supplier:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2053', '供应商修改', '2050', '3', '#', 'F', '0', 'system:supplier:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2054', '供应商删除', '2050', '4', '#', 'F', '0', 'system:supplier:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2056', '出库管理', '0', '2', '#', 'M', '0', '', 'fa fa-calculator', 'admin', '2019-07-09 13:55:45', 'admin', '2019-07-09 14:03:11', '');
INSERT INTO `sys_menu` VALUES ('2057', '出库单', '2056', '1', '/out/billOutMaster/page', 'C', '0', 'out:billOutMaster:view', '#', 'admin', '2019-07-12 10:05:28', 'admin', '2019-07-15 10:10:07', '');
INSERT INTO `sys_menu` VALUES ('2058', '报表查询', '0', '1', '#', 'M', '0', null, 'fa fa-bar-chart-o', 'admin', '2019-07-25 17:17:17', '', null, '');
INSERT INTO `sys_menu` VALUES ('2059', '库存明细', '2058', '1', '/in/boxItem/page', 'C', '0', 'in:boxItem:view', '#', 'admin', '2019-07-25 17:20:36', 'admin', '2019-07-25 17:22:53', '');
INSERT INTO `sys_menu` VALUES ('2060', '操作员管理', '1', '2', '/system/operator', 'C', '0', 'system:operator:view', '#', 'admin', '2019-09-03 09:44:21', 'admin', '2019-09-03 09:55:27', '');
INSERT INTO `sys_menu` VALUES ('2061', '库存管理', '0', '3', '#', 'M', '0', null, 'fa fa-building', 'admin', '2019-09-18 15:47:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2062', '库存管理', '2061', '2', '/in/boxItem/inventoryManagePage', 'C', '0', 'in:inventoryManage:view', '#', 'admin', '2019-09-18 15:48:48', 'admin', '2019-09-18 15:51:35', '');
INSERT INTO `sys_menu` VALUES ('2063', '服务地址', '1', '2', '/serverVisitAddress', 'C', '0', 'serverVisitAddress:view', '#', 'admin', '2019-09-26 10:19:12', 'admin', '2019-10-23 12:56:51', '');
INSERT INTO `sys_menu` VALUES ('2064', '编辑', '2063', '1', '#', 'F', '0', 'serverVisitAddress:edit', '#', 'admin', '2019-09-26 10:37:59', '', null, '');
INSERT INTO `sys_menu` VALUES ('2065', '计划时间', '1', '2', '/workerOrderIssueTime', 'C', '0', 'workerOrderIssueTime:view', '#', 'admin', '2019-09-28 16:23:02', 'admin', '2019-11-21 11:18:36', '');
INSERT INTO `sys_menu` VALUES ('2066', '编辑', '2065', '1', '#', 'F', '0', 'workerOrderIssueTime:edit', '#', 'admin', '2019-09-28 16:26:42', '', null, '');
INSERT INTO `sys_menu` VALUES ('2067', '账户别名', '1', '2', '/accountAlias', 'C', '0', 'accountAlias:view', '#', 'admin', '2019-10-29 14:42:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2069', '检验合格', '2062', '2', '#', 'F', '0', 'in:boxItem:checkOut', '#', 'wxy', '2019-10-30 17:07:06', '', null, '');
INSERT INTO `sys_menu` VALUES ('2070', '子库管理', '1', '2', '/subInventory', 'C', '0', 'subInventory:view', 'fa fa-gears', 'wxy', '2019-11-09 14:53:32', 'wxy', '2019-11-09 14:55:33', '');
INSERT INTO `sys_menu` VALUES ('2071', '任务管理', '2049', '1', '/in/taskInfo', 'C', '0', 'in:task:view', '#', 'admin', '2019-11-13 13:19:55', 'admin', '2019-11-13 13:44:11', '');
INSERT INTO `sys_menu` VALUES ('2072', '无按钮', '2060', '1', '#', 'F', '0', '', '#', 'admin', '2019-11-14 11:13:39', 'admin', '2019-11-14 11:14:41', '');
INSERT INTO `sys_menu` VALUES ('2073', '入库操作台', '2060', '2', '#', 'F', '0', 'system:operator:floor', '#', 'admin', '2019-11-14 11:14:20', 'wxy', '2019-11-14 11:29:52', '');
INSERT INTO `sys_menu` VALUES ('2075', '呆滞过期报表', '2058', '2', '/sluggishOverdue', 'C', '0', 'sluggishOverdue:view', '#', 'wxy', '2019-11-25 15:05:15', 'admin', '2020-01-16 16:41:50', '');
INSERT INTO `sys_menu` VALUES ('2076', '调用EBS接口记录', '2061', '0', '/requestId', 'C', '0', 'requestId:view', '#', 'admin', '2020-01-09 09:39:15', '', null, '');
INSERT INTO `sys_menu` VALUES ('2077', '默认', '2076', '1', '#', 'F', '0', null, '#', 'admin', '2020-01-09 09:57:41', '', null, '');
INSERT INTO `sys_menu` VALUES ('2078', '刷新', '2076', '2', '#', 'F', '0', 'requestId:refresh', '#', 'admin', '2020-01-09 10:00:07', '', null, '');
INSERT INTO `sys_menu` VALUES ('2081', '处理失败数据推送至EBS', '2076', '2', '#', 'F', '0', 'requestId:sendEbs', '#', 'admin', '2020-01-16 09:43:40', '', null, '');
INSERT INTO `sys_menu` VALUES ('2082', '转库记录', '2058', '3', '/subinventoryTransferRecord', 'C', '0', 'subinventoryTransferRecord:view', '#', 'admin', '2020-01-16 16:41:39', '', null, '');
INSERT INTO `sys_menu` VALUES ('2083', '默认选中按钮', '2082', '1', '#', 'F', '0', null, '#', 'admin', '2020-01-16 16:42:12', '', null, '');
INSERT INTO `sys_menu` VALUES ('2084', '默认选中按钮', '2075', '1', '#', 'F', '0', null, '#', 'admin', '2020-01-16 16:42:29', '', null, '');
INSERT INTO `sys_menu` VALUES ('2085', '入库记录', '2058', '4', '/billInRecord', 'C', '0', 'billInRecord:view', '#', 'admin', '2020-03-03 09:06:47', 'admin', '2020-03-03 09:07:25', '');
INSERT INTO `sys_menu` VALUES ('2086', '出库记录', '2058', '5', '/pickTask', 'C', '0', 'pickTask:view', '#', 'wxy', '2020-03-04 15:38:37', '', null, '');
INSERT INTO `sys_menu` VALUES ('2087', '添加操作员', '2060', '3', '#', 'F', '0', 'system:operator:add', '#', 'wxy', '2020-03-05 09:03:26', '', null, '');
INSERT INTO `sys_menu` VALUES ('2088', '导出操作员', '2060', '4', '#', 'F', '0', 'system:operator:export', '#', 'wxy', '2020-03-05 09:04:32', '', null, '');
INSERT INTO `sys_menu` VALUES ('2089', '修改操作员', '2060', '5', '#', 'F', '0', 'system:operator:edit', '#', 'wxy', '2020-03-05 09:05:29', '', null, '');
INSERT INTO `sys_menu` VALUES ('2090', '删除操作员', '2060', '6', '#', 'F', '0', 'system:operator:remove', '#', 'wxy', '2020-03-05 09:05:54', '', null, '');
INSERT INTO `sys_menu` VALUES ('2091', '编辑按钮', '2070', '2', '#', 'F', '0', 'subInventory:edit', '#', 'wxy', '2020-03-30 16:30:50', '', null, '');
INSERT INTO `sys_menu` VALUES ('2092', '默认按钮', '2070', '1', '#', 'F', '0', null, '#', 'wxy', '2020-03-30 16:31:14', '', null, '');

-- ----------------------------
-- Table structure for `sys_notice`
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1103 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('100', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"仓库信息\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-university\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-07 14:10:51');
INSERT INTO `sys_oper_log` VALUES ('101', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/sys_user_role', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-07 14:23:36');
INSERT INTO `sys_oper_log` VALUES ('102', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/sys_user_role', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-07 14:23:58');
INSERT INTO `sys_oper_log` VALUES ('103', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"仓库信息\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-archive\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 11:11:28');
INSERT INTO `sys_oper_log` VALUES ('104', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/ware_info', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-08 11:19:55');
INSERT INTO `sys_oper_log` VALUES ('105', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2002\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"仓库设置\" ],\r\n  \"url\" : [ \"/system/wareInfo\" ],\r\n  \"perms\" : [ \"system:wareInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 11:21:57');
INSERT INTO `sys_oper_log` VALUES ('106', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.batchGenCode()', '1', 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"cell_info,shelf_info,area_info,ware_info\" ]\r\n}', '0', null, '2019-05-08 14:22:26');
INSERT INTO `sys_oper_log` VALUES ('107', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2007\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"货区设置\" ],\r\n  \"url\" : [ \"/system/areaInfo\" ],\r\n  \"perms\" : [ \"system:areaInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 14:44:23');
INSERT INTO `sys_oper_log` VALUES ('108', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2012\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"货位设置\" ],\r\n  \"url\" : [ \"/system/cellInfo\" ],\r\n  \"perms\" : [ \"system:cellInfo:view\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 14:44:43');
INSERT INTO `sys_oper_log` VALUES ('109', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2017\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"货架设置\" ],\r\n  \"url\" : [ \"/system/shelfInfo\" ],\r\n  \"perms\" : [ \"system:shelfInfo:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 14:44:58');
INSERT INTO `sys_oper_log` VALUES ('110', '仓库设置', '1', 'com.ruoyi.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-05-08 14:45:37');
INSERT INTO `sys_oper_log` VALUES ('111', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.batchGenCode()', '1', 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"item_info,item_type\" ]\r\n}', '0', null, '2019-05-08 14:55:10');
INSERT INTO `sys_oper_log` VALUES ('112', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"物料信息\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-cubes\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 15:13:21');
INSERT INTO `sys_oper_log` VALUES ('113', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2032\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"物料信息\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"32\" ],\r\n  \"icon\" : [ \"fa fa-cubes\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 15:13:40');
INSERT INTO `sys_oper_log` VALUES ('114', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2032\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"物料信息\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-cubes\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 15:14:04');
INSERT INTO `sys_oper_log` VALUES ('115', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2022\" ],\r\n  \"parentId\" : [ \"2032\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"物料\" ],\r\n  \"url\" : [ \"/system/itemInfo\" ],\r\n  \"perms\" : [ \"system:itemInfo:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 15:14:25');
INSERT INTO `sys_oper_log` VALUES ('116', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2027\" ],\r\n  \"parentId\" : [ \"2032\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"物料分类\" ],\r\n  \"url\" : [ \"/system/itemType\" ],\r\n  \"perms\" : [ \"system:itemType:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-08 15:14:44');
INSERT INTO `sys_oper_log` VALUES ('117', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.authDataScopeSave()', '1', 'admin', '研发部门', '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', '0', null, '2019-05-08 16:16:41');
INSERT INTO `sys_oper_log` VALUES ('118', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.authDataScopeSave()', '1', 'admin', '研发部门', '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"dataScope\" : [ \"2\" ],\r\n  \"deptIds\" : [ \"100,101,105\" ]\r\n}', '0', null, '2019-05-08 16:16:58');
INSERT INTO `sys_oper_log` VALUES ('119', '角色管理', '1', 'com.ruoyi.web.controller.system.SysRoleController.addSave()', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"2323\" ],\r\n  \"roleKey\" : [ \"23\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"2323\" ],\r\n  \"menuIds\" : [ \"1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2032,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,3,113,114,1056,1057,115,2000\" ]\r\n}', '0', null, '2019-05-08 16:19:01');
INSERT INTO `sys_oper_log` VALUES ('120', '物料', '1', 'com.ruoyi.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"12\" ],\r\n  \"itemName\" : [ \"12\" ],\r\n  \"spec\" : [ \"12\" ],\r\n  \"model\" : [ \"12\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '0', null, '2019-05-08 18:24:47');
INSERT INTO `sys_oper_log` VALUES ('121', '物料', '3', 'com.ruoyi.web.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"null\" ]\r\n}', '0', null, '2019-05-08 18:24:59');
INSERT INTO `sys_oper_log` VALUES ('122', '物料', '3', 'com.ruoyi.web.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"null\" ]\r\n}', '0', null, '2019-05-08 18:25:04');
INSERT INTO `sys_oper_log` VALUES ('123', '货区设置', '1', 'com.ruoyi.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"12\" ],\r\n  \"areaName\" : [ \"12\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-05-08 20:04:05');
INSERT INTO `sys_oper_log` VALUES ('124', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/door', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-12 17:09:30');
INSERT INTO `sys_oper_log` VALUES ('125', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2033\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出入库口\" ],\r\n  \"url\" : [ \"/system/door\" ],\r\n  \"perms\" : [ \"system:door:view\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-12 17:12:26');
INSERT INTO `sys_oper_log` VALUES ('126', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-12 17:19:05');
INSERT INTO `sys_oper_log` VALUES ('127', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.update()', '1', 'admin', '研发部门', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"巡城鹿\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"sex\" : [ \"1\" ]\r\n}', '0', null, '2019-05-12 17:19:15');
INSERT INTO `sys_oper_log` VALUES ('128', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.changeStatus()', '1', 'admin', '研发部门', '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ]\r\n}', '0', null, '2019-05-12 17:52:09');
INSERT INTO `sys_oper_log` VALUES ('129', '角色管理', '2', 'com.ruoyi.web.controller.system.SysRoleController.changeStatus()', '1', 'admin', '研发部门', '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-05-12 17:52:13');
INSERT INTO `sys_oper_log` VALUES ('130', '出入库口', '1', 'com.ruoyi.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"12\" ],\r\n  \"name\" : [ \"12\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-05-12 19:17:05');
INSERT INTO `sys_oper_log` VALUES ('131', '参数管理', '2', 'com.ruoyi.web.controller.system.SysConfigController.editSave()', '1', 'admin', '研发部门', '/system/config/edit', '127.0.0.1', '内网IP', '{\r\n  \"configId\" : [ \"1\" ],\r\n  \"configName\" : [ \"主框架页-默认皮肤样式名称\" ],\r\n  \"configKey\" : [ \"sys.index.skinName\" ],\r\n  \"configValue\" : [ \"skin-green\" ],\r\n  \"configType\" : [ \"Y\" ],\r\n  \"remark\" : [ \"蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow\" ]\r\n}', '0', null, '2019-05-12 21:31:47');
INSERT INTO `sys_oper_log` VALUES ('132', '参数管理', '2', 'com.ruoyi.web.controller.system.SysConfigController.editSave()', '1', 'admin', '研发部门', '/system/config/edit', '127.0.0.1', '内网IP', '{\r\n  \"configId\" : [ \"1\" ],\r\n  \"configName\" : [ \"主框架页-默认皮肤样式名称\" ],\r\n  \"configKey\" : [ \"sys.index.skinName\" ],\r\n  \"configValue\" : [ \"skin-blue\" ],\r\n  \"configType\" : [ \"Y\" ],\r\n  \"remark\" : [ \"蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow\" ]\r\n}', '0', null, '2019-05-12 21:32:11');
INSERT INTO `sys_oper_log` VALUES ('133', '出入库口', '1', 'com.ruoyi.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"34\" ],\r\n  \"name\" : [ \"34\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-05-12 21:33:23');
INSERT INTO `sys_oper_log` VALUES ('134', '菜单管理', '1', 'com.ruoyi.web.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"入库管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-calculator\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-13 11:05:20');
INSERT INTO `sys_oper_log` VALUES ('135', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"1\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-gear\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-13 11:05:28');
INSERT INTO `sys_oper_log` VALUES ('136', '代码生成', '8', 'com.ruoyi.generator.controller.GenController.batchGenCode()', '1', 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"bill_in_detail,bill_in_master\" ]\r\n}', '0', null, '2019-05-13 13:25:36');
INSERT INTO `sys_oper_log` VALUES ('137', '菜单管理', '2', 'com.ruoyi.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2039\" ],\r\n  \"parentId\" : [ \"2038\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库单\" ],\r\n  \"url\" : [ \"/in/billInMaster\" ],\r\n  \"perms\" : [ \"in:billInMaster:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-13 13:33:49');
INSERT INTO `sys_oper_log` VALUES ('138', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/box_info', '127.0.0.1', '内网IP', '{ }', '1', 'Unable to find resource \'vm/sql/sql.vm\'', '2019-05-21 18:22:45');
INSERT INTO `sys_oper_log` VALUES ('139', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/box_info', '127.0.0.1', '内网IP', '{ }', '1', 'Unable to find resource \'vm/sql/sql.vm\'', '2019-05-21 18:24:33');
INSERT INTO `sys_oper_log` VALUES ('140', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/box_info', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-21 18:27:58');
INSERT INTO `sys_oper_log` VALUES ('141', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2044\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"容器\" ],\r\n  \"url\" : [ \"/in/boxInfo\" ],\r\n  \"perms\" : [ \"in:boxInfo:view\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-05-21 18:59:02');
INSERT INTO `sys_oper_log` VALUES ('142', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"12\" ],\r\n  \"boxType\" : [ \"1\" ]\r\n}', '0', null, '2019-05-21 19:14:29');
INSERT INTO `sys_oper_log` VALUES ('143', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2019-05-21 19:14:36');
INSERT INTO `sys_oper_log` VALUES ('144', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"12\" ],\r\n  \"boxType\" : [ \"2\" ]\r\n}', '0', null, '2019-05-21 19:14:43');
INSERT INTO `sys_oper_log` VALUES ('145', '容器', '2', 'com.deer.wms.web.controller.ware.BoxInfoController.editSave()', '1', 'admin', '研发部门', '/system/boxInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"boxId\" : [ \"2\" ],\r\n  \"boxCode\" : [ \"12\" ],\r\n  \"boxType\" : [ \"1\" ]\r\n}', '0', null, '2019-05-21 19:18:02');
INSERT INTO `sys_oper_log` VALUES ('146', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"1313\" ],\r\n  \"boxType\" : [ \"2\" ]\r\n}', '0', null, '2019-05-21 19:23:48');
INSERT INTO `sys_oper_log` VALUES ('147', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '0', null, '2019-05-21 19:23:51');
INSERT INTO `sys_oper_log` VALUES ('148', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', '0', null, '2019-05-21 19:23:53');
INSERT INTO `sys_oper_log` VALUES ('149', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"12\" ],\r\n  \"boxType\" : [ \"1\" ]\r\n}', '0', null, '2019-05-21 19:23:58');
INSERT INTO `sys_oper_log` VALUES ('150', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"13\" ],\r\n  \"boxType\" : [ \"2\" ]\r\n}', '0', null, '2019-05-21 19:24:06');
INSERT INTO `sys_oper_log` VALUES ('151', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '0', null, '2019-05-22 16:44:25');
INSERT INTO `sys_oper_log` VALUES ('152', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '0', null, '2019-05-22 16:44:27');
INSERT INTO `sys_oper_log` VALUES ('153', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"1111\" ],\r\n  \"boxType\" : [ \"2\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-05-22 16:44:56');
INSERT INTO `sys_oper_log` VALUES ('154', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"13\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-05-22 16:45:03');
INSERT INTO `sys_oper_log` VALUES ('155', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"1234\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxMemo\" : [ \"qweqqwe\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'memo\' in \'class com.deer.wms.system.domain.ware.BoxInfo\'', '2019-05-22 18:42:57');
INSERT INTO `sys_oper_log` VALUES ('156', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"1234\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxMemo\" : [ \"qweqqwe\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'memo\' in \'class com.deer.wms.system.domain.ware.BoxInfo\'', '2019-05-22 18:42:59');
INSERT INTO `sys_oper_log` VALUES ('157', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"1332\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxMemo\" : [ \"werty\" ]\r\n}', '0', null, '2019-05-22 18:46:24');
INSERT INTO `sys_oper_log` VALUES ('158', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"123\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxMemo\" : [ \"\" ]\r\n}', '0', null, '2019-05-22 18:49:35');
INSERT INTO `sys_oper_log` VALUES ('159', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"9\" ]\r\n}', '0', null, '2019-05-22 18:49:59');
INSERT INTO `sys_oper_log` VALUES ('160', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"8\" ]\r\n}', '0', null, '2019-05-26 21:24:45');
INSERT INTO `sys_oper_log` VALUES ('161', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"898989\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxUnit\" : [ \"2\" ],\r\n  \"boxMemo\" : [ \"\" ]\r\n}', '0', null, '2019-05-26 22:30:36');
INSERT INTO `sys_oper_log` VALUES ('162', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"6\" ]\r\n}', '0', null, '2019-05-26 23:13:11');
INSERT INTO `sys_oper_log` VALUES ('163', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"7\" ]\r\n}', '0', null, '2019-05-26 23:13:13');
INSERT INTO `sys_oper_log` VALUES ('164', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10\" ]\r\n}', '0', null, '2019-05-26 23:13:15');
INSERT INTO `sys_oper_log` VALUES ('165', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"1\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxUnit\" : [ \"1\" ],\r\n  \"boxMemo\" : [ \"1\" ]\r\n}', '0', null, '2019-05-26 23:36:37');
INSERT INTO `sys_oper_log` VALUES ('166', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"2\" ],\r\n  \"boxType\" : [ \"2\" ],\r\n  \"boxUnit\" : [ \"1\" ],\r\n  \"boxMemo\" : [ \"2\" ]\r\n}', '0', null, '2019-05-26 23:43:01');
INSERT INTO `sys_oper_log` VALUES ('167', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"567567\" ],\r\n  \"boxType\" : [ \"2\" ],\r\n  \"boxUnit\" : [ \"2\" ],\r\n  \"boxMemo\" : [ \"2\" ]\r\n}', '0', null, '2019-05-27 01:09:20');
INSERT INTO `sys_oper_log` VALUES ('168', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"987\" ],\r\n  \"boxType\" : [ \"2\" ],\r\n  \"boxUnit\" : [ \"2\" ],\r\n  \"boxMemo\" : [ \"2\" ]\r\n}', '0', null, '2019-05-27 01:09:54');
INSERT INTO `sys_oper_log` VALUES ('169', '容器', '1', 'com.deer.wms.web.controller.ware.BoxInfoController.addSave()', '1', 'admin', '研发部门', '/system/boxInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"boxCode\" : [ \"123456789\" ],\r\n  \"boxType\" : [ \"1\" ],\r\n  \"boxUnit\" : [ \"2\" ],\r\n  \"boxMemo\" : [ \"\" ]\r\n}', '0', null, '2019-05-28 10:04:20');
INSERT INTO `sys_oper_log` VALUES ('170', '容器', '3', 'com.deer.wms.web.controller.ware.BoxInfoController.remove()', '1', 'admin', '研发部门', '/system/boxInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"15\" ]\r\n}', '0', null, '2019-05-28 10:36:37');
INSERT INTO `sys_oper_log` VALUES ('171', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w002\" ],\r\n  \"wareName\" : [ \"四向车\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-05-28 10:47:31');
INSERT INTO `sys_oper_log` VALUES ('172', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-05-30 14:37:51');
INSERT INTO `sys_oper_log` VALUES ('173', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"213\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-05-30 15:11:28');
INSERT INTO `sys_oper_log` VALUES ('174', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-05-30 15:12:54');
INSERT INTO `sys_oper_log` VALUES ('175', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"213\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'create_user_name\' in \'class com.deer.wms.system.domain.bill.BillInMaster\'', '2019-05-30 15:16:06');
INSERT INTO `sys_oper_log` VALUES ('176', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'ware_id\' in \'class com.deer.wms.system.domain.bill.BillInMaster\'', '2019-05-30 15:17:44');
INSERT INTO `sys_oper_log` VALUES ('177', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-05-30 15:19:24');
INSERT INTO `sys_oper_log` VALUES ('178', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.addSave()', '1', 'admin', '研发部门', '/in/billInMaster/add', '127.0.0.1', '内网IP', '{\r\n  \"billNo\" : [ \"fasd\" ],\r\n  \"contractNo\" : [ \"fasdf\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-05-30 15:20:42');
INSERT INTO `sys_oper_log` VALUES ('179', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '0', null, '2019-05-30 15:32:40');
INSERT INTO `sys_oper_log` VALUES ('180', '菜单管理', '1', 'com.deer.wms.web.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa-plane\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-02 20:19:39');
INSERT INTO `sys_oper_log` VALUES ('181', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2049\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"32\" ],\r\n  \"icon\" : [ \"fa-plane\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-02 20:20:12');
INSERT INTO `sys_oper_log` VALUES ('182', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2049\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa-plane\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-02 20:20:20');
INSERT INTO `sys_oper_log` VALUES ('183', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"1\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-gear\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-02 20:20:29');
INSERT INTO `sys_oper_log` VALUES ('184', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2049\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-plane\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-02 20:20:49');
INSERT INTO `sys_oper_log` VALUES ('185', '物料分类', '1', 'com.deer.wms.web.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeCode\" : [ \"fasd\" ],\r\n  \"itemTypeName\" : [ \"fas\" ],\r\n  \"parentId\" : [ \"0\" ]\r\n}', '0', null, '2019-06-03 10:50:37');
INSERT INTO `sys_oper_log` VALUES ('186', '物料分类', '1', 'com.deer.wms.web.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeCode\" : [ \"fsadf\" ],\r\n  \"itemTypeName\" : [ \"fas\" ],\r\n  \"parentId\" : [ \"1\" ]\r\n}', '0', null, '2019-06-03 10:51:03');
INSERT INTO `sys_oper_log` VALUES ('187', '物料分类', '1', 'com.deer.wms.web.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2\" ],\r\n  \"itemTypeCode\" : [ \"fasfdsfg\" ],\r\n  \"itemTypeName\" : [ \"gfdshyth\" ]\r\n}', '0', null, '2019-06-03 14:32:22');
INSERT INTO `sys_oper_log` VALUES ('188', '菜单管理', '3', 'com.deer.wms.web.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2000', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-03 15:13:05');
INSERT INTO `sys_oper_log` VALUES ('189', '角色管理', '2', 'com.deer.wms.web.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"2323\" ],\r\n  \"roleKey\" : [ \"23\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"2323\" ],\r\n  \"menuIds\" : [ \"2038,2039,2040,2041,2042,2043,2049,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2032,2022,2023,2024,2025,2026,2027,2028,2029,2030,2031,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115\" ]\r\n}', '0', null, '2019-06-03 15:13:43');
INSERT INTO `sys_oper_log` VALUES ('190', '角色管理', '2', 'com.deer.wms.web.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"2\" ],\r\n  \"roleName\" : [ \"普通角色\" ],\r\n  \"roleKey\" : [ \"common\" ],\r\n  \"roleSort\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"普通角色\" ],\r\n  \"menuIds\" : [ \"2038,2039,2040,2041,2042,2043,2049,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115\" ]\r\n}', '0', null, '2019-06-03 15:13:49');
INSERT INTO `sys_oper_log` VALUES ('191', '菜单管理', '3', 'com.deer.wms.web.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2000', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-03 15:14:06');
INSERT INTO `sys_oper_log` VALUES ('192', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统监控\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-video-camera\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-03 15:14:28');
INSERT INTO `sys_oper_log` VALUES ('193', '物料', '1', 'com.deer.wms.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"12fgsdg\" ],\r\n  \"itemName\" : [ \"fasdf\" ],\r\n  \"spec\" : [ \"fasdffasdf\" ],\r\n  \"model\" : [ \"fasdffasdffasd\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '1', 'Index: 10, Size: 0', '2019-06-03 16:37:25');
INSERT INTO `sys_oper_log` VALUES ('194', '物料', '1', 'com.deer.wms.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"12fgsdg\" ],\r\n  \"itemName\" : [ \"fasdf\" ],\r\n  \"spec\" : [ \"fasdffasdf\" ],\r\n  \"model\" : [ \"fasdffasdffasd\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '1', 'Index: 10, Size: 0', '2019-06-03 16:38:34');
INSERT INTO `sys_oper_log` VALUES ('195', '物料', '1', 'com.deer.wms.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"fasd\" ],\r\n  \"itemName\" : [ \"fasd\" ],\r\n  \"spec\" : [ \"fasd\" ],\r\n  \"model\" : [ \"fasd\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '1', 'Index: 10, Size: 0', '2019-06-03 16:40:35');
INSERT INTO `sys_oper_log` VALUES ('196', '物料', '1', 'com.deer.wms.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"asf\" ],\r\n  \"itemName\" : [ \"fasd\" ],\r\n  \"spec\" : [ \"fsad\" ],\r\n  \"model\" : [ \"fsad\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '1', 'Index: 10, Size: 0', '2019-06-03 16:41:51');
INSERT INTO `sys_oper_log` VALUES ('197', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.batchGenCode()', '1', 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\r\n  \"tables\" : [ \"task_info,box_item\" ]\r\n}', '0', null, '2019-06-03 16:49:25');
INSERT INTO `sys_oper_log` VALUES ('198', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fasfasfd\" ],\r\n  \"wareName\" : [ \"fasdfa\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"fasdf\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:08:15');
INSERT INTO `sys_oper_log` VALUES ('199', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"vzxvzxv\" ],\r\n  \"wareName\" : [ \"zvxcvzx\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"vzxcvzx\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:11:29');
INSERT INTO `sys_oper_log` VALUES ('200', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"vzxvzxv\" ],\r\n  \"wareName\" : [ \"zvxcvzx\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"vzxcvzx\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:14:49');
INSERT INTO `sys_oper_log` VALUES ('201', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"asdfasd\" ],\r\n  \"wareName\" : [ \"vvasd\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"vasdv\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:15:47');
INSERT INTO `sys_oper_log` VALUES ('202', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"asdfasd\" ],\r\n  \"wareName\" : [ \"vvasd\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"vasdv\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:16:17');
INSERT INTO `sys_oper_log` VALUES ('203', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fasdf\" ],\r\n  \"wareName\" : [ \"fasdf\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"fsad\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:21:32');
INSERT INTO `sys_oper_log` VALUES ('204', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fasdf\" ],\r\n  \"wareName\" : [ \"fasdf\" ],\r\n  \"createUserId\" : [ \"\" ],\r\n  \"createTime\" : [ \"fsad\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:21:38');
INSERT INTO `sys_oper_log` VALUES ('205', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fasdv\" ],\r\n  \"wareName\" : [ \"fasdf\" ],\r\n  \"createTime\" : [ \"fasd\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:33:15');
INSERT INTO `sys_oper_log` VALUES ('206', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"fsf\" ],\r\n  \"memo\" : [ \"fsd\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:37:57');
INSERT INTO `sys_oper_log` VALUES ('207', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"fsf\" ],\r\n  \"memo\" : [ \"fsd\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.builder.BuilderException: Error invoking SqlProvider method (tk.mybatis.mapper.provider.base.BaseInsertProvider.dynamicSQL).  Cause: java.lang.InstantiationException: tk.mybatis.mapper.provider.base.BaseInsertProvider', '2019-06-05 18:39:58');
INSERT INTO `sys_oper_log` VALUES ('208', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"dsafd\" ],\r\n  \"wareName\" : [ \"fasd\" ],\r\n  \"memo\" : [ \"fads\" ]\r\n}', '0', null, '2019-06-05 18:56:34');
INSERT INTO `sys_oper_log` VALUES ('209', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fas\" ],\r\n  \"wareName\" : [ \"fsad\" ],\r\n  \"memo\" : [ \"fas\" ]\r\n}', '0', null, '2019-06-05 19:04:19');
INSERT INTO `sys_oper_log` VALUES ('210', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fas\" ],\r\n  \"wareName\" : [ \"fsad\" ],\r\n  \"memo\" : [ \"fas\" ]\r\n}', '0', null, '2019-06-05 19:04:20');
INSERT INTO `sys_oper_log` VALUES ('211', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fas\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-05 19:07:11');
INSERT INTO `sys_oper_log` VALUES ('212', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fasfasdf\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-05 19:07:20');
INSERT INTO `sys_oper_log` VALUES ('213', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"tet\" ],\r\n  \"wareName\" : [ \"ter\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-05 19:49:26');
INSERT INTO `sys_oper_log` VALUES ('214', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"frqewf\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-05 19:51:56');
INSERT INTO `sys_oper_log` VALUES ('215', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fasdf\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-05 19:52:07');
INSERT INTO `sys_oper_log` VALUES ('216', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"221\" ]\r\n}', '0', null, '2019-06-05 21:29:52');
INSERT INTO `sys_oper_log` VALUES ('217', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"220\" ]\r\n}', '0', null, '2019-06-05 21:29:54');
INSERT INTO `sys_oper_log` VALUES ('218', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"219\" ]\r\n}', '0', null, '2019-06-05 21:29:56');
INSERT INTO `sys_oper_log` VALUES ('219', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"218\" ]\r\n}', '0', null, '2019-06-05 21:29:58');
INSERT INTO `sys_oper_log` VALUES ('220', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"217\" ]\r\n}', '0', null, '2019-06-05 21:30:00');
INSERT INTO `sys_oper_log` VALUES ('221', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"216\" ]\r\n}', '0', null, '2019-06-05 21:30:02');
INSERT INTO `sys_oper_log` VALUES ('222', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"215\" ]\r\n}', '0', null, '2019-06-05 21:30:04');
INSERT INTO `sys_oper_log` VALUES ('223', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"214\" ]\r\n}', '0', null, '2019-06-05 21:30:05');
INSERT INTO `sys_oper_log` VALUES ('224', '角色管理', '2', 'com.deer.wms.web.controller.system.SysRoleController.changeStatus()', '1', 'admin', '研发部门', '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"status\" : [ \"1\" ]\r\n}', '0', null, '2019-06-06 21:50:00');
INSERT INTO `sys_oper_log` VALUES ('225', '物料', '3', 'com.deer.wms.web.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '0', null, '2019-06-12 16:26:01');
INSERT INTO `sys_oper_log` VALUES ('226', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2019-06-12 16:26:07');
INSERT INTO `sys_oper_log` VALUES ('227', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '0', null, '2019-06-12 16:26:09');
INSERT INTO `sys_oper_log` VALUES ('228', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', '0', null, '2019-06-12 16:26:11');
INSERT INTO `sys_oper_log` VALUES ('229', '物料分类', '1', 'com.deer.wms.web.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"itemTypeCode\" : [ \"\" ],\r\n  \"itemTypeName\" : [ \"物料分类\" ]\r\n}', '0', null, '2019-06-12 16:47:01');
INSERT INTO `sys_oper_log` VALUES ('230', '物料分类', '1', 'com.deer.wms.web.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"4\" ],\r\n  \"itemTypeCode\" : [ \"\" ],\r\n  \"itemTypeName\" : [ \"工器具\" ]\r\n}', '0', null, '2019-06-12 16:47:16');
INSERT INTO `sys_oper_log` VALUES ('231', '物料分类', '1', 'com.deer.wms.web.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"5\" ],\r\n  \"itemTypeCode\" : [ \"fsadf\" ],\r\n  \"itemTypeName\" : [ \"fasf\" ]\r\n}', '0', null, '2019-06-12 17:08:30');
INSERT INTO `sys_oper_log` VALUES ('232', '角色管理', '3', 'com.deer.wms.web.controller.system.SysRoleController.remove()', '1', 'admin', '研发部门', '/system/role/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1,2,100\" ]\r\n}', '0', null, '2019-06-12 17:16:45');
INSERT INTO `sys_oper_log` VALUES ('233', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-06-12 17:20:39');
INSERT INTO `sys_oper_log` VALUES ('234', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-06-12 17:21:05');
INSERT INTO `sys_oper_log` VALUES ('235', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"213\" ]\r\n}', '0', null, '2019-06-12 18:18:12');
INSERT INTO `sys_oper_log` VALUES ('236', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '0', null, '2019-06-12 18:24:03');
INSERT INTO `sys_oper_log` VALUES ('237', '物料分类', '3', 'com.deer.wms.web.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/6', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-12 18:42:34');
INSERT INTO `sys_oper_log` VALUES ('238', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fsd\" ],\r\n  \"wareName\" : [ \"casdfasdf\" ],\r\n  \"memo\" : [ \"fds\" ]\r\n}', '0', null, '2019-06-15 15:13:55');
INSERT INTO `sys_oper_log` VALUES ('239', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fsad\" ],\r\n  \"wareName\" : [ \"fsad\" ],\r\n  \"memo\" : [ \"fsad\" ]\r\n}', '0', null, '2019-06-15 15:27:10');
INSERT INTO `sys_oper_log` VALUES ('240', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fas\" ],\r\n  \"wareName\" : [ \"fasd\" ],\r\n  \"memo\" : [ \"fsad\" ]\r\n}', '0', null, '2019-06-15 15:30:57');
INSERT INTO `sys_oper_log` VALUES ('241', '物料', '1', 'com.deer.wms.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"fasd\" ],\r\n  \"itemName\" : [ \"fasd\" ],\r\n  \"spec\" : [ \"fsad\" ],\r\n  \"model\" : [ \"fsad\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '1', 'Index: 10, Size: 0', '2019-06-15 15:31:57');
INSERT INTO `sys_oper_log` VALUES ('242', '物料', '1', 'com.deer.wms.web.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"fas\" ],\r\n  \"itemName\" : [ \"fas\" ],\r\n  \"spec\" : [ \"fas\" ],\r\n  \"model\" : [ \"fads\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '0', null, '2019-06-15 15:35:28');
INSERT INTO `sys_oper_log` VALUES ('243', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:32:01');
INSERT INTO `sys_oper_log` VALUES ('244', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:35:15');
INSERT INTO `sys_oper_log` VALUES ('245', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:38:16');
INSERT INTO `sys_oper_log` VALUES ('246', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:41:07');
INSERT INTO `sys_oper_log` VALUES ('247', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:45:13');
INSERT INTO `sys_oper_log` VALUES ('248', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:48:28');
INSERT INTO `sys_oper_log` VALUES ('249', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"fsda\" ],\r\n  \"wareName\" : [ \"fsd\" ],\r\n  \"memo\" : [ \"fsd\" ]\r\n}', '0', null, '2019-06-16 18:48:49');
INSERT INTO `sys_oper_log` VALUES ('250', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 18:55:46');
INSERT INTO `sys_oper_log` VALUES ('251', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'bill_in_detail\' in \'where clause\'\r\n### The error may involve com.deer.wms.system.mapper.bill.BillInDetailMapper.deleteBillInDetailByIds-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from bill_in_detail where bill_in_detail in           (               ?          )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'bill_in_detail\' in \'where clause\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'bill_in_detail\' in \'where clause\'', '2019-06-16 19:28:11');
INSERT INTO `sys_oper_log` VALUES ('252', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ]\r\n}', '0', null, '2019-06-16 19:41:42');
INSERT INTO `sys_oper_log` VALUES ('253', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"10\" ]\r\n}', '0', null, '2019-06-16 19:42:31');
INSERT INTO `sys_oper_log` VALUES ('254', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"9\" ]\r\n}', '0', null, '2019-06-16 19:42:32');
INSERT INTO `sys_oper_log` VALUES ('255', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"8\" ]\r\n}', '0', null, '2019-06-16 19:42:33');
INSERT INTO `sys_oper_log` VALUES ('256', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"7\" ]\r\n}', '0', null, '2019-06-16 19:44:48');
INSERT INTO `sys_oper_log` VALUES ('257', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"7\" ]\r\n}', '0', null, '2019-06-16 19:44:57');
INSERT INTO `sys_oper_log` VALUES ('258', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10\" ]\r\n}', '0', null, '2019-06-16 19:44:59');
INSERT INTO `sys_oper_log` VALUES ('259', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"9\" ]\r\n}', '0', null, '2019-06-16 19:45:02');
INSERT INTO `sys_oper_log` VALUES ('260', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"8\" ]\r\n}', '0', null, '2019-06-16 19:45:04');
INSERT INTO `sys_oper_log` VALUES ('261', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"6\" ]\r\n}', '0', null, '2019-06-16 19:45:06');
INSERT INTO `sys_oper_log` VALUES ('262', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"5\" ]\r\n}', '0', null, '2019-06-16 19:45:08');
INSERT INTO `sys_oper_log` VALUES ('263', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '0', null, '2019-06-16 19:45:10');
INSERT INTO `sys_oper_log` VALUES ('264', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 19:55:57');
INSERT INTO `sys_oper_log` VALUES ('265', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"13\" ]\r\n}', '0', null, '2019-06-16 19:56:06');
INSERT INTO `sys_oper_log` VALUES ('266', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"12\" ]\r\n}', '0', null, '2019-06-16 19:56:08');
INSERT INTO `sys_oper_log` VALUES ('267', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"11\" ]\r\n}', '0', null, '2019-06-16 19:56:10');
INSERT INTO `sys_oper_log` VALUES ('268', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"11\" ]\r\n}', '0', null, '2019-06-16 19:56:57');
INSERT INTO `sys_oper_log` VALUES ('269', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 19:57:29');
INSERT INTO `sys_oper_log` VALUES ('270', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/supplier', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-16 20:02:43');
INSERT INTO `sys_oper_log` VALUES ('271', '供应商', '1', 'com.deer.wms.web.controller.ware.SupplierController.addSave()', '1', 'admin', '研发部门', '/system/supplier/add', '127.0.0.1', '内网IP', '{\r\n  \"supplierName\" : [ \"fas\" ],\r\n  \"linkMan\" : [ \"fsa\" ],\r\n  \"linkPhone\" : [ \"fsda\" ],\r\n  \"email\" : [ \"fsad\" ],\r\n  \"address\" : [ \"fads\" ]\r\n}', '0', null, '2019-06-16 20:49:46');
INSERT INTO `sys_oper_log` VALUES ('272', '供应商', '3', 'com.deer.wms.web.controller.ware.SupplierController.remove()', '1', 'admin', '研发部门', '/system/supplier/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2019-06-16 20:49:53');
INSERT INTO `sys_oper_log` VALUES ('273', '菜单管理', '2', 'com.deer.wms.web.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2050\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"供应商\" ],\r\n  \"url\" : [ \"/system/supplier\" ],\r\n  \"perms\" : [ \"system:supplier:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-16 20:51:47');
INSERT INTO `sys_oper_log` VALUES ('274', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect date value: \'\' for column \'pd\' at row 1\r\n### The error may involve com.deer.wms.system.mapper.bill.BillInDetailMapper.insertBillInDetail-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into bill_in_detail    ( bill_id,    item_code,            pd,        quantity )           values ( ?,    ?,            ?,        ? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect date value: \'\' for column \'pd\' at row 1\n; Data truncation: Incorrect date value: \'\' for column \'pd\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect date value: \'\' for column \'pd\' at row 1', '2019-06-16 20:55:32');
INSERT INTO `sys_oper_log` VALUES ('275', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/supplier', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-17 11:06:28');
INSERT INTO `sys_oper_log` VALUES ('276', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"16\" ]\r\n}', '0', null, '2019-06-19 09:40:33');
INSERT INTO `sys_oper_log` VALUES ('277', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"15\" ]\r\n}', '0', null, '2019-06-19 14:14:52');
INSERT INTO `sys_oper_log` VALUES ('278', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"007\" ],\r\n  \"wareName\" : [ \"007\" ],\r\n  \"memo\" : [ \"007\" ]\r\n}', '0', null, '2019-06-24 08:55:57');
INSERT INTO `sys_oper_log` VALUES ('279', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"00701\" ],\r\n  \"areaName\" : [ \"00701\" ],\r\n  \"wareId\" : [ \"218\" ],\r\n  \"memo\" : [ \"00701\" ]\r\n}', '0', null, '2019-06-24 08:56:11');
INSERT INTO `sys_oper_log` VALUES ('280', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"0070101\" ],\r\n  \"shelfName\" : [ \"0070101\" ],\r\n  \"areaId\" : [ \"244\" ],\r\n  \"shelfRow\" : [ \"7\" ],\r\n  \"shelfColumn\" : [ \"7\" ],\r\n  \"memo\" : [ \"0070101\" ]\r\n}', '0', null, '2019-06-24 08:56:31');
INSERT INTO `sys_oper_log` VALUES ('281', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"007010101010101010011\" ],\r\n  \"shelfId\" : [ \"14\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"007010101010101010011\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\r\n### The error may involve com.deer.wms.system.mapper.ware.CellInfoMapper.insertCellInfo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cell_info    ( shelf_id,    cell_code,    memo,    s_row,    s_column,    state )           values ( ?,    ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'', '2019-06-24 09:30:34');
INSERT INTO `sys_oper_log` VALUES ('282', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"0070101010\" ],\r\n  \"shelfId\" : [ \"14\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"007010101\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\r\n### The error may involve com.deer.wms.system.mapper.ware.CellInfoMapper.insertCellInfo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cell_info    ( shelf_id,    cell_code,    memo,    s_row,    s_column,    state )           values ( ?,    ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'', '2019-06-24 09:30:44');
INSERT INTO `sys_oper_log` VALUES ('283', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"00\" ],\r\n  \"shelfId\" : [ \"14\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"007010101\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\r\n### The error may involve com.deer.wms.system.mapper.ware.CellInfoMapper.insertCellInfo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cell_info    ( shelf_id,    cell_code,    memo,    s_row,    s_column,    state )           values ( ?,    ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'', '2019-06-24 09:30:48');
INSERT INTO `sys_oper_log` VALUES ('284', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"00\" ],\r\n  \"shelfId\" : [ \"14\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"007010101\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\r\n### The error may involve com.deer.wms.system.mapper.ware.CellInfoMapper.insertCellInfo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cell_info    ( shelf_id,    cell_code,    memo,    s_row,    s_column,    state )           values ( ?,    ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'', '2019-06-24 09:45:01');
INSERT INTO `sys_oper_log` VALUES ('285', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"42414\" ],\r\n  \"shelfId\" : [ \"11\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"244\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\r\n### The error may involve com.deer.wms.system.mapper.ware.CellInfoMapper.insertCellInfo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cell_info    ( shelf_id,    cell_code,    memo,    s_row,    s_column,    state )           values ( ?,    ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'s_row\' in \'field list\'', '2019-06-24 09:52:45');
INSERT INTO `sys_oper_log` VALUES ('286', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"2131\" ],\r\n  \"shelfId\" : [ \"11\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"4324\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_column\' in \'field list\'\r\n### The error may involve com.deer.wms.system.mapper.ware.CellInfoMapper.insertCellInfo-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into cell_info    ( shelf_id,    cell_code,    memo,    s_row,    s_column,    state )           values ( ?,    ?,    ?,    ?,    ?,    ? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'s_column\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'s_column\' in \'field list\'', '2019-06-24 09:56:10');
INSERT INTO `sys_oper_log` VALUES ('287', '货位设置', '1', 'com.deer.wms.web.controller.ware.CellInfoController.addSave()', '1', 'admin', '研发部门', '/system/cellInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"cellCode\" : [ \"12131\" ],\r\n  \"shelfId\" : [ \"11\" ],\r\n  \"sRow\" : [ \"0\" ],\r\n  \"sColumn\" : [ \"0\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"memo\" : [ \"13213\" ]\r\n}', '0', null, '2019-06-24 09:57:26');
INSERT INTO `sys_oper_log` VALUES ('288', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"14\" ],\r\n  \"shelfName\" : [ \"14\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-06-24 10:58:20');
INSERT INTO `sys_oper_log` VALUES ('289', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"14\" ],\r\n  \"shelfName\" : [ \"14\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-06-24 11:00:08');
INSERT INTO `sys_oper_log` VALUES ('290', '入库单', '3', 'com.deer.wms.admin.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"13\" ]\r\n}', '0', null, '2019-06-24 14:46:56');
INSERT INTO `sys_oper_log` VALUES ('291', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"3131\" ],\r\n  \"wareName\" : [ \"007\" ],\r\n  \"memo\" : [ \"131\" ]\r\n}', '0', null, '2019-06-24 14:55:26');
INSERT INTO `sys_oper_log` VALUES ('292', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"3131\" ],\r\n  \"wareName\" : [ \"007\" ],\r\n  \"memo\" : [ \"131\" ]\r\n}', '0', null, '2019-06-24 14:55:28');
INSERT INTO `sys_oper_log` VALUES ('293', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"3131\" ],\r\n  \"wareName\" : [ \"008\" ],\r\n  \"memo\" : [ \"131\" ]\r\n}', '0', null, '2019-06-24 14:55:31');
INSERT INTO `sys_oper_log` VALUES ('294', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/box_info', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-24 14:55:39');
INSERT INTO `sys_oper_log` VALUES ('295', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2044\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"容器\" ],\r\n  \"url\" : [ \"/box/infos\" ],\r\n  \"perms\" : [ \"system:boxInfo:view\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-24 15:02:42');
INSERT INTO `sys_oper_log` VALUES ('296', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"123\" ],\r\n  \"wareName\" : [ \"007\" ],\r\n  \"memo\" : [ \"131\" ]\r\n}', '0', null, '2019-06-24 15:09:34');
INSERT INTO `sys_oper_log` VALUES ('297', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"31231\" ],\r\n  \"areaName\" : [ \"12\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-24 15:09:46');
INSERT INTO `sys_oper_log` VALUES ('298', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"31231\" ],\r\n  \"areaName\" : [ \"13\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-24 15:09:49');
INSERT INTO `sys_oper_log` VALUES ('299', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"312\" ],\r\n  \"shelfName\" : [ \"14\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"1\" ],\r\n  \"shelfColumn\" : [ \"1\" ],\r\n  \"memo\" : [ \"132\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2', '2019-06-24 15:19:12');
INSERT INTO `sys_oper_log` VALUES ('300', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"14\" ],\r\n  \"shelfName\" : [ \"14\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"1\" ],\r\n  \"shelfColumn\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ]\r\n}', '0', null, '2019-06-24 15:26:57');
INSERT INTO `sys_oper_log` VALUES ('301', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"14\" ],\r\n  \"shelfName\" : [ \"15\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"1\" ],\r\n  \"shelfColumn\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ]\r\n}', '0', null, '2019-06-24 15:27:01');
INSERT INTO `sys_oper_log` VALUES ('302', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2044\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"容器\" ],\r\n  \"url\" : [ \"/boxInfo\" ],\r\n  \"perms\" : [ \"system:boxInfo:view\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-24 18:41:55');
INSERT INTO `sys_oper_log` VALUES ('303', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2050\" ],\r\n  \"parentId\" : [ \"2001\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"供应商\" ],\r\n  \"url\" : [ \"/system/supplier\" ],\r\n  \"perms\" : [ \"system:supplier:view\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-25 09:24:54');
INSERT INTO `sys_oper_log` VALUES ('304', '容器', '1', 'com.deer.wms.base.system.web.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-25 09:27:15');
INSERT INTO `sys_oper_log` VALUES ('305', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/cell_info', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-25 09:28:12');
INSERT INTO `sys_oper_log` VALUES ('306', '货架设置', '2', 'com.deer.wms.web.controller.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"11\" ],\r\n  \"areaId\" : [ \"11\" ],\r\n  \"shelfCode\" : [ \"11\" ],\r\n  \"shelfName\" : [ \"12\" ],\r\n  \"shelfRow\" : [ \"11\" ],\r\n  \"shelfColumn\" : [ \"11\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-25 09:35:44');
INSERT INTO `sys_oper_log` VALUES ('307', '货架设置', '2', 'com.deer.wms.web.controller.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"11\" ],\r\n  \"areaId\" : [ \"11\" ],\r\n  \"shelfCode\" : [ \"11\" ],\r\n  \"shelfName\" : [ \"12\" ],\r\n  \"shelfRow\" : [ \"11\" ],\r\n  \"shelfColumn\" : [ \"11\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2', '2019-06-25 09:39:42');
INSERT INTO `sys_oper_log` VALUES ('308', '货架设置', '2', 'com.deer.wms.web.controller.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"12\" ],\r\n  \"areaId\" : [ \"12\" ],\r\n  \"shelfCode\" : [ \"12\" ],\r\n  \"shelfName\" : [ \"13\" ],\r\n  \"shelfRow\" : [ \"12\" ],\r\n  \"shelfColumn\" : [ \"12\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 09:41:36');
INSERT INTO `sys_oper_log` VALUES ('309', '货架设置', '2', 'com.deer.wms.web.controller.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"12\" ],\r\n  \"areaId\" : [ \"12\" ],\r\n  \"shelfCode\" : [ \"12\" ],\r\n  \"shelfName\" : [ \"17\" ],\r\n  \"shelfRow\" : [ \"12\" ],\r\n  \"shelfColumn\" : [ \"12\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 09:41:39');
INSERT INTO `sys_oper_log` VALUES ('310', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"218\" ],\r\n  \"wareCode\" : [ \"007\" ],\r\n  \"wareName\" : [ \"008\" ],\r\n  \"memo\" : [ \"007\" ]\r\n}', '0', null, '2019-06-25 09:44:13');
INSERT INTO `sys_oper_log` VALUES ('311', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"218\" ],\r\n  \"wareCode\" : [ \"007\" ],\r\n  \"wareName\" : [ \"009\" ],\r\n  \"memo\" : [ \"007\" ]\r\n}', '0', null, '2019-06-25 09:44:18');
INSERT INTO `sys_oper_log` VALUES ('312', '货区设置', '2', 'com.deer.wms.web.controller.ware.AreaInfoController.editSave()', '1', 'admin', '研发部门', '/system/areaInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"areaId\" : [ \"243\" ],\r\n  \"areaCode\" : [ \"12\" ],\r\n  \"areaName\" : [ \"13\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 09:44:38');
INSERT INTO `sys_oper_log` VALUES ('313', '货区设置', '2', 'com.deer.wms.web.controller.ware.AreaInfoController.editSave()', '1', 'admin', '研发部门', '/system/areaInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"areaId\" : [ \"243\" ],\r\n  \"areaCode\" : [ \"12\" ],\r\n  \"areaName\" : [ \"12\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 09:44:41');
INSERT INTO `sys_oper_log` VALUES ('314', '货区设置', '2', 'com.deer.wms.web.controller.ware.AreaInfoController.editSave()', '1', 'admin', '研发部门', '/system/areaInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"areaId\" : [ \"243\" ],\r\n  \"areaCode\" : [ \"12\" ],\r\n  \"areaName\" : [ \"13\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 09:45:53');
INSERT INTO `sys_oper_log` VALUES ('315', '货区设置', '2', 'com.deer.wms.web.controller.ware.AreaInfoController.editSave()', '1', 'admin', '研发部门', '/system/areaInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"areaId\" : [ \"243\" ],\r\n  \"areaCode\" : [ \"12\" ],\r\n  \"areaName\" : [ \"14\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 09:45:58');
INSERT INTO `sys_oper_log` VALUES ('316', '容器', '1', 'com.deer.wms.base.system.web.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-25 09:49:45');
INSERT INTO `sys_oper_log` VALUES ('317', '容器', '1', 'com.deer.wms.base.system.web.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-25 09:58:55');
INSERT INTO `sys_oper_log` VALUES ('318', '容器', '1', 'com.deer.wms.base.system.web.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-25 10:09:19');
INSERT INTO `sys_oper_log` VALUES ('319', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 10:28:59');
INSERT INTO `sys_oper_log` VALUES ('320', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"13\" ]\r\n}', '0', null, '2019-06-25 10:29:00');
INSERT INTO `sys_oper_log` VALUES ('321', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"14\" ]\r\n}', '0', null, '2019-06-25 10:29:02');
INSERT INTO `sys_oper_log` VALUES ('322', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"15\" ]\r\n}', '0', null, '2019-06-25 10:29:04');
INSERT INTO `sys_oper_log` VALUES ('323', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"17\" ]\r\n}', '0', null, '2019-06-25 10:29:05');
INSERT INTO `sys_oper_log` VALUES ('324', '货位设置', '3', 'com.deer.wms.web.controller.ware.CellInfoController.remove()', '1', 'admin', '研发部门', '/system/cellInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1,2,3,4,5,6,7,8,9,10\" ]\r\n}', '0', null, '2019-06-25 10:43:24');
INSERT INTO `sys_oper_log` VALUES ('325', '货位设置', '3', 'com.deer.wms.web.controller.ware.CellInfoController.remove()', '1', 'admin', '研发部门', '/system/cellInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52\" ]\r\n}', '0', null, '2019-06-25 10:43:29');
INSERT INTO `sys_oper_log` VALUES ('326', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"001\" ],\r\n  \"shelfName\" : [ \"001\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"5\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON DELETE CASCADE ON UPDATE CASCADE)\r\n### The error may involve com.deer.wms.system.dao.ware.ShelfInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO shelf_info  ( shelf_id,area_id,shelf_code,shelf_name,shelf_row,shelf_column,memo ) VALUES( ?,?,?,?,?,?,? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON DELETE CASCADE ON UPDATE CASCADE)\n; Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON DELETE CASCADE ON UPDATE CASCADE); nested exception is java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON DELETE CASCADE ON UPDATE CASCADE)', '2019-06-25 10:43:49');
INSERT INTO `sys_oper_log` VALUES ('327', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"1312\" ],\r\n  \"shelfName\" : [ \"131\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"4\" ],\r\n  \"shelfColumn\" : [ \"4\" ],\r\n  \"memo\" : [ \"1412\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON UPDATE CASCADE)\r\n### The error may involve com.deer.wms.system.dao.ware.ShelfInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO shelf_info  ( shelf_id,area_id,shelf_code,shelf_name,shelf_row,shelf_column,memo ) VALUES( ?,?,?,?,?,?,? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON UPDATE CASCADE)\n; Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON UPDATE CASCADE); nested exception is java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`shelf_info`, CONSTRAINT `shelfId` FOREIGN KEY (`shelf_id`) REFERENCES `cell_info` (`shelf_id`) ON UPDATE CASCADE)', '2019-06-25 10:47:04');
INSERT INTO `sys_oper_log` VALUES ('328', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"11\" ],\r\n  \"shelfName\" : [ \"11\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-06-25 10:49:22');
INSERT INTO `sys_oper_log` VALUES ('329', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"18\" ]\r\n}', '0', null, '2019-06-25 11:04:23');
INSERT INTO `sys_oper_log` VALUES ('330', '货位设置', '3', 'com.deer.wms.web.controller.ware.CellInfoController.remove()', '1', 'admin', '研发部门', '/system/cellInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77\" ]\r\n}', '0', null, '2019-06-25 11:04:29');
INSERT INTO `sys_oper_log` VALUES ('331', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"11\" ],\r\n  \"shelfName\" : [ \"11\" ],\r\n  \"areaId\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-25 11:13:27');
INSERT INTO `sys_oper_log` VALUES ('332', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"19\" ]\r\n}', '0', null, '2019-06-25 11:13:38');
INSERT INTO `sys_oper_log` VALUES ('333', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2019-06-25 11:18:42');
INSERT INTO `sys_oper_log` VALUES ('334', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"243\" ]\r\n}', '0', null, '2019-06-25 11:18:44');
INSERT INTO `sys_oper_log` VALUES ('335', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"244\" ]\r\n}', '0', null, '2019-06-25 11:18:45');
INSERT INTO `sys_oper_log` VALUES ('336', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"245\" ]\r\n}', '0', null, '2019-06-25 11:18:46');
INSERT INTO `sys_oper_log` VALUES ('337', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"12\" ],\r\n  \"areaName\" : [ \"12\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 11:19:00');
INSERT INTO `sys_oper_log` VALUES ('338', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"11\" ],\r\n  \"shelfName\" : [ \"11\" ],\r\n  \"areaId\" : [ \"246\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-06-25 11:19:24');
INSERT INTO `sys_oper_log` VALUES ('339', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"246\" ]\r\n}', '0', null, '2019-06-25 11:19:31');
INSERT INTO `sys_oper_log` VALUES ('340', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"11\" ],\r\n  \"areaName\" : [ \"11\" ],\r\n  \"wareId\" : [ \"219\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-25 13:43:37');
INSERT INTO `sys_oper_log` VALUES ('341', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"11\" ],\r\n  \"shelfName\" : [ \"11\" ],\r\n  \"areaId\" : [ \"247\" ],\r\n  \"shelfRow\" : [ \"11\" ],\r\n  \"shelfColumn\" : [ \"1\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-25 13:43:50');
INSERT INTO `sys_oper_log` VALUES ('342', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"219\" ]\r\n}', '0', null, '2019-06-25 13:44:11');
INSERT INTO `sys_oper_log` VALUES ('343', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"asfas\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"spec\" : [ \"红色\" ],\r\n  \"model\" : [ \"A11\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 15:28:39');
INSERT INTO `sys_oper_log` VALUES ('344', '物料', '3', 'com.deer.wms.admin.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"16\" ]\r\n}', '0', null, '2019-06-25 15:28:45');
INSERT INTO `sys_oper_log` VALUES ('345', '物料', '3', 'com.deer.wms.admin.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"17\" ]\r\n}', '0', null, '2019-06-25 15:28:48');
INSERT INTO `sys_oper_log` VALUES ('346', '物料', '3', 'com.deer.wms.admin.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"6\" ]\r\n}', '0', null, '2019-06-25 15:28:54');
INSERT INTO `sys_oper_log` VALUES ('347', '物料', '3', 'com.deer.wms.admin.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"7,8,9,10,11\" ]\r\n}', '0', null, '2019-06-25 15:29:01');
INSERT INTO `sys_oper_log` VALUES ('348', '物料', '3', 'com.deer.wms.admin.controller.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"12,13,14,15\" ]\r\n}', '0', null, '2019-06-25 15:29:06');
INSERT INTO `sys_oper_log` VALUES ('349', '供应商', '1', 'com.deer.wms.web.controller.ware.SupplierController.addSave()', '1', 'admin', '研发部门', '/system/supplier/add', '127.0.0.1', '内网IP', '{\r\n  \"supplierName\" : [ \"fsadf\" ],\r\n  \"linkMan\" : [ \"asfda\" ],\r\n  \"linkPhone\" : [ \"sadfas\" ],\r\n  \"email\" : [ \"fasd\" ],\r\n  \"address\" : [ \"fasd\" ]\r\n}', '0', null, '2019-06-25 15:30:14');
INSERT INTO `sys_oper_log` VALUES ('350', '供应商', '1', 'com.deer.wms.web.controller.ware.SupplierController.addSave()', '1', 'admin', '研发部门', '/system/supplier/add', '127.0.0.1', '内网IP', '{\r\n  \"supplierName\" : [ \"fasfdasf\" ],\r\n  \"linkMan\" : [ \"fasdf\" ],\r\n  \"linkPhone\" : [ \"fasdf\" ],\r\n  \"email\" : [ \"fasdf\" ],\r\n  \"address\" : [ \"fads\" ]\r\n}', '0', null, '2019-06-25 15:30:59');
INSERT INTO `sys_oper_log` VALUES ('351', '入库单详情', '3', 'com.deer.wms.web.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"12\" ]\r\n}', '0', null, '2019-06-25 15:34:49');
INSERT INTO `sys_oper_log` VALUES ('352', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"11\" ]\r\n}', '0', null, '2019-06-25 15:34:55');
INSERT INTO `sys_oper_log` VALUES ('353', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'supplierCode\' in \'class com.deer.wms.system.domain.bill.BillInDetail\'', '2019-06-25 16:08:22');
INSERT INTO `sys_oper_log` VALUES ('354', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-25 16:19:13');
INSERT INTO `sys_oper_log` VALUES ('355', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"144\" ],\r\n  \"itemName\" : [ \"144\" ],\r\n  \"spec\" : [ \"144\" ],\r\n  \"model\" : [ \"144\" ],\r\n  \"thickness\" : [ \"144\" ]\r\n}', '0', null, '2019-06-26 08:55:25');
INSERT INTO `sys_oper_log` VALUES ('356', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 08:55:38');
INSERT INTO `sys_oper_log` VALUES ('357', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"5\" ],\r\n  \"itemTypeCode\" : [ \"qwrwer\" ],\r\n  \"itemTypeName\" : [ \"dfwqqw\" ]\r\n}', '0', null, '2019-06-26 09:25:26');
INSERT INTO `sys_oper_log` VALUES ('358', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2027\" ],\r\n  \"parentId\" : [ \"2032\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"物料分类\" ],\r\n  \"url\" : [ \"/system/itemType\" ],\r\n  \"perms\" : [ \"system:itemType:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-26 09:27:20');
INSERT INTO `sys_oper_log` VALUES ('359', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2022\" ],\r\n  \"parentId\" : [ \"2032\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"物料\" ],\r\n  \"url\" : [ \"/system/itemInfo\" ],\r\n  \"perms\" : [ \"system:itemInfo:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-06-26 09:27:34');
INSERT INTO `sys_oper_log` VALUES ('360', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemName\" : [ \"12\" ],\r\n  \"spec\" : [ \"12\" ],\r\n  \"model\" : [ \"12\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '0', null, '2019-06-26 09:49:16');
INSERT INTO `sys_oper_log` VALUES ('361', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"123\" ],\r\n  \"itemTypeName\" : [ \"123\" ]\r\n}', '0', null, '2019-06-26 11:11:14');
INSERT INTO `sys_oper_log` VALUES ('362', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"7\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ]\r\n}', '0', null, '2019-06-26 11:13:29');
INSERT INTO `sys_oper_log` VALUES ('363', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/8', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 13:58:28');
INSERT INTO `sys_oper_log` VALUES ('364', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"123\" ],\r\n  \"itemTypeName\" : [ \"123\" ]\r\n}', '0', null, '2019-06-26 15:07:15');
INSERT INTO `sys_oper_log` VALUES ('365', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"10\" ],\r\n  \"itemTypeCode\" : [ \"1234\" ],\r\n  \"itemTypeName\" : [ \"1234\" ]\r\n}', '0', null, '2019-06-26 15:55:20');
INSERT INTO `sys_oper_log` VALUES ('366', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"5\" ],\r\n  \"itemTypeCode\" : [ \"555\" ],\r\n  \"itemTypeName\" : [ \"555\" ]\r\n}', '0', null, '2019-06-26 15:56:52');
INSERT INTO `sys_oper_log` VALUES ('367', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/10', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 15:57:00');
INSERT INTO `sys_oper_log` VALUES ('368', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/7', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 15:57:02');
INSERT INTO `sys_oper_log` VALUES ('369', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/12', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 15:57:05');
INSERT INTO `sys_oper_log` VALUES ('370', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/9', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 15:57:07');
INSERT INTO `sys_oper_log` VALUES ('371', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/11', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-26 15:57:08');
INSERT INTO `sys_oper_log` VALUES ('372', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"markeloff\" ],\r\n  \"itemTypeName\" : [ \"markeloff\" ]\r\n}', '0', null, '2019-06-26 15:57:21');
INSERT INTO `sys_oper_log` VALUES ('373', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"13\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ]\r\n}', '0', null, '2019-06-26 15:59:21');
INSERT INTO `sys_oper_log` VALUES ('374', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"14\" ],\r\n  \"itemTypeCode\" : [ \"222\" ],\r\n  \"itemTypeName\" : [ \"222\" ]\r\n}', '0', null, '2019-06-26 15:59:40');
INSERT INTO `sys_oper_log` VALUES ('375', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"15\" ],\r\n  \"itemName\" : [ \"23\" ],\r\n  \"spec\" : [ \"23\" ],\r\n  \"model\" : [ \"23\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '0', null, '2019-06-26 17:19:49');
INSERT INTO `sys_oper_log` VALUES ('376', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"15\" ],\r\n  \"itemName\" : [ \"12\" ],\r\n  \"spec\" : [ \"12\" ],\r\n  \"model\" : [ \"12\" ],\r\n  \"thickness\" : [ \"12\" ]\r\n}', '0', null, '2019-06-26 17:23:42');
INSERT INTO `sys_oper_log` VALUES ('377', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"15\" ],\r\n  \"itemName\" : [ \"1\" ],\r\n  \"spec\" : [ \"1\" ],\r\n  \"model\" : [ \"1\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '0', null, '2019-06-26 17:26:04');
INSERT INTO `sys_oper_log` VALUES ('378', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"15\" ],\r\n  \"itemName\" : [ \"1\" ],\r\n  \"spec\" : [ \"1\" ],\r\n  \"model\" : [ \"1\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'item_id\' in \'field list\'\r\n### The error may involve com.deer.wms.system.dao.item.ItemInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO bill_in_detail  ( item_id,item_name,spec,model,thickness,item_type_id ) VALUES( ?,?,?,?,?,? )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'item_id\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'item_id\' in \'field list\'', '2019-06-26 17:28:29');
INSERT INTO `sys_oper_log` VALUES ('379', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"15\" ],\r\n  \"itemName\" : [ \"1\" ],\r\n  \"spec\" : [ \"1\" ],\r\n  \"model\" : [ \"1\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '0', null, '2019-06-26 17:30:08');
INSERT INTO `sys_oper_log` VALUES ('380', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"5\" ],\r\n  \"itemName\" : [ \"2222\" ],\r\n  \"spec\" : [ \"2222\" ],\r\n  \"model\" : [ \"2222\" ],\r\n  \"thickness\" : [ \"\" ]\r\n}', '0', null, '2019-06-26 17:30:24');
INSERT INTO `sys_oper_log` VALUES ('381', '物料', '2', 'com.deer.wms.admin.controller.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"14\" ],\r\n  \"itemId\" : [ \"24\" ],\r\n  \"itemCode\" : [ \"\" ],\r\n  \"itemName\" : [ \"1\" ],\r\n  \"spec\" : [ \"1\" ],\r\n  \"model\" : [ \"1\" ],\r\n  \"thickness\" : [ \"1.0\" ]\r\n}', '0', null, '2019-06-26 17:33:14');
INSERT INTO `sys_oper_log` VALUES ('382', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"14\" ],\r\n  \"itemTypeCode\" : [ \"333\" ],\r\n  \"itemTypeName\" : [ \"333\" ]\r\n}', '0', null, '2019-06-27 09:21:36');
INSERT INTO `sys_oper_log` VALUES ('383', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"14\" ],\r\n  \"itemTypeCode\" : [ \"444\" ],\r\n  \"itemTypeName\" : [ \"444\" ]\r\n}', '0', null, '2019-06-27 09:21:41');
INSERT INTO `sys_oper_log` VALUES ('384', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"14\" ],\r\n  \"itemTypeCode\" : [ \"555\" ],\r\n  \"itemTypeName\" : [ \"555\" ]\r\n}', '0', null, '2019-06-27 09:21:45');
INSERT INTO `sys_oper_log` VALUES ('385', '部门管理', '2', 'com.deer.wms.admin.controller.system.SysDeptController.editSave()', '1', 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"104\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"深圳总公司\" ],\r\n  \"deptName\" : [ \"市场部门\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"若依\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-06-27 09:25:31');
INSERT INTO `sys_oper_log` VALUES ('386', '部门管理', '2', 'com.deer.wms.admin.controller.system.SysDeptController.editSave()', '1', 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"parentId\" : [ \"101\" ],\r\n  \"parentName\" : [ \"深圳总公司\" ],\r\n  \"deptName\" : [ \"研发部门\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"leader\" : [ \"若依\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-06-27 09:25:41');
INSERT INTO `sys_oper_log` VALUES ('387', '物料分类', '2', 'com.deer.wms.admin.controller.item.ItemTypeController.editSave()', '1', 'admin', '研发部门', '/system/itemType/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"16\", \"16\" ],\r\n  \"parentId\" : [ \"5\" ],\r\n  \"parentName\" : [ \"工器具\" ],\r\n  \"itemTypeCode\" : [ \"333\" ],\r\n  \"itemTypeName\" : [ \"333\" ]\r\n}', '0', null, '2019-06-27 09:30:14');
INSERT INTO `sys_oper_log` VALUES ('388', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"16\" ],\r\n  \"itemTypeCode\" : [ \"3333\" ],\r\n  \"itemTypeName\" : [ \"3333\" ]\r\n}', '0', null, '2019-06-27 09:32:10');
INSERT INTO `sys_oper_log` VALUES ('389', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"15\" ],\r\n  \"itemTypeCode\" : [ \"2222\" ],\r\n  \"itemTypeName\" : [ \"2222\" ]\r\n}', '0', null, '2019-06-27 09:32:16');
INSERT INTO `sys_oper_log` VALUES ('390', '物料分类', '2', 'com.deer.wms.admin.controller.item.ItemTypeController.editSave()', '1', 'admin', '研发部门', '/system/itemType/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"5\", \"5\" ],\r\n  \"parentId\" : [ \"4\" ],\r\n  \"parentName\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"工器具\" ],\r\n  \"itemTypeName\" : [ \"工器具\" ]\r\n}', '0', null, '2019-06-27 09:33:22');
INSERT INTO `sys_oper_log` VALUES ('391', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"工具类1\" ],\r\n  \"itemTypeName\" : [ \"工具类1\" ]\r\n}', '0', null, '2019-06-27 09:35:21');
INSERT INTO `sys_oper_log` VALUES ('392', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"markeloff1\" ],\r\n  \"itemTypeName\" : [ \"markeloff1\" ]\r\n}', '0', null, '2019-06-27 09:35:36');
INSERT INTO `sys_oper_log` VALUES ('393', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/22', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 09:35:45');
INSERT INTO `sys_oper_log` VALUES ('394', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/21', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 09:35:47');
INSERT INTO `sys_oper_log` VALUES ('395', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/13', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:10:29');
INSERT INTO `sys_oper_log` VALUES ('396', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/5', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:10:31');
INSERT INTO `sys_oper_log` VALUES ('397', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/14', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:07');
INSERT INTO `sys_oper_log` VALUES ('398', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/15', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:08');
INSERT INTO `sys_oper_log` VALUES ('399', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/19', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:10');
INSERT INTO `sys_oper_log` VALUES ('400', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/16', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:14');
INSERT INTO `sys_oper_log` VALUES ('401', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/17', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:16');
INSERT INTO `sys_oper_log` VALUES ('402', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/18', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:18');
INSERT INTO `sys_oper_log` VALUES ('403', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/20', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:21:19');
INSERT INTO `sys_oper_log` VALUES ('404', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"1\" ],\r\n  \"itemTypeName\" : [ \"1\" ],\r\n  \"orderNum\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 10:21:58');
INSERT INTO `sys_oper_log` VALUES ('405', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"3\" ],\r\n  \"itemTypeName\" : [ \"3\" ],\r\n  \"orderNum\" : [ \"3\" ]\r\n}', '0', null, '2019-06-27 10:22:06');
INSERT INTO `sys_oper_log` VALUES ('406', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"2\" ],\r\n  \"itemTypeName\" : [ \"2\" ],\r\n  \"orderNum\" : [ \"2\" ]\r\n}', '0', null, '2019-06-27 10:22:11');
INSERT INTO `sys_oper_log` VALUES ('407', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"23\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"11\" ],\r\n  \"orderNum\" : [ \"11\" ]\r\n}', '0', null, '2019-06-27 10:22:30');
INSERT INTO `sys_oper_log` VALUES ('408', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"26\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ],\r\n  \"orderNum\" : [ \"111\" ]\r\n}', '0', null, '2019-06-27 10:22:41');
INSERT INTO `sys_oper_log` VALUES ('409', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/23', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 10:22:51');
INSERT INTO `sys_oper_log` VALUES ('410', '物料分类', '2', 'com.deer.wms.admin.controller.item.ItemTypeController.editSave()', '1', 'admin', '研发部门', '/system/itemType/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"27\", \"27\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"parentName\" : [ \"无\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ],\r\n  \"orderNum\" : [ \"111\" ]\r\n}', '0', null, '2019-06-27 10:23:13');
INSERT INTO `sys_oper_log` VALUES ('411', '物料分类', '2', 'com.deer.wms.admin.controller.item.ItemTypeController.editSave()', '1', 'admin', '研发部门', '/system/itemType/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"26\", \"26\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"parentName\" : [ \"无\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"11\" ],\r\n  \"orderNum\" : [ \"11\" ]\r\n}', '0', null, '2019-06-27 10:23:26');
INSERT INTO `sys_oper_log` VALUES ('412', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/24', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 11:14:47');
INSERT INTO `sys_oper_log` VALUES ('413', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/26', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 11:25:33');
INSERT INTO `sys_oper_log` VALUES ('414', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/27', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 11:25:35');
INSERT INTO `sys_oper_log` VALUES ('415', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/25', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 11:25:37');
INSERT INTO `sys_oper_log` VALUES ('416', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/24', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 11:25:38');
INSERT INTO `sys_oper_log` VALUES ('417', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"1\" ],\r\n  \"itemTypeName\" : [ \"1\" ],\r\n  \"orderNum\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 11:26:14');
INSERT INTO `sys_oper_log` VALUES ('418', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"28\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"11\" ],\r\n  \"orderNum\" : [ \"11\" ]\r\n}', '0', null, '2019-06-27 11:26:20');
INSERT INTO `sys_oper_log` VALUES ('419', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"29\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ],\r\n  \"orderNum\" : [ \"111\" ]\r\n}', '0', null, '2019-06-27 11:26:26');
INSERT INTO `sys_oper_log` VALUES ('420', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"2\" ],\r\n  \"itemTypeName\" : [ \"2\" ],\r\n  \"orderNum\" : [ \"2\" ]\r\n}', '0', null, '2019-06-27 11:26:56');
INSERT INTO `sys_oper_log` VALUES ('421', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"31\" ],\r\n  \"itemTypeCode\" : [ \"22\" ],\r\n  \"itemTypeName\" : [ \"22\" ],\r\n  \"orderNum\" : [ \"22\" ]\r\n}', '0', null, '2019-06-27 11:27:09');
INSERT INTO `sys_oper_log` VALUES ('422', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"32\" ],\r\n  \"itemTypeCode\" : [ \"222\" ],\r\n  \"itemTypeName\" : [ \"222\" ],\r\n  \"orderNum\" : [ \"222\" ]\r\n}', '0', null, '2019-06-27 11:27:17');
INSERT INTO `sys_oper_log` VALUES ('423', '物料分类', '2', 'com.deer.wms.admin.controller.item.ItemTypeController.editSave()', '1', 'admin', '研发部门', '/system/itemType/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"30\", \"30\" ],\r\n  \"parentId\" : [ \"29\" ],\r\n  \"parentName\" : [ \"11\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ],\r\n  \"orderNum\" : [ \"1111\" ]\r\n}', '0', null, '2019-06-27 11:31:27');
INSERT INTO `sys_oper_log` VALUES ('424', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"28\" ],\r\n  \"itemTypeCode\" : [ \"22\" ],\r\n  \"itemTypeName\" : [ \"22\" ],\r\n  \"orderNum\" : [ \"2\" ]\r\n}', '0', null, '2019-06-27 11:32:01');
INSERT INTO `sys_oper_log` VALUES ('425', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"30\" ],\r\n  \"itemTypeCode\" : [ \"1111\" ],\r\n  \"itemTypeName\" : [ \"1111\" ],\r\n  \"orderNum\" : [ \"1111\" ]\r\n}', '0', null, '2019-06-27 13:42:40');
INSERT INTO `sys_oper_log` VALUES ('426', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/35', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 13:43:38');
INSERT INTO `sys_oper_log` VALUES ('427', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/35', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 13:43:38');
INSERT INTO `sys_oper_log` VALUES ('428', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/28', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 13:44:02');
INSERT INTO `sys_oper_log` VALUES ('429', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/29', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 13:44:08');
INSERT INTO `sys_oper_log` VALUES ('430', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/30', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 13:44:09');
INSERT INTO `sys_oper_log` VALUES ('431', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/34', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 13:44:10');
INSERT INTO `sys_oper_log` VALUES ('432', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/33', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 14:27:31');
INSERT INTO `sys_oper_log` VALUES ('433', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/32', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 14:50:37');
INSERT INTO `sys_oper_log` VALUES ('434', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/31', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-06-27 14:54:45');
INSERT INTO `sys_oper_log` VALUES ('435', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/31', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 14:56:26');
INSERT INTO `sys_oper_log` VALUES ('436', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/31', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 14:56:31');
INSERT INTO `sys_oper_log` VALUES ('437', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"1\" ],\r\n  \"itemTypeName\" : [ \"1\" ],\r\n  \"orderNum\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 14:56:54');
INSERT INTO `sys_oper_log` VALUES ('438', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"36\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"11\" ],\r\n  \"orderNum\" : [ \"11\" ]\r\n}', '0', null, '2019-06-27 14:56:58');
INSERT INTO `sys_oper_log` VALUES ('439', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"37\" ],\r\n  \"itemTypeCode\" : [ \"111\" ],\r\n  \"itemTypeName\" : [ \"111\" ],\r\n  \"orderNum\" : [ \"111\" ]\r\n}', '0', null, '2019-06-27 14:57:03');
INSERT INTO `sys_oper_log` VALUES ('440', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '1', 'java.lang.Long cannot be cast to java.lang.Integer', '2019-06-27 14:57:05');
INSERT INTO `sys_oper_log` VALUES ('441', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-06-27 15:10:46');
INSERT INTO `sys_oper_log` VALUES ('442', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:12:10');
INSERT INTO `sys_oper_log` VALUES ('443', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/31', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:12:19');
INSERT INTO `sys_oper_log` VALUES ('444', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:12:21');
INSERT INTO `sys_oper_log` VALUES ('445', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/38', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:12:23');
INSERT INTO `sys_oper_log` VALUES ('446', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/31', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:15:20');
INSERT INTO `sys_oper_log` VALUES ('447', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:15:22');
INSERT INTO `sys_oper_log` VALUES ('448', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/38', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:15:24');
INSERT INTO `sys_oper_log` VALUES ('449', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:15:30');
INSERT INTO `sys_oper_log` VALUES ('450', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"3\" ],\r\n  \"itemTypeName\" : [ \"3\" ],\r\n  \"orderNum\" : [ \"3\" ]\r\n}', '0', null, '2019-06-27 15:16:04');
INSERT INTO `sys_oper_log` VALUES ('451', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"4\" ],\r\n  \"itemTypeName\" : [ \"4\" ],\r\n  \"orderNum\" : [ \"4\" ]\r\n}', '0', null, '2019-06-27 15:16:08');
INSERT INTO `sys_oper_log` VALUES ('452', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"5\" ],\r\n  \"itemTypeName\" : [ \"5\" ],\r\n  \"orderNum\" : [ \"5\" ]\r\n}', '0', null, '2019-06-27 15:16:13');
INSERT INTO `sys_oper_log` VALUES ('453', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:21:37');
INSERT INTO `sys_oper_log` VALUES ('454', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"36\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"11\" ],\r\n  \"orderNum\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 15:21:53');
INSERT INTO `sys_oper_log` VALUES ('455', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"42\" ],\r\n  \"itemTypeCode\" : [ \"33\" ],\r\n  \"itemTypeName\" : [ \"33\" ],\r\n  \"orderNum\" : [ \"3\" ]\r\n}', '0', null, '2019-06-27 15:21:59');
INSERT INTO `sys_oper_log` VALUES ('456', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/43', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:22:04');
INSERT INTO `sys_oper_log` VALUES ('457', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/42', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:22:06');
INSERT INTO `sys_oper_log` VALUES ('458', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/42', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:22:09');
INSERT INTO `sys_oper_log` VALUES ('459', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:22:10');
INSERT INTO `sys_oper_log` VALUES ('460', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/31', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:22:13');
INSERT INTO `sys_oper_log` VALUES ('461', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/38', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:23:59');
INSERT INTO `sys_oper_log` VALUES ('462', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"36\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"111\" ],\r\n  \"orderNum\" : [ \"11\" ]\r\n}', '0', null, '2019-06-27 15:24:04');
INSERT INTO `sys_oper_log` VALUES ('463', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"44\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"21\" ],\r\n  \"orderNum\" : [ \"11\" ]\r\n}', '0', null, '2019-06-27 15:24:07');
INSERT INTO `sys_oper_log` VALUES ('464', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/36', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:24:10');
INSERT INTO `sys_oper_log` VALUES ('465', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"31\" ],\r\n  \"itemTypeCode\" : [ \"131\" ],\r\n  \"itemTypeName\" : [ \"3123\" ],\r\n  \"orderNum\" : [ \"31212\" ]\r\n}', '0', null, '2019-06-27 15:25:05');
INSERT INTO `sys_oper_log` VALUES ('466', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"46\" ],\r\n  \"itemTypeCode\" : [ \"13\" ],\r\n  \"itemTypeName\" : [ \"12\" ],\r\n  \"orderNum\" : [ \"311\" ]\r\n}', '0', null, '2019-06-27 15:25:09');
INSERT INTO `sys_oper_log` VALUES ('467', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"46\" ],\r\n  \"itemTypeCode\" : [ \"3123\" ],\r\n  \"itemTypeName\" : [ \"13\" ],\r\n  \"orderNum\" : [ \"131\" ]\r\n}', '0', null, '2019-06-27 15:25:12');
INSERT INTO `sys_oper_log` VALUES ('468', '物料分类', '3', 'com.deer.wms.admin.controller.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/46', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-06-27 15:25:15');
INSERT INTO `sys_oper_log` VALUES ('469', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"31\" ],\r\n  \"itemName\" : [ \"1\" ],\r\n  \"spec\" : [ \"1\" ],\r\n  \"model\" : [ \"1\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 16:00:49');
INSERT INTO `sys_oper_log` VALUES ('470', '物料分类', '2', 'com.deer.wms.admin.controller.item.ItemTypeController.editSave()', '1', 'admin', '研发部门', '/system/itemType/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"31\", \"31\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"parentName\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"1\" ],\r\n  \"itemTypeName\" : [ \"1\" ],\r\n  \"orderNum\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 16:45:49');
INSERT INTO `sys_oper_log` VALUES ('471', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"31\" ],\r\n  \"itemTypeCode\" : [ \"11\" ],\r\n  \"itemTypeName\" : [ \"11\" ],\r\n  \"orderNum\" : [ \"2\" ]\r\n}', '0', null, '2019-06-27 16:46:00');
INSERT INTO `sys_oper_log` VALUES ('472', '物料分类', '1', 'com.deer.wms.admin.controller.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"31\" ],\r\n  \"itemTypeCode\" : [ \"22\" ],\r\n  \"itemTypeName\" : [ \"22\" ],\r\n  \"orderNum\" : [ \"1\" ]\r\n}', '0', null, '2019-06-27 16:46:05');
INSERT INTO `sys_oper_log` VALUES ('473', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"11\" ],\r\n  \"areaName\" : [ \"11\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-06-28 09:36:30');
INSERT INTO `sys_oper_log` VALUES ('474', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"22\" ],\r\n  \"shelfName\" : [ \"22\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-06-28 09:36:55');
INSERT INTO `sys_oper_log` VALUES ('475', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"22\" ],\r\n  \"areaName\" : [ \"22\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"22\" ]\r\n}', '0', null, '2019-06-28 10:02:15');
INSERT INTO `sys_oper_log` VALUES ('476', '货区设置', '1', 'com.deer.wms.web.controller.ware.AreaInfoController.addSave()', '1', 'admin', '研发部门', '/system/areaInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"areaCode\" : [ \"33\" ],\r\n  \"areaName\" : [ \"33\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"33\" ]\r\n}', '0', null, '2019-06-28 10:02:18');
INSERT INTO `sys_oper_log` VALUES ('477', '出入库口', '3', 'com.deer.wms.web.controller.ware.DoorController.remove()', '1', 'admin', '研发部门', '/system/door/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2019-06-28 10:02:57');
INSERT INTO `sys_oper_log` VALUES ('478', '出入库口', '3', 'com.deer.wms.web.controller.ware.DoorController.remove()', '1', 'admin', '研发部门', '/system/door/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', '0', null, '2019-06-28 10:02:59');
INSERT INTO `sys_oper_log` VALUES ('479', '出入库口', '3', 'com.deer.wms.web.controller.ware.DoorController.remove()', '1', 'admin', '研发部门', '/system/door/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '0', null, '2019-06-28 10:03:00');
INSERT INTO `sys_oper_log` VALUES ('480', '出入库口', '1', 'com.deer.wms.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"\" ],\r\n  \"name\" : [ \"出入库口1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-06-28 10:03:08');
INSERT INTO `sys_oper_log` VALUES ('481', '出入库口', '3', 'com.deer.wms.web.controller.ware.DoorController.remove()', '1', 'admin', '研发部门', '/system/door/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '0', null, '2019-06-28 10:03:16');
INSERT INTO `sys_oper_log` VALUES ('482', '出入库口', '1', 'com.deer.wms.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"出入库口1\" ],\r\n  \"name\" : [ \"出入库口1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-06-28 10:03:23');
INSERT INTO `sys_oper_log` VALUES ('483', '出入库口', '1', 'com.deer.wms.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"出入库口1\" ],\r\n  \"name\" : [ \"出入库口1\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-06-28 10:05:16');
INSERT INTO `sys_oper_log` VALUES ('484', '出入库口', '3', 'com.deer.wms.web.controller.ware.DoorController.remove()', '1', 'admin', '研发部门', '/system/door/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"6\" ]\r\n}', '0', null, '2019-06-28 10:05:22');
INSERT INTO `sys_oper_log` VALUES ('485', '出入库口', '1', 'com.deer.wms.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"\" ],\r\n  \"name\" : [ \"出入库口2\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-06-28 10:05:27');
INSERT INTO `sys_oper_log` VALUES ('486', '出入库口', '1', 'com.deer.wms.web.controller.ware.DoorController.addSave()', '1', 'admin', '研发部门', '/system/door/add', '127.0.0.1', '内网IP', '{\r\n  \"code\" : [ \"\" ],\r\n  \"name\" : [ \"出入库口3\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-06-28 10:05:36');
INSERT INTO `sys_oper_log` VALUES ('487', '出入库口', '3', 'com.deer.wms.web.controller.ware.DoorController.remove()', '1', 'admin', '研发部门', '/system/door/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"8\" ]\r\n}', '0', null, '2019-06-28 10:31:42');
INSERT INTO `sys_oper_log` VALUES ('488', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"33\" ],\r\n  \"shelfName\" : [ \"33\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"3\" ],\r\n  \"shelfColumn\" : [ \"3\" ],\r\n  \"memo\" : [ \"9\" ]\r\n}', '0', null, '2019-06-28 11:16:43');
INSERT INTO `sys_oper_log` VALUES ('489', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"44\" ],\r\n  \"shelfName\" : [ \"44\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"4\" ],\r\n  \"shelfColumn\" : [ \"4\" ],\r\n  \"memo\" : [ \"16\" ]\r\n}', '0', null, '2019-06-28 11:16:51');
INSERT INTO `sys_oper_log` VALUES ('490', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"222\" ],\r\n  \"shelfName\" : [ \"222\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"20\" ],\r\n  \"memo\" : [ \"44444\" ]\r\n}', '0', null, '2019-06-28 14:16:44');
INSERT INTO `sys_oper_log` VALUES ('491', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"333\" ],\r\n  \"shelfName\" : [ \"333\" ],\r\n  \"areaId\" : [ \"249\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"20\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-28 14:19:38');
INSERT INTO `sys_oper_log` VALUES ('492', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"555\" ],\r\n  \"shelfName\" : [ \"555\" ],\r\n  \"areaId\" : [ \"249\" ],\r\n  \"shelfRow\" : [ \"4\" ],\r\n  \"shelfColumn\" : [ \"20\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-06-28 14:27:30');
INSERT INTO `sys_oper_log` VALUES ('493', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"25\" ]\r\n}', '0', null, '2019-06-28 15:29:01');
INSERT INTO `sys_oper_log` VALUES ('494', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"24\" ]\r\n}', '0', null, '2019-06-28 15:29:04');
INSERT INTO `sys_oper_log` VALUES ('495', '货位设置', '2', 'com.deer.wms.web.controller.ware.CellInfoController.editSave()', '1', 'admin', '研发部门', '/system/cellInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"cellId\" : [ \"389\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"state\" : [ \"1\" ]\r\n}', '0', null, '2019-06-28 16:34:07');
INSERT INTO `sys_oper_log` VALUES ('496', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"27\" ]\r\n}', '0', null, '2019-06-29 10:00:28');
INSERT INTO `sys_oper_log` VALUES ('497', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"26\" ]\r\n}', '0', null, '2019-06-29 10:00:29');
INSERT INTO `sys_oper_log` VALUES ('498', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"100\" ],\r\n  \"shelfName\" : [ \"100\" ],\r\n  \"areaId\" : [ \"250\" ],\r\n  \"shelfRow\" : [ \"10\" ],\r\n  \"shelfColumn\" : [ \"10\" ],\r\n  \"memo\" : [ \"100\" ]\r\n}', '0', null, '2019-06-29 10:01:07');
INSERT INTO `sys_oper_log` VALUES ('499', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"\" ],\r\n  \"shelfName\" : [ \"25\" ],\r\n  \"areaId\" : [ \"250\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-06-29 10:02:21');
INSERT INTO `sys_oper_log` VALUES ('500', '货位设置', '2', 'com.deer.wms.web.controller.ware.CellInfoController.editSave()', '1', 'admin', '研发部门', '/system/cellInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"cellId\" : [ \"469\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"state\" : [ \"1\" ]\r\n}', '0', null, '2019-06-29 10:03:54');
INSERT INTO `sys_oper_log` VALUES ('501', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"22\" ]\r\n}', '0', null, '2019-07-01 09:49:02');
INSERT INTO `sys_oper_log` VALUES ('502', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"23\" ]\r\n}', '0', null, '2019-07-01 09:49:04');
INSERT INTO `sys_oper_log` VALUES ('503', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"28\" ]\r\n}', '0', null, '2019-07-01 09:49:05');
INSERT INTO `sys_oper_log` VALUES ('504', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"29\" ]\r\n}', '0', null, '2019-07-01 09:49:07');
INSERT INTO `sys_oper_log` VALUES ('505', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"55\" ],\r\n  \"shelfName\" : [ \"m4\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"4\" ],\r\n  \"shelfColumn\" : [ \"4\" ],\r\n  \"memo\" : [ \"16\" ]\r\n}', '0', null, '2019-07-01 09:49:31');
INSERT INTO `sys_oper_log` VALUES ('506', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"30\" ]\r\n}', '0', null, '2019-07-01 15:50:19');
INSERT INTO `sys_oper_log` VALUES ('507', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"50\" ],\r\n  \"shelfName\" : [ \"50\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"10\" ],\r\n  \"memo\" : [ \"50\" ]\r\n}', '0', null, '2019-07-01 15:51:08');
INSERT INTO `sys_oper_log` VALUES ('508', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"20\" ],\r\n  \"shelfName\" : [ \"20\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"2\" ],\r\n  \"shelfColumn\" : [ \"10\" ],\r\n  \"memo\" : [ \"20\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure\n\nThe last packet successfully received from the server was 334,720 milliseconds ago.  The last packet sent successfully to the server was 334,741 milliseconds ago.\r\n### The error may involve com.deer.wms.base.system.dao.BoxInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO box_info  ( box_id,box_cell_id ) VALUES( ?,? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure\n\nThe last packet successfully received from the server was 334,720 milliseconds ago.  The last packet sent successfully to the server was 334,741 milliseconds ago.\n; Communications link failure\n\nThe last packet successfully received from the server was 334,720 milliseconds ago.  The last packet sent successfully to the server was 334,741 milliseconds ago.; nested exception is com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure\n\nThe last packet successfully received from the server was 334,720 milliseconds ago.  The last packet sent successfully to the server was 334,741 milliseconds ago.', '2019-07-01 16:03:28');
INSERT INTO `sys_oper_log` VALUES ('509', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"2\" ],\r\n  \"shelfName\" : [ \"2\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"2\" ],\r\n  \"shelfColumn\" : [ \"2\" ],\r\n  \"memo\" : [ \"2\" ]\r\n}', '1', '', '2019-07-01 16:06:30');
INSERT INTO `sys_oper_log` VALUES ('510', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"20\" ],\r\n  \"shelfName\" : [ \"20\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"10\" ],\r\n  \"memo\" : [ \"20\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.executor.ExecutorException: A query was run and no Result Maps were found for the Mapped Statement \'com.deer.wms.system.dao.ware.CellInfoMapper.selectMaxCellInfoId\'.  It\'s likely that neither a Result Type nor a Result Map was specified.', '2019-07-01 16:16:47');
INSERT INTO `sys_oper_log` VALUES ('511', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"20\" ],\r\n  \"shelfName\" : [ \"20\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"10\" ],\r\n  \"memo\" : [ \"20\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE)\r\n### The error may involve com.deer.wms.base.system.dao.BoxInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO box_info  ( box_id,box_cell_id ) VALUES( ?,? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE)\n; Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE); nested exception is java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE)', '2019-07-01 16:20:11');
INSERT INTO `sys_oper_log` VALUES ('512', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"\" ],\r\n  \"shelfName\" : [ \"20\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"10\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE)\r\n### The error may involve com.deer.wms.base.system.dao.BoxInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO box_info  ( box_id,box_cell_id ) VALUES( ?,? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE)\n; Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE); nested exception is java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wuxi_wms`.`box_info`, CONSTRAINT `box_info_ibfk_1` FOREIGN KEY (`box_cell_id`) REFERENCES `cell_info` (`cell_id`) ON DELETE SET NULL ON UPDATE CASCADE)', '2019-07-01 16:25:56');
INSERT INTO `sys_oper_log` VALUES ('513', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"25\" ],\r\n  \"shelfName\" : [ \"25\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"5\" ],\r\n  \"shelfColumn\" : [ \"5\" ],\r\n  \"memo\" : [ \"25\" ]\r\n}', '0', null, '2019-07-01 16:33:57');
INSERT INTO `sys_oper_log` VALUES ('514', '物料', '1', 'com.deer.wms.admin.controller.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"31\" ],\r\n  \"itemName\" : [ \"markeloff\" ],\r\n  \"spec\" : [ \"55\" ],\r\n  \"model\" : [ \"55\" ],\r\n  \"thickness\" : [ \"55\" ]\r\n}', '0', null, '2019-07-05 08:53:45');
INSERT INTO `sys_oper_log` VALUES ('515', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-05 08:54:55');
INSERT INTO `sys_oper_log` VALUES ('516', '物料', '2', 'com.deer.wms.admin.controller.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"49\" ],\r\n  \"itemId\" : [ \"27\" ],\r\n  \"itemCode\" : [ \"markeloff\" ],\r\n  \"itemName\" : [ \"markeloff\" ],\r\n  \"spec\" : [ \"55\" ],\r\n  \"model\" : [ \"55\" ],\r\n  \"thickness\" : [ \"55.0\" ]\r\n}', '0', null, '2019-07-05 08:55:54');
INSERT INTO `sys_oper_log` VALUES ('517', '入库单', '1', 'com.deer.wms.web.controller.bill.BillInMasterController.insert()', '1', 'admin', '研发部门', '/in/billInMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-05 09:11:35');
INSERT INTO `sys_oper_log` VALUES ('518', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"249\" ]\r\n}', '0', null, '2019-07-05 16:52:54');
INSERT INTO `sys_oper_log` VALUES ('519', '货区设置', '3', 'com.deer.wms.web.controller.ware.AreaInfoController.remove()', '1', 'admin', '研发部门', '/system/areaInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"250\" ]\r\n}', '0', null, '2019-07-05 16:52:55');
INSERT INTO `sys_oper_log` VALUES ('520', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"31\" ]\r\n}', '0', null, '2019-07-05 16:52:59');
INSERT INTO `sys_oper_log` VALUES ('521', '货架设置', '3', 'com.deer.wms.web.controller.ware.ShelfInfoController.remove()', '1', 'admin', '研发部门', '/system/shelfInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"38\" ]\r\n}', '0', null, '2019-07-05 16:53:00');
INSERT INTO `sys_oper_log` VALUES ('522', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"01\" ],\r\n  \"shelfName\" : [ \"01\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"21\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-07-05 16:54:52');
INSERT INTO `sys_oper_log` VALUES ('523', '货架设置', '1', 'com.deer.wms.web.controller.ware.ShelfInfoController.addSave()', '1', 'admin', '研发部门', '/system/shelfInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"shelfCode\" : [ \"02\" ],\r\n  \"shelfName\" : [ \"02\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfRow\" : [ \"21\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-07-05 16:57:27');
INSERT INTO `sys_oper_log` VALUES ('524', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"学生\" ],\r\n  \"url\" : [ \"/student/test\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-05 21:16:56');
INSERT INTO `sys_oper_log` VALUES ('525', '代码生成', '8', 'com.deer.wms.generator.controller.GenController.genCode()', '1', 'admin', '研发部门', '/tool/gen/genCode/student', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-07 10:37:01');
INSERT INTO `sys_oper_log` VALUES ('526', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"出库管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-calculator\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 13:55:45');
INSERT INTO `sys_oper_log` VALUES ('527', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2038\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"入库管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-calculator\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:01:56');
INSERT INTO `sys_oper_log` VALUES ('528', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2049\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-plane\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:02:06');
INSERT INTO `sys_oper_log` VALUES ('529', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统监控\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-video-camera\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:02:14');
INSERT INTO `sys_oper_log` VALUES ('530', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"3\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统工具\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-bars\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:02:31');
INSERT INTO `sys_oper_log` VALUES ('531', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"1\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"fa fa-gear\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:02:42');
INSERT INTO `sys_oper_log` VALUES ('532', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"3\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统工具\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"fa fa-bars\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:02:48');
INSERT INTO `sys_oper_log` VALUES ('533', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"系统监控\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"fa fa-video-camera\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:02:54');
INSERT INTO `sys_oper_log` VALUES ('534', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2056\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"出库管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-calculator\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:03:11');
INSERT INTO `sys_oper_log` VALUES ('535', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2038\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"入库管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-calculator\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:03:18');
INSERT INTO `sys_oper_log` VALUES ('536', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2038\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"入库管理\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-calculator\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-09 14:04:18');
INSERT INTO `sys_oper_log` VALUES ('537', '货区设置', '2', 'com.deer.wms.web.controller.ware.AreaInfoController.editSave()', '1', 'admin', '研发部门', '/system/areaInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"areaId\" : [ \"248\" ],\r\n  \"areaCode\" : [ \"11\" ],\r\n  \"areaName\" : [ \"货区1\" ],\r\n  \"wareId\" : [ \"212\" ],\r\n  \"memo\" : [ \"11\" ]\r\n}', '0', null, '2019-07-12 09:20:42');
INSERT INTO `sys_oper_log` VALUES ('538', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2056\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库单\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-12 10:05:28');
INSERT INTO `sys_oper_log` VALUES ('539', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2057\" ],\r\n  \"parentId\" : [ \"2056\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库单\" ],\r\n  \"url\" : [ \"/Out/billInMaster\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-12 10:05:48');
INSERT INTO `sys_oper_log` VALUES ('540', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2057\" ],\r\n  \"parentId\" : [ \"2056\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库单\" ],\r\n  \"url\" : [ \"/Out/billInMaster\" ],\r\n  \"perms\" : [ \"\\tOut:billInMaster:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-12 10:06:58');
INSERT INTO `sys_oper_log` VALUES ('541', '入库单', '3', 'com.deer.wms.web.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"16\" ]\r\n}', '0', null, '2019-07-12 11:46:45');
INSERT INTO `sys_oper_log` VALUES ('542', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2039\" ],\r\n  \"parentId\" : [ \"2038\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库单\" ],\r\n  \"url\" : [ \"/in/billInMaster/page\" ],\r\n  \"perms\" : [ \"in:billInMaster:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-12 12:09:30');
INSERT INTO `sys_oper_log` VALUES ('543', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2057\" ],\r\n  \"parentId\" : [ \"2056\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库单\" ],\r\n  \"url\" : [ \"/Out/billInMaster/page\" ],\r\n  \"perms\" : [ \"\\tOut:billInMaster:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-15 09:36:55');
INSERT INTO `sys_oper_log` VALUES ('544', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2057\" ],\r\n  \"parentId\" : [ \"2056\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库单\" ],\r\n  \"url\" : [ \"/out/billInMaster/page\" ],\r\n  \"perms\" : [ \"out:billInMaster:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-15 09:37:10');
INSERT INTO `sys_oper_log` VALUES ('545', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2057\" ],\r\n  \"parentId\" : [ \"2056\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库单\" ],\r\n  \"url\" : [ \"/out/billOutMaster/page\" ],\r\n  \"perms\" : [ \"out:billOutMaster:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-15 10:10:07');
INSERT INTO `sys_oper_log` VALUES ('546', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-17 13:50:54');
INSERT INTO `sys_oper_log` VALUES ('547', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-17 13:55:32');
INSERT INTO `sys_oper_log` VALUES ('548', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'BillOutMaster\' in \'class com.deer.wms.system.domain.bill.BillOutMaster\'', '2019-07-17 14:11:15');
INSERT INTO `sys_oper_log` VALUES ('549', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'wuxi_wms.bill_our_master\' doesn\'t exist\r\n### The error may involve com.deer.wms.system.dao.bill.BillOutMasterMapper.saveBillOutMaster-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into bill_our_master values           (null, ?, ?, ?,             ?, ?, ?,               ?, ?)\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'wuxi_wms.bill_our_master\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'wuxi_wms.bill_our_master\' doesn\'t exist', '2019-07-17 14:12:47');
INSERT INTO `sys_oper_log` VALUES ('550', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-17 14:14:15');
INSERT INTO `sys_oper_log` VALUES ('551', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', 'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'bill_id\' in \'class com.deer.wms.system.domain.bill.BillOutDetail\'', '2019-07-17 14:37:37');
INSERT INTO `sys_oper_log` VALUES ('552', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-17 14:42:20');
INSERT INTO `sys_oper_log` VALUES ('553', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-17 14:45:34');
INSERT INTO `sys_oper_log` VALUES ('554', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-17 14:47:23');
INSERT INTO `sys_oper_log` VALUES ('555', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-17 15:03:14');
INSERT INTO `sys_oper_log` VALUES ('556', '出库单', '2', 'com.deer.wms.admin.controller.bill.BillOutMasterController.editSave()', '1', 'admin', '研发部门', '/out/billOutMaster/edit', '127.0.0.1', '内网IP', '{\r\n  \"billId\" : [ \"19\" ],\r\n  \"billNo\" : [ \"444\" ],\r\n  \"contractNo\" : [ \"444\" ],\r\n  \"createTime\" : [ \"2019-07-17 14:14:05\" ],\r\n  \"createUserId\" : [ \"1\" ],\r\n  \"state\" : [ \"\" ],\r\n  \"memo\" : [ \"444\" ]\r\n}', '0', null, '2019-07-17 16:59:58');
INSERT INTO `sys_oper_log` VALUES ('557', '出库单', '3', 'com.deer.wms.admin.controller.bill.BillOutMasterController.remove()', '1', 'admin', '研发部门', '/out/billOutMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"19\" ]\r\n}', '0', null, '2019-07-18 10:23:27');
INSERT INTO `sys_oper_log` VALUES ('558', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-18 11:05:49');
INSERT INTO `sys_oper_log` VALUES ('559', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-18 11:08:05');
INSERT INTO `sys_oper_log` VALUES ('560', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-18 11:10:30');
INSERT INTO `sys_oper_log` VALUES ('561', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-18 11:14:11');
INSERT INTO `sys_oper_log` VALUES ('562', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-18 11:19:03');
INSERT INTO `sys_oper_log` VALUES ('563', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-18 11:22:08');
INSERT INTO `sys_oper_log` VALUES ('564', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-07-18 11:37:38');
INSERT INTO `sys_oper_log` VALUES ('565', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-18 11:39:06');
INSERT INTO `sys_oper_log` VALUES ('566', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-18 11:45:26');
INSERT INTO `sys_oper_log` VALUES ('567', '出库单', '3', 'com.deer.wms.admin.controller.bill.BillOutMasterController.remove()', '1', 'admin', '研发部门', '/out/billOutMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"27,29\" ]\r\n}', '0', null, '2019-07-18 11:51:45');
INSERT INTO `sys_oper_log` VALUES ('568', '出库单', '3', 'com.deer.wms.admin.controller.bill.BillOutMasterController.remove()', '1', 'admin', '研发部门', '/out/billOutMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"33\" ]\r\n}', '0', null, '2019-07-18 17:33:20');
INSERT INTO `sys_oper_log` VALUES ('569', '入库单详情', '3', 'com.deer.wms.admin.controller.bill.BillInDetailController.delete()', '1', 'admin', '研发部门', '/in/billInDetail/delete', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"19\" ]\r\n}', '0', null, '2019-07-19 19:12:41');
INSERT INTO `sys_oper_log` VALUES ('570', '入库单', '3', 'com.deer.wms.admin.controller.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"17\" ]\r\n}', '0', null, '2019-07-19 19:12:59');
INSERT INTO `sys_oper_log` VALUES ('571', '出库单', '1', 'com.deer.wms.admin.controller.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-20 13:43:26');
INSERT INTO `sys_oper_log` VALUES ('572', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 16:59:21');
INSERT INTO `sys_oper_log` VALUES ('573', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:05:00');
INSERT INTO `sys_oper_log` VALUES ('574', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:08:49');
INSERT INTO `sys_oper_log` VALUES ('575', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:17:29');
INSERT INTO `sys_oper_log` VALUES ('576', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:18:53');
INSERT INTO `sys_oper_log` VALUES ('577', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:19:21');
INSERT INTO `sys_oper_log` VALUES ('578', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:24:09');
INSERT INTO `sys_oper_log` VALUES ('579', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:29:45');
INSERT INTO `sys_oper_log` VALUES ('580', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:31:43');
INSERT INTO `sys_oper_log` VALUES ('581', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-24 17:33:26');
INSERT INTO `sys_oper_log` VALUES ('582', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"报表查询\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-bar-chart-o\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-25 17:17:17');
INSERT INTO `sys_oper_log` VALUES ('583', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"库存明细\" ],\r\n  \"url\" : [ \"in:boxItem:view\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-25 17:20:36');
INSERT INTO `sys_oper_log` VALUES ('584', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2059\" ],\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"库存明细\" ],\r\n  \"url\" : [ \"in:boxItem:view\" ],\r\n  \"perms\" : [ \"in:boxItem:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-25 17:20:52');
INSERT INTO `sys_oper_log` VALUES ('585', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2059\" ],\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"库存明细\" ],\r\n  \"url\" : [ \"/in/boxItem/page\" ],\r\n  \"perms\" : [ \"in:boxItem:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-07-25 17:22:53');
INSERT INTO `sys_oper_log` VALUES ('586', '出库单', '1', 'com.deer.wms.base.system.web.bill.BillOutMasterController.insert()', '1', 'admin', '研发部门', '/out/billOutMaster/insert', '127.0.0.1', '内网IP', '{ }', '1', '\r\n### Error updating database.  Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\r\n### The error may involve com.deer.wms.base.system.dao.bill.BillOutDetailMapper.saveBillOutDetail-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into bill_out_detail values (null, ?, ?, ?)\r\n### Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\n; bad SQL grammar []; nested exception is java.sql.SQLException: Column count doesn\'t match value count at row 1', '2019-07-27 17:21:47');
INSERT INTO `sys_oper_log` VALUES ('587', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:08:40');
INSERT INTO `sys_oper_log` VALUES ('588', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:11:03');
INSERT INTO `sys_oper_log` VALUES ('589', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:14:38');
INSERT INTO `sys_oper_log` VALUES ('590', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:16:16');
INSERT INTO `sys_oper_log` VALUES ('591', '出库单', '3', 'com.deer.wms.base.system.web.bill.BillOutMasterController.remove()', '1', 'admin', '研发部门', '/out/billOutMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2019-07-29 20:41:45');
INSERT INTO `sys_oper_log` VALUES ('592', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:54:46');
INSERT INTO `sys_oper_log` VALUES ('593', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:54:56');
INSERT INTO `sys_oper_log` VALUES ('594', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-07-29 20:55:06');
INSERT INTO `sys_oper_log` VALUES ('595', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfCode\" : [ \"01\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-08-31 17:09:18');
INSERT INTO `sys_oper_log` VALUES ('596', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfCode\" : [ \"01\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-08-31 17:15:34');
INSERT INTO `sys_oper_log` VALUES ('597', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfCode\" : [ \"01\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-08-31 17:30:41');
INSERT INTO `sys_oper_log` VALUES ('598', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"areaId\" : [ \"248\" ],\r\n  \"shelfCode\" : [ \"01\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-08-31 17:33:03');
INSERT INTO `sys_oper_log` VALUES ('599', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property=\'shelfId\', mode=IN, javaType=class java.lang.String, jdbcType=null, numericScale=null, resultMapId=\'null\', jdbcTypeName=\'null\', expression=\'null\'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #1 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String', '2019-09-02 09:49:15');
INSERT INTO `sys_oper_log` VALUES ('600', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property=\'shelfId\', mode=IN, javaType=class java.lang.String, jdbcType=null, numericScale=null, resultMapId=\'null\', jdbcTypeName=\'null\', expression=\'null\'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #1 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String', '2019-09-02 09:49:47');
INSERT INTO `sys_oper_log` VALUES ('601', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', 'nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property=\'shelfId\', mode=IN, javaType=class java.lang.String, jdbcType=null, numericScale=null, resultMapId=\'null\', jdbcTypeName=\'null\', expression=\'null\'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #1 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String', '2019-09-02 09:50:13');
INSERT INTO `sys_oper_log` VALUES ('602', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2173\' for key \'PRIMARY\'\r\n### The error may involve com.deer.wms.base.system.dao.ware.CellInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO cell_info  ( cell_id,shelf_id,s_row,s_column ) VALUES( ?,?,?,? )\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2173\' for key \'PRIMARY\'\n; Duplicate entry \'2173\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2173\' for key \'PRIMARY\'', '2019-09-02 09:51:46');
INSERT INTO `sys_oper_log` VALUES ('603', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2174\' for key \'PRIMARY\'\r\n### The error may involve com.deer.wms.base.system.dao.ware.CellInfoMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2174\' for key \'PRIMARY\'\n; Duplicate entry \'2174\' for key \'PRIMARY\'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry \'2174\' for key \'PRIMARY\'', '2019-09-02 09:54:41');
INSERT INTO `sys_oper_log` VALUES ('604', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"39\" ],\r\n  \"shelfName\" : [ \"1\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-09-02 10:01:37');
INSERT INTO `sys_oper_log` VALUES ('605', '货架设置', '2', 'com.deer.wms.base.system.web.ware.ShelfInfoController.editSave()', '1', 'admin', '研发部门', '/system/shelfInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"shelfId\" : [ \"40\" ],\r\n  \"shelfName\" : [ \"2\" ],\r\n  \"shelfRow\" : [ \"18\" ],\r\n  \"shelfColumn\" : [ \"31\" ],\r\n  \"memo\" : [ \"\" ]\r\n}', '0', null, '2019-09-02 10:02:07');
INSERT INTO `sys_oper_log` VALUES ('606', '货位设置', '2', 'com.deer.wms.base.system.web.ware.CellInfoController.editSave()', '1', 'admin', '研发部门', '/system/cellInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"cellId\" : [ \"2176\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"state\" : [ \"3\" ]\r\n}', '0', null, '2019-09-02 17:00:26');
INSERT INTO `sys_oper_log` VALUES ('607', '货位设置', '2', 'com.deer.wms.base.system.web.ware.CellInfoController.editSave()', '1', 'admin', '研发部门', '/system/cellInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"cellId\" : [ \"2179\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"state\" : [ \"2\" ]\r\n}', '0', null, '2019-09-02 17:00:35');
INSERT INTO `sys_oper_log` VALUES ('608', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"操作员管理\" ],\r\n  \"url\" : [ \"/system/operator\" ],\r\n  \"perms\" : [ \"system:operator:list\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-03 09:44:21');
INSERT INTO `sys_oper_log` VALUES ('609', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2060\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"操作员管理\" ],\r\n  \"url\" : [ \"/system/operator/operator\" ],\r\n  \"perms\" : [ \"system:operator:list\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-03 09:53:28');
INSERT INTO `sys_oper_log` VALUES ('610', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2060\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"操作员管理\" ],\r\n  \"url\" : [ \"/system/operator/operator\" ],\r\n  \"perms\" : [ \"system:operator:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-03 09:54:50');
INSERT INTO `sys_oper_log` VALUES ('611', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2060\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"操作员管理\" ],\r\n  \"url\" : [ \"/system/operator\" ],\r\n  \"perms\" : [ \"system:operator:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-03 09:55:27');
INSERT INTO `sys_oper_log` VALUES ('612', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-09-03 09:58:47');
INSERT INTO `sys_oper_log` VALUES ('613', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/add', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小吴\" ],\r\n  \"operatorCard\" : [ \"1428722657\" ],\r\n  \"empNo\" : [ \"CARGOD\" ]\r\n}', '0', null, '2019-09-03 11:10:43');
INSERT INTO `sys_oper_log` VALUES ('614', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/add', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小吴\" ],\r\n  \"operatorCard\" : [ \"1428722657\" ],\r\n  \"empNo\" : [ \"CARGOD\" ]\r\n}', '0', null, '2019-09-03 11:11:29');
INSERT INTO `sys_oper_log` VALUES ('615', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小邱\" ],\r\n  \"operatorCard\" : [ \"1996100137\" ],\r\n  \"empNo\" : [ \"clearLove\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearLove\'\' at line 4\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\OperatorMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.OperatorMapper.findList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select * from operator         where 1=1                     operator = ?                               emp_no = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearLove\'\' at line 4\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearLove\'\' at line 4', '2019-09-03 13:17:08');
INSERT INTO `sys_oper_log` VALUES ('616', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小邱\" ],\r\n  \"operatorCard\" : [ \"1996100137\" ],\r\n  \"empNo\" : [ \"clearLove\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearLove\'\' at line 4\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\OperatorMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.OperatorMapper.findList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select * from operator         where 1=1                     operator = ?                               emp_no = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearLove\'\' at line 4\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearLove\'\' at line 4', '2019-09-03 13:17:38');
INSERT INTO `sys_oper_log` VALUES ('617', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小邱\" ],\r\n  \"operatorCard\" : [ \"1996100137\" ],\r\n  \"empNo\" : [ \"clearlove\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearlove\'\' at line 4\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\OperatorMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.OperatorMapper.findList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select * from operator         where 1=1                     operator = ?                               emp_no = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearlove\'\' at line 4\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator = \'小邱\'\n         \n         \n          emp_no = \'clearlove\'\' at line 4', '2019-09-03 13:19:43');
INSERT INTO `sys_oper_log` VALUES ('618', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小邱\" ],\r\n  \"operatorCard\" : [ \"1996100137\" ],\r\n  \"empNo\" : [ \"clearlove7\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator_card = \'1996100137\'\n         \n         \n          emp_no = \'clearlove7\'\' at line 4\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\OperatorMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.OperatorMapper.findList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select * from operator         where 1=1                     operator_card = ?                               emp_no = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator_card = \'1996100137\'\n         \n         \n          emp_no = \'clearlove7\'\' at line 4\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'operator_card = \'1996100137\'\n         \n         \n          emp_no = \'clearlove7\'\' at line 4', '2019-09-03 13:23:45');
INSERT INTO `sys_oper_log` VALUES ('619', '货位设置', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小邱\" ],\r\n  \"operatorCard\" : [ \"1996100137\" ],\r\n  \"empNo\" : [ \"clearlove7\" ]\r\n}', '0', null, '2019-09-03 13:28:14');
INSERT INTO `sys_oper_log` VALUES ('620', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 13:49:24');
INSERT INTO `sys_oper_log` VALUES ('621', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 13:56:12');
INSERT INTO `sys_oper_log` VALUES ('622', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 13:59:53');
INSERT INTO `sys_oper_log` VALUES ('623', '入库单', '3', 'com.deer.wms.base.system.web.bill.BillInMasterController.remove()', '1', 'admin', '研发部门', '/in/billInMaster/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"23\" ]\r\n}', '0', null, '2019-09-03 14:00:06');
INSERT INTO `sys_oper_log` VALUES ('624', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 14:21:50');
INSERT INTO `sys_oper_log` VALUES ('625', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 14:21:59');
INSERT INTO `sys_oper_log` VALUES ('626', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 14:27:06');
INSERT INTO `sys_oper_log` VALUES ('627', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '1', 'null', '2019-09-03 14:34:41');
INSERT INTO `sys_oper_log` VALUES ('628', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"小孙\" ],\r\n  \"operatorCard\" : [ \"1996092737\" ],\r\n  \"empNo\" : [ \"mlxg\" ]\r\n}', '0', null, '2019-09-03 14:37:11');
INSERT INTO `sys_oper_log` VALUES ('629', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:24:28');
INSERT INTO `sys_oper_log` VALUES ('630', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:26:45');
INSERT INTO `sys_oper_log` VALUES ('631', '岗位管理', '5', 'com.deer.wms.admin.controller.system.SysPostController.export()', '1', 'admin', '研发部门', '/system/post/export', '127.0.0.1', '内网IP', '{\r\n  \"postCode\" : [ \"\" ],\r\n  \"postName\" : [ \"\" ],\r\n  \"status\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:30:35');
INSERT INTO `sys_oper_log` VALUES ('632', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:34:16');
INSERT INTO `sys_oper_log` VALUES ('633', '角色管理', '5', 'com.deer.wms.admin.controller.system.SysRoleController.export()', '1', 'admin', '研发部门', '/system/role/export', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"\" ],\r\n  \"roleKey\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:35:54');
INSERT INTO `sys_oper_log` VALUES ('634', '角色管理', '5', 'com.deer.wms.admin.controller.system.SysRoleController.export()', '1', 'admin', '研发部门', '/system/role/export', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"\" ],\r\n  \"roleKey\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:36:00');
INSERT INTO `sys_oper_log` VALUES ('635', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:36:36');
INSERT INTO `sys_oper_log` VALUES ('636', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:37:50');
INSERT INTO `sys_oper_log` VALUES ('637', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:39:52');
INSERT INTO `sys_oper_log` VALUES ('638', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 15:47:17');
INSERT INTO `sys_oper_log` VALUES ('639', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"4\" ]\r\n}', '1', 'null', '2019-09-03 16:00:04');
INSERT INTO `sys_oper_log` VALUES ('640', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"3\" ]\r\n}', '0', null, '2019-09-03 16:12:50');
INSERT INTO `sys_oper_log` VALUES ('641', '货位设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"5\" ],\r\n  \"operatorName\" : [ \"小邱\" ],\r\n  \"operatorCard\" : [ \"1996100137\" ],\r\n  \"empNo\" : [ \"lulu\" ]\r\n}', '0', null, '2019-09-03 16:25:11');
INSERT INTO `sys_oper_log` VALUES ('642', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:31:58');
INSERT INTO `sys_oper_log` VALUES ('643', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:34:52');
INSERT INTO `sys_oper_log` VALUES ('644', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:35:04');
INSERT INTO `sys_oper_log` VALUES ('645', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:35:08');
INSERT INTO `sys_oper_log` VALUES ('646', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:35:11');
INSERT INTO `sys_oper_log` VALUES ('647', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:35:14');
INSERT INTO `sys_oper_log` VALUES ('648', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:35:17');
INSERT INTO `sys_oper_log` VALUES ('649', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:47:47');
INSERT INTO `sys_oper_log` VALUES ('650', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:54:01');
INSERT INTO `sys_oper_log` VALUES ('651', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:54:11');
INSERT INTO `sys_oper_log` VALUES ('652', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 16:57:55');
INSERT INTO `sys_oper_log` VALUES ('653', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'admin', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 17:02:38');
INSERT INTO `sys_oper_log` VALUES ('654', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-09-03 17:03:09');
INSERT INTO `sys_oper_log` VALUES ('655', '物料分类', '3', 'com.deer.wms.base.system.web.item.ItemTypeController.remove()', '1', 'admin', '研发部门', '/system/itemType/remove/50', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-04 09:31:46');
INSERT INTO `sys_oper_log` VALUES ('656', '物料分类', '1', 'com.deer.wms.base.system.web.item.ItemTypeController.addSave()', '1', 'admin', '研发部门', '/system/itemType/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"\" ],\r\n  \"itemTypeCode\" : [ \"2\" ],\r\n  \"itemTypeName\" : [ \"2\" ],\r\n  \"orderNum\" : [ \"2\" ]\r\n}', '0', null, '2019-09-04 09:32:11');
INSERT INTO `sys_oper_log` VALUES ('657', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"hehehe\" ],\r\n  \"wareName\" : [ \"立库\" ],\r\n  \"memo\" : [ \"立库\" ],\r\n  \"expectedWaring\" : [ \"12\" ],\r\n  \"alarm\" : [ \"12\" ],\r\n  \"stockWaring\" : [ \"12\" ]\r\n}', '0', null, '2019-09-04 10:36:13');
INSERT INTO `sys_oper_log` VALUES ('658', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \" hhhhh\" ],\r\n  \"wareName\" : [ \"hhh\" ],\r\n  \"memo\" : [ \"hhh\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"2\" ],\r\n  \"stockWaring\" : [ \"3\" ]\r\n}', '0', null, '2019-09-04 10:44:57');
INSERT INTO `sys_oper_log` VALUES ('659', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"220\" ],\r\n  \"wareCode\" : [ \" hhhhh\" ],\r\n  \"wareName\" : [ \"hhh\" ],\r\n  \"memo\" : [ \"hhh\" ],\r\n  \"expectedWaring\" : [ \"1.0\" ],\r\n  \"alarm\" : [ \"3.33\" ],\r\n  \"stockWaring\" : [ \"3\" ]\r\n}', '0', null, '2019-09-04 11:36:44');
INSERT INTO `sys_oper_log` VALUES ('660', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"220\" ],\r\n  \"wareCode\" : [ \" hhhhh\" ],\r\n  \"wareName\" : [ \"hhh\" ],\r\n  \"memo\" : [ \"hhh\" ],\r\n  \"expectedWaring\" : [ \"1.0\" ],\r\n  \"alarm\" : [ \"2.0\" ],\r\n  \"stockWaring\" : [ \"3\" ]\r\n}', '0', null, '2019-09-04 11:38:38');
INSERT INTO `sys_oper_log` VALUES ('661', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库库\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ]\r\n}', '0', null, '2019-09-04 11:46:54');
INSERT INTO `sys_oper_log` VALUES ('662', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ]\r\n}', '0', null, '2019-09-04 11:48:33');
INSERT INTO `sys_oper_log` VALUES ('663', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"222\" ]\r\n}', '0', null, '2019-09-04 11:48:36');
INSERT INTO `sys_oper_log` VALUES ('664', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"221\" ]\r\n}', '0', null, '2019-09-04 11:48:38');
INSERT INTO `sys_oper_log` VALUES ('665', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ]\r\n}', '0', null, '2019-09-04 11:55:33');
INSERT INTO `sys_oper_log` VALUES ('666', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ]\r\n}', '0', null, '2019-09-04 11:56:14');
INSERT INTO `sys_oper_log` VALUES ('667', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ]\r\n}', '0', null, '2019-09-04 11:56:26');
INSERT INTO `sys_oper_log` VALUES ('668', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ]\r\n}', '0', null, '2019-09-04 13:41:03');
INSERT INTO `sys_oper_log` VALUES ('669', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ]\r\n}', '0', null, '2019-09-04 14:06:52');
INSERT INTO `sys_oper_log` VALUES ('670', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ]\r\n}', '0', null, '2019-09-04 14:07:03');
INSERT INTO `sys_oper_log` VALUES ('671', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \" 鸹貔\" ],\r\n  \"memo\" : [ \"鸹貔\" ],\r\n  \"expectedWaring\" : [ \"60\" ],\r\n  \"alarm\" : [ \"90\" ],\r\n  \"stockWaring\" : [ \"3\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\ware\\WareInfoMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.ware.WareInfoMapper.findWareInfoList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select ware_id, ware_code, ware_name, create_user_id,create_user_name, create_time, memo, expected_waring, alarm, stock_waring from ware_info                WHERE 1=1                              ware_id != ?                                           and ware_code = ?                                           or ware_name = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5', '2019-09-04 14:08:07');
INSERT INTO `sys_oper_log` VALUES ('672', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \" 鸹貔\" ],\r\n  \"memo\" : [ \"鸹貔\" ],\r\n  \"expectedWaring\" : [ \"60\" ],\r\n  \"alarm\" : [ \"90\" ],\r\n  \"stockWaring\" : [ \"3\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\ware\\WareInfoMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.ware.WareInfoMapper.findWareInfoList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select ware_id, ware_code, ware_name, create_user_id,create_user_name, create_time, memo, expected_waring, alarm, stock_waring from ware_info                WHERE 1=1                              ware_id != ?                                           and ware_code = ?                                           or ware_name = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5', '2019-09-04 14:08:37');
INSERT INTO `sys_oper_log` VALUES ('673', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \" 鸹貔\" ],\r\n  \"memo\" : [ \"鸹貔\" ],\r\n  \"expectedWaring\" : [ \"60\" ],\r\n  \"alarm\" : [ \"90\" ],\r\n  \"stockWaring\" : [ \"3\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\ware\\WareInfoMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.ware.WareInfoMapper.findWareInfoList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select ware_id, ware_code, ware_name, create_user_id,create_user_name, create_time, memo, expected_waring, alarm, stock_waring from ware_info                WHERE 1=1                              ware_id != ?                                           and ware_code = ?                                           or ware_name = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'ware_id != 223\n            \n            \n                and ware_code = \'guapi\'\' at line 5', '2019-09-04 14:11:17');
INSERT INTO `sys_oper_log` VALUES ('674', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \"guapi\" ],\r\n  \"memo\" : [ \"guapi\" ],\r\n  \"expectedWaring\" : [ \"70\" ],\r\n  \"alarm\" : [ \"90\" ],\r\n  \"stockWaring\" : [ \"15\" ]\r\n}', '0', null, '2019-09-04 14:16:28');
INSERT INTO `sys_oper_log` VALUES ('675', '仓库设置', '3', 'com.deer.wms.web.controller.ware.WareInfoController.remove()', '1', 'admin', '研发部门', '/system/wareInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"220\" ]\r\n}', '0', null, '2019-09-04 14:16:49');
INSERT INTO `sys_oper_log` VALUES ('676', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \"guapi\" ],\r\n  \"memo\" : [ \"guapi\" ],\r\n  \"expectedWaring\" : [ \"70.0\" ],\r\n  \"alarm\" : [ \"90.0\" ],\r\n  \"stockWaring\" : [ \"15\" ],\r\n  \"boxParam\" : [ \"60.9\" ]\r\n}', '0', null, '2019-09-05 15:45:39');
INSERT INTO `sys_oper_log` VALUES ('677', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \"guapi\" ],\r\n  \"memo\" : [ \"guapi\" ],\r\n  \"expectedWaring\" : [ \"70.0\" ],\r\n  \"alarm\" : [ \"90.0\" ],\r\n  \"stockWaring\" : [ \"15\" ],\r\n  \"boxParam\" : [ \"60.9\" ]\r\n}', '0', null, '2019-09-05 15:46:51');
INSERT INTO `sys_oper_log` VALUES ('678', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"hehe\" ],\r\n  \"wareName\" : [ \"heihei\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1\" ],\r\n  \"stockWaring\" : [ \"1\" ],\r\n  \"boxParam\" : [ \"1\" ]\r\n}', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'(\n                \n                    and ware_code = \'hehe\'\n                \n \' at line 5\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\ware\\WareInfoMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.ware.WareInfoMapper.findWareInfoList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select ware_id, ware_code, ware_name, create_user_id,create_user_name, create_time, memo, expected_waring, alarm, stock_waring from ware_info                WHERE 1=1                          (                                      and ware_code = ?                                                       or ware_name = ?                              )\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'(\n                \n                    and ware_code = \'hehe\'\n                \n \' at line 5\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'(\n                \n                    and ware_code = \'hehe\'\n                \n \' at line 5', '2019-09-05 15:50:14');
INSERT INTO `sys_oper_log` VALUES ('679', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"heihei\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1\" ],\r\n  \"alarm\" : [ \"1 \" ],\r\n  \"stockWaring\" : [ \"1\" ],\r\n  \"boxParam\" : [ \"1\" ]\r\n}', '0', null, '2019-09-05 16:02:56');
INSERT INTO `sys_oper_log` VALUES ('680', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ],\r\n  \"boxParam\" : [ \"\" ]\r\n}', '0', null, '2019-09-05 16:03:03');
INSERT INTO `sys_oper_log` VALUES ('681', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"heihe\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ],\r\n  \"boxParam\" : [ \"\" ]\r\n}', '0', null, '2019-09-05 16:03:30');
INSERT INTO `sys_oper_log` VALUES ('682', '仓库设置', '1', 'com.deer.wms.web.controller.ware.WareInfoController.addSave()', '1', 'admin', '研发部门', '/system/wareInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"wareCode\" : [ \"\" ],\r\n  \"wareName\" : [ \"\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ],\r\n  \"boxParam\" : [ \"1\" ]\r\n}', '0', null, '2019-09-05 16:03:38');
INSERT INTO `sys_oper_log` VALUES ('683', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"224\" ],\r\n  \"wareCode\" : [ \"heihei\" ],\r\n  \"wareName\" : [ \"1\" ],\r\n  \"memo\" : [ \"1\" ],\r\n  \"expectedWaring\" : [ \"1.0\" ],\r\n  \"alarm\" : [ \"1.0\" ],\r\n  \"stockWaring\" : [ \"1\" ],\r\n  \"boxParam\" : [ \"1\" ]\r\n}', '0', null, '2019-09-05 16:04:20');
INSERT INTO `sys_oper_log` VALUES ('684', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ],\r\n  \"boxParam\" : [ \"60.9\" ]\r\n}', '0', null, '2019-09-05 16:21:37');
INSERT INTO `sys_oper_log` VALUES ('685', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"\" ],\r\n  \"stockWaring\" : [ \"\" ],\r\n  \"boxParam\" : [ \"60.9\" ]\r\n}', '0', null, '2019-09-05 16:21:53');
INSERT INTO `sys_oper_log` VALUES ('686', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"223\" ],\r\n  \"wareCode\" : [ \"guapi\" ],\r\n  \"wareName\" : [ \"guapi\" ],\r\n  \"memo\" : [ \"guapi\" ],\r\n  \"expectedWaring\" : [ \"70.0\" ],\r\n  \"alarm\" : [ \"90.0\" ],\r\n  \"stockWaring\" : [ \"15\" ],\r\n  \"boxParam\" : [ \"11\" ]\r\n}', '0', null, '2019-09-05 16:22:36');
INSERT INTO `sys_oper_log` VALUES ('687', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60\" ],\r\n  \"stockWaring\" : [ \"60\" ],\r\n  \"boxParam\" : [ \"60.9\" ]\r\n}', '0', null, '2019-09-05 16:25:28');
INSERT INTO `sys_oper_log` VALUES ('688', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"库存管理\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-building\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-18 15:47:39');
INSERT INTO `sys_oper_log` VALUES ('689', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2061\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"库存管理\" ],\r\n  \"url\" : [ \"/in/boxItem/inventoryManagePage\" ],\r\n  \"perms\" : [ \"inventoryManagePage\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-18 15:48:48');
INSERT INTO `sys_oper_log` VALUES ('690', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2062\" ],\r\n  \"parentId\" : [ \"2061\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"库存管理\" ],\r\n  \"url\" : [ \"/in/boxItem/inventoryManagePage\" ],\r\n  \"perms\" : [ \"in:inventoryManage:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-18 15:51:35');
INSERT INTO `sys_oper_log` VALUES ('691', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:03:51');
INSERT INTO `sys_oper_log` VALUES ('692', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:06:02');
INSERT INTO `sys_oper_log` VALUES ('693', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:06:31');
INSERT INTO `sys_oper_log` VALUES ('694', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:08:02');
INSERT INTO `sys_oper_log` VALUES ('695', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:13:34');
INSERT INTO `sys_oper_log` VALUES ('696', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:48:40');
INSERT INTO `sys_oper_log` VALUES ('697', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 14:49:49');
INSERT INTO `sys_oper_log` VALUES ('698', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:12:53');
INSERT INTO `sys_oper_log` VALUES ('699', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:22:47');
INSERT INTO `sys_oper_log` VALUES ('700', '容器', '1', 'com.deer.wms.base.system.web.box.BoxInfoController.add()', '1', 'admin', '研发部门', '/boxInfo/add', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:25:28');
INSERT INTO `sys_oper_log` VALUES ('701', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:29:56');
INSERT INTO `sys_oper_log` VALUES ('702', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:41:06');
INSERT INTO `sys_oper_log` VALUES ('703', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:48:13');
INSERT INTO `sys_oper_log` VALUES ('704', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:51:15');
INSERT INTO `sys_oper_log` VALUES ('705', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 15:53:43');
INSERT INTO `sys_oper_log` VALUES ('706', '退货', '0', 'com.deer.wms.base.system.web.box.BoxItemController.returnItem()', '1', 'admin', '研发部门', '/in/boxItem/returnItem', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-19 16:03:49');
INSERT INTO `sys_oper_log` VALUES ('707', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '1', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'state\' in \'field list\'\r\n### The error may exist in file [H:\\workFile\\wuXiSCCWMS\\wms-base-system\\target\\classes\\mapper\\box\\BoxItemMapper.xml]\r\n### The error may involve com.deer.wms.base.system.dao.box.BoxItemMapper.selectBoxItemById-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select id, box_code, item_code, batch, quantity, position_no, bill_in_detail_id, state from box_item               where id = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'state\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'state\' in \'field list\'', '2019-09-21 10:51:55');
INSERT INTO `sys_oper_log` VALUES ('708', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 14:59:36');
INSERT INTO `sys_oper_log` VALUES ('709', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:02:07');
INSERT INTO `sys_oper_log` VALUES ('710', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:06:14');
INSERT INTO `sys_oper_log` VALUES ('711', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:06:20');
INSERT INTO `sys_oper_log` VALUES ('712', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:07:57');
INSERT INTO `sys_oper_log` VALUES ('713', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:08:23');
INSERT INTO `sys_oper_log` VALUES ('714', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:11:04');
INSERT INTO `sys_oper_log` VALUES ('715', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:16:01');
INSERT INTO `sys_oper_log` VALUES ('716', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:22:21');
INSERT INTO `sys_oper_log` VALUES ('717', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:22:35');
INSERT INTO `sys_oper_log` VALUES ('718', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-09-21 15:28:25');
INSERT INTO `sys_oper_log` VALUES ('719', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"3\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"微服务地址\" ],\r\n  \"url\" : [ \"/serverVisitAddress/serverVisit\" ],\r\n  \"perms\" : [ \"serverVisitAddress:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-26 10:19:12');
INSERT INTO `sys_oper_log` VALUES ('720', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2063\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"微服务地址\" ],\r\n  \"url\" : [ \"/serverVisitAddress/serverVisit\" ],\r\n  \"perms\" : [ \"serverVisitAddress:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-26 10:22:18');
INSERT INTO `sys_oper_log` VALUES ('721', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.authDataScopeSave()', '1', 'admin', '研发部门', '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', '0', null, '2019-09-26 10:25:14');
INSERT INTO `sys_oper_log` VALUES ('722', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-09-26 10:25:28');
INSERT INTO `sys_oper_log` VALUES ('723', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2063\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"微服务地址\" ],\r\n  \"url\" : [ \"/serverVisitAddress\" ],\r\n  \"perms\" : [ \"serverVisitAddress:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-26 10:27:14');
INSERT INTO `sys_oper_log` VALUES ('724', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2063\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"编辑\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"serverVisitAddress:edit\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-26 10:37:59');
INSERT INTO `sys_oper_log` VALUES ('725', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"工单计划时间管理\" ],\r\n  \"url\" : [ \"workerOrderIssueTime\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-28 16:23:02');
INSERT INTO `sys_oper_log` VALUES ('726', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2065\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"工单计划时间管理\" ],\r\n  \"url\" : [ \"/workerOrderIssueTime\" ],\r\n  \"perms\" : [ \"workerOrderIssueTime:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-28 16:23:30');
INSERT INTO `sys_oper_log` VALUES ('727', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2065\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"编辑\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"workerOrderIssueTime:edit\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-09-28 16:26:42');
INSERT INTO `sys_oper_log` VALUES ('728', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2065,2066,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-09-28 16:27:07');
INSERT INTO `sys_oper_log` VALUES ('729', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-09 17:04:26');
INSERT INTO `sys_oper_log` VALUES ('730', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"132\" ],\r\n  \"addressCode\" : [ \"12321\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"219\" ]\r\n}', '0', null, '2019-10-10 16:35:44');
INSERT INTO `sys_oper_log` VALUES ('731', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"132\" ],\r\n  \"addressCode\" : [ \"12321\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 16:35:52');
INSERT INTO `sys_oper_log` VALUES ('732', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"12321\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 16:49:09');
INSERT INTO `sys_oper_log` VALUES ('733', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"7\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"321321\" ],\r\n  \"name\" : [ \"AGV入库口\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 16:49:14');
INSERT INTO `sys_oper_log` VALUES ('734', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"12321\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 16:52:28');
INSERT INTO `sys_oper_log` VALUES ('735', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"12321\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 16:52:32');
INSERT INTO `sys_oper_log` VALUES ('736', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"7\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"D10011\" ],\r\n  \"name\" : [ \"AGV入库口\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 17:14:07');
INSERT INTO `sys_oper_log` VALUES ('737', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"7\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"D10011\" ],\r\n  \"name\" : [ \"AGV入库口\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"0\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 17:18:59');
INSERT INTO `sys_oper_log` VALUES ('738', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"D10012\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2019-10-10 17:19:09');
INSERT INTO `sys_oper_log` VALUES ('739', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"\" ],\r\n  \"itemId\" : [ \"18\" ],\r\n  \"itemCode\" : [ \"132\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"spec\" : [ \"红色\" ],\r\n  \"model\" : [ \"A11\" ],\r\n  \"thickness\" : [ \"12.0\" ]\r\n}', '0', null, '2019-10-19 13:36:54');
INSERT INTO `sys_oper_log` VALUES ('740', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"\" ],\r\n  \"itemId\" : [ \"18\" ],\r\n  \"itemCode\" : [ \"132\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"spec\" : [ \"红色\" ],\r\n  \"model\" : [ \"A11\" ],\r\n  \"thickness\" : [ \"12.0\" ]\r\n}', '0', null, '2019-10-19 13:41:18');
INSERT INTO `sys_oper_log` VALUES ('741', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemTypeId\" : [ \"\" ],\r\n  \"itemId\" : [ \"20\" ],\r\n  \"itemCode\" : [ \"132\" ],\r\n  \"itemName\" : [ \"12\" ],\r\n  \"spec\" : [ \"12\" ],\r\n  \"model\" : [ \"12\" ],\r\n  \"thickness\" : [ \"12.0\" ]\r\n}', '0', null, '2019-10-19 13:46:18');
INSERT INTO `sys_oper_log` VALUES ('742', '物料', '1', 'com.deer.wms.base.system.web.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"asfas\" ],\r\n  \"itemName\" : [ \"123\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '0', null, '2019-10-19 14:12:17');
INSERT INTO `sys_oper_log` VALUES ('743', '物料', '3', 'com.deer.wms.base.system.web.item.ItemInfoController.remove()', '1', 'admin', '研发部门', '/system/itemInfo/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"28\" ]\r\n}', '0', null, '2019-10-19 14:12:30');
INSERT INTO `sys_oper_log` VALUES ('744', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"60\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"111\" ]\r\n}', '0', null, '2019-10-19 14:38:56');
INSERT INTO `sys_oper_log` VALUES ('745', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"w001\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"60\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"121\" ]\r\n}', '0', null, '2019-10-19 14:41:11');
INSERT INTO `sys_oper_log` VALUES ('746', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2063\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"服务地址\" ],\r\n  \"url\" : [ \"/serverVisitAddress\" ],\r\n  \"perms\" : [ \"serverVisitAddress:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-10-23 12:56:51');
INSERT INTO `sys_oper_log` VALUES ('747', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"李工\" ],\r\n  \"operatorCard\" : [ \"2699333981\" ],\r\n  \"empNo\" : [ \"SN15079\" ]\r\n}', '0', null, '2019-10-26 14:31:40');
INSERT INTO `sys_oper_log` VALUES ('748', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"账户别名\" ],\r\n  \"url\" : [ \"/accountAlias\" ],\r\n  \"perms\" : [ \"accountAlias:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-10-29 14:42:03');
INSERT INTO `sys_oper_log` VALUES ('749', '用户管理', '1', 'com.deer.wms.admin.controller.system.SysUserController.addSave()', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"wxy\" ],\r\n  \"deptName\" : [ \"研发部门\" ],\r\n  \"phonenumber\" : [ \"13776571947\" ],\r\n  \"email\" : [ \"1428722657@qq.com\" ],\r\n  \"loginName\" : [ \"wxy\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"测试账号\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-10-30 15:30:18');
INSERT INTO `sys_oper_log` VALUES ('750', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.update()', '1', 'wxy', '研发部门', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"wxy\" ],\r\n  \"phonenumber\" : [ \"13776571947\" ],\r\n  \"email\" : [ \"1428722657@qq.com\" ],\r\n  \"sex\" : [ \"1\" ]\r\n}', '0', null, '2019-10-30 15:43:56');
INSERT INTO `sys_oper_log` VALUES ('751', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'wxy', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"email\" : [ \"ry@qq.com\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\", \"2\" ],\r\n  \"remark\" : [ \"测试员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1,2\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-10-30 16:10:53');
INSERT INTO `sys_oper_log` VALUES ('752', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-10-30 16:15:19');
INSERT INTO `sys_oper_log` VALUES ('753', '角色管理', '4', 'com.deer.wms.admin.controller.system.SysRoleController.cancelAuthUser()', '1', 'admin', '研发部门', '/system/role/authUser/cancel', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"userId\" : [ \"2\" ]\r\n}', '0', null, '2019-10-30 16:15:43');
INSERT INTO `sys_oper_log` VALUES ('754', '角色管理', '4', 'com.deer.wms.admin.controller.system.SysRoleController.selectAuthUserAll()', '1', 'admin', '研发部门', '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"userIds\" : [ \"2\" ]\r\n}', '0', null, '2019-10-30 16:15:47');
INSERT INTO `sys_oper_log` VALUES ('755', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.authDataScopeSave()', '1', 'admin', '研发部门', '/system/role/authDataScope', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"dataScope\" : [ \"1\" ],\r\n  \"deptIds\" : [ \"\" ]\r\n}', '0', null, '2019-10-30 16:16:00');
INSERT INTO `sys_oper_log` VALUES ('756', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"3\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"wxy\" ],\r\n  \"dept.deptName\" : [ \"研发部门\" ],\r\n  \"phonenumber\" : [ \"13458735486\" ],\r\n  \"email\" : [ \"1428722657@qq.com\" ],\r\n  \"loginName\" : [ \"wxy\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"测试账号\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-10-30 16:16:23');
INSERT INTO `sys_oper_log` VALUES ('757', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"3\" ],\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"wxy\" ],\r\n  \"dept.deptName\" : [ \"研发部门\" ],\r\n  \"phonenumber\" : [ \"13458735486\" ],\r\n  \"email\" : [ \"1428722657@qq.com\" ],\r\n  \"loginName\" : [ \"wxy\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\" ],\r\n  \"remark\" : [ \"测试账号\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-10-30 16:16:28');
INSERT INTO `sys_oper_log` VALUES ('758', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2062\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"查询所有\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"in:boxItem:findList\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-10-30 16:18:22');
INSERT INTO `sys_oper_log` VALUES ('759', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,2068,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-10-30 16:18:51');
INSERT INTO `sys_oper_log` VALUES ('760', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,2068,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-10-30 16:19:58');
INSERT INTO `sys_oper_log` VALUES ('761', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'wxy', '研发部门', '/system/menu/remove/2068', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-30 16:20:24');
INSERT INTO `sys_oper_log` VALUES ('762', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-10-30 16:21:04');
INSERT INTO `sys_oper_log` VALUES ('763', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'wxy', '研发部门', '/system/menu/remove/2068', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-30 16:21:17');
INSERT INTO `sys_oper_log` VALUES ('764', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-10-30 16:21:25');
INSERT INTO `sys_oper_log` VALUES ('765', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2062\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"检验合格\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"in:boxItem:checkOut\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-10-30 17:07:06');
INSERT INTO `sys_oper_log` VALUES ('766', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.changeStatus()', '1', 'wxy', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"status\" : [ \"1\" ]\r\n}', '0', null, '2019-10-30 17:07:16');
INSERT INTO `sys_oper_log` VALUES ('767', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-10-30 17:07:31');
INSERT INTO `sys_oper_log` VALUES ('768', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:25:59');
INSERT INTO `sys_oper_log` VALUES ('769', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:26');
INSERT INTO `sys_oper_log` VALUES ('770', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:30');
INSERT INTO `sys_oper_log` VALUES ('771', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:33');
INSERT INTO `sys_oper_log` VALUES ('772', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:34');
INSERT INTO `sys_oper_log` VALUES ('773', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:34');
INSERT INTO `sys_oper_log` VALUES ('774', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:34');
INSERT INTO `sys_oper_log` VALUES ('775', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:34');
INSERT INTO `sys_oper_log` VALUES ('776', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:35');
INSERT INTO `sys_oper_log` VALUES ('777', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:26:35');
INSERT INTO `sys_oper_log` VALUES ('778', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:28:04');
INSERT INTO `sys_oper_log` VALUES ('779', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 11:28:12');
INSERT INTO `sys_oper_log` VALUES ('780', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 13:15:33');
INSERT INTO `sys_oper_log` VALUES ('781', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-10-31 13:19:40');
INSERT INTO `sys_oper_log` VALUES ('782', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-01 09:34:04');
INSERT INTO `sys_oper_log` VALUES ('783', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-01 09:34:46');
INSERT INTO `sys_oper_log` VALUES ('784', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-01 09:36:40');
INSERT INTO `sys_oper_log` VALUES ('785', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-02 10:42:13');
INSERT INTO `sys_oper_log` VALUES ('786', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '1', 'For input string: \"20191104150933\"', '2019-11-04 15:09:33');
INSERT INTO `sys_oper_log` VALUES ('787', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-11-04 15:27:33');
INSERT INTO `sys_oper_log` VALUES ('788', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-11-04 15:44:58');
INSERT INTO `sys_oper_log` VALUES ('789', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-11-04 15:45:45');
INSERT INTO `sys_oper_log` VALUES ('790', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-08 17:11:53');
INSERT INTO `sys_oper_log` VALUES ('791', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-09 10:08:06');
INSERT INTO `sys_oper_log` VALUES ('792', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-09 10:14:07');
INSERT INTO `sys_oper_log` VALUES ('793', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-09 10:14:14');
INSERT INTO `sys_oper_log` VALUES ('794', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-09 10:17:37');
INSERT INTO `sys_oper_log` VALUES ('795', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-09 10:20:13');
INSERT INTO `sys_oper_log` VALUES ('796', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-09 10:44:27');
INSERT INTO `sys_oper_log` VALUES ('797', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"子库管理\" ],\r\n  \"url\" : [ \"/system/operator\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"fa fa-gears\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-09 14:53:32');
INSERT INTO `sys_oper_log` VALUES ('798', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'wxy', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2070\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"子库管理\" ],\r\n  \"url\" : [ \"/system/operator\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-gears\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-09 14:53:45');
INSERT INTO `sys_oper_log` VALUES ('799', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'wxy', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2070\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"子库管理\" ],\r\n  \"url\" : [ \"/accountAlias\" ],\r\n  \"perms\" : [ \"accountAlias:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-09 14:54:47');
INSERT INTO `sys_oper_log` VALUES ('800', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'wxy', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2070\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"子库管理\" ],\r\n  \"url\" : [ \"/subInventory\" ],\r\n  \"perms\" : [ \"subInventory:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"fa fa-gears\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-09 14:55:33');
INSERT INTO `sys_oper_log` VALUES ('801', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-09 16:29:53');
INSERT INTO `sys_oper_log` VALUES ('802', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2049\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"menuType\" : [ \"M\" ],\r\n  \"menuName\" : [ \"任务查询\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"fa fa-plane\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-13 11:09:53');
INSERT INTO `sys_oper_log` VALUES ('803', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2049\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"/task/taskInfo\" ],\r\n  \"perms\" : [ \"in:task:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-13 13:19:55');
INSERT INTO `sys_oper_log` VALUES ('804', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2071\" ],\r\n  \"parentId\" : [ \"2049\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"任务管理\" ],\r\n  \"url\" : [ \"/in/taskInfo\" ],\r\n  \"perms\" : [ \"in:task:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-13 13:44:12');
INSERT INTO `sys_oper_log` VALUES ('805', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"60\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"121\" ]\r\n}', '0', null, '2019-11-13 17:15:31');
INSERT INTO `sys_oper_log` VALUES ('806', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-14 10:33:25');
INSERT INTO `sys_oper_log` VALUES ('807', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"无按钮\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:13:39');
INSERT INTO `sys_oper_log` VALUES ('808', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"入库操作台\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:14:20');
INSERT INTO `sys_oper_log` VALUES ('809', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2072\" ],\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"无按钮\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:14:41');
INSERT INTO `sys_oper_log` VALUES ('810', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2073\" ],\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"入库操作台\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"system:operator:floor\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:17:59');
INSERT INTO `sys_oper_log` VALUES ('811', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-14 11:20:23');
INSERT INTO `sys_oper_log` VALUES ('812', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.changeStatus()', '1', 'wxy', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:21:25');
INSERT INTO `sys_oper_log` VALUES ('813', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-14 11:21:46');
INSERT INTO `sys_oper_log` VALUES ('814', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'wxy', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2073\" ],\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库操作台\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"system:operator:floor\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:22:37');
INSERT INTO `sys_oper_log` VALUES ('815', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'wxy', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2073\" ],\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"入库操作台\" ],\r\n  \"url\" : [ \"#\" ],\r\n  \"perms\" : [ \"system:operator:floor\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-14 11:29:52');
INSERT INTO `sys_oper_log` VALUES ('816', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-14 13:22:08');
INSERT INTO `sys_oper_log` VALUES ('817', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-14 13:41:55');
INSERT INTO `sys_oper_log` VALUES ('818', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"小林\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-11-14 15:54:12');
INSERT INTO `sys_oper_log` VALUES ('819', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"憨憨\" ],\r\n  \"operatorCard\" : [ \"0985414211\" ],\r\n  \"empNo\" : [ \"IT\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-11-14 15:54:43');
INSERT INTO `sys_oper_log` VALUES ('820', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"8\" ],\r\n  \"operatorName\" : [ \"憨憨\" ],\r\n  \"operatorCard\" : [ \"0985414211\" ],\r\n  \"empNo\" : [ \"IT\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-14 15:54:50');
INSERT INTO `sys_oper_log` VALUES ('821', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"憨批\" ],\r\n  \"operatorCard\" : [ \"1112223331\" ],\r\n  \"empNo\" : [ \"123\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-14 15:56:19');
INSERT INTO `sys_oper_log` VALUES ('822', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"12\" ],\r\n  \"operatorCard\" : [ \"12312\" ],\r\n  \"empNo\" : [ \"321\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-14 15:57:03');
INSERT INTO `sys_oper_log` VALUES ('823', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"铁憨憨\" ],\r\n  \"operatorCard\" : [ \"0123456789\" ],\r\n  \"empNo\" : [ \"dze1a\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-14 16:00:03');
INSERT INTO `sys_oper_log` VALUES ('824', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10\" ]\r\n}', '0', null, '2019-11-14 16:00:10');
INSERT INTO `sys_oper_log` VALUES ('825', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10\" ]\r\n}', '0', null, '2019-11-14 16:00:15');
INSERT INTO `sys_oper_log` VALUES ('826', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"铁憨憨憨批\" ],\r\n  \"operatorCard\" : [ \"4612816332\" ],\r\n  \"empNo\" : [ \"21\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-14 16:13:14');
INSERT INTO `sys_oper_log` VALUES ('827', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/9', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"9\" ]\r\n}', '0', null, '2019-11-14 16:30:27');
INSERT INTO `sys_oper_log` VALUES ('828', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/10', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"10\" ]\r\n}', '0', null, '2019-11-14 16:30:30');
INSERT INTO `sys_oper_log` VALUES ('829', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/12', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"12\" ]\r\n}', '0', null, '2019-11-14 16:30:34');
INSERT INTO `sys_oper_log` VALUES ('830', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"憨批\" ],\r\n  \"operatorCard\" : [ \"123124\" ],\r\n  \"empNo\" : [ \"313\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-14 16:31:57');
INSERT INTO `sys_oper_log` VALUES ('831', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/8', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"8\" ]\r\n}', '0', null, '2019-11-14 16:32:01');
INSERT INTO `sys_oper_log` VALUES ('832', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/7', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"7\" ]\r\n}', '0', null, '2019-11-14 16:32:03');
INSERT INTO `sys_oper_log` VALUES ('833', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/11', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"11\" ]\r\n}', '0', null, '2019-11-14 16:32:07');
INSERT INTO `sys_oper_log` VALUES ('834', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'admin', '研发部门', '/system/operator/remove/13', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"13\" ]\r\n}', '0', null, '2019-11-14 16:32:09');
INSERT INTO `sys_oper_log` VALUES ('835', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.changeStatus()', '1', 'admin', '研发部门', '/system/role/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 09:22:19');
INSERT INTO `sys_oper_log` VALUES ('836', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"入库操作员\" ],\r\n  \"roleKey\" : [ \"operator\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"入库操作员\" ],\r\n  \"menuIds\" : [ \"2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069\" ]\r\n}', '0', null, '2019-11-15 09:23:47');
INSERT INTO `sys_oper_log` VALUES ('837', '部门管理', '2', 'com.deer.wms.admin.controller.system.SysDeptController.editSave()', '1', 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"parentName\" : [ \"无\" ],\r\n  \"deptName\" : [ \"无锡深蓝电路有限公司\" ],\r\n  \"orderNum\" : [ \"0\" ],\r\n  \"leader\" : [ \"\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 09:27:08');
INSERT INTO `sys_oper_log` VALUES ('838', '部门管理', '2', 'com.deer.wms.admin.controller.system.SysDeptController.editSave()', '1', 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"parentName\" : [ \"无\" ],\r\n  \"deptName\" : [ \"无锡深南电路有限公司\" ],\r\n  \"orderNum\" : [ \"0\" ],\r\n  \"leader\" : [ \"\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 09:27:46');
INSERT INTO `sys_oper_log` VALUES ('839', '部门管理', '2', 'com.deer.wms.admin.controller.system.SysDeptController.editSave()', '1', 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"101\" ],\r\n  \"parentId\" : [ \"100\" ],\r\n  \"parentName\" : [ \"无锡深南电路有限公司\" ],\r\n  \"deptName\" : [ \"6号楼\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"leader\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 09:28:20');
INSERT INTO `sys_oper_log` VALUES ('840', '部门管理', '3', 'com.deer.wms.admin.controller.system.SysDeptController.remove()', '1', 'admin', '研发部门', '/system/dept/remove/102', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 09:28:27');
INSERT INTO `sys_oper_log` VALUES ('841', '部门管理', '3', 'com.deer.wms.admin.controller.system.SysDeptController.remove()', '1', 'admin', '研发部门', '/system/dept/remove/108', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 09:28:30');
INSERT INTO `sys_oper_log` VALUES ('842', '部门管理', '3', 'com.deer.wms.admin.controller.system.SysDeptController.remove()', '1', 'admin', '研发部门', '/system/dept/remove/109', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 09:28:33');
INSERT INTO `sys_oper_log` VALUES ('843', '部门管理', '3', 'com.deer.wms.admin.controller.system.SysDeptController.remove()', '1', 'admin', '研发部门', '/system/dept/remove/102', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 09:28:36');
INSERT INTO `sys_oper_log` VALUES ('844', '部门管理', '1', 'com.deer.wms.admin.controller.system.SysDeptController.addSave()', '1', 'admin', '研发部门', '/system/dept/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"101\" ],\r\n  \"deptName\" : [ \"仓管部门\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"leader\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 09:29:43');
INSERT INTO `sys_oper_log` VALUES ('845', '部门管理', '1', 'com.deer.wms.admin.controller.system.SysDeptController.addSave()', '1', 'admin', '研发部门', '/system/dept/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"101\" ],\r\n  \"deptName\" : [ \"IQC\" ],\r\n  \"orderNum\" : [ \"7\" ],\r\n  \"leader\" : [ \"\" ],\r\n  \"phone\" : [ \"\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 09:30:10');
INSERT INTO `sys_oper_log` VALUES ('846', '角色管理', '1', 'com.deer.wms.admin.controller.system.SysRoleController.addSave()', '1', 'admin', '研发部门', '/system/role/add', '127.0.0.1', '内网IP', '{\r\n  \"roleName\" : [ \"IQC\" ],\r\n  \"roleKey\" : [ \"IQC\" ],\r\n  \"roleSort\" : [ \"4\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"menuIds\" : [ \"2061,2062,2069\" ]\r\n}', '0', null, '2019-11-15 09:31:09');
INSERT INTO `sys_oper_log` VALUES ('847', '用户管理', '1', 'com.deer.wms.admin.controller.system.SysUserController.addSave()', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"110\" ],\r\n  \"userName\" : [ \"入库操作台\" ],\r\n  \"deptName\" : [ \"仓管部门\" ],\r\n  \"phonenumber\" : [ \"15454545454\" ],\r\n  \"email\" : [ \"132@qq.com\" ],\r\n  \"loginName\" : [ \"入库操作员\" ],\r\n  \"password\" : [ \"132456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"100\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '0', null, '2019-11-15 09:34:43');
INSERT INTO `sys_oper_log` VALUES ('848', '用户管理', '1', 'com.deer.wms.admin.controller.system.SysUserController.addSave()', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"111\" ],\r\n  \"userName\" : [ \"IQC\" ],\r\n  \"deptName\" : [ \"IQC\" ],\r\n  \"phonenumber\" : [ \"15436579565\" ],\r\n  \"email\" : [ \"2985@163.com\" ],\r\n  \"loginName\" : [ \"IQC\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"101\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"101\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '0', null, '2019-11-15 09:36:00');
INSERT INTO `sys_oper_log` VALUES ('849', '重置密码', '2', 'com.deer.wms.admin.controller.system.SysUserController.resetPwd()', '1', 'admin', '研发部门', '/system/user/resetPwd/4', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 09:47:28');
INSERT INTO `sys_oper_log` VALUES ('850', '重置密码', '2', 'com.deer.wms.admin.controller.system.SysUserController.resetPwdSave()', '1', 'admin', '研发部门', '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"4\" ],\r\n  \"loginName\" : [ \"operator\" ],\r\n  \"password\" : [ \"123456\" ]\r\n}', '0', null, '2019-11-15 09:47:38');
INSERT INTO `sys_oper_log` VALUES ('851', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'operator', '仓管部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 09:52:11');
INSERT INTO `sys_oper_log` VALUES ('852', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 10:10:54');
INSERT INTO `sys_oper_log` VALUES ('853', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 10:13:58');
INSERT INTO `sys_oper_log` VALUES ('854', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 10:32:41');
INSERT INTO `sys_oper_log` VALUES ('855', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 10:53:37');
INSERT INTO `sys_oper_log` VALUES ('856', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 14:18:29');
INSERT INTO `sys_oper_log` VALUES ('857', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.update()', '1', 'admin', '研发部门', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"巡城鹿\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"sex\" : [ \"1\" ]\r\n}', '0', null, '2019-11-15 14:18:39');
INSERT INTO `sys_oper_log` VALUES ('858', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 14:21:40');
INSERT INTO `sys_oper_log` VALUES ('859', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.update()', '1', 'admin', '研发部门', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"巡城鹿\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"sex\" : [ \"0\" ]\r\n}', '0', null, '2019-11-15 14:21:46');
INSERT INTO `sys_oper_log` VALUES ('860', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 14:25:21');
INSERT INTO `sys_oper_log` VALUES ('861', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'wxy', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 14:50:38');
INSERT INTO `sys_oper_log` VALUES ('862', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 15:16:43');
INSERT INTO `sys_oper_log` VALUES ('863', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 15:21:27');
INSERT INTO `sys_oper_log` VALUES ('864', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 16:41:15');
INSERT INTO `sys_oper_log` VALUES ('865', '个人信息', '2', 'com.deer.wms.admin.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-15 16:43:23');
INSERT INTO `sys_oper_log` VALUES ('866', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"小林\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-11-18 09:33:52');
INSERT INTO `sys_oper_log` VALUES ('867', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-18 14:50:15');
INSERT INTO `sys_oper_log` VALUES ('868', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"2\" ],\r\n  \"operatorName\" : [ \"鸹貔\" ],\r\n  \"operatorCard\" : [ \"0865257155\" ],\r\n  \"empNo\" : [ \"鸹貔\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-11-20 09:59:23');
INSERT INTO `sys_oper_log` VALUES ('869', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'admin', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"系统自动转移子库账号\" ],\r\n  \"operatorCard\" : [ \"9638520741\" ],\r\n  \"empNo\" : [ \"SCC\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"1\" ],\r\n  \"combinePermission\" : [ \"1\" ],\r\n  \"returnItemPermission\" : [ \"1\" ],\r\n  \"qualityCheckPermission\" : [ \"1\" ],\r\n  \"scrapPermission\" : [ \"1\" ],\r\n  \"manualOutPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-20 13:35:38');
INSERT INTO `sys_oper_log` VALUES ('870', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"7\" ],\r\n  \"operatorName\" : [ \"系统自动转移子库账号\" ],\r\n  \"operatorCard\" : [ \"9638520741\" ],\r\n  \"empNo\" : [ \"SCC(请勿修改卡号)\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"1\" ],\r\n  \"combinePermission\" : [ \"1\" ],\r\n  \"returnItemPermission\" : [ \"1\" ],\r\n  \"qualityCheckPermission\" : [ \"1\" ],\r\n  \"scrapPermission\" : [ \"1\" ],\r\n  \"manualOutPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-20 13:42:25');
INSERT INTO `sys_oper_log` VALUES ('871', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-11-20 18:00:40');
INSERT INTO `sys_oper_log` VALUES ('872', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2065\" ],\r\n  \"parentId\" : [ \"1\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"计划时间\" ],\r\n  \"url\" : [ \"/workerOrderIssueTime\" ],\r\n  \"perms\" : [ \"workerOrderIssueTime:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-21 11:18:36');
INSERT INTO `sys_oper_log` VALUES ('873', '用户管理', '5', 'com.deer.wms.admin.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-11-25 09:22:13');
INSERT INTO `sys_oper_log` VALUES ('874', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"4\" ],\r\n  \"deptId\" : [ \"110\" ],\r\n  \"userName\" : [ \"入库操作台\" ],\r\n  \"dept.deptName\" : [ \"仓管部门\" ],\r\n  \"phonenumber\" : [ \"15454545454\" ],\r\n  \"email\" : [ \"132@qq.com\" ],\r\n  \"loginName\" : [ \"operator\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"100\" ],\r\n  \"remark\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"100\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '0', null, '2019-11-25 10:11:58');
INSERT INTO `sys_oper_log` VALUES ('875', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"2\" ],\r\n  \"operatorName\" : [ \"鸹貔\" ],\r\n  \"operatorCard\" : [ \"0865257155\" ],\r\n  \"empNo\" : [ \"鸹貔\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"1\" ],\r\n  \"qualityCheckPermission\" : [ \"1\" ],\r\n  \"scrapPermission\" : [ \"1\" ],\r\n  \"manualOutPermission\" : [ \"1\" ]\r\n}', '0', null, '2019-11-25 10:18:11');
INSERT INTO `sys_oper_log` VALUES ('876', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"2\" ],\r\n  \"operatorName\" : [ \"鸹貔\" ],\r\n  \"operatorCard\" : [ \"0865257155\" ],\r\n  \"empNo\" : [ \"鸹貔\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-11-25 10:18:36');
INSERT INTO `sys_oper_log` VALUES ('877', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"100\" ],\r\n  \"roleName\" : [ \"入库操作员\" ],\r\n  \"roleKey\" : [ \"operator\" ],\r\n  \"roleSort\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"入库操作员\" ],\r\n  \"menuIds\" : [ \"2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069\" ]\r\n}', '0', null, '2019-11-25 10:36:54');
INSERT INTO `sys_oper_log` VALUES ('878', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2055', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 10:46:09');
INSERT INTO `sys_oper_log` VALUES ('879', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2055', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 10:46:20');
INSERT INTO `sys_oper_log` VALUES ('880', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 10:59:29');
INSERT INTO `sys_oper_log` VALUES ('881', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '1', '请选择待检物料', '2019-11-25 11:00:30');
INSERT INTO `sys_oper_log` VALUES ('882', '检验合格', '0', 'com.deer.wms.base.system.web.box.BoxItemController.checkOut()', '1', 'admin', '研发部门', '/in/boxItem/checkOut', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 11:15:43');
INSERT INTO `sys_oper_log` VALUES ('883', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2061\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库操作台\" ],\r\n  \"url\" : [ \"/system/billinControl\" ],\r\n  \"perms\" : [ \"system:operator:floor\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-25 14:51:44');
INSERT INTO `sys_oper_log` VALUES ('884', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2074,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-25 14:52:37');
INSERT INTO `sys_oper_log` VALUES ('885', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112,2055\" ]\r\n}', '0', null, '2019-11-25 14:54:24');
INSERT INTO `sys_oper_log` VALUES ('886', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'wxy', '研发部门', '/system/menu/remove/2074', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 14:54:33');
INSERT INTO `sys_oper_log` VALUES ('887', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"呆滞过期报表\" ],\r\n  \"url\" : [ \"/sluggishOverdue\" ],\r\n  \"perms\" : [ \"sluggishOverdue:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2019-11-25 15:05:15');
INSERT INTO `sys_oper_log` VALUES ('888', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\", \"2\" ],\r\n  \"remark\" : [ \"测试员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1,2\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-11-25 17:21:39');
INSERT INTO `sys_oper_log` VALUES ('889', '重置密码', '2', 'com.deer.wms.admin.controller.system.SysUserController.resetPwd()', '1', 'admin', '研发部门', '/system/user/resetPwd/2', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 17:22:54');
INSERT INTO `sys_oper_log` VALUES ('890', '用户管理', '1', 'com.deer.wms.admin.controller.system.SysUserController.addSave()', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"103\" ],\r\n  \"userName\" : [ \"铁憨憨\" ],\r\n  \"deptName\" : [ \"研发部门\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"loginName\" : [ \"1111\" ],\r\n  \"password\" : [ \"123456\" ],\r\n  \"sex\" : [ \"0\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"铁憨憨\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"4\" ]\r\n}', '0', null, '2019-11-25 17:31:00');
INSERT INTO `sys_oper_log` VALUES ('891', '重置密码', '2', 'com.deer.wms.admin.controller.system.SysUserController.resetPwd()', '1', 'admin', '研发部门', '/system/user/resetPwd/2', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-11-25 17:34:35');
INSERT INTO `sys_oper_log` VALUES ('892', '重置密码', '2', 'com.deer.wms.admin.controller.system.SysUserController.resetPwdSave()', '1', 'admin', '研发部门', '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"password\" : [ \"123456\" ]\r\n}', '0', null, '2019-11-25 17:34:40');
INSERT INTO `sys_oper_log` VALUES ('893', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"email\" : [ \"7773@qq.com\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\", \"2\" ],\r\n  \"remark\" : [ \"测试员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1,2\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-11-25 17:35:07');
INSERT INTO `sys_oper_log` VALUES ('894', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"1\", \"2\" ],\r\n  \"remark\" : [ \"测试员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"1,2\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-11-25 17:35:34');
INSERT INTO `sys_oper_log` VALUES ('895', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"测试员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-11-25 17:35:52');
INSERT INTO `sys_oper_log` VALUES ('896', '用户管理', '2', 'com.deer.wms.admin.controller.system.SysUserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\r\n  \"userId\" : [ \"2\" ],\r\n  \"deptId\" : [ \"105\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"dept.deptName\" : [ \"测试部门\" ],\r\n  \"phonenumber\" : [ \"15666666666\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"loginName\" : [ \"ry\" ],\r\n  \"sex\" : [ \"1\" ],\r\n  \"role\" : [ \"2\" ],\r\n  \"remark\" : [ \"测试员\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"roleIds\" : [ \"2\" ],\r\n  \"postIds\" : [ \"2\" ]\r\n}', '0', null, '2019-11-25 17:39:23');
INSERT INTO `sys_oper_log` VALUES ('897', '供应商', '2', 'com.deer.wms.web.controller.ware.SupplierController.editSave()', '1', 'admin', '研发部门', '/system/supplier/edit', '127.0.0.1', '内网IP', '{\r\n  \"supplierId\" : [ \"2\" ],\r\n  \"supplierName\" : [ \"fsadf\" ],\r\n  \"supplierCode\" : [ \"10300\" ]\r\n}', '0', null, '2019-11-26 09:46:06');
INSERT INTO `sys_oper_log` VALUES ('898', '供应商', '2', 'com.deer.wms.web.controller.ware.SupplierController.editSave()', '1', 'admin', '研发部门', '/system/supplier/edit', '127.0.0.1', '内网IP', '{\r\n  \"supplierId\" : [ \"2\" ],\r\n  \"supplierName\" : [ \"fsadf\" ],\r\n  \"supplierCode\" : [ \"10300\" ]\r\n}', '0', null, '2019-11-26 09:46:14');
INSERT INTO `sys_oper_log` VALUES ('899', '供应商', '2', 'com.deer.wms.web.controller.ware.SupplierController.editSave()', '1', 'admin', '研发部门', '/system/supplier/edit', '127.0.0.1', '内网IP', '{\r\n  \"supplierId\" : [ \"2\" ],\r\n  \"supplierName\" : [ \"fsadf\" ],\r\n  \"supplierCode\" : [ \"10300\" ]\r\n}', '0', null, '2019-11-26 09:52:04');
INSERT INTO `sys_oper_log` VALUES ('900', '供应商', '2', 'com.deer.wms.web.controller.ware.SupplierController.editSave()', '1', 'admin', '研发部门', '/system/supplier/edit', '127.0.0.1', '内网IP', '{\r\n  \"supplierId\" : [ \"3\" ],\r\n  \"supplierName\" : [ \"fasfdasf\" ],\r\n  \"supplierCode\" : [ \"11123\" ]\r\n}', '0', null, '2019-11-26 09:52:09');
INSERT INTO `sys_oper_log` VALUES ('901', '供应商', '1', 'com.deer.wms.web.controller.ware.SupplierController.addSave()', '1', 'admin', '研发部门', '/system/supplier/add', '127.0.0.1', '内网IP', '{\r\n  \"supplierName\" : [ \"贴哈哈\" ],\r\n  \"supplierCode\" : [ \"213121\" ]\r\n}', '0', null, '2019-11-26 09:52:20');
INSERT INTO `sys_oper_log` VALUES ('902', '物料', '1', 'com.deer.wms.base.system.web.item.ItemInfoController.addSave()', '1', 'admin', '研发部门', '/system/itemInfo/add', '127.0.0.1', '内网IP', '{\r\n  \"itemCode\" : [ \"278638514\" ],\r\n  \"itemName\" : [ \"你是一条大韩寒\" ],\r\n  \"thickness\" : [ \"11\" ]\r\n}', '0', null, '2019-11-26 11:38:26');
INSERT INTO `sys_oper_log` VALUES ('903', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"121\" ]\r\n}', '0', null, '2019-12-02 14:30:07');
INSERT INTO `sys_oper_log` VALUES ('904', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"18\" ],\r\n  \"itemCode\" : [ \"1041112031\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '0', null, '2019-12-02 16:48:55');
INSERT INTO `sys_oper_log` VALUES ('905', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"18\" ],\r\n  \"itemCode\" : [ \"1041112031\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"thickness\" : [ \"2\" ]\r\n}', '0', null, '2019-12-02 16:55:39');
INSERT INTO `sys_oper_log` VALUES ('906', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2027\" ],\r\n  \"parentId\" : [ \"2032\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"物料分类\" ],\r\n  \"url\" : [ \"/system/itemType\" ],\r\n  \"perms\" : [ \"system:itemType:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"1\" ]\r\n}', '0', null, '2019-12-02 17:03:50');
INSERT INTO `sys_oper_log` VALUES ('907', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"18\" ],\r\n  \"itemCode\" : [ \"1041112031\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"thickness\" : [ \"1\" ]\r\n}', '0', null, '2019-12-02 17:04:51');
INSERT INTO `sys_oper_log` VALUES ('908', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"18\" ],\r\n  \"itemCode\" : [ \"1041112031\" ],\r\n  \"itemName\" : [ \"帽子\" ],\r\n  \"thickness\" : [ \"0.1\" ]\r\n}', '0', null, '2019-12-02 17:07:18');
INSERT INTO `sys_oper_log` VALUES ('909', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"28\" ],\r\n  \"itemCode\" : [ \"278638514\" ],\r\n  \"itemName\" : [ \"憨憨\" ],\r\n  \"thickness\" : [ \"11.0\" ]\r\n}', '0', null, '2019-12-02 17:08:02');
INSERT INTO `sys_oper_log` VALUES ('910', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"121\" ]\r\n}', '0', null, '2019-12-03 10:07:52');
INSERT INTO `sys_oper_log` VALUES ('911', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"121\" ]\r\n}', '0', null, '2019-12-03 10:08:00');
INSERT INTO `sys_oper_log` VALUES ('912', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2055', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-12-03 10:13:46');
INSERT INTO `sys_oper_log` VALUES ('913', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2019-12-03 10:14:17');
INSERT INTO `sys_oper_log` VALUES ('914', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2055', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-12-03 10:14:34');
INSERT INTO `sys_oper_log` VALUES ('915', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"19\" ],\r\n  \"itemCode\" : [ \"144\" ],\r\n  \"itemName\" : [ \"144\" ],\r\n  \"thickness\" : [ \"10\" ]\r\n}', '0', null, '2019-12-09 16:10:53');
INSERT INTO `sys_oper_log` VALUES ('916', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"19\" ],\r\n  \"itemCode\" : [ \"144\" ],\r\n  \"itemName\" : [ \"144\" ],\r\n  \"thickness\" : [ \"0.5\" ]\r\n}', '0', null, '2019-12-09 16:11:10');
INSERT INTO `sys_oper_log` VALUES ('917', '定时任务', '1', 'com.deer.wms.quartz.controller.SysJobController.addSave()', '1', 'admin', '研发部门', '/monitor/job/add', '127.0.0.1', '内网IP', '{\r\n  \"jobName\" : [ \"ryTask\" ],\r\n  \"jobGroup\" : [ \"获取EBS检验数据并回传交货数量\" ],\r\n  \"methodName\" : [ \"getCheckOutFromEBS\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 30 0/1 * * ? \" ],\r\n  \"misfirePolicy\" : [ \"1\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '0', null, '2019-12-16 14:34:42');
INSERT INTO `sys_oper_log` VALUES ('918', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2019-12-16 14:35:01');
INSERT INTO `sys_oper_log` VALUES ('919', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ],\r\n  \"status\" : [ \"1\" ]\r\n}', '0', null, '2019-12-16 14:35:06');
INSERT INTO `sys_oper_log` VALUES ('920', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-16 15:08:18');
INSERT INTO `sys_oper_log` VALUES ('921', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-16 15:11:11');
INSERT INTO `sys_oper_log` VALUES ('922', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-16 15:20:34');
INSERT INTO `sys_oper_log` VALUES ('923', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"31\" ],\r\n  \"itemCode\" : [ \"104125659\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.15 B/B 0.154 20.20X24.53\" ],\r\n  \"thickness\" : [ \"0.3\" ]\r\n}', '0', null, '2019-12-17 18:29:03');
INSERT INTO `sys_oper_log` VALUES ('924', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"小林\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-12-23 17:18:44');
INSERT INTO `sys_oper_log` VALUES ('925', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"2\" ],\r\n  \"operatorName\" : [ \"鸹貔\" ],\r\n  \"operatorCard\" : [ \"0865257155\" ],\r\n  \"empNo\" : [ \"鸹貔\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-12-23 17:18:49');
INSERT INTO `sys_oper_log` VALUES ('926', '检验', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-12-24 11:27:53');
INSERT INTO `sys_oper_log` VALUES ('927', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"小林\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-12-24 12:21:02');
INSERT INTO `sys_oper_log` VALUES ('928', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"小林\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2019-12-26 15:15:52');
INSERT INTO `sys_oper_log` VALUES ('929', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:30:03');
INSERT INTO `sys_oper_log` VALUES ('930', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:37:39');
INSERT INTO `sys_oper_log` VALUES ('931', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:40:22');
INSERT INTO `sys_oper_log` VALUES ('932', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:40:51');
INSERT INTO `sys_oper_log` VALUES ('933', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:44:30');
INSERT INTO `sys_oper_log` VALUES ('934', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:48:31');
INSERT INTO `sys_oper_log` VALUES ('935', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:50:57');
INSERT INTO `sys_oper_log` VALUES ('936', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:51:21');
INSERT INTO `sys_oper_log` VALUES ('937', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:58:43');
INSERT INTO `sys_oper_log` VALUES ('938', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 17:58:59');
INSERT INTO `sys_oper_log` VALUES ('939', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 18:07:27');
INSERT INTO `sys_oper_log` VALUES ('940', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 18:07:41');
INSERT INTO `sys_oper_log` VALUES ('941', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 18:10:19');
INSERT INTO `sys_oper_log` VALUES ('942', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 18:12:53');
INSERT INTO `sys_oper_log` VALUES ('943', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-26 18:13:27');
INSERT INTO `sys_oper_log` VALUES ('944', '检验', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-12-27 14:37:33');
INSERT INTO `sys_oper_log` VALUES ('945', '检验', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '1', '', '2019-12-27 14:38:11');
INSERT INTO `sys_oper_log` VALUES ('946', '检验', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-12-27 14:43:52');
INSERT INTO `sys_oper_log` VALUES ('947', '检验', '0', 'com.deer.wms.base.system.web.box.BoxItemController.qualityAbnormalCheck()', '1', 'admin', '研发部门', '/in/boxItem/qualityAbnormalCheck', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-12-27 16:12:29');
INSERT INTO `sys_oper_log` VALUES ('948', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:37:57');
INSERT INTO `sys_oper_log` VALUES ('949', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:39:28');
INSERT INTO `sys_oper_log` VALUES ('950', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:42:14');
INSERT INTO `sys_oper_log` VALUES ('951', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:44:41');
INSERT INTO `sys_oper_log` VALUES ('952', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:54:18');
INSERT INTO `sys_oper_log` VALUES ('953', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:55:04');
INSERT INTO `sys_oper_log` VALUES ('954', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 14:59:11');
INSERT INTO `sys_oper_log` VALUES ('955', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 15:23:53');
INSERT INTO `sys_oper_log` VALUES ('956', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 15:25:00');
INSERT INTO `sys_oper_log` VALUES ('957', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 15:37:08');
INSERT INTO `sys_oper_log` VALUES ('958', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 15:37:45');
INSERT INTO `sys_oper_log` VALUES ('959', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2019-12-28 15:42:48');
INSERT INTO `sys_oper_log` VALUES ('960', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2061\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"调用EBS接口记录\" ],\r\n  \"url\" : [ \"/requestId\" ],\r\n  \"perms\" : [ \"requestId:view\" ],\r\n  \"orderNum\" : [ \"0\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-09 09:39:15');
INSERT INTO `sys_oper_log` VALUES ('961', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2076\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"默认\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-09 09:57:41');
INSERT INTO `sys_oper_log` VALUES ('962', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2076\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"刷新\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"requestId:refresh\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-09 10:00:07');
INSERT INTO `sys_oper_log` VALUES ('963', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-01-09 10:14:41');
INSERT INTO `sys_oper_log` VALUES ('964', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2078,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-01-09 10:17:30');
INSERT INTO `sys_oper_log` VALUES ('965', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-01-09 10:31:43');
INSERT INTO `sys_oper_log` VALUES ('966', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2061\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库记录\" ],\r\n  \"url\" : [ \"/billInRecord\" ],\r\n  \"perms\" : [ \"billInRecord:view\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-11 16:52:14');
INSERT INTO `sys_oper_log` VALUES ('967', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2079\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"按钮\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-11 16:52:41');
INSERT INTO `sys_oper_log` VALUES ('968', '定时任务', '1', 'com.deer.wms.quartz.controller.SysJobController.addSave()', '1', 'admin', '研发部门', '/monitor/job/add', '127.0.0.1', '内网IP', '{\r\n  \"jobName\" : [ \"4小时发一次MES工单\" ],\r\n  \"jobGroup\" : [ \"1\" ],\r\n  \"methodName\" : [ \"timeCalculate\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 0 11/4 * * ? \" ],\r\n  \"misfirePolicy\" : [ \"1\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '0', null, '2020-01-13 09:53:19');
INSERT INTO `sys_oper_log` VALUES ('969', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.editSave()', '1', 'admin', '研发部门', '/monitor/job/edit', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ],\r\n  \"jobName\" : [ \"ryTask\" ],\r\n  \"jobGroup\" : [ \"4小时发一次MES工单\" ],\r\n  \"methodName\" : [ \"timeCalculate\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 0 11/4 * * ? \" ],\r\n  \"misfirePolicy\" : [ \"1\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"\" ]\r\n}', '0', null, '2020-01-13 09:53:46');
INSERT INTO `sys_oper_log` VALUES ('970', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ]\r\n}', '0', null, '2020-01-13 12:16:47');
INSERT INTO `sys_oper_log` VALUES ('971', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"1\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.15 B/B 0.154 20.20X24.53 HL832NX A-HS-E (5R) (HF)\" ],\r\n  \"thickness\" : [ \"0.1\" ]\r\n}', '0', null, '2020-01-13 13:07:19');
INSERT INTO `sys_oper_log` VALUES ('972', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.3\" ]\r\n}', '0', null, '2020-01-13 13:07:27');
INSERT INTO `sys_oper_log` VALUES ('973', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ]\r\n}', '0', null, '2020-01-13 13:07:33');
INSERT INTO `sys_oper_log` VALUES ('974', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ]\r\n}', '0', null, '2020-01-13 13:07:49');
INSERT INTO `sys_oper_log` VALUES ('975', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ]\r\n}', '0', null, '2020-01-13 13:21:56');
INSERT INTO `sys_oper_log` VALUES ('976', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"120\" ]\r\n}', '0', null, '2020-01-13 13:22:20');
INSERT INTO `sys_oper_log` VALUES ('977', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"121\" ]\r\n}', '0', null, '2020-01-13 13:28:08');
INSERT INTO `sys_oper_log` VALUES ('978', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'admin', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"memo\" : [ \"60\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ]\r\n}', '0', null, '2020-01-13 13:31:34');
INSERT INTO `sys_oper_log` VALUES ('979', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 16:59:14');
INSERT INTO `sys_oper_log` VALUES ('980', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:00:22');
INSERT INTO `sys_oper_log` VALUES ('981', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:02:12');
INSERT INTO `sys_oper_log` VALUES ('982', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:06:32');
INSERT INTO `sys_oper_log` VALUES ('983', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:08:56');
INSERT INTO `sys_oper_log` VALUES ('984', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:09:29');
INSERT INTO `sys_oper_log` VALUES ('985', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:09:45');
INSERT INTO `sys_oper_log` VALUES ('986', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:10:37');
INSERT INTO `sys_oper_log` VALUES ('987', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:11:07');
INSERT INTO `sys_oper_log` VALUES ('988', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:14:52');
INSERT INTO `sys_oper_log` VALUES ('989', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:31:14');
INSERT INTO `sys_oper_log` VALUES ('990', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:31:51');
INSERT INTO `sys_oper_log` VALUES ('991', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:38:19');
INSERT INTO `sys_oper_log` VALUES ('992', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:45:35');
INSERT INTO `sys_oper_log` VALUES ('993', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 17:46:52');
INSERT INTO `sys_oper_log` VALUES ('994', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 18:08:49');
INSERT INTO `sys_oper_log` VALUES ('995', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 18:10:03');
INSERT INTO `sys_oper_log` VALUES ('996', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 18:10:19');
INSERT INTO `sys_oper_log` VALUES ('997', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 18:11:11');
INSERT INTO `sys_oper_log` VALUES ('998', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 18:38:56');
INSERT INTO `sys_oper_log` VALUES ('999', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 19:01:07');
INSERT INTO `sys_oper_log` VALUES ('1000', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-13 19:01:27');
INSERT INTO `sys_oper_log` VALUES ('1001', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 11:39:00');
INSERT INTO `sys_oper_log` VALUES ('1002', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 13:14:20');
INSERT INTO `sys_oper_log` VALUES ('1003', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 14:07:54');
INSERT INTO `sys_oper_log` VALUES ('1004', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 14:21:43');
INSERT INTO `sys_oper_log` VALUES ('1005', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 14:37:58');
INSERT INTO `sys_oper_log` VALUES ('1006', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 14:45:20');
INSERT INTO `sys_oper_log` VALUES ('1007', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 14:59:11');
INSERT INTO `sys_oper_log` VALUES ('1008', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 16:12:10');
INSERT INTO `sys_oper_log` VALUES ('1009', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-14 16:31:33');
INSERT INTO `sys_oper_log` VALUES ('1010', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"小林\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2020-01-15 10:44:20');
INSERT INTO `sys_oper_log` VALUES ('1011', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2020-01-15 10:45:49');
INSERT INTO `sys_oper_log` VALUES ('1012', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2076\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"处理失败数据推送至EBS\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"requestId:sendEbs\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-16 09:43:40');
INSERT INTO `sys_oper_log` VALUES ('1013', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"转库记录\" ],\r\n  \"url\" : [ \"/subinventoryTransferRecord\" ],\r\n  \"perms\" : [ \"subinventoryTransferRecord:view\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-16 16:41:39');
INSERT INTO `sys_oper_log` VALUES ('1014', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2075\" ],\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"呆滞过期报表\" ],\r\n  \"url\" : [ \"/sluggishOverdue\" ],\r\n  \"perms\" : [ \"sluggishOverdue:view\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-16 16:41:50');
INSERT INTO `sys_oper_log` VALUES ('1015', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2082\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"默认选中按钮\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-16 16:42:12');
INSERT INTO `sys_oper_log` VALUES ('1016', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2075\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"默认选中按钮\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-01-16 16:42:29');
INSERT INTO `sys_oper_log` VALUES ('1017', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2084,2082,2083,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2081,2079,2080,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-01-16 16:44:02');
INSERT INTO `sys_oper_log` VALUES ('1018', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"3\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.20 T/T 0.224 20X24 HL832NS (5R) (HF)\" ],\r\n  \"thickness\" : [ \"0.1\" ]\r\n}', '0', null, '2020-01-17 15:43:18');
INSERT INTO `sys_oper_log` VALUES ('1019', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-17 18:04:11');
INSERT INTO `sys_oper_log` VALUES ('1020', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-17 18:09:36');
INSERT INTO `sys_oper_log` VALUES ('1021', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-17 18:10:46');
INSERT INTO `sys_oper_log` VALUES ('1022', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-17 18:12:23');
INSERT INTO `sys_oper_log` VALUES ('1023', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"3\" ]\r\n}', '0', null, '2020-01-17 18:16:32');
INSERT INTO `sys_oper_log` VALUES ('1024', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"D10012\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"1\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2020-01-18 13:16:14');
INSERT INTO `sys_oper_log` VALUES ('1025', '出入库口', '2', 'com.deer.wms.base.system.web.ware.DoorController.editSave()', '1', 'admin', '研发部门', '/system/door/edit', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"5\" ],\r\n  \"code\" : [ \"AC1908252\" ],\r\n  \"addressCode\" : [ \"D10012\" ],\r\n  \"name\" : [ \"AGV出库口\" ],\r\n  \"type\" : [ \"2\" ],\r\n  \"state\" : [ \"1\" ],\r\n  \"wareId\" : [ \"212\" ]\r\n}', '0', null, '2020-01-18 13:16:25');
INSERT INTO `sys_oper_log` VALUES ('1026', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'admin', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2020-01-18 14:22:19');
INSERT INTO `sys_oper_log` VALUES ('1027', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ],\r\n  \"cardNoOne\" : [ \"\" ],\r\n  \"cardNoTwo\" : [ \"\" ]\r\n}', '0', null, '2020-01-18 23:26:51');
INSERT INTO `sys_oper_log` VALUES ('1028', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"0000000000\" ]\r\n}', '0', null, '2020-01-18 23:27:06');
INSERT INTO `sys_oper_log` VALUES ('1029', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ],\r\n  \"cardNoOne\" : [ \"1234567894\" ],\r\n  \"cardNoTwo\" : [ \"1231232133\" ]\r\n}', '0', null, '2020-01-18 23:27:51');
INSERT INTO `sys_oper_log` VALUES ('1030', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"1231232133\" ]\r\n}', '0', null, '2020-01-18 23:28:06');
INSERT INTO `sys_oper_log` VALUES ('1031', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"9638520741\" ]\r\n}', '0', null, '2020-01-18 23:28:21');
INSERT INTO `sys_oper_log` VALUES ('1032', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'admin', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"2\" ],\r\n  \"itemName\" : [ \"覆铜板 FR4.1 0.15 T/T 0.174  20.20X24.53 3D R1515E (J2)\" ],\r\n  \"thickness\" : [ \"0.2\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"1996092737\" ]\r\n}', '0', null, '2020-01-18 23:29:17');
INSERT INTO `sys_oper_log` VALUES ('1033', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 14:53:42');
INSERT INTO `sys_oper_log` VALUES ('1034', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 15:03:36');
INSERT INTO `sys_oper_log` VALUES ('1035', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 15:05:54');
INSERT INTO `sys_oper_log` VALUES ('1036', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 15:14:46');
INSERT INTO `sys_oper_log` VALUES ('1037', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 16:15:35');
INSERT INTO `sys_oper_log` VALUES ('1038', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 16:24:22');
INSERT INTO `sys_oper_log` VALUES ('1039', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 16:36:36');
INSERT INTO `sys_oper_log` VALUES ('1040', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"4\" ]\r\n}', '0', null, '2020-01-19 17:31:03');
INSERT INTO `sys_oper_log` VALUES ('1041', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库记录\" ],\r\n  \"url\" : [ \"/billInRecord\" ],\r\n  \"perms\" : [ \"billInRecord:view\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-03 09:06:47');
INSERT INTO `sys_oper_log` VALUES ('1042', '菜单管理', '2', 'com.deer.wms.admin.controller.system.SysMenuController.editSave()', '1', 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\r\n  \"menuId\" : [ \"2085\" ],\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"入库记录\" ],\r\n  \"url\" : [ \"/billInRecord\" ],\r\n  \"perms\" : [ \"billInRecord:view\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"#\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-03 09:07:25');
INSERT INTO `sys_oper_log` VALUES ('1043', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2079', '127.0.0.1', '内网IP', '{ }', '0', null, '2020-03-03 09:07:36');
INSERT INTO `sys_oper_log` VALUES ('1044', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2080', '127.0.0.1', '内网IP', '{ }', '0', null, '2020-03-03 09:07:45');
INSERT INTO `sys_oper_log` VALUES ('1045', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2084,2082,2083,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2081,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-03-03 09:09:18');
INSERT INTO `sys_oper_log` VALUES ('1046', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2079', '127.0.0.1', '内网IP', '{ }', '0', null, '2020-03-03 09:09:28');
INSERT INTO `sys_oper_log` VALUES ('1047', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2080', '127.0.0.1', '内网IP', '{ }', '0', null, '2020-03-03 09:09:32');
INSERT INTO `sys_oper_log` VALUES ('1048', '菜单管理', '3', 'com.deer.wms.admin.controller.system.SysMenuController.remove()', '1', 'admin', '研发部门', '/system/menu/remove/2079', '127.0.0.1', '内网IP', '{ }', '0', null, '2020-03-03 09:09:43');
INSERT INTO `sys_oper_log` VALUES ('1049', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2084,2082,2083,2085,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2081,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-03-04 15:26:46');
INSERT INTO `sys_oper_log` VALUES ('1050', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2058\" ],\r\n  \"menuType\" : [ \"C\" ],\r\n  \"menuName\" : [ \"出库记录\" ],\r\n  \"url\" : [ \"/pickTask\" ],\r\n  \"perms\" : [ \"pickTask:view\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-04 15:38:37');
INSERT INTO `sys_oper_log` VALUES ('1051', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2084,2082,2083,2085,2086,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2081,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-03-04 15:45:08');
INSERT INTO `sys_oper_log` VALUES ('1052', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'wxy', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"1\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.15 B/B 0.154 20.20X24.53 HL832NX A-HS-E (5R) (HF)\" ],\r\n  \"thickness\" : [ \"0.1\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"0000000000\" ]\r\n}', '0', null, '2020-03-04 17:17:22');
INSERT INTO `sys_oper_log` VALUES ('1053', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'wxy', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"1\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.15 B/B 0.154 20.20X24.53 HL832NX A-HS-E (5R) (HF)\" ],\r\n  \"thickness\" : [ \"0.1\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"\" ]\r\n}', '0', null, '2020-03-04 17:17:35');
INSERT INTO `sys_oper_log` VALUES ('1054', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'wxy', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"1\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.15 B/B 0.154 20.20X24.53 HL832NX A-HS-E (5R) (HF)\" ],\r\n  \"thickness\" : [ \"0.1\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"0865257155\" ]\r\n}', '0', null, '2020-03-04 17:18:01');
INSERT INTO `sys_oper_log` VALUES ('1055', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"添加操作员\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"system:operator:add\" ],\r\n  \"orderNum\" : [ \"3\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-05 09:03:26');
INSERT INTO `sys_oper_log` VALUES ('1056', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"导出操作员\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"system:operator:export\" ],\r\n  \"orderNum\" : [ \"4\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-05 09:04:32');
INSERT INTO `sys_oper_log` VALUES ('1057', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"修改操作员\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"system:operator:edit\" ],\r\n  \"orderNum\" : [ \"5\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-05 09:05:29');
INSERT INTO `sys_oper_log` VALUES ('1058', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2060\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"删除操作员\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"system:operator:remove\" ],\r\n  \"orderNum\" : [ \"6\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-05 09:05:54');
INSERT INTO `sys_oper_log` VALUES ('1059', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2084,2082,2083,2085,2086,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2081,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2087,2088,2089,2090,2063,2064,2065,2066,2067,2070,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-03-05 09:06:06');
INSERT INTO `sys_oper_log` VALUES ('1060', '操作员管理', '5', 'com.deer.wms.base.system.web.OperatorController.export()', '1', 'wxy', '研发部门', '/system/operator/export', '127.0.0.1', '内网IP', '{\r\n  \"keyWords\" : [ \"\" ]\r\n}', '1', '导出Excel失败，请联系网站管理员！', '2020-03-05 09:06:28');
INSERT INTO `sys_oper_log` VALUES ('1061', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'wxy', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"111111111\" ],\r\n  \"operatorCard\" : [ \"1111111111\" ],\r\n  \"empNo\" : [ \"32\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"1\" ],\r\n  \"combinePermission\" : [ \"1\" ],\r\n  \"returnItemPermission\" : [ \"1\" ],\r\n  \"qualityCheckPermission\" : [ \"1\" ],\r\n  \"scrapPermission\" : [ \"1\" ],\r\n  \"manualOutPermission\" : [ \"1\" ]\r\n}', '0', null, '2020-03-23 10:00:25');
INSERT INTO `sys_oper_log` VALUES ('1062', '物料', '2', 'com.deer.wms.base.system.web.item.ItemInfoController.editSave()', '1', 'wxy', '研发部门', '/system/itemInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"itemId\" : [ \"3\" ],\r\n  \"itemName\" : [ \"覆铜板 BT 0.20 T/T 0.224 20X24 HL832NS (5R) (HF)\" ],\r\n  \"thickness\" : [ \"0.1\" ],\r\n  \"cardNoOne\" : [ \"0000000000\" ],\r\n  \"cardNoTwo\" : [ \"1111111111\" ]\r\n}', '0', null, '2020-03-23 12:02:45');
INSERT INTO `sys_oper_log` VALUES ('1063', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'wxy', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2020-03-23 13:52:30');
INSERT INTO `sys_oper_log` VALUES ('1064', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'wxy', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2020-03-23 14:05:49');
INSERT INTO `sys_oper_log` VALUES ('1065', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'wxy', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ]\r\n}', '0', null, '2020-03-23 14:06:10');
INSERT INTO `sys_oper_log` VALUES ('1066', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2070\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"编辑按钮\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"subInventory:edit\" ],\r\n  \"orderNum\" : [ \"2\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-30 16:30:50');
INSERT INTO `sys_oper_log` VALUES ('1067', '菜单管理', '1', 'com.deer.wms.admin.controller.system.SysMenuController.addSave()', '1', 'wxy', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\r\n  \"parentId\" : [ \"2070\" ],\r\n  \"menuType\" : [ \"F\" ],\r\n  \"menuName\" : [ \"默认按钮\" ],\r\n  \"url\" : [ \"\" ],\r\n  \"perms\" : [ \"\" ],\r\n  \"orderNum\" : [ \"1\" ],\r\n  \"icon\" : [ \"\" ],\r\n  \"visible\" : [ \"0\" ]\r\n}', '0', null, '2020-03-30 16:31:14');
INSERT INTO `sys_oper_log` VALUES ('1068', '角色管理', '2', 'com.deer.wms.admin.controller.system.SysRoleController.editSave()', '1', 'wxy', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\r\n  \"roleId\" : [ \"1\" ],\r\n  \"roleName\" : [ \"管理员\" ],\r\n  \"roleKey\" : [ \"admin\" ],\r\n  \"roleSort\" : [ \"1\" ],\r\n  \"status\" : [ \"0\" ],\r\n  \"remark\" : [ \"管理员\" ],\r\n  \"menuIds\" : [ \"2058,2059,2075,2084,2082,2083,2085,2086,2038,2039,2040,2041,2042,2043,2056,2057,2049,2071,2061,2076,2077,2078,2081,2062,2069,1,100,1000,1001,1002,1003,1004,1005,1006,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2017,2018,2019,2020,2021,2012,2013,2014,2015,2016,2033,2034,2035,2036,2037,2050,2051,2052,2053,2054,2044,2045,2046,2047,2048,2032,2027,2028,2029,2030,2031,2022,2023,2024,2025,2026,2060,2072,2073,2087,2088,2089,2090,2063,2064,2065,2066,2067,2070,2092,2091,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,1042,501,1043,1044,1045,3,113,114,1056,1057,115,2,109,1046,1047,1048,110,1049,1050,1051,1052,1053,1054,1055,111,112\" ]\r\n}', '0', null, '2020-03-30 16:32:03');
INSERT INTO `sys_oper_log` VALUES ('1069', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'wxy', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ],\r\n  \"unqualifiedStorageDay\" : [ \"7\" ]\r\n}', '0', null, '2020-04-02 14:53:26');
INSERT INTO `sys_oper_log` VALUES ('1070', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'wxy', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ],\r\n  \"unqualifiedStorageDay\" : [ \"8\" ]\r\n}', '0', null, '2020-04-02 14:56:43');
INSERT INTO `sys_oper_log` VALUES ('1071', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'wxy', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ],\r\n  \"unqualifiedStorageDay\" : [ \"9\" ]\r\n}', '0', null, '2020-04-02 15:26:20');
INSERT INTO `sys_oper_log` VALUES ('1072', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'wxy', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ],\r\n  \"unqualifiedStorageDay\" : [ \"10\" ]\r\n}', '0', null, '2020-04-02 15:28:15');
INSERT INTO `sys_oper_log` VALUES ('1073', '仓库设置', '2', 'com.deer.wms.web.controller.ware.WareInfoController.editSave()', '1', 'wxy', '研发部门', '/system/wareInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"wareId\" : [ \"212\" ],\r\n  \"wareCode\" : [ \"AC1908252\" ],\r\n  \"wareName\" : [ \"堆垛机库\" ],\r\n  \"expectedWaring\" : [ \"10.0\" ],\r\n  \"alarm\" : [ \"60.0\" ],\r\n  \"stockWaring\" : [ \"12\" ],\r\n  \"boxParam\" : [ \"60.9\" ],\r\n  \"boxHeight\" : [ \"130\" ],\r\n  \"unqualifiedStorageDay\" : [ \"10\" ]\r\n}', '0', null, '2020-04-02 15:28:23');
INSERT INTO `sys_oper_log` VALUES ('1074', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'wxy', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ],\r\n  \"transferWarehousePermission\" : [ \"1\" ]\r\n}', '0', null, '2020-04-02 16:14:53');
INSERT INTO `sys_oper_log` VALUES ('1075', '删除操作员', '3', 'com.deer.wms.base.system.web.OperatorController.remove()', '1', 'wxy', '研发部门', '/system/operator/remove/1', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2020-04-02 16:15:02');
INSERT INTO `sys_oper_log` VALUES ('1076', '定时任务', '1', 'com.deer.wms.quartz.controller.SysJobController.addSave()', '1', 'wxy', '研发部门', '/monitor/job/add', '127.0.0.1', '内网IP', '{\r\n  \"jobName\" : [ \"ryTask\" ],\r\n  \"jobGroup\" : [ \"定时查询不合格状态物料是否超时接口\" ],\r\n  \"methodName\" : [ \"transferOverdue\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 10 1 1/1 * ?\" ],\r\n  \"misfirePolicy\" : [ \"3\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"查询不合格状态物料是否超时,超过设定时间则报警\" ]\r\n}', '0', null, '2020-04-02 16:27:35');
INSERT INTO `sys_oper_log` VALUES ('1077', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.editSave()', '1', 'wxy', '研发部门', '/monitor/job/edit', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ],\r\n  \"jobName\" : [ \"ryTask\" ],\r\n  \"jobGroup\" : [ \"定时检查不合格状态物料存储时间是否超时接口\" ],\r\n  \"methodName\" : [ \"unqualifiedStorageSuggishOverdue\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 10 1 1/1 * ?\" ],\r\n  \"misfirePolicy\" : [ \"3\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"查询不合格状态物料是否超时,超过设定时间则报警\" ]\r\n}', '0', null, '2020-04-03 10:11:16');
INSERT INTO `sys_oper_log` VALUES ('1078', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.editSave()', '1', 'wxy', '研发部门', '/monitor/job/edit', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ],\r\n  \"jobName\" : [ \"ryTask\" ],\r\n  \"jobGroup\" : [ \"定时检查不合格物料存储时间是否超时接口\" ],\r\n  \"methodName\" : [ \"unqualifiedStorageSuggishOverdue\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 10 1 1/1 * ?\" ],\r\n  \"misfirePolicy\" : [ \"3\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"查询不合格状态物料是否超时,超过设定时间则报警\" ]\r\n}', '0', null, '2020-04-03 10:11:31');
INSERT INTO `sys_oper_log` VALUES ('1079', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.editSave()', '1', 'wxy', '研发部门', '/monitor/job/edit', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ],\r\n  \"jobName\" : [ \"ryTask\" ],\r\n  \"jobGroup\" : [ \"检查不合格物料存储时间是否超时接口\" ],\r\n  \"methodName\" : [ \"unqualifiedStorageSuggishOverdue\" ],\r\n  \"methodParams\" : [ \"\" ],\r\n  \"cronExpression\" : [ \"0 10 1 1/1 * ?\" ],\r\n  \"misfirePolicy\" : [ \"3\" ],\r\n  \"concurrent\" : [ \"1\" ],\r\n  \"status\" : [ \"1\" ],\r\n  \"remark\" : [ \"查询不合格状态物料是否超时,超过设定时间则报警\" ]\r\n}', '0', null, '2020-04-03 10:11:53');
INSERT INTO `sys_oper_log` VALUES ('1080', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 10:11:58');
INSERT INTO `sys_oper_log` VALUES ('1081', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 10:13:59');
INSERT INTO `sys_oper_log` VALUES ('1082', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 10:15:03');
INSERT INTO `sys_oper_log` VALUES ('1083', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 10:16:11');
INSERT INTO `sys_oper_log` VALUES ('1084', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 10:54:53');
INSERT INTO `sys_oper_log` VALUES ('1085', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 11:00:37');
INSERT INTO `sys_oper_log` VALUES ('1086', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 11:01:34');
INSERT INTO `sys_oper_log` VALUES ('1087', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 11:15:59');
INSERT INTO `sys_oper_log` VALUES ('1088', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 11:20:29');
INSERT INTO `sys_oper_log` VALUES ('1089', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 11:40:13');
INSERT INTO `sys_oper_log` VALUES ('1090', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 11:40:31');
INSERT INTO `sys_oper_log` VALUES ('1091', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 15:18:19');
INSERT INTO `sys_oper_log` VALUES ('1092', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 16:51:33');
INSERT INTO `sys_oper_log` VALUES ('1093', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 16:53:51');
INSERT INTO `sys_oper_log` VALUES ('1094', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 16:53:57');
INSERT INTO `sys_oper_log` VALUES ('1095', '定时任务', '2', 'com.deer.wms.quartz.controller.SysJobController.run()', '1', 'wxy', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\r\n  \"jobId\" : [ \"5\" ]\r\n}', '0', null, '2020-04-03 16:58:49');
INSERT INTO `sys_oper_log` VALUES ('1096', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'wxy', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"1\" ],\r\n  \"operatorName\" : [ \"老李\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"AE86\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"2\" ],\r\n  \"combinePermission\" : [ \"2\" ],\r\n  \"returnItemPermission\" : [ \"2\" ],\r\n  \"qualityCheckPermission\" : [ \"2\" ],\r\n  \"scrapPermission\" : [ \"2\" ],\r\n  \"manualOutPermission\" : [ \"2\" ],\r\n  \"transferWarehousePermission\" : [ \"2\" ]\r\n}', '0', null, '2020-04-07 11:20:03');
INSERT INTO `sys_oper_log` VALUES ('1097', '货位设置', '2', 'com.deer.wms.base.system.web.ware.CellInfoController.editSave()', '1', 'wxy', '研发部门', '/system/cellInfo/edit', '127.0.0.1', '内网IP', '{\r\n  \"cellId\" : [ \"2175\" ],\r\n  \"memo\" : [ \"\" ],\r\n  \"state\" : [ \"1\" ]\r\n}', '0', null, '2020-04-09 15:57:23');
INSERT INTO `sys_oper_log` VALUES ('1098', '保存操作员', '1', 'com.deer.wms.base.system.web.OperatorController.addSave()', '1', 'wxy', '研发部门', '/system/operator/addSave', '127.0.0.1', '内网IP', '{\r\n  \"operatorName\" : [ \"吴\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"Hony\" ],\r\n  \"autoverifyPermission\" : [ \"1\" ],\r\n  \"checkPermission\" : [ \"1\" ],\r\n  \"combinePermission\" : [ \"1\" ],\r\n  \"returnItemPermission\" : [ \"1\" ],\r\n  \"qualityCheckPermission\" : [ \"1\" ],\r\n  \"scrapPermission\" : [ \"1\" ],\r\n  \"manualOutPermission\" : [ \"1\" ],\r\n  \"transferWarehousePermission\" : [ \"1\" ]\r\n}', '0', null, '2020-04-15 14:42:47');
INSERT INTO `sys_oper_log` VALUES ('1099', '操作员设置', '2', 'com.deer.wms.base.system.web.OperatorController.editSave()', '1', 'wxy', '研发部门', '/system/operator/edit', '127.0.0.1', '内网IP', '{\r\n  \"operatorId\" : [ \"4\" ],\r\n  \"operatorName\" : [ \"吴\" ],\r\n  \"operatorCard\" : [ \"0000000000\" ],\r\n  \"empNo\" : [ \"Hony\" ],\r\n  \"autoverifyPermission\" : [ \"2\" ],\r\n  \"checkPermission\" : [ \"1\" ],\r\n  \"combinePermission\" : [ \"1\" ],\r\n  \"returnItemPermission\" : [ \"1\" ],\r\n  \"qualityCheckPermission\" : [ \"1\" ],\r\n  \"scrapPermission\" : [ \"1\" ],\r\n  \"manualOutPermission\" : [ \"1\" ],\r\n  \"transferWarehousePermission\" : [ \"2\" ]\r\n}', '0', null, '2020-04-15 15:03:38');
INSERT INTO `sys_oper_log` VALUES ('1100', '部门管理', '2', 'com.deer.wms.admin.controller.system.SysDeptController.editSave()', '1', 'wxy', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"100\" ],\r\n  \"parentId\" : [ \"0\" ],\r\n  \"parentName\" : [ \"无\" ],\r\n  \"deptName\" : [ \"南京大鹿智造科技有限公司\" ],\r\n  \"orderNum\" : [ \"0\" ],\r\n  \"leader\" : [ \"\" ],\r\n  \"phone\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"\" ],\r\n  \"status\" : [ \"0\" ]\r\n}', '0', null, '2020-04-18 23:11:06');
INSERT INTO `sys_oper_log` VALUES ('1101', '通知公告', '3', 'com.deer.wms.admin.controller.system.SysNoticeController.remove()', '1', 'wxy', '研发部门', '/system/notice/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"2\" ]\r\n}', '0', null, '2020-04-18 23:11:29');
INSERT INTO `sys_oper_log` VALUES ('1102', '通知公告', '3', 'com.deer.wms.admin.controller.system.SysNoticeController.remove()', '1', 'wxy', '研发部门', '/system/notice/remove', '127.0.0.1', '内网IP', '{\r\n  \"ids\" : [ \"1\" ]\r\n}', '0', null, '2020-04-18 23:11:31');

-- ----------------------------
-- Table structure for `sys_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'wxy', '2020-03-30 16:32:03', '管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2019-06-03 15:13:49', '普通角色');
INSERT INTO `sys_role` VALUES ('100', '入库操作员', 'operator', '3', '1', '0', '0', 'admin', '2019-05-08 16:19:01', 'admin', '2019-11-25 10:36:54', '入库操作员');
INSERT INTO `sys_role` VALUES ('101', 'IQC', 'IQC', '4', '1', '0', '0', 'admin', '2019-11-15 09:31:09', '', null, null);

-- ----------------------------
-- Table structure for `sys_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '100');
INSERT INTO `sys_role_menu` VALUES ('1', '101');
INSERT INTO `sys_role_menu` VALUES ('1', '102');
INSERT INTO `sys_role_menu` VALUES ('1', '103');
INSERT INTO `sys_role_menu` VALUES ('1', '104');
INSERT INTO `sys_role_menu` VALUES ('1', '105');
INSERT INTO `sys_role_menu` VALUES ('1', '106');
INSERT INTO `sys_role_menu` VALUES ('1', '107');
INSERT INTO `sys_role_menu` VALUES ('1', '108');
INSERT INTO `sys_role_menu` VALUES ('1', '109');
INSERT INTO `sys_role_menu` VALUES ('1', '110');
INSERT INTO `sys_role_menu` VALUES ('1', '111');
INSERT INTO `sys_role_menu` VALUES ('1', '112');
INSERT INTO `sys_role_menu` VALUES ('1', '113');
INSERT INTO `sys_role_menu` VALUES ('1', '114');
INSERT INTO `sys_role_menu` VALUES ('1', '115');
INSERT INTO `sys_role_menu` VALUES ('1', '500');
INSERT INTO `sys_role_menu` VALUES ('1', '501');
INSERT INTO `sys_role_menu` VALUES ('1', '1000');
INSERT INTO `sys_role_menu` VALUES ('1', '1001');
INSERT INTO `sys_role_menu` VALUES ('1', '1002');
INSERT INTO `sys_role_menu` VALUES ('1', '1003');
INSERT INTO `sys_role_menu` VALUES ('1', '1004');
INSERT INTO `sys_role_menu` VALUES ('1', '1005');
INSERT INTO `sys_role_menu` VALUES ('1', '1006');
INSERT INTO `sys_role_menu` VALUES ('1', '1007');
INSERT INTO `sys_role_menu` VALUES ('1', '1008');
INSERT INTO `sys_role_menu` VALUES ('1', '1009');
INSERT INTO `sys_role_menu` VALUES ('1', '1010');
INSERT INTO `sys_role_menu` VALUES ('1', '1011');
INSERT INTO `sys_role_menu` VALUES ('1', '1012');
INSERT INTO `sys_role_menu` VALUES ('1', '1013');
INSERT INTO `sys_role_menu` VALUES ('1', '1014');
INSERT INTO `sys_role_menu` VALUES ('1', '1015');
INSERT INTO `sys_role_menu` VALUES ('1', '1016');
INSERT INTO `sys_role_menu` VALUES ('1', '1017');
INSERT INTO `sys_role_menu` VALUES ('1', '1018');
INSERT INTO `sys_role_menu` VALUES ('1', '1019');
INSERT INTO `sys_role_menu` VALUES ('1', '1020');
INSERT INTO `sys_role_menu` VALUES ('1', '1021');
INSERT INTO `sys_role_menu` VALUES ('1', '1022');
INSERT INTO `sys_role_menu` VALUES ('1', '1023');
INSERT INTO `sys_role_menu` VALUES ('1', '1024');
INSERT INTO `sys_role_menu` VALUES ('1', '1025');
INSERT INTO `sys_role_menu` VALUES ('1', '1026');
INSERT INTO `sys_role_menu` VALUES ('1', '1027');
INSERT INTO `sys_role_menu` VALUES ('1', '1028');
INSERT INTO `sys_role_menu` VALUES ('1', '1029');
INSERT INTO `sys_role_menu` VALUES ('1', '1030');
INSERT INTO `sys_role_menu` VALUES ('1', '1031');
INSERT INTO `sys_role_menu` VALUES ('1', '1032');
INSERT INTO `sys_role_menu` VALUES ('1', '1033');
INSERT INTO `sys_role_menu` VALUES ('1', '1034');
INSERT INTO `sys_role_menu` VALUES ('1', '1035');
INSERT INTO `sys_role_menu` VALUES ('1', '1036');
INSERT INTO `sys_role_menu` VALUES ('1', '1037');
INSERT INTO `sys_role_menu` VALUES ('1', '1038');
INSERT INTO `sys_role_menu` VALUES ('1', '1039');
INSERT INTO `sys_role_menu` VALUES ('1', '1040');
INSERT INTO `sys_role_menu` VALUES ('1', '1041');
INSERT INTO `sys_role_menu` VALUES ('1', '1042');
INSERT INTO `sys_role_menu` VALUES ('1', '1043');
INSERT INTO `sys_role_menu` VALUES ('1', '1044');
INSERT INTO `sys_role_menu` VALUES ('1', '1045');
INSERT INTO `sys_role_menu` VALUES ('1', '1046');
INSERT INTO `sys_role_menu` VALUES ('1', '1047');
INSERT INTO `sys_role_menu` VALUES ('1', '1048');
INSERT INTO `sys_role_menu` VALUES ('1', '1049');
INSERT INTO `sys_role_menu` VALUES ('1', '1050');
INSERT INTO `sys_role_menu` VALUES ('1', '1051');
INSERT INTO `sys_role_menu` VALUES ('1', '1052');
INSERT INTO `sys_role_menu` VALUES ('1', '1053');
INSERT INTO `sys_role_menu` VALUES ('1', '1054');
INSERT INTO `sys_role_menu` VALUES ('1', '1055');
INSERT INTO `sys_role_menu` VALUES ('1', '1056');
INSERT INTO `sys_role_menu` VALUES ('1', '1057');
INSERT INTO `sys_role_menu` VALUES ('1', '2001');
INSERT INTO `sys_role_menu` VALUES ('1', '2002');
INSERT INTO `sys_role_menu` VALUES ('1', '2003');
INSERT INTO `sys_role_menu` VALUES ('1', '2004');
INSERT INTO `sys_role_menu` VALUES ('1', '2005');
INSERT INTO `sys_role_menu` VALUES ('1', '2006');
INSERT INTO `sys_role_menu` VALUES ('1', '2007');
INSERT INTO `sys_role_menu` VALUES ('1', '2008');
INSERT INTO `sys_role_menu` VALUES ('1', '2009');
INSERT INTO `sys_role_menu` VALUES ('1', '2010');
INSERT INTO `sys_role_menu` VALUES ('1', '2011');
INSERT INTO `sys_role_menu` VALUES ('1', '2012');
INSERT INTO `sys_role_menu` VALUES ('1', '2013');
INSERT INTO `sys_role_menu` VALUES ('1', '2014');
INSERT INTO `sys_role_menu` VALUES ('1', '2015');
INSERT INTO `sys_role_menu` VALUES ('1', '2016');
INSERT INTO `sys_role_menu` VALUES ('1', '2017');
INSERT INTO `sys_role_menu` VALUES ('1', '2018');
INSERT INTO `sys_role_menu` VALUES ('1', '2019');
INSERT INTO `sys_role_menu` VALUES ('1', '2020');
INSERT INTO `sys_role_menu` VALUES ('1', '2021');
INSERT INTO `sys_role_menu` VALUES ('1', '2022');
INSERT INTO `sys_role_menu` VALUES ('1', '2023');
INSERT INTO `sys_role_menu` VALUES ('1', '2024');
INSERT INTO `sys_role_menu` VALUES ('1', '2025');
INSERT INTO `sys_role_menu` VALUES ('1', '2026');
INSERT INTO `sys_role_menu` VALUES ('1', '2027');
INSERT INTO `sys_role_menu` VALUES ('1', '2028');
INSERT INTO `sys_role_menu` VALUES ('1', '2029');
INSERT INTO `sys_role_menu` VALUES ('1', '2030');
INSERT INTO `sys_role_menu` VALUES ('1', '2031');
INSERT INTO `sys_role_menu` VALUES ('1', '2032');
INSERT INTO `sys_role_menu` VALUES ('1', '2033');
INSERT INTO `sys_role_menu` VALUES ('1', '2034');
INSERT INTO `sys_role_menu` VALUES ('1', '2035');
INSERT INTO `sys_role_menu` VALUES ('1', '2036');
INSERT INTO `sys_role_menu` VALUES ('1', '2037');
INSERT INTO `sys_role_menu` VALUES ('1', '2038');
INSERT INTO `sys_role_menu` VALUES ('1', '2039');
INSERT INTO `sys_role_menu` VALUES ('1', '2040');
INSERT INTO `sys_role_menu` VALUES ('1', '2041');
INSERT INTO `sys_role_menu` VALUES ('1', '2042');
INSERT INTO `sys_role_menu` VALUES ('1', '2043');
INSERT INTO `sys_role_menu` VALUES ('1', '2044');
INSERT INTO `sys_role_menu` VALUES ('1', '2045');
INSERT INTO `sys_role_menu` VALUES ('1', '2046');
INSERT INTO `sys_role_menu` VALUES ('1', '2047');
INSERT INTO `sys_role_menu` VALUES ('1', '2048');
INSERT INTO `sys_role_menu` VALUES ('1', '2049');
INSERT INTO `sys_role_menu` VALUES ('1', '2050');
INSERT INTO `sys_role_menu` VALUES ('1', '2051');
INSERT INTO `sys_role_menu` VALUES ('1', '2052');
INSERT INTO `sys_role_menu` VALUES ('1', '2053');
INSERT INTO `sys_role_menu` VALUES ('1', '2054');
INSERT INTO `sys_role_menu` VALUES ('1', '2056');
INSERT INTO `sys_role_menu` VALUES ('1', '2057');
INSERT INTO `sys_role_menu` VALUES ('1', '2058');
INSERT INTO `sys_role_menu` VALUES ('1', '2059');
INSERT INTO `sys_role_menu` VALUES ('1', '2060');
INSERT INTO `sys_role_menu` VALUES ('1', '2061');
INSERT INTO `sys_role_menu` VALUES ('1', '2062');
INSERT INTO `sys_role_menu` VALUES ('1', '2063');
INSERT INTO `sys_role_menu` VALUES ('1', '2064');
INSERT INTO `sys_role_menu` VALUES ('1', '2065');
INSERT INTO `sys_role_menu` VALUES ('1', '2066');
INSERT INTO `sys_role_menu` VALUES ('1', '2067');
INSERT INTO `sys_role_menu` VALUES ('1', '2069');
INSERT INTO `sys_role_menu` VALUES ('1', '2070');
INSERT INTO `sys_role_menu` VALUES ('1', '2071');
INSERT INTO `sys_role_menu` VALUES ('1', '2072');
INSERT INTO `sys_role_menu` VALUES ('1', '2073');
INSERT INTO `sys_role_menu` VALUES ('1', '2075');
INSERT INTO `sys_role_menu` VALUES ('1', '2076');
INSERT INTO `sys_role_menu` VALUES ('1', '2077');
INSERT INTO `sys_role_menu` VALUES ('1', '2078');
INSERT INTO `sys_role_menu` VALUES ('1', '2081');
INSERT INTO `sys_role_menu` VALUES ('1', '2082');
INSERT INTO `sys_role_menu` VALUES ('1', '2083');
INSERT INTO `sys_role_menu` VALUES ('1', '2084');
INSERT INTO `sys_role_menu` VALUES ('1', '2085');
INSERT INTO `sys_role_menu` VALUES ('1', '2086');
INSERT INTO `sys_role_menu` VALUES ('1', '2087');
INSERT INTO `sys_role_menu` VALUES ('1', '2088');
INSERT INTO `sys_role_menu` VALUES ('1', '2089');
INSERT INTO `sys_role_menu` VALUES ('1', '2090');
INSERT INTO `sys_role_menu` VALUES ('1', '2091');
INSERT INTO `sys_role_menu` VALUES ('1', '2092');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '101');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '104');
INSERT INTO `sys_role_menu` VALUES ('2', '105');
INSERT INTO `sys_role_menu` VALUES ('2', '106');
INSERT INTO `sys_role_menu` VALUES ('2', '107');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '109');
INSERT INTO `sys_role_menu` VALUES ('2', '110');
INSERT INTO `sys_role_menu` VALUES ('2', '111');
INSERT INTO `sys_role_menu` VALUES ('2', '112');
INSERT INTO `sys_role_menu` VALUES ('2', '113');
INSERT INTO `sys_role_menu` VALUES ('2', '114');
INSERT INTO `sys_role_menu` VALUES ('2', '115');
INSERT INTO `sys_role_menu` VALUES ('2', '500');
INSERT INTO `sys_role_menu` VALUES ('2', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '1000');
INSERT INTO `sys_role_menu` VALUES ('2', '1001');
INSERT INTO `sys_role_menu` VALUES ('2', '1002');
INSERT INTO `sys_role_menu` VALUES ('2', '1003');
INSERT INTO `sys_role_menu` VALUES ('2', '1004');
INSERT INTO `sys_role_menu` VALUES ('2', '1005');
INSERT INTO `sys_role_menu` VALUES ('2', '1006');
INSERT INTO `sys_role_menu` VALUES ('2', '1007');
INSERT INTO `sys_role_menu` VALUES ('2', '1008');
INSERT INTO `sys_role_menu` VALUES ('2', '1009');
INSERT INTO `sys_role_menu` VALUES ('2', '1010');
INSERT INTO `sys_role_menu` VALUES ('2', '1011');
INSERT INTO `sys_role_menu` VALUES ('2', '1012');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '1017');
INSERT INTO `sys_role_menu` VALUES ('2', '1018');
INSERT INTO `sys_role_menu` VALUES ('2', '1019');
INSERT INTO `sys_role_menu` VALUES ('2', '1020');
INSERT INTO `sys_role_menu` VALUES ('2', '1021');
INSERT INTO `sys_role_menu` VALUES ('2', '1022');
INSERT INTO `sys_role_menu` VALUES ('2', '1023');
INSERT INTO `sys_role_menu` VALUES ('2', '1024');
INSERT INTO `sys_role_menu` VALUES ('2', '1025');
INSERT INTO `sys_role_menu` VALUES ('2', '1026');
INSERT INTO `sys_role_menu` VALUES ('2', '1027');
INSERT INTO `sys_role_menu` VALUES ('2', '1028');
INSERT INTO `sys_role_menu` VALUES ('2', '1029');
INSERT INTO `sys_role_menu` VALUES ('2', '1030');
INSERT INTO `sys_role_menu` VALUES ('2', '1031');
INSERT INTO `sys_role_menu` VALUES ('2', '1032');
INSERT INTO `sys_role_menu` VALUES ('2', '1033');
INSERT INTO `sys_role_menu` VALUES ('2', '1034');
INSERT INTO `sys_role_menu` VALUES ('2', '1035');
INSERT INTO `sys_role_menu` VALUES ('2', '1036');
INSERT INTO `sys_role_menu` VALUES ('2', '1037');
INSERT INTO `sys_role_menu` VALUES ('2', '1038');
INSERT INTO `sys_role_menu` VALUES ('2', '1039');
INSERT INTO `sys_role_menu` VALUES ('2', '1040');
INSERT INTO `sys_role_menu` VALUES ('2', '1041');
INSERT INTO `sys_role_menu` VALUES ('2', '1042');
INSERT INTO `sys_role_menu` VALUES ('2', '1043');
INSERT INTO `sys_role_menu` VALUES ('2', '1044');
INSERT INTO `sys_role_menu` VALUES ('2', '1045');
INSERT INTO `sys_role_menu` VALUES ('2', '1046');
INSERT INTO `sys_role_menu` VALUES ('2', '1047');
INSERT INTO `sys_role_menu` VALUES ('2', '1048');
INSERT INTO `sys_role_menu` VALUES ('2', '1049');
INSERT INTO `sys_role_menu` VALUES ('2', '1050');
INSERT INTO `sys_role_menu` VALUES ('2', '1051');
INSERT INTO `sys_role_menu` VALUES ('2', '1052');
INSERT INTO `sys_role_menu` VALUES ('2', '1053');
INSERT INTO `sys_role_menu` VALUES ('2', '1054');
INSERT INTO `sys_role_menu` VALUES ('2', '1055');
INSERT INTO `sys_role_menu` VALUES ('2', '1056');
INSERT INTO `sys_role_menu` VALUES ('2', '1057');
INSERT INTO `sys_role_menu` VALUES ('2', '2038');
INSERT INTO `sys_role_menu` VALUES ('2', '2039');
INSERT INTO `sys_role_menu` VALUES ('2', '2040');
INSERT INTO `sys_role_menu` VALUES ('2', '2041');
INSERT INTO `sys_role_menu` VALUES ('2', '2042');
INSERT INTO `sys_role_menu` VALUES ('2', '2043');
INSERT INTO `sys_role_menu` VALUES ('2', '2049');
INSERT INTO `sys_role_menu` VALUES ('100', '2038');
INSERT INTO `sys_role_menu` VALUES ('100', '2039');
INSERT INTO `sys_role_menu` VALUES ('100', '2040');
INSERT INTO `sys_role_menu` VALUES ('100', '2041');
INSERT INTO `sys_role_menu` VALUES ('100', '2042');
INSERT INTO `sys_role_menu` VALUES ('100', '2043');
INSERT INTO `sys_role_menu` VALUES ('100', '2049');
INSERT INTO `sys_role_menu` VALUES ('100', '2056');
INSERT INTO `sys_role_menu` VALUES ('100', '2057');
INSERT INTO `sys_role_menu` VALUES ('100', '2061');
INSERT INTO `sys_role_menu` VALUES ('100', '2062');
INSERT INTO `sys_role_menu` VALUES ('100', '2069');
INSERT INTO `sys_role_menu` VALUES ('100', '2071');
INSERT INTO `sys_role_menu` VALUES ('101', '2061');
INSERT INTO `sys_role_menu` VALUES ('101', '2062');
INSERT INTO `sys_role_menu` VALUES ('101', '2069');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '巡城鹿', '00', '', '15888888888', '0', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2020-03-03 09:09:54', 'admin', '2018-03-16 11:33:00', 'ry', '2020-03-03 09:09:53', '管理员');
INSERT INTO `sys_user` VALUES ('3', '103', 'wxy', 'wxy', '00', 'wxjbftbzdk@scc.com.cn', '13458735486', '1', '', '0aac848e361daa2912d21986918d8375', 'cdda19', '0', '0', '192.168.1.102', '2020-04-19 14:52:10', 'admin', '2019-10-30 15:30:18', 'admin', '2020-04-19 14:52:07', '测试账号');
INSERT INTO `sys_user` VALUES ('4', '110', 'operator', '入库操作台', '00', '', '15454545454', '0', '', '21563681e21c98f9cf2cafa97dd2abf0', '80f343', '0', '0', '127.0.0.1', '2019-11-25 10:37:04', 'admin', '2019-11-15 09:34:43', 'admin', '2019-11-25 10:37:04', '');
INSERT INTO `sys_user` VALUES ('5', '111', 'IQC', 'IQC', '00', '', '15436579565', '0', '', 'd33c25dbd1608b15b78f89c0e328c135', 'e1d7ec', '0', '0', '127.0.0.1', '2019-11-15 09:38:33', 'admin', '2019-11-15 09:36:00', '', '2019-11-15 09:38:33', '');
INSERT INTO `sys_user` VALUES ('6', '103', '1111', '铁憨憨', '00', '', '', '0', '', '951d3bc93faa33f3ea8222ed366239cd', '900482', '0', '0', '', null, 'admin', '2019-11-25 17:31:00', '', null, '铁憨憨');

-- ----------------------------
-- Table structure for `sys_user_online`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('286338c0-7b43-42cf-b710-198342f102d5', 'wxy', '研发部门', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', 'on_line', '2020-04-18 23:06:40', '2020-04-19 01:20:19', '2073600000');
INSERT INTO `sys_user_online` VALUES ('6cf47ff9-7068-49a8-ba26-2cbd9985b1b9', 'wxy', '研发部门', '127.0.0.1', '内网IP', 'Chrome 8', 'Windows 7', 'on_line', '2020-04-19 14:17:55', '2020-04-19 14:17:59', '2073600000');
INSERT INTO `sys_user_online` VALUES ('d1c1f315-8496-4c03-925c-1296ce7ebeb2', 'wxy', '研发部门', '192.168.1.102', '内网IP', 'Chrome 8', 'Windows 10', 'on_line', '2020-04-19 14:29:30', '2020-04-19 14:52:14', '2073600000');

-- ----------------------------
-- Table structure for `sys_user_post`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');
INSERT INTO `sys_user_post` VALUES ('3', '2');
INSERT INTO `sys_user_post` VALUES ('4', '4');
INSERT INTO `sys_user_post` VALUES ('5', '4');
INSERT INTO `sys_user_post` VALUES ('6', '4');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1');
INSERT INTO `sys_user_role` VALUES ('4', '100');
INSERT INTO `sys_user_role` VALUES ('5', '101');
INSERT INTO `sys_user_role` VALUES ('6', '2');

-- ----------------------------
-- Table structure for `task_info`
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_id` varchar(50) DEFAULT NULL COMMENT 'GUID',
  `start_position` varchar(20) DEFAULT NULL COMMENT '起始位置',
  `end_position` varchar(20) DEFAULT NULL COMMENT '结束位置',
  `type` int(11) DEFAULT NULL COMMENT '任务类型:\r\n 1-入库任务(将空/半空托盘从货位上移到固定位置)\r\n\r\n2-入库任务(当托盘已经到达装货点，装货完毕后   寻找合适的货位返回)\r\n\r\n//3-出库任务(根据货物编码  以及需要出库的数量，生成需要的N条任务信息，将合适的托盘移到固定位置/卸货位置)\r\n\r\n4-根据托盘编码 ， 将托盘放置空货位上\r\n\r\n5-出库任务-将有合适货物的托盘调度到出货口(固定位置)',
  `state` int(11) DEFAULT '0' COMMENT '状态 0-已下发 1-执行中 2-报错  3-已完成',
  `quantity` int(11) DEFAULT NULL COMMENT '点数数量',
  `complete_quantity` int(11) DEFAULT NULL COMMENT '完成数量',
  `bar_code` varchar(255) DEFAULT NULL COMMENT '条码信息（贴标使用）',
  `box_code` varchar(50) DEFAULT NULL COMMENT '托盘/料箱编码',
  `bill_in_detail_id` int(11) DEFAULT NULL,
  `istop` varchar(2) DEFAULT '',
  `bill_out_detail_id` int(11) DEFAULT NULL,
  `card_no` varchar(20) DEFAULT NULL,
  `task_start_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `task_end_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `task_info_box_code` (`box_code`) USING BTREE,
  KEY `task_info_task_id` (`task_id`) USING BTREE,
  KEY `bill_out_detail_id` (`bill_out_detail_id`) USING BTREE,
  KEY `task_info_isTop` (`istop`) USING BTREE,
  KEY `bill_in_detail_id` (`bill_in_detail_id`) USING BTREE,
  KEY `task_info_type` (`type`) USING BTREE,
  KEY `task_info_state` (`state`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='任务';

-- ----------------------------
-- Records of task_info
-- ----------------------------
INSERT INTO `task_info` VALUES ('1', 'BA13B530-F578-A1C3-2C7D-F254B14F11EF', '105', '01001001', '2', '3', null, null, null, 'FTB0001', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('2', 'C4D49D71-3428-62D8-100A-3B82AA9DA8F2', '105', '01002001', '2', '3', null, null, null, 'FTB0002', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('3', 'D93CEDEF-0A86-A9A4-EB2E-367752918524', '105', '01003001', '2', '3', null, null, null, 'FTB0003', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('4', '0248B6EF-DA93-293E-DF16-77790538C270', '105', '01004001', '2', '3', null, null, null, 'FTB0004', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('5', 'EF1FCC62-D542-58F8-3585-265AE4E37240', '105', '01006001', '2', '3', null, null, null, 'FTB0005', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('6', '60223C85-5F26-236D-7FB3-99125B091A7D', '105', '01007001', '2', '3', null, null, null, 'FTB0006', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('7', '76E68F54-F9A3-6042-FFB8-16330D3A76EB', '105', '01008001', '2', '3', null, null, null, 'FTB0007', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('8', 'C3B16A7E-F95A-65A4-A787-8FAADDF78305', '105', '01010001', '2', '3', null, null, null, 'FTB0008', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('9', 'BF9AD8EE-723A-0ACC-A36D-D665C32EC0F6', '105', '01012001', '2', '3', null, null, null, 'FTB0009', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('10', '544100AC-027E-15BA-92DE-E6CD7DE57595', '105', '01014001', '2', '3', null, null, null, 'FTB0010', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('11', '956DC186-94C4-6E5E-9530-7CDC97425A2D', '105', '01015001', '2', '3', null, null, null, 'FTB0011', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('12', '0C9940D6-143F-A17E-EB60-0CFB2735DC53', '105', '01016001', '2', '3', null, null, null, 'FTB0012', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('13', '368B3A82-2D92-4980-5A64-90C882E05497', '105', '01017001', '2', '3', null, null, null, 'FTB0013', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('14', 'FE88CE41-E419-CAB6-5EA0-702C12360A95', '105', '01018001', '2', '3', null, null, null, 'FTB0014', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('15', '5D4F1EF3-A975-312F-20B5-91631489CE43', '105', '01019001', '2', '3', null, null, null, 'FTB0015', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('16', 'A4C756AC-DA58-755E-E88E-714063F2F7B3', '105', '01020001', '2', '3', null, null, null, 'FTB0016', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('17', '3808FFE7-8653-6823-1C43-2B2BCBDDAD09', '105', '01022001', '2', '3', null, null, null, 'FTB0017', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('18', '3F8FC1BE-D97E-DDD6-B2EB-C5655A2A8738', '105', '01024001', '2', '3', null, null, null, 'FTB0018', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('19', 'F51EDE0B-EB2F-D558-062A-16D310FCD1A3', '105', '01025001', '2', '3', null, null, null, 'FTB0019', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('20', '5D26C967-F741-39DD-FDA5-DC1FEAF22001', '105', '01026001', '2', '3', null, null, null, 'FTB0020', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('21', '17502F41-E39F-B1D3-4DF9-1B76321E3C86', '105', '01027001', '2', '3', null, null, null, 'FTB0021', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('22', '9F5C1876-D50B-CF98-31DC-B0059557F01C', '105', '01028001', '2', '3', null, null, null, 'FTB0022', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('23', '3ED25C08-886F-3DA3-9B03-FE8091849BC5', '105', '01029001', '2', '3', null, null, null, 'FTB0023', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('24', '538E53CA-D760-55DB-B884-A64D5D8B0F55', '105', '01030001', '2', '3', null, null, null, 'FTB0024', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('25', '9AAC512B-938E-68C7-A9DB-C844CECC6C73', '105', '01031001', '2', '3', null, null, null, 'FTB0025', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('26', 'C5298732-337F-47A9-6D97-EFC3914F5A8F', '105', '02001001', '2', '3', null, null, null, 'FTB0026', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('27', '2E796145-3F61-89F8-6CF1-341E2B3D1392', '105', '02002001', '2', '3', null, null, null, 'FTB0027', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('28', '7A9598A4-EC33-C89F-438E-F91AAC199232', '105', '02003001', '2', '3', null, null, null, 'FTB0028', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('29', 'B9B9D0B6-AB1A-A10C-6533-0395C7F77D8B', '105', '02004001', '2', '3', null, null, null, 'FTB0029', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('30', '1BC64ABE-867E-4620-89DF-00356FA84208', '105', '02005001', '2', '3', null, null, null, 'FTB0030', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('31', '02F5B206-B712-3F56-AB9A-9BAD9C349165', '105', '02006001', '2', '3', null, null, null, 'FTB0031', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('32', '0191B543-9F20-7E33-7989-91595A571CDF', '105', '02007001', '2', '3', null, null, null, 'FTB0032', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('33', '7CB68042-6DB8-9D50-C401-48F48A9A98AB', '105', '02008001', '2', '3', null, null, null, 'FTB0033', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('34', '917AA478-A690-6650-0510-FB2C0DF6EC8A', '105', '02009001', '2', '3', null, null, null, 'FTB0034', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('35', '68493F32-7146-27BC-16F5-E013A1FD9037', '105', '02010001', '2', '3', null, null, null, 'FTB0035', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('36', '05EE3FF7-994A-4025-4DE2-18E4B16571CB', '105', '02011001', '2', '3', null, null, null, 'FTB0036', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('37', 'A6A3D54B-FF50-3B59-7B41-8AC58437D0D6', '105', '02012001', '2', '3', null, null, null, 'FTB0037', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('38', '6B791859-7467-0F07-E58B-98B93A09BDE4', '105', '02013001', '2', '3', null, null, null, 'FTB0038', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('39', 'D189EE06-F7DD-DFFE-CF3A-0FEABD4D30E6', '105', '02014001', '2', '3', null, null, null, 'FTB0039', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('40', '099CF7F9-8D3E-7A6C-0770-DD1AD85803A6', '105', '02015001', '2', '3', null, null, null, 'FTB0040', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('41', '8C3A1BB2-11C0-F681-E5FD-84E5A4ED9715', '105', '02016001', '2', '3', null, null, null, 'FTB0041', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('42', '614F309A-E409-1652-E4B5-440962629925', '105', '02017001', '2', '3', null, null, null, 'FTB0042', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('43', '9ABA2E43-27FE-0475-B226-A61C7A1CD52D', '105', '02018001', '2', '3', null, null, null, 'FTB0043', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('44', '8C280C06-D123-0E6C-0E07-EE7D623A61F8', '105', '02019001', '2', '3', null, null, null, 'FTB0044', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('45', 'D3E87BFB-F2E5-1231-9AD9-02E77AA9C3F7', '105', '02020001', '2', '3', null, null, null, 'FTB0045', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('46', '3B3C1C68-6067-EB64-3B4D-6629369A2681', '105', '02021001', '2', '3', null, null, null, 'FTB0046', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('47', '99E34EF3-BD4D-4E45-906E-432E40B4E218', '105', '02022001', '2', '3', null, null, null, 'FTB0047', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('48', '078242F6-2CC8-965B-FD98-5B7BBCE347CE', '105', '02023001', '2', '3', null, null, null, 'FTB0048', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('49', 'F46D65E2-4701-293C-3CCE-E932452EE23E', '105', '02024001', '2', '3', null, null, null, 'FTB0049', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('50', '79F1B336-9BCF-4723-4680-7BA2E3C220F2', '105', '02025001', '2', '3', null, null, null, 'FTB0050', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('51', 'DB5C4564-F8F6-E78D-486B-D0C1C7556159', '105', '02026001', '2', '3', null, null, null, 'FTB0051', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('52', '2BF4C3FA-4282-298E-B5EC-8D86851787B9', '105', '02027001', '2', '3', null, null, null, 'FTB0052', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('53', '76649137-49EB-0AB3-FABB-8FB329DFDAAF', '105', '02028001', '2', '3', null, null, null, 'FTB0053', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('54', '557040DC-BBDF-C3B2-C0C6-3CF7D0ACFAC2', '105', '02029001', '2', '3', null, null, null, 'FTB0054', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('55', 'D471B2D8-1E07-F959-30DD-636EA3BA2443', '105', '02030001', '2', '3', null, null, null, 'FTB0055', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('56', '299E79D5-2A87-9C5D-16DE-A7F789CB1F0D', '105', '02031001', '2', '3', null, null, null, 'FTB0056', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('57', '0D3574DA-0E9A-6D99-EDA3-10FA4B01E5EE', '105', '01001002', '2', '3', null, null, null, 'FTB0057', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('58', '4E422D19-822B-4364-DB39-B086F52E60F7', '105', '01002002', '2', '3', null, null, null, 'FTB0058', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('59', 'E04FFBD3-F856-2521-DCAB-8312719DF17F', '105', '01003002', '2', '3', null, null, null, 'FTB0059', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('60', 'A055B445-1E02-0D08-01BC-B69EFDFDC041', '105', '01004002', '2', '3', null, null, null, 'FTB0060', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('61', 'D8B08FC0-267B-14F3-D8D4-9F7AA07B7023', '105', '01005002', '2', '3', null, null, null, 'FTB0061', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('62', '47A3FD2D-7482-2F31-D4B0-8B8A2E7A04D5', '105', '01006002', '2', '3', null, null, null, 'FTB0062', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('63', '800B3B75-E6C2-64C9-8B1C-BD5F049CAF1B', '105', '01007002', '2', '3', null, null, null, 'FTB0063', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('64', '345BEDE6-8818-620F-69E4-64EA4DE98F75', '105', '01008002', '2', '3', null, null, null, 'FTB0064', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('65', '878EC131-3631-DE94-00B0-ADE63945FA0F', '105', '01009002', '2', '3', null, null, null, 'FTB0065', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('66', '10B2B97A-6555-3CE2-63D6-0B0664F6AF7E', '105', '01010002', '2', '3', null, null, null, 'FTB0066', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('67', 'ECF1CAEB-F676-32E8-4FAC-112E37B6EA48', '105', '01011002', '2', '3', null, null, null, 'FTB0067', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('68', 'AF3A5AED-806F-88FC-07BA-84F0BD9B757A', '105', '01012002', '2', '3', null, null, null, 'FTB0068', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('69', '2031BA29-77D5-B474-B5A3-643544378BDE', '105', '01013002', '2', '3', null, null, null, 'FTB0069', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('70', '7E46BB55-5B7F-041A-4C80-872C10C5400E', '105', '01014002', '2', '3', null, null, null, 'FTB0070', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('71', 'D7AAB306-9DB9-A04D-EB62-21C0178A85DF', '105', '01015002', '2', '3', null, null, null, 'FTB0071', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('72', 'C1B6A20C-4697-60EA-D767-C12E92FCFAD3', '105', '01016002', '2', '3', null, null, null, 'FTB0072', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('73', '3A887D05-5B34-EB03-F335-75DF7D182999', '105', '01017002', '2', '3', null, null, null, 'FTB0073', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('74', '3AB0C4A9-4799-00C7-2365-A7111B9C1991', '105', '01018002', '2', '3', null, null, null, 'FTB0074', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('75', '89B2CF85-E9FC-AB18-4751-0E32EE3D1CCD', '105', '01019002', '2', '3', null, null, null, 'FTB0075', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('76', '471B061F-5AF3-DC82-74A6-C658B3F6572E', '105', '01020002', '2', '3', null, null, null, 'FTB0076', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('77', '4223B76B-C10B-4545-5516-4DF31BE7B8F7', '105', '01021002', '2', '3', null, null, null, 'FTB0077', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('78', '27471C38-A0E1-D42A-65E3-17B842E7AEDC', '105', '01022002', '2', '3', null, null, null, 'FTB0078', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('79', '72EAF0CA-FF15-11B5-3DBD-9CC37717CA22', '105', '01023002', '2', '3', null, null, null, 'FTB0079', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('80', '1F9A9E2D-6A76-BB05-D303-A02A85C206BE', '105', '01024002', '2', '3', null, null, null, 'FTB0080', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('81', 'F4105CC0-EE69-7909-9403-1DE604617541', '105', '01025002', '2', '3', null, null, null, 'FTB0081', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('82', 'F3F0FE67-30C6-77AA-064E-FC8C8295A2CB', '105', '01026002', '2', '3', null, null, null, 'FTB0082', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('83', '95221F95-F840-0798-01DF-68555CA5230C', '105', '01027002', '2', '3', null, null, null, 'FTB0083', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('84', 'FD41BA6C-EF1D-A72A-9E3A-5C4E6B78D132', '105', '01028002', '2', '3', null, null, null, 'FTB0084', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('85', '8791BB3A-EA49-FDCD-7A1D-202137F74248', '105', '01029002', '2', '3', null, null, null, 'FTB0085', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('86', 'D3F649A8-5254-D012-2962-8DA2DEB73899', '105', '01030002', '2', '3', null, null, null, 'FTB0086', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('87', '9156BEFF-4BE5-B585-94E1-BC87CB801D75', '105', '01031002', '2', '3', null, null, null, 'FTB0087', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('88', '3D6439FA-9169-BBA2-90CD-DBD07C4A24C9', '105', '02001002', '2', '3', null, null, null, 'FTB0088', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('89', '58C8C31E-2A55-1EC4-ED97-EDF3E32D3DEC', '105', '02002002', '2', '3', null, null, null, 'FTB0089', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('90', '2BF6A373-3DAD-DC8C-9D41-C6A55F7E95A6', '105', '02003002', '2', '3', null, null, null, 'FTB0090', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('91', '9871E987-7D72-C6C5-67BE-0E4FE0994361', '105', '02004002', '2', '3', null, null, null, 'FTB0091', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('92', 'E162EE2F-2F5B-507A-D599-1EFB47EA58C4', '105', '02005002', '2', '3', null, null, null, 'FTB0092', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('93', '06BDF3AE-A380-F40C-FBE8-8D26E92ED7B7', '105', '02006002', '2', '3', null, null, null, 'FTB0093', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('94', '9878A002-A117-00B8-51A2-2138F2E0D8D8', '105', '02007002', '2', '3', null, null, null, 'FTB0094', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('95', 'E123C484-BFA1-2DD9-B150-CA05B4305DEE', '105', '02008002', '2', '3', null, null, null, 'FTB0095', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('96', 'DE890F77-98CE-66BA-7A73-B8E1FDE603A8', '105', '02009002', '2', '3', null, null, null, 'FTB0096', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('97', '5A42D09B-4467-3E98-15D2-0112C4719423', '105', '02010002', '2', '3', null, null, null, 'FTB0097', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('98', '393F9EFB-6E8F-BB40-E602-DEF12AF78585', '105', '02011002', '2', '3', null, null, null, 'FTB0098', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('99', '2A36DB93-8336-ABCC-E853-C220040D4838', '105', '02012002', '2', '3', null, null, null, 'FTB0099', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('100', '7239039A-F1E2-9A1A-5FB8-D7507425D56D', '105', '02013002', '2', '3', null, null, null, 'FTB0100', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('101', '6F2D523E-0304-ADF0-D954-982E2D9F85EA', '105', '02014002', '2', '3', null, null, null, 'FTB0101', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('102', 'F9606C2B-6C39-33BE-E645-D6C4264D9B30', '105', '02015002', '2', '3', null, null, null, 'FTB0102', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('103', '0B3C3EC0-2940-5D2E-AAAD-F0289AF1B4CB', '105', '02016002', '2', '3', null, null, null, 'FTB0103', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('104', 'CB8A2AFE-B85E-48E7-C8A3-7DF4788EFFE0', '105', '02017002', '2', '3', null, null, null, 'FTB0104', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('105', '32C0FA76-4B0D-FC6B-4613-FBDAAFB599AD', '105', '02018002', '2', '3', null, null, null, 'FTB0105', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('106', '1C523E83-4E7C-9847-2221-06B848F318D7', '105', '02019002', '2', '3', null, null, null, 'FTB0106', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('107', '81DAD80B-4301-625A-F6B5-B56672E39ECF', '105', '02020002', '2', '3', null, null, null, 'FTB0107', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('108', '6AD14812-AB72-DF3F-1973-D631911EF803', '105', '02021002', '2', '3', null, null, null, 'FTB0108', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('111', '76303547-D595-4110-DD70-54D8C1533623', '105', '02022002', '2', '3', null, null, null, 'FTB0109', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('112', '0DF91757-B6D1-A396-C9B9-0507678D203B', '105', '02023002', '2', '3', null, null, null, 'FTB0110', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('113', '183473B8-A72E-A702-F39C-1C21CA294D50', '105', '02025002', '2', '3', null, null, null, 'FTB0111', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('114', '754B94B8-7CA6-75A2-386A-A4E8D6F7ACE9', '105', '02025002', '2', '3', null, null, null, 'FTB0112', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('115', '5865E580-1F15-0040-1075-D5636AED28F9', '105', '02026002', '2', '3', null, null, null, 'FTB0113', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('116', '332DA0EF-85D9-3CB6-D50E-3456639E63BC', '105', '02027002', '2', '3', null, null, null, 'FTB0114', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('117', 'E01C9819-BF28-E540-1D0B-922BC01EB68C', '105', '02028002', '2', '3', null, null, null, 'FTB0115', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('118', 'AE44D859-F537-A3A7-290C-83F4E0E0E593', '105', '02029002', '2', '3', null, null, null, 'FTB0116', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('119', '7BA1CCAC-1814-2A90-1EF6-C7395DE1CF25', '105', '02030002', '2', '3', null, null, null, 'FTB0117', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('120', '718700E2-44CD-294D-1670-6749CE98FC8A', '105', '02031002', '2', '3', null, null, null, 'FTB0118', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('121', 'A70E7F88-1AE4-7DD1-FD45-96C22B56C04C', '105', '01001003', '2', '3', null, null, null, 'FTB0119', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('122', 'BF531B28-E7E9-0507-5DF7-53B47A24FAE4', '105', '01002003', '2', '3', null, null, null, 'FTB0120', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('123', '3D6743E4-D7EB-673F-22EE-987BA2817263', '105', '01003003', '2', '3', null, null, null, 'FTB0121', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('124', '4BD1CCBA-2EF4-4D01-8B59-0A60DAD3B2DE', '105', '01004003', '2', '3', null, null, null, 'FTB0122', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('125', 'EE131C77-D096-0D75-F03D-B523CBF18094', '105', '01005003', '2', '3', null, null, null, 'FTB0123', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('126', '0FC8E1B4-D7E8-37FD-FAAD-E051AD62589D', '105', '01006003', '2', '3', null, null, null, 'FTB0124', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('127', '506EC53B-CF84-FAC0-AA25-3915DC5950C7', '105', '01007003', '2', '3', null, null, null, 'FTB0125', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('128', 'B049652F-1483-2B1B-F98E-F77A5275BB8B', '105', '01008003', '2', '3', null, null, null, 'FTB0126', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('129', 'F4FB31B4-CBCF-7D9C-ACEB-F72C9C9D7194', '105', '01009003', '2', '3', null, null, null, 'FTB0127', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('130', '6A14DAE4-1050-8FCF-24E2-F5B893C8A21B', '105', '01010003', '2', '3', null, null, null, 'FTB0128', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('131', 'F7F1C85B-CCEC-41DA-1626-068FB4C23FBF', '105', '01011003', '2', '3', null, null, null, 'FTB0129', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('132', 'DD3F46B5-391F-B404-5A03-058D01A26ED6', '105', '01012003', '2', '3', null, null, null, 'FTB0130', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('133', '2454632F-5DE0-3CC5-23A0-31066B8614B7', '105', '01013003', '2', '3', null, null, null, 'FTB0131', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('134', '8840F9AF-C8CB-D4A0-8445-74A56D9E90AD', '105', '01014003', '2', '3', null, null, null, 'FTB0132', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('135', 'F143846A-C442-AD74-8632-EB88100E1801', '105', '01015003', '2', '3', null, null, null, 'FTB0133', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('136', '9D8265AF-EDA0-6407-544D-FAC23A062C33', '105', '01016003', '2', '3', null, null, null, 'FTB0134', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('137', 'B8D3537A-248F-AD53-CCD4-0F4AC9C1A73F', '105', '01017003', '2', '3', null, null, null, 'FTB0135', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('138', '58972D80-F178-8C6A-ECF3-BF5496AD64C8', '105', '01018003', '2', '3', null, null, null, 'FTB0136', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('139', '3423FEBB-62D6-DB3C-8FA2-8152D1B41AB6', '105', '01019003', '2', '3', null, null, null, 'FTB0137', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('140', 'AE1986F0-1633-CB80-CA05-262628245F9A', '105', '01020003', '2', '3', null, null, null, 'FTB0138', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('141', '976964A7-A7E8-B7C3-A171-9AAFCEF43756', '105', '01021003', '2', '3', null, null, null, 'FTB0139', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('142', 'BCF4CA10-F233-8C2F-4423-40DFBB958F2B', '105', '01022003', '2', '3', null, null, null, 'FTB0140', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('143', '0D37B3DA-0F36-3E74-F3A1-D7404D862065', '105', '01023003', '2', '3', null, null, null, 'FTB0141', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('144', '5B7E8387-EFF9-B0C4-7150-64CF3F008D2E', '105', '01024003', '2', '3', null, null, null, 'FTB0142', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('145', '513BC21C-DBFA-E838-83CC-7D33813ABA48', '105', '01025003', '2', '3', null, null, null, 'FTB0143', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('146', '6B15C89E-29D3-E3DA-F37F-411B0CBB728C', '105', '01026003', '2', '3', null, null, null, 'FTB0144', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('147', '8EC8C06A-7D8B-FC33-F5FE-681084FCB532', '105', '01027003', '2', '3', null, null, null, 'FTB0145', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('148', 'EEB1F1D6-5185-B77E-8D06-65B24B54822D', '105', '01028003', '2', '3', null, null, null, 'FTB0146', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('149', 'ADA508BC-C96E-5582-C461-4CC97C9E6304', '105', '01029003', '2', '3', null, null, null, 'FTB0147', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('150', '7F90407C-DB2D-9C98-7D4E-13E9966BFA39', '105', '01030003', '2', '3', null, null, null, 'FTB0148', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('151', '34AF9185-0404-2E8A-038D-6B3571FB978F', '105', '01031003', '2', '3', null, null, null, 'FTB0149', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('152', 'D881334D-0F62-F132-0232-9CA19E8C3D71', '105', '02001003', '2', '3', null, null, null, 'FTB0150', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('153', 'CA77AAB7-B741-4895-23A8-68A1792BEF73', '105', '02002003', '2', '3', null, null, null, 'FTB0151', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('154', 'A8EAB456-9260-7DB5-9499-9104DE15DEDA', '105', '02003003', '2', '3', null, null, null, 'FTB0152', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('155', 'F5A6E5B6-4479-9F9E-D01C-AF1FC1C51362', '105', '02004003', '2', '3', null, null, null, 'FTB0153', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('156', 'BFE34B0E-972D-2F43-CA30-03E1EB33818C', '105', '02005003', '2', '3', null, null, null, 'FTB0154', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('157', '5645C77E-0D4D-5A0C-6603-E3AA569D65BA', '105', '02006003', '2', '3', null, null, null, 'FTB0155', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('158', '5A8BA2AB-1317-C42B-B1F0-D323E08010A6', '105', '02007003', '2', '3', null, null, null, 'FTB0156', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('159', 'C924072E-18C1-F416-37A8-5D790425E6F7', '105', '02008003', '2', '3', null, null, null, 'FTB0157', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('160', '7D3B1C7B-DAB3-A759-EA4A-35640A089827', '105', '02009003', '2', '3', null, null, null, 'FTB0158', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('161', 'CC093E4B-64EE-5523-3790-B93122124F38', '105', '02010003', '2', '3', null, null, null, 'FTB0159', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('162', 'A3399E33-AE87-25D2-ECB5-E7682F4F753B', '105', '02011003', '2', '3', null, null, null, 'FTB0160', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('163', '5198A712-B392-EB14-74C0-D16BE7B5A1A1', '105', '02012003', '2', '3', null, null, null, 'FTB0161', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('164', '0769E53B-78AF-B755-92EB-D68806C243B1', '105', '02013003', '2', '3', null, null, null, 'FTB0162', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('165', '3FF21ADD-F9B6-D88D-205C-ACC1DC01796F', '105', '02014003', '2', '3', null, null, null, 'FTB0163', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('166', '43F5A707-8405-9F4A-2F99-263774B06669', '105', '02015003', '2', '3', null, null, null, 'FTB0164', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('167', '35FA98F8-766D-705A-9613-FB1410989F17', '105', '02016003', '2', '3', null, null, null, 'FTB0165', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('168', '44F129A4-EB38-3A79-B2AF-21F08FBEECBA', '105', '02017003', '2', '3', null, null, null, 'FTB0166', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('169', 'D27A81E4-44B6-BCE5-70AD-BFAF4D105523', '105', '02018003', '2', '3', null, null, null, 'FTB0167', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('170', 'F01159D3-3F05-2A74-8FCA-D8FB62871F00', '105', '02019003', '2', '3', null, null, null, 'FTB0168', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('171', '9D0143B5-B5CF-175C-C432-55FE93EF157F', '105', '02020003', '2', '3', null, null, null, 'FTB0169', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('172', 'C0E45465-2527-BD1F-169B-679E09A0E060', '105', '02021003', '2', '3', null, null, null, 'FTB0170', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('173', '64FCF2E1-C8EC-395D-E599-3E1927E7BDEE', '105', '02022003', '2', '3', null, null, null, 'FTB0171', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('174', '57FF6FAD-FA21-32D0-D64B-9A72C8E48875', '105', '02023003', '2', '3', null, null, null, 'FTB0172', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('175', 'BCEB72FE-1FC5-730D-18E1-3C43FBE68553', '105', '02024003', '2', '3', null, null, null, 'FTB0173', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('176', 'EB42C535-0B58-E557-74FB-E2A4A560E749', '105', '02025003', '2', '3', null, null, null, 'FTB0174', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('177', '225EF134-D9C1-71F9-6EB2-BFCA4483A2F6', '105', '02026003', '2', '3', null, null, null, 'FTB0175', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('178', '17274AC8-D67A-A6E2-CAA5-93F8E98F3976', '105', '02027003', '2', '3', null, null, null, 'FTB0176', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('179', 'E19517E8-1C0D-14A7-5F8F-55F4602DB22A', '105', '02028003', '2', '3', null, null, null, 'FTB0177', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('180', '3047484B-E2D7-700D-FAEF-01BBC583F4DC', '105', '02029003', '2', '3', null, null, null, 'FTB0178', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('181', 'DB0C31E7-3334-0177-6A26-E34840E30949', '105', '02030003', '2', '3', null, null, null, 'FTB0179', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('182', '0D4CE7B6-0E6A-C95E-80A0-8B241BCDF665', '105', '02031003', '2', '3', null, null, null, 'FTB0180', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('183', '6021E637-DA0A-6E6F-8A0D-82DE649A2079', '105', '01001004', '2', '3', null, null, null, 'FTB0181', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('184', '7D85435C-3FEC-8C24-75F1-EF37DEB43FFB', '105', '01002004', '2', '3', null, null, null, 'FTB0182', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('185', '8170B873-686F-A897-3F32-8373FF9CC7CE', '105', '01003004', '2', '3', null, null, null, 'FTB0183', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('186', '001ACECE-BABA-B2EE-BE0A-B35EE7ACDEC0', '105', '01004004', '2', '3', null, null, null, 'FTB0184', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('187', 'FD2EB64F-8C30-D2E4-47F7-E0CF2851E74D', '105', '01005004', '2', '3', null, null, null, 'FTB0185', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('188', '8468CFA1-F006-4C1E-F65D-1886D3019F07', '105', '01006004', '2', '3', null, null, null, 'FTB0186', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('189', '0117EAE2-5DDD-8B19-10C7-CB820055FEBA', '105', '01007004', '2', '3', null, null, null, 'FTB0187', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('190', 'C2D619B9-B97F-B936-A8EB-780A8940FB05', '105', '01008004', '2', '3', null, null, null, 'FTB0188', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('191', '9B5F204D-FAE2-A176-D9DE-CA16BEFB4A0C', '105', '01009004', '2', '3', null, null, null, 'FTB0189', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('192', '8E709D32-D6C6-EE9F-0C04-AD9F87D6B282', '105', '01010004', '2', '3', null, null, null, 'FTB0190', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('193', '41CF26DC-4FBF-1208-ECD7-5618D05261FB', '105', '01011004', '2', '3', null, null, null, 'FTB0191', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('194', '3BD19F65-0246-41F8-4634-429E5FABCFBB', '105', '01012004', '2', '3', null, null, null, 'FTB0192', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('195', '798D9C41-7F74-A718-1D25-F7CC264AF534', '105', '01013004', '2', '3', null, null, null, 'FTB0193', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('196', '1C2BAEE7-76C9-C1C8-92B0-61824A01DB33', '105', '01014004', '2', '3', null, null, null, 'FTB0194', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('197', '3285A6BE-D782-4430-4E49-B39857020122', '105', '01015004', '2', '3', null, null, null, 'FTB0195', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('198', '17615CDB-8852-72B6-D6EB-718EE58D8157', '105', '01016004', '2', '3', null, null, null, 'FTB0196', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('199', 'E5C24FEE-BDB1-0510-6EEF-DAA56ED5ADAD', '105', '01017004', '2', '3', null, null, null, 'FTB0197', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('200', '3DC55824-517F-7001-1F5D-FC01B318246D', '105', '01018004', '2', '3', null, null, null, 'FTB0198', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('201', '14B29430-3FB9-5EA2-E83B-B6371F053297', '105', '01019004', '2', '3', null, null, null, 'FTB0199', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('202', '108DABFF-5767-50A1-591C-3AB34BA353D5', '105', '01020004', '2', '3', null, null, null, 'FTB0200', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('203', '155CD5CC-1F83-78EC-3117-3623BB45546E', '105', '01021004', '2', '3', null, null, null, 'FTB0201', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('204', '2904D7B5-6C11-31B0-E5E1-54ED44C2DDE4', '105', '01022004', '2', '3', null, null, null, 'FTB0202', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('205', '6E331DF0-8C30-9A70-CDF6-854247FCA956', '105', '01023004', '2', '3', null, null, null, 'FTB0203', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('206', '59FD4075-CDDC-00AC-276D-242E68C1D3E7', '105', '01024004', '2', '3', null, null, null, 'FTB0204', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('207', 'FBE15C98-68A5-E13B-8485-9A7F37A9D03F', '105', '01025004', '2', '3', null, null, null, 'FTB0205', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('208', '5A40E141-DAAE-7DF1-CB4D-BCC2BA3126AA', '105', '01026004', '2', '3', null, null, null, 'FTB0206', null, '', null, null, null, null);
INSERT INTO `task_info` VALUES ('209', '98646961-F5F6-017E-21EB-613E3EB82FBD-199572', '105', '01027004', '2', '3', null, null, null, 'FTB0207', null, '0', null, '3240216411', '2020-04-05 16:21:21', '2020-04-05 16:22:36');
INSERT INTO `task_info` VALUES ('210', 'DBCE1201-F75D-CBBD-FBAD-AEB6183890F5-058497', '105', '01028004', '2', '3', null, null, null, 'FTB0208', null, '0', null, '3240216411', '2020-04-05 16:29:35', '2020-04-05 16:40:34');
INSERT INTO `task_info` VALUES ('211', 'CC54C099-5764-BA15-301C-785FA3D37D60-694466', '105', '01029004', '2', '3', null, null, null, 'FTB0209', null, '0', null, '3240216411', '2020-04-07 15:13:37', '2020-04-07 15:16:01');
INSERT INTO `task_info` VALUES ('212', '4AC36F6D-25DB-5478-CC63-1144043389B1-488798', '105', '01030004', '2', '3', null, null, null, 'FTB0210', null, '0', null, '3240216411', '2020-04-07 15:16:13', '2020-04-07 15:18:35');
INSERT INTO `task_info` VALUES ('213', '42DE65EA-3749-206C-F326-338A7F6D7F0E-772495', '105', '01031004', '2', '3', null, null, null, 'FTB0211', null, '0', null, '3240216411', '2020-04-07 15:17:59', '2020-04-07 15:20:27');
INSERT INTO `task_info` VALUES ('214', '38157654-BB26-B411-860C-E691CE5017AA-469766', '105', '02001004', '2', '3', null, null, null, 'FTB0212', null, '0', null, '3240216411', '2020-04-07 15:20:39', '2020-04-07 15:23:09');
INSERT INTO `task_info` VALUES ('215', '988C06AC-9AFD-0B39-2848-69AACCF3A5FD-755653', '105', '02002004', '2', '3', null, null, null, 'FTB0213', null, '0', null, '3240216411', '2020-04-07 15:24:18', '2020-04-07 15:26:51');
INSERT INTO `task_info` VALUES ('216', '745E97AB-B3CF-F2CA-FF96-3DF9F6CCB730-365305', '105', '02003004', '2', '3', null, null, null, 'FTB0214', null, '0', null, '3240216411', '2020-04-07 15:26:11', '2020-04-07 15:28:42');
INSERT INTO `task_info` VALUES ('217', 'A319C854-9B19-664C-0E91-9DEEFF6CFC7D-478719', '105', '02004004', '2', '3', null, null, null, 'FTB0215', null, '0', null, '3240216411', '2020-04-07 15:30:59', '2020-04-07 15:33:30');
INSERT INTO `task_info` VALUES ('218', '87E60B94-775F-AE6F-7838-813FCAA69F88-065444', '105', '02005004', '2', '3', null, null, null, 'FTB0216', null, '0', null, '3240216411', '2020-04-07 15:34:40', '2020-04-07 15:37:11');
INSERT INTO `task_info` VALUES ('219', '82289807-54F5-C8FC-B3BA-552BE62BA73E-386768', '105', '02006004', '2', '3', null, null, null, 'FTB0217', null, '0', null, '3240216411', '2020-04-07 15:37:00', '2020-04-07 15:39:48');
INSERT INTO `task_info` VALUES ('220', 'C89DC2B8-8E46-85A0-5A99-FEF56F645F6C-090098', '105', '02007004', '2', '3', null, null, null, 'FTB0218', null, '0', null, '3240216411', '2020-04-07 15:42:10', '2020-04-07 15:44:39');
INSERT INTO `task_info` VALUES ('221', '155EEBDB-BFF4-493C-323B-33B0B686B29C-995465', '105', '02008004', '2', '3', null, null, null, 'FTB0219', null, '0', null, '3240216411', '2020-04-07 15:53:03', '2020-04-07 15:55:28');
INSERT INTO `task_info` VALUES ('222', 'E0EA5286-B805-5B5C-E2CB-A76A5DA8905E-129014', '105', '02009004', '2', '3', null, null, null, 'FTB0220', null, '0', null, '3240216411', '2020-04-07 15:55:38', '2020-04-07 15:58:03');
INSERT INTO `task_info` VALUES ('223', '1935EF22-A8BA-D6F3-CCE8-F7D94D2361F5-551369', '105', '02010004', '2', '3', null, null, null, 'FTB0221', null, '0', null, '3240216411', '2020-04-07 15:57:25', '2020-04-07 15:59:49');
INSERT INTO `task_info` VALUES ('224', '86ABD386-B789-92D5-0C2D-A4DE763C6D16-700424', '105', '02011004', '2', '3', null, null, null, 'FTB0222', null, '0', null, '3240216411', '2020-04-07 15:59:08', '2020-04-07 16:01:33');
INSERT INTO `task_info` VALUES ('225', '393C52A7-CFA6-E5AE-5DE0-5A6A684F8ACE-027979', '01001001', null, '1', '1', null, null, null, 'FTB0001', null, '0', null, '3240216411', '2020-04-07 16:01:15', null);
INSERT INTO `task_info` VALUES ('226', '25894F8D-16EC-4415-EEDC-AAB0604AE8B0-789594', '105', '02012004', '2', '3', null, null, null, 'FTB0223', null, '0', null, '3240216411', '2020-04-07 16:07:24', '2020-04-07 16:09:55');
INSERT INTO `task_info` VALUES ('227', '6E4D6476-93B3-B9E2-D698-39B744532679-975390', '105', '02013004', '2', '3', null, null, null, 'FTB0224', null, '0', null, '3240216411', '2020-04-07 16:11:04', '2020-04-07 16:13:27');
INSERT INTO `task_info` VALUES ('228', '556841FB-5BE9-62E3-6753-C00322499D7A-167603', '105', '02014004', '2', '3', null, null, null, 'FTB0225', null, '0', null, '3240216411', '2020-04-07 16:12:49', '2020-04-07 16:15:12');
INSERT INTO `task_info` VALUES ('229', '39C447CF-EF4B-E0CB-90E4-4AAAFDCE53C2-664330', '105', '02015004', '2', '3', null, null, null, 'FTB0226', null, '0', null, '3240216411', '2020-04-07 16:14:16', '2020-04-07 16:19:19');
INSERT INTO `task_info` VALUES ('230', '8ADF0CB5-3587-2C6A-15E3-8FFF8ACFFB21-161193', '105', '02016004', '2', '3', null, null, null, 'FTB0227', null, '0', null, '3240216411', '2020-04-07 16:18:52', '2020-04-07 16:21:19');
INSERT INTO `task_info` VALUES ('231', '39161758-6078-C986-6C1E-42E2A5C2CEA3-304067', '105', '02017004', '2', '3', null, null, null, 'FTB0228', null, '0', null, '3240216411', '2020-04-07 16:20:08', '2020-04-07 16:22:34');
INSERT INTO `task_info` VALUES ('232', '32A63B80-FF34-2EE5-9703-5C40362C6560-901540', '105', '02018004', '2', '3', null, null, null, 'FTB0229', null, '0', null, '3240216411', '2020-04-07 16:31:51', '2020-04-07 16:34:11');
INSERT INTO `task_info` VALUES ('233', '0CF55C5E-0265-3263-F41D-218EBFC63471-392662', '105', '02019004', '2', '3', null, null, null, 'FTB0230', null, '0', null, '3240216411', '2020-04-07 16:32:57', '2020-04-07 16:36:21');
INSERT INTO `task_info` VALUES ('234', '5EEC0917-A0E8-FE58-E7BE-58D886F39B3E-966424', '105', '02020004', '2', '3', null, null, null, 'FTB0231', null, '0', null, '3240216411', '2020-04-07 16:33:56', '2020-04-07 16:35:17');
INSERT INTO `task_info` VALUES ('235', '7AF09787-B4EB-2300-5288-22ADE345EED7-298564', '105', '02021004', '2', '3', null, null, null, 'FTB0232', null, '0', null, '3240216411', '2020-04-07 16:35:24', '2020-04-07 16:37:41');
INSERT INTO `task_info` VALUES ('236', '7947CBC9-AADC-848F-7612-BBBF903943A9-547625', '105', '02022004', '2', '3', null, null, null, 'FTB0233', null, '0', null, '3240216411', '2020-04-07 16:36:33', '2020-04-07 16:38:53');
INSERT INTO `task_info` VALUES ('237', '0A165F6B-2BF0-2013-FE72-96DB2598E272-474352', '105', '02023004', '2', '3', null, null, null, 'FTB0234', null, '0', null, '3240216411', '2020-04-07 16:39:42', '2020-04-07 16:43:14');
INSERT INTO `task_info` VALUES ('238', '096294FF-0F60-35F5-2F03-6C4800E35E8D-386594', '105', '02024004', '2', '3', null, null, null, 'FTB0235', null, '0', null, '3240216411', '2020-04-07 16:40:57', '2020-04-07 16:42:02');
INSERT INTO `task_info` VALUES ('239', 'F8CD8856-43EF-33F7-10BA-3083B0D4EAD3-248697', '105', '02025004', '2', '3', null, null, null, 'FTB0236', null, '0', null, '3240216411', '2020-04-07 16:42:09', '2020-04-07 16:44:29');
INSERT INTO `task_info` VALUES ('240', '50B3E8F2-8673-ABC3-2E50-36A5F2CE9854-488626', '105', '02026004', '2', '3', null, null, null, 'FTB0237', null, '0', null, '3240216411', '2020-04-07 16:44:28', '2020-04-07 16:46:45');
INSERT INTO `task_info` VALUES ('241', '52A5FB8B-2874-9E49-B3CD-BE53A6E85743-085644', '105', '02027004', '2', '3', null, null, null, 'FTB0238', null, '0', null, '3240216411', '2020-04-07 16:46:28', '2020-04-07 16:48:51');
INSERT INTO `task_info` VALUES ('242', '0152C42C-77D6-4936-EB4B-53216374D214-214923', '105', '02028004', '2', '3', null, null, null, 'FTB0239', null, '0', null, '3240216411', '2020-04-08 13:17:02', '2020-04-08 13:20:22');
INSERT INTO `task_info` VALUES ('243', '7BDEBCDC-370A-A2EE-F00B-0AD4C3E27F21-169376', '105', '02029004', '2', '3', null, null, null, 'FTB0240', null, '0', null, '3240216411', '2020-04-08 13:27:27', '2020-04-08 13:29:44');
INSERT INTO `task_info` VALUES ('244', '00FD2DBB-88CF-1A67-38EA-89E959EB7B13-620143', '105', '02030004', '2', '3', null, null, null, 'FTB0241', null, '0', null, '3240216411', '2020-04-08 13:31:21', '2020-04-08 13:33:43');
INSERT INTO `task_info` VALUES ('245', '71135EE5-DFD7-2407-9B17-0F0136D0F8A1-214284', '105', '02031004', '2', '3', null, null, null, 'FTB0242', null, '0', null, '3240216411', '2020-04-08 13:32:28', '2020-04-08 13:34:51');
INSERT INTO `task_info` VALUES ('246', '8481B518-A5AE-9709-B6CB-39AE2990CB28-789916', '105', '01001005', '2', '3', null, null, null, 'FTB0243', null, '0', null, '3240216411', '2020-04-08 13:33:57', '2020-04-08 13:36:23');
INSERT INTO `task_info` VALUES ('247', '3BCBBE1E-EBAF-FF00-2D3E-D95D0D53855D-473724', '105', '01002005', '2', '3', null, null, null, 'FTB0244', null, '0', null, '3240216411', '2020-04-08 13:37:39', '2020-04-08 13:40:08');
INSERT INTO `task_info` VALUES ('248', 'D2106073-8E56-7D4C-D8F3-814ADAEFC9CE-123127', '105', '01003005', '2', '3', null, null, null, 'FTB0245', null, '0', null, '3240216411', '2020-04-08 13:39:09', '2020-04-08 13:45:14');
INSERT INTO `task_info` VALUES ('249', 'FF98AD3B-233F-446F-79D4-F4C6DB72DD49-502063', '105', '01004005', '2', '3', null, null, null, 'FTB0246', null, '0', null, '3240216411', '2020-04-08 13:49:29', '2020-04-08 13:53:22');
INSERT INTO `task_info` VALUES ('250', '37788060-2D67-CF57-B77C-8F9D8C2CF94E-484800', '105', '01005005', '2', '3', null, null, null, 'FTB0247', null, '0', null, '3240216411', '2020-04-08 13:54:16', '2020-04-08 13:57:54');
INSERT INTO `task_info` VALUES ('251', '4C59EEB8-2B53-720E-5745-1699456BC38E-458041', '105', '01006005', '2', '3', null, null, null, 'FTB0248', null, '0', null, '3240216411', '2020-04-08 13:55:16', '2020-04-08 13:56:42');
INSERT INTO `task_info` VALUES ('252', '3053D429-9BF3-890E-EE12-B3262882FD95-376130', '105', '01007005', '2', '3', null, null, null, 'FTB0249', null, '0', null, '3240216411', '2020-04-08 15:08:55', '2020-04-08 15:11:20');
INSERT INTO `task_info` VALUES ('253', '219347CB-C8E6-5864-5A18-A94541EED861-139676', '105', '01008005', '2', '3', null, null, null, 'FTB0250', null, '0', null, '3240216411', '2020-04-08 15:10:17', '2020-04-08 15:12:40');
INSERT INTO `task_info` VALUES ('254', '9CECA204-54B4-692B-A59A-F58544DAA1E4-452091', '105', '01009005', '2', '3', null, null, null, 'FTB0251', null, '0', null, '3240216411', '2020-04-08 15:11:18', '2020-04-08 15:14:56');
INSERT INTO `task_info` VALUES ('255', 'ECC50027-F721-C07A-0EEB-DA83F068AE2C-702223', '105', '01010005', '2', '3', null, null, null, 'FTB0252', null, '0', null, '3240216411', '2020-04-08 15:12:31', '2020-04-08 15:13:48');
INSERT INTO `task_info` VALUES ('256', 'CB18054A-80DA-3C1F-8F1F-619ADF21DF72-974430', '105', '01011005', '2', '3', null, null, null, 'FTB0253', null, '0', null, '3240216411', '2020-04-08 15:15:00', '2020-04-08 15:17:20');
INSERT INTO `task_info` VALUES ('257', '55A35611-9774-40CA-94E3-3469C6C7A258-628981', '105', '01012005', '2', '3', null, null, null, 'FTB0254', null, '0', null, '3240216411', '2020-04-08 15:16:27', '2020-04-08 15:18:47');
INSERT INTO `task_info` VALUES ('258', '5CEE919B-5F1D-5A90-ABEA-012C94CD60C9-945588', '105', '01013005', '2', '3', null, null, null, 'FTB0255', null, '0', null, '3240216411', '2020-04-08 15:19:13', '2020-04-08 15:21:31');
INSERT INTO `task_info` VALUES ('259', '464D5AC4-250D-1931-DB7C-B714E4471CAF-183003', '105', '01014005', '2', '3', null, null, null, 'FTB0256', null, '0', null, '3240216411', '2020-04-08 15:20:35', '2020-04-08 15:22:52');
INSERT INTO `task_info` VALUES ('260', 'E5C0231A-6EFD-9A0B-77B5-5715D8728D7D-179138', '105', '01015005', '2', '3', null, null, null, 'FTB0257', null, '0', null, '3240216411', '2020-04-08 15:21:58', '2020-04-08 15:24:17');
INSERT INTO `task_info` VALUES ('261', '82C11064-7317-8D6C-49C2-18005E6E2290-512740', '105', '01016005', '2', '3', null, null, null, 'FTB0258', null, '0', null, '3240216411', '2020-04-08 15:23:08', '2020-04-08 15:25:31');
INSERT INTO `task_info` VALUES ('262', 'C84DCC6E-876B-5E16-9DA3-9F0C1B939A63-243399', '105', '01017005', '2', '3', null, null, null, 'FTB0259', null, '0', null, '3240216411', '2020-04-08 15:24:06', '2020-04-08 15:26:37');
INSERT INTO `task_info` VALUES ('263', 'FB2FFDB6-314B-3D87-DF20-AA5970EFA90B-912051', '105', '01018005', '2', '3', null, null, null, 'FTB0260', null, '0', null, '3240216411', '2020-04-08 15:25:11', '2020-04-08 15:27:43');
INSERT INTO `task_info` VALUES ('264', '0EC37CE6-1ACC-1A03-F763-626FA4FC6969-980107', '105', '01019005', '2', '3', null, null, null, 'FTB0261', null, '0', null, '3240216411', '2020-04-08 15:35:18', '2020-04-08 15:37:35');
INSERT INTO `task_info` VALUES ('265', 'F67C808B-1B9A-C668-68CA-7E0ACCE1220B-115559', '105', '01020005', '2', '3', null, null, null, 'FTB0262', null, '0', null, '3240216411', '2020-04-08 15:36:54', '2020-04-08 15:39:12');
INSERT INTO `task_info` VALUES ('266', 'A4E03217-1A99-E7E8-2615-C4D89855C6EA-366392', '105', '01021005', '2', '3', null, null, null, 'FTB0263', null, '0', null, '3240216411', '2020-04-08 15:38:39', '2020-04-08 15:40:57');
INSERT INTO `task_info` VALUES ('267', '60168721-3611-989D-308A-BF8CEB8CC44F-850325', '105', '01022005', '2', '3', null, null, null, 'FTB0264', null, '0', null, '3240216411', '2020-04-08 15:39:38', '2020-04-08 15:42:01');
INSERT INTO `task_info` VALUES ('268', '1ED514CF-5077-D4D5-BC4C-DCC09C1CC5F8-765719', '105', '01023005', '2', '3', null, null, null, 'FTB0265', null, '0', null, '3240216411', '2020-04-08 15:41:04', '2020-04-08 15:43:21');
INSERT INTO `task_info` VALUES ('269', 'D0B7B62D-8F3D-A71A-1C87-3AC0352A4287-245551', '105', '01024005', '2', '3', null, null, null, 'FTB0266', null, '0', null, '3240216411', '2020-04-08 15:42:19', '2020-04-08 15:44:37');
INSERT INTO `task_info` VALUES ('270', '4234E789-71A0-3D1C-4765-0A67D8EFC02A-031627', '105', '01025005', '2', '3', null, null, null, 'FTB0267', null, '0', null, '3240216411', '2020-04-08 15:45:42', '2020-04-08 15:47:58');
INSERT INTO `task_info` VALUES ('271', '3D5BFF0C-98E2-E1A8-BF7E-B47DD6E075C5-271260', '105', '01026005', '2', '3', null, null, null, 'FTB0268', null, '0', null, '3240216411', '2020-04-08 15:46:47', '2020-04-08 15:49:03');
INSERT INTO `task_info` VALUES ('272', '4F4F0BB5-F1C8-9E17-146E-2A0598B17CA0-951863', '105', '01027005', '2', '3', null, null, null, 'FTB0269', null, '0', null, '3240216411', '2020-04-08 15:48:00', '2020-04-08 15:50:18');
INSERT INTO `task_info` VALUES ('273', '3DC6C5B8-4CD7-6F98-A57A-0A37EEDE37BA-643242', '105', '01028005', '2', '3', null, null, null, 'FTB0270', null, '0', null, '3240216411', '2020-04-08 15:49:07', '2020-04-08 15:54:30');
INSERT INTO `task_info` VALUES ('274', 'D837BBE0-DE6F-F101-AF7C-0CEB8B881AF9-516382', '105', '01029005', '2', '3', null, null, null, 'FTB0271', null, '0', null, '3240216411', '2020-04-08 15:50:17', '2020-04-08 15:51:27');
INSERT INTO `task_info` VALUES ('275', '0FA6DA9E-85E5-1BF0-808F-BA2F60E8D6B5-905369', '105', '01030005', '2', '3', null, null, null, 'FTB0272', null, '0', null, '3240216411', '2020-04-08 15:51:15', '2020-04-08 15:52:39');
INSERT INTO `task_info` VALUES ('276', 'B71B50AC-2F8B-DBBE-9495-907369E3A7C1-455955', '105', '01031005', '2', '3', null, null, null, 'FTB0273', null, '0', null, '3240216411', '2020-04-08 16:03:49', '2020-04-08 16:09:59');
INSERT INTO `task_info` VALUES ('277', 'D1D017E8-D1DB-E133-559E-CF52D48682CC-757882', '105', '02001005', '2', '3', null, null, null, 'FTB0274', null, '0', null, '3240216411', '2020-04-08 16:04:47', '2020-04-08 16:06:15');
INSERT INTO `task_info` VALUES ('278', 'F5A5DCE7-4E04-080C-A3D7-559DE97918D1-355542', '105', '02002005', '2', '3', null, null, null, 'FTB0275', null, '0', null, '3240216411', '2020-04-08 16:06:00', '2020-04-08 16:07:34');
INSERT INTO `task_info` VALUES ('279', '7F293983-6961-EAB9-93D0-57EB12CEEC06-900622', '105', '02003005', '2', '3', null, null, null, 'FTB0276', null, '0', null, '3240216411', '2020-04-08 16:07:02', '2020-04-08 16:08:48');
INSERT INTO `task_info` VALUES ('280', '89A218A0-A067-B52E-33B0-6F875D7215B5-132201', '105', '02004005', '2', '3', null, null, null, 'FTB0277', null, '0', null, '3240216411', '2020-04-08 16:08:00', '2020-04-08 16:12:25');
INSERT INTO `task_info` VALUES ('281', '53F1FD75-2838-8251-1F3F-629FD6D07AFA-110169', '105', '02005005', '2', '3', null, null, null, 'FTB0278', null, '0', null, '3240216411', '2020-04-08 16:09:40', '2020-04-08 16:11:11');
INSERT INTO `task_info` VALUES ('282', '7C68AA3C-9DDE-24F6-C719-F181AE032686-747008', '105', '02006005', '2', '3', null, null, null, 'FTB0279', null, '0', null, '3240216411', '2020-04-08 16:26:38', '2020-04-08 16:34:09');
INSERT INTO `task_info` VALUES ('283', '272F53C7-D716-806F-AE5B-AE5889D2BD67-753404', '105', '02007005', '2', '3', null, null, null, 'FTB0280', null, '0', null, '3240216411', '2020-04-08 16:31:46', '2020-04-08 16:40:04');
INSERT INTO `task_info` VALUES ('284', '538BDE65-2B58-9068-BFCB-9FC791D1C05A-427093', '105', '02008005', '2', '3', null, null, null, 'FTB0281', null, '0', null, '3240216411', '2020-04-08 16:35:19', '2020-04-08 16:46:59');
INSERT INTO `task_info` VALUES ('285', '29E99F2A-463F-98AC-210C-2DC437C953C9-596466', '105', '02009005', '2', '3', null, null, null, 'FTB0282', null, '0', null, '3240216411', '2020-04-08 16:47:57', '2020-04-08 16:50:27');
INSERT INTO `task_info` VALUES ('286', '8ACF593F-90EB-804C-FC1C-778296AD35C1-445380', '105', '02010005', '2', '3', null, null, null, 'FTB0283', null, '0', null, '3240216411', '2020-04-08 16:49:00', '2020-04-08 16:53:48');
INSERT INTO `task_info` VALUES ('287', '96B5D872-45B7-50F3-F619-BCC5CD454213-595575', '105', '02011005', '2', '3', null, null, null, 'FTB0284', null, '0', null, '3240216411', '2020-04-09 12:51:08', '2020-04-09 12:54:54');
INSERT INTO `task_info` VALUES ('288', '6B5AA07B-C99D-A2A0-F53D-F12E9B7A0ED0-094338', '105', '02012005', '2', '3', null, null, null, 'FTB0285', null, '0', null, '3240216411', '2020-04-09 13:01:52', '2020-04-09 13:04:09');
INSERT INTO `task_info` VALUES ('289', '48314E9B-7179-C767-3E52-091D75CC57A0-568677', '105', '02013005', '2', '3', null, null, null, 'FTB0286', null, '0', null, '3240216411', '2020-04-09 13:03:10', '2020-04-09 13:05:27');
INSERT INTO `task_info` VALUES ('290', '50D5B5B7-9AEA-1D8F-ED8F-84E4370E766F-746658', '105', '02014005', '2', '3', null, null, null, 'FTB0287', null, '0', null, '3240216411', '2020-04-09 13:04:34', '2020-04-09 13:06:51');
INSERT INTO `task_info` VALUES ('291', 'E86E8918-A5F8-8B7B-D48B-277D04F5B76C-294125', '105', '02015005', '2', '3', null, null, null, 'FTB0288', null, '0', null, '3240216411', '2020-04-09 13:05:29', '2020-04-09 13:09:04');
INSERT INTO `task_info` VALUES ('292', 'E5C77441-E17B-F270-D93D-53D3FCB6F8E2-981737', '105', '02016005', '2', '3', null, null, null, 'FTB0289', null, '0', null, '3240216411', '2020-04-09 13:06:38', '2020-04-09 13:07:55');
INSERT INTO `task_info` VALUES ('293', '06E9E9A4-0CD7-C3F2-C347-A0C7BE196357-631228', '105', '02017005', '2', '3', null, null, null, 'FTB0290', null, '0', null, '3240216411', '2020-04-09 13:11:03', '2020-04-09 13:13:23');
INSERT INTO `task_info` VALUES ('294', '3846A3E5-40DF-4CA9-0DC2-D20DA9171910-391574', '105', '02018005', '2', '3', null, null, null, 'FTB0291', null, '0', null, '3240216411', '2020-04-09 13:12:04', '2020-04-09 13:14:29');
INSERT INTO `task_info` VALUES ('295', '0A1180C5-6929-2783-FB21-D45D0CA26232-246039', '105', '02019005', '2', '3', null, null, null, 'FTB0292', null, '0', null, '3240216411', '2020-04-09 13:12:57', '2020-04-09 13:15:33');
INSERT INTO `task_info` VALUES ('296', 'E7FC0610-6F5E-0A28-0C2E-7CB15103AB2B-434591', '105', '02020005', '2', '3', null, null, null, 'FTB0293', null, '0', null, '3240216411', '2020-04-09 13:13:58', '2020-04-09 13:16:36');
INSERT INTO `task_info` VALUES ('297', '02DCB762-F37B-290B-ED41-25DD7F2B80A8-609596', '105', '02021005', '2', '3', null, null, null, 'FTB0294', null, '0', null, '3240216411', '2020-04-09 13:15:07', '2020-04-09 13:17:39');
INSERT INTO `task_info` VALUES ('298', '1429380B-3253-B3E0-7F1A-3DC45FE4EA2D-814308', '105', '02022005', '2', '3', null, null, null, 'FTB0295', null, '0', null, '3240216411', '2020-04-09 13:19:01', '2020-04-09 13:21:16');
INSERT INTO `task_info` VALUES ('299', 'FCA5BD03-3CE1-AEB9-EC77-C74E22E22CB3-439048', '105', '02023005', '2', '3', null, null, null, 'FTB0296', null, '0', null, '3240216411', '2020-04-09 13:35:28', '2020-04-09 13:37:44');
INSERT INTO `task_info` VALUES ('303', '29885BA7-C45C-2BA8-958F-4312E638780D-410722', '01002001', null, '1', '3', null, null, null, 'FTB0002', null, '0', null, '0000000000', '2020-04-15 17:28:59', '2020-04-15 17:30:54');
INSERT INTO `task_info` VALUES ('304', '5BF70441-C4F2-9143-9B87-43FBC60637B2-470030', '105', '01002001', '2', '3', null, null, null, 'FTB0002', null, '0', null, '0000000000', '2020-04-15 17:31:41', '2020-04-15 17:33:57');
INSERT INTO `task_info` VALUES ('305', '4B942D49-2F19-921F-4CF1-4CA2477520AE-806454', '01002001', null, '1', '3', null, null, null, 'FTB0002', null, '0', null, '0000000000', '2020-04-15 17:36:11', '2020-04-15 17:38:22');
INSERT INTO `task_info` VALUES ('306', 'D7785488-B707-381E-653B-2D5D583C1305-191014', '105', '01002001', '2', '3', null, null, null, 'FTB0002', null, '0', null, '0000000000', '2020-04-15 17:38:29', '2020-04-15 17:40:44');

-- ----------------------------
-- Table structure for `transfer_reason`
-- ----------------------------
DROP TABLE IF EXISTS `transfer_reason`;
CREATE TABLE `transfer_reason` (
  `transfer_reason_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '转移子库原因id',
  `transfer_reason` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '转移字库原因',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_no` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人卡号',
  PRIMARY KEY (`transfer_reason_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of transfer_reason
-- ----------------------------

-- ----------------------------
-- Table structure for `ware_info`
-- ----------------------------
DROP TABLE IF EXISTS `ware_info`;
CREATE TABLE `ware_info` (
  `ware_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `ware_code` varchar(50) DEFAULT NULL COMMENT '仓库编码',
  `ware_name` varchar(50) DEFAULT NULL COMMENT '仓库名',
  `create_user_name` varchar(20) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL COMMENT '添加时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `memo` varchar(50) DEFAULT NULL,
  `expected_waring` double(11,2) DEFAULT NULL COMMENT '爆仓预警',
  `alarm` double(11,2) DEFAULT NULL COMMENT '报警',
  `stock_waring` int(11) DEFAULT NULL COMMENT '滞库预警',
  `box_param` double(11,2) DEFAULT NULL COMMENT '物料数量在托盘中的占比参数',
  `box_height` int(11) DEFAULT NULL COMMENT '单箱可存放高度(mm)',
  `unqualified_storage_day` int(11) DEFAULT NULL COMMENT '不合格物料存储天数',
  PRIMARY KEY (`ware_id`) USING BTREE,
  KEY `ware_info_ware_id_PK` (`ware_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='仓库设置';

-- ----------------------------
-- Records of ware_info
-- ----------------------------
INSERT INTO `ware_info` VALUES ('212', 'AC1908252', '堆垛机库', null, null, '2019-06-15 15:20:56', '60', '10.00', '60.00', '12', '60.90', '130', '10');

-- ----------------------------
-- Table structure for `warn_information`
-- ----------------------------
DROP TABLE IF EXISTS `warn_information`;
CREATE TABLE `warn_information` (
  `warn_id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `memo` varchar(600) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '报警信息',
  `state` int(2) DEFAULT NULL COMMENT '1-待处理2-已处理',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`warn_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of warn_information
-- ----------------------------

-- ----------------------------
-- Table structure for `worker_order_issue_time`
-- ----------------------------
DROP TABLE IF EXISTS `worker_order_issue_time`;
CREATE TABLE `worker_order_issue_time` (
  `id` int(1) NOT NULL AUTO_INCREMENT,
  `first_time` time DEFAULT NULL,
  `second_time` time DEFAULT NULL,
  `third_time` time DEFAULT NULL,
  `fourth_time` time DEFAULT NULL,
  `fifth_time` time DEFAULT NULL,
  `sixth_time` time DEFAULT NULL,
  `auto_execute` int(1) DEFAULT NULL COMMENT '1-执行 0- 不执行',
  `operation_seqnum` varchar(11) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '工序号',
  `sluggish_export_date` int(2) DEFAULT NULL COMMENT '呆滞过期报表定时导出时间',
  `sluggish_export_param` int(1) DEFAULT NULL COMMENT '呆滞过期导出条件',
  `lock_time` int(2) DEFAULT NULL COMMENT '锁定时间(单位:分钟)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `work_order_issue_time_id_PK` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of worker_order_issue_time
-- ----------------------------
INSERT INTO `worker_order_issue_time` VALUES ('1', '00:00:00', '04:00:00', '08:00:00', '12:00:00', '16:00:00', '20:00:00', '1', '10', '1', '1', '15');
