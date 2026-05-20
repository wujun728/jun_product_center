package com.ruoyi.kbs.domain.qo;

import lombok.Data;

/**
 * 知识库主题对象
 *
 * @author wocurr.com
 */
@Data
public class KbsTopicInfoQo {

    /**
     * 主题名称
     */
    private String topicName;

    /**
     * 类别ID
     */
    private String categoryId;

    /**
     * 类别名称
     */
    private String categoryName;
}
