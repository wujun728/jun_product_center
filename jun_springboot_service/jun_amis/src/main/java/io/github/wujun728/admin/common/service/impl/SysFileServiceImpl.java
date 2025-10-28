package io.github.wujun728.admin.common.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import io.github.wujun728.admin.common.config.FileConfig;
import io.github.wujun728.admin.common.data.SysFile;
import io.github.wujun728.admin.common.service.SysFileService;
import io.github.wujun728.admin.db.service.JdbcService;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

/**
 * @author hyz
 * @date 2021/6/17 13:55
 */
@Service("sysFileService")
@Slf4j
public class SysFileServiceImpl implements SysFileService {

    @Resource
    private JdbcService jdbcService;
    @Resource
    private FileConfig fileConfig;
    private static final String TYPE_LOCAL = "local";
    private static final String TYPE_OSS = "oss";
    private static final String TYPE_MINIO = "minio";

    private MinioClient minioClient;
    private OSS oss;

    public MinioClient getMinioClient() {
        if(minioClient == null){
            synchronized (this){
                if(minioClient == null){
                    try {
                        minioClient = new MinioClient(fileConfig.getOssEndpoint(),fileConfig.getOssKey(),fileConfig.getOssSecret());
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("minioClient创建失败",e);
                    }
                }
            }
        }
        return minioClient;
    }
    public OSS getOss() {
        if(oss == null){
            synchronized (this){
                if(oss == null){
                    try {
                        oss = new OSSClientBuilder().build(fileConfig.getOssEndpoint(),fileConfig.getOssKey(),fileConfig.getOssSecret());
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("oss创建失败",e);
                    }
                }
            }
        }
        return oss;
    }

    @Override
    public SysFile upload(InputStream in, String fileName, String contentType) {
        SysFile sysFile = new SysFile();
        try {
            sysFile.setSize((long) in.available());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sysFile.setFileName(fileName);
        sysFile.setUploadTime(new Date());
        sysFile.setContentType(contentType);
        String suffix = "";
        if(sysFile.getFileName().contains(".")){
            suffix = sysFile.getFileName().substring(sysFile.getFileName().lastIndexOf(".")+1).toLowerCase();
        }
        sysFile.setSuffix(suffix);

        String f1 = DateUtil.format(new Date(),"yyyy/MM/dd");
        String name = UUID.randomUUID()
                .toString()
                .replaceAll("-","")
                .toLowerCase()
                +(StringUtils.isBlank(suffix) ? "" : ("."+suffix));
        sysFile.setPath("/"+f1+"/"+name);
        if(TYPE_LOCAL.equals(fileConfig.getType())){
            File folder = new File(fileConfig.getBase()+"/"+f1);
            folder.mkdirs();
            File dest = new File(folder,name);
            try {
                FileOutputStream out = new FileOutputStream(dest);
                IoUtil.copy(in,out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传失败",e);
            }
            jdbcService.saveOrUpdate(sysFile);
            return sysFile;
        }else if(TYPE_OSS.equals(fileConfig.getType())){
            try {
                getOss().putObject(fileConfig.getOssBucketName(),fileConfig.getBase()+sysFile.getPath(),in);
                jdbcService.saveOrUpdate(sysFile);
                return sysFile;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("文件上传失败",e);
            }
        }else if(TYPE_MINIO.equals(fileConfig.getType())){
            try{
                getMinioClient().putObject(fileConfig.getOssBucketName(),fileConfig.getBase()+sysFile.getPath(), in,contentType);
                jdbcService.saveOrUpdate(sysFile);
                return sysFile;
            }catch (Exception e){
                e.printStackTrace();
                log.error("文件上传失败",e);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public SysFile upload(MultipartFile file) {
        SysFile sysFile = new SysFile();
        sysFile.setSize(file.getSize());
        sysFile.setFileName(file.getOriginalFilename());
        sysFile.setUploadTime(new Date());
        sysFile.setContentType(file.getContentType());
        String suffix = "";
        if(sysFile.getFileName().contains(".")){
            suffix = sysFile.getFileName().substring(sysFile.getFileName().lastIndexOf(".")+1).toLowerCase();
        }
        sysFile.setSuffix(suffix);

        String f1 = DateUtil.format(new Date(),"yyyy/MM/dd");
        String name = UUID.randomUUID()
                .toString()
                .replaceAll("-","")
                .toLowerCase()
                +(StringUtils.isBlank(suffix) ? "" : ("."+suffix));
        sysFile.setPath("/"+f1+"/"+name);
        if(TYPE_LOCAL.equals(fileConfig.getType())){
            File folder = new File(fileConfig.getBase()+"/"+f1);
            folder.mkdirs();
            File dest = new File(folder,name);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传失败",e);
            }
            jdbcService.saveOrUpdate(sysFile);
            return sysFile;
        }else if(TYPE_OSS.equals(fileConfig.getType())){
            try {
                getOss().putObject(fileConfig.getOssBucketName(),fileConfig.getBase()+sysFile.getPath(),file.getInputStream());
                jdbcService.saveOrUpdate(sysFile);
                return sysFile;
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传失败",e);
            }
        }else if(TYPE_MINIO.equals(fileConfig.getType())){
            try{
                getMinioClient().putObject(fileConfig.getOssBucketName(),fileConfig.getBase()+sysFile.getPath(), file.getInputStream(),file.getContentType());
                jdbcService.saveOrUpdate(sysFile);
                return sysFile;
            }catch (Exception e){
                e.printStackTrace();
                log.error("文件上传失败",e);
            }
        }
        return null;
    }

    @Override
    public void download(Long id, HttpServletRequest request, HttpServletResponse response) {
        InputStream in = download(id);
        boolean download = "true".equals(request.getParameter("download"));
        SysFile sysFile = jdbcService.getById(SysFile.class, id);
        try {
            response.setContentLength(sysFile.getSize().intValue());
            response.setContentType(sysFile.getContentType());
            if(download){
                String downloadFileName = URLEncoder.encode(sysFile.getFileName(),"UTF-8");
                response.addHeader("Content-Disposition","attachment;fileName="+downloadFileName);
            }
            ServletOutputStream out = response.getOutputStream();
            if(in != null){
                IoUtil.copy(in,out);
                IoUtil.close(in);
            }else{
                response.setStatus(404);
                response.setContentLength(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public InputStream download(Long id) {

        SysFile sysFile = jdbcService.getById(SysFile.class, id);
        InputStream in = null;
        try{
            if(TYPE_LOCAL.equals(fileConfig.getType())){
                File file = new File(fileConfig.getBase()+sysFile.getPath());
                if(file.exists()){
                    in = new FileInputStream(file);
                }
            }else if(TYPE_OSS.equals(fileConfig.getType())){
                OSSObject ossObject = getOss().getObject(fileConfig.getOssBucketName(), fileConfig.getBase() + sysFile.getPath());
                in = ossObject.getObjectContent();
            }else if(TYPE_MINIO.equals(fileConfig.getType())){
                in = getMinioClient().getObject(fileConfig.getOssBucketName(), fileConfig.getBase()+sysFile.getPath());
            }
            return in;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFile(SysFile sysFile) {
        try{
            if(TYPE_LOCAL.equals(fileConfig.getType())){
                File file = new File(fileConfig.getBase()+sysFile.getPath());
                if(file.exists()){
                    file.delete();
                }
            }else if(TYPE_OSS.equals(fileConfig.getType())){
                getOss().deleteObject(fileConfig.getOssBucketName(),fileConfig.getBase()+sysFile.getPath());
            }else if(TYPE_MINIO.equals(fileConfig.getType())){
                getMinioClient().removeObject(fileConfig.getOssBucketName(),fileConfig.getBase()+sysFile.getPath());
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除文件失败:",e);
        }
        jdbcService.delete(sysFile);
    }

    @Override
    public void shutdown() {
        if(oss != null){
            oss.shutdown();
        }
    }

    @Override
    public InputStream download(String path) {
        //path="/admin/download/{id}/{fileName}";
        String idStr = path.substring("/admin/download/".length(),path.lastIndexOf("/"));
        return download(Long.parseLong(idStr));
    }
}
