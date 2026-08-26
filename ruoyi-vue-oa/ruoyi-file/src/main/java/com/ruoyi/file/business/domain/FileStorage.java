package com.ruoyi.file.business.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 文件存储对象 t_file_storage
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileStorage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 文件id
     */
    @Excel(name = "文件id")
    private String fileId;

    /**
     * 文件名
     */
    @Excel(name = "文件名")
    private String fileName;

    /**
     * 路径
     */
    @Excel(name = "路径")
    private String fileUrl;

    /**
     * 大小
     */
    @Excel(name = "大小")
    private Long fileSize;

    /**
     * 文件唯一码
     */
    @Excel(name = "文件唯一码")
    private String identifier;

    /**
     * 存储类型
     */
    @Excel(name = "存储类型")
    private String storageType;

    /**
     * 扩展名
     */
    @Excel(name = "扩展名")
    private String extendName;

    /**
     * 文件md5
     */
    @Excel(name = "文件md5")
    private String md5;

    /**
     * 文件排序
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 文件预览路径，office文档转成pdf做预览件
     */
    private String previewUrl;

    /**
     * 生成预览文件时间
     */
    private LocalDateTime genPreviewTime;

    /**
     * 删除状态，0-否，1-是
     */
    private String delFlag;

    /**
     * 创建人id
     */
    private String createId;

    /**
     * 更新人id
     */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("fileId", getFileId())
                .append("fileName", getFileName())
                .append("filePath", getFileUrl())
                .append("fileSize", getFileSize())
                .append("identifier", getIdentifier())
                .append("storageType", getStorageType())
                .append("ext", getExtendName())
                .append("md5", getMd5())
                .append("delFlag", getDelFlag())
                .append("createTime", getCreateTime())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
