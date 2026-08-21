package com.ruoyi.flowable.el;

/**
 * Flowable表达式接口
 *
 * @author wocurr.com
 */
public interface IFlowableSingleExpression {

    /**
     * 执行表达式
     *
     * @param expressionParam 表达式参数
     * @param targetValue 目标值
     * @return 表达式结果
     */
    Boolean execute(String expressionParam, Object targetValue);
}
