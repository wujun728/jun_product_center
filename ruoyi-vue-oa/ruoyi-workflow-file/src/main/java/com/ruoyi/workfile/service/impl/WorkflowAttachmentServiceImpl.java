package com.ruoyi.workfile.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.workfile.domain.WorkflowAttachment;
import com.ruoyi.workfile.mapper.WorkflowAttachmentMapper;
import com.ruoyi.workfile.module.BizAttachmentDTO;
import com.ruoyi.workfile.service.IWorkflowAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程业务附件Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class WorkflowAttachmentServiceImpl implements IWorkflowAttachmentService {
    @Autowired
    private WorkflowAttachmentMapper workflowAttachmentMapper;
    @Autowired
    private IFileStorageService fileStorageService;

    /**
     * 新增流程业务附件
     *
     * @param workflowAttachment 流程业务附件
     * @return 结果
     */
    @Override
    public int saveWorkflowAttachment(WorkflowAttachment workflowAttachment) {
        workflowAttachment.setId(IdUtils.fastSimpleUUID());
        workflowAttachment.setCreateTime(DateUtils.getNowDate());
        workflowAttachment.setCreateId(SecurityUtils.getUserId());
        return workflowAttachmentMapper.insertWorkflowAttachment(workflowAttachment);
    }

    /**
     * 删除附件
     *
     * @param fileId 文件ID
     * @return
     */
    @Override
    public int remove(String fileId) {
        WorkflowAttachment workflowAttachment = new WorkflowAttachment();
        workflowAttachment.setFileId(fileId);
        workflowAttachment.setDelFlag(Constants.YES_VALUE);
        workflowAttachment.setUpdateTime(DateUtils.getNowDate());
        workflowAttachment.setUpdateId(SecurityUtils.getUserId());
        return workflowAttachmentMapper.remove(workflowAttachment);
    }

    /**
     * 查询流程业务附件列表
     *
     * @param businessId 业务ID
     * @return
     */
    @Override
    public List<BizAttachmentDTO> listAttachment(String businessId) {
        if (StringUtils.isBlank(businessId)) {
            return null;
        }
        List<WorkflowAttachment> list = workflowAttachmentMapper.selectWorkflowAttachmentByBusinessId(businessId);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<String> fileIds = list.stream().map(WorkflowAttachment::getFileId).collect(Collectors.toList());
        List<FileStorage> fileStorages = fileStorageService.listByIds(fileIds);
        if (CollectionUtils.isEmpty(fileStorages)) {
            return null;
        }
        return fileStorages.stream()
                .map(fileStorage -> buildBizAttachmentDTO(fileStorage, businessId))
                .collect(Collectors.toList());
    }

    /**
     * 构建业务附件DTO
     *
     * @param fileStorage 文件存储
     * @param businessId  业务ID
     * @return
     */
    private BizAttachmentDTO buildBizAttachmentDTO(FileStorage fileStorage, String businessId) {
        BizAttachmentDTO dto = new BizAttachmentDTO();
        dto.setBusinessId(businessId);
        dto.setFileId(fileStorage.getFileId());
        dto.setFileName(fileStorage.getFileName() + Constants.DOT + fileStorage.getExtendName());
        dto.setFileExt(fileStorage.getExtendName());
        dto.setFileSize(fileStorage.getFileSize());
        dto.setIdentifier(fileStorage.getIdentifier());
        dto.setSort(fileStorage.getSort());
        return dto;
    }
}
