package com.sanri.tools.modules.database.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class BatchTableRelationParam {
    @NotNull
    private String connName;
    private String catalog;

    @Valid
    Set<TableRelationDto> tableRelations = new HashSet<>();
}
