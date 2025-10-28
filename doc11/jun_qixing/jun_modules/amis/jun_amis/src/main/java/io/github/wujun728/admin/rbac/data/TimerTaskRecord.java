package io.github.wujun728.admin.rbac.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-02-22 15:14:47
 * @remark 定时任务记录
 */
@Data
public class TimerTaskRecord extends BaseData {
    //定时任务id
    private Long timerTaskId;
    //执行状态
    private String timerTaskRecordStatus;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //参数
    private String params;
    //提示信息
    private String msg;
}