package com.ruoyi.workflow.el;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.flowable.el.AbstractFlowableSingleExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 简单表达式对象
 * <p>
 *     TODO: 可参考 RoleFlowableSingleExpression，PostFlowableSingleExpression 自定义扩展
 * </p>
 *
 * @author wocurr.com
 */
@Slf4j
@Component
public class SimpleFlowableSingleExpression extends AbstractFlowableSingleExpression {
    @Override
    public Boolean executeJSONObject(JSONObject jsonObject, Object targetValue) {
        // TODO: 自定义扩展
        return true;
    }

    @Override
    protected Boolean executeJSONArray(JSONArray jsonArray, Object targetValue) {
        // TODO: 自定义扩展
        return true;
    }
}
