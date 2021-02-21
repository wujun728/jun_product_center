package com.deer.wms.base.system.service.ware;


import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.ware.WareInfo;

import java.util.List;

/**
 * 仓库设置 服务层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface IWareInfoService  extends Service<WareInfo, Integer>
{



	/**
	 *根据仓库名查找仓库
	 *
	 * @param wareName 根据仓库名查找仓库是否存在
	 * @return	WareInfo对象
	 */
	public WareInfo selectWareInfoByName(String wareName);




	/**
     * 查询仓库设置信息
     * 
     * @param wareId 仓库设置ID
     * @return 仓库设置信息
     */
	public WareInfo selectWareInfoById(Integer wareId);
	
	/**
     * 查询仓库设置列表
     * 
     * @param wareInfo 仓库设置信息
     * @return 仓库设置集合
     */
	public List<WareInfo> selectWareInfoList(WareInfo wareInfo);
	
	/**
     * 新增仓库设置
     * 
     * @param wareInfo 仓库设置信息
     * @return 结果
     */
	public int insertWareInfo(WareInfo wareInfo);
	
	/**
     * 修改仓库设置
     * 
     * @param wareInfo 仓库设置信息
     * @return 结果
     */
	public int updateWareInfo(WareInfo wareInfo);
		
	/**
     * 删除仓库设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWareInfoByIds(String ids);

	/**
	 * 仓库新建及修改验证
	 * @param wareInfo
	 * @return
	 */
	List<WareInfo> findWareInfoList(WareInfo wareInfo);
	
}
