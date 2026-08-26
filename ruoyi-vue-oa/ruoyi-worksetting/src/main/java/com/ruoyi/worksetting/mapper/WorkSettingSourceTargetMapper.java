package com.ruoyi.worksetting.mapper;

import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import com.ruoyi.worksetting.domain.vo.WorkflowEntrustVO;
import com.ruoyi.worksetting.domain.vo.WorkflowSecretaryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 转换实体
 *
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface WorkSettingSourceTargetMapper {
    WorkSettingSourceTargetMapper INSTANCE = Mappers.getMapper(WorkSettingSourceTargetMapper.class);

    WorkflowEntrust entrustVo2entrust(WorkflowEntrustVO workflowEntrustVo);

    WorkflowEntrustVO entrust2entrustVo(WorkflowEntrust entrust);

    WorkflowSecretaryVO secretary2secretaryVO(WorkflowSecretary secretary);

}
