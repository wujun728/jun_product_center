package com.pearadmin.schedule.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Describe: 定时任务配置管理
 * Author: 就免仪式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("ScheduleJob")
public class ScheduleJob implements Serializable {

	/**
	 * 任务调度参数key
	 */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
	 * 任务编号
	 * */
	private String jobId;

	/**
	 * 任务名称
	 * */
	private String jobName;

	/**
	 * 运行类
	 * */
	private String beanName;

	/**
	 * 携带参数
	 * */
	private String params;

	/**
	 * cron 表达式
	 * */
	private String cronExpression;

	/**
	 * 状态
	 * */
	private String status;

	/**
	 * 分组编号
	 * */
	private String groupId;

	/**
	 * 创建时间
	 * */
	private LocalDateTime createTime;

	/**
	 * 备 注
	 * */
	private String remark;

}
