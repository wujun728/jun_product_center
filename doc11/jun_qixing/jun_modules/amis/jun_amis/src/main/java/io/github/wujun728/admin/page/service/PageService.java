package io.github.wujun728.admin.page.service;

import io.github.wujun728.admin.common.CrudData;
import io.github.wujun728.admin.common.PageParam;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.service.CacheService;
import io.github.wujun728.admin.page.data.Page;

import java.util.Map;

public interface PageService extends CacheService<Page> {
    Result<CrudData<Map<String,Object>>> query(String pageCode, PageParam pageParam);
    Result<CrudData<Map<String,Object>>> queryAll(String pageCode);
    Result<CrudData<Map<String,Object>>> queryAll(String pageCode,Map<String,Object> params);
    Map<String,Object> optionConfig(String pageCode);
    void reload(Page page);
    String getQuerySql(String querySql);
    void save(Page page);
    Page get(Long id);
    Page load(String pageCode);
    void del(Page page);
}
