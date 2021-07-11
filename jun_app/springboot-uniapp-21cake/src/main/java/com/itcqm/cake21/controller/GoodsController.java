package com.itcqm.cake21.controller;


import com.itcqm.cake21.entity.Goods;
import com.itcqm.cake21.service.GoodsService;
import com.itcqm.commons.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CQM
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;

    /**
     * 查询所有商品信息
     *
     * @return
     */
    @GetMapping("/find/all")
    public CommonResult findAll() {
        return CommonResult.success(goodsService.findAll());
    }

    /**
     * 分类查询商品信息
     *
     * @param typeId 商品分类
     * @return
     */
    @GetMapping("/find/all/byType/{typeId}")
    public CommonResult findAllByType(@PathVariable("typeId") Integer typeId) {
        return CommonResult.success(goodsService.findAllByType(typeId));
    }


    /**
     * 查询所有新品信息
     *
     * @return 新品集合
     */
    @GetMapping("/find/all/toNew")
    public CommonResult findAllNew() {
        return CommonResult.success(goodsService.findAllNew());
    }

    /**
     * 查询单个商品信息 详情页
     *
     * @param id
     * @return
     */
    @GetMapping("/find/one/toDetail/{id}")
    public CommonResult findOneByDetail(@PathVariable("id") int id) {
        return CommonResult.success(goodsService.findOneByDetail(id));
    }

}
