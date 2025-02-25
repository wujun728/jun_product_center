package com.jun.plugin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.system.entity.SysContentEntity;
import com.jun.plugin.system.mapper.SysContentMapper;
import com.jun.plugin.system.service.SysContentService;

import org.springframework.stereotype.Service;

/**
 * 内容 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysContentService")
public class SysContentServiceImpl extends ServiceImpl<SysContentMapper, SysContentEntity> implements SysContentService {


}