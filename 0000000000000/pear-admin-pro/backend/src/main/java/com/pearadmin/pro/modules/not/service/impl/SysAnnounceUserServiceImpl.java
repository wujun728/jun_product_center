package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.not.domain.SysAnnounceUser;
import com.pearadmin.pro.modules.not.param.SysAnnounceUserRequest;
import com.pearadmin.pro.modules.not.repository.SysAnnounceUserRepository;
import com.pearadmin.pro.modules.not.service.SysAnnounceUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAnnounceUserServiceImpl extends ServiceImpl<SysAnnounceUserRepository, SysAnnounceUser>
implements SysAnnounceUserService {

    @Resource
    private SysAnnounceUserRepository sysAnnounceUserRepository;

    @Override
    public List<SysAnnounceUser> list(SysAnnounceUserRequest request) {
        return sysAnnounceUserRepository.selectAnnounceUser(request);
    }

    @Override
    public PageResponse<SysAnnounceUser> page(SysAnnounceUserRequest request) {
        return Pageable.of(request, (()-> sysAnnounceUserRepository.selectAnnounceUser(request)));
    }
}
