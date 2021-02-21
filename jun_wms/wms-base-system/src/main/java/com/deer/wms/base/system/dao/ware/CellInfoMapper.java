package com.deer.wms.base.system.dao.ware;

import com.deer.wms.base.system.model.ware.CellInfoCriteria;
import com.deer.wms.base.system.web.ware.CellInfoController;
import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 货位设置 数据层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface CellInfoMapper extends Mapper<CellInfo>
{

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
	public List<CellInfoDto> findCellInfoDtoByItemNameAndItemCodeAndBatch
				(@Param("itemName") String itemName,@Param("itemCode") String itemCode,@Param("batch") String batch);


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
     * 删除货位设置
     * 
     * @param cellId 货位设置ID
     * @return 结果
     */
	public int deleteCellInfoById(Integer cellId);
	
	/**
     * 批量删除货位设置
     * 
     * @param cellIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCellInfoByIds(String[] cellIds);

	void deleteByShelfId(Integer shelfId);

	Double cellOccupyRatio();
	int notItemCell();
	int count();
	int available();

	/**
	 * 查询无货货位
	 * @return
	 */
	CellInfoDto getBestCell();
	/**
	 * 根据批次与料号查询信息
	 * @param criteria
	 * @return
	 */
	List<CellInfoDto> findList(CellInfoCriteria criteria);

	List<CellInfo> selectCellInfoListByAreaId(Integer areaId);

	CellInfoDto findByCellId(Integer cellId);
}