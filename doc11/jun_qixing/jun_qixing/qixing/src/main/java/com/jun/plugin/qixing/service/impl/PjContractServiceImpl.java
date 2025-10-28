package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.PjContractMapper;
import com.jun.plugin.qixing.entity.PjContractEntity;
import com.jun.plugin.qixing.service.PjContractService;


@Service("pjContractService")
public class PjContractServiceImpl extends ServiceImpl<PjContractMapper, PjContractEntity> implements PjContractService {


}