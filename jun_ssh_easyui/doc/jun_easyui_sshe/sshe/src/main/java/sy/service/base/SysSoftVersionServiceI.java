package sy.service.base;

import java.util.List;

import sy.model.base.SysSoftVersion;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 系统版本
 * 
 * @author Wujun
 * 
 */
public interface SysSoftVersionServiceI extends BaseServiceI<SysSoftVersion> {

	/**
	 * 更新系统版本
	 */
	public void updateSysSoftVersion(SysSoftVersion SysSoftVersion);

	/**
	 * 查找系统版本
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SysSoftVersion> findSysSoftVersionByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找系统版本
	 */
	public List<SysSoftVersion> findSysSoftVersionByFilter(HqlFilter hqlFilter);

	/**
	 * 统计系统版本
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countSysSoftVersionByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个系统版本
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveSysSoftVersion(SysSoftVersion SysSoftVersion, String userId);

}
