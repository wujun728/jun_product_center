package com.doroodo.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.SyDemoDatetree;
import com.doroodo.sys.service.SyDemoDatetreeService;
import com.doroodo.util.SmarteUntil;


@Service("syDemoDatetreeService")
public class SyDemoDatetreeServiceImpl extends BaseServiceImpl<SyDemoDatetree>  implements SyDemoDatetreeService {
	
	public DataGrid treeGrid(int page, int rows, String searchName,
			String searchKey,String year) {
		DataGrid dg = new DataGrid();
		String hql = "from SyDemoDatetree t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyDemoDatetree> l=null;
		if(rows==0 &&page==0){
			l = this.getDao().find(hql, params);
		}else{
			l = this.getDao().find(hql, params,page, rows);
		}
		dg.setTotal(this.getDao().count(totalHql, params));
		dg.setRows(listByYear(year));
		dg.setModelName("syDemoDatetree");
		return dg;
	}
	
	
	
	public List<SyDemoDatetree> listByYear(String year) {
		List<SyDemoDatetree> l=new ArrayList();
		SyDemoDatetree sp=new SyDemoDatetree();
		sp.setDoroodoDate(year+"年");		//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改sp.setDoroodoDate(year+"年");
		sp.setId(-Integer.parseInt(year));
		sp.setState("closed");
		l.add(sp);
		return l;
	}
	
	
	private List<SyDemoDatetree> queryTree(String begintime,String endtime){
		String hql = "from SyDemoDatetree t where t.doroodoDate>='"+begintime+"'and t.doroodoDate<='"+endtime+"'";//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改
		List<SyDemoDatetree> l = this.getDao().find(hql);
		System.out.println(l.size()+"-----数据库查询出的list的长度");
		return l;
	}
	
	public int queryCount(String begintime,String endtime){
		//用到日期树时，请将下方的sql语句中的表对象名该为你所操作的表的对象，所以来的时间字段也需要做对应的更改
		String sql = "select count(*) from SyDemoDatetree t where t.doroodoDate>='"+begintime+"'and t.doroodoDate<='"+endtime+"'";
		List<SyDemoDatetree> l = this.getDao().find(sql);      
	    return l.size();  
	}
	
	private List<SyDemoDatetree> getTreeList(String [] arr){
		List<SyDemoDatetree> l_=new ArrayList();
		String y = "";
		String m = "";
		String w = "";
		String datestr = "";
		String db = "";
		String de = "";
		List<SyDemoDatetree> l =new ArrayList();
		if(arr.length==3){
			y = arr[0];
			m = arr[1];
			w = arr[2];
			try {
				datestr = SmarteUntil.printfWeeks(w, m, y);
				String [] arr2 = datestr.split("!");
				l = queryTree(arr2[0], arr2[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			y = arr[0];
		}
		return l;
	}
	
	
	
	public List<SyDemoDatetree> listByDate(String year) {
		String[] strs=year.split(":");
		year=strs[0];
		List<SyDemoDatetree> l=new ArrayList();
		switch(strs.length){
			case 1:
				for(int i=1;i<=12;i++){
					SyDemoDatetree sp_=new SyDemoDatetree();
					sp_.setDoroodoDate(year+"年"+i+"月");								//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改sp_.setDatetimes(year+"年"+i+"月");
					sp_.setId(-Integer.parseInt(year+i));
					sp_.setState("closed");
					l.add(sp_);
				}
				return l;			
			case 2:
				int k = 0;
				String datestr = "";
				String [] arr  = {};
				for(int i=1;i<=4;i++){
					SyDemoDatetree sp_=new SyDemoDatetree();
					sp_.setDoroodoDate(year+"年"+strs[1]+"月第"+i+"周");									//DoroodoDate字段为日期字段，数据库默认日期字段名为DoroodoDate，也可根据实际情况进行更改sp_.setDatetimes(year+"年"+strs[1]+"月第"+i+"周");
					sp_.setId(-Integer.parseInt(year+strs[1]+i+k++));
					try {
						datestr =  SmarteUntil.printfWeeks(String.valueOf(i), strs[1], strs[0]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					arr = datestr.split("!");
					if(queryCount(arr[0],arr[1])>0){
						sp_.setState("closed");
					}else{
						sp_.setState("open");
					}
					l.add(sp_);
				}
				return l;
			case 3:
					return getTreeList(strs);
		}
		return l;
	}
	
}