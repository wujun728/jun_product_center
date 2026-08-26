package com.ruoyi.template.module;

import lombok.Data;

/**
 * <p> 表单下拉项 </p>
 *
 * @Author wocurr.com
 */
@Data
public class FormOption {

    /**
     * 表单ID
     */
    private String formId;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 表单KEY
     */
    private String formKey;
}
