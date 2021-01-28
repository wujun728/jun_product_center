package sy.service.app;

import java.util.ArrayList;
import java.util.List;

import sy.model.app.MedicalReportDef;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.Syrole;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
public interface MedicalReportDefServiceI extends
		BaseServiceI<MedicalReportDef> {

	
	 /**
	 * 获得客户部门树，或者combotree
	 *
	 * @return
	 */
	 public List<MedicalReportDef> getMainMenu(HqlFilter hqlFilter);
	//
	// /**
	// * 获得客户部门treeGrid
	// *
	// * @return
	// */
	// public List<MedicalReportDef> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新客户
	 */
	public void updateMedicalReportDef(MedicalReportDef MedicalReportDef);

	/**
	 * 查找客户
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<MedicalReportDef> findMedicalReportDefByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找客户
	 */
	public List<MedicalReportDef> findMedicalReportDefByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计客户
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countMedicalReportDefByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个客户
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveMedicalReportDef(MedicalReportDef ustomerInfo, String userId);

	// public List<MedicalReportDef> findByFilter(HqlFilter hqlFilter,int
	// userId);
	// public Long countByFilter(HqlFilter hqlFilter,int userId) ;
}
