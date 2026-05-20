package com.ruoyi.flowable.service.impl;

import com.ruoyi.flowable.domain.dto.FlowCommentDto;
import com.ruoyi.flowable.domain.qo.FlowCommentQo;
import com.ruoyi.flowable.mapper.FlowCommentMapper;
import com.ruoyi.flowable.service.IFlowCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 流程审批意见接口实现 </p>
 *
 * @Author wocurr.com
 */
@Service
public class FlowCommentServiceImpl implements IFlowCommentService {

    @Autowired
    private FlowCommentMapper flowCommentMapper;

    @Override
    public List<FlowCommentDto> getComments(FlowCommentQo qo) {
        return flowCommentMapper.selectCommentList(qo);
    }
}
