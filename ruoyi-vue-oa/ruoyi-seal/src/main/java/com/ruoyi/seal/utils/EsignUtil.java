package com.ruoyi.seal.utils;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.seal.constant.SealConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 电子签工具类
 * @Author wocurr.com
 */
@Slf4j
public class EsignUtil {

    /**
     * 系统默认路径
     */
    private static String DEFAULT_BASEDIR = RuoYiConfig.getProfile();
    private static final String SEPARATOR = "/";
    private static final int MAX_RETRY = 5; // 最大尝试次数
    private static final int RETRY_INTERVAL = 100; // 重试间隔时间（毫秒）

    /**
     * 获取文件绝对路径
     *
     * @param relativePath
     * @return
     */
    public static String getAbsolutePath(String relativePath) {
        String fullPath = DEFAULT_BASEDIR + SEPARATOR + relativePath;
        Path path = Paths.get(fullPath).getParent();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            log.error("目录创建失败：{}", path, e);
            throw new BaseException("文件目录创建失败");
        }
        return fullPath;
    }

    /**
     * 获取pdf合同存放路径
     *
     * @return
     */
    public static String getPdfContractPath() {
        return SealConstants.PDF_CONTRACT_DIR;
    }

    /**
     * 获取pdf模板路径
     *
     * @return
     */
    public static String getPdfTemplatePath() {
        return DEFAULT_BASEDIR + SEPARATOR + SealConstants.PDF_TEMPLATE_DIR;
    }

    /**
     * 获取证书路径
     * @return
     */
    public static String getCertPath() {
        return DEFAULT_BASEDIR + SEPARATOR + SealConstants.PDF_CERT_DIR;
    }

    /**
     * 创建合同pdf文件路径
     * @return
     */
    public static String createPdfContractUrl() {
        return getPdfContractPath() + IdUtils.fastSimpleUUID() + SealConstants.PDF_SUFFIX;
    }

    /**
     * 删除文件
     *
     * @param fileUrl
     */
    public static void deleteFile(String fileUrl) {
//        String absoluteFileUrl = getAbsolutePath(fileUrl);
        if (StringUtils.isBlank(fileUrl)) {
            return;
        }
        File targetFile = new File(fileUrl);
        if (!targetFile.isFile() || !targetFile.exists()) {
            log.warn("文件不存在或非普通文件：{}", fileUrl);
            return;
        }
        for (int attempt = 1; attempt <= MAX_RETRY; attempt++) {
            try {
                if (targetFile.delete()) {
                    return;
                }
                if (attempt < MAX_RETRY) {
                    Thread.sleep(RETRY_INTERVAL);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (SecurityException e) {
                break;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                log.error("删除文件异常 [尝试{}/{}]：{}", attempt, MAX_RETRY, fileUrl, e);
            }
        }
        log.error("文件删除失败（已尝试{}次）：{}", MAX_RETRY, fileUrl);
    }

}
