package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.not.domain.SysInbox;
import com.pearadmin.pro.modules.not.param.SysInboxRequest;
import com.pearadmin.pro.modules.not.repository.SysInboxRepository;
import com.pearadmin.pro.modules.not.service.SysInboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysInboxServiceImpl extends ServiceImpl<SysInboxRepository, SysInbox> implements SysInboxService {

    @Resource
    private SysInboxRepository sysInboxRepository;

    @Override
    public List<SysInbox> list(SysInboxRequest request) {
        return sysInboxRepository.selectInbox(request);
    }

    @Override
    public PageResponse<SysInbox> page(SysInboxRequest request) {
        return Pageable.of(request, (()-> sysInboxRepository.selectInbox(request)));
    }
}
