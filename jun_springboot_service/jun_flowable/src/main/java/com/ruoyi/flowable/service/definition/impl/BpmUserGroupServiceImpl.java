package com.ruoyi.flowable.service.definition.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.utils.collection.CollectionUtils;
import com.ruoyi.flowable.convert.definition.BpmUserGroupConvert;
import com.ruoyi.flowable.domain.entity.definition.BpmUserGroupDO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupCreateReqVO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupPageReqVO;
import com.ruoyi.flowable.domain.vo.group.BpmUserGroupUpdateReqVO;
import com.ruoyi.flowable.mapper.definition.BpmUserGroupMapper;
import com.ruoyi.flowable.service.definition.BpmUserGroupService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.ErrorCodeConstants.USER_GROUP_IS_DISABLE;
import static com.ruoyi.flowable.core.enums.ErrorCodeConstants.USER_GROUP_NOT_EXISTS;


/**
 * 用户组 Service 实现类
 * <p>
 * hasPermi
 */
@Service
@Validated
public class BpmUserGroupServiceImpl implements BpmUserGroupService {

    @Resource
    private BpmUserGroupMapper userGroupMapper;

    @Override
    public Long createUserGroup(BpmUserGroupCreateReqVO createReqVO) {
        // 插入
        BpmUserGroupDO userGroup = BpmUserGroupConvert.INSTANCE.convert(createReqVO);
        userGroupMapper.insert(userGroup);
        // 返回
        return userGroup.getId();
    }

    @Override
    public void updateUserGroup(BpmUserGroupUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateUserGroupExists(updateReqVO.getId());
        // 更新
        BpmUserGroupDO updateObj = BpmUserGroupConvert.INSTANCE.convert(updateReqVO);
        userGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserGroup(Long id) {
        // 校验存在
        this.validateUserGroupExists(id);
        // 删除
        userGroupMapper.deleteById(id);
    }

    private void validateUserGroupExists(Long id) {
        if (userGroupMapper.selectById(id) == null) {
            throw exception(USER_GROUP_NOT_EXISTS);
        }
    }

    @Override
    public BpmUserGroupDO getUserGroup(Long id) {
        return userGroupMapper.selectById(id);
    }

    @Override
    public List<BpmUserGroupDO> getUserGroupList(Collection<Long> ids) {
        return userGroupMapper.selectBatchIds(ids);
    }


    @Override
    public List<BpmUserGroupDO> getUserGroupListByStatus(Integer status) {
        return userGroupMapper.selectListByStatus(status);
    }

    @Override
    public PageResult<BpmUserGroupDO> getUserGroupPage(BpmUserGroupPageReqVO pageReqVO) {
        return userGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public void validUserGroups(Set<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得用户组信息
        List<BpmUserGroupDO> userGroups = userGroupMapper.selectBatchIds(ids);
        Map<Long, BpmUserGroupDO> userGroupMap = CollectionUtils.convertMap(userGroups, BpmUserGroupDO::getId);
        // 校验
        ids.forEach(id -> {
            BpmUserGroupDO userGroup = userGroupMap.get(id);
            if (userGroup == null) {
                throw exception(USER_GROUP_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(userGroup.getStatus())) {
                throw exception(USER_GROUP_IS_DISABLE, userGroup.getName());
            }
        });
    }

}
