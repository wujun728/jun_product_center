package com.ruoyi.logic.mapper;

import java.util.List;

import com.ruoyi.logic.domain.FGoods;
import com.ruoyi.common.mybatis.mapper.BaseMapperX;

/**
 * 商品信息Mapper接口
 *
 * @author hqy
 * @date 2025-01-13
 */
public interface FGoodsMapper extends BaseMapperX<FGoods> {
    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public FGoods selectFGoodsById(String id);

    /**
     * 查询商品信息列表
     *
     * @param fGoods 商品信息
     * @return 商品信息集合
     */
    public List<FGoods> selectFGoodsList(FGoods fGoods);

    /**
     * 新增商品信息
     *
     * @param fGoods 商品信息
     * @return 结果
     */
    public int insertFGoods(FGoods fGoods);

    /**
     * 修改商品信息
     *
     * @param fGoods 商品信息
     * @return 结果
     */
    public int updateFGoods(FGoods fGoods);

    /**
     * 删除商品信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteFGoodsById(String id);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFGoodsByIds(String[] ids);
}
