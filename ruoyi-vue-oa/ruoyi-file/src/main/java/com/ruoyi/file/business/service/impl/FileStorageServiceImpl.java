package com.ruoyi.file.business.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.file.business.mapper.FileStorageMapper;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.service.IFileStorageService;

/**
 * 文件存储Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class FileStorageServiceImpl implements IFileStorageService {
    @Autowired
    private FileStorageMapper fileStorageMapper;

    /**
     * 查询文件存储
     * 
     * @param id 文件存储主键
     * @return 文件存储
     */
    @Override
    public FileStorage getFileStorageById(String id) {
        return fileStorageMapper.selectFileStorageById(id);
    }

    @Override
    public FileStorage getFileStorageByFileId(String fileId) {
        return fileStorageMapper.selectFileStorageByFileId(fileId);
    }

    @Override
    public FileStorage getFileStorageByFileUrl(String fileUrl) {
        return fileStorageMapper.selectFileStorageByFileUrl(fileUrl);
    }

    @Override
    public FileStorage getFileStorageByIdentifier(String identifier) {
        return fileStorageMapper.selectFileStorageByIdentifier(identifier);
    }

    /**
     * 查询文件存储列表
     * 
     * @param fileStorage 文件存储
     * @return 文件存储
     */
    @Override
    public List<FileStorage> listFileStorage(FileStorage fileStorage) {
        return fileStorageMapper.selectFileStorageList(fileStorage);
    }

    /**
     * 条件查询文件存储列表
     * @param fileStorage
     * @return
     */
    public List<FileStorage> listByEqConditions(FileStorage fileStorage) {
        return fileStorageMapper.listByEqConditions(fileStorage);
    }

    @Override
    public List<FileStorage> listByIds(List<String> ids) {
        return fileStorageMapper.listByIds(ids);
    }

    /**
     * 新增文件存储
     * 
     * @param fileStorage 文件存储
     * @return 结果
     */
    @Override
    public int saveFileStorage(FileStorage fileStorage) {
        fileStorage.setId(IdUtils.fastSimpleUUID());
        fileStorage.setCreateId(SecurityUtils.getUserId());
        fileStorage.setCreateTime(DateUtils.getNowDate());
        return fileStorageMapper.insertFileStorage(fileStorage);
    }

    /**
     * 修改文件存储
     * 
     * @param fileStorage 文件存储
     * @return 结果
     */
    @Override
    public int updateFileStorage(FileStorage fileStorage) {
        fileStorage.setUpdateId(SecurityUtils.getUserId());
        fileStorage.setUpdateBy(SecurityUtils.getUsername());
        fileStorage.setUpdateTime(DateUtils.getNowDate());
        return fileStorageMapper.updateFileStorage(fileStorage);
    }

    @Override
    public int updateByFileId(FileStorage fileStorage) {
        if (fileStorage == null || StringUtils.isBlank(fileStorage.getFileId())) {
            return 0;
        }
        fileStorage.setGenPreviewTime(LocalDateTime.now());
        return fileStorageMapper.updateByFileId(fileStorage);
    }

    /**
     * 批量删除文件存储
     * 
     * @param ids 需要删除的文件存储主键
     * @return 结果
     */
    @Override
    public int deleteFileStorageByIds(String[] ids) {
        return fileStorageMapper.deleteFileStorageByIds(ids);
    }

    /**
     * 删除文件存储信息
     * 
     * @param fileId 文件存储主键
     * @return 结果
     */
    @Override
    public int deleteByFileId(String fileId) {
        return fileStorageMapper.deleteByFileId(fileId);
    }

    @Override
    public int updateFileSort(FileStorage fileStorage) {
        if (StringUtils.isBlank(fileStorage.getFileId())) {
            throw new BaseException("参数错误");
        }
        return fileStorageMapper.updateFileSort(fileStorage);
    }
}
