package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.HrUserOfferMapper;
import com.ruoyi.qixing.domain.HrUserOffer;
import com.ruoyi.qixing.service.IHrUserOfferService;

/**
 * Offer发放Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class HrUserOfferServiceImpl implements IHrUserOfferService
{
    @Autowired
    private HrUserOfferMapper hrUserOfferMapper;

    /**
     * 查询Offer发放
     *
     * @param id Offer发放主键
     * @return Offer发放
     */
    @Override
    public HrUserOffer selectHrUserOfferById(String id)
    {
        return hrUserOfferMapper.selectHrUserOfferById(id);
    }

    /**
     * 查询Offer发放列表
     *
     * @param hrUserOffer Offer发放
     * @return Offer发放
     */
    @Override
    public List<HrUserOffer> selectHrUserOfferList(HrUserOffer hrUserOffer)
    {
        return hrUserOfferMapper.selectHrUserOfferList(hrUserOffer);
    }

    /**
     * 新增Offer发放
     *
     * @param hrUserOffer Offer发放
     * @return 结果
     */
    @Override
    public int insertHrUserOffer(HrUserOffer hrUserOffer)
    {if (hrUserOffer.getId() == null || hrUserOffer.getId().length() == 0)
        {
            hrUserOffer.setId(String.valueOf(IdWorker.getId()));
        }

        hrUserOffer.setCreateTime(DateUtils.getNowDate());
        return hrUserOfferMapper.insertHrUserOffer(hrUserOffer);
    }

    /**
     * 修改Offer发放
     *
     * @param hrUserOffer Offer发放
     * @return 结果
     */
    @Override
    public int updateHrUserOffer(HrUserOffer hrUserOffer)
    {
        hrUserOffer.setUpdateTime(DateUtils.getNowDate());
        return hrUserOfferMapper.updateHrUserOffer(hrUserOffer);
    }

    /**
     * 批量删除Offer发放
     *
     * @param ids 需要删除的Offer发放主键
     * @return 结果
     */
    @Override
    public int deleteHrUserOfferByIds(String[] ids)
    {
        return hrUserOfferMapper.deleteHrUserOfferByIds(ids);
    }

    /**
     * 删除Offer发放信息
     *
     * @param id Offer发放主键
     * @return 结果
     */
    @Override
    public int deleteHrUserOfferById(String id)
    {
        return hrUserOfferMapper.deleteHrUserOfferById(id);
    }
}
