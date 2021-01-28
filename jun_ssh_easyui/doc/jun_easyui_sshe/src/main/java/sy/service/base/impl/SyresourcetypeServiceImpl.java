package sy.service.base.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import sy.model.base.Syresourcetype;
import sy.service.base.SyresourcetypeServiceI;
import sy.service.impl.BaseServiceImpl;

/**
 * 资源类型业务逻辑
 * 
 * @author Wujun
 * 
 */
@Service
public class SyresourcetypeServiceImpl extends BaseServiceImpl<Syresourcetype> implements SyresourcetypeServiceI {

	@Override
	@Cacheable(value = "SyresourcetypeServiceCache", key = "'SyresourcetypeList'")
	public List<Syresourcetype> findResourcetype() {
		return find();
	}

}
