package sy.service.app;

import java.util.List;

import sy.model.app.EmpHis;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 员工职业史业务逻辑
 * 
 */
public interface EmpHisServiceI extends
		BaseServiceI<EmpHis> {

 
	/**
	 * 更新员工职业史
	 */
	public void updateEmpHis(EmpHis EmpHis);

	/**
	 * 查找员工职业史
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpHis> findEmpHisByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找员工职业史
	 */
	public List<EmpHis> findEmpHisByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计员工职业史
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpHisByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个员工职业史
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpHis(EmpHis ustomerInfo, String userId);

}
