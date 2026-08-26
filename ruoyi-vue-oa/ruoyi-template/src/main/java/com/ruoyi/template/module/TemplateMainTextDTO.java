package com.ruoyi.template.module;

import com.ruoyi.template.domain.TemplateMainText;
import lombok.Data;

/**
 * @Author wocurr.com
 */
@Data
public class TemplateMainTextDTO extends TemplateMainText {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String extendName;
}
