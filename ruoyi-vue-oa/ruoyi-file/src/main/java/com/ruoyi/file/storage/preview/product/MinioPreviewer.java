package com.ruoyi.file.storage.preview.product;

import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.preview.domain.PreviewFile;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Slf4j
@Component
public class MinioPreviewer extends Previewer {
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public InputStream getInputStream(PreviewFile previewFile) {
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(previewFile.getPreviewUrl()).build());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return inputStream;
    }
}
