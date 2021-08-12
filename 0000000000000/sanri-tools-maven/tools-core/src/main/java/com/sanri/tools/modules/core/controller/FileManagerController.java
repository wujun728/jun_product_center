package com.sanri.tools.modules.core.controller;

import com.sanri.tools.modules.core.exception.ToolException;
import com.sanri.tools.modules.core.service.file.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Controller
@RequestMapping("/file/manager")
@Validated
public class FileManagerController {
    @Autowired
    FileManager fileManager;

    /**
     * 给定的 baseName 相对于临时目录如果是文件直接下载
     * 如果是目录,查看里面是不是只有一个文件,如果只有一个文件则下载那个文件
     * 如果是目录,但目录里面有很多文件,则打包成 zip 下载
     * @param baseName
     */
    @GetMapping("/download")
    public ResponseEntity download(@NotNull String baseName, HttpServletResponse response) throws IOException {
        Resource resource = fileManager.relativeResource(baseName);
        if (resource == null){
            throw new ToolException("未找到资源 "+baseName);
        }

        String filenameEncode = new String(resource.getFilename().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", filenameEncode);
        headers.add("fileName",filenameEncode);
        headers.add("Access-Control-Expose-Headers", "fileName");
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Content-Disposition", "attachment; filename=" + filenameEncode);
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add("Last-Modified", new Date().toString());
//        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
        return body;
    }
}
