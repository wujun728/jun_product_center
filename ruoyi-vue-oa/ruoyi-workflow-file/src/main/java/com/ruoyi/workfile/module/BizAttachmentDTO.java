package com.ruoyi.workfile.module;

import lombok.Data;

import java.io.Serializable;

/**
 * 流程业务附件
 * @Author wocurr.com
 */
@Data
public class BizAttachmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String fileExt;

    /**
     * 文件标识
     */
    private String identifier;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 排序
     */
    private int sort;
}
