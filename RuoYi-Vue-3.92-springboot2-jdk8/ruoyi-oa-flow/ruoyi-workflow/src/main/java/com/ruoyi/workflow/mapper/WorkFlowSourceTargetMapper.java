package com.ruoyi.workflow.mapper;

import com.ruoyi.workfile.domain.WorkflowMainText;
import com.ruoyi.workfile.module.MainInfoResult;
import com.ruoyi.workflow.domain.WorkflowMainSeal;
import com.ruoyi.workflow.module.WorkflowSealResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 对象转换
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface WorkFlowSourceTargetMapper {
    WorkFlowSourceTargetMapper INSTANCE = Mappers.getMapper(WorkFlowSourceTargetMapper.class);

    WorkflowSealResult toWorkflowSealResult(WorkflowMainSeal workflowMainSeal);

    List<WorkflowSealResult> toListWorkflowSealResult(List<WorkflowMainSeal> list);
}
