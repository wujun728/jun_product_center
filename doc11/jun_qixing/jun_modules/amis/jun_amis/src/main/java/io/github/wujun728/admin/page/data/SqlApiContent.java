package io.github.wujun728.admin.page.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SqlApiContent {
    private String type;
    private String name;
    private String sql;
    private List<SqlApiContent> props = new ArrayList<>();
    private List<SqlApiContentArg> args = new ArrayList<>();
}
