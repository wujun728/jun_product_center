package sy.service.app;

import java.util.List;

import sy.action.OpResult;
import sy.model.app.EmpDrugUseRecord;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 员工用药记录业务逻辑
 * 
 */
public interface EmpDrugUseRecordServiceI extends
		BaseServiceI<EmpDrugUseRecord> {

	// /**
	// * 获得员工用药记录部门树，或者combotree
	// *
	// * @return
	// */
	// public List<EmpDrugUseRecord> getMainMenu(HqlFilter hqlFilter);
	//
	// /**
	// * 获得员工用药记录部门treeGrid
	// *
	// * @return
	// */
	// public List<EmpDrugUseRecord> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新员工用药记录
	 */
	public void updateEmpDrugUseRecord(EmpDrugUseRecord EmpDrugUseRecord);

	/**
	 * 查找员工用药记录
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpDrugUseRecord> findEmpDrugUseRecordByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找员工用药记录
	 */
	public List<EmpDrugUseRecord> findEmpDrugUseRecordByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计员工用药记录
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpDrugUseRecordByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个员工用药记录
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpDrugUseRecord(EmpDrugUseRecord ustomerInfo, String userId);

	public void saveEmpDrugUseRecord(List<EmpDrugUseRecord> empDrugUseRecordList,
			String userId);
		
	public OpResult saveEmpDrugUseRecord2(List<EmpDrugUseRecord> empDrugUseRecordList,
			String userId);
}
