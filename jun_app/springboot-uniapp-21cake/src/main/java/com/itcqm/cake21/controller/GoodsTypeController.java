package com.itcqm.cake21.controller;


import com.itcqm.cake21.entity.GoodsType;
import com.itcqm.cake21.service.GoodsTypeService;
import com.itcqm.commons.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CQM
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/goods-type")
public class GoodsTypeController {

    @Resource
    GoodsTypeService goodsTypeService;

    @GetMapping("/findAll")
    public CommonResult findAll() {
        return CommonResult.success(goodsTypeService.list());
    }
}
