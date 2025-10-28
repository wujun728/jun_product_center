package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.SysTreeDict;

import java.util.List;

/**
 * 系统树形Service接口
 *
 * @author wangzongrun
 * @date 2021-06-04
 */
public interface ISysTreeDictService {
    /**
     * 查询系统树形
     *
     * @param id 系统树形ID
     * @return 系统树形
     */
    public SysTreeDict selectById(String id);

    /**
     * 分页查询系统树形列表
     *
     * @param sysTreeDict 系统树形
     * @return 系统树形集合
     */
    public IPage<SysTreeDict> selectList(IPage<SysTreeDict> page, SysTreeDict sysTreeDict);

    /**
     * 查询所有系统树形列表
     *
     * @param sysTreeDict 系统树形
     * @return 系统树形集合
     */
    public List<SysTreeDict> selectListAll(SysTreeDict sysTreeDict);

    /**
     * 新增系统树形
     *
     * @param sysTreeDict 系统树形
     * @return 结果
     */
    public int insert(SysTreeDict sysTreeDict);

    /**
     * 修改系统树形
     *
     * @param sysTreeDict 系统树形
     * @return 结果
     */
    public int update(SysTreeDict sysTreeDict);

    /**
     * 批量删除系统树形
     *
     * @param sids 需要删除的系统树形ID
     * @return 结果
     */
    public int deleteByIds(String[] sids);

    /**
     * 删除系统树形信息
     *
     * @param id 系统树形ID
     * @return 结果
     */
    public int deleteById(String id);
}
