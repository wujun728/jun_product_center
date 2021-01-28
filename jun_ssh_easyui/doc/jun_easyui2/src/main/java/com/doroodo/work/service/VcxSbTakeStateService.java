package com.doroodo.work.service;

import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface VcxSbTakeStateService extends BaseService<VcxSbTakeState> {
	public DataGrid dataGridk(int page,int rows, String searchName, String searchKey);
}
