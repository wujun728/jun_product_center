package com.ruoyi.file.business.api;

import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.module.BookmarkData;
import com.ruoyi.file.business.module.FileQO;
import com.ruoyi.file.storage.copy.domain.CopyFile;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 文件服务接口<br>
 *  前端使用的是vue-simple-uploader插件，前端文件上传时，会先调用文件秒传接口，判断文件是否已存在，存在则直接返回文件信息，不存在则调用文件上传接口，上传文件。
 * @Author wocurr.com
 */
public interface IFileService {

    /**
     * 文件上传
     * @return 返回文件fileId
     */
    public String uploadFile(UploadFile uploadFile);

    /**
     * 文件秒传<br>
     * 1、相同文件copy一份；
     * 2、插入数据库文件信息；
     * @param uploadFile
     * @return
     */
    public List<UploadFileResult> uploadFileSpeed(UploadFile uploadFile);

    /**
     * 文件秒传（相同文件copy一份）
     * @param uploadFile
     * @return
     */
    public UploadFileResult uploadFileSpeedAndCopy(UploadFile uploadFile);

    /**
     * 文件上传，兼容普通上传、分片上传
     * @param request
     * @param uploadFile
     */
    public List<UploadFileResult> uploadFile(HttpServletRequest request, UploadFile uploadFile);

    /**
     * 文件下载
     * @param httpServletResponse
     * @param fileQO
     */
    public void downloadFile(HttpServletResponse httpServletResponse, FileQO fileQO);

    /**
     * 批量下载文件
     * @param httpServletResponse
     * @param fileQO
     */
    public void bathDownloadFile(HttpServletResponse httpServletResponse, FileQO fileQO);

    /**
     * 文件预览
     * @param httpServletResponse
     * @param fileQO
     */
    public void previewFile(HttpServletResponse httpServletResponse, FileQO fileQO);

    /**
     * office文档预览
     * @param httpServletResponse
     * @param fileQO
     */
    public void officePreviewFile(HttpServletResponse httpServletResponse, FileQO fileQO);

    /**
     * 写入文件
     * @param storageType
     * @param fileUrl
     * @param inputStream
     */
    public void writeFile(String storageType, String fileUrl, long fileSize, InputStream inputStream);

    /**
     * 删除文件
     * @param fileQO
     */
    public void deleteFile(FileQO fileQO);

    /**
     * 批量删除
     * @param fileQO
     */
    public void batchDeleteFile(FileQO fileQO);

    /**
     * 修改文件内容
     * @param fileQO
     * @return 修改后文件大小
     */
    public long modifyFile(FileQO fileQO);

    /**
     * 文件复制（复制已存在的文件）<br>
     *   1、复制物理文件；
     *   2、插入数据库文件信息；
     * @param fileId
     * @return 返回文件信息对象
     */
    public FileStorage copyFile(String fileId);

    /**
     * 文件复制
     * @return 文件url
     */
    public String copyFile(String storageType, String fileUrl, String fileName, String fileExt, long fileSize);

    /**
     * 创建office文件
     * @param qo
     * @return
     */
    public FileStorage createOfficeFile(FileQO qo);

    /**
     * 创建报表文件<br>
     *  1、此方法提供报表使用；
     *  2、根据报表文件名，查询是否存在，存在则更新，否则新增
     * @param fileQO
     */
    public FileStorage createReportFile(FileQO fileQO);

    /**
     * 获取文件流
     * @param fileQO
     * @return
     */
    public InputStream getInputStream(FileQO fileQO);

    /**
     * 文件重命名
     * @param fileQO
     */
    public int rename(FileQO fileQO);

    /**
     * 文件排序
     * @param fileQO
     * @return
     */
    public int sort(FileQO fileQO);

    /**
     * Word书签替换（本方法只支持docx格式，doc格式的需要自行实现）
     * @param templateFileId 模板文件ID
     * @param data 包含书签键值对和格式设置的数据对象
     * @return 替换后的新文件ID
     */
    public String wordBookmarks(String templateFileId, BookmarkData data);

    /**
     * 获取图片base64
     * @param fileId
     * @return
     */
    public String getImageBase64(String fileId);
}
