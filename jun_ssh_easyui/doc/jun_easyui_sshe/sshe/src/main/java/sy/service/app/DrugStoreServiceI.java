package sy.service.app;

import java.util.List;

import sy.model.app.DrugStore;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 药品仓库业务逻辑
 * 
 */
public interface DrugStoreServiceI extends BaseServiceI<DrugStore> {

	/**
	 * 更新药品仓库
	 */
	public void updateDrugStore(DrugStore DrugStore);

	/**
	 * 查找药品仓库
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugStore> findDrugStoreByFilter(HqlFilter hqlFilter, int page,
			int rows);

	/**
	 * 查找药品仓库
	 */
	public List<DrugStore> findDrugStoreByFilter(HqlFilter hqlFilter);

	/**
	 * 统计药品仓库
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countDrugStoreByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个药品仓库
	 * 
	 * @param drugStore
	 * @param userId
	 */
	public void saveDrugStore(DrugStore drugStore, String userId);

	/**
	 * 修改库存数据， 根据操作类型opType 写对应的日志
	 * 
	 * @param drugStore
	 * @param userId
	 * @param opType
	 */
	public void saveOrUpdateDrugStore(DrugStore drugStore, String userId,
			byte opType);

	public List<DrugStore> getDrugStoreByCond(int specId, String drugTLotNo,
			String unit);

	public int getDrugStoreCount(int specId, String drugTLotNo, String unit);

}
