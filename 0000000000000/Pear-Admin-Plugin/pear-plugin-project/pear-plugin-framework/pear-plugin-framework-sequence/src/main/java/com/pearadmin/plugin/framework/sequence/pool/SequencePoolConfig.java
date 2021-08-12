package com.pearadmin.plugin.framework.sequence.pool;

import lombok.Data;

/**
 * 资 源 池 配 置 类 -- [就眠仪式]
 * */
@Data
public class SequencePoolConfig {

    /**
     * 资 源 池 初 始 化 大 小
     * */
    private int initSize = 300000;

    /**
     * 最 小 空 闲 数 量
     * */
    private int minIdle = 10000;

    /**
     * 机 器 编 码
     * */
    private int workerId = 0;

    /**
     * 数 据 中 心
     * */
    private int centerId = 0;

}
