package com.itcqm.cake21.controller;

import com.itcqm.commons.upload.FileUpload;
import com.itcqm.commons.util.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author CQM
 * @since 2020/9/10
 */
@RestController
@RequestMapping("/file")
public class FileController extends FileUpload {

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile files) throws Exception {
        String serverPath = "E:/项目实战/SpringBoot开发实战/spring-boot-uniapp-21cake/src/main/resources/static/upload/";
        return CommonResult.success("上传成功!", "localhost:12301/upload" + super.upload(files, serverPath));
    }
}