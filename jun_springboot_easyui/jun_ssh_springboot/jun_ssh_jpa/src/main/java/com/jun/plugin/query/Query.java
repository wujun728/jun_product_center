package com.jun.plugin.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface Query<T> {
	/**
	 * 异常后返回null
	 */
	public List<T> list(Sort sort);
	/**
	 * 异常后返回null
	 */
	public Page<T> page(Pageable pageable);
	/**
	 * 异常后返回null
	 */
	public Long count();
}
