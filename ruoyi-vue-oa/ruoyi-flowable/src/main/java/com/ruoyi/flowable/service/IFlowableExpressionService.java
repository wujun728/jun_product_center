package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.FlowableExpression;

import java.util.List;

/**
 * 流程达式Service接口
 *
 * @author wocurr.com
 */
public interface IFlowableExpressionService {
    /**
     * 查询流程达式
     *
     * @param id 流程达式主键
     * @return 流程达式
     */
    FlowableExpression getFlowableExpressionById(String id);

    /**
     * 查询流程达式列表
     *
     * @param flowableExpression 流程达式
     * @return 流程达式集合
     */
    List<FlowableExpression> listFlowableExpression(FlowableExpression flowableExpression);

    /**
     * 新增流程达式
     *
     * @param flowableExpression 流程达式
     * @return 结果
     */
    int saveFlowableExpression(FlowableExpression flowableExpression);

    /**
     * 修改流程达式
     *
     * @param flowableExpression 流程达式
     * @return 结果
     */
    int updateFlowableExpression(FlowableExpression flowableExpression);

    /**
     * 批量删除流程达式
     *
     * @param ids 需要删除的流程达式主键集合
     * @return 结果
     */
    int deleteFlowableExpressionByIds(String[] ids);

    /**
     * 删除流程达式信息
     *
     * @param id 流程达式主键
     * @return 结果
     */
    int deleteFlowableExpressionById(String id);
}
