package com.sz.message.request;

import com.sz.message.RequestMessage;
import com.sz.message.vo.UserRechargeData;

/**
 * 用户充值接口
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-6-21 下午6:03:17 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserRechargeRequestMessage extends RequestMessage {

	/**
	 * 充值对象
	 */
	private UserRechargeData userRechargeData;

	public UserRechargeData getUserRechargeData() {
		return userRechargeData;
	}

	public void setUserRechargeData(UserRechargeData userRechargeData) {
		this.userRechargeData = userRechargeData;
	}

}
