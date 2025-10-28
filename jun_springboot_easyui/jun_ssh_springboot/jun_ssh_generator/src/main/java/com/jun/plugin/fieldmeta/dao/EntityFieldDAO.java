package com.jun.plugin.fieldmeta.dao;

import com.jun.plugin.common.dataaccess.BaseRepository;
import com.jun.plugin.fieldmeta.entity.EntityField;

public interface EntityFieldDAO extends BaseRepository<EntityField, Long> {
	public static final String UPDATE_SORT="update_sort";
}
