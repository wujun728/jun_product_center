package com.erp.util;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.collection.internal.PersistentSet;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * 主要用于过滤不需要序列化的属性，或者包含需要序列化的属性
 * 
 * @author 孙宇
 * 
 */
public class FastjsonFilter implements PropertyFilter {

	private final Set<String> includes = new HashSet<String>();
	private final Set<String> excludes = new HashSet<String>();

	public Set<String> getIncludes() {
		return includes;
	}

	public Set<String> getExcludes() {
		return excludes;
	}

	@Override
	public boolean apply(Object source, String name, Object value) {
		if (value != null && (value instanceof PersistentSet)) {// 避免hibernate对象循环引用，一切Set属性不予序列化
			return false;
		}
		if (excludes.contains(name)) {
			return false;
		}
		if (excludes.contains(source.getClass().getSimpleName() + "." + name)) {
			return false;
		}
		if (includes.size() == 0 || includes.contains(name)) {
			return true;
		}
		if (includes.contains(source.getClass().getSimpleName() + "." + name)) {
			return true;
		}
		return false;
	}

}
