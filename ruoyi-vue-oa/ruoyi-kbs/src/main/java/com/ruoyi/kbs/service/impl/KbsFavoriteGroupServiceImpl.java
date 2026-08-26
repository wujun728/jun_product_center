package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsFavoriteGroup;
import com.ruoyi.kbs.domain.KbsFavoriteGroupOption;
import com.ruoyi.kbs.domain.qo.KbsFavoriteGroupUpdateQo;
import com.ruoyi.kbs.mapper.KbsFavoriteGroupMapper;
import com.ruoyi.kbs.service.IKbsFavoriteGroupService;
import com.ruoyi.kbs.utils.TransferUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 知识库收藏组Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsFavoriteGroupServiceImpl implements IKbsFavoriteGroupService {
    @Autowired
    private KbsFavoriteGroupMapper kbsFavoriteGroupMapper;

    /**
     * 查询知识库收藏组
     *
     * @param id 知识库收藏组主键
     * @return 知识库收藏组
     */
    @Override
    public KbsFavoriteGroup getKbsFavoriteGroupById(String id) {
        return kbsFavoriteGroupMapper.selectKbsFavoriteGroupById(id);
    }

    /**
     * 查询知识库收藏组列表
     *
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 知识库收藏组
     */
    @Override
    public List<KbsFavoriteGroup> listKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup) {
        kbsFavoriteGroup.setCreateId(SecurityUtils.getUserId());
        return kbsFavoriteGroupMapper.selectKbsFavoriteGroupList(kbsFavoriteGroup);
    }

    /**
     * 新增知识库收藏组
     *
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 结果
     */
    @Override
    public int saveKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsFavoriteGroup.setId(IdUtils.fastSimpleUUID());
        kbsFavoriteGroup.setCreateId(loginUser.getUserId());
        kbsFavoriteGroup.setCreateBy(loginUser.getUser().getNickName());
        kbsFavoriteGroup.setCreateTime(DateUtils.getNowDate());
        return kbsFavoriteGroupMapper.insertKbsFavoriteGroup(kbsFavoriteGroup);
    }

    /**
     * 修改知识库收藏组
     *
     * @param kbsFavoriteGroup 知识库收藏组
     * @return 结果
     */
    @Override
    public int updateKbsFavoriteGroup(KbsFavoriteGroup kbsFavoriteGroup) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        kbsFavoriteGroup.setUpdateId(loginUser.getUserId());
        kbsFavoriteGroup.setUpdateBy(loginUser.getUser().getNickName());
        kbsFavoriteGroup.setUpdateTime(DateUtils.getNowDate());
        return kbsFavoriteGroupMapper.updateKbsFavoriteGroup(kbsFavoriteGroup);
    }

    /**
     * 批量删除知识库收藏组
     *
     * @param ids 需要删除的知识库收藏组主键
     * @return 结果
     */
    @Override
    public int deleteKbsFavoriteGroupByIds(String[] ids) {
        KbsFavoriteGroupUpdateQo qo = new KbsFavoriteGroupUpdateQo();
        qo.setIds(TransferUtil.arrayToList(ids));
        qo.setDelFlag(WhetherStatus.YES.getCode());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        qo.setUpdateId(loginUser.getUserId());
        qo.setUpdateBy(loginUser.getUser().getNickName());
        qo.setUpdateTime(DateUtils.getNowDate());
        return kbsFavoriteGroupMapper.updateDelFlagByIds(qo);
    }


    /**
     * 获取收藏组下拉列表
     *
     * @return 结果
     */
    @Override
    public List<KbsFavoriteGroupOption> getGroupSelectList() {
        return kbsFavoriteGroupMapper.selectGroupSelectList(SecurityUtils.getUserId());
    }

    /**
     * 根据收藏组ID集合查询收藏组信息
     *
     * @param groupIds 收藏组ID集合
     * @return 结果
     */
    @Override
    public List<KbsFavoriteGroup> listKbsFavoriteGroupByIds(List<String> groupIds) {
        return kbsFavoriteGroupMapper.selectKbsFavoriteGroupByIds(groupIds);
    }
}
