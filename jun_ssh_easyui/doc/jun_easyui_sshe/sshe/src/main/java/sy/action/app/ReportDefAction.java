package sy.action.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustomerInfo;
import sy.model.app.MedicalReportDef;
import sy.model.app.MedicalReportDef;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.SessionInfo;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.MedicalReportDefServiceI;
import sy.service.app.PhysicalTypeDefServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户
 */
@Namespace("/app")
@Action
public class ReportDefAction extends BaseAction<MedicalReportDef> {

	private static final Logger logger = Logger
			.getLogger(ReportDefAction.class);
	private int MedicalReportDefId;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(MedicalReportDefServiceI service) {
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
	 * 更新客户部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getTemplateId())) {

			((MedicalReportDefServiceI) service).updateMedicalReportDef(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}
	public void addCutomerFilter(HqlFilter hqlFilter) {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser syuser = sessionInfo.getUser();
		Set<CustomerInfo> set = syuser.getSycustomerInfos();
		if (set == null || (set != null && set.isEmpty())) {
			if (!syuser.isAdmin()) {
				hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ", "-1");
			}

			return;
		}
		if (!syuser.isAdmin()) {
			for (CustomerInfo customerInfo : set) {
				int customerId = customerInfo.getCustomerId();
				if (customerId > 0) {
					hqlFilter.addFilter("QUERY_t#customerInfo#customerId_I_EQ", customerId
							+ "");
				}
			}
		}
	}
	
	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
//		Set<CustomerInfo> set = sessionInfo.getUser().getSycustomerInfo();
//		for (CustomerInfo customerInfo : set) {
//			int customerId = customerInfo.getCustomerId();
//			if (customerId > 0) {
//				hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
//			}
//		}
		
		this.addCutomerFilter(hqlFilter);
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				MedicalReportDef.STATUS_DELETED + "");

		grid.setTotal(((MedicalReportDefServiceI) service)
				.countMedicalReportDefByFilter(hqlFilter));
		grid.setRows(((MedicalReportDefServiceI) service)
				.findMedicalReportDefByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得主菜单tree，也用于获得上级资源菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// hqlFilter
		// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		// hqlFilter.addFilter("QUERY_t#level_S_EQ", "0");// 0就是只查菜单

		List<MedicalReportDef> resources = ((MedicalReportDefServiceI) service)
				.getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (MedicalReportDef medicalReportDef : resources) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(medicalReportDef, node,
					new String[] { "id", "pid" });
			node.setPid("");
			node.setId(medicalReportDef.getTemplateId() + "");
			node.setText(medicalReportDef.getTemplateName());
			Map<String, String> attributes = new HashMap<String, String>();
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}
	
//	/**
//	 * 获得客户表格下啦列表
//	 */
//	public void doNotNeedSessionAndSecurity_MedicalReportDefIdComboGrid() {
//		Grid grid = new Grid();
//		HqlFilter hqlFilter = new HqlFilter(getRequest());
//
//		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
//				"%%" + StringUtils.defaultString(q) + "%%");
//		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
//				"%%" + StringUtils.defaultString(q) + "%%");
//		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
//				MedicalReportDef.STATUS_DELETED + "");
//
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
//
//		// // 过滤用户ID
//		// if(sessionInfo.getUser().getCustomerId() >0){
//		// hqlFilter
//		// .addFilter("QUERY_t#id_I_EQ",
//		// sessionInfo.getUser().getCustomerId()+"");
//		// }
//
//		grid.setTotal(service.countByFilter(hqlFilter));
//		grid.setRows(service.findByFilter(hqlFilter, page, rows));
//		writeJson(grid);
//	}

	// /**
	// * 获得客户部门treeGrid
	// */
	// public void treeGrid() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
	// ConfigUtil.getSessionInfoName());
	// int customerId = sessionInfo.getUser().getCustomerId();
	// if (customerId > 0) {
	// hqlFilter.addFilter("QUERY_t#MedicalReportDef#customerId_I_EQ",
	// customerId + "");
	// }
	//
	// List<MedicalReportDef> list = ((MedicalReportDefServiceI) service)
	// .deptTreeGrid(hqlFilter);
	//
	// logger.info(" 获得客户部门treeGrid , 数量:" + list.size());
	// logger.info(list.toString());
	// writeJson(list);
	// }

	// /**
	// * 获得角色的客户部门列表
	// */
	// public void doNotNeedSecurity_getRoleResources() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// hqlFilter.addFilter("QUERY_role#id_S_EQ", id);
	// writeJson(((MedicalReportDefServiceI)
	// service).findResourceByFilter(hqlFilter));
	// }
	//
	// /**
	// * 获得机构的客户部门列表
	// */
	// public void doNotNeedSecurity_getOrganizationResources() {
	// HqlFilter hqlFilter = new HqlFilter(getRequest());
	// hqlFilter.addFilter("QUERY_organization#id_S_EQ", id);
	// writeJson(((MedicalReportDefServiceI)
	// service).findResourceByFilter(hqlFilter));
	// }

	// /**
	// * 获得客户部门树
	// */
	// public void doNotNeedSecurity_getResourcesTree() {
	// treeGrid();
	// }

	/**
	 * 保存一个客户
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((MedicalReportDefServiceI) service).saveMedicalReportDef(data,
					sessionInfo.getUser().getId());
			json.setSuccess(true);
			json.setMsg("新增成功！");
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(MedicalReportDefId)) {
			MedicalReportDef t = service.getById(MedicalReportDefId);
			t.setStatus(MedicalReportDef.STATUS_DELETED);
			((MedicalReportDefServiceI) service).updateMedicalReportDef(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the MedicalReportDefId
	 */
	public int getMedicalReportDefId() {
		return MedicalReportDefId;
	}

	/**
	 * @param MedicalReportDefId
	 *            the MedicalReportDefId to set
	 */
	public void setMedicalReportDefId(int MedicalReportDefId) {
		this.MedicalReportDefId = MedicalReportDefId;
	}

}
