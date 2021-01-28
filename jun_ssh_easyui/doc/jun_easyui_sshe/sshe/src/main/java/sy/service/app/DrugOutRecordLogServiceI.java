package sy.service.app;

import java.util.List;

import sy.model.app.DrugOutRecord;
import sy.model.app.DrugOutRecordLog;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用药记录业务逻辑
 * 
 */
public interface DrugOutRecordLogServiceI extends BaseServiceI<DrugOutRecordLog> {

	/**
	 * 查找用药记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugOutRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找用药记录
	 */
	public List<DrugOutRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter);

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
	public void saveDrugRecord(DrugOutRecordLog drugOutRecordLog, String userId);

}
