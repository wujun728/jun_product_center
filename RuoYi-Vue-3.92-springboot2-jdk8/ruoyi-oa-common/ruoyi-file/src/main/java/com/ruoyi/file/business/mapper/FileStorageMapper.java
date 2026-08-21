package com.ruoyi.file.business.mapper;

import java.util.List;
import com.ruoyi.file.business.domain.FileStorage;

/**
 * 文件存储Mapper接口
 * 
 * @author wocurr.com
 */
public interface FileStorageMapper {
    /**
     * 查询文件存储
     * 
     * @param id 文件存储主键
     * @return 文件存储
     */
    public FileStorage selectFileStorageById(String id);

    /**
     * 根据文件id查询文件存储
     * @param fileId
     * @return
     */
    public FileStorage selectFileStorageByFileId(String fileId);

    /**
     * 根据文件路径查询
     * @param fileId
     * @return
     */
    public FileStorage selectFileStorageByFileUrl(String fileUrl);

    /**
     * 根据文件唯一码查询文件存储
     * @param fileId
     * @return
     */
    public FileStorage selectFileStorageByIdentifier(String fileId);

    /**
     * 查询文件存储列表
     * 
     * @param fileStorage 文件存储
     * @return 文件存储集合
     */
    public List<FileStorage> selectFileStorageList(FileStorage fileStorage);

    /**
     * 根据条件查询（条件都是eq，非like）
     * @param fileStorage
     * @return
     */
    public List<FileStorage> listByEqConditions(FileStorage fileStorage);

    /**
     * 根据id集合
     * @param list
     * @return
     */
    public List<FileStorage> listByIds(List<String> list);

    /**
     * 新增文件存储
     * 
     * @param fileStorage 文件存储
     * @return 结果
     */
    public int insertFileStorage(FileStorage fileStorage);

    /**
     * 修改文件存储
     * 
     * @param fileStorage 文件存储
     * @return 结果
     */
    public int updateFileStorage(FileStorage fileStorage);

    /**
     * 删除文件存储
     * 
     * @param fileId 文件id
     * @return 结果
     */
    public int deleteByFileId(String fileId);

    /**
     * 批量删除文件存储
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFileStorageByIds(String[] ids);

    /**
     * 修改文件排序
     * @param fileStorage
     * @return
     */
    public int updateFileSort(FileStorage fileStorage);

    /**
     * 根据fileId更新
     * @param fileStorage
     * @return
     */
    public int updateByFileId(FileStorage fileStorage);
}
