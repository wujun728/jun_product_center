package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.BizMailMapper;
import com.jun.plugin.qixing.entity.BizMailEntity;
import com.jun.plugin.qixing.service.BizMailService;


@Service("bizMailService")
public class BizMailServiceImpl extends ServiceImpl<BizMailMapper, BizMailEntity> implements BizMailService {


}