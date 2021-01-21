package sy.service.app;

import java.util.List;

import sy.model.app.DrugInRecord;
import sy.model.app.DrugInRecordLog;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用药记录业务逻辑
 * 
 */
public interface DrugInRecordLogServiceI extends BaseServiceI<DrugInRecordLog> {

	/**
	 * 查找用药记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugInRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找用药记录
	 */
	public List<DrugInRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter);

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
	public void saveDrugRecord(DrugInRecordLog drugRecordLog, String userId);

}
