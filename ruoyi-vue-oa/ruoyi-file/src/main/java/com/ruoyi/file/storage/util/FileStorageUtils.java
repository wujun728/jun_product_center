package com.ruoyi.file.storage.util;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.tools.utils.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class FileStorageUtils {

    public static String LOCAL_STORAGE_PATH = RuoYiConfig.getUploadPath();

    public static final String ROOT_PATH = "upload";

    public static final String CACHE_FLODER = "cache";

    public static final String CACHE_THUMBNAIL_FLODER = "thumbnail/";

    public static final List<String> IMAGE_EXT_LIST = Arrays.asList("jpg", "jpeg", "png", "tif", "gif", "bmp");
    public static final String[] TXT_FILE = {};
    private static final String SEPERATOR = "/";

    /**
     * 删除缓存文件（文件缓存到本地服务器，用于提高预览速度，每个月底清理上个月的缓存，避免过多占用磁盘空间）
     */
    public static void deleteTempFile() {
        String tempPath = getDataPath() + CACHE_FLODER + File.separator + LocalDateTimeUtil.month(LocalDateTimeUtil.monthFirst(-1));
        log.info("## 删除缓存文件，目录：" + tempPath);
        Path dirPath = Paths.get(tempPath);
        if (!Files.exists(dirPath)) {
            return;
        }
        try {
            Files.walk(dirPath)
                    .sorted(Comparator.reverseOrder())
                    .parallel() // 使用并行流
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            log.error("Failed to delete file: " + file, e);
                        }
                    });
        } catch (IOException e) {
            log.error("Failed to walk the directory: " + dirPath, e);
        }
    }

    public static String pathSplitFormat(String filePath) {
        return filePath.replace("///", "/")
                .replace("//", "/")
                .replace("\\\\\\", "/")
                .replace("\\\\", "/");
    }

    public static File getLocalSaveFile(String fileUrl) {
        String localSavePath = getDataPath() + fileUrl;
        return new File(localSavePath);
    }

    /**
     * 获取缓存路径<br>
     * 路径为： 系统配置路径/cache/月份/fileUrl
     *
     * @param fileUrl
     * @return
     */
    public static String getCachePath(String fileUrl) {
        return getDataPath() + CACHE_FLODER + File.separator + LocalDateTimeUtil.month() + File.separator + fileUrl;
    }

    public static File getCacheFile(String fileUrl) {
        return new File(getCachePath(fileUrl));
    }

    /**
     * 获取临时文件
     *
     * @param fileUrl
     * @return
     */
    public static File getTempFile(String fileUrl) {
        String tempPath = getDataPath() + "temp" + File.separator + fileUrl;
        File tempFile = new File(tempPath);
        File parentFile = tempFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return tempFile;
    }

    public static File getProcessFile(String fileUrl) {
        String processPath = getDataPath() + "temp" + File.separator + "process" + File.separator + fileUrl;
        File processFile = new File(processPath);
        File parentFile = processFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return processFile;
    }

    /**
     * 判断是否为图片文件
     *
     * @param extendName 文件扩展名
     * @return 是否为图片文件
     */
    public static boolean isImageFile(String extendName) {
        if (StringUtils.isBlank(extendName)) {
            return false;
        }
        return IMAGE_EXT_LIST.contains(extendName.toLowerCase());
    }


    /**
     * 获取项目所在的根目录路径 resources路径
     *
     * @return 结果
     */
    public static String getProjectRootPath() {
        String absolutePath;
        try {
            String url = ResourceUtils.getURL("classpath:").getPath();
            absolutePath = urlDecode(new File(url).getAbsolutePath()) + File.separator;
        } catch (FileNotFoundException e) {
            throw new BaseException("操作失败");
        }
        return absolutePath;
    }

    /**
     * 路径解码
     *
     * @param url url
     * @return 结果
     */
    public static String urlDecode(String url) {
        String decodeUrl;
        try {
            decodeUrl = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseException("不支持的编码格式");
        }
        return decodeUrl;
    }


    public static String getDataPath() {
        return new File(LOCAL_STORAGE_PATH).getPath() + File.separator;
    }

    /**
     * 获取上传文件路径
     * 返回路径格式 “upload/yyyy/MM/dd/”
     *
     * @param identifier 文件名（一般传入md5或uuid,防止文件名重复）
     * @param extendName 文件扩展名
     * @return 返回上传文件路径
     */
    public static String getUploadFileUrl(String identifier, String extendName) {
        String path = ROOT_PATH + SEPERATOR + LocalDateTimeUtil.formatNow(LocalDateTimeUtil.FORMAT_YMD) + SEPERATOR;
        File dir = new File(getDataPath() + path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path = path + identifier + "." + extendName;
        return path;
    }

    /**
     * 格式化路径<br>
     * 1、去除多余的“/”和“\”
     *
     * @param path
     * @return
     */
    public static String formatPath(String path) {
        path = pathSplitFormat(path);
        if (SEPERATOR.equals(path)) {
            return path;
        }
        if (!path.startsWith(SEPERATOR)) {
            path = SEPERATOR + path;
        }
        if (path.endsWith(SEPERATOR)) {
            int length = path.length();
            return path.substring(0, length - 1);
        }
        return path;
    }

    /**
     * 获取父级路径
     *
     * @param path
     * @return
     */
    public static String getParentPath(String path) {
        path = pathSplitFormat(path);
        if (SEPERATOR.equals(path)) {
            return path;
        }
        if (!path.startsWith(SEPERATOR)) {
            path = SEPERATOR + path;
        }
        if (!path.endsWith(SEPERATOR)) {
            path = path + SEPERATOR;
        }
        return path;
    }

    public static String getAliyunObjectNameByFileUrl(String fileUrl) {
        return getObjectName(fileUrl);
    }

    public static String getTencentObjectNameByFileUrl(String fileUrl) {
        return getObjectName(fileUrl);
    }

    @NotNull
    private static String getObjectName(String fileUrl) {
        if (fileUrl.startsWith("/") || fileUrl.startsWith("\\")) {
            fileUrl = fileUrl.substring(1);
        }
        return fileUrl;
    }
}
