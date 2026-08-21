package com.ruoyi.biz.domain;

import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.template.domain.Template;
import lombok.Data;

/**
 * <p> 流程提交实体 </p>
 *
 * @Author wocurr.com
 */
@Data
public class CommonFlowSubmit {

    /**
     * 操作类型，提交、驳回、转交、退回等
     */
    private String operateType;

    /**
     * 流程数据
     */
    private FlowTaskVo flowTask;

    /**
     * 模板信息
     */
    private Template template;
}
