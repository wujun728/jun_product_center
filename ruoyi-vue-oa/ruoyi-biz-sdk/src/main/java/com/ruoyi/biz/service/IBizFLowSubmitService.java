package com.ruoyi.biz.service;

import com.ruoyi.biz.domain.CommonFlowSubmit;

/**
 * <p> 业务流程接口 </p>
 *
 * @Author wocurr.com
 */
public interface IBizFLowSubmitService{

    String getBizType();

    void buildBizFlowData(CommonFlowSubmit submit);

    void submit(CommonFlowSubmit commFlowSubmit);

    void beforeSubmit(CommonFlowSubmit commFlowSubmit);

    void afterSubmit(CommonFlowSubmit commFlowSubmit);
}
