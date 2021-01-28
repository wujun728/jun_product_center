package com.sz.message.request;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sz.message.RequestMessage;
import com.sz.message.vo.UserInfoData;

/**
 * 玩家注册接口
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-6-21 下午6:03:59 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserRegRequestMessage extends RequestMessage {

	/**
	 * 玩家对象
	 */
	private UserInfoData userInfoData;

	public UserInfoData getUserInfoData() {
		return userInfoData;
	}

	public void setUserInfoData(UserInfoData userInfoData) {
		this.userInfoData = userInfoData;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	@Override
	public String getName() {
		return "注册用户消息";
	}

	@Override
	public String toString() {
		return "UserRegRequestMessage [userInfoData=" + userInfoData + "]";
	}

}
