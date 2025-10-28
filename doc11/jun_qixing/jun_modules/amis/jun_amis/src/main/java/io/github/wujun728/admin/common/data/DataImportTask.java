package io.github.wujun728.admin.common.data;

import io.github.wujun728.admin.common.BaseData;
import lombok.Data;

import java.util.Date;

/***
 * @date 2022-07-11 15:07:32
 * @remark 数据导入任务
 */
@Data
public class DataImportTask extends BaseData {
    //用户id
    private Long userId;
    //企业id
    private Long enterpriseId;
    //创建时间
    private Date createTime;
    //计划时间
    private Date planTime;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //任务状态
    private String dataImportTaskStatus;
    //源文件
    private String srcFile;
    //错误文件
    private String errorFile;
    //错误提示
    private String errorMsg;
    //模板编号
    private String templateCode;

    //动态定时任务id
    private Long dynamicTaskId;
}