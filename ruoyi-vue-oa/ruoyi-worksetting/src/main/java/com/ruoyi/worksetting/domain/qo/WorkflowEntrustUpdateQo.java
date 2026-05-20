package com.ruoyi.worksetting.domain.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 流程委托更新对象
 *
 * @author wocurr.com
 */
@Data
public class WorkflowEntrustUpdateQo {

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
     * 主键ID集合
     */
    private String[] ids;
}
