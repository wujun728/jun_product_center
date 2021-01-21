package org.myframework.support.file.service;

import org.myframework.commons.util.StringUtils;
import org.myframework.support.base.BaseCrudService;
import org.myframework.support.file.dao.MediaFileDao;
import org.myframework.support.file.entities.SysMediaFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mediaFileService")
public class MediaFileServiceImpl extends BaseCrudService<SysMediaFile, String> {

	@Autowired
	MediaFileDao mediaFileDao ;
	
	public void removeFile(String mediaId) {
		String mediaIdSql = StringUtils.parseToSqlOr(mediaId, "MEDIA_ID");
		jdbc.getJdbcTemplate()
				.update(" update  TBL_SYS_MEDIA_FILE set enabled ='0' where "
						+ mediaIdSql);
	}
	


}
