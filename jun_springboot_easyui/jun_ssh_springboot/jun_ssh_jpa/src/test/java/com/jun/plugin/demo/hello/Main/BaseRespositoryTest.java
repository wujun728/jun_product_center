package com.jun.plugin.demo.hello.Main;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;
import com.jun.plugin.common.utils.MyPrinter2;
import com.jun.plugin.demo.jpa.pojo.Logrole;
import com.jun.plugin.demo.jpa.pojo.User;
import com.jun.plugin.demo.jpa.pojo.User_;
import com.jun.plugin.demo.jpa.respository.UserDAO;
import com.jun.plugin.query.jpa.expr.AExpr;
import com.jun.plugin.query.sql.expr.Expr;

public class BaseRespositoryTest extends BaseTest{
	@Autowired
	UserDAO userDAO;

	
	@Test
	public void findpage(){
		Sort sort = new Sort(Direction.DESC, "userId");
		Pageable pageable = new PageRequest(0, 5, sort);
		MyPrinter.printJson(userDAO.findPage(pageable));
	}
	
	@Test
	public void contain() {
		MyPrinter.printJson(userDAO.findList(new Sort(Direction.DESC, "userId"),
				AExpr.contain(User_.userName, "fd")));
		
		MyPrinter.printJson(userDAO.findList(new Sort(Direction.DESC, "userId"),
				AExpr.contain(User_.userName, "")).size());
	}

	@Test
	public void testSort() {
		MyPrinter.print(userDAO.findList(new Sort(Direction.ASC, "logrole.logRoleName"), 
				AExpr.contain(User_.userName, "gg")));
	}


	@Test
	public void testIgEmpty() {
		Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "userId"));
		Page<User> userPage = userDAO.findPage(pageable, 
				AExpr.eq(User_.account, "").igEmpty(),
				AExpr.contain(User_.userName, "fd"));
		MyPrinter.printJson(userPage);
	}

	@Test
	public void testRandom() {
		MyPrinter.printWithSign("random", userDAO.random(10));
	}

	@Test
	public void testRandom2() {
		MyPrinter.printWithSign("random", userDAO.random(10, Expr.gt("userId", 10)));
	}



	//---------------------ExampleMatcher

	@Test
	public void testExampleMatcher() {
		Sort sort = new Sort(Direction.DESC, "userId");
		Pageable pageable = new PageRequest(0, 100, sort);
		User user = new User();
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("userName", GenericPropertyMatcher.of(StringMatcher.CONTAINING).ignoreCase());
		MyPrinter2.print(userDAO.findPage(user, pageable).getContent());
	}

	@Test
	public void testExampleMatcherNested() {
		User user = new User();
		Logrole logrole = new Logrole();
		logrole.setLogRoleName("学");
		user.setLogrole(logrole);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("logrole.logRoleName", GenericPropertyMatcher.of(StringMatcher.CONTAINING).ignoreCase());
		MyPrinter2.print(userDAO.findAll(Example.of(user, matcher)));
	}
}
