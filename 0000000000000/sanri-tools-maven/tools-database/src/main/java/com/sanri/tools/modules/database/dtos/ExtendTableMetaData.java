package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.database.dtos.meta.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtendTableMetaData extends TableMetaData {
    private TableMark tableMark;

    public ExtendTableMetaData(TableMark tableMark) {
        this.tableMark = tableMark;
    }

    public ExtendTableMetaData(TableMetaData tableMetaData,TableMark tableMark) {
        super(tableMetaData.getActualTableName(), tableMetaData.getTable(), tableMetaData.getColumns(), tableMetaData.getIndexs(), tableMetaData.getPrimaryKeys());
        this.tableMark = tableMark;
    }
}
