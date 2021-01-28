package org.myframework.dao.jpashowcase.dao;

import org.myframework.dao.jpashowcase.entity.AccountInfo;
import org.myframework.dao.orm.AbstractConfigedQuery;
import org.myframework.dao.orm.EntityQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <ol>
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
public class UserDao2Impl extends AbstractConfigedQuery<AccountInfo>
		implements EntityQuery<AccountInfo> {

}
