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

@Entity
@Table(name = "syonline", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
//@TableGenerator(name = "SEQ_SYONLINE", // 别名
//table = "GENERATOR_TABLE", // 生成的表名
//pkColumnName = "sequence_name", // key列名
//valueColumnName = "next_val", // value列名
//pkColumnValue = "SEQ_SYONLINE", // 具体key内容
//initialValue = 1, // 初始值
//allocationSize = 1)
//// 增长值
public class Syonline implements java.io.Serializable {

	private String id;
	private String loginname;
	private String ip;
	private Date createdatetime;
	private String type;// 1.登录0.注销

	public Syonline() {
	}

	public Syonline(String id) {
		this.id = id;
	}

	public Syonline(String id, String loginname, String ip, Date createdatetime, String type) {
		this.id = id;
		this.loginname = loginname;
		this.ip = ip;
		this.createdatetime = createdatetime;
		this.type = type;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36,columnDefinition = "varchar(36) NOT NULL comment '在线编号'")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_SYONLINE")
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

	@Column(name = "LOGINNAME", length = 100)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "IP", length = 100)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	@Column(name = "TYPE", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
