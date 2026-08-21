package com.ruoyi.flowable.domain.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>流程环节<p>
 *
 * @author wocurr.com
 */
@Data
public class FlowActivityVo {

    /**
     * 任务Id
     */
    private String taskId;

    /**
     * 任务Id
     */
    private String taskDefKey;

    /**
     * 流程实例Id
     */
    private String procInsId;

    /**
     * 跳转节点
     */
    private String targetKey;

    /**
     * 部署ID
     */
    private String deploymentId;

    /**
     * 流程环节定义ID
     */
    private String defId;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 流程变量信息
     */
    private Map<String, Object> variables = new HashMap<>();

}
