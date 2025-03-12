package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.HrUserInterviewMapper;
import com.jun.plugin.qixing.entity.HrUserInterviewEntity;
import com.jun.plugin.qixing.service.HrUserInterviewService;


@Service("hrUserInterviewService")
public class HrUserInterviewServiceImpl extends ServiceImpl<HrUserInterviewMapper, HrUserInterviewEntity> implements HrUserInterviewService {


}