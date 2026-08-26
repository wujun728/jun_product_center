package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.dto.FlowProcessInstanceDto;
import com.ruoyi.flowable.domain.qo.FlowProcessInstanceQo;

import java.util.List;

/**
 * <p> 流程实例映射 </p>
 *
 * @Author wocurr.com
 */
public interface FlowProcessInstanceMapper {

    /**
     * 获取流程实例列表
     *
     * @param qo
     * @return List<FlowProcessInstanceDto>
     */
    List<FlowProcessInstanceDto> selectProcessInstances(FlowProcessInstanceQo qo);
}
