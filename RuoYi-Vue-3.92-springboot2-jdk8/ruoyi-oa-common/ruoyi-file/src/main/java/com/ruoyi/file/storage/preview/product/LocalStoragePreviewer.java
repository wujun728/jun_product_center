package com.ruoyi.file.storage.preview.product;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.preview.domain.PreviewFile;
import com.ruoyi.file.storage.util.FileStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class LocalStoragePreviewer extends Previewer {
    
    @Override
    public InputStream getInputStream(PreviewFile previewFile) {
        //设置文件路径
        File file = FileStorageUtils.getLocalSaveFile(previewFile.getPreviewUrl());
        if (!file.exists()) {
            throw new BaseException("文件不存在");
        }
        InputStream inputStream = null;
        byte[] bytes = new byte[0];
        try {
            inputStream = new FileInputStream(file);
            bytes = IOUtils.toByteArray(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return new ByteArrayInputStream(bytes);
    }
}
