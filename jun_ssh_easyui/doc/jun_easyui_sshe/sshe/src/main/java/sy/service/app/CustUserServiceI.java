package sy.service.app;

import java.util.List;

import sy.model.app.CustUser;
import sy.model.base.Syrole;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 员工部门业务逻辑
 * 
 */
public interface CustUserServiceI extends BaseServiceI<CustUser> {

	// /**
	// * 获得员工部门树，或者combotree
	// *
	// * @return
	// */
	// public List<CustUser> getMainMenu(HqlFilter hqlFilter);
	//
	// /**
	// * 获得员工部门treeGrid
	// *
	// * @return
	// */
	// public List<CustUser> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新员工
	 */
	public void updateCustUser(CustUser CustUser);

	/**
	 * 查找员工
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<CustUser> findCustUserByFilter(HqlFilter hqlFilter, int page,
			int rows);

	/**
	 * 查找员工
	 */
	public List<CustUser> findCustUserByFilter(HqlFilter hqlFilter);
	
	/**
	 * 统计员工
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countCustUserByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个员工
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveCustUser(CustUser ustomerInfo, String userId);

	/**
	 * 给员工分配部门
	 * 
	 * @param id
	 * @param organizationIds
	 */
	void grantOrganization(String id, String organizationIds);

}
