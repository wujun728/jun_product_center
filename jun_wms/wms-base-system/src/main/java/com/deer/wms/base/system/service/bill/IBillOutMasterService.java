package com.deer.wms.base.system.service.bill;


import com.deer.wms.base.system.model.bill.BillOutMasterCriteria;
import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.bill.BillOutMaster;
import com.deer.wms.base.system.model.bill.BillOutMasterDto;

import java.util.List;

/**
 * 出库单 服务层
 * 
 * @author guo
 * @date 2019-05-13
 */
public interface IBillOutMasterService  extends Service<BillOutMaster, Integer>
{

    /**
     * 根据billId查询出库单信息
     */
    public BillOutMasterDto findBillOutMasterDtoByBillId(Integer billId);



    /**
     * 删除入库单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBillOutMasterByIds(String ids);


    /**
     * 修改BillOutMaster信息
     *
     * @param billOutMaster
     */
    public int updateBillOutMaster(BillOutMaster billOutMaster);



    /**
     * 根据billId查找BillOutMaster
     *
     * @return
     */
    public BillOutMaster selectBillOutMasterById(Integer billId);


    /**
     * 保存BillOutMaster  出库单
     *
     * @param billOutMaster
     */
    public void saveBillOutMaster(BillOutMaster billOutMaster);



    /**
     *
     * 查询出库单
     *
     * @return
     */
    public List<BillOutMasterDto> selectBillOutMasterList();

    /**
     * 根据billOutDetailId查询billoutMaster信息
     */
    BillOutMaster selectBillOutMasterByBillOutDetailId(Integer billOutDetailId);

    /**
     * 根据条件查询相应出库单据
     * @param criteria
     * @return
     */
    List<BillOutMasterDto> findList(BillOutMasterCriteria criteria);

}
