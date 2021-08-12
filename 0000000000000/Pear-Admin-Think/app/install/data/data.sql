SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `{{$pk}}admin_admin`;
CREATE TABLE `{{$pk}}admin_admin` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，登陆使用',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1正常,2禁用 默认1',
  `token` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'token',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='管理表';

DROP TABLE IF EXISTS `{{$pk}}admin_admin_role`;
CREATE TABLE `{{$pk}}admin_admin_role` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `admin_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='管理-角色中间表';

DROP TABLE IF EXISTS `{{$pk}}admin_admin_log`;
CREATE TABLE `{{$pk}}admin_admin_log` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` int(11) DEFAULT NULL COMMENT '管理员ID',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '操作页面',
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '日志内容',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT '操作IP',
  `user_agent` text NOT NULL COMMENT 'User-Agent',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='管理员日志';

DROP TABLE IF EXISTS `{{$pk}}admin_admin_permission`;
CREATE TABLE `{{$pk}}admin_admin_permission` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `admin_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='管理-权限中间表';

DROP TABLE IF EXISTS `{{$pk}}admin_permission`;
CREATE TABLE `{{$pk}}admin_permission` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父级ID',
  `title` varchar(50) DEFAULT NULL COMMENT '名称',
  `href` varchar(50) NOT NULL COMMENT '地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort` tinyint(4) NOT NULL DEFAULT '99' COMMENT '排序',
  `type` tinyint(1) DEFAULT '1' COMMENT '菜单',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
   PRIMARY KEY (`id`),
   KEY `pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 COMMENT='权限表';

INSERT INTO `{{$pk}}admin_permission` (`id`, `pid`, `title`, `href`, `icon`, `sort`, `type`, `status`) VALUES
(1, 0, '后台权限', '', 'layui-icon layui-icon-username', 2, 0, 1),
(2, 1, '管理员', '/admin.admin/index', '', 1, 1, 1),
(3, 2, '新增管理员', '/admin.admin/add', '', 1, 1, 1),
(4, 2, '编辑管理员', '/admin.admin/edit', '', 1, 1, 1),
(5, 2, '修改管理员状态', '/admin.admin/status', '', 1, 1, 1),
(6, 2, '删除管理员', '/admin.admin/remove', '', 1, 1, 1),
(7, 2, '批量删除管理员', '/admin.admin/batchRemove', '', 1, 1, 1),
(8, 2, '管理员分配角色', '/admin.admin/role', '', 1, 1, 1),
(9, 2, '管理员分配直接权限', '/admin.admin/permission', '', 1, 1, 1),
(10, 2, '管理员回收站', '/admin.admin/recycle', '', 1, 1, 1),
(11, 1, '角色管理', '/admin.role/index', '', 99, 1, 1),
(12, 11, '新增角色', '/admin.role/add', '', 99, 1, 1),
(13, 11, '编辑角色', '/admin.role/edit', '', 99, 1, 1),
(14, 11, '删除角色', '/admin.role/remove', '', 99, 1, 1),
(15, 11, '角色分配权限', '/admin.role/permission', '', 99, 1, 1),
(16, 11, '角色回收站', '/admin.role/recycle', '', 99, 1, 1),
(17, 1, '菜单权限', '/admin.permission/index', '', 99, 1, 1),
(18, 17, '新增菜单', '/admin.permission/add', '', 99, 1, 1),
(19, 17, '编辑菜单', '/admin.permission/edit', '', 99, 1, 1),
(20, 17, '修改菜单状态', '/admin.permission/status', '', 99, 1, 1),
(21, 17, '删除菜单', '/admin.permission/remove', '', 99, 1, 1),
(22, 0, '系统管理', '', 'layui-icon layui-icon-set', 3, 0, 1),
(23, 22, '后台日志', '/admin.admin/log', '', 2, 1, 1),
(24, 23, '清空管理员日志', '/admin.admin/removeLog', '', 1, 1, 1),
(25, 22, '系统设置', '/config/index', '', 1, 1, 1),
(26, 22, '图片管理', '/admin.photo/index', '', 2, 1, 1),
(27, 26, '新增图片文件夹', '/admin.photo/add', '', 2, 1, 1),
(28, 26, '删除图片文件夹', '/admin.photo/del', '', 2, 1, 1),
(29, 26, '图片列表', '/admin.photo/list', '', 2, 1, 1),
(30, 26, '添加单图', '/admin.photo/addPhoto', '', 2, 1, 1),
(31, 26, '添加多图', '/admin.photo/addPhotos', '', 2, 1, 1),
(32, 26, '删除图片', '/admin.photo/remove', '', 2, 1, 1),
(33, 26, '批量删除图片', '/admin.photo/batchRemove', '', 2, 1, 1);
DROP TABLE IF EXISTS `{{$pk}}admin_role`;
CREATE TABLE `{{$pk}}admin_role` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 COMMENT='角色表';

DROP TABLE IF EXISTS `{{$pk}}admin_role_permission`;
CREATE TABLE `{{$pk}}admin_role_permission` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='角色-权限中间表';

INSERT INTO `{{$pk}}admin_role` (`id`, `name`, `desc`, `create_time`, `update_time`, `delete_time`) VALUES
(1, '超级管理员', '拥有所有管理权限', '2020-09-01 11:01:34', '2020-09-01 11:01:34', NULL);

DROP TABLE IF EXISTS `{{$pk}}admin_photo`;
CREATE TABLE `{{$pk}}admin_photo` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) NOT NULL COMMENT '文件名称',
  `href` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `path` varchar(30) DEFAULT NULL COMMENT '路径',
  `mime` varchar(50) NOT NULL COMMENT 'mime类型',
  `size` varchar(30) NOT NULL COMMENT '大小',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1本地2阿里云',
  `ext` varchar(10) DEFAULT NULL COMMENT '文件后缀',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 COMMENT='图片表';
