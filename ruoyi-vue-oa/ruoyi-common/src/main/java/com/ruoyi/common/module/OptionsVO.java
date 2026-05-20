package com.ruoyi.common.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选项
 * @Author wocurr.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsVO {

    /**
     * 值
     */
    private String value;

    /**
     * 标签
     */
    private String label;
}
