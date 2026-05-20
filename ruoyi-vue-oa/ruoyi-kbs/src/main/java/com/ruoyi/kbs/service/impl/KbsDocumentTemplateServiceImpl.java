package com.ruoyi.kbs.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.qo.KbsDocumentTemplateUpdateQo;
import com.ruoyi.kbs.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.kbs.mapper.KbsDocumentTemplateMapper;
import com.ruoyi.kbs.domain.KbsDocumentTemplate;
import com.ruoyi.kbs.service.IKbsDocumentTemplateService;

/**
 * 知识库文档模板Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentTemplateServiceImpl implements IKbsDocumentTemplateService {
    @Autowired
    private KbsDocumentTemplateMapper kbsDocumentTemplateMapper;

    /**
     * 查询知识库文档模板
     * 
     * @param id 知识库文档模板主键
     * @return 知识库文档模板
     */
    @Override
    public KbsDocumentTemplate getKbsDocumentTemplateById(String id) {
        return kbsDocumentTemplateMapper.selectKbsDocumentTemplateById(id);
    }

    /**
     * 查询知识库文档模板列表
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 知识库文档模板
     */
    @Override
    public List<KbsDocumentTemplate> listKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate) {
        return kbsDocumentTemplateMapper.selectKbsDocumentTemplateList(kbsDocumentTemplate);
    }

    /**
     * 新增知识库文档模板
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 结果
     */
    @Override
    public int saveKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate) {
        kbsDocumentTemplate.setId(IdUtils.fastSimpleUUID());
        kbsDocumentTemplate.setCreateId(SecurityUtils.getUserId());
        kbsDocumentTemplate.setCreateTime(DateUtils.getNowDate());
        return kbsDocumentTemplateMapper.insertKbsDocumentTemplate(kbsDocumentTemplate);
    }

    /**
     * 修改知识库文档模板
     * 
     * @param kbsDocumentTemplate 知识库文档模板
     * @return 结果
     */
    @Override
    public int updateKbsDocumentTemplate(KbsDocumentTemplate kbsDocumentTemplate) {
        kbsDocumentTemplate.setUpdateId(SecurityUtils.getUserId());
        kbsDocumentTemplate.setUpdateTime(DateUtils.getNowDate());
        return kbsDocumentTemplateMapper.updateKbsDocumentTemplate(kbsDocumentTemplate);
    }

    /**
     * 批量删除知识库文档模板
     * 
     * @param ids 需要删除的知识库文档模板主键
     * @return 结果
     */
    @Override
    public int deleteKbsDocumentTemplateByIds(String[] ids) {
        KbsDocumentTemplateUpdateQo qo = new KbsDocumentTemplateUpdateQo();
        qo.setIds(TransferUtil.arrayToList(ids));
        qo.setDelFlag(WhetherStatus.YES.getCode());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        qo.setUpdateId(loginUser.getUserId());
        qo.setUpdateTime(DateUtils.getNowDate());
        return kbsDocumentTemplateMapper.updateDelFlagByIds(qo);
    }
}
