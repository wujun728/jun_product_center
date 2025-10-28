package io.github.wujun728.admin.page.service;

import java.util.List;
import java.util.Map;

public interface DicCacheService {
    List<Map<String,Object>> options(String dicCode);
    String getLabel(String code,String value);
    void clear();
}
