package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.EmpSickLeaveRecord;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.EmpSickLeaveRecordServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 病假
 */
@Namespace("/app")
@Action
public class SickUserAction extends BaseAction<EmpSickLeaveRecord> {
	private static final Logger logger = Logger.getLogger(SickUserAction.class);
	private int empSickLeaveRecordId;
	/**
	 * 客户类型 对应 CustomerInfo 中的 CUSTOMER_TYPE
	 */
	private String custType;
	
	@Autowired
	public void setService(EmpSickLeaveRecordServiceI service) {
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
	 * 更新病假部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getId())) {

			((EmpSickLeaveRecordServiceI) service)
					.updateEmpSickLeaveRecord(data);
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
		super.addCutomerFilter(hqlFilter);
		hqlFilter.addFilter("QUERY_t#custUser#customerInfo#customerType_B_EQ", getCustType() + "");
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				EmpSickLeaveRecord.STATUS_DELETED + "");

		grid.setTotal(((EmpSickLeaveRecordServiceI) service)
				.countEmpSickLeaveRecordByFilter(hqlFilter));
		grid.setRows(((EmpSickLeaveRecordServiceI) service)
				.findEmpSickLeaveRecordByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得病假表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_EmpSickLeaveRecordIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#customerName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerEnName_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				EmpSickLeaveRecord.STATUS_DELETED + "");
		super.addCutomerFilter(hqlFilter);
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
	 * 保存一个病假
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((EmpSickLeaveRecordServiceI) service).saveEmpSickLeaveRecord(data,
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
		if (!DataTypeUtil.isBlank(empSickLeaveRecordId)) {
			EmpSickLeaveRecord t = service.getById(empSickLeaveRecordId);
			t.setStatus(EmpSickLeaveRecord.STATUS_DELETED);
			((EmpSickLeaveRecordServiceI) service).updateEmpSickLeaveRecord(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the EmpSickLeaveRecordId
	 */
	public int getEmpSickLeaveRecordId() {
		return empSickLeaveRecordId;
	}

	/**
	 * @param EmpSickLeaveRecordId
	 *            the EmpSickLeaveRecordId to set
	 */
	public void setEmpSickLeaveRecordId(int empSickLeaveRecordId) {
		this.empSickLeaveRecordId = empSickLeaveRecordId;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

}
