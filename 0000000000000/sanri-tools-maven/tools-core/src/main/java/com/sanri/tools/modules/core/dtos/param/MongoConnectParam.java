package com.sanri.tools.modules.core.dtos.param;

import lombok.Data;

@Data
public class MongoConnectParam extends AbstractConnectParam {
    private MongoAuthParam authParam;
}
