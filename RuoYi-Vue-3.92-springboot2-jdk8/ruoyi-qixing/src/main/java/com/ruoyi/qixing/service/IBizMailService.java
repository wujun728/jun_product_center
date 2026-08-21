package com.ruoyi.qixing.service;
import com.ruoyi.qixing.domain.BizMail;
import java.util.List;
public interface IBizMailService {
    public List<BizMail> selectBizMailList(BizMail bizMail);
    public BizMail selectBizMailById(String id);
    public int insertBizMail(BizMail bizMail);
    public int updateBizMail(BizMail bizMail);
    public int deleteBizMailByIds(String[] ids);
}