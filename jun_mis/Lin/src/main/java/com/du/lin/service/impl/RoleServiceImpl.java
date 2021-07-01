package com.du.lin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.du.lin.bean.Menu;
import com.du.lin.bean.Role;
import com.du.lin.bean.RoleMenuRelation;
import com.du.lin.bean.ShiroUser;
import com.du.lin.bean.ShowRole;
import com.du.lin.constant.Constant;
import com.du.lin.dao.MenuMapper;
import com.du.lin.dao.RoleMapper;
import com.du.lin.dao.RoleMenuRelationMapper;
import com.du.lin.dao.UserMapper;
import com.du.lin.service.RoleService;
import com.du.lin.utils.JqgridUtil;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper mapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JqgridUtil jqgridUtil;
	@Autowired
	private RoleMenuRelationMapper rmrMapper;
	
	@Override
	public List<ShowRole> getAllShowRole() {
		
		List<Role> list = mapper.selectList(null);
		List<ShowRole> result = new ArrayList<ShowRole>();
		for (Role role : list) {
			StringBuilder menus = new StringBuilder();
			ShowRole sr = new ShowRole();
			sr.setId(role.getId());
			sr.setRoles(role.getRoles());
			sr.setTips(role.getTips());
			
			List<RoleMenuRelation> ralationList = rmrMapper.selectList(new EntityWrapper<RoleMenuRelation>().eq("roleid", role.getId()));
			List<Menu> menuList = new ArrayList<Menu>();
			
			for (RoleMenuRelation roleMenuRelation : ralationList) {
				menuList.add(menuMapper.selectById(roleMenuRelation.getMenuid()));
			}
			
			if (menuList.size()>0) {
				for (Menu menu : menuList) {
					menus.append(menu.getName()).append(",");
				}
				
				sr.setMenus(menus.substring(0, menus.length()-1));	
			}else {
				sr.setMenus("无");
			}

			result.add(sr);
		}
		
		
		return result;
	}

	@Override
	public String getAllShowRoleJson(int page, int count) {

		List<ShowRole> all = getAllShowRole();
		int toIndex = count * page;
		if (all.size() < toIndex) {
			toIndex = all.size();
		}
		List<ShowRole> list = all.subList(count * (page - 1), toIndex);
		return jqgridUtil.getJson(list, page + "", all.size() , count);
	
	}

	@Override
	public String addRole(String roleName, String englisgName) {
		roleName = roleName.trim();
		englisgName = englisgName.trim();
		if ("".endsWith(roleName)) {
			return "";
		}
		if ("".endsWith(englisgName)) {
			return "";
		}
		if(!englisgName.matches("^[a-zA-Z\\d_]*$")){
			return Constant.ERROR_CODE_NOT_MATCHE;
		}
		
		Role role = new Role();
		role.setRoles(englisgName);
		role.setTips(roleName);
		
		Role temp = new Role();
		temp.setRoles(englisgName);
		if(mapper.selectOne(temp) != null){
			return Constant.ERROR_CODE_ROLES_EXIST;
		}
		temp = new Role();
		temp.setTips(roleName);
		if(mapper.selectOne(temp) != null){
			return Constant.ERROR_CODE_TIPS_EXIST;
		}
		
		int result = mapper.insert(role);
		if (result == 1) {
			return Constant.OPERATION_SUCCESS_CODE;			
		}
		return Constant.UNKNOWN_ERROR_CODE;
	}

	@Override
	public String setRole(int id, String tips, String roles) {
		tips = tips.trim();
		roles = roles.trim();
		if ("".equals(tips)) {
			return "";
		}
		if ("".equals(roles)) {
			return "";
		}
		if(!roles.matches("^[a-zA-Z\\d_]*$")){
			return Constant.ERROR_CODE_NOT_MATCHE;
		}
		
		Role role = new Role();
		role.setId(id);
		role.setRoles(roles);
		role.setTips(tips);

		List<Role> select1 = mapper.selectList(new EntityWrapper<Role>().eq("roles", roles));
		if(select1.size() > 0){
			for (Role temp : select1){
				if(temp.getId() != id){
					return Constant.ERROR_CODE_ROLES_EXIST;
				}
			}
		}

		select1.clear();
		select1 = mapper.selectList(new EntityWrapper<Role>().eq("tips", tips));
		if(select1.size() > 0){

			for (Role temp : select1){
				if(temp.getId() != id){
					return Constant.ERROR_CODE_TIPS_EXIST;
				}
			}
		}
		
		int result = mapper.updateById(role);
		if (result == 1) {
			return Constant.OPERATION_SUCCESS_CODE; 
		}
		return Constant.UNKNOWN_ERROR_CODE;
	}

	@Override
	public String deleteRole(int id) {
		int result = mapper.deleteById(id);
		if (result == 1) {
			rmrMapper.deleteById(id);
//			userMapper.updateByRoleidSelective(id);
			ShiroUser updateUser = new ShiroUser();
			updateUser.setId(id);
			updateUser.setRoleid(2);
			userMapper.updateById(updateUser);
			return Constant.OPERATION_SUCCESS_CODE;
		}
		return Constant.UNKNOWN_ERROR_CODE;
	}

	@Override
	public String roleListForUserAdd() {
        List<Role> list = mapper.selectList(null);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getId() + ":" + list.get(i).getTips() + ";");
        }
        return sb.substring(0, sb.length()-1);
    
	}

	@Override
	public String addRelation(int roleid, String menus) {
		List<String> menuList = null;
		if (menus.contains(",")) {
			menuList = Arrays.asList(menus.split(","));			
		}else{
			menuList = new ArrayList<String>();
			menuList.add(menus);
		}
		if(roleid == 1){
			if (!menuList.contains("角色管理")) {
				menuList.add("角色管理");				
			} 
		}
		rmrMapper.delete(new EntityWrapper<RoleMenuRelation>().eq("roleid", roleid));
		for (String string : menuList) {
			Menu temp = new Menu();
			temp.setName(string);
			Menu menu = menuMapper.selectOne(temp);
			if (menu != null) {
				RoleMenuRelation rmr = new RoleMenuRelation();
				rmr.setMenuid(menu.getId());
				rmr.setRoleid(roleid);
				rmrMapper.insert(rmr);
			}
		}
			return Constant.OPERATION_SUCCESS_CODE;
	}

}
