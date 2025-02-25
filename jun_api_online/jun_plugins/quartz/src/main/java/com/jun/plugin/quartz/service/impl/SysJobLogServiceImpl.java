package com.jun.plugin.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.quartz.service.SysJobLogService;
import com.jun.plugin.quartz.entity.SysJobLogEntity;
import com.jun.plugin.quartz.mapper.SysJobLogMapper;

import org.springframework.stereotype.Service;

/**
 * 定时任务 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysJobLogService")
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLogEntity> implements SysJobLogService {


}