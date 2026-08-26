package com.ruoyi.file.business.module;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文件传输对象
 * @Author wocurr.com
 */
@Data
public class FileQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件唯一码
     */
    private String identifier;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String extendName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 是否目录
     */
    private boolean isDir;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否缩略图
     */
    private boolean thumbnail;

    /**
     * 文件数组
     */
    private List<FileQO> fileList;

    /**
     * 文件唯一码（批量）
     */
    private List<String> identifiers;

    /**
     * 文件id（批量）
     */
    private List<String> fileIds;

    /**
     * 下载类型，0-文件数组，1-文件id数组
     */
    private String downType;

    /**
     * 是否预览文件
     */
    private boolean usePreviewUrl;

}
