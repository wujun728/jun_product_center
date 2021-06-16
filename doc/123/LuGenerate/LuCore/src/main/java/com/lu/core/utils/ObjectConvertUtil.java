package com.lu.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectConvertUtil {

    public static final String SELECT_ID = "id";
    public static final String SELECT_CODE = "code";
    public static final String SELECT_NAME = "name";

    /**
     * 转换select_plus
     * @param mapList
     * @return
     */
    public static List<Map<String, Object>> select(List<Map> mapList) throws RuntimeException{
        List<Map<String, Object>> resList = new ArrayList<>();
        if(ToolUtil.isNotEmpty(mapList)){
            mapList.stream().forEach(map -> {
                Map<String, Object> m = new HashMap<>();
                m.put(SELECT_CODE, map.get(SELECT_ID));
                m.put(SELECT_NAME, map.get(SELECT_NAME));
                resList.add(m);
            });
        }
        return resList;
    }

    /**
     * 转换select_plus
     * @param map
     * @return
     */
    public static List<Map<String, Object>> select(Map<String, List<String>> map) throws RuntimeException{
        List<Map<String, Object>> resList = new ArrayList<>();
        if(ToolUtil.isNotEmpty(map)){
            map.forEach((k, v) -> {
                Map<String, Object> m = new HashMap<>();
                m.put(SELECT_CODE, k);
                m.put(SELECT_NAME, v.get(1));
                resList.add(m);
            });
        }
        return resList;
    }

    /**
     * 转换select_plus
     * @param mapList
     * @return
     */
    public static List<Map<String, Object>> select(List<Map<String, Object>> mapList, String code, String name){
        List<Map<String, Object>> resList = new ArrayList<>();
        if(ToolUtil.isNotEmpty(mapList)){
            mapList.stream().forEach(map -> {
                Map<String, Object> m = new HashMap<>();
                m.put("code", map.get(code));
                m.put("name", map.get(name));
                resList.add(m);
            });
        }
        return resList;
    }

}
