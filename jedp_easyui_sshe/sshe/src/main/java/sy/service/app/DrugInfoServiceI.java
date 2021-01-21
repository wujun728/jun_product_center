package sy.service.app;

import java.util.List;

import sy.model.app.DrugInfo;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 药品信息部门业务逻辑
 * 
 */
public interface DrugInfoServiceI extends
		BaseServiceI<DrugInfo> {

	/**
	 * 更新药品信息
	 */
	public void updateDrugInfo(DrugInfo DrugInfo);

	/**
	 * 查找药品信息
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugInfo> findDrugInfoByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找药品信息
	 */
	public List<DrugInfo> findDrugInfoByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计药品信息
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countDrugInfoByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个药品信息
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveDrugInfo(DrugInfo ustomerInfo, String userId);

	
}
