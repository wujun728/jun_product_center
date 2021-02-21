package com.deer.wms.base.system.service.ware.impl;

import java.util.List;

import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.model.ware.WareInfo;
import com.deer.wms.base.system.dao.ware.WareInfoMapper;
import com.deer.wms.base.system.service.ware.IWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;


import com.deer.wms.common.core.text.Convert;
import org.springframework.stereotype.Service;

/**
 * 仓库设置 服务层实现
 * 
 * @author deer
 * @date 2019-05-08
 */
@Service
public class WareInfoServiceImpl extends AbstractService<WareInfo, Integer> implements IWareInfoService
{
	@Autowired
	private WareInfoMapper wareInfoMapper;


	/**
	 *根据仓库名查找仓库
	 *
	 * @param wareName 根据仓库名查找仓库是否存在
	 * @return	WareInfo对象
	 */
	@Override
	public WareInfo selectWareInfoByName(String wareName) {

		return wareInfoMapper.selectWareInfoByName(wareName);
	}

	/**
     * 查询仓库设置信息
     * 
     * @param wareId 仓库设置ID
     * @return 仓库设置信息
     */
    @Override
	public WareInfo selectWareInfoById(Integer wareId)
	{
	    return wareInfoMapper.selectWareInfoById(wareId);
	}
	
	/**
     * 查询仓库设置列表
     * 
     * @param wareInfo 仓库设置信息
     * @return 仓库设置集合
     */
	@Override
	public List<WareInfo> selectWareInfoList(WareInfo wareInfo)
	{
	    return wareInfoMapper.selectWareInfoList(wareInfo);
	}
	
    /**
     * 新增仓库设置
     * 
     * @param wareInfo 仓库设置信息
     * @return 结果
     */
	@Override
	public int insertWareInfo(WareInfo wareInfo)
	{
	    return wareInfoMapper.insertWareInfo(wareInfo);
	}
	
	/**
     * 修改仓库设置
     * 
     * @param wareInfo 仓库设置信息
     * @return 结果
     */
	@Override
	public int updateWareInfo(WareInfo wareInfo)
	{
	    return wareInfoMapper.updateWareInfo(wareInfo);
	}

	/**
     * 删除仓库设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWareInfoByIds(String ids)
	{
		return wareInfoMapper.deleteWareInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WareInfo> findWareInfoList(WareInfo wareInfo)
	{
		return wareInfoMapper.findWareInfoList(wareInfo);
	}
}
