package sy.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 系统消息
 * 
 * @author Wujun
 * 
 */
@Entity
@Table(name = "sys_message", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_sys_message", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_sys_message", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class SysMessage implements java.io.Serializable {
	/**
	 * 新建
	 */
	@Transient
	public static final byte STATUS_NEW = (byte) 0;

	/**
	 * 已读
	 */
	@Transient
	public static final byte STATUS_READED = (byte) 1;

	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	private int id;

	private String userId;
	private String messageBody;
	private String messageSubject;
	private String messageFrom;
	private String messageTo;
	private String ip;
	private Date createdatetime;
	private Date updateTime;
	private byte status;
	
	@Id
	@Column(name = "ID", unique = true, length = 10, columnDefinition = "int(10) not null   comment '消息编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_sys_message")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "IP", length = 100)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "USER_ID", nullable = true, columnDefinition = "varchar(36)   default 0   comment '创建人'")
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "MESSAGE_BODY", nullable = true, columnDefinition = "varchar(2000)   default 0   comment '消息体'")
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * @param messageBody
	 *            the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	@Column(name = "MESSAGE_SUBJECT", nullable = true, columnDefinition = "varchar(200)   default 0   comment '消息主题'")
	public String getMessageSubject() {
		return messageSubject;
	}

	/**
	 * @param messageSubject
	 *            the messageSubject to set
	 */
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}

	@Column(name = "MESSAGE_FROM", nullable = true, columnDefinition = "varchar(100)   default 0   comment '消息来源'")
	public String getMessageFrom() {
		return messageFrom;
	}

	/**
	 * @param messageFrom
	 *            the messageFrom to set
	 */
	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	@Column(name = "MESSAGE_TO", nullable = true, columnDefinition = "varchar(100)   default 0   comment '消息来源'")
	public String getMessageTo() {
		return messageTo;
	}

	/**
	 * @param messageTo
	 *            the messageTo to set
	 */
	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", nullable = true, columnDefinition = "datetime   null  comment '修改时间'")
	public Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint default 0 comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
