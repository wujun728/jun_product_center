package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.PjCustomerMapper;
import com.jun.plugin.qixing.entity.PjCustomerEntity;
import com.jun.plugin.qixing.service.PjCustomerService;


@Service("pjCustomerService")
public class PjCustomerServiceImpl extends ServiceImpl<PjCustomerMapper, PjCustomerEntity> implements PjCustomerService {


}