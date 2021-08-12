package cn.iocoder.yudao.adminserver.modules.system.dal.mysql.dict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.SysDictDataExportReqVO;
import cn.iocoder.yudao.adminserver.modules.system.controller.dict.vo.data.SysDictDataPageReqVO;
import cn.iocoder.yudao.adminserver.modules.system.dal.dataobject.dict.SysDictDataDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface SysDictDataMapper extends BaseMapperX<SysDictDataDO> {

    default SysDictDataDO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(new QueryWrapper<SysDictDataDO>().eq("dict_type", dictType)
                .eq("value", value));
    }

    default int selectCountByDictType(String dictType) {
        return selectCount("dict_type", dictType);
    }

    default PageResult<SysDictDataDO> selectPage(SysDictDataPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysDictDataDO>()
                .likeIfPresent("label", reqVO.getLabel())
                .likeIfPresent("dict_type", reqVO.getDictType())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByAsc("dict_type", "sort"));
    }

    default List<SysDictDataDO> selectList(SysDictDataExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysDictDataDO>().likeIfPresent("label", reqVO.getLabel())
                        .likeIfPresent("dict_type", reqVO.getDictType())
                        .eqIfPresent("status", reqVO.getStatus()));
    }

    default boolean selectExistsByUpdateTimeAfter(Date maxUpdateTime) {
        return selectOne(new QueryWrapper<SysDictDataDO>().select("id")
                .gt("update_time", maxUpdateTime).last("LIMIT 1")) != null;
    }

}
