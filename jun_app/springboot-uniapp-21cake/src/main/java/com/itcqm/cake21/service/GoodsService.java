package com.itcqm.cake21.service;

import com.itcqm.cake21.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CQM
 * @since 2020-09-12
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 查询所有商品信息
     *
     * @return
     */
    List<Goods> findAll();

    /**
     * 分类查询商品信息
     *
     * @param typeId 商品分类
     * @return
     */
    List<Goods> findAllByType(Integer typeId);

    /**
     * 查询新品信息
     *
     * @return
     */
    List<Goods> findAllNew();


    /**
     * 查询单个商品信息 详情页
     *
     * @return
     */
    Goods findOneByDetail(Integer id);

}
