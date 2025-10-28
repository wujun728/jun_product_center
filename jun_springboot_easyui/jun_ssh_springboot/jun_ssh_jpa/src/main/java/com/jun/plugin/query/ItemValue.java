package com.jun.plugin.query;

public interface ItemValue<T> {
	public String of(T item) throws NullPointerException;
}
