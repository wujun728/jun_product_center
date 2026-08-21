package com.ruoyi.flowable.domain.dto;

import lombok.Data;

/**
 * <p> 流程变量实例 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowVariableInstanceDto {

    private String id;

    private String name;

    private String type;

    private String processInstanceId;

    private String executionId;

    private String taskId;

    private String scopeId;

    private String scopeType;

    private String revision;

    private String bytearrayId;

    private String textValue;

    private String textValue2;

    private Double doubleValue;

    private Long longValue;
}
