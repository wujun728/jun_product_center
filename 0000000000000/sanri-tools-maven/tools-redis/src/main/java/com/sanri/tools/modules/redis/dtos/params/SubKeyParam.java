package com.sanri.tools.modules.redis.dtos.params;

import lombok.Data;

@Data
public class SubKeyParam {
    private String key;
    private String [] subKeys;
    // 如果 all 为 true ,不关心 subKeys , 查询所有数据
    private boolean all;
}
