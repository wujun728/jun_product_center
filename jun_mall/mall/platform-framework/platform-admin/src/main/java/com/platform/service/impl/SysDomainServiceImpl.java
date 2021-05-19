package com.platform.service.impl;

import com.platform.dao.SysDomainDao;
import com.platform.entity.SysDomainEntity;
import com.platform.service.SysDomainService;
import com.platform.utils.IdUtil;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 域对象Service实现类
 *
 * @author lipengjun
 * @date 2017-11-20 18:05:59
 */
@Service("domainService")
public class SysDomainServiceImpl implements SysDomainService {
    @Autowired
    private SysDomainDao domainDao;

    @Override
    public SysDomainEntity queryObject(String id) {
        return domainDao.queryObject(id);
    }

    @Override
    public List<SysDomainEntity> queryList(Map<String, Object> map) {
        return domainDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return domainDao.queryTotal(map);
    }

    @Override
    public int save(SysDomainEntity domain) {
        domain.setId(IdUtil.createIdbyUUID());
        domain.setCreateUser(ShiroUtils.getUserId());
        domain.setCreateTime(new Date());
        return domainDao.save(domain);
    }

    @Override
    public int update(SysDomainEntity domain) {
        domain.setUpdateUser(ShiroUtils.getUserId());
        domain.setUpdateTime(new Date());
        return domainDao.update(domain);
    }

    @Override
    public int delete(String id) {
        return domainDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return domainDao.deleteBatch(ids);
    }
}
