package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.OaNotesInfoMapper;
import com.jun.plugin.qixing.entity.OaNotesInfoEntity;
import com.jun.plugin.qixing.service.OaNotesInfoService;


@Service("oaNotesInfoService")
public class OaNotesInfoServiceImpl extends ServiceImpl<OaNotesInfoMapper, OaNotesInfoEntity> implements OaNotesInfoService {


}