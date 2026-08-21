package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.dto.FlowVariableInstanceDto;
import com.ruoyi.flowable.domain.qo.FlowVariableInstanceQo;

import java.util.List;

/**
 * <p> 流程变量实例 </p>
 *
 * @Author wocurr.com
 */
public interface FlowVariableInstanceMapper {

    /**
     * 流程变量实例列表
     *
     * @param qo 查询参数
     * @return List<FlowVariableInstanceDto>
     */
    List<FlowVariableInstanceDto> selectVariableInstances(FlowVariableInstanceQo qo);
}
