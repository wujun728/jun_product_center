package com.pearadmin.schedule.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Describe: 定时任务日志记录
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("ScheduleLog")
public class ScheduleLog implements Serializable {

	/**
	 * 日志编号
	 * */
	private String logId;

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
	 * 参数集合
	 * */
	private String params;

	/**
	 * 日志状态
	 * */
	private Integer status;

	/**
	 * 异常信息
	 * */
	private String error;

	/**
	 * 运行时长
	 * */
	private Integer times;

	/**
	 * 创建时间
	 * */
	private LocalDateTime createTime;

}
