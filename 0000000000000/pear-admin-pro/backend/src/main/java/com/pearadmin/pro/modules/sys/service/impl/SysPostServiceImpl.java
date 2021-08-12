package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.repository.SysPostRepository;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import com.pearadmin.pro.modules.sys.service.SysPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostRepository, SysPost> implements SysPostService {

    @Resource
    private SysPostRepository sysPostRepository;

    @Override
    public List<SysPost> list(SysPostRequest request) {
        return sysPostRepository.selectPost(request);
    }

    @Override
    public PageResponse<SysPost> page(SysPostRequest request) {
        return Pageable.of(request,(()-> sysPostRepository.selectPost(request)));
    }
}
