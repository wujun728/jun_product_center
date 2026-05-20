package com.ruoyi.flowable.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wocurr.com
 */
@Data
public class FlowRecordFilterDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 流程环节id
     */
    private String actId;

    /**
     * 流程环节名称
     */
    private String actName;
}
