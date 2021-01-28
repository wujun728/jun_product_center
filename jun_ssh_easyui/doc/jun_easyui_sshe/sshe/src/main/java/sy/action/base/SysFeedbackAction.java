package sy.action.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.SessionInfo;
import sy.model.base.SysFeedback;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.base.SysFeedbackServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 系统反馈
 */
@SuppressWarnings("serial")
@Namespace("/base")
@Action
public class SysFeedbackAction extends BaseAction<SysFeedback> {
	private static final Logger logger = Logger
			.getLogger(SysFeedbackAction.class);
	private String SysFeedbackId;
	
	@Autowired
	public void setService(SysFeedbackServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!StringUtils.isBlank(SysFeedbackId)) {
			writeJson(service.getById(SysFeedbackId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	public void  doNotNeedSecurity_getById() {
		if (!StringUtils.isBlank(SysFeedbackId)) {
			writeJson(service.getById(SysFeedbackId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	/**
	 * 更新
	 */
	public void doNotNeedSecurity_update() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			//data.setStatus(SysFeedback.STATUS_DELETED);
			((SysFeedbackServiceI) service).updateSysFeedback(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	/**
	 * 角色grid
	 */
	public void doNotNeedSecurity_grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#status_B_NE", SysFeedback.STATUS_DELETED
				+ "");

		grid.setTotal(((SysFeedbackServiceI) service)
				.countSysFeedbackByFilter(hqlFilter));
		grid.setRows(((SysFeedbackServiceI) service).findSysFeedbackByFilter(
				hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 保存一个系统反馈
	 */
	public void doNotNeedSecurity_save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((SysFeedbackServiceI) service).saveSysFeedback(data, sessionInfo
					.getUser().getId());
			json.setSuccess(true);
			json.setMsg("新增成功！");
		}
		writeJson(json);
	}

	/**
	 * 删除一个对象
	 */
	public void doNotNeedSecurity_delete() {
		Json json = new Json();
		if (!StringUtils.isBlank(SysFeedbackId)) {
			SysFeedback t = service.getById(SysFeedbackId);
			t.setStatus(SysFeedback.STATUS_DELETED);
			((SysFeedbackServiceI) service).updateSysFeedback(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the SysFeedbackId
	 */
	public String getSysFeedbackId() {
		return SysFeedbackId;
	}

	/**
	 * @param SysFeedbackId
	 *            the SysFeedbackId to set
	 */
	public void setSysFeedbackId(String SysFeedbackId) {
		this.SysFeedbackId = SysFeedbackId;
	}

}
