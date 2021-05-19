package com.platform.service.impl;

import com.platform.cache.CacheUtil;
import com.platform.dao.SysDictDao;
import com.platform.entity.SysDictEntity;
import com.platform.service.SysDictService;
import com.platform.utils.IdUtil;
import com.platform.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统数据字典Service实现类
 *
 * @author lipengjun
 * @date 2017-12-25 18:26:15
 */
@Service("dictService")
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    private SysDictDao dictDao;

    @Override
    public SysDictEntity queryObject(String id) {
        return dictDao.queryObject(id);
    }

    @Override
    public List<SysDictEntity> queryList(Map<String, Object> map) {
        return dictDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dictDao.queryTotal(map);
    }

    @Override
    public int save(SysDictEntity dict) {
        dict.setId(IdUtil.createIdbyUUID());
        dict.setCreateTime(new Date());
        dict.setCreateUser(ShiroUtils.getUserId());
        dictDao.save(dict);
        CacheUtil.reloadDict();
        return 1;
    }

    @Override
    public int update(SysDictEntity dict) {
        dictDao.update(dict);
        CacheUtil.reloadDict();
        return 1;
    }

    @Override
    public int delete(String id) {
        dictDao.delete(id);
        CacheUtil.reloadDict();
        return 1;
    }

    @Override
    public int deleteBatch(String[] ids) {
        dictDao.deleteBatch(ids);
        CacheUtil.reloadDict();
        return ids.length;
    }
}
