package com.ruoyi.file.business.service;

import java.util.List;
import com.ruoyi.file.business.domain.FileStorage;

/**
 * 文件存储Service接口
 * 
 * @author wocurr.com
 */
public interface IFileStorageService {
    /**
     * 查询文件存储
     * 
     * @param id 文件存储主键
     * @return 文件存储
     */
    public FileStorage getFileStorageById(String id);

    /**
     * 根据文件id查询文件存储
     * @param fileId
     * @return
     */
    public FileStorage getFileStorageByFileId(String fileId);

    /**
     * 根据文件路径查询文件存储
     * @param fileUrl
     * @return
     */
    public FileStorage getFileStorageByFileUrl(String fileUrl);

    /**
     * 根据文件唯一码查询文件存储
     * @param identifier
     * @return
     */
    public FileStorage getFileStorageByIdentifier(String identifier);

    /**
     * 查询文件存储列表
     * 
     * @param fileStorage 文件存储
     * @return 文件存储集合
     */
    public List<FileStorage> listFileStorage(FileStorage fileStorage);

    /**
     * 根据条件查询文件（条件都是eq）
     * @param fileStorage
     * @return
     */
    public List<FileStorage> listByEqConditions(FileStorage fileStorage);

    /**
     * 根据id集合查询文件
     * @param ids
     * @return
     */
    public List<FileStorage> listByIds(List<String> ids);

    /**
     * 新增文件存储
     * 
     * @param fileStorage 文件存储
     * @return 结果
     */
    public int saveFileStorage(FileStorage fileStorage);

    /**
     * 修改文件存储
     * 
     * @param fileStorage 文件存储
     * @return 结果
     */
    public int updateFileStorage(FileStorage fileStorage);

    /**
     * 根据文件id修改文件存储
     * @param fileStorage
     * @return
     */
    public int updateByFileId(FileStorage fileStorage);

    /**
     * 批量删除文件存储
     * 
     * @param ids 需要删除的文件存储主键集合
     * @return 结果
     */
    public int deleteFileStorageByIds(String[] ids);

    /**
     * 删除文件存储信息
     * 
     * @param fileId 文件id
     * @return 结果
     */
    public int deleteByFileId(String fileId);

    /**
     * 更新文件排序
     * @param fileStorage
     * @return
     */
    public int updateFileSort(FileStorage fileStorage);
}
