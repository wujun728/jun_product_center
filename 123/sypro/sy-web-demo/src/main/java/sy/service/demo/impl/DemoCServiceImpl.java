package sy.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoC;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoCService;

/**
 * DemoCServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoCService")
public class DemoCServiceImpl extends BaseServiceImpl<DemoC, Long> implements DemoCService {

	@Resource(name = "demoCDao")
	@Override
	public void setDao(BaseDao<DemoC, Long> dao) {
		super.dao = dao;
	}

}
