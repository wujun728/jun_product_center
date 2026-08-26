package com.ruoyi.kbs.domain;

import lombok.Data;

import java.util.List;


/**
 * <p> 文档模型 </p>
 *
 * @Author wocurr.com
 */
@Data
public class KbsDocumentModel {

    /**
     * 文档ID
     */
    private String id;

    /**
     * 主题分类名称
     */
    private String name;

    /**
     * 文档类型，1-文档，2-表格，3-画板，4-数据表
     */
    private String type;

    /**
     * 文档排序
     */
    private Integer sort;

    /**
     * 主题ID
     */
    private String topicId;

    /**
     * 父文档ID
     */
    private String parentId;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 更新人ID
     */
    private String updateId;

    /**
     * 文档集合
     */
    private List<KbsDocumentModel> children;
}
