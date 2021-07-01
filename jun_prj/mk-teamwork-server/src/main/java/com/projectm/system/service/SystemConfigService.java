package com.projectm.system.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.constant.Constants;
import com.framework.security.util.UserUtil;
import com.projectm.login.entity.LoginUser;
import com.projectm.system.domain.Notify;
import com.projectm.system.domain.SystemConfig;
import com.projectm.system.mapper.NotifyMapper;
import com.projectm.system.mapper.SystemConfigMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemConfigService extends ServiceImpl<SystemConfigMapper, SystemConfig> {

}
