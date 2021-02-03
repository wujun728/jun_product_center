package sy.model.aop;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 记录所有Controller访问日志
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Entity
@Table(name = "tlog")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ControllerLog implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	private String className;// 类名

	private String methodName;// 方法名

	private String methodFullName;// 方法全名

	private String methodCnName;// 中文方法名

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String argsContent;// 参数内容

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String returnValue;// 返回值

	private String ip;

	private String userName;// 操作用户

	private Date created = new Date();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodFullName() {
		return methodFullName;
	}

	public void setMethodFullName(String methodFullName) {
		this.methodFullName = methodFullName;
	}

	public String getMethodCnName() {
		return methodCnName;
	}

	public void setMethodCnName(String methodCnName) {
		this.methodCnName = methodCnName;
	}

	public String getArgsContent() {
		return argsContent;
	}

	public void setArgsContent(String argsContent) {
		this.argsContent = argsContent;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
