package com.ruoyi.biz.service.impl;

import com.ruoyi.biz.domain.CommonFlowSubmit;
import com.ruoyi.biz.factory.BizFlowSubmitFactory;
import com.ruoyi.biz.service.IBizFLowService;
import com.ruoyi.biz.service.IBizFLowSubmitService;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p> 业务流程接口实现类 </p>
 *
 * @Author wocurr.com
 */
@Service
public class BizFLowServiceImpl implements IBizFLowService {

    @Autowired
    private BizFlowSubmitFactory bizFlowSubmitFactory;
    @Autowired
    private ITemplateService templateService;

    @Override
    public void submit(CommonFlowSubmit submit) {
        if (Objects.isNull(submit.getFlowTask()) || StringUtils.isBlank(submit.getFlowTask().getTemplateId())) {
            throw new BaseException("模板ID为空");
        }
        Template template = templateService.getTemplateById(submit.getFlowTask().getTemplateId());
        if (Objects.isNull(template)) {
            throw new BaseException("未找到模板");
        }
        if (StringUtils.isBlank(template.getType())) {
            throw new BaseException("模板类型为空");
        }
        if (StringUtils.isBlank(template.getFormCode())) {
            throw new BaseException("表单编码为空");
        }
        IBizFLowSubmitService bizFLowSubmitImpl = bizFlowSubmitFactory.getBizFLowSubmitImplByType(template.getFormCode());
        if (Objects.isNull(bizFLowSubmitImpl)) {
            throw new BaseException("未找到业务流程实现类");
        }
        submit.setTemplate(template);
        bizFLowSubmitImpl.submit(submit);
    }
}
