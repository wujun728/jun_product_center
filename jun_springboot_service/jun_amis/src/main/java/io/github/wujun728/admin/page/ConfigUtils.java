package io.github.wujun728.admin.page;

import com.google.api.client.util.Lists;

import java.util.List;

public class ConfigUtils {

    public static List ignoreListColumnName = Lists.newArrayList();

    static {
        ignoreListColumnName.add("creator");
        ignoreListColumnName.add("create_time");
        ignoreListColumnName.add("create_id");
        ignoreListColumnName.add("update_time");
        ignoreListColumnName.add("update_id");
        ignoreListColumnName.add("deleted");
        ignoreListColumnName.add("order_id");
        ignoreListColumnName.add("order_status");
        ignoreListColumnName.add("order_state");
        ignoreListColumnName.add("editor");

    }
}
