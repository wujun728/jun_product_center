package com.platform.dao;


import com.platform.entity.ResultMap;

import java.util.List;
import java.util.Map;

/**
 * oracle代码生成器
 *
 * @author lipengjun
 * @date 2017年11月20日 下午19:29:40
 */
public interface SysOracleGeneratorDao {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<ResultMap> queryColumns(String tableName);
}
