package com.sanri.tools.modules.websocket.service.storage;

import com.sanri.tools.modules.core.service.file.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 文件和图片存储服务
 */
@Slf4j
@Service
public class FileStorageService {
    @Autowired
    private FileManager fileManager;
    private IdGenerator idGenerator = new SimpleIdGenerator();

    /**
     * 上传文件 , 相对于临时文件的文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public Path uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        File chatDir = fileManager.mkTmpDir("chat");
        String sequence = idGenerator.generateId().toString().replaceAll("-", "");
        String extension = FilenameUtils.getExtension(originalFilename);
        File targetFile = new File(chatDir, sequence + "." + extension);
        targetFile.getParentFile().mkdirs();
        multipartFile.transferTo(targetFile);
        return fileManager.relativePath(targetFile.toPath());
    }

    /**
     * 获取图片资源
     * @param relativePath
     * @return
     */
    public Resource viewImage(String relativePath) {
        Resource resource = fileManager.relativeResource(relativePath);
        return resource;
    }
}
