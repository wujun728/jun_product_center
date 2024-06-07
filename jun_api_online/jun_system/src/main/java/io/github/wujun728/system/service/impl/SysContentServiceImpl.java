package io.github.wujun728.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.wujun728.system.entity.SysContentEntity;
import io.github.wujun728.system.mapper.SysContentMapper;
import io.github.wujun728.system.service.SysContentService;

import org.springframework.stereotype.Service;

/**
 * 内容 服务类
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysContentService")
public class SysContentServiceImpl extends ServiceImpl<SysContentMapper, SysContentEntity> implements SysContentService {


}