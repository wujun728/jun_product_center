package com.ruoyi.kbs.domain.qo;

import lombok.Data;

/**
 * 知识库主题权限对象
 *
 * @author wocurr.com
 */
@Data
public class KbsTopicAuthUserQo {

    /**
     * 主题ID
     */
    private String topicId;

    /**
     * 用户ID
     */
    private String userId;
}
