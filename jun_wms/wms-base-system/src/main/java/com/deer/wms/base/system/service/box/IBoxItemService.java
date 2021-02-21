package com.deer.wms.base.system.service.box;


import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.box.UnqualifiedOverTakeCanDelayDays;
import com.deer.wms.common.core.service.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组盘 服务层
 * 
 * @author guo
 * @date 2019-06-03
 */
public interface IBoxItemService extends Service<BoxItem, String>
{


	/**
	 *	关联查询托盘相关信息
	 *
	 * @return
	 */
	public List<BoxItemDto> selectBoxItemDtoList(BoxItemCriteria boxItemCriteria);



	/**
	 * 根据托盘编码寻找所有在货位上的托盘和其他信息  便于统计数据
	 *
	 * @param itemCode
	 * @return
	 */
	public List<BoxItemDto> getBoxItemDtoByitemCode(String itemCode);

	/**
	 * 根据任务id查询托盘信息
	 *
	 * @param taskId
	 * @return
	 */
	public BoxItem getBoxItemByTaskId(String taskId);

	/**
	 * 根据物料编码查询货位信息  (根据批次排序， 保证先进先出)
	 *
	 * @param itemCode
	 * @return
	 */
	public List<BoxItemDto> getFullCellInfoForOutOfStock(String itemCode);


	/**
	 *
	 * 出库任务，寻找合适的出库货位(根据货物编码)
	 *
	 * @param itemCode
	 * @param quantity
	 * @return   返回类型集合， 当最早批次货位货物数量不够时，继续用别的货位,一直不够就一直加，直到所有货位都出货
	 */
	public List<BoxItemDto>  getFullCellInfoForOutOfStockForSaveTaskInfo(String itemCode, Integer quantity);



	/**
	 * 根据 托盘编码查找托盘
	 *
	 * @param boxCode
	 * @return
	 */
	public BoxItem getBoxItemByBoxCode(String boxCode);


	/**
     * 查询组盘信息
     * 
     * @param id 组盘ID
     * @return 组盘信息
     */
	public BoxItem selectBoxItemById(Integer id);
	
	/**
     * 查询组盘列表
     * 
     * @param boxItem 组盘信息
     * @return 组盘集合
     */
	public List<BoxItem> selectBoxItemList(BoxItem boxItem);
	
	/**
     * 新增组盘
     * 
     * @param boxItem 组盘信息
     * @return 结果
     */
	public int insertBoxItem(BoxItem boxItem);
	
	/**
     * 修改组盘
     * 
     * @param boxItem 组盘信息
     * @return 结果
     */
	public int updateBoxItem(BoxItem boxItem);
		
	/**
     * 删除组盘信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBoxItemByIds(String ids);

	/**
	 * 根据id升序查找第一个可合框物料
	 */
	BoxItem findOneCombineBoxGroupByItemCodeAndBatch();

	/**
	 * 根据物料编码与批次查询多条可合框货位
	 */
	List<BoxItemDto> findMoreCombineBoxByItemCodeAndBatch(BoxItem boxItem);


	/**
	 * 查找工单备料货位
	 *
	 * @return
	 */
	List<BoxItemDto> findByWorkerOrder(BoxItemCriteria criteria);

	void deleteByBoxCode(String boxCode);

	/**
	 * 查找合适的货位进行预测备料
	 */
	List<BoxItemDto> findSuitByItemCodeAndQuantity(String itemCode, Integer quantity);

	//根据条件查询信息
	List<BoxItemDto> findList(BoxItemCriteria criteria);
	//根据查询条件呆滞过期物料
	List<BoxItemDto> findSluggishOverdue(BoxItemCriteria criteria);
	//根据条件查询即将过期物料
	List<BoxItemDto> findWillOverdue(BoxItemCriteria criteria);

	List<BoxItemDto> findBoxItemList(BoxItemCriteria criteria);

	/**
	 * 工单出库点数箱中数量缺少（查找符合数量的箱数到指定货位）
	 * @param criteria
	 * @return
	 */
	List<BoxItemDto> workerOrderLackOut(BoxItemCriteria criteria);

	/**
	 * 根据条件查询不合格库存滞库超过设定日期
	 * @param criteria
	 * @return
	 */
	List<UnqualifiedOverTakeCanDelayDays> findUnqualifiedOverTakeCanDelayDays(BoxItemCriteria criteria);

}
