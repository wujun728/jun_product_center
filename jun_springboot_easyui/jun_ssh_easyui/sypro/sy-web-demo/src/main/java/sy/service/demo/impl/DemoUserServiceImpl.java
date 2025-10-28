package sy.service.demo.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.nutz.dao.entity.Record;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.dao.demo.DemoUserDao;
import sy.model.demo.DemoUser;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoUserService;
import sy.util.base.QueryFilter;

/**
 * DemoUserServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoUserService")
public class DemoUserServiceImpl extends BaseServiceImpl<DemoUser, Long> implements DemoUserService {

	@Resource(name = "demoUserDao")
	@Override
	public void setDao(BaseDao<DemoUser, Long> dao) {
		super.dao = dao;
	}

	@Override
	public Map<String, Object> getBySql(Long id) {
		return ((DemoUserDao) dao).getBySql(id);// 自定义dao接口，基类没有提供这个接口
	}

	@Override
	public Record getBySql2(QueryFilter filter) {
		return ((DemoUserDao) dao).getBySql2(filter);
	}

	@Override
	public Long countBySql(QueryFilter filter) {
		return ((DemoUserDao) dao).countBySql(filter);
	}

}
