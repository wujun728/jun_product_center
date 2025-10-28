package com.ruoyi.system.mapper;

import com.ruoyi.common.mybatis.mapper.BaseMapperX;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.SysTreeDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 树形字典数据Mapper接口
*/
@Mapper
public interface SysTreeDictDataMapper extends BaseMapperX<SysTreeDictData> {
    /**
     * 分页查询树形字典数据列表
     * <p>
     * param page             分页信息
     *
     * @param entity 树形字典数据信息
     * @return 树形字典数据集合
     */
    public IPage<SysTreeDictData> selectTreeDictDataPage(IPage<SysTreeDictData> page, @Param("entity") SysTreeDictData entity);

    /**
     * 查询所有树形字典数据列表
     *
     * @param entity 树形字典数据信息
     * @return 树形字典数据集合
     */
    public List<SysTreeDictData> selectTreeDictDataList(@Param("entity") SysTreeDictData entity);
}
