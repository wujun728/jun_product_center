package com.sz.handle;

import com.sz.intf.IEntity;

/**
 * json 的数据处理接口
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-2-1 下午7:45:48 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public interface IJsonHandle extends IHandle {
	
	/**
	 * 转换为 json 字符串 toJsonStr (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param entity
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public String toJsonStr(IEntity entity);

	/**
	 * 转换为数据结构 toBean
	 * 
	 * @param jsonStr
	 * @return IEntity
	 * @exception
	 * @since 1.0.0
	 */
	public IEntity toBean(String jsonStr);

}
