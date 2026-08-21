package com.ruoyi.flowable.domain;

import lombok.Data;

/**
 * <p> 流程定义下拉项 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowOption {

    /**
     * 流程定义Key
     */
    private String procDefKey;

    /**
     * 流程定义名称
     */
    private String procDefName;

}
