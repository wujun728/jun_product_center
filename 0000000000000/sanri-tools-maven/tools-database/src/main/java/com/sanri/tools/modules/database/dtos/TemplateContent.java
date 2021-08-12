package com.sanri.tools.modules.database.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TemplateContent {
    @NotNull
    private String name;
    @NotNull
    private String content;
}
