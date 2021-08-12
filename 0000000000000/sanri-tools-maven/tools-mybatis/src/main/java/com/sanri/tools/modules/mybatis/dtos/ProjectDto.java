package com.sanri.tools.modules.mybatis.dtos;

import lombok.Data;

@Data
public class ProjectDto {
    private String project;
    private String classloaderName;

    public ProjectDto() {
    }

    public ProjectDto(String project, String classloaderName) {
        this.project = project;
        this.classloaderName = classloaderName;
    }
}
