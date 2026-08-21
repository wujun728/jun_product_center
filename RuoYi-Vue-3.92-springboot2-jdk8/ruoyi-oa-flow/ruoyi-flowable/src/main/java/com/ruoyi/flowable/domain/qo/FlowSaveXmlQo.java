package com.ruoyi.flowable.domain.qo;

import lombok.Data;

import java.io.Serializable;

/**
 * 流程定义xml
 *
 * @author wocurr.com
 */
@Data
public class FlowSaveXmlQo implements Serializable {

    /**
     * 流程定义key
     */
    private String procDefKey;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程分类
     */
    private String category;

    /**
     * xml 文件
     */
    private String xml;
}
