package com.ruoyi.qixing.service;
import com.ruoyi.qixing.domain.BizTest;
import java.util.List;
public interface IBizTestService {
    public List<BizTest> selectBizTestList(BizTest bizTest);
    public BizTest selectBizTestById(String id);
    public int insertBizTest(BizTest bizTest);
    public int updateBizTest(BizTest bizTest);
    public int deleteBizTestByIds(String[] ids);
}