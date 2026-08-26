package com.ruoyi.template.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.template.domain.*;
import com.ruoyi.template.enums.FormTypeEnum;
import com.ruoyi.template.mapper.TemplateMapper;
import com.ruoyi.template.mapper.TemplateSourceTargetMapper;
import com.ruoyi.template.module.*;
import com.ruoyi.template.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 模板配置Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class TemplateServiceImpl implements ITemplateService {
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private ITemplateAttachmentService templateAttachmentService;
    @Autowired
    private ITemplateDynamicFormService templateDynamicFormService;
    @Autowired
    private ITemplateMainTextService templateMainTextService;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private ITemplateMessageNoticeService templateMessageNoticeService;
    @Autowired
    private ITemplateTypeService templateTypeService;

    /**
     * 查询模板配置
     *
     * @param id 模板配置主键
     * @return 模板配置
     */
    @Override
    public Template getTemplateById(String id) {
        return templateMapper.selectTemplateById(id);
    }

    /**
     * 查询模板配置
     *
     * @param id 模板配置主键
     * @return
     */
    @Override
    public TemplateDTO getTemplateDTOById(String id) {
        Template template = getTemplateById(id);
        if (template == null) {
            return null;
        }
        TemplateDTO dto = TemplateSourceTargetMapper.INSTANCE.convertTemplateDTO(template);
        if (StringUtils.equals(Constants.YES_VALUE, template.getAttachFlag())) {
            TemplateAttachment attachment = templateAttachmentService.getTemplateAttachmentByTemplateId(template.getId());
            dto.setAttachment(attachment);
        }
        // 动态表单才填充
        if (StringUtils.equals(FormTypeEnum.DYNAMIC.getCode(), template.getFormType())) {
            TemplateDynamicForm dynamicForm = templateDynamicFormService.getTemplateDynamicFormById(template.getFormId());
            dto.setDynamicForm(dynamicForm);
        }
        if (StringUtils.equals(Constants.YES_VALUE, template.getMainTextFlag())) {
            TemplateMainText mainText = templateMainTextService.getByTemplateId(template.getId());
            TemplateMainTextDTO mainTextDTO = TemplateSourceTargetMapper.INSTANCE.copyTemplateMainTextDTO(mainText);
            if (mainText != null && StringUtils.isNotBlank(mainText.getFileId())) {
                FileStorage fileStorage = fileStorageService.getFileStorageByFileId(mainText.getFileId());
                if (fileStorage != null) {
                    mainTextDTO.setFileName(fileStorage.getFileName());
                    mainTextDTO.setExtendName(fileStorage.getExtendName());
                }
            }
            dto.setMainText(mainTextDTO);
        }
        if (StringUtils.equals(Constants.YES_VALUE, template.getMessageNoticeFlag())) {
            TemplateMessageNotice messageNotice = templateMessageNoticeService.getByTemplateId(template.getId());
            TemplateMessageNoticeDTO messageNoticeDTO = TemplateSourceTargetMapper.INSTANCE.copyTemplateMessageNoticeDTO(messageNotice);
            dto.setMessageNotice(messageNoticeDTO);
        }
        return dto;
    }

    /**
     * 查询模板配置列表
     *
     * @param template 模板配置
     * @return 模板配置
     */
    @Override
    public List<Template> listTemplate(Template template) {
        template.setDelFlag(WhetherStatus.NO.getCode());
        List<Template> result = templateMapper.selectTemplateList(template);
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptyList();
        }
        List<String> templateTypes = result.stream().map(Template::getType).distinct().collect(Collectors.toList());
        List<TemplateType> templateTypList = templateTypeService.listTemplateType(templateTypes);
        Map<String, String> templateMap = templateTypList.stream().collect(Collectors.toMap(TemplateType::getId, TemplateType::getName));
        result.stream().forEach(t -> {
            t.setTypeName(templateMap.get(t.getType()));
        });
        return result;
    }

    /**
     * 新增模板配置
     *
     * @param templateDTO 模板配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveTemplate(TemplateDTO templateDTO) {
        Template template = TemplateSourceTargetMapper.INSTANCE.convertTemplate(templateDTO);
        template.setId(IdUtils.fastSimpleUUID());
        template.setCreateId(SecurityUtils.getUserId());
        template.setCreateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        template.setCreateTime(DateUtils.getNowDate());
        handleAttachment(template.getId(), template.getAttachFlag(), templateDTO.getAttachment());
        handleMainText(template.getId(), template.getMainTextFlag(), templateDTO.getMainText());
        handleMessageNotice(template.getId(), template.getMessageNoticeFlag(), templateDTO.getMessageNotice());
        return templateMapper.insertTemplate(template);
    }

    /**
     * 修改模板配置
     *
     * @param templateDTO 模板配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTemplate(TemplateDTO templateDTO) {
        Template template = templateMapper.selectTemplateById(templateDTO.getId());
        if (template == null) {
            throw new BaseException("记录不存在");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Template newTemplate = TemplateSourceTargetMapper.INSTANCE.copyTemplate(templateDTO);
        template.setUpdateId(loginUser.getUserId());
        template.setUpdateBy(loginUser.getUser().getNickName());
        template.setUpdateTime(DateUtils.getNowDate());
        template.setEnableFlag(WhetherStatus.NO.getCode());
        template.setDelFlag(Constants.YES_VALUE);
        templateMapper.updateTemplate(template);

        newTemplate.setId(IdUtils.fastSimpleUUID());
        newTemplate.setCreateId(loginUser.getUserId());
        newTemplate.setCreateBy(loginUser.getUser().getNickName());
        newTemplate.setCreateTime(DateUtils.getNowDate());
        handleAttachment(newTemplate.getId(), newTemplate.getAttachFlag(), templateDTO.getAttachment());
        handleMainText(newTemplate.getId(), newTemplate.getMainTextFlag(), templateDTO.getMainText());
        handleMessageNotice(newTemplate.getId(), newTemplate.getMessageNoticeFlag(), templateDTO.getMessageNotice());
        return templateMapper.insertTemplate(newTemplate);
    }

    /**
     * 批量删除模板配置
     *
     * @param ids 需要删除的模板配置主键
     * @return 结果
     */
    @Override
    public int deleteTemplateByIds(String[] ids) {
        return templateMapper.deleteTemplateByIds(ids);
    }

    /**
     * 更新模板配置状态
     *
     * @param template 模板
     * @return Integer
     */
    @Override
    public int changeEnableFlag(Template template) {
        return templateMapper.changeEnableFlag(template);
    }

    /**
     * 查询新启流程模板列表
     *
     * @return List<TemplateModel>
     */
    @Override
    public List<TemplateModel> listNewStartTemplate() {
        List<TemplateModel> templateModels = new ArrayList<>();
        Template template = new Template();
        List<Template> templates = templateMapper.selectNewStartTemplateList(template);
        if (CollectionUtils.isEmpty(templates)) {
            return templateModels;
        }
        // 按类型分组
        Map<String, List<Template>> templateTypeMap = templates.stream()
                .collect(Collectors.groupingBy(
                        Template::getType,
                        () -> new LinkedHashMap<>(),
                        Collectors.toList()
                ));
        // 按模板类型的顺序进行排序
        List<String> templateTypes = templates.stream().map(Template::getType).distinct().collect(Collectors.toList());
        List<TemplateType> templateTypList = templateTypeService.listTemplateType(templateTypes);
        for (TemplateType templateType : templateTypList) {
            String typeId = templateType.getId();
            if (!templateTypeMap.containsKey(typeId)) {
                continue;
            }
            TemplateModel templateModel = new TemplateModel();
            templateModel.setType(templateType.getId());
            templateModel.setTypeName(templateType.getName());
            templateModel.setTemplates(templateTypeMap.get(templateType.getId()));
            templateModels.add(templateModel);
        }
        return templateModels;
    }

    /**
     * 获取模板选择列表
     *
     * @return
     */
    @Override
    public List<TemplateOption> getSelectTemplateList() {
        Template template = new Template();
        List<Template> templates = templateMapper.selectNewStartTemplateList(template);
        if (CollectionUtils.isEmpty(templates)) {
            return Collections.emptyList();
        }
        List<TemplateOption> templateOptions = new ArrayList<>();
        for (Template template1 : templates) {
            TemplateOption templateOption = new TemplateOption();
            templateOption.setTemplateId(template1.getId());
            templateOption.setTemplateName(template1.getName());
            templateOptions.add(templateOption);
        }
        return templateOptions;
    }

    /**
     * 处理附件（为了不影响已在途的文件，每次模板的修改都新增）
     *
     * @param templateId 模板ID
     * @param attachFlag 是否有附件
     * @param attachment 附件集合
     */
    private void handleAttachment(String templateId, String attachFlag, TemplateAttachment attachment) {
        if (attachment == null || StringUtils.equals(Constants.NO_VALUE, attachFlag)) {
            return;
        }
        String userId = SecurityUtils.getLoginUser().getUserId();
        attachment.setId(IdUtils.fastSimpleUUID());
        attachment.setTemplateId(templateId);
        attachment.setCreateId(userId);
        attachment.setCreateTime(DateUtils.getNowDate());
        templateAttachmentService.saveTemplateAttachment(attachment);
    }

    /**
     * 处理正文（为了不影响已在途的文件，每次模板的修改都新增）
     *
     * @param templateId
     * @param mainTextFlag
     * @param mainText
     */
    private void handleMainText(String templateId, String mainTextFlag, TemplateMainText mainText) {
        if (mainText == null || StringUtils.equals(Constants.NO_VALUE, mainTextFlag)) {
            return;
        }
        if (StringUtils.equals(mainText.getType(), Constants.YES_VALUE)) {
            mainText.setLimitSize(null);
            mainText.setLimitType(null);
        } else {
            mainText.setFileId(null);
        }
        String userId = SecurityUtils.getLoginUser().getUserId();
        mainText.setId(IdUtils.fastSimpleUUID());
        mainText.setTemplateId(templateId);
        mainText.setCreateId(userId);
        mainText.setCreateTime(DateUtils.getNowDate());
        templateMainTextService.saveTemplateMainText(mainText);
    }

    /**
     * 处理消息（为了不影响已在途的文件，每次模板的修改都新增）
     *
     * @param templateId
     * @param messageNoticeFlag
     * @param messageNotice
     */
    private void handleMessageNotice(String templateId, String messageNoticeFlag, TemplateMessageNotice messageNotice) {
        if (messageNotice == null || StringUtils.equals(Constants.NO_VALUE, messageNoticeFlag)) {
            return;
        }
        messageNotice.setId(IdUtils.fastSimpleUUID());
        messageNotice.setTemplateId(templateId);
        messageNotice.setCreateId(SecurityUtils.getLoginUser().getUserId());
        messageNotice.setCreateTime(DateUtils.getNowDate());
        templateMessageNoticeService.saveTemplateMessageNotice(messageNotice);
    }
}
