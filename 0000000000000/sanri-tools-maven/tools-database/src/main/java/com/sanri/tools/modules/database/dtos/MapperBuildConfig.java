package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.database.dtos.meta.TableMetaData;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MapperBuildConfig {
    private String targetRunTime = "MyBatis3Simple";
    private String modelType = "FLAT";
    private String javaClientType = "XMLMAPPER";

    // 需要生成的内容
    private FilesConfig filesConfig = new FilesConfig();

    @Valid
    private CodeGeneratorConfig.DataSourceConfig dataSourceConfig;
    @Valid
    private CodeGeneratorConfig.PackageConfig packageConfig;
    @NotNull
    private String projectName;

    private List<PluginConfig> pluginConfigs = new ArrayList<>();

    @Data
    public static class FilesConfig{
        private boolean entity = true;
        private boolean xml = true;
        private boolean mapper = true;
    }


    @Data
    public static class PluginConfig{
        private String type;
        private Map<String,String> properties = new HashMap<>();
    }
}
