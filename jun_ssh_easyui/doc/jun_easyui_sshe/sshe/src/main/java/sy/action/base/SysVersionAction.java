package sy.action.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.SessionInfo;
import sy.model.base.SysSoftVersion;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.base.SysSoftVersionServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 系统版本
 */
@SuppressWarnings("serial")
@Namespace("/base")
@Action
public class SysVersionAction extends BaseAction<SysSoftVersion> {
	private static final Logger logger = Logger
			.getLogger(SysVersionAction.class);
	private int SysSoftVersionId;

	@Autowired
	public void setService(SysSoftVersionServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(SysSoftVersionId)) {
			writeJson(service.getById(SysSoftVersionId));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

	/**
	 * 更新
	 */
	public void update() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			((SysSoftVersionServiceI) service).updateSysSoftVersion(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	public void doNotNeedSecurity_read() {
		Json json = new Json();
		for (String id : ids.split(",")) {
			if (!StringUtils.isBlank(id)) {
				SysSoftVersion SysSoftVersion = service.getById(Integer
						.valueOf(id));
				if (SysSoftVersion != null) {
					((SysSoftVersionServiceI) service)
							.updateSysSoftVersion(SysSoftVersion);
				}
			}
		}
		json.setSuccess(true);
		json.setMsg("阅读成功！");

		writeJson(json);
	}

	/**
	 * 角色grid
	 */
	public void doNotNeedSecurity_grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		grid.setTotal(((SysSoftVersionServiceI) service)
				.countSysSoftVersionByFilter(hqlFilter));
		grid.setRows(((SysSoftVersionServiceI) service)
				.findSysSoftVersionByFilter(hqlFilter, page, rows));

		writeJson(grid);
	}

	/**
	 * 保存一个系统版本
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			((SysSoftVersionServiceI) service).saveSysSoftVersion(data,
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
		if (!DataTypeUtil.isBlank(SysSoftVersionId)) {
			SysSoftVersion t = service.getById(SysSoftVersionId);
			((SysSoftVersionServiceI) service).updateSysSoftVersion(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	
	/**
	 * @return the SysSoftVersionId
	 */
	public int getSysSoftVersionId() {
		return SysSoftVersionId;
	}

	/**
	 * @param SysSoftVersionId
	 *            the SysSoftVersionId to set
	 */
	public void setSysSoftVersionId(int SysSoftVersionId) {
		this.SysSoftVersionId = SysSoftVersionId;
	}

}
