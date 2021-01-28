package sy.service.app;

import java.util.List;

import sy.model.app.EmpDiagnoseRecord;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 员工就诊记录业务逻辑
 * 
 */
public interface EmpDiagnoseRecordServiceI extends
		BaseServiceI<EmpDiagnoseRecord> {

	
	/**
	 * 更新员工就诊记录
	 */
	public void updateEmpDiagnoseRecord(EmpDiagnoseRecord EmpDiagnoseRecord);

	/**
	 * 查找员工就诊记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpDiagnoseRecord> findEmpDiagnoseRecordByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找员工就诊记录
	 */
	public List<EmpDiagnoseRecord> findEmpDiagnoseRecordByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计员工就诊记录
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpDiagnoseRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个员工就诊记录
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpDiagnoseRecord(EmpDiagnoseRecord ustomerInfo, String userId);

}
