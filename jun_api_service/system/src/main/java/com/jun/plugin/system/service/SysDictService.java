package com.jun.plugin.system.service;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.system.entity.SysDictEntity;

/**
 * 数据字典 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
public interface SysDictService extends IService<SysDictEntity> {
	
	JSONArray getType(String name);
	
	JSONArray getType2(String name);

}

