package com.jun.plugin.qixing.service.impl;

import com.jun.plugin.qixing.entity.BizTestV2Entity;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.BizTestMapper;
import com.jun.plugin.qixing.service.BizTestService;


@Service("bizTestService")
public class BizTestServiceImpl extends ServiceImpl<BizTestMapper, BizTestV2Entity> implements BizTestService {


}
