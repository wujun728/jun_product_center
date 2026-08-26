package com.ruoyi.worksetting.domain.qo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 流程办理委托查询对象
 *
 * @author wocurr.com
 */
@Data
public class WorkflowEntrustQo {

    /**
     * 委托人ID
     */
    private List<String> entrustIds;

    /**
     * 被委托人ID
     */
    private List<String> beEntrustIds;

    /**
     * 当前日期
     */
    private Date currentDate;

    /**
     * 委托方式，0-全部， 1-部分
     */
    private String type;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 模板id
     */
    private List<String> templateIds;
}
