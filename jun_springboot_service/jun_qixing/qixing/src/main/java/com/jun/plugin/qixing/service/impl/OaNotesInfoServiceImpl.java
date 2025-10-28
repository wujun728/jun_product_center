package com.jun.plugin.qixing.service.impl;

import com.jun.plugin.qixing.entity.OaNotesInfoV2Entity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.OaNotesInfoMapper;
import com.jun.plugin.qixing.service.OaNotesInfoService;


@Service("oaNotesInfoService")
public class OaNotesInfoServiceImpl extends ServiceImpl<OaNotesInfoMapper, OaNotesInfoV2Entity> implements OaNotesInfoService {


}
