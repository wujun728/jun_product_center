package com.ruoyi.file.storage.preview.domain;

import lombok.Data;

@Data
public class PreviewFile {
    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 文件预览地址
     */
    private String previewUrl;

    /**
     * 文件扩展名
     */
    private String extName;

    /**
     * 是否缩略图
     */
    private boolean thumbnail;
}
