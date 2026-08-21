package com.ruoyi.file.storage.upload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否分片
     */
    private boolean chunkFlag;

    /**
     * 文件，普通上传（例如element-ui的upload组件）
     */
    private MultipartFile multipartFile;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件扩展名
     */
    private String extendName;

    /**
     * 文件字节数组
     */
    private byte[] fileBytes;

    /**
     * 切片数量
     */
    private int chunkNumber;

    /**
     * 切片大小
     */
    private long chunkSize;

    /**
     * 相对路径
     */
    private String relativePath;

    /**
     * 所有切片
     */
    private int totalChunks;

    /**
     * 总大小
     */
    private long totalSize;

    /**
     * 当前切片大小
     */
    private long currentChunkSize;

    /**
     * 文件的唯一标识
     */
    private String identifier;

    /**
     * 文件排序
     */
    private Integer sort;
}
