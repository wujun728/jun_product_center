package com.lu.modulargenerate.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 大佬，别偷懒：代码千万行，注释第一行。
 *
 * @Classname: SuperClassConfig
 * @Author: DaZhou
 * @Date: 2019/6/3 14:48
 * @Version: 1.0.0
 * @Description: 生成的父类信息
 * @Modification: ...
 */
@Data
@Accessors(chain = true)
public class SuperClassConfig {

    /**
     * 控制层的父类
     */
    private String controllerSuperClass;

    /**
     * 服务接口父类
     */
    private String serviceSuperClass;

    /**
     * 服务层实现类父类
     */
    private String serviceImplSuperClass;

    /**
     * mapper接口父类
     */
    private String mapperSuperClass;

    /**
     * 实体类的父类
     */
    private String entitySuperClass;

    public String getControllerSuperClass() {
        return controllerSuperClass;
    }

    public void setControllerSuperClass(String controllerSuperClass) {
        this.controllerSuperClass = controllerSuperClass;
    }

    public String getServiceSuperClass() {
        return serviceSuperClass;
    }

    public void setServiceSuperClass(String serviceSuperClass) {
        this.serviceSuperClass = serviceSuperClass;
    }

    public String getServiceImplSuperClass() {
        return serviceImplSuperClass;
    }

    public void setServiceImplSuperClass(String serviceImplSuperClass) {
        this.serviceImplSuperClass = serviceImplSuperClass;
    }

    public String getMapperSuperClass() {
        return mapperSuperClass;
    }

    public void setMapperSuperClass(String mapperSuperClass) {
        this.mapperSuperClass = mapperSuperClass;
    }

    public String getEntitySuperClass() {
        return entitySuperClass;
    }

    public void setEntitySuperClass(String entitySuperClass) {
        this.entitySuperClass = entitySuperClass;
    }


}
