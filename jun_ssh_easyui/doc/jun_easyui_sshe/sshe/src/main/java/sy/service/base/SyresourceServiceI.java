package sy.service.base;

import java.util.List;

import sy.model.base.Syresource;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 资源业务逻辑
 * 
 * @author Wujun
 * 
 */
public interface SyresourceServiceI extends BaseServiceI<Syresource> {

	/**
	 * 获得资源树，或者combotree(只查找菜单类型的节点)
	 * 
	 * @return
	 */
	public List<Syresource> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得资源treeGrid
	 * 
	 * @return
	 */
	public List<Syresource> resourceTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新资源
	 */
	public void updateResource(Syresource syresource);

	/**
	 * 保存一个资源
	 * 
	 * @param syresource
	 * @param userId
	 * @return
	 */
	public void saveResource(Syresource syresource, String userId);

	/**
	 * 查找符合条件的资源
	 */
	public List<Syresource> findResourceByFilter(HqlFilter hqlFilter);

}
