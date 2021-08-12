package com.pearadmin.process.domain;

import com.pearadmin.common.web.base.BaseDomain;
import lombok.Data;

/**
 * Describe: 流程定义实体
 * Author: 就眠仪式
 * createTime: 2019/10/23
 * */
@Data
public class ProDefined extends BaseDomain {

    /**
     * 流程定义编号
     * */
    private String id;

    /**
     * 流程定义名称
     * */
    private String name;

    /**
     * 流程定义标识
     * */
    private String key;

    /**
     * 流程定义版本
     * */
    private int version;

    /**
     * xml 资源文件名称
     * */
    private String bpmn;

    /**
     * png 资源文件名称
     * */
    private String png;

    /**
     * 部署 Id
     * */
    private String deploymentId;

}
