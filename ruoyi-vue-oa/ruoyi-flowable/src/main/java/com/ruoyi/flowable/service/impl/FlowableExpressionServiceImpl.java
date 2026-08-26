package com.ruoyi.flowable.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.flowable.domain.FlowableExpression;
import com.ruoyi.flowable.mapper.FlowableExpressionMapper;
import com.ruoyi.flowable.service.IFlowableExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程达式Service业务层处理
 *
 * @author wocurr.com
 */
@Service
public class FlowableExpressionServiceImpl implements IFlowableExpressionService {
    @Autowired
    private FlowableExpressionMapper flowableExpressionMapper;

    /**
     * 查询流程达式
     *
     * @param id 流程达式主键
     * @return 流程达式
     */
    @Override
    public FlowableExpression getFlowableExpressionById(String id) {
        return flowableExpressionMapper.selectFlowableExpressionById(id);
    }

    /**
     * 查询流程达式列表
     *
     * @param flowableExpression 流程达式
     * @return 流程达式
     */
    @Override
    public List<FlowableExpression> listFlowableExpression(FlowableExpression flowableExpression) {
        return flowableExpressionMapper.selectFlowableExpressionList(flowableExpression);
    }

    /**
     * 新增流程达式
     *
     * @param flowableExpression 流程达式
     * @return 结果
     */
    @Override
    public int saveFlowableExpression(FlowableExpression flowableExpression) {
        flowableExpression.setId(IdUtils.fastSimpleUUID());
        flowableExpression.setCreateId(SecurityUtils.getUserId());
        flowableExpression.setCreateTime(DateUtils.getNowDate());
        return flowableExpressionMapper.insertFlowableExpression(flowableExpression);
    }

    /**
     * 修改流程达式
     *
     * @param flowableExpression 流程达式
     * @return 结果
     */
    @Override
    public int updateFlowableExpression(FlowableExpression flowableExpression) {
        flowableExpression.setUpdateId(SecurityUtils.getUserId());
        flowableExpression.setUpdateTime(DateUtils.getNowDate());
        return flowableExpressionMapper.updateFlowableExpression(flowableExpression);
    }

    /**
     * 批量删除流程达式
     *
     * @param ids 需要删除的流程达式主键
     * @return 结果
     */
    @Override
    public int deleteFlowableExpressionByIds(String[] ids) {
        return flowableExpressionMapper.deleteFlowableExpressionByIds(ids);
    }

    /**
     * 删除流程达式信息
     *
     * @param id 流程达式主键
     * @return 结果
     */
    @Override
    public int deleteFlowableExpressionById(String id) {
        return flowableExpressionMapper.deleteFlowableExpressionById(id);
    }
}
