package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.core.validation.custom.EnumStringValue;
import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 表关系
 */
@Data
public class TableRelationDto {
    @Valid
    private ActualTableName sourceTableName;
    @Valid
    private ActualTableName targetTableName;
    @NotNull
    private String sourceColumnName;
    @NotNull
    private String targetColumnName;

    // ONE_ONE,ONE_MANY,MANY_MANY
    @NotNull
    @EnumStringValue({"ONE_ONE","ONE_MANY","MANY_MANY"})
    private String relation;

    public static enum Relation{
        ONE_ONE,ONE_MANY,MANY_MANY
    }
}
