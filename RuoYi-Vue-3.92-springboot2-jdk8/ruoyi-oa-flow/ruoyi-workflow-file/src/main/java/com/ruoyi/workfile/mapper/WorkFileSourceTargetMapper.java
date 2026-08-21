package com.ruoyi.workfile.mapper;

import com.ruoyi.workfile.domain.WorkflowMainText;
import com.ruoyi.workfile.module.MainInfoResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 对象转换
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface WorkFileSourceTargetMapper {
    WorkFileSourceTargetMapper INSTANCE = Mappers.getMapper(WorkFileSourceTargetMapper.class);

    MainInfoResult toMainInfoResult(WorkflowMainText workflowMainText);
}
