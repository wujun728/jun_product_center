package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysTreeDict;
import com.ruoyi.system.domain.SysTreeDictData;

import java.util.List;

/**
 * 树形字典数据Service接口
 *
 * @author wangzongrun
 * @date 2021-05-31
 */
public interface ISysTreeDictDataService {
    /**
     * 查询树形字典数据
     *
     * @param id 树形字典数据ID
     * @return 树形字典数据
     */
    SysTreeDictData selectById(String id);

    /**
     * `
     * 分页查询树形字典数据列表
     *
     * @param page 分页
     * @param sysTreeDictData 树形字典数据
     * @return 树形字典数据集合
     */
    IPage<SysTreeDictData> selectList(IPage<SysTreeDictData> page, SysTreeDictData sysTreeDictData);

    /**
     * 查询所有树形字典数据列表
     *
     * @param sysTreeDictData 树形字典数据
     * @return 树形字典数据集合
     */
    List<SysTreeDictData> selectListAll(SysTreeDictData sysTreeDictData);

    /**
     * 查询所有树形字典数据树结构
     *
     * @param sysTreeDictData 树形字典数据
     * @return 树结构
     */
    List<Object> buildTree(SysTreeDictData sysTreeDictData);

    /**
     * 新增树形字典数据
     *
     * @param sysTreeDictData 树形字典数据
     * @return 结果
     */
    int insert(SysTreeDictData sysTreeDictData);

    /**
     * 修改树形字典数据
     *
     * @param sysTreeDictData 树形字典数据
     * @return 结果
     */
    int update(SysTreeDictData sysTreeDictData);

    /**
     * 批量删除树形字典数据
     *
     * @param sids 需要删除的树形字典数据ID
     * @return 结果
     */
    int deleteByIds(String[] sids);

    /**
     * 删除树形字典数据信息
     *
     * @param id 树形字典数据ID
     * @return 结果
     */
    int deleteById(String id);

    /**
     * 检查名称唯一性
     *
     * @param entity 查询条件
     * @return 结果
     */
    AjaxResult checkUniqueByLabel(SysTreeDictData entity);


    /**
     * 检查编码唯一性
     *
     * @param treeDict 查询条件
     * @return 结果
     */
    List<SysTreeDict> selectTreeDictByType(String treeDict);

    /**
     *  校验编码
     *
     * @param sysTreeDictData 查询条件
     * @return 结果
     */
    AjaxResult checkCode(SysTreeDictData sysTreeDictData);
}
