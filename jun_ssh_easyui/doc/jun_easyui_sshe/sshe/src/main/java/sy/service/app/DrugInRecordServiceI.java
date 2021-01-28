package sy.service.app;

import java.util.List;

import sy.model.app.DrugInRecord;
import sy.model.app.DrugStore;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用药记录业务逻辑
 * 
 */
public interface DrugInRecordServiceI extends BaseServiceI<DrugInRecord> {

	/**
	 * 更新用药记录
	 */
	public void updateDrugRecord(DrugInRecord DrugRecord);

	/**
	 * 查找用药记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugInRecord> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找用药记录
	 */
	public List<DrugInRecord> findDrugRecordByFilter(HqlFilter hqlFilter);

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
	public void saveDrugRecord(DrugInRecord ustomerInfo, String userId);

	/**
	 * 添加一个用药记录, 并且修正库存的数据
	 * 
	 * @param data
	 * @param userId
	 */
	void saveDrugRecordAndRelStore(DrugInRecord drugRecord, String userId);
	
	
	

}
