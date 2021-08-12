package com.sanri.tools.modules.core.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class SpiderDataParam {
    @NotNull
    private String className;
    @NotNull
    private String classloaderName;
    private Map<String,String> params;
}
