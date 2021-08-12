package com.sanri.tools.modules.redis.dtos.params;

import lombok.Data;

@Data
public class RangeParam {
    // 用于 zset score 范围查询
    private Double min;
    private Double max;

    private int offset = 0;
    private int count = 10;
    // zset  分数 score(min,max) 来查列表,也可以取区间内成员 range(start,stop) ,即排名顺序

    // 用于 List 范围数据查询 , zset 排名范围查询
    private Long start;
    private Long stop;
}
