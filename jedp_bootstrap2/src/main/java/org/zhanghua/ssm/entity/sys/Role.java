package org.zhanghua.ssm.entity.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * 角色
 * 
 * @author Wujun
 * 
 */
public class Role extends DataEntity<Role> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private List<Resource> resources; // 资源

	public Role() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<String> getResourceIds() {
		List<String> resourceIds = new ArrayList<String>();
		for (Resource resource : resources) {
			resourceIds.add(resource.getId());
		}
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		for (String id : resourceIds) {
			Resource resource = new Resource();
			resource.setId(id);
			resources.add(resource);
		}
	}

}