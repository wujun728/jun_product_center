package com.ruoyi.kbs.domain;

import com.ruoyi.kbs.domain.vo.KbsTopicInfoVo;
import lombok.Data;

import java.util.List;


/**
 * <p> 主题模型 </p>
 *
 * @Author wocurr.com
 */
@Data
public class KbsTopicModel {

    /**
     * 主题分类ID
     */
    private String categoryId;

    /**
     * 主题分类名称
     */
    private String categoryName;

    /**
     * 主题创建人
     */
    private String createId;

    /**
     * 主题集合
     */
    private List<KbsTopicInfoVo> topicInfos;
}
