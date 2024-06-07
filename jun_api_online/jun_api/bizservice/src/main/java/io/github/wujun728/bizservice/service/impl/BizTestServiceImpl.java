package io.github.wujun728.bizservice.service.impl;

import io.github.wujun728.bizservice.service.BizTestService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.github.wujun728.bizservice.mapper.BizTestMapper;
import io.github.wujun728.bizservice.entity.BizTestEntity;


@Service("bizTestService")
public class BizTestServiceImpl extends ServiceImpl<BizTestMapper, BizTestEntity> implements BizTestService {


}