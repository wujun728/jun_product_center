package com.ruoyi.file.storage.copy.domain;

import lombok.Data;

@Data
public class CopyFile {

    private String fileUrl;

    private String extendName;

    private long fizeSize;

    /**
     * 是否为服务端文件复制（如果是，则表示从文件服务器已存在的文件，进行服务）
     */
    private boolean isServerCopy;
}
