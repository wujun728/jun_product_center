package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.OaOfficeCountMapper;
import com.jun.plugin.qixing.entity.OaOfficeCountEntity;
import com.jun.plugin.qixing.service.OaOfficeCountService;


@Service("oaOfficeCountService")
public class OaOfficeCountServiceImpl extends ServiceImpl<OaOfficeCountMapper, OaOfficeCountEntity> implements OaOfficeCountService {


}