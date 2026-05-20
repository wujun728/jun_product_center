package com.ruoyi.workflow.el;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.flowable.el.AbstractFlowableMutilExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 简单表达式对象（多个目标值）
 * <p>
 *     校验表达式对象是否符合条件要求
 *     eg: ${simpleFlowableMutilExpression(jsonObject, targetValue1, targetValue2, ...)}
 * </p>
 *
 * @author wocurr.com
 */
@Slf4j
@Component
public class SimpleFlowableMutilExpression extends AbstractFlowableMutilExpression {
    @Override
    public Boolean executeJSONObject(JSONObject jsonObject, Object... targetValues) {
        // TODO: 自定义扩展
        return true;
    }

    @Override
    protected Boolean executeJSONArray(JSONArray jsonArray, Object... targetValues) {
        // TODO: 自定义扩展
        return true;
    }
}
