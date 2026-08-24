package com.ruoyi.qixing.service.impl;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.BizMailMapper;
import com.ruoyi.qixing.domain.BizMail;
import com.ruoyi.qixing.service.IBizMailService;
@Service
public class BizMailServiceImpl implements IBizMailService {
    @Autowired
    private BizMailMapper bizMailMapper;
    @Override
    public List<BizMail> selectBizMailList(BizMail bizMail) { return bizMailMapper.selectBizMailList(bizMail); }
    @Override
    public BizMail selectBizMailById(String id) { return bizMailMapper.selectBizMailById(id); }
    @Override
    public int insertBizMail(BizMail bizMail) {if (bizMail.getId() == null || bizMail.getId().length() == 0)
        {
            bizMail.setId(String.valueOf(IdWorker.getId()));
        }
 return bizMailMapper.insertBizMail(bizMail); }
    @Override
    public int updateBizMail(BizMail bizMail) { return bizMailMapper.updateBizMail(bizMail); }
    @Override
    public int deleteBizMailByIds(String[] ids) { return bizMailMapper.deleteBizMailByIds(ids); }
}