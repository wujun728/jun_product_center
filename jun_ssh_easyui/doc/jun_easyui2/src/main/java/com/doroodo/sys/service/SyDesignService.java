package com.doroodo.sys.service;
import java.util.List;
import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
public interface SyDesignService  extends BaseService<SyDesign> {
	public List list();
	public List DbList();
}
