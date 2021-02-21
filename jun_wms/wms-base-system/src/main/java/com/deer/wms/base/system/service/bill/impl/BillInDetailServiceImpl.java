package com.deer.wms.base.system.service.bill.impl;

 
import com.deer.wms.base.system.model.bill.BillInDetailCriteria;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.core.text.Convert;
import com.deer.wms.base.system.model.bill.BillInDetail;
import com.deer.wms.base.system.model.bill.BillInDetailDto;
import com.deer.wms.base.system.dao.bill.BillInDetailMapper;
import com.deer.wms.base.system.service.bill.IBillInDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 入库单详情 服务层实现
 * 
 * @author guo
 * @date 2019-05-13
 */
@Service
public class BillInDetailServiceImpl extends AbstractService<BillInDetail, Integer> implements IBillInDetailService
{
	@Autowired
	private BillInDetailMapper billInDetailMapper;

	@Override
	public List<BillInDetailDto> findList(BillInDetailCriteria billInDetailCriteria) {
		return billInDetailMapper.findList(billInDetailCriteria);
	}

	/**
     * 查询入库单详情信息
     * 
     * @param billInDetail 入库单详情ID
     * @return 入库单详情信息
     */
    @Override
	public BillInDetail selectBillInDetailById(Integer billInDetail)
	{
	    return billInDetailMapper.selectBillInDetailById(billInDetail);
	}
	
	/**
     * 查询入库单详情列表
     * 
     * @param billInDetail 入库单详情信息
     * @return 入库单详情集合
     */
	@Override
	public List<BillInDetail> selectBillInDetailList(BillInDetail billInDetail)
	{
	    return billInDetailMapper.selectBillInDetailList(billInDetail);
	}

	/**
     * 删除入库单详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBillInDetailByIds(String ids)
	{
		return billInDetailMapper.deleteBillInDetailByIds(Convert.toStrArray(ids));
	}
	
}
