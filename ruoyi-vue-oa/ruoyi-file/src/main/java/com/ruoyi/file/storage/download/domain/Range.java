package com.ruoyi.file.storage.download.domain;

import lombok.Data;

/**
 * @author MAC
 * @version 1.0
 */
@Data
public class Range {
    private long start;
    private int length;
}
