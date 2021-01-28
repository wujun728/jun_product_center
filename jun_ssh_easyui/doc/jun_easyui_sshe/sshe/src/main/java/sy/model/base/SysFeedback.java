package sy.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;

/**
 * 软件反馈
 * 
 * @author Wujun
 * 
 */
@Entity
@Table(name = "sys_feedback", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
// @TableGenerator(name = "SEQ_SYS_FEEDBACK", // 别名
// table = "GENERATOR_TABLE", // 生成的表名
// pkColumnName = "sequence_name", // key列名
// valueColumnName = "next_val", // value列名
// pkColumnValue = "SEQ_SYS_FEEDBACK", // 具体key内容
// initialValue = 1, // 初始值
// allocationSize = 1)
// // 增长值
public class SysFeedback implements java.io.Serializable {
	/**
	 * 0 提交 1 已经解决 2 待解决 3 未知 99 删除
	 */
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_RESOLVED = (byte) 1;
	@Transient
	public static final byte STATUS_WAIT_RESOLVED = (byte) 2;
	@Transient
	public static final byte STATUS_UNKONWN = (byte) 3;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	private String id;
	private String feedbackContent;
	private String feedbackEmail;
	private int type;

	private Date createdatetime;
	private Date updatedatetime;
	private byte status;
	private String ext1;
	private Syuser syuser;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36, columnDefinition = "varchar(36) NOT NULL comment '反馈id'")
	// @GeneratedValue(strategy = GenerationType.TABLE, generator =
	// "SEQ_SYS_FEEDBACK")
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
		// return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", columnDefinition = "datetime null comment '创建时间'")
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", columnDefinition = "datetime null comment '修改时间'")
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	/**
	 * @return the feedbackContent
	 */
	@Column(name = "feedback_content", columnDefinition = " varchar(500) comment '反馈内容'")
	public String getFeedbackContent() {
		return feedbackContent;
	}

	/**
	 * @param feedbackContent
	 *            the feedbackContent to set
	 */
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	/**
	 * @return the feedbackEmail
	 */
	@EmailValidator()
	@Column(name = "feedback_email", columnDefinition = " varchar(50) comment '反馈邮箱'")
	public String getFeedbackEmail() {
		return feedbackEmail;
	}

	/**
	 * @param feedbackEmail
	 *            the feedbackEmail to set
	 */
	public void setFeedbackEmail(String feedbackEmail) {
		this.feedbackEmail = feedbackEmail;
	}

	@Column(name = "TYPE", nullable = true, columnDefinition = "int(8)   default 0   comment '模块类型'")
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OP_USER_ID", columnDefinition = "varchar(36)   NULL default null comment '用户编号'")
	public Syuser getSyuser() {
		return syuser;
	}

	/**
	 * @param syuser
	 *            the syuser to set
	 */
	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
	}
	
	@Column(name = "EXT1", nullable = true, length = 40, columnDefinition = "varchar(400) null comment 'EXT1'")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint null comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
