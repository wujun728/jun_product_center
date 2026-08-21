package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.dto.FlowProcDefDto;
import com.ruoyi.flowable.domain.qo.FlowProcDefQo;

import java.util.List;

/**
 * 流程定义查询
 *
 * @author wocurr.com
 **/
public interface FlowDeployMapper {

    /**
     * 流程定义列表
     *
     * @param qo 流程名称
     * @return List<FlowProcDefDto> 流程定义列表
     */
    List<FlowProcDefDto> selectDeployList(FlowProcDefQo qo);
}
