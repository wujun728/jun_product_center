package com.ruoyi.file.storage.util;

import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类
 *
 * @Author wocurr.com
 */
@Slf4j
public class FileUtil {

    /**
     * 获取文件md5值
     *
     * @param file
     * @return
     */
    public static String getFileMd5(byte[] file) {
        return DigestUtils.md5DigestAsHex(file);
    }

    /**
     * 获取文件md5值
     *
     * @param file
     * @return
     */
    public static String getFileMd5(InputStream file) {
        try {
            return DigestUtils.md5DigestAsHex(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("生成文件md5失败");
        }
    }

    /**
     * 获取文件扩展名
     * @param fileUrl
     * @return
     */
    public static String getExt(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return "";
        }
        int lastDotIndex = fileUrl.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return fileUrl.substring(lastDotIndex + 1);
    }

    /**
     * 获取文件名，不包含扩展名
     * @param fileName
     * @return
     */
    public static String getName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, lastDotIndex);
    }

    /**
     * 计算文件大小
     *
     * @param file
     * @return
     */
    private String getFileSize(File file) {
        long fileSize = FileUtils.sizeOf(file);
        return getFileSize(Long.valueOf(fileSize));
    }

    /**
     * 计算文件大小
     *
     * @param size
     * @return
     */
    public static String getFileSize(Long size) {
        if (size == null) {
            return "0KB";
        }
        if (size.longValue() < 1024L) {
            return size + "B";
        }
        size = Long.valueOf(size.longValue() / 1024L);
        if (size.longValue() < 1024L) {
            return size + "KB";
        }
        size = Long.valueOf(size.longValue() / 1024L);
        if (size.longValue() < 1024L) {
            size = Long.valueOf(size.longValue() * 100L);
            return (size.longValue() / 100L) + "." + (size.longValue() % 100L) + "MB";
        }
        size = Long.valueOf(size.longValue() * 100L / 1024L);
        return (size.longValue() / 100L) + "." + (size.longValue() % 100L) + "GB";
    }
}
