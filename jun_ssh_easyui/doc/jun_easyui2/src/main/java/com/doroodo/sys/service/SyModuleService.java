package com.doroodo.sys.service;

import java.util.List;
import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.SyModule;

public interface SyModuleService extends BaseService<SyModule> {
	public List<SyModule> list();
}
