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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
@Entity
@Table(name = "tresource")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DemoResource implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tresource_id")
	private DemoResource resource;
	private String name;
	private Date created = new Date();
	private Date modified = new Date();
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "resources")
	private Set<DemoRole> roles = new HashSet<DemoRole>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	private Set<DemoResource> resources = new HashSet<DemoResource>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DemoResource getResource() {
		return resource;
	}

	public void setResource(DemoResource resource) {
		this.resource = resource;
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

	public Set<DemoResource> getResources() {
		return resources;
	}

	public void setResources(Set<DemoResource> resources) {
		this.resources = resources;
	}

}