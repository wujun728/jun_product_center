package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.Menu;

public interface MenuService {
	List<Menu> getUserMenu();
	String menuTreeData();
}
