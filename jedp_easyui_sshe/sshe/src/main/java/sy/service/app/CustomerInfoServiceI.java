package sy.service.app;

import java.util.List;

import sy.model.app.CustomerInfo;
import sy.model.base.Syrole;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
public interface CustomerInfoServiceI extends BaseServiceI<CustomerInfo> {

//	/**
//	 * 获得客户部门树，或者combotree
//	 * 
//	 * @return
//	 */
//	public List<CustomerInfo> getMainMenu(HqlFilter hqlFilter);
//
//	/**
//	 * 获得客户部门treeGrid
//	 * 
//	 * @return
//	 */
//	public List<CustomerInfo> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新客户
	 */
	public void updateCustomerInfo(CustomerInfo CustomerInfo);
	
 
	/**
	 * 查找客户
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<CustomerInfo> findCustomerInfoByFilter(HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找客户
	 */
	public List<CustomerInfo> findCustomerInfoByFilter(HqlFilter hqlFilter);

	/**
	 * 统计客户
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countCustomerInfoByFilter(HqlFilter hqlFilter);
	
	/**
	 * 添加一个客户
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveCustomerInfo(CustomerInfo ustomerInfo, String userId);
	/**
	 * 没有关联用户的客户查询 ， 适用于管理员
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public Long countCustomerInfoWithoutUserCondByFilter(HqlFilter hqlFilter);
	
	/**
	 * 没有关联用户的客户查询 ， 适用于管理员
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<CustomerInfo> findCustomerInfoWithoutUserCondByFilter(
			HqlFilter hqlFilter, int page, int rows);
	
//	public List<CustomerInfo> findByFilter(HqlFilter hqlFilter,int userId);
//	public Long countByFilter(HqlFilter hqlFilter,int userId) ;
}
