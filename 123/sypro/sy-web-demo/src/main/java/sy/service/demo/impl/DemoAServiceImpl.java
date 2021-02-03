package sy.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoA;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoAService;

/**
 * DemoAServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoAService")
public class DemoAServiceImpl extends BaseServiceImpl<DemoA, Long> implements DemoAService {

	@Resource(name = "demoADao")
	@Override
	public void setDao(BaseDao<DemoA, Long> dao) {
		super.dao = dao;
	}

}
