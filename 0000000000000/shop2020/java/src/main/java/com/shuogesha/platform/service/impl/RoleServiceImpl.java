package com.shuogesha.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.platform.dao.RoleDao;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.MenuService;
import com.shuogesha.platform.service.RoleService;
import com.shuogesha.platform.service.UserService;
import com.shuogesha.platform.web.mongo.Pagination;


@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao dao;
	@Autowired
	public MenuService menuService;
	@Autowired
	public UserService userService;

	@Override
	public Role findById(Long id) {
		Role bean=dao.findById(id);
		return bean;
	}
	
	@Override
	public Pagination getPage( String name,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		long totalCount = dao.count(map);
		Pagination<Role> page = new Pagination<Role>(pageNo, pageSize, totalCount);
		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Role> datas = dao.queryList(map);
		page.setDatas(datas);
		return page; 
	}

	@Override
	public void save(Role bean) {
		 dao.saveEntity(bean); 
		 if(bean.getPerms()!=null&&bean.getPerms().size()>0) {
			 dao.addRolePerms(bean);
		 }
	}

	@Override
	public void update(Role bean) { 
		 dao.updateById(bean);
		 dao.removeRoleById(bean.getId());
		 if(bean.getPerms()!=null&&bean.getPerms().size()>0) {
			 dao.addRolePerms(bean);
		 }
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public List<Role> getList() { 
		return dao.findList();
	}

	@Override
	public void updateRoleUser(Long id, List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", id);
		map.put("userList", list);
		dao.removeRoleUser(map);
		dao.addRoleUser(map);
	}
	
	@Override
	public void saveRoleMenus(Role bean) {
		 dao.updateById(bean);
		 dao.removeRoleMenuById(bean.getId());
		 if(!bean.isAllperms()) {//不是全部权限的时候直接设置权限
			 if(bean.getMenus()!=null&&bean.getMenus().size()>0) {
				 dao.addRoleMenus(bean);
			 }
		 } 
	}

	@Override
	public List<Menu> getAllMenusByUser(Long userId,Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type","0");//默认查询菜单
		User user = userService.findById(userId);
		if((user!=null&&user.isAdmin())||dao.countAllperms(map)>0) {
			return menuService.getAllMenus(null,"0");//给全部权限
		} 
		return dao.getAllMenusByUser(map);
		//暂时废弃不用java代码排序
//		List<Menu> menuList = new ArrayList<>();//新的根节点
//		List<Menu> menus = dao.getAllMenusByUser(map);
//		for (Menu menu : menus) {
//			if (menu.getPid() == null || menu.getPid() == 0) {
// 				if(!exists(menuList, menu)) {
// 					menuList.add(menu);
//				}
//			}
//		}
//		menuList.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));
//		findChildren(menuList, menus, type);
//		return menuList;
	}
	
	@Override
	public Set<String> getAllPermsByUser(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId); 
		User user = userService.findById(userId);
		if((user!=null&&user.isAdmin())||dao.countAllperms(map)>0) {
			return dao.getAllPerms(null);//给全部权限
		} 
		return dao.getAllPermsByUser(map);
	}
	
	
	private void findChildren(List<Menu> SysMenus, List<Menu> menus, Integer type) {
		for (Menu menu1 : SysMenus) {
			List<Menu> children = new ArrayList<>();
			for (Menu menu2 : menus) {
//				if( "1".equals(type) && "1".equals(menu2.getType())) {
//					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
//					continue ;
//				}
				if (menu1.getId() != null && menu1.getId().equals(menu2.getPid())) { 
					if(!exists(children, menu2)) {
						children.add(menu2);
					}
				}
			}
			menu1.setChildren(children);
			children.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));
			findChildren(children, menus, type);
		}
	}

	private boolean exists(List<Menu> sysMenus, Menu sysMenu) {
		boolean exist = false;
		for(Menu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	} 

	
	
	

}
