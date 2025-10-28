package io.github.wujun728.admin.page.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PageButtonData {
    //顶部按钮
    private List<Object> topButtons = new ArrayList<>();
    //行按钮
    private List<Map<String,Object>> rowButtons = new ArrayList<>();
    //行更多按钮
    private List<Map<String,Object>> rowMoreButtons = new ArrayList<>();
    //批量操作按钮
    private List<Object> bulkButtons = new ArrayList<>();

}
