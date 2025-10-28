package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.PjProjectMapper;
import com.jun.plugin.qixing.entity.PjProjectEntity;
import com.jun.plugin.qixing.service.PjProjectService;


@Service("pjProjectService")
public class PjProjectServiceImpl extends ServiceImpl<PjProjectMapper, PjProjectEntity> implements PjProjectService {


}