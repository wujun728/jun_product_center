package com.pearadmin.generate.mapper;

import com.pearadmin.generate.domain.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Describe: 业务表字段接口
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface GenTableColumnMapper
{
    /**
     * 根据表名称查询列信息
     * 
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
    
    /**
     * 查询业务字段列表
     * 
     * @param genTableColumn 业务字段信息
     * @return 业务字段集合
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(GenTableColumn genTableColumn);

    /**
     * 新增业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 修改业务字段
     * 
     * @param genTableColumn 业务字段信息
     * @return 结果
     */
    int updateGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 批量删除业务字段
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteGenTableColumnByIds(String[] ids);
}