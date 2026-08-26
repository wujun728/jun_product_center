package com.ruoyi.file.storage.read.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.read.Reader;
import com.ruoyi.file.storage.read.domain.ReadFile;
import com.ruoyi.file.storage.util.CharsetUtils;
import com.ruoyi.file.storage.util.FileStorageUtils;
import com.ruoyi.file.storage.util.ReadFileUtils;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class MinioReader extends Reader {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public String read(ReadFile readFile) {
        String fileUrl = readFile.getFileUrl();
        String fileType = FilenameUtils.getExtension(fileUrl);
        try {
            return ReadFileUtils.getContentByInputStream(fileType, getInputStream(readFile.getFileUrl()));
        } catch (IOException e) {
            throw new BaseException("读取文件失败");
        }
    }

    @Override
    public byte[] readBytes(ReadFile readFile) {
        String fileUrl = readFile.getFileUrl();
        if (StringUtils.isBlank(fileUrl)) {
            return new byte[0];
        }
        InputStream inputStream = null;
        try {
            inputStream = getInputStream(fileUrl);
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return new byte[0];
    }

    public InputStream getInputStream(String fileUrl) {
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileUrl).build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage());
        }
        return inputStream;
    }
}
