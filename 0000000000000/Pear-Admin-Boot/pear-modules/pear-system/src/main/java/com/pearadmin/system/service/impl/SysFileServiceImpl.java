package com.pearadmin.system.service.impl;

import com.pearadmin.common.config.proprety.TemplateProperty;
import com.pearadmin.common.constant.SystemConstant;
import com.pearadmin.common.tools.common.FileUtil;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.tools.servlet.ServletUtil;
import com.pearadmin.system.domain.SysFile;
import com.pearadmin.system.mapper.SysFileMapper;
import com.pearadmin.system.service.ISysFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 文件服务接口实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@Slf4j
@Service("SysFileServiceImpl")
public class SysFileServiceImpl implements ISysFileService {

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

    /**
     * Describe: 文 件 夹 列 表
     * Param: File
     * Return: id
     */
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

    /**
     * Describe: 文件上传
     * Param: File
     * Return: id
     */
    @Override
    @Transactional
    public String upload(MultipartFile file) {
        String result = "";
        try {
            String fileId = SequenceUtil.makeStringId();
            String name = file.getOriginalFilename();
            assert name != null;
            String suffixName = name.substring(name.lastIndexOf("."));
            String fileName = fileId + suffixName;
            String fileDir = LocalDate.now().toString();
            String parentPath = uploadProperty.getUploadPath() + fileDir;
            java.io.File filepath = new java.io.File(parentPath, fileName);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            file.transferTo(filepath);

            SysFile fileDomain = new SysFile();
            fileDomain.setId(fileId);
            fileDomain.setFileDesc(name);
            fileDomain.setFileName(fileName);
            fileDomain.setTargetDate(LocalDate.now());
            fileDomain.setFilePath(filepath.getPath());
            fileDomain.setCreateTime(LocalDateTime.now());
            fileDomain.setFileSize(FileUtil.getPrintSize(filepath.length()));
            fileDomain.setFileType(suffixName.replace(".", ""));
            int flag = fileMapper.insert(fileDomain);
            if (flag > 0) {
                result = fileId;
            } else {
                result = "";
            }
        } catch (Exception e) {
            log.error("failed to upload file.detail message:{}", e.getMessage());
        }
        return result;
    }

    /**
     * Describe: 根据 Id 下载文件
     * Param: id
     * Return: IO
     */
    @Override
    public void download(String id) {
        try {
            SysFile file = fileMapper.selectById(id);
            if( null==file ){
                file = new SysFile();
                file.setFilePath(SystemConstant.EMPTY);
            }
            java.io.File files = new java.io.File(file.getFilePath());
            if (files.exists()) {
                FileCopyUtils.copy(new FileInputStream(file.getFilePath()), ServletUtil.getResponse().getOutputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Describe: 查 询 文 件 列 表
     * Param: id
     * Return: File
     */
    @Override
    public List<SysFile> data() {
        return fileMapper.selectList();
    }


    /**
     * Describe: 根据 Id 删除文件信息
     * Param: id
     * Return: int
     */
    @Override
    public boolean remove(String id) {
        SysFile file = fileMapper.selectById(id);
        boolean fileDeleteResult;
        //如果文件不存在
        if (file != null && file.getFilePath() != null) {
            File deleteFile;
            if ((deleteFile = new File(file.getFilePath())).exists()) {
                fileDeleteResult = deleteFile.delete();
            } else {
                fileDeleteResult = false;
            }
        } else {
            fileDeleteResult = false;
        }
        if (fileDeleteResult) {
            log.warn("fileId:{} ,need delete file:{} not exists ", id, file.getFilePath());
        }
        return fileMapper.deleteById(id) > 0;
    }
}
