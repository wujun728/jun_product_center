package sy.interceptor.base;

import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import sy.model.base.SessionInfo;
import sy.model.base.Syorganization;
import sy.model.base.Syresource;
import sy.model.base.Syrole;
import sy.util.base.ConfigUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author Wujun
 * 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String servletPath = ServletActionContext.getRequest().getServletPath();
		servletPath = servletPath.substring(0, servletPath.lastIndexOf("."));// 去掉后面的后缀 *.sy *.action之类的

		logger.info("进入权限拦截器->访问的资源为：[" + servletPath + "]");

		Set<Syrole> roles = sessionInfo.getUser().getSyroles();
		for (Syrole role : roles) {
			for (Syresource resource : role.getSyresources()) {
				if (resource != null && resource.getUrl() != null && resource.getUrl().equals(servletPath)) {
					return actionInvocation.invoke();
				}
			}
		}
		Set<Syorganization> organizations = sessionInfo.getUser().getSyorganizations();
		for (Syorganization organization : organizations) {
			for (Syresource resource : organization.getSyresources()) {
				if (resource != null && resource.getUrl() != null && resource.getUrl().equals(servletPath)) {
					return actionInvocation.invoke();
				}
			}
		}

		String errMsg = "您没有访问此功能的权限！功能路径为[" + servletPath + "]请联系管理员给你赋予相应权限。";
		logger.info(errMsg);
		ServletActionContext.getRequest().setAttribute("msg", errMsg);
		return "noSecurity";
	}

}
