package com.jun.plugin.generator.code;

import com.jun.plugin.generator.utils.GenUtils;
import org.apache.commons.lang.WordUtils;

import java.util.List;
import java.util.Objects;

/**
 * 数据库表对象
 */
public class TableInfo {

    /**
     * 数据库表名字 t_fifle
     */
    private String tableName;

    /**
     * java表名字例如    SysOperLog
     */
    private String javaTableName;

    /**
     * 数据表注释 例如文件管理系统
     */
    private String tableComment;

    /**
     * java表名字例如    sysOperLog
     */
    private String javaTableName_a;

    /**
     * 字段集合
     */
    List<BeanColumn> beanColumns;

    /**
     * Entity基类字段
     */
    public static final String[] BASE_ENTITY = {"createBy" , "createTime" , "updateBy" , "updateTime" , "remark"};

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getJavaTableName() {
        return javaTableName;
    }

    public void setJavaTableName(String javaTableName) {
        this.javaTableName = javaTableName;
    }

    public List<BeanColumn> getBeanColumns() {
        return beanColumns;
    }

    public void setBeanColumns(List<BeanColumn> beanColumns) {
        this.beanColumns = beanColumns;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getJavaTableName_a() {
        return javaTableName_a;
    }

    public void setJavaTableName_a(String javaTableName_a) {
        this.javaTableName_a = javaTableName_a;
    }

    public TableInfo(String tableName, List<BeanColumn> beanColumns, String tableComment) {
        super();
        this.tableName = tableName;
        this.javaTableName = tableToJava(tableName);
        this.beanColumns = beanColumns;
        this.tableComment = tableComment;
        this.javaTableName_a = tableToJava_a(tableName);
    }

    public TableInfo() {
        super();
    }

    /**
     * 列名转换成Java属性名
     *
     * @param columnName
     * @return
     */
    public String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_" , "");
    }

    /**
     * 表名转换成Java类名
     *
     * @param tableName
     * @return
     */
    public String tableToJava(String tableName) {
        String tablePrefix = Objects.requireNonNull(GenUtils.getConfig().getString("tablePrefix"));
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replaceFirst(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 首字母小写
     *
     * @param tableName
     * @return
     */
    public String tableToJava_a(String tableName) {
        String str = tableToJava(tableName);
        return StringUtils.firstLowerCase(str);
    }

    /**
     *
     *
     * @param javaField
     * @return
     */
    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField, BASE_ENTITY);
    }
}
