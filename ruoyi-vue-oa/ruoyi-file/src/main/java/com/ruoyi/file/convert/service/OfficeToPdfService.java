package com.ruoyi.file.convert.service;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.file.convert.module.ConvertFileDTO;
import com.ruoyi.file.storage.context.FileServerContext;
import com.ruoyi.file.storage.copy.domain.CopyFile;
import com.ruoyi.tools.utils.date.LocalDateTimeUtil;
import com.sun.star.document.UpdateDocMode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jodconverter.local.LocalConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * office转pdf服务
 * @author wocurr.com
 */
@Slf4j
@Component
public class OfficeToPdfService {

    @Autowired
    private FileServerContext fileServerContext;
    @Autowired
    private IFileStorageService fileStorageService;

    /**
     * 转换office为pdf（同步方式）
     * @param convertFileDTO
     */
    public String convertPdf(ConvertFileDTO convertFileDTO) {
        log.info("开始转换pdf。。。");
        long t1 = System.currentTimeMillis();
        InputStream in = convertFileDTO.getInputStream();
        try {
            String fileUrl = convertFileDTO.getFileUrl();
            String sourceFile = fileUrl.substring(fileUrl.lastIndexOf("/"));
            int index = sourceFile.lastIndexOf(".");
            String targetFileUrl = LocalDateTimeUtil.formatNow(LocalDateTimeUtil.FORMAT_YYYYMM) + sourceFile.substring(0, index) + ".pdf";
            // 在本地临时目录下创建转换文件
            String localPath = RuoYiConfig.getUploadPath() + "/" + targetFileUrl;
            File outputFile = new File(localPath);
            // 假如目标路径不存在,则新建该路径
            if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
                log.error("创建目录【{}】失败，请检查目录权限！", targetFileUrl);
                throw new BaseException("创建文件目录失败");
            }
            LocalConverter.Builder builder;
            Map<String, Object> filterData = new HashMap<>();
            filterData.put("EncryptFile", true);
            filterData.put("ExportBookmarks", true);
            filterData.put("DocumentOpenPassword", convertFileDTO.getFilePassword()); //给PDF添加密码
            Map<String, Object> customProperties = new HashMap<>();
            customProperties.put("FilterData", filterData);
            if (StringUtils.isNotBlank(convertFileDTO.getFilePassword())) {
                Map<String, Object> loadProperties = new HashMap<>();
                loadProperties.put("Hidden", true);
                loadProperties.put("ReadOnly", true);
                loadProperties.put("UpdateDocMode", UpdateDocMode.NO_UPDATE);
                loadProperties.put("Password", convertFileDTO.getFilePassword());
                builder = LocalConverter.builder().loadProperties(loadProperties).storeProperties(customProperties);
            } else {
                builder = LocalConverter.builder().storeProperties(customProperties);
            }
            // 转换文件（同步）
            builder.build().convert(in).to(outputFile).execute();
            // 将文件上传到存储
            InputStream inputStream = new FileInputStream(outputFile);
            CopyFile copyFile = new CopyFile();
            copyFile.setExtendName("pdf");
            copyFile.setFizeSize(outputFile.length());
            String pdfUrl = fileServerContext.getCopier(convertFileDTO.getStorageType()).copy(inputStream, copyFile);
            log.info("pdfUrl: {}", pdfUrl);
            // 更新文件信息
            FileStorage fileStorage = new FileStorage();
            fileStorage.setFileId(convertFileDTO.getFileId());
            fileStorage.setPreviewUrl(pdfUrl);
            fileStorageService.updateByFileId(fileStorage);
            // 删除临时文件
            if (outputFile.exists()) {
                outputFile.delete();
                log.info("删除临时文件：" + localPath);
            }
            long t2 = System.currentTimeMillis();
            log.info("转换pdf完成，耗时：" + (t2-t1)) ;
            return pdfUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("转换文件失败！", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

}
