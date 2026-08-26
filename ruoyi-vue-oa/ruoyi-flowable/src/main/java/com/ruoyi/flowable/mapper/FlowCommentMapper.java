package com.ruoyi.flowable.mapper;

import com.ruoyi.flowable.domain.dto.FlowCommentDto;
import com.ruoyi.flowable.domain.qo.FlowCommentQo;

import java.util.List;

/**
 * 流程审批意见查询
 *
 * @author wocurr.com
 **/
public interface FlowCommentMapper {

    /**
     * 流程审批意见列表
     *
     * @param qo 流程名称
     * @return List<Comment> 流程定义列表
     */
    List<FlowCommentDto> selectCommentList(FlowCommentQo qo);
}
