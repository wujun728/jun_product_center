package com.deer.wms.generator2.configurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码的数据库链接设置
 *
 * Created by Floki on 2017/7/31.
 */
public class DatasourceConfigurer {
    /**
     * 生成代码的作者
     */
    private String author;

    private String url;

    private String userName;

    private String password;

    private String diverClassName;

    private String moduleName;

    private String basePackage;

    private String modelPackage;

    private String mapperPackage;

    private String daoPackage;

    private String servicePackage;

    private String serviceImplPackage;

    private String controllerPackage;

    private List<Table> tables = new ArrayList<>();

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiverClassName() {
        return diverClassName;
    }

    public void setDiverClassName(String diverClassName) {
        this.diverClassName = diverClassName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        this.modelPackage = basePackage + ".model";
        this.mapperPackage = basePackage + ".mapper";
        this.daoPackage = basePackage + ".dao";
        this.servicePackage = basePackage + ".service";
        this.serviceImplPackage = this.servicePackage + ".impl";
        this.controllerPackage = basePackage + ".web";
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
