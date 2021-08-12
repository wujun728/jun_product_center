package com.sanri.tools.modules.database.service;

import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import com.sanri.tools.modules.database.dtos.meta.Column;
import com.sanri.tools.modules.database.dtos.meta.TableMetaData;
import com.sanri.tools.modules.database.service.rename.JavaBeanInfo;

public interface RenameStrategy {
    JavaBeanInfo mapping(TableMetaData tableMetaData);
}
