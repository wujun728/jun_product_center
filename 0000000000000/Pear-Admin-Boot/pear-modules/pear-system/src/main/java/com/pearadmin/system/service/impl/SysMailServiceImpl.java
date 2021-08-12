package com.pearadmin.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.tools.secure.SecurityUtil;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysMail;
import com.pearadmin.system.domain.SysUser;
import com.pearadmin.system.mapper.SysMailMapper;
import com.pearadmin.system.service.ISysMailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysMailServiceImpl implements ISysMailService {

    @Resource
    private SysMailMapper sysMailMapper;

    @Resource
    private MailAccount mailAccount;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(SysMail sysMail) {
        if (sendMail(sysMail)) {
            sysMail.setMailId(SequenceUtil.makeStringId());
            sysMail.setCreateBy(((SysUser) SecurityUtil.currentUserObj()).getUsername());
            return sysMailMapper.insert(sysMail);
        } else {
            return 0;
        }
    }

    @Override
    public List<SysMail> list(SysMail sysMail) {
        return sysMailMapper.selectList(sysMail);
    }

    @Override
    public PageInfo<SysMail> page(SysMail sysMail, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysMail> sysMails = sysMailMapper.selectList(sysMail);
        return new PageInfo<>(sysMails);
    }

    @Override
    public Boolean sendMail(SysMail sysMail) {
        ArrayList<String> tos = CollectionUtil.newArrayList(StrUtil.split(sysMail.getReceiver(), ";"));
        String send = MailUtil.send(mailAccount, tos, ObjectUtil.isEmpty(sysMail.getSubject()) ? null : sysMail.getSubject(), sysMail.getContent(), false);
        return ObjectUtil.isNotEmpty(send);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeById(String id) {
        return sysMailMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeByIds(List<String> ids) {
        return sysMailMapper.deleteByIds(ids);
    }

}
