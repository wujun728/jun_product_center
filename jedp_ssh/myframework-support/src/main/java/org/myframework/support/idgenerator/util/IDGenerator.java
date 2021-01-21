package org.myframework.support.idgenerator.util;

import org.myframework.support.idgenerator.service.ID;
import org.myframework.support.spring.SpringUtils;

public class IDGenerator {

    /**
     * 取下一个流水号
     * @param seriesID String
     * @param arguments String[],参数数组
     * @return String
     */
    public static String getNext(String seriesID, String[] arguments){
        ID id =  SpringUtils.getBean("idGenerator");//iDGen
        return id.getNext(seriesID, arguments);
    }

    /**---------------以下为一些方便的方法------------------*/
    /**
     * 取下一个流水号，返回字符串
     * @param seriesID String
     * @return String
     */
    public static String getNext(String seriesID){
        return getNext(seriesID, (String[])null);
    }

    /**
     * 取下一个流水号，只有一个参数
     * @param seriesID String
     * @param argument String
     * @return String
     */
    public static String getNext(String seriesID, String argument){
        return getNext(seriesID, new String[] {argument});
    }

    /**
     * 取下一个流水号，有2个参数
     * @param seriesID String
     * @param argument1 String
     * @param argument2 String
     * @return String
     */
    public static String getNext(String seriesID, String argument1, String argument2){
        return getNext(seriesID, new String[] {argument1, argument2});
    }

    /**
     * 取下一个流水号，有3个参数
     * @param seriesID String
     * @param argument1 String
     * @param argument2 String
     * @param argument3 String
     * @return String
     */
    public static String getNext(String seriesID, String argument1, String argument2,
                          String argument3){
        return getNext(seriesID, new String[] {argument1, argument2, argument3});
    }

    /**
     * 取下一个流水号，有4个参数
     * @param seriesID String
     * @param argument1 String
     * @param argument2 String
     * @param argument3 String
     * @param argument4 String
     * @return String
     */
    public static String getNext(String seriesID, String argument1, String argument2,
                          String argument3, String argument4){
        return getNext(seriesID, new String[] {argument1, argument2, argument3,
                       argument4});
    }
}
