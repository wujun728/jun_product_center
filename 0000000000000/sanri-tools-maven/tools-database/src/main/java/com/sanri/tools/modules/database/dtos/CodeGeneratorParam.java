package com.sanri.tools.modules.database.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CodeGeneratorParam {
   private List<String> templates;
   private CodeGeneratorConfig.DataSourceConfig dataSourceConfig;
   private CodeGeneratorConfig.PackageConfig packageConfig;
   private String renameStrategyName;
   // 单一文件 , 这时只会使用 dataSourceConfig
   private boolean single;
}
