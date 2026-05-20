package com.ruoyi.file.storage.upload.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.file.storage.enums.StorageTypeEnum;
import com.ruoyi.file.storage.enums.UploadFileStatusEnum;
import com.ruoyi.file.storage.upload.Uploader;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import com.ruoyi.file.storage.upload.param.MultipartFileParam;
import com.ruoyi.file.storage.util.FileUtil;
import com.ruoyi.file.storage.util.FileStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LocalStorageUploader extends Uploader {

    public static Map<String, String> FILE_URL_MAP = new HashMap<>();

    protected UploadFileResult doUploadFlow(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        UploadFileResult uploadFileResult = new UploadFileResult();
        try {
            String fileUrl = FileStorageUtils.getUploadFileUrl(uploadFile.getIdentifier(), multipartFileParam.getExtendName());
            if (FILE_URL_MAP.containsKey(uploadFile.getIdentifier())) {
                fileUrl = FILE_URL_MAP.get(uploadFile.getIdentifier());
            } else {
                FILE_URL_MAP.put(uploadFile.getIdentifier(), fileUrl);
            }
            String tempFileUrl = fileUrl + "_tmp";
            String confFileUrl = fileUrl.replace("." + multipartFileParam.getExtendName(), ".conf");
            File file = new File(FileStorageUtils.getDataPath() + fileUrl);
            File tempFile = new File(FileStorageUtils.getDataPath() + tempFileUrl);
            File confFile = new File(FileStorageUtils.getDataPath() + confFileUrl);

            RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
            try {
                FileChannel fileChannel = raf.getChannel();
                // 计算偏移量
                long position = (uploadFile.getChunkNumber() - 1) * uploadFile.getChunkSize();
                //获取分片数据
                byte[] fileData = multipartFileParam.getUploadBytes();
                fileChannel.position(position);
                fileChannel.write(ByteBuffer.wrap(fileData));
                fileChannel.force(true);
                fileChannel.close();
            } finally {
                IOUtils.closeQuietly(raf);
            }

            boolean isComplete = checkUploadStatus(uploadFile, confFile);
            uploadFileResult.setFileUrl(fileUrl);
            uploadFileResult.setFileName(multipartFileParam.getFileName());
            uploadFileResult.setExtendName(StringUtils.isBlank(multipartFileParam.getExtendName()) ? "" : multipartFileParam.getExtendName().toString());
            uploadFileResult.setFileSize(uploadFile.isChunkFlag() ? (uploadFile.getTotalChunks() == 1 ? multipartFileParam.getSize() : uploadFile.getTotalSize()) : multipartFileParam.getSize());
            uploadFileResult.setStorageType(StorageTypeEnum.LOCAL.getCode());
            uploadFileResult.setMd5(FileUtil.getFileMd5(multipartFileParam.getUploadBytes()));
            uploadFileResult.setIdentifier(uploadFile.getIdentifier());
            uploadFileResult.setSort(uploadFile.getSort());
            uploadFileResult.setStatus(isComplete ? UploadFileStatusEnum.SUCCESS : UploadFileStatusEnum.UNCOMPLATE);
            if (isComplete) {
                tempFile.renameTo(file);
                FILE_URL_MAP.remove(uploadFile.getIdentifier());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("上传失败");
        }
        return uploadFileResult;
    }

    @Override
    public void cancelUpload(UploadFile uploadFile) {
        String fileUrl = FILE_URL_MAP.get(uploadFile.getIdentifier());
        String tempFileUrl = fileUrl + "_tmp";
        String confFileUrl = fileUrl.replace("." + FilenameUtils.getExtension(fileUrl), ".conf");
        File tempFile = new File(tempFileUrl);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        File confFile = new File(confFileUrl);
        if (confFile.exists()) {
            confFile.delete();
        }
    }

    @Override
    protected void doUploadFileChunk(MultipartFileParam multipartFileParam, UploadFile uploadFile) {

    }

    @Override
    protected UploadFileResult organizationalResults(MultipartFileParam multipartFileParam, UploadFile uploadFile) {
        return null;
    }

}
