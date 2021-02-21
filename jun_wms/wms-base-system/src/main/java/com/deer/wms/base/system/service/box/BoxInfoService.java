package com.deer.wms.base.system.service.box;

import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxInfoCriteria;
import com.deer.wms.base.system.model.box.BoxInfoDto;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by guo on 2019/06/24.
 */
public interface BoxInfoService extends Service<BoxInfo, Integer> {


    /**
     * 根据boxItemid  查询boxInfoDto相关信息
     *
     * @param boxItemId
     * @return
     */
    public BoxInfoDto getBoxInfoDtoByboxItemId(Integer boxItemId);

    /**
     * 根据taskId查询容器信息
     *
     * @param taskId
     * @return
     */
    public BoxInfo getBoxInfoByTaskId(String taskId);

    /**
     * 根据托盘编码查询托盘信息
     *
     * @param boxCode
     * @return
     */
    public BoxInfo getBoxInfoByBoxCode(String boxCode);


    /**
     * 入库任务， 当托盘已经到达装货点，装货完毕后   调用此方法寻找合适的货位返回
     *
     * @return
     */
    public BoxInfoDto getFreeCellInfoForBack();


    /**
     * 查找空的货位信息  根据状态值为0，  有货的情况为1
     *
     * @return
     */
    public BoxInfoDto getFreeCellInfoForBillIn();


    /**
     * 	根据物料编码，批次，数量 查询货位信息
     *
     * @param itemCode
     * @param batch
     * @param quantity
     * @return
     */
    public BoxInfoDto getHalfCellInfoForBillIn(String itemCode, String batch, Double quantity);

    /**
     * 更新托盘信息
     */
    void updateBoxInfo(BoxInfo boxInfo);

    /**
     * 查询所有信息
     * @param criteria
     * @return
     */
    List<BoxInfoDto> findList(BoxInfoCriteria criteria);
}
