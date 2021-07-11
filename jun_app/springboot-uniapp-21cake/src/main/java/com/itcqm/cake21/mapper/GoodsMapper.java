package com.itcqm.cake21.mapper;

import com.itcqm.cake21.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CQM
 * @since 2020-09-12
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 查询所有商品信息
     *
     * @return
     */
    List<Goods> findAll();

    /**
     * 分类查询商品信息
     *
     * @param typeId
     * @return
     */
    List<Goods> findAllByType(@Param("typeId") Integer typeId);

    /**
     * 查询新品信息
     *
     * @return
     */
    @Select("select id,name,title,new_img from goods where state = 1")
    List<Goods> findAllNew();


    /**
     * 查询单个商品信息 详情页
     *
     * @param id 商品id
     * @return
     */
    Goods findOneByDetail(@Param("id") Integer id);
}
