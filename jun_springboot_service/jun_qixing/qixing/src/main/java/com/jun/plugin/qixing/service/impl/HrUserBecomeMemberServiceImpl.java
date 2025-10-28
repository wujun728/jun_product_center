package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.HrUserBecomeMemberMapper;
import com.jun.plugin.qixing.entity.HrUserBecomeMemberEntity;
import com.jun.plugin.qixing.service.HrUserBecomeMemberService;


@Service("hrUserBecomeMemberService")
public class HrUserBecomeMemberServiceImpl extends ServiceImpl<HrUserBecomeMemberMapper, HrUserBecomeMemberEntity> implements HrUserBecomeMemberService {


}