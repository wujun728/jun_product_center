package sy.service.app;

import java.util.List;

import sy.model.app.MedicalReportAttr;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 体检报告的属性业务逻辑
 * 
 */
public interface MedicalReportAttrServiceI extends
		BaseServiceI<MedicalReportAttr> {

	 /**
	 * 获得属性，或者combotree
	 *
	 * @return
	 */
	 public List<MedicalReportAttr> getMainMenu(HqlFilter hqlFilter);
	
	/**
	 * 获得表数据treeGrid
	 * 
	 * @return
	 */
	public List<MedicalReportAttr> getTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新属性
	 */
	public void updateMedicalReportAttr(MedicalReportAttr MedicalReportAttr);

	/**
	 * 查找属性
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<MedicalReportAttr> findMedicalReportAttrByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找属性
	 */
	public List<MedicalReportAttr> findMedicalReportAttrByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计属性
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countMedicalReportAttrByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个属性
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveMedicalReportAttr(MedicalReportAttr ustomerInfo,
			String userId);

	// public List<MedicalReportAttr> findByFilter(HqlFilter hqlFilter,int
	// userId);
	// public Long countByFilter(HqlFilter hqlFilter,int userId) ;
}
