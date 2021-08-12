package com.pearadmin.plugin.configure.sequence;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 分 布 式 Id 配 置 文 件 映 射 , 用 于 加 载 Application.yml 配 置 --【就眠仪式】
 */
@Data
@ConfigurationProperties("pear.plugin.sequence")
public class SequenceAutoProperties {

    /**
     * 工 作 中 心 , 默认 0
     */
    private int workerId = 0;

    /**
     * 数 据 中 心 , 默认 0
     */
    private int centerId = 0;

    /**
     * 初 始 化 数 量 , 默认 100
     * */
    private int initSize = 300;

    /**
     * 最 小 闲 置 , 默认 100
     * */
    private int minIdle = 100;

}
