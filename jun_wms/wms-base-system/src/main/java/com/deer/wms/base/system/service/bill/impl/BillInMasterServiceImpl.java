package com.deer.wms.base.system.service.bill.impl;


import com.deer.wms.base.system.model.bill.BillInMasterCriteria;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.core.text.Convert;
import com.deer.wms.base.system.dao.bill.BillInMasterMapper;
import com.deer.wms.base.system.model.bill.BillInDetail;
import com.deer.wms.base.system.model.bill.BillInMaster;
import com.deer.wms.base.system.model.bill.BillInMasterDto;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.service.bill.IBillInMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 入库单 服务层实现
 * 
 * @author guo
 * @date 2019-05-13
 */
@Service
public class BillInMasterServiceImpl  extends AbstractService<BillInMaster, Integer> implements IBillInMasterService
{
	@Autowired
	private BillInMasterMapper billInMasterMapper;

	/**
     * 查询入库单信息
     * 
     * @param billId 入库单ID
     * @return 入库单信息
     */
    @Override
	public BillInMaster selectBillInMasterById(Integer billId)
	{
	    return billInMasterMapper.selectBillInMasterById(billId);
	}
	

	/**
	 *
	 * 查询物料详情数据(点击物料详情)
	 */
	@Override
	public List<BillInDetail> selectBillInDetail() {


		return billInMasterMapper.selectBillInDetail();
	}


	/**
	 *
	 * 查询组盘(点击组盘信息)
	 */
	@Override
	public List<BoxItem> selectBoxItem(Integer billId) {

		return billInMasterMapper.selectBoxItem(billId);
	}

	/**
	 *
	 * 保存组盘
	 */
	@Override
	public void saveBox(BoxItem boxItem) {

		billInMasterMapper.saveBox(boxItem);

	}





	/**
     * 查询入库单列表
     * 
     * @param billInMaster 入库单信息
     * @return 入库单集合
     */
	@Override
	public List<BillInMaster> selectBillInMasterList(BillInMaster billInMaster)
	{
	    return billInMasterMapper.selectBillInMasterList(billInMaster);
	}

	@Override
	public List<BillInMasterDto> findList(BillInMasterCriteria billInMasterCriteria) {
		return billInMasterMapper.findList(billInMasterCriteria);
	}

	/**
     * 新增入库单
     * 
     * @param billInMaster 入库单信息
     * @return 结果
     */
	@Override
	public int insertBillInMaster(BillInMaster billInMaster)
	{

	    return billInMasterMapper.insertBillInMaster(billInMaster);
	}
	
	/**
     * 修改入库单
     * 
     * @param billInMaster 入库单信息
     * @return 结果
     */
	@Override
	public int updateBillInMaster(BillInMaster billInMaster)
	{
	    return billInMasterMapper.updateBillInMaster(billInMaster);
	}

	/**
     * 删除入库单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBillInMasterByIds(String ids)
	{
		return billInMasterMapper.deleteBillInMasterByIds(Convert.toStrArray(ids));
	}

	
}
