package org.myframework.support.base;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 功能说明:
 *  基础rest
 *  service用到同一个模型的场景很多,所以不使用泛型注入
 *
 */
public abstract class BaseTypedRest<T, ID extends Serializable> extends BaseRest {

	protected final Log logger = LogFactory.getLog(getClass());
 
	@Autowired
	BaseCrudService<T, ID> baseCrudService  ;
	 
	
}
