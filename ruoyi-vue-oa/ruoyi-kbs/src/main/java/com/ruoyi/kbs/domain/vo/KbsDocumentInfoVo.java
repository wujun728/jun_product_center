package com.ruoyi.kbs.domain.vo;

import com.ruoyi.kbs.domain.KbsDocumentBase;
import lombok.Data;

/**
 * 知识库文档详情对象
 *
 * @author wocurr.com
 */
@Data
public class KbsDocumentInfoVo extends KbsDocumentBase {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 文档ID
     */
    private String docId;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 路径
     */
    private String url;

    /**
     * 标签
     */
    private String tag;

    /**
     * 封面图片ID
     */
    private String picId;

    /**
     * 摘要
     */
    private String summary;
}
