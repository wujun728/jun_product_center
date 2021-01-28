package com.doroodo.sys.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
@Service("syDesignService")
public class SyDesignServiceImpl extends BaseServiceImpl<SyDesign>  implements SyDesignService {
	
	public List<SyDesign> list() {
		String hql = "from SyDesign t ";
		List<SyDesign> l = this.getDao().find(hql);
		return l;
	}

	public List DbList() {
		String sql ="";
		return this.getDao().sql(sql);
	}

}