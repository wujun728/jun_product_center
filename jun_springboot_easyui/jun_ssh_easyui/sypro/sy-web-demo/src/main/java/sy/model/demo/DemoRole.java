package sy.model.demo;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
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
@Table(name = "trole")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DemoRole implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tcompany_id")
	private DemoCompany company;
	private String name;
	private Date created = new Date();
	private Date modified = new Date();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trole_tresource", joinColumns = { @JoinColumn(name = "trole_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tresource_id", nullable = false, updatable = false) })
	private Set<DemoResource> resources = new HashSet<DemoResource>(0);
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tuser_trole", joinColumns = { @JoinColumn(name = "trole_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tuser_id", nullable = false, updatable = false) })
	private Set<DemoUser> users = new HashSet<DemoUser>(0);

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

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Set<DemoResource> getResources() {
		return resources;
	}

	public void setResources(Set<DemoResource> resources) {
		this.resources = resources;
	}

	public Set<DemoUser> getUsers() {
		return users;
	}

	public void setUsers(Set<DemoUser> users) {
		this.users = users;
	}

}