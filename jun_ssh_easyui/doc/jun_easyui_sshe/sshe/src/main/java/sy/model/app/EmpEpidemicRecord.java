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

/**
 * 员工流行病记录
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "emp_epidemic_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_epidemic_record", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_epidemic_record", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpEpidemicRecord implements Serializable {

	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	
	// 疑似 确证
	@Transient
	public static final byte SUSPECTED_YS = (byte) 1;
	@Transient
	public static final byte SUSPECTED_QZ = (byte) 2;
	
	private int id;
	private CustUser custUser;
	private int illType;
	private Date startTime;
	private Date endTime;
	/**
	 * 确诊时间
	 */
	private Date confirmDate;

	private Date backDate;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;
	
	private byte suspected;
	

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '流行病编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_epidemic_record")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "int(10)   NULL default null comment '员工编号'")
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

	@Column(name = "ILL_TYPE", nullable = false, columnDefinition = "int(10) not null   comment '流行病类型'")
	public int getIllType() {
		return illType;
	}

	public void setIllType(int illType) {
		this.illType = illType;
	}

	@Column(name = "START_TIME", nullable = true, columnDefinition = "datetime   null   comment '开始时间'")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", nullable = true, columnDefinition = "datetime   null   comment '结束时间'")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CONFIRM_DATE", nullable = true, columnDefinition = "datetime   null   comment '确诊时间'")
	public Date getConfirmDate() {
		return confirmDate;
	}

	/**
	 * @param confirmDate
	 *            the confirmDate to set
	 */
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "BACK_DATE", nullable = true, columnDefinition = "datetime   null   comment '返回时间'")
	public Date getBackDate() {
		return backDate;
	}

	/**
	 * @param backDate
	 *            the backDate to set
	 */
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36) default  0   comment '创建人'")
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

	@Column(name = "SUSPECTED", nullable = true, columnDefinition = "tinyint null comment '1 疑似 2 确诊'")
	public byte getSuspected() {
		return suspected;
	}

	/**
	 * @param suspected the suspected to set
	 */
	public void setSuspected(byte suspected) {
		this.suspected = suspected;
	}
	
	

}