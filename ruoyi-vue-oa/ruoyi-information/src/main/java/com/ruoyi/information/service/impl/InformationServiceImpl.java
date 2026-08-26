package com.ruoyi.information.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.information.domain.qo.InformationUpdateQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.information.mapper.InformationMapper;
import com.ruoyi.information.domain.Information;
import com.ruoyi.information.service.IInformationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新闻资讯Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class InformationServiceImpl implements IInformationService {
    @Autowired
    private InformationMapper informationMapper;

    /**
     * 查询新闻资讯
     * 
     * @param id 新闻资讯主键
     * @return 新闻资讯
     */
    @Override
    public Information getInformationById(String id) {
        return informationMapper.selectInformationById(id);
    }

    /**
     * 查询新闻资讯列表
     * 
     * @param information 新闻资讯
     * @return 新闻资讯
     */
    @Override
    public List<Information> listInformation(Information information) {
        return informationMapper.selectInformationList(information);
    }

    /**
     * 查询发布新闻资讯列表
     *
     * @param information 新闻资讯
     * @return 新闻资讯
     */
    @Override
    public List<Information> listPub(Information information) {
        return informationMapper.selectPubList(information);
    }

    /**
     * 新增新闻资讯
     * 
     * @param information 新闻资讯
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveInformation(Information information) {
        information.setId(IdUtils.fastSimpleUUID());
        information.setCreateId(SecurityUtils.getUserId());
        information.setCreateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        information.setCreateTime(DateUtils.getNowDate());
        // 更新已置顶的
        if (StringUtils.isNotBlank(information.getTopFlag()) && StringUtils.equals(Constants.YES_VALUE, information.getTopFlag())) {
            informationMapper.resetTopFlag();
        }
        return informationMapper.insertInformation(information);
    }

    /**
     * 修改新闻资讯
     * 
     * @param information 新闻资讯
     * @return 结果
     */
    @Override
    public int updateInformation(Information information) {
        information.setUpdateId(SecurityUtils.getUserId());
        information.setUpdateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        information.setUpdateTime(DateUtils.getNowDate());
        // 更新已置顶的
        if (StringUtils.isNotBlank(information.getTopFlag()) && StringUtils.equals(Constants.YES_VALUE, information.getTopFlag())) {
            informationMapper.resetTopFlag();
        }
        return informationMapper.updateInformation(information);
    }

    /**
     * 批量删除新闻资讯
     * 
     * @param ids 需要删除的新闻资讯主键
     * @return 结果
     */
    @Override
    public int deleteInformationByIds(String[] ids) {
        return updateDelFlagByIds(ids);
    }

    /**
     * 改变状态
     *
     * @param information 新闻资讯
     * @return
     */
    @Override
    public int changeStatus(Information information) {
        if (StringUtils.isBlank(information.getId())) {
            throw new BaseException("参数错误");
        }
        if (getInformationById(information.getId()) == null) {
            throw new BaseException("记录不存在");
        }
        information.setUpdateId(SecurityUtils.getUserId());
        information.setUpdateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        information.setUpdateTime(DateUtils.getNowDate());
        return informationMapper.changeStatus(information);
    }

    /**
     * 添加阅读数
     *
     * @param id 新闻资讯主键
     */
    @Override
    public void addReadNum(String id) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        informationMapper.addReadNum(id);
    }

    /**
     * 置顶
     *
     * @param information 新闻资讯
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void toTop(Information information) {
        if (StringUtils.isBlank(information.getId()) || StringUtils.isBlank(information.getTopFlag())) {
            throw new BaseException("参数错误");
        }
        // 保持只有一份是置顶的
        if (StringUtils.equals(Constants.YES_VALUE, information.getTopFlag())) {
            informationMapper.resetTopFlag();
        }
        informationMapper.toTop(information);
    }

    /**
     * 批量删除新闻资讯信息
     *
     * @param ids 需要删除的日程主键
     * @return
     */
    private int updateDelFlagByIds(String[] ids) {
        InformationUpdateQo qo = new InformationUpdateQo();
        qo.setIds(ids);
        qo.setDelFlag(WhetherStatus.YES.getCode());
        qo.setUpdateId(SecurityUtils.getUserId());
        qo.setUpdateTime(DateUtils.getNowDate());
        return informationMapper.updateDelFlagByIds(qo);
    }
}
