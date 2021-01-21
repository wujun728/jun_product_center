package org.zhanghua.ssm.entity.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zhanghua.ssm.common.entity.DataEntity;

/**
 * 资源(菜单、功能等)
 * 
 * @author Wujun
 * 
 */
public class Resource extends DataEntity<Resource> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name; // 名称

	private String url; // url

	private Resource parent; // 父节点

	private Integer isShow; // 是否显示 默认 0 显示 1 不显示

	private Integer weight; // 权重 越小排在最前面

	private String permission; // 权限

	private String icon; // 图标

	private String target; // 目标 ifarme/_blank等

	private List<Resource> childrens = new ArrayList<Resource>(); //子节点

	public Resource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<Resource> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Resource> childrens) {
		this.childrens = childrens;
	}

}