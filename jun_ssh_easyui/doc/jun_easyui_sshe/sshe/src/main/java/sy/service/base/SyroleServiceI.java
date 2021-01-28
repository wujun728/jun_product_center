package sy.service.base;

import java.util.List;

import sy.model.base.Syrole;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 角色业务
 * 
 * @author Wujun
 * 
 */
public interface SyroleServiceI extends BaseServiceI<Syrole> {

	/**
	 * 查找角色
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Syrole> findRoleByFilter(HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找角色
	 */
	public List<Syrole> findRoleByFilter(HqlFilter hqlFilter);

	/**
	 * 统计角色
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countRoleByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个角色
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveRole(Syrole syrole, String userId);

	/**
	 * 为角色授权
	 * 
	 * @param id
	 *            角色ID
	 * @param resourceIds
	 *            资源IDS
	 */
	public void grant(String id, String resourceIds);

	/**
	 * 关联角色用户
	 * @param id
	 * @param userids
	 */
	public void relationRoleUser(String id, String userids);

}
