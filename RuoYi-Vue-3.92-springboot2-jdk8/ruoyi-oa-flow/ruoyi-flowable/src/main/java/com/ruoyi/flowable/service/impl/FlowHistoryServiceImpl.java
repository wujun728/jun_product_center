package com.ruoyi.flowable.service.impl;

import com.ruoyi.flowable.domain.dto.FlowHistoricActivityInstanceDto;
import com.ruoyi.flowable.domain.dto.FlowHistoricProcessInstanceDto;
import com.ruoyi.flowable.domain.dto.FlowIdentityLinkDto;
import com.ruoyi.flowable.domain.qo.FlowHistoricActivityInstanceQo;
import com.ruoyi.flowable.domain.qo.FlowHistoricProcessInstanceQo;
import com.ruoyi.flowable.domain.qo.FlowIdentityLinkQo;
import com.ruoyi.flowable.mapper.FlowHistoryMapper;
import com.ruoyi.flowable.service.IFlowHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 流程历史接口实现 </p>
 *
 * @Author wocurr.com
 */
@Service
public class FlowHistoryServiceImpl implements IFlowHistoryService {

    @Autowired
    private FlowHistoryMapper flowHistoryMapper;

    @Override
    public List<FlowIdentityLinkDto> getIdentityLinks(FlowIdentityLinkQo qo) {
        return flowHistoryMapper.selectIdentityLinks(qo);
    }

    @Override
    public List<FlowHistoricActivityInstanceDto> getHistoricActivityInstances(FlowHistoricActivityInstanceQo qo) {
        return flowHistoryMapper.selectHistoricActivityInstances(qo);
    }

    @Override
    public List<FlowHistoricProcessInstanceDto> getHistoricProcessInstances(FlowHistoricProcessInstanceQo qo) {
        return flowHistoryMapper.selectHistoricProcessInstances(qo);
    }
}
