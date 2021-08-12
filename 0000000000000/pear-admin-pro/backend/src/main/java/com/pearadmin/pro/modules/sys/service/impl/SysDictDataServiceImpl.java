package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.repository.SysDictDataRepository;
import com.pearadmin.pro.modules.sys.param.SysDictDataRequest;
import com.pearadmin.pro.modules.sys.service.SysDictDataService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataRepository, SysDictData> implements SysDictDataService {

    @Resource
    private SysDictDataRepository sysDictDataRepository;

    @Override
    public List<SysDictData> list(SysDictDataRequest request) {
        return sysDictDataRepository.selectDictData(request);
    }

    @Override
    public PageResponse<SysDictData> page(SysDictDataRequest request) {
        return Pageable.of(request,(()-> sysDictDataRepository.selectDictData(request)));
    }
}
