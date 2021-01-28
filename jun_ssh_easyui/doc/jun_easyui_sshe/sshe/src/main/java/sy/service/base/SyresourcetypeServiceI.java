package sy.service.base;

import java.util.List;

import sy.model.base.Syresourcetype;
import sy.service.BaseServiceI;

/**
 * 资源类型业务
 * 
 * @author Wujun
 * 
 */
public interface SyresourcetypeServiceI extends BaseServiceI<Syresourcetype> {

	/**
	 * 获取所有资源类型
	 */
	public List<Syresourcetype> findResourcetype();

}
