package com.ruoyi.file.business.api.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.business.api.IFileService;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.enmus.OfficeTemplateEnum;
import com.ruoyi.file.business.mapper.FileSourceTargetMapper;
import com.ruoyi.file.business.module.BookmarkData;
import com.ruoyi.file.business.module.FontStyle;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.file.business.module.FileQO;
import com.ruoyi.file.convert.module.ConvertFileDTO;
import com.ruoyi.file.convert.service.OfficeToPdfService;
import com.ruoyi.file.storage.config.FileConfig;
import com.ruoyi.file.storage.contants.StorageContants;
import com.ruoyi.file.storage.context.FileServerContext;
import com.ruoyi.file.storage.copy.domain.CopyFile;
import com.ruoyi.file.storage.delete.domain.DeleteFile;
import com.ruoyi.file.storage.download.Downloader;
import com.ruoyi.file.storage.download.domain.DownloadFile;
import com.ruoyi.file.storage.enums.StorageTypeEnum;
import com.ruoyi.file.storage.enums.UploadFileStatusEnum;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.preview.domain.PreviewFile;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import com.ruoyi.file.storage.util.FileUtil;
import com.ruoyi.file.storage.util.MimeUtils;
import com.ruoyi.file.storage.util.FileStorageUtils;
import com.ruoyi.file.storage.write.Writer;
import com.ruoyi.file.storage.write.domain.WriteFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author wocurr.com
 */
@Slf4j
@Service
public class FileService implements IFileService {

    @Autowired
    private FileServerContext fileServerContext;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private OfficeToPdfService officeToPdfService;
    @Autowired
    private FileConfig config;

    @Override
    public String uploadFile(UploadFile uploadFile) {
        try {
            String filePath = uploadFile.getFilePath();
            byte[] file = uploadFile.getFileBytes();
            String storageType = config.getStorageType();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file);
            long fileSize = byteArrayInputStream.available();
            writeFile(storageType, filePath, fileSize, byteArrayInputStream);
            String md5 = FileUtil.getFileMd5(file);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setFileName(uploadFile.getFilename());
            fileStorage.setMd5(md5);
            fileStorage.setIdentifier(md5);
            fileStorage.setFileSize(fileSize);
            fileStorage.setFileUrl(filePath);
            fileStorage.setStorageType(storageType);
            fileStorage.setExtendName(uploadFile.getExtendName());
            setDefaultFileStorage(fileStorage);
            fileStorageService.saveFileStorage(fileStorage);
            return fileStorage.getFileId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件上传失败！");
        }
    }
    @Override
    public List<UploadFileResult> uploadFileSpeed(UploadFile uploadFile) {
        if (uploadFile == null) {
            throw new BaseException("参数错误");
        }
        List<UploadFileResult> result = new ArrayList<>();
        UploadFileResult uploadFileResult = uploadFileSpeedAndCopy(uploadFile);
        result.add(uploadFileResult);
        return result;
    }

    @Override
    public UploadFileResult uploadFileSpeedAndCopy(UploadFile uploadFile) {
        if (uploadFile == null) {
            throw new BaseException("参数错误");
        }
        UploadFileResult uploadFileResult = null;
        boolean skipUpload = false;
        // 通过文件名+identifier确定文件唯一（同类型的空文件计算md5时，前端spark是相同值！）
        FileStorage query = new FileStorage();
        query.setIdentifier(uploadFile.getIdentifier());
        query.setFileName(FileUtil.getName(uploadFile.getFilename()));
        List<FileStorage> list = fileStorageService.listByEqConditions(query);
        if (CollectionUtils.isNotEmpty(list)) {
            skipUpload = true;
            FileStorage fileStorage = list.get(0);
            // 存在相同的，则copy
            String fileUrl = copyFile(fileStorage.getStorageType(), fileStorage.getFileUrl(), fileStorage.getFileName(), fileStorage.getExtendName(), fileStorage.getFileSize());
            fileStorage.setId(null);
            fileStorage.setFileUrl(fileUrl);
            fileStorage.setFileId(IdUtils.fastSimpleUUID());
            // 非office文件，预览地址为文件地址
            if (!StorageContants.DOCS.contains(fileStorage.getExtendName())) {
                fileStorage.setPreviewUrl(fileStorage.getFileUrl());
            }
            fileStorageService.saveFileStorage(fileStorage);
            uploadFileResult = FileSourceTargetMapper.INSTANCE.convertUploadFileResult(fileStorage);
        } else {
            uploadFileResult = new UploadFileResult();
        }
        uploadFileResult.setSkipUpload(skipUpload);
        return uploadFileResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UploadFileResult> uploadFile(HttpServletRequest request, UploadFile uploadFile) {
        if (uploadFile == null) {
            throw new BaseException("参数错误");
        }
        // 普通上传使用uuid做为文件唯一标识
        if (!uploadFile.isChunkFlag() && StringUtils.isBlank(uploadFile.getIdentifier())) {
            uploadFile.setIdentifier(IdUtils.fastSimpleUUID());
        }
        List<UploadFileResult> result = fileServerContext.getUploader().upload(request, uploadFile);
        result.stream().forEach(f -> {
            if (UploadFileStatusEnum.SUCCESS.equals(f.getStatus())) {
                FileStorage fileStorage = FileSourceTargetMapper.INSTANCE.convertFileStorage(f);
                setDefaultFileStorage(fileStorage);
                // 非office文件，预览地址为文件地址
                if (!StorageContants.DOCS.contains(fileStorage.getExtendName())) {
                    fileStorage.setPreviewUrl(fileStorage.getFileUrl());
                }
                fileStorageService.saveFileStorage(fileStorage);
                f.setFileId(fileStorage.getFileId());
                f.setFileUrl(fileStorage.getFileUrl());
                f.setFileSize(fileStorage.getFileSize());
            }
        });
        return result;
    }

    /**
     * 文件下载<br>
     * 请在上层业务中校验是否有权限，本方法不做强校验，只处理下载
     *
     * @param httpServletResponse
     * @param fileQO
     */
    @Override
    public void downloadFile(HttpServletResponse httpServletResponse, FileQO fileQO) {
        if (fileQO == null || StringUtils.isEmpty(fileQO.getFileId())) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileQO.getFileId());
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        try {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setFileUrl(fileStorage.getFileUrl());
            // 设置响应头
            String fileName = fileStorage.getFileName() + Constants.DOT + fileStorage.getExtendName();
            String encodedFileName = "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8");
            httpServletResponse.setContentType("application/force-download");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.addHeader("Content-Disposition", encodedFileName);
            httpServletResponse.setContentLengthLong(fileStorage.getFileSize());
            // 获取对应存储类型下载器执行下载
            fileServerContext.getDownloader(fileStorage.getStorageType()).download(httpServletResponse, downloadFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件下载失败");
        }
    }

    /**
     * 批量下载文件<br>
     * 1、请在上层校验是否有权限下载；
     * 2、本方法只处理下载业务，不强校验
     *
     * @param httpServletResponse
     * @param fileQO
     */
    @Override
    public void bathDownloadFile(HttpServletResponse httpServletResponse, FileQO fileQO) {
        if (CollectionUtils.isEmpty(fileQO.getFileIds()) && CollectionUtils.isEmpty(fileQO.getFileList())) {
            throw new BaseException("参数错误");
        }
        String tempPath = FileStorageUtils.getDataPath() + File.separator + "temp" + File.separator;
        File tempDirFile = new File(tempPath);
        if (!tempDirFile.exists()) {
            tempDirFile.mkdirs();
        }
        String tmpfileName = DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS);
        String tempFilePath = tempPath + tmpfileName + ".zip";
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(tempFilePath);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件不存在");
        }
        CheckedOutputStream out = new CheckedOutputStream(f, new Adler32());
        if (StringUtils.isNotBlank(fileQO.getDownType()) && StringUtils.equals(Constants.YES_VALUE, fileQO.getDownType())) {
            // 只打包文件
            downloadByFileIds(fileQO.getFileIds(), out);
        } else {
            // 可以打包文件夹，路径需要传入
            downloadByFiles(fileQO.getFileList(), out);
        }
        try {
            Downloader downloader = fileServerContext.getDownloader(StorageTypeEnum.LOCAL.getCode());
            DownloadFile zipDownloadFile = new DownloadFile();
            zipDownloadFile.setFileUrl(File.separator + "temp" + File.separator + tmpfileName + ".zip");
            // 设置响应头
            String title = StringUtils.isBlank(fileQO.getFileName()) ? tmpfileName : fileQO.getFileName() + Constants.DOT + ".zip";
            String encodedFileName = "attachment; filename*=UTF-8''" + URLEncoder.encode(title, "UTF-8");
            httpServletResponse.setContentLengthLong(new File(tempFilePath).length());
            httpServletResponse.setContentType("application/force-download");
            httpServletResponse.addHeader("Content-Disposition", encodedFileName);
            downloader.download(httpServletResponse, zipDownloadFile);
        } catch (Exception e) {
            log.error("下传zip文件出现异常：{}", e.getMessage());
        } finally {
            // 删除临时文件
            File file = new File(tempFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    public void previewFile(HttpServletResponse httpServletResponse, FileQO fileQO) {
        if (fileQO == null || StringUtils.isBlank(fileQO.getFileId())) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileQO.getFileId());
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        Previewer previewer = fileServerContext.getPreviewer(fileStorage.getStorageType());
        String previewUrl = fileStorage.getPreviewUrl();
        String ext = fileStorage.getExtendName();
        if (StringUtils.isBlank(previewUrl)) {
            // 如果不存在预览文件，则同步转换
            if (StorageContants.DOCS.contains(fileStorage.getExtendName())) {
                ConvertFileDTO convertFileDTO = new ConvertFileDTO();
                PreviewFile pw = new PreviewFile();
                pw.setPreviewUrl(fileStorage.getFileUrl());
                convertFileDTO.setInputStream(previewer.getInputStream(pw));
                convertFileDTO.setStorageType(fileStorage.getStorageType());
                convertFileDTO.setFileUrl(fileStorage.getFileUrl());
                convertFileDTO.setFileId(fileQO.getFileId());
                previewUrl = officeToPdfService.convertPdf(convertFileDTO);
                ext = StorageContants.PDF;
            } else {
                previewUrl = fileStorage.getFileUrl();
            }
        }
        // 中文问题
        String fileName = fileStorage.getFileName() + Constants.DOT + ext;
        try {
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        // 设置文件名
        httpServletResponse.addHeader("Content-Disposition", "fileName=" + fileName);
        String mime = MimeUtils.getMime(ext);
        httpServletResponse.setHeader("Content-Type", mime);
        // 设置缓存
        if (FileStorageUtils.isImageFile(ext)) {
            httpServletResponse.setHeader("cache-control", "public");
        }
        PreviewFile previewFile = new PreviewFile();
        previewFile.setPreviewUrl(previewUrl);
        previewFile.setExtName(ext);
        previewFile.setThumbnail(fileQO.isThumbnail());
        previewer.preview(httpServletResponse, previewFile);
    }

    @Override
    public void officePreviewFile(HttpServletResponse httpServletResponse, FileQO fileQO) {
        if (fileQO == null || StringUtils.isBlank(fileQO.getFileId())) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileQO.getFileId());
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        Previewer previewer = fileServerContext.getPreviewer(fileStorage.getStorageType());
        String ext = fileStorage.getExtendName();
        // 中文问题
        String fileName = fileStorage.getFileName() + Constants.DOT + ext;
        try {
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        // 设置文件名
        httpServletResponse.addHeader("Content-Disposition", "fileName=" + fileName);
        String mime = MimeUtils.getMime(ext);
        httpServletResponse.setHeader("Content-Type", mime);
        // 设置缓存
        if (FileStorageUtils.isImageFile(ext)) {
            httpServletResponse.setHeader("cache-control", "public");
        }
        PreviewFile previewFile = new PreviewFile();
        previewFile.setPreviewUrl(fileStorage.getFileUrl());
        previewer.preview(httpServletResponse, previewFile);
    }

    @Override
    public void writeFile(String storageType, String fileUrl, long fileSize, InputStream inputStream) {
        try {
            Writer writer = fileServerContext.getWriter(storageType);
            WriteFile writeFile = new WriteFile();
            writeFile.setFileUrl(fileUrl);
            writeFile.setFileSize(fileSize);
            writer.write(inputStream, writeFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(FileQO fileQO) {
        if (fileQO == null || StringUtils.isBlank(fileQO.getFileId())) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileQO.getFileId());
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        DeleteFile deleteFile = new DeleteFile();
        deleteFile.setFileUrl(fileStorage.getFileUrl());
        // 删除文件（物理删除）
        fileServerContext.getDeleter(fileStorage.getStorageType()).delete(deleteFile);
        // 删除记录（物理删除）
        fileStorageService.deleteByFileId(fileQO.getFileId());
    }

    /**
     * 批量删除文件
     *
     * @param fileQO
     */
    @Override
    public void batchDeleteFile(FileQO fileQO) {
        if (fileQO == null || CollectionUtils.isEmpty(fileQO.getFileIds())) {
            throw new BaseException("参数错误");
        }
        for (String fileId : fileQO.getFileIds()) {
            fileQO.setFileId(fileId);
            deleteFile(fileQO);
        }
    }

    @Override
    public long modifyFile(FileQO fileQO) {
        if (StringUtils.isBlank(fileQO.getFileId()) || StringUtils.isBlank(fileQO.getContent())) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileQO.getFileId());
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        long fileSize = 0L;
        InputStream inputStream = null;
        try {
            String fileUrl = fileStorage.getFileUrl();
            String content = fileQO.getContent();
            String storageType = fileStorage.getStorageType();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
            fileSize = byteArrayInputStream.available();
            writeFile(storageType, fileUrl, fileSize, byteArrayInputStream);
            inputStream = getInputStream(storageType, fileUrl);
            String md5 = FileUtil.getFileMd5(inputStream);
            fileStorage.setMd5(md5);
            fileStorage.setIdentifier(md5);
            fileStorage.setFileSize(fileSize);
            fileStorageService.updateFileStorage(fileStorage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return fileSize;
    }

    @Override
    public FileStorage copyFile(String fileId) {
        if (StringUtils.isBlank(fileId)) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileId);
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        String fileUril = copyFile(fileStorage.getStorageType(), fileStorage.getFileUrl(), fileStorage.getFileName(), fileStorage.getExtendName(), fileStorage.getFileSize());
        fileStorage.setFileUrl(fileUril);
        fileStorage.setId(null);
        setDefaultFileStorage(fileStorage);
        fileStorageService.saveFileStorage(fileStorage);
        return fileStorage;
    }

    @Override
    public String copyFile(String storageType, String fileUrl, String fileName, String fileExt, long fileSize) {
        CopyFile copyFile = new CopyFile();
        String extName = StringUtils.isBlank(fileExt) ? FileUtil.getExt(fileName) : fileExt;
        copyFile.setExtendName(extName);
        copyFile.setFizeSize(fileSize);
        copyFile.setFileUrl(fileUrl);
        copyFile.setServerCopy(true);
        InputStream inputStream = getInputStream(storageType, fileUrl);
        String copyfileUrl = fileServerContext.getCopier(storageType).copy(inputStream, copyFile);
        return copyfileUrl;
    }

    @Override
    public FileStorage createOfficeFile(FileQO qo) {
        String fileName = qo.getFileName();
        String extendName = qo.getExtendName();
        if (StringUtils.isBlank(fileName) || StringUtils.isBlank(extendName)) {
            throw new BaseException("参数错误");
        }
        OfficeTemplateEnum templateFile = OfficeTemplateEnum.getEnum(extendName);
        if (templateFile == null) {
            throw new BaseException("不支持该文件类型");
        }
        FileStorage fileStorage = new FileStorage();
        try {
            String url = ClassUtils.getDefaultClassLoader().getResource(templateFile.getTemplatePath()).getPath();
            FileInputStream fileInputStream = new FileInputStream(URLDecoder.decode(url, "UTF-8"));
            String storageType = config.getStorageType();
            CopyFile copyFile = new CopyFile();
            copyFile.setExtendName(extendName);
            String fileUrl = fileServerContext.getCopier(storageType).copy(fileInputStream, copyFile);
            fileStorage.setFileName(fileName);
            fileStorage.setIdentifier(IdUtils.fastSimpleUUID());
            fileStorage.setFileSize(0L);
            fileStorage.setFileUrl(fileUrl);
            fileStorage.setStorageType(storageType);
            fileStorage.setExtendName(extendName);
            setDefaultFileStorage(fileStorage);
            fileStorageService.saveFileStorage(fileStorage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件创建失败！");
        }
        return fileStorage;
    }

    @Override
    public FileStorage createReportFile(FileQO qo) {
        String fileName = qo.getFileName();
        String extendName = qo.getExtendName();
        String filePath = qo.getFilePath();
        String content = qo.getContent();
        if (StringUtils.isBlank(fileName) || StringUtils.isBlank(extendName)
                || StringUtils.isBlank(filePath) || StringUtils.isBlank(content)) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileUrl(filePath);
        try {
            String storageType = fileStorage == null ? config.getStorageType() : fileStorage.getStorageType();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
            long fileSize = byteArrayInputStream.available();
            String md5 = FileUtil.getFileMd5(content.getBytes());
            writeFile(storageType, filePath, fileSize, byteArrayInputStream);
            if (fileStorage == null) {
                fileStorage = new FileStorage();
                fileStorage.setFileName(fileName);
                fileStorage.setMd5(md5);
                fileStorage.setIdentifier(md5);
                fileStorage.setFileSize(fileSize);
                fileStorage.setFileUrl(filePath);
                fileStorage.setStorageType(storageType);
                fileStorage.setExtendName(extendName);
                setDefaultFileStorage(fileStorage);
                fileStorageService.saveFileStorage(fileStorage);
            } else {
                fileStorage.setMd5(md5);
                fileStorage.setIdentifier(md5);
                fileStorage.setFileSize(fileSize);
                fileStorageService.updateFileStorage(fileStorage);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("模板创建失败！");
        }
        return fileStorage;
    }

    @Override
    public InputStream getInputStream(FileQO fileQO) {
        String fileId = fileQO.getFileId();
        if (StringUtils.isBlank(fileId)) {
            throw new BaseException("参数错误");
        }
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileId);
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        String fileUrl = fileQO.isUsePreviewUrl() && StringUtils.isNotBlank(fileStorage.getPreviewUrl()) ? fileStorage.getPreviewUrl() : fileStorage.getFileUrl();
        return getInputStream(fileStorage.getStorageType(), fileUrl);
    }

    @Override
    public int rename(FileQO fileQO) {
        if (StringUtils.isBlank(fileQO.getFileId()) || StringUtils.isBlank(fileQO.getFileName())) {
            throw new BaseException("参数错误");
        }
        FileStorage f = fileStorageService.getFileStorageByFileId(fileQO.getFileId());
        if (f == null) {
            throw new BaseException("文件不存在");
        }
        f.setFileName(fileQO.getFileName());
        return fileStorageService.updateFileStorage(f);
    }

    @Override
    public int sort(FileQO fileQO) {
        if (CollectionUtils.isEmpty(fileQO.getFileList())) {
            throw new BaseException("参数错误");
        }
        fileQO.getFileList().stream().forEach(f -> {
            FileStorage fileStorage = new FileStorage();
            fileStorage.setFileId(f.getFileId());
            fileStorage.setSort(f.getSort());
            fileStorageService.updateFileSort(fileStorage);
        });
        return 1;
    }

    @Override
    public String wordBookmarks(String templateFileId, BookmarkData data) {
        XWPFDocument docx = null;
        try {
            FileQO qo = new FileQO();
            qo.setFileId(templateFileId);
            InputStream is = getInputStream(qo);
            if (is == null) {
                throw new BaseException("模板文件内容为空或不存在，请检查模板配置！");
            }
            docx = new XWPFDocument(is);
            // 处理书签替换
            processDocxBookmarks(docx, data);
            return storeDocxFile(docx);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("书签替换失败！");
        } finally {
            IOUtils.closeQuietly(docx);
        }
    }

    @Override
    public String getImageBase64(String fileId) {
        InputStream inputStream = null;
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileId);
        if (fileStorage == null) {
            throw new BaseException("文件不存在");
        }
        try {
            inputStream = getInputStream(fileStorage.getStorageType(), fileStorage.getFileUrl());
            // 获取文件MIME类型
            String mimeType = determineImageMimeType(fileStorage.getExtendName());
            // 读取文件流并编码
            byte[] bytes = IOUtils.toByteArray(inputStream);
            String base64 = Base64.getEncoder().encodeToString(bytes);
            return "data:" + mimeType + ";base64," + base64;
        } catch (Exception e) {
            throw new BaseException("获取图片失败！");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 设置默认值
     *
     * @param fileStorage
     */
    private void setDefaultFileStorage(FileStorage fileStorage) {
        if (fileStorage.getId() != null) {
            fileStorage.setUpdateId(SecurityUtils.getUserId());
            fileStorage.setUpdateBy(SecurityUtils.getUsername());
            fileStorage.setUpdateTime(DateUtils.getNowDate());
        } else {
            fileStorage.setFileId(IdUtils.fastSimpleUUID());
            fileStorage.setCreateId(SecurityUtils.getUserId());
            fileStorage.setCreateBy(SecurityUtils.getUsername());
            fileStorage.setCreateTime(DateUtils.getNowDate());
        }
    }

    /**
     * 获取文件输入流
     *
     * @param storageType
     * @param fileUrl
     * @return
     */
    private InputStream getInputStream(String storageType, String fileUrl) {
        Downloader downloader = fileServerContext.getDownloader(storageType);
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setFileUrl(fileUrl);
        return downloader.getInputStream(downloadFile);
    }

    /**
     * 根据文件下载
     * @param fileList
     * @param out
     */
    private void downloadByFiles(List<FileQO> fileList, CheckedOutputStream out) {
        if (CollectionUtils.isEmpty(fileList)) {
            throw new BaseException("文件不存在！");
        }
        if (fileList.size() > 5000) {
            throw new BaseException("超过下载上限！一次最多支持下载5000条。");
        }
        ZipOutputStream zos = new ZipOutputStream(out);
        BufferedOutputStream buff = new BufferedOutputStream(zos);
        try {
            Map<String, String> fileMap = new HashMap<>();
            for (FileQO file : fileList) {
                if (file.isDir()) {
                    // 空文件夹的处理（过滤重复文件夹）
                    String path = FileStorageUtils.getParentPath(file.getFilePath()) + file.getFileName() + Constants.SEPARATOR;
                    if (!fileMap.containsKey(path)) {
                        zos.putNextEntry(new ZipEntry(path));
                        zos.closeEntry();
                        fileMap.put(path, "1");
                    }
                    continue;
                }
                FileStorage fileStorage = fileStorageService.getFileStorageByFileId(file.getFileId());
                if (fileStorage == null) {
                    log.error("## 批量下载，包含不存在文件");
                    continue;
                }
                InputStream inputStream = getInputStream(fileStorage.getStorageType(), fileStorage.getFileUrl());
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                try {
                    String filePath = FileStorageUtils.getParentPath(file.getFilePath()) + file.getFileName() + Constants.DOT + fileStorage.getExtendName();
                    zos.putNextEntry(new ZipEntry(filePath));
                    byte[] buffer = new byte[1024];
                    int i = bis.read(buffer);
                    while (i != -1) {
                        buff.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    buff.flush();
                    IOUtils.closeQuietly(bis);
                }
            }
        } catch (Exception e) {
            log.error("压缩过程中出现异常:" + e);
        } finally {
            IOUtils.closeQuietly(buff);
            IOUtils.closeQuietly(zos);
        }
    }

    /**
     * 根据文件id下载
     * @param fileIds
     * @param out
     */
    private void downloadByFileIds(List<String> fileIds, CheckedOutputStream out) {
        if (CollectionUtils.isEmpty(fileIds)) {
            throw new BaseException("文件不存在！");
        }
        if (fileIds.size() > 5000) {
            throw new BaseException("超过下载上限！一次最多支持下载5000条。");
        }
        ZipOutputStream zos = new ZipOutputStream(out);
        BufferedOutputStream buff = new BufferedOutputStream(zos);
        try {
            Set<String> addedEntries = new HashSet<>();
            for (String fileId : fileIds) {
                FileStorage file = fileStorageService.getFileStorageByFileId(fileId);
                if (file == null) {
                    log.error("## 批量下载，包含不存在文件");
                    continue;
                }
                InputStream inputStream = getInputStream(file.getStorageType(), file.getFileUrl());
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                try {
                    String originalFileName = file.getFileName() + Constants.DOT + file.getExtendName();
                    String zipEntryName = originalFileName;

                    // 处理名称重复的文件
                    int counter = 1;
                    while (addedEntries.contains(zipEntryName)) {
                        String nameWithoutExt = file.getFileName();
                        String ext = file.getExtendName();
                        zipEntryName = nameWithoutExt + "(" + counter + ")" + Constants.DOT + ext;
                        counter++;
                    }
                    addedEntries.add(zipEntryName);

                    zos.putNextEntry(new ZipEntry(zipEntryName));
                    byte[] buffer = new byte[1024];
                    int i = bis.read(buffer);
                    while (i != -1) {
                        buff.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    buff.flush();
                    IOUtils.closeQuietly(bis);
                }
            }
        } catch (Exception e) {
            log.error("压缩过程中出现异常:" + e);
        } finally {
            IOUtils.closeQuietly(buff);
            IOUtils.closeQuietly(zos);
        }
    }

    /**
     * 存储文档
     * @param docx
     * @return
     * @throws IOException
     */
    private String storeDocxFile(XWPFDocument docx) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        docx.write(bos);
        byte[] content = bos.toByteArray();
        String storageType = config.getStorageType();
        String fileName = IdUtils.fastSimpleUUID() + ".docx";
        String filePath = "bookmarks/" + DateUtils.datePath() + Constants.SEPARATOR + fileName;

        // 写入文件
        InputStream is = new ByteArrayInputStream(content);
        writeFile(storageType, filePath, content.length, is);

        // 插入记录
        FileStorage fileStorage = new FileStorage();
        fileStorage.setFileName(FileUtil.getName(fileName));
        fileStorage.setFileUrl(filePath);
        fileStorage.setStorageType(storageType);
        fileStorage.setExtendName(FileUtil.getExt(fileName));
        fileStorage.setFileSize((long) content.length);
        setDefaultFileStorage(fileStorage);
        fileStorageService.saveFileStorage(fileStorage);
        return fileStorage.getFileId();
    }

    /**
     * 处理文档书签
     * @param docx
     * @param data
     */
    private void processDocxBookmarks(XWPFDocument docx, BookmarkData data) {
        // 替换表格
        handleTablesBookmark(docx, data);
        // 替换段落
        handleParagraphBookmark(docx.getParagraphs(), data);
    }

    /**
     * 处理表格
     * @param docx
     * @param data
     */
    private void handleTablesBookmark(XWPFDocument docx, BookmarkData data) {
        List<XWPFTable> tableList = docx.getTables();
        if (CollectionUtils.isEmpty(tableList)) {
            return;
        }
        tableList.stream().forEach(table -> {
            int rowCount = table.getNumberOfRows();
            for (int i = 0; i < rowCount; i++) {
                XWPFTableRow row = table.getRow(i);
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    handleParagraphBookmark(cell.getParagraphs(), data);
                }
            }
        });
    }

    /**
     * 处理段落
     * @param paragraphs
     * @param data
     */
    private void handleParagraphBookmark(List<XWPFParagraph> paragraphs, BookmarkData data) {
        if (CollectionUtils.isEmpty(paragraphs)) {
            return;
        }
        for (XWPFParagraph paragraph : paragraphs) {
            List<CTBookmark> bookmarks = paragraph.getCTP().getBookmarkStartList();
            if (CollectionUtils.isEmpty(bookmarks)) {
                continue;
            }
            for (CTBookmark bookmark : bookmarks) {
                String bookmarkName = bookmark.getName();
                if (data.getBookmarks().containsKey(bookmarkName)) {
                    replaceBookmarkContent(paragraph, bookmark, data);
                }
            }
        }
    }

    /**
     * 替换书签内容<br>
     *  书签的定义可以有两种：<br>
     *      1、直接定义书签名，默认在段落的末尾追加，适用于独立成一个段落的场景<br>
     *      2、使用占位符+书签的方式，可以定位到书签的位置进行插入，适用于在段落中动态插入内容的场景（保持前、后内容不变）。
     *
     * @param paragraph
     * @param bookmark
     * @param data
     */
    private void replaceBookmarkContent(XWPFParagraph paragraph, CTBookmark bookmark, BookmarkData data) {
        String bookmarkName = bookmark.getName();
        String replacement = data.getBookmarks().get(bookmarkName) == null ? "" : data.getBookmarks().get(bookmarkName).toString();
        String fullText = paragraph.getText();
        // 查找书签占位符（需确保模板中书签格式为 ${bookmarkName}），示例：
//        在Word文档中：
//        - 输入占位符文本如：`${姓名}`
//        - 选中整个`${姓名}`（必须完整选中包括${}）
//        - 点击插入书签 -> 书签名设为`姓名` -> 确定
        String placeholder = "${" + bookmarkName + "}";
        int startIndex = fullText.indexOf(placeholder);
        if (startIndex == -1) {
            // 当前的书签独占一个段落的（或者表格中的一行），则直接插入新段落
            XWPFRun newRun = paragraph.createRun();
            saveRun(replacement, bookmarkName, newRun, data.getCustomFormats());
            return;
        }
        int endIndex = startIndex + placeholder.length();
        List<XWPFRun> runs = paragraph.getRuns();
        StringBuilder currentText = new StringBuilder();
        int runIndex = 0;
        for (; runIndex < runs.size(); runIndex++) {
            XWPFRun run = runs.get(runIndex);
            String runText = run.getText(0);
            if (StringUtils.isBlank(runText)) {
                continue;
            }
            currentText.append(runText);
            if (currentText.length() >= endIndex) {
                break;
            }
        }
        // 清除占位符
        for (int i = 0; i <= runIndex; i++) {
            if (i == runs.size()) {
                break;
            }
            XWPFRun run = runs.get(i);
            // 这里判断，只删除占位符，如果需要删除整个段落，可以去掉过滤条件
            if (StringUtils.equals(run.getText(0), placeholder)) {
                run.setText("", 0);
            }
        }
        // 插入新内容
        XWPFRun newRun = paragraph.insertNewRun(runIndex);
        saveRun(replacement, bookmarkName, newRun, data.getCustomFormats());
    }

    /**
     * 保存新文本
     * @param replacement
     * @param run
     */
    private void saveRun(String replacement, String bookmarkName,  XWPFRun run, Map<String, FontStyle> customFormats) {
        // 字体格式
        if (customFormats != null && customFormats.containsKey(bookmarkName)) {
            setFontStyle(run, customFormats.get(bookmarkName));
        }
        if (replacement.contains("\n")) {
            String[] lines = replacement.split("\n");
            for (String line : lines) {
                run.setText(line);
                run.addBreak();
            }
            return;
        }
        run.setText(replacement);
    }

    /**
     * 设置字体样式
     * @param run
     * @param style
     */
    private void setFontStyle(XWPFRun run, FontStyle style) {
        String fontFamily = convertChineseFont(style.getFontName());
        if (fontFamily != null) {
            run.setFontFamily(fontFamily);
        }
        if (style.getFontSize() > 0) {
            run.setFontSize(style.getFontSize());
        }
        if (style.getColor() != null) {
            run.setColor(style.getColor().replace("#", ""));
        }
        run.setBold(style.isBold());
    }

    /**
     * 将中文字体转换为系统支持的字体
     * @param fontName
     * @return
     */
    private String convertChineseFont(String fontName) {
        if (fontName == null) {
            return null;
        }
        switch (fontName) {
            case "宋体":
                fontName = "SimSun";
                break;
            case "黑体":
                fontName = "SimHei";
                break;
            case "楷体":
                fontName = "KaiTi";
                break;
            default:
        };
        return fontName;
    }

    /**
     * 根据文件扩展名判断MIME类型
     */
    private String determineImageMimeType(String extendName) {
        switch (extendName.toLowerCase()) {
            case "png": return "image/png";
            case "jpg":
            case "jpeg": return "image/jpeg";
            case "gif": return "image/gif";
            case "bmp": return "image/bmp";
            case "webp": return "image/webp";
            default:
                throw new IllegalArgumentException("不支持的图片格式: " + extendName);
        }
    }
}
