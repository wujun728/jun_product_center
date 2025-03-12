package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.PjProjectMemberMapper;
import com.jun.plugin.qixing.entity.PjProjectMemberEntity;
import com.jun.plugin.qixing.service.PjProjectMemberService;


@Service("pjProjectMemberService")
public class PjProjectMemberServiceImpl extends ServiceImpl<PjProjectMemberMapper, PjProjectMemberEntity> implements PjProjectMemberService {


}