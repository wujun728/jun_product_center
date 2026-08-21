package com.ruoyi.template.module;

import com.ruoyi.template.domain.Template;
import lombok.Data;

import java.util.List;

/**
 * <p> 新启模板模型 </p>
 *
 * @Author wocurr.com
 */
@Data
public class TemplateModel {

    /**
     * 模板类型
     */
    private String type;

    /**
     * 模板类型名称
     */
    private String typeName;

    /**
     * 模板列表
     */
    private List<Template> templates;
}
