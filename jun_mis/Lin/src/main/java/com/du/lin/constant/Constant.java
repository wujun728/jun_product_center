package com.du.lin.constant;

/**
 * 常量
 */
public interface Constant {
	/**
	 * 请假isfinish
	 * 允许
	 */
	int LEAVE_ACCEPTED_CODE = 1;
	/**
	 * 请假isfinish
	 * 拒绝
	 */
	int LEAVE_UNACCEPTED_CODE = 0;
	/**
	 * 请假isfinish
	 * 未作处理
	 */
	int LEAVE_UNKNOWN_CODE = 2;
	/**
	 * 请假isfinish
	 * 撤回
	 */
	int LEAVE_WITHDRAW_CODE = 3;
	/**
	 *用户缓存
	 */
	String USER_CACHE_VALUE = "usercache";
	
	/**
	 * 添加通知的默认结果
	 */
	int RESULT_DEFAULT_ADD_NOTICE = 0;
	/**
	 * 默认角色id 即用户
	 */
	int DEFAULT_ROLE_ID = 2;
	/**
	 * 操作成功代码
	 */
	String OPERATION_SUCCESS_CODE = "000";
	/**
	 * 未知错误代码
	 */
	String UNKNOWN_ERROR_CODE = "555";
	/**
	 * 设置密码原密码不正确时返回结果
	 */
	String ERROR_SET_PASSWORD_NO_MATCH = "3";
	/**
	 * 添加部门，部门已经存在了
	 */
	String ERROR_ADD_DEPT_ALREADY_EXISTS = "3";
	/**
	 * 不能删除默认部门
	 */
	String ERROR_CAN_NOT_DELETE_DEFAULT_DEPT = "4";
	/**
	 * 删除便签错误
	 * 可能是id传递错误
	 */
	String ERROR_DELETE_MEMO = "006";
	/**
	 * 添加便签错误
	 */
	String ERROR_ADD_MEMO = "007";
	/**
	 * 错误代码
	 * 删除登陆日志失败
	 * 也可能是日志为空
	 */
	String ERROR_DELETE_LOGINLOG_FAIL = "005";

	/**
	 * 错误代码
	 * 账号密码不匹配
	 */
	String ERROR_CODE_USERNAME_PASSWORD_MISMATCH = "201";
	/**
	 * 错误代码
	 * 验证码错误
	 */
	String ERROR_CODE_VERICATION_CODE_ERROR = "301";
	/**
	 * 错误代码
	 * 没有权限
	 */
	String ERROR_CODE_NO_PERMISION = "101";
	/**
	 * 错误代码
	 * 添加角色时，数据库中已经存在相同的roles
	 */
	String ERROR_CODE_ROLES_EXIST = "008";
	/**
	 * 错误代码
	 * 添加角色时，数据库中已经存在相同的tips
	 */
	String ERROR_CODE_TIPS_EXIST = "009";
	/**
	 * 错误代码
	 * 添加角色时，roles包含特殊字符
	 */
	String ERROR_CODE_NOT_MATCHE = "010";
	/**
	 * 错误代码
	 * 添加角色时，roles包含特殊字符
	 */
	String ERROR_CODE_ADD_ROLE_MENU_RELATION_FAIL = "011";

}
