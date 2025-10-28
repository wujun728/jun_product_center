package com.ruoyi.web.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.*;
import java.sql.*;

/**
 * 数据库表结构与实体类同步工具
 * 用于比对数据库表结构与实体类字段差异并同步
 */
@Component
public class TableSyncUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 同步指定实体类与对应数据库表结构
     * @param entityClass 实体类
     * @param tableName 数据库表名
     * @return 同步结果信息
     */
    public Map<String, Object> syncTable(Class<?> entityClass, String tableName) {
        Map<String, Object> result = new HashMap<>();
        List<String> executedSql = new ArrayList<>();
        List<String> errorSql = new ArrayList<>();
        boolean success = true;

        try {
            // 1. 获取实体类字段信息
            Map<String, String> entityFields = getEntityFields(entityClass);
            
            // 2. 获取数据库表结构
            Map<String, String> tableFields = getTableFields(tableName);
            
            // 3. 比对并生成同步SQL
            List<String> syncSqlList = generateSyncSql(tableName, entityFields, tableFields);
            
            // 4. 执行同步SQL
            for (String sql : syncSqlList) {
                try {
                    jdbcTemplate.execute(sql);
                    executedSql.add(sql);
                } catch (Exception e) {
                    errorSql.add("Failed to execute: " + sql + ", Error: " + e.getMessage());
                    success = false;
                }
            }
            
            result.put("success", success);
            result.put("executedSql", executedSql);
            result.put("errorSql", errorSql);
            result.put("message", success ? "表结构同步成功" : "表结构同步部分失败，请检查错误信息");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "同步过程中发生错误: " + e.getMessage());
            errorSql.add(e.getMessage());
            result.put("errorSql", errorSql);
        }
        
        return result;
    }

    /**
     * 批量同步多个表
     * @param tableMappings 表名与实体类的映射关系
     * @return 同步结果列表
     */
    public List<Map<String, Object>> batchSyncTables(Map<String, Class<?>> tableMappings) {
        List<Map<String, Object>> results = new ArrayList<>();
        
        for (Map.Entry<String, Class<?>> entry : tableMappings.entrySet()) {
            String tableName = entry.getKey();
            Class<?> entityClass = entry.getValue();
            
            Map<String, Object> result = syncTable(entityClass, tableName);
            result.put("tableName", tableName);
            result.put("entityClass", entityClass.getName());
            
            results.add(result);
        }
        
        return results;
    }

    /**
     * 获取实体类的字段信息
     * @param entityClass 实体类
     * @return 字段名与类型映射
     */
    private Map<String, String> getEntityFields(Class<?> entityClass) {
        Map<String, String> fields = new HashMap<>();
        Field[] declaredFields = entityClass.getDeclaredFields();
        
        for (Field field : declaredFields) {
            // 跳过静态字段和序列化ID
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) || "serialVersionUID".equals(field.getName())) {
                continue;
            }
            
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            
            // 转换Java类型到MySQL类型
            String mysqlType = convertJavaTypeToMysqlType(fieldType);
            fields.put(fieldName, mysqlType);
        }
        
        // 检查父类，包括BaseEntity
        Class<?> superClass = entityClass.getSuperclass();
        if (superClass != null && !superClass.equals(Object.class)) {
            Map<String, String> superFields = getEntityFields(superClass);
            fields.putAll(superFields);
        }
        
        return fields;
    }

    /**
     * 转换Java类型到MySQL类型
     * @param javaType Java类型名
     * @return MySQL类型名
     */
    private String convertJavaTypeToMysqlType(String javaType) {
        switch (javaType.toLowerCase()) {
            case "string":
                return "VARCHAR(255)";
            case "long":
                return "BIGINT(20)";
            case "integer":
            case "int":
                return "INT(11)";
            case "double":
                return "DOUBLE";
            case "float":
                return "FLOAT";
            case "boolean":
                return "TINYINT(1)";
            case "date":
            case "timestamp":
                return "DATETIME";
            case "byte[]":
                return "BLOB";
            default:
                return "VARCHAR(255)";
        }
    }

    /**
     * 获取数据库表的字段信息
     * @param tableName 表名
     * @return 字段名与类型映射
     */
    private Map<String, String> getTableFields(String tableName) {
        Map<String, String> fields = new HashMap<>();
        
        String sql = "SHOW FULL COLUMNS FROM `" + tableName + "`";
        jdbcTemplate.query(sql, (rs) -> {
            String fieldName = rs.getString("Field");
            String fieldType = rs.getString("Type");
            fields.put(fieldName, fieldType);
        });
        
        return fields;
    }

    /**
     * 生成同步SQL脚本
     * @param tableName 表名
     * @param entityFields 实体类字段
     * @param tableFields 表字段
     * @return SQL脚本列表
     */
    private List<String> generateSyncSql(String tableName, Map<String, String> entityFields, Map<String, String> tableFields) {
        List<String> sqlList = new ArrayList<>();
        
        // 将实体类的驼峰命名转换为下划线命名，并比对数据库表字段
        for (Map.Entry<String, String> entry : entityFields.entrySet()) {
            String javaFieldName = entry.getKey();
            String mysqlFieldName = camelToUnderline(javaFieldName);
            String mysqlType = entry.getValue();
            
            // 检查字段是否存在于数据库表中
            if (!tableFields.containsKey(mysqlFieldName)) {
                // 字段不存在，需要添加
                String addSql = "ALTER TABLE `" + tableName + "` ADD COLUMN `" + mysqlFieldName + "` " + mysqlType + " DEFAULT NULL COMMENT '" + javaFieldName + "';";
                sqlList.add(addSql);
            } else {
                // 字段存在，可以选择是否更新类型
                // 这里可以添加类型比对逻辑，根据需要决定是否更新
            }
        }
        
        return sqlList;
    }

    /**
     * 驼峰命名转下划线命名
     * @param param 驼峰命名字符串
     * @return 下划线命名字符串
     */
    private String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append('_');
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }
}