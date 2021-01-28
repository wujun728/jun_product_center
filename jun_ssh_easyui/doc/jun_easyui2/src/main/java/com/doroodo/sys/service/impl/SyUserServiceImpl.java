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
import com.doroodo.sys.model.SyOrgan;
import com.doroodo.sys.model.SyRole;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyOrganService;
import com.doroodo.sys.service.SyRoleService;
import com.doroodo.sys.service.SyUserService;
@Service("syUserService")
public class SyUserServiceImpl implements SyUserService {
	@Autowired
	private BaseDao<SyUser> syUserDao;
	@Autowired
	private BaseDao<SyOrgan> syOrganDao;
	@Autowired
	private BaseDao<SyRole> syRoleDao;
	@Autowired
	private SyRoleService syRoleService;
	@Autowired
	private SyOrganService syOrganService;
	
	public DataGrid dataGrid(SyOrgan syOrgan,int page, int rows, String searchName,
			String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyUser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if( !searchName.trim().equals("") ){
			hql+="where t."+searchName+" like :key and t.routeid like :organid";
			params.put("key", "%%" + searchKey.trim() + "%%");
			params.put("organid", "%%" +syOrgan.getOrganid().trim()+ "%%");
		}else{
			hql+="where t.routeid like :organid";
			params.put("organid", "%%" +syOrgan.getOrganid().trim()+ "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyUser> l =  null;
		if(rows==0 &&page==0){
			l = syUserDao.find(hql, params);
		}else{
			l = syUserDao.find(hql, params,page, rows);
		}
		dg.setTotal(syUserDao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syUser");
		return dg;
	}
	
	public void saveOrUpdate(SyUser syUser) {
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.organid = :organid";
		params.put("organid", syUser.getOrganid());
		SyOrgan syOrgan = syOrganDao.get(hql, params);
		syUser.setRouteid(syOrgan.getRouteid());
		syUser.setRoutename(syOrgan.getRoutename());
		syUserDao.saveOrUpdate(syUser);
	}
	
	public void delete(String ids) {
		String[] ids_=ids.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			SyUser syUser =syUserDao.get(SyUser.class, Integer.parseInt(ids_[i]));
			syUserDao.delete(syUser);
		}
		
	}

	public List<SyUser> list() {
		String hql = "from SyUser t ";
		List<SyUser> l = syUserDao.find(hql);
		return l;
	}

	public void updateRole(String userIds, String rolename) {
		String[] ids_=userIds.split(",");
		for(int i=0;i<ids_.length;i++)
		{
			SyUser syUser =syUserDao.get(SyUser.class, Integer.parseInt(ids_[i]));
			syUser.setRolename(rolename.substring(0,rolename.length()-1));
			syUserDao.saveOrUpdate(syUser);
		}
	}

	public SyUser login(SyUser syUser) {
		if(syUser==null) return null;
		if(syUser.getUserid()==null) return null;
		if(syUser.getPassword()==null) return null;
		String hql = "from SyUser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.userid = :userid and t.password = :password ";
		params.put("userid", syUser.getUserid());
		params.put("password", syUser.getPassword());
		SyUser syUser_ =syUserDao.get(hql, params);
		return syUser_;
	}

	public SyRole getRoleByUserId(String userid) {
		SyUser syUser_ =getByUserId(userid);
		return syRoleService.getBySyRoleName(syUser_.getRolename());
	}

	public SyUser getByUserId(String userid) {
		String hql = "from SyUser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql+="where t.userid = :userid ";
		params.put("userid", userid);
		return syUserDao.get(hql, params);
	}

	public List<SyUser> get(SyUser syUser) {
		String hql = "from SyUser t where";
		Map<String, Object> params = new HashMap<String, Object>();
		List<SyUser> l = null;
		for (int i = 0; i < syUser.getClass().getMethods().length; i++) {
			Method f = syUser.getClass().getMethods()[i];
			if (f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0 
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				String fieldResult;
				String fieldName="";
				try {
					fieldResult = f.invoke(syUser, null) == null ? "" : f.invoke(syUser, null).toString();
					for (int j = 0; j < syUser.getClass().getDeclaredFields().length; j++) {
						String fieldName_=syUser.getClass().getDeclaredFields()[j].getName();
						if(fieldName_.equalsIgnoreCase(f.getName().substring(3))){
							fieldName = fieldName_;
							break;
						}
					}
					
					if( !fieldResult.trim().equals("")  && !fieldName.trim().isEmpty()){
						if("java.lang.integer".equalsIgnoreCase(f.getReturnType().getName())){
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, Integer.parseInt(fieldResult));
						}else{
							hql+=" t."+fieldName+" = :"+fieldName+" and";
							params.put(fieldName, fieldResult);
						}
					}
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
		try{
			l = syUserDao.find(hql, params);
		}catch(Exception e){
			e.toString();
		}
		return l;
	}

}
