package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.HrUserHireMapper;
import com.jun.plugin.qixing.entity.HrUserHireEntity;
import com.jun.plugin.qixing.service.HrUserHireService;


@Service("hrUserHireService")
public class HrUserHireServiceImpl extends ServiceImpl<HrUserHireMapper, HrUserHireEntity> implements HrUserHireService {


}