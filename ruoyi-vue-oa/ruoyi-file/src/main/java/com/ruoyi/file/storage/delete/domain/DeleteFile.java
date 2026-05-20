package com.ruoyi.file.storage.delete.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteFile implements Serializable {

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
     * 文件路径（相对路径）
     */
    private String fileUrl;
}
