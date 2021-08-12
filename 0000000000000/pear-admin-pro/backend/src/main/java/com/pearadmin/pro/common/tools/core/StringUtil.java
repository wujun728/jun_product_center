package com.pearadmin.pro.common.tools.core;

import com.pearadmin.pro.common.constant.SystemConstant;

/**
 * Describe: String 工具类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class StringUtil {

    /**
     * 判断是否为空
     * */
    public static boolean isBlank(String text){
        if(text.equals(SystemConstant.EMPTY)){ return true; }
        return false;
    }

    /**
     * 判断是否为空
     * */
    public static boolean isEmpty(String text){
        if(text == null){ return true; }
        return false;
    }

    /**
     * 判断是否不为空
     * */
    public static boolean isNotBlank(String text){
        return !isBlank(text);
    }

    /**
     * 判断是否不为空
     * */
    public static boolean isNotEmpty(String text){
        return !isNotEmpty(text);
    }
}
