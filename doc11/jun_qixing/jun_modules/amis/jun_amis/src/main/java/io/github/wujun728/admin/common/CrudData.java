package io.github.wujun728.admin.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CrudData<T> {
    private Integer count;
    //总计
    private Map<String,Object> statistics = new HashMap<>();
    private List<T> rows = new ArrayList<>();
    private List<ColumnData> columns = new ArrayList<>();
    private Map<String,Object> params = new HashMap<>();
}
