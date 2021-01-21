package sy.action.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.app.CustDept;
import sy.model.base.SessionInfo;
import sy.model.base.SysMessage;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.base.SysMessageServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 系统消息
 */
@SuppressWarnings("serial")
@Namespace("/base")
@Action
public class SysMessageAction extends BaseAction<SysMessage> {
	private static final Logger logger = Logger
			.getLogger(SysMessageAction.class);
	private int sysMessageId;

	@Autowired
	public void setService(SysMessageServiceI service) {
		this.service = service;
	}

	/**
	 * 获得一个对象
	 */
	@Override
	public void getById() {
		if (!DataTypeUtil.isBlank(sysMessageId)) {
			writeJson(service.getById(sysMessageId));
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
		if (!DataTypeUtil.isBlank(data.getId())) {
			data.setStatus(SysMessage.STATUS_DELETED);
			((SysMessageServiceI) service).updateSysMessage(data);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		writeJson(json);
	}

	public void doNotNeedSecurity_read() {
		Json json = new Json();
		for (String id : ids.split(",")) {
			if (!StringUtils.isBlank(id)) {
				SysMessage sysMessage = service.getById(Integer.valueOf(id));
				if (sysMessage != null) {
					sysMessage.setStatus(SysMessage.STATUS_READED);
					((SysMessageServiceI) service).updateSysMessage(sysMessage);
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
	public void  grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_t#messageTo_S_RLK", sessionInfo.getUser()
				.getLoginname() + "@");
		
		grid.setTotal(((SysMessageServiceI) service)
				.countSysMessageByFilter(hqlFilter));
		grid.setRows(((SysMessageServiceI) service).findSysMessageByFilter(
				hqlFilter, page, rows));

		writeJson(grid);
	}
	
	/**
	 * 角色grid
	 */
	public void doNotNeedSecurity_grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());

		hqlFilter.addFilter("QUERY_t#status_B_EQ", SysMessage.STATUS_NEW + "");
		
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_t#messageTo_S_RLK", sessionInfo.getUser()
				.getLoginname() + "");

		grid.setTotal(((SysMessageServiceI) service)
				.countSysMessageByFilter(hqlFilter));
		grid.setRows(((SysMessageServiceI) service).findSysMessageByFilter(
				hqlFilter, page, rows));

		writeJson(grid);
	}

	// /**
	// * 保存一个系统消息
	// */
	// public void save() {
	// Json json = new Json();
	// if (data != null) {
	// SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
	// ConfigUtil.getSessionInfoName());
	// ((SysMessageServiceI) service).saveSysMessage(data, sessionInfo
	// .getUser().getId());
	// json.setSuccess(true);
	// json.setMsg("新增成功！");
	// }
	// writeJson(json);
	// }

	/**
	 * 删除一个对象
	 */
	public void delete() {
		Json json = new Json();
		if (!DataTypeUtil.isBlank(sysMessageId)) {
			SysMessage t = service.getById(sysMessageId);
			t.setStatus(SysMessage.STATUS_DELETED);
			((SysMessageServiceI) service).updateSysMessage(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	/**
	 * @return the SysMessageId
	 */
	public int getSysMessageId() {
		return sysMessageId;
	}

	/**
	 * @param SysMessageId
	 *            the SysMessageId to set
	 */
	public void setSysMessageId(int sysMessageId) {
		this.sysMessageId = sysMessageId;
	}

}
