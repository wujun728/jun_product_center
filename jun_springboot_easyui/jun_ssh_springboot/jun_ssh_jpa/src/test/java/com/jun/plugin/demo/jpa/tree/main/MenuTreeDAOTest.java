package com.jun.plugin.demo.jpa.tree.main;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.annotation.Rollback;

import com.jun.plugin.common.tree.enumpath.TreeNodeEntity.MovePoint;
import com.jun.plugin.common.utils.MyPrinter;

public class MenuTreeDAOTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("app-jpa.xml");
	MenuTreeDAO menuTreeDAO = context.getBean(MenuTreeDAO.class);

//	//  需要先清空表，TRUNCATE table menu_tree
//	@Test
//	public void inserData(){	
//		for(int i=0;i<2;i++){
//			menuTreeDAO.createTreeNode(create(null));;
//		}
//		for(int i=0;i<3;i++){
//			menuTreeDAO.createTreeNode(create(2L));;
//		}
//		
//		for(int i=0;i<5;i++){
//			menuTreeDAO.createTreeNode(create(3L));;
//		}
//		
//		for(int i=0;i<3;i++){
//			menuTreeDAO.createTreeNode(create(4L));;
//		}
//		for (int i = 0; i < 2; i++) {
//			menuTreeDAO.createTreeNode(create(12L));
//			;
//		}
//	}
	

	
	public MenuTree create(Long parentId) {
		MenuTree menuTree = new MenuTree();
		menuTree.setNodeName(DateFormatUtils.format(new Date(), "HH:mm:ss"));
		menuTree.setParentId(parentId);
		return menuTree;
	}

	@Test
	@Rollback(true)//回滚
	public void testCreateTreeNode() {
		for (int i = 0; i < 1; i++) {
			menuTreeDAO.createTreeNode(create(5L));
		}
	}

	@Test
	public void testFindChildrenPageable() {
		Page<MenuTree> page = menuTreeDAO.findChildrenPage(3L, new PageRequest(0, 3));
		MyPrinter.printJson(page);
		Assert.assertTrue(page.getTotalElements() == 5);
	}

	@Test
	public void testFindChildrenPageable1() {
		Page<MenuTree> page=menuTreeDAO.findChildrenPage(null, new PageRequest(0, 3));
		MyPrinter.printJson(page);
		Assert.assertTrue(page.getTotalElements() == 2);
	}

	@Test
	public void testFindDescendantPageable() {
		Pageable pageable = new PageRequest(0, 3, new Sort(Direction.DESC, "nodeName"));
		Page<MenuTree> page = menuTreeDAO.findDescendantPage(4L, pageable);
		MyPrinter.printJson(page);
		Assert.assertTrue(page.getTotalElements() == 5);
	}

	@Test
	public void testFindDescendantPageable1() {
		Pageable pageable = new PageRequest(0, 3, new Sort(Direction.DESC, "nodeName"));
		//相当于findAll
		Page<MenuTree> page = menuTreeDAO.findDescendantPage(null, pageable);
		MyPrinter.printJson(page);
	}

	@Test
	public void testGetTreeNode() {
		MyPrinter.printJson(menuTreeDAO.getTreeNode(4L));
	}

	@Test
	public void testFindRoots() {
		MyPrinter.printJson(menuTreeDAO.findRoots(true, new Sort(Direction.DESC, "orderNum")));
	}

	@Test
	public void testFindChildrenTBooleanSort() {
		MenuTree menuTree = menuTreeDAO.findOne(4L);
		List<MenuTree> list=menuTree.getChildren();
		//Sort sort = new Sort(new Order(Direction.ASC, "orderNum"),new Order(Direction.DESC, "nodeName"));
		Sort sort = new Sort(new Order(Direction.DESC, "nodeName"),new Order(Direction.DESC, "orderNum"));
		MyPrinter.printJson(menuTreeDAO.findChildren(menuTree, true, sort));
	}

	@Test
	public void testFindAncestor() {
		MenuTree menuTree = menuTreeDAO.findOne(12L);
		MyPrinter.printJson(menuTreeDAO.findAncestor(menuTree));
	}

	@Test
	public void testFindDescendantTSort() {
		MenuTree menuTree = menuTreeDAO.findOne(2L);
		MyPrinter.printJson(menuTreeDAO.findDescendant(menuTree, new Sort(Direction.DESC, "nodeName")));
	}

	@Test
	public void moveTreeNode_APPEND() {
		menuTreeDAO.moveTreeNode(12L, 7L, MovePoint.APPEND);
	}

	@Test
	public void moveTreeNode_PREPEND() {
		menuTreeDAO.moveTreeNode(12L, 7L, MovePoint.PREPEND);
	}

	@Test
	public void moveTreeNode_TOP() {
		menuTreeDAO.moveTreeNode(9L, 7L, MovePoint.TOP);
	}

	@Test
	public void moveTreeNode_BOTTOM() {
		menuTreeDAO.moveTreeNode(10L, 7L, MovePoint.BOTTOM);
	}
	
	@Test
	public void setRandomOrderNum() {
		List<MenuTree> list = menuTreeDAO.findAll();
		for (MenuTree menuTree : list) {
			Double orderNum = Math.floor((Math.random() * 1000)) / 100;
			menuTree.setOrderNum(orderNum);
		}
		menuTreeDAO.save(list);
	}
}
