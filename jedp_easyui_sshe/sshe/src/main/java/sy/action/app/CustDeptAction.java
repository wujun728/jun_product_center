package sy.action.app;

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
import sy.model.app.CustDept;
import sy.model.app.CustomerInfo;
import sy.model.base.SessionInfo;
import sy.model.base.Syresource;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.CustDeptServiceI;
import sy.service.app.CustomerInfoServiceI;
import sy.service.base.SyresourceServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户
 */
@Namespace("/app")
@Action
public class CustDeptAction extends BaseAction<CustDept> {

	private static final Logger logger = Logger.getLogger(CustDeptAction.class);

	private int deptId;

	private int customerId;
	/**
	 * 客户类型 对应 CustomerInfo 中的 CUSTOMER_TYPE
	 */
	private String custType;

	public CustDept getData() {
		return data;
	}
	
	public void setData(CustDept data) {
		this.data = data;
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(CustDeptServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (Integer.valueOf(id) >= 0) {
			writeJson(service.getById(Integer.valueOf(id)));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	/**
	 * 获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTree() {
		HqlFilter hqlFilter = new HqlFilter();

		List<CustDept> CustDeptList = service.findByFilter(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (CustDept custDept : CustDeptList) {
			Tree node = new Tree();
			node.setId(custDept.getDeptId() + "");
			node.setPid(custDept.getPid() + "");
			node.setText(custDept.getDeptName());

			Map<String, String> attributes = new HashMap<String, String>();
			// attributes.put("url", "");
			attributes.put("target", "");
			node.setAttributes(attributes);
			tree.add(node);
		}

		writeJson(tree);
	}

	// /**
	// * 更新客户部门
	// */
	// public void update() {
	// Json json = new Json();
	// if (!StringUtils.isBlank(data.getId())) {
	// if (data.getCustDept() != null
	// && StringUtils.equals(data.getId(), data.getCustDept()
	// .getId())) {
	// json.setMsg("父客户部门不可以是自己！");
	// } else {
	// ((CustDeptServiceI) service).updateResource(data);
	// json.setSuccess(true);
	// }
	// }
	// writeJson(json);
	// }

	// /**
	// * 获得主菜单tree，也用于获得上级客户部门菜单combotree
	// */
	// public void doNotNeedSecurity_getMainMenu() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
	// ConfigUtil.getSessionInfoName());
	// hqlFilter
	// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
	// hqlFilter.addFilter("QUERY_t#CustDepttype#id_S_EQ", "0");// 0就是只查菜单
	// List<CustDept> resources = ((CustDeptServiceI) service)
	// .getMainMenu(hqlFilter);
	// List<Tree> tree = new ArrayList<Tree>();
	// for (CustDept resource : resources) {
	// Tree node = new Tree();
	// BeanUtils.copyNotNullProperties(resource, node);
	// node.setText(resource.getName());
	// Map<String, String> attributes = new HashMap<String, String>();
	// attributes.put("url", resource.getUrl());
	// attributes.put("target", resource.getTarget());
	// attributes.put("isOpen", resource.getIsOpen() + "");
	// if (resource.getIsOpen() == CustDept.IS_OPEN_FLAG.IS_OPEN_FLAG_CLOSE
	// .ordinal()) {
	// node.setIconCls("ext-icon-exclamation");
	// }
	// node.setAttributes(attributes);
	// tree.add(node);
	// }
	// writeJson(tree);
	// }

	/**
	 * 获得客户部门treeGrid - 获得菜单
	 */
	public void treeGridMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// 过滤用户ID
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId()
				+ "");
		// 过滤菜单还是功能
		hqlFilter.addFilter("QUERY_t#CustDepttype#id_S_EQ", "0");// 0就是只查菜单

		List<CustDept> list = ((CustDeptServiceI) service)
				.getMainMenu(hqlFilter);

		logger.info(" 获得客户部门treeGrid , 数量:" + list.size());
		logger.info(list.toString());
		writeJson(list);
	}

	/**
	 * 获得客户部门treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		
		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// int customerId = sessionInfo.getUser().getCustomerId();
		// if (customerId > 0) {
		// hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ",
		// customerId + "");
		// }
		
		super.addCutomerFilter(hqlFilter,
				"QUERY_t#customerInfo#customerId_I_EQ");
		
		hqlFilter.addFilter("QUERY_t#customerInfo#customerType_B_EQ",getCustType() + "");
		
		List<CustDept> list = ((CustDeptServiceI) service)
				.deptTreeGrid(hqlFilter);

		logger.info(" 获得客户部门treeGrid , 数量:" + list.size());
		logger.info(list.toString());
		writeJson(list);
	}

	// /**
	// * 获得角色的客户部门列表
	// */
	// public void doNotNeedSecurity_getRoleResources() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// hqlFilter.addFilter("QUERY_role#id_S_EQ", id);
	// writeJson(((CustDeptServiceI) service).findResourceByFilter(hqlFilter));
	// }
	//
	// /**
	// * 获得机构的客户部门列表
	// */
	// public void doNotNeedSecurity_getOrganizationResources() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// hqlFilter.addFilter("QUERY_organization#id_S_EQ", id);
	// writeJson(((CustDeptServiceI) service).findResourceByFilter(hqlFilter));
	// }

	/**
	 * 获得客户部门树
	 */
	public void doNotNeedSecurity_getResourcesTree() {
		treeGrid();
	}

	/**
	 */
	public void doNotNeedSessionAndSecurity_deptIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (getCustomerId() > 0) {
			hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ",
					getCustomerId() + "");
		}
		hqlFilter.addFilter("QUERY_t#deptName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");

		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#id_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }
		//
		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 更新客户部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getDeptId())) {

			((CustDeptServiceI) service).updateDept(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 保存一个客户部门
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			// if(getCustDept_deptId()>0){
			// CustDept c = ((CustDeptServiceI)
			// service).getById(getCustDept_deptId());
			// {
			// ((CustDept)data).setCustDept(c);
			// }
			// }
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((CustDeptServiceI) service).saveDept(data, sessionInfo.getUser()
					.getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(deptId)) {
			CustDept t = service.getById(deptId);
			((CustDeptServiceI) service).delete(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType
	 *            the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
}
