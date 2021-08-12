package com.ruoyi.citylife.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.citylife.domain.CActivity;
import com.ruoyi.citylife.mapper.CActivityMapper;
import com.ruoyi.citylife.service.ICActivityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 活动Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-17
 */
@Service
public class CActivityServiceImpl extends ServiceImpl<CActivityMapper, CActivity> implements ICActivityService {
  
  
  @Override
  public ArrayList<CActivity> getSwiperActivity() {
    return baseMapper.getSwiperActivity();
  }
  
  @Override
  public ArrayList<CActivity> getActivityList(Integer pageNum, Integer pageSize) {
    return baseMapper.getActivityList((pageNum-1)*pageSize,pageSize);
  }
}
