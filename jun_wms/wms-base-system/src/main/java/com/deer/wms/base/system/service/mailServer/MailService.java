package com.deer.wms.base.system.service.mailServer;

import com.deer.wms.common.core.domain.AjaxResult;

public interface MailService {
    void sendAttachmentsMail(String subject,String content,String filePath);
    void sendMail(String to,String subject,String content);
    void analysisSendMail(AjaxResult filePath, String subject, String content);
}
