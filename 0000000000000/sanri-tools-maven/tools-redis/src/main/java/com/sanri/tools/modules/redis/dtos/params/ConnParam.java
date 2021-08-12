package com.sanri.tools.modules.redis.dtos.params;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConnParam {
    @NotNull
    private String connName;
    private int index;
}
