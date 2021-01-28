package sy.action.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.SelfMedicalDef;
import sy.model.base.SessionInfo;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.app.SelfMedicalDefServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 自定义
 */
@Namespace("/app")
@Action
public class SelfMedicalDefAction extends BaseAction<SelfMedicalDef> {
	private static final Logger logger = Logger
			.getLogger(SelfMedicalDefAction.class);
	private int defId;
	
	@Autowired
	public void setService(SelfMedicalDefServiceI service) {
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
	 * 更新自定义部门
	 */
	public void update() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(data.getDefId())) {

			((SelfMedicalDefServiceI) service).updateSelfMedicalDef(data);
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

		super.addCutomerFilter(hqlFilter);
		
		hqlFilter.addFilter("QUERY_t#status_B_NE",
				SelfMedicalDef.STATUS_DELETED + "");
		
		grid.setTotal(((SelfMedicalDefServiceI) service)
				.countSelfMedicalDefByFilter(hqlFilter));
		grid.setRows(((SelfMedicalDefServiceI) service)
				.findSelfMedicalDefByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 获得自定义表格下啦列表
	 */
	public void doNotNeedSessionAndSecurity_defIdComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		if (q != null) {
			hqlFilter.addFilter("QUERY_t#customerName_S_LK",
					"%%" + StringUtils.defaultString(q) + "%%");
			hqlFilter.addFilter("QUERY_t#customerEnName_S_LK", "%%"
					+ StringUtils.defaultString(q) + "%%");
		}

		hqlFilter.addFilter("QUERY_t#customerStatus_B_NE",
				SelfMedicalDef.STATUS_DELETED + "");
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
	 * 保存一个自定义
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((SelfMedicalDefServiceI) service).saveSelfMedicalDef(data,
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
		if (!DataTypeUtil.isBlank(defId)) {
			SelfMedicalDef t = service.getById(defId);
			t.setStatus(SelfMedicalDef.STATUS_DELETED);
			((SelfMedicalDefServiceI) service).updateSelfMedicalDef(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the defId
	 */
	public int getSelfMedicalDefId() {
		return defId;
	}

	/**
	 * @param defId
	 *            the defId to set
	 */
	public void setSelfMedicalDefId(int defId) {
		this.defId = defId;
	}

}
