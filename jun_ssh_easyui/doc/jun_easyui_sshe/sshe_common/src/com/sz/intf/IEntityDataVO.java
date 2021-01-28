package com.sz.intf;

/**
 * 业务类实体数据接口
 * 
 * 业务类的数据实体结构。 需要保存或者需要通信直接交互的数据使用该结构
 * 
 * 
 * 
 * <b>项目名称: CommonVO </b><br/>
 * <b>类描述: </b><br/>
 * <b>创 建 人: </b> zhouxj <br/>
 * <b>创建时间: </b> 2013-2-1 下午1:16:24 <br/>
 * <b>修 改 人: </b><br/>
 * <b>修改时间: </b><br/>
 * <b>修改备注: </b><br/>
 * <b>JDK 版本: </b> JDK1.6</br/>
 * 
 * @version 1.0.0<br/>
 */
public interface IEntityDataVO extends IEntity {
	
	/**
	 * 获得数据实体的名称
	 */
	public String getName();

}
