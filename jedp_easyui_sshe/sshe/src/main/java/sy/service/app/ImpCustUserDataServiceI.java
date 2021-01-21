package sy.service.app;

import java.util.List;

import sy.model.app.ImpCustUserData;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 导入员工数据业务逻辑
 * 
 */
public interface ImpCustUserDataServiceI extends BaseServiceI<ImpCustUserData> {

	/**
	 * 获得导入员工数据树，或者combotree
	 * 
	 * @return
	 */
	public List<ImpCustUserData> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 更新导入员工数据
	 */
	public void updateImpCustUserData(ImpCustUserData ImpCustUserData);

	/**
	 * 查找符合条件的导入员工数据
	 */
	public List<ImpCustUserData> findByFilter(HqlFilter hqlFilter);

	/**
	 * 保存员工数据
	 * 
	 * @param ImpCustUserData
	 * @param userId
	 */
	public void saveImpCustUserData(ImpCustUserData ImpCustUserData,
			String userId);

}
