package com.sz.message.vo;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sz.intf.IEntityDataVO;

/**
 * 用户注册基本信息
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-6-21 下午3:44:06 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserInfoData implements IEntityDataVO {

	/**
	 * UserId 编号
	 */
	private String UserId;
	/**
	 * Accounts 帐号
	 * 
	 */
	private String Accounts;
	/**
	 * UserName 昵称
	 * 
	 */
	private String UserName;
	/**
	 * LogonPass 登陆密码
	 * 
	 */
	private String LogonPass;
	/**
	 * PasQuestion 找回密码提问
	 * 
	 */
	private String PasQuestion;
	/**
	 * PasAnswer 找回密码答案
	 * 
	 */
	private String PasAnswer;
	/**
	 * EMail 安全油箱
	 * 
	 */
	private String Email;
	/**
	 * FaceID 头像
	 * 
	 */
	private String FaceID;
	/**
	 * [PhoneNumber] 电话
	 * 
	 */
	private String PhoneNumber;
	/**
	 * [Address] 邮寄地址
	 * 
	 */
	private String Address;
	/**
	 * [PostalCard] 邮编
	 */
	private String PostalCard;
	/**
	 * [QQ] QQ
	 */
	private String QQ;
	/**
	 * [MSN] MSN
	 */
	private String MSN;
	/**
	 * [RealName] 真实姓名
	 */
	private String RealName;
	/**
	 * Gender 性别
	 */
	private String Gender;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getAccounts() {
		return Accounts;
	}

	public void setAccounts(String accounts) {
		Accounts = accounts;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getLogonPass() {
		return LogonPass;
	}

	public void setLogonPass(String logonPass) {
		LogonPass = logonPass;
	}

	public String getPasAnswer() {
		return PasAnswer;
	}

	public void setPasAnswer(String pasAnswer) {
		PasAnswer = pasAnswer;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFaceID() {
		return FaceID;
	}

	public void setFaceID(String faceID) {
		FaceID = faceID;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPostalCard() {
		return PostalCard;
	}

	public void setPostalCard(String postalCard) {
		PostalCard = postalCard;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getMSN() {
		return MSN;
	}

	public void setMSN(String mSN) {
		MSN = mSN;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
	
	@JsonIgnore
	public String getName() {
		return "玩家帐号数据结构";
	}
	
	public void setName(String name){
		return;
	}

}
