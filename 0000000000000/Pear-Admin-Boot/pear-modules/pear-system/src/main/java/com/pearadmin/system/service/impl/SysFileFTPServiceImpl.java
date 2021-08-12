package com.pearadmin.system.service.impl;

import com.pearadmin.common.config.proprety.TemplateProperty;
import com.pearadmin.common.tools.common.FTPUtil;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.tools.servlet.ServletUtil;
import com.pearadmin.system.domain.SysFile;
import com.pearadmin.system.mapper.SysFileMapper;
import com.pearadmin.system.service.ISysFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("SysFileFTPServiceImpl")
public class SysFileFTPServiceImpl implements ISysFileService {

    /**
     * 引 入 服 务
     */
    @Resource
    private SysFileMapper fileMapper;

    /**
     * 上 传 可 读 配 置
     */
    @Resource
    private TemplateProperty uploadProperty;

    @Override
    public String upload(MultipartFile file) {
        String result = "";
        FTPClient ftpClient = null;
        try {
            String fileId = SequenceUtil.makeStringId();
            String name = file.getOriginalFilename();
            String suffixName = name.substring(name.lastIndexOf("."));
            String fileName = fileId + suffixName;
            String path = LocalDate.now().toString();

            ftpClient = FTPUtil.open(uploadProperty.getHostname(), uploadProperty.getUsername(), uploadProperty.getPassword());
            if (ftpClient.isConnected()) {
                FTPUtil.upload(ftpClient, path, fileName, file.getInputStream());
            }

            SysFile fileDomain = new SysFile();
            fileDomain.setId(fileId);
            fileDomain.setFileDesc(name);
            fileDomain.setFileName(fileName);
            fileDomain.setTargetDate(LocalDate.now());
            fileDomain.setFilePath(path + "/" + fileName);
            fileDomain.setCreateTime(LocalDateTime.now());
            fileDomain.setFileSize(String.valueOf(file.getSize()));
            fileDomain.setFileType(suffixName.replace(".", ""));
            int flag = fileMapper.insert(fileDomain);
            if (flag > 0) {
                result = fileId;
            } else {
                result = "";
            }
        } catch (Exception e) {
            log.error("failed to upload file.detail message:{}", e.getMessage());
        } finally {
            FTPUtil.close(ftpClient);
        }
        return result;
    }

    @Override
    public void download(String id) {
        FTPClient ftpClient = null;
        try {
            SysFile file = fileMapper.selectById(id);
            String path = file.getFilePath();
            ftpClient = FTPUtil.open(uploadProperty.getHostname(), uploadProperty.getUsername(), uploadProperty.getPassword());
            FTPUtil.download(ftpClient, path, ServletUtil.getResponse().getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FTPUtil.close(ftpClient);
        }
    }

    @Override
    public List<SysFile> data() {
        return fileMapper.selectList();
    }

    @Override
    public boolean remove(String id) {
        SysFile file = fileMapper.selectById(id);
        //如果文件不存在
        if (file != null && file.getFilePath() != null) {
            FTPClient ftpClient = FTPUtil.open(uploadProperty.getHostname(), uploadProperty.getUsername(), uploadProperty.getPassword());
            if (ftpClient.isConnected()){
                FTPUtil.remove(ftpClient,file.getFilePath());
            }
            FTPUtil.close(ftpClient);
        } else {
            log.warn("fileId:{} ,need delete file:{} not exists ", id, file.getFilePath());
        }
        return fileMapper.deleteById(id) > 0;
    }

    @Override
    public List<String> fileDirs() {
        List<String> fileDirs = new ArrayList<>();
        java.io.File file = new java.io.File("/home/upload");
        if (file.isDirectory()) {
            java.io.File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    String dirName = files[i].getName();
                    fileDirs.add(dirName);
                }
            }
        }
        return fileDirs;
    }
}
