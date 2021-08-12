package com.shuogesha.cms.service;

import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.cms.entity.Yaoqing;

public interface YaoqingService {
	Pagination getPage(String name,Integer uid, int pageNo, int pageSize);

	Yaoqing findById(Long id);

	void save(Yaoqing bean);

	void update(Yaoqing bean);

	void removeById(Long id);
	
	void removeByIds(Long[] ids);
	/**
	 * 邀请码
	 * @param id
	 */
	Yaoqing init(Long id,String refer);
	/**
	 * 获取邀请码
	 * @param refer
	 * @return
	 */
	Yaoqing findByCode(String code);
//	/**
//	 * 注册奖励
//	 * @param bean
//	 * @param yaoqing
//	 */
//	void upOne(Yaoqing bean, Double yaoqing);
//	/**
//	 * 消费提成
//	 * @param payeer_id
//	 * @param end
//	 */
//	void upTwo(Integer payeer_id,Double yaoqing_fee, Double amount);
//	/**
//	 * 收益提成
//	 * @param payeer_id
//	 * @param end
//	 */
//	void upThree(Long id, Double shouyi_fee, Double amount); 
}