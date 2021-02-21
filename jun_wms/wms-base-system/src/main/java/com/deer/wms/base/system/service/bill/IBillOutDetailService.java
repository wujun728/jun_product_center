package com.deer.wms.base.system.service.bill;

import com.deer.wms.base.system.model.bill.BillOutDetailCriteria;
import com.deer.wms.base.system.service.MESWebService.WebserviceResponse;
import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.bill.BillOutDetail;
import com.deer.wms.base.system.model.bill.BillOutDetailDto;

import java.util.List;

/**
 * 出库单 服务层
 * 
 * @author cai
 * @date 2019-05-13
 */
public interface IBillOutDetailService extends Service<BillOutDetail,Integer>
{

    /**
     * 根据BillOutDetailId删除BillOutDetail
     *
     */
    public void deleteBillOutDetailByBillOutDetailId(Integer billOutDetailId);


    /**
     *  根据billId查询BillOutDetail信息
     *
     * @param billId
     * @return
     */
    public List<BillOutDetailDto> findListByBillId(Integer billId);



    /**
     * 保存BillOutDetail  出库单详情
     *
     * @param billOutDetail
     */
    public void saveBillOutDetail(BillOutDetail billOutDetail);
    /**
     *  MES工单下发接口
     */
    WebserviceResponse downWipToStock(String input);
    WebserviceResponse emptyShelfArrive(String input);

    /**
     * 根据条件查询出库单
     * @param criteria
     * @return
     */
    List<BillOutDetail> findList(BillOutDetailCriteria criteria);
}
