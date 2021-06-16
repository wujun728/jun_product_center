package sy.service.aop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sy.dao.base.BaseDao;
import sy.model.aop.ControllerLog;
import sy.service.aop.ControllerLogService;
import sy.service.base.impl.BaseServiceImpl;

/**
 * ControllerLogServiceImpl
 * 
 * http://git.oschina.net/sphsyv/sypro
 *
 * 由代码生成器生成
 *
 * @author 孙宇
 *
 */
@Service("controllerLogService")
public class ControllerLogServiceImpl extends BaseServiceImpl<ControllerLog, Long> implements ControllerLogService {

	@Resource(name = "controllerLogDao")
	@Override
	public void setDao(BaseDao<ControllerLog, Long> dao) {
		super.dao = dao;
	}

}
