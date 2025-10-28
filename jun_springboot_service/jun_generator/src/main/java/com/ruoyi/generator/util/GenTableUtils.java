package com.ruoyi.generator.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.core.env.Environment;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GenTableUtils {

    private static final String DATASOURCE_DYNAMIC_KEY = "spring.datasource.druid.master";

    public static Environment environment = SpringUtils.getBean(Environment.class);

    public static List<TableInfo> getTableList(String nameLike, String commentLike) {
        List<TableInfo> tables = getTableList0(null);
        return tables.stream().filter(tableInfo -> (StrUtil.isEmpty(nameLike) || tableInfo.getName().contains(nameLike))
                && (StrUtil.isEmpty(commentLike) || tableInfo.getComment().contains(commentLike)))
                .collect(Collectors.toList());
    }

    public static TableInfo getTable(String name) {
        return CollUtil.getFirst(getTableList0(name));
    }

    public static List<TableInfo> getTableList0(String name) {
        String url = environment.getProperty(DATASOURCE_DYNAMIC_KEY + ".url");
        String username = environment.getProperty(DATASOURCE_DYNAMIC_KEY + ".username");
        String password = environment.getProperty(DATASOURCE_DYNAMIC_KEY + ".password");
        // 使用 MyBatis Plus Generator 解析表结构
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username,
                password).build();
        StrategyConfig.Builder strategyConfig = new StrategyConfig.Builder();
        if (StrUtil.isNotEmpty(name)) {
            strategyConfig.addInclude(name);
        }
        // 只使用 Date 类型，不使用 LocalDate
        GlobalConfig globalConfig = new GlobalConfig.Builder().dateType(DateType.ONLY_DATE).build();
        ConfigBuilder builder = new ConfigBuilder(null, dataSourceConfig, strategyConfig.build(),
                null, globalConfig, null);
        // 按照名字排序
        List<TableInfo> tables = builder.getTableInfoList();
        tables.sort(Comparator.comparing(TableInfo::getName));
        return tables;
    }

}
