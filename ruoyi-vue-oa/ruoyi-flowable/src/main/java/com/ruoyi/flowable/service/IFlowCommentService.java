package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.dto.FlowCommentDto;
import com.ruoyi.flowable.domain.qo.FlowCommentQo;

import java.util.List;

/**
 * <p> 流程审批意见接口 </p>
 *
 * @Author wocurr.com
 */
public interface IFlowCommentService {

    /**
     * 流程审批意见列表
     *
     * @param qo 查询参数
     * @return List<FlowCommentDto> 流程审批意见列表
     */
    List<FlowCommentDto> getComments(FlowCommentQo qo);
}
