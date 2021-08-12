package com.sanri.tools.modules.redis.dtos;

import lombok.Data;

@Data
public class ZSetTuple {
    private Object value;
    private double score;

    public ZSetTuple() {
    }

    public ZSetTuple(Object value, double score) {
        this.value = value;
        this.score = score;
    }
}
