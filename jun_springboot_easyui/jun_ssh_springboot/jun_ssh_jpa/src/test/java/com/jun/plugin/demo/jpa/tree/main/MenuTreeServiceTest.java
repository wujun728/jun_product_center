package com.jun.plugin.demo.jpa.tree.main;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jun.plugin.common.tree.enumpath.TreeNodeEntity.MovePoint;
import com.jun.plugin.common.utils.MyPrinter;

public class MenuTreeServiceTest {

	
	ApplicationContext context = new ClassPathXmlApplicationContext("app-jpa.xml");
	MenuTreeService menuTreeService = context.getBean(MenuTreeService.class);	
	
	public MenuTree create(Long parentId){
		MenuTree menuTree = new MenuTree();
		menuTree.setNodeName(DateFormatUtils.format(new Date(), "HH:mm:ss") );
		menuTree.setParentId(parentId);
		return menuTree;
	}	

	
	@Test
	public void testCreateTreeNode() {
		for(int i=0;i<3;i++){
			menuTreeService.createTreeNode(create(3L));;
		}
	}

	@Test
	public void testFindChildrenIDPageable() {

		MyPrinter.printJson(menuTreeService.findChildrenPage(3L, new PageRequest(0, 10)));
	}

	@Test
	public void testFindDescendantTPageable() {
		Pageable pageable = new PageRequest(0, 3,new Sort(Direction.DESC,"nodeName"));
		MyPrinter.printJson(menuTreeService.findDescendantPage(3L, pageable).getContent());
	}

	@Test
	public void testGetTreeNode() {
		MyPrinter.printJson(menuTreeService.getTreeNode(1L));
	}

	@Test
	public void testFindRoots() {
		MyPrinter.printJson(menuTreeService.findRoots(true, new Sort(Direction.DESC,"nodeName")));
	}

	@Test
	public void testFindChildrenTBooleanSort() {
		MenuTree menuTree = menuTreeService.getById(2L);
		MyPrinter.printJson(menuTreeService.findChildren(menuTree, true, null));
	}

	@Test
	public void testFindAncestor() {
		MenuTree menuTree = menuTreeService.getById(12L);
		MyPrinter.printJson(menuTreeService.findAncestor(menuTree));
	}

	@Test
	public void testFindDescendantTSort() {
		MenuTree menuTree = menuTreeService.getById(2L);
		MyPrinter.printJson(menuTreeService.findDescendant(menuTree, new Sort(Direction.DESC,"nodeName")));
	}
	@Test
	public void moveTreeNode_APPEND(){		
		menuTreeService.moveTreeNode(2L, 5L, MovePoint.APPEND);
	}
	@Test
	public void moveTreeNode_PREPEND(){		
		menuTreeService.moveTreeNode(3L, 2L, MovePoint.PREPEND);
	}
	
	@Test
	public void moveTreeNode_TOP(){		
		menuTreeService.moveTreeNode(2L, 6L, MovePoint.TOP);
	}
	
	@Test
	public void moveTreeNode_BOTTOM(){		
		menuTreeService.moveTreeNode(2L, 6L, MovePoint.BOTTOM);
	}
	
	@Test
	public void setRandomOrderNum(){
		List<MenuTree> list = menuTreeService.findAll();
		for(MenuTree menuTree : list){
			Double orderNum = Math.floor((Math.random()*1000))/100;
			menuTree.setOrderNum(orderNum);
		}		
		menuTreeService.save(list);
	}
}
