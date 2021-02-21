package com.deer.wms.base.system.service.ware;


import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.ware.CellInfoCriteria;
import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;

import java.util.List;

/**
 * 货位设置 服务层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface ICellInfoService  extends Service<CellInfo, Integer>
{


	/**
	 * 将坐标值封装格式
	 *
	 * @param str
	 * @return
	 */
	public String toStringForWcs(String str);

	/**
	 * 根据任务id查找货位信息
	 *
	 * @param taskId
	 * @return
	 */
	public CellInfo getCellInfoByTaskId(String taskId);


	/**
	 * 查询没有托盘的货位  排序查第一个
	 *
	 * @return
	 */
	public CellInfo getCellInfoHasNoBoxInfo();


	/**
	 * 根据物料名，物料编码，批次 查找货位相关信息
	 *
	 * @return
	 */
	public List<CellInfoDto> findCellInfoDtoByItemNameAndItemCodeAndBatch(String itemName, String itemCode, String batch);


	/**
	 * 查询货位表主键最大值，用于同步添加容器box
	 *
	 * @return
	 */
	public Integer selectMaxCellInfoId();

	/**
	 * 根据货架ID查询所有货位信息
	 *
	 * @param shelfId
	 * @return
	 */
	public List<CellInfo> selectCellInfoByShelfId(Integer shelfId);



	/**
     * 查询货位设置信息
     * 
     * @param cellId 货位设置ID
     * @return 货位设置信息
     */
	public CellInfo selectCellInfoById(Integer cellId);
	
	/**
     * 查询货位设置列表
     * 
     * @param cellInfo 货位设置信息
     * @return 货位设置集合
     */
	public List<CellInfo> selectCellInfoList(CellInfo cellInfo);
	
	/**
     * 新增货位设置
     * 
     * @param cellInfo 货位设置信息
     * @return 结果
     */
	public int insertCellInfo(CellInfo cellInfo);
	
	/**
     * 修改货位设置
     * 
     * @param cellInfo 货位设置信息
     * @return 结果
     */
	public int updateCellInfo(CellInfo cellInfo);
		
	/**
     * 删除货位设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCellInfoByIds(String ids);



	String getPositionByCellId(Integer cellId);

	void deleteByShelfId(Integer shelfId);

	/**
	 * 提供给三维立体显示信息
	 */

	/**
	 * 货位利用率
	 * @return
	 */
	Double cellOccupyRatio();

	/***
	 * 可用货位
	 * @return
	 */
	int notItemCell();
	//总货位
	int count();
	//有货货位
	int available();

	/**
	 * 获取货位最优路径
	 */
	CellInfoDto getBestCell();

	/**
	 * 1001-根据批次与料号查询货位信息
	 * 1002-查询有托盘无货的货位
	 * 1003-根据托盘编码查询货位
	 * @param criteria
	 * @return
	 */
	List<CellInfoDto> findList(CellInfoCriteria criteria);

	/** 根据cellInfo修改货位状态*/
	void updateCellInfoState(CellInfo cellInfo,Integer state);

	List<CellInfo> selectCellInfoListByAreaId(Integer areaId);

	String findOutBox(Integer math,String itemCode, String batch,String exp,String loginPersonCard);

	void updateCellStateAndBoxStateAndSendTaskInfo(BoxItemDto boxItemDto, Integer billOutDetailId, String loginPersonCard);

	String inNullBox(String boxCode,String loginPersonCard);

	String judgeBoxItemState(List<BoxItemDto> boxItemDtos);

	CellInfoDto findByCellId(Integer cellId);
}
