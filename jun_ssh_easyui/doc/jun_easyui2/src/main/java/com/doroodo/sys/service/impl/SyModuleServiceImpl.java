package com.doroodo.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.SyModule;
import com.doroodo.sys.service.SyModuleService;
@Service("syModuleService")
public class SyModuleServiceImpl extends BaseServiceImpl<SyModule>  implements SyModuleService {
	
	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyModule t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyModule> l =null;
		if(rows==0 &&page==0){
			l = this.getDao().find(hql, params);
		}else{
			l = this.getDao().find(hql, params,page, rows);
		}
		dg.setTotal(this.getDao().count(totalHql, params));
		dg.setRows(toTree(l));
		dg.setModelName("syModule");
		return dg;
	}
	
	private Comparator<SyModule> getSyModuleCompBySort(){
		Comparator<SyModule> comparator = new Comparator<SyModule>(){
			   public int compare(SyModule s1, SyModule s2) {
				   return s1.getSort()-s2.getSort();
			   }};
			   return comparator;
	}
	
	//注意，只针对2级目录
	private List<SyModule> toTree(List<SyModule> l){
		List<SyModule> l_=new ArrayList();
		for(int i=0;i<l.size();i++){
			SyModule syModule = l.get(i);
			if(syModule.getPid().equalsIgnoreCase("0")){
				List<SyModule> l_c=new ArrayList();
				for(int j=0;j<l.size();j++){
					SyModule syModule_=l.get(j);
					if(syModule_.getPid().equalsIgnoreCase(syModule.getMenuid())){
						l_c.add(syModule_);
					}
				}
				Collections.sort(l_c,getSyModuleCompBySort());
				syModule.setChildren(l_c);
				l_.add(syModule);
			}
		}
		Collections.sort(l_,getSyModuleCompBySort());
		return l_;
	}
	
	
	public List<SyModule> list() {
		String hql = "from SyModule t ";
		List<SyModule> l = this.getDao().find(hql);
		return l;
	}
	
	public void saveOrUpdate(SyModule syModule) {
		String menuid=syModule.getMenuid();
		if(!menuid.trim().isEmpty()){
			SyModule syModule_=new SyModule();
			syModule_.setPid(menuid);
			List<SyModule> syModule_s=this.get(syModule_);
			for(int i=0;i<syModule_s.size();i++){
				syModule_=syModule_s.get(i);
				syModule_.setPid(syModule.getMenuname());
				dao.saveOrUpdate(syModule_);
			}
		}
		if(syModule.getSort()==null){
			syModule.setSort(0);
		}
		syModule.setMenuid(syModule.getMenuname());
		dao.saveOrUpdate(syModule);
	}

}
