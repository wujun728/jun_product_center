package com.ruoyi.kbs.domain.vo;

import com.ruoyi.kbs.domain.KbsTopicInfo;
import lombok.Data;

/**
 * 知识库主题所有详细信息对象
 *
 * @author wocurr.com
 */
@Data
public class KbsTopicAllInfoVo extends KbsTopicInfo {

    /**
     * 主题管理标识
     */
    private Boolean manageFlag;

    /**
     * 文档数量
     */
    private Long docNums;

    /**
     * 是否已收藏
     */
    private Boolean favoriteFlag;
}
