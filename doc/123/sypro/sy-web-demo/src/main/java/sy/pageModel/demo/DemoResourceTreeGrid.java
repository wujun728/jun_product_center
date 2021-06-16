package sy.pageModel.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import sy.model.demo.DemoResource;
import sy.model.demo.DemoRole;

public class DemoResourceTreeGrid extends TreeGrid {

	private Long id;
	private DemoResource resource;
	private String name;
	private Date created = new Date();
	private Date modified = new Date();
	private Set<DemoRole> roles = new HashSet<DemoRole>(0);
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
