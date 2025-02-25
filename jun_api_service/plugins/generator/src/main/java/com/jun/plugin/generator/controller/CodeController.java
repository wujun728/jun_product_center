package com.jun.plugin.generator.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.extra.spring.SpringUtil;
import com.jun.plugin.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/code")
public class CodeController {

    //RestServiceController

    @GetMapping("/getTableList")
    public Result getTableList(){
        DataSource dataSource = SpringUtil.getBean(DataSource.class);
        List<String> tables = MetaUtil.getTables(dataSource);
        List<Map> tablemap = new ArrayList<>();
        for(String tab : tables){
            Table table = MetaUtil.getTableMeta(dataSource,tab);
            Map map = new HashMap(){{
                put("tableName",table.getTableName());
                put("tableComment",table.getComment());
                put("createTime", DateUtil.now());
                put("updateTime", DateUtil.now());
            }};
            tablemap.add(map);
        }
        return Result.success(tablemap);
    }

}
