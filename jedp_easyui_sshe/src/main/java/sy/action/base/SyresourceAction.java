package sy.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.SessionInfo;
import sy.model.base.Syresource;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.base.SyresourceServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.HqlFilter;

/**
 * 资源
 * 
 * @author Wujun
 * 
 */
@Namespace("/base")
@Action
public class SyresourceAction extends BaseAction<Syresource> {

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyresourceServiceI service) {
		this.service = service;
	}

	/**
	 * 更新资源
	 */
	public void update() {
		Json json = new Json();
		if (data.getId() != null) {
			if (data.getSyresource() != null && data.getId().equals(data.getSyresource().getId())) {
				json.setMsg("父资源不可以是自己！");
			} else {
				((SyresourceServiceI) service).updateResource(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 获得主菜单tree，也用于获得上级资源菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单
		List<Syresource> resources = ((SyresourceServiceI) service).getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (Syresource resource : resources) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(resource, node);
			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		writeJson(((SyresourceServiceI) service).resourceTreeGrid(hqlFilter));
	}

	/**
	 * 获得角色的资源列表
	 */
	public void doNotNeedSecurity_getRoleResources() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_role#id_S_EQ", id);
		writeJson(((SyresourceServiceI) service).findResourceByFilter(hqlFilter));
	}

	/**
	 * 获得机构的资源列表
	 */
	public void doNotNeedSecurity_getOrganizationResources() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_organization#id_S_EQ", id);
		writeJson(((SyresourceServiceI) service).findResourceByFilter(hqlFilter));
	}

	/**
	 * 获得资源树
	 */
	public void doNotNeedSecurity_getResourcesTree() {
		treeGrid();
	}

	/**
	 * 保存一个资源
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((SyresourceServiceI) service).saveResource(data, sessionInfo.getUser().getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

}
