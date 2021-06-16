package sy.model.demo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Entity
@Table(name = "tuser")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DemoUser implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tcompany_id")
	private DemoCompany company;
	private String name;
	@Column(length = 50)
	private String pwd;
	private Short sex;
	private Short age;
	private Date birthday;
	private Date created = new Date();
	private Date modified = new Date();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tuser_trole", joinColumns = { @JoinColumn(name = "tuser_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "trole_id", nullable = false, updatable = false) })
	private Set<DemoRole> roles = new HashSet<DemoRole>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DemoCompany getCompany() {
		return company;
	}

	public void setCompany(DemoCompany company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Set<DemoRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<DemoRole> roles) {
		this.roles = roles;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}