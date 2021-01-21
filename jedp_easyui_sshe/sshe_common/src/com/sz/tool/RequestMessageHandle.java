package com.sz.tool;

import com.sz.handle.IJsonHandle;
import com.sz.intf.IEntity;
import com.sz.intf.IEntityDataVO;
import com.sz.intf.IEntityVO;

public abstract class RequestMessageHandle implements IJsonHandle {
		
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
	
	public abstract String toNormalStr(IEntity entityVO) ;
	
	public abstract String toJsonDataStr(IEntityDataVO dataVO);
	
}
