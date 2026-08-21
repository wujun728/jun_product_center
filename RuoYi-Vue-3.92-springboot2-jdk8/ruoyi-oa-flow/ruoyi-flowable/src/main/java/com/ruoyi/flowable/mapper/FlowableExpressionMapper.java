package com.ruoyi.flowable.mapper;


import com.ruoyi.flowable.domain.FlowableExpression;

import java.util.List;

/**
 * 流程达式Mapper接口
 *
 * @author wocurr.com
 */
public interface FlowableExpressionMapper {
    /**
     * 查询流程达式
     *
     * @param id 流程达式主键
     * @return 流程达式
     */
    public FlowableExpression selectFlowableExpressionById(String id);

    /**
     * 查询流程达式列表
     *
     * @param flowableExpression 流程达式
     * @return 流程达式集合
     */
    public List<FlowableExpression> selectFlowableExpressionList(FlowableExpression flowableExpression);

    /**
     * 新增流程达式
     *
     * @param flowableExpression 流程达式
     * @return 结果
     */
    public int insertFlowableExpression(FlowableExpression flowableExpression);

    /**
     * 修改流程达式
     *
     * @param flowableExpression 流程达式
     * @return 结果
     */
    public int updateFlowableExpression(FlowableExpression flowableExpression);

    /**
     * 删除流程达式
     *
     * @param id 流程达式主键
     * @return 结果
     */
    public int deleteFlowableExpressionById(String id);

    /**
     * 批量删除流程达式
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowableExpressionByIds(String[] ids);
}
