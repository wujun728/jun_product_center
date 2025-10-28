package com.jun.plugin.qixing.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.qixing.mapper.PjProjectInvoiceMapper;
import com.jun.plugin.qixing.entity.PjProjectInvoiceEntity;
import com.jun.plugin.qixing.service.PjProjectInvoiceService;


@Service("pjProjectInvoiceService")
public class PjProjectInvoiceServiceImpl extends ServiceImpl<PjProjectInvoiceMapper, PjProjectInvoiceEntity> implements PjProjectInvoiceService {


}