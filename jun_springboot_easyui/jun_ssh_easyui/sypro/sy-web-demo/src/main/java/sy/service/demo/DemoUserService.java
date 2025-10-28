package sy.service.demo;

import java.util.Map;

import org.nutz.dao.entity.Record;

import sy.model.demo.DemoUser;
import sy.service.base.BaseService;
import sy.util.base.QueryFilter;

/**
 * DemoUserService
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
public interface DemoUserService extends BaseService<DemoUser, Long> {

	/**
	 * 通过sql获得对象信息
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> getBySql(Long id);

	/**
	 * 通过过滤返回记录，内部演示了纯sql的调用
	 * 
	 * @param filter
	 * @return
	 */
	Record getBySql2(QueryFilter filter);

	/**
	 * 统计个数
	 * 
	 * @param filter
	 * @return
	 */
	Long countBySql(QueryFilter filter);

}