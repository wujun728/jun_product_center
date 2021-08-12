package com.sanri.tools.modules.redis.dtos.params;

import lombok.Data;

@Data
public class RedisScanParam {
    private String pattern;
    private int limit;
    private String cursor;

    // 非快速搜索时,超时设置
    private long timeout = -1;

    // 是否快速搜索,数据量不能保证每页数量,一般用于查找单个 key 的情况
    private boolean fast;
}
