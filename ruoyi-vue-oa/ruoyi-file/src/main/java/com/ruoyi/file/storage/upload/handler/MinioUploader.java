package com.ruoyi.file.storage.upload.handler;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.contants.StorageContants;
import com.ruoyi.file.storage.enums.StorageTypeEnum;
import com.ruoyi.file.storage.enums.UploadFileStatusEnum;
import com.ruoyi.file.storage.upload.Uploader;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileInfo;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import com.ruoyi.file.storage.upload.param.MultipartFileParam;
import com.ruoyi.file.storage.util.FileUtil;
import com.ruoyi.file.storage.util.FileStorageUtils;
import io.minio.*;
import io.minio.messages.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

@Slf4j
@Component
public class MinioUploader extends Uploader {

    @Autowired
    private CustomMinioClient customMinioClient;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public void cancelUpload(UploadFile uploadFile) {
    }

    @Override
    protected void doUploadFileChunk(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        Multimap<String, String> headers = HashMultimap.create();
        InputStream inputStream = multipartFileParam.getUploadInputStream();
        try {
            String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
            String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + SecurityUtils.getUserId() + Constants.COLON + fileHash;
            UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
            String fileUrl = multipartFileParam.getFileUrl();
            if (uploadFileInfo == null) {
                CreateMultipartUploadResponse response = customMinioClient.createMultipartUploadAsync(
                        minioConfig.getBucketName(),
                        null,
                        fileUrl,
                        headers,
                        null
                ).get();
                uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setBucketName(minioConfig.getBucketName());
                uploadFileInfo.setKey(fileUrl);
                uploadFileInfo.setUploadId(response.result().uploadId());
                redisCache.setCacheObject(cacheKey, JSON.toJSONString(uploadFileInfo));
            }
            customMinioClient.uploadPartAsync(
                    uploadFileInfo.getBucketName(),
                    null,
                    uploadFileInfo.getKey(),
                    inputStream,
                    multipartFileParam.getSize(),
                    uploadFileInfo.getUploadId(),
                    uploadFile.getChunkNumber(),
                    headers,
                    null
            ).get();
        } catch (Exception e) {
            log.error("分片上传失败", e);
            throw new BaseException("上传失败");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    @Override
    protected UploadFileResult organizationalResults(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        InputStream inputStream = multipartFileParam.getUploadInputStream();
        String userId = SecurityUtils.getUserId();
        String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
        String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + userId + Constants.COLON + fileHash;
        String currentChunkKey = StorageContants.UPLOAD_CHUNKNUM_LOCK_KEY + userId + Constants.COLON + fileHash;
        try {
            UploadFileResult uploadFileResult = new UploadFileResult();
            UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
            uploadFileResult.setFileUrl(uploadFileInfo.getKey());
            uploadFileResult.setFileName(multipartFileParam.getFileName());
            uploadFileResult.setExtendName(StringUtils.isBlank(multipartFileParam.getExtendName()) ? "" : multipartFileParam.getExtendName().toString());
            long size = uploadFile.getTotalSize();
            if (uploadFile.getTotalChunks() == 1) {
                size = multipartFileParam.getSize();
            }
            uploadFileResult.setFileSize(size);
            uploadFileResult.setStorageType(StorageTypeEnum.MINIO.getCode());
            uploadFileResult.setIdentifier(uploadFile.getIdentifier());
            uploadFileResult.setMd5(FileUtil.getFileMd5(inputStream));
            uploadFileResult.setSort(uploadFile.getSort());
            UploadFileStatusEnum status = UploadFileStatusEnum.UNCOMPLATE;
            if (uploadFile.getChunkNumber() == uploadFile.getTotalChunks()) {
                log.info("分片上传完成，处理合并...");
                completeMultipartUpload(uploadFile);
                status = UploadFileStatusEnum.SUCCESS;
            }
            uploadFileResult.setStatus(status);
            // 图片属性
            if (FileStorageUtils.isImageFile(uploadFileResult.getExtendName())) {
                InputStream in = null;
                try {
                    in = minioClient.getObject(GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(uploadFileResult.getFileUrl()).build());
                    BufferedImage src = ImageIO.read(in);
                    uploadFileResult.setBufferedImage(src);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            return uploadFileResult;
        } catch (Exception e) {
            redisCache.deleteObject(currentChunkKey);
            redisCache.deleteObject(cacheKey);
            log.error("# organizationalResults分片上传失败！", e);
            throw new BaseException("上传失败");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private void completeMultipartUpload(UploadFile uploadFile) {
        String fileHash = Md5Utils.hash(uploadFile.getFilename() + uploadFile.getIdentifier());
        String cacheKey = StorageContants.UPLOAD_PARTRESULT_LOCK_KEY + SecurityUtils.getUserId() + Constants.COLON + fileHash;
        UploadFileInfo uploadFileInfo = JSON.parseObject((String) redisCache.getCacheObject(cacheKey), UploadFileInfo.class);
        Multimap<String, String> headers = HashMultimap.create();
        try {
            ListPartsResponse listPartsResponse = customMinioClient.listPartsAsync(
                    minioConfig.getBucketName(),
                    null,
                    uploadFileInfo.getKey(),
                    uploadFile.getTotalChunks() + 10,
                    0,
                    uploadFileInfo.getUploadId(),
                    headers,
                    null
            ).get();
            Part[] parts = listPartsResponse.result().partList().toArray(new Part[]{});
            customMinioClient.completeMultipartUploadAsync(
                    minioConfig.getBucketName(),
                    null,
                    uploadFileInfo.getKey(),
                    uploadFileInfo.getUploadId(),
                    parts,
                    headers,
                    null
            ).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
