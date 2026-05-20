package com.ruoyi.kbs.domain.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 知识库文档更新对象
 *
 * @author wocurr.com
 */
@Data
public class KbsDocumentBaseUpdateQo {

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

    /**
     * 更新人ID
     */
    private String updateId;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 批量更新ID
     */
    private List<String> ids;
}
