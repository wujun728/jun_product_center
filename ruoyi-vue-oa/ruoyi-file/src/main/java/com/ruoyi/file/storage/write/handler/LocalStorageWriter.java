package com.ruoyi.file.storage.write.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.write.Writer;
import com.ruoyi.file.storage.write.domain.WriteFile;
import com.ruoyi.file.storage.util.FileStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class LocalStorageWriter extends Writer {
    @Override
    public void write(InputStream inputStream, WriteFile writeFile) {
        try (FileOutputStream out = new FileOutputStream(FileStorageUtils.getDataPath() + writeFile.getFileUrl())){
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            throw new BaseException("待写入的文件不存在");
        } catch (IOException e) {
            throw new BaseException("操作失败");
        }
    }
}
