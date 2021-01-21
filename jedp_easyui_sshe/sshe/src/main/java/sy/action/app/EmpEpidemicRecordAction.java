package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpEpidemicRecord;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpEpidemicRecordServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 流行病
 */
@Namespace("/app")
@Action
public class EmpEpidemicRecordAction extends BaseAction<EmpEpidemicRecord> {
	private static final Logger logger = Logger
			.getLogger(EmpEpidemicRecordAction.class);
	
	private int EmpEpidemicRecordId;	
	
	@Autowired
	public void setService(EmpEpidemicRecordServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpEpidemicRecordId)) {
			writeJson(service.getById(EmpEpidemicRecordId));
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
		if (!DataTypeUtil.isBlank(data.getId())) {
			((EmpEpidemicRecordServiceI) service).updateEmpEpidemicRecord(data);
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
		super.addCutomerFilter(hqlFilter,
				"QUERY_t#custUser#customerInfo#customerId_I_EQ");

		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpEpidemicRecord.STATUS_DELETED + "");

		grid.setTotal(((EmpEpidemicRecordServiceI) service)
				.countEmpEpidemicRecordByFilter(hqlFilter));
		grid.setRows(((EmpEpidemicRecordServiceI) service)
				.findEmpEpidemicRecordByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得体检表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpEpidemicRecordIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpEpidemicRecord.STATUS_DELETED + "");

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
			((EmpEpidemicRecordServiceI) service).saveEmpEpidemicRecord(data,
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
		if (!DataTypeUtil.isBlank(EmpEpidemicRecordId)) {
			EmpEpidemicRecord t = service.getById(EmpEpidemicRecordId);
			t.setStatus(EmpEpidemicRecord.STATUS_DELETED);
			((EmpEpidemicRecordServiceI) service).updateEmpEpidemicRecord(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the EmpEpidemicRecordId
	 */
	public int getEmpEpidemicRecordId() {
		return EmpEpidemicRecordId;
	}

	/**
	 * @param EmpEpidemicRecordId
	 *            the EmpEpidemicRecordId to set
	 */
	public void setEmpEpidemicRecordId(int EmpEpidemicRecordId) {
		this.EmpEpidemicRecordId = EmpEpidemicRecordId;
	}

}
