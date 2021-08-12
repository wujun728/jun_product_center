package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class JavaBeanBuildConfig {
    @NotNull
    private String connName;
    private String catalog;
    private List<ActualTableName> tables;

    private boolean lombok;
    private boolean swagger2;
    private boolean persistence;
    private boolean serializer;
    private String supperClass;
    private List<String> exclude;

    private String renameStrategy;
    @NotNull
    private String packageName;

    @Tolerate
    public JavaBeanBuildConfig() {
    }
}
