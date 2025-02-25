package com.jun.plugin.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.generator.code.BeanColumn;
import com.jun.plugin.generator.code.TsysTables;
import com.jun.plugin.generator.entity.SysGenerator;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 代码生成 Mapper
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface GeneratorMapper extends BaseMapper<SysGenerator> {

    IPage<SysGenerator> selectAllTables(Page<SysGenerator> page, @Param(value = "vo") SysGenerator vo);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);


    /**
     * 查询当前所有表
     * @param tableName 条件搜索
     * @return
     */
    List<TsysTables> queryList(String tableName);

    /**
     * 查询表详情
     * @param tableName
     * @return
     */
    List<BeanColumn> queryColumns2(String tableName);
    List<Map<String, String>> queryColumns3(String tableName);
}
