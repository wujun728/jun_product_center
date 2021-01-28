package com.doroodo.sys.service.impl;

import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("syFileService")
public class SyFileServiceImpl extends BaseServiceImpl<SyFile>  implements SyFileService {
	
	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey, String where) {
		DataGrid dg = new DataGrid();
		String hql = "from SyFile t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
			hql+=" and t."+where;
		}else{
			hql+="where t."+where;
		}
		String totalHql = "select count(*) " + hql;
		List<SyFile> l =  null;
		if(rows==0 &&page==0){
			l = this.getDao().find(hql, params);
		}else{
			l = this.getDao().find(hql, params,page, rows);
		}
		dg.setTotal(this.getDao().count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syFile");
		return dg;
	}

	public void deleteByFileIds(String fileIds) {
		String[] fileIds_=fileIds.split(",");
		for(int i=0;i<fileIds_.length;i++)
		{
			String fileId=fileIds_[i];
			if(!fileId.isEmpty()){
				SyFile syFile=new SyFile();
				syFile.setFileid(fileId);
				List<SyFile> syFiles=this.get(syFile);
				for(int z=0;z<syFiles.size();z++){
					this.getDao().delete(syFiles.get(z));
				}
			}
		}
	}
}