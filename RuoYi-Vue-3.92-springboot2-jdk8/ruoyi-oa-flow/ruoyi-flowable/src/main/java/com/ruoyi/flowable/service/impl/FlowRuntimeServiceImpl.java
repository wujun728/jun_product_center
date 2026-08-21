package com.ruoyi.flowable.service.impl;

import com.ruoyi.flowable.domain.dto.FlowProcessInstanceDto;
import com.ruoyi.flowable.domain.qo.FlowProcessInstanceQo;
import com.ruoyi.flowable.mapper.FlowProcessInstanceMapper;
import com.ruoyi.flowable.service.IFlowRuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 流程实例接口实现 </p>
 *
 * @Author wocurr.com
 */
@Service
public class FlowRuntimeServiceImpl implements IFlowRuntimeService {

    @Autowired
    private FlowProcessInstanceMapper flowProcessInstanceMapper;

    /**
     * 获取流程实例列表
     *
     * @param qo
     * @return
     */
    @Override
    public List<FlowProcessInstanceDto> getProcessInstances(FlowProcessInstanceQo qo) {
        return flowProcessInstanceMapper.selectProcessInstances(qo);
    }
}
