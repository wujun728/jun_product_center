package com.ruoyi.citylife.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.citylife.domain.CActivity;

import java.util.ArrayList;

/**
 * 活动Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-17
 */
public interface CActivityMapper extends BaseMapper<CActivity> {
  
  ArrayList<CActivity> getSwiperActivity();
  
  ArrayList<CActivity> getActivityList(int i, Integer pageSize);
}
