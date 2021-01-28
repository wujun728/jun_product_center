package sy.action.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.base.SessionInfo;
import sy.model.base.Syorganization;
import sy.model.base.Syrole;
import sy.model.base.Syuser;
import sy.model.easyui.Grid;
import sy.model.easyui.Json;
import sy.service.base.SyuserServiceI;
import sy.util.HttpRequest;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.HqlFilter;
import sy.util.base.IpUtil;
import sy.util.base.MD5Util;

import com.opensymphony.xwork2.ActionContext;
import com.sz.message.request.UserPresenceRequestMessage;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.vo.JameMessage;
import com.sz.message.vo.UserInfoData;

/**
 * 用户
 * 
 * action访问地址是/base/syuser.sy
 * 
 * @author Wujun
 * 
 */
@Namespace("/base")
@Action
public class SyuserAction extends BaseAction<Syuser> {
	private static final Logger logger = Logger.getLogger(SyuserAction.class);
	
	private boolean isConnected = true;
	
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyuserServiceI service) {
		this.service = service;
	}

	/**
	 * 注销系统
	 */
	public void doNotNeedSessionAndSecurity_logout() {
		if (getSession() != null) {
			getSession().invalidate();
		}
		Json j = new Json();
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 注册
	 */
	synchronized public void doNotNeedSessionAndSecurity_reg() {
		Json json = new Json();
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
		Syuser user = service.getByFilter(hqlFilter);
		if (user != null) {
			json.setMsg("用户名已存在！");
			writeJson(json);
		} else {
			Syuser u = new Syuser(null);
			u.setLoginname(data.getLoginname());
			u.setPwd(MD5Util.md5(data.getPwd()));
			service.save(u);
			doNotNeedSessionAndSecurity_login();
		}
	}

	/**
	 * 登录
	 */
	public void doNotNeedSessionAndSecurity_login() {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
		hqlFilter.addFilter("QUERY_t#pwd_S_EQ", MD5Util.md5(data.getPwd()));
		Json json = new Json();

		Map<String, Object> session = ActionContext.getContext().getSession();
		String scode = (String) session.get("code");
		if (!data.getCheckCode().equalsIgnoreCase(scode)) {
			json.setSuccess(false);
			json.setMsg("验证码错误!");
			writeJson(json);
			return;
		} else {
			Syuser user = service.getByFilter(hqlFilter);

			if (user != null) {
				json.setSuccess(true);

				SessionInfo sessionInfo = new SessionInfo();
				Hibernate.initialize(user.getSyroles());
				Hibernate.initialize(user.getSyorganizations());

				for (Syrole role : user.getSyroles()) {
					Hibernate.initialize(role.getSyresources());
				}
				for (Syorganization organization : user.getSyorganizations()) {
					Hibernate.initialize(organization.getSyresources());
				}
				user.setIp(IpUtil.getIpMacAddr(getRequest()));
				sessionInfo.setUser(user);
				getSession().setAttribute(ConfigUtil.getSessionInfoName(),
						sessionInfo);
				
				getSession().setAttribute("sshe_chat_webroot",ConfigUtil.get("sshe_chat_webroot"));
						
			} else {
				json.setMsg("用户名或密码错误！");
			}
			writeJson(json);
		}
	}

	/**
	 * 修改自己的密码
	 */
	public void doNotNeedSecurity_updateCurrentPwd() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		Syuser user = service.getById(sessionInfo.getUser().getId());
		user.setPwd(MD5Util.md5(data.getPwd()));
		user.setUpdatedatetime(new Date());
		user.setOriPwd(data.getPwd());
		// service.update(user);
		UserRegRequestMessage userRegRequestMessage = new UserRegRequestMessage();

		userRegRequestMessage.setTime(System.currentTimeMillis() + "");
		userRegRequestMessage.setUserId(user.getLoginname());
		UserInfoData userInfoData = new UserInfoData();
		userInfoData.setAccounts(user.getLoginname());
		userInfoData.setAddress("");
		userInfoData.setLogonPass(data.getPwd());
		userInfoData.setFaceID(data.getPhoto());
		userInfoData.setRealName(data.getName());
		userInfoData.setPostalCard("000000");
		userInfoData.setUserName(data.getName());
		userRegRequestMessage.setUserInfoData(userInfoData);

		((SyuserServiceI) service).updateSyuserPwd(user, userRegRequestMessage);

		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 修改用户关联的客户
	 */
	public void grantCustomer() {
		Json json = new Json();
		((SyuserServiceI) service).grantCustomer(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 修改用户角色
	 */
	public void grantRole() {
		Json json = new Json();
		((SyuserServiceI) service).grantRole(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 修改用户机构
	 */
	public void grantOrganization() {
		Json json = new Json();
		((SyuserServiceI) service).grantOrganization(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 统计用户注册时间图表
	 */
	public void doNotNeedSecurity_userCreateDatetimeChart() {
		writeJson(((SyuserServiceI) service).userCreateDatetimeChart());
	}

	/**
	 * 新建一个用户
	 */
	synchronized public void save() {
		Json json = new Json();
		if (data != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
			Syuser user = service.getByFilter(hqlFilter);
			if (user != null) {
				json.setMsg("新建用户失败，用户名已存在！");
			} else {
				data.setPwd(MD5Util.md5("123456"));
				data.setOriPwd("123456");

				UserRegRequestMessage userRegRequestMessage = new UserRegRequestMessage();

				userRegRequestMessage.setTime(System.currentTimeMillis() + "");
				userRegRequestMessage.setUserId(data.getLoginname());
				UserInfoData userInfoData = new UserInfoData();
				userInfoData.setAccounts(data.getLoginname());
				userInfoData.setAddress("");
				userInfoData.setLogonPass("123456");
				userInfoData.setFaceID(data.getPhoto());
				userInfoData.setRealName(data.getName());
				userInfoData.setPostalCard("000000");
				userInfoData.setUserName(data.getName());
				userRegRequestMessage.setUserInfoData(userInfoData);

				((SyuserServiceI) service).saveSyuser(data,
						userRegRequestMessage);

				json.setMsg("新建用户成功！默认密码：123456");
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 更新一个用户
	 */
	synchronized public void update() {
		Json json = new Json();
		json.setMsg("更新失败！");
		if (data != null && !StringUtils.isBlank(data.getId())) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#id_S_NE", data.getId() + "");
			hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
			Syuser user = service.getByFilter(hqlFilter);
			if (user != null) {
				json.setMsg("更新用户失败，用户名已存在！");
			} else {
				Syuser t = service.getById(data.getId());
				BeanUtils.copyNotNullProperties(data, t, new String[] {
						"createdatetime", "pwd", "oriPwd" });
				// service.update(t);
				UserRegRequestMessage userRegRequestMessage = new UserRegRequestMessage();

				userRegRequestMessage.setTime(System.currentTimeMillis() + "");
				userRegRequestMessage.setUserId(data.getLoginname());
				UserInfoData userInfoData = new UserInfoData();
				userInfoData.setAccounts(data.getLoginname());
				userInfoData.setAddress("");
				userInfoData.setLogonPass(t.getOriPwd());
				userInfoData.setFaceID(data.getPhoto());
				userInfoData.setRealName(data.getName());
				userInfoData.setPostalCard("000000");
				userInfoData.setUserName(data.getName());
				userRegRequestMessage.setUserInfoData(userInfoData);

				((SyuserServiceI) service).updateSyuser(t,
						userRegRequestMessage);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
		}
		writeJson(json);
	}

	/**
	 * 用户登录页面的自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboBox() {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginname_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addSort("t.loginname");
		hqlFilter.addOrder("asc");
		writeJsonByIncludesProperties(service.findByFilter(hqlFilter, 1, 10),
				new String[] { "loginname" });
	}

	/**
	 * 用户登录页面的grid自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboGrid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#loginname_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 用户grid
	 */
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (!StringUtils.isBlank(getId())) {
			hqlFilter.addFilter("QUERY_role#id_S_EQ", getId() + "");
		}
		grid.setTotal(((SyuserServiceI) service).countUserByFilter(hqlFilter));
		grid.setRows(((SyuserServiceI) service).findUserByFilter(hqlFilter,
				page, rows));
		writeJson(grid);
	}

	public void gotoChatIM() {
		Json json = new Json();
		json.setMsg("进入聊天界面失败！");
		if (!StringUtils.isBlank(id)) {
			Syuser t = service.getById(id);

			// http://localhost:8081/sshe_chat/adapter/login?

			String loginUrl = ConfigUtil.get("sshe_chat_webroot")
					+ "adapter/login";
			String chatServer = ConfigUtil.get("sshe_chat_server");
			int port = Integer.parseInt(ConfigUtil.get("sshe_chat_port"));

			String param = "name=" + t.getLoginname() + "&password="
					+ t.getOriPwd() + "&port=" + port + "&server=" + chatServer
					+ "";

			// BeanUtils.copyNotNullProperties(data, t, new String[] {
			// "createdatetime", "pwd", "oriPwd" });
			// service.update(t);
			UserRegRequestMessage userRegRequestMessage = new UserRegRequestMessage();

			userRegRequestMessage.setTime(System.currentTimeMillis() + "");
			userRegRequestMessage.setUserId(t.getLoginname());
			UserInfoData userInfoData = new UserInfoData();
			userInfoData.setAccounts(t.getLoginname());
			userInfoData.setAddress("");
			userInfoData.setLogonPass(t.getOriPwd());
			userInfoData.setFaceID(t.getPhoto());
			userInfoData.setRealName(t.getName());
			userInfoData.setPostalCard("000000");
			userInfoData.setUserName(t.getName());
			userRegRequestMessage.setUserInfoData(userInfoData);

			boolean ret = ((SyuserServiceI) service).loginToChatServer(t,
					userRegRequestMessage);
			if (ret) {
				String returnStr = HttpRequest.sendPost(loginUrl, param);
				logger.info("聊天服务器返回的结果为:" + returnStr);
			}
			OpResult opResult = new OpResult();
			opResult.setName(t.getLoginname());
			opResult.setPass(t.getOriPwd());
			opResult.setPort(port + "");
			opResult.setServer(chatServer);

			json.setObj(opResult);

			json.setSuccess(true);
			json.setMsg("更新成功！");

		}
		writeJson(json);
	}

	/**
	 * 获得聊天消息数量
	 */
	public void doNotNeedSecurity_getChatCount() {
		Json json = new Json();
		try{
			if(this.isConnected){
				if (!StringUtils.isBlank(id)) {
					Syuser t = service.getById(id);
					
					// Syuser user = service.getById(sessionInfo.getUser().getId());
					// user.setPwd(MD5Util.md5(data.getPwd()));
					// user.setUpdatedatetime(new Date());
					// user.setOriPwd(data.getPwd());
					// service.update(user);
					UserPresenceRequestMessage userRegRequestMessage = new UserPresenceRequestMessage();

					userRegRequestMessage.setTime(System.currentTimeMillis() + "");
					userRegRequestMessage.setUserId(t.getLoginname());
					UserInfoData userInfoData = new UserInfoData();
					userInfoData.setAccounts(t.getLoginname());
					userInfoData.setAddress("");
					userInfoData.setLogonPass(t.getOriPwd());
					userInfoData.setFaceID(t.getPhoto());
					userInfoData.setRealName(t.getName());
					userInfoData.setPostalCard("000000");
					userInfoData.setUserName(t.getLoginname());
					userRegRequestMessage.setUserInfoData(userInfoData);

					List<JameMessage> list = ((SyuserServiceI) service)
							.saveChatMessage(t, userRegRequestMessage);
					int count = (list != null) ? list.size() : 0;
					json.setObj(list);

					json.setMsg("您有[" + count + "]条消息!!请注意查收 !");
					json.setSuccess(true);
					this.setConnected(true);
				}
				
				writeJson(json);
			}else{
				json.setSuccess(false);
				json.setMsg("网络连接失败了 !!");
				writeJson(json);
			}
		}catch(Exception e){
			this.setConnected(false);
			json.setSuccess(false);
			writeJson(json);
		}
	}

	public class OpResult {
		private String name;
		private String server;
		private String port;
		private String pass;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the server
		 */
		public String getServer() {
			return server;
		}

		/**
		 * @param server
		 *            the server to set
		 */
		public void setServer(String server) {
			this.server = server;
		}

		/**
		 * @return the port
		 */
		public String getPort() {
			return port;
		}

		/**
		 * @param port
		 *            the port to set
		 */
		public void setPort(String port) {
			this.port = port;
		}

		/**
		 * @return the pass
		 */
		public String getPass() {
			return pass;
		}

		/**
		 * @param pass
		 *            the pass to set
		 */
		public void setPass(String pass) {
			this.pass = pass;
		}

	}

	/**
	 * @return the isConnected
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * @param isConnected the isConnected to set
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
}
