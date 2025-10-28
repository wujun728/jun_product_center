package io.github.wujun728.admin.page.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class InputTableData {
    private String tableName;
    private String refField;
    private List<Map<String,Object>> items = new ArrayList<>();

}
