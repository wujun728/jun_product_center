package sy.service.app;

import java.util.List;

import sy.model.app.EmpAttendRecord;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 员工考勤记录业务逻辑
 * 
 */
public interface EmpAttendRecordServiceI extends BaseServiceI<EmpAttendRecord> {

	/**
	 * 更新员工考勤记录
	 */
	public void updateEmpAttendRecord(EmpAttendRecord empAttendRecord);
	
	/**
	 * 查找员工考勤记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpAttendRecord> findEmpAttendRecordByFilter(
			HqlFilter hqlFilter, int page, int rows);
	
	/**
	 * 查找员工考勤记录
	 */
	public List<EmpAttendRecord> findEmpAttendRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 统计员工考勤记录
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpAttendRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个员工考勤记录
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpAttendRecord(EmpAttendRecord empAttendRecord,
			String userId);

	Long countEmpAttendRecordByFilterNoDept(HqlFilter hqlFilter);

	List<EmpAttendRecord> findEmpAttendRecordByFilterNoDept(
			HqlFilter hqlFilter, int page, int rows);

	List<EmpAttendRecord> findEmpAttendRecordByFilterNoDept(HqlFilter hqlFilter);
		
	/**
	 *  查找
	 *  
	 * @param userId
	 * @param yyyymmdd
	 */
	public EmpAttendRecord findByUserAndDate(String userId, String yyyymmdd);

}
