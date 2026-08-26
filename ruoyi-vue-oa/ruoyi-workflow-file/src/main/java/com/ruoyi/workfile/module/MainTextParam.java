package com.ruoyi.workfile.module;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wocurr.com
 */
@Data
public class MainTextParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 业务id
     */
    private String businessId;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 操作人id
     */
    private String operatorId;
}
