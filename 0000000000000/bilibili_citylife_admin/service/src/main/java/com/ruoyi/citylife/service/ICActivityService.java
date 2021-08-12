package com.ruoyi.citylife.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.citylife.domain.CActivity;

import java.util.ArrayList;

/**
 * 活动Service接口
 *
 * @author ruoyi
 * @date 2020-11-17
 */
public interface ICActivityService extends IService<CActivity> {
  
  ArrayList<CActivity> getSwiperActivity();
  
  ArrayList<CActivity> getActivityList(Integer pageNum, Integer pageSize);
}
