package com.ruoyi.file.storage.download.domain;

import lombok.Data;
import java.util.List;

@Data
public class DownloadFile {
    /**
     * 文件id（单个）
     */
    private String fileId;

    /**
     * 文件id（批量）
     */
    private List<String> fileIds;

    /**
     * 文件唯一码（单个）
     */
    private String identifier;

    /**
     * 文件路径
     */
    private String fileUrl;

    private Range range;
}
