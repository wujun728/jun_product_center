package com.ruoyi.qixing.mapper;

import com.ruoyi.qixing.domain.BizTest;
import java.util.List;

public interface BizTestMapper {
    public List<BizTest> selectBizTestList(BizTest bizTest);
    public BizTest selectBizTestById(String id);
    public int insertBizTest(BizTest bizTest);
    public int updateBizTest(BizTest bizTest);
    public int deleteBizTestByIds(String[] ids);
}