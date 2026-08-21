package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.dto.FlowProcessInstanceDto;
import com.ruoyi.flowable.domain.qo.FlowProcessInstanceQo;

import java.util.List;

/**
 * <p> 流程运行接口 </p>
 *
 * @Author wocurr.com
 */
public interface IFlowRuntimeService {

    /**
     * 获取流程实例列表
     *
     * @param qo
     * @return List<FlowProcessInstanceDto>
     */
    public List<FlowProcessInstanceDto> getProcessInstances(FlowProcessInstanceQo qo);
}
