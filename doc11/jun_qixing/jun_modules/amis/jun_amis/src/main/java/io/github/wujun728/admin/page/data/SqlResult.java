package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/***
 * @date 2023-02-01 14:36:11
 * @remark sql结果
 */
@Data
@FieldNameConstants
public class SqlResult extends BaseData {
    //sql主键
    private Long sqlInfoId;
    //字段
    private String field;
    //名称
    private String label;
    //类型
    private String type;
    //格式化
    private String format;
    //序号
    private Integer seq;
}
