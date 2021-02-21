package com.deer.wms.base.system.service.bill;


import com.deer.wms.base.system.model.bill.BillInMasterCriteria;
import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.bill.BillInDetail;
import com.deer.wms.base.system.model.bill.BillInMaster;
import com.deer.wms.base.system.model.bill.BillInMasterDto;
import com.deer.wms.base.system.model.box.BoxItem;

import java.util.List;

/**
 * 入库单 服务层
 * 
 * @author guo
 * @date 2019-05-13
 */
public interface IBillInMasterService extends Service<BillInMaster, Integer>
{

	/**
	 *
	 * 查询物料详情数据(点击物料详情)
	 */
	public List<BillInDetail> selectBillInDetail();


	/**
	 *
	 * 查询组盘(点击组盘信息)
	 */

	public List<BoxItem> selectBoxItem(Integer billId);



	/**
	 *
	 * 保存组盘
	 */
	public void saveBox(BoxItem boxItem);


	/**
     * 查询入库单信息
     * 
     * @param billId 入库单ID
     * @return 入库单信息
     */
	public BillInMaster selectBillInMasterById(Integer billId);
	
	/**
     * 查询入库单列表
     * 
     * @param billInMaster 入库单信息
     * @return 入库单集合
     */
	public List<BillInMaster> selectBillInMasterList(BillInMaster billInMaster);

	/**
	 * 查询入库单列表(关联查询)
	 *
	 * @return 入库单集合
	 */
	public List<BillInMasterDto> findList(BillInMasterCriteria billInMasterCriteria);
	
	/**
     * 新增入库单
     * 
     * @param billInMaster 入库单信息
     * @return 结果
     */
	public int insertBillInMaster(BillInMaster billInMaster);
	
	/**
     * 修改入库单
     * 
     * @param billInMaster 入库单信息
     * @return 结果
     */
	public int updateBillInMaster(BillInMaster billInMaster);
		
	/**
     * 删除入库单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBillInMasterByIds(String ids);
	
}
