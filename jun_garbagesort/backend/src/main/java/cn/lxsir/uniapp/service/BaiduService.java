package cn.lxsir.uniapp.service;

import java.util.Map;

/**
 * Auth:luoxiang
 * Time:2019/7/7 7:47 PM
 * Desc:    百度api 接口
 */
public interface BaiduService {

    Map<String,Object> imageClassify(String fileName);

    Map<String,Object> apiSpecch(String fileName);
}
