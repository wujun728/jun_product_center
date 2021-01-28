package com.sz.tool;

import com.sz.handle.IJsonHandle;
import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.intf.IEntityVO;

/**
 * 返回的消息处理类
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-2-4 下午6:23:59 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public abstract class ReturnMessageHandle implements IJsonHandle {
	public String toJsonStr(IEntity entity) {
		String str = "";
		if (entity instanceof IEntityDataVO) {
			str = toJsonDataStr((IEntityDataVO) entity);

		} else if (entity instanceof IEntityVO) {
			str = toNormalStr(entity);
		}
     
		return str;
	}
	
	public abstract IEntity toBean(String jsonStr);

	public abstract String toNormalStr(IEntity entityVO);

	public abstract String toJsonDataStr(IEntityDataVO dataVO);

}
