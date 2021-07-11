package me.wuwenbin.noteblogv5.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.wuwenbin.noteblogv5.mapper.CashMapper;
import me.wuwenbin.noteblogv5.model.entity.Cash;
import me.wuwenbin.noteblogv5.service.CashService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CashServiceImpl extends ServiceImpl<CashMapper, Cash> implements CashService {
}
