package com.ruoyi.qixing.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.BizTestMapper;
import com.ruoyi.qixing.domain.BizTest;
import com.ruoyi.qixing.service.IBizTestService;
@Service
public class BizTestServiceImpl implements IBizTestService {
    @Autowired
    private BizTestMapper bizTestMapper;
    @Override
    public List<BizTest> selectBizTestList(BizTest bizTest) { return bizTestMapper.selectBizTestList(bizTest); }
    @Override
    public BizTest selectBizTestById(String id) { return bizTestMapper.selectBizTestById(id); }
    @Override
    public int insertBizTest(BizTest bizTest) { return bizTestMapper.insertBizTest(bizTest); }
    @Override
    public int updateBizTest(BizTest bizTest) { return bizTestMapper.updateBizTest(bizTest); }
    @Override
    public int deleteBizTestByIds(String[] ids) { return bizTestMapper.deleteBizTestByIds(ids); }
}