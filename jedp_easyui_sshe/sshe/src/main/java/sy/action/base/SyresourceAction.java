package sy.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 资源
 */
@Namespace("/base")
@Action
public class SyresourceAction extends BaseAction<Syresource> {
	private static final Logger logger = Logger
			.getLogger(SyresourceAction.class);
	
	private String nodeType;
	
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
	 * 关闭资源
	 */
	public void updateCloseFlag() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			if (data.getSyresource() != null
					&& (data.getId() == data.getSyresource().getId())) {
				json.setMsg("父资源不可以是自己！");
			} else {
				if (data.getIsOpen() == (short) Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_OPEN
						.ordinal()) {
					json.setMsg("开启==>>关闭！");
					data.setIsOpen((short) Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_CLOSE
							.ordinal());
				} else if (data.getIsOpen() == (short) 1) {
					json.setMsg("关闭==>>关闭！");
					data.setIsOpen((short) Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_CLOSE
							.ordinal());
				}
				((SyresourceServiceI) service).updateResource(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 开启OR关闭资源
	 */
	public void updateOpenFlag() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			if (data.getSyresource() != null
					&& (data.getId() == data.getSyresource().getId())) {
				json.setMsg("父资源不可以是自己！");
			} else {
				if (data.getIsOpen() == (short) Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_OPEN
						.ordinal()) {
					json.setMsg("开启==>>开启！");
					data.setIsOpen((short) Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_OPEN
							.ordinal());
				} else if (data.getIsOpen() == (short) 1) {
					json.setMsg("关闭==>>开启！");
					data.setIsOpen((short) Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_OPEN
							.ordinal());
				}
				((SyresourceServiceI) service).updateResource(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 更新资源
	 */
	public void update() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			if (data.getSyresource() != null
					&& (data.getId() == data.getSyresource().getId())) {
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
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId()
				+ "");
		hqlFilter.addFilter("QUERY_t#nodeType_ST_EQ", getNodeType());// 0就是只查菜单
		hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单
		hqlFilter.addSort("t.seq");
		List<Syresource> resources = ((SyresourceServiceI) service)
				.getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (Syresource resource : resources) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(resource, node);
			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			attributes.put("target", resource.getTarget());
			attributes.put("isOpen", resource.getIsOpen() + "");
			if (resource.getIsOpen() == Syresource.IS_OPEN_FLAG.IS_OPEN_FLAG_CLOSE
					.ordinal()) {
				node.setIconCls("ext-icon-exclamation");
			}
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 获得资源treeGrid - 获得菜单
	 */
	public void treeGridMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// 过滤用户ID
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId()
				+ "");
		// 过滤菜单还是功能
		hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单

		List<Syresource> list = ((SyresourceServiceI) service)
				.getMainMenu(hqlFilter);

		logger.info(" 获得资源treeGrid , 数量:" + list.size());
		logger.info(list.toString());
		writeJson(list);
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId()
				+ "");
		List<Syresource> list = ((SyresourceServiceI) service)
				.resourceTreeGrid(hqlFilter);

		logger.info(" 获得资源treeGrid , 数量:" + list.size());
		logger.info(list.toString());
		writeJson(list);
	}

	/**
	 * 获得角色的资源列表
	 */
	public void doNotNeedSecurity_getRoleResources() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_role#id_S_EQ", id+"");
		writeJson(((SyresourceServiceI) service)
				.findResourceByFilter(hqlFilter));
	}

	/**
	 * 获得机构的资源列表
	 */
	public void doNotNeedSecurity_getOrganizationResources() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_organization#id_S_EQ", id+"");
		writeJson(((SyresourceServiceI) service)
				.findResourceByFilter(hqlFilter));
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
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((SyresourceServiceI) service).saveResource(data, sessionInfo
					.getUser().getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * @return the nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}

	/**
	 * @param nodeType the nodeType to set
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

}
