package sy.action.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.SessionInfo;
import sy.model.base.Syorganization;
import sy.model.base.Syuser;
import sy.model.easyui.Json;
import sy.service.base.SyorganizationServiceI;
import sy.service.base.SyuserServiceI;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 机构
 * 
 * 访问地址：/base/syorganization.sy
 * 
 * @author Wujun
 * 
 */
@Namespace("/base")
@Action
public class SyorganizationAction extends BaseAction<Syorganization> {

	@Autowired
	private SyuserServiceI userService;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyorganizationServiceI service) {
		this.service = service;
	}

	/**
	 * 保存一个机构
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((SyorganizationServiceI) service).saveOrganization(data, sessionInfo.getUser().getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 更新机构
	 */
	public void update() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			if (data.getSyorganization() != null && (data.getId() == data.getSyorganization().getId())) {
				json.setMsg("父机构不可以是自己！");
			} else {
				((SyorganizationServiceI) service).updateOrganization(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTree() {
		HqlFilter hqlFilter = new HqlFilter();
		writeJson(service.findByFilter(hqlFilter));
	}

	/**
	 * 机构授权
	 */
	public void grant() {
		Json json = new Json();
		((SyorganizationServiceI) service).grant(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 获得当前用户能看到的所有机构树
	 */
	public void doNotNeedSecurity_getSyorganizationsTree() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Syuser user = userService.getById(sessionInfo.getUser().getId());
		Set<Syorganization> organizations = user.getSyorganizations();
		List<Syorganization> l = new ArrayList<Syorganization>(organizations);
		Collections.sort(l, new Comparator<Syorganization>() {// 排序
					@Override
					public int compare(Syorganization o1, Syorganization o2) {
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
	 * 获得当前用户的机构
	 */
	public void doNotNeedSecurity_getSyorganizationByUserId() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", id+"");
		List<Syorganization> organizations = ((SyorganizationServiceI) service).findOrganizationByFilter(hqlFilter);
		writeJson(organizations);
	}

}
