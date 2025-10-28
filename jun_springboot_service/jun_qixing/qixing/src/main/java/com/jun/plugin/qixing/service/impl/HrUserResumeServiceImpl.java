package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.HrUserResumeMapper;
import com.jun.plugin.qixing.entity.HrUserResumeV2Entity;
import com.jun.plugin.qixing.service.HrUserResumeService;


@Service("hrUserResumeService")
public class HrUserResumeServiceImpl extends ServiceImpl<HrUserResumeMapper, HrUserResumeV2Entity> implements HrUserResumeService {


}
