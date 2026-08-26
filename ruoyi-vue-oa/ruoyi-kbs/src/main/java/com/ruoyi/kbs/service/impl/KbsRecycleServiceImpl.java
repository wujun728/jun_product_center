package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.kbs.domain.KbsRecycle;
import com.ruoyi.kbs.handle.RecycleHandleService;
import com.ruoyi.kbs.mapper.KbsRecycleMapper;
import com.ruoyi.kbs.service.IKbsRecycleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 知识库文档回收站Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsRecycleServiceImpl implements IKbsRecycleService {
    @Autowired
    private KbsRecycleMapper kbsRecycleMapper;
    @Autowired
    private RecycleHandleService recycleHandleService;

    /**
     * 查询知识库文档回收站列表
     * 
     * @param kbsRecycle 知识库文档回收站
     * @return 知识库文档回收站
     */
    @Override
    public List<KbsRecycle> listKbsRecycle(KbsRecycle kbsRecycle) {
        kbsRecycle.setCreateId(SecurityUtils.getUserId());
        return kbsRecycleMapper.selectKbsRecycleList(kbsRecycle);
    }

    /**
     * 彻底批量删除知识库文档回收站
     * 
     * @param ids 需要删除的知识库文档回收站主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteKbsRecycleByIds(String[] ids) {
        List<KbsRecycle> kbsRecycles = kbsRecycleMapper.selectKbsRecycleByIds(ids);
        if (CollectionUtils.isEmpty(kbsRecycles)) {
            return 0;
        }
        recycleHandleService.completelyDelete(kbsRecycles);
        return kbsRecycleMapper.deleteKbsRecycleByIds(ids);
    }

    /**
     * 批量插入回收信息
     *
     * @param recycles 回收信息列表
     */
    @Override
    public int saveBatch(List<KbsRecycle> recycles) {
        return kbsRecycleMapper.batchInsert(recycles);
    }

    /**
     * 恢复回收信息
     *
     * @param ids   回收信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recover(String[] ids) {
        List<KbsRecycle> kbsRecycles = kbsRecycleMapper.selectKbsRecycleByIds(ids);
        if (CollectionUtils.isEmpty(kbsRecycles)) {
            return 0;
        }
        recycleHandleService.recover(kbsRecycles);
        return kbsRecycleMapper.deleteKbsRecycleByIds(ids);
    }
}
