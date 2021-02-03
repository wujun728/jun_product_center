package com.wangzhixuan.commons.report.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 用于映射实体类和Excel某列名称
 *
 * @author L.cm
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelField {
    /**
     * 在excel文件中某列数据的名称
     *
     * @return 名称
     */
    String value() default "";

    /**
     * 类型
     * @return 数据类型
     */
    ExcelCellType type() default ExcelCellType.TEXT;

    /**
     * number: #.##
     * date: yyyy-MM-dd hh:mm:ss
     * bool:  true:false
     *
     * @return 格式转换
     */
    String format() default "";

    /**
     * 表达式，单元格计算
     * 
     * @return 表达式
     */
    String el() default "";

    /**
     * 在excel中列的顺序，从小到大排，默认为0，model里面要么全设置，不设置的时候会按照field排序
     *
     * @return 顺序
     */
    int order() default 0;
}
