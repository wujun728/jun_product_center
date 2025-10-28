package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-02-24 10:52:15
 * @remark 全局操作日志
 */
@Data
public class GlobalLog extends BaseData {
    //表
    private String tableName;
    //操作人
    private Long userId;
    //操作时间
    private Date createTime;
    //操作类型
    private String optionType;
    //字段
    private String field;
    //操作前值
    private String beforeValue;
    //操作后值
    private String afterValue;
    //操作人姓名
    private String userName;
    //说明
    private String remark;
    //关联id
    private Long refId;
}
