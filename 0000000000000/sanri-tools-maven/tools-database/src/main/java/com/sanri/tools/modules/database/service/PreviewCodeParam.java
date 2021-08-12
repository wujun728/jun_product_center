package com.sanri.tools.modules.database.service;

import com.sanri.tools.modules.database.dtos.CodeGeneratorConfig;
import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PreviewCodeParam {
    @NotNull
    private String template;
    @NotNull
    private String connName;
    @Valid
    private ActualTableName actualTableName;
    private String renameStrategyName;
    @Valid
    private CodeGeneratorConfig.PackageConfig packageConfig;

    public PreviewCodeParam() {
    }

    public PreviewCodeParam(String template, String connName, ActualTableName actualTableName, String renameStrategyName) {
        this.template = template;
        this.connName = connName;
        this.actualTableName = actualTableName;
        this.renameStrategyName = renameStrategyName;
    }
}
