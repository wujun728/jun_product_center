package com.ruoyi.file.business.module;

import lombok.Data;

/**
 * 自定义样式（可自行扩展）
 * @Author wocurr.com
 */
@Data
public class FontStyle {
    /**
     * 字体名称
     */
    private String fontName;
    /**
     * 字体大小
     */
    private int fontSize;
    /**
     * 字体颜色
     */
    private String color;
    /**
     * 是否加粗
     */
    private boolean bold;
}
