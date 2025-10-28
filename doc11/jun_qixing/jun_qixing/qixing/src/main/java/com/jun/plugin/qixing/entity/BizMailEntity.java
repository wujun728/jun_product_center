package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 短信&消息&邮件
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@Data
@TableName("biz_mail")
public class BizMailEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 邮件ID
	 */
	@Id
	@TableId("id")
	private BigDecimal id;

	/**
	 * 收件人
	 */
	@TableField(value = "recipients"  )
	private String recipients;

	/**
	 * 抄送人
	 */
	@TableField(value = "copy_recipients"  )
	private String copyRecipients;

	/**
	 * 匿名抄送人
	 */
	@TableField(value = "blind_copy_recipients"  )
	private String blindCopyRecipients;

	/**
	 * 消息类型(1-邮件；2-短信)
	 */
	@TableField(value = "dict_msg_type"  )
	private String dictMsgType;

	/**
	 * 邮件标题
	 */
	@TableField(value = "mail_subject"  )
	private String mailSubject;

	/**
	 * 邮件正文
	 */
	@TableField(value = "mail_body"  )
	private String mailBody;

	/**
	 * 消息格式(TEXT HTML)
	 */
	@TableField(value = "dict_body_format"  )
	private String dictBodyFormat;

	/**
	 * 重要级别(0 低，1 普通，2 高)
	 */
	@TableField(value = "dict_importance"  )
	private String dictImportance;

	/**
	 * 保密级别(1 普通 2 个人 3 私密 4 机密)
	 */
	@TableField(value = "sensitivity"  )
	private String sensitivity;

	/**
	 * 请求发送日期
	 */
	@TableField(value = "request_date"  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date requestDate;

	/**
	 * 发送状态(1 sent - 邮件已发送。2 unsent - 数据库邮件仍在尝试发送消息。3 failed - 数据库无法发送消息。)
	 */
	@TableField(value = "sent_status"  )
	private String sentStatus;

	/**
	 * 最多重试5次
	 */
	@TableField(value = "retrying_times"  )
	private BigDecimal retryingTimes;

	/**
	 * 发送日期
	 */
	@TableField(value = "sent_date"  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date sentDate;


}
