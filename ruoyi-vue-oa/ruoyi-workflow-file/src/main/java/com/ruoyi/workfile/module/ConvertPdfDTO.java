package com.ruoyi.workfile.module;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wocurr.com
 */
@Data
public class ConvertPdfDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 正文id
     */
    private String mainTextId;

    /**
     * 业务id
     */
    private String businessId;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 文件存储类型
     */
    private String storageType;

    /**
     * 操作人id
     */
    private String operatorId;
}
