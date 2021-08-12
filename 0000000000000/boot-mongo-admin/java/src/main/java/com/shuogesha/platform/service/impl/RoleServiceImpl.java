package com.shuogesha.platform.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.shuogesha.platform.dao.RoleDao;
import com.shuogesha.platform.entity.App;
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
		Pagination<App> page = new Pagination<App>(pageNo, pageSize, 0);   
		Query query = new Query(); 
 		if(StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		} 
		return dao.findPage(page, query);
	}

	@Override
	public void save(Role bean) {
		 bean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		 dao.saveEntity(bean);  
	}

	@Override
	public void update(Role bean) { 
		 dao.updateEntity(bean,bean.getId()); 
		 updatePerms(bean);
	}

	@Override
	public void removeById(String id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(String[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public List<Role> getList() { 
		return dao.find(new Query());
	}

	@Override
	public void updateRoleUser(String id, List<Integer> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", id);
		map.put("userList", list); 
	}
	
	@Override
	public void saveRoleMenus(Role bean) {
		 dao.updateEntity(bean,bean.getId());
		 updatePerms(bean);
	}

	@Override
	public List<Menu> getAllMenusByUser(String userId,Integer type) { 
		Query query = new Query(); 
  		query.addCriteria(Criteria.where("type").is(type)); 
		User user = userService.findById(userId);
		if((user!=null&&user.isAdmin())||dao.count(query)>0) {
			return menuService.getAllMenus("","0");//给全部权限
		} 
		List<Role> roles = user.getRoles();
		List<Menu> menuList = new ArrayList<>();//新的根节点
		List<String> ids = new ArrayList<>();//新的根节点
		for (Role role : roles) {
			if(role.isAllperms()) { 
				return menuService.getAllMenus("","0");//给全部权限
			}else {
//				ids.add(role.getId());
				List<Menu> menus =role.getMenus();
				if(menus!=null) {
					for (Menu menu : menus) {
						if(type==Integer.valueOf(menu.getType())){  
							if(!ids.contains(menu.getId())) {
								menu.setChildren(null);
								menuList.add(menu);
								ids.add(menu.getId());
							} 
						} 
					}
				} 
			}
		}
//		menuList=dao.findMenu(ids);
		if(menuList!=null) {
			menuList = getInfiniteLevelTree(menuList);
		} 
		return menuList; 
	}
	
	@Override
	public List<String> getAllPermsByUser(String userId) { 
		User user = userService.findById(userId);
		if((user!=null&&user.isAdmin())) {
			return menuService.getAllPerms();//给全部权限
		} 
		List<Role> roles = user.getRoles();
		List<String> ids= new ArrayList<>();
 		for (Role role : roles) {
			if(role.isAllperms()) { 
				return menuService.getAllPerms();//给全部权限
			}else {
				ids.add(role.getId()) ;
			}
		}
		return dao.getAllPerms(ids);
	}
	
	
//	private void findChildren(List<Menu> SysMenus, List<Menu> menus, Integer type) {
//		for (Menu menu1 : SysMenus) {
//			List<Menu> children = new ArrayList<>();
//			for (Menu menu2 : menus) {
////				if( "1".equals(type) && "1".equals(menu2.getType())) {
////					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
////					continue ;
////				}
//				if (menu1.getId() != null && menu1.getId().equals(menu2.getPid())) { 
//					if(!exists(children, menu2)) {
//						children.add(menu2);
//					}
//				}
//			}
//			menu1.setChildren(children);
//			children.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));
//			findChildren(children, menus, type);
//		}
//	}

	private boolean exists(List<Menu> sysMenus, Menu sysMenu) {
		boolean exist = false;
		for(Menu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	} 
	
	/**
	 * 更新权限
	 * @param bean
	 */
	private void updatePerms(Role bean){
		Update update = new Update(); 
		 if(!bean.isAllperms()) {//不是全部权限的时候直接设置权限 
			 if(bean.getMenus()!=null&&bean.getMenus().size()>0) { 
				  List<Menu> menus= bean.getMenus();
				  List list = new ArrayList<>();
				  for (Menu menu : menus) {
					  list.add(new DBRef("menu", menu.getId()));
				  }
				  update.set("menus",  list.toArray()); 
			 }
			 if(bean.getPerms()!=null&&bean.getPerms().size()>0) {  
				  update.set("perms",  bean.getPerms().toArray()); 
			 } 
		 } 
		 Query query = new Query(); 
		 query.addCriteria(Criteria.where("_id").is(bean.getId()));
		 dao.update(query, update); 
	}
	
	
	// 入口方法
    public static List<Menu> getInfiniteLevelTree(List<Menu> nodeList) {
        List<Menu> list = new ArrayList<>();
        // 遍历节点列表
        for (Menu node : nodeList) {
            if (node.getPid().equals("")) {
                // parentID为-1（根节点）作为入口
                node.setChildren(getChildrenNode(node.getId(), nodeList));
                list.add(node);
            }
        }
        // 排序
        list.sort(new NodeOrderComparator());
        return list;
    }

    // 获取子节点的递归方法
    public static List<Menu> getChildrenNode(String id, List<Menu> nodeList) {
        List<Menu> lists = new ArrayList<>();
        for (Menu node : nodeList) {
            if (node.getPid().equals(id)) {
                // 递归获取子节点
                node.setChildren(getChildrenNode(node.getId(), nodeList));
                lists.add(node);
            }
        }
        // 排序
        lists.sort(new NodeOrderComparator());
        return lists;
    }
	

}

//节点排序Comparator
class NodeOrderComparator implements Comparator<Menu>{
 // 按照节点排序值进行排序
 public int compare(Menu n1, Menu n2) {
     return (n1.getSort() < n2.getSort() ? -1 : (n1.getSort() == n2.getSort() ? 0 : 1));
 }
}