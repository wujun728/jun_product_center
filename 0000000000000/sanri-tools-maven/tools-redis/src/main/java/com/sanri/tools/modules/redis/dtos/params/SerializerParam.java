package com.sanri.tools.modules.redis.dtos.params;

import lombok.Data;

@Data
public class SerializerParam {
    private String keySerializer;
    private String value;
    private String hashKey;
    private String hashValue;
    private String classloaderName;
}
