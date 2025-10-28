package com.ruoyi.common.constant;

/**
 * 树形通用常量信息
 *
 * @author wangzongrun
 */
public class TreeConstants {
    /**
     * 无父级的情况下，祖籍的值
     */
    public static final String ANCESTORS_ROOT_VALUE = "0";

    /**
     * 无父级的情况下，
     */
    public static final String PARENT_ROOT_VALUE = "0";

    /**
     * 无父级的情况下，层次默认值
     */
    public static final Integer LAYOUT_DEPTH_INIT_VALUE = 1;

    /**
     * 层次码分隔符
     * .不能直接转义，添加转移字符
     */
    public static final String LAYOUT_CODE_SEPARATOR = ".";
}
