package com.ruoyi.workflow.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.biz.domain.CommonFlowSubmit;
import com.ruoyi.biz.domain.CommonForm;
import com.ruoyi.biz.service.abs.AbstractBizFlowSubmitData;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 动态表单数据相关业务实现 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class DynamicFlowSubmitDataImpl extends AbstractBizFlowSubmitData {

    @Autowired
    private ISyncPush syncPush;

    @Override
    public String getBizType() {
        return "dynamic";
    }

    @Override
    public void buildBizFlowData(CommonFlowSubmit submit) {
        log.info("动态表单提交");
    }

    @Override
    public void beforeSubmit(CommonFlowSubmit commFlowSubmit) {
        log.info("动态表单提交前处理");
    }

    @Override
    public void afterSubmit(CommonFlowSubmit commFlowSubmit) {
        log.info("动态表单提交后处理");
    }
}
