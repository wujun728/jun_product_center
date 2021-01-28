package org.myframework.support.file.dao;

import org.myframework.dao.orm.EntityQuery;
import org.myframework.support.file.entities.SysMediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileDao extends  EntityQuery<SysMediaFile> ,JpaRepository<SysMediaFile, String>{

}
