package com.jun.plugin.generator.code;





/**
 * 字符串工具类
 * 
 * @author fc
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    /** 空字符串 */
    private static final String NULLSTR = "";








    /**
     * * 判断一个字符串是否为空串
     * 
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个对象是否为空
     * 
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }



    

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String firstUpperCase(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String firstLowerCase(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;

    }
    
    /**
     * 将下划线转化为大写
     *
     * @param name
     * @param firstCase 首字母是否大写 true:大写 false;小写
     * @return
     */
    public static String upperCase_(String name, boolean firstCase) {
        if(isEmpty(name)){
            return "";
        }
        String[] s = name.split("_");
        StringBuffer stringBuffer = new StringBuffer();
        for (String s1 : s) {
            stringBuffer.append(s1.substring(0, 1).toUpperCase() + s1.substring(1));
        }
        if(!firstCase){
            return firstLowerCase(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }
    


    

}