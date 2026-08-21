package com.ruoyi.flowable.el;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.common.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * Flowable表达式抽象类
 *
 * @author wocurr.com
 */
@Slf4j
public abstract class AbstractFlowableSingleExpression implements IFlowableSingleExpression {

    /**
     * 执行表达式
     *
     * @param expressionParam 表达式参数
     * @param targetValue 目标值
     * @return 表达式结果
     */
    @Override
    public Boolean execute(String expressionParam, Object targetValue) {
        if (StringUtils.isBlank(expressionParam)) {
            log.error("Expression param is null");
            throw new NullPointerException("Expression param is null");
        }
        if (expressionParam.startsWith(CommonConstants.LEFT_BRACE) && expressionParam.endsWith(CommonConstants.RIGHT_BRACE)) {
            // 单个JSON对象
            if (expressionParam.contains(CommonConstants.COMMA_BRACE)) {
                // 多个JSON对象
                return executeJSONArray(JSON.parseArray(CommonConstants.LEFT_BRACKET + expressionParam + CommonConstants.RIGHT_BRACKET), targetValue);
            }
            return executeJSONObject(JSON.parseObject(expressionParam), targetValue);
        } else if (expressionParam.startsWith(CommonConstants.LEFT_BRACKET) && expressionParam.endsWith(CommonConstants.RIGHT_BRACKET)) {
            // JSON对象数组
            return executeJSONArray(JSON.parseArray(expressionParam), targetValue);
        }
        return true;
    }

    /**
     * 执行JSON对象表达式
     *
     * @param jsonObject json对象
     * @param targetValue 目标值
     * @return 表达式结果
     */
    public abstract Boolean executeJSONObject(JSONObject jsonObject, Object targetValue);

    /**
     * 执行JSON对象数组表达式
     *
     * @param jsonArray json数组
     * @param targetValue 目标值
     * @return
     */
    protected abstract Boolean executeJSONArray(JSONArray jsonArray, Object targetValue);
}
