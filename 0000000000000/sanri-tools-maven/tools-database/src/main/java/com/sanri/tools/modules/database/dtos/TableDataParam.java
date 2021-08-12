package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class TableDataParam {
    @NotNull
    private String connName;
    @Valid
    private ActualTableName actualTableName;
    @Positive
    private int size = 1000;
    @Valid
    private List<ColumnMapper> columnMappers;

    @Data
    public static class ColumnMapper{
        @NotNull
        private String columnName;
        // 随机方法 , 使用 spel 表达式
        private String random;
        // 使用 sql 的方式, 我会取第 0 个字段,然后取 100 数据,然后随便选择一条数据插入; 这种在有关联关系将会很有用
        private String sql;
    }
}
