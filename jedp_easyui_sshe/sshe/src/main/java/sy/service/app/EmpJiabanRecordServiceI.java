package sy.service.app;

import java.util.List;

import sy.model.app.EmpJiabanRecord;
import sy.model.app.EmpJiabanRecordDetail;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 加班请假业务逻辑
 * 
 */
public interface EmpJiabanRecordServiceI extends BaseServiceI<EmpJiabanRecord> {

	/**
	 * 更新加班请假
	 */
	public void updateEmpJiabanRecord(EmpJiabanRecord EmpJiabanRecord);

	/**
	 * 查找加班请假
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpJiabanRecord> findEmpJiabanRecordByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找加班请假
	 */
	public List<EmpJiabanRecord> findEmpJiabanRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 统计加班请假
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpJiabanRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个加班请假
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpJiabanRecord(EmpJiabanRecord ustomerInfo, String userId);

	/**
	 * 提交到明细操作表中
	 * 
	 * @param EmpJiabanRecord
	 * @param EmpJiabanRecordDetail
	 * @param userId
	 */
	public void updateEmpJiabanRecord(EmpJiabanRecord EmpJiabanRecord,
			EmpJiabanRecordDetail EmpJiabanRecordDetail, String userId);

}
