package com.sanri.tools.modules.websocket.service.controller;

import com.sanri.tools.modules.websocket.service.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/chat/storage")
@Controller
public class ChatStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    /**
     * 客户端上传文件
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public List<String> uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            Path path = fileStorageService.uploadFile(file);
            paths.add(path.toString());
        }

        return paths;
    }


    /**
     * 查看图片
     * @param relativePath
     * @return
     * @throws IOException
     */
    @GetMapping("/viewImage")
    public ResponseEntity viewImage(String relativePath) throws IOException {
        Resource resource = fileStorageService.viewImage(relativePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
        return body;
    }
}
