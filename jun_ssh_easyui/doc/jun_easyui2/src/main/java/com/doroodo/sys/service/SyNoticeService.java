package com.doroodo.sys.service;
import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
public interface SyNoticeService extends BaseService<SyNotice>{
	public DataGrid dataGrid(int page,int rows, String searchName, String searchKey,String where);
}
