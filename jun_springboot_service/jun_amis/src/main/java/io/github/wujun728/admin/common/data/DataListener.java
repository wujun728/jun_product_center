package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-04-29 11:20:58
 * @remark 数据监听
 */
@Data
public class DataListener extends BaseData {
    //表名称
    private String tableName;
    //事件类型
    private String eventType;
    //字段名称
    private String columnName;
    //接口
    private String afterApi;
    //序号
    private Integer seq;
    //javaBean
    private String javaBean;
}
