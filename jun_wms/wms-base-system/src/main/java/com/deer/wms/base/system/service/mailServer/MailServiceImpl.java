package com.deer.wms.base.system.service.mailServer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.system.domain.SysUser;
import com.deer.wms.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.properties.from}")
    private String from;
    @Autowired
    private ISysUserService userService;
    @Value("${deer.profile}")
    private String filePath;

    /**
     * 发送带附件的邮件
     * @param subject 主题
     * @param content 内容
     * @param filePath 文件路径
     */
    @Override
    public void sendAttachmentsMail(String subject, String content, String filePath) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            List<SysUser> users = userService.findEmail();
            for(SysUser user : users) {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(from);
                helper.setTo(user.getEmail());
                helper.setSubject(subject);
                helper.setText(content);
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                //添加多个附件可以使用多条
                //helper.addAttachment(fileName,file);
                helper.addAttachment(fileName, file);
                mailSender.send(message);
            }
            System.out.println("带附件的邮件发送成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
    }

    @Override
    public void sendMail(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
    }

    @Override
    public void analysisSendMail(AjaxResult filePath1 , String subject, String content) {
        Object file = AjaxResult.success(filePath1);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(file);
        String filePathLocation = filePath+"\\download\\"+jsonObject.getJSONObject("data").getString("msg");
        sendAttachmentsMail(subject,content,filePathLocation);
    }
}
