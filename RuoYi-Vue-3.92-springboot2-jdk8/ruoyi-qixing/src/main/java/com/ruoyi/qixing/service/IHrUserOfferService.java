package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserOffer;

/**
 * Offer发放Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrUserOfferService 
{
    /**
     * 查询Offer发放
     * 
     * @param id Offer发放主键
     * @return Offer发放
     */
    public HrUserOffer selectHrUserOfferById(String id);

    /**
     * 查询Offer发放列表
     * 
     * @param hrUserOffer Offer发放
     * @return Offer发放集合
     */
    public List<HrUserOffer> selectHrUserOfferList(HrUserOffer hrUserOffer);

    /**
     * 新增Offer发放
     * 
     * @param hrUserOffer Offer发放
     * @return 结果
     */
    public int insertHrUserOffer(HrUserOffer hrUserOffer);

    /**
     * 修改Offer发放
     * 
     * @param hrUserOffer Offer发放
     * @return 结果
     */
    public int updateHrUserOffer(HrUserOffer hrUserOffer);

    /**
     * 批量删除Offer发放
     * 
     * @param ids 需要删除的Offer发放主键集合
     * @return 结果
     */
    public int deleteHrUserOfferByIds(String[] ids);

    /**
     * 删除Offer发放信息
     * 
     * @param id Offer发放主键
     * @return 结果
     */
    public int deleteHrUserOfferById(String id);
}
