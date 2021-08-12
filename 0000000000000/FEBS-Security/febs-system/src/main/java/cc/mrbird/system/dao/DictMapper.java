package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Dict;

import java.util.List;

public interface DictMapper extends MyMapper<Dict> {

    List<Dict> findDictByFieldName(String fieldName);

    Dict findDictByFieldNameAndKeyy(String fieldName, String keyy);
}