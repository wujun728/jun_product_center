package com.jun.plugin.snakerflow.ext.service.impl;

import com.jun.plugin.snakerflow.ext.entity.ExtLog;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.snakerflow.ext.mapper.ExtLogMapper;
import com.jun.plugin.snakerflow.ext.service.IExtLogService;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * 
 * @since 2021-08-16
 */
@Service
public class ExtLogServiceImpl extends ServiceImpl<ExtLogMapper, ExtLog> implements IExtLogService {

}
