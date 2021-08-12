package com.sanri.tools.modules.core.dtos.param;

import lombok.Data;

@Data
public class RedisConnectParam extends AbstractConnectParam {
    private AuthParam authParam;
}
