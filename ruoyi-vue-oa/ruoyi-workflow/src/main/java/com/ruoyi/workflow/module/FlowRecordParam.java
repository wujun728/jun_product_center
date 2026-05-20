package com.ruoyi.workflow.module;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wocurr.com
 */
@Data
public class FlowRecordParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 流程实例id */
    private String procInsId;

    /** 流程活动id */
    private String actId;

    /** 当前页 */
    private int pageNum;

    /** 每页条数 */
    private int pageSize;
}
