package sy.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 系统操作日志
 * 
 * @author Wujun
 * 
 */
@Entity
@Table(name = "sys_op_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysOpRecord implements java.io.Serializable {

	private String opId;
	private short opType;
	private String opUserId;
	private Date createdatetime;
	private Date updatedatetime;
	private String ext;

	@Id
	@Column(name = "OP_ID", unique = true, nullable = false, length = 20, columnDefinition = "varchar(20) not null comment '操作流水'")
	public String getId() {
		if (!StringUtils.isBlank(this.opId)) {
			return this.opId;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.opId = id;
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
	 * @return the opType
	 */
	@Column(name = "OP_TYPE", columnDefinition = "tinyint not null comment '见系统字典表'")
	public short getOpType() {
		return opType;
	}

	/**
	 * @param opType
	 *            the opType to set
	 */
	public void setOpType(short opType) {
		this.opType = opType;
	}

	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", columnDefinition = "int(8) not null comment '操作人'")
	public String getOpUserId() {
		return opUserId;
	}

	/**
	 * @param opUserId
	 *            the opUserId to set
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	
	/**
	 * @return the desc
	 */
	@Column(name = "EXT", columnDefinition = "varchar(300)   null comment '备注'")
	public String getExt() {
		return ext;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

}
