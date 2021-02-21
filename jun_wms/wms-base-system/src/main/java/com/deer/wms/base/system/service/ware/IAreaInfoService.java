package com.deer.wms.base.system.service.ware;


import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.ware.AreaInfo;
import com.deer.wms.base.system.model.ware.AreaInfoDto;

import java.util.List;

/**
 * 货区设置 服务层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface IAreaInfoService extends Service<AreaInfo, String>
{

	/**
	 *
	 * 根据货区名查找货区
	 */
	public AreaInfo selectAreaInfoByName(String areaName);



	/**
     * 查询货区设置信息
     * 
     * @param areaId 货区设置ID
     * @return 货区设置信息
     */
	public AreaInfo selectAreaInfoById(Integer areaId);
	
	/**
     * 查询货区设置列表
     * 
     * @param areaInfo 货区设置信息
     * @return 货区设置集合
     */
	public List<AreaInfoDto> selectAreaInfoList(AreaInfo areaInfo);
	
	/**
     * 新增货区设置
     * 
     * @param areaInfo 货区设置信息
     * @return 结果
     */
	public int insertAreaInfo(AreaInfo areaInfo);
	
	/**
     * 修改货区设置
     * 
     * @param areaInfo 货区设置信息
     * @return 结果
     */
	public int updateAreaInfo(AreaInfo areaInfo);
		
	/**
     * 删除货区设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAreaInfoByIds(String ids);
	
}
