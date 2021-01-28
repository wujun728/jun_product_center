package sy.service.app;

import java.util.List;

import sy.model.app.DrugTimes;
import sy.model.app.MyDrugTimes;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 药品信息部门业务逻辑
 * 
 */
public interface DrugTimesServiceI extends BaseServiceI<DrugTimes> {

	/**
	 * 更新药品信息
	 */
	public void updateDrugTimes(DrugTimes DrugTimes);

	/**
	 * 查找药品信息
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<DrugTimes> findDrugTimesByFilter(HqlFilter hqlFilter, int page,
			int rows);

	/**
	 * 查找药品信息
	 */
	public List<DrugTimes> findDrugTimesByFilter(HqlFilter hqlFilter);

	/**
	 * 统计药品信息
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countDrugTimesByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个药品信息
	 * 
	 * @param drugTimes
	 * @param userId
	 */
	public void saveDrugTimes(DrugTimes drugTimes, String userId);

	/**
	 * 自定义查询
	 * 
	 * @param specId
	 * @param drugLotNo
	 * @return
	 */
	public DrugTimes getDrugTimesByCond(int specId, String drugLotNo,String unit);

	/**
	 * 自定义结构的查询
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countMyDrugTimesByFilter(HqlFilter hqlFilter);
	
	/**
	 * 自定义结构的查询
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<MyDrugTimes> findMyDrugTimesByFilter(HqlFilter hqlFilter,
			int page, int rows);

}
