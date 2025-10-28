# 在线表单模块整合说明

本文件描述了将jun-onlineForm-spring-boot-starter模块整合到jun_admin模块的步骤和注意事项。

## 已完成的整合步骤

1. **添加依赖**：在jun_admin的pom.xml中添加了对jun-onlineForm-spring-boot-starter的依赖

2. **扫描配置**：修改了RuoYiApplication.java，添加了scanBasePackages参数，确保Spring能够扫描到io.github.wujun728.online包

3. **MyBatis配置更新**：更新了mybatis-plus的配置，添加了对onlineForm模块实体类的扫描和DAO文件的支持

## 需要手动执行的操作

1. **数据库表创建**：
   请执行以下SQL脚本创建必要的表：
   ```sql
   -- 创建在线表单表
   CREATE TABLE `online_table` (
     `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
     `comments` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表描述',
     `form_layout` tinyint NULL DEFAULT NULL COMMENT '表单布局',
     `tree` tinyint NULL DEFAULT NULL COMMENT '是否树  0：否   1：是',
     `tree_pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树父id',
     `tree_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '树展示列',
     `table_type` tinyint NULL DEFAULT NULL COMMENT '表类型  0：单表',
     `status` tinyint NULL DEFAULT NULL COMMENT '是否更新  0：否   1：是',
     `version` int NULL DEFAULT NULL COMMENT '版本号',
     `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
     `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
     `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
     `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
     `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`) USING BTREE
   ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Online表单' ROW_FORMAT = Dynamic;
   
   -- 创建在线表单字段表
   CREATE TABLE `online_table_column` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
     `comments` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段描述',
     `length` int NOT NULL COMMENT '字段长度',
     `point_length` int NOT NULL COMMENT '小数点',
     `default_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认值',
     `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段类型',
     `column_pk` tinyint NULL DEFAULT NULL COMMENT '字段主键 0：否  1：是',
     `column_null` tinyint NULL DEFAULT NULL COMMENT '字段为空 0：否  1：是',
     `form_item` tinyint NULL DEFAULT NULL COMMENT '表单项 0：否  1：是',
     `form_required` tinyint NULL DEFAULT NULL COMMENT '表单必填 0：否  1：是',
     `form_input` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单控件',
     `form_default` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单控件默认值',
     `form_dict` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单字典',
     `grid_item` tinyint NULL DEFAULT NULL COMMENT '列表项 0：否  1：是',
     `grid_sort` tinyint NULL DEFAULT NULL COMMENT '列表排序 0：否  1：是',
     `query_item` tinyint NULL DEFAULT NULL COMMENT '查询项 0：否  1：是',
     `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询方式',
     `query_input` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询控件',
     `sort` int NULL DEFAULT NULL COMMENT '排序',
     `table_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表id',
     PRIMARY KEY (`id`) USING BTREE
   ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Online表单字段' ROW_FORMAT = Dynamic;
   ```

2. **静态资源访问**：
   在线表单模块的静态资源位于jun-onlineForm-spring-boot-starter模块的src/main/resources/static/online/目录下
   可以通过以下URL访问这些资源：http://localhost:8080/online/form.html

## 验证方法

1. 启动jun_admin模块
2. 访问在线表单页面：http://localhost:8080/online/form.html
3. 尝试创建和管理表单，验证功能是否正常

## 注意事项

1. 确保数据库连接配置正确，并且具有创建表的权限
2. 如果遇到任何类加载或依赖冲突问题，请检查依赖版本和排除冲突的依赖
3. 如有需要，可以进一步自定义onlineForm模块的配置，通过修改application.yml文件