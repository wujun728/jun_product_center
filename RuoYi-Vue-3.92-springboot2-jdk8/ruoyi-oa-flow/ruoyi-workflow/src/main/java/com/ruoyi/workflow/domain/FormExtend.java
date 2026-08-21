package com.ruoyi.workflow.domain;

import lombok.Data;

import java.util.Map;

/**
 * 流程表单扩展对象
 *
 * @author wocurr.com
 */
@Data
public class FormExtend extends Form {

    /**
     * 字段值
     */
    private Map<String, Object> valData;
}
