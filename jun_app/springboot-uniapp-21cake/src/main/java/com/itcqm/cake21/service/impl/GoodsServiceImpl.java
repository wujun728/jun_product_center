package com.itcqm.cake21.service.impl;

import com.itcqm.cake21.entity.Goods;
import com.itcqm.cake21.mapper.GoodsMapper;
import com.itcqm.cake21.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CQM
 * @since 2020-09-12
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public List<Goods> findAllByType(Integer typeId) {
        return goodsMapper.findAllByType(typeId);
    }

    @Override
    public List<Goods> findAllNew() {
        return goodsMapper.findAllNew();
    }

    @Override
    public Goods findOneByDetail(Integer id) {
        return goodsMapper.findOneByDetail(id);
    }
}
