package com.ruoyi.kbs.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre> 转换工具 </pre>
 */
public class TransferUtil {

    /**
     * List<String> 转换为 String[]
     */
    public static String[] listToArray(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new String[0];
        }
        return list.toArray(new String[0]);
    }

    /**
     * String[] 转换为 List<String>
     */
    public static List<String> arrayToList(String[] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

    /**
     * String 转换为 String[]
     */
    public static String[] objectToArray(String object) {
        if (object == null || object.isEmpty()) {
            return new String[0];
        }
        return new String[]{object};
    }

    /**
     * String 转换为 List<String>
     */
    public static List<String> objectToList(String object) {
        if (object == null || object.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.singletonList(object);
    }
}
