package com.ruoyi.file.storage.context;

import com.ruoyi.file.storage.config.FileConfig;
import com.ruoyi.file.storage.copy.Copier;
import com.ruoyi.file.storage.delete.Deleter;
import com.ruoyi.file.storage.download.Downloader;
import com.ruoyi.file.storage.factory.FileServerFactory;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.read.Reader;
import com.ruoyi.file.storage.upload.Uploader;
import com.ruoyi.file.storage.write.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件服务上下文
 * @Author wocurr.com
 */
@Component
public class FileServerContext {
    @Autowired
    private FileConfig config;

    /**
     * 获取文件上传实现
     * @return
     */
    public Uploader getUploader() {
        return FileServerFactory.getInstance().getUploader(config.getStorageType());
    }

    /**
     * 获取文件下载实现
     * @return
     */
    public Downloader getDownloader(String storageType) {
        return FileServerFactory.getInstance().getDownloader(storageType);
    }

    /**
     * 获取文件读取实现
     * @return
     */
    public Reader getReader(String storageType) {
        return FileServerFactory.getInstance().getReader(storageType);
    }

    /**
     * 获取文件写入实现
     * @return
     */
    public Writer getWriter(String storageType) {
        return FileServerFactory.getInstance().getWriter(storageType);
    }

    /**
     * 获取文件复制实现
     * @return
     */
    public Copier getCopier(String storageType) {
        return FileServerFactory.getInstance().getCopier(storageType);
    }

    /**
     * 获取文件删除实现
     * @return
     */
    public Deleter getDeleter(String storageType) {
        return FileServerFactory.getInstance().getDeleter(storageType);
    }

    /**
     * 获取文件预览实现
     * @param storageType
     * @return
     */
    public Previewer getPreviewer(String storageType) {
        return FileServerFactory.getInstance().getPreviewer(storageType);
    }
}
