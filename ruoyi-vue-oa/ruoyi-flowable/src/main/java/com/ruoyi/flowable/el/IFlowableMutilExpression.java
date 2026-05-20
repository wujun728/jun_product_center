package com.ruoyi.flowable.el;

/**
 * Flowable表达式接口
 *
 * @author wocurr.com
 */
public interface IFlowableMutilExpression {

    /**
     * 执行表达式
     *
     * @param expressionParam 表达式参数
     * @param targetValues 多个目标值（Object数组）
     * @return 表达式结果
     */
    Boolean execute(String expressionParam, Object... targetValues);
}
