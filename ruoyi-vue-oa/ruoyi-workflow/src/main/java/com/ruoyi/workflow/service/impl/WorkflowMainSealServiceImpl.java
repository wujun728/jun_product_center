package com.ruoyi.workflow.service.impl;

import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.business.api.IFileService;
import com.ruoyi.file.storage.upload.domain.UploadFile;
import com.ruoyi.seal.enums.SealColorEnum;
import com.ruoyi.seal.enums.SealStyleEnum;
import com.ruoyi.seal.utils.seal.SealUtil;
import com.ruoyi.tools.utils.date.LocalDateTimeUtil;
import com.ruoyi.workflow.mapper.WorkFlowSourceTargetMapper;
import com.ruoyi.workflow.module.WorkflowSealResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.workflow.mapper.WorkflowMainSealMapper;
import com.ruoyi.workflow.domain.WorkflowMainSeal;
import com.ruoyi.workflow.service.IWorkflowMainSealService;

/**
 * 正文印章Service业务层处理
 * 
 * @author wocurr.com
 * @date 2025-04-15
 */
@Slf4j
@Service
public class WorkflowMainSealServiceImpl implements IWorkflowMainSealService {
    @Autowired
    private WorkflowMainSealMapper workflowMainSealMapper;
    @Autowired
    private IFileService fileService;

    /**
     * 查询正文印章
     * 
     * @param id 正文印章主键
     * @return 正文印章
     */
    @Override
    public WorkflowMainSeal getWorkflowMainSealById(String id) {
        return workflowMainSealMapper.selectWorkflowMainSealById(id);
    }

    @Override
    public WorkflowSealResult getWorkflowDetail(String id) {
        WorkflowMainSeal mainSeal = getWorkflowMainSealById(id);
        if (mainSeal == null) {
            throw new BaseException("记录不存在！");
        }
        WorkflowSealResult detail = WorkFlowSourceTargetMapper.INSTANCE.toWorkflowSealResult(mainSeal);
        // 获取预览图片
        String previewBase64 = fileService.getImageBase64(detail.getFileId());
        detail.setPreviewBase64(previewBase64);
        return detail;
    }

    /**
     * 查询正文印章列表
     * 
     * @param workflowMainSeal 正文印章
     * @return 正文印章
     */
    @Override
    public List<WorkflowMainSeal> listWorkflowMainSeal(WorkflowMainSeal workflowMainSeal) {
        return workflowMainSealMapper.selectWorkflowMainSealList(workflowMainSeal);
    }

    @Override
    public List<WorkflowSealResult> findAllSeals() {
        WorkflowMainSeal query = new WorkflowMainSeal();
        query.setEnableFlag(Constants.YES_VALUE);
        List<WorkflowMainSeal> list = listWorkflowMainSeal(query);
        if (CollectionUtils.isEmpty(list)) {
            throw new BaseException("未配置有效印章，请前往配置！");
        }
        List<WorkflowSealResult> data = WorkFlowSourceTargetMapper.INSTANCE.toListWorkflowSealResult(list);
        data.stream().forEach(s -> {
            s.setPreviewBase64(fileService.getImageBase64(s.getFileId()));
        });
        return data;
    }

    /**
     * 新增正文印章
     * 
     * @param workflowMainSeal 正文印章
     * @return 结果
     */
    @Override
    public int saveWorkflowMainSeal(WorkflowMainSeal workflowMainSeal) {
        workflowMainSeal.setId(IdUtils.fastSimpleUUID());
        workflowMainSeal.setCreateId(SecurityUtils.getUserId());
        workflowMainSeal.setCreateTime(DateUtils.getNowDate());
        return workflowMainSealMapper.insertWorkflowMainSeal(workflowMainSeal);
    }

    /**
     * 修改正文印章
     * 
     * @param workflowMainSeal 正文印章
     * @return 结果
     */
    @Override
    public int updateWorkflowMainSeal(WorkflowMainSeal workflowMainSeal) {
        workflowMainSeal.setUpdateTime(DateUtils.getNowDate());
        return workflowMainSealMapper.updateWorkflowMainSeal(workflowMainSeal);
    }

    /**
     * 批量删除正文印章
     * 
     * @param ids 需要删除的正文印章主键
     * @return 结果
     */
    @Override
    public int deleteWorkflowMainSealByIds(String[] ids) {
        return workflowMainSealMapper.deleteWorkflowMainSealByIds(ids);
    }

    @Override
    public int changeEnableFlag(WorkflowMainSeal mainSeal) {
        WorkflowMainSeal config = workflowMainSealMapper.selectWorkflowMainSealById(mainSeal.getId());
        if (config == null) {
            throw new BaseException("记录不存在");
        }
        return workflowMainSealMapper.changeEnableFlag(mainSeal);
    }

    @Override
    public WorkflowSealResult previewMainSeal(WorkflowMainSeal mainSeal) {
        try {
            WorkflowSealResult result = new WorkflowSealResult();
            SealStyleEnum styleEnum = SealStyleEnum.getByCode(mainSeal.getStyle());
            String centerWord = styleEnum == SealStyleEnum.CIRCLE ? null : "";
            String previewStr = SealUtil.createSealToBase64(mainSeal.getSurroundWord(), centerWord, mainSeal.getUnderWord(),
                    mainSeal.getCentreWord(), SealColorEnum.getByCode(mainSeal.getColor()), styleEnum);
            result.setPreviewBase64(previewStr);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("预览失败，请稍后重试！");
        }
    }

    @Override
    public int createMainSeal(WorkflowMainSeal mainSeal) {
        if (StringUtils.isBlank(mainSeal.getSurroundWord()) || StringUtils.isBlank(mainSeal.getType())) {
            throw new BaseException("参数错误！");
        }
        String companyName = mainSeal.getSurroundWord();
        // 同类型印章不能重复
        WorkflowMainSeal query = new WorkflowMainSeal();
        query.setSurroundWord(companyName);
        query.setType(mainSeal.getType());
        List<WorkflowMainSeal> dbSealList = workflowMainSealMapper.listByName(query);
        if (CollectionUtils.isNotEmpty(dbSealList)) {
            throw new BaseException("当前印章已存在，请勿重复创建！");
        }
        try {
            // 创建印章图片
            SealStyleEnum styleEnum = SealStyleEnum.getByCode(mainSeal.getStyle());
            String centerWord = styleEnum == SealStyleEnum.CIRCLE ? null : "";
            byte[] imageBytes = SealUtil.createSealToByte(companyName, centerWord, mainSeal.getUnderWord(),
                    mainSeal.getCentreWord(), SealColorEnum.getByCode(mainSeal.getColor()), styleEnum);
            // 存储到文件服务
            String fileName = mainSeal.getSealName();
            String filePath = RuoYiConfig.getUploadPath() + Constants.SEPARATOR + LocalDateTimeUtil.formatNow(LocalDateTimeUtil.FORMAT_YMD)
                    + Constants.SEPARATOR + IdUtils.fastSimpleUUID() + Constants.DOT + Constants.PNG;
            UploadFile uploadFile = new UploadFile();
            uploadFile.setFilePath(filePath);
            uploadFile.setFilename(fileName);
            uploadFile.setExtendName(Constants.PNG);
            uploadFile.setFileBytes(imageBytes);
            String fileId = fileService.uploadFile(uploadFile);
            mainSeal.setFileId(fileId);
            mainSeal.setWidth(mainSeal.getWidth() == null ? 32 : mainSeal.getWidth());
            mainSeal.setHeight(mainSeal.getHeight() == null ? 32 : mainSeal.getHeight());
            return saveWorkflowMainSeal(mainSeal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("创建印章失败，请稍后重试！");
        }
    }
}
