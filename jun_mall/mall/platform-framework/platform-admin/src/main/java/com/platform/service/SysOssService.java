package com.platform.service;

import com.platform.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysOssService {

    SysOssEntity queryObject(String id);

    List<SysOssEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysOssEntity sysOss);

    void update(SysOssEntity sysOss);

    void delete(String id);

    void deleteBatch(String[] ids);
}
