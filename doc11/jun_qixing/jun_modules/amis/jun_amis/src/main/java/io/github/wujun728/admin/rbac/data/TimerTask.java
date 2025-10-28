package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

/***
 * @date 2022-02-22 15:14:01
 * @remark 定时任务
 */
@Data
public class TimerTask extends BaseData {
    //任务名称
    private String name;
    //接口
    private String api;
    //执行频率
    private String schedulingPattern;
    //状态
    private String dataStatus;
    //参数
    private String params;
}