package com.sanri.tools.modules.core.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class PluginDto {
    // 模块名,作者,logo ,使用环境列表逗号分隔,模块描述,帮助文档地址,依赖列表
    private String module;
    private String name;
    private String author;
    private String logo;
    private String envs;
    private String desc;
    private String help;
    private String helpContent;
    private String dependencies;

    @Tolerate
    public PluginDto() {
    }

    /**
     * 模块唯一主键
     * @return
     */
    public String key(){
        return module+":"+name;
    }
}
