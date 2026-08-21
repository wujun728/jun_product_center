package com.ruoyi.qixing.service;

import com.ruoyi.qixing.domain.BizCommon;
import java.util.List;

public interface IBizCommonService {
    public List<BizCommon> selectBizCommonList(BizCommon bizCommon);
    public BizCommon selectBizCommonById(String id);
    public int insertBizCommon(BizCommon bizCommon);
    public int updateBizCommon(BizCommon bizCommon);
    public int deleteBizCommonByIds(String[] ids);
}