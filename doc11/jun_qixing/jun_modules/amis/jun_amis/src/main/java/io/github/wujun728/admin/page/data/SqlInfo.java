package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

/***
 * @date 2023-02-01 14:35:30
 * @remark sql语句
 */
@Data
@FieldNameConstants
public class SqlInfo extends BaseData {
    //编号
    private String code;
    //名称
    private String name;
    //sql语句
    private String sqlInfo;
    //sql类型
    private String sqlType;
    //关联sql
    private String refSqlCodes;
    //参数列表
    private List<SqlParam> sqlParams = new ArrayList<>();
    //结果列表
    private List<SqlResult> sqlResults = new ArrayList<>();
}
