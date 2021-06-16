package sy.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.demo.DemoB;
import sy.service.base.impl.BaseServiceImpl;
import sy.service.demo.DemoBService;

/**
 * DemoBServiceImpl
 *
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("demoBService")
public class DemoBServiceImpl extends BaseServiceImpl<DemoB, Long> implements DemoBService {

	@Resource(name = "demoBDao")
	@Override
	public void setDao(BaseDao<DemoB, Long> dao) {
		super.dao = dao;
	}

}
