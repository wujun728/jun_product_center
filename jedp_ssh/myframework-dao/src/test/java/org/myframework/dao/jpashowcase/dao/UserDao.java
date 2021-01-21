package org.myframework.dao.jpashowcase.dao;

import org.myframework.dao.jpashowcase.entity.AccountInfo;
import org.myframework.dao.orm.MyRepository;

//  @RepositoryDefinition(domainClass = AccountInfo.class, idClass = Long.class)
// or  extends org.springframework.data.repository.Repository 
// see  PagingAndSortingRepository
// see  CrudRepository
public interface UserDao extends UserDaoPlus , MyRepository<AccountInfo,String>{
 
	
}
