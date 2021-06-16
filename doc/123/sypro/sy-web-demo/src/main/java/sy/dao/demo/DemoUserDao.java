package sy.dao.demo;

import java.util.Map;

import org.nutz.dao.entity.Record;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoUser;
import sy.util.base.QueryFilter;

/**
 * DemoUserDao接口
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
public interface DemoUserDao extends BaseDao<DemoUser, Long> {

	Map<String, Object> getBySql(Long id);

	Record getBySql2(QueryFilter filter);

	Long countBySql(QueryFilter filter);

}