/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : localhost:3306
 Source Schema         : cl

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 26/11/2020 07:29:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_activity
-- ----------------------------
DROP TABLE IF EXISTS `c_activity`;
CREATE TABLE `c_activity`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '活动id',
  `pbid` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布人id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `time` datetime NULL DEFAULT NULL COMMENT '举办时间',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动地点',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动标签',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主图',
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '活动详情',
  `views` int(10) NULL DEFAULT NULL COMMENT '浏览数',
  `collections` int(10) NULL DEFAULT NULL COMMENT '收藏数',
  `likes` int(10) NULL DEFAULT NULL COMMENT '点赞数',
  `unlikes` int(10) NULL DEFAULT NULL COMMENT '点踩数',
  `comments` int(10) NULL DEFAULT NULL COMMENT '评论数',
  `checked` bit(1) NULL DEFAULT b'1' COMMENT '审核与否',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '活动表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of c_activity
-- ----------------------------
INSERT INTO `c_activity` VALUES ('1301860828549709825', '1311560408772210690', '2020年清代扬州画派精品展将在广州开展_', '2020-08-28 00:00:00', '广东省广州市广州艺术博物院', '画展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20200926/FFB6CEBECDC14DAABC40EC3EE0A0064D.png', '来自辽宁省博物馆、扬州博物馆馆藏的70件清代扬州画派书画精品，汇聚广州艺术博物院馆藏的56文物（连同实物230件）组成“领异标新——清代扬州画派精品展”，8月28日起将在广州艺术博物院开展，本次展览由辽宁省博物馆、扬州博物馆、广州艺术博物院联合主办，也是文化和旅游部2020年全国美术馆馆藏精品展出季项目。展览将持续到11月28日。', 112, 4, 5, 0, 0, b'1', b'0', '2020-09-04 20:33:03', '2020-10-30 11:21:01');
INSERT INTO `c_activity` VALUES ('1301862830780416001', '1311560408772210690', '2020广州塔名家周末大舞台惠民演出时间及看点一览', '2020-07-25 00:00:00', '广东省广州市广州塔二层移动平台', '演出', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20200926/868AD047ECA04F28A2B62F5ACF123AA5.png', '7月25日至12月25日的每个周六晚上，广州塔都将举行精彩演出，一共举行23场，邀请文艺大家和名师名团轮番登台献艺，表演粤剧、广东音乐、粤曲等，并免费向市民和游客开放。', 36, 4, 2, 0, 0, b'1', b'0', '2020-09-04 20:41:00', '2020-11-01 08:01:39');
INSERT INTO `c_activity` VALUES ('1310225430411022337', '1309841503443828738', '【免费活动】马可菠萝x未命名 全民摆摊计划 ', '2020-10-01 00:00:00', '天津市 河北区 万福生活广场北宁湾2楼 ', '漫展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20200927/C7F506B30F4A4A7A892F7629BB9A9F73.jpg', '10月1日10点开始，结束时间为10月4日20点30分，为期四天的免费漫展活动，喜欢漫展的朋友可以看看。', 65, 6, 5, 0, 4, b'1', b'0', '2020-09-27 22:31:00', '2020-10-27 13:06:37');
INSERT INTO `c_activity` VALUES ('1312356672992530434', '1312337003208331265', '梦回“塘朗”·汉服嘉年华', '2020-10-01 00:00:00', '广东省 深圳市 南山区 西丽镇塘朗社区留仙大道3333号塘朗城', '漫展,汉服,嘉年华', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201003/9358E776FF014EA1AAE3D33738320DAF.png', '10月1日-8日，梦回“塘朗”·汉服嘉年华。\n活动时间：10月1日-8日。\n活动地点：塘朗城（塘朗站地铁直达）\n门票：无需门票\n福利：所有人汉服游园会项目免费玩。穿汉服的同袍可额外体验团扇制作并带走成品！\n\n汉服游园会项目\n\n10月1日-10月8日\n每日10:30-20:00\n\n一.投壶\n每次限3人体验，每人5支箭矢。\n注意事项：\n1、请遵守秩序 ，排队体验；\n2、严禁跨线进入投掷区域；\n3、限5岁以上顾客体验，5-8岁儿童可由1位家长陪同进入场地；8岁以上顾客限单人进入场地。\n\n二.射箭\n每次限3人体验，每人5支箭矢，限5分钟。\n注意事项：\n1、请遵守秩序 ，排队体验；\n2、严禁跨线进入射箭区域；\n3、限5岁以上顾客体验，5-8岁儿童可由1位家长陪同进入场地；8岁以上顾客限单人进入场地。\n\n三.团扇制作\n身着汉服的顾客可体验画团扇一次；每次限2人体验，限15-30分钟。\n由现场老师指导绘制，制作完成可将作品带走。\n注意事项：\n1、请遵守秩序 ，排队体验；\n2、需身着汉服，才可排队体验该项目；\n3、限4岁以上顾客体验，4-8岁儿童可由1位家长陪同进入场地；8岁以上顾客限单人进入场地；\n\n四.活字印刷术\n顾客凭当日任意金额消费小票体验活字印刷一次；\n每次限3人体验，限5-10分钟。\n顾客可挑选自己喜欢的诗词，由现场老师指导印刷，制作完成可将作品带走。\n注意事项：\n1、请遵守秩序 ，排队体验；\n2、顾客需出示当日消费小票，工作人员核验后，方可进场体验；\n3、消费小票为唯一入场凭证，线上支付记录、点菜单、提货单等不作为入场凭证；\n4、限4岁以上顾客体验，4-8岁儿童可由1位家长陪同进入场地；8岁以上顾客限单人进入场地；\n5、每日活动名额有限，先到先享。\n\n五.沉浸式大臣拖走体验\n顾客将扮演在朝堂上被拖走的大臣，大声喊出冒死进谏的宣言。\n活动现场准备“大臣”服装道具供顾客换装，安排古装侍卫将“大臣”拖走。有预备台词供“大臣”选择。\n注意事项：\n1、请遵守秩序 ，排队体验；\n2、限18岁以上顾客体验。\n3、本项目会与工作人员有一定的身体接触（需要被拖走），请谨慎选择参与。\n\n六.舞台区\n10月1日，古风歌舞表演\n汉舞《采薇》11:00、14:00\n真人布袋戏《般若行》11:30、16:00\n古风舞台剧《清平乐》17:00、19:30\n\n\n10月2日，汉仪汉服公开课\n礼仪之邦文化传承\n15：00 第一堂\n17：00 第二堂\n19：30 第三堂\n\n10月3日，古风歌舞大赛\n古典乐舞大赏\n14:30-17:00\n\n七.汉服巡游\n\n10月1日、2日、4日。将定时有大部队汉服同袍巡游并派送礼物~', 14, 2, 2, 0, 0, b'1', b'0', '2020-10-03 19:39:48', '2020-10-13 07:48:12');
INSERT INTO `c_activity` VALUES ('1312372138028912642', '1312337003208331265', '2020深圳第七届国际时尚电玩节', '2020-09-30 00:00:00', '广东省 深圳市 福田区 华强北步行街北区', '电玩,深圳,漫展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201003/A77AEF39BFA642598564553071AE6E78.png', '2020深圳国际电玩节看点\n\n　　节展以“漫尚潮玩季，赶潮敢燥来”为主题，开幕仪式将于9月30日下午举行，展期由原来的五天增加至十六天，展览时间也调整为下午三点至晚上九点。\n\n　　展商：大牌云集\n\n　　本届展会已有近百家国内外知名动漫游戏企业及潮玩品牌确定参展，其中包括腾讯、网易、多益网络、华强方特、中南卡通、美国POP、玄机科技、雷神科技、森宝积木等。\n\n　　漫威英雄系列、Hello kitty、熊出没、小黄鸭、乐比悠悠、天眼、秦时明月等深受喜爱的知名IP也将集体亮相。\n\n赛事：精彩不断\n\n　　设在华强北步行街的主舞台将上演三十余项不同类型的赛事活动。\n\n　　加多宝Cosplay大赛、爱奇艺JUMP JUMP舞蹈大赛深圳赛区、蒙牛花果轻乳偶像大赛等赛事燃爆舞台；\n\n　　“战马杯深圳市电子竞技锦标赛”总决赛、雷神科技举办的“第二届王者荣耀全国大赛海选赛深圳站”共掀电竞狂潮；\n\n　　街舞大赛、说唱大赛、滑板大赛等引爆新热点。\n\n　　主办方还特别邀请了国内人气声优、唱见与主播空降现场与粉丝互动，更有国内知名嘉宾举办专场签售会，与众多粉丝一齐掀起电玩风暴。\n\n　　时下热门的新款游戏、全程互动直播、竞技感十足的VR体验、科技智慧的机器人、赛博朋克风格的打卡点、国际潮流的赛事、点亮华强北的时尚光影秀等，共同构成沉浸式的“潮玩乐园”。\n\n　　通过打卡互动、舞台表演、明星签售等多种形式吸引众多动漫游戏爱好者参与，打造一场国际潮流文化的年度盛会。\n\n　　交通指南\n\n　　乘坐地铁2号线或者7号线，可以直接到华强北地铁站，下车就是华强北了；此外3号线在华新下车，也可以前往深圳华强北。', 24, 2, 2, 0, 1, b'1', b'0', '2020-10-03 20:41:15', '2020-10-26 15:19:36');
INSERT INTO `c_activity` VALUES ('1312378287193149441', '1312337003208331265', '【免费活动】王老吉动漫游戏展', '2020-10-07 00:00:00', '上海市 松江区 上海市松江区光星路1399号 五龙商业广场', '漫展,游戏展,电玩', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201003/B7DAAD806954468E97148FA42F7EE001.png', '王老吉动漫展时间：2020.10.7-2020.10.8\n王老吉动漫展地点：国华广场\n王老吉动漫展门票价格： 普通票0元 VIP票80元\n\n尊敬的广大市民及游客朋友们：\n\n1、 观展时间: 周一闭馆，周二至周日10:00-17:00(16:30停止入馆)，周五10:00-21:00(20:30停止入馆)，仅限单次使用;\n\n2、 观展前请先购买门票;\n\n3、 观展前须出示“随申码”+“预约通行码”(可提前截图保存)。到场馆的市民游客，请实名登记个人信息，获得“预约通行码”，凭门票+“随申码”+“预约通行码”，核销入塔;\n\n4、 游客观展前请配合工作人员进行测温仪或其他温度计的体温测量。温度超3℃的的游客不得入场;\n\n5、 游客务必全程佩戴口罩、展馆主入口醒目位置已设立相关防控的具体要求及注意事项，请游客配合遵守。\n\n6、 “预约通行码”可提前扫码申请，申请后当日检查有效，错过日期需重新申请;\n', 15, 2, 2, 0, 0, b'1', b'0', '2020-10-03 21:05:41', '2020-10-14 04:19:45');
INSERT INTO `c_activity` VALUES ('1312379926528479234', '1309841503443828738', '【免费展会】妖漫动漫游戏展暨万圣节奇妙日', '2020-10-31 00:00:00', '上海市 杨浦区 区闸殷路1599号国华广场', '妖漫,动漫', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201003/973FAF29D8A14253999BD5A2D94CBB38.png', '上海万圣节活动–妖漫动漫展　　\n玩法1：鬼怪充电站：　　COS阴阳师，屠夫，第五人格等鬼怪类角色皆可到服务台领取限量专属福利\n\n玩法2：VIP票福利　　1.可在2020年AACG国风动漫游戏展中获得一次抽奖资格　　2. 随机抽取3名优质coser邀请作为AACG国风动漫游戏展嘉宾　　3. 福利3，可在AACH官方服务台兴趣神秘礼品一份\n\n玩法3：特效化妆体验　　现场游客均可享受特效化妆玩法\n\n玩法4：现场神秘人发放万圣节礼物\n\n玩法五：妖漫自由行开启\n\n玩法6：万圣节布景', 17, 2, 2, 0, 1, b'1', b'0', '2020-10-03 21:12:12', '2020-10-23 18:10:35');
INSERT INTO `c_activity` VALUES ('1312386082407796738', '1309841503443828738', '【免费活动】第十届SKYACG EXPO 天空漫境动漫嘉年华', '2020-10-31 00:00:00', '上海市 浦东新区 云台路800号亿丰时代广场', '上海,动漫,嘉年华,天空', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201003/699DBED6C2C24E31A4D8B6F0F3BBA820.png', '上海·第十届SKYACG EXPO 天空漫境动漫嘉年华\n\n活动时间：2020.10.24 10:00 - 10.25 17:00\n\n活动地点：亿丰时代广场 云台路800号\n\n活动详情：\n\n　　第十届SKYACG EXPO 天空漫境动漫嘉年华来啦！情缘初梦，未来有你。喜你为疾，药石无医。忘我如痴，颦笑难寝。恋你成痴，疯癫半世。无医治心，无药疗疾。半生疯癫，半世痴迷。天若有情天亦老 ，月若无恨月长圆。一起相聚嘉年华吧！\n\n　　交通指南：\n\n　　公交583路、784路内圈、784路外圈、1012路云台路杨思路站下，步行72米', 19, 2, 2, 0, 1, b'1', b'0', '2020-10-03 21:36:40', '2020-10-26 15:08:24');
INSERT INTO `c_activity` VALUES ('1314215972086042626', '1311560408772210690', '比佛利无锡马拉松', '2020-11-01 00:00:00', '江苏无锡', '马拉松', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201008/8370E7CF59724F87B1005E994E6EEA1C.jpg', '2020江苏无锡暨大运河马拉松系列赛，无锡站。', 14, 1, 1, 0, 0, b'1', b'0', '2020-10-08 22:47:59', '2020-10-23 15:21:54');
INSERT INTO `c_activity` VALUES ('1320552380283326466', '1311560408772210690', '【免费活动】第十届SKYACG EXPO 天空漫境动漫嘉年华', '2020-10-31 00:00:00', '上海市 浦东新区 云台路800号亿丰时代广场', '漫展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/BA837A95FFB448F3BEC6E1A1D2FF9134.jpeg', '时间 2020-10-31 10:00 - 2020-11-01 17:00\n场馆设施 普通场馆级 交通方式 地铁公交直达 平台热度 热度一般 延期历史无延期', 3, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:26:37', '2020-10-28 16:38:16');
INSERT INTO `c_activity` VALUES ('1320553407883612161', '1311560408772210690', '【免费活动】CCPARK万圣祭', '2020-11-01 00:00:00', '山东省 济南市 历下区 CCPARK创意港室内', '漫展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/A805E010FC7143FB825785349C268579.jpg', '时间2020-11-01 10:00 - 2020-11-01 18:00', 3, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:30:42', '2020-10-28 16:35:17');
INSERT INTO `c_activity` VALUES ('1320554612655493122', '1311560408772210690', '【免费展会】第三届妖漫动漫游戏展', '2020-10-05 00:00:00', '上海市 松江区 上海市松江区光星路1399号 五龙商业广场', '漫展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/6839028FA49C48AFA2E4773B170307D5.jpg', '时间2020-10-05 10:00 - 2020-10-06 17:00', 3, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:35:29', '2020-10-28 16:36:17');
INSERT INTO `c_activity` VALUES ('1320555975443582978', '1311560408772210690', '【亲子活动】九九菊香——制作衍纸菊花', '2020-10-25 00:00:00', '西汉南越王博物馆一楼多功能游客中心', '纸菊花', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/04B0E9A92D3543599DCD26D8EC0DDD78.jpg', '“每逢佳节倍思亲”，在九九重阳节来临之际，南越工坊的线下手工活动回归，与大家重新见面。菊花在中国传统观念有“长寿花”的美誉，赏菊也是重阳节的重要特色习俗之一。借此佳节，南越工坊邀大家，三代同堂一起制作一副衍纸菊花，共度重阳。', 4, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:40:54', '2020-10-28 16:35:25');
INSERT INTO `c_activity` VALUES ('1320556458266693633', '1311560408772210690', '我是创客+AI智能 体验活动', '2020-10-22 00:00:00', '广州青少年科技馆（广东省广州市越秀区童心南路西胜街42号）', '科普', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/EA379A62D52944BB9EAE3301167A1942.png', '教育专家说家长的引导与陪伴是孩子最好的老师,未来是网络化、数字化、智能化为主基调的时代，为了激发青少年儿童从小对科学的热爱和兴趣，由广州市科学技术协会主办，广州市电子技术协会承办的“千师万苗工程——我是创客+AI智能体验活动”将于10月-11月举办多场亲子体验活动，欢迎各位家长报名参与。\n活动人群及报名形式：本次活动主题“我是创客+AI智能”。对象为3～12岁儿童参与，报名方式必须为网上预约，免费参与。本次活动涉及提前套件采购，所以只接受预约参与形式，预约是否成功以电话、短信通知为准。活动时间为1～1.5小时，完成后可以把成品带回家。套件主要涉及机械电路、手工拼装内容。1位儿童及1位家长，限2人。每场人数限定30个家庭参与。活动分为主会场、分会场及线上直播相继展开。\nA、线上活动设置：\n线上活动在《电子制作》官方抖音号直播形式举行，直播主持人直播线下活动环节。\nB、主会场线下活动设置：\n报名截止时间：2020年10月21日-11月2日\n活动时间：2020年11月7日或8日（以接到通知为准）\n活动场次：第一场：上午10:00-11:30\n第二场：下午14:00-15:30\n活动名额：每场不超过30组家庭（1名家长携带1名儿童）\n活动形式：科普知识讲解+趣味电子制作（纸质类模块安全环保）\n活动地点：广州青少年科技馆（广东省广州市越秀区童心南路西胜街42号）\n防疫要求：参加活动需佩戴口罩、量体温、扫描穗康码并作登记方能进馆，谢谢配合！', 3, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:42:49', '2020-10-28 16:36:24');
INSERT INTO `c_activity` VALUES ('1320556826904072193', '1311560408772210690', '展览推荐丨“我俩”——罗小颜•陈煜璧书画小品展', '2020-10-18 00:00:00', '岭南活力非遗艺术馆（广州市越秀区广州大道中289号289艺术园区生活楼二楼）', '人物画,书法', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/0EA55E8BD7C048BE8AFBB5BC645F967E.jpeg', '她，研习工笔人物画；他，精通书法；\n她，痴爱中国画的笔墨意趣；他，迷恋书法的线条飘逸。\n当她遇到他，伉俪联手，天作之合，便成为美妙的“我俩”。\n主办单位：\n广东南方二八九文化产业投资有限公司\n广东南方艺术空间展览有限公司\n广东洛克南方美术馆\n开幕式时间：2020年10月18日 下午15：30\n展览时间：2020年10月18日—31日\n地点：岭南活力非遗艺术馆（广州市越秀区广州大道中289号289艺术园区生活楼二楼）', 4, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:44:17', '2020-10-28 16:36:25');
INSERT INTO `c_activity` VALUES ('1320557507262124033', '1311560408772210690', '活动公告 | 2020惠州罗—南骑游活动', '2020-10-31 00:00:00', '广东省惠州市博罗县罗浮山游客服务中心广场', '骑游', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/4F72A5D7AD0B4E01BD3E728133935548.jpeg', '罗浮山风景区，是国家AAAAA级景区\n它位于广东东江之滨\n山势雄伟壮丽，自然风光旖旎\n南昆山原始森林自然保护区\n是国家AAAA级旅游景区\n也是重点保护的国家森林公园\n被誉为”北回归线上的绿洲”\n“南国避暑天堂”、”珠三角后花园”\n在森氧与蓝天的罗南之路上\n骑行穿梭山水风景\n在旖旎的景色中释放自我\n在森林海洋里尽情呼吸\n负氧离子成为我们的披风\n以稳健的姿态破风前进\n体验征服大自然的畅快淋漓\n惠州素以山、河、湖、海的自然资源而著称，南临南海，内地地貌多样、境内多以低海拔的丘陵、台地为主、森林覆盖面积近三分之二，海岸线长度280余公里。依托惠州的自然资源开展多样的户外山野活动有着得天独厚的优势。通过打造品牌的户外山野活动，挖掘惠州市的户外山野资源、推广惠州市的户外休闲旅游有着深远的意义。\n活动名称：2020罗—南骑游活动\n组织架构：\n指导单位：惠州市文化广电旅游体育局\n主办单位：龙门县文化广电旅游体育局\n博罗县文化广电旅游体育局\n博罗县罗浮山管理委员会\n支持单位：南昆山生态旅游区管理委员会\n龙门县体育总会\n承办单位：惠州市登山户外运动协会\n磨房网\n执行单位：惠州左邻右里文化传播有限公司\n\n活动时间：2020年10月31日\n\n活动地点：\n起点:博罗县罗浮山游客服务中心广场\n终点:龙门县南昆山生态旅游区管理委员会\n\n活动人数：800人（额满截止）\n\n路线描述：\n博罗县罗浮山游客服务中心（起点）—下浪村—酥醪村—罗坑村—大陂村—河东村—麻榨镇—苏村—合口村—马图岗—永汉镇—花竹村—二坑村—南昆山生态旅游区管理委员会（终点）\n路线全程75公里，累计爬升约1000米。路线穿越博罗县罗浮山风景名胜区、横河镇和龙门县麻榨镇、永汉镇及南昆山生态旅游区管理委员会。\n沿途每20公里左右安排补给站、救援点、下撤指引。补给物资分别有饮水、饮料、水果等物资。', 3, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:46:59', '2020-10-28 16:36:00');
INSERT INTO `c_activity` VALUES ('1320558382416236545', '1311560408772210690', '不辞长作岭南人——荔枝文化展', '2020-10-31 00:00:00', '广东省博物馆 三层展厅3', '文化展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/A88557F7A6A94BB1860977F2893CD5F9.jpeg', '刚刚结束了十月的小长假，市民朋友们回归了正常的工作学习生活。在闲暇之余，不妨走进艺术馆，于凉风秋日共度艺术的周末。\n\n广东省博物馆\n\n不辞长作岭南人——荔枝文化展\n\n在广东省文化和旅游厅的指导下，广东省博物馆结合自身馆藏资源，深入挖掘荔枝历史底蕴、文化内涵和岭南特色，于国庆中秋双庆期间推出《不辞长作岭南人——荔枝文化展》，旨在通过展览助力荔枝产业高质量发展，推进文旅融合，提升“广东荔枝”的知名度和影响力。\n展期：2020年9月29日 至 2020年12月6日\n费用：免费\n地点：广东省博物馆 三层展厅3', 6, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:50:28', '2020-10-28 16:34:57');
INSERT INTO `c_activity` VALUES ('1320559019078029313', '1311560408772210690', '月满中秋 灯赏华诞 主题灯彩艺术作品展', '2020-10-01 00:00:00', '广东省文化馆一楼展览厅（地址：广州市天河区广州大道中1229号）', '文化馆', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/102018C6D2A444A98F2BF4771929CDE7.jpg', '展览展出了由非遗传承人和新锐设计师亲手制作或设计的灯彩作品百余件，涵盖了大良鱼灯、佛山彩灯等多种传统灯彩，以及融合了现代美学理念的原创新式灯。灯影交错之间，市民朋友们还能看到以图文形式呈现的广东各地“灯”文化，感受传统与现代的美丽邂逅，唤醒对“灯”的美好记忆。\n展期：2020年10月1日至30日9:30-17:00（逢周一闭馆）\n费用：免费\n地点：广东省文化馆一楼展览厅（地址：广州市天河区广州大道中1229号）', 4, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:53:00', '2020-10-28 16:35:30');
INSERT INTO `c_activity` VALUES ('1320559270849515522', '1311560408772210690', '祥瑞之气“定格”青年艺术家展示计划 × 张伟', '2020-10-01 00:00:00', '广州定格美术馆', '美术', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/6E01C58938CD47D9AE8B23C5E21445BA.jpeg', '祥瑞之气“定格”青年艺术家展示计划 × 张伟\n\n艺术家张伟通过对日常生活的观察和思考，利用生活中习以为常的物品为灵芝菌丝塑造出一个可控的有利于生长的环境。作品中灵芝子实体的生长也仿佛是容器里喷薄而发的气体，小提琴、水壶、红酒瓶等容器的冒出的祥瑞之“气”。\n展期：2020年9月30日-11月29日\n费用：免费\n地点：广州定格美术馆', 4, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:54:00', '2020-10-28 16:34:37');
INSERT INTO `c_activity` VALUES ('1320559592653295617', '1311560408772210690', '物是&物非 文-画及其不适吴笛笛个展', '2020-11-01 00:00:00', '广州三域·汇 艺术空间', '艺术', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/29F52A19902A4D869546208523D491DF.jpeg', '展出吴笛笛多件以石、木、竹、鸟、虫为素材的重要作品。\n吴笛笛的创作以个体直观体验为基础进行重新架构，从而试图产生个体性的在地实践、思考和绘画方式，并以精湛的手法揭示出超越观看视距的视觉真实，她的绘画实践是个体感知力对存在物认识的考察，将东方美学与西方哲学结合构建出的画面，传达出生命种种讯息，各自的状态，不禁让人思考存在的意义。\n展期：2020年9月19日-11月22日\n费用：免费\n地点：广州三域·汇 艺术空间', 6, 1, 2, 0, 0, b'1', b'0', '2020-10-26 10:55:16', '2020-10-28 16:35:38');
INSERT INTO `c_activity` VALUES ('1320559906643087361', '1311560408772210690', '燃的尽2020当代火柴展', '2020-10-01 00:00:00', '海珠区BIG海珠湾艺术园区六栋二楼INFOREST展览馆', '火柴展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/7D9A815BAE5E48E1A9B2A51E8AB58061.jpeg', '燃的尽2020当代火柴展\n\n樊哲是著名的旅法艺术家，本次在广州“在苒艺术馆”开展的火柴艺术展，集摄影作品，装置与绘画与一体，在光影中体验樊哲所表达的“共享体验”理念。\n展期：10月01日-10月31日\n费用：68元（含饮品一杯）\n地点：海珠区BIG海珠湾艺术园区六栋二楼INFOREST展览馆', 14, 1, 1, 0, 0, b'1', b'0', '2020-10-26 10:56:31', '2020-10-28 16:35:12');
INSERT INTO `c_activity` VALUES ('1320560158519431169', '1311560408772210690', '粤港澳大湾区潮流文化中心  身体的独白', '2020-10-01 00:00:00', '2020PROJECT IMAC/广州市海珠万达广场负一层粤港澳大湾区潮流文化中心内', '漫展,汉服,嘉年华,漫展,汉服,嘉年华', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/38BB8A599CC94CE49F49DD9D314F2610.jpeg', '同样是源自于疫情的感受，所做的展览。本展览展出了3位艺术家的作品，这些展品很特别，属于“实验影像作品”。通过视觉、听觉、环境影响，来让人感受此次的主题：聆听身体的独白。\n展期：2020.09.12—11.15 Mon-Sun 10:00-18:00\n费用：免费\n地点：2020PROJECT IMAC/广州市海珠万达广场负一层粤港澳大湾区潮流文化中心内', 36, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:57:31', '2020-10-28 16:36:02');
INSERT INTO `c_activity` VALUES ('1320560390250532866', '1311560408772210690', '2020广州雕塑大展暨澳门雕塑邀请展', '2020-10-01 00:00:00', '天河区文化艺术中心一楼展览厅（天河区汇彩北路8号）', '雕塑', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/8E124FFECF814C22A52D1CA095D2B087.jpeg', '2020广州雕塑大展暨澳门雕塑邀请展\n\n本次展览是多个文化单位共同组办的“邀请展”，众多中青年雕塑家的作品将被集中展出。\n本次展览分为四大展区：天河区文化艺术中心展区、羊城创意园筑梦空间展区、广州城市公园展览馆展区、小洲艺术区展区。\n展期：9月27日-10月20日\n费用：免费\n地点：天河区文化艺术中心一楼展览厅（天河区汇彩北路8号）', 36, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:58:27', '2020-10-28 16:36:21');
INSERT INTO `c_activity` VALUES ('1320560729049632770', '1311560408772210690', '错觉 Feeling Right', '2020-10-03 00:00:00', '广州越秀区东山口启明社区东二门启明三马路23号ART23当代艺术馆', 'Feeling,Right', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/8B47F2A6355246FC8F278138F598298F.jpeg', 'ART23艺术馆\n错觉 Feeling Right\n“作者希望观看这个展览的观众减少对展览语言的依赖。作者没有什么需要被读懂和被理解的信息要传达，只是希望创造出充满美和愉悦的作品供各位欣赏体验。祝观展愉快。”（为尊重作者意愿，此处引用原文）\n展期：2020年9月12日 – 2021年1月3日周二至周五 14:00 – 18:30周六至周日 11:00 – 19:00\n费用：29元/人（一票双展）\n地点：广州越秀区东山口启明社区东二门启明三马路23号ART23当代艺术馆', 23, 0, 0, 0, 0, b'1', b'0', '2020-10-26 10:59:47', '2020-10-28 16:39:04');
INSERT INTO `c_activity` VALUES ('1320561328835104770', '1311560408772210690', '跟着这十条乡村游线 玩遍南沙！', '2020-10-03 00:00:00', '广州南沙', '南沙,广州风情', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/6A95C93F683B4CE085C8778F0421B89E.jpeg', '在我大广州，有这么一处地方：它地处广州最南端，是珠三角地理几何中心，同时拥有国家级新区、自贸试验区、粤港澳全面合作示范区、承载门户枢纽功能的广州城市副中心四重战略定位，这就是南沙！\n\n现在很多人，把南沙作为自己的创业、打拼的热土，正用双手创造属于自己的美好生活。\n\n不过南沙的魅力还不止于此，作为海陆交汇的一个区，它拥有超高颜值。目前，全区开发建设旅游景点20余处，形成了东部、南部、北部三大特色旅游板块。其中国家4A级景区3个（南沙湿地、百万葵园、南沙天后宫），3A级景区1个（永乐农庄），不仅是改革开放的热土，也孕育了岭南、水乡、海洋和妈祖四大文化。\n\n万顷沙的“莲藕文化节”、榄核的果蔗文化节、南沙街的荔枝文化节、东涌镇的农业生态旅游推广节等一系列具有地域特色的乡村旅游活动品牌已深入人心。真是一个来了就不想走的旅游宝库。\n\n12月12日，南沙区更是一口气发布10条南沙乡村旅游精品线路。它们集纳了南沙的精华景观——东涌镇东涌村民居沿河而建，有岭南水乡的风情；黄阁镇莲溪村古朴沉静，莲塘飘香，宛若世外桃源；万顷沙镇红港渔村河涌环绕，渔船穿梭，尽显渔家风情……十条线路将种业小镇、十九涌、南沙水鸟徒步线路、南沙现代农业科学院、农场优质水果采摘体验地等“网红打卡点”串联起来，给你一个“不一样”的南沙。\n1.南沙湿地徒步观鸟、十九涌海鲜美食、智正农场采摘水果\n\n最具中国南部沿海景观特色和科学价值的南沙湿地，是珠江三角洲的“候鸟天堂”，每年吸引了十多万只候鸟来湿地栖息过冬，占广州市候鸟总数的50%以上，种类超过200种。这里不仅是苍鹭、白鹭、夜鹭、东方白鹳、黑翅长脚鹬、斑嘴鸭、燕鸥等鸟类游乐的天堂，国家一级保护鸟类白尾海雕以及二级保护鸟类黑脸琵鹭等也在此停歇。十九涌的海鲜美食，对于任何一位广州老饕来说，也都是绝对不能忽略的存在。\n2.百万葵园观赏花卉、永乐农庄团建拓展\n\n百万葵园是中国花卉业景观的一个经典故事，花田里的梦幻般的酒店，令孩子绽放笑容，也是许多大人未偿的童梦。永乐农庄的乡村风景，不仅是传统乡土生活的存留，一样是人们与自然和谐共生的美好体验。\n3.水鸟世界、红港渔村疍家文化\n\n南沙是水鸟的家园，在这里，不仅能观鸟，孩子们还能学到很多有关水鸟的知识；至于疍家文化，那更是属于广东，属于海洋的独特记忆。', 14, 0, 0, 0, 0, b'1', b'0', '2020-10-26 11:02:10', '2020-10-28 16:34:41');
INSERT INTO `c_activity` VALUES ('1320562407769477121', '1311560408772210690', ' 长隆万圣节', '2020-10-22 00:00:00', ' 广州 番禺区 长隆欢乐世界', '万圣节', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/26C64432EE674A35B0B85AE055F2FD19.jpg', '活动详情\n10月9日~11月1日，连续24日\n\n全新打造三大鬼王强势登场，\n\n联手欢乐回归五大鬼王，\n\n八大鬼王IP全面集结！\n\n给你一次视觉上惊悚的狂欢盛宴\n五大鬼王强势回归，协同三大新鬼王暗黑恶魔、千年冰封尸王、木偶大师强势登场，到时候还会在园区内游走与你近距离惊悚互动，邀你共度欢乐万圣\n\n敬请期待！\n\n两大鬼屋“木偶大师”和“诅咒之屋”全新登场！\n\n8大主题区刺激升级，尖叫不间断\n\n被肢解的木偶肢体，血腥腐化的气息，给你终生难忘的惊悚体验\n\n众鬼齐聚演艺，创新鬼甘震撼', 17, 0, 0, 0, 0, b'1', b'0', '2020-10-26 11:06:28', '2020-10-29 21:07:05');
INSERT INTO `c_activity` VALUES ('1320563455703748610', '1311560408772210690', '看得见的地方——庄棪作品展', '2020-10-24 00:00:00', ' 广州 越秀区 东山口恤孤院路9号 逵园艺术馆（中共三大会址正对面）', '作品展', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/AAD9214614DE41BAA398B31A7C8950F2.png', '艺术家：庄棪\n策展人：李听尘\n展期：2020年10月24日——2020年11月16日 \n时间：每天 11:00—18:00\n地点：广州市越秀区恤孤院路9号 逵园艺术馆\n主办：逵园艺术馆\n活动详情\n艺术家庄棪1991年出生于广东潮州。2010年毕业于广州美术学院附中，2014年本科毕业于广州美术学院油画系，2018年硕士研究生毕业于广州美术学院油画系。现为广东省潮州市美术家协会会员，任教于广州美术学院附属中等美术学校。\n\n尽管与同辈共享艺术发展传承的脉络，如何与各自的时代背景和文化基因产生关联，艺术家们有各自不同的探索。在强调自我和时代之真实本质的各种尝试中，社会写实主义仍然是广受欢迎的方式之一。在艺术界热衷于对形式主义的大胆探索或关于架空现实的新奇描绘的当下，许多艺术家仍然诚恳谦卑地刻画着日常生活场景。在过往百年的美术史中，从苏联时期的社会主义写实绘画到上世纪初美国的垃圾桶画派，从古斯塔夫·库尔贝（Gustave Courbet）到爱德华·霍珀（Edward Hopper），无论具体哪一个运动或艺术家，社会写实主义都能积极参与到对当代生活、社会和文化现象的共同思考中来。在这一长长脉络中，青年艺术家庄棪的作品再次丰富了这一创造性手段，回顾并发展了前人的成果，同时也毫无疑问地保留了批判性的框架，这一切都为解读她的作品增添了内涵和趣味。本次展览中，她的作品带来了没有预设立场的现实“视界”，却捕捉到了当代生活中的最富吸引力的气息。', 64, 0, 0, 0, 1, b'1', b'0', '2020-10-26 11:10:37', '2020-10-29 21:19:26');
INSERT INTO `c_activity` VALUES ('1320565600192663553', '1311560408772210690', '串联34处大型绿地、220处传统村落，这样的碧道你喜欢吗', '2020-10-26 00:00:00', '广州市海珠区阅江路示范段碧道', '绿道', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/36D9A99594344C4089DE923E0C3B4481.jpeg', '利用碧道串联34处全市大型绿地斑块，串联超220处传统村落、文物古迹、特色名城小镇等节点，沿碧道打造公共服务综合带和373公里水上运动产业带，辅助85片产业片区发展，挖掘碧道沿线4类48片价值地区。\n\n对于市民来说，这样的碧道规划是不是特别吸引人。\n蕉门河边的碧道\n\n没错，这就是最近广州已正式印发《广州市碧道建设总体规划（2019-2035年）》（以下简称《规划》）提到的。\n广州要建2000公里碧道\n\n获悉，《规划》提出至2025年，全市建成碧道1506公里；至2035年，全市建成碧道2000公里的总体目标。提出了水资源保障、水安全提示、水环境改善等主要任务，制定了详细的建设计划及保障措施，并总结出一套千里碧道建设的广州经验。', 13, 0, 0, 0, 0, b'1', b'0', '2020-10-26 11:19:09', '2020-10-29 21:18:47');
INSERT INTO `c_activity` VALUES ('1320566146844692481', '1311560408772210690', '周末还能去哪', '2020-10-26 00:00:00', '广州二沙岛体育公园', '体育公园', 'https://leoi.oss-cn-shenzhen.aliyuncs.com/citylife/20201026/AE67A7A8C01145458CACE07A0CB7A9C6.jpeg', '最近\n不少景区都开始闭园升级\n例如广州融创水世界\n广州长隆水上乐园\n岭南印象园\n广州市儿童公园戏水乐园\n珠海荷包岛旅游景区\n另外，受今年16号台风“浪卡”的影响\n汕尾红海湾遮浪奇观景区也闭园了\n\n出去玩的小伙伴可要注意\n不要扑个空了\n这些景区不能去玩了\n那周末还能去哪儿呢？\n景区\n\n广州二沙岛体育公园\n\n改造后的二沙岛体育公园，将成为广州首座智能体育公园，现在已经有部分区域对外开放了！现在有不少智能体育器械可以体验，周末去健身运动，也是一个好选择！\n开放时间：8:00-23:00\n地址：广东省广州市越秀区二沙岛大通路\n费用：免费', 18, 0, 0, 0, 0, b'1', b'0', '2020-10-26 11:21:19', '2020-11-01 08:01:34');

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'openid',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` bit(1) NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户签名',
  `disabled` bit(1) NULL DEFAULT b'0' COMMENT '是否小黑屋',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登录',
  `cheats` int(10) NULL DEFAULT NULL COMMENT '秘籍数量',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES ('1328623794515992577', 'oupHO4vTn7NQkW6qx62oqXw9OTyU', '指尖的阳光', b'1', 'https://thirdwx.qlogo.cn/mmopen/vi_32/zIacicM0F3hbcMQZhJuM8KgEANt1ANLvHWcg1Pvbg1wib7Wbkvh9k4hicjEAQGJf6HKIlzFqqajmz8AkOzwlknH5g/132', '湛江', NULL, b'0', NULL, NULL, b'0', '2020-11-17 16:59:31', '2020-11-17 19:47:25');

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'c_user', '微信用户', 'CUser', 'crud', 'com.ruoyi.system', 'system', 'user', '微信用户', 'ruoyi', '0', '/', NULL, 'admin', '2020-10-28 08:10:56', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (2, 'c_activity', '活动表', 'CActivity', 'crud', 'com.ruoyi.system', 'system', 'activity', '活动', 'ruoyi', '0', '/', NULL, 'admin', '2020-11-17 21:54:22', '', NULL, NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
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
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', '用户id', 'char(19)', 'String', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (2, '1', 'openid', 'openid', 'varchar(128)', 'String', 'openid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (3, '1', 'nickname', '昵称', 'varchar(100)', 'String', 'nickname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (4, '1', 'sex', '性别', 'bit(1)', 'Integer', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (5, '1', 'avatar', '头像', 'varchar(255)', 'String', 'avatar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (6, '1', 'city', '城市', 'varchar(50)', 'String', 'city', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (7, '1', 'sign', '用户签名', 'varchar(255)', 'String', 'sign', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (8, '1', 'disabled', '是否小黑屋', 'bit(1)', 'Integer', 'disabled', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (9, '1', 'last_login_time', '最近一次登录', 'datetime', 'Date', 'lastLoginTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (10, '1', 'cheats', '秘籍数量', 'int(10)', 'Integer', 'cheats', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (11, '1', 'deleted', '逻辑删除', 'bit(1)', 'Integer', 'deleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (12, '1', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (13, '1', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2020-10-28 08:10:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (14, '2', 'id', '活动id', 'char(19)', 'String', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (15, '2', 'pbid', '发布人id', 'char(19)', 'String', 'pbid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (16, '2', 'title', '活动标题', 'varchar(100)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (17, '2', 'time', '举办时间', 'datetime', 'Date', 'time', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (18, '2', 'position', '活动地点', 'varchar(255)', 'String', 'position', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (19, '2', 'label', '活动标签', 'varchar(100)', 'String', 'label', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (20, '2', 'img', '主图', 'varchar(255)', 'String', 'img', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (21, '2', 'detail', '活动详情', 'text', 'String', 'detail', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (22, '2', 'views', '浏览数', 'int(10)', 'Integer', 'views', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (23, '2', 'collections', '收藏数', 'int(10)', 'Integer', 'collections', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (24, '2', 'likes', '点赞数', 'int(10)', 'Integer', 'likes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (25, '2', 'unlikes', '点踩数', 'int(10)', 'Integer', 'unlikes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (26, '2', 'comments', '评论数', 'int(10)', 'Integer', 'comments', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (27, '2', 'checked', '审核与否', 'bit(1)', NULL, 'checked', '0', '0', NULL, '1', '1', '1', '1', 'EQ', NULL, '', 14, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (28, '2', 'deleted', '逻辑删除', 'bit(1)', NULL, 'deleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', NULL, '', 15, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (29, '2', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2020-11-17 21:54:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (30, '2', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2020-11-17 21:54:22', '', NULL);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2020-10-28 00:13:37', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2020-10-28 00:13:37', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2020-10-28 00:13:37', '', NULL, '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '女', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2020-10-28 00:13:37', 'admin', '2020-10-28 09:25:45', '性别女');
INSERT INTO `sys_dict_data` VALUES (2, 2, '男', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2020-10-28 00:13:37', 'admin', '2020-10-28 09:25:50', '性别男');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 0, '是', '1', 'sys_logic', NULL, NULL, 'N', '0', 'admin', '2020-11-12 11:33:58', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (101, 1, '否', '0', 'sys_logic', NULL, NULL, 'N', '0', 'admin', '2020-11-12 11:34:07', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2020-10-28 00:13:37', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '逻辑', 'sys_logic', '0', 'admin', '2020-11-12 11:33:32', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2020-10-28 00:13:37', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (113, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-11-12 10:30:41');
INSERT INTO `sys_logininfor` VALUES (114, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码已失效', '2020-11-17 21:54:08');
INSERT INTO `sys_logininfor` VALUES (115, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2020-11-17 21:54:11');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 'M', '0', '0', '', 'system', 'admin', '2020-10-28 00:13:36', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 'M', '0', '0', '', 'monitor', 'admin', '2020-10-28 00:13:36', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, 1, 'M', '0', '0', '', 'tool', 'admin', '2020-10-28 00:13:36', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2020-10-28 00:13:36', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2020-10-28 00:13:36', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2020-10-28 00:13:36', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2020-10-28 00:13:36', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2020-10-28 00:13:36', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2020-10-28 00:13:36', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2020-10-28 00:13:36', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2020-10-28 00:13:36', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', 'system/log/index', 1, 'M', '0', '0', '', 'log', 'admin', '2020-10-28 00:13:36', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2020-10-28 00:13:37', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2020-10-28 00:13:37', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2020-10-28 00:13:37', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2020-10-28 00:13:37', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2020-10-28 00:13:37', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2020-10-28 00:13:37', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2020-10-28 00:13:37', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2020-10-28 00:13:37', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', '0', 'system:user:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', '0', 'system:user:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 'F', '0', '0', 'system:user:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 'F', '0', '0', 'system:user:import', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', '0', 'system:role:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', '0', 'system:role:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 'F', '0', '0', 'system:role:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 'F', '0', '0', 'system:post:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 'F', '0', '0', 'system:post:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 'F', '0', '0', 'system:post:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 'F', '0', '0', 'system:config:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 'F', '0', '0', 'system:config:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 'F', '0', '0', 'system:config:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 114, 1, '#', '', 1, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 114, 2, '#', '', 1, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 114, 3, '#', '', 1, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 114, 2, '#', '', 1, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 114, 4, '#', '', 1, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 114, 5, '#', '', 1, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2020-10-28 00:13:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '同城小活动', 0, 4, 'citylife', NULL, 1, 'M', '0', '0', NULL, 'guide', 'admin', '2020-10-28 07:52:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, '微信用户', 2000, 1, 'user', 'citylife/user/index', 1, 'C', '0', '0', 'citylife:user:list', 'wechat', 'admin', '2020-10-28 08:14:12', 'admin', '2020-11-17 22:05:46', '微信用户菜单');
INSERT INTO `sys_menu` VALUES (2002, '微信用户查询', 2001, 1, '#', '', 1, 'F', '0', '0', 'citylife:user:query', '#', 'admin', '2020-10-28 08:14:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2003, '微信用户新增', 2001, 2, '#', '', 1, 'F', '0', '0', 'citylife:user:add', '#', 'admin', '2020-10-28 08:14:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2004, '微信用户修改', 2001, 3, '#', '', 1, 'F', '0', '0', 'citylife:user:edit', '#', 'admin', '2020-10-28 08:14:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '微信用户删除', 2001, 4, '#', '', 1, 'F', '0', '0', 'citylife:user:remove', '#', 'admin', '2020-10-28 08:14:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '微信用户导出', 2001, 5, '#', '', 1, 'F', '0', '0', 'citylife:user:export', '#', 'admin', '2020-10-28 08:14:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '活动', 2000, 1, 'activity', 'citylife/activity/index', 1, 'C', '0', '0', 'citylife:activity:list', 'international', 'admin', '2018-03-01 00:00:00', 'admin', '2020-11-17 22:06:33', '活动菜单');
INSERT INTO `sys_menu` VALUES (2008, '活动查询', 2007, 1, '#', '', 1, 'F', '0', '0', 'citylife:activity:query', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2009, '活动新增', 2007, 2, '#', '', 1, 'F', '0', '0', 'citylife:activity:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2010, '活动修改', 2007, 3, '#', '', 1, 'F', '0', '0', 'citylife:activity:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2011, '活动删除', 2007, 4, '#', '', 1, 'F', '0', '0', 'citylife:activity:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2012, '活动导出', 2007, 5, '#', '', 1, 'F', '0', '0', 'citylife:activity:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2020-10-28 00:13:37', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2020-10-28 00:13:37', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (110, '微信用户', 1, 'com.ruoyi.citylife.controller.CUserController.add()', 'POST', 1, 'admin', NULL, '/citylife/citylife/user', '127.0.0.1', '内网IP', '{\"openid\":\"1\",\"sex\":0,\"updateTime\":1605148261124,\"createTime\":1605148261113,\"id\":\"1326714082492022786\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 10:31:01');
INSERT INTO `sys_oper_log` VALUES (111, '微信用户', 2, 'com.ruoyi.citylife.controller.CUserController.edit()', 'PUT', 1, 'admin', NULL, '/citylife/citylife/user', '127.0.0.1', '内网IP', '{\"openid\":\"1\",\"sex\":1,\"updateTime\":1605148269909,\"deleted\":0,\"createTime\":1605148261000,\"disabled\":0,\"id\":\"1326714082492022786\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 10:31:09');
INSERT INTO `sys_oper_log` VALUES (112, '微信用户', 2, 'com.ruoyi.citylife.controller.CUserController.edit()', 'PUT', 1, 'admin', NULL, '/citylife/citylife/user', '127.0.0.1', '内网IP', '{\"openid\":\"1\",\"sex\":1,\"updateTime\":1605148279766,\"deleted\":0,\"createTime\":1605148261000,\"disabled\":0,\"id\":\"1326714082492022786\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 10:31:19');
INSERT INTO `sys_oper_log` VALUES (113, '微信用户', 2, 'com.ruoyi.citylife.controller.CUserController.edit()', 'PUT', 1, 'admin', NULL, '/citylife/citylife/user', '127.0.0.1', '内网IP', '{\"openid\":\"1\",\"sex\":true,\"updateTime\":1605149816367,\"lastLoginTime\":1601481600000,\"deleted\":false,\"createTime\":1603849211000,\"disabled\":false,\"id\":\"1321265474867208193\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 10:56:56');
INSERT INTO `sys_oper_log` VALUES (114, '微信用户', 3, 'com.ruoyi.citylife.controller.CUserController.remove()', 'DELETE', 1, 'admin', NULL, '/citylife/citylife/user/1326714082492022786', '127.0.0.1', '内网IP', '{ids=1326714082492022786}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 11:00:56');
INSERT INTO `sys_oper_log` VALUES (115, '微信用户', 2, 'com.ruoyi.citylife.controller.CUserController.edit()', 'PUT', 1, 'admin', NULL, '/citylife/citylife/user', '127.0.0.1', '内网IP', '{\"openid\":\"1\",\"sex\":false,\"updateTime\":1605151828612,\"lastLoginTime\":1601481600000,\"deleted\":false,\"createTime\":1603849211000,\"disabled\":false,\"id\":\"1321265474867208193\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 11:30:28');
INSERT INTO `sys_oper_log` VALUES (116, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.add()', 'POST', 1, 'admin', NULL, '/citylife/system/dict/type', '127.0.0.1', '内网IP', '{\"params\":{},\"dictType\":\"sys_logic\",\"createBy\":\"admin\",\"dictName\":\"逻辑\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 11:33:32');
INSERT INTO `sys_oper_log` VALUES (117, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/citylife/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"1\",\"dictSort\":0,\"params\":{},\"dictType\":\"sys_logic\",\"dictLabel\":\"是\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 11:33:58');
INSERT INTO `sys_oper_log` VALUES (118, '字典数据', 1, 'com.ruoyi.web.controller.system.SysDictDataController.add()', 'POST', 1, 'admin', NULL, '/citylife/system/dict/data', '127.0.0.1', '内网IP', '{\"dictValue\":\"0\",\"dictSort\":1,\"params\":{},\"dictType\":\"sys_logic\",\"dictLabel\":\"否\",\"createBy\":\"admin\",\"default\":false,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-12 11:34:07');
INSERT INTO `sys_oper_log` VALUES (119, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/citylife/tool/gen/importTable', '127.0.0.1', '内网IP', 'c_activity', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-17 21:54:22');
INSERT INTO `sys_oper_log` VALUES (120, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/citylife/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2020-11-17 21:54:26');
INSERT INTO `sys_oper_log` VALUES (121, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/citylife/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"wechat\",\"orderNum\":\"1\",\"menuName\":\"微信用户\",\"params\":{},\"parentId\":2000,\"path\":\"user\",\"component\":\"citylife/user/index\",\"children\":[],\"createTime\":1603844052000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2001,\"menuType\":\"C\",\"perms\":\"citylife:user:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-17 22:05:46');
INSERT INTO `sys_oper_log` VALUES (122, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/citylife/system/menu', '127.0.0.1', '内网IP', '{\"visible\":\"0\",\"icon\":\"international\",\"orderNum\":\"1\",\"menuName\":\"活动\",\"params\":{},\"parentId\":2000,\"path\":\"activity\",\"component\":\"citylife/activity/index\",\"children\":[],\"createTime\":1519833600000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2007,\"menuType\":\"C\",\"perms\":\"citylife:activity:list\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-17 22:06:33');
INSERT INTO `sys_oper_log` VALUES (123, '活动', 1, 'com.ruoyi.citylife.controller.CActivityController.add()', 'POST', 1, 'admin', NULL, '/citylife/citylife/activity', '127.0.0.1', '内网IP', '{\"img\":\"1\",\"comments\":1,\"updateTime\":1605622020453,\"label\":\"1\",\"title\":\"1\",\"unlikes\":1,\"collections\":1,\"createTime\":1605622020451,\"pbid\":\"1\",\"detail\":\"1\",\"id\":\"1328701173267554305\",\"position\":\"1\",\"time\":1604246400000,\"views\":1,\"likes\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2020-11-17 22:07:00');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2020-10-28 00:13:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2020-10-28 00:13:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2020-10-28 00:13:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2020-10-28 00:13:36', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2020-10-28 00:13:36', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2020-10-28 00:13:36', 'admin', '2020-10-28 00:13:36', '', NULL, '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2020-10-28 00:13:36', 'admin', '2020-10-28 00:13:36', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
