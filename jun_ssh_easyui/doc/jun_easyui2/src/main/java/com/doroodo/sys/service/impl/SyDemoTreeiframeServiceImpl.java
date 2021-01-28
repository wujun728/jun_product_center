package com.doroodo.sys.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.*;
import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;

@Service("syDemoTreeiframeService")
public class SyDemoTreeiframeServiceImpl extends BaseServiceImpl<SyDemoTreeiframe> implements SyDemoTreeiframeService {
	@Autowired
	private BaseDao<SyDemoTreeiframe> syDemoTreeiframeDao;
	private final int SYSTREEID=-1000000;
	public DataGrid dataGrid(int page, int rows, String searchName,
			String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyDemoTreeiframe t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key";
			params.put("key", "%%" + searchKey.trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyDemoTreeiframe> l=null;
		if(rows==0 &&page==0){
			l = syDemoTreeiframeDao.find(hql, params);
		}else{
			l = syDemoTreeiframeDao.find(hql, params,page, rows);
		}
		dg.setTotal(syDemoTreeiframeDao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syDemoTreeiframe");
		return dg;
	}
	

	public void saveOrUpdate(SyDemoTreeiframe syDemoTreeiframe) {
		boolean isSave=true;
		String routeid="";
		SyDemoTreeiframe p=new SyDemoTreeiframe();
		if(syDemoTreeiframe.getPid()!=null){
			p.setId(syDemoTreeiframe.getPid());
			List<SyDemoTreeiframe> SyDemoTreeiframes=this.get(p);
			if(syDemoTreeiframe.getId()!=null){
				isSave=false;
			}
			if(SyDemoTreeiframes.size()==1){
				p=SyDemoTreeiframes.get(0);
				routeid=p.getRouteId();
				String routename=p.getRouteName();
				if(p.getRouteId()==null){routeid=p.getId().toString();}
				if(p.getRouteName()==null){routename=p.getName().toString();}//需要修改
				if(!isSave){//编辑
					syDemoTreeiframe.setRouteId(routeid+"/"+syDemoTreeiframe.getId());
				}
				syDemoTreeiframe.setRouteName(routename+"/"+syDemoTreeiframe.getName());//需要修改
			}
		}else{
			syDemoTreeiframe.setPid(SYSTREEID);
		}
		syDemoTreeiframeDao.saveOrUpdate(syDemoTreeiframe);
		if(isSave){//新建
			if(routeid==""){
				syDemoTreeiframe.setRouteName(syDemoTreeiframe.getName());//需要修改
				syDemoTreeiframe.setRouteId(syDemoTreeiframe.getId().toString());
			}else{
				syDemoTreeiframe.setRouteId(routeid+"/"+syDemoTreeiframe.getId());
			}
			syDemoTreeiframeDao.saveOrUpdate(syDemoTreeiframe);
		}
		setChildren(syDemoTreeiframe);
	}
	
	public void delete(String ids) {
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			int id=Integer.parseInt(ids_[i]);
			syDemoTreeiframeDao.delete(syDemoTreeiframeDao.get(SyDemoTreeiframe.class, id));
			SyDemoTreeiframe syDemoTreeiframe =new SyDemoTreeiframe();
			syDemoTreeiframe.setId(id);
			deleteChildren(syDemoTreeiframe);
		}
		
	}

	public List<SyDemoTreeiframe> listAll() {
		String hql = "from SyDemoTreeiframe t ";
		List<SyDemoTreeiframe> l = syDemoTreeiframeDao.find(hql);
		return l;
	}
	
	public List<Tree> tree(String id) {
		String hql = "from SyDemoTreeiframe t ";
		Map<String, Object> params = new HashMap<String, Object>();
		SyDemoTreeiframe syDemoTreeiframe=null;
		if(id==null || id.trim().isEmpty()){
			hql+="where t.pid = :id";
			params.put("id", SYSTREEID);
		}
		else{
			hql+="where t.pid = :id";
			params.put("id", Integer.parseInt(id.trim()));
		}
		List<SyDemoTreeiframe> SyDemoTreeiframes=null;
		try{
			SyDemoTreeiframes=syDemoTreeiframeDao.find(hql,params);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(SyDemoTreeiframes.size()<1) return null;
		List<Tree> list=new ArrayList<Tree>();
		for(int i=0;i<SyDemoTreeiframes.size();i++){
			syDemoTreeiframe=SyDemoTreeiframes.get(i);
			Tree tree=new Tree();
			tree.setId(syDemoTreeiframe.getId().toString());
			tree.setPid(syDemoTreeiframe.getPid().toString());
			tree.setText(syDemoTreeiframe.getName().toString());
			if(countById(syDemoTreeiframe.getId().toString())>0){
				tree.setState("closed");
			}else{
				tree.setState("open");
			}
			list.add(tree);
		}
		return list;
	}
	
	private int countById(String id){
		String hql = "select count(*) as count from SyDemoTreeiframe t where t.pid='"+id+"'";
		return syDemoTreeiframeDao.get_(hql);
	}
	
	public void setChildren(SyDemoTreeiframe syDemoTreeiframe){
		if(syDemoTreeiframe==null)return;
		String hql = "from SyDemoTreeiframe t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.pid = :id";
		params.put("id", syDemoTreeiframe.getId());
		List<SyDemoTreeiframe> syDemoTreeiframes = syDemoTreeiframeDao.find(hql, params);
		for(int i=0;i<syDemoTreeiframes.size();i++){
			SyDemoTreeiframe syDemoTreeiframe_=syDemoTreeiframes.get(i);
			syDemoTreeiframe_.setRouteId(syDemoTreeiframe.getRouteId()+"/"+syDemoTreeiframe_.getId());
			syDemoTreeiframe_.setRouteName(syDemoTreeiframe.getRouteName()+"/"+syDemoTreeiframe_.getName());
			syDemoTreeiframeDao.saveOrUpdate(syDemoTreeiframe_);
			setChildren(syDemoTreeiframe_);
		}
	}
	
	public void deleteChildren(SyDemoTreeiframe syDemoTreeiframe){
		if(syDemoTreeiframe==null) return;
		if(syDemoTreeiframe.getId().toString().trim().isEmpty()) return;
		String hql = "from SyDemoTreeiframe t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.pid = :id";
		params.put("id", syDemoTreeiframe.getId());
		List<SyDemoTreeiframe> syDemoTreeiframes = syDemoTreeiframeDao.find(hql, params);
		for(int i=0;i<syDemoTreeiframes.size();i++){
			SyDemoTreeiframe syDemoTreeiframe_=syDemoTreeiframes.get(i);
			syDemoTreeiframeDao.delete(syDemoTreeiframe_);
			deleteChildren(syDemoTreeiframe_);
		}
	}

	public DataGrid dataGrid(
			SyDemoTreeiframe syDemoTreeiframe, int page,
			int rows, String searchName, String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyDemoTreeiframe t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key and t.routeId like :id";
			params.put("key", "%%" + searchKey.trim() + "%%");
			params.put("id","%%" +  syDemoTreeiframe.getId().toString().trim() + "%%");
		}else{
			hql+="where t.routeId like :id";
			params.put("id","%%" +  syDemoTreeiframe.getId().toString().trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyDemoTreeiframe> l =  null;
		if(rows==0 &&page==0){
			l = syDemoTreeiframeDao.find(hql, params);
		}else{
			l = syDemoTreeiframeDao.find(hql, params,page, rows);
		}
		dg.setTotal(syDemoTreeiframeDao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syDemoTreeiframe");
		return dg;
	}
}