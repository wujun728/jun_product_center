package com.ruoyi.flowable.domain.qo;

import lombok.Data;

import java.util.List;

/**
 * <p> 流程变量查询实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FlowVariableInstanceQo {

    private String id;

    private String name;

    private List<String> names;

    private String processInstanceId;

    private String executionId;

    private List<String> executionIds;

    private String taskId;

    private List<String> taskIds;

    private Boolean withoutTaskId;

    private String scopeId;

    private List<String> scopeIds;

    private String subScopeId;

    private List<String> subScopeIds;

    private Boolean withoutSubScopeId;

    private String scopeType;

    private List<String> scopeTypes;
}
