package com.deer.wms.base.system.service.box.impl;

import com.deer.wms.base.system.dao.box.BoxItemMapper;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.box.UnqualifiedOverTakeCanDelayDays;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.core.text.Convert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 组盘 服务层实现
 * 
 * @author guo
 * @date 2019-06-03
 */
@Service
public class BoxItemServiceImpl extends AbstractService<BoxItem, String> implements IBoxItemService
{
	@Autowired
	private BoxItemMapper boxItemMapper;

	@Autowired
	private IBoxItemService boxItemService;


	/**
	 *	关联查询托盘相关信息
	 *
	 * @return
	 */
	@Override
	public List<BoxItemDto> selectBoxItemDtoList(BoxItemCriteria boxItemCriteria) {

		return boxItemMapper.selectBoxItemDtoList(boxItemCriteria);
	}

	/**
	 * 根据托盘编码寻找所有在货位上的托盘信息  便于统计数据
	 *
	 * @param itemCode
	 * @return
	 */
	@Override
	public List<BoxItemDto> getBoxItemDtoByitemCode(String itemCode) {

		return boxItemMapper.getBoxItemDtoByitemCode(itemCode);
	}

	/**
	 * 根据任务id查询托盘信息
	 *
	 * @param taskId
	 * @return
	 */
	@Override
	public BoxItem getBoxItemByTaskId(String taskId) {

		return boxItemMapper.getBoxItemByTaskId(taskId);
	}

	/**
	 * 根据物料编码查询货位信息  (根据批次排序， 保证先进先出)
	 *
	 * @param itemCode
	 * @return
	 */
	@Override
	public List<BoxItemDto> getFullCellInfoForOutOfStock(String itemCode) {

		return boxItemMapper.getFullCellInfoForOutOfStock(itemCode);
	}

	/**
	 *
	 * 出库任务，寻找合适的出库货位(根据货物编码)
	 *
	 * @param itemCode
	 * @param quantity
	 * @return   返回类型集合， 当最早批次货位货物数量不够时，继续用别的货位,一直不够就一直加，直到所有货位都出货
	 */
	@Override
	public List<BoxItemDto> getFullCellInfoForOutOfStockForSaveTaskInfo(@Param("itemCode") String itemCode,@Param("quantity") Integer quantity) {
		List<BoxItemDto> boxItemDtos = boxItemService.getFullCellInfoForOutOfStock(itemCode);
		if(boxItemDtos != null){
			Integer quantitys = 0;
			int count = 0;
			/*for(BoxItemDto boxItemDto : boxItemDtos){
				quantitys += boxItemDto.getQuantity();
				//当需要的数量<可取出的数量
				if(quantity < quantitys){
					//如果第一次循环就也满足上面条件，则就取第一个元素作为新集合返回
					if(count == 0){
						List<BoxItemDto> fistBoxItemDto = new ArrayList<BoxItemDto>();
						fistBoxItemDto.add(boxItemDto);
						return fistBoxItemDto;
					}
					//如果超过1次循环,则截取一个新的集合返回
					List<BoxItemDto> newBoxItemDtos = boxItemDtos.subList(0,count+1);
					return newBoxItemDtos;
				}
				count++;
				//如果计已经达到集合长度，表示货物不足， 直接返回该集合，全出
				if(count == boxItemDtos.size()){
					return boxItemDtos;
				}
			}*/
		}
		return null;
	}

	/**
	 * 根据 托盘编码查找托盘
	 *
	 * @param boxCode
	 * @return
	 */
	@Override
	public BoxItem getBoxItemByBoxCode(String boxCode) {

		return boxItemMapper.getBoxItemByBoxCode(boxCode);
	}

	/**
     * 查询组盘信息
     * 
     * @param id 组盘ID
     * @return 组盘信息
     */
    @Override
	public BoxItem selectBoxItemById(Integer id)
	{
	    return boxItemMapper.selectBoxItemById(id);
	}
	
	/**
     * 查询组盘列表
     * 
     * @param boxItem 组盘信息
     * @return 组盘集合
     */
	@Override
	public List<BoxItem> selectBoxItemList(BoxItem boxItem)
	{
	    return boxItemMapper.selectBoxItemList(boxItem);
	}
	
    /**
     * 新增组盘
     * 
     * @param boxItem 组盘信息
     * @return 结果
     */
	@Override
	public int insertBoxItem(BoxItem boxItem)
	{
	    return boxItemMapper.insertBoxItem(boxItem);
	}
	
	/**
     * 修改组盘
     * 
     * @param boxItem 组盘信息
     * @return 结果
     */
	@Override
	public int updateBoxItem(BoxItem boxItem)
	{
	    return boxItemMapper.updateBoxItem(boxItem);
	}

	@Override
	public void deleteByBoxCode(String boxCode){
		boxItemMapper.deleteByBoxCode(boxCode);
	}

	/**
     * 删除组盘对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBoxItemByIds(String ids)
	{
		return boxItemMapper.deleteBoxItemByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据id升序查找第一个可合框物料
	 */
	public BoxItem findOneCombineBoxGroupByItemCodeAndBatch(){
		return boxItemMapper.findOneCombineBoxGroupByItemCodeAndBatch();
	}

	public List<BoxItemDto> findMoreCombineBoxByItemCodeAndBatch(BoxItem boxItem){
		return boxItemMapper.findMoreCombineBoxByItemCodeAndBatch(boxItem);
	}

	public List<BoxItemDto> findByWorkerOrder(BoxItemCriteria criteria){
		return boxItemMapper.findByWorkerOrder(criteria);
	}

	/**
	 * 跟物料编码查询合适的托盘进行预测备料
	 * @return
	 */
	@Override
	public List<BoxItemDto> findSuitByItemCodeAndQuantity(@Param("itemCode") String itemCode,@Param("quantity") Integer quantity) {
		List<BoxItemDto> boxItemDtos = boxItemService.getFullCellInfoForOutOfStock(itemCode);
		if(boxItemDtos != null){
			Integer quantitys = 0;
			int count = 0;
			List<BoxItemDto> lists = new ArrayList<BoxItemDto>();
			for(BoxItemDto boxItemDto : boxItemDtos){
				quantitys += boxItemDto.getQuantity()-boxItemDto.getForecastStockQuantity();
				//当需要的数量小于等于托盘中可出数量
				if(quantity<=quantitys){
					//如果第一次循环就也满足上面条件，则就取第一个元素作为新集合返回
					if(count == 0){
						lists.add(boxItemDto);
						return lists;
					}
					//如果超过1次循环,则截取一个新的集合返回
					lists = boxItemDtos.subList(0,count+1);
					return lists;
				}
				count++;
			}
		}
		return null;
	}

	@Override
	public List<BoxItemDto> findList(BoxItemCriteria criteria){
		return boxItemMapper.findList(criteria);
	}

	@Override
	public List<BoxItemDto> findSluggishOverdue(BoxItemCriteria criteria){
		return boxItemMapper.findSluggishOverdue(criteria);
	}

	@Override
	public List<BoxItemDto> findWillOverdue(BoxItemCriteria criteria){
		return boxItemMapper.findWillOverdue(criteria);
	}

	@Override
	public List<BoxItemDto> findBoxItemList(BoxItemCriteria criteria){
		return boxItemMapper.findBoxItemList(criteria);
	}

	@Override
	public List<BoxItemDto> workerOrderLackOut(BoxItemCriteria criteria){
		return boxItemMapper.workerOrderLackOut(criteria);
	}

	@Override
	public 	List<UnqualifiedOverTakeCanDelayDays> findUnqualifiedOverTakeCanDelayDays(BoxItemCriteria criteria)
	{
		return boxItemMapper.findUnqualifiedOverTakeCanDelayDays(criteria);
	}

}
