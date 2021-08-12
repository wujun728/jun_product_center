package com.ruoyi.citylife.controller;

import com.ruoyi.citylife.domain.CActivity;
import com.ruoyi.citylife.service.ICActivityService;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/*
 *@DESCRIPTION
 *@author Ye
 *@create 2020/11/17 23:03
 */
@RestController
@RequestMapping("citylife/nocheck")
public class FrontNoCheckController {
  @Autowired
  private ICActivityService icActivityService;
  @GetMapping("getSwiperActivity")
  @Cacheable(value = "getSwiperActivity",key = "'swiper'",cacheManager = "redisExpire")
  public R getSwiperActivity() {
    ArrayList<CActivity> list = icActivityService.getSwiperActivity();
    return R.success("swiperList", list);
  }
  @GetMapping("getActivityList/{pageNum}/{pageSize}")
  @Cacheable(value = "getActivityList",key = "'list'",cacheManager = "redisExpire")
  public R getActivityList(@PathVariable Integer pageNum,@PathVariable Integer pageSize) {
    ArrayList<CActivity> list = icActivityService.getActivityList(pageNum,pageSize);
    return R.success("activityList", list);
  }
}
