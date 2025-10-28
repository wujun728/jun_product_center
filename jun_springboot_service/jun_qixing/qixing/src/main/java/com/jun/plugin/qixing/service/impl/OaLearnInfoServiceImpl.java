package com.jun.plugin.qixing.service.impl;

import com.jun.plugin.qixing.entity.OaLearnInfoV2Entity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.OaLearnInfoMapper;
import com.jun.plugin.qixing.service.OaLearnInfoService;


@Service("oaLearnInfoService")
public class OaLearnInfoServiceImpl extends ServiceImpl<OaLearnInfoMapper, OaLearnInfoV2Entity> implements OaLearnInfoService {


}
