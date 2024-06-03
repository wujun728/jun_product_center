package com.jun.plugin.system.service;

import com.jun.plugin.system.vo.resp.HomeRespVO;
import com.jun.plugin.system.vo.resp.UserInfoRespVO;

/**
 * 首页
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface HomeService {

    /**
     * 获取首页信息
     *
     * @param userId userId
     * @return HomeRespVO
     */
    HomeRespVO getHomeInfo(String userId);
    
    /**
     * 获取首页信息
     *
     * @param userId userId
     * @return HomeRespVO
     */
    UserInfoRespVO getUserInfo(String userId);
}
