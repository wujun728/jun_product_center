package sy.service.base;

import java.util.List;

import sy.model.base.SysMessage;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 系统消息
 * 
 * @author Wujun
 * 
 */
public interface SysMessageServiceI extends BaseServiceI<SysMessage> {

	/**
	 * 更新系统消息
	 */
	public void updateSysMessage(SysMessage SysMessage);

	/**
	 * 查找系统消息
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SysMessage> findSysMessageByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找系统消息
	 */
	public List<SysMessage> findSysMessageByFilter(HqlFilter hqlFilter);

	/**
	 * 统计系统消息
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countSysMessageByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个系统消息
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveSysMessage(SysMessage sysMessage, String userId);

}
