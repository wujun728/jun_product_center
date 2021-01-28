package org.coderfun.fieldmeta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.coderfun.fieldmeta.entity.EntityField;
import org.springframework.beans.factory.annotation.Autowired;

import klg.j2ee.common.dataaccess.BaseRepository;

public class EntityFieldDAOImpl {
	@Autowired
	protected BaseRepository<EntityField, Long> baseRepository;

	public long executeUpdate(String updateName, Object obj) {
		if (EntityFieldDAO.UPDATE_SORT.equals(updateName)) {
			return updateSort((List<EntityField>) obj);
		}
		return 0;
	}
	public long updateSort(List<EntityField> entityFields) {
		EntityManager entityManager=baseRepository.getEntityManager();
		long count=0;
		for(EntityField entityField:entityFields){
			if(entityField.getId()==null)
				continue;
			Query query=entityManager.createNativeQuery("UPDATE fm_field_entity set column_sort=:columnSort where id=:id");
			query.setParameter("id", entityField.getId());
			query.setParameter("columnSort", entityField.getColumnSort());
			count += query.executeUpdate();
		}

		return count;
	}

}