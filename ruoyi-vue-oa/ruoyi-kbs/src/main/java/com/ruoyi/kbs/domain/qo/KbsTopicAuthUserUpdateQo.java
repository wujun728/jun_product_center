package com.ruoyi.kbs.domain.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 知识库文档模板更新对象
 *
 * @author wocurr.com
 */
@Data
public class KbsTopicAuthUserUpdateQo {

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

    /**
     * 更新人ID
     */
    private String updateId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 主题ID集合
     */
    private String[] topicIds;
}
