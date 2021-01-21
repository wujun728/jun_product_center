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

@SuppressWarnings("serial")
@Entity
@Table(name = "emp_ill_his", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_ill_his", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_ill_his", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpIllHis implements Serializable {

	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	private int id;

	private CustUser custUser;

	private String jwbsContent;

	private String sysContent;

	private String jzContent;
	
	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '序号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_ill_his")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "int(10)   NULL default null comment '用户编号'")
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

	@Column(name = "JWBS_CONTENT", nullable = true, columnDefinition = "varchar(500) default null  comment '既往病史'")
	public String getJwbsContent() {
		return jwbsContent;
	}

	public void setJwbsContent(String jwbsContent) {
		this.jwbsContent = jwbsContent;
	}

	@Column(name = "SYS_CONTENT", nullable = true, columnDefinition = "varchar(500) default null  comment '生育史'")
	public String getSysContent() {
		return sysContent;
	}

	public void setSysContent(String sysContent) {
		this.sysContent = sysContent;
	}

	@Column(name = "JZ_CONTENT", nullable = true, columnDefinition = "varchar(500) default null  comment '家族史'")
	public String getJzContent() {
		return jzContent;
	}

	public void setJzContent(String jzContent) {
		this.jzContent = jzContent;
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
}