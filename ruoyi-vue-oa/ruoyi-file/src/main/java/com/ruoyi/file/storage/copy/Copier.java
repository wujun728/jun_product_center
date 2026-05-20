package com.ruoyi.file.storage.copy;


import com.ruoyi.file.storage.copy.domain.CopyFile;

import java.io.InputStream;

public abstract class Copier {
    /**
     * 复制文件，返回文件url
     *  1、可以用作文件流的拷贝传输
     *  2、可以用作服务器已存在的原文件复制，需要传递参数isServerCopy
     * @param inputStream 文件流
     * @param copyFile 拷贝文件相关参数
     * @return 文件url
     */
    public abstract String copy(InputStream inputStream, CopyFile copyFile);
}
