package sy.service.app;

import java.util.List;

import sy.model.app.PhysicalTypeDef;
import sy.model.base.SysDef;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用户业务
 * 
 * @author Wujun
 * 
 */
public interface PhysicalTypeDefServiceI extends BaseServiceI<PhysicalTypeDef> {
	
	public PhysicalTypeDef getPhysicalTypeDefById(int sysCode) ;
	
	/**
	 * 获得所有的类型数据， 缓存起来
	 * @return
	 */
	public List<PhysicalTypeDef> getAllList();
	
	/**
	 * 获得字典表数据树，或者combotree(只查找菜单类型的节点)
	 * 
	 * @return
	 */
	public List<PhysicalTypeDef> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得字典表数据treeGrid
	 * 
	 * @return
	 */
	public List<PhysicalTypeDef> PhysicalTypeDefTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新字典表数据
	 */
	public void updatePhysicalTypeDef(PhysicalTypeDef PhysicalTypeDef);

	/**
	 * 保存一个字典表数据
	 * 
	 * @param PhysicalTypeDef
	 * @param userId
	 * @return
	 */
	public void savePhysicalTypeDef(PhysicalTypeDef PhysicalTypeDef, String userId);

	List<PhysicalTypeDef> getAllNoCacheList();
}
