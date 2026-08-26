package com.ruoyi.file.convert.module;


import lombok.Data;

import java.io.InputStream;

/**
 * 转换文件参数
 */
@Data
public class ConvertFileDTO {
    /**
     * 文件存储类型
     */
    private String storageType;

    /**
     * 源文件输入流
     */
    private InputStream inputStream;

    /**
     * 目标文件地址
     */
    private String targetFileUrl;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 源文件路径
     */
    private String fileUrl;

    /**
     * 文件密码（加密文档）
     */
    private String filePassword;
}
