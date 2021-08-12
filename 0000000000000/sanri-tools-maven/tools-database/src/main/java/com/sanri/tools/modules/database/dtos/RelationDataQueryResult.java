package com.sanri.tools.modules.database.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RelationDataQueryResult {
    private List<String> parents;
    private List<String> childs;
    private String sql;

    public RelationDataQueryResult() {
    }

    public RelationDataQueryResult(String sql) {
        this.sql = sql;
    }
}
