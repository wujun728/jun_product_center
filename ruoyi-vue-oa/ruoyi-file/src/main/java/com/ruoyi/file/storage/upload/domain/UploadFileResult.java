package com.ruoyi.file.storage.upload.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.file.storage.enums.StorageTypeEnum;
import com.ruoyi.file.storage.enums.UploadFileStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UploadFileResult implements Serializable {

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
     * 扩展属性
     */
    private String extendName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 访问路径
     */
    private String fileUrl;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 完整的文件路径
     */
    private String relativePath;
    /**
     * 唯一标识
     */
    private String identifier;
    /**
     * 文件md5
     */
    private String md5;
    /**
     * 文件排序
     */
    private Integer sort;
    /**
     * 存储类型
     */
    private String storageType;

    @JsonIgnore
    private BufferedImage bufferedImage;

    /**
     * 时间戳
     */
    private String timeStampName;

    /**
     * 跳过上传
     */
    private boolean skipUpload;

    /**
     * 是否需要合并分片
     */
    private boolean needMerge;

    /**
     * 已经上传的分片，格式：[1,2,3]
     */
    private List<Integer> uploaded;

    /**
     * 状态
     */
    private UploadFileStatusEnum status;

}
