package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-02-22 16:28:18
 * @remark 动态任务
 */
@Data
public class DynamicTask extends BaseData {
    //任务名称
    private String name;
    //关联id
    private Long refId;
    //参数
    private String params;
    //任务状态
    private String timerTaskStatus;
    //提示信息
    private String msg;
    //计划执行时间
    private Date planTime;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //接口
    private String api;
    //创建时间
    private Date createTime;
    //java接口
    private String javaService;
}