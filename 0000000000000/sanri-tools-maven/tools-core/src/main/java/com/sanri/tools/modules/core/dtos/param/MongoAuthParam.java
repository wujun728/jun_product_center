package com.sanri.tools.modules.core.dtos.param;

import lombok.Data;

@Data
public class MongoAuthParam extends AuthParam {
    private String database;
}
