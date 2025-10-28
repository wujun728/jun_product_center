package com.ruoyi.logic.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.logic.mapper.FGoodsMapper;
import com.ruoyi.logic.domain.FGoods;
import com.ruoyi.logic.service.IFGoodsService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;

/**
 * 商品信息Service业务层处理
 *
 * @author hqy
 * @date 2025-01-13
 */
@Service
public class FGoodsServiceImpl implements IFGoodsService {
    @Autowired
    private FGoodsMapper fGoodsMapper;

    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public FGoods selectFGoodsById(String id) {
        return fGoodsMapper.selectFGoodsById(id);
    }

    /**
     * 查询商品信息列表
     *
     * @param fGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<FGoods> selectFGoodsList(FGoods fGoods) {
        return fGoodsMapper.selectFGoodsList(fGoods);
    }

    /**
     * 新增商品信息
     *
     * @param fGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertFGoods(FGoods fGoods) {
        if (fGoods.getId() == null) {
            fGoods.setId(IdUtils.fastSimpleUUID());
        }
        fGoods.setCreateTime(DateUtils.getNowDate());
        SysUser user = SecurityUtils.getLoginUser().getUser();
        fGoods.setCreateBy(user.getNickName());
        fGoods.setCreateName(String.valueOf(user.getUserId()));
        return fGoodsMapper.insertFGoods(fGoods);
    }

    /**
     * 修改商品信息
     *
     * @param fGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateFGoods(FGoods fGoods) {
        fGoods.setUpdateTime(DateUtils.getNowDate());
        SysUser user = SecurityUtils.getLoginUser().getUser();
        fGoods.setUpdateBy(user.getNickName());
        fGoods.setUpdateName(String.valueOf(user.getUserId()));
        return fGoodsMapper.updateFGoods(fGoods);
    }

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteFGoodsByIds(String[] ids) {
        return fGoodsMapper.deleteFGoodsByIds(ids);
    }

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteFGoodsById(String id) {
        return fGoodsMapper.deleteFGoodsById(id);
    }
}
