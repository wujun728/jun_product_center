package com.ruoyi.file.storage.upload.param;

import com.ruoyi.file.storage.util.FileStorageUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class MultipartFileParam {

    MultipartFile multipartFile = null;

    private MultipartFileParam() {}

    public MultipartFileParam(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getFileName() {
        String originalName = getMultipartFile().getOriginalFilename();
        if (!originalName.contains(".")) {
            return originalName;
        }
        return originalName.substring(0, originalName.lastIndexOf("."));
    }

    public String getExtendName() {
        String originalName = getMultipartFile().getOriginalFilename();
        return FilenameUtils.getExtension(originalName);
    }

    public String getFileUrl() {
        String uuid = UUID.randomUUID().toString();
        return FileStorageUtils.getUploadFileUrl(uuid, getExtendName());
    }

    public String getFileUrl(String identify) {
        return FileStorageUtils.getUploadFileUrl(identify, getExtendName());
    }

    public InputStream getUploadInputStream() {
        try {
            return getMultipartFile().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getUploadBytes() throws IOException {
        return getMultipartFile().getBytes();
    }

    public long getSize() {
        return getMultipartFile().getSize();
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

}
