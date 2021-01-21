package org.myframework.support.file.dao;

import org.myframework.dao.orm.AbstractEntityQuery;
import org.myframework.support.file.entities.SysMediaFile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository 
@Transactional
public class MediaFileDaoImpl extends  AbstractEntityQuery<SysMediaFile>  {

}
