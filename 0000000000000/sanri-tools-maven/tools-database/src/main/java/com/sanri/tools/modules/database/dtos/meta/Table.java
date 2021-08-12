package com.sanri.tools.modules.database.dtos.meta;

import lombok.Data;

@Data
public class Table {
    private ActualTableName actualTableName;
    private String remark;

    public Table() {
    }

    public Table(ActualTableName actualTableName, String remark) {
        this.actualTableName = actualTableName;
        this.remark = remark;
    }
}
