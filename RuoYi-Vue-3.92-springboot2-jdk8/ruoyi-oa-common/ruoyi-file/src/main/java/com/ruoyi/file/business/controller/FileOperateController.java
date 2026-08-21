package com.ruoyi.file.business.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.file.business.api.IFileService;
import com.ruoyi.file.business.module.FileQO;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.file.storage.upload.domain.UploadFileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件操作
 *
 * @Author wocurr.com
 */
@RestController
@RequestMapping("/file/operate")
public class FileOperateController {

    @Autowired
    private IFileService fileService;

    /**
     * 极速上传
     * @param uploadFile
     * @return
     */
    @GetMapping("/uploadfile")
    public AjaxResult uploadFileSpeed(UploadFile uploadFile) {
        List<UploadFileResult> result = fileService.uploadFileSpeed(uploadFile);
        return AjaxResult.success(result);
    }

    /**
     * 文件上传（兼容普通上传、分片上传）
     *
     * @param request
     * @param uploadFile
     * @return
     */
    @PostMapping("/uploadfile")
    public AjaxResult uploadFile(HttpServletRequest request, UploadFile uploadFile) {
        List<UploadFileResult> result = fileService.uploadFile(request, uploadFile);
        return AjaxResult.success(result);
    }

    /**
     * 文件下载
     * @param httpServletResponse
     * @param fileQO
     */
    @PostMapping(value = "/downloadfile")
    public void downloadFile(HttpServletResponse httpServletResponse, FileQO fileQO) {
        fileService.downloadFile(httpServletResponse, fileQO);
    }

    /**
     * 文件下载
     * @param httpServletResponse
     * @param fileQO
     */
    @GetMapping(value = "/downloadfile/v1")
    public void downloadFileV1(HttpServletResponse httpServletResponse, FileQO fileQO) {
        fileService.downloadFile(httpServletResponse, fileQO);
    }

    /**
     * 文件批量下载
     * @param httpServletResponse
     * @param fileQO
     */
    @PostMapping(value = "/batchDownloadFile")
    public void batchDownloadFile(FileQO fileQO, HttpServletResponse httpServletResponse) {
        fileService.bathDownloadFile(httpServletResponse, fileQO);
    }

    /**
     * 文件预览
     * @param httpServletResponse
     * @param fileQO
     */
    @GetMapping("/preview")
    public void preview(HttpServletResponse httpServletResponse, FileQO fileQO) {
        fileService.previewFile(httpServletResponse, fileQO);
    }

    /**
     * office文件预览<br>
     *  作用：
     *      用于onlyoffice预览、编辑office文档
     * @param httpServletResponse
     * @param fileQO
     */
    @GetMapping("/office/preview")
    public void officePreview(HttpServletResponse httpServletResponse, FileQO fileQO) {
        fileService.officePreviewFile(httpServletResponse, fileQO);
    }

    /**
     * 文件重命名
     *
     * @param fileQO
     * @return
     */
    @PostMapping(value = "/rename")
    public AjaxResult rename(@RequestBody FileQO fileQO) {
        int row = fileService.rename(fileQO);
        return row > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 文件排序
     *
     * @param fileQO
     * @return
     */
    @PostMapping(value = "/sort")
    public AjaxResult sort(@RequestBody FileQO fileQO) {
        int row = fileService.sort(fileQO);
        return row > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 删除文件
     *
     * @param fileQO
     * @return
     */
    @PostMapping(value = "/delFile")
    public AjaxResult delFile(@RequestBody FileQO fileQO) {
        fileService.deleteFile(fileQO);
        return AjaxResult.success();
    }
}
