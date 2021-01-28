package sy.action.app;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustomerInfo;
import sy.model.app.DrugInfo;
import sy.model.base.SessionInfo;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.DrugInfoServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 药品记录
 */
@Namespace("/app")
@Action
public class DrugInfoAction extends BaseAction<DrugInfo> {
	private static final Logger logger = Logger
			.getLogger(DrugInfoAction.class);
	
	private int DrugInfoId;
	
	private String durgName;
	private String shortName;
	 
	@Autowired
	public void setService(DrugInfoServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		System.out.println("getById执行...");
		if (!DataTypeUtil.isBlank(DrugInfoId)) {
			System.out.println("DrugInfoId:" + DrugInfoId);
			writeJson(service.getById(DrugInfoId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新体检部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getDrugCode())) {
			((DrugInfoServiceI) service).updateDrugInfo(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}
	
	
	public void doNotNeedSecurity_getMainMenu() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		 
		hqlFilter.addFilter("QUERY_t#drugName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
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
	 * 增加用户所属客户的过滤器
	 * 
	 * 超级管理员，不增加任何的过滤。一般的用户，强制给 客户编号 == -1
	 * 
	 * @param hqlFilter
	 * @param filtStr
	 */
	public void addCutomerFilter(HqlFilter hqlFilter, String filtStr) {
		if (filtStr == null || (filtStr != null && filtStr.isEmpty())) {
			addCutomerFilter(hqlFilter);
			return;
		}
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Syuser syuser = sessionInfo.getUser();
		Set<CustomerInfo> set = syuser.getSycustomerInfos();
		if (set == null || (set != null && set.isEmpty())) {
			if (!syuser.isAdmin()) {
				hqlFilter.addFilter(filtStr, "-1");
			}

			return;
		}
		if (!syuser.isAdmin()) {
		for (CustomerInfo customerInfo : set) {
			int customerId = customerInfo.getCustomerId();
			if (customerId > 0) {
				hqlFilter.addFilter(filtStr, customerId + "");
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
//		int customerId = sessionInfo.getUser().getCustomerId();
//		if (customerId > 0) {
//			hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
//		}
		if(durgName != null){
			hqlFilter.addFilter("QUERY_t#drugName_S_LK",
					durgName + "");
			
		}
		if(shortName != null){
			hqlFilter.addFilter("QUERY_t#shortName_S_LK",
					shortName + "");
		}
		addCutomerFilter(hqlFilter,"QUERY_t#customerInfo#customerId_I_EQ");
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				DrugInfo.STATUS_DELETED + "");

		grid.setTotal(((DrugInfoServiceI) service)
				.countDrugInfoByFilter(hqlFilter));
		grid.setRows(((DrugInfoServiceI) service)
				.findDrugInfoByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_DrugInfoIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				DrugInfo.STATUS_DELETED + "");

		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
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
	 * 保存一个体检
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			if(data.getCustomerInfo() == null){
				json.setSuccess(false);
				json.setMsg("请选择所属的客户！");
			}else{
				((DrugInfoServiceI) service).saveDrugInfo(data,
						sessionInfo.getUser().getId());
				json.setSuccess(true);
				json.setMsg("新增成功！");
			}
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(DrugInfoId)) {
			DrugInfo t = service.getById(DrugInfoId);
			t.setStatus(DrugInfo.STATUS_DELETED);
			((DrugInfoServiceI) service).updateDrugInfo(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the DrugInfoId
	 */
	public int getDrugInfoId() {
		return DrugInfoId;
	}

	/**
	 * @param DrugInfoId
	 *            the DrugInfoId to set
	 */
	public void setDrugInfoId(int DrugInfoId) {
		this.DrugInfoId = DrugInfoId;
	}

	/**
	 * @return the durgName
	 */
	public String getDurgName() {
		return durgName;
	}

	/**
	 * @param durgName the durgName to set
	 */
	public void setDurgName(String durgName) {
		this.durgName = durgName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
