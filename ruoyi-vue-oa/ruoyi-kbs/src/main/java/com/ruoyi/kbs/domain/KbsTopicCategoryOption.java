package com.ruoyi.kbs.domain;

import lombok.Data;

/**
 * <p> 主题类别下拉项 </p>
 *
 * @Author wocurr.com
 */
@Data
public class KbsTopicCategoryOption {

    /**
     * 类别ID
     */
    private String categoryId;

    /**
     * 类别名称
     */
    private String categoryName;
}
