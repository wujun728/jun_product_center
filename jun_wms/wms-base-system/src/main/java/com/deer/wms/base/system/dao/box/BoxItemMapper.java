package com.deer.wms.base.system.dao.box;


import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.box.UnqualifiedOverTakeCanDelayDays;
import com.deer.wms.common.core.commonMapper.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组盘 数据层
 * 
 * @author guo
 * @date 2019-06-03
 */
public interface BoxItemMapper extends Mapper<BoxItem>
{



	/**
	 *	关联查询托盘相关信息
	 *
	 * @return
	 */
	public List<BoxItemDto> selectBoxItemDtoList(BoxItemCriteria boxItemCriteria);


	/**
	 * 根据托盘编码寻找所有在货位上的托盘信息  便于统计数据
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
	 * 根据 托盘编码查找托盘
	 *
	 * @param boxCode
	 * @return
	 */
	public BoxItem getBoxItemByBoxCode(String boxCode);

	/**
	 * 根据数量(数量==0)查询空货位用于入库
	 *
	 * @param
	 * @return
	 */
	public BoxItemDto getFreeCellInfoForBillIn();



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
     * 删除组盘
     * 
     * @param id 组盘ID
     * @return 结果
     */
	public int deleteBoxItemById(Long id);

	void deleteByBoxCode(@Param("boxCode") String boxCode);
	
	/**
     * 批量删除组盘
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBoxItemByIds(String[] ids);

	/**
	 * 根据id升序查找第一个可合框物料
	 */
	BoxItem findOneCombineBoxGroupByItemCodeAndBatch();

	/**
	 * 根据物料编码与批次查询多条可合框货位
	 */
	List<BoxItemDto> findMoreCombineBoxByItemCodeAndBatch(BoxItem boxItem);

	List<BoxItemDto> findByWorkerOrder(BoxItemCriteria criteria);


	List<BoxItemDto> findList(BoxItemCriteria criteria);

	List<BoxItemDto> findSluggishOverdue(BoxItemCriteria criteria);

	List<BoxItemDto> findWillOverdue(BoxItemCriteria criteria);

	List<BoxItemDto> findBoxItemList(BoxItemCriteria criteria);

	List<BoxItemDto> workerOrderLackOut(BoxItemCriteria criteria);

	List<UnqualifiedOverTakeCanDelayDays> findUnqualifiedOverTakeCanDelayDays(BoxItemCriteria criteria);
}
