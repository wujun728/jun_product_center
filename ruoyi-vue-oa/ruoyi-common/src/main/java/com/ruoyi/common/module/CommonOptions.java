package com.ruoyi.common.module;

import lombok.Data;

import java.util.List;

/**
 * 通用的下拉选项
 * @Author wocurr.com
 */
@Data
public class CommonOptions {
    /**
     * 值
     */
    private String value;

    /**
     * 标签
     */
    private String label;

    /**
     * 子选项
     */
    private List<OptionsVO> children;

}
