package sy.service.app;

import java.util.List;

import sy.model.app.CustDept;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
public interface CustDeptServiceI extends BaseServiceI<CustDept> {

	/**
	 * 获得客户部门树，或者combotree
	 * 
	 * @return
	 */
	public List<CustDept> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得客户部门treeGrid
	 * 
	 * @return
	 */
	public List<CustDept> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新客户部门
	 */
	public void updateDept(CustDept CustDept);

	/**
	 * 保存一个客户部门
	 * 
	 * @param CustDept
	 * @param userId
	 * @return
	 */
	public void saveDept(CustDept CustDept, String userId);

	/**
	 * 查找符合条件的客户部门
	 */
	public List<CustDept> findDeptByFilter(HqlFilter hqlFilter);

}
