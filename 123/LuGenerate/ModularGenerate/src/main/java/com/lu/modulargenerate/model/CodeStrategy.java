package com.lu.modulargenerate.model;

/**
 * @program sKnowledge_Blog
 * @description: 代码生成策略
 * @author: zhanglu
 * @create: 2019-07-14 17:11:00
 */
public class CodeStrategy {

    //包路径
    private String packagePath;
    //模块名
    private String module;
    //作者
    private String author;
    //是否覆盖
    private boolean override;
    //项目名
    private String project;
    //表名
    private String[] tableNames;
    //是否去掉前缀
    private String prefix;

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
