package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.core.dtos.param.AuthParam;
import lombok.Data;

@Data
public class ConnectionMetaData {
    private AuthParam authParam;
    private String driverClass;
    private String connectionURL;

    public ConnectionMetaData() {
    }

    public ConnectionMetaData(AuthParam authParam, String driverClass, String connectionURL) {
        this.authParam = authParam;
        this.driverClass = driverClass;
        this.connectionURL = connectionURL;
    }
}
