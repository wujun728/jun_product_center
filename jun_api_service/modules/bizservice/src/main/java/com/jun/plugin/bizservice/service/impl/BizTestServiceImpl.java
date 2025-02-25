package com.jun.plugin.bizservice.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.bizservice.mapper.BizTestMapper;
import com.jun.plugin.bizservice.entity.BizTestEntity;
import com.jun.plugin.bizservice.service.BizTestService;


@Service("bizTestService")
public class BizTestServiceImpl extends ServiceImpl<BizTestMapper, BizTestEntity> implements BizTestService {


}