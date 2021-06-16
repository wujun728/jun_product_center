package sy.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoRole;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoRoleService;

/**
 * DemoRoleServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoRoleService")
public class DemoRoleServiceImpl extends BaseServiceImpl<DemoRole, Long> implements DemoRoleService {

	@Resource(name = "demoRoleDao")
	@Override
	public void setDao(BaseDao<DemoRole, Long> dao) {
		super.dao = dao;
	}

}
