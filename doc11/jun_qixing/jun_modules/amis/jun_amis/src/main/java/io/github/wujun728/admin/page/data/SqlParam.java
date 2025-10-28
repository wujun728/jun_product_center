package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/***
 * @date 2023-02-01 14:35:59
 * @remark sql参数
 */
@Data
@FieldNameConstants
public class SqlParam extends BaseData {
    //sql主键
    private Long sqlInfoId;
    //序号
    private Integer seq;
    //参数名称
    private String name;
    //参数标签
    private String label;
    //是否必填
    private String must;
    //参数类型
    private String paramType;
    //格式化
    private String format;
    //默认值
    private String defaultValue;
}
