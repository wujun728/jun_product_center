package com.sz.tool;

import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.message.request.UserRechargeRequestMessage;
import com.sz.util.CommonLogUtil;
import com.sz.util.JsonUtils;

/**
 * 玩家充值接口
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-6-21 下午6:05:57 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public class UserRegchargeMessageHandle extends RequestMessageHandle {

	@Override
	public String toNormalStr(IEntity entityVO) {
		String returnstr = JsonUtils.getJson(entityVO);

		return returnstr;
	}

	/**
	 * 对象转换成字符串
	 */
	@Override
	public String toJsonDataStr(IEntityDataVO dataVO) {

		String returnstr = JsonUtils.getJson(dataVO);

		return returnstr;
	}

	/**
	 * 字符串转换成对象
	 */
	@Override
	public IEntity toBean(String jsonStr) {
		if (jsonStr == null) {
			return null;
		}
		UserRechargeRequestMessage userRechargeRequestMessage = JsonUtils
				.readValue(jsonStr, UserRechargeRequestMessage.class);

		CommonLogUtil.debug("收到消息:" + userRechargeRequestMessage.getName());
		// 消息处理的时间
		long value = System.currentTimeMillis()
				- Long.valueOf(userRechargeRequestMessage.getTime());

		CommonLogUtil.debug("收到消息时间:" + value);
		
		return userRechargeRequestMessage.getUserRechargeData();
	}

}
