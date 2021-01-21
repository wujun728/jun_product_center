package org.myframework.dao.jpashowcase.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <ol>
 * 自定义扩展接口的实现必须是
 * <li>{@link }</li>
 * 
 * </ol>
 * 
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年2月25日
 *
 */
@Repository
@Transactional
public class UserDaoImpl  implements UserDaoPlus         {

	@Override
	public void printPlus() {
		 System.out.println("printPlus");
	}
	 

	 


}
