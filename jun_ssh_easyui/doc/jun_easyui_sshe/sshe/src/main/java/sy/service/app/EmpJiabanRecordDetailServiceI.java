package sy.service.app;

import java.util.List;

import sy.model.app.EmpJiabanRecordDetail;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 加班请假操作明细业务逻辑
 * 
 */
public interface EmpJiabanRecordDetailServiceI extends
		BaseServiceI<EmpJiabanRecordDetail> {
	
	/**
	 * 更新加班请假操作明细
	 */
	public void updateEmpJiabanRecordDetail(
			EmpJiabanRecordDetail EmpJiabanRecordDetail);

	/**
	 * 查找加班请假操作明细
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpJiabanRecordDetail> findEmpJiabanRecordDetailByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找加班请假操作明细
	 */
	public List<EmpJiabanRecordDetail> findEmpJiabanRecordDetailByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计加班请假操作明细
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpJiabanRecordDetailByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个加班请假操作明细
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpJiabanRecordDetail(EmpJiabanRecordDetail ustomerInfo,
			String userId);

}
