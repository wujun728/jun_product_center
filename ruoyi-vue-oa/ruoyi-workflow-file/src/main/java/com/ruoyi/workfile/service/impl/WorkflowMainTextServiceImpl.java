package com.ruoyi.workfile.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.business.api.IFileService;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.module.BookmarkData;
import com.ruoyi.file.business.module.FileQO;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.file.convert.module.ConvertFileDTO;
import com.ruoyi.file.convert.service.OfficeToPdfService;
import com.ruoyi.file.storage.contants.StorageContants;
import com.ruoyi.file.storage.context.FileServerContext;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.preview.domain.PreviewFile;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.enums.QueueEnum;
import com.ruoyi.seal.api.PdfService;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateMainText;
import com.ruoyi.template.enums.MainTextTypeEnum;
import com.ruoyi.template.service.ITemplateMainTextService;
import com.ruoyi.template.service.ITemplateService;
import com.ruoyi.tools.lock.RedisLock;
import com.ruoyi.tools.utils.pools.DataPassPools;
import com.ruoyi.workfile.domain.WorkflowMainText;
import com.ruoyi.workfile.mapper.WorkFileSourceTargetMapper;
import com.ruoyi.workfile.mapper.WorkflowMainTextMapper;
import com.ruoyi.workfile.module.ConvertPdfDTO;
import com.ruoyi.workfile.module.MainInfoResult;
import com.ruoyi.workfile.module.MainStampParam;
import com.ruoyi.workfile.module.MainTextParam;
import com.ruoyi.workfile.service.IWorkflowMainTextService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 正文Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowMainTextServiceImpl implements IWorkflowMainTextService {
    @Autowired
    private WorkflowMainTextMapper workflowMainTextMapper;
    @Autowired
    private ITemplateService templateService;
    @Autowired
    private ITemplateMainTextService templateMainTextService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private FileServerContext fileServerContext;
    @Autowired
    private OfficeToPdfService officeToPdfService;
    @Autowired
    private ISyncPush syncPush;

    private static final String SAVE_LOCK_PREFIX = "save:maintext:lock:";
    private static final String UPLOAD_LOCK_PREFIX = "upload:maintext:lock:";
    private static final String STAMP_LOCK_PREFIX = "stamp:maintext:lock:";
    private static final String PDF_EXTEND = "pdf";

    /**
     * 查询正文
     *
     * @param id 正文主键
     * @return 正文
     */
    @Override
    public WorkflowMainText getWorkflowMainTextById(String id) {
        return workflowMainTextMapper.selectWorkflowMainTextById(id);
    }

    /**
     * 根据业务ID查询正文
     *
     * @param businessId 业务ID
     * @return
     */
    @Override
    public WorkflowMainText getByBusinessId(String businessId) {
        if (StringUtils.isBlank(businessId)) {
            return null;
        }
        return workflowMainTextMapper.getByBusinessId(businessId);
    }

    /**
     * 获取正文信息
     *
     * @param param 查询参数
     * @return
     */
    @Override
    public MainInfoResult getMainInfo(MainTextParam param) {
        if (param == null || StringUtils.isBlank(param.getBusinessId()) || StringUtils.isBlank(param.getTemplateId())) {
            throw new BaseException("参数错误！");
        }
        TemplateMainText templateMainText = templateMainTextService.getByTemplateId(param.getTemplateId());
        if (templateMainText == null) {
            throw new BaseException("正文模板不存在！");
        }
        WorkflowMainText workflowMainText = getByBusinessId(param.getBusinessId());
        if (workflowMainText == null) {
            MainInfoResult res = new MainInfoResult();
            res.setMainTextType(templateMainText.getType());
            return res;
        }
        boolean isStamp = StringUtils.equals(Constants.YES_VALUE, workflowMainText.getStampStatus());
        MainInfoResult result = WorkFileSourceTargetMapper.INSTANCE.toMainInfoResult(workflowMainText);
        // 已盖章的文，直接使用fileId；
        String fileId = isStamp ? workflowMainText.getFileId() : StringUtils.isBlank(workflowMainText.getOriFileId())
                ? workflowMainText.getFileId() : workflowMainText.getOriFileId();
        FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileId);
        result.setFileName(fileStorage == null ? null : fileStorage.getFileName() + Constants.DOT + fileStorage.getExtendName());
        result.setFileId(fileId);
        result.setMainTextType(templateMainText.getType());
        return result;
    }

    /**
     * 新增正文
     *
     * @param workflowMainText 正文
     * @return 结果
     */
    @Override
    public int saveWorkflowMainText(WorkflowMainText workflowMainText) {
        workflowMainText.setId(IdUtils.fastSimpleUUID());
        workflowMainText.setCreateId(SecurityUtils.getUserId());
        workflowMainText.setCreateTime(DateUtils.getNowDate());
        return workflowMainTextMapper.insertWorkflowMainText(workflowMainText);
    }

    /**
     * 修改正文
     *
     * @param workflowMainText 正文
     * @return 结果
     */
    @Override
    public int updateWorkflowMainText(WorkflowMainText workflowMainText) {
        workflowMainText.setUpdateId(SecurityUtils.getUserId());
        workflowMainText.setUpdateTime(DateUtils.getNowDate());
        return workflowMainTextMapper.updateWorkflowMainText(workflowMainText);
    }

    /**
     * 异步任务更新正文（任务执行时，无法获取当前登录人信息，需要通过参数传递）
     *
     * @param param
     * @return
     */
    @Override
    public int asyncUpdateWorkflowMainText(MainTextParam param) {
        WorkflowMainText workflowMainText = new WorkflowMainText();
        workflowMainText.setId(param.getId());
        workflowMainText.setUpdateId(param.getOperatorId());
        workflowMainText.setUpdateTime(DateUtils.getNowDate());
        return workflowMainTextMapper.updateWorkflowMainText(workflowMainText);
    }

    /**
     * 上传正文
     *
     * @param mainTextParam 上传参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadMainText(MainTextParam mainTextParam) {
        if (StringUtils.isBlank(mainTextParam.getBusinessId()) || StringUtils.isBlank(mainTextParam.getFileId())) {
            throw new BaseException("参数错误");
        }
        String businessId = mainTextParam.getBusinessId();
        String key = UPLOAD_LOCK_PREFIX + businessId;
        try {
            redisLock.lock(key);
            String fileId = mainTextParam.getFileId();
            FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileId);
            if (fileStorage == null) {
                throw new BaseException("文件不存在");
            }
            String templateId = mainTextParam.getTemplateId();
            Template template = templateService.getTemplateById(templateId);
            if (template == null || StringUtils.equals(Constants.NO_VALUE, template.getMainTextFlag())) {
                log.info("## 流程正文，当前模板没有启用正文，无需处理！businessId：{}", businessId);
                return;
            }
            TemplateMainText mainText = templateMainTextService.getByTemplateId(templateId);
            if (mainText == null || StringUtils.equals(MainTextTypeEnum.BOOKMARK.getCode(), mainText.getType())) {
                log.info("## 流程正文，当前模板书签替换，不支持手动上传！businessId：{}", businessId);
                return;
            }
            boolean isPdf = StringUtils.equals(PDF_EXTEND, fileStorage.getExtendName());
            String oriFileId = isPdf ? fileId : null;
            // 把文件转换为pdf，以便后续盖章
            if (!isPdf) {
                oriFileId = convertPdf(fileStorage);
            }
            // 删除已存在的，确保正文唯一
            workflowMainTextMapper.deleteByBusinessId(businessId);
            // 插入新
            WorkflowMainText workflowMainText = new WorkflowMainText();
            workflowMainText.setFileId(fileId);
            workflowMainText.setBusinessId(businessId);
            workflowMainText.setOriFileId(StringUtils.isBlank(oriFileId) ? null : fileId);
            saveWorkflowMainText(workflowMainText);
        } finally {
            redisLock.unlock(key);
        }
    }

    /**
     * 删除正文
     *
     * @param businessId 业务ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeMainText(String businessId) {
        if (StringUtils.isBlank(businessId)) {
            log.error("## 删除正文，参数错误，businessId：{}，", businessId);
            return 0;
        }
        WorkflowMainText mainText = workflowMainTextMapper.getByBusinessId(businessId);
        if (mainText == null) {
            log.error("## 删除正文，正文不存在，businessId：{}，", businessId);
            return 0;
        }
        try {
            String fileId = mainText.getFileId();
            if (StringUtils.isNotBlank(fileId)) {
                FileQO fileQO = new FileQO();
                fileQO.setFileId(fileId);
                fileService.deleteFile(fileQO);
            }
            workflowMainTextMapper.deleteWorkflowMainTextById(mainText.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return 1;
    }

    /**
     * 保存正文
     *
     * @param templateId 模板ID
     * @param businessId 业务ID
     * @param data       书签数据
     */
    @Override
    public void saveMainText(String templateId, String businessId, BookmarkData data) {
        if (StringUtils.isBlank(businessId) || StringUtils.isBlank(templateId) || data == null) {
            log.error("## 流程正文，参数错误，businessId：{}，templateId：{}, data：{}", businessId, templateId, JSON.toJSONString(data));
            return;
        }
        String key = SAVE_LOCK_PREFIX + businessId;
        try {
            redisLock.lock(key);
            Template template = templateService.getTemplateById(templateId);
            if (template == null || StringUtils.equals(Constants.NO_VALUE, template.getMainTextFlag())) {
                log.info("## 流程正文，当前模板没有启用正文，无需处理！templateId：{}", templateId);
                return;
            }
            TemplateMainText mainText = templateMainTextService.getByTemplateId(templateId);
            if (mainText == null || StringUtils.equals(MainTextTypeEnum.UPLOAD.getCode(), mainText.getType())) {
                log.info("## 流程正文，当前模板为用户上传，无需处理！templateId：{}", templateId);
                return;
            }
            if (StringUtils.isBlank(mainText.getTemplateId())) {
                log.error("## 流程正文模板配置错误，模板id：{}", mainText.getId());
                return;
            }
            // 书签替换
            String fileId = fileService.wordBookmarks(mainText.getFileId(), data);

            // 处理正文记录
            WorkflowMainText workflowMainText = getByBusinessId(businessId);
            if (workflowMainText == null) {
                workflowMainText = new WorkflowMainText();
                workflowMainText.setBusinessId(businessId);
                workflowMainText.setFileId(fileId);
                workflowMainText.setOriFileId(fileId);
                saveWorkflowMainText(workflowMainText);
            } else {
                workflowMainText.setFileId(fileId);
                workflowMainText.setOriFileId(fileId);
                updateWorkflowMainText(workflowMainText);
            }
            // 延迟处理转换pdf
            delayConvertPdf(businessId, fileId, workflowMainText.getId(), SecurityUtils.getUserId());
        } finally {
            redisLock.unlock(key);
        }
    }

    /**
     * 正文盖章
     *
     * @param param 盖章参数
     * @return 返回盖章后的文件id
     */
    @Override
    public String stamp(MainStampParam param) {
        if (param == null || StringUtils.isBlank(param.getBusinessId()) || CollectionUtils.isEmpty(param.getSealInfos())) {
            log.error("## 盖章失败，参数错误！");
            throw new BaseException("盖章失败！");
        }
        String businessId = param.getBusinessId();
        String key = STAMP_LOCK_PREFIX + businessId;
        try {
            redisLock.lock(key);
            WorkflowMainText workflowMainText = getByBusinessId(businessId);
            if (workflowMainText == null || StringUtils.isBlank(workflowMainText.getFileId())) {
                log.error("## 盖章失败，找不到正文记录，businessId：{}", businessId);
                throw new BaseException("盖章失败！");
            }
            // 已盖章的，不重复盖章
            if (StringUtils.equals(Constants.YES_VALUE, workflowMainText.getStampStatus())) {
                log.info("## 已盖章，不重复！businessId：{}", businessId);
                return workflowMainText.getOriFileId();
            }
            // 判断是否可以盖章（可以盖章的文件，在写入正文记录时，会填充oriFileId，若不支持，则此值为空，此处为了提高性能，不做额外的文件类型判断逻辑）
            if (StringUtils.isBlank(workflowMainText.getOriFileId())) {
                log.error("## 盖章失败，当前文件不支持盖章，businessId：{}", businessId);
                throw new BaseException("当前文件不支持盖章！");
            }
            String fileName = StringUtils.isBlank(param.getTitle()) ? "正文" : param.getTitle();
            String fileId = workflowMainText.getOriFileId(); // 默认使用原始文件进行盖章
            List<MainStampParam.SealInfo> sealInfos = param.getSealInfos();
            for (MainStampParam.SealInfo info : sealInfos) {
                String sealFileId = info.getSealFileId();
                fileId = pdfService.stamp(fileId, fileName, sealFileId, info.getPositionX(), info.getPositionY(), info.getSealPage(), true);
            }
            // 更新盖章状态
            workflowMainText.setFileId(fileId);
            workflowMainText.setStampStatus(Constants.YES_VALUE);
            workflowMainText.setStampUserId(SecurityUtils.getUserId());
            workflowMainText.setStampTime(DateUtils.getNowDate());
            updateWorkflowMainText(workflowMainText);
            return fileId;
        } finally {
            redisLock.unlock(key);
        }
    }

    /**
     * 还原印章（把已盖章的文件，替换为原始文件）
     *
     * @param businessId 业务ID
     * @return
     */
    @Override
    public String restoreSeal(String businessId) {
        if (StringUtils.isBlank(businessId)) {
            throw new BaseException("参数错误！");
        }
        WorkflowMainText workflowMainText = getByBusinessId(businessId);
        if (workflowMainText == null) {
            throw new BaseException("还原印章失败，此正文不存在！");
        }
        workflowMainText.setFileId(workflowMainText.getOriFileId());
        workflowMainTextMapper.restoreSeal(workflowMainText);
        return workflowMainText.getOriFileId();
    }

    /**
     * 转换为pdf
     *
     * @param fileStorage 文件存储信息
     * @return
     */
    private String convertPdf(FileStorage fileStorage) {
        if (!StorageContants.DOCS.contains(fileStorage.getExtendName())) {
            return null;
        }
        Previewer previewer = fileServerContext.getPreviewer(fileStorage.getStorageType());
        ConvertFileDTO convertFileDTO = new ConvertFileDTO();
        PreviewFile pre = new PreviewFile();
        pre.setPreviewUrl(fileStorage.getFileUrl());
        convertFileDTO.setInputStream(previewer.getInputStream(pre));
        convertFileDTO.setStorageType(fileStorage.getStorageType());
        convertFileDTO.setFileUrl(fileStorage.getFileUrl());
        convertFileDTO.setFileId(fileStorage.getFileId());
        return officeToPdfService.convertPdf(convertFileDTO);
    }

    /**
     * 延迟转换pdf
     *
     * @param businessId
     * @param fileId
     * @param mainTextId
     */
    private void delayConvertPdf(String businessId, String fileId, String mainTextId, String operationId) {
        CompletableFuture.runAsync(() -> {
            try {
                // 延迟2秒，等待事务处理完毕后，再执行转换pdf
                Thread.sleep(2000);
                FileStorage fileStorage = fileStorageService.getFileStorageByFileId(fileId);
                ConvertPdfDTO convertPdfDTO = new ConvertPdfDTO();
                convertPdfDTO.setMainTextId(mainTextId);
                convertPdfDTO.setFileId(fileId);
                convertPdfDTO.setFileUrl(fileStorage.getFileUrl());
                convertPdfDTO.setStorageType(fileStorage.getStorageType());
                convertPdfDTO.setBusinessId(businessId);
                convertPdfDTO.setOperatorId(operationId);
                syncPush.push(QueueEnum.ASYNC_NAME_MAIN_TEXT_CONVERT_QUEUE.getConsumerBeanName(), JSONObject.toJSONString(convertPdfDTO), QueueEnum.ASYNC_NAME_MAIN_TEXT_CONVERT_QUEUE.getQueueName(), operationId);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }, DataPassPools.pool);
    }
}
