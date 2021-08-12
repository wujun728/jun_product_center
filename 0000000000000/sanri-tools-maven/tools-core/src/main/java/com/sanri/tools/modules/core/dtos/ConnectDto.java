package com.sanri.tools.modules.core.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ConnectDto { // {module:[{},{}],module2:[{},{}]}
    private String module;
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModified;

    public ConnectDto() {
    }

    public ConnectDto(String module, String name, Date lastModified) {
        this.module = module;
        this.name = name;
        this.lastModified = lastModified;
    }
}
