package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpMedicalDetail;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpMedicalDetailServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 加班请假
 */
@Namespace("/app")
@Action
public class EmpJiabanDetailAction extends BaseAction<EmpMedicalDetail> {
	private static final Logger logger = Logger
			.getLogger(EmpJiabanDetailAction.class);
	private int EmpJiabanDetailId;
	
	@Autowired
	public void setService(EmpMedicalDetailServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(EmpJiabanDetailId)) {
			writeJson(service.getById(EmpJiabanDetailId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新加班请假部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getId())) {
			((EmpMedicalDetailServiceI) service).updateEmpMedicalDetail(data);
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
				EmpMedicalDetail.STATUS_DELETED + "");

		grid.setTotal(((EmpMedicalDetailServiceI) service)
				.countEmpMedicalDetailByFilter(hqlFilter));
		grid.setRows(((EmpMedicalDetailServiceI) service)
				.findEmpMedicalDetailByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得加班请假表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpJiabanDetailIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpMedicalDetail.STATUS_DELETED + "");

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
	 * 保存一个加班请假
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((EmpMedicalDetailServiceI) service).saveEmpMedicalDetail(data,
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
		if (!DataTypeUtil.isBlank(EmpJiabanDetailId)) {
			EmpMedicalDetail t = service.getById(EmpJiabanDetailId);
			t.setStatus(EmpMedicalDetail.STATUS_DELETED);
			((EmpMedicalDetailServiceI) service).updateEmpMedicalDetail(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the EmpJiabanDetailId
	 */
	public int getEmpJiabanDetailId() {
		return EmpJiabanDetailId;
	}

	/**
	 * @param EmpJiabanDetailId
	 *            the EmpJiabanDetailId to set
	 */
	public void setEmpJiabanDetailId(int EmpJiabanDetailId) {
		this.EmpJiabanDetailId = EmpJiabanDetailId;
	}

}
