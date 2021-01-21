package sy.service.app;

import java.util.List;

import sy.model.app.EmpEpidemicRecord;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 流行病业务逻辑
 * 
 */
public interface EmpEpidemicRecordServiceI extends
		BaseServiceI<EmpEpidemicRecord> {

	/**
	 * 更新流行病
	 */
	public void updateEmpEpidemicRecord(EmpEpidemicRecord EmpEpidemicRecord);

	/**
	 * 查找流行病
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpEpidemicRecord> findEmpEpidemicRecordByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找流行病
	 */
	public List<EmpEpidemicRecord> findEmpEpidemicRecordByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计流行病
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpEpidemicRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个流行病
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpEpidemicRecord(EmpEpidemicRecord ustomerInfo,
			String userId);

}
