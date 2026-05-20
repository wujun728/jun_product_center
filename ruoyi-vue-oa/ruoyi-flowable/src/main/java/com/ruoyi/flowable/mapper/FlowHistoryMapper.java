package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.dto.FlowHistoricActivityInstanceDto;
import com.ruoyi.flowable.domain.dto.FlowHistoricProcessInstanceDto;
import com.ruoyi.flowable.domain.dto.FlowIdentityLinkDto;
import com.ruoyi.flowable.domain.qo.FlowHistoricActivityInstanceQo;
import com.ruoyi.flowable.domain.qo.FlowHistoricProcessInstanceQo;
import com.ruoyi.flowable.domain.qo.FlowIdentityLinkQo;

import java.util.List;

/**
 * <p> 流程处理人身份 </p>
 *
 * @Author wocurr.com
 */
public interface FlowHistoryMapper {

    /**
     * 流程审批意见列表
     *
     * @param qo 查询参数
     * @return List<Comment> 流程处理人身份
     */
    List<FlowIdentityLinkDto> selectIdentityLinks(FlowIdentityLinkQo qo);

    /**
     * 历史环节实例列表
     *
     * @param qo 查询参数
     * @return List<FlowHistoricActivityInstanceDto>
     */
    List<FlowHistoricActivityInstanceDto> selectHistoricActivityInstances(FlowHistoricActivityInstanceQo qo);

    /**
     * 历史流程实例列表
     *
     * @param qo 查询参数
     * @return List<FlowHistoricProcessInstanceDto>
     */
    List<FlowHistoricProcessInstanceDto> selectHistoricProcessInstances(FlowHistoricProcessInstanceQo qo);
}
