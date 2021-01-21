
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT 'N' COMMENT 'Y-被删除，N-未被删除',
  `received_user` int(10) DEFAULT NULL,
  `action_url` varchar(1024) DEFAULT NULL,
  `message_type` varchar(64) DEFAULT NULL COMMENT 'SYS/USER',
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `thread`;
CREATE TABLE `thread` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `count` text,
  `category` char(1) DEFAULT NULL COMMENT 'Q-问答，S-分享，D-讨论，A-建议，N-公告，T-动态',
  `reward_kiss_count` int(10) DEFAULT '0',
  `status` char(1) DEFAULT 'N' COMMENT 'N-未结，Y-已结',
  `comment_count` int(10) DEFAULT NULL,
  `is_top` char(1) DEFAULT 'N' COMMENT 'Y-置顶，N-非置顶',
  `is_star` char(1) DEFAULT 'N' COMMENT 'Y-精品，N-非精',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `thread_comment`;
CREATE TABLE `thread_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_thread_id` int(10) NOT NULL,
  `content` text,
  `like_count` int(10) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_accepted` char(1) DEFAULT 'N' COMMENT 'Y-被采纳，N-未被采纳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(128) DEFAULT NULL,
  `avatar_url` varchar(512) DEFAULT NULL COMMENT '头像url',
  `email` varchar(64) NOT NULL,
  `login_pwd` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT 'M',
  `personalized_signature` varchar(2048) DEFAULT NULL,
  `recevied_kiss_count` int(10) DEFAULT '0' COMMENT '收到的飞吻数量',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

ALTER TABLE `user`
ADD COLUMN `location`  varchar(128) NULL AFTER `recevied_kiss_count`;