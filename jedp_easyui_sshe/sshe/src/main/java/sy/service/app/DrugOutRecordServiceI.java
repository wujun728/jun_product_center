package sy.service.app;

import java.util.List;

import sy.model.app.DrugOutRecord;
import sy.model.app.DrugStore;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用药记录业务逻辑
 * 
 */
public interface DrugOutRecordServiceI extends BaseServiceI<DrugOutRecord> {

	/**
	 * 更新用药记录
	 */
	public void updateDrugRecord(DrugOutRecord DrugRecord);

	/**
	 * 查找用药记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugOutRecord> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找用药记录
	 */
	public List<DrugOutRecord> findDrugRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 统计用药记录
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countDrugRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个用药记录
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveDrugRecord(DrugOutRecord ustomerInfo, String userId);

	/**
	 * 添加一个用药记录, 并且修正库存的数据
	 * 
	 * @param data
	 * @param userId
	 */
	void saveDrugRecordAndRelStore(DrugOutRecord drugRecord, String userId);
	
	
	

}
