package com.shuogesha.common.email;

/**
 * 邮件模板
 */
public interface MessageTemplate {
	/**
	 * 找回密码主题
	 * 
	 * @return
	 */
	public String getForgotPasswordSubject();

	/**
	 * 找回密码内容
	 * 
	 * @return
	 */
	public String getForgotPasswordText();
	
	/**
	 * 会员注册主题
	 * 
	 * @return
	 */
	public String getRegisterSubject();

	/**
	 * 会员注册内容
	 * 
	 * @return
	 */
	public String getRegisterText();
	
	/**
	 * 验证码 
	 * 
	 * @return
	 */
	public String getCodeSubject();
	
	/**
	 * 邮箱验证码内容
	 * 
	 * @return
	 */
	public String getCodeText();
}
