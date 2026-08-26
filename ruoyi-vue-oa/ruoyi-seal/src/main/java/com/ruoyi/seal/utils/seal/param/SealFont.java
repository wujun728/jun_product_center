package com.ruoyi.seal.utils.seal.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * 印章字体类
 *
 * @Author wocurr.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SealFont {
    /**
     * 字体内容
     */
    private String fontText;
    /**
     * 是否加粗
     */
    private Boolean isBold = true;
    /**
     * 字形名，默认为宋体
     */
    private String fontFamily = "宋体";
    /**
     * 字体大小
     */
    private Integer fontSize;
    /**
     * 字距
     */
    private Double fontSpace;
    /**
     * 边距（环边距或上边距）
     */
    private Integer marginSize;

    /**
     * 获取系统支持的字形名集合
     */
    public static String[] getSupportFontNames() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
}
