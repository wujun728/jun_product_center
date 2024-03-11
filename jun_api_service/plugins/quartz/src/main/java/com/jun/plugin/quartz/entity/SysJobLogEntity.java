package com.jun.plugin.quartz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jun.plugin.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_job_log")
public class SysJobLogEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
    @TableId("id")
    private String id;

    /**
     * 任务id
     */
    @TableField("job_id")
    private String jobId;

    /**
     * spring bean名称
     */
    @TableField("bean_name")
    private String beanName;

    /**
     * 参数
     */
    @TableField("params")
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    @TableField("status")
    private Integer status;

    /**
     * 失败信息
     */
    @TableField("error")
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    @TableField("times")
    private Integer times;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


}
