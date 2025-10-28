package io.github.wujun728.admin.common.service;

import io.github.wujun728.admin.common.data.SysFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author hyz
 * @date 2021/6/17 11:18
 */
public interface SysFileService {
    SysFile upload(MultipartFile file);
    SysFile upload(InputStream in,String fileName,String contentType);
    void download(Long id, HttpServletRequest request, HttpServletResponse response);
    InputStream download(Long id);
    InputStream download(String path);
    void deleteFile(SysFile sysFile);
    void shutdown();
}
