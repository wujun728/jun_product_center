package com.sanri.tools.modules.core.dtos.param;

import lombok.Data;

@Data
public abstract class AbstractConnectParam {
    protected ConnectIdParam connectIdParam;
    protected ConnectParam connectParam;
}
