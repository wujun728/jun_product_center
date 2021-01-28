package sy.action.app;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustomerInfo;
import sy.model.app.DrugSpecInfo;
import sy.model.base.SessionInfo;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.DrugSpecInfoServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 用药库存记录
 */
@Namespace("/app")
@Action
public class DrugSpecInfoAction extends BaseAction<DrugSpecInfo> {
	private static final Logger logger = Logger
			.getLogger(DrugSpecInfoAction.class);
	private int DrugSpecInfoId;
	private int drugCode;
	private int customerId;

	@Autowired
	public void setService(DrugSpecInfoServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(DrugSpecInfoId)) {
			writeJson(service.getById(DrugSpecInfoId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新规格部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getSpecId())) {
			((DrugSpecInfoServiceI) service).updateDrugSpecInfo(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// int customerId = sessionInfo.getUser().getCustomerId();
		// if (customerId > 0) {
		// hqlFilter.addFilter("QUERY_t#customerId_I_EQ", customerId + "");
		// }
		// super.addCutomerFilter(hqlFilter,
		// "QUERY_t#custUser#customerInfo#customerId_I_EQ");

		hqlFilter.addFilter("QUERY_t#status_B_NE", DrugSpecInfo.STATUS_DELETED
				+ "");
		if (this.getDrugCode() > 0) {
			hqlFilter.addFilter("QUERY_t#drugInfo#drugCode_I_EQ", getDrugCode()
					+ "");
		}
		grid.setTotal(((DrugSpecInfoServiceI) service)
				.countDrugSpecInfoByFilter(hqlFilter));
		grid.setRows(((DrugSpecInfoServiceI) service).findDrugSpecInfoByFilter(
				hqlFilter, page, rows));

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

	public void doNotNeedSecurity_getMainMenu() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#drugInfo#drugName_S_LK", "%%"
				+ StringUtils.defaultString(q) + "%%");

		// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
		// ConfigUtil.getSessionInfoName());
		// // 过滤用户ID
		// if(sessionInfo.getUser().getCustomerId() >0){
		// hqlFilter
		// .addFilter("QUERY_t#id_I_EQ",
		// sessionInfo.getUser().getCustomerId()+"");
		// }
		addCutomerFilter(hqlFilter,
				"QUERY_t#drugInfo#customerInfo#customerId_I_EQ");
		if (this.getCustomerId() > 0) {
			hqlFilter.addFilter(
					"QUERY_t#drugInfo#customerInfo#customerId_I_EQ",
					this.getCustomerId() + "");
		}
		
		grid.setTotal(((DrugSpecInfoServiceI) service)
				.countMyDrugTimesByFilter(hqlFilter));
		grid.setRows(((DrugSpecInfoServiceI) service).findMyDrugTimesByFilter(
				hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得规格表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_DrugSpecInfoIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				DrugSpecInfo.STATUS_DELETED + "");

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
	 * 保存一个规格
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((DrugSpecInfoServiceI) service).saveDrugSpecInfo(data, sessionInfo
					.getUser().getId());
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
		if (!DataTypeUtil.isBlank(DrugSpecInfoId)) {
			DrugSpecInfo t = service.getById(DrugSpecInfoId);
			t.setStatus(DrugSpecInfo.STATUS_DELETED);
			((DrugSpecInfoServiceI) service).updateDrugSpecInfo(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the DrugSpecInfoId
	 */
	public int getDrugSpecInfoId() {
		return DrugSpecInfoId;
	}

	/**
	 * @param DrugSpecInfoId
	 *            the DrugSpecInfoId to set
	 */
	public void setDrugSpecInfoId(int DrugSpecInfoId) {
		this.DrugSpecInfoId = DrugSpecInfoId;
	}

	/**
	 * @return the drugCode
	 */
	public int getDrugCode() {
		return drugCode;
	}

	/**
	 * @param drugCode
	 *            the drugCode to set
	 */
	public void setDrugCode(int drugCode) {
		this.drugCode = drugCode;
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

}
