package io.github.wujun728.admin.page;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenPage {

    // 数据库连接信息
    private String url;
    private String username;
    private String password;

    // Amis组件类型常量
    private static final String INPUT = "input";
    private static final String TEXTAREA = "textarea";
    private static final String NUMBER = "number";
    private static final String DATE = "date";
    private static final String DATETIME = "datetime";
    private static final String RADIO = "radio";

    public GenPage(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 生成指定表的Amis页面配置
     */
    public String generateAmisConfig(String tableName) throws SQLException {
        List<ColumnInfo> columns = getTableColumns(tableName);
        String tableComment = getTableComment(tableName);

        JSONObject pageConfig = new JSONObject();
        pageConfig.set("type", "page");
        pageConfig.set("title", tableComment.isEmpty() ? tableName : tableComment);
        pageConfig.set("body", createPageBody(tableName, columns));

        return pageConfig.toStringPretty();
    }

    /**
     * 创建页面主体内容（CRUD组件）
     */
    private JSONObject createPageBody(String tableName, List<ColumnInfo> columns) {
        JSONObject body = new JSONObject();
        body.set("type", "crud");
        body.set("api", createCrudApi(tableName));
        body.set("columns", createColumnsConfig(columns));
        body.set("filter", createFilterConfig(columns)); // 修复过滤器配置
        body.set("perPage", 10);
        body.set("syncLocation", false);
        body.set("columnsTogglable", true);

        // 顶部操作按钮
        JSONArray operations = new JSONArray();
        operations.add(createAddButton(tableName, columns));
        body.set("operations", operations);

        // 行操作按钮
        body.set("rowOperation", createRowOperations(tableName, columns));

        return body;
    }

    /**
     * 创建新增按钮
     */
    private JSONObject createAddButton(String tableName, List<ColumnInfo> columns) {
        JSONObject addAction = new JSONObject();
        addAction.set("type", "button");
        addAction.set("label", "新增");
        addAction.set("level", "primary");
        addAction.set("actionType", "dialog");
        addAction.set("dialog", createAddEditDialog(tableName, columns, "新增"));
        return addAction;
    }

    /**
     * 创建行操作按钮（编辑/删除）
     */
    private JSONObject createRowOperations(String tableName, List<ColumnInfo> columns) {
        JSONArray rowButtons = new JSONArray();

        // 编辑按钮
        JSONObject editAction = new JSONObject();
        editAction.set("type", "button");
        editAction.set("label", "编辑");
        editAction.set("actionType", "dialog");
        editAction.set("dialog", createAddEditDialog(tableName, columns, "编辑"));
        rowButtons.add(editAction);

        // 删除按钮
        JSONObject deleteAction = new JSONObject();
        deleteAction.set("type", "button");
        deleteAction.set("label", "删除");
        deleteAction.set("level", "danger");
        deleteAction.set("actionType", "ajax");
        deleteAction.set("confirmText", "确定要删除该记录吗？");
        deleteAction.set("api", createDeleteApi(tableName));
        rowButtons.add(deleteAction);

        JSONObject rowOperation = new JSONObject();
        rowOperation.set("buttons", rowButtons);
        return rowOperation;
    }

    /**
     * 创建CRUD列表API配置
     */
    private JSONObject createCrudApi(String tableName) {
        JSONObject api = new JSONObject();
        api.set("method", "get");
        api.set("url", "/api/" + tableName);

        JSONObject data = new JSONObject();
        data.set("page", "${page}");
        data.set("perPage", "${perPage}");
        data.set("${filter}", "${filter}");
        api.set("data", data);

        return api;
    }

    /**
     * 创建删除操作API配置
     */
    private JSONObject createDeleteApi(String tableName) {
        JSONObject api = new JSONObject();
        api.set("method", "delete");
        api.set("url", "/api/" + tableName + "/${id}");

        JSONObject success = new JSONObject();
        success.set("actionType", "toast");
        success.set("msg", "删除成功");
        api.set("success", success);

        return api;
    }

    /**
     * 创建新增/编辑对话框
     */
    private JSONObject createAddEditDialog(String tableName, List<ColumnInfo> columns, String title) {
        JSONObject dialog = new JSONObject();
        dialog.set("title", title);
        dialog.set("size", "lg");
        dialog.set("body", createForm(tableName, columns, title));
        return dialog;
    }

    /**
     * 创建表单组件
     */
    private JSONObject createForm(String tableName, List<ColumnInfo> columns, String action) {
        JSONObject form = new JSONObject();
        form.set("type", "form");
        form.set("api", createFormApi(tableName, action));
        form.set("mode", "horizontal");
        form.set("labelWidth", 120);
        form.set("body", createFormItems(columns, action));
        return form;
    }

    /**
     * 创建表单提交API配置
     */
    private JSONObject createFormApi(String tableName, String action) {
        JSONObject api = new JSONObject();
        api.set("method", "新增".equals(action) ? "post" : "put");
        api.set("url", "/api/" + tableName + ("编辑".equals(action) ? "/${id}" : ""));

        JSONObject success = new JSONObject();
        success.set("actionType", "close");
        success.set("then", new JSONObject().set("actionType", "refresh"));
        api.set("success", success);

        return api;
    }

    /**
     * 创建表格列配置
     */
    private JSONArray createColumnsConfig(List<ColumnInfo> columns) {
        JSONArray columnsConfig = new JSONArray();

        for (ColumnInfo column : columns) {
            JSONObject columnConfig = new JSONObject();
            columnConfig.set("name", column.getName());
            columnConfig.set("label", column.getComment().isEmpty() ? column.getName() : column.getComment());

            // 主键列默认隐藏
            if (column.isPrimaryKey()) {
                columnConfig.set("hidden", true);
            }

            columnsConfig.add(columnConfig);
        }

        return columnsConfig;
    }


    /**
     * 修复：创建查询过滤器配置（调整JSONArray的创建方式）
     */
    private JSONObject createFilterConfig(List<ColumnInfo> columns) {
        JSONObject filter = new JSONObject();
        filter.set("type", "form");
        filter.set("title", "查询");
        filter.set("mode", "inline");
        filter.set("labelWidth", 80);
        filter.set("wrap", false);

        JSONArray body = new JSONArray();
        int count = 0;

        for (ColumnInfo column : columns) {
            if (count >= 5) break;
            if (column.isPrimaryKey()) continue;

            String componentType = getAmisComponentType(column.getType());
            if (componentType.equals(TEXTAREA)) continue;

            JSONObject filterItem = createFormItem(column, false);
            filterItem.set("required", false);
            filterItem.set("placeholder", "请输入" + filterItem.getStr("label")); // 默认占位符

            // 日期/时间类型范围查询的占位符（拆分JSONArray创建步骤）
            if (componentType.equals(DATE) || componentType.equals(DATETIME)) {
                filterItem.set("range", true);
                // 先创建JSONArray再添加元素，避免链式调用的语法问题
                JSONArray placeholders = new JSONArray();
                placeholders.add("开始" + filterItem.getStr("label"));
                placeholders.add("结束" + filterItem.getStr("label"));
                filterItem.set("placeholder", placeholders); // 单独设置
            }

            body.add(filterItem);
            count++;
        }

        // 添加查询和重置按钮
        body.add(createFilterButton("查询", "submit", null));
        body.add(createFilterButton("重置", "reset", 8));

        filter.set("body", body);
        return filter;
    }

    /**
     * 创建过滤器按钮（适配filter的表单类型）
     */
    private JSONObject createFilterButton(String label, String actionType, Integer marginLeft) {
        JSONObject button = new JSONObject();
        button.set("type", "button");
        button.set("label", label);
        button.set("actionType", actionType);
        button.set("level", "primary".equals(actionType) ? "primary" : "default");

        if (marginLeft != null) {
            JSONObject style = new JSONObject();
            style.set("marginLeft", marginLeft);
            button.set("style", style);
        }
        return button;
    }

    /**
     * 创建表单项目
     */
    private JSONArray createFormItems(List<ColumnInfo> columns, String action) {
        JSONArray formItems = new JSONArray();

        for (ColumnInfo column : columns) {
            // 编辑时显示主键（只读），新增时不显示
            if (column.isPrimaryKey()) {
                if ("编辑".equals(action)) {
                    JSONObject formItem = createFormItem(column, true);
                    formItems.add(formItem);
                }
                continue;
            }

            formItems.add(createFormItem(column, false));
        }

        return formItems;
    }

    /**
     * 创建单个表单项目（确保组件类型正确）
     */
    private JSONObject createFormItem(ColumnInfo column, boolean readOnly) {
        JSONObject formItem = new JSONObject();
        formItem.set("name", column.getName());
        formItem.set("label", column.getComment().isEmpty() ? column.getName() : column.getComment());
        formItem.set("required", !column.isNullable());
        formItem.set("readOnly", readOnly);

        String mysqlType = column.getType();
        String componentType = getAmisComponentType(mysqlType);
        formItem.set("type", componentType); // 确保类型正确，避免渲染器找不到

        // 特殊组件配置
        if (componentType.equals(RADIO)) {
            JSONArray options = new JSONArray();
            options.add(new JSONObject().set("label", "是").set("value", 1));
            options.add(new JSONObject().set("label", "否").set("value", 0));
            formItem.set("options", options);
            formItem.set("defaultValue", 0);
        } else if (componentType.equals(NUMBER)) {
            formItem.set("precision", getNumberPrecision(mysqlType));
            if (mysqlType.contains("int") && !column.isNullable()) {
                formItem.set("min", 0);
            }
        } else if (componentType.equals(INPUT) && mysqlType.contains("varchar")) {
            Integer length = getVarcharLength(mysqlType);
            if (length != null) {
                formItem.set("maxLength", length);
            }
        }

        return formItem;
    }

    /**
     * 映射MySQL类型到Amis组件类型（确保无错误类型）
     */
    private String getAmisComponentType(String mysqlType) {
        if (mysqlType == null) return INPUT;
        mysqlType = mysqlType.toLowerCase().trim();

        // 严格匹配类型，避免返回不存在的组件类型
        if (mysqlType.matches(".*(int|bigint|smallint|tinyint|float|double|decimal).*")) {
            return NUMBER;
        } else if (mysqlType.equals("date")) {
            return DATE;
        } else if (mysqlType.matches(".*(datetime|timestamp).*")) {
            return DATETIME;
        } else if (mysqlType.matches(".*(text|longtext).*")) {
            return TEXTAREA;
        } else if (mysqlType.matches("tinyint\\(1\\)")) {
            return RADIO;
        } else {
            return INPUT; // 默认使用input，避免未知类型
        }
    }

    /**
     * 获取数字类型精度
     */
    private Integer getNumberPrecision(String mysqlType) {
        if (mysqlType.contains("decimal") || mysqlType.contains("float") || mysqlType.contains("double")) {
            int start = mysqlType.indexOf('(');
            int end = mysqlType.indexOf(',');
            if (start > 0 && end > 0) {
                try {
                    return Integer.parseInt(mysqlType.substring(end + 1, mysqlType.indexOf(')')));
                } catch (Exception e) {
                    return 2;
                }
            }
        }
        return null;
    }

    /**
     * 获取Varchar长度
     */
    private Integer getVarcharLength(String mysqlType) {
        if (mysqlType.startsWith("varchar")) {
            int start = mysqlType.indexOf('(');
            int end = mysqlType.indexOf(')');
            if (start > 0 && end > start) {
                try {
                    return Integer.parseInt(mysqlType.substring(start + 1, end));
                } catch (Exception e) {
                    // 忽略解析错误
                }
            }
        }
        return null;
    }

    /**
     * 获取表的列信息
     */
    private List<ColumnInfo> getTableColumns(String tableName) throws SQLException {
        List<ColumnInfo> columns = new ArrayList<>();

        try (Connection conn = getConnection();
             ResultSet rs = conn.getMetaData().getColumns(null, null, tableName, null)) {

            while (rs.next()) {
                ColumnInfo column = new ColumnInfo();
                column.setName(rs.getString("COLUMN_NAME"));
                column.setType(rs.getString("TYPE_NAME"));
                column.setComment(rs.getString("REMARKS"));
                column.setNullable(rs.getInt("NULLABLE") == 1);

                columns.add(column);
            }
        }

        // 设置主键信息
        setPrimaryKeys(tableName, columns);

        return columns;
    }

    /**
     * 设置主键信息
     */
    private void setPrimaryKeys(String tableName, List<ColumnInfo> columns) throws SQLException {
        try (Connection conn = getConnection();
             ResultSet rs = conn.getMetaData().getPrimaryKeys(null, null, tableName)) {

            while (rs.next()) {
                String pkColumnName = rs.getString("COLUMN_NAME");
                for (ColumnInfo column : columns) {
                    if (column.getName().equals(pkColumnName)) {
                        column.setPrimaryKey(true);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 获取表注释
     */
    private String getTableComment(String tableName) throws SQLException {
        String sql = "SELECT table_comment FROM information_schema.tables WHERE table_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tableName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("table_comment");
                }
            }
        }

        return "";
    }

    /**
     * 获取数据库连接
     */
    private Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        props.setProperty("useSSL", "false");
        props.setProperty("serverTimezone", "UTC");
        props.setProperty("useInformationSchema", "true");

        return DriverManager.getConnection(url, props);
    }

    /**
     * 列信息实体类
     */
    private static class ColumnInfo {
        private String name;
        private String type;
        private String comment;
        private boolean isNullable;
        private boolean isPrimaryKey;

        // getter和setter
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }

        public boolean isNullable() { return isNullable; }
        public void setNullable(boolean nullable) { isNullable = nullable; }

        public boolean isPrimaryKey() { return isPrimaryKey; }
        public void setPrimaryKey(boolean primaryKey) { isPrimaryKey = primaryKey; }
    }



    // 示例用法
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3307/db_qixing_v2";
            String username = "root";
            String password = "";
            String tableName = "pj_contract";

            GenPage generator = new GenPage(url, username, password);
            String amisConfig = generator.generateAmisConfig(tableName);

            System.out.println(amisConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
