package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.DrugTimes;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.DrugTimesServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 药品记录
 */
@Namespace("/app")
@Action
public class DrugTimesAction extends BaseAction<DrugTimes> {
	private static final Logger logger = Logger
			.getLogger(DrugTimesAction.class);
	
	private int DrugTimesId;

	 
	@Autowired
	public void setService(DrugTimesServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(DrugTimesId)) {
			writeJson(service.getById(DrugTimesId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

//	/**
//	 * 更新体检部门
//	 */
//	public void update() {
//		Json json = new Json();
//		if (!DataTypeUtil.isBlank(data.getDrugCode())) {
//			((DrugTimesServiceI) service).updateDrugTimes(data);
//			json.setSuccess(true);
//			json.setMsg("修改成功！");
//		}
//		writeJson(json);
//	}
	
	
	public void doNotNeedSecurity_getMainMenu() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		 
		hqlFilter.addFilter("QUERY_t#drugSpecInfo#drugInfo#drugName_S_LK",
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
		grid.setTotal(((DrugTimesServiceI) service).countMyDrugTimesByFilter(hqlFilter));
		grid.setRows(((DrugTimesServiceI) service).findMyDrugTimesByFilter(hqlFilter, page, rows));
		writeJson(grid);
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
		//super.addCutomerFilter(hqlFilter,"QUERY_t#custUser#customerInfo#customerId_I_EQ");
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				DrugTimes.STATUS_DELETED + "");

		grid.setTotal(((DrugTimesServiceI) service)
				.countDrugTimesByFilter(hqlFilter));
		grid.setRows(((DrugTimesServiceI) service)
				.findDrugTimesByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

//	/**
//	 * 获得体检表格下啦列表
//	 */
//	public void doNotNeedSessionAndSecurity_DrugTimesIdComboGrid() {
//		Grid grid = new Grid();
//		HqlFilter hqlFilter = new HqlFilter(getRequest());
//
//		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
//				"%%" + StringUtils.defaultString(q) + "%%");
//		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
//				"%%" + StringUtils.defaultString(q) + "%%");
//		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
//				DrugTimes.STATUS_DELETED + "");
//
//		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//				ConfigUtil.getSessionInfoName());
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
	
//	/**
//	 * 保存一个体检
//	 */
//	public void save() {
//		Json json = new Json();
//		if (data != null) {
//			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
//					ConfigUtil.getSessionInfoName());
//			if(data.getCustomerInfo() == null){
//				json.setSuccess(false);
//				json.setMsg("请选择所属的客户！");
//			}else{
//				((DrugTimesServiceI) service).saveDrugTimes(data,
//						sessionInfo.getUser().getId());
//				json.setSuccess(true);
//				json.setMsg("新增成功！");
//			}
//		}
//		writeJson(json);
//	}
//
//	/**
//	 * 删除一个对象
//	 */
//	public void delete() {
//		Json json = new Json();
//		if (!DataTypeUtil.isBlank(DrugTimesId)) {
//			DrugTimes t = service.getById(DrugTimesId);
//			t.setStatus(DrugTimes.STATUS_DELETED);
//			((DrugTimesServiceI) service).updateDrugTimes(t);
//			json.setSuccess(true);
//			json.setMsg("删除成功！");
//		}
//		writeJson(json);
//	}

	/**
	 * @return the DrugTimesId
	 */
	public int getDrugTimesId() {
		return DrugTimesId;
	}

	/**
	 * @param DrugTimesId
	 *            the DrugTimesId to set
	 */
	public void setDrugTimesId(int DrugTimesId) {
		this.DrugTimesId = DrugTimesId;
	}

}
