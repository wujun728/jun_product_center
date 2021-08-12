package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Describe: 文件服务接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysFileMapper {

    /**
     * Describe: 插入文件信息
     * Param: File
     * Return: int
     * */
    int insert(SysFile file);

    /**
     * Describe: 查询文件列表
     * Param: null
     * Return: List<File>
     * */
    List<SysFile> selectList();

    /**
     * Describe: 根据 Id 查询文件信息
     * Param: id
     * Return: File
     * */
    SysFile selectById(@Param("id") String id);

    /**
     * Describe: 根据 Id 删除文件信息
     * Param: id
     * Return: int
     * */
    int deleteById(@Param("id") String id);

}
