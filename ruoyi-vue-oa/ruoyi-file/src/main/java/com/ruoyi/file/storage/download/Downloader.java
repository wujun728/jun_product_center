package com.ruoyi.file.storage.download;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.storage.download.domain.DownloadFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * 下载器
 */
@Slf4j
public abstract class Downloader {

    public void download(HttpServletResponse httpServletResponse, DownloadFile downloadFile) {
        InputStream inputStream = getInputStream(downloadFile);
        try {
            // 设置响应头优化下载64kb
            httpServletResponse.setBufferSize(65536);
            OutputStream outputStream = httpServletResponse.getOutputStream();
//            nio方式，带宽不受限时，可以使用下面注释的方法
//            WritableByteChannel channel = Channels.newChannel(outputStream);
//            ByteBuffer buffer = ByteBuffer.allocateDirect(65536);
//            byte[] bytes = new byte[65536];
//            int read;
//            while ((read = inputStream.read(bytes)) != -1) {
//                buffer.put(bytes, 0, read);
//                buffer.flip();
//                channel.write(buffer);
//                buffer.clear();
//            }
            IOUtils.copyLarge(inputStream, outputStream, new byte[65536]);
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件下载失败");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public abstract InputStream getInputStream(DownloadFile downloadFile);
}
