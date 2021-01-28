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
import sy.model.app.MedicalReportAttr;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.model.easyui.Tree;
import sy.service.app.MedicalReportAttrServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户
 */
@Namespace("/app")
@Action
public class ReportAttrAction extends BaseAction<MedicalReportAttr> {
	private static final Logger logger = Logger
			.getLogger(ReportAttrAction.class);
	private int MedicalReportAttrId;

	private int templateId;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(MedicalReportAttrServiceI service) {
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
		if (!DataTypeUtil.isBlank(data.getAttrId())) {

			((MedicalReportAttrServiceI) service).updateMedicalReportAttr(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
		// hqlFilter
		// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		
		 hqlFilter.addFilter("QUERY_t#medicalReportDef#templateId_I_EQ",templateId+"");
		
		List<MedicalReportAttr> list = ((MedicalReportAttrServiceI) service)
				.getTreeGrid(hqlFilter);

		logger.info(" 获得资源treeGrid , 数量:" + list.size());
		logger.info(list.toString());
		
		writeJson(list);
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
//		 
//		Set<CustomerInfo> set = sessionInfo.getUser().getSycustomerInfo();
//		for (CustomerInfo customerInfo : set) {
//			int customerId = customerInfo.getCustomerId();
//			if (customerId > 0) {
//				hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
//			}
//		}
		super.addCutomerFilter(hqlFilter);
		if (templateId > 0) {
			hqlFilter.addFilter("QUERY_t#medicalReportDef#templateId_I_EQ",
					templateId + "");
		}
		grid.setTotal(((MedicalReportAttrServiceI) service)
				.countMedicalReportAttrByFilter(hqlFilter));
		grid.setRows(((MedicalReportAttrServiceI) service)
				.findMedicalReportAttrByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得主菜单tree，也用于获得上级菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		// hqlFilter
		// .addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		// hqlFilter.addFilter("QUERY_t#level_S_EQ", "0");// 0就是只查菜单

		List<MedicalReportAttr> resources = ((MedicalReportAttrServiceI) service)
				.getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (MedicalReportAttr medicalReportAttr : resources) {
			Tree node = new Tree();

			BeanUtils.copyNotNullProperties(medicalReportAttr, node,
					new String[] { "id", "pid" });
			node.setPid(medicalReportAttr.getPid() + "");
			node.setId(medicalReportAttr.getAttrId() + "");
			node.setText(medicalReportAttr.getAttrName());
			Map<String, String> attributes = new HashMap<String, String>();
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}
	
	/**
	 * 获得客户表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_MedicalReportAttrIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");

		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#id_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }

		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
	/**
	 * 保存一个客户
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((MedicalReportAttrServiceI) service).saveMedicalReportAttr(data,
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
		if (!DataTypeUtil.isBlank(MedicalReportAttrId)) {
			MedicalReportAttr t = service.getById(MedicalReportAttrId);
			((MedicalReportAttrServiceI) service).delete(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the MedicalReportAttrId
	 */
	public int getMedicalReportAttrId() {
		return MedicalReportAttrId;
	}

	/**
	 * @param MedicalReportAttrId
	 *            the MedicalReportAttrId to set
	 */
	public void setMedicalReportAttrId(int MedicalReportAttrId) {
		this.MedicalReportAttrId = MedicalReportAttrId;
	}

	/**
	 * @return the templateId
	 */
	public int getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

}
