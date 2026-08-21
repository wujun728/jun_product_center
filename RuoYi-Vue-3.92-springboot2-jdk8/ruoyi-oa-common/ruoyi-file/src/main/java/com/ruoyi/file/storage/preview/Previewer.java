package com.ruoyi.file.storage.preview;

import com.ruoyi.file.storage.preview.domain.PreviewFile;
import com.ruoyi.file.storage.util.CharsetUtils;
import com.ruoyi.file.storage.util.ImageOperation;
import com.ruoyi.file.storage.util.FileStorageUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件预览父类
 */
@Slf4j
@Data
public abstract class Previewer {

    public abstract InputStream getInputStream(PreviewFile previewFile);

    /**
     * 预览<br>
     * 1、如果是图片，并且isThumbnail为true，则预览缩略图;
     * 2、缓存文件到本地，如果不存在，则从源文件系统读取，然后写入本地缓存文件，最后预览缓存文件；
     * 3、定期清理缓存，机制：每月底清理上月缓存，因此缓存的目录需要带上月度
     *
     * @param httpServletResponse
     * @param previewFile
     */
    public void preview(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        String ext = previewFile.getExtName();
        if (FileStorageUtils.isImageFile(ext) && previewFile.isThumbnail()) {
            previewThumbnailImage(httpServletResponse, previewFile);
            return;
        }
        previewFile(httpServletResponse, previewFile);
    }

    /**
     * 预览缩略图
     *
     * @param httpServletResponse
     * @param previewFile
     */
    private void previewThumbnailImage(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        String fileUrl = FileStorageUtils.CACHE_THUMBNAIL_FLODER + previewFile.getPreviewUrl();
        if (previewForCache(fileUrl, httpServletResponse)) {
            return;
        }
        OutputStream outputStream = null;
        InputStream in = null;
        InputStream inputstream = null;
        try {
            inputstream = getInputStream(previewFile);
            outputStream = httpServletResponse.getOutputStream();
            in = ImageOperation.thumbnailsImageForScale(inputstream, FileStorageUtils.getCacheFile(fileUrl), 50);
            IOUtils.copy(in, outputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(inputstream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 预览源文件
     *
     * @param httpServletResponse
     * @param previewFile
     */
    private void previewFile(HttpServletResponse httpServletResponse, PreviewFile previewFile) {
        String fileUrl = previewFile.getPreviewUrl();
        if (previewForCache(fileUrl, httpServletResponse)) {
            return;
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = getInputStream(previewFile);
            outputStream = httpServletResponse.getOutputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            // 写入本地缓存
            FileUtils.writeByteArrayToFile(FileStorageUtils.getCacheFile(fileUrl), bytes);
            bytes = CharsetUtils.convertTxtCharsetToUTF8(bytes, FilenameUtils.getExtension(fileUrl));
            outputStream.write(bytes);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 从本地缓存中预览文件
     *
     * @param fileUrl
     * @param httpServletResponse
     * @return
     */
    private boolean previewForCache(String fileUrl, HttpServletResponse httpServletResponse) {
        File cacheFile = FileStorageUtils.getCacheFile(fileUrl);
        if (!cacheFile.exists()) {
            return false;
        }
        FileInputStream fis = null;
        OutputStream outputStream = null;
        try {
            fis = new FileInputStream(cacheFile);
            outputStream = httpServletResponse.getOutputStream();
            IOUtils.copy(fis, outputStream);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(outputStream);
        }
        return false;
    }

}
