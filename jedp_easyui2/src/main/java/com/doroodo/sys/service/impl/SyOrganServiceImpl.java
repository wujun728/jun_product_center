package com.doroodo.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.DataGrid;
import com.doroodo.base.model.Tree;
import com.doroodo.sys.model.SyModule;
import com.doroodo.sys.model.SyOrgan;
import com.doroodo.sys.model.SyUser;
import com.doroodo.sys.service.SyOrganService;
import com.doroodo.sys.service.SyUserService;

@Service("syOrganService")
public class SyOrganServiceImpl implements SyOrganService {
	@Autowired
	private BaseDao<SyOrgan> syOrganDao;
	@Autowired
	private SyUserService syUserService;

	private final String SYSORGANID = "000000";

	public void saveOrUpdate(SyOrgan syOrgan) {
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql += "where t.organid = :organid";
		if (syOrgan.getUporganid().trim().equalsIgnoreCase("")) {
			syOrgan.setUporganid(SYSORGANID);
		}
		params.put("organid", syOrgan.getUporganid());
		SyOrgan syOrgan_p = syOrganDao.get(hql, params);
		if (syOrgan.getOrganid().trim().equalsIgnoreCase("")) {
			syOrgan.setOrganid(gId());
		}
		;
		syOrgan.setRouteid(syOrgan_p.getRouteid() + "/" + syOrgan.getOrganid());
		syOrgan.setRoutename(syOrgan_p.getRoutename() + "/"
				+ syOrgan.getOrganname());
		syOrganDao.saveOrUpdate(syOrgan);
		setChildren(syOrgan);
	}

	public void setChildren(SyOrgan syOrgan) {
		if (syOrgan == null)
			return;
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql += "where t.uporganid = :organid";
		params.put("organid", syOrgan.getOrganid());
		List<SyOrgan> syOrgans = syOrganDao.find(hql, params);
		for (int i = 0; i < syOrgans.size(); i++) {
			SyOrgan syOrgan_ = syOrgans.get(i);
			syOrgan_.setRouteid(syOrgan.getRouteid() + "/"
					+ syOrgan_.getOrganid());
			syOrgan_.setRoutename(syOrgan.getRoutename() + "/"
					+ syOrgan_.getOrganname());
			syOrganDao.saveOrUpdate(syOrgan_);
			setChildren(syOrgan_);
		}
	}

	private String gId() {
		String hql = "select max(cast(t.organid as integer)) from SyOrgan t";
		String id = String.valueOf(syOrganDao.get_(hql) + 1);
		int j = 6 - id.length();
		if (j > 0) {
			String s = "";
			for (int i = 0; i < j; i++) {
				s += "0";
			}
			id = s + id;
		}
		return id;
	}

	public void delete(String ids) {
		String[] ids_ = ids.split(",");
		for (int i = 0; i < ids_.length; i++) {
			SyOrgan syOrgan = syOrganDao.get(SyOrgan.class,
					Integer.parseInt(ids_[i]));
			try{
				deleteOrganTree(syOrgan);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void deleteOrganTree(SyOrgan syOrgan) {
		if(syOrgan ==null) return;
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
			hql += "where t.routeid like :routeid";
			params.put("routeid", "%%" + syOrgan.getRouteid().trim() + "%%");
			List<SyOrgan> syOrgans = syOrganDao.find(hql, params);
		for (int i = 0; i < syOrgans.size(); i++) {
			String organId = syOrgans.get(i).getOrganid();
			SyOrgan syOrgan_ = syOrganDao.get(SyOrgan.class,
					syOrgans.get(i).getId());
			if (syOrgan_ != null) {
				if (!(syOrgan_.getOrganid().equalsIgnoreCase(SYSORGANID))) {
					SyUser syUser = new SyUser();
					syUser.setOrganid(organId);
					List<SyUser> syUsers = syUserService.get(syUser);
					String ids = "";
					for (int j = 0; j < syUsers.size(); j++) {
						ids += syUsers.get(j).getId().toString() + ",";
					}
					if(!ids.isEmpty()){ids=ids.substring(0,ids.length()-1);syUserService.delete(ids);};
					syOrganDao.delete(syOrgan_);
				}
			}
		}
	}

	public List<Tree> tree(String id) {
		Tree tree = new Tree();
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (id == null) {
			id = SYSORGANID;
		}
		if (!id.trim().equals("")) {
			hql += "where t.organid = :organid";
			params.put("organid", id.trim());
		}
		SyOrgan syOrgan = syOrganDao.get(hql, params);
		if (syOrgan == null)
			return null;
		tree.setAttributes(tree.initAb(syOrgan.getRouteid(),
				syOrgan.getRoutename()));
		tree.setId(syOrgan.getOrganid());
		tree.setPid(syOrgan.getUporganid());
		tree.setText(syOrgan.getOrganname());
		tree.setPid(syOrgan.getUporganid());
		gTree(id, tree);
		return tree.getChildren();
	}

	private int countByOrganId(String organid) {
		String hql = "select count(*) as count from SyOrgan t where t.uporganid='"
				+ organid + "'";
		return syOrganDao.get_(hql);
	}

	public void gTree(String id, Tree t) {
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (!id.trim().equals("")) {
			hql += "where t.uporganid = :organid";
			params.put("organid", id.trim());
		}
		List<SyOrgan> syOrgans = syOrganDao.find(hql, params);
		List<Tree> list = new ArrayList<Tree>();
		for (int i = 0; i < syOrgans.size(); i++) {
			Tree tree = new Tree();
			SyOrgan syOrgan = syOrgans.get(i);
			tree.setAttributes(tree.initAb(syOrgan.getRouteid(),
					syOrgan.getRoutename()));
			tree.setId(syOrgan.getOrganid());
			tree.setPid(syOrgan.getUporganid());
			tree.setText(syOrgan.getOrganname());
			// gTree(syOrgan.getOrganid(),tree);
			if (countByOrganId(syOrgan.getOrganid()) > 0) {
				tree.setState("closed");
			} else {
				tree.setState("open");
			}
			list.add(tree);
		}
		if (list.size() == 0) {
			t.setState("open");
		}
		t.setChildren(list);
	}

	public List<Tree> gTree_(String id) {
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (!id.trim().equals("")) {
			hql += "where t.uporganid = :organid";
			params.put("organid", id.trim());
		}
		List<SyOrgan> syOrgans = syOrganDao.find(hql, params);
		List<Tree> list = new ArrayList<Tree>();
		for (int i = 0; i < syOrgans.size(); i++) {
			Tree tree = new Tree();
			SyOrgan syOrgan = syOrgans.get(i);
			tree.setAttributes(tree.initAb(syOrgan.getRouteid(),
					syOrgan.getRoutename()));
			tree.setId(syOrgan.getOrganid());
			tree.setPid(syOrgan.getUporganid());
			tree.setPid(syOrgan.getUporganid());
			tree.setText(syOrgan.getOrganname());
			List<Tree> list_ = gTree_(syOrgan.getOrganid());
			list.add(tree);
			list.addAll(list);
		}
		return list;

	}

	public DataGrid dataGrid(SyOrgan syOrgan, int page, int rows,
			String searchName, String searchKey) {
		DataGrid dg = new DataGrid();
		String hql = "from SyOrgan t ";
		Map<String, Object> params = new HashMap<String, Object>();
		if (!searchName.trim().equals("")) {
			hql += "where t." + searchName
					+ " like :key and t.routeid like :organid";
			params.put("key", "%%" + searchKey.trim() + "%%");
			params.put("organid", "%%" + syOrgan.getOrganid().trim() + "%%");
		} else {
			hql += "where t.routeid like :organid";
			params.put("organid", "%%" + syOrgan.getOrganid().trim() + "%%");
		}
		String totalHql = "select count(*) " + hql;
		List<SyOrgan> l = null;
		if (rows == 0 && page == 0) {
			l = syOrganDao.find(hql, params);
		} else {
			l = syOrganDao.find(hql, params, page, rows);
		}
		dg.setTotal(syOrganDao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName("syOrgan");
		return dg;
	}

	public List<SyOrgan> get(SyOrgan syOrgan) {
		String hql = "from SyOrgan t where";
		Map<String, Object> params = new HashMap<String, Object>();
		List<SyOrgan> l = null;
		for (int i = 0; i < syOrgan.getClass().getMethods().length; i++) {
			Method f = syOrgan.getClass().getMethods()[i];
			if (f.getName().startsWith("get")
					&& f.getParameterTypes().length == 0
					&& (!f.getName().equalsIgnoreCase("getClass"))) {
				String fieldResult;
				String fieldName = "";
				try {
					fieldResult = f.invoke(syOrgan, null) == null ? "" : f
							.invoke(syOrgan, null).toString();
					for (int j = 0; j < syOrgan.getClass().getDeclaredFields().length; j++) {
						String fieldName_ = syOrgan.getClass()
								.getDeclaredFields()[j].getName();
						if (fieldName_.equalsIgnoreCase(f.getName()
								.substring(3))) {
							fieldName = fieldName_;
							break;
						}
					}

					if (!fieldResult.trim().equals("")
							&& !fieldName.trim().isEmpty()) {
						if ("java.lang.integer".equalsIgnoreCase(f
								.getReturnType().getName())) {
							hql += " t." + fieldName + " = :" + fieldName
									+ " and";
							params.put(fieldName, Integer.parseInt(fieldResult));
						} else {
							hql += " t." + fieldName + " = :" + fieldName
									+ " and";
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
		if (hql.endsWith("and")) {
			hql = hql.substring(0, hql.length() - 3);
		}
		try {
			l = syOrganDao.find(hql, params);
		} catch (Exception e) {
			e.toString();
		}
		return l;
	}

	public List<SyOrgan> getLeave(int iLeave) {
		iLeave = (iLeave - 1) * 7 + 1; // 在一级id一位，二级以后为六位的规则下
		String hql = "from SyOrgan t ";
		hql += "where length(t.routeid) = " + iLeave;
		List<SyOrgan> l = syOrganDao.find(hql);
		return l;
	}

	public List<SyOrgan> getLeave(int iLeave, String upOrganId) {
		iLeave = (iLeave - 1) * 7 + 1; // 在一级id一位，二级以后为六位的规则下
		String hql = "from SyOrgan t ";
		hql += "where length(t.routeid) = " + iLeave + " and  t.uporganid='"
				+ upOrganId + "'";
		List<SyOrgan> l = syOrganDao.find(hql);
		return l;
	}

}
