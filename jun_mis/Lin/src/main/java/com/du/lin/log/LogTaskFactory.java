package com.du.lin.log;

import java.util.TimerTask;


import com.du.lin.bean.LoginLog;
import com.du.lin.bean.OperationLog;
import com.du.lin.constant.state.LogState;
import com.du.lin.constant.state.LogType;
import com.du.lin.dao.LoginLogMapper;
import com.du.lin.dao.OperationLogMapper;
import com.du.lin.utils.Db;

public class LogTaskFactory {
	private static OperationLogMapper operationLogMapper = Db.getMapper(OperationLogMapper.class);
	private static LoginLogMapper loginLogMapper = Db.getMapper(LoginLogMapper.class);
	
	public static TimerTask getOperationSuccessTimerTask(int userid ,String className , String logName , String methodNmae ,String message ){
		return new TimerTask() {
			
			@Override
			public void run() {
				OperationLog operLog = LogFactory.newOperationLogInstance(userid, className, logName, LogType.BUSSINESS, message, methodNmae, LogState.SUCCESS);
				try {
					operationLogMapper.insert(operLog);
				} catch (Exception e) {
					System.out.println("业务日志异常");
					e.printStackTrace();
				}
			}
		};
	}
	
	public static TimerTask getOperationFailTimerTask(int userid ,String className, String methodNmae ,String message ){
		return new TimerTask() {
			
			@Override
			public void run() {
				OperationLog operLog = LogFactory.newOperationLogInstance(userid, className, null, LogType.BUSSINESS, message, methodNmae, LogState.FAIL);
				try {
					operationLogMapper.insert(operLog);
				} catch (Exception e) {
					System.out.println("业务日志异常");
					e.printStackTrace();
				}
			}
		};
	}
	
	public static TimerTask getLoginSuccessTimerTask(int userid , String username ,String ip ){
		return new TimerTask() {
			
			@Override
			public void run() {
				LoginLog loginLog = LogFactory.newLoginLogIntstance(userid, LogType.LOGIN, LogState.SUCCESS, username, ip);
				try {
					loginLogMapper.insert(loginLog);
				} catch (Exception e) {
					System.out.println("登陆日志异常");
					e.printStackTrace();
				}
			}
		};
	}
	public static TimerTask getLoginFailTimerTask( String username ,String message ,String ip ){
		return new TimerTask() {
			
			@Override
			public void run() {
				LoginLog loginLog = LogFactory.newLoginLogIntstance( null , LogType.LOGIN_FAIL, LogState.SUCCESS, "用户名：" + username + ",原因：" + message, ip);
				try {
					loginLogMapper.insert(loginLog);
				} catch (Exception e) {
					System.out.println("登陆日志异常");
				}
			}
		};
	}
	public static TimerTask getLogoutTimerTask(Integer userid , String username ,String ip ){
		return new TimerTask() {
			
			@Override
			public void run() {
				LoginLog loginLog = LogFactory.newLoginLogIntstance( userid , LogType.EXIT, LogState.SUCCESS,username, ip);
				try {
					System.out.println((loginLogMapper == null) + "");
					loginLogMapper.insert(loginLog);
				} catch (Exception e) {
					System.out.println("退出日志异常");
					e.printStackTrace();
				}
			}
		};
	}
}
