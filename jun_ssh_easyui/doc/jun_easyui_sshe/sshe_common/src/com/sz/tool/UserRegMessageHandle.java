package com.sz.tool;

import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.message.request.UserRegRequestMessage;
import com.sz.util.CommonLogUtil;
import com.sz.util.JsonUtils;

public class UserRegMessageHandle extends RequestMessageHandle {

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
		UserRegRequestMessage userRegRequestMessage = JsonUtils.readValue(
				jsonStr, UserRegRequestMessage.class);

		CommonLogUtil.debug("收到消息:" + userRegRequestMessage.getName());
		// 消息处理的时间
		long value = System.currentTimeMillis()
				- Long.valueOf(userRegRequestMessage.getTime());

		CommonLogUtil.debug("收到消息时间:" + value);

		return userRegRequestMessage.getUserInfoData();
	}

}
