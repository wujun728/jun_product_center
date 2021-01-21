package sy.service.base;

import java.util.List;

import sy.model.base.Syresource;
import sy.model.base.SysDef;
import sy.model.base.Syuser;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 用户业务
 * 
 * @author Wujun
 * 
 */
public interface SysDefServiceI extends BaseServiceI<SysDef> {
	
	public SysDef getSysDefById(int sysCode) ;
	
	/**
	 * 获得所有的类型数据， 缓存起来
	 * @return
	 */
	public List<SysDef> getAllList();
	
	/**
	 * 获得字典表数据树，或者combotree(只查找菜单类型的节点)
	 * 
	 * @return
	 */
	public List<SysDef> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得字典表数据treeGrid
	 * 
	 * @return
	 */
	public List<SysDef> sysDefTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新字典表数据
	 */
	public void updateSysDef(SysDef sysDef);

	/**
	 * 保存一个字典表数据
	 * 
	 * @param SysDef
	 * @param userId
	 * @return
	 */
	public void saveSysDef(SysDef sysDef, String userId);

	List<SysDef> getAllNoCacheList();

}
