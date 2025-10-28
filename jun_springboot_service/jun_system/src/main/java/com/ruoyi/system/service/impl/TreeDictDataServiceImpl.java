package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.TreeConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.TreeUtils;
import com.ruoyi.system.domain.SysTreeDict;
import com.ruoyi.system.domain.SysTreeDictData;
import com.ruoyi.system.mapper.SysTreeDictDataMapper;
import com.ruoyi.system.service.ISysTreeDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 树形字典数据Service业务层处理
 *
 * @author wangzongrun
 * @date 2021-05-31
 */
@Service
public class TreeDictDataServiceImpl implements ISysTreeDictDataService {

    @Autowired
    private SysTreeDictDataMapper sysTreeDictDataMapper;

    /**
     * 查询树形字典数据
     *
     * @param id 树形字典数据ID
     * @return 树形字典数据
     */
    @Override
    public SysTreeDictData selectById(String id) {
        return sysTreeDictDataMapper.selectById(id);
    }

    /**
     * 分页查询树形字典数据列表
     *
     * @param sysTreeDictData 树形字典数据
     * @return 树形字典数据
     */
    @Override
    public IPage<SysTreeDictData> selectList(IPage<SysTreeDictData> page, SysTreeDictData sysTreeDictData) {
        return sysTreeDictDataMapper.selectTreeDictDataPage(page, sysTreeDictData);
    }

    /**
     * 查询所有树形字典数据列表
     *
     * @param sysTreeDictData 树形字典数据
     * @return 树形字典数据
     */
    @Override
    public List<SysTreeDictData> selectListAll(SysTreeDictData sysTreeDictData) {
        return sysTreeDictDataMapper.selectTreeDictDataList(sysTreeDictData);
    }

    /**
     * 查询所有树形字典数据树结构
     * fsd
     *
     * @param sysTreeDictData 树形字典数据
     * @return 树结构
     */
    @Override
    public List<Object> buildTree(SysTreeDictData sysTreeDictData) {
        List<SysTreeDictData> list = sysTreeDictDataMapper.selectTreeDictDataList(sysTreeDictData);
        TreeUtils treeUtils = new TreeUtils();
        List<Object> result = treeUtils.buildTree(list);
        return result;
    }

    /**
     * 新增树形字典数据
     *
     * @param sysTreeDictData 树形字典数据
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(SysTreeDictData sysTreeDictData) {
        // 更新父类节点
        SysTreeDictData parent = new SysTreeDictData();
        parent.setId(sysTreeDictData.getPid());
        parent.setIsLeaf(Constants.FALSE);
        sysTreeDictDataMapper.updateById(parent);

        // 插入实体
        return sysTreeDictDataMapper.insert(sysTreeDictData);
    }

    /**
     * 修改树形字典数据
     *
     * @param sysTreeDictData 树形字典数据
     * @return 结果
     */
    @Override
    public int update(SysTreeDictData sysTreeDictData) {
        return sysTreeDictDataMapper.updateById(sysTreeDictData);
    }

    /**
     * 批量删除树形字典数据
     *
     * @param sids 需要删除的树形字典数据ID
     * @return 结果
     */
    @Override
    public int deleteByIds(String[] sids) {
        return sysTreeDictDataMapper.deleteBatchIds(Arrays.asList(sids));
    }

    /**
     * 删除树形字典数据信息
     *
     * @param id 树形字典数据ID
     * @return 结果
     */
    @Override
    public int deleteById(String id) {
        return sysTreeDictDataMapper.deleteById(id);
    }

    /**
     * 检查名称唯一性
     *
     * @param entity 查询条件
     * @return 结果
     */
    @Override
    public AjaxResult checkUniqueByLabel(SysTreeDictData entity) {
        LambdaQueryWrapper<SysTreeDictData> queryWrapper = new LambdaQueryWrapper<SysTreeDictData>();
        String parentId = TreeConstants.PARENT_ROOT_VALUE;
        // 如果传入数据名称为空的情况下，返回null
        if (StringUtils.isEmpty(entity.getLabel())) {
            return null;
        }

        // 增加查询条件：名称， 分组KEY
        queryWrapper.eq(SysTreeDictData::getLabel, entity.getLabel());
        queryWrapper.eq(SysTreeDictData::getTreeDict, entity.getTreeDict());

        // 同级层次下不能重名
        if (StringUtils.isNotNull(entity.getPid())) {
            parentId = entity.getPid();
        }
        queryWrapper.eq(SysTreeDictData::getPid, parentId);


        // 如果存在ID，排除本身ID的实体存在
        if (StringUtils.isNotNull(entity.getId())) {
            queryWrapper.ne(SysTreeDictData::getId, entity.getId());
        }

        SysTreeDictData result = sysTreeDictDataMapper.selectOne(queryWrapper);

        if (StringUtils.isNotNull(result)) {
            return AjaxResult.error(501, "名称：" + entity.getLabel() + " 重复");
        } else {
            return AjaxResult.success();
        }
    }

    @Override
    public List<SysTreeDict> selectTreeDictByType(String treeDict) {
        LambdaQueryWrapper<SysTreeDictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysTreeDictData::getTreeDict, treeDict);
        sysTreeDictDataMapper.selectList(wrapper);
        return null;
    }

    @Override
    public AjaxResult checkCode(SysTreeDictData sysTreeDictData) {
        String code = sysTreeDictData.getCode();
        if (code.length() <= 0) {
            return AjaxResult.error(501, "不能为空");
        }
        if (checkUniqueCode(sysTreeDictData)) {
            return AjaxResult.error(502, "编码重复!" );
        }
        return AjaxResult.success(sysTreeDictData);
    }

    private String getPLevelCode(String pid) {
        String pLevelCode = "";
        if (!TreeConstants.PARENT_ROOT_VALUE.equals(pid)) {
            SysTreeDictData parent = sysTreeDictDataMapper.selectById(pid);
            pLevelCode = parent.getLevelCode();
        }
        return pLevelCode;
    }

    private String getLevelCode(SysTreeDictData sysTreeDictData, String pid) {
        String levelCode = "";
        LambdaQueryWrapper<SysTreeDictData> queryWrapper = new LambdaQueryWrapper<SysTreeDictData>();
        queryWrapper.eq(SysTreeDictData::getTreeDict, sysTreeDictData.getTreeDict());
        queryWrapper.eq(SysTreeDictData::getPid, pid);
        queryWrapper.orderByDesc(SysTreeDictData::getLevelCode);
        List<SysTreeDictData> sibling = sysTreeDictDataMapper.selectList(queryWrapper);
        if (sibling.size() > 0) {
            levelCode = sibling.get(0).getLevelCode();
        }
        return levelCode;
    }

    private boolean checkUniqueCode(SysTreeDictData entity) {
        LambdaQueryWrapper<SysTreeDictData> queryWrapper = new LambdaQueryWrapper<SysTreeDictData>();

        // 增加查询条件：编码， 分组KEY
        queryWrapper.eq(SysTreeDictData::getCode, entity.getCode());
        queryWrapper.eq(SysTreeDictData::getTreeDict, entity.getTreeDict());

        // 如果存在ID，排除本身ID的实体存在
        if (StringUtils.isNotNull(entity.getId())) {
            queryWrapper.ne(SysTreeDictData::getId, entity.getId());
        }

        SysTreeDictData result = sysTreeDictDataMapper.selectOne(queryWrapper);

        return StringUtils.isNotNull(result);
    }
}
