package com.ruoyi.qixing.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.BizCommonMapper;
import com.ruoyi.qixing.domain.BizCommon;
import com.ruoyi.qixing.service.IBizCommonService;

@Service
public class BizCommonServiceImpl implements IBizCommonService {
    @Autowired
    private BizCommonMapper bizCommonMapper;

    @Override
    public List<BizCommon> selectBizCommonList(BizCommon bizCommon) {
        return bizCommonMapper.selectBizCommonList(bizCommon);
    }

    @Override
    public BizCommon selectBizCommonById(String id) {
        return bizCommonMapper.selectBizCommonById(id);
    }

    @Override
    public int insertBizCommon(BizCommon bizCommon) {
        return bizCommonMapper.insertBizCommon(bizCommon);
    }

    @Override
    public int updateBizCommon(BizCommon bizCommon) {
        return bizCommonMapper.updateBizCommon(bizCommon);
    }

    @Override
    public int deleteBizCommonByIds(String[] ids) {
        return bizCommonMapper.deleteBizCommonByIds(ids);
    }
}