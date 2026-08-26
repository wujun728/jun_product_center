package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.dto.FlowHistoricActivityInstanceDto;
import com.ruoyi.flowable.domain.dto.FlowHistoricProcessInstanceDto;
import com.ruoyi.flowable.domain.dto.FlowIdentityLinkDto;
import com.ruoyi.flowable.domain.qo.FlowHistoricActivityInstanceQo;
import com.ruoyi.flowable.domain.qo.FlowHistoricProcessInstanceQo;
import com.ruoyi.flowable.domain.qo.FlowIdentityLinkQo;

import java.util.List;

/**
 * <p> 流程历史接口 </p>
 *
 * @Author wocurr.com
 */
public interface IFlowHistoryService {

    /**
     * 流程审批意见列表
     *
     * @param qo 查询参数
     * @return List<Comment>
     */
    List<FlowIdentityLinkDto> getIdentityLinks(FlowIdentityLinkQo qo);

    /**
     * 历史环节实例列表
     *
     * @param qo 查询参数
     * @return List<FlowHistoricActivityInstanceDto>
     */
    List<FlowHistoricActivityInstanceDto> getHistoricActivityInstances(FlowHistoricActivityInstanceQo qo);

    /**
     * 历史流程实例列表
     *
     * @param qo 查询参数
     * @return List<FlowHistoricProcessInstanceDto>
     */
    List<FlowHistoricProcessInstanceDto> getHistoricProcessInstances(FlowHistoricProcessInstanceQo qo);
}
