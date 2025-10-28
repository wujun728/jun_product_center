package io.github.wujun728.admin.db.service;

import io.github.wujun728.admin.common.service.CacheService;
import io.github.wujun728.admin.page.data.SqlInfo;
import io.github.wujun728.admin.page.data.SqlParam;

import java.util.Map;
import java.util.Set;

public interface SqlInfoService extends CacheService<SqlInfo> {
    void resultFields(SqlInfo sqlInfo);
    String getSql(String code);
    String getSql(SqlInfo sqlInfo, Map<String, SqlParam> paramMap, Set<String> sqlCodes);
    String getSql(SqlInfo sqlInfo);
    SqlInfo getSqlInfo(String code);
}
