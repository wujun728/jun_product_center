package com.pearadmin.generate.service;

import com.pearadmin.generate.domain.GenTableColumn;
import java.util.List;

/**
 * Describe: 业务表字段服务层
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 * */
public interface IGenTableColumnService
{
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
     * 删除业务字段信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteGenTableColumnByIds(String ids);
}
