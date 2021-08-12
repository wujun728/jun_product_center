package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据表标记
 * 可以打上配置表,业务表,统计表等标签
 */
@Data
public class TableMark {
    @NotNull
    private String connName;
    @Valid
    private ActualTableName actualTableName;
    private Set<String> tags = new HashSet<>();

    public TableMark() {
    }

    public TableMark(String connName, ActualTableName actualTableName) {
        this.connName = connName;
        this.actualTableName = actualTableName;
    }

    /**
     * 表是否弃用
     * @return
     */
    public boolean isDeprecated(){
        return this.tags != null && tags.contains("deprecated");
    }
}
