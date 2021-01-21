package sy.util.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import sy.model.base.SessionInfo;
import sy.model.base.Syorganization;
import sy.model.base.Syresource;
import sy.model.base.Syrole;

/**
 * 用于前台页面判断是否有权限的工具类
 * 
 * @author Wujun
 * 
 */
public class SecurityUtil {
	private HttpSession session;

	public SecurityUtil(HttpSession session) {
		this.session = session;
	}

	/**
	 * 判断当前用户是否可以访问某资源
	 * 
	 * @param url
	 *            资源地址
	 * @return
	 */
	public boolean havePermission(String url) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Syresource> resources = new ArrayList<Syresource>();
		for (Syrole role : sessionInfo.getUser().getSyroles()) {
			resources.addAll(role.getSyresources());
		}
		for (Syorganization organization : sessionInfo.getUser().getSyorganizations()) {
			resources.addAll(organization.getSyresources());
		}
		resources = new ArrayList<Syresource>(new HashSet<Syresource>(resources));// 去重(这里包含了当前用户可访问的所有资源)
		for (Syresource resource : resources) {
			if (resource.getUrl() != null && resource.getUrl().equals(url)) {// 如果有相同的，则代表当前用户可以访问这个资源
				return true;
			}
		}
		return false;
	}
}
