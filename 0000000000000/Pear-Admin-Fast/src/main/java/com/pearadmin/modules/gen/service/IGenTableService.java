package com.pearadmin.modules.gen.service;

import com.pearadmin.modules.gen.domain.GenTable;

import java.util.List;
import java.util.Map;

/**
 * Describe: 业务表服务接口
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 * */
public interface IGenTableService
{
    /**
     * 查询业务列表
     * 
     * @param genTable 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询据库列表
     * 
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询据库列表
     * 
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     * 
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询业务信息
     * 
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(String id);

    /**
     * 修改业务
     * 
     * @param genTable 业务信息
     * @return 结果
     */
    void updateGenTable(GenTable genTable);

    /**
     * 删除业务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    void deleteGenTableByIds(String ids);

    /**
     * 导入表结构
     * 
     * @param tableList 导入表列表
     * @param operName 操作人员
     */
    void importGenTable(List<GenTable> tableList, String operName);

    /**
     * 预览代码
     * 
     * @param tableId 表编号
     * @return 预览数据列表
     */
    Map<String, String> previewCode(String tableId);

    /**
     * 生成代码（下载方式）
     * 
     * @param tableName 表名称
     * @return 数据
     */
    byte[] downloadCode(String tableName);

    /**
     * 生成代码（自定义路径）
     * 
     * @param tableName 表名称
     * @return 数据
     */
    void generatorCode(String tableName);

    /**
     * 批量生成代码（下载方式）
     * 
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] downloadCode(String[] tableNames);

    /**
     * 修改保存参数校验
     * 
     * @param genTable 业务信息
     */
    void validateEdit(GenTable genTable);
}
