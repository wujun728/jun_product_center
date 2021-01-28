package sy.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustomerInfo;
import sy.model.base.SessionInfo;
import sy.model.base.Syorganization;
import sy.model.base.Syrole;
import sy.model.base.SysMessage;
import sy.model.base.Syuser;
import sy.service.base.SysMessageServiceI;
import sy.service.base.SyuserServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.ConfigUtil;
import sy.util.base.HqlFilter;
import webservice.RegUserServiceClient;

import com.sz.message.request.UserPresenceRequestMessage;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.vo.JameMessage;

/**
 * 用户业务逻辑
 * 
 */
@Service
public class SyuserServiceImpl extends BaseServiceImpl<Syuser> implements
		SyuserServiceI {
	private static final Logger logger = Logger
			.getLogger(SyuserServiceImpl.class);
	@Autowired
	private BaseDaoI<Syrole> roleDao;

	@Autowired
	private BaseDaoI<Syorganization> organizationDao;

	@Autowired
	private RegUserServiceClient regUserServiceClient;

	@Autowired
	private BaseDaoI<CustomerInfo> customerInfoDao;

	@Autowired
	private SysMessageServiceI sysMessageServiceI;

	/**
	 * 由于新添加的资源
	 */
	@Override
	public void saveSyuser(Syuser syuser) {
		save(syuser);
	}

	@Override
	public void saveSyuser(Syuser syuser,
			UserRegRequestMessage userRegRequestMessage) {

		if (userRegRequestMessage == null) {
			return;
		}

		boolean result = regUserServiceClient
				.regUserToServer(userRegRequestMessage);
		if (result) {
			syuser.setIsSynChat(Syuser.IS_SYN_CHAT_SUCC);
		} else {
			syuser.setIsSynChat(Syuser.IS_SYN_CHAT_FAIL);
		}

		save(syuser);
	}

	@Override
	public void updateSyuser(Syuser syuser,
			UserRegRequestMessage userRegRequestMessage) {
		if (syuser.getIsSynChat() == Syuser.IS_SYN_CHAT_FAIL) {
			if (userRegRequestMessage == null) {
				return;
			}

			boolean result = regUserServiceClient
					.regUserToServer(userRegRequestMessage);

			if (result) {
				syuser.setIsSynChat(Syuser.IS_SYN_CHAT_SUCC);
			} else {
				syuser.setIsSynChat(Syuser.IS_SYN_CHAT_FAIL);
			}
		}

		update(syuser);
	}

	@Override
	public List<Syuser> findUserByFilter(HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from Syuser t left join t.syroles role";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<Syuser> findUserByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Syuser t left join t.syroles role";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countUserByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from Syuser t left join t.syroles role";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public void grantRole(String id, String roleIds) {
		Syuser user = getById(id);
		if (user != null) {
			user.setSyroles(new HashSet<Syrole>());
			for (String roleId : roleIds.split(",")) {
				if (!StringUtils.isBlank(roleId)) {
					Syrole role = roleDao.getById(Syrole.class, roleId);
					if (role != null) {
						user.getSyroles().add(role);
					}
				}
			}
		}
	}

	@Override
	public void grantOrganization(String id, String organizationIds) {
		Syuser user = getById(id);
		if (user != null) {
			user.setSyorganizations(new HashSet<Syorganization>());
			for (String organizationId : organizationIds.split(",")) {
				if (!StringUtils.isBlank(organizationId)) {
					Syorganization organization = organizationDao.getById(
							Syorganization.class, organizationId);
					if (organization != null) {
						user.getSyorganizations().add(organization);
					}
				}
			}
		}
	}

	@Override
	public List<Long> userCreateDatetimeChart() {
		List<Long> l = new ArrayList<Long>();
		int k = 0;
		for (int i = 0; i < 12; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("a", k);
			params.put("b", k + 2);
			k = k + 2;
			l.add(count(
					"select count(*) from Syuser t where HOUR(t.createdatetime)>=:a and HOUR(t.createdatetime)<:b",
					params));
		}
		return l;
	}

	@Override
	public Long countUserByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		String hql = "select count(*) from Syuser t join t.syroles role where role.id = :roleId";
		return count(hql, params);
	}

	@Override
	public Long countUserByNotRoleId() {
		String hql = "select count(*) from Syuser t left join t.syroles role where role.id is null";
		return count(hql);
	}

	@Override
	public void updateSyuserPwd(Syuser syuser,
			UserRegRequestMessage userRegRequestMessage) {
		// if (syuser.getIsSynChat() == Syuser.IS_SYN_CHAT_FAIL)
		{
			if (userRegRequestMessage == null) {
				return;
			}

			boolean result = regUserServiceClient
					.updUserPwdToServer(userRegRequestMessage);
			if (result) {
				syuser.setIsSynChat(Syuser.IS_SYN_CHAT_SUCC);
			} else {
				syuser.setIsSynChat(Syuser.IS_SYN_CHAT_FAIL);
			}
		}

		update(syuser);
	}

	@Override
	public boolean loginToChatServer(Syuser syuser,
			UserRegRequestMessage userRegRequestMessage) {
		if (syuser.getIsSynChat() == Syuser.IS_SYN_CHAT_SUCC) {
			if (userRegRequestMessage == null) {
				return false;
			}

			boolean result = regUserServiceClient
					.loginUserToServer(userRegRequestMessage);

			return result;
		} else {
			logger.error("用户没有同步到聊天服务器!! 无法自动登陆 !!");
			return false;
		}
	}

	@Override
	public List<JameMessage> saveChatMessage(Syuser syuser,
			UserPresenceRequestMessage userPresenceRequestMessage) {
		if (userPresenceRequestMessage == null) {
			return null;
		}
		HqlFilter hqlFilter = new HqlFilter();

		hqlFilter.addFilter("QUERY_t#userId_S_EQ", syuser.getId() + "");

		hqlFilter.addFilter("QUERY_t#status_B_EQ", SysMessage.STATUS_NEW + "");

		List<SysMessage> list = sysMessageServiceI
				.findSysMessageByFilter(hqlFilter);
		List<JameMessage> dbResult = new ArrayList<JameMessage>();
		if (list != null && !list.isEmpty()) {

			for (SysMessage sysMessage : list) {
				JameMessage jm = new JameMessage();
				jm.setMessageBody(sysMessage.getMessageBody());
				jm.setMessageFrom(sysMessage.getMessageFrom());
				jm.setMessageSubject(sysMessage.getMessageSubject());
				jm.setMessageTo(sysMessage.getMessageTo());
				dbResult.add(jm);
			}
			// return result;
		}
		List<JameMessage> result = regUserServiceClient
				.getChatMessage(userPresenceRequestMessage);

		if (result != null) {
			for (JameMessage msg : result) {
				SysMessage sysMessage = new SysMessage();
				sysMessage.setMessageBody(msg.getMessageBody());
				sysMessage.setMessageFrom(msg.getMessageFrom());
				sysMessage.setMessageSubject(msg.getMessageSubject());
				sysMessage.setMessageTo(msg.getMessageTo());
				sysMessage.setStatus(SysMessage.STATUS_NEW);
				sysMessage.setUserId(syuser.getId());

				sysMessageServiceI.saveSysMessage(sysMessage, syuser.getId());
			}
			result.addAll(dbResult);

			return result;
		} else {
			return dbResult;
		}
	}

	@Override
	public void grantCustomer(String id, String ids) {
		Syuser user = getById(id);
		if (user != null) {
			user.setSycustomerInfos(new HashSet<CustomerInfo>());
			for (String customerId : ids.split(",")) {
				if (!StringUtils.isBlank(customerId)) {
					CustomerInfo customerInfo = customerInfoDao.getById(
							CustomerInfo.class, Integer.valueOf(customerId));
					if (customerInfo != null) {
						user.getSycustomerInfos().add(customerInfo);
					}
				}
			}
		}
	}
	
	@Override
	public void updateSyuser(Syuser syuser) {
		if (syuser.getIp() == null) {
			update(syuser);
		}
	}

}
