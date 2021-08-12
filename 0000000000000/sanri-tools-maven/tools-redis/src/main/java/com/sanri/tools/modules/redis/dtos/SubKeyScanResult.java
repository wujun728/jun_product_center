package com.sanri.tools.modules.redis.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SubKeyScanResult<T> {
    private List<T> keys;
    private String cursor;
    private boolean finish;

    public SubKeyScanResult() {
    }

    public SubKeyScanResult(List<T> keys, String cursor) {
        this.keys = keys;
        this.cursor = cursor;
    }
}
