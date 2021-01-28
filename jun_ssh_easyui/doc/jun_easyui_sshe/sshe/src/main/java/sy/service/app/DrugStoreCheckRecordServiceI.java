package sy.service.app;

import java.util.List;

import sy.model.app.DrugStoreCheck;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用药记录业务逻辑
 * 
 */
public interface DrugStoreCheckRecordServiceI extends BaseServiceI<DrugStoreCheck> {

	/**
	 * 更新用药记录
	 */
	public void updateDrugStoreCheck(DrugStoreCheck DrugStoreCheck);

	/**
	 * 查找用药记录
	 * 
	 * @param hqlFilter
	 * @param page
//	 * @param rows
	 * @return
	 */
	public List<DrugStoreCheck> findDrugStoreCheckByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找用药记录
	 */
	public List<DrugStoreCheck> findDrugStoreCheckByFilter(HqlFilter hqlFilter);

	/**
	 * 统计用药记录
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countDrugStoreCheckByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个用药记录
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveDrugStoreCheck(DrugStoreCheck drugStoreCheck, String userId);

	/**
	 * 添加一个用药记录, 并且修正库存的数据
	 * 
	 * @param data
	 * @param userId
	 */
	void saveDrugStoreCheckAndRelStore(DrugStoreCheck drugStoreCheck, String userId);

}
