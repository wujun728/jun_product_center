package com.ruoyi.system.mapper;

import com.ruoyi.common.mybatis.mapper.BaseMapperX;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.SysTreeDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统树形Mapper接口
*/
@Mapper
public interface SysTreeDictMapper extends BaseMapperX<SysTreeDict> {
    /**
     * 分页查询系统树形列表
     * <p>
     * param page             分页信息
     * param entity           实体
     *
     * @param sysTreeDict 系统树形信息
     * @return 系统树形集合
     */
    public IPage<SysTreeDict> selectTreeDictPage(IPage<SysTreeDict> page, @Param("entity") SysTreeDict sysTreeDict);

    /**
     * 查询所有系统树形列表
     *
     * @param entity 系统树形信息
     * @return 系统树形集合
     */
    public List<SysTreeDict> selectTreeDictList(@Param("entity") SysTreeDict entity);
}
