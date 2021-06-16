package sy.model.demo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "tcompany")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DemoCompany implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	private String name;
	private Date created = new Date();
	private Date modified = new Date();
	@OneToMany(mappedBy = "company")
	private Set<DemoRole> roles = new HashSet<DemoRole>(0);
	@OneToMany(mappedBy = "company")
	private Set<DemoUser> users = new HashSet<DemoUser>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<DemoUser> getUsers() {
		return users;
	}

	public void setUsers(Set<DemoUser> users) {
		this.users = users;
	}

}