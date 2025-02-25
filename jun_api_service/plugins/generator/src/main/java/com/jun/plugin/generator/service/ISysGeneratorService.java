package com.jun.plugin.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.generator.code.BeanColumn;
import com.jun.plugin.generator.code.TsysTables;
import com.jun.plugin.generator.entity.SysGenerator;

import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface ISysGeneratorService {

    /**
     * 获取所有表
     *
     * @param page page
     * @param vo   vo
     * @return IPage
     */
    IPage<SysGenerator> selectAllTables(Page<SysGenerator> page, SysGenerator vo);

    /**
     * 生成代码
     *
     * @param tables tables
     * @return byte[]
     */
    byte[] generatorCode(String[] tables);


    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    /**
     * 查询具体某表信息
     *
     * @param tableName
     * @return
     */
    public List<TsysTables> queryList(String tableName);

    /**
     * 查询表详情
     *
     * @param tableName
     * @return
     */
    public List<BeanColumn> queryColumns2(String tableName);



}
