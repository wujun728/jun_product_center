package com.ruoyi.flowable.domain.qo;

import lombok.Data;

/**
 * 在途流程
 *
 * @author wocurr.com
 */
@Data
public class FlowUpdateRouteXmlQo {

    /**
     * 流程定义key
     */
    private String procDefKey;

    /**
     * 部署ID
     */
    private String deploymentId;

    /**
     * 流程实例ID
     */
    private String procInstId;
}
