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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 软件版本
 * 
 * @author Wujun
 * 
 */
@Entity
@Table(name = "sys_soft_version", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
//@TableGenerator(name = "SEQ_SOFT_VERSION", // 别名
//table = "GENERATOR_TABLE", // 生成的表名
//pkColumnName = "sequence_name", // key列名
//valueColumnName = "next_val", // value列名
//pkColumnValue = "SEQ_SOFT_VERSION", // 具体key内容
//initialValue = 1, // 初始值
//allocationSize = 1)
//// 增长值
public class SysSoftVersion implements java.io.Serializable {

	private String id;
	private String version;
	private String versionDesc;
	private Date createdatetime;
	private Date updatedatetime;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36, columnDefinition = "varchar(36) NOT NULL comment '编号'")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_SOFT_VERSION")
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
//		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "VERSION",columnDefinition="varchar(100) null comment '版本'")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "VERSION_DESC",columnDefinition="varchar(1000) null comment '版本描述'")
	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME",columnDefinition="datetime null comment '创建时间'")
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}
	
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME",columnDefinition="datetime null comment '修改时间'")
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

}
