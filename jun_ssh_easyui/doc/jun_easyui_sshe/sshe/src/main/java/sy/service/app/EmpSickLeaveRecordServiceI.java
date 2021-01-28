package sy.service.app;

import java.util.List;

import sy.model.app.EmpSickLeaveRecord;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 病假管理业务逻辑
 * 
 */
public interface EmpSickLeaveRecordServiceI extends BaseServiceI<EmpSickLeaveRecord> {

	/**
	 * 更新病假管理
	 */
	public void updateEmpSickLeaveRecord(EmpSickLeaveRecord EmpSickLeaveRecord);
	
 
	/**
	 * 查找病假管理
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpSickLeaveRecord> findEmpSickLeaveRecordByFilter(HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找病假管理
	 */
	public List<EmpSickLeaveRecord> findEmpSickLeaveRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 统计病假管理
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpSickLeaveRecordByFilter(HqlFilter hqlFilter);
	
	/**
	 * 添加一个病假管理
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpSickLeaveRecord(EmpSickLeaveRecord ustomerInfo, String userId);
	
}
