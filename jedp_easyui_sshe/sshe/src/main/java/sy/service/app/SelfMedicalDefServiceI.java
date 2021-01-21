package sy.service.app;

import java.util.List;

import sy.model.app.SelfMedicalDef;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 自定义部门业务逻辑
 * 
 */
public interface SelfMedicalDefServiceI extends BaseServiceI<SelfMedicalDef> {

	/**
	 * 更新自定义
	 */
	public void updateSelfMedicalDef(SelfMedicalDef SelfMedicalDef);

	/**
	 * 查找自定义
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SelfMedicalDef> findSelfMedicalDefByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找自定义
	 */
	public List<SelfMedicalDef> findSelfMedicalDefByFilter(HqlFilter hqlFilter);

	/**
	 * 统计自定义
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countSelfMedicalDefByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个自定义
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveSelfMedicalDef(SelfMedicalDef ustomerInfo, String userId);

}
