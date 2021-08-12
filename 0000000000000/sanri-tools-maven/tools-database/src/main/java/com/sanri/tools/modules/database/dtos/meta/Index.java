package com.sanri.tools.modules.database.dtos.meta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Index {
    @JsonIgnore
    private ActualTableName actualTableName;
    private boolean unique;
    private String indexName;
    private short indexType;
    private short ordinalPosition;
    private String columnName;

    public Index() {
    }

    public Index(ActualTableName actualTableName, boolean unique, String indexName, short indexType, short ordinalPosition, String columnName) {
        this.actualTableName = actualTableName;
        this.unique = unique;
        this.indexName = indexName;
        this.indexType = indexType;
        this.ordinalPosition = ordinalPosition;
        this.columnName = columnName;
    }
}
