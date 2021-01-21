package sy.service.app;

import java.util.List;

import sy.model.app.EmpIllHis;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 员工个人历史病史业务逻辑
 * 
 */
public interface EmpIllHisServiceI extends BaseServiceI<EmpIllHis> {

	/**
	 * 更新员工个人历史病史
	 */
	public void updateEmpIllHis(EmpIllHis EmpIllHis);

	/**
	 * 查找员工个人历史病史
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpIllHis> findEmpIllHisByFilter(HqlFilter hqlFilter, int page,
			int rows);

	/**
	 * 查找员工个人历史病史
	 */
	public List<EmpIllHis> findEmpIllHisByFilter(HqlFilter hqlFilter);

	/**
	 * 统计员工个人历史病史
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpIllHisByFilter(HqlFilter hqlFilter);
	
	/**
	 * 添加一个员工个人历史病史
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpIllHis(EmpIllHis ustomerInfo, String userId);

}
