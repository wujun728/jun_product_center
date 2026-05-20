package com.ruoyi.file.storage.factory;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.copy.Copier;
import com.ruoyi.file.storage.copy.handler.*;
import com.ruoyi.file.storage.delete.Deleter;
import com.ruoyi.file.storage.delete.handler.*;
import com.ruoyi.file.storage.download.Downloader;
import com.ruoyi.file.storage.download.handler.*;
import com.ruoyi.file.storage.enums.StorageTypeEnum;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.preview.product.*;
import com.ruoyi.file.storage.read.Reader;
import com.ruoyi.file.storage.read.handler.*;
import com.ruoyi.file.storage.upload.Uploader;
import com.ruoyi.file.storage.upload.handler.*;
import com.ruoyi.file.storage.write.Writer;
import com.ruoyi.file.storage.write.handler.*;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件服务工厂
 * @Author wocurr.com
 */
public class FileServerFactory {

    private Map<String, Class<? extends Writer>> writerStrategyMap = new ConcurrentHashMap<>();
    private Map<String, Class<? extends Reader>> readerStrategyMap = new ConcurrentHashMap<>();
    private Map<String, Class<? extends Uploader>> uploaderStrategyMap = new ConcurrentHashMap<>();
    private Map<String, Class<? extends Downloader>> downloaderStrategyMap = new ConcurrentHashMap<>();
    private Map<String, Class<? extends Deleter>> deleterStrategyMap = new ConcurrentHashMap<>();
    private Map<String, Class<? extends Copier>> copyerStrategyMap = new ConcurrentHashMap<>();
    private Map<String, Class<? extends Previewer>> previewerStrategyMap = new ConcurrentHashMap<>();

    /**
     * 扩展实现
     */
    private FileServerFactory() {
        writerStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStorageWriter.class);
        writerStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioWriter.class);

        readerStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStorageReader.class);
        readerStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioReader.class);

        uploaderStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStorageUploader.class);
        uploaderStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioUploader.class);

        downloaderStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStorageDownloader.class);
        downloaderStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioDownloader.class);

        copyerStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStorageCopier.class);
        copyerStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioCopier.class);

        deleterStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStorageDeleter.class);
        deleterStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioDeleter.class);

        previewerStrategyMap.put(StorageTypeEnum.LOCAL.getCode(), LocalStoragePreviewer.class);
        previewerStrategyMap.put(StorageTypeEnum.MINIO.getCode(), MinioPreviewer.class);

    }


    private static class InnerFileServerFactory {
        private final static FileServerFactory fileServerFactory = new FileServerFactory();
    }

    public static FileServerFactory getInstance() {
        return InnerFileServerFactory.fileServerFactory;
    }

    public Uploader getUploader(String type) throws BaseException {
        Class<? extends Uploader> clazz = uploaderStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }

    public Downloader getDownloader(String type) throws BaseException {
        Class<? extends Downloader> clazz = downloaderStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }


    public Reader getReader(String type) throws BaseException {
        Class<? extends Reader> clazz = readerStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }

    public Writer getWriter(String type) throws BaseException {
        Class<? extends Writer> clazz = writerStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }

    public Copier getCopier(String type) throws BaseException {
        Class<? extends Copier> clazz = copyerStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }

    public Deleter getDeleter(String type) throws BaseException {
        Class<? extends Deleter> clazz = deleterStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }

    public Previewer getPreviewer(String type) throws BaseException {
        Class<? extends Previewer> clazz = previewerStrategyMap.get(type);
        if(clazz == null) {
            throw new BaseException("类型不支持");
        }
        return ApplicationContextHelper.getBean(clazz);
    }
}
