package com.ruoyi.biz.domain;

import lombok.Data;

/**
 * <p> 业务按钮 </p>
 *
 * @Author wocurr.com
 */
@Data
public class BizButton {

    /**
     * 按钮code
     */
    private String code;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 是否显示
     */
    private Boolean showFlag;

    /**
     * 按钮排序
     */
    private Integer sort;

}
