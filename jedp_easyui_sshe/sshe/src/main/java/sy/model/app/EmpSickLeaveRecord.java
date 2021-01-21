package sy.model.app;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import sy.model.base.Syuser;

/**
 * 员工病假记录
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "emp_sick_leave_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_sick_leave_record", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_sick_leave_record", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpSickLeaveRecord implements Serializable {

	/**
	 * 1 有效 99 删除
	 */
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	private int id;
	
	// private String userId;
	private CustUser custUser;

	private int type;

	private String reason;

	private String hospital;

	private String total;

	private int unit;

	private int number;

	private String sickYm;

	private Date sickStartDate;

	private Date sickEndDate;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_sick_leave_record")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "int(10)   NULL default null comment '客户的用户编号'")
	public CustUser getCustUser() {
		return custUser;
	}
	
	/**
	 * @param custUser
	 *            the custUser to set
	 */
	public void setCustUser(CustUser custUser) {
		this.custUser = custUser;
	}
	
	// @Column(name = "USER_ID", nullable = false, length = 36, columnDefinition
	// = "varchar(36) not null   comment '账户id'")
	// public String getUserId() {
	// return userId;
	// }
	//
	// public void setUserId(String userId) {
	// this.userId = userId;
	// }
	
	@Column(name = "TYPE", nullable = true, columnDefinition = "int(10)   default 0   comment '类别'")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "REASON", nullable = true, length = 40, columnDefinition = "varchar(40) not null   comment '病因'")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "HOSPITAL", nullable = true, length = 40, columnDefinition = "varchar(40) not null   comment '就诊医院'")
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@Column(name = "TOTAL", nullable = true, length = 40, columnDefinition = "varchar(40) not null   comment '汇总'")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Column(name = "UNIT", nullable = true, length = 8, columnDefinition = "int(8) not null   comment '单位'")
	public int getUnit() {
		return unit;
	}
	
	public void setUnit(int unit) {
		this.unit = unit;
	}

	@Column(name = "NUMBER", nullable = true, columnDefinition = "int not null   comment '数量'")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "SICK_YM", nullable = true, columnDefinition = "varchar(10) not null   comment '病假年月'")
	public String getSickYm() {
		return sickYm;
	}
	
	public void setSickYm(String sickYm) {
		this.sickYm = sickYm;
	}
	
	@Column(name = "SICK_START_DATE", nullable = true, columnDefinition = "datetime not null   comment '病假开始时间'")
	public Date getSickStartDate() {
		return sickStartDate;
	}
	
	public void setSickStartDate(Date sickStartDate) {
		this.sickStartDate = sickStartDate;
	}
	
	@Column(name = "SICK_END_DATE", nullable = true, columnDefinition = "datetime not null   comment '病假结束时间'")
	public Date getSickEndDate() {
		return sickEndDate;
	}

	public void setSickEndDate(Date sickEndDate) {
		this.sickEndDate = sickEndDate;
	}

	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36) default  null  comment '创建人'")
	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = true, columnDefinition = "datetime null  comment '创建时间'")
	public Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	@Column(name = "EXT1", nullable = true, columnDefinition = "varchar(200)   null comment '备注1'")
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