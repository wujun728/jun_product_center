package com.ruoyi.file.storage.delete.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.delete.Deleter;
import com.ruoyi.file.storage.delete.domain.DeleteFile;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio删除文件实现
 */
@Slf4j
@Component
public class MinioDeleter extends Deleter {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public void delete(DeleteFile deleteFile) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioConfig.getBucketName()).object(deleteFile.getFileUrl()).build());
        } catch (MinioException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("Minio删除文件失败");
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new BaseException("Minio删除文件失败");
        }
        deleteCacheFile(deleteFile);
    }
}
