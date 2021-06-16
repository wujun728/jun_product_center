package sy.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoCompany;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoCompanyService;

/**
 * DemoCompanyServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoCompanyService")
public class DemoCompanyServiceImpl extends BaseServiceImpl<DemoCompany, Long> implements DemoCompanyService {

	@Resource(name = "demoCompanyDao")
	@Override
	public void setDao(BaseDao<DemoCompany, Long> dao) {
		super.dao = dao;
	}

}
