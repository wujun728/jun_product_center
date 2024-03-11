package com.jun.plugin.generator.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.generator.code.BeanColumn;
import com.jun.plugin.generator.code.TsysTables;
import com.jun.plugin.generator.entity.SysGenerator;
import com.jun.plugin.generator.mapper.GeneratorMapper;
import com.jun.plugin.generator.service.ISysGeneratorService;
import com.jun.plugin.generator.utils.GenUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
@Slf4j
public class SysGeneratorServiceImpl implements ISysGeneratorService {
    @Resource
    GeneratorMapper generatorMapper;

//    public SysGeneratorServiceImpl(GeneratorMapper generatorMapper) {
//        this.generatorMapper = generatorMapper;
//    }

    @Override
    public IPage<SysGenerator> selectAllTables(Page<SysGenerator> page, SysGenerator vo) {
        return generatorMapper.selectAllTables(page, vo);
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip,false);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
    @Override
    public Map<String, String> queryTable(String tableName) {
        return generatorMapper.queryTable(tableName);
    }
    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorMapper.queryColumns(tableName);
    }



    /**
     * 查询具体某表信息
     *
     * @param tableName
     * @return
     */
    @Override
    public List<TsysTables> queryList(String tableName) {
        return generatorMapper.queryList(tableName);
    }

    /**
     * 查询表详情
     *
     * @param tableName
     * @return
     */
    @Override
    public List<BeanColumn> queryColumns2(String tableName) {
        System.out.println("queryColumns2>>>" + JSONUtil.toJsonPrettyStr(generatorMapper.queryColumns3(tableName)));
        return generatorMapper.queryColumns2(tableName);
    }


}
