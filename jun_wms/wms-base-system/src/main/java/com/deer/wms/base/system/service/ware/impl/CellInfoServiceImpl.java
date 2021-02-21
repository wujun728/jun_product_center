package com.deer.wms.base.system.service.ware.impl;

import com.deer.wms.base.system.model.TaskTypeConstant;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.model.ware.CellInfoCriteria;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.core.text.Convert;
import com.deer.wms.base.system.dao.ware.CellInfoMapper;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
import com.deer.wms.framework.util.MyUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 货位设置 服务层实现
 * 
 * @author deer
 * @date 2019-05-08
 */
@Service
public class CellInfoServiceImpl  extends AbstractService<CellInfo, Integer> implements ICellInfoService
{
	@Autowired
	private CellInfoMapper cellInfoMapper;

	@Autowired
	private IBoxItemService boxItemService;

	@Autowired
	private ITaskInfoService taskInfoService;

	@Autowired
	private BoxInfoService boxInfoService;

	/**
	 * 将坐标值封装格式
	 *
	 * @param str
	 * @return
	 */
	@Override
	public String toStringForWcs(String str) {

		String[] strs = str.split(":");


		String s1 = strs[0];
		if(s1.length() == 1){

			s1 = "0" + s1;
		}

		String s2 = strs[1];
		if(s2.length() == 1){

			s2 = "00" + s2;
		}else if(s2.length() == 2){

			s2 = "0" +s2;
		}

		String s3 = strs[2];
		if(s3.length() == 1){

			s3 = "00" + s3;
		}else if(s3.length() == 2){

			s3 = "0" + s3;
		}
		String newStr = s1 + s2 + s3;

		return newStr;
	}


	/**
	 * 根据任务id查找货位信息
	 *
	 * @param taskId
	 * @return
	 */
	@Override
	public CellInfo getCellInfoByTaskId(String taskId) {

		return cellInfoMapper.getCellInfoByTaskId(taskId);
	}

	/**
	 * 查询没有托盘的货位  排序查第一个
	 *
	 * @return
	 */
	@Override
	public CellInfo getCellInfoHasNoBoxInfo() {

		return cellInfoMapper.getCellInfoHasNoBoxInfo();
	}

	/**
	 * 根据物料名，物料编码，批次 查找货位相关信息
	 *
	 * @return
	 */
	@Override
	public List<CellInfoDto> findCellInfoDtoByItemNameAndItemCodeAndBatch(String itemName, String itemCode, String batch) {

		return cellInfoMapper.findCellInfoDtoByItemNameAndItemCodeAndBatch(itemName,itemCode,batch);
	}

	/**
	 * 查询货位表主键最大值，用于同步添加容器box
	 *
	 * @return
	 */
	@Override
	public Integer selectMaxCellInfoId() {

		return cellInfoMapper.selectMaxCellInfoId();
	}

	/**
	 * 根据货架ID查询所有货位信息
	 *
	 * @param shelfId
	 * @return
	 */
	@Override
	public List<CellInfo> selectCellInfoByShelfId(Integer shelfId) {


		return cellInfoMapper.selectCellInfoByShelfId(shelfId);
	}

	/**
     * 查询货位设置信息
     * 
     * @param cellId 货位设置ID
     * @return 货位设置信息
     */
    @Override
	public CellInfo selectCellInfoById(Integer cellId)
	{
	    return cellInfoMapper.selectCellInfoById(cellId);
	}
	
	/**
     * 查询货位设置列表
     * 
     * @param cellInfo 货位设置信息
     * @return 货位设置集合
     */
	@Override
	public List<CellInfo> selectCellInfoList(CellInfo cellInfo)
	{
	    return cellInfoMapper.selectCellInfoList(cellInfo);
	}
	
    /**
     * 新增货位设置
     * 
     * @param cellInfo 货位设置信息
     * @return 结果
     */
	@Override
	public int insertCellInfo(CellInfo cellInfo)
	{
	    return cellInfoMapper.insertCellInfo(cellInfo);
	}
	
	/**
     * 修改货位设置
     * 
     * @param cellInfo 货位设置信息
     * @return 结果
     */
	@Override
	public int updateCellInfo(CellInfo cellInfo)
	{
	    return cellInfoMapper.updateCellInfo(cellInfo);
	}

	/**
     * 删除货位设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCellInfoByIds(String ids)
	{
		return cellInfoMapper.deleteCellInfoByIds(Convert.toStrArray(ids));
	}

	/**
	 * 通过CellId获得Postion
	 * @param cellId
	 * @return
	 */
	@Override
	public String getPositionByCellId(Integer cellId) {
		CellInfo cellInfo = super.findBy("cellId",cellId);
		String position = ""+cellInfo.getShelfId()+cellInfo.getSColumn()+cellInfo.getSColumn();
		return position;
	}

	public void deleteByShelfId(Integer shelfId){
		cellInfoMapper.deleteByShelfId(shelfId);
	}

	public Double cellOccupyRatio(){
		return cellInfoMapper.cellOccupyRatio();
	}
	public int notItemCell(){
		return cellInfoMapper.notItemCell();
	}
	public int count(){return cellInfoMapper.count();}
	public int available(){return cellInfoMapper.available();}

	public CellInfoDto getBestCell(){
		return cellInfoMapper.getBestCell();
	}

	/**
	 * 根据批次与料号查询信息
	 * @param criteria
	 * @return
	 */
	@Override
	public List<CellInfoDto> findList(CellInfoCriteria criteria){
		return 	cellInfoMapper.findList(criteria);
	}

	@Override
	public void updateCellInfoState(CellInfo cellInfo,@Param("state") Integer state){
		cellInfo.setState(state);
		update(cellInfo);
	}

	@Override
	public List<CellInfo> selectCellInfoListByAreaId(@Param("areaId") Integer areaId){
		return cellInfoMapper.selectCellInfoListByAreaId(areaId);
	}
	@Override
	public String judgeBoxItemState(List<BoxItemDto> boxItemDtos){
		for(BoxItemDto boxItemDto : boxItemDtos){
			if(!boxItemDto.getCellState().equals(1)){
				return "选中箱不在货位";
			}
			if(!boxItemDto.getBoxState().equals(1)){
				return "选中箱任务中";
			}
			if(boxItemDto.getWorkOrderStockState().equals(1)){
				return "选中箱工单锁定";
			}
		}
		return "success";
	}
	@Override
	public void updateCellStateAndBoxStateAndSendTaskInfo(BoxItemDto boxItemDto, Integer billOutDetailId, String loginPersonCard){
		BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxItemDto.getBoxCode());
		boxInfo.setBoxState(2);
		boxInfoService.update(boxInfo);
		CellInfo cellInfo = findById(boxItemDto.getBoxCellId());
		updateCellInfoState(cellInfo,2);
		TaskInfo taskInfo = new TaskInfo(new GuidUtils().toString(),
				MyUtils.connectShelfNameAndRowAndColumn(boxItemDto.getShelfName(),boxItemDto.getsColumn(),boxItemDto.getsRow()),
				"105", TaskTypeConstant.CELL_TO_OPERATOR_FLOOR,0,boxItemDto.getQuantity(),boxItemDto.getBoxCode(), "0",null
		);
		if(billOutDetailId != null){
			taskInfo.setBillOutDetailId(billOutDetailId);
		}
		taskInfo.setTaskStartTime(DateUtils.getTime());
		taskInfo.setCardNo(loginPersonCard);
		taskInfoService.save(taskInfo);
	}
	/** 下发出空框或半框到入库口任务*/
	@Override
	public String findOutBox(Integer math,String itemCode, String batch,String exp,String loginPersonCard){
		String message = "";
		CellInfoCriteria cellInfoCriteria = new CellInfoCriteria();
		cellInfoCriteria.setTypeAndState(math);
		if(math.equals(1001)){
			cellInfoCriteria.setItemCode(itemCode);
			cellInfoCriteria.setBatch(batch);
			cellInfoCriteria.setExp(exp);
		}
		List<CellInfoDto> cellInfoDtos = cellInfoMapper.findList(cellInfoCriteria);
		if(cellInfoDtos.size()>0) {
			CellInfoDto cellInfoDto = cellInfoDtos.get(0);
			updateCellInfoState(cellInfoDto,2);
			BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(cellInfoDto.getBoxCode());
			boxInfo.setBoxState(2);
			boxInfoService.update(boxInfo);
			message = cellInfoDto.getBoxCode();
			TaskInfo taskInfo = new TaskInfo(null, new GuidUtils().toString(),
					MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(),cellInfoDto.getSColumn(),cellInfoDto.getSRow()),
					null, TaskTypeConstant.CELL_TO_OPERATOR_FLOOR, 0, null, cellInfoDto.getBoxCode());
			taskInfo.setIsTop("0");
			taskInfo.setTaskStartTime(DateUtils.getTime());
			taskInfo.setCardNo(loginPersonCard);
			taskInfoService.save(taskInfo);
		}else {
			message = "error";
		}
		return message;
	}
	@Override
	public String inNullBox(@Param("boxCode") String boxCode,@Param("loginPersonCard") String loginPersonCard){
		CellInfoDto cellInfoDto = getBestCell();
		String error = "";
		if (cellInfoDto != null) {
			updateCellInfoState(cellInfoDto, 2);
			String end = MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(), cellInfoDto.getSColumn(), cellInfoDto.getSRow());
			BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxCode);
			if (boxInfo != null) {
				boxInfo = new BoxInfo(
						boxInfo.getBoxId(), boxCode, 2, cellInfoDto.getCellId(), 2, 0);
				boxInfoService.update(boxInfo);
				BoxItem boxItem = boxItemService.getBoxItemByBoxCode(boxCode);
				boxItem.setQuantity(0);
				boxItem.setSubInventoryId(0);
				boxItem.setForecastStockQuantity(0);
                boxItem.setWorkOrderStockState(0);
				boxItemService.update(boxItem);
			} else {
				boxInfo = new BoxInfo(
						null, boxCode, 2, cellInfoDto.getCellId(), 2, 0);
				boxInfoService.save(boxInfo);
				BoxItem boxItem = new BoxItem(boxCode,null,null,0,null,0);
				boxItem.setForecastStockQuantity(0);
				boxItem.setWorkOrderStockState(0);
				boxItemService.save(boxItem);
			}
			TaskInfo taskInfo = new TaskInfo(null, new GuidUtils().toString(), "105", end, TaskTypeConstant.IN_NULL_BOX, 0, null, boxCode);
			taskInfo.setIsTop("0");
			taskInfo.setCardNo(loginPersonCard);
			taskInfo.setTaskStartTime(DateUtils.getTime());
			taskInfoService.save(taskInfo);
			error = "success";
		} else {
			error = "货位已满!";
		}
		return error;
	}

	@Override
	public CellInfoDto findByCellId(@Param("cellId") Integer cellId){
		return cellInfoMapper.findByCellId(cellId);
	}
}
