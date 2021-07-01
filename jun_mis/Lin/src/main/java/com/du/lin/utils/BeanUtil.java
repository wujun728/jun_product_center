package com.du.lin.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.du.lin.bean.Dept;
import com.du.lin.bean.Leave;
import com.du.lin.bean.LoginLog;
import com.du.lin.bean.Notice;
import com.du.lin.bean.OperationLeave;
import com.du.lin.bean.OperationLeaveUser;
import com.du.lin.bean.OperationLog;
import com.du.lin.bean.Role;
import com.du.lin.bean.ShiroUser;
import com.du.lin.bean.ShowLog;
import com.du.lin.bean.ShowNotice;
import com.du.lin.bean.User;
import com.du.lin.bean.UserLeave;
import com.du.lin.constant.Constant;
import com.du.lin.constant.state.NoticeType;
import com.du.lin.dao.DeptMapper;
import com.du.lin.dao.OperationLeaveUserMapper;

import com.du.lin.dao.RoleMapper;
import com.du.lin.dao.UserMapper;

public class BeanUtil {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private OperationLeaveUserMapper oluMapper;

	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");

	/**
	 * 将数据酷通知类 转成 前台通知类
	 * 
	 * @param notice
	 * @return
	 */
	public ShowNotice toShowNotice(Notice notice) {

		String message = "";
		if (notice.getBody().trim().length() > 40) {
			message = notice.getBody().substring(0, 39) + "...";
		} else {
			message = notice.getBody();
		}

		ShowNotice result = new ShowNotice();

		result.setBody(notice.getBody());
		result.setMessage(
				message + "<br/><small class=\"text-muted\"> " + sdf.format(notice.getCreatetime()) + " </small>");
		if (NoticeType.System.getName().endsWith(notice.getType())) {
			result.setAvatar("systemavatar");
			result.setSendUsername("系统通知");
		} else {
			ShiroUser user = userMapper.selectById(notice.getSenduserid());
			result.setAvatar(user.getAvator().endsWith("0") ? "girl" : "boy");
			result.setSendUsername(user.getUsername());
		}

		// result.setCreatetime(sdf.format(notice.getCreatetime()));
		return result;
	}

	/**
	 * 将数据库用户类 转化为 封装了更多信息的用户类
	 * 
	 * @param shiroUser
	 * @return
	 */
	public User toUser(ShiroUser shiroUser) {
		User user = new User();
		user.setAvator("0".equals(shiroUser.getAvator()) ? "女" : "男");
		user.setId(shiroUser.getId());
		int deptId = shiroUser.getDeptid();
		Dept dept = deptMapper.selectById(deptId);
		String deptName = dept.getName();
		user.setDept(deptName);
		user.setPassword(shiroUser.getPassword());
		Role role = roleMapper.selectById(shiroUser.getRoleid());
		user.setRole(role.getRoles());
		user.setRoleid(shiroUser.getRoleid());
		user.setRoleTip(role.getTips());
		user.setSalt(shiroUser.getSalt());
		user.setUsername(shiroUser.getUsername());
		return user;
	}

	/**
	 * 用户类 List转化
	 * 
	 * @param userList
	 * @return
	 */
	public List<User> toUserList(List<ShiroUser> userList) {

		List<User> users = new ArrayList<User>();
		for (ShiroUser user : userList) {
			users.add(toUser(user));
		}
		return users;
	}

	/**
	 * 登陆日志 转化为 前台展示日志
	 * 
	 * @param log
	 * @return
	 */
	public ShowLog loginLogToShowLog(LoginLog log) {
		ShowLog showLoginLog = new ShowLog();
		showLoginLog.setLogname(log.getLogname());
		showLoginLog.setUsername(log.getMessage());
		showLoginLog.setCreatetime(sdf.format(log.getCreatetime()));
		return showLoginLog;
	}

	/**
	 * 操作日志 转化为 前台展示日志
	 * 
	 * @param log
	 * @return
	 */
	public ShowLog operationLogToShowLog(OperationLog log) {
		ShowLog showLoginLog = new ShowLog();
		showLoginLog.setLogname(log.getLogname());
		showLoginLog.setUsername(log.getMessage());
		showLoginLog.setCreatetime(sdf.format(log.getCreatetime()));
		return showLoginLog;
	}

	/**
	 * 登陆日志 List转化
	 * 
	 * @param loglist
	 * @return
	 */
	public List<ShowLog> loginLogListToShowLogList(List<LoginLog> loglist) {
		List<ShowLog> result = new ArrayList<ShowLog>();
		if (loglist == null || loglist.size() == 0) {
			return result;
		}
		for (LoginLog loginLog : loglist) {
			result.add(loginLogToShowLog(loginLog));
		}
		return result;
	}

	/**
	 * 操作日志 List转化
	 * 
	 * @param loglist
	 * @return
	 */
	public List<ShowLog> operationLogListToShowLogList(List<OperationLog> loglist) {
		List<ShowLog> result = new ArrayList<ShowLog>();
		if (loglist == null || loglist.size() == 0) {
			return result;
		}
		for (OperationLog loginLog : loglist) {
			result.add(operationLogToShowLog(loginLog));
		}
		return result;
	}

	public  UserLeave leaveToUserLeave(Leave leave) {

		UserLeave result = new UserLeave(leave.getId(), leave.getType(), sdf.format(leave.getStarttime()),
				sdf.format(leave.getEndtime()), leave.getReason(), leave.getIsfinish() + "",
				sdf.format(leave.getCreatetime()));
		
		switch (leave.getIsfinish()) {
		case Constant.LEAVE_UNKNOWN_CODE:
			result.setIsfinish("未处理");
			break;
		case Constant.LEAVE_ACCEPTED_CODE:
			result.setIsfinish("允许");
			break;
		case Constant.LEAVE_UNACCEPTED_CODE:
			result.setIsfinish("拒绝");
			break;
		case Constant.LEAVE_WITHDRAW_CODE:
			result.setIsfinish("已撤回");
			break;
		}
		
		return result;
	}

	public  OperationLeave leaveToOperationLeave(Leave leave){
		OperationLeave result = new OperationLeave();
		result.setId(leave.getId());
		result.setCreatetime(sdf.format(leave.getCreatetime()));
		result.setEndtime(sdf.format(leave.getEndtime()));
		result.setStarttime(sdf.format(leave.getStarttime()));
		result.setReason(leave.getReason());
		result.setType(leave.getType());
		result.setUsername(leave.getUsername());
		
		switch (leave.getIsfinish()) {
		case Constant.LEAVE_UNKNOWN_CODE:
			result.setIsfinish("未处理");
			break;
		case Constant.LEAVE_ACCEPTED_CODE:
			result.setIsfinish("允许");
			break;
		case Constant.LEAVE_UNACCEPTED_CODE:
			result.setIsfinish("拒绝");
			break;
		case Constant.LEAVE_WITHDRAW_CODE:
			result.setIsfinish("已撤回");
			break;
		}
		
		if (leave.getIsfinish() == 2 || leave.getIsfinish() == 3) {
			result.setOperationUsername("");
			return result;
		}
		
		OperationLeaveUser temp = new OperationLeaveUser();
		temp.setLeaveid(leave.getId());
		OperationLeaveUser olu =  oluMapper.selectOne(temp);
		result.setOperationUsername(olu.getUsername());
		return result;
	}
	
	
	public List<UserLeave> leaveListToUserLeaveList(List<Leave> list){
		List<UserLeave> result = new ArrayList<UserLeave>();
		if (list == null || list.size() == 0) {
			return result;
		}
		for (Leave leave : list) {
			result.add(leaveToUserLeave(leave));
		}
		return result;
	}
	
	public List<OperationLeave> leaveListToOperationLeaveList(List<Leave> list){
		List<OperationLeave> result = new ArrayList<OperationLeave>();
		if (list == null || list.size() == 0) {
			return result;
		}
		for (Leave leave : list) {
			result.add(leaveToOperationLeave(leave));
		}
		return result;
	}
	
	
}
