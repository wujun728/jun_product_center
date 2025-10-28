package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.HrUserOfferMapper;
import com.jun.plugin.qixing.entity.HrUserOfferV2Entity;
import com.jun.plugin.qixing.service.HrUserOfferService;


@Service("hrUserOfferService")
public class HrUserOfferServiceImpl extends ServiceImpl<HrUserOfferMapper, HrUserOfferV2Entity> implements HrUserOfferService {


}
