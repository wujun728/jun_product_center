package com.ruoyi.kbs.domain.qo;

import com.ruoyi.kbs.domain.KbsDocumentModel;
import lombok.Data;

import java.util.List;

/**
 * 知识库文档基本对象
 *
 * @author wocurr.com
 */
@Data
public class KbsDocumentBaseQo {

    /**
     * 文档名称
     */
    private String name;

    /**
     * 文档类型，1-文档，2-表格，3-画板，4-数据表
     */
    private String type;

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
     * 文档列表
     */
    private List<KbsDocumentModel> documents;
}
