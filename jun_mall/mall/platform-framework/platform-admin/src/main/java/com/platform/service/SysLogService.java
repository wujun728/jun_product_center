package com.platform.service;

import com.platform.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public interface SysLogService {

    SysLogEntity queryObject(Long id);

    List<SysLogEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysLogEntity sysLog);

    void update(SysLogEntity sysLog);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
