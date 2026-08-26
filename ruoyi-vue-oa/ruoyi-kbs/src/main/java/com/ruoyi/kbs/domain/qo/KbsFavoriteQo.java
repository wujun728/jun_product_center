package com.ruoyi.kbs.domain.qo;

import lombok.Data;

/**
 * 知识库收藏对象
 *
 * @author wocurr.com
 */
@Data
public class KbsFavoriteQo {

    /**
     * 文档ID
     */
    private String docId;

    /**
     * 用户ID
     */
    private String userId;
}
