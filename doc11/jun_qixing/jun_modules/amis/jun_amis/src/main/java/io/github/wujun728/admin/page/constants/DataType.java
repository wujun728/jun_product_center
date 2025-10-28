package io.github.wujun728.admin.page.constants;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class DataType {
    //短字符串
    public static final String STRING = "string";
    //数据字典
    public static final String DIC = "dic";
    //选择器
    public static final String Selector = "selector";
    //弹出选择器
    public static final String SelectorPop = "selector-pop";
    //长字符串
    public static final String LONG_STRING = "long-string";
    //大文本
    public static final String LONG_TEXT = "big-string";//超长文本
    //sql
    public static final String SQL = "sql";
    //js
    public static final String JS = "js";
    //文章
    public static final String ARTICLE = "article";
    //日期类型
    public static final String DATE = "date";
    //数字类型
    public static final String LONG = "long";
    public static final String INT = "int";
    //小数
    public static final String DOUBLE = "double";

    //图片
    public static final String IMAGE = "image";
    public static final String FILE = "file";
    public static final String TPL = "tpl";

    public static final boolean isStr(String type){
        return Arrays.asList(STRING,DIC,LONG_STRING,LONG_TEXT,SQL,JS,ARTICLE,IMAGE,FILE,TPL).contains(type);
    }
    public static final boolean isDic(String type){
        return Arrays.asList(DIC).contains(type);
    }
    public static final boolean isImg(String type){
        return Arrays.asList(IMAGE).contains(type);
    }
    public static final boolean isFile(String type){
        return Arrays.asList(FILE).contains(type);
    }
    public static final boolean isSelector(String type){
        return Arrays.asList(Selector).contains(type);
    }
    public static final boolean isDate(String type){
        return Arrays.asList(DATE).contains(type);
    }
    public static final boolean isLong(String type){
        return Arrays.asList(LONG,Selector).contains(type);
    }
    public static final boolean isInt(String type){
        return Arrays.asList(INT).contains(type);
    }
    public static final boolean isDouble(String type){
        return Arrays.asList(DOUBLE).contains(type);
    }

    public static final boolean isNumber(String type){
        return Arrays.asList(LONG,INT,DOUBLE).contains(type);
    }

    public static final Object getValue(String type,String value,String format){
        try {
            if(StrUtil.isBlank(value)){
                return null;
            }
            if (isStr(type)) {
                return value;
            }
            if (isDate(type)) {
                return DateUtil.parse(value,format);
            }
            if (isInt(type)) {
                return Integer.valueOf(value);
            }
            if (isLong(type)) {
                return Long.valueOf(value);
            }
            if (isDouble(type)) {
                return Double.valueOf(value);
            }
        }catch (Exception e){
            log.error(StrUtil.format("数据转换异常,{},{},{}",type,value,format),e);
        }
        return null;
    }
    public static final List<Object> getValues(String type, String value, String format){
        List<Object> values = new ArrayList<>();
        if(StrUtil.isBlank(value)){
            return values;
        }
        String[] arr = StringUtil.splitStr(value, ",");
        for(String v:arr){
            Object realValue = getValue(type, v, format);
            if(realValue != null){
                values.add(realValue);
            }
        }
        return values;
    }

}
