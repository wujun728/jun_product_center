package com.deer.wms.base.system.service.box.impl;

import com.deer.wms.base.system.dao.box.BoxInfoMapper;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxInfoCriteria;
import com.deer.wms.base.system.model.box.BoxInfoDto;
import com.deer.wms.base.system.service.box.BoxInfoService;


import com.deer.wms.common.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by guo on 2019/06/24.
 */
@Service
@Transactional
public class BoxInfoServiceImpl extends AbstractService<BoxInfo, Integer> implements BoxInfoService {

    @Autowired
    private BoxInfoMapper boxInfoMapper;

    /**
     * 根据boxItemid  查询boxInfoDto相关信息
     *
     * @param boxItemId
     * @return
     */
    @Override
    public BoxInfoDto getBoxInfoDtoByboxItemId(Integer boxItemId) {
        return boxInfoMapper.getBoxInfoDtoByboxItemId(boxItemId);
    }

    /**
     * 根据taskId查询容器信息
     *
     * @param taskId
     * @return
     */
    @Override
    public BoxInfo getBoxInfoByTaskId(String taskId) {
        return boxInfoMapper.getBoxInfoByTaskId(taskId);
    }

    /**
     * 根据托盘编码查询托盘信息
     *
     * @param boxCode
     * @return
     */
    @Override
    public BoxInfo getBoxInfoByBoxCode(String boxCode) {
        return boxInfoMapper.getBoxInfoByBoxCode(boxCode);
    }

    /**
     * 入库任务， 当托盘已经到达装货点，装货完毕后   调用此方法寻找合适的货位返回
     *
     * @return
     */
    @Override
    public BoxInfoDto getFreeCellInfoForBack() {
        return boxInfoMapper.getFreeCellInfoForBack();
    }

    /**
     * 查找空的货位信息  根据状态值为0，  有货的情况为1
     *
     * @return
     */
    @Override
    public BoxInfoDto getFreeCellInfoForBillIn() {
        return boxInfoMapper.getFreeCellInfoForBillIn();
    }

    /**
     * 	根据物料编码，批次，数量 查询货位用于入库
     *
     * @param itemCode
     * @param batch
     * @param quantity
     * @return
     */
    @Override
    public BoxInfoDto getHalfCellInfoForBillIn(String itemCode, String batch, Double quantity) {
        return boxInfoMapper.getHalfCellInfoForBillIn(itemCode,batch,quantity);
    }

    @Override
    public void updateBoxInfo(BoxInfo boxInfo){
        boxInfoMapper.updateBoxInfo(boxInfo);
    }

    @Override
    public List<BoxInfoDto> findList(BoxInfoCriteria criteria){
        return boxInfoMapper.findList(criteria);
    }
}
