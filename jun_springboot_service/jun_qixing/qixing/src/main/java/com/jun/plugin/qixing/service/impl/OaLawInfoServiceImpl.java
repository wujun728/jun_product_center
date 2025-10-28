package com.jun.plugin.qixing.service.impl;

import com.jun.plugin.qixing.entity.OaLawInfoV2Entity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.OaLawInfoMapper;
import com.jun.plugin.qixing.service.OaLawInfoService;


@Service("oaLawInfoService")
public class OaLawInfoServiceImpl extends ServiceImpl<OaLawInfoMapper, OaLawInfoV2Entity> implements OaLawInfoService {


}
