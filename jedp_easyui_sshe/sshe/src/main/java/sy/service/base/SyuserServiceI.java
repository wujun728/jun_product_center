package sy.service.base;

import java.util.List;

import com.sz.message.request.UserPresenceRequestMessage;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.message.vo.JameMessage;

import sy.model.base.Syuser;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用户业务
 * 
 * @author Wujun
 * 
 */
public interface SyuserServiceI extends BaseServiceI<Syuser> {

	/**
	 * 修改用户角色
	 * 
	 * @param id
	 *            用户ID
	 * @param roleIds
	 *            角色IDS
	 */
	public void grantRole(String id, String roleIds);

	/**
	 * 修改用户机构
	 * 
	 * @param id
	 *            用户ID
	 * @param organizationIds
	 *            机构IDS
	 */
	public void grantOrganization(String id, String organizationIds);

	/**
	 * 统计用户注册时间图表
	 * 
	 * @return
	 */
	public List<Long> userCreateDatetimeChart();

	/**
	 * 统计?角色的用户
	 * 
	 * @param roleId
	 * @return
	 */
	public Long countUserByRoleId(String roleId);

	/**
	 * 统计没有角色的用户数量
	 * 
	 * @return
	 */
	public Long countUserByNotRoleId();

	/**
	 * 查找用户
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Syuser> findUserByFilter(HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找用户
	 */
	public List<Syuser> findUserByFilter(HqlFilter hqlFilter);

	public Long countUserByFilter(HqlFilter hqlFilter);

	/**
	 * 保存用户数据
	 * 
	 * @param sysDef
	 */
	public void saveSyuser(Syuser syuser);

	/**
	 * 保存用户信息，同时同步到聊天服务器
	 * 
	 * @param syuser
	 * @param userRegRequestMessage
	 */
	public void saveSyuser(Syuser syuser,
			UserRegRequestMessage userRegRequestMessage);
	public void updateSyuser(Syuser t);
	/**
	 * 更新用户信息，同时同步到聊天服务器
	 * 
	 * @param syuser
	 * @param userRegRequestMessage
	 */
	public void updateSyuser(Syuser syuser,
			UserRegRequestMessage userRegRequestMessage);

	/**
	 * 修改密码，同时同步到聊天服务器
	 * 
	 * @param user
	 * @param userRegRequestMessage
	 */
	public void updateSyuserPwd(Syuser user,
			UserRegRequestMessage userRegRequestMessage);

	/**
	 * 用户登陆到聊天服务器
	 * 
	 * @param t
	 * @param userRegRequestMessage
	 * @return
	 */
	public boolean loginToChatServer(Syuser t,
			UserRegRequestMessage userRegRequestMessage);

	/**
	 * 关联客户
	 * 
	 * @param id
	 *            用户id
	 * @param ids
	 *            客户id
	 */
	public void grantCustomer(String id, String ids);

	/***
	 * 获得用户的聊天消息数量
	 * 
	 * @param syuser
	 * @param userPresenceRequestMessage
	 * @return
	 */
	public List<JameMessage> saveChatMessage(Syuser syuser,
			UserPresenceRequestMessage userPresenceRequestMessage);



}
