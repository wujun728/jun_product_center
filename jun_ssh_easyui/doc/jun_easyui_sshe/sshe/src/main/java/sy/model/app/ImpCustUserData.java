package sy.model.app;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import sy.model.base.Syuser;

@SuppressWarnings("serial")
@Entity
@Table(name = "imp_cust_user_data", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_imp_cust_user_data", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_imp_cust_user_data", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// // 增长值
public class ImpCustUserData implements java.io.Serializable {
	
	private int id;
	
	private String srcFileName;
	private String newFileName;
	private String url;

	private int dataLine;
	private byte dataType;
	private Date impTime;

	private byte status;
	private Syuser syuser;
	private Date createTime;
	private Date updateTime;
	private String ext1;

	@Id
	@Column(name = "ID", unique = true, length = 10, columnDefinition = "int(10) not null   comment '编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_imp_cust_user_data")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SRC_FILE_NAME", nullable = true, length = 100, columnDefinition = "varchar(100) not null comment '原文件名'")
	public String getSrcFileName() {
		return srcFileName;
	}

	/**
	 * @param srcFileName
	 *            the srcFileName to set
	 */
	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}

	@Column(name = "NEW_FILE_NAME", nullable = true, length = 100, columnDefinition = "varchar(100) not null comment '新文件名称'")
	public String getNewFileName() {
		return newFileName;
	}

	/**
	 * @param newFileName
	 *            the newFileName to set
	 */
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	@Column(name = "URL", nullable = true, length = 100, columnDefinition = "varchar(100) not null comment '路径'")
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "DATA_LINE", nullable = true, length = 10, columnDefinition = "int not null comment '数据行数'")
	public int getDataLine() {
		return dataLine;
	}

	/**
	 * @param dataLine
	 *            the dataLine to set
	 */
	public void setDataLine(int dataLine) {
		this.dataLine = dataLine;
	}

	@Column(name = "DATA_TYPE", nullable = true, length = 10, columnDefinition = "tinyint not null comment '数据类型'")
	public byte getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(byte dataType) {
		this.dataType = dataType;
	}

	@Column(name = "IMP_TIME", nullable = true, columnDefinition = "datetime not null   comment '导入时间'")
	public Date getImpTime() {
		return impTime;
	}

	/**
	 * @param impTime
	 *            the impTime to set
	 */
	public void setImpTime(Date impTime) {
		this.impTime = impTime;
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

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint default 0 comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "varchar(36) NULL default null comment '用户编号'")
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

}