package com.ruoyi.file.business.module;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件详情
 */
@Data
public class FileDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;


    /**
     * 文件大小
     */
    private Long fileSize;

    private String timeStampName;
    /**
     * 存储方式
     */
    private Integer storageType;

    /**
     * 唯一码值
     */
    private String identifier;

    private String userId;

    private String filePath;

    /**
     * 扩展名
     */
    private String extendName;

    private Integer isDir;

    private String uploadTime;

    private Integer deleteFlag;

    private String deleteTime;

    private String deleteBatchNum;

//    private Image image;
//
//    private Music music;
}
