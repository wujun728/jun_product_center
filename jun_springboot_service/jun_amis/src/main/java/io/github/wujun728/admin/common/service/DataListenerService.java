package io.github.wujun728.admin.common.service;

import java.util.Map;

public interface DataListenerService {
    void newObj(String tableName, Map<String,Object> obj);
    void deleteObj(String tableName, Map<String,Object> obj);
    void updateObj(String tableName, Map<String,Object> beforeObj,Map<String,Object> afterObj);
    void updateObjColumn(String tableName, String columnName, Map<String,Object> beforeObj,Map<String,Object> afterObj,Object beforeValue,Object afterValue);
}
