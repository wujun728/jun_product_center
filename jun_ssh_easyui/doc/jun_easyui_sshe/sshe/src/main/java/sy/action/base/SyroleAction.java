package sy.action.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.SessionInfo;
import sy.model.base.Syrole;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.base.SyroleServiceI;
import sy.service.base.SyuserServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 角色
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Namespace("/base")
@Action
public class SyroleAction extends BaseAction<Syrole> {

	@Autowired
	private SyuserServiceI userService;
	
	protected String userids;// 主键集合，逗号分割
	
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyroleServiceI service) {
		this.service = service;
	}
	
	/**
	 * 关联角色和用户
	 */
	public void relationRoleUser() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			((SyroleServiceI) service).relationRoleUser(id, userids);
			json.setSuccess(true);
			json.setMsg("关联角色和用户成功！");
		}
		
		writeJson(json);
	}
	
	/**
	 * 角色grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId()+"");
		grid.setTotal(((SyroleServiceI) service).countRoleByFilter(hqlFilter));
		grid.setRows(((SyroleServiceI) service).findRoleByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 保存一个角色
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((SyroleServiceI) service).saveRole(data, sessionInfo.getUser().getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 角色授权
	 */
	public void grant() {
		Json json = new Json();
		((SyroleServiceI) service).grant(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 获得当前用户能看到的所有角色树
	 */
	public void doNotNeedSecurity_getRolesTree() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Syuser user = userService.getById(sessionInfo.getUser().getId());
		Set<Syrole> roles = user.getSyroles();
		List<Syrole> l = new ArrayList<Syrole>(roles);
		Collections.sort(l, new Comparator<Syrole>() {// 排序
					@Override
					public int compare(Syrole o1, Syrole o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		writeJson(l);
	}

	/**
	 * 获得当前用户的角色
	 */
	public void doNotNeedSecurity_getRoleByUserId() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", id+"");
		List<Syrole> roles = ((SyroleServiceI) service).findRoleByFilter(hqlFilter);
		writeJson(roles);
	}

	/**
	 * 用户角色分布报表
	 */
	public void doNotNeedSecurity_userRoleChart() {
		List<Syrole> roles = service.find();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Syrole role : roles) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", role.getName());
			m.put("y", userService.countUserByRoleId(role.getId()));
			m.put("sliced", false);
			m.put("selected", false);
			list.add(m);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "无");
		m.put("y", userService.countUserByNotRoleId());
		m.put("sliced", true);
		m.put("selected", true);
		list.add(m);
		writeJson(list);
	}
	
	public String getUserids() {
		return userids;
	}

	public void setUserids(String userids) {
		this.userids = userids;
	}
}
