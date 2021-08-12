package com.sanri.tools.modules.database.dtos.meta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableMetaData {
    protected ActualTableName actualTableName;
    protected Table table;
    protected List<Column> columns;
    protected List<Index> indexs;
    protected List<PrimaryKey> primaryKeys;

    public TableMetaData() {
    }

    public TableMetaData(ActualTableName actualTableName, Table table, List<Column> columns, List<Index> indexs, List<PrimaryKey> primaryKeys) {
        this.actualTableName = actualTableName;
        this.table = table;
        this.columns = columns;
        this.indexs = indexs;
        this.primaryKeys = primaryKeys;
    }
}
