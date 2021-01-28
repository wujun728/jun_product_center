package sy.service.app;

import java.util.List;

import sy.model.app.DrugSpecInfo;
import sy.model.app.MyDrugSpecInfo;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 药品信息部门业务逻辑
 * 
 */
public interface DrugSpecInfoServiceI extends BaseServiceI<DrugSpecInfo> {

	/**
	 * 更新药品信息
	 */
	public void updateDrugSpecInfo(DrugSpecInfo DrugSpecInfo);

	/**
	 * 查找药品信息
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugSpecInfo> findDrugSpecInfoByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找药品信息
	 */
	public List<DrugSpecInfo> findDrugSpecInfoByFilter(HqlFilter hqlFilter);

	/**
	 * 统计药品信息
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countDrugSpecInfoByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个药品信息
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveDrugSpecInfo(DrugSpecInfo ustomerInfo, String userId);

	public List<MyDrugSpecInfo> findMyDrugTimesByFilter(HqlFilter hqlFilter, int page,
			int rows);

	public Long countMyDrugTimesByFilter(HqlFilter hqlFilter);

}
