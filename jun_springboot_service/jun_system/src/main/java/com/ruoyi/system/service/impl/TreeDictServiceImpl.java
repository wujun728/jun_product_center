package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.TreeUtils;
import com.ruoyi.system.domain.SysTreeDict;
import com.ruoyi.system.domain.SysTreeDictData;
import com.ruoyi.system.mapper.SysTreeDictDataMapper;
import com.ruoyi.system.mapper.SysTreeDictMapper;
import com.ruoyi.system.service.ISysTreeDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * 系统树形Service业务层处理
 *
 * @author wangzongrun
 * @date 2021-06-04
 */
@Service
public class TreeDictServiceImpl implements ISysTreeDictService {

    @Autowired
    private SysTreeDictMapper sysTreeDictMapper;

    @Autowired
    private SysTreeDictDataMapper sysTreeDictDataMapper;

    /**
     * 项目启动时，初始化分类树到缓存
     */
    @PostConstruct
    public void init() {
        List<SysTreeDict> list = sysTreeDictMapper.selectList(null);
        list.stream().forEach(dictData -> {
            List<SysTreeDictData> dictDatas =sysTreeDictDataMapper.selectList(new QueryWrapper<SysTreeDictData>().lambda().eq(SysTreeDictData::getDelFlag, UserConstants.NORMAL)
                    .eq(SysTreeDictData::getTreeDict, dictData.getCode()).orderByAsc(SysTreeDictData::getOrderNum).orderByAsc(SysTreeDictData::getLevelCode));
            List<Object> buildTree = new TreeUtils().buildTree(dictDatas);
            TreeUtils.initCacheTreeData(dictData.getCode(),buildTree);
        });
    }

    /**
     * 查询系统树形
     *
     * @param id 系统树形ID
     * @return 系统树形
     */
    @Override
    public SysTreeDict selectById(String id) {
        return sysTreeDictMapper.selectById(id);
    }

    /**
     * 分页查询系统树形列表
     *
     * @param sysTreeDict 系统树形
     * @return 系统树形
     */
    @Override
    public IPage<SysTreeDict> selectList(IPage<SysTreeDict> page, SysTreeDict sysTreeDict) {
        return sysTreeDictMapper.selectTreeDictPage(page, sysTreeDict);
    }

    /**
     * 查询所有系统树形列表
     *
     * @param sysTreeDict 系统树形
     * @return 系统树形
     */
    @Override
    public List<SysTreeDict> selectListAll(SysTreeDict sysTreeDict) {
        return sysTreeDictMapper.selectTreeDictList(sysTreeDict);
    }

    /**
     * 新增系统树形
     *
     * @param sysTreeDict 系统树形
     * @return 结果
     */
    @Override
    public int insert(SysTreeDict sysTreeDict) {
        return sysTreeDictMapper.insert(sysTreeDict);
    }

    /**
     * 修改系统树形
     *
     * @param sysTreeDict 系统树形
     * @return 结果
     */
    @Override
    public int update(SysTreeDict sysTreeDict) {
        return sysTreeDictMapper.updateById(sysTreeDict);
    }

    /**
     * 批量删除系统树形
     *
     * @param sids 需要删除的系统树形ID
     * @return 结果
     */
    @Override
    public int deleteByIds(String[] sids) {
        return sysTreeDictMapper.deleteBatchIds(Arrays.asList(sids));
    }

    /**
     * 删除系统树形信息
     *
     * @param id 系统树形ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return sysTreeDictMapper.deleteById(id);
    }
}
