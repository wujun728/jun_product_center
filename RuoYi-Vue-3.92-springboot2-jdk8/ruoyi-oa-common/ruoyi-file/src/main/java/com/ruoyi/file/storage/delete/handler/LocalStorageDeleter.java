package com.ruoyi.file.storage.delete.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.delete.Deleter;
import com.ruoyi.file.storage.delete.domain.DeleteFile;
import com.ruoyi.file.storage.util.FileStorageUtils;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 本地文件删除器
 */
@Component
public class LocalStorageDeleter extends Deleter {
    @Override
    public void delete(DeleteFile deleteFile) {
        File localSaveFile = FileStorageUtils.getLocalSaveFile(deleteFile.getFileUrl());
        if (localSaveFile.exists()) {
            boolean result = localSaveFile.delete();
            if (!result) {
                throw new BaseException("删除本地文件失败");
            }
        }
        deleteCacheFile(deleteFile);
    }
}
