package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysDictData;
import com.pearadmin.system.domain.SysDictType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Describe: 字典数据接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysDictDataMapper {
    /**
     * 通过字典code获取字典数据
     * @param typeCode
     * @return
     */
    List<SysDictData> selectByCode(@Param("typeCode") String typeCode);
    /**
     * Describe: 查询字典数据信息
     * Param: SysDictData
     * Return: List<SysDictData>
     * */
    List<SysDictData> selectList(SysDictData sysDictData);

    /**
     * Describe: 插入字典数据信息
     * Param: SysDictData
     * Return: Integer
     * */
    Integer insert(SysDictData sysDictData);

    /**
     * Describe: 根据 Id 查询字典数据
     * Param: id
     * Return: SysDictData
     * */
    SysDictData selectById(String id);

    /**
     * Describe: 根据 Id 修改数据字典数据
     * Param: id
     * Return: Boolean
     * */
    Integer updateById(SysDictData sysDictData);

    /**
     * Describe: 根据 Id 删除字典数据
     * Param: id
     * Return: 执行结果
     * */
    Integer deleteById(String id);

    /**
     * Describe: 根据 code 删除字典数据
     * Param: id
     * Return 执行结果
     * */
    Integer deleteByCode(String code);


    /**
     * 通过查询指定table的 text code key 获取字典值
     * @param table 表名
     * @param text label
     * @param code value
     * @return
     */
    public List<SysDictData> queryTableDictItemsByCode(@Param("table") String table,@Param("text") String text,@Param("code") String code);

    /**
     * 通过查询指定table的 text code 获取字典（指定查询条件）
     * @param table 表名
     * @param text label
     * @param code value
     * @return
     */
    public List<SysDictData> queryTableDictItemsByCodeAndFilter(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("filterSql") String filterSql);

    /**
     * 通过查询指定table的 text code key 获取字典值，包含value
     * @param table 表名
     * @param text label
     * @param code value
     * @param keyArray values
     * @return
     */
    public List<SysDictData> queryTableDictByKeys(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("keyArray") String[] keyArray);

}
