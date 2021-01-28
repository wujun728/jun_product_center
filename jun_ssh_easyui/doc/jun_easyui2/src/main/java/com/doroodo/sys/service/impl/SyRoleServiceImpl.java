package com.doroodo.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.DataGrid;
import com.doroodo.sys.model.SyRole;
import com.doroodo.sys.service.SyRoleService;
@Service("SyRoleService")
public class SyRoleServiceImpl implements SyRoleService{
	@Autowired
	private BaseDao<SyRole> syRoleDao;
	
	public void saveOrUpdate(SyRole syRole) {
		if(syRole==null)return;
		if(syRole.getId()==null){
			syRole.setRoleid(gId());
		}else{
			String hql = "from SyRole t ";
			Map<String, Object> params = new HashMap<String, Object>();
			hql+="where t.roleid = :roleid";
			params.put("roleid", syRole.getRoleid());
			SyRole syRole_ = syRoleDao.get(hql, params);
			if(syRole_==null) return;
			syRole_.setOperatemap(syRole.getOperatemap());
			syRole_.setRoleinfo(syRole.getRoleinfo());
			syRole_.setRolename(syRole.getRolename());
			syRole = syRole_;
		}
		syRoleDao.saveOrUpdate(syRole);
	}
	
	
	private String gId(){
		String hql="select max(t.id) from SyRole t";
		return "sy_role_"+(syRoleDao.get_(hql)+1);
	}

	public void delete(String ids) {
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			SyRole SyRole = syRoleDao.get(SyRole.class, Integer.parseInt(ids_[i]));
			if(SyRole!=null){
				//if(!(SyRole.getRoleid().equalsIgnoreCase("sy_organ_1")||SyRole.getRoleid().equalsIgnoreCase("sy_organ_0"))){
					syRoleDao.delete(SyRole);
				//}
			}
		}
	}


	public DataGrid dataGrid(int page, int rows,
			String searchName, String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyRole t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key ";
			params.put("key", "%%" + searchKey.trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyRole> l =  null;
		if(rows==0 &&page==0){
			l = syRoleDao.find(hql, params);
		}else{
			l = syRoleDao.find(hql, params,page, rows);
		}
		dg.setTotal(syRoleDao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syRole");
		return dg;
	}


	public SyRole getBySyRoleName(String rolename) {
		if(rolename.trim()=="") return null;
		String hql = "from SyRole t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.rolename = :rolename";
		params.put("rolename", rolename);
		return syRoleDao.get(hql, params);
	}
	
	public List<SyRole> get(SyRole syRole) {
		String hql = "from SyRole t where";
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < syRole.getClass().getMethods().length; i++) {
			Method f = syRole.getClass().getMethods()[i];
			if (f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0 
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				String fieldResult;
				String fieldName="";
				try {
					fieldResult = f.invoke(syRole, null) == null ? "" : f.invoke(syRole, null).toString();
					for (int j = 0; j < syRole.getClass().getDeclaredFields().length; j++) {
						String fieldName_=syRole.getClass().getDeclaredFields()[j].getName();
						if(fieldName_.equalsIgnoreCase(f.getName().substring(3))){
							fieldName = fieldName_;
							break;
						}
					}
					
					if( !fieldResult.trim().equals("") && !fieldName.trim().isEmpty()){
						if("java.lang.integer".equalsIgnoreCase(f.getReturnType().getName())){
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, Integer.parseInt(fieldResult));
						}else{
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, fieldResult);
						}
					}
					
					System.out.println("返回字段："+fieldName+"  结果:"+fieldResult);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if(hql.endsWith("and")){
			hql = hql.substring(0, hql.length()-3);
		}
		List<SyRole> l = syRoleDao.find(hql, params);
		return l;
	}
}
