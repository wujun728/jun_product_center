package com.ruoyi.qixing.mapper;

import com.ruoyi.qixing.domain.BizMail;
import java.util.List;

public interface BizMailMapper {
    public List<BizMail> selectBizMailList(BizMail bizMail);
    public BizMail selectBizMailById(String id);
    public int insertBizMail(BizMail bizMail);
    public int updateBizMail(BizMail bizMail);
    public int deleteBizMailByIds(String[] ids);
}