package sy.dao.demo.impl;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.entity.Record;
import org.springframework.stereotype.Repository;

import sy.dao.base.impl.BaseDaoImpl;
import sy.dao.demo.DemoUserDao;
import sy.model.demo.DemoUser;
import sy.util.base.QueryFilter;

/**
 * DemoUserDaoImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Repository("demoUserDao")
public class DemoUserDaoImpl extends BaseDaoImpl<DemoUser, Long> implements DemoUserDao {

	/**
	 * 自定义sql查询
	 */
	@Override
	public Map<String, Object> getBySql(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return super.getBySql("select * from tuser t where t.id = :id", params);
	}

	@Override
	public Record getBySql2(QueryFilter filter) {
		return super.getRecordBySql("select * from tuser t", filter);
	}

	@Override
	public Long countBySql(QueryFilter filter) {
		return super.countRecordBySql("select count(1) from tuser t", filter);
	}

}