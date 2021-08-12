package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysDictType;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Describe: 字典类型接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysDictTypeMapper {

    /**
     * Describe: 查询字典类型列表
     * Param: SysDictType
     * Return: List<SysDictType>
     * */
    List<SysDictType> selectList(SysDictType sysDictType);

    /**
     * Describe: 根据 id 查询字典类型
     * Param: id
     * Return: SysDictType
     * */
    SysDictType selectById(String id);

    /**
     * Describe: 插入字典类型
     * Param: SysDictType
     * Return: Integer
     * */
    Integer insert(SysDictType sysDictType);

    /**
     * Describe: 根据 Id 修改字典类型
     * Param: SysDictType
     * Return: 执行结果
     * */
    Integer updateById(SysDictType sysDictType);

    /**
     * Describe: 根据 id 删除字典类型
     * Param: id
     * Return: 执行结果
     * */
    Integer deleteById(String id);
}
