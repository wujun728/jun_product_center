package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.HrAssessmentUserRecordMapper;
import com.jun.plugin.qixing.entity.HrAssessmentUserRecordEntity;
import com.jun.plugin.qixing.service.HrAssessmentUserRecordService;


@Service("hrAssessmentUserRecordService")
public class HrAssessmentUserRecordServiceImpl extends ServiceImpl<HrAssessmentUserRecordMapper, HrAssessmentUserRecordEntity> implements HrAssessmentUserRecordService {


}