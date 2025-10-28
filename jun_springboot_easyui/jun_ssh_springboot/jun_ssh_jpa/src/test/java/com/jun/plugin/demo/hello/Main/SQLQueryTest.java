package com.jun.plugin.demo.hello.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter2;
import com.jun.plugin.query.sql.Condition;
import com.jun.plugin.query.sql.SQLQuery;
import com.jun.plugin.query.sql.expr.Expr;

public class SQLQueryTest extends BaseTest{
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void testSqlQuery() {
		Sort sort = new Sort(Direction.DESC, "userId");
		SQLQuery<Map> sqlQuery = new SQLQuery<>(em, Map.class);
		Pageable pageable = new PageRequest(0, 10, sort);
		Page page = sqlQuery.select("u.*")
				.from("user u inner join logrole r on u.logRoleId=r.logRoleId")
				.where(new Condition().add(Expr.like("u.userName", null).igEmpty())
						.add(Expr.in("u.userId", 1, 2, 3, 4, 5, 6, 7, 8, 9)).add(Expr.eq("u.logRoleId", "3")))
				.page(pageable);
		List list = new ArrayList();
		list.addAll(page.getContent());
		list.addAll(page.getContent());
		MyPrinter2.print(list);
	}

	@Test
	public void testSqlQueryAddPart() {
		Sort sort = new Sort(Direction.DESC, "userId");
		SQLQuery<Map> sqlQuery = new SQLQuery<>(em, Map.class);
		Pageable pageable = new PageRequest(0, 10, sort);
		Page page = sqlQuery.select("u.*")
				.from("user u inner join logrole r on u.logRoleId=r.logRoleId")
				.where(new Condition().allIgEmpty()
						.add(Expr.in("u.userId", 1, 2, 3, 4, 5, 6, 7, 8, 9)))
				.addPart("group by u.logRoleId")
				.page(pageable);
		MyPrinter2.print(page.getContent());
	}
}
