//package sy.service.base.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import sy.dao.base.BaseDaoI;
//import sy.model.base.Syorganization;
//import sy.model.base.Syrole;
//import sy.model.base.Syuser;
//import sy.service.base.SyuserServiceI;
//import sy.service.impl.BaseServiceImpl;
//
///**
// * 用户业务逻辑
// * 
// * @author 孙宇
// * 
// */
//@Service
//public class SyuserServiceImpl extends BaseServiceImpl<Syuser> implements SyuserServiceI {
//
//	@Autowired
//	private BaseDaoI<Syrole> roleDao;
//
//	@Autowired
//	private BaseDaoI<Syorganization> organizationDao;
//
//	@Override
//	public void grantRole(String id, String roleIds) {
//		Syuser user = getById(id);
//		if (user != null) {
//			user.setSyroles(new HashSet<Syrole>());
//			for (String roleId : roleIds.split(",")) {
//				if (roleId != null && !roleId.equals("")) {
//					Syrole role = roleDao.getById(Syrole.class, roleId);
//					if (role != null) {
//						user.getSyroles().add(role);
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public void grantOrganization(String id, String organizationIds) {
//		Syuser user = getById(id);
//		if (user != null) {
//			user.setSyorganizations(new HashSet<Syorganization>());
//			for (String organizationId : organizationIds.split(",")) {
//				if (organizationId != null && !organizationId.equals("")) {
//					Syorganization organization = organizationDao.getById(Syorganization.class, organizationId);
//					if (organization != null) {
//						user.getSyorganizations().add(organization);
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public List<Long> userCreateDatetimeChart() {
//		List<Long> l = new ArrayList<Long>();
//		int k = 0;
//		for (int i = 0; i < 12; i++) {
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("a", k);
//			params.put("b", k + 2);
//			k = k + 2;
//			l.add(count("select count(*) from Syuser t where HOUR(t.createdatetime)>=:a and HOUR(t.createdatetime)<:b", params));
//		}
//		return l;
//	}
//
//	@Override
//	public Long countUserByRoleId(String roleId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("roleId", roleId);
//		String hql = "select count(*) from Syuser t join t.syroles role where role.id = :roleId";
//		return count(hql, params);
//	}
//
//	@Override
//	public Long countUserByNotRoleId() {
//		String hql = "select count(*) from Syuser t left join t.syroles role where role.id is null";
//		return count(hql);
//	}
//
//}
