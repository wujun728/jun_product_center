package com.ruoyi.kbs.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 知识库文档统计对象
 *
 * @author wocurr.com
 */
@Data
@Builder
public class KbsDocumentStatVo {

    /**
     * 浏览数
     */
    private Long  viewNum;

    /**
     * 收藏数
     */
    private Long  favoriteNum;

    /**
     * 评论数
     */
    private Long  commentNum;

    /**
     * 是否收藏
     */
    private Boolean isFavorite;
}
