package com.ruoyi.workfile.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.file.convert.module.ConvertFileDTO;
import com.ruoyi.file.convert.service.OfficeToPdfService;
import com.ruoyi.file.storage.context.FileServerContext;
import com.ruoyi.file.storage.preview.Previewer;
import com.ruoyi.file.storage.preview.domain.PreviewFile;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.tools.lock.RedisLock;
import com.ruoyi.workfile.domain.WorkflowMainText;
import com.ruoyi.workfile.module.ConvertPdfDTO;
import com.ruoyi.workfile.module.MainTextParam;
import com.ruoyi.workfile.service.IWorkflowMainTextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 正文转换消费者
 *
 * @Author wocurr.com
 */
@Slf4j
@Service("convertMainTextConsumer")
public class ConvertMainTextConsumer implements IAsyncHandler {

    @Autowired
    private FileServerContext fileServerContext;
    @Autowired
    private OfficeToPdfService officeToPdfService;
    @Autowired
    private IWorkflowMainTextService mainTextService;
    @Autowired
    private RedisLock redisLock;

    private static final String LOCK_KEY = "convert:main:textConsumer:";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAsync(AsyncLog asyncLog) {
        log.info("## 开始处理正文转换，消息内容：{}", asyncLog);
        String msg = asyncLog.getMessageContent();
        if (StringUtils.isBlank(msg)) {
            log.error("## 处理正文转换，消息内容不能为空！");
            return;
        }
        ConvertPdfDTO convertPdf = JSONObject.parseObject(msg, ConvertPdfDTO.class);
        if (StringUtils.isBlank(convertPdf.getBusinessId())) {
            log.error("处理正文转换，业务id不能为空！");
            return;
        }
        WorkflowMainText mainText = mainTextService.getWorkflowMainTextById(convertPdf.getMainTextId());
        if (mainText == null) {
            log.error("## 处理正文转换pdf，正文为空，businessId：{}", convertPdf.getBusinessId());
            throw new RuntimeException("正文不能为空！");
        }
        try {
            // 这里加锁，避免重复相同任务消费，导致office负载问题
            String lockKey = LOCK_KEY + convertPdf.getBusinessId();
            redisLock.doLock(lockKey, () -> {
                Previewer previewer = fileServerContext.getPreviewer(convertPdf.getStorageType());
                ConvertFileDTO convertFileDTO = new ConvertFileDTO();
                PreviewFile pre = new PreviewFile();
                pre.setPreviewUrl(convertPdf.getFileUrl());
                convertFileDTO.setInputStream(previewer.getInputStream(pre));
                convertFileDTO.setStorageType(convertPdf.getStorageType());
                convertFileDTO.setFileUrl(convertPdf.getFileUrl());
                convertFileDTO.setFileId(convertPdf.getFileId());
                String pdfUrl = officeToPdfService.convertPdf(convertFileDTO);
                log.info("## 异步处理转换pdf完成，pdfUrl：{}", pdfUrl);
                // 更新
                MainTextParam mainTextParam = new MainTextParam();
                mainTextParam.setId(mainText.getId());
                mainTextParam.setOperatorId(convertPdf.getOperatorId());
                mainTextService.asyncUpdateWorkflowMainText(mainTextParam);
            });
        } catch (Exception e) {
            log.error("## 处理正文转换失败", e.getMessage());
            throw new RuntimeException("## 处理正文转换失败:" + e.getMessage());
        }
    }
}
