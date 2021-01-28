package org.zhanghua.ssm.common.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zhanghua.ssm.common.entity.DataEntity;
import org.zhanghua.ssm.common.mapper.CrudMapper;
import org.zhanghua.ssm.common.page.Page;
import org.zhanghua.ssm.common.utils.IdGenerator;
import org.zhanghua.ssm.common.utils.UserUtils;

/**
 * Service层增删改查基类
 * 
 * @author Wujun
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class CrudService<T extends DataEntity<T>, ID extends Serializable> extends BaseService {

	@Autowired
	protected CrudMapper<T, ID> mapper;

	/**
	 * 查询单笔记录
	 * 
	 * @param id
	 * @return
	 */
	public T get(ID id) {
		return mapper.get(id);
	}

	/**
	 * 查询单笔记录
	 * 
	 * @param t
	 * @return
	 */
	public T get(T t) {
		return mapper.get(t);
	}

	/**
	 * 查询全部记录
	 * 
	 * @return
	 */
	public List<T> queryList() {
		return mapper.queryList();
	}

	/**
	 * 查询全部记录
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryList(T t) {
		return mapper.queryList(t);
	}

	/**
	 * 查询全部记录(分页)
	 * 
	 * @param page
	 * @param t
	 * @return
	 */
	public Page<T> queryList(Page<T> page, T t) {
		t.setPage(page);
		page = page.setList(mapper.queryList(t));
		return page;
	}

	/**
	 * 保存货更新(不推荐该方法，这个把业务分开个更为明确)
	 * 
	 * @param t
	 */
	// public void saveOrUpdate(T t) {
	// if (t.isNew()) {
	// this.save(t);
	// } else {
	// this.update(t);
	// }
	// }

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(T t) {
		t.setId(IdGenerator.uuid());
		t.setCreateTime(new Date());
		t.setCreateUser(UserUtils.getUser());
		mapper.save(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(T t) {
		t.setUpdateTime(new Date());
		t.setUpdateUser(UserUtils.getUser());
		mapper.update(t);
	}

	/**
	 * 删除单笔
	 * 
	 * @param id
	 */
	public void delete(ID id) {
		mapper.delete(id);
	}

	/**
	 * 删除多笔记录
	 * 
	 * @param ids
	 */
	public void delete(List<ID> ids) {
		mapper.delete(ids);
	}

}
